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
 * @author Ferri
 */
@Entity
@Table(name = "TB_USUARIO")
@NamedQueries({
	@NamedQuery(name="Usuario.deletePerfil", query="update Usuario set perfil=null where perfil.perfilId = :perfilId"),
	@NamedQuery(name="Usuario.usuarioPorUsuarioId", query="select u from Usuario u where u.usuarioId = :usuarioId"),
	@NamedQuery(name="Usuario.usuariosPorPerfilId", query="select u from Usuario u where u.perfil.perfilId = :perfilId")
})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "usuario";
    public static final String ALIAS_CLASSE_UNDERLINE = "usuario_";
    // Constantes com os nomes dos campos.
    public static final String NOME = "nome";
    public static final String USUARIO_ID = "usuarioId";
    public static final String LOGIN = "login";
    public static final String SENHA = "senha";
    public static final String ATIVO = "ativo";
    public static final String PESSOA = "pessoa";
    public static final String COMPRADOR = "comprador";
    public static final String TOKEN_MOBILE = "tokenMobile";
    public static final String FL_ADMIN = "flAdmin";
    public static final String PERFIL = "perfil";
    public static final String CENTRO = "centro";
    public static final String AREA = "area";

    @Id
    @Column(name = "USUARIO_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_USUARIO_ID", allocationSize = 1)
    private Integer usuarioId;

    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;

    @Basic(optional = false)
    @Column(name = "LOGIN")
    private String login;

    @Basic(optional = false)
    @Column(name = "SENHA")
    private String senha;

    @Basic(optional = false)
    @Column(name = "ATIVO")
    private String ativo;

    @Basic(optional = true)
    @Column(name = "FL_ADMIN")
    private String flAdmin;

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

    @Basic(optional = true)
    @Column(name = "TOKEN_MOBILE")
    private String tokenMobile;

    @Transient
    private List<Role> roles;

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

	public Usuario() {
    }

    public Usuario(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Usuario(Integer usuarioId, String nome, String login, String senha, String ativo) {
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
    }

    public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
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

    public String getTokenMobile() {
        return tokenMobile;
    }

    public void setTokenMobile(String tokenMobile) {
        this.tokenMobile = tokenMobile;
    }

    public String getFlAdmin() {
        return flAdmin;
    }

    public void setFlAdmin(String flAdmin) {
        this.flAdmin = flAdmin;
    }

    public List<UsuarioEapMultiEmpresa> getEapMultiEmpresas() {
        return eapMultiEmpresas;
    }

    public void setEapMultiEmpresas(List<UsuarioEapMultiEmpresa> eapMultiEmpresas) {
        this.eapMultiEmpresas = eapMultiEmpresas;
    }

    public boolean hasProfile(final String roleName) {
        return Optional.ofNullable(roles).orElse(Collections.emptyList()).stream().anyMatch(r -> r.getNome().equals(roleName));
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioId != null ? usuarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        return !((this.usuarioId == null && other.usuarioId != null) || (this.usuarioId != null && !this.usuarioId.equals(other.usuarioId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.Usuario[usuarioId=" + usuarioId + "]";
    }
}
