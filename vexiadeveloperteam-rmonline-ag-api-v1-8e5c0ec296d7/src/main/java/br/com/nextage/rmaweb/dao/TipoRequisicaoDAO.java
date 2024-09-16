package br.com.nextage.rmaweb.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.TipoRequisicao;

public class TipoRequisicaoDAO {
	
	private TipoRequisicaoDAO() {}
	private static TipoRequisicaoDAO tipoRequisicaoDAO=null;
	public static TipoRequisicaoDAO getInstance() {
		if(tipoRequisicaoDAO==null) {
			tipoRequisicaoDAO = new TipoRequisicaoDAO();
		}
		return tipoRequisicaoDAO;
	}

	public List<TipoRequisicao> getAll(){
		List<TipoRequisicao> tps = new ArrayList<TipoRequisicao>();
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            tps = session.getNamedQuery("tipoRequisicao.getAll").list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tps;
	}
	
	public TipoRequisicao getTipoRequisicaoPorCodigo(String codigo){
		TipoRequisicao tp = new TipoRequisicao();
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            tp = (TipoRequisicao) session.getNamedQuery("tipoRequisicao.getTipoRequisicaoPorCodigo").setParameter("codigo", codigo).list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tp;
	}
}
