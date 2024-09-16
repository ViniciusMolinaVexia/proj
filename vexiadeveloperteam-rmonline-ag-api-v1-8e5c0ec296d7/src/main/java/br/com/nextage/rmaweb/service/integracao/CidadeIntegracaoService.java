package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Cidade;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author l.pordeus
 */
public class CidadeIntegracaoService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    private List<Cidade> listaCidade;

    public CidadeIntegracaoService() {
        listaCidade = new ArrayList<>();
    }

    /**
     * Lista todas as cidade do BD.
     */
    private void listarCidades() {
        try {

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Cidade.CIDADE));
            propriedades.add(new Propriedade(Cidade.NOME));
            propriedades.add(new Propriedade(Cidade.UF));

            //Obtem elementos.
            GenericDao<Cidade> genericDao = new GenericDao<>();
            listaCidade = genericDao.listarByFilter(propriedades, null, Cidade.class, Constantes.NO_LIMIT, null);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
    }

    /**
     * Pesquisa cidade com base no nome e na sigla do uf passados por parametro.
     *
     * @param nomeCidade
     * @param siglaUf
     * @return
     */
    public Cidade searchCidade(String nomeCidade, String siglaUf) {
        try {
            if (listaCidade == null || listaCidade.isEmpty()) {
                listarCidades();
            }

            for (Cidade cidade : listaCidade) {
                if (cidade.getNome().equals(nomeCidade) && cidade.getUf().getUf().equals(siglaUf)) {
                    return cidade;
                }
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return null;
    }

}
