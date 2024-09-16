package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Deposito;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.entitybean.RmDeposito;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author l.pordeus
 */
public class RmDepositoService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    public List<RmDeposito> listarDepositosByRm(Rm rm) {
        List<RmDeposito> lista = new ArrayList();

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), rm, null));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmDeposito.ID));

            String aliasDeposito = NxCriterion.montaAlias(RmDeposito.ALIAS_CLASSE, RmDeposito.DEPOSITO);
            String aliasRm = NxCriterion.montaAlias(RmDeposito.ALIAS_CLASSE, RmDeposito.RM);

            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));

            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            NxCriterion criterion = NxCriterion.montaRestriction(new Filtro(Rm.RM_ID, rm.getRmId(), Filtro.EQUAL, aliasRm));

            GenericDao genericDao = new GenericDao();

            lista = genericDao.listarByFilter(propriedades, null, RmDeposito.class, Constantes.NO_LIMIT, criterion);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), rm, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return lista;
    }
}
