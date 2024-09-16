package br.com.nextage.rmaweb.exception;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ApiException extends WebApplicationException {

    public ApiException(final String label, final Throwable t) {
        super(Response.status(Response.Status.BAD_REQUEST)
                .entity(new CustomResponse(label, t)).type(MediaType.APPLICATION_JSON).build());
    }

    public ApiException(final String label, final String message) {
        super(Response.status(Response.Status.BAD_REQUEST)
                .entity(new CustomResponse(label, message)).type(MediaType.APPLICATION_JSON).build());
    }

    public ApiException(String label, String message, Throwable t) {
        super(Response.status(Response.Status.BAD_REQUEST)
                .entity(new CustomResponse(label, message, t)).type(MediaType.APPLICATION_JSON).build());
    }
}
