package br.com.nextage.rmaweb.controller.rm.material.status.saldo;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Set;

@Path("rm/material/saldo")
public class ObterSaldoRequisicaoMaterialController {

    @Inject
    private ObterSaldoRequisicaoMaterialService obterRequisicaoMaterialService;

    @POST
    @Path("obter")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ObterSaldoRequisicaoMaterialResponse> obter(Set<Integer> request)
            throws Exception {
        List<ObterSaldoRequisicaoMaterialResponse> obterSaldoRequisicaoMaterialResponse =
                this.obterRequisicaoMaterialService.obterSaldo(request);
        return obterSaldoRequisicaoMaterialResponse;
    }
}
