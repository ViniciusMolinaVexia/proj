package br.com.nextage.rmaweb.controller.pessoa.obterPorPerfilECentro;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("pessoas")
public class ObterPessoaPorPerfilECentroController {

    @Inject
    private ObterPessoaPorPerfilECentroService obterAprovadoresRmMaterialService;

    @Inject
    private ObterPessoaPorPerfilECentroModelToResponseMapper mapperModelToResponse;

    @GET
    @Path("obter/perfil/{perfilId}/centro/{centroId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ObterPessoaPorPerfilECentroResponse> obter(@PathParam("perfilId") Integer perfilId, @PathParam("centroId") Integer centroId)
            throws Exception {
        List<ObterPessoaPorPerfilECentro> pessoas =
                this.obterAprovadoresRmMaterialService.obterPorPerfilECentro(perfilId, centroId);

        return Optional.ofNullable(pessoas).orElse(Collections.emptyList()).stream().map(mapperModelToResponse.mapper::apply).collect(
          Collectors.toList());
    }
}
