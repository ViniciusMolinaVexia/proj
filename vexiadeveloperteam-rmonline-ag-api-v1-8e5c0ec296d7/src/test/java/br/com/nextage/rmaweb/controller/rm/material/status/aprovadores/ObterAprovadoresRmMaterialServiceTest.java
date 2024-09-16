package br.com.nextage.rmaweb.controller.rm.material.status.aprovadores;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.com.nextage.rmaweb.util.DateUtils;

@RunWith(MockitoJUnitRunner.class)
public class ObterAprovadoresRmMaterialServiceTest {

    private static final Integer ID_RM_PADRAO = 1;
    private static final Integer ID_RM_MATERIAL_PADRAO = 123;


    @InjectMocks
    private ObterAprovadoresRmMaterialServiceImpl service;

    @Mock
    private ObterAprovadoresRmMaterialDao dao;

    @BeforeClass
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void comFluxoDeAprovacaoAprovadoDeveRetornarTodosAprovadoresPreenchidos() throws Exception {

        Date dataCoordenador = new Date();
        ObterAprovadoresRmMaterial coordenador = new ObterAprovadoresRmMaterial();
        coordenador.setIdRm(ID_RM_PADRAO);
        coordenador.setIdRmMaterial(ID_RM_MATERIAL_PADRAO);
        coordenador.setNomeStatus("Aprovação Área");
        coordenador.setCodigoStatus("AprA");
        coordenador.setIdStatus(1002);
        coordenador.setNomeUsuario("VEXIA");
        coordenador.setDataStatus(dataCoordenador);

        Date dataGerenteArea = new Date();
        ObterAprovadoresRmMaterial gerenteArea = new ObterAprovadoresRmMaterial();
        gerenteArea.setIdRm(ID_RM_PADRAO);
        gerenteArea.setIdRmMaterial(ID_RM_MATERIAL_PADRAO);
        gerenteArea.setNomeStatus("Aprovação Gerente Área");
        gerenteArea.setCodigoStatus("AprGa");
        gerenteArea.setIdStatus(3);
        gerenteArea.setNomeUsuario("AP 14MG AREA 1");
        gerenteArea.setDataStatus(dataGerenteArea);

        Date dataGerenteCusto = new Date();
        ObterAprovadoresRmMaterial gerenteCusto = new ObterAprovadoresRmMaterial();
        gerenteCusto.setIdRm(ID_RM_PADRAO);
        gerenteCusto.setIdRmMaterial(ID_RM_MATERIAL_PADRAO);
        gerenteCusto.setNomeStatus("Aprovação Custos");
        gerenteCusto.setCodigoStatus("AprGc");
        gerenteCusto.setIdStatus(3);
        gerenteCusto.setNomeUsuario("AP 14MG GERENTE AREA 1");
        gerenteCusto.setDataStatus(dataGerenteCusto);

        Date dataEmergencial = new Date();
        ObterAprovadoresRmMaterial emergencial = new ObterAprovadoresRmMaterial();
        emergencial.setIdRm(ID_RM_PADRAO);
        emergencial.setIdRmMaterial(ID_RM_MATERIAL_PADRAO);
        emergencial.setNomeStatus("Aprovação Emergencial");
        emergencial.setCodigoStatus("AprE");
        emergencial.setIdStatus(1003);
        emergencial.setNomeUsuario("AP 14MG CUSTO 1");
        emergencial.setDataStatus(dataEmergencial);

        Date dataAprovado = new Date();
        ObterAprovadoresRmMaterial aprovado = new ObterAprovadoresRmMaterial();
        aprovado.setIdRm(ID_RM_PADRAO);
        aprovado.setIdRmMaterial(ID_RM_MATERIAL_PADRAO);
        aprovado.setNomeStatus("Aprovado");
        aprovado.setCodigoStatus("Apr");
        aprovado.setIdStatus(1004);
        aprovado.setNomeUsuario("AP 14MG EMERGENCIAL 1");
        aprovado.setDataStatus(dataAprovado);

        List<ObterAprovadoresRmMaterial> listaStatus = Arrays.asList(coordenador, gerenteArea, gerenteCusto, emergencial, aprovado);

        when(dao.obterAprovadoresMaterial(anyInt(), anyInt())).thenReturn(listaStatus);

        ObterAprovadoresRmMaterialRequest request = new ObterAprovadoresRmMaterialRequest();
        request.setIdRm(1);
        request.setIdRmMaterial(123);

        List<ObterAprovadoresRmMaterialResponse> responseList = service.obterAprovadoresMaterial(Arrays.asList(request));

        Optional<ObterAprovadoresRmMaterialResponse> responseOpt = responseList.stream().findFirst();

        assertTrue(responseOpt.isPresent());
        assertEquals(responseOpt.get().getNomeAprovCoordenador(), "AP 14MG AREA 1");
        assertEquals(responseOpt.get().getNomeAprovGerenteArea(), "AP 14MG GERENTE AREA 1");
        assertEquals(responseOpt.get().getNomeAprovGerenteCusto(), "AP 14MG CUSTO 1");
        assertEquals(responseOpt.get().getNomeAprovMaquinaParada(), "AP 14MG EMERGENCIAL 1");

        assertEquals(responseOpt.get().getDataAprovCoordenador(), DateUtils.dateToStringWithFormatter(dataCoordenador, "dd/MM/yyyy"));
        assertEquals(responseOpt.get().getDataAprovGerenteArea(), DateUtils.dateToStringWithFormatter(dataGerenteArea, "dd/MM/yyyy"));
        assertEquals(responseOpt.get().getDataAprovGerenteCusto(), DateUtils.dateToStringWithFormatter(dataGerenteCusto, "dd/MM/yyyy"));
        assertEquals(responseOpt.get().getDataAprovMaquinaParada(), DateUtils.dateToStringWithFormatter(dataEmergencial, "dd/MM/yyyy"));

    }


