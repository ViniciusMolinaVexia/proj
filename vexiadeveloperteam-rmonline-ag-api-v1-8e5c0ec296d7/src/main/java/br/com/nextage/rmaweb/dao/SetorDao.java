/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.dao;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.Setor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Classe que permite acesso à persistencia
 *
 * @author Felipe
 */
public class SetorDao {

    /**
     * Método que realiza a inserção de um novo registro.
     *
     * <br>
     * <br>
     * <b>Autor:</b> Felipe.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param obj - Setor
     * @return objSalvo.toString - String
     * @throws Exception - Exception
     */
    public String persist(Setor obj) throws Exception {
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
     * Método que realiza a atualização dos dados de um Setor.
     * <br>
     * <br>
     * <b>Autor:</b> Felipe.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param obj - Setor
     * @return objSalvo.toString - String
     * @throws Exception - Exception
     */
    public String update(Setor obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        Object objSalvo = new Object();
        try {
            trx = session.beginTransaction();

            session.update(obj);

            objSalvo = obj.getSetorId();

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
