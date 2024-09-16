package br.com.nextage.rmaweb.controller.rm.material.status.aprovadores;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("rm/material/status")
public class ObterAprovadoresRmMaterialController {

    @Inject
    private ObterAprovadoresRmMaterialService obterAprovadoresRmMaterialService;


    @POST
    @Path("obter")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ObterAprovadoresRmMaterialResponse> obter(List<ObterAprovadoresRmMaterialRequest> request)
            throws Exception {
        List<ObterAprovadoresRmMaterialResponse> obterAprovadoresRmMaterialResponses =
                this.obterAprovadoresRmMaterialService.obterAprovadoresMaterial(request);
        return obterAprovadoresRmMaterialResponses;
    }
}
