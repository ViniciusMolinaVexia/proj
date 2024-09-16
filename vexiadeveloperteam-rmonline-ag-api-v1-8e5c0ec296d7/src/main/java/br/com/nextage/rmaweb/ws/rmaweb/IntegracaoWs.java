package br.com.nextage.rmaweb.ws.rmaweb;

import br.com.nextage.rmaweb.service.integracao.*;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.LogInterfaceVo;
import br.com.nextage.rmaweb.vo.integracao.*;
import br.com.nextage.util.NxResourceBundle;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author l.pordeus
 */
@WebService(serviceName = "IntegracaoWs")
public class IntegracaoWs {

    /**
     * Operação que realiza cadastro de fornecedores, de acordo com lista de
     * fornecedores passada por parâmetro.
     *
     * @param login
     * @param token
     * @param listaFornecedores
     * @return
     */
    @WebMethod(operationName = "cadastroFornecedores")
    public List<MensagemIntegracao> cadastroFornecedores(@WebParam(name = "login") String login,
            @WebParam(name = "token") String token,
            @WebParam(name = "listaFornecedores") List<FornecedorVo> listaFornecedores) {

        MensagemIntegracao mensagemIntegracao = null;
        List<MensagemIntegracao> lista = new ArrayList<>();
        try {
            ConfigAcessoServService configAcessoServService = new ConfigAcessoServService();
            if (configAcessoServService.selectConfigAcessoServ(login, token, Constantes.CODIGO_INTEGRACAO_FORNECEDOR) != null) {
                FornecedorIntegracaoService fornecedorIntegracaoService = new FornecedorIntegracaoService();
                lista = fornecedorIntegracaoService.salvar(listaFornecedores);
            } else {
                NxResourceBundle resourceBundle = new NxResourceBundle(null);
                mensagemIntegracao = new MensagemIntegracao("E", resourceBundle.getString(Constantes.MSG_USUARIO_NAO_ENCONTRADO_I18N),null);
                lista.add(mensagemIntegracao);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Operação que realiza cadastro de materiais, de acordo com lista de
     * fornecedores passada por parâmetro.
     *
     * @param login
     * @param token
     * @param listaMateriais
     * @return
     */
    @WebMethod(operationName = "cadastroMateriais")
    public List<MensagemIntegracao> cadastroMateriais(@WebParam(name = "login") String login,
            @WebParam(name = "token") String token,
            @WebParam(name = "listaMateriais") List<MaterialVo> listaMateriais) {

        MensagemIntegracao mensagemIntegracao = null;
        List<MensagemIntegracao> lista = new ArrayList<>();
        try {
            ConfigAcessoServService configAcessoServService = new ConfigAcessoServService();
            if (configAcessoServService.selectConfigAcessoServ(login, token, Constantes.CODIGO_INTEGRACAO_MATERIAL) != null) {
                MaterialIntegracaoService materialIntegracaoService = new MaterialIntegracaoService();
                lista = materialIntegracaoService.salvar(listaMateriais);
            } else {
                NxResourceBundle resourceBundle = new NxResourceBundle(null);
                mensagemIntegracao = new MensagemIntegracao("E", resourceBundle.getString(Constantes.MSG_USUARIO_NAO_ENCONTRADO_I18N),null);
                lista.add(mensagemIntegracao);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Operação que recebe uma compra do sap
     *
     * @param login
     * @param token
     * @param listaCompra
     * @return
     */
    @WebMethod(operationName = "receberCompra")
    public List<MensagemIntegracao> receberCompra(@WebParam(name = "login") String login,
            @WebParam(name = "token") String token,
            @WebParam(name = "listaCompra") List<CompraVo> listaCompra) {

        MensagemIntegracao mensagemIntegracao = null;
        List<MensagemIntegracao> lista = new ArrayList<>();
        List<LogInterfaceVo> logInterfaceVo = new ArrayList<>();
        try {
            ConfigAcessoServService configAcessoServService = new ConfigAcessoServService();
            if (configAcessoServService.selectConfigAcessoServ(login, token, Constantes.CODIGO_INTEGRACAO_COMPRA) != null) {
                CompraIntegracaoService compraIntegracaoService = new CompraIntegracaoService();
                logInterfaceVo = compraIntegracaoService.salvar(login, listaCompra);
            } else {
                NxResourceBundle resourceBundle = new NxResourceBundle(null);
                mensagemIntegracao = new MensagemIntegracao("E", resourceBundle.getString(Constantes.MSG_USUARIO_NAO_ENCONTRADO_I18N),null);
                lista.add(mensagemIntegracao);
            }

//            for(MensagemIntegracao mensagemFinal : lista){
//                //Gerando Log de interface
////                LogInterfaceService.inserirLog(Constantes.SISTEMA_SAP, Constantes.INTERFACE_ESTOQUE, estoqueVo.getNumeroRma(), estoqueVo.getDeposito(),
////                        estoqueVo.getItemRmSap(), estoqueVo.getPedidoCompra(), estoqueVo.getRmSap(), login, estoqueVo, mensagemFinal.getMensagem(),
////                        mensagemFinal.getTipoMensagem(), mensagemFinal.getErroMensagem());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Operação que atualiza o estoque de acordo com o sap
     *
     * @param login
     * @param token
     * @param estoqueVo
     * @return
     */
    @WebMethod(operationName = "atualizarEstoque")
    public List<MensagemIntegracao> atualizarEstoque(@WebParam(name = "login") String login,
            @WebParam(name = "token") String token,
            @WebParam(name = "estoqueVo") EstoqueVo estoqueVo) {

        MensagemIntegracao mensagemIntegracao = new MensagemIntegracao();
        List<MensagemIntegracao> lista = new ArrayList<>();
        List<LogInterfaceVo> listaLog = new ArrayList<>();
        try {
            ConfigAcessoServService configAcessoServService = new ConfigAcessoServService();
            if (configAcessoServService.selectConfigAcessoServ(login, token, Constantes.CODIGO_INTEGRACAO_ESTOQUE) != null) {
                EstoqueIntegracaoService estoqueIntegracaoService = new EstoqueIntegracaoService();
                listaLog = estoqueIntegracaoService.atualizar(login, estoqueVo);
            } else {
                NxResourceBundle resourceBundle = new NxResourceBundle(null);
                LogInterfaceVo logVo = new LogInterfaceVo();
                logVo.setTipoMensagem("E");
                logVo.setMensagem(resourceBundle.getString(Constantes.MSG_USUARIO_NAO_ENCONTRADO_I18N));
                listaLog.add(logVo);
            }

            //Recupera logs de retorno para transformar no objeto a ser retornado para o SAP.
            LogInterfaceVo logInterfaceVo = null;
            if(listaLog != null && !listaLog.isEmpty() && listaLog.get(0) != null){
                logInterfaceVo = listaLog.get(0);
            }

            mensagemIntegracao = new MensagemIntegracao(logInterfaceVo.getTipoMensagem(),logInterfaceVo.getMensagem(),logInterfaceVo.getCodigo());
            lista.add(mensagemIntegracao);

            //Gerando Log de interface
            LogInterfaceService.inserirLog(Constantes.SISTEMA_SAP, Constantes.INTERFACE_ESTOQUE, estoqueVo.getNumeroRma(), estoqueVo.getDeposito(),
                    estoqueVo.getItemRmSap(), estoqueVo.getRmSap(), estoqueVo.getPedidoCompra(), login, estoqueVo, logInterfaceVo);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Operação que recebe um estorno do sap
     *
     * @param login
     * @param token
     * @param estornoVo
     * @return
     */
    @WebMethod(operationName = "estornoRequisicao")
    public List<MensagemIntegracao> estornoRequisicao(@WebParam(name = "login") String login,
            @WebParam(name = "token") String token,
            @WebParam(name = "estornoVo") List<EstornoVo> estornoVo) {

        MensagemIntegracao mensagemIntegracao = null;
        List<MensagemIntegracao> lista = new ArrayList<>();
        List<LogInterfaceVo> listaLog = new ArrayList<>();
        try {
            ConfigAcessoServService configAcessoServService = new ConfigAcessoServService();
            if (configAcessoServService.selectConfigAcessoServ(login, token, Constantes.CODIGO_INTEGRACAO_ESTORNO) != null) {
                EstornoIntegracaoService estornoIntegracaoService = new EstornoIntegracaoService();
                listaLog = estornoIntegracaoService.salvar(login, estornoVo);
            } else {
                NxResourceBundle resourceBundle = new NxResourceBundle(null);
                LogInterfaceVo logVo = new LogInterfaceVo();
                logVo.setTipoMensagem("E");
                logVo.setMensagem(resourceBundle.getString(Constantes.MSG_USUARIO_NAO_ENCONTRADO_I18N));
                listaLog.add(logVo);
            }

            //Recupera logs de retorno para transformar no objeto a ser retornado para o SAP.
            LogInterfaceVo logInterfaceVo = null;
            if(listaLog != null && !listaLog.isEmpty() && listaLog.get(0) != null){
                logInterfaceVo = listaLog.get(0);
            }

            mensagemIntegracao = new MensagemIntegracao(logInterfaceVo.getTipoMensagem(),logInterfaceVo.getMensagem(),logInterfaceVo.getCodigo());
            lista.add(mensagemIntegracao);

            //Gerando Log de interface
            //LogInterfaceService.inserirLog(Constantes.SISTEMA_SAP, Constantes.INTERFACE_ESTORNO, login, estornoVo, logInterfaceVo);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Operação que criar uma RMA de acordo com o sap
     *
     * @param login
     * @param token
     * @param rmIntegracaoVo
     * @return
     */
    @WebMethod(operationName = "criarRma")
    public MensagemIntegracao criarRma(@WebParam(name = "login") String login,
                                       @WebParam(name = "token") String token,
                                       @WebParam(name = "rmIntegracaoVo") RmIntegracaoVo rmIntegracaoVo) {

        MensagemIntegracao mensagemIntegracao = null;
        try {
            ConfigAcessoServService configAcessoServService = new ConfigAcessoServService();
            if (configAcessoServService.selectConfigAcessoServ(login, token, Constantes.CODIGO_INTEGRACAO_RM) != null) {
                RmIntegracaoService rmIntegracaoService = new RmIntegracaoService();
                mensagemIntegracao = rmIntegracaoService.criarRm(login, rmIntegracaoVo);
            } else {
                NxResourceBundle resourceBundle = new NxResourceBundle(null);
                mensagemIntegracao = new MensagemIntegracao("E", resourceBundle.getString(Constantes.MSG_USUARIO_NAO_ENCONTRADO_I18N),null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mensagemIntegracao;
    }
}
