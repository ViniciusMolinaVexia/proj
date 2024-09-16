package br.com.nextage.rmaweb.service.requisicao.compra;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.xml.bind.DatatypeConverter;

import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.controller.painelRequisicaoMaterial.EnviarReqMaterialSapRequest;
import br.com.nextage.rmaweb.controller.painelRequisicaoMaterial.EnviarReqMaterialSapResponse;
import br.com.nextage.rmaweb.controller.painelRequisicaoMaterial.ItemReqMaterialSapResponse;
import br.com.nextage.rmaweb.dao.RequisicaoCompraDAO;
import br.com.nextage.rmaweb.dao.RmMaterialDao;
import br.com.nextage.rmaweb.entitybean.Anexo;
import br.com.nextage.rmaweb.entitybean.ConfIntegracao;
import br.com.nextage.rmaweb.entitybean.LogInterface;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.exception.ApiException;
import br.com.nextage.rmaweb.service.ConfIntegracaoService;
import br.com.nextage.rmaweb.service.ConfIntegracaoServiceImpl;
import br.com.nextage.rmaweb.service.ConsultaEstoqueMaterialSap;
import br.com.nextage.rmaweb.service.ConsultaEstoqueMaterialSapResponse;
import br.com.nextage.rmaweb.service.EstoqueMateriaisSap;
import br.com.nextage.rmaweb.service.impl.ServiceSapAg;
import br.com.nextage.rmaweb.service.integracao.LogInterfaceService;
import br.com.nextage.rmaweb.service.integracao.RmMaterialStatusService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.util.JsonUtils;
import com.google.gson.Gson;
import com.sap.document.sap.rfc.functions.ZGLRMAWEB;
import com.sap.document.sap.rfc.functions.ZGLSRMAREQRETURN;
import com.sap.document.sap.rfc.functions.ZGLSRMAREQUISICAO;
import com.sap.document.sap.rfc.functions.ZGLSRMAREQUISICAOANEXO;
import com.sap.document.sap.rfc.functions.ZGLSRMAWEBMATERIAL;
import com.sap.document.sap.rfc.functions.ZGLSRMAWEBMATERIALSTOCK;
import com.sap.document.sap.rfc.functions.ZGLSRMAWEBSTOCKIN;
import com.sap.document.sap.rfc.functions.ZGLSRMAWEBSTOCKOUT;
import com.sap.document.sap.rfc.functions.ZGLSRMAWEBTABMATERIAL;
import com.sap.document.sap.rfc.functions.ZGLSTABRMAREQRETURN;
import com.sap.document.sap.rfc.functions.ZGLSTABRMAREQUISICAO;
import com.sap.document.sap.rfc.functions.ZGLSTABRMAREQUISICAOANEXO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class SapServiceRequisicaoCompraClientImpl implements SapServiceRequisicaoCompraClient {

    private static final Logger LOG = LoggerFactory.getLogger(SapServiceRequisicaoCompraClientImpl.class);

    private static final String LABEL_ZGLS_RMA_REQUISICAO = "SAP CRIAR REQUISICAO MATERIAL";
    private static final String MESSAGE_ZGLS_RMA_REQUISICAO = "Um erro ocorreu ao tentar criar requisição no SAP.";
    private static final String STATUS_SUCCESS = "S";
    private static final String STATUS_WARNING = "W";
    private static final String STATUS_ERROR = "E";

    private ConfIntegracaoService confIntegracaoService;
    private ServiceSapAg service;
    private ConfIntegracao configCompraMaterial;
    private ZGLRMAWEB webServiceClient;
    private RmMaterialDao dao;
    private RmMaterialStatusService rmMaterialStatusService;

    public SapServiceRequisicaoCompraClientImpl() {
        super();
        confIntegracaoService = new ConfIntegracaoServiceImpl();

        ConfIntegracao config = confIntegracaoService
                .listarConfIntegracao(Constantes.CODIGO_INTEGRACAO_COMPRA_MATERIAL);

        confIntegracaoService.autenticar(config.getLogin(), config.getSenha(), config.getUrl());
        configCompraMaterial = obterConfigCompraMaterial();

        ClassLoader classLoader = getClass().getClassLoader();
        // TODO - ADRIANO, precisa voltar quando for subir para produção.
        service = new ServiceSapAg(classLoader.getResource("zgl_rmaweb.xml"));
//        service = new ServiceSapAg(classLoader.getResource("zgl_rmaweb_prod.xml"));
        webServiceClient = service.getBinding();
        dao = new RmMaterialDao();
    }

    private Function<EnviarReqMaterialSapRequest, ZGLSRMAREQUISICAO> mapperRequestToRequestSoap = request -> {
        ZGLSRMAREQUISICAO requestSoap = new ZGLSRMAREQUISICAO();
        requestSoap.setITEM(request.getItem());
        requestSoap.setDOCTYPE(truncate(request.getCodigoPrioridadeSap(), 4));
        requestSoap.setCODRMA(request.getCodigoRma());
        requestSoap.setMATNR(request.getCodigoMaterialSap());
        requestSoap.setTEXTOITEM(request.getTextoItem());
        requestSoap.setAFNAM(truncate(request.getNomeSolicitante(), 12));
        requestSoap.setWERKS(request.getCentroSap());
        requestSoap.setMENGE(request.getQuantidade());
        requestSoap.setMEINS(request.getUnidadeMedida());
        requestSoap.setPSPSPPNR("");
        requestSoap.setVORNR(request.getElementoEp());
        requestSoap.setLGORT(request.getDepositoSap());
        requestSoap.setEEIND(request.getDataRemessa());
        final String APRORMA = truncate(request.getUsuarioCadastro(), 12);
        requestSoap.setAPRORMA(APRORMA);

        requestSoap.setEKGRP(request.getGrupoCompradores());
        requestSoap.setBADAT(request.getDataAprovacao());
        requestSoap.setWGBEZ(request.getGrupoMercadoria());
        requestSoap.setEBELN(request.getCodigoContratoSap());
        requestSoap.setZZKOKRS(request.getAreaSap());

        BigDecimal valorOrcado = request.getValorOrcado();

        if(valorOrcado != null) {
            requestSoap.setPREIS(valorOrcado.setScale(2));
        }else {
            requestSoap.setPREIS(BigDecimal.ZERO);
        }

        requestSoap.setOBSSERVACAO(request.getObservacaoRm());

        return requestSoap;
    };
    
    private Function<Anexo, ZGLSRMAREQUISICAOANEXO> mapperRequestAnexoToRequestSoap = request -> {
    	ZGLSRMAREQUISICAOANEXO requestSoap = new ZGLSRMAREQUISICAOANEXO();
        requestSoap.setANEXO(DatatypeConverter.printBase64Binary(request.getAnexo()));
        requestSoap.setEXTENSAO(request.getNome().toString());
        
        return requestSoap;
    };


    private Function<ZGLSTABRMAREQRETURN, EnviarReqMaterialSapResponse> mapperResponseSoapToResponse = responseSoap -> {
        EnviarReqMaterialSapResponse response = new EnviarReqMaterialSapResponse();
        ItemReqMaterialSapResponse itemReqTmp = null;

        List<ZGLSRMAREQRETURN> itens = responseSoap.getItem();

        for (ZGLSRMAREQRETURN item : itens) {
            itemReqTmp = new ItemReqMaterialSapResponse();
            itemReqTmp.setItem(item.getITEM());
            itemReqTmp.setCodigoRma(item.getCODRMA());
            itemReqTmp.setMensagem(item.getMESSAGE());
            itemReqTmp.setNumeroMaterial(item.getMATNR());
            itemReqTmp.setRequisicao(item.getREQUISICAO());
            itemReqTmp.setTipoMensagem(item.getTPMSG());
            response.getItens().add(itemReqTmp);
        }

        return response;
    };

    @Override
    public EnviarReqMaterialSapResponse zglRmaCriarRequisicaoCompra(final List<EnviarReqMaterialSapRequest> lista,
      final String usuarioLogado, final HttpServletRequest request) {
    	
    	RequisicaoCompraDAO rCompraDAO = new RequisicaoCompraDAO();
    	List<Anexo> anexo = rCompraDAO.getAnexosByRm(Integer.parseInt(lista.get(0).getRmId()));
    	
        final List<ZGLSRMAREQUISICAO> requisicoes = lista.stream()
                .map(req -> mapperRequestToRequestSoap.apply(req)).collect(Collectors.toList());
        final List<ZGLSRMAREQUISICAOANEXO> anexos = anexo.stream()
        		.map(req -> mapperRequestAnexoToRequestSoap.apply(req)).collect(Collectors.toList());

        return processarRequisicaoCompraMaterial(requisicoes, usuarioLogado, request, anexos);
    }

	@Override
    public ConsultaEstoqueMaterialSapResponse zglRmaBuscaEstoqueMaterial(ConsultaEstoqueMaterialSap consulta) {

        //Popula request para o SAP
        ZGLSRMAWEBSTOCKIN request = populaRequestEstoqueMateriaisSap(consulta);
        LOG.debug("Request ZGLSRMAWEBSTOCKIN: " + JsonUtils.convertToJSon(request));


        //Faz requisiçao ao metodo de consulta de estoque no SAP
        LOG.debug("Vai enviar consulta de estoque ao SAP para centro: " + request.getCENTRO());
        ZGLSRMAWEBSTOCKOUT responseSap = webServiceClient.zglRMABUSCAESTOQUE(request);
        LOG.debug("Response obtido da chamada ao SAP: " + JsonUtils.convertToJSon(responseSap));


        //Trata retorno da chamada ao SAP
        ConsultaEstoqueMaterialSapResponse response = tratarRetornoDeConsultaDeEstoqueDoSap(request, responseSap);
        LOG.debug("Retorno ao metodo de aplicação: " + JsonUtils.convertToJSon(response));

        return response;
    }

    private ZGLSRMAWEBSTOCKIN populaRequestEstoqueMateriaisSap(ConsultaEstoqueMaterialSap consulta) {

        ZGLSRMAWEBSTOCKIN request = new ZGLSRMAWEBSTOCKIN();

        ZGLSRMAWEBTABMATERIAL listaMateriais = new ZGLSRMAWEBTABMATERIAL();
        List<ZGLSRMAWEBMATERIAL> item = listaMateriais.getItem();

        consulta.getMateriais().forEach(material -> {
            ZGLSRMAWEBMATERIAL m = new ZGLSRMAWEBMATERIAL();
            m.setMATERIAL(material);
            item.add(m);
        });

        final String centro = StringUtils.isEmpty(consulta.getDeposito()) ? consulta.getCentro(): consulta.getCentro() + "|" + consulta.getDeposito();
        request.setCENTRO(centro);
        request.setTMATERIAL(listaMateriais);

        return request;
    }

    private ConsultaEstoqueMaterialSapResponse tratarRetornoDeConsultaDeEstoqueDoSap(ZGLSRMAWEBSTOCKIN request, ZGLSRMAWEBSTOCKOUT responseSap) {
        List<EstoqueMateriaisSap> listEstoqueMateriaisSap = new ArrayList<>();

        List<ZGLSRMAWEBMATERIALSTOCK> itemEstoque = responseSap.getTESTOQUE().getItem();
        itemEstoque.forEach(i ->{

            EstoqueMateriaisSap estoqueMateriaisSap = new EstoqueMateriaisSap();
            estoqueMateriaisSap.setNomeDeposito(i.getDEPOSITO());
            estoqueMateriaisSap.setCodigoMaterial(i.getMATERIAL());
            estoqueMateriaisSap.setQuantidadeEstoque(i.getESTOQUELIVRE());

            listEstoqueMateriaisSap.add(estoqueMateriaisSap);

        });

        ConsultaEstoqueMaterialSapResponse response = new ConsultaEstoqueMaterialSapResponse();
        response.setNomeCentro(request.getCENTRO());
        response.setEstoqueMateriaisSap(listEstoqueMateriaisSap);
        return response;
    }

    private ConfIntegracao obterConfigCompraMaterial() {
        return confIntegracaoService.listarConfIntegracao(Constantes.CODIGO_INTEGRACAO_COMPRA_MATERIAL);
    }

    private EnviarReqMaterialSapResponse processarRequisicaoCompraMaterial(final List<ZGLSRMAREQUISICAO> requestSap, final String usuarioLogado, final HttpServletRequest request, final List<ZGLSRMAREQUISICAOANEXO> anexo) {
        ZGLSTABRMAREQUISICAO listaRequisicoes = new ZGLSTABRMAREQUISICAO();
        ZGLSTABRMAREQUISICAOANEXO listaAnexos = new ZGLSTABRMAREQUISICAOANEXO();
        listaRequisicoes.getItem().addAll(requestSap);
        listaAnexos.getItem().addAll(anexo);
        //HM
        //Anexo Here

        gravarRequest(requestSap, usuarioLogado);

        final ZGLSTABRMAREQRETURN responseSap = webServiceClient.zglRMACRIAREQUISICAO(listaRequisicoes, listaAnexos);

        if (responseSap != null && responseSap.getItem() != null && !responseSap.getItem().isEmpty()) {
            final EnviarReqMaterialSapResponse response = mapperResponseSoapToResponse.apply(responseSap);

            gravarResponse(responseSap, usuarioLogado);

            if (response.getItens() != null && !response.getItens().isEmpty()) {
                atualizarStatusRMA(response.getItens(), usuarioLogado, request);
            }
            response.contarItensComApostamentos();
            return response;
        } else {
            throw new ApiException(LABEL_ZGLS_RMA_REQUISICAO, "Não foi possível processar solicitação, o retorno 'ZGLSTABRMAREQRETURN.item' do SAP está vazio!");
        }
    }

    private void gravarRequest(final List<ZGLSRMAREQUISICAO> request, final String usuarioLogado) {
        try {
            if (request != null) {

                for (ZGLSRMAREQUISICAO item : request) {
                    LogInterface log = new LogInterface();
                    log.setDataHoraInclusaoLog(new Date());
                    log.setMensagem("");
                    log.setSistema(Constantes.SISTEMA_SAP);
                    log.setInterfaceExec(Constantes.INTERFACE_REQUISICAO_COMPRA_REQUEST);
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

    private void gravarResponse(final ZGLSTABRMAREQRETURN response, final String usuarioLogado) {
        try {
            if (response != null) {

                List<ZGLSRMAREQRETURN> itens = response.getItem();

                for (ZGLSRMAREQRETURN item : itens) {
                    LogInterface log = new LogInterface();
                    log.setDataHoraInclusaoLog(new Date());
                    log.setMensagem(item.getMESSAGE());
                    log.setSistema(Constantes.SISTEMA_SAP);
                    log.setInterfaceExec(Constantes.INTERFACE_REQUISICAO_COMPRA_RESPONSE);
                    log.setNomeClasse(item.getClass().getName());
                    log.setUsuarioInclusao(usuarioLogado);
                    log.setNumRma(item.getCODRMA());
                    log.setItemRequisicaoSap(item.getITEM());
                    log.setNumRequisicaoSap(item.getREQUISICAO());
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
                String codigoStatus = "AgComp";
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
