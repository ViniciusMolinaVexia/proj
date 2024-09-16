package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.utils.ConnectionFactory;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibm.icu.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Daniel H. Parisotto
 * @date 02/10/2015
 */
public class RmServicoStatusService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    @Context
    HttpServletRequest request;

    public RmServicoStatusService() {
    }

    public RmServicoStatusService(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * Gera um status para um rmServico
     * <p/>
     * <br><br>
     * <b><font color='red'> ATENCAO: </font>
     * E necessario passar o genericDao com transaction aberta para persistir o
     * status, caso nao seja passado o genericDao, sera apenas retornado o
     * objeto com as informacoes, porem sem persistir, isso foi necessario para
     * a aprovacao de requisicao onde e gerado muitos status de uma vez e e
     * necessario listar os status antigos para serem copiados para novos
     * rmMateriais</b>
     *
     * @param rmServico   - rmServico que sera gerado o status
     * @param codigoStatus - Codigo do status que sera gerado
     * @param genericDao   - genericDao utilizado na transaction
     * @param data         - data do status, e passado uma data ao invez de fazer um new
     *                     Date(), pois caso tenha mais de um servico por requisicao, os status
     *                     irao ficar com data diferente
     * @param observacao   - Observacao do status de cancelamento
     * @param usuario
     * @return
     * @throws java.lang.Exception
     */
    public RmServicoStatus gerarStatus(RmServico rmServico, String codigoStatus, GenericDao<?> genericDao,
                                        Date data, String observacao, String usuario) throws Exception {

        RmServicoStatus servicoStatus = new RmServicoStatus();
        Status status = listarStatus(codigoStatus);
        if (status == null) {
            System.out.println("STATUS NAO CADASTRADO: " + codigoStatus);

            throw new Exception();
        }

        servicoStatus.setRmServico(rmServico);
        servicoStatus.setStatus(status);
        if (usuario != null) {
            servicoStatus.setUsuario(usuario);
        } else {
            servicoStatus.setUsuario(LoginService.getUsuarioLogado(request).getNome());
        }
        data = new Date();
        servicoStatus.setHistorico(Boolean.FALSE);
        servicoStatus.setDataHoraStatus(data);
        servicoStatus.setObservacao(observacao);

        if (genericDao != null) {
            genericDao.persistWithCurrentTransaction(servicoStatus);
        } else {
            //Caso nao tenha sido passado um genericDao, significa que no momento
            //nao e possivel persistir um status pois ira travar a transacao.
            //Este caso ocorre ao decidir o fluxo dos materiais na aprovacao,
            //onde e gerado varios status para os materiais e na mesma transacao
            //e feita a listagem de status antigos
        }

        return servicoStatus;
    }
    
    
    public RmServicoStatus gerarStatusServico(RmServico rmServico, String codigoStatus, GenericDao<?> genericDao,
                                        Date data, String observacao, String usuario) throws Exception {

        RmServicoStatus servicoStatus = new RmServicoStatus();
        Status status = listarStatus(codigoStatus);
        if (status == null) {
            System.out.println("STATUS NAO CADASTRADO: " + codigoStatus);

            throw new Exception();
        }

        servicoStatus.setRmServico(rmServico);
        servicoStatus.setStatus(status);
        if (usuario != null) {
            servicoStatus.setUsuario(usuario);
        } else {
            servicoStatus.setUsuario(LoginService.getUsuarioLogado(request).getNome());
        }
        servicoStatus.setHistorico(Boolean.FALSE);
        servicoStatus.setDataHoraStatus(data);
        servicoStatus.setObservacao(observacao);

        if (genericDao != null) {
            genericDao.persistWithCurrentTransaction(servicoStatus);
        } else {
            //Caso nao tenha sido passado um genericDao, significa que no momento
            //nao e possivel persistir um status pois ira travar a transacao.
            //Este caso ocorre ao decidir o fluxo dos materiais na aprovacao,
            //onde e gerado varios status para os materiais e na mesma transacao
            //e feita a listagem de status antigos
        }

        return servicoStatus;
    }
    


    
    /**
     * Seta data no status para informar que a requisicao ja passou desse status
     *
     * @param servicoStatus
     * @param genericDao
     * @param data
     */
    public void finalizarStatus(RmServicoStatus servicoStatus, GenericDao<?> genericDao, Date data) throws Exception {
//        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmServicoStatus.DATA_HORA_STATUS));
            servicoStatus.setDataHoraStatus(data);

            genericDao.updateWithCurrentTransaction(servicoStatus, propriedades);
//        } catch (Exception e) {
//            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
//        }
    }
    
    
    public void finalizarStatusServico(RmServicoStatus servicoStatus, GenericDao<?> genericDao, Date data) throws Exception {
//      try {
          List<Propriedade> propriedades = new ArrayList<>();
          propriedades.add(new Propriedade(RmServicoStatus.DATA_HORA_STATUS));
          servicoStatus.setDataHoraStatus(data);

          genericDao.updateWithCurrentTransaction(servicoStatus, propriedades);
//      } catch (Exception e) {
//          logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
//      }
  }
   

    /**
     * Lista o objeto Status com o codigo informado
     *
     * @param codigoStatus
     * @return
     */
    private Status listarStatus(String codigoStatus) {
        Status status = new Status();

        GenericDao<Status> genericDao = new GenericDao();

        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Status.ID));
            propriedades.add(new Propriedade(Status.CODIGO));
            propriedades.add(new Propriedade(Status.NOME));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Status.CODIGO, codigoStatus, Filtro.EQUAL));

            status = genericDao.selectUnique(propriedades, Status.class, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return status;
    }

    /**
     * Lista todos os status do rmServico
     *
     * @param rmServico
     * @param listarHistorico
     * @return
     */
    public List<RmServicoStatus> listarServicoStatus(RmServico rmServico, Boolean listarHistorico) {
        List<RmServicoStatus> lista = new ArrayList<>();

        GenericDao<RmServicoStatus> genericDao = new GenericDao();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

        try {
            String aliasServico = NxCriterion.montaAlias(RmServicoStatus.ALIAS_CLASSE, RmServicoStatus.RM_SERVICO_ID);
            String aliasStatus = NxCriterion.montaAlias(RmServicoStatus.ALIAS_CLASSE, RmServicoStatus.STATUS_ID);
            String aliasRm = NxCriterion.montaAlias(RmServicoStatus.ALIAS_CLASSE, RmServicoStatus.RM_SERVICO_ID, RmServico.RM);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmServicoStatus.ID));
            propriedades.add(new Propriedade(RmServicoStatus.DATA_HORA_STATUS));
            propriedades.add(new Propriedade(RmServicoStatus.OBSERVACAO));
            propriedades.add(new Propriedade(RmServicoStatus.USUARIO));

            propriedades.add(new Propriedade(RmServico.RM_SERVICO_ID, RmServico.class, aliasServico));

            propriedades.add(new Propriedade(Status.ID, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.NOME, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.CODIGO, Status.class, aliasStatus));

            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.REPROVADO_POR, Rm.class, aliasRm));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmServicoStatus.RM_SERVICO_ID, rmServico, Filtro.EQUAL));

            if (!listarHistorico) {
                NxCriterion nxCriterionOr = NxCriterion.montaRestriction(new Filtro(RmServicoStatus.IS_HISTORICO, null, Filtro.IS_NULL));
                nxCriterionOr = NxCriterion.or(nxCriterionOr, NxCriterion.montaRestriction(new Filtro(RmServicoStatus.IS_HISTORICO, Boolean.FALSE, Filtro.EQUAL)));

                nxCriterion = NxCriterion.and(nxCriterion, nxCriterionOr);
            }

            lista = genericDao.listarByFilter(propriedades, null, RmServicoStatus.class, Constantes.NO_LIMIT, nxCriterion);


        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return lista;
    }
    
    /**
     * Lista todos os status dos rmMateriais
     *
     * @param listaRmServico
     * @param listarHistorico
     * @return
     */
    public List<RmServicoStatus> listarServicoStatus(List<RmServico> listaRmServico, Boolean listarHistorico) {
        List<RmServicoStatus> lista = new ArrayList<>();

        GenericDao<RmServicoStatus> genericDao = new GenericDao();

        try {
            String aliasServico = NxCriterion.montaAlias(RmServicoStatus.ALIAS_CLASSE, RmServicoStatus.RM_SERVICO_ID);
            String aliasStatus = NxCriterion.montaAlias(RmServicoStatus.ALIAS_CLASSE, RmServicoStatus.STATUS_ID);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmServicoStatus.ID));
            propriedades.add(new Propriedade(RmServicoStatus.DATA_HORA_STATUS));
            propriedades.add(new Propriedade(RmServicoStatus.OBSERVACAO));
            propriedades.add(new Propriedade(RmServicoStatus.USUARIO));

            propriedades.add(new Propriedade(RmServico.RM_SERVICO_ID, RmServico.class, aliasServico));

            propriedades.add(new Propriedade(Status.ID, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.NOME, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.CODIGO, Status.class, aliasStatus));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmServicoStatus.RM_SERVICO_ID, listaRmServico, Filtro.IN));

            if (!listarHistorico) {
                NxCriterion nxCriterionOr = NxCriterion.montaRestriction(new Filtro(RmServicoStatus.IS_HISTORICO, null, Filtro.IS_NULL));
                nxCriterionOr = NxCriterion.or(nxCriterionOr, NxCriterion.montaRestriction(new Filtro(RmServicoStatus.IS_HISTORICO, Boolean.FALSE, Filtro.EQUAL)));

                nxCriterion = NxCriterion.and(nxCriterion, nxCriterionOr);
            }

            lista = genericDao.listarByFilter(propriedades, null, RmServicoStatus.class, Constantes.NO_LIMIT, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return lista;
    }

    /**
     * Lista todos os status dos rmMateriais
     *
     * @param listaRmServico
     * @return
     */
    public List<RmServicoStatus> listarServicoStatus(List<VwRmServico> listaRmServico) {
        List<RmServicoStatus> lista = new ArrayList<>();

        List<RmServico> listaRmMateriais = new ArrayList<>();

        for (VwRmServico vw : listaRmServico) {
            listaRmMateriais.add(vw.getRmServico());
        }

        GenericDao<RmServicoStatus> genericDao = new GenericDao();

        try {
            String aliasServico = NxCriterion.montaAlias(RmServicoStatus.ALIAS_CLASSE, RmServicoStatus.RM_SERVICO_ID);
            String aliasStatus = NxCriterion.montaAlias(RmServicoStatus.ALIAS_CLASSE, RmServicoStatus.STATUS_ID);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmServicoStatus.ID));
            propriedades.add(new Propriedade(RmServicoStatus.DATA_HORA_STATUS));
            propriedades.add(new Propriedade(RmServicoStatus.OBSERVACAO));
            propriedades.add(new Propriedade(RmServicoStatus.USUARIO));

            propriedades.add(new Propriedade(RmServico.RM_SERVICO_ID, RmServico.class, aliasServico));

            propriedades.add(new Propriedade(Status.ID, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.NOME, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.CODIGO, Status.class, aliasStatus));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmServicoStatus.RM_SERVICO_ID, listaRmMateriais, Filtro.IN));

            NxCriterion nxCriterionOr = NxCriterion.montaRestriction(new Filtro(RmServicoStatus.IS_HISTORICO, null, Filtro.IS_NULL));
            nxCriterionOr = NxCriterion.or(nxCriterionOr, NxCriterion.montaRestriction(new Filtro(RmServicoStatus.IS_HISTORICO, Boolean.FALSE, Filtro.EQUAL)));

            nxCriterion = NxCriterion.and(nxCriterion, nxCriterionOr);

            lista = genericDao.listarByFilter(propriedades, null, RmServicoStatus.class, Constantes.NO_LIMIT, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return lista;
    }

    /**
     * Lista o status atual do rmServico
     *
     * @param listaRmServico
     * @return
     */
    public List<RmServicoStatus> listarStatusAtual(List<RmServico> listaRmServico) {
        GenericDao<VwRmServico> genericDao = new GenericDao<>();
        List<RmServicoStatus> lista = new ArrayList<>();
        List<VwRmServico> listaVwRmServico = new ArrayList<>();
        try {
            String aliasRmServicoStatus = NxCriterion.montaAlias(VwRmServico.ALIAS_CLASSE, VwRmServico.RM_SERVICO_STATUS_ID);
            String aliasRmServico = NxCriterion.montaAlias(VwRmServico.ALIAS_CLASSE, VwRmServico.RM_SERVICO_ID);
            String aliasRm = NxCriterion.montaAlias(VwRmServico.ALIAS_CLASSE, VwRmServico.RM_SERVICO_ID, RmServico.RM);
            String aliasStatus = NxCriterion.montaAlias(VwRmServico.ALIAS_CLASSE, VwRmServico.RM_SERVICO_STATUS_ID, RmServicoStatus.STATUS_ID);
            String aliasStatusRmServico = NxCriterion.montaAlias(VwRmServico.ALIAS_CLASSE, VwRmServico.RM_SERVICO_STATUS_ID, RmServicoStatus.RM_SERVICO_ID);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(VwRmServico.ID));

            propriedades.add(new Propriedade(RmServico.RM_SERVICO_ID, RmServico.class, aliasRmServico));

            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));

            propriedades.add(new Propriedade(RmServicoStatus.ID, RmServicoStatus.class, aliasRmServicoStatus));
            propriedades.add(new Propriedade(RmServicoStatus.USUARIO, RmServicoStatus.class, aliasRmServicoStatus));

            propriedades.add(new Propriedade(Status.ID, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.CODIGO, Status.class, aliasStatus));

            propriedades.add(new Propriedade(RmServico.RM_SERVICO_ID, RmServico.class, aliasStatusRmServico));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(VwRmServico.RM_SERVICO_ID, listaRmServico, Filtro.IN));
            listaVwRmServico = genericDao.listarByFilter(propriedades, null, VwRmServico.class, Constantes.NO_LIMIT, nxCriterion);

            for (VwRmServico vwRmServico : listaVwRmServico) {
                lista.add(vwRmServico.getRmServicoStatus());
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return lista;
    }
    
    

    
    /************************** BUSCA AS INFORMAÇÔES DE STATUS *********************/
    
    
    public List<RmServicoStatus> listarStatusAtualServico(List<RmServico> listaRmS) {
        GenericDao<VwRmServico> genericDao = new GenericDao<>();
        List<RmServicoStatus> lista = new ArrayList<>();
        List<VwRmServico> listaVwRmServico = new ArrayList<>();
        try {
        	
        String sql = "select TOP(1) MS.ID, MS.STATUS_ID, MS.RM_SERVICO_ID, MS.RM_SERVICO_ID, MS.DATA_HORA_STATUS, MS.USUARIO, MS.IS_HISTORICO, MS.OBSERVACAO FROM TB_RM_SERVICO_STATUS MS WHERE RM_SERVICO_ID = (?) ORDER BY MS.STATUS_ID DESC;";
        	
       	 sql = sql.replace("(?)", listaRmS.get(0).getRmServicoId().toString());
            PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);
            ResultSet resultSet = smt.executeQuery();

            while (resultSet.next()) {
            	VwRmServico vwRmServico = new VwRmServico();
            	RmServicoStatus rmMatS = new RmServicoStatus();
            	rmMatS.setId(resultSet.getInt("ID"));
            	rmMatS.setStatus(getStatus(resultSet.getInt("STATUS_ID")));
            	rmMatS.setRmServico(listaRmS.get(0));
                rmMatS.setObservacao(resultSet.getString("OBSERVACAO"));
                rmMatS.setUsuario(resultSet.getString("USUARIO"));
                rmMatS.setHistorico(resultSet.getBoolean("IS_HISTORICO"));
                rmMatS.setDataHoraStatus(resultSet.getDate("DATA_HORA_STATUS"));
                lista.add(rmMatS);
            }
            
            smt.close();
            resultSet.close();	

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return lista;
    }
    
    
    public List<RmServicoStatus> listarStatusAtualServico(RmServico Rms) {
        GenericDao<VwRmServico> genericDao = new GenericDao<>();
        List<RmServicoStatus> lista = new ArrayList<>();
        List<VwRmServico> listaVwRmServico = new ArrayList<>();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
        try {
        	
        String sql = "select MS.ID, MS.STATUS_ID, MS.RM_SERVICO_ID, MS.RM_SERVICO_ID, MS.DATA_HORA_STATUS, MS.USUARIO, MS.IS_HISTORICO, MS.OBSERVACAO FROM TB_RM_SERVICO_STATUS MS WHERE RM_SERVICO_ID = (?);";
        	
       	 sql = sql.replace("(?)", Rms.getRmServicoId().toString());
            PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);
            ResultSet resultSet = smt.executeQuery();

            String d1 = ""; 
            String d2 = ""; 

            while (resultSet.next()) {
            	VwRmServico vwRmServico = new VwRmServico();
            	RmServicoStatus rmMatS = new RmServicoStatus();
            	rmMatS.setId(resultSet.getInt("ID"));
            	rmMatS.setStatus(getStatus(resultSet.getInt("STATUS_ID")));
            	rmMatS.setRmServico(Rms);
                rmMatS.setObservacao(resultSet.getString("OBSERVACAO"));
                rmMatS.setUsuario(resultSet.getString("USUARIO"));
                rmMatS.setHistorico(resultSet.getBoolean("IS_HISTORICO"));
                rmMatS.setDataHoraStatus(resultSet.getDate("DATA_HORA_STATUS"));
                if(rmMatS.getDataHoraStatus() != null) {
                	d1 = (resultSet.getString("DATA_HORA_STATUS"));
                    d2 = d1.substring(8, 10) + "/" + d1.substring(5, 7) + "/" + d1.substring(0, 4) + " " + d1.substring(11, 13) + ":" + d1.substring(14, 16); 
                } else {
                	d2 = "";
                }
                rmMatS.setStDataHoraStatus(d2);
                lista.add(rmMatS);
            }
            
            smt.close();
            resultSet.close();	

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return lista;
    }
    
    
    public Status getStatus(Integer id) {
    	Status status = new Status();
        try {
        	
        String sql = "SELECT S.ID, S.CODIGO, S.NOME FROM TB_STATUS S WHERE ID = (?);";
        	
        String filtro = id.toString();
       	 sql = sql.replace("(?)", filtro);
            PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);
            ResultSet resultSet = smt.executeQuery();

            while (resultSet.next()) {
            	status.setId(resultSet.getInt("ID"));
            	status.setCodigo(resultSet.getString("CODIGO"));
            	status.setNome(resultSet.getString("NOME"));	
            }

            smt.close();
            resultSet.close();	

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return status;
    }
    
    
    public RmServicoStatus listarStatusAtualServico(List<RmServicoStatus> listaServicoStatus, RmServico rmServico) throws Exception {
        for (RmServicoStatus rms : listaServicoStatus) {
            if (rms.getRmServico().equals(rmServico)) {
                return rms;
            }
        }
        return null;
    }
    
    
    
    /**************************************** END ********************************************/
    
    
    /**
     * Lista o status atual do rmServico
     *
     * @param rmServico
     * @return
     */
    public RmServicoStatus listarStatusAtual(RmServico rmServico) {
        GenericDao<VwRmServico> genericDao = new GenericDao<>();
        VwRmServico vwRmServico = null;

        try {
            String aliasRmServicoStatus = NxCriterion.montaAlias(VwRmServico.ALIAS_CLASSE, VwRmServico.RM_SERVICO_STATUS_ID);
            String aliasRmServico = NxCriterion.montaAlias(VwRmServico.ALIAS_CLASSE, VwRmServico.RM_SERVICO_ID);
            String aliasRm = NxCriterion.montaAlias(VwRmServico.ALIAS_CLASSE, VwRmServico.RM_SERVICO_ID, RmServico.RM);
            String aliasStatus = NxCriterion.montaAlias(VwRmServico.ALIAS_CLASSE, VwRmServico.RM_SERVICO_STATUS_ID, RmServicoStatus.STATUS_ID);
            String aliasStatusRmServico = NxCriterion.montaAlias(VwRmServico.ALIAS_CLASSE, VwRmServico.RM_SERVICO_STATUS_ID, RmServicoStatus.RM_SERVICO_ID);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(VwRmServico.ID));

            propriedades.add(new Propriedade(RmServico.RM_SERVICO_ID, RmServico.class, aliasRmServico));

            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));

            propriedades.add(new Propriedade(RmServicoStatus.ID, RmServicoStatus.class, aliasRmServicoStatus));
            propriedades.add(new Propriedade(RmServicoStatus.USUARIO, RmServicoStatus.class, aliasRmServicoStatus));

            propriedades.add(new Propriedade(Status.ID, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.CODIGO, Status.class, aliasStatus));

            propriedades.add(new Propriedade(RmServico.RM_SERVICO_ID, RmServico.class, aliasStatusRmServico));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(VwRmServico.RM_SERVICO_ID, rmServico, Filtro.EQUAL));
            vwRmServico = genericDao.selectUnique(propriedades, VwRmServico.class, nxCriterion);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return vwRmServico.getRmServicoStatus();
    }

    /**
     * Procura o status do servico em uma lista de status
     *
     * @param listaServicoStatus
     * @param rmServico
     * @return
     */
    public RmServicoStatus listarStatusAtual(List<RmServicoStatus> listaServicoStatus, RmServico rmServico) throws Exception {
        for (RmServicoStatus rms : listaServicoStatus) {
            if (rms.getRmServico().equals(rmServico)) {
                return rms;
            }
        }
        return null;
    }
    

    /**
     * Seleciona todos os status do rmServico
     *
     * @param listaServicoStatus
     * @param rmServico
     * @return
     */
    public List<RmServicoStatus> selecionarStatusServico(List<RmServicoStatus> listaServicoStatus, final RmServico rmServico) {
        return (List<RmServicoStatus>) CollectionUtils.select(listaServicoStatus, new Predicate() {

            @Override
            public boolean evaluate(Object object) {
                RmServicoStatus rmServicoStatus = (RmServicoStatus) object;

                return rmServicoStatus.getRmServico().equals(rmServico);
            }
        });
    }
}
