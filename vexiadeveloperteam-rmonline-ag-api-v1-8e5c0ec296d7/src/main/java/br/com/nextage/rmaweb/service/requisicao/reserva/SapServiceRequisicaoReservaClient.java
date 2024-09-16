package br.com.nextage.rmaweb.service.requisicao.reserva;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import br.com.nextage.rmaweb.controller.painelRequisicaoMaterial.EnviarReqReservaMaterialSapRequest;
import br.com.nextage.rmaweb.controller.painelRequisicaoMaterial.EnviarReqReservaMaterialSapResponse;

public interface SapServiceRequisicaoReservaClient {

  EnviarReqReservaMaterialSapResponse zglRmaCriarRequisicaoReserva(final List<EnviarReqReservaMaterialSapRequest> lista,
    final String usuarioLogado, final HttpServletRequest request);

}
