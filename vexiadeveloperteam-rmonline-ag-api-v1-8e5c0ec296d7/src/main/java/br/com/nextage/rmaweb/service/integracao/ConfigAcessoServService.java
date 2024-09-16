package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.ConfigAcessoServ;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author l.pordeus
 */
public class ConfigAcessoServService {

    private static final Logger log = Logger.getLogger(ConfigAcessoServService.class);

    public ConfigAcessoServService() {
    }

    /**
     * Recupera Acesso, com base no login, token e nome do serviço passado por
     * parametro.
     *
     * @param login
     * @param token
     * @param nomeServico
     * @return
     */
    public ConfigAcessoServ selectConfigAcessoServ(String login, String token, String nomeServico) {

        ConfigAcessoServ configAcessoServ = new ConfigAcessoServ();

        try {
            NxCriterion nxCriterion = null;

            nxCriterion = NxCriterion.montaRestriction(new Filtro(ConfigAcessoServ.LOGIN, login, Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(ConfigAcessoServ.TOKEN, token, Filtro.EQUAL)));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(ConfigAcessoServ.NOME_SERVICO, nomeServico, Filtro.EQUAL)));

            /* Filtra as propriedades da Requisição */
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(ConfigAcessoServ.ID));
            propriedades.add(new Propriedade(ConfigAcessoServ.LOGIN));
            propriedades.add(new Propriedade(ConfigAcessoServ.TOKEN));
            propriedades.add(new Propriedade(ConfigAcessoServ.NOME_SERVICO));

            GenericDao<ConfigAcessoServ> genericDao = new GenericDao<>();
            configAcessoServ = genericDao.selectUnique(propriedades, ConfigAcessoServ.class, nxCriterion);

        } catch (Exception e) {
            log.error(e);
        }
        return configAcessoServ;
    }

}
