package br.com.nextage.rmaweb.service.requisicao.compra;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import br.com.nextage.rmaweb.controller.painelRequisicaoMaterial.EnviarReqMaterialSapRequest;
import br.com.nextage.rmaweb.controller.painelRequisicaoMaterial.EnviarReqMaterialSapResponse;
import br.com.nextage.rmaweb.controller.painelRequisicaoMaterial.EnviarReqReservaMaterialSapRequest;
import br.com.nextage.rmaweb.controller.painelRequisicaoMaterial.EnviarReqReservaMaterialSapResponse;
import br.com.nextage.rmaweb.service.ConsultaEstoqueMaterialSap;
import br.com.nextage.rmaweb.service.ConsultaEstoqueMaterialSapResponse;

public interface SapServiceRequisicaoCompraClient {

  EnviarReqMaterialSapResponse zglRmaCriarRequisicaoCompra(final List<EnviarReqMaterialSapRequest> lista,
    final String usuarioLogado, final HttpServletRequest request);

  ConsultaEstoqueMaterialSapResponse zglRmaBuscaEstoqueMaterial(final ConsultaEstoqueMaterialSap consulta);

}
