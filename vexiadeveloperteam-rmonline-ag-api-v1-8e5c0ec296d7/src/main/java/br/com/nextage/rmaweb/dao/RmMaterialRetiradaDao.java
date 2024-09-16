/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.dao;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.RmMaterialRetirada;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Classe que permite acesso à persistencia
 *
 * @author Jerry
 */
public class RmMaterialRetiradaDao {
	
    /**
     * Método que realiza a inserção de um novo registro.
     *
     * <p/>
     * <b>Autor:</b> Jerry.
     * <b>Data:</b> 23-03-2011
     * <p/>
     *
     * @param obj - RmMaterialRetirada
     * @return objSalvo.toString - String
     * @throws Exception - Exception
     */
    public String persist(RmMaterialRetirada obj) throws Exception {
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
     * Método que realiza a atualização dos dados de um RmMaterialRetirada.
     * <p/>
     * <b>Autor:</b> Jerry.
     * <b>Data:</b> 23-03-2011
     * <p/>
     *
     * @param obj - RmMaterialRetirada
     * @return objSalvo.toString - String
     * @throws Exception - Exception
     */
    public String update(RmMaterialRetirada obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        Object objSalvo = new Object();
        try {
            trx = session.beginTransaction();

            session.update(obj);

            objSalvo = obj.getRmMaterialRetiradaId();

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
     * Método que faz a consulta no banco e traz o maior numero do protocolo.
     * <p/>
     * <b>Autor:</b> Jerry.
     * <b>Data:</b> 29-04-2011
     * <p/>
     * @return count - Integer
     * @throws Exception - Exception
     */
    public Integer numeroProtocoloMax() throws Exception {
        Integer count = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            StringBuilder sB = new StringBuilder();
            sB.append("select MAX(numero_protocolo)as maior from tb_rm_material_retirada");

            Query q = session.createSQLQuery(sB.toString());
            count = (Integer) q.uniqueResult();
            if (count == null) {
                return 0;
            }
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return count;
    }
}
