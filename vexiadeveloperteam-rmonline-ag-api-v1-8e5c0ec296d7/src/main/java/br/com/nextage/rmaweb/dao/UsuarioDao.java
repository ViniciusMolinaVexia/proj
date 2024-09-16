/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.dao;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Centro;
import br.com.nextage.rmaweb.entitybean.Perfil;
import br.com.nextage.rmaweb.entitybean.RmServicoCodigoSap;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.entitybean.UsuarioCentro;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.utils.ConnectionFactory;
import br.com.nextage.rmaweb.vo.CadastroUsuarioVo;
import br.com.nextage.util.Info;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import br.com.nextage.util.filter.FiltroAutoComplete;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Classe realiza acesso à persistência
 *
 * @author Leandro
 */
public class UsuarioDao {

    /**
     * Método que realiza a inserção de um novo registro.
     *
     * <br>
     * <br>
     * <b>Autor:</b> Leandro.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param obj - Usuario
     * @return objSalvo.toString - String
     * @throws Exception - Exception
     */
	
	@Context
    HttpServletRequest request;
    public String persist(Usuario obj) throws Exception {
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
     * Método que realiza a atualização dos dados de um Usuario.
     * <br>
     * <br>
     * <b>Autor:</b> Leandro.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param obj - Usuario
     * @return objSalvo.toString - String
     * @throws Exception - Exception
     */
    public String update(Usuario obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        Object objSalvo = new Object();
        try {
            trx = session.beginTransaction();

            session.update(obj);

            objSalvo = obj.getUsuarioId();

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
    
    /**
     * <b>getUsuariosPorPerfil</b><br />
     * Recupera todos os usuarios associados a um perfil
     * @param perfilId
     * @return
     * @throws Exception
     */
    public List<Usuario> getUsuariosPorPerfil(Integer perfilId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            trx = session.beginTransaction();
            usuarios = session.getNamedQuery("Usuario.usuariosPorPerfilId").setParameter("perfilId", perfilId).list();
            trx.commit();

        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: getUsuariosPorPerfil - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: getUsuariosPorPerfil - ERRO: " + e.getMessage());
            }
        }
        return usuarios;
    }
    
    /**
     * <b>getUsuarioPorUsuarioId</b><br />
     * Recupera o usuario por usuarioId
     * @param perfilId
     * @return
     * @throws Exception
     */
    public Usuario getUsuarioPorUsuarioId(Integer usuarioId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        Usuario usuario = null;
        try {
            trx = session.beginTransaction();
            usuario = (Usuario) session.getNamedQuery("Usuario.usuarioPorUsuarioId").setParameter("usuarioId", usuarioId).uniqueResult();
            trx.commit();

        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: getUsuarioPorUsuarioId - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: getUsuarioPorUsuarioId - ERRO: " + e.getMessage());
            }
        }
        return usuario;
    }
    
    /**
     * <b>getUsuarioPorUsuarioId</b><br />
     * Recupera o usuario por usuarioId
     * @param perfilId
     * @return
     * @throws Exception
     */
    public List<Usuario> getUsuariosPorRole(String role) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        List<Perfil> perfis = null;
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            trx = session.beginTransaction();
            perfis = session.getNamedQuery("PerfilRole.getPerfisPorRole").setParameter("role", role).list();
            for(Perfil p : perfis) {
            	usuarios.addAll(session.getNamedQuery("Usuario.usuariosPorPerfilId").setParameter("perfilId", p.getPerfilId()).list());
            }
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: getUsuarioPorRole - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: getUsuarioPorRole - ERRO: " + e.getMessage());
            }
        }
        return usuarios;
    }
    
    public List<UsuarioCentro> getUsuariosCentrosById(int idUsuario) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        List<UsuarioCentro> centros = null;
        //List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            trx = session.beginTransaction();
            centros = session.getNamedQuery("UsuarioCentro.usuarioPorUsuarioId").setParameter("usuarioId", idUsuario).list();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: getUsuariosCentrosById - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: getUsuariosCentrosById - ERRO: " + e.getMessage());
            }
        }
        return centros;
    }
    
    public List<UsuarioCentro> getCentrosByUsuario(int usuarioId) {
    	Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);
    	List<UsuarioCentro> list = new ArrayList<>();
        GenericDao genericDao = new GenericDao();
        try {
        	
        	String sql = "SELECT USUARIO_CENTRO_ID, CENTRO_ID, USUARIO_ID "
        			+ " FROM TB_USUARIO_CENTRO "
        			+ " WHERE USUARIO_ID = " +usuarioId+" ;";

        	PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet resultSet = smt.executeQuery();

             while (resultSet.next()) {
            	 UsuarioCentro usuarioCentro = new UsuarioCentro();
            	 usuarioCentro.setUsuarioCentroId(Integer.parseInt(resultSet.getString("USUARIO_CENTRO_ID")));
            	 /*usuarioCentro.setCentro(Integer.parseInt(resultSet.getString("CENTRO_ID")));
            	 usuarioCentro.setUsuario(resultSet.getString("USUARIO_ID")));*/
                 list.add(usuarioCentro);
             }

             smt.close();
             resultSet.close();	
            
        } catch (Exception e) {
            System.out.println("*************************************************ERRO AO GERAR CONSULTA GET CENTROS BY USUARIO*************************************************");
        }
        return list;
    }
    
    public Info deleteCentrosByUsuario(int usuarioCentroId, int usuarioId) {
    	Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);
    	List<UsuarioCentro> list = new ArrayList<>();
        GenericDao genericDao = new GenericDao();
        Info info = new Info();
		try {
        	
        	String sql = "DELETE FROM TB_USUARIO_CENTRO "
        			+ " WHERE USUARIO_CENTRO_ID = " +usuarioCentroId + " AND USUARIO_ID = " +usuarioId;
        	 
             Statement smt = ConnectionFactory.getConnection().createStatement();
             smt.executeUpdate(sql);
             
             smt.close();
             
             info  = new Info();
             info.setTipo(Info.TIPO_MSG_SUCCESS);
             info.setMensagem("msg_registro_exclusao_sucesso");
            
        } catch (Exception e) {
        	info = Info.GetError(Constantes.REG_EXCLUSAO_ERRO_I18N);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            System.out.println("*************************************************ERRO AO GERAR CONSULTA GET CENTROS BY USUARIO*************************************************");
        }
		return info;
    }
}
