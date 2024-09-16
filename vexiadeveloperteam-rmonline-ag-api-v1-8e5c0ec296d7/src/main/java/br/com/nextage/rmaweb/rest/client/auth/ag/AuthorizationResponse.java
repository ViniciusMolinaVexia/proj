package br.com.nextage.rmaweb.rest.client.auth.ag;

import java.io.Serializable;

public class AuthorizationResponse implements Serializable {

    private boolean Autenticado;
    private String Token;
    private String MotivoCritica;
    private String Alerta;
    private String Nome;
    private String Login;
    private String Email;

    public boolean isAutenticado() {
        return Autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        Autenticado = autenticado;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getMotivoCritica() {
        return MotivoCritica;
    }

    public void setMotivoCritica(String motivoCritica) {
        MotivoCritica = motivoCritica;
    }

    public String getAlerta() {
        return Alerta;
    }

    public void setAlerta(String alerta) {
        Alerta = alerta;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
