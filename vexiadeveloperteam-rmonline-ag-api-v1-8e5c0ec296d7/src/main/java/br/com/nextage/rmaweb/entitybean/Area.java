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
@Table(name="TB_AREA")
@NamedQueries({
	@NamedQuery(name="area.getByCode",query="select a from Area a where a.codigo = :codigo"),
	@NamedQuery(name="area.getAll",query="select a from Area a order by a.nome")
})
public class Area {
	
	public static final String ALIAS_CLASSE = "area";
	public static final String AREA_ID = "areaId";
	public static final String CODIGO = "codigo";
	public static final String NOME = "nome";
	public static final String DESCRICAO = "descricao";
	public static final String IDIOMA = "idioma";
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AREA_ID")
    private Integer areaId;
	
	@Column(name="CODIGO")
	private String codigo;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="IDIOMA")
	private String idioma;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@Column(name="CRIADO")
	private Date criado;
	
	@Column(name="MODIFICADO")
	private Date modificado;
	
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
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
	
	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
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
		result = prime * result + ((areaId == null) ? 0 : areaId.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((criado == null) ? 0 : criado.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((idioma == null) ? 0 : idioma.hashCode());
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
		Area other = (Area) obj;
		if (areaId == null) {
			if (other.areaId != null)
				return false;
		} else if (!areaId.equals(other.areaId))
			return false;
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
		if (idioma == null) {
			if (other.idioma != null)
				return false;
		} else if (!idioma.equals(other.idioma))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Area [areaId=" + areaId + ", codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao
				+ ", criado=" + criado + ", modificado=" + modificado + ", idioma=" + idioma + "]";
	}
	
}
