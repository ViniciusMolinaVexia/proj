package br.com.nextage.rmaweb.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.Perfil;
import br.com.nextage.rmaweb.entitybean.Role;

public class PerfilRoleDao {
	
	/**
	 * <b>getRolesPorPerfilId</b><br />
	 * Recupera todas as Roles de um perfil
	 * @param perfilId
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRolesPorPerfilId(Integer perfilId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        List<Role> roles = new ArrayList<Role>();
        try {
            trx = session.beginTransaction();
            roles = session.getNamedQuery("PerfilRole.getRolesPorPerfilId").setParameter("perfilId", perfilId).list();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: saveOrUpdate - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: saveOrUpdate - ERRO: " + e.getMessage());
            }
        }
        return roles;
    } 
	
	/**
	 * <b>getPerfisPorRole</b><br />
	 * Recupera todos os perfis de que contenham a role
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public List<Perfil> getPerfisPorRole(String role) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        List<Perfil> perfis = new ArrayList<Perfil>();
        try {
            trx = session.beginTransaction();
            perfis = session.getNamedQuery("PerfilRole.getPerfisPorRole").setParameter("role", role).list();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: saveOrUpdate - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: saveOrUpdate - ERRO: " + e.getMessage());
            }
        }
        return perfis;
    } 
	
}
