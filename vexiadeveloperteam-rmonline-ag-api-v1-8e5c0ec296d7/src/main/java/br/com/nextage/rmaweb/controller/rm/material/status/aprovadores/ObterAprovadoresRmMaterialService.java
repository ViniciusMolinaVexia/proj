package br.com.nextage.rmaweb.controller.rm.material.status.aprovadores;

import java.util.List;

public interface ObterAprovadoresRmMaterialService {

	List<ObterAprovadoresRmMaterialResponse> obterAprovadoresMaterial(List<ObterAprovadoresRmMaterialRequest> request) throws Exception;

}
