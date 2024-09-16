package br.com.nextage.rmaweb.rest.client.auth.ag;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AuthorizationRequest implements Serializable {
    private String Login;
    private String Senha;
    private String AplicacaoId;

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getAplicacaoId() {
        return AplicacaoId;
    }

    public void setAplicacaoId(String aplicacaoId) {
        AplicacaoId = aplicacaoId;
    }
}
