package br.com.nextage.rmaweb.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.Perfil;
import br.com.nextage.rmaweb.entitybean.PerfilRole;
import br.com.nextage.rmaweb.entitybean.Role;

public class PerfilDao {
	
	public List<Perfil> getAll() throws Exception {
		List<Perfil> perfis = new ArrayList<Perfil>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
        	trx = session.beginTransaction();
        	perfis = session.getNamedQuery("Perfil.getAll").list();
        	trx.commit();
        } catch (HibernateException e) {
        	trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: getAll - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: getAll - ERRO: " + e.getMessage());
            }
        }
        return perfis;
    } 
	
	public String saveOrUpdate(Perfil perfil) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        Object objSalvo = new Object();
        try {
            trx = session.beginTransaction();
            
            //Apaga todas as associacoes existentes
            session.getNamedQuery("PerfilRole.deleteByPerfil").setParameter("perfilId", perfil.getPerfilId()).executeUpdate();

            //Insere um novo registro
            if(perfil.getPerfilId()==null) {
            	objSalvo = session.save(perfil);
            }else {
            	session.update(perfil);
            	objSalvo = perfil;
            }
            
            //Associa as regras ao perfil
            for(Role r : perfil.getRoles()) {
            	PerfilRole pr = new PerfilRole();
            	pr.setPerfil(perfil);
            	pr.setRole(r);
            	session.persist(pr);
            }

            trx.commit();

        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: saveOrUpdate - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: saveOrUpdate - ERRO: " + e.getMessage());
            }
        }
        return objSalvo.toString();
    } 
	
	public void excluir(Perfil perfil) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            session.getNamedQuery("PerfilRole.deleteByPerfil").setParameter("perfilId", perfil.getPerfilId()).executeUpdate();
            session.getNamedQuery("Usuario.deletePerfil").setParameter("perfilId", perfil.getPerfilId()).executeUpdate();
            session.delete(perfil);
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: delete - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: delete - ERRO: " + e.getMessage());
            }
        }
    } 

}
