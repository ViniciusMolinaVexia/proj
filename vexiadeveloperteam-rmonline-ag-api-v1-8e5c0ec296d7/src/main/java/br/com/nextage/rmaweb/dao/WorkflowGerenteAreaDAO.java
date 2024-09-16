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
import br.com.nextage.rmaweb.entitybean.WorkflowGerenteArea;

public class WorkflowGerenteAreaDAO {

    private WorkflowGerenteAreaDAO() {
    }

    private static WorkflowGerenteAreaDAO workflowGerenteAreaDAO = null;

    public static WorkflowGerenteAreaDAO getInstance() {
        if (workflowGerenteAreaDAO == null) {
            workflowGerenteAreaDAO = new WorkflowGerenteAreaDAO();
        }
        return workflowGerenteAreaDAO;
    }

    public List<WorkflowGerenteArea> getWorkflowGerenteAreas(Workflow workflow) {
        List<WorkflowGerenteArea> wgas = new ArrayList<WorkflowGerenteArea>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            wgas = session.getNamedQuery("WorkflowGerenteArea.getByWorkflowId")
                    .setParameter("workflowId", workflow.getWorkflowId()).list();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            e.printStackTrace();
        }
        return wgas;
    }

    public List<Usuario> getUsuariosWorkflow(Workflow workflow) {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();

            List<WorkflowGerenteArea> was = session.getNamedQuery("WorkflowGerenteArea.getByWorkflowId")
                    .setParameter("workflowId", workflow.getWorkflowId()).list();
            for (WorkflowGerenteArea wa : was) {
                usuarios.add(wa.getUsuario());
            }

            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            e.printStackTrace();
        }
        return usuarios;
    }

    public String save(WorkflowGerenteArea workflowGerenteArea) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        Object objSalvo = new Object();
        try {
            trx = session.beginTransaction();
            session.save(workflowGerenteArea);
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
            session.getNamedQuery("WorkflowGerenteArea.deleteByWorkflowId")
                    .setParameter("workflowId", workflow.getWorkflowId()).executeUpdate();
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

    public WorkflowGerenteArea getWokflowGerenteAreaByUsuario(Integer usuarioId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        WorkflowGerenteArea workflowGerenteArea = null;
        List<WorkflowGerenteArea> list = null;
        try {
            trx = session.beginTransaction();

            list = session.getNamedQuery("WorkflowGerenteArea.getByWorkflowIdUsuario")
                    .setParameter("usuarioId", usuarioId).list();
            workflowGerenteArea = list.get(0);
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            e.printStackTrace();
        }

        return workflowGerenteArea;
    }

    public Long getCountWorkflowGerenteAreas(Workflow workflow) {
        Long count = 0L;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();

            count = (Long) session.getNamedQuery("WorkflowGerenteArea.getCountByWorkflowId").setParameter("workflowId", workflow.getWorkflowId()).uniqueResult();

            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            e.printStackTrace();
        }
        return count;
    }
}
