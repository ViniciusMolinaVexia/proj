package br.com.nextage.rmaweb.controller;

import br.com.nextage.rmaweb.entitybean.Workflow;
import br.com.nextage.rmaweb.service.WorkflowService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("WorkflowController")
public class WorkflowController {
	
	@Context
    private HttpServletRequest request;

	private WorkflowService workflowService = WorkflowService.getInstance();

	@POST
    @Path("salvar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response salvar(Workflow workflow){
		String saida = null;
    	try {
    		saida = workflowService.salvar(workflow);
    	}catch (Exception e) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
   		return Response.ok(saida, MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("excluir")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response excluir(Workflow workflow) {
    	try {
        	workflowService.delete(workflow);
    	}catch (Exception e) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
    	return Response.status(Response.Status.OK).build();
    }
    
    @POST
    @Path("listarWorkflows")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Workflow> listarWorkflows() {
    	return workflowService.getAll();
    }
    
}
