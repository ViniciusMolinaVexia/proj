package br.com.nextage.rmaweb.controller.rm.material.status.aprovadores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import br.com.nextage.rmaweb.util.DateUtils;
import org.apache.commons.lang.StringUtils;

public class ObterAprovadoresRmMaterialServiceImpl implements ObterAprovadoresRmMaterialService {

    private static final String CODIGO_STATUS_APROVACAO_COORDENADOR_AREA = "AprA";
    private static final String CODIGO_STATUS_APROVACAO_GERENTE_AREA = "AprGa";
    private static final String CODIGO_STATUS_APROVACAO_GERENTE_CUSTOS = "AprGc";
    private static final String CODIGO_STATUS_APROVACAO_EMERGENCIAL = "AprE";
    private static final String CODIGO_STATUS_APROVADO = "Apr";

    public ObterAprovadoresRmMaterialServiceImpl() {
    }

    @Inject
    private ObterAprovadoresRmMaterialDao dao;

    @Override
    public List<ObterAprovadoresRmMaterialResponse> obterAprovadoresMaterial(List<ObterAprovadoresRmMaterialRequest> request) throws Exception {
        try {
            List<ObterAprovadoresRmMaterialResponse> listaResponse = new ArrayList<>();

            for (ObterAprovadoresRmMaterialRequest item : request) {
                List<ObterAprovadoresRmMaterial> listaStatusMaterial = dao.obterAprovadoresMaterial(item.getIdRm(), item.getIdRmMaterial());
                if(!listaStatusMaterial.isEmpty()) {
                    ObterAprovadoresRmMaterialResponse response = preencherResponse(item.getIdRm(), item.getIdRmMaterial(), listaStatusMaterial);
                    listaResponse.add(response);
                }
            }
            return listaResponse;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private ObterAprovadoresRmMaterialResponse preencherResponse(Integer idRm, Integer idRmMaterial, List<ObterAprovadoresRmMaterial> listaStatus)
      throws SQLException {

        ObterAprovadoresRmMaterialResponse response = new ObterAprovadoresRmMaterialResponse();
        response.setIdRm(idRm);
        response.setIdRmMaterial(idRmMaterial);


        Optional<ObterAprovadoresRmMaterial> coordenador = listaStatus.stream()
                .filter(status -> status.getCodigoStatus().equals(CODIGO_STATUS_APROVACAO_COORDENADOR_AREA)).findFirst();

        Optional<ObterAprovadoresRmMaterial> gerenteArea = listaStatus.stream()
                .filter(status -> status.getCodigoStatus().equals(CODIGO_STATUS_APROVACAO_GERENTE_AREA)).findFirst();

        Optional<ObterAprovadoresRmMaterial> gerenteCusto = listaStatus.stream()
                .filter(status -> status.getCodigoStatus().equals(CODIGO_STATUS_APROVACAO_GERENTE_CUSTOS)).findFirst();

        Optional<ObterAprovadoresRmMaterial> emergencial = listaStatus.stream()
                .filter(status -> status.getCodigoStatus().equals(CODIGO_STATUS_APROVACAO_EMERGENCIAL)).findFirst();

        Optional<ObterAprovadoresRmMaterial> aprovado = listaStatus .stream()
                .filter(status -> status.getCodigoStatus().equals(CODIGO_STATUS_APROVADO)).findFirst();


        // Preencher nome coordenador
        if (coordenador.isPresent()) {
            String nomeAprovador = preencherAprovador(coordenador, gerenteArea);

            if(StringUtils.isEmpty(nomeAprovador)) {
                nomeAprovador = preencherAprovador(coordenador, gerenteCusto);
            }

            if(StringUtils.isEmpty(nomeAprovador)) {
                nomeAprovador = preencherAprovador(coordenador, aprovado);
            }

            if(!StringUtils.isEmpty(nomeAprovador)) {
                response.setDataAprovCoordenador(DateUtils.dateToStringWithFormatter(coordenador.get().getDataStatus(), "dd/MM/yyyy"));
                response.setNomeAprovCoordenador(nomeAprovador);
            }
        }

        //Preencher nome gerente de area
        if (gerenteArea.isPresent()) {
            String nomeAprovador = preencherAprovador(gerenteArea, gerenteCusto);

            if(StringUtils.isEmpty(nomeAprovador)) {
                nomeAprovador = preencherAprovador(gerenteArea, emergencial);
            }

            if(StringUtils.isEmpty(nomeAprovador)) {
                nomeAprovador = preencherAprovador(gerenteArea, aprovado);
            }

            if(!StringUtils.isEmpty(nomeAprovador)) {
                response.setDataAprovGerenteArea(DateUtils.dateToStringWithFormatter(gerenteArea.get().getDataStatus(), "dd/MM/yyyy"));
                response.setNomeAprovGerenteArea(nomeAprovador);
            }
        }

        //Preencher nome gerente de custo
        if (gerenteCusto.isPresent()) {
            String nomeAprovador = preencherAprovador(gerenteCusto, emergencial);

            if(StringUtils.isEmpty(nomeAprovador)) {
                nomeAprovador = preencherAprovador(gerenteCusto, aprovado);
            }

            if(!StringUtils.isEmpty(nomeAprovador)) {
                response.setDataAprovGerenteCusto(DateUtils.dateToStringWithFormatter(gerenteCusto.get().getDataStatus(), "dd/MM/yyyy"));
                response.setNomeAprovGerenteCusto(nomeAprovador);
            }
        }

        //Preencher nome emergencial
        if (emergencial.isPresent()) {
            String nomeAprovador = preencherAprovador(emergencial, aprovado);

            if(!StringUtils.isEmpty(nomeAprovador)) {
                response.setDataAprovMaquinaParada(DateUtils.dateToStringWithFormatter(emergencial.get().getDataStatus(), "dd/MM/yyyy"));
                response.setNomeAprovMaquinaParada(nomeAprovador);
            }
        }

        return response;
    }

    private String preencherAprovador(Optional<ObterAprovadoresRmMaterial> aprovador, Optional<ObterAprovadoresRmMaterial> proximo) {

        if(aprovador.isPresent() && proximo.isPresent() && aprovador.get().getDataStatus() != null) {
            return proximo.get().getNomeUsuario();
        }

        return StringUtils.EMPTY;
    }
}
