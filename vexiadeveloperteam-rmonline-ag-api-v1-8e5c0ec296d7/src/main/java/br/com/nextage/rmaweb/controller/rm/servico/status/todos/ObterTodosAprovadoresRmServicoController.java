package br.com.nextage.rmaweb.controller.rm.servico.status.todos;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("rm/servico/status/aprovadores")
public class ObterTodosAprovadoresRmServicoController {

  @Inject
  private ObterTodosAprovadoresRmServicoService obterTodosAprovadoresRmServicoService;

  @POST
  @Path("todos")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public ObterTodosAprovadoresRmServicoResponse obter(ObterTodosAprovadoresRmServicoRequest request)
    throws Exception {
    return this.obterTodosAprovadoresRmServicoService
      .obterTodosAprovadoresServico(request.getIdRm(), request.getIdRmServico(), request.getIdArea(),
        request.getIdCentro(), request.getPrioridade());
  }
}
