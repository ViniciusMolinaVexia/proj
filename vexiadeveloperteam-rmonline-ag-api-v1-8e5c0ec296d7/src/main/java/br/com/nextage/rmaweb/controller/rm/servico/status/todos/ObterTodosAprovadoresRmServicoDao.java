package br.com.nextage.rmaweb.controller.rm.servico.status.todos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.nextage.rmaweb.controller.rm.material.status.aprovadores.ObterAprovadoresRmMaterial;
import br.com.nextage.rmaweb.controller.rm.servico.status.aprovadores.ObterAprovadoresRmServico;
import br.com.nextage.rmaweb.dao.RmDao;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.entitybean.Workflow;
import br.com.nextage.rmaweb.service.WorkflowEnum;
import br.com.nextage.rmaweb.service.WorkflowService;
import br.com.nextage.rmaweb.utils.ConnectionFactory;

public class ObterTodosAprovadoresRmServicoDao implements Serializable {

  public static final String PRIORIDADE_MAQ_PARADA = "MAQ_PARADA";

  private RmDao rmDao = new RmDao();

  private WorkflowService workflowService = WorkflowService.getInstance();

  public ObterTodosAprovadoresRmServico obterTodosAprovadoresServico(Integer idRm, Integer idRmServico,
                  Integer idArea, Integer idCentro, String prioridade) throws SQLException {

    if (idRm == null || idRmServico == null) {
      return null;
    }

    ObterTodosAprovadoresRmServico obterTodosAprovadoresRmServico = new ObterTodosAprovadoresRmServico();
    obterTodosAprovadoresRmServico.setIdRm(idRm);
    obterTodosAprovadoresRmServico.setIdRmServico(idRmServico);
    obterTodosAprovadoresRmServico.setPrioridade(prioridade);

    Rm rm = rmDao.getRmPorId(idRm);
    List<WorkflowEnum> customWorkflow = workflowService.getCustomWorkflow(rm);
    Workflow workflow = workflowService.getWorkflowRm(idArea, idCentro);

    // Obter aprovador marcado na RM como favorito
    final ObterAprovadorRmMarcado coordenadorMarcadoComoFavorito = this.obterAprovadorerPorRmENivel(idRm, WorkflowEnum.WORKFLOW_AREA);

    if (customWorkflow.contains(WorkflowEnum.WORKFLOW_AREA)) {
      Pessoa coordenador = workflowService.getAprovadorRm(workflow, WorkflowEnum.WORKFLOW_AREA);
      if (coordenador != null) {
        final String nomeCoordenador = coordenadorMarcadoComoFavorito != null
                        ? coordenadorMarcadoComoFavorito.getNomeUsuario() : coordenador.getNome();

        AprovadorRmServico aprovadorCoordenador =
                        new AprovadorRmServico(nomeCoordenador, WorkflowEnum.WORKFLOW_AREA.name(), "");
        obterTodosAprovadoresRmServico.adicionaAprovador(aprovadorCoordenador);
      }
    }

    if (customWorkflow.contains(WorkflowEnum.WORKFLOW_CUSTO)) {
      Pessoa custo = workflowService.getAprovadorRm(workflow, WorkflowEnum.WORKFLOW_CUSTO);
      if (custo != null) {
        AprovadorRmServico aprovadorCusto = new AprovadorRmServico(custo.getNome(), WorkflowEnum.WORKFLOW_CUSTO.name(), "");
        obterTodosAprovadoresRmServico.adicionaAprovador(aprovadorCusto);
      }
    }

    if (customWorkflow.contains(WorkflowEnum.WORKFLOW_GERENTE_AREA)) {
      Pessoa gerenteArea = workflowService.getAprovadorRm(workflow, WorkflowEnum.WORKFLOW_GERENTE_AREA);
      if (gerenteArea != null) {
        AprovadorRmServico aprovadorGerenteArea =
                        new AprovadorRmServico(gerenteArea.getNome(), WorkflowEnum.WORKFLOW_GERENTE_AREA.name(), "");
        obterTodosAprovadoresRmServico.adicionaAprovador(aprovadorGerenteArea);
      }
    }

    if (customWorkflow.contains(WorkflowEnum.WORKFLOW_EMERGENCIAL)) {
      Pessoa emergencial = workflowService.getAprovadorRm(workflow, WorkflowEnum.WORKFLOW_EMERGENCIAL);
      if (emergencial != null) {
        AprovadorRmServico aprovadorEmergencial =
                        new AprovadorRmServico(emergencial.getNome(), WorkflowEnum.WORKFLOW_EMERGENCIAL.name(), "");
        obterTodosAprovadoresRmServico.adicionaAprovador(aprovadorEmergencial);
      }
    }

    return obterTodosAprovadoresRmServico;
  }

  public ObterAprovadorRmMarcado obterAprovadorerPorRmENivel(Integer idRm, WorkflowEnum nivel) throws SQLException {
    List<ObterAprovadoresRmServico> listaStatus = new ArrayList<>();

    if(idRm == null || nivel == null) {
      return null;
    }

    String sql = "SELECT TP.NOME, "
      + "       TP.EMAIL, "
      + "       AP.RM_ID "
      + "FROM TB_RM_APROVADOR AP "
      + "INNER JOIN TB_PESSOA TP on AP.APROVADOR_PESSOA_ID = TP.PESSOA_ID "
      + "WHERE AP.RM_ID = " + idRm
      + "  and AP.NIVEL_APROVADOR = '" + nivel.getStep() + "'";

    PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);
    ResultSet resultSet = smt.executeQuery();

    ObterAprovadorRmMarcado aprovadorRmMarcado = null;

    while (resultSet.next()) {
      aprovadorRmMarcado = new ObterAprovadorRmMarcado();
      aprovadorRmMarcado.setIdRm(resultSet.getInt("RM_ID"));
      aprovadorRmMarcado.setNomeUsuario(resultSet.getString("NOME"));
      aprovadorRmMarcado.setEmail(resultSet.getString("EMAIL"));
    }

    smt.close();
    resultSet.close();

    return aprovadorRmMarcado;
  }

}
