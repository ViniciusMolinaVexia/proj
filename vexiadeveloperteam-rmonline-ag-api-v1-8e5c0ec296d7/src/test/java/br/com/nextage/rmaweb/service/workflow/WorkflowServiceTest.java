package br.com.nextage.rmaweb.service.workflow;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.annotations.BeforeClass;

import br.com.nextage.rmaweb.dao.WorkflowAreaDAO;
import br.com.nextage.rmaweb.dao.WorkflowCustoDAO;
import br.com.nextage.rmaweb.dao.WorkflowDAO;
import br.com.nextage.rmaweb.dao.WorkflowEmergencialDAO;
import br.com.nextage.rmaweb.dao.WorkflowGerenteAreaDAO;
import br.com.nextage.rmaweb.entitybean.Area;
import br.com.nextage.rmaweb.entitybean.Centro;
import br.com.nextage.rmaweb.entitybean.Prioridade;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.entitybean.Workflow;
import br.com.nextage.rmaweb.service.WorkflowEnum;
import br.com.nextage.rmaweb.service.WorkflowService;

@RunWith(MockitoJUnitRunner.class)
public class WorkflowServiceTest {

  private static final Integer ID_WORKFLOW_PADRAO = 1;
  private static final Integer ID_AREA_PADRAO = 111;
  private static final Integer ID_CENTRO_PADRAO = 222;
  public static final String MAQ_PARADA = "MAQ_PARADA";


  @InjectMocks
  private WorkflowService service;

  @Mock
  private WorkflowDAO workflowDAO;
  @Mock
  private WorkflowAreaDAO workflowAreaDAO;
  @Mock
  private WorkflowGerenteAreaDAO workflowGerenteAreaDAO;
  @Mock
  private WorkflowCustoDAO workflowCustoDAO;
  @Mock
  private WorkflowEmergencialDAO workflowEmergencialDAO;

  @BeforeClass
  public void init(){
    MockitoAnnotations.initMocks(this);
  }


  @Test
  public void comPrimeiroAprovadorCoordenadorArea() throws Exception {
    gerarWorkflowCompleto();

    WorkflowEnum workflowEnum = service.getNextStep(getMockedRm(), null);
    Assert.assertEquals(WorkflowEnum.WORKFLOW_AREA, workflowEnum);
  }

  @Test
  public void comPrimeiroAprovadorGerenteCusto() throws Exception {
    gerarWorkflowPrimeiroAprovadorGerenteCusto();

    WorkflowEnum workflowEnum = service.getNextStep(getMockedRm(), null);
    Assert.assertEquals(WorkflowEnum.WORKFLOW_CUSTO, workflowEnum);
  }

  @Test
  public void comPrimeiroAprovadorGerenteArea() throws Exception {
    gerarWorkflowPrimeiroAprovadorGerenteArea();

    WorkflowEnum workflowEnum = service.getNextStep(getMockedRm(), null);
    Assert.assertEquals(WorkflowEnum.WORKFLOW_GERENTE_AREA, workflowEnum);
  }

  @Test
  public void comUltimoAprovadorEmergencial() throws Exception {
    gerarWorkflowUltimoAprovadorEmergencial();

    WorkflowEnum workflowEnum = service.getNextStep(getMockedRm(), null);
    Assert.assertEquals(WorkflowEnum.WORKFLOW_EMERGENCIAL, workflowEnum);
  }

  @Test
  public void enviadoParaAprovacaoDeveRetornarAprovadorCoordenadorArea() throws Exception {
    gerarWorkflowCompleto();

    WorkflowEnum workflowEnum = service.getNextStep(getMockedRm(), null);
    Assert.assertEquals(WorkflowEnum.WORKFLOW_AREA, workflowEnum);
  }

  @Test
  public void aprovadoPelaCoordenadorAreaDeveRetornarAprovadorGerenteCusto() throws Exception {
    gerarWorkflowCompleto();

    WorkflowEnum workflowEnum = service.getNextStep(getMockedRm(), WorkflowEnum.WORKFLOW_AREA);
    Assert.assertEquals(WorkflowEnum.WORKFLOW_CUSTO, workflowEnum);
  }

  @Test
  public void aprovadoPelaGerenteCustoDeveRetornarAprovadorGerenteArea() throws Exception {
    gerarWorkflowCompleto();

    WorkflowEnum workflowEnum = service.getNextStep(getMockedRm(), WorkflowEnum.WORKFLOW_CUSTO);
    Assert.assertEquals(WorkflowEnum.WORKFLOW_GERENTE_AREA, workflowEnum);
  }

