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
@Table(name="TB_WORKFLOW_EMERGENCIAL")
@NamedQueries({
	@NamedQuery(name = "WorkflowEmergencial.deleteByWorkflowId", query = "delete from WorkflowEmergencial a where a.workflow.workflowId = :workflowId"),
	@NamedQuery(name="WorkflowEmergencial.getByWorkflowId", query = "select a from WorkflowEmergencial a where a.workflow.workflowId = :workflowId"),
		@NamedQuery(name="WorkflowEmergencial.getCountByWorkflowId", query = "select count(a) from WorkflowEmergencial a where a.workflow.workflowId = :workflowId")
})
public class WorkflowEmergencial {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="WORKFLOW_EMERGENCIAL_ID")
	private int workflowEmergencialId;
	
	@JoinColumn(name = "WORKFLOW_ID", referencedColumnName = "WORKFLOW_ID")
    @ManyToOne(optional = false)
    private Workflow workflow;

    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne(optional = false)
    private Usuario usuario;
    
    @Column(name="STATUS")
    private char status;

	public int getWorkflowEmergencialId() {
		return workflowEmergencialId;
	}

	public void setWorkflowEmergencialId(int workflowEmergencialId) {
		this.workflowEmergencialId = workflowEmergencialId;
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
    
	public char getStatus() {
		return status;
	}
	
	public void setStatus(char status) {
		this.status = status;
	}
}
