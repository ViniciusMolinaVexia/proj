/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.dao;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.Deposito;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jerry 21/03/2011
 */
public class DepositoDao {
	
	/**
	 * <b>getDepositosPorCentro</b><br />
	 * Recupera os depositos por centro
	 * @param codigo
	 * @return
	 * @throws Exception
	 */
	public List<Deposito> getDepositosPorCentro(Integer centroId) throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        List<Deposito> depositos = new ArrayList<Deposito>();
        try {
            trx = session.beginTransaction();
            depositos = session.getNamedQuery("Deposito.getByCentroId").setParameter("centroId", centroId).list();
            trx.commit();
            return depositos;
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: getDepositosPorCentro - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: getDepositosPorCentro - ERRO: " + e.getMessage());
            }
        }
	}

    /**
     * Método que realiza a inserção de um novo registro.
     *
     * <br>
     * <br>
     * <b>Autor:</b> Jerry.
     * <b>Data:</b> 21/03/2011
     * <br>
     * <br>
     *
     * @param obj - Deposito
     * @return objSalvo.toString - String
     * @throws Exception - Exception
     */
    public String persist(Deposito obj) throws Exception {
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
     * Método que realiza a atualização dos dados de um Deposito.
     * <br>
     * <br>
     * <b>Autor:</b> Jerry.
     * <b>Data:</b> 21/03/2011
     * <br>
     * <br>
     *
     * @param obj - Deposito
     * @return objSalvo.toString - String
     * @throws Exception - Exception
     */
    public String update(Deposito obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        Object objSalvo = new Object();
        try {
            trx = session.beginTransaction();

            session.update(obj);

            objSalvo = obj.getDepositoId();

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

    public Deposito findByCodigoAndCentro(final String codigo, final String centro) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
//        List<Deposito> depositos = new ArrayList<Deposito>();
        try {
            trx = session.beginTransaction();
            final Deposito deposito = (Deposito) session.getNamedQuery("Deposito.findByCodigoAndCentro").setParameter("codigo", codigo).setParameter("centro", centro).uniqueResult();
            trx.commit();
            return deposito;
        } catch (HibernateException e) {
            throw new Exception("Método: findByCodigoAndCentro - ERRO: " + e.getCause().getMessage());
        }
    }

}
