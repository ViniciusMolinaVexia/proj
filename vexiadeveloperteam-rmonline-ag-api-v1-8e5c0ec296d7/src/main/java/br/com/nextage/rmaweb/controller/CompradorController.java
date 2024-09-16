/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.controller;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.persistence_2.util.Paginacao;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.filtro.FiltroComprador;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.integracao.MaterialService;
import br.com.nextage.rmaweb.service.integracao.RmMaterialStatusService;
import br.com.nextage.rmaweb.service.integracao.RmaService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.CadastroRmVo;
import br.com.nextage.rmaweb.ws.sap.CompraMateriaisSapService;
import br.com.nextage.util.*;
import br.com.nextage.util.vo.ArquivoVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * @author Daniel H. Parisotto
 */
@Path("Comprador")
public class CompradorController {

    @Context
    HttpServletRequest request;

    private static final Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    /**
     * Lista os materiais que tenham ligacao com o usuario logado e que nao
     * tenham sidos enviados para o sap
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object listar(FiltroComprador filtro) {
        return listar(filtro, true);
    }

    private Object listar(FiltroComprador filtro, Boolean paginado) {
        List<Propriedade> propriedades = new ArrayList<>();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroComprador.class.getName(), Util.getNomeMetodoAtual(), filtro, null));

            String aliasMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL);
            String aliasTipoMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL, Material.TIPO_MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.DEPOSITO_ID);
            String aliasRm = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);
            String aliasRequisitante = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.REQUISITANTE);
            String aliasUnidadeMedida = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL, Material.UNIDADE_MEDIDA);
            String aliasPrioridade = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.PRIORIDADE);
            String aliasGerenteObra = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.GERENTE_OBRA);
            String aliasGerenteCusto = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.GERENTE_CUSTOS);
            String aliasGerenteArea = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.GERENTE_AREA);
            String aliasTipoRequisicao = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.TIPO_REQUISICAO);
            String aliasEapMultiEmpresa = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.EAP_MULTI_EMPRESA);
            String aliasUsuario = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.USUARIO);
            String aliasPessoa = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.USUARIO,Usuario.PESSOA);

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));
            propriedades.add(new Propriedade(RmMaterial.ORDEM));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_CUSTOS));
            propriedades.add(new Propriedade(RmMaterial.OPERACAO));
            propriedades.add(new Propriedade(RmMaterial.DIAGRAMA_REDE));
            propriedades.add(new Propriedade(RmMaterial.LOCAL_ENTREGA));
            propriedades.add(new Propriedade(RmMaterial.FLUXO_MATERIAL));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA));
            propriedades.add(new Propriedade(RmMaterial.AGRUPADOR_ERRO));
            propriedades.add(new Propriedade(RmMaterial.DATA_PREVISTA_ENTREGA));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_REQUISICAO_SAP));
            propriedades.add(new Propriedade(RmMaterial.ITEM_REQUISICAO_SAP));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_ORDEM));
            propriedades.add(new Propriedade(RmMaterial.DATA_FINALIZACAO_SERVICO));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_ESTOQUE));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO_ESTOQUISTA));
            propriedades.add(new Propriedade(RmMaterial.DATA_ATRIBUIR_COMPRADOR));
            propriedades.add(new Propriedade(RmMaterial.DATA_ULTIMA_NF));
            propriedades.add(new Propriedade(RmMaterial.CONCLUIDA_COMPRADOR));
            propriedades.add(new Propriedade(RmMaterial.JUSTIFICATIVA_ALTERACAO_MATERIAL));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO));

            //Material
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.IS_SERVICO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.ULTIMO_VALOR_NEGOCIADO, Material.class, aliasMaterial));

            //Tipo Material
            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));

            //Deposito
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            //Rm
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_EMISSAO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.LOCAL_ENTREGA, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.OBSERVACAO, Rm.class, aliasRm));

            //Eap Multi Empresa
            propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));

            //Gerente Area
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasGerenteArea));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasGerenteArea));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasGerenteArea));

            //Gerente custo
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasGerenteCusto));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasGerenteCusto));

            //Gerente obra
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasGerenteObra));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasGerenteObra));

            //Unidade medida
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            //Prioridade
            propriedades.add(new Propriedade(Prioridade.PRIORIDADE_ID, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.DESCRICAO, Prioridade.class, aliasPrioridade));

            //Requisitante
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRequisitante));

            //Usuario
            propriedades.add(new Propriedade(Usuario.USUARIO_ID, Usuario.class, aliasUsuario));
            propriedades.add(new Propriedade(Usuario.NOME, Usuario.class, aliasUsuario));

            //Pessoa
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasPessoa));

            NxCriterion nxCriterion = null;
            NxCriterion nxCriterionAux = null;
            NxCriterion nxCriterionAuxConcluidos = null;

            if (filtro.getNumeroRm() != null && !filtro.getNumeroRm().isEmpty()) {
                filtro.setNumeroRm(filtro.getNumeroRm().replaceAll(",", ";"));
                String[] listaRma = filtro.getNumeroRm().split(";");
                NxCriterion nxAux = null;
                for (String rma : listaRma) {
                    if (rma == null || rma.isEmpty()) {
                        continue;
                    }
                    nxAux = NxCriterion.montaRestriction(new Filtro(Rm.NUMERO_RM, rma, Filtro.EQUAL, aliasRm));
                    if (nxCriterionAux != null) {
                        nxCriterionAux = NxCriterion.or(nxCriterionAux, nxAux);
                    } else {
                        nxCriterionAux = nxAux;
                    }
                }
                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (filtro.getMaterial() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.MATERIAL, filtro.getMaterial(), Filtro.EQUAL));

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (filtro.getTpMaterialServico() != null && filtro.getTpMaterialServico().length() > 0) {
                if("S".equals(filtro.getTpMaterialServico())) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Material.IS_SERVICO, true, Filtro.EQUAL,aliasMaterial));

                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }else if("M".equals(filtro.getTpMaterialServico())) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Material.IS_SERVICO, false, Filtro.EQUAL,aliasMaterial));
                    nxCriterionAux = NxCriterion.or(nxCriterionAux,NxCriterion.montaRestriction(new Filtro(Material.IS_SERVICO, null, Filtro.IS_NULL,aliasMaterial)));

                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }
            }

            if (filtro.getMaterialChave() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Material.NOME, filtro.getMaterialChave(), Filtro.LIKE, aliasMaterial));
                nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(Material.CODIGO, filtro.getMaterialChave(), Filtro.LIKE, aliasMaterial)));
                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (filtro.getRequisitante() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Rm.REQUISITANTE, filtro.getRequisitante(), Filtro.EQUAL, aliasRm));

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (filtro.getUsuario() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, filtro.getUsuario().getPessoaId(), Filtro.EQUAL, aliasPessoa));

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (filtro.getColetorCustos() != null && !filtro.getColetorCustos().isEmpty()) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.COLETOR_CUSTOS, filtro.getColetorCustos(), Filtro.LIKE));
                nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(RmMaterial.COLETOR_ORDEM, filtro.getColetorCustos(), Filtro.LIKE)));
                nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(RmMaterial.DIAGRAMA_REDE, filtro.getColetorCustos(), Filtro.LIKE)));
                nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(RmMaterial.OPERACAO, filtro.getColetorCustos(), Filtro.LIKE)));
                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (filtro.getLocalEntrega() != null && !filtro.getLocalEntrega().isEmpty()) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.LOCAL_ENTREGA, filtro.getLocalEntrega(), Filtro.LIKE));
                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (filtro.getNumeroPedido() != null && !filtro.getNumeroPedido().isEmpty()) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.NUMERO_PEDIDO_COMPRA, filtro.getNumeroPedido(), Filtro.LIKE));
                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (filtro.getExibirCanceladas() != null && !filtro.getExibirCanceladas().isEmpty()) {
                nxCriterionAux = null;
                if("S".equals(filtro.getExibirCanceladas())) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.DATA_CANCELAMENTO, null, Filtro.NOT_NULL));

                }else if("N".equals(filtro.getExibirCanceladas())){
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.DATA_CANCELAMENTO, null, Filtro.IS_NULL));
                }
                if(nxCriterionAux != null){
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }
            }

            if (filtro.getEapMultiEmpresa() != null && filtro.getEapMultiEmpresa().getId() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(EapMultiEmpresa.ID, filtro.getEapMultiEmpresa().getId(), Filtro.EQUAL, aliasEapMultiEmpresa));
                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (filtro.getNumeroRequisicao() != null && !filtro.getNumeroRequisicao().isEmpty()) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.NUMERO_REQUISICAO_SAP, filtro.getNumeroRequisicao(), Filtro.LIKE));
                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (filtro.getPrioridade() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Rm.PRIORIDADE, filtro.getPrioridade(), Filtro.EQUAL, aliasRm));
                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }


            Comprador comprador = LoginService.getUsuarioLogado(request).getComprador();

            if (comprador != null) {
                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterial.COMPRADOR, comprador, Filtro.EQUAL)));
                } else {
                    nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.COMPRADOR, comprador, Filtro.EQUAL));
                }

                nxCriterionAux = null;
                switch (filtro.getStatus()) {
                    case "CNP":
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.NUMERO_PEDIDO_COMPRA, null, Filtro.NOT_NULL));
                        break;
                    case "CNR":
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.NUMERO_REQUISICAO_SAP, null, Filtro.NOT_NULL));
                        break;
                    case "SNP":
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.NUMERO_PEDIDO_COMPRA, null, Filtro.IS_NULL));
                        break;
                    case "SNR":
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.NUMERO_REQUISICAO_SAP, null, Filtro.IS_NULL));
                        break;
                    case "ST":
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.NUMERO_PEDIDO_COMPRA, null, Filtro.IS_NULL));
                        nxCriterionAux = NxCriterion.and(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(RmMaterial.NUMERO_REQUISICAO_SAP, null, Filtro.IS_NULL)));
                        break;
                }

                switch (filtro.getItensConcluidos()) {
                    case "T":
                        break;
                    case "S":
                        nxCriterionAuxConcluidos = NxCriterion.montaRestriction(new Filtro(RmMaterial.CONCLUIDA_COMPRADOR, true, Filtro.EQUAL));
                        break;
                    case "N":
                        nxCriterionAuxConcluidos = NxCriterion.montaRestriction(new Filtro(RmMaterial.CONCLUIDA_COMPRADOR, false, Filtro.EQUAL));
                        nxCriterionAuxConcluidos = NxCriterion.or(nxCriterionAuxConcluidos, NxCriterion.montaRestriction(new Filtro(RmMaterial.CONCLUIDA_COMPRADOR, null, Filtro.IS_NULL)));
                        break;
                }

                if (nxCriterionAuxConcluidos != null) {
                    if (nxCriterionAux != null) {
                        nxCriterionAux = NxCriterion.and(nxCriterionAux, nxCriterionAuxConcluidos);
                    } else {
                        nxCriterionAux = nxCriterionAuxConcluidos;
                    }
                }

                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterial.DATA_FINALIZACAO_SERVICO, null, Filtro.IS_NULL)));

                if (nxCriterionAux != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                }

                List<NxOrder> orders = Arrays.asList(new NxOrder(Rm.ALIAS_CLASSE + "." + Rm.DATA_EMISSAO, NxOrder.NX_ORDER.DESC));

                if (paginado) {
                    Paginacao paginacao = new Paginacao(propriedades, orders, RmMaterial.class, nxCriterion, filtro.getPaginacaoVo());

                    for (Object object : filtro.getPaginacaoVo().getItensConsulta()) {
                        RmMaterial rmMaterial = (RmMaterial) object;

                        if (rmMaterial.getDataPrevistaEntrega() != null) {
                            rmMaterial.setStDataPrevistaEntrega(Util.dateToString(rmMaterial.getDataPrevistaEntrega(), rb.getString("format_date")));
                        }

                        rmMaterial.setPossuiAnexoMaterial(verificaAnexo(rmMaterial.getRmMaterialId(), Constantes.ESCOPO_ANEXO_RM_MATERIAL));
                        rmMaterial.setPossuiAnexoRm(verificaAnexo(rmMaterial.getRm().getRmId(), Constantes.ESCOPO_ANEXO_RM));
                    }

                    return filtro.getPaginacaoVo();
                } else {
                    GenericDao<RmMaterial> genericDao = new GenericDao<>();
                    return genericDao.listarByFilter(propriedades, orders, RmMaterial.class, Constantes.NO_LIMIT, nxCriterion);
                }
            }

        } catch (Exception e){
        	e.printStackTrace();
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return null;
    }

    private Boolean verificaAnexo(Integer id, String escopo) {
        try {

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Anexo.ESCOPO_ID, id, Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Anexo.ESCOPO, escopo, Filtro.EQUAL)));

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Anexo.ID));

            GenericDao<Pessoa> dao = new GenericDao<>();
            return dao.selectCountByFilter(nxCriterion, Anexo.class, propriedades) > 0;

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return false;
    }

    /**
     * Faz o envio dos materiais selecionados como uma requisição de compra
     *
     * @param listaRmMaterial
     * @return
     */
    @POST
    @Path("enviarParaSap")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info enviarParaSap(List<RmMaterial> listaRmMaterial) {
        Info info = new Info();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

        RmaService rmaService = new RmaService(request);
        CompraMateriaisSapService compraMateriaisSapService = new CompraMateriaisSapService();
        info = compraMateriaisSapService.enviarCompra(listaRmMaterial);

        if (info.getErro() != null) {
            info.setTipo(Info.TIPO_MSG_DANGER);
            rmaService.enviarEmailAdministrador("Compra", info.getMensagem());
        } else {
            info.setMensagem(rb.getString("msg_requisicao_criada_sucesso"));
            info.setTipo(Info.TIPO_MSG_SUCCESS);
        }

        return info;
    }

