package br.com.nextage.rmaweb.entitybean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author l.pordeus
 */
@Entity
@Table(name = "TB_CONFIG_ACESSO_SERV")
public class ConfigAcessoServ implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "configAcessoServ";
    public static final String ALIAS_CLASSE_UNDERLINE = "configAcessoServ_";

    public static final String ID = "id";
    public static final String LOGIN = "login";
    public static final String TOKEN = "token";
    public static final String NOME_SERVICO = "nomeServico";

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_CONFIG_ACESSO_SERV_ID", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "LOGIN", length = 200)
    private String login;

    @Basic(optional = false)
    @Column(name = "TOKEN", length = 100)
    private String token;

    @Basic(optional = false)
    @Column(name = "NOME_SERVICO", length = 100)
    private String nomeServico;

    public ConfigAcessoServ() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfigAcessoServ)) {
            return false;
        }
        ConfigAcessoServ other = (ConfigAcessoServ) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.ConfigAcessoServ[id=" + id + "]";
    }
}
