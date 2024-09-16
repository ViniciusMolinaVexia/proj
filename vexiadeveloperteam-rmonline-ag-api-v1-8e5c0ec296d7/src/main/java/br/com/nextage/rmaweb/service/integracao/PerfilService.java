package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.rmaweb.dao.PerfilDao;
import br.com.nextage.rmaweb.dao.PerfilRoleDao;
import br.com.nextage.rmaweb.entitybean.Perfil;
import br.com.nextage.rmaweb.entitybean.Role;

import java.util.ArrayList;
import java.util.List;

public class PerfilService {
	
	public void excluir(Perfil perfil) throws Exception{
		try {
			PerfilDao perfilDao = new PerfilDao();
			perfilDao.excluir(perfil);
		}catch (Exception e) {
			throw e;
		}
	}
	
	public Perfil salvar(Perfil perfil) throws Exception{
		try {
			PerfilDao perfilDao = new PerfilDao();
			perfilDao.saveOrUpdate(perfil);
		}catch (Exception e) {
			throw e;
		}
		return perfil;
	}
	
	/**
	 * <b>getRolesPorPerfil</b><br />
	 * Recupera todas as Roles do perfil
	 * @param perfilId
	 * @return
	 */
	public List<Role> getRolesPorPerfil(Integer perfilId){
		try {
			PerfilRoleDao perfilRoleDao = new PerfilRoleDao();
			return perfilRoleDao.getRolesPorPerfilId(perfilId);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * <b>getPerfisPorRole</b><br />
	 * Recupera todas as Roles do perfil
	 * @param perfilId
	 * @return
	 */
	public List<Perfil> getPerfisPorRole(String role){
		try {
			PerfilRoleDao perfilRoleDao = new PerfilRoleDao();
			return perfilRoleDao.getPerfisPorRole(role);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Perfil> getAll(){
		PerfilDao perfilDao = new PerfilDao();
		List<Perfil> perfis = new ArrayList<Perfil>();
		try {
			perfis = perfilDao.getAll();
			for(Perfil p : perfis) {
				p.setRoles(getRolesPorPerfil(p.getPerfilId()));
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		System.out.println("PERFIS:"+perfis.size());
		return perfis;
	}

}
