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
 * @author nextage
 */
@Entity
@Table(name = "TB_CONF_INTEGRACAO")
public class ConfIntegracao implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "confIntegracao";
    public static final String ID = "id";
    public static final String CODIGO = "codigo";
    public static final String URL = "url";
    public static final String LOGIN = "login";
    public static final String SENHA = "senha";
    public static final String URL_WSDL_SERVICE = "urlWsdlService";

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_CONF_INTEGRACAO_ID", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CODIGO", length = 50)
    private String codigo;

    @Column(name = "URL", length = 400)
    private String url;

    @Column(name = "LOGIN", length = 50)
    private String login;

    @Column(name = "SENHA", length = 50)
    private String senha;

    @Column(name = "URL_WSDL_SERVICE", length = 400)
    private String urlWsdlService;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getUrlWsdlService() {
        return urlWsdlService;
    }

    public void setUrlWsdlService(String urlWsdlService) {
        this.urlWsdlService = urlWsdlService;
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
        if (!(object instanceof ConfIntegracao)) {
            return false;
        }
        ConfIntegracao other = (ConfIntegracao) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.ConfIntegracao[id=" + id + "]";
    }
}
