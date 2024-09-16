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
import javax.persistence.Table;

@Entity
@Table(name="TB_WORKFLOW_CUSTO")
@NamedQueries({
	@NamedQuery(name = "WorkflowCusto.deleteByWorkflowId", query = "delete from WorkflowCusto a where a.workflow.workflowId = :workflowId"),
	@NamedQuery(name = "WorkflowCusto.getByWorkflowId", query = "select a from WorkflowCusto a where a.workflow.workflowId = :workflowId"),
		@NamedQuery(name = "WorkflowCusto.getCountByWorkflowId", query = "select count(a) from WorkflowCusto a where a.workflow.workflowId = :workflowId")
})
public class WorkflowCusto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="WORKFLOW_CUSTO_ID")
	private int workflowCustoId;
	
	@JoinColumn(name = "WORKFLOW_ID", referencedColumnName = "WORKFLOW_ID")
    @ManyToOne(optional = false)
    private Workflow workflow;

    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne(optional = false)
    private Usuario usuario;

	public int getWorkflowCustoId() {
		return workflowCustoId;
	}

	public void setWorkflowCustoId(int workflowCustoId) {
		this.workflowCustoId = workflowCustoId;
	}

	public Workflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
}
