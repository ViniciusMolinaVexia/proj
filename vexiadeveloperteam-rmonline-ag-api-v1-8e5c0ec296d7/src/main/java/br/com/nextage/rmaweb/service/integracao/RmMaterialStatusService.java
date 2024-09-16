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
public class RmMaterialStatusService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    @Context
    HttpServletRequest request;

    public RmMaterialStatusService() {
    }

    public RmMaterialStatusService(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * Gera um status para um rmMaterial
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
     * @param rmMaterial   - rmMaterial que sera gerado o status
     * @param codigoStatus - Codigo do status que sera gerado
     * @param genericDao   - genericDao utilizado na transaction
     * @param data         - data do status, e passado uma data ao invez de fazer um new
     *                     Date(), pois caso tenha mais de um material por requisicao, os status
     *                     irao ficar com data diferente
     * @param observacao   - Observacao do status de cancelamento
     * @param usuario
     * @return
     * @throws java.lang.Exception
     */
    public RmMaterialStatus gerarStatus(RmMaterial rmMaterial, String codigoStatus, GenericDao<?> genericDao,
                                        Date data, String observacao, String usuario) throws Exception {

        RmMaterialStatus materialStatus = new RmMaterialStatus();
        Status status = listarStatus(codigoStatus);
        if (status == null) {
            System.out.println("STATUS NAO CADASTRADO: " + codigoStatus);

            throw new Exception();
        }

        materialStatus.setRmMaterial(rmMaterial);
        materialStatus.setStatus(status);
        if (usuario != null) {
            materialStatus.setUsuario(usuario);
        } else {
            materialStatus.setUsuario(LoginService.getUsuarioLogado(request).getNome());
        }
        materialStatus.setHistorico(Boolean.FALSE);
        materialStatus.setDataHoraStatus(data);
        materialStatus.setObservacao(observacao);

        if (genericDao != null) {
            genericDao.persistWithCurrentTransaction(materialStatus);
        } else {
            //Caso nao tenha sido passado um genericDao, significa que no momento
            //nao e possivel persistir um status pois ira travar a transacao.
            //Este caso ocorre ao decidir o fluxo dos materiais na aprovacao,
            //onde e gerado varios status para os materiais e na mesma transacao
            //e feita a listagem de status antigos
        }

        return materialStatus;
    }
    
    
    public RmMaterialStatus gerarStatusServico(RmServico rmServico, String codigoStatus, GenericDao<?> genericDao,
                                        Date data, String observacao, String usuario) throws Exception {

        RmMaterialStatus materialStatus = new RmMaterialStatus();
        Status status = listarStatus(codigoStatus);
        if (status == null) {
            System.out.println("STATUS NAO CADASTRADO: " + codigoStatus);

            throw new Exception();
        }
        
        RmMaterial rmMat = new RmMaterial();
        rmMat.setRmMaterialId(13345);

        materialStatus.setRmMaterial(rmMat);
        materialStatus.setStatus(status);
        if (usuario != null) {
            materialStatus.setUsuario(usuario);
        } else {
            materialStatus.setUsuario(LoginService.getUsuarioLogado(request).getNome());
        }
        materialStatus.setHistorico(Boolean.FALSE);
        materialStatus.setDataHoraStatus(data);
        materialStatus.setObservacao(observacao);

        if (genericDao != null) {
            genericDao.persistWithCurrentTransaction(materialStatus);
        } else {
            //Caso nao tenha sido passado um genericDao, significa que no momento
            //nao e possivel persistir um status pois ira travar a transacao.
            //Este caso ocorre ao decidir o fluxo dos materiais na aprovacao,
            //onde e gerado varios status para os materiais e na mesma transacao
            //e feita a listagem de status antigos
        }

        return materialStatus;
    }
    


    
    /**
     * Seta data no status para informar que a requisicao ja passou desse status
     *
     * @param materialStatus
     * @param genericDao
     * @param data
     */
    public void finalizarStatus(RmMaterialStatus materialStatus, GenericDao<?> genericDao, Date data) throws Exception {
//        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialStatus.DATA_HORA_STATUS));
            materialStatus.setDataHoraStatus(data);

            genericDao.updateWithCurrentTransaction(materialStatus, propriedades);
//        } catch (Exception e) {
//            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
//        }
    }
    
    
    public void finalizarStatusServico(RmMaterialStatus materialStatus, GenericDao<?> genericDao, Date data) throws Exception {
//      try {
          List<Propriedade> propriedades = new ArrayList<>();
          propriedades.add(new Propriedade(RmMaterialStatus.DATA_HORA_STATUS));
          materialStatus.setDataHoraStatus(data);

          genericDao.updateWithCurrentTransaction(materialStatus, propriedades);
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
     * Lista todos os status do rmMaterial
     *
     * @param rmMaterial
     * @param listarHistorico
     * @return
     */
    public List<RmMaterialStatus> listarMaterialStatus(RmMaterial rmMaterial, Boolean listarHistorico) {
        List<RmMaterialStatus> lista = new ArrayList<>();

        GenericDao<RmMaterialStatus> genericDao = new GenericDao();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

        try {
            String aliasMaterial = NxCriterion.montaAlias(RmMaterialStatus.ALIAS_CLASSE, RmMaterialStatus.RM_MATERIAL_ID);
            String aliasStatus = NxCriterion.montaAlias(RmMaterialStatus.ALIAS_CLASSE, RmMaterialStatus.STATUS_ID);
            String aliasRm = NxCriterion.montaAlias(RmMaterialStatus.ALIAS_CLASSE, RmMaterialStatus.RM_MATERIAL_ID, RmMaterial.RM);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialStatus.ID));
            propriedades.add(new Propriedade(RmMaterialStatus.DATA_HORA_STATUS));
            propriedades.add(new Propriedade(RmMaterialStatus.OBSERVACAO));
            propriedades.add(new Propriedade(RmMaterialStatus.USUARIO));

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasMaterial));
            propriedades.add(new Propriedade(RmMaterial.DATA_PREVISTA_ENTREGA, RmMaterial.class, aliasMaterial));

            propriedades.add(new Propriedade(Status.ID, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.NOME, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.CODIGO, Status.class, aliasStatus));

            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.REPROVADO_POR, Rm.class, aliasRm));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterialStatus.RM_MATERIAL_ID, rmMaterial, Filtro.EQUAL));

            if (!listarHistorico) {
                NxCriterion nxCriterionOr = NxCriterion.montaRestriction(new Filtro(RmMaterialStatus.IS_HISTORICO, null, Filtro.IS_NULL));
                nxCriterionOr = NxCriterion.or(nxCriterionOr, NxCriterion.montaRestriction(new Filtro(RmMaterialStatus.IS_HISTORICO, Boolean.FALSE, Filtro.EQUAL)));

                nxCriterion = NxCriterion.and(nxCriterion, nxCriterionOr);
            }

            lista = genericDao.listarByFilter(propriedades, null, RmMaterialStatus.class, Constantes.NO_LIMIT, nxCriterion);

            for (RmMaterialStatus rmMaterialStatus : lista) {
                if (rmMaterialStatus.getRmMaterial().getDataPrevistaEntrega() != null) {
                    rmMaterialStatus.getRmMaterial().setStDataPrevistaEntrega(Util.dateToString(rmMaterialStatus.getRmMaterial().getDataPrevistaEntrega(), rb.getString("format_date")));
                }
            }
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return lista;
    }
    

    /**
     * Lista todos os status dos rmMateriais
     *
     * @param listaRmMaterial
     * @param listarHistorico
     * @return
     */
    public List<RmMaterialStatus> listarMaterialStatus(List<RmMaterial> listaRmMaterial, Boolean listarHistorico) {
        List<RmMaterialStatus> lista = new ArrayList<>();

        GenericDao<RmMaterialStatus> genericDao = new GenericDao();

        try {
            String aliasMaterial = NxCriterion.montaAlias(RmMaterialStatus.ALIAS_CLASSE, RmMaterialStatus.RM_MATERIAL_ID);
            String aliasStatus = NxCriterion.montaAlias(RmMaterialStatus.ALIAS_CLASSE, RmMaterialStatus.STATUS_ID);
            String aliasServico = NxCriterion.montaAlias(RmMaterialStatus.ALIAS_CLASSE, RmMaterialStatus.RM_MATERIAL_ID, RmMaterial.MATERIAL);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialStatus.ID));
            propriedades.add(new Propriedade(RmMaterialStatus.DATA_HORA_STATUS));
            propriedades.add(new Propriedade(RmMaterialStatus.OBSERVACAO));
            propriedades.add(new Propriedade(RmMaterialStatus.USUARIO));

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasMaterial));
            propriedades.add(new Propriedade(RmMaterial.MATERIAL, RmMaterial.class, aliasMaterial));

            propriedades.add(new Propriedade(Material.IS_SERVICO, Material.class, aliasServico));

            propriedades.add(new Propriedade(Status.ID, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.NOME, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.CODIGO, Status.class, aliasStatus));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterialStatus.RM_MATERIAL_ID, listaRmMaterial, Filtro.IN));

            if (!listarHistorico) {
                NxCriterion nxCriterionOr = NxCriterion.montaRestriction(new Filtro(RmMaterialStatus.IS_HISTORICO, null, Filtro.IS_NULL));
                nxCriterionOr = NxCriterion.or(nxCriterionOr, NxCriterion.montaRestriction(new Filtro(RmMaterialStatus.IS_HISTORICO, Boolean.FALSE, Filtro.EQUAL)));

                nxCriterion = NxCriterion.and(nxCriterion, nxCriterionOr);
            }

            lista = genericDao.listarByFilter(propriedades, null, RmMaterialStatus.class, Constantes.NO_LIMIT, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return lista;
    }

    /**
     * Lista todos os status dos rmMateriais
     *
     * @param listaRmMaterial
     * @return
     */
    public List<RmMaterialStatus> listarMaterialStatus(List<VwRmMaterial> listaRmMaterial) {
        List<RmMaterialStatus> lista = new ArrayList<>();

        List<RmMaterial> listaRmMateriais = new ArrayList<>();

        for (VwRmMaterial vw : listaRmMaterial) {
            listaRmMateriais.add(vw.getRmMaterial());
        }

        GenericDao<RmMaterialStatus> genericDao = new GenericDao();

        try {
            String aliasMaterial = NxCriterion.montaAlias(RmMaterialStatus.ALIAS_CLASSE, RmMaterialStatus.RM_MATERIAL_ID);
            String aliasStatus = NxCriterion.montaAlias(RmMaterialStatus.ALIAS_CLASSE, RmMaterialStatus.STATUS_ID);
            String aliasServico = NxCriterion.montaAlias(RmMaterialStatus.ALIAS_CLASSE, RmMaterialStatus.RM_MATERIAL_ID, RmMaterial.MATERIAL);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialStatus.ID));
            propriedades.add(new Propriedade(RmMaterialStatus.DATA_HORA_STATUS));
            propriedades.add(new Propriedade(RmMaterialStatus.OBSERVACAO));
            propriedades.add(new Propriedade(RmMaterialStatus.USUARIO));

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasMaterial));
            propriedades.add(new Propriedade(RmMaterial.MATERIAL, RmMaterial.class, aliasMaterial));

            propriedades.add(new Propriedade(Material.IS_SERVICO, Material.class, aliasServico));

            propriedades.add(new Propriedade(Status.ID, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.NOME, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.CODIGO, Status.class, aliasStatus));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterialStatus.RM_MATERIAL_ID, listaRmMateriais, Filtro.IN));

            NxCriterion nxCriterionOr = NxCriterion.montaRestriction(new Filtro(RmMaterialStatus.IS_HISTORICO, null, Filtro.IS_NULL));
            nxCriterionOr = NxCriterion.or(nxCriterionOr, NxCriterion.montaRestriction(new Filtro(RmMaterialStatus.IS_HISTORICO, Boolean.FALSE, Filtro.EQUAL)));

            nxCriterion = NxCriterion.and(nxCriterion, nxCriterionOr);

            lista = genericDao.listarByFilter(propriedades, null, RmMaterialStatus.class, Constantes.NO_LIMIT, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return lista;
    }

    /**
     * Lista o status atual do rmMaterial
     *
     * @param listaRmMaterial
     * @return
     */
    public List<RmMaterialStatus> listarStatusAtual(List<RmMaterial> listaRmMaterial) {
        GenericDao<VwRmMaterial> genericDao = new GenericDao<>();
        List<RmMaterialStatus> lista = new ArrayList<>();
        List<VwRmMaterial> listaVwRmMaterial = new ArrayList<>();
        try {
            String aliasRmMaterialStatus = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_STATUS_ID);
            String aliasRmMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID);
            String aliasRm = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM);
            String aliasStatus = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_STATUS_ID, RmMaterialStatus.STATUS_ID);
            String aliasStatusRmMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_STATUS_ID, RmMaterialStatus.RM_MATERIAL_ID);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(VwRmMaterial.ID));

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));

            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));

            propriedades.add(new Propriedade(RmMaterialStatus.ID, RmMaterialStatus.class, aliasRmMaterialStatus));
            propriedades.add(new Propriedade(RmMaterialStatus.USUARIO, RmMaterialStatus.class, aliasRmMaterialStatus));

            propriedades.add(new Propriedade(Status.ID, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.CODIGO, Status.class, aliasStatus));

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasStatusRmMaterial));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(VwRmMaterial.RM_MATERIAL_ID, listaRmMaterial, Filtro.IN));
            listaVwRmMaterial = genericDao.listarByFilter(propriedades, null, VwRmMaterial.class, Constantes.NO_LIMIT, nxCriterion);

            for (VwRmMaterial vwRmMaterial : listaVwRmMaterial) {
                lista.add(vwRmMaterial.getRmMaterialStatus());
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return lista;
    }
    
 
    /**
     * Lista o status atual do rmMaterial
     *
     * @param rmMaterial
     * @return
     */
    public RmMaterialStatus listarStatusAtual(RmMaterial rmMaterial) {
        GenericDao<VwRmMaterial> genericDao = new GenericDao<>();
        VwRmMaterial vwRmMaterial = null;

        try {
            String aliasRmMaterialStatus = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_STATUS_ID);
            String aliasRmMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID);
            String aliasRm = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM);
            String aliasStatus = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_STATUS_ID, RmMaterialStatus.STATUS_ID);
            String aliasStatusRmMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_STATUS_ID, RmMaterialStatus.RM_MATERIAL_ID);
            String aliasMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.MATERIAL);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(VwRmMaterial.ID));

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));

            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));

            propriedades.add(new Propriedade(RmMaterialStatus.ID, RmMaterialStatus.class, aliasRmMaterialStatus));
            propriedades.add(new Propriedade(RmMaterialStatus.USUARIO, RmMaterialStatus.class, aliasRmMaterialStatus));

            propriedades.add(new Propriedade(Status.ID, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.CODIGO, Status.class, aliasStatus));

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasStatusRmMaterial));

            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.HIERARQUIA, Material.class, aliasMaterial));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(VwRmMaterial.RM_MATERIAL_ID, rmMaterial, Filtro.EQUAL));
            vwRmMaterial = genericDao.selectUnique(propriedades, VwRmMaterial.class, nxCriterion);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return vwRmMaterial.getRmMaterialStatus();
    }

    /**
     * Procura o status do material em uma lista de status
     *
     * @param listaMaterialStatus
     * @param rmMaterial
     * @return
     */
    public RmMaterialStatus listarStatusAtual(List<RmMaterialStatus> listaMaterialStatus, RmMaterial rmMaterial) throws Exception {
        for (RmMaterialStatus rms : listaMaterialStatus) {
            if (rms.getRmMaterial().equals(rmMaterial)) {
                return rms;
            }
        }
        return null;
    }
    

    /**
     * Seleciona todos os status do rmMaterial
     *
     * @param listaMaterialStatus
     * @param rmMaterial
     * @return
     */
    public List<RmMaterialStatus> selecionarStatusMaterial(List<RmMaterialStatus> listaMaterialStatus, final RmMaterial rmMaterial) {
        return (List<RmMaterialStatus>) CollectionUtils.select(listaMaterialStatus, new Predicate() {

            @Override
            public boolean evaluate(Object object) {
                RmMaterialStatus rmMaterialStatus = (RmMaterialStatus) object;

                return rmMaterialStatus.getRmMaterial().equals(rmMaterial);
            }
        });
    }
}
