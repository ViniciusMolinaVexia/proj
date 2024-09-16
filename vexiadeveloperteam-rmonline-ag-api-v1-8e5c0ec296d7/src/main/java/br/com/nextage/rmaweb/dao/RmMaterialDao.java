/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.dao;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.vo.AlmoxarifadoVo;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que permite acesso à persistencia
 *
 * @author Juliano A. Felipe
 */
public class RmMaterialDao {

    /**
     * Método que realiza a inserção de um novo registro.
     *
     * <br>
     * <br>
     * <b>Autor:</b> Juliano.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param obj - RmMaterial
     * @return objSalvo.toString - String
     * @throws Exception - Exception
     */
    public String persist(RmMaterial obj) throws Exception {
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
     * Método que realiza a atualização dos dados de um RmMaterial.
     * <br>
     * <br>
     * <b>Autor:</b> Juliano.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param obj - RmMaterial
     * @return objSalvo.toString - String
     * @throws Exception - Exception
     */
    public String update(RmMaterial obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        Object objSalvo = new Object();
        try {
            trx = session.beginTransaction();

            session.update(obj);

            objSalvo = obj.getRmMaterialId();

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
    
    public RmMaterial getById(Integer id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        RmMaterial rmMaterial = null;
        try {
            trx = session.beginTransaction();
            rmMaterial = (RmMaterial) session.getNamedQuery("rmMaterial.getById").setParameter("id", id).uniqueResult();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: update - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: update - ERRO: " + e.getMessage());
            }
        }
        return rmMaterial;
    }
    
    public List<AlmoxarifadoVo> getList(String sql) throws Exception {
    	List<AlmoxarifadoVo> ms = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            List<Object[]> result = session.createSQLQuery(sql).list();
            for(Object[] obj : result) {
            	AlmoxarifadoVo a = new AlmoxarifadoVo();
            	a.setCentro((obj[0]!=null)?(""+obj[0]):(""));
            	a.setNumero((obj[1]!=null)?(""+obj[1]):(""));
            	a.setNumeroSap((obj[2]!=null)?(""+obj[2]):(""));
            	a.setNome((obj[3]!=null)?(""+obj[3]):(""));
            	a.setTipoMaterial((obj[4]!=null)?(""+obj[4]):(""));
            	a.setQuantidadeSolicitada((obj[5]!=null)?(""+obj[5]):(""));
            	a.setRecebimentoSolcitado((obj[6]!=null)?(""+obj[6]):(""));
            	a.setDataAplicacao((obj[7]!=null)?(""+obj[7]):(""));
            	a.setRequisitante((obj[8]!=null)?(""+obj[8]):(""));
            	a.setCadastrante((obj[9]!=null)?(""+obj[9]):(""));
            	a.setFluxo((obj[10]!=null)?(""+obj[10]):(""));
            	a.setColetorCusto((obj[11]!=null)?(""+obj[11]):(""));
            	a.setDataAvaliacao((obj[12]!=null)?(""+obj[12]):(""));
            	a.setStatus((obj[13]!=null)?(""+obj[13]):(""));
            	a.setStatusItem((obj[14]!=null)?(""+obj[14]):(""));
            	a.setTipoRequisicao((obj[15]!=null)?(""+obj[15]):(""));
            	a.setObservacao((obj[16]!=null)?(""+obj[16]):(""));
            	
            	ms.add(a);
            }
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: getList - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: getList - ERRO: " + e.getMessage());
            }
        }
        return ms;
    }

    public void atualizarStatusRma(final String item, final String requisicao, final String status, final Integer codigoRma) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {

            final int STATUS_AGUARDANDO_COMPRA = 10;
            trx = session.beginTransaction();

            StringBuilder sql = new StringBuilder("UPDATE TB_RM_MATERIAL ");
            sql.append("SET ITEM_REQUISICAO_SAP =");
            sql.append(item);
            sql.append(", NUMERO_REQUISICAO_SAP='");
            sql.append(requisicao);
            sql.append("', STATUS='");
            sql.append(status);
            sql.append("' WHERE RM_MATERIAL_ID =");
            sql.append(codigoRma);

            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            int i = sqlQuery.executeUpdate();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: atualizarStatusRma - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: atualizarStatusRma - ERRO: " + e.getMessage());
            }
        }
    }

}
