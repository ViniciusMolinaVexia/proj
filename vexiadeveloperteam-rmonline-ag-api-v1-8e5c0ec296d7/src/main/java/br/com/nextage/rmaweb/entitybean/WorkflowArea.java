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
@Table(name="TB_WORKFLOW_AREA")
@NamedQueries({
	@NamedQuery(name = "WorkflowArea.deleteByWorkflowId", query = "delete from WorkflowArea a where a.workflow.workflowId = :workflowId"),
	@NamedQuery(name = "WorkflowArea.getByWorkflowId", query = "select a from WorkflowArea a where a.workflow.workflowId = :workflowId"),
		@NamedQuery(name = "WorkflowArea.getCountByWorkflowId", query = "select count(a) from WorkflowArea a where a.workflow.workflowId = :workflowId")
})
public class WorkflowArea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="WORKFLOW_AREA_ID")
	private int workflowAreaId;
	
	@JoinColumn(name = "WORKFLOW_ID", referencedColumnName = "WORKFLOW_ID")
    @ManyToOne(optional = false)
    private Workflow workflow;

    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne(optional = false)
    private Usuario usuario;

	public int getWorkflowAreaId() {
		return workflowAreaId;
	}

	public void setWorkflowAreaId(int workflowAreaId) {
		this.workflowAreaId = workflowAreaId;
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
