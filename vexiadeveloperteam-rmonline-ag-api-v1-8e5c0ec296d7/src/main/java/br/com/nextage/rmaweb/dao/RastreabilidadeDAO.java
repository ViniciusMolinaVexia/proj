package br.com.nextage.rmaweb.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.Rastreabilidade;

public class RastreabilidadeDAO {
	
	private static RastreabilidadeDAO rastreabilidadeDAO = null;
	
	private RastreabilidadeDAO() {}
	
	public static RastreabilidadeDAO getInstance() {
		if(rastreabilidadeDAO==null) {
			rastreabilidadeDAO = new RastreabilidadeDAO();
		}
		return rastreabilidadeDAO;
	}
	
	/**
	 * <b>getRastreabilidadePorNumeroRm</b><br />
	 * @param numeroRm
	 * @return
	 * @throws Exception
	 */
	public List<Rastreabilidade> getRastreabilidadePorNumeroRm(String numeroRm) throws Exception {
		List<Rastreabilidade> rastros = new ArrayList<Rastreabilidade>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
        	trx = session.beginTransaction();
        	rastros = session.getNamedQuery("rastreabilidade.getPorNumeroRm").setParameter("numero", numeroRm).list();
        	trx.commit();
        } catch (HibernateException e) {
        	trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: getRastreabilidadePorNumeroRm - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: getRastreabilidadePorNumeroRm - ERRO: " + e.getMessage());
            }
        }
        return rastros;
    } 
	
	/**
	 * <b>incluirRastro</b><br />
	 * Incluir rastro de alteracao de uma RM
	 * @param rastreabilidade
	 * @throws Exception
	 */
	public void incluirRastro(Rastreabilidade rastreabilidade) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
        	trx = session.beginTransaction();
        	session.persist(rastreabilidade);
        	trx.commit();
        } catch (HibernateException e) {
        	trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: incluirRastro - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: incluirRastro - ERRO: " + e.getMessage());
            }
        }
    } 

}
