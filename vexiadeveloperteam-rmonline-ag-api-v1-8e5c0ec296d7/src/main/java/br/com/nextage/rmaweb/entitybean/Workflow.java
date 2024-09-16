package br.com.nextage.rmaweb.entitybean;

import java.util.List;

import javax.inject.Named;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Entity
@Table(name="TB_WORKFLOW")
@NamedQueries({
    @NamedQuery(name = "Workflow.getAll", query = "SELECT w FROM Workflow w"),
    @NamedQuery(name = "Workflow.getById", query = "SELECT w FROM Workflow w where w.workflowId = :workflowId"),
    @NamedQuery(name = "Workflow.getByAreaIdCentroId", query = "SELECT w FROM Workflow w where w.area.areaId = :areaId and w.centro.centroId = :centroId"),
    @NamedQuery(name = "Workflow.getByCentroId", query = "SELECT w FROM Workflow w where w.centro.centroId = :centroId")
})
@XmlAccessorType(XmlAccessType.FIELD)
public class Workflow {
	
	@Id
	@Column(name = "WORKFLOW_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_WORKFLOW_ID", allocationSize = 1)
	private Integer workflowId;
	
	@JoinColumn(name = "CENTRO_ID", referencedColumnName = "CENTRO_ID")
    @ManyToOne
	private Centro centro;
	
	@JoinColumn(name = "AREA_ID", referencedColumnName = "AREA_ID")
    @ManyToOne
	private Area area;
	
	@Transient
	public List<WorkflowArea> workflowAreas;
	
	@Transient
	public List<WorkflowGerenteArea> workflowGerenteAreas;
	
	@Transient
	public List<WorkflowCusto> workflowCustos;
	
	@Transient
	public List<WorkflowEmergencial> workflowEmergenciais;
	
	public List<WorkflowEmergencial> getWorkflowEmergenciais(){
		return this.workflowEmergenciais;
	}
	
	public List<WorkflowCusto> getWorkflowCustos(){
		return this.workflowCustos;
	}
	
	public List<WorkflowArea> getWorkflowAreas(){
		return this.workflowAreas;
	}
	
	public List<WorkflowGerenteArea> getWorkflowGerenteAreas(){
		return this.workflowGerenteAreas;
	}
	
	public void setWorkflowGerenteAreas(List<WorkflowGerenteArea> workflowGerenteAreas) {
		this.workflowGerenteAreas=workflowGerenteAreas;
	}
	
	public void setWorkflowAreas(List<WorkflowArea> workflowAreas) {
		this.workflowAreas=workflowAreas;
	}
	
	public void setWorkflowCustos(List<WorkflowCusto> workflowCustos) {
		this.workflowCustos=workflowCustos;
	}
	
	public void setWorkflowEmergenciais(List<WorkflowEmergencial> workflowEmergenciais) {
		this.workflowEmergenciais=workflowEmergenciais;
	}
	
	public Integer getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Integer workflowId) {
		this.workflowId = workflowId;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "Workflow [workflowId=" + workflowId + ", centro=" + centro + ", area=" + area + ", workflowAreas="
				+ workflowAreas + ", workflowGerenteAreas=" + workflowGerenteAreas + ", workflowCustos="
				+ workflowCustos + ", workflowEmergenciais=" + workflowEmergenciais + "]";
	}
	
}
