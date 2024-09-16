package br.com.nextage.rmaweb.controller;

import br.com.nextage.rmaweb.filtro.FiltroLogin;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.util.Info;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

/**
 *
 * @author Marlos M. Novo
 * @date 11/08/2014
 */
@Path("LoginController")
public class LoginController {

    @Context
    HttpServletRequest request;

    @POST
    @Path("login")
    @Consumes("application/json")
    public Info login(FiltroLogin filtro) {
        return new LoginService(request).login(filtro);
    }
}
