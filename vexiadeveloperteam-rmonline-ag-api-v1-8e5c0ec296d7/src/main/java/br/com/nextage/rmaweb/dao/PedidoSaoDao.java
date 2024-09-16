/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.dao;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.PedidoSap;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Nextage
 */
public class PedidoSaoDao {

    /**
     * Método que realiza a inserção de um novo registro.
     * <br>
     * <br>
     * <b>Autor:</b> Moysés.
     * <b>Data:</b> 10/05/2011
     * <br>
     * <br>
     *
     * @param obj - PedidoSao
     * @return objSalvo.toString - String
     * @throws Exception - Exception
     */
    public String persist(PedidoSap obj) throws Exception {
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
     * Método que realiza a atualização dos dados de um Pedido SAP.
     * <br>
     * <br>
     * <b>Autor:</b> Moysés.
     * <b>Data:</b> 10/05/2011
     * <br>
     * <br>
     *
     * @param obj - PedidoSap
     * @return objSalvo.toString - String
     * @throws Exception - Exception
     */
    public String update(PedidoSap obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        Object objSalvo = new Object();
        try {
            trx = session.beginTransaction();

            session.update(obj);

            objSalvo = obj.getPedidoSapId();

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
}
