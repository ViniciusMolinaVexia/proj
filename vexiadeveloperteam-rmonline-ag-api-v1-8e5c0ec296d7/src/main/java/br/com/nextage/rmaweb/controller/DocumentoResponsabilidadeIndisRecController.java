package br.com.nextage.rmaweb.controller;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.persistence_2.util.Paginacao;
import br.com.nextage.persistence_2.vo.PaginacaoVo;
import br.com.nextage.rmaweb.entitybean.Configuracao;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.entitybean.VwDocumentoResponsabilidade;
import br.com.nextage.rmaweb.filtro.FiltroDocumentoResponsabilidadeIndisRec;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.integracao.RmaService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.DocumentoResponsabilidadeIndisRecVo;
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
 * @author Alyson X. Leite <a.leite@nextage.com.br>
 * @brief Classe DocumentoResponsabilidadeIndisRecController
 * @date 04/02/2016
 */
@Path("DocumentoResponsabilidadeIndisRec")
public class DocumentoResponsabilidadeIndisRecController {

    @Context
    private HttpServletRequest request;

    private static final Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    /**
     * Método rest para recuperar as requisições de campo com paginação
     *
     * @param filtro
     * @return PaginacaoVo - paginação de VwRmMaterial
     * @author Alyson X. Leite
     */
    @POST
    @Path("listarRmMaterialCampo")
    @Consumes("application/json")
    public PaginacaoVo listarRmMaterialCampo(FiltroDocumentoResponsabilidadeIndisRec filtro) {
        return (PaginacaoVo) listarRmMaterialCampo(filtro, true);
    }

    /**
     * Responsavel por listar as rms que já estão finalizadas e que são do tipo
     * "Campo" de acordo com o filtro
     *
     * @param filtro
     * @param paginado
     * @return Object - lista ou paginacao de VwRmMaterial
     * @author Alyson X. Leite
     */
    public Object listarRmMaterialCampo(FiltroDocumentoResponsabilidadeIndisRec filtro, boolean paginado) {
        try {
            List<Propriedade> propriedades = new ArrayList<>();

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroDocumentoResponsabilidadeIndisRec.class.getName(), Util.getNomeMetodoAtual(), filtro, null));

            //VIEW

