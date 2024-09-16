package br.com.nextage.rmaweb.dao;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.entitybean.Workflow;
import br.com.nextage.rmaweb.entitybean.WorkflowEmergencial;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class WorkflowEmergencialDAO {

    private WorkflowEmergencialDAO() {
    }

    private static WorkflowEmergencialDAO workflowEmergencialDAO = null;

    public static WorkflowEmergencialDAO getInstance() {
        if (workflowEmergencialDAO == null) {
            workflowEmergencialDAO = new WorkflowEmergencialDAO();
        }
        return workflowEmergencialDAO;
    }

    public List<WorkflowEmergencial> getWorkflowEmergenciais(Workflow workflow) {
        List<WorkflowEmergencial> wes = new ArrayList<WorkflowEmergencial>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            wes = session.getNamedQuery("WorkflowEmergencial.getByWorkflowId").setParameter("workflowId", workflow.getWorkflowId()).list();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            e.printStackTrace();
        }
        return wes;
    }

    public List<Usuario> getUsuariosWorkflow(Workflow workflow) {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();

            List<WorkflowEmergencial> wes = session.getNamedQuery("WorkflowEmergencial.getByWorkflowId").setParameter("workflowId", workflow.getWorkflowId()).list();
            for (WorkflowEmergencial we : wes) {
                usuarios.add(we.getUsuario());
            }

            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            e.printStackTrace();
        }
        return usuarios;
    }

    public String save(WorkflowEmergencial workflowEmergencial) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        Object objSalvo = new Object();
        try {
            trx = session.beginTransaction();
            session.save(workflowEmergencial);
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
            session.getNamedQuery("WorkflowEmergencial.deleteByWorkflowId").setParameter("workflowId", workflow.getWorkflowId()).executeUpdate();
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

    public WorkflowEmergencial getWorkflowByIdWorkFlow(Integer workflowId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        List<WorkflowEmergencial> wes = null;

        try {

            trx = session.beginTransaction();
            wes = session.getNamedQuery("WorkflowEmergencial.getByWorkflowId").setParameter("workflowId", workflowId).list();

            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            e.printStackTrace();
        }
        return wes.get(0);

    }

    public String update(WorkflowEmergencial emergencial) throws Exception {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        Object objSalvo = new Object();
        try {
            trx = session.beginTransaction();
            session.update(emergencial);
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


    public Long getCountWorkflowEmergenciais(Workflow workflow) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        Long count = 0L;

        try {

            trx = session.beginTransaction();
            count = (Long) session.getNamedQuery("WorkflowEmergencial.getCountByWorkflowId").setParameter("workflowId", workflow.getWorkflowId()).uniqueResult();

            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            e.printStackTrace();
        }
        return count;
    }
}
