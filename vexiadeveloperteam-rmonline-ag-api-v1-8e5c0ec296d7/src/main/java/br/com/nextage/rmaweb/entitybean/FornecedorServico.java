package br.com.nextage.rmaweb.entitybean;

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

@Entity
@Table(name="TB_FORNECEDOR_SERVICO")
@NamedQueries({
	@NamedQuery(name="fornecedorServico.deleteByRmServico", query="delete from FornecedorServico f where f.rmServico.rmServicoId = :rmServicoId")
})
public class FornecedorServico {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_FORNECEDOR_SERVICO_ID", allocationSize = 1)
    @Column(name = "FORNECEDOR_SERVICO_ID")
    private Integer fornecedorServicoId;
	
	@Column(name="FORNECEDOR")
	private String fornecedor;
	
	@Column(name="TELEFONE")
	private String telefone;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="CONTATO")
	private String contato;
	
	@JoinColumn(name = "RM_SERVICO_ID", referencedColumnName = "RM_SERVICO_ID")
    @ManyToOne
	private RmServico rmServico;
	
	public RmServico getRmServico() {
		return this.rmServico;
	}
	
	public void setRmServico(RmServico rmServico) {
		this.rmServico = rmServico;
	}

	public Integer getFornecedorServicoId() {
		return fornecedorServicoId;
	}

	public void setFornecedorServicoId(Integer fornecedorServicoId) {
		this.fornecedorServicoId = fornecedorServicoId;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}
	
}
