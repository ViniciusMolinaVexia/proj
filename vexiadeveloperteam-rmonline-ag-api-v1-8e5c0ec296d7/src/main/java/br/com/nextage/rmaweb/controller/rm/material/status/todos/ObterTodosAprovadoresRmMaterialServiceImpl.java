package br.com.nextage.rmaweb.controller.rm.material.status.todos;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import br.com.nextage.rmaweb.controller.rm.material.status.aprovadores.ObterAprovadoresRmMaterialRequest;
import br.com.nextage.rmaweb.controller.rm.material.status.aprovadores.ObterAprovadoresRmMaterialResponse;
import br.com.nextage.rmaweb.controller.rm.material.status.aprovadores.ObterAprovadoresRmMaterialService;
import br.com.nextage.rmaweb.dao.RmDao;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.service.WorkflowEnum;

public class ObterTodosAprovadoresRmMaterialServiceImpl implements ObterTodosAprovadoresRmMaterialService {

    public ObterTodosAprovadoresRmMaterialServiceImpl() {
    }

    @Inject
    private ObterTodosAprovadoresRmMaterialDao dao;

    private RmDao rmDao = new RmDao();

    @Inject
    private ObterAprovadoresRmMaterialService obterAprovadoresRmMaterialService;

    @Override
    public ObterTodosAprovadoresRmMaterialResponse obterTodosAprovadoresMaterial(Integer idRm, Integer idRmMaterial, Integer idArea,
                    Integer idCentro, String prioridade) throws Exception {
        try {

            ObterTodosAprovadoresRmMaterial obterTodosAprovadoresRmMaterial =
                            dao.obterTodosAprovadoresMaterial(idRm, idRmMaterial, idArea, idCentro, prioridade);
            if (!obterTodosAprovadoresRmMaterial.getAprovadores().isEmpty()) {

                return preencherResponse(obterTodosAprovadoresRmMaterial);
            }
            return new ObterTodosAprovadoresRmMaterialResponse();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private ObterTodosAprovadoresRmMaterialResponse preencherResponse(ObterTodosAprovadoresRmMaterial obterTodosAprovadoresRmMaterial)
      throws Exception {

        // Obter aprovadores e datas
        ObterAprovadoresRmMaterialRequest obterAprovadoresRmMaterialRequest = new ObterAprovadoresRmMaterialRequest();
        obterAprovadoresRmMaterialRequest.setIdRm(obterTodosAprovadoresRmMaterial.getIdRm());
        obterAprovadoresRmMaterialRequest.setIdRmMaterial(obterTodosAprovadoresRmMaterial.getIdRmMaterial());
        List<ObterAprovadoresRmMaterialResponse> statusAprovadores =
          obterAprovadoresRmMaterialService.obterAprovadoresMaterial(Arrays.asList(obterAprovadoresRmMaterialRequest));
        Optional<ObterAprovadoresRmMaterialResponse> statusAprovadoresResponseOpt = statusAprovadores.stream().findFirst();
        ObterAprovadoresRmMaterialResponse statusMaterialResponse;

        if(statusAprovadoresResponseOpt.isPresent()) {
            statusMaterialResponse = statusAprovadoresResponseOpt.get();
        } else {
            statusMaterialResponse = new ObterAprovadoresRmMaterialResponse();
        }

        // fim

        ObterTodosAprovadoresRmMaterialResponse response = new ObterTodosAprovadoresRmMaterialResponse();
        response.setIdRm(obterTodosAprovadoresRmMaterial.getIdRm());
        response.setIdRmMaterial(obterTodosAprovadoresRmMaterial.getIdRmMaterial());
        response.setPrioridade(obterTodosAprovadoresRmMaterial.getPrioridade());

        Optional<AprovadorRmMaterial> coordenador = obterTodosAprovadoresRmMaterial.getAprovadores().stream()
                        .filter(aprovador -> WorkflowEnum.WORKFLOW_AREA.name().equals(aprovador.getTipoAprovador())).findFirst();

        Optional<AprovadorRmMaterial> gerenteArea = obterTodosAprovadoresRmMaterial.getAprovadores().stream()
                        .filter(aprovador -> WorkflowEnum.WORKFLOW_GERENTE_AREA.name().equals(aprovador.getTipoAprovador())).findFirst();

        Optional<AprovadorRmMaterial> gerenteCusto = obterTodosAprovadoresRmMaterial.getAprovadores().stream()
                        .filter(aprovador -> WorkflowEnum.WORKFLOW_CUSTO.name().equals(aprovador.getTipoAprovador())).findFirst();

        Optional<AprovadorRmMaterial> emergencial = obterTodosAprovadoresRmMaterial.getAprovadores().stream()
                        .filter((aprovador -> WorkflowEnum.WORKFLOW_EMERGENCIAL.name().equals(aprovador.getTipoAprovador()))).findFirst();

        // Preencher nome coordenador
        if (coordenador.isPresent()) {
            response.setDataAprovCoordenador(statusMaterialResponse.getDataAprovCoordenador());
            response.setNomeAprovCoordenador(coordenador.get().getNome());
        }

        //Preencher nome gerente de area
        if (gerenteArea.isPresent()) {
            Rm rm = rmDao.getRmPorId(obterTodosAprovadoresRmMaterial.getIdRm());
            if ("CGEQ".equals(rm.getArea().getCodigo())
                            && rm.getPrioridade().isAtivo()
                            && "NORMAL".equals(rm.getPrioridade().getCodigo())) {
                response.setDataAprovGerenteArea(null);
                response.setNomeAprovGerenteArea(null);
            } else {
                response.setDataAprovGerenteArea(statusMaterialResponse.getDataAprovGerenteArea());
                response.setNomeAprovGerenteArea(gerenteArea.get().getNome());
            }
        }

        //Preencher nome gerente de custo
        if (gerenteCusto.isPresent()) {
            response.setDataAprovGerenteCusto(statusMaterialResponse.getDataAprovGerenteCusto());
            response.setNomeAprovGerenteCusto(gerenteCusto.get().getNome());
        }

        //Preencher nome emergencial
        if (emergencial.isPresent()) {
            response.setDataAprovMaquinaParada(statusMaterialResponse.getDataAprovMaquinaParada());
            response.setNomeAprovMaquinaParada(emergencial.get().getNome());
        }

        return response;
    }
}
