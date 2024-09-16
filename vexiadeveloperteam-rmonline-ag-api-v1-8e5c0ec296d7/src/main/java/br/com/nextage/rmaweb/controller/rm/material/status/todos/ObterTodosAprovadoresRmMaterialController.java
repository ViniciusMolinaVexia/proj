package br.com.nextage.rmaweb.controller.rm.material.status.todos;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("rm/material/status/aprovadores")
public class ObterTodosAprovadoresRmMaterialController {

  @Inject
  private ObterTodosAprovadoresRmMaterialService obterTodosAprovadoresRmMaterialService;

  @POST
  @Path("todos")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public ObterTodosAprovadoresRmMaterialResponse obter(ObterTodosAprovadoresRmMaterialRequest request)
    throws Exception {
    return this.obterTodosAprovadoresRmMaterialService
      .obterTodosAprovadoresMaterial(request.getIdRm(), request.getIdRmMaterial(), request.getIdArea(),
        request.getIdCentro(), request.getPrioridade());
  }
}
