/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.entitybean;

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
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Henrique Marques
 */
@Entity
@Table(name = "TB_USUARIO_CENTRO")
@NamedQueries({
	@NamedQuery(name="UsuarioCentro.deletePerfil", query="update UsuarioCentro set perfil=null where perfil.perfilId = :perfilId"),
	@NamedQuery(name="UsuarioCentro.usuarioPorUsuarioId", query="select u from UsuarioCentro u where usuario.usuarioId = :usuarioId"),
	@NamedQuery(name="UsuarioCentro.usuariosPorPerfilId", query="select u from UsuarioCentro u where u.perfil.perfilId = :perfilId")
})
public class UsuarioCentro implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "usuarioCentro";
    public static final String ALIAS_CLASSE_UNDERLINE = "usuarioCentro_";
    // Constantes com os nomes dos campos.
    public static final String USUARIO_CENTRO_ID = "usuarioCentroId";
    public static final String USUARIO = "usuario";
    public static final String PESSOA = "pessoa";
    public static final String COMPRADOR = "comprador";
    public static final String PERFIL = "perfil";
    public static final String CENTRO = "centro";
    public static final String AREA = "area";
    
    @Id
    @Column(name = "USUARIO_CENTRO_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_USUARIO_CENTRO_ID", allocationSize = 1)
    private Integer usuarioCentroId;

    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne
    private Usuario usuario;

	@JoinColumn(name = "COMPRADOR_ID", referencedColumnName = "COMPRADOR_ID")
    @ManyToOne
    private Comprador comprador;

    @JoinColumn(name = "PESSOA_ID", referencedColumnName = "PESSOA_ID")
    @ManyToOne
    private Pessoa pessoa;
    
    @JoinColumn(name = "PERFIL_ID", referencedColumnName = "PERFIL_ID")
    @ManyToOne
    private Perfil perfil;
    
    @JoinColumn(name = "CENTRO_ID", referencedColumnName = "CENTRO_ID")
    @ManyToOne
    private Centro centro;
    
    @JoinColumn(name = "AREA_ID", referencedColumnName = "AREA_ID")
    @ManyToOne
    private Area area;

    @Transient
    private List<Role> roles;
    
    @Transient
    private int centroId;

    @Transient
    private List<UsuarioEapMultiEmpresa> eapMultiEmpresas;
    
    @Transient
    private List<Centro> centros;
    
    @Transient
    private List<Perfil> perfis;
    
    @Transient
    private List<TipoRequisicao> tipoRequisicoes;
    
    public Area getArea() {
    	return this.area;
    }
    
    public void setArea(Area area) {
    	this.area = area;
    }
    
    public Centro getCentro() {
    	return this.centro;
    }
    
    public List<TipoRequisicao> getTipoRequisicoes(){
    	return this.tipoRequisicoes;
    }
    
    public void setTipoRequisicoes(List<TipoRequisicao> tipoRequisicoes) {
    	this.tipoRequisicoes=tipoRequisicoes;
    }
    
    public void setCentro(Centro centro) {
    	this.centro=centro;
    }
    
    public void setCentros(List<Centro> centros) {
    	this.centros=centros;
    }
    
    public List<Centro> getCentros(){
    	return this.centros;
    }

    public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
	
    public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioCentro() {
    }

    /*public UsuarioCentro(Integer usuarioId, String nome, String login, String senha, String ativo) {
        this.usuario = usuarioId;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
    }*/

    public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public UsuarioCentro(Integer usuarioCentroId) {
        this.usuarioCentroId = usuarioCentroId;
    }

	public Integer getUsuarioCentroId() {
        return usuarioCentroId;
    }

    public void setUsuarioCentroId(Integer usuarioCentroId) {
        this.usuarioCentroId = usuarioCentroId;
    }


    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

  
    public List<UsuarioEapMultiEmpresa> getEapMultiEmpresas() {
        return eapMultiEmpresas;
    }

    public void setEapMultiEmpresas(List<UsuarioEapMultiEmpresa> eapMultiEmpresas) {
        this.eapMultiEmpresas = eapMultiEmpresas;
    }

    public int getCentroId() {
		return centroId;
	}

	public void setCentroId(int centroId) {
		this.centroId = centroId;
	}

	public boolean hasProfile(final String roleName) {
        return Optional.ofNullable(roles).orElse(Collections.emptyList()).stream().anyMatch(r -> r.getNome().equals(roleName));
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioCentroId != null ? usuarioCentroId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioCentro)) {
            return false;
        }
        UsuarioCentro other = (UsuarioCentro) object;
        return !((this.usuarioCentroId == null && other.usuarioCentroId != null) || (this.usuarioCentroId != null && !this.usuarioCentroId.equals(other.usuarioCentroId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.usuarioCentro[usuarioCentroId=" + usuarioCentroId + "]";
    }
}
