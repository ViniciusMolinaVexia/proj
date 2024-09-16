package br.com.nextage.rmaweb.dao;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.entitybean.Workflow;
import br.com.nextage.rmaweb.utils.ConnectionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkflowDAO {

    private WorkflowDAO() {
    }

    private static WorkflowDAO workflowDAO = null;

    public static WorkflowDAO getInstance() {
        if (workflowDAO == null) {
            workflowDAO = new WorkflowDAO();
        }
        return workflowDAO;
    }

    public List<Integer> getAreasByWorkflowAreaUsuarioId(Integer usuarioId) {
        List<Integer> centroIds = new ArrayList<>();
        String sql = "select b.AREA_ID from TB_WORKFLOW_AREA a inner join TB_WORKFLOW b on a.WORKFLOW_ID = b.WORKFLOW_ID where a.USUARIO_ID = " + usuarioId;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            centroIds = session.createSQLQuery(sql).list();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
        }
        return centroIds;
    }

    public List<Integer> getAreasByWorkflowGerenteAreaUsuarioId(Integer usuarioId) {
        List<Integer> centroIds = new ArrayList<>();
        String sql = "select b.AREA_ID from TB_WORKFLOW_GERENTE_AREA a inner join TB_WORKFLOW b on a.WORKFLOW_ID = b.WORKFLOW_ID where a.USUARIO_ID = " + usuarioId;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            centroIds = session.createSQLQuery(sql).list();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
        }
        return centroIds;
    }

    public List<Integer> getAreasByWorkflowCustoUsuarioId(Integer usuarioId) {
        List<Integer> centroIds = new ArrayList<>();
        String sql = "select b.AREA_ID from TB_WORKFLOW_CUSTO a inner join TB_WORKFLOW b on a.WORKFLOW_ID = b.WORKFLOW_ID where a.USUARIO_ID = " + usuarioId;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            centroIds = session.createSQLQuery(sql).list();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
        }
        return centroIds;
    }

    public List<Integer> getAreasByWorkflowEmergencialUsuarioId(Integer usuarioId) {
        List<Integer> centroIds = new ArrayList<>();
        String sql = "select b.AREA_ID from TB_WORKFLOW_EMERGENCIAL a inner join TB_WORKFLOW b on a.WORKFLOW_ID = b.WORKFLOW_ID where a.USUARIO_ID = " + usuarioId;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            centroIds = session.createSQLQuery(sql).list();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
        }
        return centroIds;
    }

    public List<Integer> getCentrosByWorkflowAreaUsuarioId(Integer usuarioId) {
        List<Integer> centroIds = new ArrayList<>();
        String sql = "select b.CENTRO_ID from TB_WORKFLOW_AREA a inner join TB_WORKFLOW b on a.WORKFLOW_ID = b.WORKFLOW_ID where a.USUARIO_ID = " + usuarioId;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            centroIds = session.createSQLQuery(sql).list();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
        }
        return centroIds;
    }

    public List<Integer> getCentrosByWorkflowGerenteAreaUsuarioId(Integer usuarioId) {
        List<Integer> centroIds = new ArrayList<>();
        String sql = "select b.CENTRO_ID from TB_WORKFLOW_GERENTE_AREA a inner join TB_WORKFLOW b on a.WORKFLOW_ID = b.WORKFLOW_ID where a.USUARIO_ID = " + usuarioId;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            centroIds = session.createSQLQuery(sql).list();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
        }
        return centroIds;
    }

    public List<Integer> getCentrosByWorkflowCustoUsuarioId(Integer usuarioId) {
        List<Integer> centroIds = new ArrayList<>();
        String sql = "select b.CENTRO_ID from TB_WORKFLOW_CUSTO a inner join TB_WORKFLOW b on a.WORKFLOW_ID = b.WORKFLOW_ID where a.USUARIO_ID = " + usuarioId;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            centroIds = session.createSQLQuery(sql).list();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
        }
        return centroIds;
    }

    public List<Integer> getCentrosByWorkflowEmergencialUsuarioId(Integer usuarioId) {
        List<Integer> centroIds = new ArrayList<>();
        String sql = "select b.CENTRO_ID from TB_WORKFLOW_EMERGENCIAL a inner join TB_WORKFLOW b on a.WORKFLOW_ID = b.WORKFLOW_ID where a.USUARIO_ID = " + usuarioId;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            centroIds = session.createSQLQuery(sql).list();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
        }
        return centroIds;
    }

    public List<Workflow> getAll() throws Exception {
        List<Workflow> workflows = new ArrayList<Workflow>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            workflows = session.getNamedQuery("Workflow.getAll").list();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: getAll - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: getAll - ERRO: " + e.getMessage());
            }
        }
        return workflows;
    }

    public Workflow getWorkflowPorId(Integer workflowId) throws Exception {
        Workflow w = new Workflow();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            w = (Workflow) session.getNamedQuery("Workflow.getById").setParameter("workflowId", workflowId).uniqueResult();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: getAll - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: getAll - ERRO: " + e.getMessage());
            }
        }
        return w;
    }

    public Workflow getWorkflowPorAreaIdCentroId(Integer areaId, Integer centroId) throws Exception {
        Workflow w = new Workflow();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            w = (Workflow) session.getNamedQuery("Workflow.getByAreaIdCentroId")
                    .setParameter("areaId", areaId)
                    .setParameter("centroId", centroId)
                    .uniqueResult();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: getAll - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: getAll - ERRO: " + e.getMessage());
            }
        }
        return w;
    }

    public Workflow getWorkflowPorCentroId(Integer centroId) throws Exception {
        Workflow w = new Workflow();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            w = (Workflow) session.getNamedQuery("Workflow.getByCentroId").setParameter("centroId", centroId).uniqueResult();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: getAll - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: getAll - ERRO: " + e.getMessage());
            }
        }
        return w;
    }

    public String saveOrUpdate(Workflow workflow) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        Object objSalvo = new Object();
        try {
            trx = session.beginTransaction();

            if (workflow.getWorkflowId() == null) {
                objSalvo = session.save(workflow);
            } else {
                session.update(workflow);
                objSalvo = workflow;
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

    public void excluir(Workflow workflow) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            session.delete(workflow);
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

    public List<String> getPerfilAprovador(Usuario usuario) {

        List<String> perfis = new ArrayList<>();

        String sql = "SELECT B.USUARIO_ID, P.NOME FROM TB_WORKFLOW AS A INNER JOIN TB_WORKFLOW_CUSTO B\n " +
                "ON A.WORKFLOW_ID = B.WORKFLOW_ID\n " +
                "INNER JOIN TB_USUARIO U\n " +
                "ON U.USUARIO_ID = B.USUARIO_ID\n " +
                "INNER JOIN TB_PERFIL P\n " +
                "ON P.PERFIL_ID = U.PERFIL_ID\n " +
                "WHERE A.AREA_ID = ? AND A.CENTRO_ID = ?";
        try {

            PreparedStatement smt = new ConnectionFactory().getConnection().prepareStatement(sql);
            smt.setInt(1, usuario.getArea().getAreaId());
            smt.setInt(2, usuario.getCentro().getCentroId());

            ResultSet resultSet = smt.executeQuery();

            while (resultSet.next()) {
                String perfil = resultSet.getString("NOME");
                perfis.add(perfil);
            }

            smt.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return perfis;
    }

}
