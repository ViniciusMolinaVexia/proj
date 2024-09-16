package br.com.nextage.rmaweb.controller.pessoa.obterPorPerfilECentro;

import java.util.List;

public interface ObterPessoaPorPerfilECentroService {

	List<ObterPessoaPorPerfilECentro> obterPorPerfilECentro(Integer perfilId, Integer centroId) throws Exception;

}
