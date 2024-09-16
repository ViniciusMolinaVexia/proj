package br.com.nextage.rmaweb.controller.rm.material.status.todos;

public interface ObterTodosAprovadoresRmMaterialService {

	ObterTodosAprovadoresRmMaterialResponse obterTodosAprovadoresMaterial(Integer idRm, Integer idRmMaterial, Integer idArea, Integer idCentr, String prioridade) throws Exception;

}
