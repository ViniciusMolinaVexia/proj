package br.com.nextage.rmaweb.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.Centro;
import br.com.nextage.rmaweb.entitybean.GrupoComprador;

public class GrupoCompradorDAO {
	
	/**
	 * <b>getGrupoCompradorPorCodigo</b><br />
	 * Recupera os grupos de compradores por codigo
	 * @param codigo
	 * @return
	 * @throws Exception
	 */
	public GrupoComprador getGrupoCompradorPorCodigo(String codigo) throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            GrupoComprador gc = (GrupoComprador) session.getNamedQuery("grupoComprador.getByCode").setParameter("codigo", codigo).uniqueResult();
            trx.commit();
            return gc;
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: getGrupoCompradorPorCodigo - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: getGrupoCompradorPorCodigo - ERRO: " + e.getMessage());
            }
        }
	}
	
	/**
	 * <b>atualizarGrupoComprador</b><br />
	 * Atualiza um grupo comprador
	 * @param centroDeposito
	 * @throws Exception
	 */
	public void atualizarGrupoComprador(GrupoComprador grupoComprador) throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            session.saveOrUpdate(grupoComprador);
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: atualizarGrupoComprador - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: atualizarGrupoComprador - ERRO: " + e.getMessage());
            }
        }
	}

	/**
	 * <b>atualizarGrupoComprador</b><br />
	 * Atualiza um grupo comprador
	 *
	 * @param centroDeposito
	 * @throws Exception
	 */
	public List<GrupoComprador> listar() throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction trx = null;
		try {
			trx = session.beginTransaction();
			Query query = session.createQuery("from GrupoComprador");
			return query.list();
		} catch (HibernateException e) {
			trx.rollback();
			if (e.getCause() != null) {
				throw new Exception("Método: atualizarGrupoComprador - ERRO: " + e.getCause().getMessage());
			} else {
				throw new Exception("Método: atualizarGrupoComprador - ERRO: " + e.getMessage());
			}
		}
	}

}
