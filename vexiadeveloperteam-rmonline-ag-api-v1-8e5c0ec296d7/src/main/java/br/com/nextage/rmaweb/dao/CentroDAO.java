package br.com.nextage.rmaweb.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.Collections;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Centro;
import br.com.nextage.rmaweb.entitybean.TipoRequisicao;
import br.com.nextage.rmaweb.entitybean.UsuarioCentro;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.utils.ConnectionFactory;

public class CentroDAO {
	
	private CentroDAO() {}
	private static CentroDAO centroDAO=null;
	public static CentroDAO getInstance() {
		if(centroDAO==null) {
			centroDAO = new CentroDAO();
		}
		return centroDAO;
	}
	
	/**
	 * <b>getCentroPorId</b><br />
	 * Recupera os centros por codigo
	 * @param codigo
	 * @return
	 * @throws Exception
	 */
	public Centro getCentroPorId(Integer centroId) throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            Centro cd = (Centro) session.getNamedQuery("Centro.getById").setParameter("centroId", centroId).uniqueResult();
            trx.commit();
            return cd;
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: getCentroPorCodigo - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: getCentroPorCodigo - ERRO: " + e.getMessage());
            }
        }
	}
	
	/**
	 * <b>getCentroPorCodigo</b><br />
	 * Recupera os centros por codigo
	 * @param codigo
	 * @return
	 * @throws Exception
	 */
	public Centro getCentroPorCodigo(String codigo) throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            Centro cd = (Centro) session.getNamedQuery("Centro.getByCode").setParameter("codigo", codigo).uniqueResult();
            trx.commit();
            return cd;
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: getCentroPorCodigo - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: getCentroPorCodigo - ERRO: " + e.getMessage());
            }
        }
	}
	
	/**
	 * <b>atualizarCentroDeposito</b><br />
	 * Atualiza um centro/despoito
	 * @param centroDeposito
	 * @throws Exception
	 */
	public void atualizarCentroDeposito(Centro centroDeposito) throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            session.saveOrUpdate(centroDeposito);
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: atualizarCentroDeposito - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: atualizarCentroDeposito - ERRO: " + e.getMessage());
            }
        }
	}
	
	/**
	 * <b>getAll</b><br />
	 * Recupera todos os centros
	 * @param codigo
	 * @return
	 * @throws Exception
	 */
	public List<Centro> getAll() throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        List<Centro> centros = new ArrayList<Centro>();
        try {
            trx = session.beginTransaction();
            centros = session.getNamedQuery("Centro.getAll").list();
            trx.commit();
            return centros;
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: getCentroDepositoPorCodigo - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: getCentroDepositoPorCodigo - ERRO: " + e.getMessage());
            }
        }
	}
	
	public List<Centro> getAllCentroCusto(){
		List<Centro> centros = new ArrayList<Centro>();
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            centros = session.getNamedQuery("Centro.getAll").list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return centros;
	}
	
	/*Henrique Marques*/	
	@SuppressWarnings("unchecked")
	public List<Centro> getCentrosByUsuario(int usuarioId) throws Exception {
    	Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);
    	//List<UsuarioCentro> list = new ArrayList<>();
    	List<Integer> list = new ArrayList<>();
    	List<Centro> listCentros = new ArrayList<Centro>();
        GenericDao genericDao = new GenericDao();
        try {
        	
        	String sql = "SELECT USUARIO_CENTRO_ID, CENTRO_ID, USUARIO_ID "
        			+ " FROM TB_USUARIO_CENTRO "
        			+ " WHERE USUARIO_ID = " +usuarioId+" ;";

        	PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet resultSet = smt.executeQuery();

             while (resultSet.next()) {
            	 
            	 int ids = resultSet.getInt("CENTRO_ID");
            	 
                 list.add(ids);
             }
                                      
             smt.close();
             resultSet.close();	
             
             Session session = HibernateUtil.getSessionFactory().getCurrentSession();
             session.beginTransaction();
             
             listCentros = session.createQuery( "select centro from Centro as centro where centro.centroId in (:centrosIds) order by centro.nome" ).setParameterList("centrosIds", list ).list();
            
             session.getTransaction().commit();
            
        } catch (HibernateException e) {
            System.out.println("*************************************************ERRO AO GERAR CONSULTA GET CENTROS BY USUARIO*************************************************");
            if (e.getCause() != null) {
                throw new Exception("Método: getCentrosByUsuario - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: getCentrosByUsuario - ERRO: " + e.getMessage());
            }
        }
        return listCentros;
    }

}