            //Material
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.MATERIAL_ID));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.MATERIAL_NOME));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.MATERIAL_CODIGO));

            //Requisitante
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.REQUISITANTE_NOME));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.REQUISITANTE_REFERENCIA));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.REQUISITANTE_FUNCAO));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.REQUISITANTE_SETOR));

            //Usuario
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.USUARIO_NOME));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.USUARIO_REFERENCIA));

            //Status
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.STATUS_CODIGO));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.STATUS_NOME));

            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.CF_RESPONSAVEL));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.DATA_APLICACAO));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.DATA_EMISSAO));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.NUMERO_RM));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.RM_MATERIAL_ID));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.RM_ID));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.RM_MATERIAL_STATUS_ID));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.FLUXO_MATERIAL));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.QUANTIDADE));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.OBSERVACAO));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.DEPOSITO_NOME));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.DEPOSITO_EAP_MULTI_EMPRESA_DESCRICAO));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.RM_EAP_MULTI_EMPRESA_DESCRICAO));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.NOME_RESPONSAVEL));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.REFERENCIA_RESPONSAVEL));


            NxCriterion nxCriterionAux = null;
            NxCriterion nxCriterion = null;
            //Fluxo Material
            nxCriterion = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.FLUXO_MATERIAL, Constantes.FLUXO_MATERIAL_CAMPO, Filtro.EQUAL));
            //Tipo Requisição
            nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.TIPO_REQUISICAO_CODIGO, Constantes.TIPO_REQUISICAO_FRENTE_SERVICO, Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);

            //Realizar um or entre os status STATUS_RM_MATERIAL_INDISPONIVEL e STATUS_RM_MATERIAL_RECUSADO_ESTOQUISTA
            NxCriterion nxCriterionOr = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.STATUS_CODIGO, Constantes.STATUS_RM_MATERIAL_INDISPONIVEL, Filtro.EQUAL));
            nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.STATUS_CODIGO, Constantes.STATUS_RM_MATERIAL_RECUSADO_ESTOQUISTA, Filtro.EQUAL));
            nxCriterionOr = NxCriterion.or(nxCriterionOr, nxCriterionAux);
            nxCriterion = NxCriterion.and(nxCriterion, nxCriterionOr);

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
                    nxAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.NUMERO_RM, rma, Filtro.EQUAL));
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

            // Requisitante
            if (filtro.getRequisitante() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.REQUISITANTE_REFERENCIA, filtro.getRequisitante().getReferencia(), Filtro.EQUAL));
                if (nxCriterion == null) {
                    nxCriterion = nxCriterionAux;
                } else {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                }
            }

            if (filtro.getEapMultiEmpresa() != null && filtro.getEapMultiEmpresa().getId() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.RM_EAP_MULTI_EMPRESA_ID, filtro.getEapMultiEmpresa().getId(), Filtro.EQUAL));
                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            // Cadastrante
            if (filtro.getUsuarioCadastrante() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.USUARIO_REFERENCIA, filtro.getUsuarioCadastrante().getReferencia(), Filtro.EQUAL));
                if (nxCriterion == null) {
                    nxCriterion = nxCriterionAux;
                } else {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                }
            }
            // Material
            if (filtro.getMaterial() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.MATERIAL_CODIGO, filtro.getMaterial().getCodigo(), Filtro.EQUAL));
                if (nxCriterion == null) {
                    nxCriterion = nxCriterionAux;
                } else {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                }
            }
            //Material Chave
            if (filtro.getMaterialChave() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.MATERIAL_NOME, filtro.getMaterialChave(), Filtro.LIKE));
                nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.MATERIAL_CODIGO, filtro.getMaterialChave(), Filtro.LIKE)));
                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }
            //Depósito
            if (filtro.getDeposito() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.DEPOSITO_CODIGO, filtro.getDeposito().getCodigo(), Filtro.EQUAL));
                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (filtro.getTipoMaterial() != null && !filtro.getTipoMaterial().isEmpty()) {
                nxCriterionAux = null;
                Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
                switch (filtro.getTipoMaterial()) {
                    case Constantes.EQUIPAMENTO:
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.MATERIAL_PATRIMONIADO, Constantes.SIM_ABREVIADO, Filtro.EQUAL));
                        break;
                    case Constantes.EPI:
                        if (conf != null && conf.getCodigoEpi() != null && !conf.getCodigoEpi().isEmpty()) {
                            List<String> listaEpi = new ArrayList(Arrays.asList(conf.getCodigoEpi().split(";")));
                            nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.MATERIAL_HIERARQUIA, listaEpi, Filtro.IN));
                            nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                        }
                        break;
                    case Constantes.MC:
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.TIPO_MATERIAL_CODIGO, Constantes.NAO_CAUTELAVEL, Filtro.EQUAL));
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                        //E somente diferentes de EPI
                        if (conf != null && conf.getCodigoEpi() != null && !conf.getCodigoEpi().isEmpty()) {
                            List<String> listaEpi = new ArrayList(Arrays.asList(conf.getCodigoEpi().split(";")));
                            nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.MATERIAL_HIERARQUIA, listaEpi, Filtro.NOT_IN));
                            nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.MATERIAL_HIERARQUIA, null, Filtro.IS_NULL)));
                            nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                        }
                        break;
                }
                if (nxCriterionAux != null) {
                    if (nxCriterion == null) {
                        nxCriterion = nxCriterionAux;
                    } else {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    }
                }
            }

            Date dataEmissaoDe = null;
            Date dataEmissaoAte = null;

            Date dataAplicacaoDe = null;
            Date dataAplicacaoAte = null;
            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

            if (filtro.getStDataAplicacaoDe() != null && !filtro.getStDataAplicacaoDe().isEmpty()) {
                dataAplicacaoDe = Util.parseData(filtro.getStDataAplicacaoDe(), rb.getString("format_date"));
            }
            if (filtro.getStDataAplicacaoAte() != null && !filtro.getStDataAplicacaoAte().isEmpty()) {
                dataAplicacaoAte = Util.parseData(filtro.getStDataAplicacaoAte(), rb.getString("format_date"));
                dataAplicacaoAte.setHours(23);
                dataAplicacaoAte.setMinutes(59);
                dataAplicacaoAte.setSeconds(59);
            }

            if (filtro.getStDataEmissaoDe() != null && !filtro.getStDataEmissaoDe().isEmpty()) {
                dataEmissaoDe = Util.parseData(filtro.getStDataEmissaoDe(), rb.getString("format_date"));
            }
            if (filtro.getStDataEmissaoAte() != null && !filtro.getStDataEmissaoAte().isEmpty()) {
                dataEmissaoAte = Util.parseData(filtro.getStDataEmissaoAte(), rb.getString("format_date"));
                dataEmissaoAte.setHours(23);
                dataEmissaoAte.setMinutes(59);
                dataEmissaoAte.setSeconds(59);
            }


            if (dataEmissaoDe != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.DATA_EMISSAO, dataEmissaoDe, Filtro.MAIOR_IGUAL));

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }
            if (dataEmissaoAte != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.DATA_EMISSAO, dataEmissaoAte, Filtro.MENOR_IGUAL));

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (dataAplicacaoDe != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.DATA_APLICACAO, dataAplicacaoDe, Filtro.MAIOR_IGUAL));

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }
            if (dataAplicacaoAte != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.DATA_APLICACAO, dataAplicacaoAte, Filtro.MENOR_IGUAL));

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            List<NxOrder> nxOrder = Arrays.asList(new NxOrder(VwDocumentoResponsabilidade.RM_ID, NxOrder.NX_ORDER.DESC));

            if (paginado) {
                Paginacao.build(propriedades, nxOrder, VwDocumentoResponsabilidade.class, nxCriterion, filtro.getPaginacaoVo());
                for (Object vw : filtro.getPaginacaoVo().getItensConsulta()) {
                    VwDocumentoResponsabilidade vwDocumentoResponsabilidade = (VwDocumentoResponsabilidade) vw;
                    if (vwDocumentoResponsabilidade.getDataAplicacao() != null) {
                        GregorianCalendar calendar = new GregorianCalendar();
                        calendar.setTime(vwDocumentoResponsabilidade.getDataAplicacao());
                        calendar.set(GregorianCalendar.HOUR_OF_DAY, 12);
                        vwDocumentoResponsabilidade.setDataAplicacao(calendar.getTime());
                    }
                    if (vwDocumentoResponsabilidade.getDataEmissao() != null) {
                        GregorianCalendar calendar = new GregorianCalendar();
                        calendar.setTime(vwDocumentoResponsabilidade.getDataEmissao());
                        calendar.set(GregorianCalendar.HOUR_OF_DAY, 12);
                        vwDocumentoResponsabilidade.setDataEmissao(calendar.getTime());
                    }
                }
                return filtro.getPaginacaoVo();
            } else {
                GenericDao<VwDocumentoResponsabilidade> dao = new GenericDao<>();
                List<VwDocumentoResponsabilidade> lista = dao.listarByFilter(propriedades, nxOrder, VwDocumentoResponsabilidade.class, Constantes.NO_LIMIT, nxCriterion);
                return lista;
            }
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            return null;
        }

    }

    /**
     * Método rest para gerar os documentos de responsabilidade para os
     * materiais que já estão finalidos pelo estoquista
     *
     * @param listaMaterial Lista com os materiais selecionados para serem
     *                      impressos
     * @return Info
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     */
    @POST
    @Path("gerarDocumento")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info gerarDocumento(List<VwDocumentoResponsabilidade> listaVwDocumentoResponsabilidade) {
        Info info = new Info();
        try {
            //Separo os encaregados pela lista de listaVwDocumentoResponsabilidade para uma lista de listVwDocumentoResponsabilidadeVo
            List<DocumentoResponsabilidadeIndisRecVo> listaDocumentoResponsabilidadeIndisRecVo = new ArrayList<>();
            Boolean encontrou = false;
            for (VwDocumentoResponsabilidade vw : listaVwDocumentoResponsabilidade) {
                for (DocumentoResponsabilidadeIndisRecVo voIndisRec : listaDocumentoResponsabilidadeIndisRecVo) {
                    if (voIndisRec.getRequisitanteReferencia().equals(vw.getRequisitanteReferencia())) {
                        List<VwDocumentoResponsabilidade> vwAuxList = new ArrayList<>();
                        for (VwDocumentoResponsabilidade voAux : voIndisRec.getListaVwDocumentoResponsabilidade()) {
                            vwAuxList.add(voAux);
                        }
                        vwAuxList.add(vw);
                        voIndisRec.setListaVwDocumentoResponsabilidade(vwAuxList);
                        encontrou = true;
                    }
                }
                if (encontrou == false) {
                    DocumentoResponsabilidadeIndisRecVo documentoResponsabilidadeIndisRecVo = new DocumentoResponsabilidadeIndisRecVo();
                    documentoResponsabilidadeIndisRecVo.setRequisitanteNome(vw.getRequisitanteNome());
                    documentoResponsabilidadeIndisRecVo.setRequisitanteReferencia(vw.getRequisitanteReferencia());
                    documentoResponsabilidadeIndisRecVo.setRequisitanteFuncao(vw.getRequisitanteFuncao());
                    documentoResponsabilidadeIndisRecVo.setRequisitanteSetor(vw.getRequisitanteSetor());
                    documentoResponsabilidadeIndisRecVo.setListaVwDocumentoResponsabilidade(Arrays.asList(vw));
                    listaDocumentoResponsabilidadeIndisRecVo.add(documentoResponsabilidadeIndisRecVo);
                }
                encontrou = false;
            }


            //Gero o relatorio
            List<RelatorioBean> listaBeans = new ArrayList<>();
            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));


            // Agrupo os materiais pelos responsáveis

            RelatorioBean relatorioBean = new RelatorioBean();
            relatorioBean.setTitulo(rb.getString("label_documento_responsabilidade"));
            relatorioBean.setTipo(RelatorioBean.TipoRelatorio.PDF);
            relatorioBean.setListaRelatorioBeanItens(new ArrayList());

            Configuracao conf = ConfiguracaoSingleton.getConfiguracao();

            HashMap parameters = new HashMap();

            parameters.put("SUBREPORT_DIR", conf.getCaminhoRealInstalacao() + "relatorio/");
            parameters.put("IMAGES_DIR", conf.getCaminhoPathImagemRel());
            parameters.put("UNIDADE", conf.getNome());
            parameters.put("CENTRO", conf.getCentroCod());
            parameters.put("EMPRESA", conf.getEmpresa());


            for (DocumentoResponsabilidadeIndisRecVo vwVo : listaDocumentoResponsabilidadeIndisRecVo) {
                RelatorioBean relatorioBeanAux = new RelatorioBean();
                relatorioBeanAux.setCaminhoJasper(conf.getCaminhoRealInstalacao());
                relatorioBeanAux.setNome("relatorio/DocumentoResponsabilidadeIndisRec.jasper");
                relatorioBeanAux.setParameters(parameters);

                relatorioBeanAux.setListaJasperBeanCollection(Arrays.asList(vwVo));
                relatorioBean.getListaRelatorioBeanItens().add(relatorioBeanAux);
            }

            RelatorioUtil relatorioBeanUtil = new RelatorioUtil();
            byte[] conteudoRelatorio = relatorioBeanUtil.geraRelatorio(relatorioBean);
            ArquivoVo vo = new ArquivoVo();
            vo.setArquivo(conteudoRelatorio);
            vo.setNmAnexo(rb.getString("label_documento_responsabilidade") + ".pdf");

            info.setObjeto(vo);

        } catch (Exception e) {
            info = Info.GetError(Constantes.ERRO_OPERACAO_I18N);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    /**
     * Verifico se o material é um equipamento
     *
     * @param rmMaterial
     * @return
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     */
    private boolean isEquipamento(RmMaterial rmMaterial) {
        return RmaService.isEquipamento(rmMaterial);
    }

    /**
     * Verifico se o material é cautelável.
     *
     * @param rmMaterial
     * @return
     * @author Marlos Morbis Novo
     */
    public boolean isCautelavelSemEpi(RmMaterial rmMaterial) {
        return RmaService.isCautelavelSemEpi(rmMaterial);
    }

    /**
     * Verifico se o material é um Equipamento EPI
     *
     * @param rmMaterial
     * @return
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     */
    private boolean isEpi(RmMaterial rmMaterial) {
        return RmaService.isEpi(rmMaterial);
    }

    /**
     * Verifico se o material é um Material de consumo
     *
     * @param rmMaterial
     * @return
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     */
    private boolean isMaterialConsumo(RmMaterial rmMaterial) {
        return RmaService.isMaterialConsumo(rmMaterial);
    }


}
