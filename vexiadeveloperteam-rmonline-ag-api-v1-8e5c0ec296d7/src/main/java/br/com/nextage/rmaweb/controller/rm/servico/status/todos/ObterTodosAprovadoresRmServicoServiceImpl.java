package br.com.nextage.rmaweb.controller.rm.servico.status.todos;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import br.com.nextage.rmaweb.controller.rm.material.status.aprovadores.ObterAprovadoresRmMaterialService;
import br.com.nextage.rmaweb.controller.rm.servico.status.aprovadores.ObterAprovadoresRmServicoRequest;
import br.com.nextage.rmaweb.controller.rm.servico.status.aprovadores.ObterAprovadoresRmServicoResponse;
import br.com.nextage.rmaweb.controller.rm.servico.status.aprovadores.ObterAprovadoresRmServicoService;
import br.com.nextage.rmaweb.dao.RmDao;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.service.WorkflowEnum;

public class ObterTodosAprovadoresRmServicoServiceImpl implements ObterTodosAprovadoresRmServicoService {

    public ObterTodosAprovadoresRmServicoServiceImpl() {
    }

    @Inject
    private ObterTodosAprovadoresRmServicoDao dao;

    private RmDao rmDao = new RmDao();

    @Inject
    private ObterAprovadoresRmServicoService obterAprovadoresRmServicoService;

    @Override
    public ObterTodosAprovadoresRmServicoResponse obterTodosAprovadoresServico(Integer idRm, Integer idRmServico, Integer idArea,
                    Integer idCentro, String prioridade) throws Exception {
        try {

            ObterTodosAprovadoresRmServico obterTodosAprovadoresRmServico =
                            dao.obterTodosAprovadoresServico(idRm, idRmServico, idArea, idCentro, prioridade);
            if (!obterTodosAprovadoresRmServico.getAprovadores().isEmpty()) {

                return preencherResponse(obterTodosAprovadoresRmServico);
            }
            return new ObterTodosAprovadoresRmServicoResponse();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private ObterTodosAprovadoresRmServicoResponse preencherResponse(ObterTodosAprovadoresRmServico obterTodosAprovadoresRmServico)
      throws Exception {

        // Obter aprovadores e datas
        ObterAprovadoresRmServicoRequest obterAprovadoresRmServicoRequest = new ObterAprovadoresRmServicoRequest();
        obterAprovadoresRmServicoRequest.setIdRm(obterTodosAprovadoresRmServico.getIdRm());
        obterAprovadoresRmServicoRequest.setIdRmServico(obterTodosAprovadoresRmServico.getIdRmServico());
        List<ObterAprovadoresRmServicoResponse> statusAprovadores =
          obterAprovadoresRmServicoService.obterAprovadoresServico(Arrays.asList(obterAprovadoresRmServicoRequest));
        Optional<ObterAprovadoresRmServicoResponse> statusAprovadoresResponseOpt = statusAprovadores.stream().findFirst();
        ObterAprovadoresRmServicoResponse statusServicoResponse;

        if(statusAprovadoresResponseOpt.isPresent()) {
            statusServicoResponse = statusAprovadoresResponseOpt.get();
        } else {
            statusServicoResponse = new ObterAprovadoresRmServicoResponse();
        }

        // fim

        ObterTodosAprovadoresRmServicoResponse response = new ObterTodosAprovadoresRmServicoResponse();
        response.setIdRm(obterTodosAprovadoresRmServico.getIdRm());
        response.setIdRmServico(obterTodosAprovadoresRmServico.getIdRmServico());
        response.setPrioridade(obterTodosAprovadoresRmServico.getPrioridade());

        Optional<AprovadorRmServico> coordenador = obterTodosAprovadoresRmServico.getAprovadores().stream()
                        .filter(aprovador -> WorkflowEnum.WORKFLOW_AREA.name().equals(aprovador.getTipoAprovador())).findFirst();

        Optional<AprovadorRmServico> gerenteArea = obterTodosAprovadoresRmServico.getAprovadores().stream()
                        .filter(aprovador -> WorkflowEnum.WORKFLOW_GERENTE_AREA.name().equals(aprovador.getTipoAprovador())).findFirst();

        Optional<AprovadorRmServico> gerenteCusto = obterTodosAprovadoresRmServico.getAprovadores().stream()
                        .filter(aprovador -> WorkflowEnum.WORKFLOW_CUSTO.name().equals(aprovador.getTipoAprovador())).findFirst();

        Optional<AprovadorRmServico> emergencial = obterTodosAprovadoresRmServico.getAprovadores().stream()
                        .filter((aprovador -> WorkflowEnum.WORKFLOW_EMERGENCIAL.name().equals(aprovador.getTipoAprovador()))).findFirst();

        // Preencher nome coordenador
        if (coordenador.isPresent()) {
            response.setDataAprovCoordenador(statusServicoResponse.getDataAprovCoordenador());
            response.setNomeAprovCoordenador(coordenador.get().getNome());
        }

        //Preencher nome gerente de area
        if (gerenteArea.isPresent()) {
            Rm rm = rmDao.getRmPorId(obterTodosAprovadoresRmServico.getIdRm());
            if ("CGEQ".equals(rm.getArea().getCodigo())
                            && rm.getPrioridade().isAtivo()
                            && "NORMAL".equals(rm.getPrioridade().getCodigo())) {
                response.setDataAprovGerenteArea(null);
                response.setNomeAprovGerenteArea(null);
            } else {
                response.setDataAprovGerenteArea(statusServicoResponse.getDataAprovGerenteArea());
                response.setNomeAprovGerenteArea(gerenteArea.get().getNome());
            }
        }

        //Preencher nome gerente de custo
        if (gerenteCusto.isPresent()) {
            response.setDataAprovGerenteCusto(statusServicoResponse.getDataAprovGerenteCusto());
            response.setNomeAprovGerenteCusto(gerenteCusto.get().getNome());
        }

        //Preencher nome emergencial
        if (emergencial.isPresent()) {
            response.setDataAprovMaquinaParada(statusServicoResponse.getDataAprovMaquinaParada());
            response.setNomeAprovMaquinaParada(emergencial.get().getNome());
        }

        return response;
    }
}
