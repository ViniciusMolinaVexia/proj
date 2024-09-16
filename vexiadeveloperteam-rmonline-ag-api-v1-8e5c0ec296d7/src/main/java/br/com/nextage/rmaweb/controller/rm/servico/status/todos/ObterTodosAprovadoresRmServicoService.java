package br.com.nextage.rmaweb.controller.rm.servico.status.todos;

public interface ObterTodosAprovadoresRmServicoService {

	ObterTodosAprovadoresRmServicoResponse obterTodosAprovadoresServico(Integer idRm, Integer idRmServico, Integer idArea, Integer idCentr, String prioridade) throws Exception;

}
