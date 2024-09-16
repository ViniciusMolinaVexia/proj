package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.ConfIntegracao;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;

public class ConfIntegracaoServiceImpl implements ConfIntegracaoService {


	@Override
	public void autenticar(String login, String senha, String url) {

		Authenticator myAuth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(login, senha.toCharArray());
			}
		};

		Authenticator.setDefault(myAuth);
	}

	@Override
	public ConfIntegracao listarConfIntegracao(String codigo) {
		ConfIntegracao confIntegracao = new ConfIntegracao();
		GenericDao<ConfIntegracao> genericDao = new GenericDao<>();

		try {
			List<Propriedade> propriedades = new ArrayList<>();
			propriedades.add(new Propriedade(ConfIntegracao.ID));
			propriedades.add(new Propriedade(ConfIntegracao.CODIGO));
			propriedades.add(new Propriedade(ConfIntegracao.URL));
			propriedades.add(new Propriedade(ConfIntegracao.LOGIN));
			propriedades.add(new Propriedade(ConfIntegracao.SENHA));
			propriedades.add(new Propriedade(ConfIntegracao.URL_WSDL_SERVICE));

			NxCriterion nxCriterion = NxCriterion
					.montaRestriction(new Filtro(ConfIntegracao.CODIGO, codigo, Filtro.EQUAL));

			confIntegracao = genericDao.selectUnique(propriedades, ConfIntegracao.class, nxCriterion);
		} catch (Exception e) {

		}
		return confIntegracao;
	}
}
