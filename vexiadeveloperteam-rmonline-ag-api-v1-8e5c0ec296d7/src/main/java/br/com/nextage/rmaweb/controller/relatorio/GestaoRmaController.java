/*
 * NextAge License
 * Copyright 2015 - Nextage
 *
 */
package br.com.nextage.rmaweb.controller.relatorio;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.persistence_2.util.Paginacao;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.filtro.FiltroGestaoRma;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.RelatorioBean;
import br.com.nextage.util.RelatorioUtil;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import br.com.nextage.util.vo.ArquivoVo;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import jxl.write.DateTime;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Daniel H. Parisotto
 */
@Path("GestaoRma")
public class GestaoRmaController {

    @Context
    HttpServletRequest request;

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    /**
     * Lista os rmMaterial
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object listar(FiltroGestaoRma filtro) {
        return listar(filtro, true);
    }

    private Object listar(FiltroGestaoRma filtro, Boolean paginado) {
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
        List<Propriedade> propriedades = new ArrayList<>();

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroGestaoRma.class.getName(), Util.getNomeMetodoAtual(), filtro, null));

            //VIEW
            propriedades.add(new Propriedade(VwConsultaRma.RM_RM_ID));
            propriedades.add(new Propriedade(VwConsultaRma.RM_NUMERO_RM));
            propriedades.add(new Propriedade(VwConsultaRma.REQUISITANTE_REFERENCIA));
            propriedades.add(new Propriedade(VwConsultaRma.REQUISITANTE_NOME));
            propriedades.add(new Propriedade(VwConsultaRma.SETOR_DESCRICAO));
            propriedades.add(new Propriedade(VwConsultaRma.SUB_AREA_DESCRICAO));
            propriedades.add(new Propriedade(VwConsultaRma.TIPO_REQUISICAO_DESCRICAO));
            propriedades.add(new Propriedade(VwConsultaRma.MATERIAL_CODIGO));
            propriedades.add(new Propriedade(VwConsultaRma.MATERIAL_NOME));
            propriedades.add(new Propriedade(VwConsultaRma.DEPOSITO_DESCRICAO));
            propriedades.add(new Propriedade(VwConsultaRma.RM_MATERIAL_QUANTIDADE));
            propriedades.add(new Propriedade(VwConsultaRma.RM_DATA_EMISSAO));
            propriedades.add(new Propriedade(VwConsultaRma.PRIORIDADE_DESCRICAO));
            propriedades.add(new Propriedade(VwConsultaRma.DATA_APROV_COORDENADOR));
            propriedades.add(new Propriedade(VwConsultaRma.DATA_APROV_GERENTE_AREA));
            propriedades.add(new Propriedade(VwConsultaRma.DATA_APROV_COMPLEMENTO_CUSTOS));
            propriedades.add(new Propriedade(VwConsultaRma.DATA_APROV_GERENTE_CUSTOS));
            propriedades.add(new Propriedade(VwConsultaRma.DATA_APROV_RESPONSAVEL_RETIRADA_ESTOQUE));
            propriedades.add(new Propriedade(VwConsultaRma.DATA_COMPRA));
            propriedades.add(new Propriedade(VwConsultaRma.COMPRADOR_NOME));
            propriedades.add(new Propriedade(VwConsultaRma.COMPRADOR_EAP));
            propriedades.add(new Propriedade(VwConsultaRma.RM_EAP_MULTI_EMPRESA_DESCRICAO));
            propriedades.add(new Propriedade(VwConsultaRma.RM_EAP_MULTI_EMPRESA_ID));
            propriedades.add(new Propriedade(VwConsultaRma.DEPOSITO_EAP_MULTI_EMPRESA_DESCRICAO));

            NxCriterion nxCriterion = null;
            NxCriterion nxCriterionAux;

            if (filtro != null) {
                //Material
                if (filtro.getMaterial() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.MATERIAL_CODIGO, filtro.getMaterial().getCodigo(), Filtro.EQUAL));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                //Material Chave
                if (filtro.getMaterialChave() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.MATERIAL_NOME, filtro.getMaterialChave(), Filtro.LIKE));
                    nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(VwConsultaRma.MATERIAL_CODIGO, filtro.getMaterialChave(), Filtro.LIKE)));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getRequisitante() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.REQUISITANTE_REFERENCIA, filtro.getRequisitante().getReferencia(), Filtro.EQUAL));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                //Numero Rma
                if (filtro.getNumeroRm() != null && !filtro.getNumeroRm().isEmpty()) {
                    filtro.setNumeroRm(filtro.getNumeroRm().replaceAll(",", ";"));
                    String[] listaRma = filtro.getNumeroRm().split(";");
                    NxCriterion nxAux = null;
                    nxCriterionAux = null;
                    for (String rma : listaRma) {
                        if (rma == null || rma.isEmpty()) {
                            continue;
                        }
                        nxAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.RM_NUMERO_RM, rma, Filtro.EQUAL));
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

                //Aprovadores - (GA, CO, GC, RRE, CO)
                if (filtro.getAprovador() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.GERENTE_AREA_ID, filtro.getAprovador().getPessoaId(), Filtro.EQUAL));
                    nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(VwConsultaRma.GERENTE_CUSTO_ID, filtro.getAprovador().getPessoaId(), Filtro.EQUAL)));
                    nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(VwConsultaRma.RESPONSAVEL_RETIRADA_ESTOQUE_ID, filtro.getAprovador().getPessoaId(), Filtro.EQUAL)));
                    nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(VwConsultaRma.COORDENADOR_ID, filtro.getAprovador().getPessoaId(), Filtro.EQUAL)));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getSetor() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.SETOR_CODIGO, filtro.getSetor().getCodigo(), Filtro.EQUAL));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getSubArea() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.SUB_AREA_CODIGO, filtro.getSubArea().getCodigo(), Filtro.EQUAL));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if ((filtro.getStDataEmissaoDe() != null && !filtro.getStDataEmissaoDe().isEmpty())
                        || (filtro.getStDataEmissaoAte() != null && !filtro.getStDataEmissaoAte().isEmpty())) {
                    Date dataDe = null, dataAte = null;

                    if (filtro.getStDataEmissaoDe() != null && !filtro.getStDataEmissaoDe().isEmpty()) {
                        try {
                            dataDe = Util.parseData(filtro.getStDataEmissaoDe(), rb.getString("format_date"));
                        } catch (Exception e) {
                            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
                        }
                    }

                    if (filtro.getStDataEmissaoAte() != null && !filtro.getStDataEmissaoAte().isEmpty()) {
                        try {
                            dataAte = Util.parseData(filtro.getStDataEmissaoAte(), rb.getString("format_date"));
                            dataAte.setHours(23);
                            dataAte.setMinutes(59);
                            dataAte.setSeconds(59);
                        } catch (Exception e) {
                            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
                        }
                    }

                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.RM_DATA_EMISSAO, dataDe, dataAte, true, Filtro.BETWEEN, null));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if ((filtro.getStDataNecessidadeDe() != null && !filtro.getStDataNecessidadeDe().isEmpty())
                        || (filtro.getStDataNecessidadeAte() != null && !filtro.getStDataNecessidadeAte().isEmpty())) {
                    Date dataDe = null, dataAte = null;

                    if (filtro.getStDataNecessidadeDe() != null && !filtro.getStDataNecessidadeDe().isEmpty()) {
                        try {
                            dataDe = Util.parseData(filtro.getStDataNecessidadeDe(), rb.getString("format_date"));
                        } catch (Exception e) {
                            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
                        }
                    }

                    if (filtro.getStDataNecessidadeAte() != null && !filtro.getStDataNecessidadeAte().isEmpty()) {
                        try {
                            dataAte = Util.parseData(filtro.getStDataNecessidadeAte(), rb.getString("format_date"));
                            dataAte.setHours(23);
                            dataAte.setMinutes(59);
                            dataAte.setSeconds(59);
                        } catch (Exception e) {
                            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
                        }
                    }

                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.RM_DATA_APLICACAO, dataDe, dataAte, true, Filtro.BETWEEN, null));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getPrioridade() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.PRIORIDADE_CODIGO, filtro.getPrioridade().getCodigo(), Filtro.EQUAL));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getEapMultiEmpresa() != null && filtro.getEapMultiEmpresa().getId() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.RM_EAP_MULTI_EMPRESA_ID, filtro.getEapMultiEmpresa().getId(), Filtro.EQUAL));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }
            }

            List<NxOrder> orders = Arrays.asList(new NxOrder(VwConsultaRma.RM_RM_ID, NxOrder.NX_ORDER.DESC));

            if (paginado) {
                Paginacao paginacao = new Paginacao(propriedades, orders, VwConsultaRma.class, nxCriterion, filtro.getPaginacaoVo());
                return filtro.getPaginacaoVo();
            } else {
                GenericDao<VwConsultaRma> genericDao = new GenericDao<>();
                return genericDao.listarByFilter(propriedades, orders, VwConsultaRma.class, Constantes.NO_LIMIT, nxCriterion);
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return null;
    }

    @POST
    @Path("geraExcel")
    @Consumes("application/json")
    public ArquivoVo geraExcel(FiltroGestaoRma filtro) {
        ArquivoVo arquivoVo = new ArquivoVo();
        try {
            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
            Map<String, String> mapFiltro = new HashMap<>();
            Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();

            String label, valor, status = "";

            int position = 0;

            if (filtro.getEapMultiEmpresa() != null && filtro.getEapMultiEmpresa().getDescricao() != null) {
                label = rb.getString("label_eap_multi_empresa");
                valor = filtro.getEapMultiEmpresa().getDescricao();
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
            if (filtro.getSubArea() != null) {
                label = rb.getString("label_subarea");
                valor = filtro.getSubArea().getDescricao();
                mapFiltro.put(position++ + ":" + label, valor);
            }
            if (filtro.getMaterialChave() != null) {
                label = rb.getString("label_material_chave");
                valor = filtro.getMaterialChave();
                mapFiltro.put(position++ + ":" + label, valor);
            }
            if (filtro.getAprovador() != null) {
                label = rb.getString("label_aprovador");
                valor = filtro.getAprovador().getNome();
                mapFiltro.put(position++ + ":" + label, valor);
            }
            if (filtro.getSetor() != null) {
                label = rb.getString("label_setor");
                valor = filtro.getSetor().getDescricao();
                mapFiltro.put(position++ + ":" + label, valor);
            }
            if (filtro.getPrioridade() != null) {
                label = rb.getString("label_prioridade");
                valor = filtro.getPrioridade().getDescricao();
                mapFiltro.put(position++ + ":" + label, valor);
            }
            if ((filtro.getStDataEmissaoDe() != null && !filtro.getStDataEmissaoDe().isEmpty()) || (filtro.getStDataEmissaoAte() != null && !filtro.getStDataEmissaoAte().isEmpty())) {
                label = rb.getString("label_data_emissao") + ":";
                valor = "";
                if ((filtro.getStDataEmissaoDe() != null && !filtro.getStDataEmissaoDe().isEmpty()) && (filtro.getStDataEmissaoAte() != null && !filtro.getStDataEmissaoAte().isEmpty())) {
                    valor = rb.getString("label_de") + ": " + filtro.getStDataEmissaoDe() + " - " + rb.getString("label_ate") + ": " + filtro.getStDataEmissaoAte();
                } else if (filtro.getStDataEmissaoDe() != null && !filtro.getStDataEmissaoDe().isEmpty()) {
                    valor = rb.getString("label_de") + ": " + filtro.getStDataEmissaoDe();
                } else if (filtro.getStDataEmissaoAte() != null && !filtro.getStDataEmissaoAte().isEmpty()) {
                    valor = rb.getString("label_ate") + ": " + filtro.getStDataEmissaoAte();
                }
                mapFiltro.put(position++ + ":" + label, valor);
            }
            if ((filtro.getStDataNecessidadeDe() != null && !filtro.getStDataNecessidadeDe().isEmpty()) || (filtro.getStDataNecessidadeAte() != null && !filtro.getStDataNecessidadeAte().isEmpty())) {
                label = rb.getString("label_data_aplicacao") + ":";
                valor = "";
                if ((filtro.getStDataNecessidadeDe() != null && !filtro.getStDataNecessidadeDe().isEmpty()) && (filtro.getStDataNecessidadeAte() != null && !filtro.getStDataNecessidadeAte().isEmpty())) {
                    valor = rb.getString("label_de") + ": " + filtro.getStDataNecessidadeDe() + " - " + rb.getString("label_ate") + ": " + filtro.getStDataNecessidadeAte();
                } else if (filtro.getStDataNecessidadeDe() != null && !filtro.getStDataNecessidadeDe().isEmpty()) {
                    valor = rb.getString("label_de") + ": " + filtro.getStDataNecessidadeDe();
                } else if (filtro.getStDataNecessidadeAte() != null && !filtro.getStDataNecessidadeAte().isEmpty()) {
                    valor = rb.getString("label_ate") + ": " + filtro.getStDataNecessidadeAte();
                }
                mapFiltro.put(position++ + ":" + label, valor);
            }

            Map<String, String> mapItens = new HashMap<>();

            List<VwRmMaterial> lista = (List<VwRmMaterial>) listar(filtro, false);

            position = 0;

            if (configuracao.getHabilitaEapMultiEmpresa() != null && configuracao.getHabilitaEapMultiEmpresa() == true) {
                label = rb.getString("label_eap_multi_empresa");
                mapItens.put(VwConsultaRma.RM_EAP_MULTI_EMPRESA_DESCRICAO, position++ + ":" + label);
            }
            label = rb.getString("label_numero");
            mapItens.put(VwConsultaRma.RM_NUMERO_RM, position++ + ":" + label);
            label = rb.getString("label_requisitante");
            mapItens.put(VwConsultaRma.REQUISITANTE_NOME, position++ + ":" + label);

            label = rb.getString("label_setor");
            mapItens.put(VwConsultaRma.SETOR_DESCRICAO, position++ + ":" + label);
            label = rb.getString("label_subarea");
            mapItens.put(VwConsultaRma.SUB_AREA_DESCRICAO, position++ + ":" + label);

            label = rb.getString("label_tipo_requisicao");
            mapItens.put(VwConsultaRma.TIPO_REQUISICAO_DESCRICAO, position++ + ":" + label);


            label = rb.getString("label_codigo_material");
            mapItens.put(VwConsultaRma.MATERIAL_CODIGO, position++ + ":" + label);
            label = rb.getString("label_material");
            mapItens.put(VwConsultaRma.MATERIAL_NOME, position++ + ":" + label);
            label = rb.getString("label_deposito");
            mapItens.put(VwConsultaRma.DEPOSITO_DESCRICAO, position++ + ":" + label);
            label = rb.getString("label_quantidade");
            mapItens.put(VwConsultaRma.RM_MATERIAL_QUANTIDADE, position++ + ":" + label);
            label = rb.getString("label_data_emissao");
            mapItens.put(VwConsultaRma.RM_DATA_EMISSAO, position++ + ":" + label);
            label = rb.getString("label_prioridade");
            mapItens.put(VwConsultaRma.PRIORIDADE_DESCRICAO, position++ + ":" + label);

            label = rb.getString("label_data_aprov_coordenador");
            mapItens.put(VwConsultaRma.DATA_APROV_COORDENADOR, position++ + ":" + label);
            label = rb.getString("label_data_aprov_gerente_area");
            mapItens.put(VwConsultaRma.DATA_APROV_GERENTE_AREA, position++ + ":" + label);
            label = rb.getString("label_data_aprov_equipe_custo");
            mapItens.put(VwConsultaRma.DATA_APROV_COMPLEMENTO_CUSTOS, position++ + ":" + label);
            label = rb.getString("label_data_aprov_gerente_custos");
            mapItens.put(VwConsultaRma.DATA_APROV_GERENTE_CUSTOS, position++ + ":" + label);
            label = rb.getString("label_data_aprov_resp_retirada_estoque");
            mapItens.put(VwConsultaRma.DATA_APROV_RESPONSAVEL_RETIRADA_ESTOQUE, position++ + ":" + label);
            label = rb.getString("label_comprador");
            mapItens.put(VwConsultaRma.COMPRADOR_NOME, position++ + ":" + label);
            if (configuracao.getHabilitaEapMultiEmpresa() != null && configuracao.getHabilitaEapMultiEmpresa() == true) {
                label = rb.getString("label_comprador_eap");
                mapItens.put(VwConsultaRma.COMPRADOR_EAP, position++ + ":" + label);
            }
            //retornoRelatorio
            RelatorioBean rBean = new RelatorioBean();
            rBean.setMapaFiltro(mapFiltro);
            rBean.setMapaListaItens(mapItens);
            rBean.setTitulo(rb.getString("label_relatorio_gestao_rma"));
            rBean.setItens(lista);
            rBean.setTipo(RelatorioBean.TipoRelatorio.XLS);
            rBean.setTitulo(rb.getString("label_relatorio_gestao_rma"));
            rBean.setNome(rb.getString("label_relatorio_gestao_rma") + ".xls");
            arquivoVo.setNmAnexo(rBean.getNome());
            byte[] conteudo = new RelatorioUtil().geraRelatorio(rBean);
            arquivoVo.setArquivo(conteudo);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(),
                    this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return arquivoVo;
    }
}
