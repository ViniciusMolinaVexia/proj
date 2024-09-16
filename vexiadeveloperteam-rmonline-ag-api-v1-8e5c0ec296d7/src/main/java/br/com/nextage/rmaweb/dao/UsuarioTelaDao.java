/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.dao;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.UsuarioTela;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Classe realiza acesso à persistência
 *
 * @author Leandro
 */
public class UsuarioTelaDao {

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
     * @param obj - UsuarioTela
     * @return objSalvo.toString - String
     * @throws Exception - Exception
     */
    public String persist(UsuarioTela obj) throws Exception {
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
     * Método que realiza a atualização dos dados de um UsuarioTela.
     * <br>
     * <br>
     * <b>Autor:</b> Leandro.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param obj - UsuarioTela
     * @return objSalvo.toString - String
     * @throws Exception - Exception
     */
    public String update(UsuarioTela obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        Object objSalvo = new Object();
        try {
            trx = session.beginTransaction();

            session.update(obj);

            objSalvo = obj.getUsuarioTelaId();

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

    /**
     * Método que realiza a atualização dos dados de um Usuario.
     * <br>
     * <br>
     * <b>Autor:</b> Leandro.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param usuarioId - Integer
     * @return true/false - Boolean
     * @throws Exception - Exception
     */
    public Boolean deleteAll(Integer usuarioId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            Query deletar = session.getNamedQuery("UsuarioTela.deleteAll");
            deletar.setInteger("usuarioId", usuarioId);
            deletar.executeUpdate();

            trx.commit();
        } catch (Exception e) {
            trx.rollback();
            throw new Exception("Método: deleteAll - ERRO: " + e.getMessage());
        }
        return true;
    }
}
