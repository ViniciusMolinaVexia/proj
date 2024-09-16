/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.dao;

import br.com.nextage.persistence.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.jdbc.Batcher;

/**
 *
 * @author Moysés Borges <m.borges@nextage.com.br>
 */
public class DaoContext {

    private Session session;
    private Transaction trx;

    public DaoContext(Session session, Transaction trx) {
        this.session = session;
        this.trx = trx;
    }

    public DaoContext() {
        session = HibernateUtil.getSessionFactory().openSession();
        trx = session.beginTransaction();
    }

    public void close() throws HibernateException {
        SessionImplementor s = (SessionImplementor) session;
        Batcher b = s.getBatcher();
        b.executeBatch();
        trx.commit();
        session.close();
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTrx() {
        return trx;
    }

    public void setTrx(Transaction trx) {
        this.trx = trx;
    }
}
