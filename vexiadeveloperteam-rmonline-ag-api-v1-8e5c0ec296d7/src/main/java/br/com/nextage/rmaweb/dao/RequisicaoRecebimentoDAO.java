/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.dao;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.RequisicaoRecebimento;
import br.com.nextage.rmaweb.ws.receberentradamaterial.ReceberEntradaMaterialRequest;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequisicaoRecebimentoDAO {

    public Integer persist(RequisicaoRecebimento obj, GenericDao<?> genericDao) throws Exception {
        try {
            return genericDao.persistWithCurrentTransaction(obj);
        } catch (Exception e) {
            throw new Exception("Método: persist - ERRO: " + e.getCause().getMessage());
        }
    }

    public boolean exists(ReceberEntradaMaterialRequest entrada) throws Exception {
        try {
            NxCriterion nxCriterion = NxCriterion.montaRestriction(
                    new Filtro(RequisicaoRecebimento.ITEM_RM_SAP, entrada.getItemRmSap(), Filtro.EQUAL));

            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(
                    new Filtro(RequisicaoRecebimento.NUMERO_RM_SAP, entrada.getRmSap(), Filtro.EQUAL)));

            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(
                    new Filtro(RequisicaoRecebimento.DOCUMENTO_RECEBIMENTO, entrada.getDocumentoEntrada(), Filtro.EQUAL)));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RequisicaoRecebimento.ID));
            GenericDao<RequisicaoRecebimento> genericDao = new GenericDao<>();
            return genericDao.selectCountByFilter(nxCriterion, RequisicaoRecebimento.class, propriedades) > 0;
        } catch (Exception e) {
            throw new Exception("Método: persist - ERRO: " + e.getCause().getMessage());
        }
    }

    public Long getAmountByRmId(Integer rmId) {
        Long count = 0L;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            Query q = session.createQuery("select sum(r.quantidade) from RequisicaoRecebimento r where r.rm.rmId = :rmId");
            q.setParameter("rmId", rmId);
            count = (Long) q.uniqueResult();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
