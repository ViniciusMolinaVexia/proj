package br.com.nextage.rmaweb.entitybean;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="TB_CENTRO")
@NamedQueries({
	@NamedQuery(name="Centro.getByCode",query="select c from Centro c where c.codigo = :codigo"),
	@NamedQuery(name="Centro.getById",query="select c from Centro c where c.centroId = :centroId"),
	@NamedQuery(name="Centro.getAll",query="select c from Centro c order by c.nome ")
})
public class Centro {
	
	public static final String ALIAS_CLASSE = "centro";
	public static final String CENTRO_ID = "centroId";
	public static final String CODIGO = "codigo";
	public static final String NOME = "nome";
	public static final String DESCRICAO = "descricao";
	public static final String IDIOMA = "idioma";

	public Centro() {
	}

	public Centro(Integer centroId) {
		this.centroId = centroId;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CENTRO_ID")
    private Integer centroId;
	
	@Column(name="CODIGO")
	private String codigo;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@Column(name="IDIOMA")
	private String idioma;
	
	@Column(name="CRIADO")
	private Date criado;
	
	@Column(name="MODIFICADO")
	private Date modificado;
	
	@Transient
	private List<Area> areas;
	
	public void setAreas(List<Area> areas) {
		this.areas=areas;
	}
	
	public List<Area> getAreas(){
		return this.areas;
	}
	
	public Integer getCentroId() {
		return centroId;
	}

	public void setCentroId(Integer centroId) {
		this.centroId = centroId;
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
	
	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
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
		result = prime * result + ((centroId == null) ? 0 : centroId.hashCode());
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
		Centro other = (Centro) obj;
		if (centroId == null) {
			if (other.centroId != null)
				return false;
		} else if (!centroId.equals(other.centroId))
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
		if (idioma == null) {
			if (other.idioma != null)
				return false;
		} else if (!idioma.equals(other.idioma))
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
		return "Centro [centroId=" + centroId + ", codigo=" + codigo + ", nome=" + nome
				+ ", descricao=" + descricao + ", criado=" + criado + ", modificado=" + modificado + ", idioma=" + idioma + "]";
	}
	
}
