package br.com.nextage.rmaweb.service.requisicao.reserva;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.controller.painelRequisicaoMaterial.EnviarReqReservaMaterialSapRequest;
import br.com.nextage.rmaweb.controller.painelRequisicaoMaterial.EnviarReqReservaMaterialSapResponse;
import br.com.nextage.rmaweb.controller.painelRequisicaoMaterial.ItemReqMaterialSapResponse;
import br.com.nextage.rmaweb.dao.RmMaterialDao;
import br.com.nextage.rmaweb.entitybean.ConfIntegracao;
import br.com.nextage.rmaweb.entitybean.LogInterface;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.exception.ApiException;
import br.com.nextage.rmaweb.service.ConfIntegracaoService;
import br.com.nextage.rmaweb.service.ConfIntegracaoServiceImpl;
import br.com.nextage.rmaweb.service.impl.ServiceSapAg;
import br.com.nextage.rmaweb.service.integracao.LogInterfaceService;
import br.com.nextage.rmaweb.service.integracao.RmMaterialStatusService;
import br.com.nextage.rmaweb.util.Constantes;
import com.google.gson.Gson;
import com.sap.document.sap.rfc.functions.ZGLRMAWEB;
import com.sap.document.sap.rfc.functions.ZGLSRMARESERVA;
import com.sap.document.sap.rfc.functions.ZGLSRMARESRETURN;
import com.sap.document.sap.rfc.functions.ZGLSTABRESRETURN;
import com.sap.document.sap.rfc.functions.ZGLSTABRMARESERVA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SapServiceRequisicaoReservaClientImpl implements SapServiceRequisicaoReservaClient {

    private static final Logger LOG = LoggerFactory.getLogger(
      SapServiceRequisicaoReservaClientImpl.class);

    private static final String LABEL_ZGLS_RMA_REQUISICAO = "SAP CRIAR REQUISICAO MATERIAL";
    private static final String MESSAGE_ZGLS_RMA_REQUISICAO = "Um erro ocorreu ao tentar criar requisição no SAP.";
    private static final String STATUS_SUCCESS = "S";
    private static final String STATUS_WARNING = "W";
    private static final String STATUS_ERROR = "E";

    private ConfIntegracaoService confIntegracaoService;
    private ServiceSapAg service;
    private ConfIntegracao configIntegracao;
    private ZGLRMAWEB webServiceClient;
    private RmMaterialDao dao;
    private RmMaterialStatusService rmMaterialStatusService;

    public SapServiceRequisicaoReservaClientImpl() {
        super();
        confIntegracaoService = new ConfIntegracaoServiceImpl();

        this.configIntegracao = confIntegracaoService
                .listarConfIntegracao(Constantes.CODIGO_INTEGRACAO_COMPRA_MATERIAL);

        confIntegracaoService.autenticar(configIntegracao.getLogin(), configIntegracao.getSenha(), configIntegracao.getUrl());


        ClassLoader classLoader = getClass().getClassLoader();
        service = new ServiceSapAg(classLoader.getResource("zgl_rmaweb.xml"));
        webServiceClient = service.getBinding();
        dao = new RmMaterialDao();
    }



    private Function<EnviarReqReservaMaterialSapRequest, ZGLSRMARESERVA> mapperRequestToRequestSoap = request -> {
        final String APRORMA = truncate(request.getUsuarioCadastro(), 12);

        ZGLSRMARESERVA requestSoap = new ZGLSRMARESERVA();
        //requestSoap.setCODRMA(request.getCodigoRma());
        requestSoap.setCODRMA(request.getCodigoRma());
        requestSoap.setITEM(request.getItem());
        requestSoap.setDTRESERVA(request.getDataReserva());
        requestSoap.setWERKS(request.getCentroSap());
        requestSoap.setSOLICITANTE(truncate(request.getNomeSolicitante(), 12));
        requestSoap.setAPROVADOR(APRORMA);
        requestSoap.setMATNR(request.getCodigoMaterialSap());
        requestSoap.setMENGE(request.getQuantidade());
        requestSoap.setMEINS(request.getUnidadeMedida());
        requestSoap.setLGORT(request.getDepositoSap());
        requestSoap.setEEIND(request.getDataNecessidade());

        if(request.getColetorCustos() != null && !request.getColetorCustos().equals("")) {
            requestSoap.setCOLETORCUSTO("EP" + request.getColetorCustos());
        } else if(request.getDiagramaRede() != null && !request.getDiagramaRede().equals("")) {
            requestSoap.setCOLETORCUSTO("CC" + request.getDiagramaRede());
        }

        return requestSoap;
    };

    private Function<ZGLSTABRESRETURN, EnviarReqReservaMaterialSapResponse> mapperResponseSoapToResponse = responseSoap -> {
        EnviarReqReservaMaterialSapResponse response = new EnviarReqReservaMaterialSapResponse();
        ItemReqMaterialSapResponse itemReqTmp = null;

        List<ZGLSRMARESRETURN> itens = responseSoap.getItem();

        for (ZGLSRMARESRETURN item : itens) {
            itemReqTmp = new ItemReqMaterialSapResponse();
            itemReqTmp.setItem(item.getITEM());
            itemReqTmp.setCodigoRma(item.getCODRMA());
            itemReqTmp.setMensagem(item.getMESSAGE());
            itemReqTmp.setTipoMensagem(item.getTPMSG());
//            itemReqTmp.setNumeroMaterial(item.getMATNR());
            itemReqTmp.setRequisicao(item.getRESERVA());

            response.getItens().add(itemReqTmp);
        }

        return response;
    };

    @Override
    public EnviarReqReservaMaterialSapResponse zglRmaCriarRequisicaoReserva(final List<EnviarReqReservaMaterialSapRequest> lista,
      final String usuarioLogado, final HttpServletRequest request) {
        final List<ZGLSRMARESERVA> requisicoes = lista.stream()
          .map(req -> mapperRequestToRequestSoap.apply(req)).collect(Collectors.toList());
        return processarRequisicaoReservaMaterial(requisicoes, usuarioLogado, request);
    }

    private ConfIntegracao obterConfigReservaMaterial() {
        return confIntegracaoService.listarConfIntegracao(Constantes.CODIGO_INTEGRACAO_RESERVA_MATERIAL);
    }

    private EnviarReqReservaMaterialSapResponse processarRequisicaoReservaMaterial(final List<ZGLSRMARESERVA> requestSap,
      final String usuarioLogado, final HttpServletRequest request) {
        ZGLSTABRMARESERVA listaRequisicoes = new ZGLSTABRMARESERVA();
        listaRequisicoes.getItem().addAll(requestSap);

        gravarRequest(requestSap, usuarioLogado);

        final ZGLSTABRESRETURN responseSap = webServiceClient.zglRMACRIARESERVA(listaRequisicoes);

        if (responseSap != null && responseSap.getItem() != null && !responseSap.getItem().isEmpty()) {
            final EnviarReqReservaMaterialSapResponse response = mapperResponseSoapToResponse.apply(responseSap);

            gravarResponse(responseSap, usuarioLogado);

            if (response.getItens() != null && !response.getItens().isEmpty()) {
                atualizarStatusRMA(response.getItens(), usuarioLogado, request);
            }
            response.contarItensComApostamentos();
            return response;
        } else {
            LOG.warn("Não foi possível processar solicitação, o retorno 'ZGLSTABRMAREQRETURN.item' do SAP está vazio!", responseSap);
            throw new ApiException(LABEL_ZGLS_RMA_REQUISICAO, "Não foi possível processar solicitação, o retorno 'ZGLSTABRMAREQRETURN.item' do SAP está vazio!");
        }
    }

    private void gravarRequest(final List<ZGLSRMARESERVA> request, final String usuarioLogado) {
        try {
            if (request != null) {

                for (ZGLSRMARESERVA item : request) {
                    LogInterface log = new LogInterface();
                    log.setDataHoraInclusaoLog(new Date());
                    log.setMensagem("");
                    log.setSistema(Constantes.SISTEMA_SAP);
                    log.setInterfaceExec(Constantes.INTERFACE_REQUISICAO_RESERVA_REQUEST);
                    log.setNomeClasse(item.getClass().getName());
                    log.setUsuarioInclusao(usuarioLogado);
                    log.setNumRma(item.getCODRMA());
                    log.setCodigoDeposito(item.getLGORT());

                    String json = new Gson().toJson(item);
                    log.setJson(json);

                    //Gerando Log de interface
                    LogInterfaceService.inserirLog(log);
                }
            }
        } catch (Exception e) {
            LOG.error("Não foi possível registrar log do request de requisição de material para o SAP", e);
        }
    }

    private void gravarResponse(final ZGLSTABRESRETURN response, final String usuarioLogado) {
        try {
            if (response != null) {

                List<ZGLSRMARESRETURN> itens = response.getItem();

                for (ZGLSRMARESRETURN item : itens) {
                    LogInterface log = new LogInterface();
                    log.setDataHoraInclusaoLog(new Date());
                    log.setMensagem(item.getMESSAGE());
                    log.setSistema(Constantes.SISTEMA_SAP);
                    log.setInterfaceExec(Constantes.INTERFACE_REQUISICAO_RESERVA_RESPONSE);
                    log.setNomeClasse(item.getClass().getName());
                    log.setUsuarioInclusao(usuarioLogado);
                    log.setNumRma(item.getCODRMA());
                    log.setItemRequisicaoSap(item.getITEM());
                    log.setNumRequisicaoSap(item.getRESERVA());
                    log.setTipoMensagem(item.getTPMSG());
                    log.setMensagem(item.getMESSAGE());


                    String json = new Gson().toJson(item);
                    log.setJson(json);

                    //Gerando Log de interface
                    LogInterfaceService.inserirLog(log);
                }
            }
        } catch (Exception e) {
            LOG.error("Não foi possível registrar log do response de requisição de material para o SAP", e);
        }
    }

    private void atualizarStatusRMA(final List<ItemReqMaterialSapResponse> itens, final String usuarioLogado, final HttpServletRequest request) {
        try {
            for (ItemReqMaterialSapResponse item : itens) {
                atualizarRmaPorTipoRetornoSAP(item);
                atualizarStatusMaterialWorkflow(item, usuarioLogado, request);
            }
        } catch (Exception e) {
            LOG.error("Um erro ocorreu ao tentar atualizar status da RMA", itens, e);
        }
    }

    private void atualizarRmaPorTipoRetornoSAP(final ItemReqMaterialSapResponse item) {
        try {
            String codItem = item.getItem();
            String requisicao = item.getRequisicao();
            String status = item.getTipoMensagem();
            Integer codigoRma = Integer.parseInt(item.getCodigoRma());

            dao.atualizarStatusRma(codItem, requisicao, status, codigoRma);
        } catch (Exception e) {
            LOG.error("Erro ao tentar atualizar status requisição de material.", e);
        }
    }

    private void atualizarStatusMaterialWorkflow(final ItemReqMaterialSapResponse item, final String usuarioLogado, final HttpServletRequest request) throws Exception {
        if (item.getTipoMensagem().equals(STATUS_SUCCESS)) {
            GenericDao genericDao = new GenericDao();
            try {
                RmMaterial rmMaterial = new RmMaterial(Integer.parseInt(item.getCodigoRma()));
                String codigoStatus = "AgRet";
                Date data = new Date();
                String observacao = item.getMensagem();

                genericDao.beginTransaction();
                rmMaterialStatusService = new RmMaterialStatusService(request);

                rmMaterialStatusService.gerarStatus(rmMaterial, codigoStatus, genericDao, null, observacao, usuarioLogado);
                genericDao.commitCurrentTransaction();
            } catch (Exception e) {
                genericDao.rollbackCurrentTransaction();
            }
        }
    }

    public static String truncate(String value, int length) {
        if (value != null && value.length() > length)
            value = value.substring(0, length);
        return value;
    }

}
