package br.com.nextage.rmaweb.controller;

import java.sql.PreparedStatement;
import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.persistence_2.util.AliasToBean;
import br.com.nextage.persistence_2.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.Configuracao;
import br.com.nextage.rmaweb.entitybean.EapMultiEmpresa;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.entitybean.RmServicoCodigoSap;
import br.com.nextage.rmaweb.entitybean.Setor;
import br.com.nextage.rmaweb.entitybean.SubArea;
import br.com.nextage.rmaweb.entitybean.TipoMaterial;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.filtro.FiltroAutoCompleteCadastroRma;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.utils.ConnectionFactory;
import br.com.nextage.rmaweb.vo.RmAprovacaoVo;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import br.com.nextage.util.filter.FiltroAutoComplete;
import com.google.gson.Gson;
import br.com.nextage.rmaweb.utils.ConnectionFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author nextage3
 */
@Path("AutoComplete")
public class AutoCompleteController {

    @Context
    HttpServletRequest request;

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    /**
     * Lista as pessoas para autocomplete
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("listarPessoas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pessoa> listarPessoas(FiltroAutoComplete filtro) {
        List<Pessoa> lista = new ArrayList<>();
        try {
            String aliasEapMultiEmpresa = NxCriterion.montaAlias(Pessoa.ALIAS_CLASSE, Pessoa.EAP_MULTI_EMPRESA);

            Configuracao conf = ConfiguracaoSingleton.getConfiguracao();

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Pessoa.NOME));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA));
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID));

            //Se caso a pessoa tiver habilitado eap multi empresa, então o mesmo é listado para ser exibido junto no autoComplete
            if (conf.getHabilitaEapMultiEmpresa() != null && conf.getHabilitaEapMultiEmpresa() == true) {
                //EAP MULTI EMPRESA
                propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
                propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
                propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            }

            NxCriterion criterion = NxCriterion.montaRestriction(new Filtro(Pessoa.NOME, filtro.getStrFiltro(), Filtro.LIKE));
            criterion = NxCriterion.or(criterion, NxCriterion.montaRestriction(new Filtro(Pessoa.REFERENCIA, filtro.getStrFiltro(), Filtro.LIKE)));

            if (conf.getHabilitaEapMultiEmpresa() != null && conf.getHabilitaEapMultiEmpresa() == true && filtro.getObjetoFiltro() != null) {
                criterion = NxCriterion.and(criterion, NxCriterion.montaRestriction(new Filtro(EapMultiEmpresa.ID, filtro.getObjetoFiltro(), Filtro.EQUAL, aliasEapMultiEmpresa)));
            }
            
            GenericDao genericDao = new GenericDao();

            lista = genericDao.listarByFilter(propriedades, null, Pessoa.class, 20, criterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    /**
     * Lista os materiais para autocomplete
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("listarMateriais")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Material> listarMateriais(FiltroAutoComplete filtro) {
        List<Material> list = new ArrayList<>();
        try {
            String aliasTipoMaterial = NxCriterion.montaAlias(Material.ALIAS_CLASSE, Material.TIPO_MATERIAL);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Material.NOME));
            propriedades.add(new Propriedade(Material.NOME_COMPLETO));
            propriedades.add(new Propriedade(Material.CODIGO));
            propriedades.add(new Propriedade(Material.UNIDADE_MEDIDA));
            propriedades.add(new Propriedade(Material.MATERIAL_ID));
            propriedades.add(new Propriedade(Material.NCM));
            propriedades.add(new Propriedade(Material.ULTIMO_VALOR_NEGOCIADO));
            propriedades.add(new Propriedade(Material.HIERARQUIA));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP));
            propriedades.add(new Propriedade(Material.PATRIMONIADO));

            //Tipo Material
            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.DESCRICAO, TipoMaterial.class, aliasTipoMaterial));

            NxCriterion criterion;
            NxCriterion criterionAnd = NxCriterion.montaRestriction(new Filtro(Material.STATUS, Constantes.ATIVO_ABREVIADO, Filtro.EQUAL));
            criterionAnd = NxCriterion.or(criterionAnd, NxCriterion.montaRestriction(new Filtro(Material.STATUS, null, Filtro.IS_NULL)));

            NxCriterion criterionAux = NxCriterion.montaRestriction(new Filtro(Material.NOME, filtro.getStrFiltro(), Filtro.LIKE));
            criterionAux = NxCriterion.or(criterionAux, NxCriterion.montaRestriction(new Filtro(Material.NOME_COMPLETO, filtro.getStrFiltro(), Filtro.LIKE)));
            criterionAux = NxCriterion.or(criterionAux, NxCriterion.montaRestriction(new Filtro(Material.CODIGO, filtro.getStrFiltro(), Filtro.LIKE)));
            criterion = NxCriterion.and(criterionAux, criterionAnd);

            criterionAnd = NxCriterion.montaRestriction(new Filtro(Material.CODIGO, null, Filtro.NOT_NULL));
            criterion = NxCriterion.and(criterion, criterionAnd);

            GenericDao genericDao = new GenericDao();

            List<NxOrder> orders = Arrays.asList(new NxOrder(Material.NOME_COMPLETO, NxOrder.NX_ORDER.ASC));

            list = genericDao.listarByFilter(propriedades, orders, Material.class, 50, criterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return list;
    }

    /**
     * Lista os materiais para autocomplete do cadastro de RMA. Se for marcado o
     * tipo de requisição "Requisição de Retirada em Estoque" ou "Requisição de Retirada em Estoque (Estoquista)" , não traz os itens
     * cauteláveis
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("listarMateriaisCadastroRma")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Material> listarMateriaisCadastroRma(FiltroAutoCompleteCadastroRma filtro) {
        List<Material> list = new ArrayList<>();
        try {
            String aliasTipoMaterial = NxCriterion.montaAlias(Material.ALIAS_CLASSE, Material.TIPO_MATERIAL);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Material.NOME));
            propriedades.add(new Propriedade(Material.NOME_COMPLETO));
            propriedades.add(new Propriedade(Material.CODIGO));
            propriedades.add(new Propriedade(Material.UNIDADE_MEDIDA));
            propriedades.add(new Propriedade(Material.MATERIAL_ID));
            propriedades.add(new Propriedade(Material.NCM));
            propriedades.add(new Propriedade(Material.ULTIMO_VALOR_NEGOCIADO));
            propriedades.add(new Propriedade(Material.HIERARQUIA));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP));
			propriedades.add(new Propriedade(Material.GRUPO_MERCADORIA));

            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));

            NxCriterion criterion;
            NxCriterion criterionAnd = NxCriterion.montaRestriction(new Filtro(Material.STATUS, Constantes.ATIVO_ABREVIADO, Filtro.EQUAL));
            criterionAnd = NxCriterion.or(criterionAnd, NxCriterion.montaRestriction(new Filtro(Material.STATUS, null, Filtro.IS_NULL)));

            NxCriterion criterionAux = NxCriterion.montaRestriction(new Filtro(Material.NOME, filtro.getStrFiltro(), Filtro.LIKE));
            criterionAux = NxCriterion.or(criterionAux, NxCriterion.montaRestriction(new Filtro(Material.NOME_COMPLETO, filtro.getStrFiltro(), Filtro.LIKE)));
            criterionAux = NxCriterion.or(criterionAux, NxCriterion.montaRestriction(new Filtro(Material.CODIGO, filtro.getStrFiltro(), Filtro.LIKE)));
            criterion = NxCriterion.and(criterionAux, criterionAnd);

            if (filtro.getCodigoCentro() != null && !filtro.getCodigoCentro().isEmpty()) {
                criterionAnd = NxCriterion.montaRestriction(new Filtro(Material.CENTRO, filtro.getCodigoCentro(), Filtro.EQUAL));
                criterion = NxCriterion.and(criterion, criterionAnd);
            }

            criterionAnd = NxCriterion.montaRestriction(new Filtro(Material.CODIGO, null, Filtro.NOT_NULL));
            criterion = NxCriterion.and(criterion, criterionAnd);

            GenericDao genericDao = new GenericDao();

            List<NxOrder> orders = Arrays.asList(new NxOrder(Material.NOME_COMPLETO, NxOrder.NX_ORDER.ASC));

            list = genericDao.listarByFilter(propriedades, orders, Material.class, 100, criterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return list;
    }

    /**
     * Lista os materiais para autocomplete apenas cadastrados na rmMaterial
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("listarMateriaisExistentes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Material> listarMateriaisExistentes(FiltroAutoComplete filtro) {
        List<Material> listaMaterial = new ArrayList<>();
        try {
            Transaction trx = null;
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();

            trx = session.beginTransaction();

            String auxMontaLike = "%" + filtro.getStrFiltro() + "%";

            Criteria criteria = session.createCriteria(RmMaterial.class, "rmMaterial");

            criteria.createAlias("rmMaterial.material", "material", CriteriaSpecification.LEFT_JOIN);

            ProjectionList list = Projections.projectionList();
            list.add(Projections.distinct(Projections.property("material.materialId").as("material.materialId")));
            list.add(Projections.property("material.nome").as("material.nome"));
            criteria.add(Restrictions.or(Restrictions.like("material.nome", auxMontaLike), Restrictions.like("material.codigo", auxMontaLike)));
            list.add(Projections.property("material.status").as("material.status"));
            criteria.add(Restrictions.or(Restrictions.eq("material.status", "A"), Restrictions.isNull("material.status")));
            list.add(Projections.property("material.codigo").as("material.codigo"));
            list.add(Projections.property("material.unidadeMedida").as("material.unidadeMedida"));
            list.add(Projections.property("material.ncm").as("material.ncm"));
            list.add(Projections.property("material.ultimoValorNegociado").as("material.ultimoValorNegociado"));
            list.add(Projections.property("material.hierarquia").as("material.hierarquia"));
            criteria.setProjection(list);

            criteria.setMaxResults(filtro.getLimite());
            /**
             * Order comentado por causa do dialect 2008, ele tentava ordenar pelo alias.
             */
            //criteria.addOrder(Order.asc("material.nome"));

