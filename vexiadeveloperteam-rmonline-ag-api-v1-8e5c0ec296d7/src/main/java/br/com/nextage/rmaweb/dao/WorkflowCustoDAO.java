package br.com.nextage.rmaweb.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.entitybean.Workflow;
import br.com.nextage.rmaweb.entitybean.WorkflowCusto;
import br.com.nextage.rmaweb.entitybean.WorkflowEmergencial;

public class WorkflowCustoDAO {
	
	private WorkflowCustoDAO() {}
	private static WorkflowCustoDAO workflowCustoDAO=null;
	public static WorkflowCustoDAO getInstance() {
		if(workflowCustoDAO==null) {
			workflowCustoDAO = new WorkflowCustoDAO();
		}
		return workflowCustoDAO;
	}
	
	public List<WorkflowCusto> getWorkflowCustos(Workflow workflow){
		List<WorkflowCusto> wcs = new ArrayList<WorkflowCusto>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            wcs = session.getNamedQuery("WorkflowCusto.getByWorkflowId").setParameter("workflowId", workflow.getWorkflowId()).list();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            e.printStackTrace();
        }
        return wcs;
	}
	
	public List<Usuario> getUsuariosWorkflow(Workflow workflow){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            
            List<WorkflowCusto> wes = session.getNamedQuery("WorkflowCusto.getByWorkflowId").setParameter("workflowId", workflow.getWorkflowId()).list();
            for(WorkflowCusto we : wes) {
            	usuarios.add(we.getUsuario());
            }
            
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            e.printStackTrace();
        }
        return usuarios;
	}

    public Long getCountWorkflowCustos(Workflow workflow) {
        Long count = 0L;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            count = (Long) session.getNamedQuery("WorkflowCusto.getCountByWorkflowId").setParameter("workflowId", workflow.getWorkflowId()).uniqueResult();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            e.printStackTrace();
        }
        return count;
    }
	
	public String save(WorkflowCusto workflowCusto) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        Object objSalvo = new Object();
        try {
            trx = session.beginTransaction();
            session.save(workflowCusto);
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
	
	public void excluirPorWorkflow(Workflow workflow) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            session.getNamedQuery("WorkflowCusto.deleteByWorkflowId").setParameter("workflowId", workflow.getWorkflowId()).executeUpdate();
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