    /**
     * Atualiza os registros com a data especificada
     *
     * @param listaRmMaterial
     * @return
     */
    @POST
    @Path("salvarDataPrevisaoEntrega")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info salvarDataPrevisaoEntrega(List<RmMaterial> listaRmMaterial) {
        Info info = new Info();
        GenericDao<RmMaterial> genericDao = new GenericDao<>();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(RmMaterial.DATA_PREVISTA_ENTREGA));

        try {
            genericDao.beginTransaction();

            for (RmMaterial rmMaterial : listaRmMaterial) {
                if (rmMaterial.getStDataPrevistaEntrega() != null && !rmMaterial.getStDataPrevistaEntrega().isEmpty()) {
                    rmMaterial.setDataPrevistaEntrega(Util.parseData(rmMaterial.getStDataPrevistaEntrega(), rb.getString("format_date")));
                } else {
                    rmMaterial.setDataPrevistaEntrega(null);
                }

                genericDao.updateWithCurrentTransaction(rmMaterial, propriedades);
            }

            genericDao.commitCurrentTransaction();
            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setErro(null);
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.ERRO_OPERACAO_I18N);
        }

        return info;
    }

    @POST
    @Path("geraExcel")
    @Consumes("application/json")
    public ArquivoVo geraExcel(FiltroComprador filtro) {
        ArquivoVo arquivoVo = new ArquivoVo();
        try {
            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
            Map<String, String> mapFiltro = new HashMap<>();

            String label, valor, status = "";

            int position = 0;
            if (filtro.getEapMultiEmpresa() != null && filtro.getEapMultiEmpresa().getDescricao() != null) {
                label = rb.getString("label_eap_multi_empresa");
                valor = filtro.getEapMultiEmpresa().getDescricao();
                mapFiltro.put(position++ + ":" + label, valor);
            }
            if (filtro.getNumeroRm() != null) {
                label = rb.getString("label_numero");
                valor = filtro.getNumeroRm();
                mapFiltro.put(position++ + ":" + label, valor);
            }
            if (filtro.getLocalEntrega() != null) {
                label = rb.getString("label_local_entrega");
                valor = filtro.getLocalEntrega();
                mapFiltro.put(position++ + ":" + label, valor);
            }
            if (filtro.getColetorCustos() != null && filtro.getColetorCustos() != null) {
                label = rb.getString("label_coletor_custos");
                valor = filtro.getColetorCustos();
                mapFiltro.put(position++ + ":" + label, valor);
            }
            if (filtro.getMaterial() != null) {
                label = rb.getString("label_material");
                valor = filtro.getMaterial().getCodigo() + " - " + filtro.getMaterial().getNome();
                mapFiltro.put(position++ + ":" + label, valor);
            }
            if (filtro.getRequisitante() != null) {
                label = rb.getString("label_requisitante");
                valor = filtro.getRequisitante().getNome();
                mapFiltro.put(position++ + ":" + label, valor);
            }

            if (filtro.getStatus() != null) {
                label = rb.getString("label_status");
                valor = "";
                switch (filtro.getStatus()) {
                    case "T":
                        valor = rb.getString("label_todos");
                        break;
                    case "CNP":
                        valor = rb.getString("label_com_numero_pedido");
                        break;
                    case "CNR":
                        valor = rb.getString("label_com_numero_requisicao");
                        break;
                    case "SNP":
                        valor = rb.getString("label_sem_numero_pedido");
                        break;
                    case "SNR":
                        valor = rb.getString("label_sem_numero_requisicao");
                        break;
                    case "ST":
                        valor = rb.getString("label_sem_tratamento");
                        break;
                }

                mapFiltro.put(position++ + ":" + label, valor);
            }

            Map<String, String> mapItens = new HashMap<>();

            List<RmMaterial> lista = (List<RmMaterial>) listar(filtro, false);

            position = 0;
            Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();

            if (configuracao.getHabilitaEapMultiEmpresa() != null && configuracao.getHabilitaEapMultiEmpresa() == true) {
                label = rb.getString("label_eap_multi_empresa");
                mapItens.put(RmMaterial.RM + "." + Rm.EAP_MULTI_EMPRESA + "." + EapMultiEmpresa.DESCRICAO, position++ + ":" + label);
            }
            label = rb.getString("label_numero");
            mapItens.put(RmMaterial.RM + "." + Rm.NUMERO_RM, position++ + ":" + label);
            label = rb.getString("label_numero");
            mapItens.put(RmMaterial.RM + "." + Rm.NUMERO_RM, position++ + ":" + label);

            label = rb.getString("label_material");
            mapItens.put(RmMaterial.MATERIAL + "." + Material.CODIGO + ";" + RmMaterial.MATERIAL + "." + Material.NOME, position++ + ":" + label);

            label = rb.getString("label_quantidade");
            mapItens.put(RmMaterial.QUANTIDADE, position++ + ":" + label);

            label = rb.getString("label_unidade_medida");
            mapItens.put(RmMaterial.MATERIAL+"."+Material.UNIDADE_MEDIDA+"."+UnidadeMedida.SIGLA, position++ + ":" + label);

            label = rb.getString("label_observacao") ;
            label += " "+rb.getString("label_rma");
            mapItens.put(RmMaterial.RM+"."+Rm.OBSERVACAO, position++ + ":" + label);

            label = rb.getString("label_observacao");
            label += " "+rb.getString("label_material");
            mapItens.put(RmMaterial.OBSERVACAO, position++ + ":" + label);

            label = rb.getString("label_requisitante");
            mapItens.put(RmMaterial.RM + "." + Rm.REQUISITANTE + "." + Pessoa.REFERENCIA + ";" + RmMaterial.RM + "." + Rm.REQUISITANTE + "." + Pessoa.NOME, position++ + ":" + label);
            label = rb.getString("label_emissao");
            mapItens.put(RmMaterial.RM + "." + Rm.DATA_EMISSAO, position++ + ":" + label);
            label = rb.getString("label_aplicacao");
            mapItens.put(RmMaterial.RM + "." + Rm.DATA_APLICACAO, position++ + ":" + label);
            label = rb.getString("label_numero_requisicao_sap");
            mapItens.put(RmMaterial.NUMERO_REQUISICAO_SAP, position++ + ":" + label);
            label = rb.getString("label_item_requisicao_sap");
            mapItens.put(RmMaterial.ITEM_REQUISICAO_SAP, position++ + ":" + label);
            label = rb.getString("label_local_entrega");
            mapItens.put(RmMaterial.LOCAL_ENTREGA, position++ + ":" + label);
            label = rb.getString("label_coletor_custos");
            mapItens.put(RmMaterial.COLETOR_CUSTOS, position++ + ":" + label);
            label = rb.getString("label_diagrama_de_rede");
            mapItens.put(RmMaterial.DIAGRAMA_REDE, position++ + ":" + label);
            label = rb.getString("label_operacao");
            mapItens.put(RmMaterial.OPERACAO, position++ + ":" + label);
            label = rb.getString("label_estoque");
            mapItens.put(RmMaterial.COLETOR_ESTOQUE, position++ + ":" + label);
            label = rb.getString("label_data_previsao_entrega");
            mapItens.put(RmMaterial.DATA_PREVISTA_ENTREGA, position++ + ":" + label);

            //retornoRelatorio
            RelatorioBean rBean = new RelatorioBean();
            rBean.setMapaFiltro(mapFiltro);
            rBean.setMapaListaItens(mapItens);
            rBean.setTitulo(rb.getString("label_comprador"));
            rBean.setItens(lista);
            rBean.setTipo(RelatorioBean.TipoRelatorio.XLS);
            rBean.setTitulo(rb.getString("label_comprador"));
            rBean.setNome(rb.getString("label_comprador") + ".xls");
            arquivoVo.setNmAnexo(rBean.getNome());
            byte[] conteudo = new RelatorioUtil().geraRelatorio(rBean);
            arquivoVo.setArquivo(conteudo);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(),
                    this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return arquivoVo;
    }

    @POST
    @Path("alterarMaterial")
    @Consumes("application/json")
    public Info alterarMaterial(RmMaterial rmMaterial) {
        Info info = new Info();

        GenericDao<RmMaterial> genericDao = new GenericDao<>();

        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.MATERIAL));
            propriedades.add(new Propriedade(RmMaterial.JUSTIFICATIVA_ALTERACAO_MATERIAL));

            genericDao.update(rmMaterial, propriedades);

            info.setObjeto(rmMaterial);
            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setErro(null);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));

            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
        }
        return info;
    }

    @POST
    @Path("adicionarMaterialCodigo")
    @Consumes("application/json")
    public Info adicionarMaterialCodigo(RmMaterial rmMaterial) {
        Info info = new Info();

        GenericDao<RmMaterial> genericDao = new GenericDao<>();

        try {
            Boolean salvar = true;
            if(rmMaterial ==null || rmMaterial.getMaterial() == null || rmMaterial.getMaterial().getCodigo() == null || rmMaterial.getMaterial().getCodigo().trim() == ""){
                salvar = false;
                info.setCodigo(Info.INFO_002);
                info.setTipo(Info.TIPO_MSG_DANGER);
                info.setErro("label_preencha_os_campos_obrigatorios");
                info.setMensagem("label_preencha_os_campos_obrigatorios");

            }else {
                //O código do material deve ter 18 caracteres, se não tem preenche com 0 na frente.
                while (rmMaterial.getMaterial().getCodigo().length() < 18) {
                    rmMaterial.getMaterial().setCodigo("0" + rmMaterial.getMaterial().getCodigo());
                }

                //Verifica se já existe material com código
                MaterialService matService = new MaterialService();
                Material mat = matService.getMaterialByCodigo(rmMaterial.getMaterial().getCodigo());
                if(mat != null && mat.getMaterialId() > 0){
                    salvar = false;
                    info.setCodigo(Info.INFO_002);
                    info.setTipo(Info.TIPO_MSG_DANGER);
                    info.setErro("msg_erro_existe_material_codigo");
                    info.setMensagem("msg_erro_existe_material_codigo");
                }
            }

            if(salvar) {
                List<Propriedade> prop = new ArrayList<>();
                prop.add(new Propriedade(RmMaterial.JUSTIFICATIVA_ALTERACAO_MATERIAL));

                List<Propriedade> propMat = new ArrayList<>();
                propMat.add(new Propriedade(Material.CODIGO));

                genericDao.beginTransaction();
                genericDao.updateWithCurrentTransaction(rmMaterial, prop);
                genericDao.updateWithCurrentTransaction(rmMaterial.getMaterial(), propMat);
                genericDao.commitCurrentTransaction();

                info.setObjeto(rmMaterial);
                info.setCodigo(Info.INFO_001);
                info.setTipo(Info.TIPO_MSG_SUCCESS);
                info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
                info.setErro(null);
            }
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));

            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
        }
        return info;
    }

    @POST
    @Path("alterarColetor")
    @Consumes("application/json")
    public Info alterarColetor(RmMaterial rmMaterial) {
        Info info = new Info();

        GenericDao<RmMaterial> genericDao = new GenericDao<>();

        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.COLETOR_CUSTOS));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_ORDEM));
            propriedades.add(new Propriedade(RmMaterial.DIAGRAMA_REDE));
            propriedades.add(new Propriedade(RmMaterial.OPERACAO));

            genericDao.update(rmMaterial, propriedades);

            info.setObjeto(rmMaterial);
            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setErro(null);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));

            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
        }
        return info;
    }

    @POST
    @Path("finalizarIsServico")
    @Consumes("application/json")
    public Info finalizarIsServico(RmMaterial rmMaterial) {
        Info info = new Info();
        Date data = new Date();
        GenericDao genericDao = new GenericDao<>();

        RmMaterialStatus materialStatus = new RmMaterialStatus();

        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);

        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(RmMaterial.DATA_FINALIZACAO_SERVICO));

        try {
            materialStatus = rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_FINALIZADO, null, data, "Finalizado por Serviço", null);

            genericDao.persist(materialStatus);

            rmMaterial.setDataFinalizacaoServico(data);

            genericDao.update(rmMaterial, propriedades);

            info.setObjeto(rmMaterial);
            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setErro(null);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));

            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
        }
        return info;
    }

    @POST
    @Path("detalharRm")
    @Consumes("application/json")
    public RmMaterial detalharRm(RmMaterial rmMaterial) {
        GenericDao<RmMaterial> genericDao = new GenericDao<>();

        try {
            String aliasMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL);
            String aliasTipoMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL, Material.TIPO_MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.DEPOSITO_ID);
            String aliasRm = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);
            String aliasEapMultiEmpresa = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.EAP_MULTI_EMPRESA);
            String aliasUnidadeMedida = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL, Material.UNIDADE_MEDIDA);
            String aliasPrioridade = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.PRIORIDADE);
            String aliasTipoRequisicao = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.TIPO_REQUISICAO);
            String aliasUsuario = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.USUARIO);
            String aliasUsuarioPessoa = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.USUARIO, Usuario.PESSOA);
            String aliasRequisitante = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.REQUISITANTE);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));
            propriedades.add(new Propriedade(RmMaterial.LOCAL_ENTREGA));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_REQUISICAO_SAP));
            propriedades.add(new Propriedade(RmMaterial.FLUXO_MATERIAL));
            propriedades.add(new Propriedade(RmMaterial.ORDEM));
            propriedades.add(new Propriedade(RmMaterial.DATA_ULTIMA_NF));

            //Deposito
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            //Material
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));

            //Tipo Material
            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));

            //Rm
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_EMISSAO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.OBSERVACAO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.LOCAL_ENTREGA, Rm.class, aliasRm));

            //Eap Multi Empresa
            propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));

            //Prioridade
            propriedades.add(new Propriedade(Prioridade.PRIORIDADE_ID, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.DESCRICAO, Prioridade.class, aliasPrioridade));

            //Unidade Medida
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            //Requisitante
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRequisitante));

            //Usuario
            propriedades.add(new Propriedade(Usuario.USUARIO_ID, Usuario.class, aliasUsuario));

            //Usuario pessoa
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasUsuarioPessoa));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasUsuarioPessoa));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM_MATERIAL_ID, rmMaterial.getRmMaterialId(), Filtro.EQUAL));

            rmMaterial = genericDao.selectUnique(propriedades, RmMaterial.class, nxCriterion);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return rmMaterial;
    }

    /**
     * Atualiza a RmMaterial com o numeroPedidoCompra e a observaçãoEstoquista
     * Depois atribui ao status Aguardando Recebimento
     *
     * @param rmMaterial
     * @return Info
     * @author Alyson X. Leite
     */
    @POST
    @Path("salvarAtribuirComprado")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info salvarAtribuirComprado(RmMaterial rmMaterial) {
        Info info;
        GenericDao<RmMaterial> genericDao = new GenericDao<>();
        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO_ESTOQUISTA));

            if(rmMaterial.getMaterial() != null && rmMaterial.getMaterial().getIsServico()){
                propriedades.add(new Propriedade(RmMaterial.DATA_FINALIZACAO_SERVICO));
                rmMaterial.setDataFinalizacaoServico(new Date());
            }

            // Recupero o ultimo status
            RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);
            RmMaterialStatus rmMaterialStatus = rmMaterialStatusService.listarStatusAtual(rmMaterial);

            genericDao.beginTransaction();

            // Atualizo a rmMaterial
            genericDao.updateWithCurrentTransaction(rmMaterial, propriedades);

            if(rmMaterial.getMaterial() != null && rmMaterial.getMaterial().getIsServico() != null && rmMaterial.getMaterial().getIsServico()) {

                // Caso já esteja no status "Material Finalizado", significa que o comprador está só atualizando o registro
                // assim não é preciso gerar o status novamente
                if (!rmMaterialStatus.getStatus().getCodigo().equals(Constantes.STATUS_RM_MATERIAL_FINALIZADO)) {
                    //Finalizo o status anterior e gero o status "Rm Material Finalizado"
                    rmMaterialStatusService.finalizarStatus(rmMaterialStatus, genericDao, new Date());
                    rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_FINALIZADO, genericDao, new Date(), null, null);

                }
            }else{
                // Caso já esteja no status "Aguardando Recebimento", significa que o comprador está só atualizando o registro
                // assim não é preciso gerar o status e o MaterialDepositoSaidaReserva novamente
                if (!rmMaterialStatus.getStatus().getCodigo().equals(Constantes.STATUS_RM_MATERIAL_COMPRADO_AGUARDANDO_RECEBIMENTO)) {
                    //Finalizo o status anterior e gero o status "Aguardando Recebimento"
                    rmMaterialStatusService.finalizarStatus(rmMaterialStatus, genericDao, new Date());
                    rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_COMPRADO_AGUARDANDO_RECEBIMENTO, genericDao, new Date(), null, null);

                    //Gera uma saida reserva, para ficar como historico
                    MaterialDepositoSaidaReserva materialDepositoSaidaReserva = new MaterialDepositoSaidaReserva();
                    materialDepositoSaidaReserva.setDataSaida(new Date());
                    materialDepositoSaidaReserva.setRmMaterial(rmMaterial);
                    materialDepositoSaidaReserva.setQuantidadeSolicitada(rmMaterial.getQuantidade());
                    materialDepositoSaidaReserva.setObservacaoPainelEstoquista(rmMaterial.getObservacaoEstoquista());
                    genericDao.persistWithCurrentTransaction(materialDepositoSaidaReserva);
                }
            }
            genericDao.commitCurrentTransaction();

            info = Info.GetSuccess(Constantes.SUCESSO_OPERACAO_I18N, rmMaterial);

        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            info = Info.GetError(Constantes.ERRO_OPERACAO_I18N, rmMaterial);
        }

        return info;
    }

    /**
     * Gera relatório conforme o resultado em tela
     *
     * @param filtro
     * @return
     * @author Yuri Mello
     */
    @POST
    @Path("gerarDocumento")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info gerarDocumento(FiltroComprador filtro) {
        Info info = new Info();
        List<RmMaterial> lista;
        try {
            Object rmMaterials = null;

            rmMaterials = listar(filtro, false);

            lista = (List<RmMaterial>) rmMaterials;

            List<RmMaterial> listaAux = new ArrayList<>();
            if(filtro.getIdsSelecionados() != null && filtro.getIdsSelecionados().size() > 0){
                for (RmMaterial rmm :lista) {
                    if(filtro.getIdsSelecionados().contains(rmm.getRmMaterialId())) {
                        listaAux.add(rmm);
                    }
                }
                lista = listaAux;
            }

            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
            CadastroRmVo cadastroRmVo = new CadastroRmVo();
            RelatorioBean relatorioBean = new RelatorioBean();
            relatorioBean.setTitulo(rb.getString("label_documento_comprador"));
            relatorioBean.setTipo(RelatorioBean.TipoRelatorio.PDF);

            relatorioBean.setNome("relatorio/DocumentoComprador.jasper");

            Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
            relatorioBean.setCaminhoJasper(conf.getCaminhoRealInstalacao());

            HashMap parameters = new HashMap();

            parameters.put("SUBREPORT_DIR", conf.getCaminhoRealInstalacao() + "relatorio/");
            parameters.put("IMAGES_DIR", conf.getCaminhoPathImagemRel());
            parameters.put("UNIDADE", conf.getNome());
            parameters.put("CENTRO", conf.getCentroCod());
            if (conf.getHabilitaEapMultiEmpresa() != null && conf.getHabilitaEapMultiEmpresa() == true && filtro.getEapMultiEmpresa() != null && filtro.getEapMultiEmpresa().getDescricao() != null) {
                parameters.put("PROJETO", filtro.getEapMultiEmpresa().getDescricao());
            }

            relatorioBean.setParameters(parameters);

            cadastroRmVo.setRmMateriais(lista);

            relatorioBean.setListaJasperBeanCollection(Arrays.asList(cadastroRmVo));

            RelatorioUtil relatorioBeanUtil = new RelatorioUtil();
            byte[] conteudoRelatorio = relatorioBeanUtil.geraRelatorio(relatorioBean);
            ArquivoVo vo = new ArquivoVo();
            vo.setArquivo(conteudoRelatorio);
            vo.setNmAnexo(rb.getString("label_documento_comprador") + ".pdf");

            info.setObjeto(vo);
        } catch (Exception e) {
            info = Info.GetError(Constantes.ERRO_OPERACAO_I18N);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    /**
     * Atualiza a flag do concluidaComprador para true ou false.
     * Lucas Heitor
     *
     * @param rmMaterial
     * @return
     */
    @POST
    @Path("concluirComprador")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info concluirComprador(RmMaterial rmMaterial) {
        Info info = new Info();
        GenericDao<RmMaterial> genericDao = new GenericDao<>();
        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(RmMaterial.CONCLUIDA_COMPRADOR));

        try {
            genericDao.beginTransaction();
            genericDao.updateWithCurrentTransaction(rmMaterial, propriedades);
            genericDao.commitCurrentTransaction();

            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setObjeto(rmMaterial);
            info.setErro(null);
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
        }

        return info;
    }
}
