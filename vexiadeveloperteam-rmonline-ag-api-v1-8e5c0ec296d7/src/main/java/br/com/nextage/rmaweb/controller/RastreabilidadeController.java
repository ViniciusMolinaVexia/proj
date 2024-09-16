package br.com.nextage.rmaweb.controller;

import br.com.nextage.rmaweb.entitybean.Rastreabilidade;
import br.com.nextage.rmaweb.service.integracao.RastreabilidadeService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("RastreabilidadeController")
public class RastreabilidadeController {
	
	@Context
    HttpServletRequest request;
	
	@POST
    @Path("listarRastreabilidade")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rastreabilidade> listarRastreabilidade(String numero) throws Exception{
		RastreabilidadeService rastreabilidadeService = RastreabilidadeService.getInstance();
		return rastreabilidadeService.getListaRastreabilidade(numero);
    }

}
