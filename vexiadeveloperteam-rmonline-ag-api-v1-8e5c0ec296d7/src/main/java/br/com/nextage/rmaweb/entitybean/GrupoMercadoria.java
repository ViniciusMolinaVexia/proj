package br.com.nextage.rmaweb.entitybean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="TB_GRUPO_MERCADORIA")
@NamedQueries({
	@NamedQuery(name="grupoMercadoria.getByCode",query="select gm from GrupoMercadoria gm where codigo = :codigo")
})
public class GrupoMercadoria {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GRUPO_MERCADORIA_ID")
    private Integer grupoMercadoriaId;
	
	@Column(name="CODIGO")
	private String codigo;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@Column(name="CRIADO")
	private Date criado;
	
	@Column(name="MODIFICADO")
	private Date modificado;

	public Integer getGrupoMercadoriaId() {
		return grupoMercadoriaId;
	}

	public void setGrupoMercadoriaId(Integer grupoMercadoriaId) {
		this.grupoMercadoriaId = grupoMercadoriaId;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getCriado() {
		return criado;
	}

	public void setCriado(Date criado) {
		this.criado = criado;
	}

	public Date getModificado() {
		return modificado;
	}

	public void setModificado(Date modificado) {
		this.modificado = modificado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((criado == null) ? 0 : criado.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((grupoMercadoriaId == null) ? 0 : grupoMercadoriaId.hashCode());
		result = prime * result + ((modificado == null) ? 0 : modificado.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrupoMercadoria other = (GrupoMercadoria) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (criado == null) {
			if (other.criado != null)
				return false;
		} else if (!criado.equals(other.criado))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (grupoMercadoriaId == null) {
			if (other.grupoMercadoriaId != null)
				return false;
		} else if (!grupoMercadoriaId.equals(other.grupoMercadoriaId))
			return false;
		if (modificado == null) {
			if (other.modificado != null)
				return false;
		} else if (!modificado.equals(other.modificado))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GrupoMercadoria [grupoMercadoriaId=" + grupoMercadoriaId + ", codigo=" + codigo + ", nome=" + nome
				+ ", descricao=" + descricao + ", criado=" + criado + ", modificado=" + modificado + "]";
	}
	
}
