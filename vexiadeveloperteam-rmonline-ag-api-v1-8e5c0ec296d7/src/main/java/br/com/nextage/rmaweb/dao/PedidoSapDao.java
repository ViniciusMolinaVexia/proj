/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.dao;

import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.PedidoSap;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.jdbc.Batcher;

/**
 *
 * @author Nextage
 */
public class PedidoSapDao {

    private DaoContext daoContext;
    private Session batchSession;
    private Transaction batchTx;
    private List<PedidoSap> batchUpdateList;
    private int pendingOperations;
    private final Filtro filtroNumeroPedido;
    private GenericDao<PedidoSap> genericDAO;

    public PedidoSapDao(DaoContext daoCtx) {
        this.daoContext = daoCtx;
        filtroNumeroPedido = new Filtro(PedidoSap.NUMERO_PEDIDO_COMPRA, null, Filtro.EQUAL);
        genericDAO = new GenericDao<>();
        pendingOperations = 0;
    }

    public PedidoSapDao() {
        filtroNumeroPedido = new Filtro(PedidoSap.NUMERO_PEDIDO_COMPRA, null, Filtro.EQUAL);
        genericDAO = new GenericDao<>();
        pendingOperations = 0;
    }

    public List<PedidoSap> getBatchUpdateList() {
        return batchUpdateList;
    }

    public void setBatchUpdateList(List<PedidoSap> batchUpdateList) {
        this.batchUpdateList = batchUpdateList;
    }

    public void openBatchUpdateSession() {
        if (daoContext != null) {
            batchSession = daoContext.getSession();
            batchTx = daoContext.getTrx();
        } else {
            batchSession = HibernateUtil.getSessionFactory().openSession();
            batchTx = batchSession.beginTransaction();
        }
    }

    public boolean closeBatchUpdateSession() {
        boolean ret = true;
        try {
            if (daoContext != null) {
                daoContext.close();
            } else {
                SessionImplementor s = (SessionImplementor) batchSession;
                Batcher b = s.getBatcher();
                b.executeBatch();
                batchTx.commit();
                batchSession.close();
            }
        } catch (HibernateException he) {
            Logger.getLogger(PedidoSapDao.class.getName()).log(Level.SEVERE, null, he);
            ret = false;
        }
        return ret;
    }

    public void addEntityToBatchUpdateList(PedidoSap ps) {
        try {
            pendingOperations++;
            if (ps.getPedidoSapId() == null) {
                batchSession.save(ps);
            } else {
                batchSession.update(ps);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public String merge(PedidoSap ps) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        Object objSalvo = new Object();
        try {
            trx = session.beginTransaction();

            //Insere um novo registro
            objSalvo = session.merge(ps);

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

    public PedidoSap selectPedidoPorNumero(String numeroPedidoCompra) throws Exception {
        return selectPedidoPorNumero(null, numeroPedidoCompra);
    }

    public PedidoSap selectPedidoPorNumero(DaoContext ctx, String numeroPedidoCompra) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        PedidoSap pSap = null;
        try {
            if (ctx != null) {
                Query q = ctx.getSession().getNamedQuery("PedidoSap.findByNumeroPedidoCompra");
                q = q.setParameter("numeroPedidoCompra", numeroPedidoCompra);
                pSap = (PedidoSap) q.uniqueResult();
            } else {
                filtroNumeroPedido.setValorCampo(numeroPedidoCompra);
                filtros.add(filtroNumeroPedido);
                pSap = (PedidoSap) genericDAO.selectUnique(filtros, PedidoSap.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return pSap;
    }

    public DaoContext getDaoContext() {
        return daoContext;
    }

    public void setDaoContext(DaoContext daoContext) {
        this.daoContext = daoContext;
    }

}
