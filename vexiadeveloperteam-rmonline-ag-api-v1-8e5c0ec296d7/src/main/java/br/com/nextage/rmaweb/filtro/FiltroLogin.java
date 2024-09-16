package br.com.nextage.rmaweb.filtro;

/**
 *
 * @author Marlos M. Novo
 * @date 19/09/2014
 */
public class FiltroLogin {

    private String login;
    private String senha;
    private String siglaSistema;
    private boolean ldap;
    private String local;

    public FiltroLogin() {
    }

    public FiltroLogin(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSiglaSistema() {
        return siglaSistema;
    }

    public void setSiglaSistema(String siglaSistema) {
        this.siglaSistema = siglaSistema;
    }

    public boolean isLdap() {
        return ldap;
    }

    public void setLdap(boolean ldap) {
        this.ldap = ldap;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

}
