package br.com.nextage.rmaweb.exception;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class InfraestrutureException extends WebApplicationException {

    public InfraestrutureException(String label, String message, Throwable t) {
        super(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new CustomResponse(label, message, t)).type(MediaType.APPLICATION_JSON).build());
    }
}
