package br.com.nextage.rmaweb.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.entitybean.Workflow;
import br.com.nextage.rmaweb.entitybean.WorkflowArea;
import br.com.nextage.rmaweb.entitybean.WorkflowEmergencial;

public class WorkflowAreaDAO {
	
	private WorkflowAreaDAO() {}
	private static WorkflowAreaDAO workflowAreaDAO = null;
	public static WorkflowAreaDAO getInstance() {
		if(workflowAreaDAO==null) {
			workflowAreaDAO = new WorkflowAreaDAO();
		}
		return workflowAreaDAO;
	}
	
	public List<WorkflowArea> getWorkflowAreas(Workflow workflow){
		List<WorkflowArea> was = new ArrayList<WorkflowArea>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            was = session.getNamedQuery("WorkflowArea.getByWorkflowId").setParameter("workflowId", workflow.getWorkflowId()).list();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            e.printStackTrace();
        }
        return was;
	}
	
	public List<Usuario> getUsuariosWorkflow(Workflow workflow){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            
            List<WorkflowArea> was = session.getNamedQuery("WorkflowArea.getByWorkflowId").setParameter("workflowId", workflow.getWorkflowId()).list();
            for(WorkflowArea wa : was) {
            	usuarios.add(wa.getUsuario());
            }
            
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            e.printStackTrace();
        }
        return usuarios;
	}

    public Long getCountWorkflowAreas(Workflow workflow){
        Long count = 0L;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            count = (Long) session.getNamedQuery("WorkflowArea.getCountByWorkflowId").setParameter("workflowId", workflow.getWorkflowId()).uniqueResult();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            e.printStackTrace();
        }
        return count;
    }
	
	public String save(WorkflowArea workflowArea) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        Object objSalvo = new Object();
        try {
            trx = session.beginTransaction();
            session.save(workflowArea);
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
            session.getNamedQuery("WorkflowArea.deleteByWorkflowId").setParameter("workflowId", workflow.getWorkflowId()).executeUpdate();
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
