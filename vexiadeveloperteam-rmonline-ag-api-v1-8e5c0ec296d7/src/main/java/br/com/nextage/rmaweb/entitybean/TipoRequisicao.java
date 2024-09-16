package br.com.nextage.rmaweb.entitybean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TB_TIPO_REQUISICAO")
@NamedQueries({
	@NamedQuery(name="tipoRequisicao.getAll",query="select tr from TipoRequisicao tr order by tr.descricao")
})
public class TipoRequisicao {
	
	public static final String DESCRICAO = "descricao";
	public static final String CODIGO = "codigo";
	
	@Id
    @Column(name = "TIPO_REQUISICAO_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_TIPO_REQUISICAO_ID", allocationSize = 1)
	private Integer tipoRequisicaoId;
	
	@Column(name="CODIGO")
	private String codigo;
	
	@Column(name="DESCRICAO")
	private String descricao;

	public Integer getTipoRequisicaoId() {
		return tipoRequisicaoId;
	}

	public void setTipoRequisicaoId(Integer tipoRequisicaoId) {
		this.tipoRequisicaoId = tipoRequisicaoId;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
