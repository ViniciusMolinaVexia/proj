package br.com.nextage.rmaweb.controller;

import static br.com.nextage.rmaweb.util.PropertyUtils.getProperty;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.persistence_2.util.Paginacao;
import br.com.nextage.persistence_2.vo.PaginacaoVo;
import br.com.nextage.rmaweb.controller.notificacao.email.enviar.EmailService;
import br.com.nextage.rmaweb.controller.pessoa.obterPorPerfilECentro.ObterPessoaPorPerfilECentro;
import br.com.nextage.rmaweb.controller.pessoa.obterPorPerfilECentro.ObterPessoaPorPerfilECentroDao;
import br.com.nextage.rmaweb.dao.PerfilRoleDao;
import br.com.nextage.rmaweb.dao.PrioridadeDao;
import br.com.nextage.rmaweb.dao.RmDao;
import br.com.nextage.rmaweb.dao.WorkflowDAO;
import br.com.nextage.rmaweb.entitybean.Area;
import br.com.nextage.rmaweb.entitybean.Centro;
import br.com.nextage.rmaweb.entitybean.Configuracao;
import br.com.nextage.rmaweb.entitybean.Deposito;
import br.com.nextage.rmaweb.entitybean.EapMultiEmpresa;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.Prioridade;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.entitybean.RmAprovador;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.entitybean.RmMaterialStatus;
import br.com.nextage.rmaweb.entitybean.RmServico;
import br.com.nextage.rmaweb.entitybean.RmServicoStatus;
import br.com.nextage.rmaweb.entitybean.Role;
import br.com.nextage.rmaweb.entitybean.Status;
import br.com.nextage.rmaweb.entitybean.TipoMaterial;
import br.com.nextage.rmaweb.entitybean.TipoRequisicao;
import br.com.nextage.rmaweb.entitybean.UnidadeMedida;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.entitybean.VwRmMaterial;
import br.com.nextage.rmaweb.entitybean.VwRmServico;
import br.com.nextage.rmaweb.entitybean.Workflow;
import br.com.nextage.rmaweb.filtro.FiltroRmAprovacao;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.WorkflowEnum;
import br.com.nextage.rmaweb.service.WorkflowService;
import br.com.nextage.rmaweb.service.integracao.RastreabilidadeService;
import br.com.nextage.rmaweb.service.integracao.RmMaterialStatusService;
import br.com.nextage.rmaweb.service.integracao.RmServicoStatusService;
import br.com.nextage.rmaweb.service.integracao.RmaService;
import br.com.nextage.rmaweb.service.integracao.RmaServicoService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.RmAprovadorVo;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import br.com.nextage.util.criptografia.Criptografia;
import org.apache.log4j.Logger;

/**
 * @author Daniel H. Parisotto
 * @date 28/09/2015
 */
@Path("RmAprovacaoController")
public class RmAprovacaoController {

    public static final String REPROVADA = "reprovada";
    private static final Logger logger = Logger.getLogger(RmAprovacaoController.class);
    public static final int ALMOXARIFADO_PERFIL_ID = 7;

    @Context
    HttpServletRequest request;

    @Inject
    private EmailService emailService;

    @Inject
    private ObterPessoaPorPerfilECentroDao obterPessoaPorPerfilECentroDao;

    private RmDao rmDao = new RmDao();

    private WorkflowService workflowService = WorkflowService.getInstance();
    private RastreabilidadeService rastroService = RastreabilidadeService.getInstance();