  @Test
  public void aprovadoPelaGerenteAreaDeveFinalizarOFluxo() throws Exception {
    gerarWorkflowCompleto();

    WorkflowEnum workflowEnum = service.getNextStep(getMockedRm(), WorkflowEnum.WORKFLOW_GERENTE_AREA);
    Assert.assertEquals(null, workflowEnum);
  }

  @Test
  public void aprovadoPelaGerenteAreaDeveRetornarAprovadorEmergencial() throws Exception {
    gerarWorkflowCompleto();

    Rm mockedRm = getMockedRm();
    mockedRm.getPrioridade().setCodigo(MAQ_PARADA);
    WorkflowEnum workflowEnum = service.getNextStep(mockedRm, WorkflowEnum.WORKFLOW_GERENTE_AREA);
    Assert.assertEquals(WorkflowEnum.WORKFLOW_EMERGENCIAL, workflowEnum);
  }


  private void gerarWorkflowCompleto() throws Exception {
    Workflow workflow = new Workflow();
    workflow.setWorkflowId(ID_WORKFLOW_PADRAO);
    when(workflowDAO.getWorkflowPorAreaIdCentroId(ID_AREA_PADRAO, ID_CENTRO_PADRAO)).thenReturn(workflow);
    when(workflowAreaDAO.getCountWorkflowAreas(workflow)).thenReturn(1L);
    when(workflowGerenteAreaDAO.getCountWorkflowGerenteAreas(workflow)).thenReturn(1L);
    when(workflowCustoDAO.getCountWorkflowCustos(workflow)).thenReturn(1L);
    when(workflowEmergencialDAO.getCountWorkflowEmergenciais(workflow)).thenReturn(1L);
  }

  private void gerarWorkflowPrimeiroAprovadorGerenteCusto() throws Exception {
    Workflow workflow = new Workflow();
    workflow.setWorkflowId(ID_WORKFLOW_PADRAO);

    when(workflowDAO.getWorkflowPorAreaIdCentroId(ID_AREA_PADRAO, ID_CENTRO_PADRAO)).thenReturn(workflow);
    when(workflowAreaDAO.getCountWorkflowAreas(workflow)).thenReturn(0L);
    when(workflowGerenteAreaDAO.getCountWorkflowGerenteAreas(workflow)).thenReturn(1L);
    when(workflowCustoDAO.getCountWorkflowCustos(workflow)).thenReturn(1L);
    when(workflowEmergencialDAO.getCountWorkflowEmergenciais(workflow)).thenReturn(1L);
  }

  private void gerarWorkflowPrimeiroAprovadorGerenteArea() throws Exception {
    Workflow workflow = new Workflow();
    workflow.setWorkflowId(ID_WORKFLOW_PADRAO);

    when(workflowDAO.getWorkflowPorAreaIdCentroId(ID_AREA_PADRAO, ID_CENTRO_PADRAO)).thenReturn(workflow);
    when(workflowAreaDAO.getCountWorkflowAreas(workflow)).thenReturn(0L);
    when(workflowCustoDAO.getCountWorkflowCustos(workflow)).thenReturn(0L);
    when(workflowGerenteAreaDAO.getCountWorkflowGerenteAreas(workflow)).thenReturn(1L);
    when(workflowEmergencialDAO.getCountWorkflowEmergenciais(workflow)).thenReturn(1L);
  }

  private void gerarWorkflowUltimoAprovadorEmergencial() throws Exception {
    Workflow workflow = new Workflow();
    workflow.setWorkflowId(ID_WORKFLOW_PADRAO);

    when(workflowDAO.getWorkflowPorAreaIdCentroId(ID_AREA_PADRAO, ID_CENTRO_PADRAO)).thenReturn(workflow);
    when(workflowAreaDAO.getCountWorkflowAreas(workflow)).thenReturn(0L);
    when(workflowCustoDAO.getCountWorkflowCustos(workflow)).thenReturn(0L);
    when(workflowGerenteAreaDAO.getCountWorkflowGerenteAreas(workflow)).thenReturn(0L);
    when(workflowEmergencialDAO.getCountWorkflowEmergenciais(workflow)).thenReturn(1L);
  }

  private Rm getMockedRm() {
    Rm rm = new Rm();
    Area area = new Area();
    area.setAreaId(ID_AREA_PADRAO);
    rm.setArea(area);
    Centro centro = new Centro();
    centro.setCentroId(ID_CENTRO_PADRAO);
    rm.setCentro(centro);
    Prioridade prioridade = new Prioridade();
    prioridade.setCodigo("NORMAL");
    rm.setPrioridade(prioridade);
    return rm;
  }

}
