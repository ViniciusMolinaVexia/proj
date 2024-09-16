package br.com.nextage.rmaweb.service;

import br.com.nextage.rmaweb.entitybean.ConfIntegracao;

/**
 *
 * @author adriano.gomes
 */
public interface ConfIntegracaoService {

	void autenticar(final String login, final String senha, String url);
	ConfIntegracao listarConfIntegracao(String codigo);
}
