package br.com.nextage.rmaweb.controller.notificacao.email.enviar;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


@Path("email")
public class EmailController {

    @Context
    HttpServletRequest request;
    @Inject
    private EmailService emailService;

    @POST
    @Path("enviar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void listar() {
        emailService.enviarEmail("Test Get DataBase Configuration", "kapiturasp@gmail.com,kapiturasp@yahoo.com.br", "Message include on body email message.");
    }
}
