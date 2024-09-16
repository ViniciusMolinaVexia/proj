/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.Prioridade;

/**
 * Classe que permite acesso à persistencia
 *
 * @author Felipe
 */
public class PrioridadeDao {

    /**
     * Método que realiza a inserção de um novo registro.
     *
     * <br>
     * <br>
     * <b>Autor:</b> Leandro.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param obj - Prioridade
     * @return objSalvo.toString - String
     * @throws Exception - Exception
     */
    public String persist(Prioridade obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        Object objSalvo = new Object();
        try {
            trx = session.beginTransaction();

            //Insere um novo registro
            objSalvo = session.save(obj);

            trx.commit();

        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: persist - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: persist - ERRO: " + e.getMessage());
            }
        }
        return objSalvo.toString();
    }

    /**
     * Método que realiza a atualização dos dados de uma Prioridade.
     * <br>
     * <br>
     * <b>Autor:</b> Felipe.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param obj - Prioridade
     * @return objSalvo.toString - String
     * @throws Exception - Exception
     */
    public String update(Prioridade obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        Object objSalvo = new Object();
        try {
            trx = session.beginTransaction();

            session.update(obj);

            objSalvo = obj.getPrioridadeId();

            trx.commit();

        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: update - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: update - ERRO: " + e.getMessage());
            }
        }
        return objSalvo.toString();
    }
    
    public List<Prioridade> getHql(String hql) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        List<Prioridade> prioridades = new ArrayList<>();
        try {
            trx = session.beginTransaction();
            //Insere um novo registro
            prioridades = session.createQuery(hql).list();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: persist - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: persist - ERRO: " + e.getMessage());
            }
        }
        return prioridades;
    }

    public Prioridade getPrioridadeId(Integer prioridadeId) throws Exception {
    	
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            //Insere um novo registro
            Prioridade prioridade = (Prioridade) session.getNamedQuery("prioridade.getById").setParameter("prioridadeId", prioridadeId).uniqueResult();
            trx.commit();
            return prioridade;
        } catch (HibernateException e) {
        	trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: getAreaPorCodigo - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: getAreaPorCodigo - ERRO: " + e.getMessage());
            }
        }
    }
}
