package br.com.nextage.rmaweb.exception;

import org.apache.commons.lang.exception.ExceptionUtils;

public class CustomResponse {
    private String label;
    private String message;
    private String error;

    public CustomResponse(String message) {
        this.message = message;
    }

    public CustomResponse(String label, String message) {
        this.label = label;
        this.message = message;
    }

    public CustomResponse(String label, Throwable t) {
        this.label = label;
        if (t != null) {
            this.error = ExceptionUtils.getStackTrace(t);
        }
    }

    public CustomResponse(String label, String message, Throwable t) {
        this.label = label;
        this.message = message;
        if (t != null) {
            this.error = ExceptionUtils.getStackTrace(t);
        }
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
