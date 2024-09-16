package br.com.nextage.rmaweb.controller;

import br.com.nextage.rmaweb.entitybean.Perfil;
import br.com.nextage.rmaweb.entitybean.Role;
import br.com.nextage.rmaweb.service.RoleService;
import br.com.nextage.rmaweb.service.integracao.PerfilService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("PerfilController")
public class PerfilController {
	
	@Context
    HttpServletRequest request;

    @POST
    @Path("salvar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response salvar(Perfil perfil){
    	try {
    		PerfilService perfilService = new PerfilService();
    		perfil = perfilService.salvar(perfil);
    	}catch (Exception e) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
    	return Response.ok(perfil, MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("excluir")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response excluir(Perfil perfil) {
    	try {
    		PerfilService perfilService = new PerfilService();
        	perfilService.excluir(perfil);
    	}catch (Exception e) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
    	return Response.status(Response.Status.OK).build();
    }
    
    @POST
    @Path("listarPerfis")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Perfil> listarPerfis() {
    	PerfilService perfilService = new PerfilService();
    	return perfilService.getAll();
    }
    
    @POST
    @Path("listarRoles")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Role> listarRoles() {
    	RoleService roleService = new RoleService();
    	return roleService.getAll();
    }
    
}
