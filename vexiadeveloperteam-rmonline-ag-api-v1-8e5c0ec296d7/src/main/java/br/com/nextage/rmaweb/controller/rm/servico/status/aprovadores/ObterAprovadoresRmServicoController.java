package br.com.nextage.rmaweb.controller.rm.servico.status.aprovadores;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("rm/servico/status")
public class ObterAprovadoresRmServicoController {

    @Inject
    private ObterAprovadoresRmServicoService obterAprovadoresRmServicoService;


    @POST
    @Path("obter")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ObterAprovadoresRmServicoResponse> obter(List<ObterAprovadoresRmServicoRequest> request)
            throws Exception {
        List<ObterAprovadoresRmServicoResponse> obterAprovadoresRmServicoResponses =
                this.obterAprovadoresRmServicoService.obterAprovadoresServico(request);
        return obterAprovadoresRmServicoResponses;
    }
}
