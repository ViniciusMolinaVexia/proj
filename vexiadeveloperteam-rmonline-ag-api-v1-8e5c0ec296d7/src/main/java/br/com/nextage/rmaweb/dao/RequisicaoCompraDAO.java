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
import br.com.nextage.rmaweb.entitybean.Anexo;
import br.com.nextage.rmaweb.entitybean.Centro;
import br.com.nextage.rmaweb.entitybean.RequisicaoCompra;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.utils.ConnectionFactory;
import br.com.nextage.rmaweb.vo.integracao.CompraVo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RequisicaoCompraDAO {

    public Integer persist(RequisicaoCompra obj, GenericDao<?> genericDao) throws Exception {
        try {
            return genericDao.persistWithCurrentTransaction(obj);
        } catch (Exception e) {
            throw new Exception("Método: persist - ERRO: " + e.getCause().getMessage());
        }
    }

    public boolean exists(CompraVo compra) throws Exception {
        try {
            NxCriterion nxCriterion = NxCriterion.montaRestriction(
                    new Filtro(RequisicaoCompra.ITEM_RM_SAP, compra.getItemRmSap(), Filtro.EQUAL));

            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(
                    new Filtro(RequisicaoCompra.NUMERO_RM_SAP, compra.getRmSap(), Filtro.EQUAL)));

            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(
                    new Filtro(RequisicaoCompra.DOCUMENTO_COMPRA, compra.getDocumentoCompra(), Filtro.EQUAL)));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RequisicaoCompra.ID));
            GenericDao<RequisicaoCompra> genericDao = new GenericDao<>();
            return genericDao.selectCountByFilter(nxCriterion, RequisicaoCompra.class, propriedades) > 0;
        } catch (Exception e) {
            throw new Exception("Método: persist - ERRO: " + e.getCause().getMessage());
        }
    }

    public Long getAmountByRmId(Integer rmId) {
        Long count = 0L;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            Query q = session.createQuery("select sum(r.quantidade) from RequisicaoCompra r where r.rm.rmId = :rmId");
            q.setParameter("rmId", rmId);
            count = (Long) q.uniqueResult();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public Long getExistObservacaoSAP(String rmId) {
        Long count = 0L;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            Query q = session.createQuery("select Count(*) from RmMaterial r where r.rm.rmId = :rmId and r.numeroRequisicaoSap != '' ");
            q.setString("rmId", rmId);
            count = (Long)q.uniqueResult();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    
    //Get Anexos pelo numero da RM
    @SuppressWarnings("unchecked")
	public List<Anexo> getAnexosByRm(int rmId) {
    	List<Anexo> listAnexos = new ArrayList<Anexo>();
        try {
        	
             Session session = HibernateUtil.getSessionFactory().getCurrentSession();
             session.beginTransaction();
             
             listAnexos = session.createQuery( "select anexo from Anexo as anexo where anexo.escopoId = :escopoId order by anexo.id" )
            		 .setParameter("escopoId", rmId)
            		 .list();
            
             session.getTransaction().commit();
            
        } catch (Exception e) {
            System.out.println("*************************************************ERRO AO GERAR CONSULTA getAnexosByRm*************************************************");
            e.printStackTrace();
        }
        return listAnexos;
    }
}
