package br.com.nextage.rmaweb.controller;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.util.Paginacao;
import br.com.nextage.persistence_2.vo.PaginacaoVo;
import br.com.nextage.rmaweb.entitybean.LogInterface;
import br.com.nextage.rmaweb.filtro.FiltroLogInterface;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.LogInterfaceItemVo;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import com.google.gson.Gson;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Path("LogInterface")
public class LogInterfaceController {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);
    @Context
    HttpServletRequest request;

    /**
     * Lista o(s) iten(s) de acordo com o filtro passado por parametro.
     *
     * @param filtro
     * @return lista
     **/
    @POST
    @Path("listar")
    @Consumes("application/json")
    public PaginacaoVo listar(FiltroLogInterface filtro) {
        try {
            if (filtro != null && filtro.getPaginacaoVo() != null) {
                logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroLogInterface.class.getName(), Util.getNomeMetodoAtual(), filtro, null));
                List<Propriedade> propriedades = new ArrayList<Propriedade>();
                propriedades.add(new Propriedade(LogInterface.ID));
                propriedades.add(new Propriedade(LogInterface.SISTEMA));
                propriedades.add(new Propriedade(LogInterface.INTERFACE_EXEC));
                propriedades.add(new Propriedade(LogInterface.NUM_RMA));
                propriedades.add(new Propriedade(LogInterface.CODIGO_DEPOSITO));
                propriedades.add(new Propriedade(LogInterface.ITEM_REQUICISAO_SAP));
                propriedades.add(new Propriedade(LogInterface.NUM_REQUISICAO_SAP));
                propriedades.add(new Propriedade(LogInterface.NUM_PEDIDO_SAP));
                propriedades.add(new Propriedade(LogInterface.DATA_HORA_INCLUSAO_LOG));
                propriedades.add(new Propriedade(LogInterface.USUARIO_INCLUSAO));
                propriedades.add(new Propriedade(LogInterface.JSON));
                propriedades.add(new Propriedade(LogInterface.MENSAGEM));
                propriedades.add(new Propriedade(LogInterface.TIPO_MENSAGEM));
                propriedades.add(new Propriedade(LogInterface.ERRO_MENSAGEM));
                propriedades.add(new Propriedade(LogInterface.NOME_CLASSE));


                NxCriterion nxCriterionPrincipal = null;
                NxCriterion nxCriterionAux = null;
                NxCriterion nxCriterionOr = null;

                if (filtro != null) {
                    NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
                    if (filtro.getStDataHoraInclusaoLogInicio() != null && !filtro.getStDataHoraInclusaoLogInicio().isEmpty()) {
                        filtro.setDataHoraInclusaoLogInicio(Util.parseData(filtro.getStDataHoraInclusaoLogInicio(), rb.getString("format_date")));
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(LogInterface.DATA_HORA_INCLUSAO_LOG,
                                filtro.getDataHoraInclusaoLogInicio(), Filtro.MAIOR_IGUAL));
                        nxCriterionPrincipal = NxCriterion.and(nxCriterionPrincipal, nxCriterionAux);
                    }

                    if (filtro.getStDataHoraInclusaoLogFim() != null && !filtro.getStDataHoraInclusaoLogFim().isEmpty()) {
                        filtro.setDataHoraInclusaoLogFim(Util.parseData(filtro.getStDataHoraInclusaoLogFim(), rb.getString("format_date")));
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(LogInterface.DATA_HORA_INCLUSAO_LOG,
                                filtro.getDataHoraInclusaoLogFim(), Filtro.MENOR_IGUAL));
                        nxCriterionPrincipal = NxCriterion.and(nxCriterionPrincipal, nxCriterionAux);
                    }
                    if (filtro.getSistema() != null && !"".equals(filtro.getSistema())) {
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(LogInterface.SISTEMA, filtro.getSistema(), Filtro.LIKE));
                        nxCriterionPrincipal = NxCriterion.and(nxCriterionPrincipal, nxCriterionAux);
                    }

                    if (filtro.getInterfaceExec() != null && !"".equals(filtro.getInterfaceExec())) {
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(LogInterface.INTERFACE_EXEC, filtro.getInterfaceExec(), Filtro.LIKE));
                        nxCriterionPrincipal = NxCriterion.and(nxCriterionPrincipal, nxCriterionAux);
                    }

                    if (filtro.getNumRma() != null && !"".equals(filtro.getNumRma())) {
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(LogInterface.NUM_RMA, filtro.getNumRma(), Filtro.LIKE));
                        nxCriterionPrincipal = NxCriterion.and(nxCriterionPrincipal, nxCriterionAux);
                    }

                    if (filtro.getCodigoDeposito() != null && !"".equals(filtro.getCodigoDeposito())) {
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(LogInterface.CODIGO_DEPOSITO, filtro.getCodigoDeposito(), Filtro.LIKE));
                        nxCriterionPrincipal = NxCriterion.and(nxCriterionPrincipal, nxCriterionAux);
                    }

                    if (filtro.getItemRequisicaoSap() != null && !"".equals(filtro.getItemRequisicaoSap())) {
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(LogInterface.ITEM_REQUICISAO_SAP, filtro.getItemRequisicaoSap(), Filtro.LIKE));
                        nxCriterionPrincipal = NxCriterion.and(nxCriterionPrincipal, nxCriterionAux);
                    }

                    if (filtro.getNumRequisicaoSap() != null && !"".equals(filtro.getNumRequisicaoSap())) {
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(LogInterface.NUM_REQUISICAO_SAP, filtro.getNumRequisicaoSap(), Filtro.LIKE));
                        nxCriterionPrincipal = NxCriterion.and(nxCriterionPrincipal, nxCriterionAux);
                    }

                    if (filtro.getNumPedidoSap() != null && !"".equals(filtro.getNumPedidoSap())) {
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(LogInterface.NUM_PEDIDO_SAP, filtro.getNumPedidoSap(), Filtro.LIKE));
                        nxCriterionPrincipal = NxCriterion.and(nxCriterionPrincipal, nxCriterionAux);
                    }

                    if (filtro.getUsuarioInclusao() != null && !"".equals(filtro.getUsuarioInclusao())) {
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(LogInterface.USUARIO_INCLUSAO, filtro.getUsuarioInclusao(), Filtro.LIKE));
                        nxCriterionPrincipal = NxCriterion.and(nxCriterionPrincipal, nxCriterionAux);
                    }

                    if (filtro.getJson() != null && !"".equals(filtro.getJson())) {
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(LogInterface.JSON, filtro.getJson(), Filtro.LIKE_CASE_SENSITIVE));
                        nxCriterionPrincipal = NxCriterion.and(nxCriterionPrincipal, nxCriterionAux);
                    }

                    if (filtro.getMensagem() != null && !"".equals(filtro.getMensagem())) {
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(LogInterface.MENSAGEM, filtro.getMensagem(), Filtro.SEM_ACENTO));
                        nxCriterionPrincipal = NxCriterion.and(nxCriterionPrincipal, nxCriterionAux);
                    }

                    if (filtro.getTipoMensagem() != null && !"".equals(filtro.getTipoMensagem())) {
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(LogInterface.TIPO_MENSAGEM, filtro.getTipoMensagem(), Filtro.LIKE));
                        nxCriterionPrincipal = NxCriterion.and(nxCriterionPrincipal, nxCriterionAux);
                    }

                    if (filtro.getErroMensagem() != null && !"".equals(filtro.getErroMensagem())) {
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(LogInterface.ERRO_MENSAGEM, filtro.getErroMensagem(), Filtro.LIKE_CASE_SENSITIVE));
                        nxCriterionPrincipal = NxCriterion.and(nxCriterionPrincipal, nxCriterionAux);
                    }


                }

                List<NxOrder> nxOrders = Arrays.asList(new NxOrder(LogInterface.ID, NxOrder.NX_ORDER.ASC));
                Paginacao pag = new Paginacao(propriedades, null, LogInterface.class, nxCriterionPrincipal, filtro.getPaginacaoVo());
                logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroLogInterface.class.getName(), Util.getNomeMetodoAtual(), filtro, null));
            }
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return filtro.getPaginacaoVo();
    }

    /**
     * Lista o(s) iten(s) de acordo com o filtro passado por parametro.
     *
     * @param logInterface
     * @return List<LogInterfaceItemVo>
     **/
    @POST
    @Path("detalharJson")
    @Consumes("application/json")
    public List<LogInterfaceItemVo> detalharJson(LogInterface logInterface) {
        List<LogInterfaceItemVo> itens = new ArrayList<>();
        try {
            if(logInterface.getJson() != null && logInterface.getJson().length() > 5 && logInterface.getNomeClasse() != null) {
                Gson gson = new Gson();

                Class<?> act = Class.forName(logInterface.getNomeClasse());
                Object obj = gson.fromJson(logInterface.getJson(),act);
                itens = detalharObjeto(obj,5,0);
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return itens;
    }

    /**
     * Monta a lista de propriedades com valor, os niveis é utilizado para evitar loop já que um objeto pode ser setado nele mesmo.
     *
     * @param obj
     * @param niveis
     * @return List<LogInterfaceItemVo>
     **/
    public List<LogInterfaceItemVo> detalharObjeto(Object obj,int niveis,int count) {
        List<LogInterfaceItemVo> itens = new ArrayList<>();
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field campo: fields) {
                count++;
                try {
                    if (!campo.toString().contains(" static ")) {
                        Object objCampo = null;

                        if (hasSapClazz(campo.toGenericString())) {
                            objCampo = PropertyUtils.getProperty(obj, campo.getName().toUpperCase());
                        } else {
                            objCampo = PropertyUtils.getProperty(obj, campo.getName());
                        }

                        if (objCampo == null) {
                            itens.add(new LogInterfaceItemVo(campo.getName(), "null",count));
                        } else if (objCampo instanceof String || objCampo instanceof Integer || objCampo instanceof Date ||
                                objCampo instanceof Double || objCampo instanceof Float || objCampo instanceof Boolean) {
                            itens.add(new LogInterfaceItemVo(campo.getName(), objCampo.toString(),count));

                            //Se o nome da classe possuí "br.com.nextage" que dizer que é um objeto e não outro campo sem importância
                        } else if (objCampo.getClass().getName().contains("br.com.nextage")) {
                            if (niveis > 0) {
                                itens.add(new LogInterfaceItemVo(campo.getName(), null,count, detalharObjeto(objCampo, niveis - 1,count)));
                            } else {
                                itens.add(new LogInterfaceItemVo(campo.getName(), "Object...",count));
                            }
                        } else {
                            itens.add(new LogInterfaceItemVo(campo.getName(), objCampo.toString(),count));
                        }
                    }
                } catch (Exception ex) {
                    itens.add(new LogInterfaceItemVo(campo.getName(), "Erro ao recuperar valor",count));
                }
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return itens;
    }

    private boolean hasSapClazz(final String clazzName) {
        if (clazzName.contains("sap")) {
            return true;
        }
        return false;
    }


}
