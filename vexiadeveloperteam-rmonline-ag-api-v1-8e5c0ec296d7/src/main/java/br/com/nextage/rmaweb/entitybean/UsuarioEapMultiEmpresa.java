/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.entitybean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Lucas Heitor
 */
@Entity
@Table(name = "TB_USUARIO_EAP_MULTI_EMPRESA")
public class UsuarioEapMultiEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "usuarioEapMultiEmpresa";
    public static final String ALIAS_CLASSE_UNDERLINE = "usuarioEapMultiEmpresa_";

    // Constantes com os nomes dos campos.
    public static final String ID = "id";
    public static final String EAP_MULTI_EMPRESA = "eapMultiEmpresa";
    public static final String USUARIO = "usuario";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_USUARIO_EAP_MULTI_EMPRESA_ID", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @JoinColumn(name = "EAP_MULTI_EMPRESA_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EapMultiEmpresa eapMultiEmpresa;

    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne(optional = false)
    private Usuario usuario;

    @Transient
    private Boolean selected;

    public UsuarioEapMultiEmpresa() {
    }

    public UsuarioEapMultiEmpresa(Integer id) {
        this.id = id;
    }


    public EapMultiEmpresa getEapMultiEmpresa() {
        return eapMultiEmpresa;
    }

    public void setEapMultiEmpresa(EapMultiEmpresa eapMultiEmpresa) {
        this.eapMultiEmpresa = eapMultiEmpresa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof UsuarioEapMultiEmpresa)) {
            return false;
        }
        UsuarioEapMultiEmpresa other = (UsuarioEapMultiEmpresa) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.UsuarioEapMultiEmpresa[id=" + id + "]";
    }
}
