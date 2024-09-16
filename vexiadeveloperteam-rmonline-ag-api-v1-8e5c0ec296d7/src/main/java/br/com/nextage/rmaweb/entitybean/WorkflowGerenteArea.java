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
@Table(name="TB_WORKFLOW_GERENTE_AREA")
@NamedQueries({
	@NamedQuery(name = "WorkflowGerenteArea.deleteByWorkflowId", query = "delete from WorkflowGerenteArea a where a.workflow.workflowId = :workflowId"),
	@NamedQuery(name = "WorkflowGerenteArea.getByWorkflowId", query = "select a from WorkflowGerenteArea a where a.workflow.workflowId = :workflowId"),
	@NamedQuery(name = "WorkflowGerenteArea.getByWorkflowIdUsuario", query = "select a from WorkflowGerenteArea a where a.usuario.usuarioId = :usuarioId"),
	@NamedQuery(name = "WorkflowGerenteArea.getCountByWorkflowId", query = "select count(a) from WorkflowGerenteArea a where a.workflow.workflowId = :workflowId")
})
public class WorkflowGerenteArea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="WORKFLOW_GERENTE_AREA_ID")
	private int workflowGerenteAreaId;
	
	@JoinColumn(name = "WORKFLOW_ID", referencedColumnName = "WORKFLOW_ID")
    @ManyToOne(optional = false)
    private Workflow workflow;

    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne(optional = false)
    private Usuario usuario;

	public int getWorkflowGerenteAreaId() {
		return workflowGerenteAreaId;
	}

	public void setWorkflowGerenteAreaId(int workflowGerenteAreaId) {
		this.workflowGerenteAreaId = workflowGerenteAreaId;
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
