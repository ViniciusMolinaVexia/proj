package br.com.nextage.rmaweb.filtro;

import br.com.nextage.rmaweb.entitybean.Comprador;
import br.com.nextage.util.filter.FiltroGeral;


public class FiltroUsuario extends FiltroGeral {

    private String nome;
    private String login;
    private String ativo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
}
