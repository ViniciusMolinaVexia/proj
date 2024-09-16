/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.EapMultiEmpresa;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.LogInterfaceVo;
import br.com.nextage.rmaweb.ws.cpweb.UnidadeMedida;
import br.com.nextage.rmaweb.ws.cpweb.*;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel H. Parisotto
 */
public class SincReservaService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    @Context
    HttpServletRequest request;


    public void sincronizar() {
        try {
            SincRegistroService sincRegistroService = new SincRegistroService();

            List<SincRegistro> listaSincRegistro = sincRegistroService.listar(Constantes.SINC_RESERVA);

            if (listaSincRegistro != null) {
                List<Integer> ids = new ArrayList<Integer>();
                for (SincRegistro sr : listaSincRegistro) {
                    ids.add(sr.getIdRegistro());
                }

                List<RmMaterial> listaRmMaterial = null;
                if (ids != null && !ids.isEmpty()) {
                    listaRmMaterial = listarRmMateriais(ids);
                }

                if (listaRmMaterial != null && !listaRmMaterial.isEmpty()) {
                    for (RmMaterial rm : listaRmMaterial) {
                        Info info = enviarReserva(rm, "SincRegistro", false);

                        //Se o reenvio deu sucesso então desativa o SincRegistro
                        if (Info.INFO_001.equals(info.getCodigo())) {
                            for (SincRegistro sr : listaSincRegistro) {
                                if (sr.getIdRegistro().equals(rm.getRmMaterialId())) {
                                    sincRegistroService.desativar(sr);
                                    break;
                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError("", this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
    }

    public Info enviarReserva(RmMaterial rmMaterial, String nomeUsuario, boolean gerarSincRegistro) {
        Info info = new Info();
        String confCaminhoUrl = ConfiguracaoSingleton.getConfiguracao().getCaminhoUrlCpweb();
        SincRegistroService sincRegistroService = new SincRegistroService();
        LogInterfaceVo logVo = null;
        try {
            SincEquipamentoVo equipamentoVo = new SincEquipamentoVo();

            //Configura TipoEquipamento
            TipoEquipamento tipoEquipamento = new TipoEquipamento();
            tipoEquipamento.setDescricao(rmMaterial.getMaterial().getNome());
            tipoEquipamento.setCodigo(rmMaterial.getMaterial().getCodigo());
            tipoEquipamento.setHierarquia(rmMaterial.getMaterial().getHierarquia());
            equipamentoVo.setTipoEquipamento(tipoEquipamento);
            equipamentoVo.setPatrimoniado(rmMaterial.getMaterial().getPatrimoniado());

            //Configura Estoque
            LocalEstoque estoque = new LocalEstoque();
            estoque.setDescricao(rmMaterial.getDeposito().getNome());
            estoque.setCodigo(rmMaterial.getDeposito().getCodigo());
            equipamentoVo.setLocalEstoque(estoque);
            if (rmMaterial.getPrefixoEquipamento() != null) {
                equipamentoVo.setPrefixoEquipamento(rmMaterial.getPrefixoEquipamento());
            }

            //Configura Estoque
            equipamentoVo.setQuantidade(rmMaterial.getQuantidade());

            //Configura Unidade Medida
            UnidadeMedida unidadeMedida = new UnidadeMedida();
            unidadeMedida.setDescricao(rmMaterial.getMaterial().getUnidadeMedida().getDescricao());
            equipamentoVo.setUnidadeMedida(unidadeMedida);
            equipamentoVo.setCodigoRequisicao(rmMaterial.getRm().getNumeroRm());

            if (rmMaterial.getPessoaResponsavel() != null && !rmMaterial.getPessoaResponsavel().getReferencia().equals(rmMaterial.getCfResponsavel())) {
                equipamentoVo.setReferenciaCorresponsavel(rmMaterial.getPessoaResponsavel().getReferencia());
            } else if (rmMaterial.getCfResponsavel() != null && !rmMaterial.getCfResponsavel().isEmpty()) {
                equipamentoVo.setReferenciaCorresponsavel(rmMaterial.getCfResponsavel());
            }

            if (rmMaterial.getRm().getRequisitante() != null) {
                equipamentoVo.setReferenciaPessoa(rmMaterial.getRm().getRequisitante().getReferencia());
            }

            if (rmMaterial.getRmMaterialId() != null) {
                equipamentoVo.setIdentificadorRmMaterial(rmMaterial.getRmMaterialId());
            }

            if (nomeUsuario != null) {
                equipamentoVo.setNomeUsuario(nomeUsuario);
            }


            // Limpa as propriedades do TOMCAT
            // Pois a sincronização com o SAP seta estas propriedades
            // E quando o sistema necessita fazer outra requisição, o mesmo
            // tenta acessar o proxy que era setado, causando erro
            System.setProperty("proxySet", "false");
            System.clearProperty("proxySet");
            System.clearProperty("http.proxyHost");
            System.clearProperty("http.proxyPort");
            System.clearProperty("http.proxyUser");
            System.clearProperty("http.proxyPassword");

            Boolean habilitaEapMultiEmpresa = ConfiguracaoSingleton.getConfiguracao().getHabilitaEapMultiEmpresa();
            if (habilitaEapMultiEmpresa != null && habilitaEapMultiEmpresa == true) {
                if (rmMaterial.getRm() != null && rmMaterial.getRm().getRequisitante() != null) {
                    equipamentoVo.setFuncionarioEap(obterCodigoEapMultiEmpresaPessoa(rmMaterial.getRm().getRequisitante().getPessoaId()));
                }
                if (equipamentoVo.getLocalEstoque() != null) {
                    equipamentoVo.getLocalEstoque().setLocalEstoqueEap(obterCodigoEapMultiEmpresaDeposito(rmMaterial.getDeposito().getDepositoId()));
                }
            }


            SincEstoque_Service sincEstoque = new SincEstoque_Service(new URL(confCaminhoUrl.trim()));
            //br.com.nextage.rmaweb.ws.Info infoCp = sincEstoque.getSincEstoquePort().enviaEquipamento(equipamentoVo);

            br.com.nextage.rmaweb.ws.cpweb.Info infoCp = sincEstoque.getSincEstoquePort().enviaReserva(equipamentoVo);

            if (infoCp != null) {
                info.setCodigo(infoCp.getCodigo());
                info.setErro(infoCp.getErro());
                info.setMensagem(infoCp.getMensagem());
                info.setTipo(infoCp.getTipo());
                info.setIdObjetoSalvo(rmMaterial.getRmMaterialId());
                if (info.getErro() != null && gerarSincRegistro) {
                    sincRegistroService.salvar(info, Constantes.SINC_RESERVA, Constantes.SISTEMA_CPWEB);
                }
                logVo = new LogInterfaceVo(info);
            }
//            else {
//                infoCp = sincEstoque.getSincEstoquePort().enviaReserva(equipamentoVo);
//            }
        } catch (Exception e) {
            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(null));
            String msg = rb.getString(Constantes.ERRO_COMUNICACAO_CPWEB_CONTATE_ADMINISTRADOR_I18N);
            info = Info.GetError(msg);
            String rma = "";
            if (rmMaterial.getRm() != null && rmMaterial.getRm().getNumeroRm() != null) {
                rma = ";" + rmMaterial.getRm().getNumeroRm() + ";";
            }
            String erro = e.getCause() != null && e.getCause().getMessage() != null ? e.getMessage() + " - " + e.getCause().getMessage() : e.getMessage();
            info.setErro(erro);
            info.setIdObjetoSalvo(rmMaterial.getRmMaterialId());
            logVo = new LogInterfaceVo(info);
            if (gerarSincRegistro) {
                sincRegistroService.salvar(info, Constantes.SINC_RESERVA, Constantes.SISTEMA_CPWEB, null, rma);
            }
            logger.error(ResourceLogUtil.createMessageError("", this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            e.printStackTrace();
        }
        if (gerarSincRegistro) {
            //Gerando Log de interface
            logVo.setObjeto(rmMaterial);
            LogInterfaceService.inserirLog(Constantes.SISTEMA_CPWEB, Constantes.INTERFACE_ENVIAR_RESERVA, getUsuarioNome(), logVo);
        }
        return info;
    }


    public List<RmMaterial> listarRmMateriais(List<Integer> ids) {
        List<RmMaterial> rmMateriais = new ArrayList<>();
        GenericDao<RmMaterial> genericDao = new GenericDao<>();

        try {
            String aliasRm = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);
            String aliasTipoRequisicao = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.TIPO_REQUISICAO);
            String aliasRequisitante = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.REQUISITANTE);
            String aliasPessoaResponsavel = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.PESSOA_RESPONSAVEL);
            String aliasDeposito = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.DEPOSITO_ID);
            String aliasMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL);
            String aliasFornecedor = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.FORNECEDOR_ID);
            String aliasUnidadeMedida = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL, Material.UNIDADE_MEDIDA);
            String aliasTipoMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL, Material.TIPO_MATERIAL);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
            propriedades.add(new Propriedade(RmMaterial.CF_RESPONSAVEL));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));
            propriedades.add(new Propriedade(RmMaterial.PREFIXO_EQUIPAMENTO));

            //RM
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));

            //REQUISITANTE
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasRequisitante));

            //REQUISITANTE
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasPessoaResponsavel));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasPessoaResponsavel));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasPessoaResponsavel));

            //DEPOSITO
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            //MATERIAL
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.PATRIMONIADO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.IS_SERVICO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.STATUS, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.HIERARQUIA, Material.class, aliasMaterial));

            //Fornecedor
            propriedades.add(new Propriedade(br.com.nextage.rmaweb.entitybean.Fornecedor.FORNECEDOR_ID, br.com.nextage.rmaweb.entitybean.Fornecedor.class, aliasFornecedor));
            propriedades.add(new Propriedade(br.com.nextage.rmaweb.entitybean.Fornecedor.CODIGO, br.com.nextage.rmaweb.entitybean.Fornecedor.class, aliasFornecedor));

            //UNIDADE MEDIDA
            propriedades.add(new Propriedade(br.com.nextage.rmaweb.entitybean.UnidadeMedida.UNIDADE_MEDIDA_ID, br.com.nextage.rmaweb.entitybean.UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(br.com.nextage.rmaweb.entitybean.UnidadeMedida.DESCRICAO, br.com.nextage.rmaweb.entitybean.UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(br.com.nextage.rmaweb.entitybean.UnidadeMedida.SIGLA, br.com.nextage.rmaweb.entitybean.UnidadeMedida.class, aliasUnidadeMedida));

            //TIPO MATERIAL
            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM_MATERIAL_ID, ids, Filtro.IN));

            rmMateriais = genericDao.listarByFilter(propriedades, null, RmMaterial.class, Constantes.NO_LIMIT, nxCriterion);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError("SincResenva", this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return rmMateriais;
    }

    private String obterCodigoEapMultiEmpresaDeposito(Integer depositoId) {

        GenericDao<Deposito> genericDao = new GenericDao<>();

        if (depositoId == null) {
            return null;
        }

        Deposito deposito = new Deposito();

        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));
        propriedades.add(new Propriedade(Deposito.NOME));
        propriedades.add(new Propriedade(Deposito.CODIGO));


        NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Deposito.DEPOSITO_ID, depositoId, Filtro.EQUAL));

        try {
            deposito = genericDao.selectUnique(propriedades, Deposito.class, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        if (deposito != null && deposito.getEapMultiEmpresa() != null && deposito.getEapMultiEmpresa().getCodigo() != null) {
            return deposito.getEapMultiEmpresa().getCodigo();
        }

        return null;
    }

    private String obterCodigoEapMultiEmpresaPessoa(Integer pessoaId) {

        if (pessoaId == null) {
            return null;
        }

        GenericDao<Pessoa> genericDao = new GenericDao<>();

        Pessoa pessoa = new Pessoa();

        String aliasEapMultiEmpresa = NxCriterion.montaAlias(Pessoa.ALIAS_CLASSE, Pessoa.EAP_MULTI_EMPRESA);

        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
        propriedades.add(new Propriedade(Pessoa.NOME));
        propriedades.add(new Propriedade(Pessoa.REFERENCIA));

        propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
        propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
        propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));

        NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, pessoaId, Filtro.EQUAL));

        try {
            pessoa = genericDao.selectUnique(propriedades, Pessoa.class, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        if (pessoa != null && pessoa.getEapMultiEmpresa() != null && pessoa.getEapMultiEmpresa().getCodigo() != null) {
            return pessoa.getEapMultiEmpresa().getCodigo();
        }

        return null;
    }

    public String getUsuarioNome() {
        String nome = LoginService.getUsuarioLogado(request).getNome();
        if (request == null) {
            nome = "LISTENER";
        }
        return nome;
    }
}