            HashMap<String, Class> classesEstrangeiras = new HashMap<>();
            classesEstrangeiras.put("material", Material.class);

            criteria.setResultTransformer(new AliasToBean(RmMaterial.class, classesEstrangeiras));

            List<RmMaterial> listaRmMaterialMaterial = criteria.list();

            trx.commit();

            for (RmMaterial rm : listaRmMaterialMaterial) {
                listaMaterial.add(rm.getMaterial());

            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return listaMaterial;
    }

    /**
     * Lista pessoas com tipoAtuacao igual GA ou E
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("listarGerenteAreaEncarregado")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pessoa> listarGerenteAreaEncarregado(FiltroAutoComplete filtro) {

        List<Pessoa> list = new ArrayList<>();
        GenericDao genericDao = new GenericDao();

        RmAprovacaoVo vo = new RmAprovacaoVo();
        String json = (String) filtro.getObjetoFiltro();

        Gson gson = new Gson();
        vo = gson.fromJson(json, RmAprovacaoVo.class);

        try {
            String aliasEapMultiEmpresa = NxCriterion.montaAlias(Pessoa.ALIAS_CLASSE, Pessoa.EAP_MULTI_EMPRESA);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Pessoa.NOME));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA));
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
            propriedades.add(new Propriedade(Pessoa.EMAIL));

            //Se caso a pessoa tiver habilitado eap multi empresa, então o mesmo é listado para ser exibido junto no autoComplete
            Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
            if (conf.getHabilitaEapMultiEmpresa() != null && conf.getHabilitaEapMultiEmpresa() == true) {
                //EAP MULTI EMPRESA
                propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
                propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
                propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            }

            NxCriterion nxCriterion, nxCriterionOr = null;

            nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.NOME, filtro.getStrFiltro(), Filtro.LIKE));
            nxCriterion = NxCriterion.or(nxCriterion, NxCriterion.montaRestriction(new Filtro(Pessoa.REFERENCIA, filtro.getStrFiltro(), Filtro.LIKE)));

            if (vo != null && vo.getGerenteArea() != null && vo.getGerenteArea().equals(true)) {
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Pessoa.TIPO_ATUACAO, Constantes.TIPO_ATUACAO_GERENTE_AREA, Filtro.LIKE)));
            } else {
                nxCriterionOr = NxCriterion.montaRestriction(new Filtro(Pessoa.TIPO_ATUACAO, Constantes.TIPO_ATUACAO_GERENTE_AREA, Filtro.LIKE));
                nxCriterionOr = NxCriterion.or(nxCriterionOr, NxCriterion.montaRestriction(new Filtro(Pessoa.TIPO_ATUACAO, Constantes.TIPO_ATUACAO_ENCARREGADO, Filtro.LIKE)));

                nxCriterion = NxCriterion.and(nxCriterion, nxCriterionOr);
            }

            if (conf.getHabilitaEapMultiEmpresa() != null && conf.getHabilitaEapMultiEmpresa() == true) {
                if (vo.getEapMultiEmpresaId() != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(EapMultiEmpresa.ID, vo.getEapMultiEmpresaId(), Filtro.EQUAL, aliasEapMultiEmpresa)));

                }
            }

            List<NxOrder> orders = Arrays.asList(new NxOrder(Pessoa.NOME, NxOrder.NX_ORDER.ASC));

            list = genericDao.listarByFilter(propriedades, orders, Pessoa.class, 20, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return list;
    }

    @POST
    @Path("listarCoordenadores")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pessoa> listarCoordenadores(FiltroAutoComplete filtro) {
        List<Pessoa> list = new ArrayList<>();
        GenericDao genericDao = new GenericDao();
        try {
            String aliasEapMultiEmpresa = NxCriterion.montaAlias(Pessoa.ALIAS_CLASSE, Pessoa.EAP_MULTI_EMPRESA);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Pessoa.NOME));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA));
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
            propriedades.add(new Propriedade(Pessoa.EMAIL));

            //Se caso a pessoa tiver habilitado eap multi empresa, então o mesmo é listado para ser exibido junto no autoComplete
            Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
            if (conf.getHabilitaEapMultiEmpresa() != null && conf.getHabilitaEapMultiEmpresa() == true) {
                //EAP MULTI EMPRESA
                propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
                propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
                propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            }

            NxCriterion nxCriterion = null;

            nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.NOME, filtro.getStrFiltro(), Filtro.LIKE));
            nxCriterion = NxCriterion.or(nxCriterion, NxCriterion.montaRestriction(new Filtro(Pessoa.REFERENCIA, filtro.getStrFiltro(), Filtro.LIKE)));

            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Pessoa.TIPO_ATUACAO, Constantes.TIPO_ATUACAO_COORDENADOR, Filtro.LIKE)));

            if (conf.getHabilitaEapMultiEmpresa() != null && conf.getHabilitaEapMultiEmpresa() == true && filtro.getObjetoFiltro() != null) {
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(EapMultiEmpresa.ID, filtro.getObjetoFiltro(), Filtro.EQUAL, aliasEapMultiEmpresa)));
            }

            List<NxOrder> orders = Arrays.asList(new NxOrder(Pessoa.NOME, NxOrder.NX_ORDER.ASC));

            list = genericDao.listarByFilter(propriedades, orders, Pessoa.class, 20, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return list;
    }

    @POST
    @Path("listarResponsavelRetiradaEstoque")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pessoa> listarResponsavelRetiradaEstoque(FiltroAutoComplete filtro) {
        List<Pessoa> list = new ArrayList<>();
        GenericDao genericDao = new GenericDao();
        try {
            String aliasEapMultiEmpresa = NxCriterion.montaAlias(Pessoa.ALIAS_CLASSE, Pessoa.EAP_MULTI_EMPRESA);


            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Pessoa.NOME));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA));
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
            propriedades.add(new Propriedade(Pessoa.EMAIL));

            //Se caso a pessoa tiver habilitado eap multi empresa, então o mesmo é listado para ser exibido junto no autoComplete
            Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
            if (conf.getHabilitaEapMultiEmpresa() != null && conf.getHabilitaEapMultiEmpresa() == true) {
                //EAP MULTI EMPRESA
                propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
                propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
                propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            }

            NxCriterion nxCriterion = null;

            nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.NOME, filtro.getStrFiltro(), Filtro.LIKE));
            nxCriterion = NxCriterion.or(nxCriterion, NxCriterion.montaRestriction(new Filtro(Pessoa.REFERENCIA, filtro.getStrFiltro(), Filtro.LIKE)));

            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Pessoa.TIPO_ATUACAO, Constantes.TIPO_ATUACAO_RESP_RET_ESTOQ, Filtro.LIKE)));

            if (conf.getHabilitaEapMultiEmpresa() != null && conf.getHabilitaEapMultiEmpresa() == true && filtro.getObjetoFiltro() != null) {
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(EapMultiEmpresa.ID, filtro.getObjetoFiltro(), Filtro.EQUAL, aliasEapMultiEmpresa)));
            }

            List<NxOrder> orders = Arrays.asList(new NxOrder(Pessoa.NOME, NxOrder.NX_ORDER.ASC));

            list = genericDao.listarByFilter(propriedades, orders, Pessoa.class, 20, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return list;
    }

    @POST
    @Path("listarUsuarios")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listarUsuarios(FiltroAutoComplete filtro) {
        List<Usuario> list = new ArrayList<>();
        GenericDao genericDao = new GenericDao();
        try {
            String aliasPessoa = NxCriterion.montaAlias(Usuario.ALIAS_CLASSE, Usuario.PESSOA);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Usuario.USUARIO_ID));
            propriedades.add(new Propriedade(Usuario.NOME));

            //Pessoa
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasPessoa));

            NxCriterion nxCriterion = null;

            nxCriterion = NxCriterion.montaRestriction(new Filtro(Usuario.NOME, filtro.getStrFiltro(), Filtro.LIKE));
            nxCriterion = NxCriterion.or(nxCriterion, NxCriterion.montaRestriction(new Filtro(Pessoa.NOME, filtro.getStrFiltro(), Filtro.LIKE, aliasPessoa)));

            List<NxOrder> orders = Arrays.asList(new NxOrder(Usuario.NOME, NxOrder.NX_ORDER.ASC));

            list = genericDao.listarByFilter(propriedades, orders, Usuario.class, 20, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return list;
    }


    @POST
    @Path("listarSetor")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Setor> listarSetor(FiltroAutoComplete filtro) {
        List<Setor> list = new ArrayList<>();
        GenericDao genericDao = new GenericDao();
        try {

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Setor.SETOR_ID));
            propriedades.add(new Propriedade(Setor.CODIGO));
            propriedades.add(new Propriedade(Setor.DESCRICAO));

            NxCriterion nxCriterion = null;

            nxCriterion = NxCriterion.montaRestriction(new Filtro(Setor.DESCRICAO, filtro.getStrFiltro(), Filtro.LIKE));
            nxCriterion = NxCriterion.or(nxCriterion, NxCriterion.montaRestriction(new Filtro(Setor.CODIGO, filtro.getStrFiltro(), Filtro.LIKE)));

            List<NxOrder> orders = Arrays.asList(new NxOrder(Setor.DESCRICAO, NxOrder.NX_ORDER.ASC));

            list = genericDao.listarByFilter(propriedades, orders, Setor.class, 20, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return list;
    }

    @POST
    @Path("listarSubarea")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<SubArea> listarSubarea(FiltroAutoComplete filtro) {
        List<SubArea> list = new ArrayList<>();
        GenericDao genericDao = new GenericDao();
        try {

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(SubArea.SUB_AREA_ID));
            propriedades.add(new Propriedade(SubArea.CODIGO));
            propriedades.add(new Propriedade(SubArea.DESCRICAO));

            NxCriterion nxCriterion = null;

            nxCriterion = NxCriterion.montaRestriction(new Filtro(SubArea.DESCRICAO, filtro.getStrFiltro(), Filtro.LIKE));
            nxCriterion = NxCriterion.or(nxCriterion, NxCriterion.montaRestriction(new Filtro(SubArea.CODIGO, filtro.getStrFiltro(), Filtro.LIKE)));

            List<NxOrder> orders = Arrays.asList(new NxOrder(SubArea.DESCRICAO, NxOrder.NX_ORDER.ASC));

            list = genericDao.listarByFilter(propriedades, orders, SubArea.class, 20, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return list;
    }
    
    
    @POST
    @Path("listarServicosCodigoSap")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<RmServicoCodigoSap> listarServicosCodigoSap(FiltroAutoComplete filtro) {
        List<RmServicoCodigoSap> list = new ArrayList<>();
        GenericDao genericDao = new GenericDao();
        try {
        	
        	String sql = "SELECT CRIADO, CODIGO_ID, CODIGO, DESCRICAO FROM TB_SERVICO_CODIGO_SAP WHERE DESCRICAO LIKE '%(?)%';";
        	
        	 sql = sql.replace("(?)", filtro.getStrFiltro());
             PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet resultSet = smt.executeQuery();

             while (resultSet.next()) {
            	 RmServicoCodigoSap codigoSap = new RmServicoCodigoSap();
                 codigoSap.setCriado(resultSet.getString("CRIADO"));
                 codigoSap.setDescricao(resultSet.getString("DESCRICAO"));
                 codigoSap.setCodigo(resultSet.getString("CODIGO"));
                 list.add(codigoSap);
             }

             smt.close();
             resultSet.close();	
            
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return list;
    }
}
