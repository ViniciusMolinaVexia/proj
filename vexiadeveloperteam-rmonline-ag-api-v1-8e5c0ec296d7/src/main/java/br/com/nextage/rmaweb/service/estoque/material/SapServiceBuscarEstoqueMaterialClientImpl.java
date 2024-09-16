package br.com.nextage.rmaweb.service.estoque.material;

import java.util.ArrayList;
import java.util.List;
import br.com.nextage.rmaweb.entitybean.ConfIntegracao;
import br.com.nextage.rmaweb.service.ConfIntegracaoService;
import br.com.nextage.rmaweb.service.ConfIntegracaoServiceImpl;
import br.com.nextage.rmaweb.service.ConsultaEstoqueMaterialSap;
import br.com.nextage.rmaweb.service.ConsultaEstoqueMaterialSapResponse;
import br.com.nextage.rmaweb.service.EstoqueMateriaisSap;
import br.com.nextage.rmaweb.service.impl.ServiceSapAg;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.util.JsonUtils;
import com.sap.document.sap.rfc.functions.ZGLRMAWEB;
import com.sap.document.sap.rfc.functions.ZGLSRMAWEBMATERIAL;
import com.sap.document.sap.rfc.functions.ZGLSRMAWEBMATERIALSTOCK;
import com.sap.document.sap.rfc.functions.ZGLSRMAWEBSTOCKIN;
import com.sap.document.sap.rfc.functions.ZGLSRMAWEBSTOCKOUT;
import com.sap.document.sap.rfc.functions.ZGLSRMAWEBTABMATERIAL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SapServiceBuscarEstoqueMaterialClientImpl implements SapServiceBuscarEstoqueMaterialClient {

    private static final Logger LOG = LoggerFactory.getLogger(
      SapServiceBuscarEstoqueMaterialClientImpl.class);

    private ConfIntegracaoService confIntegracaoService;
    private ServiceSapAg service;
    private ConfIntegracao configSap;
    private ZGLRMAWEB webServiceClient;

    public SapServiceBuscarEstoqueMaterialClientImpl() {
        super();
        confIntegracaoService = new ConfIntegracaoServiceImpl();

        ConfIntegracao config = confIntegracaoService
                .listarConfIntegracao(Constantes.CODIGO_INTEGRACAO_COMPRA_MATERIAL);

        confIntegracaoService.autenticar(config.getLogin(), config.getSenha(), config.getUrl());
        configSap = obterConfigSap();

        ClassLoader classLoader = getClass().getClassLoader();
        service = new ServiceSapAg(classLoader.getResource("zgl_rmaweb.xml"));
        webServiceClient = service.getBinding();
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

        request.setCENTRO(consulta.getCentro());
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

    private ConfIntegracao obterConfigSap() {
        return confIntegracaoService.listarConfIntegracao(Constantes.CODIGO_INTEGRACAO_COMPRA_MATERIAL);
    }


    public static String truncate(String value, int length) {
        if (value != null && value.length() > length)
            value = value.substring(0, length);
        return value;
    }

}
