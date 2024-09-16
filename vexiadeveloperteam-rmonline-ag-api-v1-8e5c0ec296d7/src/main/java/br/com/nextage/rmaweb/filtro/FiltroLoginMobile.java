package br.com.nextage.rmaweb.filtro;

import java.security.Principal;

/**
 * @author Marlos M. Novo
 * @date 19/09/2014
 */
public class FiltroLoginMobile implements Principal {

	private String login;
	private String senha;
	private String nome;
	private String url;
	private String token;
	private Integer usuarioId;
	private Integer pessoaId;
	private String pessoaNome;
	private String pessoaReferencia;
	private String pessoaSetor;
	private String pessoaSetorCodigo;
	private Integer pessoaSetorId;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String getName() {
		return this.login;
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

	public Integer getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Integer pessoaId) {
		this.pessoaId = pessoaId;
	}

	public String getPessoaNome() {
		return pessoaNome;
	}

	public void setPessoaNome(String pessoaNome) {
		this.pessoaNome = pessoaNome;
	}

	public String getPessoaReferencia() {
		return pessoaReferencia;
	}

	public void setPessoaReferencia(String pessoaReferencia) {
		this.pessoaReferencia = pessoaReferencia;
	}

	public String getPessoaSetor() {
		return pessoaSetor;
	}

	public void setPessoaSetor(String pessoaSetor) {
		this.pessoaSetor = pessoaSetor;
	}

	public String getPessoaSetorCodigo() {
		return pessoaSetorCodigo;
	}

	public void setPessoaSetorCodigo(String pessoaSetorCodigo) {
		this.pessoaSetorCodigo = pessoaSetorCodigo;
	}

	public Integer getPessoaSetorId() {
		return pessoaSetorId;
	}

	public void setPessoaSetorId(Integer pessoaSetorId) {
		this.pessoaSetorId = pessoaSetorId;
	}
}

