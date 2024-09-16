package br.com.nextage.rmaweb.vo.integracao;

public class CentroVO {
	
	private String codigo;
	private String nome;
	private String descricao;
	private String idioma;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return "CentroDepositoVO [codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao + "]";
	}
	
}
