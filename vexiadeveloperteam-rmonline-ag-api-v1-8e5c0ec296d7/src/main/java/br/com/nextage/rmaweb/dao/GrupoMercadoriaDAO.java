package br.com.nextage.rmaweb.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.GrupoMercadoria;

public class GrupoMercadoriaDAO {
	
	/**
	 * <b>getGrupoMercadoriaPorCodigo</b><br />
	 * Recupera os grupos de mercadorias por codigo
	 * @param codigo
	 * @return
	 * @throws Exception
	 */
	public GrupoMercadoria getGrupoMercadoriaPorCodigo(String codigo) throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            GrupoMercadoria gm = (GrupoMercadoria) session.getNamedQuery("grupoMercadoria.getByCode").setParameter("codigo", codigo).uniqueResult();
            trx.commit();
            return gm;
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: getGrupoMercadoriaPorCodigo - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: getGrupoMercadoriaPorCodigo - ERRO: " + e.getMessage());
            }
        }
	}
	
	/**
	 * <b>atualizarGrupoMercadoria</b><br />
	 * Atualiza um grupo mercadoria
	 * @param centroDeposito
	 * @throws Exception
	 */
	public void atualizarGrupoMercadoria(GrupoMercadoria grupoMercadoria) throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            session.saveOrUpdate(grupoMercadoria);
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: atualizarGrupoMercadoria - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: atualizarGrupoMercadoria - ERRO: " + e.getMessage());
            }
        }
	}

}
