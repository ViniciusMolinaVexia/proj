package br.com.nextage.rmaweb.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.Area;

public class AreaDAO {
	
	/**
	 * <b>getAreaPorCodigoCentro</b><br />
	 * Recupera as areas
	 * @param codigo
	 * @return
	 * @throws Exception
	 */
	public List<Area> getAreas() throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            List<Area> areas = session.getNamedQuery("area.getAll").list();
            trx.commit();
            return areas;
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: getAreaPorCodigoCentro - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: getAreaPorCodigoCentro - ERRO: " + e.getMessage());
            }
        }
	}
	
	/**
	 * <b>getAreaPorCodigo</b><br />
	 * Recupera as areas por codigo
	 * @param codigo
	 * @return
	 * @throws Exception
	 */
	public Area getAreaPorCodigo(String codigo) throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            Area area = (Area) session.getNamedQuery("area.getByCode").setParameter("codigo", codigo).uniqueResult();
            trx.commit();
            return area;
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: getAreaPorCodigo - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: getAreaPorCodigo - ERRO: " + e.getMessage());
            }
        }
	}
	
	/**
	 * <b>atualizarArea</b><br />
	 * Atualiza uma area
	 * @param centroDeposito
	 * @throws Exception
	 */
	public void atualizarArea(Area area) throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            session.saveOrUpdate(area);
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: atualizarArea - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: atualizarArea - ERRO: " + e.getMessage());
            }
        }
	}

}
