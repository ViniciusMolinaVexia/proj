package br.com.nextage.rmaweb.dao;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ibm.icu.text.SimpleDateFormat;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.FornecedorServico;
import br.com.nextage.rmaweb.entitybean.RmServico;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.integracao.RmServicoStatusService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.Util;

public class RmServicoDAO {
	
	private RmServicoDAO() {}
	private static RmServicoDAO rmServicoDAO = null;
	public static RmServicoDAO getInstance() {
		if(rmServicoDAO==null) {
			rmServicoDAO = new RmServicoDAO();
		}
		return rmServicoDAO;
	}
	
	public void saveOrUpdate(RmServico rmServico) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            
            //Insere um novo registro
            if(rmServico.getRmServicoId()==null){
            	System.out.println("---------------------------------" + rmServico.getRmServicoId() + "---------------------------------" );
            	rmServico.setOrdem(0);
            	rmServico.setQuantidade(0.0);
            	session.save(rmServico);
            }else {
            	//Atualiza um novo servico
            	System.out.println("---------------------------------" + rmServico.getRmServicoId() + "---------------------------------" );
            	session.update(rmServico);
            }
            
            //Apaga registros de fornecedores
            session.getNamedQuery("fornecedorServico.deleteByRmServico").setParameter("rmServicoId", rmServico.getRmServicoId()).executeUpdate();
            
            if(rmServico.getFornecedores()!=null) {
            	for(FornecedorServico fs : rmServico.getFornecedores()) {
            		fs.setRmServico(rmServico);
            		session.save(fs);
            	}
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
    } 
	
	public void updateEnviadoSap(RmServico rmServico) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            
            //Insere um novo registro
            if(rmServico.getRmServicoId()==null){
            	System.out.println("---------------------------------" + rmServico.getRmServicoId() + "---------------------------------" );
            	rmServico.setOrdem(0);
            	rmServico.setQuantidade(0.0);
            	session.save(rmServico);
            }else {
            	//Atualiza um novo servico
            	System.out.println("---------------------------------" + rmServico.getRmServicoId() + "---------------------------------" );
            	session.update(rmServico);
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
    } 

}