    @Test
    public void comFluxoDeAprovacaoAprovadoSemEmergencialDeveRetornarTodosAprovadoresMenosOEmergencial() throws Exception {

        Date dataCoordenador = new Date();
        ObterAprovadoresRmMaterial coordenador = new ObterAprovadoresRmMaterial();
        coordenador.setIdRm(ID_RM_PADRAO);
        coordenador.setIdRmMaterial(ID_RM_MATERIAL_PADRAO);
        coordenador.setNomeStatus("Aprovação Área");
        coordenador.setCodigoStatus("AprA");
        coordenador.setIdStatus(1002);
        coordenador.setNomeUsuario("VEXIA");
        coordenador.setDataStatus(dataCoordenador);

        Date dataGerenteArea = new Date();
        ObterAprovadoresRmMaterial gerenteArea = new ObterAprovadoresRmMaterial();
        gerenteArea.setIdRm(ID_RM_PADRAO);
        gerenteArea.setIdRmMaterial(ID_RM_MATERIAL_PADRAO);
        gerenteArea.setNomeStatus("Aprovação Gerente Área");
        gerenteArea.setCodigoStatus("AprGa");
        gerenteArea.setIdStatus(3);
        gerenteArea.setNomeUsuario("AP 14MG AREA 1");
        gerenteArea.setDataStatus(dataGerenteArea);

        Date dataGerenteCusto = new Date();
        ObterAprovadoresRmMaterial gerenteCusto = new ObterAprovadoresRmMaterial();
        gerenteCusto.setIdRm(ID_RM_PADRAO);
        gerenteCusto.setIdRmMaterial(ID_RM_MATERIAL_PADRAO);
        gerenteCusto.setNomeStatus("Aprovação Custos");
        gerenteCusto.setCodigoStatus("AprGc");
        gerenteCusto.setIdStatus(3);
        gerenteCusto.setNomeUsuario("AP 14MG GERENTE AREA 1");
        gerenteCusto.setDataStatus(dataGerenteCusto);


        Date dataAprovado = new Date();
        ObterAprovadoresRmMaterial aprovado = new ObterAprovadoresRmMaterial();
        aprovado.setIdRm(ID_RM_PADRAO);
        aprovado.setIdRmMaterial(ID_RM_MATERIAL_PADRAO);
        aprovado.setNomeStatus("Aprovado");
        aprovado.setCodigoStatus("Apr");
        aprovado.setIdStatus(1004);
        aprovado.setNomeUsuario("AP 14MG CUSTO 1");
        aprovado.setDataStatus(dataAprovado);

        List<ObterAprovadoresRmMaterial> listaStatus = Arrays.asList(coordenador, gerenteArea, gerenteCusto, aprovado);

        when(dao.obterAprovadoresMaterial(anyInt(), anyInt())).thenReturn(listaStatus);

        ObterAprovadoresRmMaterialRequest request = new ObterAprovadoresRmMaterialRequest();
        request.setIdRm(1);
        request.setIdRmMaterial(123);

        List<ObterAprovadoresRmMaterialResponse> responseList = service.obterAprovadoresMaterial(Arrays.asList(request));

        Optional<ObterAprovadoresRmMaterialResponse> responseOpt = responseList.stream().findFirst();

        assertTrue(responseOpt.isPresent());
        assertEquals(responseOpt.get().getNomeAprovCoordenador(), "AP 14MG AREA 1");
        assertEquals(responseOpt.get().getNomeAprovGerenteArea(), "AP 14MG GERENTE AREA 1");
        assertEquals(responseOpt.get().getNomeAprovGerenteCusto(), "AP 14MG CUSTO 1");
        assertEquals(responseOpt.get().getDataAprovCoordenador(), DateUtils.dateToStringWithFormatter(dataCoordenador, "dd/MM/yyyy"));
        assertEquals(responseOpt.get().getDataAprovGerenteArea(), DateUtils.dateToStringWithFormatter(dataGerenteArea, "dd/MM/yyyy"));
        assertEquals(responseOpt.get().getDataAprovGerenteCusto(), DateUtils.dateToStringWithFormatter(dataGerenteCusto, "dd/MM/yyyy"));


    }

    @Test
    public void comFluxoDeAprovacaoSemNenhumStatusLocalizadoDeveRetornarVazio() throws Exception {

        when(dao.obterAprovadoresMaterial(anyInt(), anyInt())).thenReturn(Collections.emptyList());

        ObterAprovadoresRmMaterialRequest request = new ObterAprovadoresRmMaterialRequest();
        request.setIdRm(1);
        request.setIdRmMaterial(123);

        List<ObterAprovadoresRmMaterialResponse> responseList = service.obterAprovadoresMaterial(Arrays.asList(request));

        assertNotNull(responseList);
        assertTrue(responseList.isEmpty());
    }

}
