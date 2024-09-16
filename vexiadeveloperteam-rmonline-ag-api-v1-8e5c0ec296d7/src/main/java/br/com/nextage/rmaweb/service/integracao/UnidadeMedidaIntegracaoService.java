package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.UnidadeMedida;
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
public class UnidadeMedidaIntegracaoService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    private List<UnidadeMedida> listaUnidadeMedida;

    public UnidadeMedidaIntegracaoService() {
        listaUnidadeMedida = new ArrayList<>();
    }

    /**
     * Lista todas as unidades do BD.
     */
    private void listarUnidadeMedidas() {
        try {

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO));

            //Obtem elementos.
            GenericDao<UnidadeMedida> genericDao = new GenericDao<>();
            listaUnidadeMedida = genericDao.listarByFilter(propriedades, null, UnidadeMedida.class, Constantes.NO_LIMIT, null);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
    }

    /**
     * Pesquisa unidade com base no nome e na sigla passado por parametro.
     *
     * @param siglaUnidade
     * @return
     */
    public UnidadeMedida searchUnidadeMedida(String siglaUnidade) {
        UnidadeMedida unidadeMedida = null;
        try {
            if (listaUnidadeMedida == null || listaUnidadeMedida.isEmpty()) {
                listarUnidadeMedidas();
            }

            for (UnidadeMedida uM : listaUnidadeMedida) {
                if (uM.getSigla().equals(siglaUnidade)) {
                    return uM;
                }
            }

            unidadeMedida = novaUnidadeMedida(siglaUnidade);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return unidadeMedida;
    }

    private UnidadeMedida novaUnidadeMedida(String siglaUnidade) {
        UnidadeMedida unidadeMedida = null;
        Integer idObjeto;
        try {
            unidadeMedida = new UnidadeMedida(-1, siglaUnidade, siglaUnidade);
            GenericDao<UnidadeMedida> genericDao = new GenericDao<>();
            idObjeto = genericDao.persist(unidadeMedida);
            unidadeMedida.setUnidadeMedidaId(idObjeto);
        } catch (Exception e) {

        }
        return unidadeMedida;
    }
}
