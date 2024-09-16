/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.dao;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.MaterialDepositoSaida;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author jerry
 */
public class MaterialDepositoSaidaDao {

    /* Método que realiza a inserção de um novo registro.
     *
     * <br>
     * <br>
     * <b>Autor:</b> Jerry.
     * <b>Data:</b> 09/05/2011
     * <br>
     * <br>
     *
     * @param obj - MaterialDepositoSaida
     * @return objSalvo.toString - String
     * @throws Exception - Exception
     */
    public String persist(MaterialDepositoSaida obj) throws Exception {
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
     * Método que faz a consulta no banco e traz o maior numero do protocolo de
     * saida.
     * <p/>
     * <b>Autor:</b> Jerry.
     * <b>Data:</b> 12-05-2011
     * <p/>
     * @return count - Integer
     * @throws Exception - Exception
     */
    public Integer numeroProtocoloSaidaMax() throws Exception {
        Integer count = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            StringBuilder sB = new StringBuilder();
            sB.append("select MAX(NUMERO_PROTOCOLO_SAIDA)as maior from TB_MATERIAL_DEPOSITO_SAIDA");

            Query q = session.createSQLQuery(sB.toString());
            count = (Integer) q.uniqueResult();
            session.close();
            if(count == null){
                count = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return count;
    }
}
