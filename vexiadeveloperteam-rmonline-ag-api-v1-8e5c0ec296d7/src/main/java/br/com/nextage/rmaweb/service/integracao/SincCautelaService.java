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
 * @author l.pordeus
 */
public class SincCautelaService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    @Context
    HttpServletRequest request;


    public void sincronizar() {
        try {
            SincRegistroService sincRegistroService = new SincRegistroService();
            List<SincRegistro> listaSincRegistro = sincRegistroService.listar(Constantes.SINC_GERA_CAUTELA);

            if (listaSincRegistro != null) {
                List<RmMaterial> listaMaterial;
                List<Integer> ids;

                for (SincRegistro sr : listaSincRegistro) {
                    try {
                        if (sr.getRmMaterialConcatena() != null && sr.getRmMaterialConcatena().trim().length() > 0) {

                            ids = new ArrayList<Integer>();
                            String[] strIds = sr.getRmMaterialConcatena().split(";");
                            for (String id : strIds) {
                                if (id.trim().length() > 0) {
                                    ids.add(Integer.parseInt(id));
                                }
                            }
                            listaMaterial = listarRmMateriais(ids);

                            Info info = enviaCautelaCp(listaMaterial, false);

                            //Só desativa os registros se sincronizar com sucesso
                            if (Info.INFO_001.equals(info.getCodigo())) {
                                sincRegistroService.desativar(sr);
                            }
                        }
                    }catch (Exception ex){
                        ex.printStackTrace();
                        logger.error(ResourceLogUtil.createMessageError("SincCautelaService", this.getClass().getName(), Util.getNomeMetodoAtual(), ex));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(ResourceLogUtil.createMessageError("SincCautelaService", this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
    }

    /**
     * Cria uma thread para sincronizar as cautelas com o CPWEB Não é necessário
     * esperar o retorno do método, por isso foi utilizada thread
     *
     * @param listaMaterial
     * @return
     * @author Alyson X. Leite
     */
    public Info enviaCautelaCpThread(final List<RmMaterial> listaMaterial) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                enviaCautelaCp(listaMaterial,true);
            }
        });
        t.start();
        return Info.GetSuccess();
    }

    /**
     * Envia uma lista de materiais para o CPWEB O mesmo gera as cautelas de
     * saida dos equipamentos
     *
     * @param listaMaterial
     * @return
     * @author Alyson X. Leite
     */
    public Info enviaCautelaCp(List<RmMaterial> listaMaterial,Boolean gerarSincRegistro) {
        Info info = Info.GetSuccess();
        LogInterfaceVo logVo = null;
        try {
            List<SincEquipamentoVo> lista = new ArrayList<>();
            String confCaminhoUrl = ConfiguracaoSingleton.getConfiguracao().getCaminhoUrlCpweb();
            for (RmMaterial rmMaterial : listaMaterial) {
                // Somente material cautelavel vai para o CPWEB
                if (rmMaterial != null
                        && rmMaterial.getMaterial() != null
                        && rmMaterial.getMaterial().getTipoMaterial() != null
                        && rmMaterial.getMaterial().getTipoMaterial().getCodigo() != null
                        && rmMaterial.getMaterial().getTipoMaterial().getCodigo().equals(Constantes.CAUTELAVEL)
                        && rmMaterial.getQuantidade() > 0.0) {

                    SincEquipamentoVo equipamentoVo = new SincEquipamentoVo();
                    // Verifico se o equipamento é EPI ou não
                    String isEpi = RmaService.isEpi(rmMaterial) ? Constantes.SIM_ABREVIADO : Constantes.NAO_ABREVIADO;
                    equipamentoVo.setEpi(isEpi);

                    //Configura TipoEquipamento
                    TipoEquipamento tipoEquipamento = new TipoEquipamento();
                    tipoEquipamento.setDescricao(rmMaterial.getMaterial().getNome());
                    tipoEquipamento.setCodigo(rmMaterial.getMaterial().getCodigo());
                    if(rmMaterial.getMaterial().getHierarquia() == null || rmMaterial.getMaterial().getHierarquia().trim().length() == 0) {
                        MaterialService matService = new MaterialService();
                        tipoEquipamento.setHierarquia(matService.getHierarquiaByMaterial(rmMaterial.getMaterial()));
                    }else{
                        tipoEquipamento.setHierarquia(rmMaterial.getMaterial().getHierarquia());
                    }
                    equipamentoVo.setTipoEquipamento(tipoEquipamento);
                    equipamentoVo.setPatrimoniado(rmMaterial.getMaterial().getPatrimoniado());
                    if (rmMaterial.getPrefixoEquipamento() != null) {
                        equipamentoVo.setPrefixoEquipamento(rmMaterial.getPrefixoEquipamento());
                    }

                    // Pessoa responsável
                    //Se caso existir Pessoa Responsavel e a referencia dela for diferente da referencia da pessoa responsavel,
                    //então eu seto a pessoa responsavel, pois a cfResponsavel pode ter sido alterada em uma sincronização do pessoa.
                    if (rmMaterial.getPessoaResponsavel() != null && !rmMaterial.getPessoaResponsavel().getReferencia().equals(rmMaterial.getCfResponsavel())) {
                        equipamentoVo.setReferenciaCorresponsavel(rmMaterial.getPessoaResponsavel().getReferencia());
                    } else if (rmMaterial.getCfResponsavel() != null && !rmMaterial.getCfResponsavel().isEmpty()) {
                        equipamentoVo.setReferenciaCorresponsavel(rmMaterial.getCfResponsavel());
                    }

                    if(rmMaterial.getRm().getRequisitante() != null){
                        equipamentoVo.setReferenciaPessoa(rmMaterial.getRm().getRequisitante().getReferencia());
                    }
                    //Configura Estoque
                    LocalEstoque estoque = new LocalEstoque();
                    estoque.setDescricao(rmMaterial.getDeposito().getNome());
                    estoque.setCodigo(rmMaterial.getDeposito().getCodigo());
                    equipamentoVo.setLocalEstoque(estoque);

                    //Configura Quantidade
                    equipamentoVo.setQuantidade(rmMaterial.getQuantidade());

                    //Configura Unidade Medida
                    UnidadeMedida unidadeMedida = new UnidadeMedida();
                    unidadeMedida.setDescricao(rmMaterial.getMaterial().getUnidadeMedida().getDescricao());
                    equipamentoVo.setUnidadeMedida(unidadeMedida);

                    if (rmMaterial.getRm() != null && rmMaterial.getRm().getNumeroRm() != null) {
                        equipamentoVo.setCodigoRequisicao(rmMaterial.getRm().getNumeroRm());
                    }

                    if(rmMaterial.getRmMaterialId() != null){
                        equipamentoVo.setIdentificadorRmMaterial(rmMaterial.getRmMaterialId());
                    }

                    equipamentoVo.setNomeUsuario("RmaMobile");

                    Boolean habilitaEapMultiEmpresa = ConfiguracaoSingleton.getConfiguracao().getHabilitaEapMultiEmpresa();
                    if (habilitaEapMultiEmpresa != null && habilitaEapMultiEmpresa == true) {

                        if (rmMaterial.getRm() != null && rmMaterial.getRm().getRequisitante() != null) {
                            equipamentoVo.setFuncionarioEap(obterCodigoEapMultiEmpresaPessoa(rmMaterial.getRm().getRequisitante().getPessoaId()));
                        }

                        if (rmMaterial.getDeposito() != null) {
                            equipamentoVo.getLocalEstoque().setLocalEstoqueEap(obterCodigoEapMultiEmpresaDeposito(rmMaterial.getDeposito().getDepositoId()));
                        }
                    }

                    lista.add(equipamentoVo);
                }
            }
            if (!lista.isEmpty()) {
                // Sincroniza com o CPWEB

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

                SincEstoque_Service sincEstoque = new SincEstoque_Service(new URL(confCaminhoUrl));
                br.com.nextage.rmaweb.ws.cpweb.Info infoCp = sincEstoque.getSincEstoquePort().gerarCautela(lista);

                if (infoCp != null) {
                    info.setCodigo(infoCp.getCodigo());
                    info.setErro(infoCp.getErro());
                    info.setTipo(infoCp.getTipo());
                    info.setMensagem(infoCp.getMensagem());
                    logVo = new LogInterfaceVo(info);
                    //IF criado para não regerar registro na tabela de SincRegistro quando está reenviando os registros que já deram problema
                    if (info.getErro() != null && gerarSincRegistro) {
                        SincRegistroService sincRegistroService = new SincRegistroService();
                        String ids = "";
                        for (RmMaterial obj: listaMaterial) {
                            ids += (";"+obj.getRmMaterialId());
                        }
                        sincRegistroService.salvar(info, Constantes.SINC_GERA_CAUTELA, Constantes.SISTEMA_CPWEB,null,null,ids);

                    }
                }
            }

        } catch (Exception e) {
             NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(null));
            SincRegistroService sincRegistroService = new SincRegistroService();
            String erro = e.getCause() != null && e.getCause().getMessage() != null ? e.getMessage() + " - " + e.getCause().getMessage() : e.getMessage();
            String msg = rb.getString(Constantes.ERRO_COMUNICACAO_CPWEB_CONTATE_ADMINISTRADOR_I18N);

            info = Info.GetError(msg);
            info.setErro(erro);
            logVo = new LogInterfaceVo(info);
            //IF criado para não regerar registro na tabela de SincRegistro quando está reenviando os registros que já deram problema
            if(gerarSincRegistro) {
                String ids = "";
                for (RmMaterial obj: listaMaterial) {
                    ids += (";"+obj.getRmMaterialId());
                }
                sincRegistroService.salvar(info, Constantes.SINC_GERA_CAUTELA, Constantes.SISTEMA_CPWEB,null,null,ids);
            }
            logger.error(ResourceLogUtil.createMessageError("", this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            logger.error(erro);
            e.printStackTrace();
        }

        if(listaMaterial != null) {
            String numRma = "";
            List<Integer> listaIds = new ArrayList<Integer>();
            for (RmMaterial obj: listaMaterial) {
                numRma = obj.getRm().getNumeroRm();
                listaIds.add(obj.getRmMaterialId());
            }
            if(gerarSincRegistro) {
                //Gerando Log de interface
                LogInterfaceService.inserirLog(Constantes.SISTEMA_CPWEB, Constantes.INTERFACE_ENVIAR_CAUTELA, numRma, null, null, null, null, getUsuarioNome(), listaIds, logVo);
            }
        }
        return info;
    }


    public List<RmMaterial> listarRmMateriais(List<Integer> ids) {
        List<RmMaterial> rmMateriais = new ArrayList<>();
        GenericDao<RmMaterial> genericDao = new GenericDao<>();

        try {
            String aliasRm = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);
            String aliasTipoRequisicao = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM,Rm.TIPO_REQUISICAO);
            String aliasRequisitante = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.REQUISITANTE);
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
            e.printStackTrace();
            logger.error(ResourceLogUtil.createMessageError("SincCautelaService", this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return rmMateriais;
    }

    public String getUsuarioNome() {
        String nome = LoginService.getUsuarioLogado(request).getNome();
        if(request == null){
            nome = "LISTENER";
        }
        return nome;
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
}
