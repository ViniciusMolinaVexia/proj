package br.com.nextage.rmaweb.controller.rm.servico.status.aprovadores;

import java.util.List;

public interface ObterAprovadoresRmServicoService {

	List<ObterAprovadoresRmServicoResponse> obterAprovadoresServico(List<ObterAprovadoresRmServicoRequest> request) throws Exception;

}
