/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.entitybean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Leco
 */
@Entity
@Table(name = "TB_USUARIO_TELA")
@NamedQueries({
    @NamedQuery(name = "UsuarioTela.findAll", query = "SELECT u FROM UsuarioTela u"),
    @NamedQuery(name = "UsuarioTela.deleteAll", query = "DELETE FROM UsuarioTela u WHERE u.usuario.usuarioId = :usuarioId")})
public class UsuarioTela implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "usuarioTela";
    public static final String ALIAS_CLASSE_UNDERLINE = "usuarioTela_";

    // Constantes com os nomes dos campos.
    public static final String USUARIO_TELA_ID = "usuarioTelaId";
    public static final String USUARIO = "usuario";
    public static final String TELA = "tela";

    @Id
    @Basic(optional = false)
    @Column(name = "USUARIO_TELA_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_USUARIO_TELA_ID", allocationSize = 1)
    private Integer usuarioTelaId;

    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne(optional = false)
    private Usuario usuario;

    @JoinColumn(name = "TELA_ID", referencedColumnName = "TELA_ID")
    @ManyToOne(optional = false)
    private Tela tela;

    public UsuarioTela() {
    }

    public UsuarioTela(Integer usuarioTelaId) {
        this.usuarioTelaId = usuarioTelaId;
    }

    public UsuarioTela(Integer usuarioTelaId, Usuario usuario, Tela tela) {
        this.usuarioTelaId = usuarioTelaId;
        this.usuario = usuario;
        this.tela = tela;
    }

    public Integer getUsuarioTelaId() {
        return usuarioTelaId;
    }

    public void setUsuarioTelaId(Integer usuarioTelaId) {
        this.usuarioTelaId = usuarioTelaId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tela getTela() {
        return tela;
    }

    public void setTela(Tela tela) {
        this.tela = tela;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioTelaId != null ? usuarioTelaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioTela)) {
            return false;
        }
        UsuarioTela other = (UsuarioTela) object;
        return !((this.usuarioTelaId == null && other.usuarioTelaId != null) || (this.usuarioTelaId != null && !this.usuarioTelaId.equals(other.usuarioTelaId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.UsuarioTela[usuarioTelaId=" + usuarioTelaId + "]";
    }

}