    /**
     * Carrega as informações do rmAprovador passado como parametro, carrega
     * tambem uma lista de materiais ligados a rm.
     * <p/>
     * Utilizado ao editar uma rm na tela de aprovação pelo sistema e utilizado
     * pelo link de aprovação
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("selectUniqueRmAprovador")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RmAprovadorVo selectUniqueRmAprovador(FiltroRmAprovacao filtro) {
        RmAprovadorVo vo = new RmAprovadorVo();
        Integer idRmAprovacao;

        GenericDao<RmAprovador> genericDao = new GenericDao<>();
        RmaServicoService rmaServicoService = new RmaServicoService(request);
        
        //Verifica se veio o parametro "idRmAprovacaoCriptografada", caso sim
        //significa que e da tela de aprovacao pelo email'
        if (filtro.getIdRmAprovacaoCriptografada() != null) {
            idRmAprovacao = Integer.valueOf(Criptografia.decript(Util.decodingUrl(filtro.getIdRmAprovacaoCriptografada())).split("=")[1]);
        } else {
            idRmAprovacao = Integer.valueOf(filtro.getIdRmAprovacao());
        }

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroRmAprovacao.class.getName(), Util.getNomeMetodoAtual(), filtro, null));

            String aliasRequisicao = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID);
            String aliasEapMultiEmpresa = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID, Rm.EAP_MULTI_EMPRESA);
            String aliasAprovador = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.APROVADOR_PESSOA_ID);
            String aliasRequisitante = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID, Rm.REQUISITANTE);
            String aliasGerenteCustos = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID, Rm.GERENTE_CUSTOS);
            String aliasGerenteObra = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID, Rm.GERENTE_OBRA);
            String aliasSolicitante = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID, Rm.USUARIO);
            String aliasPrioridade = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID, Rm.PRIORIDADE);
            String aliasAtribuidoPara = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID, Rm.ATRIBUIDO_PARA);
            String aliasTipoRequisicao = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID, Rm.TIPO_REQUISICAO);
            String aliasSolicitantePessoa = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID, Rm.USUARIO, Usuario.PESSOA);

            //1 nivel
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmAprovador.ID));
            propriedades.add(new Propriedade(RmAprovador.APROVADA));
            propriedades.add(new Propriedade(RmAprovador.DATA_AVALIACAO));
            propriedades.add(new Propriedade(RmAprovador.OBSERVACAO));
            propriedades.add(new Propriedade(RmAprovador.ORDEM));
            propriedades.add(new Propriedade(RmAprovador.APROVADOR_VEZ));
            propriedades.add(new Propriedade(RmAprovador.TIPO_APROVADOR));
            propriedades.add(new Propriedade(RmAprovador.NIVEL_APROVADOR));
            propriedades.add(new Propriedade(RmAprovador.APROVADOR_AREA));
            propriedades.add(new Propriedade(RmAprovador.APROVADOR_GERENTE_AREA));
            propriedades.add(new Propriedade(RmAprovador.APROVADOR_CUSTO));
            propriedades.add(new Propriedade(RmAprovador.APROVADOR_EMERGENCIAL));

            //Requisicao
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRequisicao));
            propriedades.add(new Propriedade(Rm.DATA_EMISSAO, Rm.class, aliasRequisicao));
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO, Rm.class, aliasRequisicao));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRequisicao));
            propriedades.add(new Propriedade(Rm.OBSERVACAO, Rm.class, aliasRequisicao));
            propriedades.add(new Propriedade(Rm.LOCAL_ENTREGA, Rm.class, aliasRequisicao));
            propriedades.add(new Propriedade(Rm.JUST_RETORNO_EQUIPE_CUSTOS, Rm.class, aliasRequisicao));
            propriedades.add(new Propriedade(Rm.ATRIBUIDO_PARA, Rm.class, aliasRequisicao));
            propriedades.add(new Propriedade(Rm.DATA_APROVACAO_COMPRA, Rm.class, aliasRequisicao));
            propriedades.add(new Propriedade(Rm.DATA_REPROVACAO, Rm.class, aliasRequisicao));
            propriedades.add(new Propriedade(Rm.DATA_CANCELAMENTO, Rm.class, aliasRequisicao));
            propriedades.add(new Propriedade(Rm.CENTRO, Rm.class, aliasRequisicao));
            propriedades.add(new Propriedade(Rm.AREA, Rm.class, aliasRequisicao));
            propriedades.add(new Propriedade(Rm.TIPO_RM, Rm.class, aliasRequisicao));
            propriedades.add(new Propriedade(Rm.NUMERO_RM_SERVICO, Rm.class, aliasRequisicao));

            //Eap Multi Empresa
            propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));

            //Requisitante
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.EMAIL, Pessoa.class, aliasRequisitante));

            //Aprovador
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasAprovador));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasAprovador));
            propriedades.add(new Propriedade(Pessoa.TIPO_ATUACAO, Pessoa.class, aliasAprovador));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasAprovador));

            //Usuario
            propriedades.add(new Propriedade(Usuario.USUARIO_ID, Usuario.class, aliasSolicitante));

            //Gerente de custos
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasGerenteCustos));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasGerenteCustos));
            propriedades.add(new Propriedade(Pessoa.EMAIL, Pessoa.class, aliasGerenteCustos));

            //Gerente de obra
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasGerenteObra));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasGerenteObra));
            propriedades.add(new Propriedade(Pessoa.EMAIL, Pessoa.class, aliasGerenteObra));

            //Prioridade
            propriedades.add(new Propriedade(Prioridade.PRIORIDADE_ID, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.CODIGO, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.DESCRICAO, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.CONF_DIAS_PREV_ENTREGA, Prioridade.class, aliasPrioridade));

            //Atribuido para
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasAtribuidoPara));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasAtribuidoPara));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasAtribuidoPara));

            //Pessoa Usuario
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasSolicitantePessoa));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasSolicitantePessoa));
            propriedades.add(new Propriedade(Pessoa.EMAIL, Pessoa.class, aliasSolicitantePessoa));

            //Tipo de requisicao
            propriedades.add(new Propriedade(TipoRequisicao.CODIGO, TipoRequisicao.class, aliasTipoRequisicao));

            NxCriterion nxCriterion;
            nxCriterion = NxCriterion.montaRestriction(new Filtro(RmAprovador.ID, idRmAprovacao, Filtro.EQUAL));

            RmAprovador rmAprovador = genericDao.selectUnique(propriedades, RmAprovador.class, nxCriterion);

            vo.setRmAprovador(rmAprovador);
            
            if (rmAprovador != null && rmAprovador.getRm().getTipoRm().equals("MAT")) {
                vo.setListaRmMaterial(listarVwRmMateriais(rmAprovador.getRm()));
            } else if(rmAprovador != null && rmAprovador.getRm().getTipoRm().equals("SER")) {
            	vo.setRmServico(rmaServicoService.listarCadastroRmVo(rmAprovador.getRm()).getRmServico());
            }
            
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroRmAprovacao.class.getName(), Util.getNomeMetodoAtual(), filtro, null));

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return vo;
    }

    /**
     * Lista as rmAprovador que estao na vez e que o usuario logado seja o
     * aprovador.
     * <p/>
     * Utilizado na tela de aprovação pelo sistema
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PaginacaoVo listar(FiltroRmAprovacao filtro) {

        Usuario usuario = LoginService.getUsuarioLogado(request);

        String aliasRequisicao = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID);
        String aliasAprovador = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.APROVADOR_PESSOA_ID);
        String aliasRequisitante = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID, Rm.REQUISITANTE);
        String aliasUsuario = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID, Rm.USUARIO);
        String aliasUsuarioPessoa = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID, Rm.USUARIO, Usuario.PESSOA);
        String aliasCentro = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID, Rm.CENTRO);
        String aliasArea = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID, Rm.AREA);
        String aliasTipoRequisicao = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID, Rm.TIPO_REQUISICAO);

        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(RmAprovador.ID));
        propriedades.add(new Propriedade(RmAprovador.TIPO_APROVADOR));
        propriedades.add(new Propriedade(RmAprovador.APROVADA));
        propriedades.add(new Propriedade(RmAprovador.APROVADOR_PESSOA_ID));
        propriedades.add(new Propriedade(RmAprovador.DATA_AVALIACAO));

        //Requisicao
        propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRequisicao));
        propriedades.add(new Propriedade(Rm.DATA_EMISSAO, Rm.class, aliasRequisicao));
        propriedades.add(new Propriedade(Rm.DATA_APLICACAO, Rm.class, aliasRequisicao));
        propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRequisicao));
        propriedades.add(new Propriedade(Rm.ATRIBUIDO_PARA, Rm.class, aliasRequisicao));
        propriedades.add(new Propriedade(Rm.DATA_REPROVACAO, Rm.class, aliasRequisicao));
        propriedades.add(new Propriedade(Rm.DATA_APROVACAO_COMPRA, Rm.class, aliasRequisicao));
        propriedades.add(new Propriedade(Rm.DATA_REPROVACAO, Rm.class, aliasRequisicao));
        propriedades.add(new Propriedade(Rm.DATA_CANCELAMENTO, Rm.class, aliasRequisicao));
        propriedades.add(new Propriedade(Rm.TIPO_RM, Rm.class, aliasRequisicao));
        propriedades.add(new Propriedade(Rm.NUMERO_RM_SERVICO, Rm.class, aliasRequisicao));

        //Centro
        propriedades.add(new Propriedade(Centro.CENTRO_ID, Centro.class, aliasCentro));
        propriedades.add(new Propriedade(Centro.CODIGO, Centro.class, aliasCentro));
        propriedades.add(new Propriedade(Centro.DESCRICAO, Centro.class, aliasCentro));
        propriedades.add(new Propriedade(Centro.IDIOMA, Centro.class, aliasCentro));
        
        //Area
        propriedades.add(new Propriedade(Area.AREA_ID, Area.class, aliasArea));
        propriedades.add(new Propriedade(Area.CODIGO, Area.class, aliasArea));
        propriedades.add(new Propriedade(Area.DESCRICAO, Area.class, aliasArea));
        propriedades.add(new Propriedade(Area.IDIOMA, Area.class, aliasArea));

        //Requisitante
        propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRequisitante));
        propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRequisitante));

        //Aprovador
        propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasAprovador));
        propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasAprovador));

        //Usuario
        propriedades.add(new Propriedade(Usuario.USUARIO_ID, Usuario.class, aliasUsuario));

        //Pessoa Usuario
        propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasUsuarioPessoa));
        propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasUsuarioPessoa));

        //Tipo de requisicao
        propriedades.add(new Propriedade(TipoRequisicao.DESCRICAO, TipoRequisicao.class, aliasTipoRequisicao));
        propriedades.add(new Propriedade(TipoRequisicao.CODIGO, TipoRequisicao.class, aliasTipoRequisicao));

        NxCriterion nxCriterion = null;

        // Busca apenas as rm's pendentes
        nxCriterion = NxCriterion.and(nxCriterion,
                        NxCriterion.montaRestriction(new Filtro(RmAprovador.APROVADOR_VEZ, Boolean.TRUE, Filtro.EQUAL)));
        nxCriterion = NxCriterion.and(nxCriterion,
                        NxCriterion.montaRestriction(new Filtro(RmAprovador.DATA_AVALIACAO, null, Filtro.IS_NULL)));
        nxCriterion = NxCriterion.and(nxCriterion,
                        NxCriterion.montaRestriction(new Filtro(Rm.DATA_CANCELAMENTO, null, Filtro.IS_NULL, aliasRequisicao)));

        if (filtro.getNumeroRma() != null && !filtro.getNumeroRma().isEmpty()) {
            filtro.setNumeroRma(filtro.getNumeroRma().replaceAll(",", ";"));
            String[] listaRma = filtro.getNumeroRma().split(";");
            NxCriterion nx = null;
            NxCriterion nxAux = null;
            NxCriterion nxAuxSer = null;
            NxCriterion nxAuxResult = null;
            for (String rma : listaRma) {
                if (rma == null || rma.isEmpty()) {
                    continue;
                }
                
                nxAux = NxCriterion.montaRestriction(new Filtro(Rm.NUMERO_RM, rma, Filtro.EQUAL, aliasRequisicao));
                nxAuxSer = NxCriterion.montaRestriction(new Filtro(Rm.NUMERO_RM_SERVICO, rma, Filtro.EQUAL, aliasRequisicao));
              
                if (nx != null) {
                    nx = NxCriterion.or(nxAuxSer, nxAux);
                    
                } else {
                    //nx = nxAux;
                	nx = NxCriterion.or(nxAuxSer, nxAux);
                }
            }
            nxCriterion = NxCriterion.and(nxCriterion, nx);
        }

        if (filtro.getSolicitante() != null) {
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Rm.REQUISITANTE, filtro.getSolicitante(), Filtro.EQUAL, aliasRequisicao)));
        }
        
        if (filtro.getTipoRm() != null && !filtro.getTipoRm().isEmpty()) {
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Rm.TIPO_RM, filtro.getTipoRm(), Filtro.EQUAL, aliasRequisicao)));
        }

        NxCriterion nxCriterionAux = null;
        if (filtro.getCentro() != null && filtro.getCentro().getCentroId() != null) {
            nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Centro.CENTRO_ID, filtro.getCentro().getCentroId(), Filtro.EQUAL, aliasCentro));
            if (nxCriterion != null) {
                nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
            } else {
                nxCriterion = nxCriterionAux;
            }
        }

        if (filtro.getCadastrante() != null) {
            nxCriterion = NxCriterion.and(nxCriterion,
                            NxCriterion.montaRestriction(new Filtro(Usuario.PESSOA, filtro.getCadastrante(), Filtro.EQUAL, aliasUsuario)));
        }

        if (filtro.getTipoRequisicao() != null) {
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(
                            new Filtro(TipoRequisicao.CODIGO, filtro.getTipoRequisicao().getCodigo(), Filtro.EQUAL, aliasTipoRequisicao)));
        }

        boolean perfilAlmoxarife = false;
        for (Role r : usuario.getRoles()) {
            if (r.getNome().equals(Role.ROLE_ALMOXARIFE)) {
                perfilAlmoxarife = true;
            }
        }
        if (!perfilAlmoxarife) {
            WorkflowDAO workflowDAO = WorkflowDAO.getInstance();
            for (Role r : usuario.getRoles()) {
                if (r.getNome().equals(Role.ROLE_APROVADOR_AREA)) {
                    nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmAprovador.NIVEL_APROVADOR, "AREA", Filtro.EQUAL)));
                    List<Integer> centroIds = workflowDAO.getCentrosByWorkflowAreaUsuarioId(usuario.getUsuarioId());
                    List<Integer> areaIds = workflowDAO.getAreasByWorkflowAreaUsuarioId(usuario.getUsuarioId());

                    NxCriterion nxCentro = null;
                    for (Integer centro : centroIds) {
                        nxCentro = NxCriterion.or(nxCentro, NxCriterion.montaRestriction(new Filtro(Centro.CENTRO_ID, centro, Filtro.EQUAL, aliasCentro)));
                    }
                    nxCriterion = NxCriterion.and(nxCriterion, nxCentro);

                    NxCriterion nxArea = null;
                    for (Integer area : areaIds) {
                        nxArea = NxCriterion.or(nxArea, NxCriterion.montaRestriction(new Filtro(Area.AREA_ID, area, Filtro.EQUAL, aliasArea)));
                    }
                    nxCriterion = NxCriterion.and(nxCriterion, nxArea);
                } else if (r.getNome().equals(Role.ROLE_APROVADOR_GERENTE_AREA)) {
                    nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmAprovador.NIVEL_APROVADOR, "GERENTE_AREA", Filtro.EQUAL)));
                    List<Integer> centroIds = workflowDAO.getCentrosByWorkflowGerenteAreaUsuarioId(usuario.getUsuarioId());
                    List<Integer> areaIds = workflowDAO.getAreasByWorkflowGerenteAreaUsuarioId(usuario.getUsuarioId());

                    NxCriterion nxCentro = null;
                    for (Integer centro : centroIds) {
                        nxCentro = NxCriterion.or(nxCentro, NxCriterion.montaRestriction(new Filtro(Centro.CENTRO_ID, centro, Filtro.EQUAL, aliasCentro)));
                    }
                    nxCriterion = NxCriterion.and(nxCriterion, nxCentro);

                    NxCriterion nxArea = null;
                    for (Integer area : areaIds) {
                        nxArea = NxCriterion.or(nxArea, NxCriterion.montaRestriction(new Filtro(Area.AREA_ID, area, Filtro.EQUAL, aliasArea)));
                    }
                    nxCriterion = NxCriterion.and(nxCriterion, nxArea);
                } else if (r.getNome().equals(Role.ROLE_APROVADOR_CUSTO)) {
                    nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmAprovador.NIVEL_APROVADOR, "CUSTO", Filtro.EQUAL)));
                    List<Integer> centroIds = workflowDAO.getCentrosByWorkflowCustoUsuarioId(usuario.getUsuarioId());
                    List<Integer> areaIds = workflowDAO.getAreasByWorkflowCustoUsuarioId(usuario.getUsuarioId());

                    NxCriterion nxCentro = null;
                    for (Integer centro : centroIds) {
                        nxCentro = NxCriterion.or(nxCentro, NxCriterion.montaRestriction(new Filtro(Centro.CENTRO_ID, centro, Filtro.EQUAL, aliasCentro)));
                    }
                    nxCriterion = NxCriterion.and(nxCriterion, nxCentro);

                    NxCriterion nxArea = null;
                    for (Integer area : areaIds) {
                        nxArea = NxCriterion.or(nxArea, NxCriterion.montaRestriction(new Filtro(Area.AREA_ID, area, Filtro.EQUAL, aliasArea)));
                    }
                    nxCriterion = NxCriterion.and(nxCriterion, nxArea);
                } else if (r.getNome().equals(Role.ROLE_APROVADOR_EMERGENCIAL)) {
                    nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmAprovador.NIVEL_APROVADOR, "EMERGENCIAL", Filtro.EQUAL)));
                    List<Integer> centroIds = workflowDAO.getCentrosByWorkflowEmergencialUsuarioId(usuario.getUsuarioId());
                    List<Integer> areaIds = workflowDAO.getAreasByWorkflowEmergencialUsuarioId(usuario.getUsuarioId());

                    NxCriterion nxCentro = null;
                    for (Integer centro : centroIds) {
                        nxCentro = NxCriterion.or(nxCentro, NxCriterion.montaRestriction(new Filtro(Centro.CENTRO_ID, centro, Filtro.EQUAL, aliasCentro)));
                    }
                    nxCriterion = NxCriterion.and(nxCriterion, nxCentro);

                    NxCriterion nxArea = null;
                    for (Integer area : areaIds) {
                        nxArea = NxCriterion.or(nxArea,
                                        NxCriterion.montaRestriction(new Filtro(Area.AREA_ID, area, Filtro.EQUAL, aliasArea)));
                    }
                    nxCriterion = NxCriterion.and(nxCriterion, nxArea);
                }
            }
        } else {
            nxCriterion = NxCriterion.and(nxCriterion,
                            NxCriterion.montaRestriction(new Filtro(RmAprovador.NIVEL_APROVADOR, null, Filtro.NOT_NULL)));
        }

        List<NxOrder> orders = Arrays.asList(new NxOrder(RmAprovador.RM_ID, NxOrder.NX_ORDER.ASC));

        try {
            Paginacao.build(propriedades, orders, RmAprovador.class, nxCriterion, filtro.getPaginacaoVo());
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return filtro.getPaginacaoVo();
    }

    /**
     * Persiste a resposta do aprovador e da continuidade ao fluxo de aprovacao
     * da rm conforme a resposta e o tipoAtuacao do aprovador
     *
     * @param rmAprovadorVo
     * @return
     */
    @POST
    @Path("aprovarReprovar")
    @Consumes("application/json")
    public Info aprovarReprovar(RmAprovadorVo rmAprovadorVo) {
        rmAprovadorVo.getRmAprovador().getNivelAprovador();
        WorkflowEnum proximaEtapa = null;
        String status = null;
        Info info = new Info();
        GenericDao<RmAprovador> genericDao = new GenericDao<>();
        GenericDao<RmAprovador> genericDaoAlteraAvaliacao = new GenericDao<>();

        RmaService rmaService = new RmaService(request);
        String itensNaoOrcamentados = null;
        Integer usuarioId = LoginService.getUsuarioLogado(request).getUsuarioId();

        //E necessario listar estes objetos antes de iniciar a transaction
        List<RmMaterialStatus> materialStatus = new ArrayList<>();

        //Preenche lista que deve ser considerada para realizar os fluxos de aprovações
        List<VwRmMaterial> materiaisAprovacao = new ArrayList<>();
        for (VwRmMaterial vwRmMaterial : rmAprovadorVo.getListaRmMaterial()) {
            if (vwRmMaterial.getRmMaterial().getDataCancelamento() == null) {
                materiaisAprovacao.add(vwRmMaterial);
            }
        }
        rmAprovadorVo.setListaRmMaterial(materiaisAprovacao);

        for (VwRmMaterial vwRmMaterial : rmAprovadorVo.getListaRmMaterial()) {
            materialStatus.add(vwRmMaterial.getRmMaterialStatus());

            if (vwRmMaterial.getRmMaterial().getEstaNoOrcamento() != null
              && vwRmMaterial.getRmMaterial().getEstaNoOrcamento().equals(Constantes.NAO_ABREVIADO)) {
                if (itensNaoOrcamentados == null) {
                    itensNaoOrcamentados = "";
                }

                itensNaoOrcamentados += "\n" + vwRmMaterial.getRmMaterial().getMaterial().getCodigo() + " - " + vwRmMaterial.getRmMaterial().getMaterial().getNome();
            }
        }

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), RmAprovadorVo.class.getName(), Util.getNomeMetodoAtual(), rmAprovadorVo, null));

            FiltroRmAprovacao filtro = new FiltroRmAprovacao();
            filtro.setRmAprovador(rmAprovadorVo.getRmAprovador());
            filtro.setAprovadoresDaVez(Boolean.TRUE);

            String nivel = "";
            String perfil = LoginService.getUsuarioLogado(request).getPerfil().getNome();
            Usuario usuario = LoginService.getUsuarioLogado(request);

            if (perfil.equals("Aprovador Area")) {
                nivel = "Aprovador de Area";
            } else if (perfil.equals("Aprovador Gerente Area")) {
                nivel = "Aprovador Gerente de Area";
            } else if (perfil.equals("Aprovador Emergencial")) {
                nivel = "Aprovador Emergencial";
            } else if (perfil.equals("Aprovador Custo")) {
                nivel = "Aprovador de Custo";

            }


            //            List<Role> roles = LoginService.getUsuarioLogado(request).getRoles();
            //            for(Role r : roles) {
            //            	if(r.getNome().equals(Role.ROLE_APROVADOR_AREA)) {
            //            		nivel = "Aprovador de Area";
            //            	}else if(r.getNome().equals(Role.ROLE_APROVADOR_GERENTE_AREA)) {
            //            		nivel = "Aprovador Gerente de Area";
            //            	}else if(r.getNome().equals(Role.ROLE_APROVADOR_CUSTO)) {
            //            		nivel = "Aprovador de Custo";
            //            	}else if(r.getNome().equals(Role.ROLE_APROVADOR_EMERGENCIAL)) {
            //            		nivel = "Aprovador Emergencial";
            //            	}
            //            }

            if (rmAprovadorVo.getRmAprovador().getAprovada()) {
                rastroService.rastrearAprovado(rmAprovadorVo.getRmAprovador().getRm(), LoginService.getUsuarioLogado(request).getLogin(), nivel);
            } else {
                rastroService.rastrearReprovado(rmAprovadorVo.getRmAprovador().getRm(), LoginService.getUsuarioLogado(request).getLogin(), nivel);
            }

            Prioridade prioridade = new PrioridadeDao().getPrioridadeId(rmAprovadorVo.getRmAprovador().getRm().getPrioridade().getPrioridadeId());

            Date dataAprovacao = new Date();

            if (rmAprovadorVo.getRmAprovador().getAprovada()) {

                Rm rm = rmAprovadorVo.getRmAprovador().getRm();
                proximaEtapa = workflowService.getNextStep(rm, WorkflowEnum.getByStep(rmAprovadorVo.getRmAprovador().getNivelAprovador()));

                if (perfil.equals("Aprovador Gerente Area")) {
                    //TODO: verificar o fluxo de gerente com perfil de aprovador
                    if (workflowService.getPerfilAprovador(usuario) != null && proximaEtapa != null && proximaEtapa.equals(WorkflowEnum.WORKFLOW_CUSTO)) {

                        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);
                        RmMaterialStatus rmMaterialStatusAtual = new RmMaterialStatus();
                        rmAprovadorVo.getRmAprovador().setNivelAprovador(proximaEtapa.getStep());
                        genericDao.update(rmAprovadorVo.getRmAprovador());
                        status = Constantes.STATUS_RM_MATERIAL_APROVADA_GC;
                        genericDao.beginTransaction();
                        Date data = new Date();
                        List<RmMaterial> listaRmMaterial = rmaService.listarRmMateriais(rmAprovadorVo.getRmAprovador().getRm());
                        List<RmMaterialStatus> materiaisStatusAtual = rmMaterialStatusService.listarStatusAtual(listaRmMaterial);
                        for (RmMaterial material : listaRmMaterial) {
                            rmMaterialStatusAtual = rmMaterialStatusService.listarStatusAtual(materiaisStatusAtual, material);
                            rmMaterialStatusService.finalizarStatus(rmMaterialStatusAtual, genericDao, data);
                            rmMaterialStatusService.gerarStatus(material, status, genericDao, null, null, null);
                        }

                        genericDao.commitCurrentTransaction();

                        Workflow workflow = workflowService.getWorkflowRm(rm.getArea().getAreaId(), rm.getCentro().getCentroId());
                        Pessoa proximoAprovador = workflowService.getAprovadorRm(workflow, proximaEtapa);
                        notificar(rmAprovadorVo.getRmAprovador(), proximaEtapa, proximoAprovador.getEmail());
                        proximaEtapa = null;
                    } else if (("NORMAL".equals(prioridade.getCodigo()) || "URGENTE".equals(prioridade.getCodigo())) && proximaEtapa == null) {
                        status = Constantes.STATUS_APROVADO;
                        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);
                        RmMaterialStatus rmMaterialStatusAtual = new RmMaterialStatus();
                        rmAprovadorVo.getRmAprovador().setNivelAprovador(null);
                        genericDao.update(rmAprovadorVo.getRmAprovador());
                        genericDao.beginTransaction();
                        Date data = new Date();
                        List<RmMaterial> listaRmMaterial = rmaService.listarRmMateriais(rmAprovadorVo.getRmAprovador().getRm());
                        List<RmMaterialStatus> materiaisStatusAtual = rmMaterialStatusService.listarStatusAtual(listaRmMaterial);
                        for (RmMaterial material : listaRmMaterial) {
                            rmMaterialStatusAtual = rmMaterialStatusService.listarStatusAtual(materiaisStatusAtual, material);
                            rmMaterialStatusService.finalizarStatus(rmMaterialStatusAtual, genericDao, data);
                            rmMaterialStatusService.gerarStatus(material, status, genericDao, data, null, null);
                        }
                        atualizarDataNecessidade(rmAprovadorVo.getRmAprovador().getRm(), data);
                        genericDao.commitCurrentTransaction();

                        notificarAprovacaoReprovacao(rmAprovadorVo, null);
                    } else {
                        Workflow workflow = workflowService.getWorkflowRm(rm.getArea().getAreaId(), rm.getCentro().getCentroId());
                        //                        proximaEtapa = workflowService.getProximaEtapaRm(workflow, rmAprovadorVo.getRmAprovador().getNivelAprovador(), rmAprovadorVo.getRmAprovador().getRm(), usuarioId);
                        proximaEtapa = workflowService.getNextStep(rm, WorkflowEnum.getByStep(rmAprovadorVo.getRmAprovador().getNivelAprovador()));
                    }

                } else if (perfil.equals("Aprovador Custo")) {
                    if (("NORMAL".equals(prioridade.getCodigo()) || "URGENTE".equals(prioridade.getCodigo())) && proximaEtapa == null) {
                        status = Constantes.STATUS_APROVADO;
                        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);
                        RmMaterialStatus rmMaterialStatusAtual = new RmMaterialStatus();
                        rmAprovadorVo.getRmAprovador().setNivelAprovador(null);
                        genericDao.update(rmAprovadorVo.getRmAprovador());
                        genericDao.beginTransaction();
                        Date data = new Date();
                        List<RmMaterial> listaRmMaterial = rmaService.listarRmMateriais(rmAprovadorVo.getRmAprovador().getRm());
                        List<RmMaterialStatus> materiaisStatusAtual = rmMaterialStatusService.listarStatusAtual(listaRmMaterial);
                        for (RmMaterial material : listaRmMaterial) {
                            rmMaterialStatusAtual = rmMaterialStatusService.listarStatusAtual(materiaisStatusAtual, material);
                            rmMaterialStatusService.finalizarStatus(rmMaterialStatusAtual, genericDao, data);
                            rmMaterialStatusService.gerarStatus(material, status, genericDao, data, null, null);
                        }
                        atualizarDataNecessidade(rmAprovadorVo.getRmAprovador().getRm(), data);

                        genericDao.commitCurrentTransaction();

                        notificarAprovacaoReprovacao(rmAprovadorVo, null);
                    } else if(proximaEtapa.equals(WorkflowEnum.WORKFLOW_EMERGENCIAL)) {
                        proximaEtapa = WorkflowEnum.WORKFLOW_EMERGENCIAL;
                        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);
                        RmMaterialStatus rmMaterialStatusAtual = new RmMaterialStatus();
                        rmAprovadorVo.getRmAprovador().setNivelAprovador(proximaEtapa.getStep());
                        genericDao.update(rmAprovadorVo.getRmAprovador());
                        status = Constantes.STATUS_RM_MATERIAL_APROVADA_E;
                        genericDao.beginTransaction();
                        Date data = new Date();
                        List<RmMaterial> listaRmMaterial = rmaService.listarRmMateriais(rmAprovadorVo.getRmAprovador().getRm());
                        List<RmMaterialStatus> materiaisStatusAtual = rmMaterialStatusService.listarStatusAtual(listaRmMaterial);
                        for (RmMaterial material : listaRmMaterial) {
                            rmMaterialStatusAtual = rmMaterialStatusService.listarStatusAtual(materiaisStatusAtual, material);
                            rmMaterialStatusService.finalizarStatus(rmMaterialStatusAtual, genericDao, data);
                            rmMaterialStatusService.gerarStatus(material, status, genericDao, null, null, null);
                        }

                        genericDao.commitCurrentTransaction();

                        Workflow workflow = workflowService.getWorkflowRm(rm.getArea().getAreaId(), rm.getCentro().getCentroId());
                        Pessoa proximoAprovador = workflowService.getAprovadorRm(workflow, proximaEtapa);
                        notificar(rmAprovadorVo.getRmAprovador(), proximaEtapa, proximoAprovador.getEmail());
                        proximaEtapa = null;
                    }
                } else if (perfil.equals("Aprovador Emergencial")) {
                    status = Constantes.STATUS_APROVADO;
                    RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);
                    RmMaterialStatus rmMaterialStatusAtual = new RmMaterialStatus();
                    rmAprovadorVo.getRmAprovador().setNivelAprovador(null);
                    genericDao.update(rmAprovadorVo.getRmAprovador());
                    genericDao.beginTransaction();
                    Date data = new Date();
                    List<RmMaterial> listaRmMaterial = rmaService.listarRmMateriais(rmAprovadorVo.getRmAprovador().getRm());
                    List<RmMaterialStatus> materiaisStatusAtual = rmMaterialStatusService.listarStatusAtual(listaRmMaterial);
                    for (RmMaterial material : listaRmMaterial) {
                        rmMaterialStatusAtual = rmMaterialStatusService.listarStatusAtual(materiaisStatusAtual, material);
                        rmMaterialStatusService.finalizarStatus(rmMaterialStatusAtual, genericDao, data);
                        rmMaterialStatusService.gerarStatus(material, status, genericDao, new Date(), null, null);
                    }
                    atualizarDataNecessidade(rmAprovadorVo.getRmAprovador().getRm(), data);
                    genericDao.commitCurrentTransaction();

                    notificarAprovacaoReprovacao(rmAprovadorVo, null);
                } else if (perfil.equals("Aprovador Area")) {
                    Workflow workflow = workflowService.getWorkflowRm(rm.getArea().getAreaId(), rm.getCentro().getCentroId());
                    //                    proximaEtapa = workflowService.getProximaEtapaRm(workflow, rmAprovadorVo.getRmAprovador().getNivelAprovador(), rmAprovadorVo.getRmAprovador().getRm(), usuarioId);
                    proximaEtapa = workflowService.getNextStep(rm, WorkflowEnum.getByStep(rmAprovadorVo.getRmAprovador().getNivelAprovador()));
                }
            }


            if (proximaEtapa != null && rmAprovadorVo.getRmAprovador().getAprovada()) {
                RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);
                RmMaterialStatus rmMaterialStatusAtual = new RmMaterialStatus();
                rmAprovadorVo.getRmAprovador().setNivelAprovador(proximaEtapa.getStep());
                genericDao.update(rmAprovadorVo.getRmAprovador());

                Rm rm = rmAprovadorVo.getRmAprovador().getRm();
                Workflow workflow = workflowService.getWorkflowRm(rm.getArea().getAreaId(), rm.getCentro().getCentroId());
                Pessoa proximoAprovador = workflowService.getAprovadorRm(workflow, proximaEtapa);

                if (proximaEtapa == WorkflowEnum.WORKFLOW_EMERGENCIAL && perfil.equals("Aprovador Emergencial")) {
                    status = Constantes.STATUS_APROVADO;
                } else if (proximaEtapa == WorkflowEnum.WORKFLOW_AREA) {
                    status = Constantes.STATUS_RM_MATERIAL_APROVADA_A;
                    notificar(rmAprovadorVo.getRmAprovador(), proximaEtapa, proximoAprovador.getEmail());
                } else if (proximaEtapa == WorkflowEnum.WORKFLOW_GERENTE_AREA) {
                    status = Constantes.STATUS_RM_MATERIAL_APROVADA_GA;
                    notificar(rmAprovadorVo.getRmAprovador(), proximaEtapa, proximoAprovador.getEmail());
                } else if (proximaEtapa == WorkflowEnum.WORKFLOW_CUSTO) {
                    status = Constantes.STATUS_RM_MATERIAL_APROVADA_GC;
                } else if (proximaEtapa == WorkflowEnum.WORKFLOW_EMERGENCIAL) {
                    status = Constantes.STATUS_RM_MATERIAL_APROVADA_E;
                    notificar(rmAprovadorVo.getRmAprovador(), proximaEtapa, proximoAprovador.getEmail());
                }

                genericDao.beginTransaction();
                Date data = new Date();
                List<RmMaterial> listaRmMaterial = rmaService.listarRmMateriais(rmAprovadorVo.getRmAprovador().getRm());
                List<RmMaterialStatus> materiaisStatusAtual = rmMaterialStatusService.listarStatusAtual(listaRmMaterial);
                for (RmMaterial material : listaRmMaterial) {
                    rmMaterialStatusAtual = rmMaterialStatusService.listarStatusAtual(materiaisStatusAtual, material);
                    rmMaterialStatusService.finalizarStatus(rmMaterialStatusAtual, genericDao, data);
                    rmMaterialStatusService.gerarStatus(material, status, genericDao, null, null, null);
                }
                genericDao.commitCurrentTransaction();
            } else {
                List<RmMaterial> listaRmMaterial = rmaService.listarRmMateriais(rmAprovadorVo.getRmAprovador().getRm());
                rmaService.verificaHierarquia(listaRmMaterial);
            }

            if (rmAprovadorVo.getRmAprovador() != null && !rmAprovadorVo.getRmAprovador().getAprovada()) {

                List<Propriedade> props = new ArrayList<>();
                rmAprovadorVo.getRmAprovador().setAprovadorVez(false);
                props.add(new Propriedade(rmAprovadorVo.getRmAprovador().APROVADOR_VEZ));
                genericDaoAlteraAvaliacao.update(rmAprovadorVo.getRmAprovador(), props);

                RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);
                RmMaterialStatus rmMaterialStatusAtual = new RmMaterialStatus();
                genericDao.beginTransaction();
                Date data = new Date();
                List<RmMaterial> listaRmMaterial = rmaService.listarRmMateriais(rmAprovadorVo.getRmAprovador().getRm());
                List<RmMaterialStatus> materiaisStatusAtual = rmMaterialStatusService.listarStatusAtual(listaRmMaterial);
                status = Constantes.STATUS_RM_MATERIAL_REPROVADA;
                for (RmMaterial material : listaRmMaterial) {
                    rmMaterialStatusAtual = rmMaterialStatusService.listarStatusAtual(materiaisStatusAtual, material);
                    rmMaterialStatusService.finalizarStatus(rmMaterialStatusAtual, genericDao, data);
                    rmMaterialStatusService.gerarStatus(material, status, genericDao, null, null, null);
                }
                genericDao.commitCurrentTransaction();
                rmaService.verificaHierarquia(listaRmMaterial);

                notificarAprovacaoReprovacao(rmAprovadorVo, WorkflowEnum.WORKFLOW_REPROVADO);
            }

            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setErro(null);
            info.setObjeto(rmAprovadorVo.getRmAprovador());

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), RmAprovadorVo.class.getName(), Util.getNomeMetodoAtual(), rmAprovadorVo, null));
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setMensagem(Constantes.ERRO_OPERACAO_I18N);
            info.setErro(e.getMessage());
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }


    
    //HM
    @POST
    @Path("aprovarReprovarServico")
    @Consumes("application/json")
    public Info aprovarReprovarServico(RmAprovadorVo rmAprovadorVo) {
        rmAprovadorVo.getRmAprovador().getNivelAprovador();
        WorkflowEnum proximaEtapa = null;
        String status = null;
        Info info = new Info();
        GenericDao<RmAprovador> genericDao = new GenericDao<>();
        GenericDao<RmAprovador> genericDaoAlteraAvaliacao = new GenericDao<>();

        RmaServicoService rmaService = new RmaServicoService(request);

        Integer usuarioId = LoginService.getUsuarioLogado(request).getUsuarioId();

        //E necessario listar estes objetos antes de iniciar a transaction
        List<RmServicoStatus> servicoStatus = new ArrayList<>();

        //Preenche lista que deve ser considerada para realizar os fluxos de aprovações
        RmServico servicosAprovacao = new RmServico(rmAprovadorVo.getRmServico());
        
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), RmAprovadorVo.class.getName(), Util.getNomeMetodoAtual(), rmAprovadorVo, null));

            FiltroRmAprovacao filtro = new FiltroRmAprovacao();
            filtro.setRmAprovador(rmAprovadorVo.getRmAprovador());
            filtro.setAprovadoresDaVez(Boolean.TRUE);

            String nivel = "";
            String perfil = LoginService.getUsuarioLogado(request).getPerfil().getNome();
            Usuario usuario = LoginService.getUsuarioLogado(request);
            
            System.out.println("************** ---- " + perfil + "************** -----");

            if (perfil.equals("Aprovador Area")) {
                nivel = "Aprovador de Area";
            } else if (perfil.equals("Aprovador Gerente Area")) {
                nivel = "Aprovador Gerente de Area";
            } else if (perfil.equals("Aprovador Emergencial")) {
                nivel = "Aprovador Emergencial";
            } else if (perfil.equals("Aprovador Custo")) {
                nivel = "Aprovador de Custo";

            }


            //            List<Role> roles = LoginService.getUsuarioLogado(request).getRoles();
            //            for(Role r : roles) {
            //            	if(r.getNome().equals(Role.ROLE_APROVADOR_AREA)) {
            //            		nivel = "Aprovador de Area";
            //            	}else if(r.getNome().equals(Role.ROLE_APROVADOR_GERENTE_AREA)) {
            //            		nivel = "Aprovador Gerente de Area";
            //            	}else if(r.getNome().equals(Role.ROLE_APROVADOR_CUSTO)) {
            //            		nivel = "Aprovador de Custo";
            //            	}else if(r.getNome().equals(Role.ROLE_APROVADOR_EMERGENCIAL)) {
            //            		nivel = "Aprovador Emergencial";
            //            	}
            //            }

            if (rmAprovadorVo.getRmAprovador().getAprovada()) {
                rastroService.rastrearServicoAprovado(rmAprovadorVo.getRmAprovador().getRm(), LoginService.getUsuarioLogado(request).getLogin(), nivel);
            } else {
                rastroService.rastrearServicoReprovado(rmAprovadorVo.getRmAprovador().getRm(), LoginService.getUsuarioLogado(request).getLogin(), nivel);
            }

            Prioridade prioridade = new PrioridadeDao().getPrioridadeId(rmAprovadorVo.getRmAprovador().getRm().getPrioridade().getPrioridadeId());

            Date dataAprovacao = new Date();

            if (rmAprovadorVo.getRmAprovador().getAprovada()) {

                Rm rm = rmAprovadorVo.getRmAprovador().getRm();
                proximaEtapa = workflowService.getNextStep(rm, WorkflowEnum.getByStep(rmAprovadorVo.getRmAprovador().getNivelAprovador()));

                if (perfil.equals("Aprovador Gerente Area")) {
                    //TODO: verificar o fluxo de gerente com perfil de aprovador
                    if (workflowService.getPerfilAprovador(usuario) != null && proximaEtapa != null && proximaEtapa.equals(WorkflowEnum.WORKFLOW_CUSTO)) {

                        RmServicoStatusService rmServicoStatusService = new RmServicoStatusService(request);
                        RmServicoStatus rmServicoStatusAtual = new RmServicoStatus();
                        rmAprovadorVo.getRmAprovador().setNivelAprovador(proximaEtapa.getStep());
                        genericDao.update(rmAprovadorVo.getRmAprovador());
                        status = Constantes.STATUS_RM_MATERIAL_APROVADA_GC;
                        genericDao.beginTransaction();
                        Date data = new Date();
                        List<RmServico> listaRmServico = rmaService.listarRmServicos(rmAprovadorVo.getRmAprovador().getRm());
                        List<RmServicoStatus> servicosStatusAtual = rmServicoStatusService.listarStatusAtualServico(listaRmServico);
                        for (RmServico servico : listaRmServico) {
                            rmServicoStatusAtual = rmServicoStatusService.listarStatusAtualServico(servicosStatusAtual, servico);
                            rmServicoStatusService.finalizarStatus(rmServicoStatusAtual, genericDao, data);
                            rmServicoStatusService.gerarStatus(servico, status, genericDao, data, null, null);
                        }

                        genericDao.commitCurrentTransaction();

                        Workflow workflow = workflowService.getWorkflowRm(rm.getArea().getAreaId(), rm.getCentro().getCentroId());
                        Pessoa proximoAprovador = workflowService.getAprovadorRm(workflow, proximaEtapa);
                        notificar(rmAprovadorVo.getRmAprovador(), proximaEtapa, proximoAprovador.getEmail());
                        proximaEtapa = null;
                    } else if (("NORMAL".equals(prioridade.getCodigo()) || "URGENTE".equals(prioridade.getCodigo())) && proximaEtapa == null) {
                        status = Constantes.STATUS_APROVADO;
                        RmServicoStatusService rmServicoStatusService = new RmServicoStatusService(request);
                        RmServicoStatus rmServicoStatusAtual = new RmServicoStatus();
                        rmAprovadorVo.getRmAprovador().setNivelAprovador(null);
                        genericDao.update(rmAprovadorVo.getRmAprovador());
                        genericDao.beginTransaction();
                        Date data = new Date();
                        
                        List<RmServico> listaRmServico = rmaService.listarRmServicos(rmAprovadorVo.getRmAprovador().getRm());
                        List<RmServicoStatus> servicosStatusAtual = rmServicoStatusService.listarStatusAtualServico(listaRmServico);
                        for (RmServico servico : listaRmServico) {
                            rmServicoStatusAtual = rmServicoStatusService.listarStatusAtualServico(servicosStatusAtual, servico);
                            rmServicoStatusService.finalizarStatus(rmServicoStatusAtual, genericDao, data);
                            rmServicoStatusService.gerarStatus(servico, status, genericDao, data, null, null);
                        }
                        atualizarDataNecessidade(rmAprovadorVo.getRmAprovador().getRm(), data);
                        genericDao.commitCurrentTransaction();

                        notificarAprovacaoReprovacao(rmAprovadorVo, null);
                    } else {
                        Workflow workflow = workflowService.getWorkflowRm(rm.getArea().getAreaId(), rm.getCentro().getCentroId());
                        //                        proximaEtapa = workflowService.getProximaEtapaRm(workflow, rmAprovadorVo.getRmAprovador().getNivelAprovador(), rmAprovadorVo.getRmAprovador().getRm(), usuarioId);
                        proximaEtapa = workflowService.getNextStep(rm, WorkflowEnum.getByStep(rmAprovadorVo.getRmAprovador().getNivelAprovador()));
                    }

                } else if (perfil.equals("Aprovador Custo")) {
                    if (("NORMAL".equals(prioridade.getCodigo()) || "URGENTE".equals(prioridade.getCodigo())) && proximaEtapa == null) {
                        status = Constantes.STATUS_APROVADO;
                        RmServicoStatusService rmServicoStatusService = new RmServicoStatusService(request);
                        RmServicoStatus rmServicoStatusAtual = new RmServicoStatus();
                        rmAprovadorVo.getRmAprovador().setNivelAprovador(null);
                        genericDao.update(rmAprovadorVo.getRmAprovador());
                        genericDao.beginTransaction();
                        Date data = new Date();
                        List<RmServico> listaRmServico = rmaService.listarRmServicos(rmAprovadorVo.getRmAprovador().getRm());
                        List<RmServicoStatus> servicosStatusAtual = rmServicoStatusService.listarStatusAtualServico(listaRmServico);
                        for (RmServico servico : listaRmServico) {
                            rmServicoStatusAtual = rmServicoStatusService.listarStatusAtualServico(servicosStatusAtual, servico);
                            rmServicoStatusService.finalizarStatus(rmServicoStatusAtual, genericDao, data);
                            rmServicoStatusService.gerarStatus(servico, status, genericDao, data, null, null);
                        }
                        atualizarDataNecessidade(rmAprovadorVo.getRmAprovador().getRm(), data);

                        genericDao.commitCurrentTransaction();

                        notificarAprovacaoReprovacao(rmAprovadorVo, null);
                    } else if(proximaEtapa.equals(WorkflowEnum.WORKFLOW_EMERGENCIAL)) {
                        proximaEtapa = WorkflowEnum.WORKFLOW_EMERGENCIAL;
                        RmServicoStatusService rmServicoStatusService = new RmServicoStatusService(request);
                        RmServicoStatus rmServicoStatusAtual = new RmServicoStatus();
                        rmAprovadorVo.getRmAprovador().setNivelAprovador(proximaEtapa.getStep());
                        genericDao.update(rmAprovadorVo.getRmAprovador());
                        status = Constantes.STATUS_RM_MATERIAL_APROVADA_E;
                        genericDao.beginTransaction();
                        Date data = new Date();
                        List<RmServico> listaRmServico = rmaService.listarRmServicos(rmAprovadorVo.getRmAprovador().getRm());
                        List<RmServicoStatus> servicosStatusAtual = rmServicoStatusService.listarStatusAtualServico(listaRmServico);
                        for (RmServico servico : listaRmServico) {
                            rmServicoStatusAtual = rmServicoStatusService.listarStatusAtualServico(servicosStatusAtual, servico);
                            rmServicoStatusService.finalizarStatus(rmServicoStatusAtual, genericDao, data);
                            rmServicoStatusService.gerarStatus(servico, status, genericDao, data, null, null);
                        }

                        genericDao.commitCurrentTransaction();

                        Workflow workflow = workflowService.getWorkflowRm(rm.getArea().getAreaId(), rm.getCentro().getCentroId());
                        Pessoa proximoAprovador = workflowService.getAprovadorRm(workflow, proximaEtapa);
                        notificar(rmAprovadorVo.getRmAprovador(), proximaEtapa, proximoAprovador.getEmail());
                        proximaEtapa = null;
                    }
                } else if (perfil.equals("Aprovador Emergencial")) {
                    status = Constantes.STATUS_APROVADO;
                    RmServicoStatusService rmServicoStatusService = new RmServicoStatusService(request);
                    RmServicoStatus rmServicoStatusAtual = new RmServicoStatus();
                    rmAprovadorVo.getRmAprovador().setNivelAprovador(null);
                    genericDao.update(rmAprovadorVo.getRmAprovador());
                    genericDao.beginTransaction();
                    Date data = new Date();
                    List<RmServico> listaRmServico = rmaService.listarRmServicos(rmAprovadorVo.getRmAprovador().getRm());
                    List<RmServicoStatus> servicosStatusAtual = rmServicoStatusService.listarStatusAtualServico(listaRmServico);
                    for (RmServico servico : listaRmServico) {
                        rmServicoStatusAtual = rmServicoStatusService.listarStatusAtualServico(servicosStatusAtual, servico);
                        rmServicoStatusService.finalizarStatus(rmServicoStatusAtual, genericDao, data);
                        rmServicoStatusService.gerarStatus(servico, status, genericDao, data, null, null);
                    }
                    atualizarDataNecessidade(rmAprovadorVo.getRmAprovador().getRm(), data);
                    genericDao.commitCurrentTransaction();

                    notificarAprovacaoReprovacao(rmAprovadorVo, null);
                } else if (perfil.equals("Aprovador Area")) {
                    Workflow workflow = workflowService.getWorkflowRm(rm.getArea().getAreaId(), rm.getCentro().getCentroId());
                    //                    proximaEtapa = workflowService.getProximaEtapaRm(workflow, rmAprovadorVo.getRmAprovador().getNivelAprovador(), rmAprovadorVo.getRmAprovador().getRm(), usuarioId);
                    proximaEtapa = workflowService.getNextStep(rm, WorkflowEnum.getByStep(rmAprovadorVo.getRmAprovador().getNivelAprovador()));
                }
            }


            if (proximaEtapa != null && rmAprovadorVo.getRmAprovador().getAprovada()) {
                RmServicoStatusService rmServicoStatusService = new RmServicoStatusService(request);
                RmServicoStatus rmServicoStatusAtual = new RmServicoStatus();
                rmAprovadorVo.getRmAprovador().setNivelAprovador(proximaEtapa.getStep());
                genericDao.update(rmAprovadorVo.getRmAprovador());

                Rm rm = rmAprovadorVo.getRmAprovador().getRm();
                Workflow workflow = workflowService.getWorkflowRm(rm.getArea().getAreaId(), rm.getCentro().getCentroId());
                Pessoa proximoAprovador = workflowService.getAprovadorRm(workflow, proximaEtapa);

                if (proximaEtapa == WorkflowEnum.WORKFLOW_EMERGENCIAL && perfil.equals("Aprovador Emergencial")) {
                    status = Constantes.STATUS_APROVADO;
                } else if (proximaEtapa == WorkflowEnum.WORKFLOW_AREA) {
                    status = Constantes.STATUS_RM_MATERIAL_APROVADA_A;
                    notificar(rmAprovadorVo.getRmAprovador(), proximaEtapa, proximoAprovador.getEmail());
                } else if (proximaEtapa == WorkflowEnum.WORKFLOW_GERENTE_AREA) {
                    status = Constantes.STATUS_RM_MATERIAL_APROVADA_GA;
                    notificar(rmAprovadorVo.getRmAprovador(), proximaEtapa, proximoAprovador.getEmail());
                } else if (proximaEtapa == WorkflowEnum.WORKFLOW_CUSTO) {
                    status = Constantes.STATUS_RM_MATERIAL_APROVADA_GC;
                } else if (proximaEtapa == WorkflowEnum.WORKFLOW_EMERGENCIAL) {
                    status = Constantes.STATUS_RM_MATERIAL_APROVADA_E;
                    notificar(rmAprovadorVo.getRmAprovador(), proximaEtapa, proximoAprovador.getEmail());
                }

                genericDao.beginTransaction();
                Date data = new Date();
                List<RmServico> listaRmServico = rmaService.listarRmServicos(rmAprovadorVo.getRmAprovador().getRm());
                List<RmServicoStatus> servicosStatusAtual = rmServicoStatusService.listarStatusAtualServico(listaRmServico);
                for (RmServico servico : listaRmServico) {
                    rmServicoStatusAtual = rmServicoStatusService.listarStatusAtualServico(servicosStatusAtual, servico);
                    rmServicoStatusService.finalizarStatus(rmServicoStatusAtual, genericDao, data);
                    rmServicoStatusService.gerarStatus(servico, status, genericDao, data, null, null);
                }
                genericDao.commitCurrentTransaction();
            } else {
                List<RmServico> listaRmServico = rmaService.listarRmServicos(rmAprovadorVo.getRmAprovador().getRm());
                rmaService.verificaHierarquiaServico(listaRmServico);
            }

            if (rmAprovadorVo.getRmAprovador() != null && !rmAprovadorVo.getRmAprovador().getAprovada()) {

                List<Propriedade> props = new ArrayList<>();
                rmAprovadorVo.getRmAprovador().setAprovadorVez(false);
                props.add(new Propriedade(rmAprovadorVo.getRmAprovador().APROVADOR_VEZ));
                genericDaoAlteraAvaliacao.update(rmAprovadorVo.getRmAprovador(), props);

                RmServicoStatusService rmServicoStatusService = new RmServicoStatusService(request);
                RmServicoStatus rmServicoStatusAtual = new RmServicoStatus();
                genericDao.beginTransaction();
                Date data = new Date();
                List<RmServico> listaRmServico = rmaService.listarRmServicos(rmAprovadorVo.getRmAprovador().getRm());
                List<RmServicoStatus> servicosStatusAtual = rmServicoStatusService.listarStatusAtualServico(listaRmServico);
                status = Constantes.STATUS_RM_MATERIAL_REPROVADA;
                for (RmServico servico : listaRmServico) {
                    rmServicoStatusAtual = rmServicoStatusService.listarStatusAtualServico(servicosStatusAtual, servico);
                    rmServicoStatusService.finalizarStatus(rmServicoStatusAtual, genericDao, data);
                    rmServicoStatusService.gerarStatus(servico, status, genericDao, data, null, null);
                }
                genericDao.commitCurrentTransaction();
                rmaService.verificaHierarquiaServico(listaRmServico);

                notificarAprovacaoReprovacao(rmAprovadorVo, WorkflowEnum.WORKFLOW_REPROVADO);
            }

            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setErro(null);
            info.setObjeto(rmAprovadorVo.getRmAprovador());

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), RmAprovadorVo.class.getName(), Util.getNomeMetodoAtual(), rmAprovadorVo, null));
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setMensagem(Constantes.ERRO_OPERACAO_I18N);
            info.setErro(e.getMessage());
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    private void atualizarDataNecessidade(Rm rm, Date dataAprovacaoRm) throws Exception {
        /**
         * Calcular nova data necessidade.
         * DATA_NECESSIDADE menos a DATA EMISSAO == dias necessidade
         * DATA_APROVACAO + DIAS NECESSIDADE == NOVA DATA_NECESSIDADE
         */
        /**
         * Obter diferença entre dias DT_EMISSAO E DT_NECESSIDADE.
         */
        Integer diasNecessidade = Util.diferencaDias(rm.getDataEmissao(), rm.getDataAplicacao()) + 1;

        /**
         * Obter data de aprovação da RM e somar com a diferença entre dias da necessidade.
         */
        LocalDate dataAprovacao = this.convertToLocalDateViaInstant(dataAprovacaoRm);
        LocalDate dataNovaNecessidade = dataAprovacao.plusDays(diasNecessidade);
        Date newDataNecessidade = this.convertToDateViaInstant(dataNovaNecessidade);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        rmDao.atualizarDataNecessidadeRm(rm.getRmId().toString(), formatter.format(newDataNecessidade));
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
    }

    public Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
          .atZone(ZoneId.systemDefault())
          .toInstant());
    }

    private void notificarAprovacaoReprovacao(RmAprovadorVo rmAprovadorVo, WorkflowEnum status) throws SQLException {
        Rm rm = rmAprovadorVo.getRmAprovador().getRm();
        List<ObterPessoaPorPerfilECentro> pessoas = obterPessoaPorPerfilECentroDao.obterPessoaPorPerfilECentro(ALMOXARIFADO_PERFIL_ID, rm.getCentro().getCentroId());
        String emailRequisitante = rm.getRequisitante().getEmail();
        String emailUsuarioCadastrante = rm.getUsuario().getPessoa().getEmail();
        Set<String> emails = Optional.ofNullable(pessoas).orElse(Collections.emptyList()).stream().map(pessoa -> pessoa.getEmail()).collect(Collectors.toSet());
        emails.add(emailRequisitante);
        emails.add(emailUsuarioCadastrante);

        notificar(rmAprovadorVo.getRmAprovador(), status, emails.toArray(new String[]{}));
    }

    private void notificar(RmAprovador rmAprovador, WorkflowEnum proximaEtapa, String... emailsEmCopia) {
        try {
            String link;
            Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();

            if (configuracao.getPrefUrlExternaSist() != null && !configuracao.getPrefUrlExternaSist().isEmpty()) {
                link = configuracao.getPrefUrlExternaSist();
            } else if (request != null) {
                link = LoginService.getUrlServidor(request);
            } else {
                link = "";
            }

            String tituloEmail;
            String mensagemEmail;
            Rm rm = rmAprovador.getRm();
            String nomeRequisitante = rm.getRequisitante().getNome();
            String numeroRm = "";
            
           if(rm.getTipoRm().equals("SER")  || rm.getNumeroRm().equals("0")) {
        	   
        	   numeroRm = rm.getNumeroRmServico();
        	   
        	   if (WorkflowEnum.WORKFLOW_REPROVADO == proximaEtapa) {
                   tituloEmail = Util.setParamsLabel(
                     getProperty("msg_titulo_email_reprovada_rma"), numeroRm);
                   mensagemEmail = Util
                     .setParamsLabel(getProperty("msg_email_rma_reprovada_servico"), rmAprovador.getAprovador().getNome(), numeroRm,
                       nomeRequisitante, rmAprovador.getObservacao(), link);

               } else if (Objects.nonNull(proximaEtapa)) {
                   tituloEmail = String.join(" - ", Util.setParamsLabel(
                     getProperty("msg_titulo_email_aprovacao_rma_servico"), rm.getPrioridade().getDescricao()), proximaEtapa.getStep());

                   mensagemEmail = Util
                     .setParamsLabel(getProperty("msg_email_rma_envio_servico"), numeroRm,
                       nomeRequisitante, link);
               } else {
                   tituloEmail = Util.setParamsLabel(
                     getProperty("msg_titulo_email_aprovada_rma"), numeroRm);
                   mensagemEmail = Util
                     .setParamsLabel(getProperty("msg_email_rma_aprovada_servico"), numeroRm,
                       nomeRequisitante, link);
               }
           } else {
        	   numeroRm = rm.getNumeroRm();
        	   
        	   if (WorkflowEnum.WORKFLOW_REPROVADO == proximaEtapa) {
                   tituloEmail = Util.setParamsLabel(
                     getProperty("msg_titulo_email_reprovada_rma"), numeroRm);
                   mensagemEmail = Util
                     .setParamsLabel(getProperty("msg_email_rma_reprovada"), rmAprovador.getAprovador().getNome(), numeroRm,
                       nomeRequisitante, rmAprovador.getObservacao(), link);

               } else if (Objects.nonNull(proximaEtapa)) {
                   tituloEmail = String.join(" - ", Util.setParamsLabel(
                     getProperty("msg_titulo_email_aprovacao_rma"), rm.getPrioridade().getDescricao()), proximaEtapa.getStep());

                   mensagemEmail = Util
                     .setParamsLabel(getProperty("msg_email_rma_envio"), numeroRm,
                       nomeRequisitante, link);
               } else {
                   tituloEmail = Util.setParamsLabel(
                     getProperty("msg_titulo_email_aprovada_rma"), numeroRm);
                   mensagemEmail = Util
                     .setParamsLabel(getProperty("msg_email_rma_aprovada"), numeroRm,
                       nomeRequisitante, link);
               }
           }
           
            final String recipients = String.join(",", emailsEmCopia);
            final String body = mensagemEmail;
            this.emailService.enviarEmail(tituloEmail, recipients, body);
        } catch (Exception e) {
            logger.error("Erro ao tentar notificar aprovador", e);
        }
    }

    /**
     * Lista os aprovadores na rm selecionada
     *
     * @param filtro
     * @return
     */
    private List<RmAprovador> listarAprovadores(FiltroRmAprovacao filtro) {
        List<RmAprovador> lista = null;
        try {

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroRmAprovacao.class.getName(), Util.getNomeMetodoAtual(), filtro, lista));
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmAprovador.ID));
            propriedades.add(new Propriedade(RmAprovador.ORDEM));
            propriedades.add(new Propriedade(RmAprovador.OBSERVACAO));
            propriedades.add(new Propriedade(RmAprovador.TIPO_APROVADOR));

            NxCriterion nxCriterion;
            NxCriterion nxCriterionOr;

            //Listar os aprovadores da vez?
            if (filtro.getAprovadoresDaVez()) {
                nxCriterion = NxCriterion.montaRestriction(new Filtro(RmAprovador.APROVADOR_VEZ, Boolean.TRUE, Filtro.EQUAL));
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmAprovador.RM_ID, filtro.getRmAprovador().getRm(), Filtro.EQUAL)));
            } else {
                nxCriterionOr = NxCriterion.montaRestriction(new Filtro(RmAprovador.APROVADOR_VEZ, Boolean.FALSE, Filtro.EQUAL));
                nxCriterionOr = NxCriterion.or(nxCriterionOr, NxCriterion.montaRestriction(new Filtro(RmAprovador.APROVADOR_VEZ, null, Filtro.IS_NULL)));

                nxCriterion = NxCriterion.and(nxCriterionOr, NxCriterion.montaRestriction(new Filtro(RmAprovador.DATA_AVALIACAO, null, Filtro.IS_NULL)));
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmAprovador.RM_ID, filtro.getRmAprovador().getRm(), Filtro.EQUAL)));
            }

            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(RmAprovador.ORDEM, NxOrder.NX_ORDER.ASC));

            GenericDao<RmAprovador> genericDao = new GenericDao<>();
            lista = genericDao.listarByFilter(propriedades, nxOrders, RmAprovador.class, Constantes.NO_LIMIT, nxCriterion);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroRmAprovacao.class.getName(), Util.getNomeMetodoAtual(), filtro, lista));

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    /**
     * Envia email ao aprovador com as informacoes da rm e o link de aprovacao
     *
     * @param rmAprovador
     */
    private void enviarEmailAprovador(RmAprovador rmAprovador) {
        RmaService rmaService = new RmaService(request);
        String emails = "";
        String mensagemEmail = "";
        String tituloEmail;
        String link;

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), RmAprovador.class.getName(), Util.getNomeMetodoAtual(), rmAprovador, null));

            if (rmAprovador.getTipoAprovador().equals(Constantes.TIPO_ATUACAO_LIDER_CUSTOS)) {
                for (Pessoa p : rmaService.listarPessoaRole(Constantes.ROLE_LIDER_CUSTOS)) {
                    if (p.getEmail() != null) {
                        emails += p.getEmail() + ";";
                    }
                }
            } else {
                emails = rmAprovador.getAprovador().getEmail();
            }

            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
            Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();

            if (rmAprovador.getTipoAprovador().equals(Constantes.TIPO_ATUACAO_GERENTE_OBRA)) {
                tituloEmail = Util.setParamsLabel(rb.getString("msg_titulo_email_aprovacao_rma_emergencial"), rmAprovador.getRm().getPrioridade().getDescricao());
            } else {
                tituloEmail = Util.setParamsLabel(rb.getString("msg_titulo_email_aprovacao_rma"), rmAprovador.getRm().getPrioridade().getDescricao());
            }

            if (configuracao.getPrefUrlExternaSist() != null && !configuracao.getPrefUrlExternaSist().isEmpty()) {
                link = configuracao.getPrefUrlExternaSist();
            } else {
                link = LoginService.getUrlServidor(request);
            }

            //String parametro = Criptografia.cript("id=" + rmAprovador.getId());
            //link += request.getContextPath() + "/html5/rma/rmAprovacao.html#aprovacaoRma?id=" + parametro;
            link += request.getContextPath() + "/html5/rma/index.html";

            if (rmAprovador.getAprovador() != null) {
                mensagemEmail = Util.setParamsLabel(rb.getString("msg_email_rma_envio"), rmAprovador.getRm().getNumeroRm(), rmAprovador.getRm().getRequisitante().getNome(), link);
            } else if (rmAprovador.getAprovador() == null) {
                mensagemEmail = Util.setParamsLabel(rb.getString("msg_email_rma_custos"), rmAprovador.getRm().getNumeroRm(), rmAprovador.getRm().getRequisitante().getNome(), link);
            }

            //TODO Retirado apenas para teste
            /***Email2.enviaEmailPorThread(null, tituloEmail, mensagemEmail, null,
             configuracao.getEmailHost(), configuracao.getEmailPorta(),
             configuracao.getEmailOrigem(), configuracao.getEmailUser(),
             configuracao.getEmailPasswd(),
             configuracao.getEmailChave(), null, emails);*/

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), RmAprovador.class.getName(), Util.getNomeMetodoAtual(), rmAprovador, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
    }

    /**
     * Envia email ao solicitante notificando que uma rm foi aprovada
     */
    //    private void enviarEmailFinal(RmAprovador rmAprovador,
    //                                  List<RmAprovador> listaAprovadores, String itensNaoOrcamentados) {
    //        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
    //
    //        try {
    //            if (rmAprovador != null) {
    //                logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), RmAprovador.class.getName(), Util.getNomeMetodoAtual(), rmAprovador, null));
    //
    //                String tituloEmail;
    //                String mensagemEmail;
    //                String email = new String();
    //                if (rmAprovador.getRm() != null
    //                        && rmAprovador.getRm().getRequisitante() != null
    //                        && rmAprovador.getRm().getRequisitante().getEmail() != null
    //                        && !rmAprovador.getRm().getRequisitante().getEmail().trim().isEmpty()) {
    //                    email = rmAprovador.getRm().getRequisitante().getEmail();
    //                }
    //
    //                if (rmAprovador.getRm() != null
    //                        && rmAprovador.getRm().getUsuario() != null
    //                        && rmAprovador.getRm().getUsuario().getPessoa() != null
    //                        && rmAprovador.getRm().getUsuario().getPessoa().getEmail() != null
    //                        && !rmAprovador.getRm().getUsuario().getPessoa().getEmail().trim().isEmpty()) {
    //                    if (email != null && !email.isEmpty()) {
    //                        email += ";" + rmAprovador.getRm().getUsuario().getPessoa().getEmail();
    //                    } else {
    //                        email = rmAprovador.getRm().getUsuario().getPessoa().getEmail();
    //                    }
    //                }
    //
    //                //Se caso tiver email preenchido, seja do requisitante ou user, então o mesmo envia o e-mail.
    //                if (email != null && !email.isEmpty()) {
    //                    if (rmAprovador.getAprovada()) {
    //                        tituloEmail = rb.getString("msg_titulo_email_aprovada_rma");
    //                        mensagemEmail = Util.setParamsLabel(rb.getString("msg_email_rma_aprovada"), rmAprovador.getRm().getNumeroRm());
    //                    } else {
    //                        String mensagemEmailAux = "";
    //                        if (itensNaoOrcamentados != null && !itensNaoOrcamentados.isEmpty()) {
    //                            mensagemEmailAux = Util.setParamsLabel(rb.getString("msg_email_rma_itens_nao_orcamento"),
    //                                    itensNaoOrcamentados);
    //                        }
    //                        tituloEmail = rb.getString("msg_titulo_email_reprovada_rma");
    //                        mensagemEmail = Util.setParamsLabel(rb.getString("msg_email_rma_reprovada"),
    //                                rmAprovador.getRm().getNumeroRm(), rmAprovador.getObservacao(), mensagemEmailAux);
    //
    //                        if (rmAprovador.getTipoAprovador().equals(Constantes.TIPO_ATUACAO_GERENTE_CUSTO)
    //                                || rmAprovador.getTipoAprovador().equals(Constantes.TIPO_ATUACAO_LIDER_CUSTOS)
    //                                || rmAprovador.getTipoAprovador().equals(Constantes.TIPO_ATUACAO_CUSTOS)) {
    //                            email = carregaEmailsReprovacaoGerenteCusto(email, listaAprovadores);
    //                        }
    //                    }
    //
    //
    //                  final String subject = tituloEmail;
    //                  final String recipients = email;
    //                  final String body = mensagemEmail;
    //                  this.emailService.enviarEmail(subject, recipients, body);
    //
    //                }
    //            }
    //            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), RmAprovador.class.getName(), Util.getNomeMetodoAtual(), rmAprovador, null));
    //        } catch (Exception e) {
    //            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
    //        }
    //    }
    
    //Lista Materiais na aprovação do TIPO RM = 'MAT'
    private List<VwRmMaterial> listarVwRmMateriais(Rm rm) {
        List<VwRmMaterial> materiais = new ArrayList<>();
        GenericDao<VwRmMaterial> genericDao = new GenericDao<>();

        try {
            String aliasRmMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID);
            String aliasRmMaterialStatus = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_STATUS_ID);

            String aliasRm = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM);
            String aliasEapMultiEmpresa = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.EAP_MULTI_EMPRESA);
            String aliasMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.DEPOSITO_ID);
            String aliasStatus = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_STATUS_ID, RmMaterialStatus.STATUS_ID);

            String aliasPrioridade = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.PRIORIDADE);
            String aliasUnidadeMedida = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.MATERIAL, Material.UNIDADE_MEDIDA);
            String aliasTipoMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.MATERIAL, Material.TIPO_MATERIAL);
            String aliasTipoRequisicao = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.TIPO_REQUISICAO);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(VwRmMaterial.ID));

            //RmMaterial
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.ORDEM, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.ESTA_NO_ORCAMENTO, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_CUSTOS, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.OPERACAO, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DIAGRAMA_REDE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO_CUSTO, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.VALOR_ORCADO, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.FLUXO_MATERIAL, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.LOCAL_ENTREGA, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_ORDEM, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_ESTOQUE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_REQUISICAO_SAP, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DATA_CANCELAMENTO, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.STATUS, RmMaterial.class, aliasRmMaterial));

            //Material Status
            propriedades.add(new Propriedade(RmMaterialStatus.ID, RmMaterialStatus.class, aliasRmMaterialStatus));
            propriedades.add(new Propriedade(RmMaterialStatus.USUARIO, RmMaterialStatus.class, aliasRmMaterialStatus));

            //RM
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));

            //EAP MULTI EMPRESA
            propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));

            //Material
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME_COMPLETO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.ULTIMO_VALOR_NEGOCIADO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.PATRIMONIADO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.IS_SERVICO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.HIERARQUIA, Material.class, aliasMaterial));

            //DEPOSITO
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.ENDERECO, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.OBSERVACAO, Deposito.class, aliasDeposito));

            //Status
            propriedades.add(new Propriedade(Status.ID, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.CODIGO, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.NOME, Status.class, aliasStatus));

            //Prioridade
            propriedades.add(new Propriedade(Prioridade.PRIORIDADE_ID, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.CODIGO, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.DESCRICAO, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.CONF_DIAS_PREV_ENTREGA, Prioridade.class, aliasPrioridade));

            //Unidade Medida
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            //Tipo Material
            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM, rm, Filtro.EQUAL, aliasRmMaterial));

            List<NxOrder> orders = Arrays.asList(new NxOrder(VwRmMaterial.RM_MATERIAL_ID + "." + RmMaterial.ORDEM, NxOrder.NX_ORDER.ASC));

            materiais = genericDao.listarByFilter(propriedades, orders, VwRmMaterial.class, Constantes.NO_LIMIT, nxCriterion);

            for (VwRmMaterial vw : materiais) {
                vw.getRmMaterialStatus().setRmMaterial(vw.getRmMaterial());
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return materiais;
    }

    
  //Lista Servicos na aprovação do TIPO RM = 'SER'
    private List<VwRmServico> listarVwRmServico(Rm rm) {
        List<VwRmServico> servicos = new ArrayList<>();
        GenericDao<VwRmServico> genericDao = new GenericDao<>();
        RmaServicoService rmaServicoService = new RmaServicoService(request);

        try {
        	int teste = rm.getRmId();
            String aliasRmServico = NxCriterion.montaAlias(RmServico.ALIAS_CLASSE, RmServico.RM);
            String aliasRmServicoStatus = NxCriterion.montaAlias(VwRmServico.ALIAS_CLASSE, VwRmServico.RM_SERVICO_STATUS_ID);

            String aliasRm = NxCriterion.montaAlias(VwRmServico.ALIAS_CLASSE, VwRmServico.RM_SERVICO_ID, RmServico.RM);
            String aliasEapMultiEmpresa = NxCriterion.montaAlias(VwRmServico.ALIAS_CLASSE, VwRmServico.RM_SERVICO_ID, RmServico.RM, Rm.EAP_MULTI_EMPRESA);
            String aliasServico = NxCriterion.montaAlias(VwRmServico.ALIAS_CLASSE, VwRmServico.RM_SERVICO_ID, RmServico.RM_SERVICO_ID);
            //String aliasDeposito = NxCriterion.montaAlias(VwRmServico.ALIAS_CLASSE, VwRmServico.RM_MATERIAL_ID, RmServico.DEPOSITO_ID);
            String aliasStatus = NxCriterion.montaAlias(VwRmServico.ALIAS_CLASSE, VwRmServico.RM_SERVICO_STATUS_ID, RmServicoStatus.STATUS_ID);

            String aliasPrioridade = NxCriterion.montaAlias(VwRmServico.ALIAS_CLASSE, VwRmServico.RM_SERVICO_ID, RmServico.RM, Rm.PRIORIDADE);
            /*String aliasUnidadeMedida = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.MATERIAL, Material.UNIDADE_MEDIDA);
            String aliasTipoMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.MATERIAL, Material.TIPO_MATERIAL);
            String aliasTipoRequisicao = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.TIPO_REQUISICAO);*/

            List<Propriedade> propriedades = new ArrayList<>();
            //propriedades.add(new Propriedade(VwRmServico.ID));

            //RmServico
            //propriedades.add(new Propriedade(RmServico.RM_SERVICO_ID, RmServico.class, aliasRmServico));
            //propriedades.add(new Propriedade(RmServico.ORDEM, RmServico.class, aliasRmServico));
            //propriedades.add(new Propriedade(RmServico.RESPONSABILIDADE_OBSERVACOES, RmServico.class, aliasRmServico));
            //propriedades.add(new Propriedade(RmServico.QUANTIDADE, RmServico.class, aliasRmServico));
           /* propriedades.add(new Propriedade(RmServico.ESTA_NO_ORCAMENTO, RmServico.class, aliasRmServico));
            propriedades.add(new Propriedade(RmServico.COLETOR_CUSTOS, RmServico.class, aliasRmServico));
            propriedades.add(new Propriedade(RmServico.OPERACAO, RmServico.class, aliasRmServico));
            propriedades.add(new Propriedade(RmServico.DIAGRAMA_REDE, RmServico.class, aliasRmServico));
            propriedades.add(new Propriedade(RmServico.OBSERVACAO_CUSTO, RmServico.class, aliasRmServico));
            propriedades.add(new Propriedade(RmServico.VALOR_ORCADO, RmServico.class, aliasRmServico));
            propriedades.add(new Propriedade(RmServico.FLUXO_MATERIAL, RmServico.class, aliasRmServico));
            propriedades.add(new Propriedade(RmServico.LOCAL_ENTREGA, RmServico.class, aliasRmServico));
            propriedades.add(new Propriedade(RmServico.COLETOR_ORDEM, RmServico.class, aliasRmServico));
            propriedades.add(new Propriedade(RmServico.COLETOR_ESTOQUE, RmServico.class, aliasRmServico));
            propriedades.add(new Propriedade(RmServico.NUMERO_REQUISICAO_SAP, RmServico.class, aliasRmServico));
            propriedades.add(new Propriedade(RmServico.DATA_CANCELAMENTO, RmServico.class, aliasRmServico));
            propriedades.add(new Propriedade(RmServico.STATUS, RmServico.class, aliasRmServico));*/

            //Material Status
            /*propriedades.add(new Propriedade(RmServicoStatus.ID, RmServicoStatus.class, aliasRmServicoStatus));
            propriedades.add(new Propriedade(RmServicoStatus.USUARIO, RmServicoStatus.class, aliasRmServicoStatus));*/

            //RM
            //propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM_SERVICO, Rm.class, aliasRmServico));

            //EAP MULTI EMPRESA
            //propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            //propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            //propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));

            /*Material
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME_COMPLETO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.ULTIMO_VALOR_NEGOCIADO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.PATRIMONIADO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.IS_SERVICO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.HIERARQUIA, Material.class, aliasMaterial));*/

            //DEPOSITO
           /* propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.ENDERECO, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.OBSERVACAO, Deposito.class, aliasDeposito));*/

            //Status
            /*propriedades.add(new Propriedade(Status.ID, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.CODIGO, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.NOME, Status.class, aliasStatus));*/

            //Prioridade
            /*propriedades.add(new Propriedade(Prioridade.PRIORIDADE_ID, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.CODIGO, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.DESCRICAO, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.CONF_DIAS_PREV_ENTREGA, Prioridade.class, aliasPrioridade));*/

            //Unidade Medida
            /*propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            //Tipo Material
            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));*/

            //NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmServico.RM, rm.getRmId(), Filtro.EQUAL, aliasRmServico));

            //List<NxOrder> orders = Arrays.asList(new NxOrder(RmServico.ORDEM, NxOrder.NX_ORDER.ASC));
           // servicos = genericDao.listarByFilter(propriedades, null, RmServico.class, Constantes.NO_LIMIT, nxCriterion);
            
            System.out.println("**!!************" + servicos + "***************!!****");
            
            for (VwRmServico vw : servicos) {
                vw.getRmServicoStatus().setRmServico(vw.getRmServico());
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return servicos;
    }
    
    /**
     * Verifica se o usuario logado possui a role passada por parametro
     *
     * @return
     */
    private Boolean verificarRole(String role) {
        try {
            Usuario usuario = LoginService.getUsuarioLogado(request);
            PerfilRoleDao prDao = new PerfilRoleDao();
            List<Role> roles = prDao.getRolesPorPerfilId(usuario.getPerfil().getPerfilId());
            for (Role r : roles) {
                if (r.getNome().equals(role)) {
                    return true;
                }
            }
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return false;
    }

    /**
     * Lista os aprovadores na rm selecionada
     *
     * @param rm
     * @return
     */
    private List<RmAprovador> listarAprovadores(Rm rm) {
        List<RmAprovador> lista = null;
        try {

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), rm, lista));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmAprovador.ID));
            propriedades.add(new Propriedade(RmAprovador.ORDEM));
            propriedades.add(new Propriedade(RmAprovador.OBSERVACAO));
            propriedades.add(new Propriedade(RmAprovador.TIPO_APROVADOR));

            //Aprovador
            String aliasAprovador = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.APROVADOR_PESSOA_ID);
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasAprovador));
            propriedades.add(new Propriedade(Pessoa.EMAIL, Pessoa.class, aliasAprovador));

            String aliasRm = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID);
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.ATRIBUIDO_PARA, Rm.class, aliasRm));

            String aliasUsuario = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID, Rm.USUARIO);
            propriedades.add(new Propriedade(Usuario.USUARIO_ID, Usuario.class, aliasUsuario));

            String aliasPessoaUsuario = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID, Rm.USUARIO, Usuario.PESSOA);
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasPessoaUsuario));
            propriedades.add(new Propriedade(Pessoa.EMAIL, Pessoa.class, aliasPessoaUsuario));

            NxCriterion nxCriterion;

            nxCriterion = NxCriterion.montaRestriction(new Filtro(RmAprovador.RM_ID, rm, Filtro.EQUAL));

            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(RmAprovador.ORDEM, NxOrder.NX_ORDER.ASC));

            GenericDao<RmAprovador> genericDao = new GenericDao<>();
            lista = genericDao.listarByFilter(propriedades, nxOrders, RmAprovador.class, Constantes.NO_LIMIT, nxCriterion);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), rm, lista));

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    /**
     * Volta a aprovacao para a equipe de custos
     *
     * @param rmAprovadorVo
     * @return
     */
    @POST
    @Path("voltarCustos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info voltarCustos(RmAprovadorVo rmAprovadorVo) {
        Info info = new Info();
        GenericDao<RmAprovador> genericDao = new GenericDao<>();

        List<Propriedade> propriedades = new ArrayList<>();

        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);
        RmaService rmaService = new RmaService(request);

        try {
            List<RmAprovador> listaAprovadores = listarAprovadores(rmAprovadorVo.getRmAprovador().getRm());

            List<RmMaterial> listaRmMaterial = rmaService.listarRmMateriais(rmAprovadorVo.getRmAprovador().getRm());
            List<RmMaterialStatus> listaMaterialStatus = rmMaterialStatusService.listarMaterialStatus(listaRmMaterial, Boolean.FALSE);

            genericDao.beginTransaction();
            propriedades.add(new Propriedade(RmMaterialStatus.IS_HISTORICO));
            for (RmMaterialStatus rmMaterialStatus : listaMaterialStatus) {
                if (rmMaterialStatus.getStatus().getCodigo().equals(Constantes.STATUS_RM_MATERIAL_APROVADA_GC)
                  || rmMaterialStatus.getStatus().getCodigo().equals(Constantes.STATUS_RM_MATERIAL_COMPLEMENTO_CUSTOS)) {

                    rmMaterialStatus.setHistorico(Boolean.TRUE);

                    genericDao.updateWithCurrentTransaction(rmMaterialStatus, propriedades);
                }
            }

            propriedades.clear();
            propriedades.add(new Propriedade(Rm.JUST_RETORNO_EQUIPE_CUSTOS));

            genericDao.updateWithCurrentTransaction(rmAprovadorVo.getRmAprovador().getRm(), propriedades);

            for (RmMaterial rmMaterial : listaRmMaterial) {
                rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_COMPLEMENTO_CUSTOS, genericDao, null, null, LoginService.getUsuarioLogado(request).getNome());
            }

            Boolean custos = false;
            for (RmAprovador aprovador : listaAprovadores) {
                if (aprovador.getTipoAprovador().equals(Constantes.TIPO_ATUACAO_CUSTOS)) {
                    custos = true;
                    break;
                }
            }

            propriedades.clear();
            propriedades.add(new Propriedade(RmAprovador.APROVADA));
            propriedades.add(new Propriedade(RmAprovador.APROVADOR_VEZ));
            propriedades.add(new Propriedade(RmAprovador.DATA_AVALIACAO));
            propriedades.add(new Propriedade(RmAprovador.HORA_AVALIACAO));

            RmAprovador rmAprovador = null;

            for (RmAprovador aprovador : listaAprovadores) {
                if (custos) {
                    switch (aprovador.getTipoAprovador()) {
                        case Constantes.TIPO_ATUACAO_CUSTOS:
                            aprovador.setAprovada(null);
                            aprovador.setAprovadorVez(Boolean.TRUE);
                            aprovador.setDataAvaliacao(null);
                            aprovador.setHoraAvaliacao(null);
                            aprovador.setObservacao(null);
                            genericDao.updateWithCurrentTransaction(aprovador, propriedades);
                            rmAprovador = aprovador;
                            break;
                        case Constantes.TIPO_ATUACAO_LIDER_CUSTOS:
                            aprovador.setAprovada(null);
                            aprovador.setAprovadorVez(Boolean.TRUE);
                            aprovador.setDataAvaliacao(null);
                            aprovador.setHoraAvaliacao(null);
                            aprovador.setObservacao(null);
                            genericDao.updateWithCurrentTransaction(aprovador, propriedades);
                            break;
                    }
                } else if (aprovador.getTipoAprovador().equals(Constantes.TIPO_ATUACAO_LIDER_CUSTOS)) {
                    aprovador.setAprovada(null);
                    aprovador.setAprovadorVez(Boolean.TRUE);
                    aprovador.setDataAvaliacao(null);
                    aprovador.setHoraAvaliacao(null);
                    aprovador.setObservacao(null);

                    genericDao.updateWithCurrentTransaction(aprovador, propriedades);

                    rmAprovador = aprovador;
                }
            }

            genericDao.deleteWithCurrentTransaction(rmAprovadorVo.getRmAprovador());

            genericDao.commitCurrentTransaction();

            enviarEmailAprovador(rmAprovador);

            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem("label_requisicao_enviado_para_custos_sucesso");
            info.setErro(null);
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return info;
    }

    /**
     * Volta a aprovacao para a equipe de custos
     *
     * @param rmAprovadorVo
     * @return
     */
    @POST
    @Path("atribuirEquipeCusto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info atribuirEquipeCusto(RmAprovadorVo rmAprovadorVo) {
        Info info = new Info();
        GenericDao<RmAprovador> genericDao = new GenericDao<>();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
        List<Propriedade> propriedades = new ArrayList<>();
        List<RmAprovador> listaRmAprovador = new ArrayList<>();

        try {
            Rm rm = rmAprovadorVo.getRmAprovador().getRm();

            if (!rmAprovadorVo.getPessoaCusto().equals(rm.getAtribuidoPara())) {
                listaRmAprovador = listarAprovadores(rm);
            }

            RmAprovador aprovador = new RmAprovador();
            aprovador.setAprovador(rmAprovadorVo.getPessoaCusto());
            aprovador.setOrdem(2);
            aprovador.setRm(rm);
            aprovador.setTipoAprovador(Constantes.TIPO_ATUACAO_CUSTOS);
            aprovador.setAprovadorVez(true);

            genericDao.beginTransaction();

            rm.setAtribuidoPara(rmAprovadorVo.getPessoaCusto());

            propriedades.add(new Propriedade(Rm.ATRIBUIDO_PARA));
            genericDao.updateWithCurrentTransaction(rm, propriedades);

            genericDao.persistWithCurrentTransaction(aprovador);

            for (RmAprovador rmAprovador : listaRmAprovador) {
                if (rmAprovador.getTipoAprovador().equals(Constantes.TIPO_ATUACAO_CUSTOS)) {
                    genericDao.deleteWithCurrentTransaction(rmAprovador);
                }
            }

            genericDao.commitCurrentTransaction();

            enviarEmailAprovador(aprovador);

            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Util.setParamsLabel(rb.getString("msg_requisicao_atribuida_para"), rmAprovadorVo.getPessoaCusto().getNome()));
            info.setErro(null);
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    /**
     * Método rest para encaminhar a Rm ao estoquista Salva os dados alterados
     * na tela e finaliza os aprovadores para não exibir mais na tela de
     * Aprovação Requisição
     *
     * @param rmAprovadorVo
     * @return
     * @author Alyson X. Leite
     */
    @POST
    @Path("encaminharEstoquista")
    @Consumes("application/json")
    public Info encaminharEstoquista(RmAprovadorVo rmAprovadorVo) {
        Info info;
        GenericDao<RmAprovador> dao = new GenericDao<>();
        try {

            //carrega as listas
            FiltroRmAprovacao filtro = new FiltroRmAprovacao();
            filtro.setRmAprovador(rmAprovadorVo.getRmAprovador());
            filtro.setAprovadoresDaVez(Boolean.TRUE);
            List<RmAprovador> listaAprovadoresVez = listarAprovadores(filtro);

            filtro.setAprovadoresDaVez(Boolean.FALSE);
            List<RmAprovador> listaProximosAprovadores = listarAprovadores(filtro);

            dao.beginTransaction();
            info = salvar(rmAprovadorVo, dao);

            if (info.getCodigo().equals(Info.INFO_001)) {
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(RmAprovador.DATA_AVALIACAO));
                propriedades.add(new Propriedade(RmAprovador.HORA_AVALIACAO));
                propriedades.add(new Propriedade(RmAprovador.APROVADOR_VEZ));
                propriedades.add(new Propriedade(RmAprovador.APROVADA));

                Date data = new Date();
                rmAprovadorVo.getRmAprovador().setDataAvaliacao(data);
                rmAprovadorVo.getRmAprovador().setHoraAvaliacao(data);
                rmAprovadorVo.getRmAprovador().setAprovadorVez(Boolean.FALSE);
                rmAprovadorVo.getRmAprovador().setAprovada(Boolean.TRUE);

                dao.updateWithCurrentTransaction(rmAprovadorVo.getRmAprovador(), propriedades);

                if (!listaAprovadoresVez.isEmpty()) {
                    for (RmAprovador aprovador : listaAprovadoresVez) {
                        if (!aprovador.getId().equals(rmAprovadorVo.getRmAprovador().getId())) {
                            aprovador.setAprovadorVez(false);
                            aprovador.setDataAvaliacao(data);
                            aprovador.setHoraAvaliacao(data);
                            dao.updateWithCurrentTransaction(aprovador, propriedades);
                        }
                    }
                }

                if (!listaProximosAprovadores.isEmpty()) {
                    for (RmAprovador aprovador : listaProximosAprovadores) {
                        if (!aprovador.getId().equals(rmAprovadorVo.getRmAprovador().getId())) {
                            aprovador.setAprovadorVez(false);
                            dao.updateWithCurrentTransaction(aprovador, propriedades);
                        }
                    }
                }

                dao.commitCurrentTransaction();
            } else {
                dao.rollbackCurrentTransaction();
            }
        } catch (Exception e) {
            dao.rollbackCurrentTransaction();
            info = Info.GetError(Constantes.ERRO_OPERACAO_I18N);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    /**
     * Método rest para salvar os dados da aprovação
     *
     * @param rmAprovadorVo
     * @return
     * @author Alyson X. Leite
     */
    @POST
    @Path("salvar")
    @Consumes("application/json")
    public Info salvar(RmAprovadorVo rmAprovadorVo) {
        Info info;
        GenericDao<RmAprovador> dao = new GenericDao<>();
        try {
            dao.beginTransaction();
            info = salvar(rmAprovadorVo, dao);
            if (info.getCodigo().equals(Info.INFO_001)) {
                dao.commitCurrentTransaction();
            } else {
                dao.rollbackCurrentTransaction();
            }
        } catch (Exception e) {
            info = Info.GetError(Constantes.ERRO_OPERACAO_I18N);
            dao.rollbackCurrentTransaction();
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    /**
     * Salva os dados que foram alterados da aprovação, porém ainda não foram
     * aprovados/recusados
     *
     * @param rmAprovadorVo
     * @param dao
     * @return
     * @author Alyson X. Leite
     */
    private Info salvar(RmAprovadorVo rmAprovadorVo, GenericDao dao) {
        Info info;
        try {
            Rm rm = rmAprovadorVo.getRmAprovador().getRm();

            rm.setAtribuidoPara(rmAprovadorVo.getPessoaCusto());
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Rm.ATRIBUIDO_PARA));
            dao.updateWithCurrentTransaction(rm, propriedades);
            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

            List<Propriedade> propriedadesMaterial = new ArrayList<>();
            propriedadesMaterial.add(new Propriedade(RmMaterial.COLETOR_CUSTOS));
            propriedadesMaterial.add(new Propriedade(RmMaterial.OPERACAO));
            propriedadesMaterial.add(new Propriedade(RmMaterial.DIAGRAMA_REDE));
            propriedadesMaterial.add(new Propriedade(RmMaterial.ESTA_NO_ORCAMENTO));
            propriedadesMaterial.add(new Propriedade(RmMaterial.OBSERVACAO_CUSTO));
            propriedadesMaterial.add(new Propriedade(RmMaterial.VALOR_ORCADO));
            propriedadesMaterial.add(new Propriedade(RmMaterial.COLETOR_ORDEM));
            propriedadesMaterial.add(new Propriedade(RmMaterial.COLETOR_ESTOQUE));

            //Gero status para cada material
            for (VwRmMaterial rmMaterial : rmAprovadorVo.getListaRmMaterial()) {
                dao.updateWithCurrentTransaction(rmMaterial.getRmMaterial(), propriedadesMaterial);
            }

            info = Info.GetSuccess(rb.getString("msg_registro_salvo_sucesso"), rmAprovadorVo);

        } catch (Exception e) {
            info = Info.GetError(Constantes.ERRO_OPERACAO_I18N);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    private String carregaEmailsReprovacaoGerenteCusto(String email, List<RmAprovador> listaAprovadores) {
        for (RmAprovador rmAp : listaAprovadores) {
            if (rmAp.getTipoAprovador().equals(Constantes.TIPO_ATUACAO_GERENTE_AREA)
              || rmAp.getTipoAprovador().equals(Constantes.TIPO_ATUACAO_COORDENADOR)
              && rmAp.getRm() != null && rmAp.getAprovador() != null
              && rmAp.getAprovador().getEmail() != null
              && !rmAp.getAprovador().getEmail().trim().isEmpty()) {
                email += ";" + rmAp.getAprovador().getEmail();
            }
        }
        RmAprovador rmAprovadorAux = listaAprovadores.get(0);
        return email;
    }
}
