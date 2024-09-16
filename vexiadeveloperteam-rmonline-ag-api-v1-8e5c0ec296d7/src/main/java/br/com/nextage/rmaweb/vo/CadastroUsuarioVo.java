package br.com.nextage.rmaweb.vo;

import java.util.ArrayList;
import java.util.List;

import br.com.nextage.rmaweb.entitybean.Area;
import br.com.nextage.rmaweb.entitybean.Centro;
import br.com.nextage.rmaweb.entitybean.Comprador;
import br.com.nextage.rmaweb.entitybean.Perfil;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.TipoRequisicao;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.entitybean.UsuarioCentro;
import br.com.nextage.rmaweb.entitybean.UsuarioEapMultiEmpresa;

/**
 *
 * @author Henrique Marques
 */
public class CadastroUsuarioVo {

    public CadastroUsuarioVo() {
    	centro = new ArrayList<>();
    	usuario = new Usuario();
    }

    private List<Centro> centro;
    private List<Centro> allCentro;
    private List<UsuarioCentro> usuarioCentro;
    private Pessoa pessoa;
    private Area area;
    private Perfil perfil;
    private Comprador comprador;
    private Usuario usuario;
    private List<UsuarioEapMultiEmpresa> eapMultiEmpresas;
    private List<TipoRequisicao> tipoRequisicoes;
    
	public List<Centro> getCentro() {
		return centro;
	}
	public void setCentro(List<Centro> centro) {
		this.centro = centro;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public Comprador getComprador() {
		return comprador;
	}
	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<UsuarioEapMultiEmpresa> getEapMultiEmpresas() {
		return eapMultiEmpresas;
	}
	public void setEapMultiEmpresas(List<UsuarioEapMultiEmpresa> eapMultiEmpresas) {
		this.eapMultiEmpresas = eapMultiEmpresas;
	}
	public List<TipoRequisicao> getTipoRequisicoes() {
		return tipoRequisicoes;
	}
	public void setTipoRequisicoes(List<TipoRequisicao> tipoRequisicoes) {
		this.tipoRequisicoes = tipoRequisicoes;
	}
	public List<UsuarioCentro> getUsuarioCentro() {
		return usuarioCentro;
	}
	public void setUsuarioCentro(List<UsuarioCentro> usuarioCentro) {
		this.usuarioCentro = usuarioCentro;
	}
	public List<Centro> getAllCentro() {
		return allCentro;
	}
	public void setAllCentro(List<Centro> allCentro) {
		this.allCentro = allCentro;
	}
	


}
