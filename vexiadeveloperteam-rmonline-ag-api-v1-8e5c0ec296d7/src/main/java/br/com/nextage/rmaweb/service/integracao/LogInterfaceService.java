package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.LogInterface;
import br.com.nextage.rmaweb.entitybean.MaterialDepositoEntrada;
import br.com.nextage.rmaweb.entitybean.MaterialDepositoSaida;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.CompraMateriaisSapVo;
import br.com.nextage.rmaweb.vo.LogInterfaceVo;
import br.com.nextage.rmaweb.vo.MaterialDepositoVo;
import br.com.nextage.rmaweb.vo.MaterialTransferenciaVo;
import br.com.nextage.rmaweb.vo.integracao.CompraVo;
import br.com.nextage.rmaweb.vo.integracao.EstoqueVo;
import br.com.nextage.rmaweb.vo.integracao.EstornoVo;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * Created by Marlos on 09/05/2016.
 */
public class LogInterfaceService {

    static Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    /**
     * Realiza o save de logs para interfaces com outros sistemas de acordo com os parametros informados.
     *
     * @author Marlos Morbis Novo
     */
    public static void inserirLog(String sistema, String interfaceExec, String numRma, String codigoDeposito, String itemRequisicaoSap,
                                  String numRequisicaoSap, String numPedidoSap, String usuarioInclusao, Object objetoLogJson, LogInterfaceVo logInterfaceVo) {

        GenericDao<LogInterface> genericDao = new GenericDao<>();
        try {

            LogInterface log = new LogInterface();
            log.setDataHoraInclusaoLog(new Date());
            log.setSistema(sistema);
            log.setInterfaceExec(interfaceExec);
            log.setNumRma(numRma);
            log.setCodigoDeposito(codigoDeposito);
            log.setItemRequisicaoSap(itemRequisicaoSap);
            log.setNumRequisicaoSap(numRequisicaoSap);
            log.setNumPedidoSap(numPedidoSap);
            log.setUsuarioInclusao(usuarioInclusao);
            if (objetoLogJson != null) {
                log.setNomeClasse(objetoLogJson.getClass().getName());
                String json = new Gson().toJson(objetoLogJson);
                log.setJson(json);
            }
            if (logInterfaceVo.getMensagem() != null) {
                log.setMensagem(logInterfaceVo.getMensagem());
            }
            if (logInterfaceVo.getTipoMensagem() != null) {
                log.setTipoMensagem(logInterfaceVo.getTipoMensagem());
            }
            if (logInterfaceVo.getErroMensagem() != null) {
                log.setErroMensagem(logInterfaceVo.getErroMensagem());
            }

            genericDao.persist(log);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), LogInterfaceService.class.getName(), Util.getNomeMetodoAtual(), e));
        }

    }

    public static void inserirLog(final LogInterface log) {
        try {
            GenericDao<LogInterface> genericDao = new GenericDao<>();
            genericDao.persist(log);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), LogInterfaceService.class.getName(), Util.getNomeMetodoAtual(), e));
        }
    }

    /**
     * Realiza o save de logs para interfaces com outros sistemas de acordo com os parametros informados.
     *
     * @author Igor Rodrigues Pessoa
     */
    public static void inserirLog(String sistema, String interfaceExec, String usuarioInclusao, LogInterfaceVo logInterfaceVo) {

        GenericDao<LogInterface> genericDao = new GenericDao<>();
        try {
            Object objetoLogJson = logInterfaceVo.getObjeto();
            LogInterface log = new LogInterface();
            log.setDataHoraInclusaoLog(new Date());
            log.setSistema(sistema);
            log.setInterfaceExec(interfaceExec);
            if(objetoLogJson != null) {
                log.setNomeClasse(objetoLogJson.getClass().getName());
                String json = new Gson().toJson(objetoLogJson);
                if (objetoLogJson.getClass().equals(CompraVo.class)) {
                    CompraVo compraVo = (CompraVo) objetoLogJson;
                    log.setNumRma(compraVo.getNumeroRma());
                    log.setItemRequisicaoSap(compraVo.getItemRmSap());
                    log.setNumRequisicaoSap(compraVo.getRmSap());
                    log.setNumPedidoSap(compraVo.getCodigoPedido());
                } else if (objetoLogJson.getClass().equals(EstoqueVo.class)) {
                    EstoqueVo estoqueVo = (EstoqueVo) objetoLogJson;
                    log.setNumRma(estoqueVo.getNumeroRma());
                    log.setCodigoDeposito(estoqueVo.getDeposito());
                    log.setItemRequisicaoSap(estoqueVo.getItemRmSap());
                    log.setNumRequisicaoSap(estoqueVo.getRmSap());
                    log.setNumPedidoSap(estoqueVo.getPedidoCompra());
                } else if (objetoLogJson.getClass().equals(EstornoVo.class)) {
                    EstornoVo estornoVo = (EstornoVo) objetoLogJson;
                    log.setNumRma(estornoVo.getNumeroRma());
                    log.setItemRequisicaoSap(estornoVo.getItemRmSap());
                    log.setNumRequisicaoSap(estornoVo.getRmSap());
                } else if (objetoLogJson.getClass().equals(MaterialDepositoSaida.class)) {
                    MaterialDepositoSaida materialDepositoSaida = (MaterialDepositoSaida) objetoLogJson;
                    log.setNumRma(materialDepositoSaida.getRm() != null ? materialDepositoSaida.getRm().getNumeroRm() : null);
                    log.setCodigoDeposito( materialDepositoSaida.getMaterialDeposito() != null && materialDepositoSaida.getMaterialDeposito().getDeposito() != null ? materialDepositoSaida.getMaterialDeposito().getDeposito().getCodigo() : null);
                } else if (objetoLogJson.getClass().equals(MaterialDepositoEntrada.class)) {
                    MaterialDepositoEntrada materialDepositoEntrada = (MaterialDepositoEntrada) objetoLogJson;
                    log.setNumRma(materialDepositoEntrada.getRm() != null ? materialDepositoEntrada.getRm().getNumeroRm() : null);
                    log.setCodigoDeposito( materialDepositoEntrada.getMaterialDeposito() != null && materialDepositoEntrada.getMaterialDeposito().getDeposito() != null ? materialDepositoEntrada.getMaterialDeposito().getDeposito().getCodigo() : null);
                } else if (objetoLogJson.getClass().equals(CompraMateriaisSapVo.class)) {
                    CompraMateriaisSapVo compraMateriaisSapVo = (CompraMateriaisSapVo) objetoLogJson;
                    log.setNumRma(compraMateriaisSapVo.getNumRmaConcat());
                    log.setCodigoDeposito(compraMateriaisSapVo.getCodDepositoConcat());
                    log.setItemRequisicaoSap(compraMateriaisSapVo.getItemRmSapConcat());
                    log.setNumRequisicaoSap(compraMateriaisSapVo.getNumReqSap());
                    json = null;
                } else if (objetoLogJson.getClass().equals(MaterialDepositoVo.class)) {
                    MaterialDepositoVo materialDepositoVo = (MaterialDepositoVo) objetoLogJson;
                    log.setCodigoDeposito(materialDepositoVo.getDeposito());
                } else if(objetoLogJson.getClass().equals(RmMaterial.class)) {
                    RmMaterial rmMaterial = (RmMaterial)objetoLogJson;
                    log.setNumRma(rmMaterial.getRm()!=null ? rmMaterial.getRm().getNumeroRm() : null);
                    log.setCodigoDeposito(rmMaterial.getDeposito().getCodigo());
                    log.setItemRequisicaoSap(rmMaterial.getItemRequisicaoSap()!=null ? rmMaterial.getItemRequisicaoSap().toString():null);
                    log.setNumRequisicaoSap(rmMaterial.getNumeroRequisicaoSap());
                    log.setNumPedidoSap(rmMaterial.getNumeroPedidoCompra());
                } else if(objetoLogJson.getClass().equals(MaterialTransferenciaVo.class)) {
                    MaterialTransferenciaVo materialTransferenciaVo = (MaterialTransferenciaVo)objetoLogJson;
                    RmMaterial rmMaterial = materialTransferenciaVo.getRmMaterial();
                    log.setCodigoDeposito(materialTransferenciaVo.getDepositoOrigem() != null ? materialTransferenciaVo.getDepositoOrigem().getCodigo() : null);
                    if(rmMaterial != null) {
                        log.setNumRma(rmMaterial.getRm() != null ? rmMaterial.getRm().getNumeroRm() : null);
                        log.setItemRequisicaoSap(rmMaterial.getItemRequisicaoSap() != null ? rmMaterial.getItemRequisicaoSap().toString() : null);
                        log.setNumRequisicaoSap(rmMaterial.getNumeroRequisicaoSap());
                        log.setNumPedidoSap(rmMaterial.getNumeroPedidoCompra());
                    }
                }

                log.setJson(json);
            }

            log.setUsuarioInclusao(usuarioInclusao);
            log.setMensagem(logInterfaceVo.getMensagem());
            log.setTipoMensagem(logInterfaceVo.getTipoMensagem());
            if(log.getTipoMensagem() == null && logInterfaceVo.getCodigo() != null){
                log.setTipoMensagem(logInterfaceVo.getCodigo());
            }
            log.setErroMensagem(logInterfaceVo.getErroMensagem());

            genericDao.persist(log);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), LogInterfaceService.class.getName(), Util.getNomeMetodoAtual(), e));
        }

    }

}
