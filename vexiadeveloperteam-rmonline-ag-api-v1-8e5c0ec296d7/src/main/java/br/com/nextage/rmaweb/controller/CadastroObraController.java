package br.com.nextage.rmaweb.controller;

import br.com.nextage.rmaweb.entitybean.Centro;
import br.com.nextage.rmaweb.entitybean.Prioridade;
import br.com.nextage.rmaweb.service.PrioridadeService;
import br.com.nextage.rmaweb.service.integracao.CentroService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("CadastroObraController")
public class CadastroObraController {
	
	private PrioridadeService prioridadeService = new PrioridadeService();
	
	@POST
    @Path("listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Centro> listar() throws Exception{
		CentroService centroService = CentroService.getInstance();
		return centroService.getAll();
    }
	
	
	@POST
	@Path("getPrioridadesCentro")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public List<Prioridade> getPrioridadesCentro(Centro centro) throws Exception{
		return prioridadeService.getPrioridadesCentro(centro.getCentroId());
	}
	
	@POST
	@Path("salvarPrioridades")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public void salvarPrioridade(List<Prioridade> prioridades) throws Exception{
		prioridadeService.salvarPrioridade(prioridades);
	}

}
