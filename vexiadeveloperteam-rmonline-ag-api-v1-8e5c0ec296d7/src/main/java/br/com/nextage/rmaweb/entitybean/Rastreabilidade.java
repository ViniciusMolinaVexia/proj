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
@Table(name="TB_RASTREABILIDADE")
@NamedQueries({
	@NamedQuery(name="rastreabilidade.getPorNumeroRm",query="select r from Rastreabilidade r where r.numero = :numero order by r.modificacao desc")
})
public class Rastreabilidade {
	
	public static final String CRIADO="CRIADO";
	public static final String MODIFICADO="MODIFICADO";
	
	@Id
    @Column(name = "RASTREABILIDADE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long rastreabilidadeId;
	
	@Column(name="NUMERO")
	private String numero;
	
	@Column(name="MODIFICACAO")
	private Date modificacao;
	
	@Column(name="CAMPO")
	private String campo;
	
	@Column(name="VALOR")
	private String valor;

	@Column(name="ACAO")
	private String acao;
	
	@Column(name="usuario")
	private String usuario;

	public Long getRastreabilidadeId() {
		return rastreabilidadeId;
	}

	public void setRastreabilidadeId(Long rastreabilidadeId) {
		this.rastreabilidadeId = rastreabilidadeId;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getModificacao() {
		return modificacao;
	}

	public void setModificacao(Date modificacao) {
		this.modificacao = modificacao;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acao == null) ? 0 : acao.hashCode());
		result = prime * result + ((campo == null) ? 0 : campo.hashCode());
		result = prime * result + ((modificacao == null) ? 0 : modificacao.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((rastreabilidadeId == null) ? 0 : rastreabilidadeId.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		Rastreabilidade other = (Rastreabilidade) obj;
		if (acao == null) {
			if (other.acao != null)
				return false;
		} else if (!acao.equals(other.acao))
			return false;
		if (campo == null) {
			if (other.campo != null)
				return false;
		} else if (!campo.equals(other.campo))
			return false;
		if (modificacao == null) {
			if (other.modificacao != null)
				return false;
		} else if (!modificacao.equals(other.modificacao))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (rastreabilidadeId == null) {
			if (other.rastreabilidadeId != null)
				return false;
		} else if (!rastreabilidadeId.equals(other.rastreabilidadeId))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rastreabilidade [rastreabilidadeId=" + rastreabilidadeId + ", numero=" + numero + ", modificacao="
				+ modificacao + ", campo=" + campo + ", valor=" + valor + ", acao=" + acao + ", usuario=" + usuario
				+ "]";
	}
	
}
