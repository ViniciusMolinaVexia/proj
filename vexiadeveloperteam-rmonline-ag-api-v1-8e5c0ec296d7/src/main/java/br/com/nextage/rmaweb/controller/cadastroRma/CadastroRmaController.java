package br.com.nextage.rmaweb.controller.cadastroRma;

import static br.com.nextage.rmaweb.enumerator.PrioridadeEnum.MAQ_PARADA;
import static br.com.nextage.rmaweb.enumerator.PrioridadeEnum.NORMAL;
import static br.com.nextage.rmaweb.enumerator.PrioridadeEnum.URGENTE;
import static br.com.nextage.rmaweb.util.PropertyUtils.getProperty;
import static br.com.nextage.rmaweb.util.UtilsApp.string;
import static br.com.nextage.rmaweb.util.UtilsApp.stringDate;
import static org.springframework.util.StringUtils.isEmpty;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.persistence_2.util.Paginacao;
import br.com.nextage.rmaweb.controller.ConsultaMateriaisSimilaresSap;
import br.com.nextage.rmaweb.controller.ConsultaMaterialSap;
import br.com.nextage.rmaweb.controller.DepositoSap;
import br.com.nextage.rmaweb.controller.MateriaisSimilaresSap;
import br.com.nextage.rmaweb.controller.MaterialSap;
import br.com.nextage.rmaweb.controller.ResponseConsultaInformacoesSap;
import br.com.nextage.rmaweb.controller.ResponseMateriaisSimilaresSap;
import br.com.nextage.rmaweb.controller.notificacao.email.enviar.EmailService;
import br.com.nextage.rmaweb.controller.rm.material.status.aprovadores.ObterAprovadoresRmMaterialRequest;
import br.com.nextage.rmaweb.controller.rm.material.status.aprovadores.ObterAprovadoresRmMaterialResponse;
import br.com.nextage.rmaweb.controller.rm.material.status.aprovadores.ObterAprovadoresRmMaterialService;
import br.com.nextage.rmaweb.controller.rm.material.status.saldo.ObterSaldoRequisicaoMaterialResponse;
import br.com.nextage.rmaweb.controller.rm.material.status.saldo.ObterSaldoRequisicaoMaterialService;
import br.com.nextage.rmaweb.dao.CentroDAO;
import br.com.nextage.rmaweb.dao.RmDao;
import br.com.nextage.rmaweb.dao.RmServicoDAO;
import br.com.nextage.rmaweb.dao.VwConsultaRmaDAO;
import br.com.nextage.rmaweb.entitybean.Centro;
import br.com.nextage.rmaweb.entitybean.Comprador;
import br.com.nextage.rmaweb.entitybean.Configuracao;
import br.com.nextage.rmaweb.entitybean.Deposito;
import br.com.nextage.rmaweb.entitybean.EapMultiEmpresa;
import br.com.nextage.rmaweb.entitybean.FornecedorServico;
import br.com.nextage.rmaweb.entitybean.GrupoComprador;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.entitybean.MaterialDeposito;
import br.com.nextage.rmaweb.entitybean.MaterialDepositoSaidaReserva;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.Prioridade;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.entitybean.RmAprovador;
import br.com.nextage.rmaweb.entitybean.RmDeposito;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.entitybean.RmMaterialRetirada;
import br.com.nextage.rmaweb.entitybean.RmMaterialStatus;
import br.com.nextage.rmaweb.entitybean.Role;
import br.com.nextage.rmaweb.entitybean.Status;
import br.com.nextage.rmaweb.entitybean.SubArea;
import br.com.nextage.rmaweb.entitybean.TipoMaterial;
import br.com.nextage.rmaweb.entitybean.UnidadeMedida;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.entitybean.VwConsultaRma;
import br.com.nextage.rmaweb.entitybean.VwRmMaterial;
import br.com.nextage.rmaweb.entitybean.Workflow;
import br.com.nextage.rmaweb.filtro.FiltroCadastroRma;
import br.com.nextage.rmaweb.filtro.FiltroMateriaisSimilares;
import br.com.nextage.rmaweb.service.CompradorService;
import br.com.nextage.rmaweb.service.ConsultaEstoqueMaterialSap;
import br.com.nextage.rmaweb.service.ConsultaEstoqueMaterialSapResponse;
import br.com.nextage.rmaweb.service.DepositoService;
import br.com.nextage.rmaweb.service.EstoqueMateriaisSap;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.PrioridadeService;
import br.com.nextage.rmaweb.service.RmMaterialService;
import br.com.nextage.rmaweb.service.RmService;
import br.com.nextage.rmaweb.service.requisicao.compra.SapServiceRequisicaoCompraClient;
import br.com.nextage.rmaweb.service.UsuarioService;
import br.com.nextage.rmaweb.service.WorkflowEnum;
import br.com.nextage.rmaweb.service.WorkflowService;
import br.com.nextage.rmaweb.service.integracao.CentroService;
import br.com.nextage.rmaweb.service.integracao.EapMultiEmpresaService;
import br.com.nextage.rmaweb.service.integracao.GrupoCompradorService;
import br.com.nextage.rmaweb.service.integracao.RastreabilidadeService;
import br.com.nextage.rmaweb.service.integracao.RmMaterialStatusService;
import br.com.nextage.rmaweb.service.integracao.RmaService;
import br.com.nextage.rmaweb.service.integracao.TipoMaterialIntegracaoService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.CadastroMaterial;
import br.com.nextage.rmaweb.vo.CadastroRmVo;
import br.com.nextage.rmaweb.vo.DepositoQuantidadeVo;
import br.com.nextage.rmaweb.vo.MateriaisSimilaresVo;
import br.com.nextage.rmaweb.vo.MaterialDepositoQuantidadeVo;
import br.com.nextage.rmaweb.vo.MaterialVo;
import br.com.nextage.rmaweb.vo.RetornoVo;
import br.com.nextage.rmaweb.vo.ValidacaoMaterialVo;
import br.com.nextage.util.Email2;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.log4j.Logger;

/**
 * @author Daniel H. Parisotto
 */
@Path("CadastroRma")
public class CadastroRmaController {

    @Context
    HttpServletRequest request;
    @Inject
    private SapServiceRequisicaoCompraClient sapServiceClient;

    @Inject
    private EmailService emailService;

    @Inject
    private ObterAprovadoresRmMaterialService obterAprovadoresRmMaterialService;

    @Inject
    private ObterSaldoRequisicaoMaterialService obterSaldoRequisicaoMaterialService;

    private DepositoService depositoService = new DepositoService();
    private CentroService centroService = CentroService.getInstance();

    private static final Logger log = Logger.getLogger(VwConsultaRmaDAO.class);

    private RastreabilidadeService rastrearService = RastreabilidadeService.getInstance();
    private RmServicoDAO rmServicoDAO = RmServicoDAO.getInstance();
    private CentroDAO centroDAO = CentroDAO.getInstance();
    private RmService rmService = new RmService();
    private WorkflowService workflowService = WorkflowService.getInstance();

    /**
     * Lista os rmMaterial
     *
     * @param filtro
     * @return
     * 
     * 
     */
    @POST
    @Path("listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object listar(FiltroCadastroRma filtro) {
        return listar(filtro, true);
    }

    private Object listar(FiltroCadastroRma filtro, Boolean paginado) {
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
        List<Propriedade> propriedades = new ArrayList<>();

        try {
            final Usuario usuarioLogado = LoginService.getUsuarioLogado(request);
            Boolean possuiRoleVisualizarTodasRm = false;
            Boolean possuiRoleLeituraTodasRm = false;

            Set<String> roleName = new HashSet<>();
            for (Role role : usuarioLogado.getRoles()) {
                roleName.add(role.getNome());
            }
            if (roleName.contains(Role.ROLE_ADMINISTRADOR) || roleName.contains(Role.ROLE_REQUISITANTE_CORPORATIVO)) {
                possuiRoleLeituraTodasRm = true;
                possuiRoleVisualizarTodasRm = true;
            } 
            /*else {
                if (filtro.getEapMultiEmpresa() == null) {
                    EapMultiEmpresa eap = new EapMultiEmpresa();
                    eap.setDescricao(usuarioLogado.getCentro().getDescricao());
                    filtro.setEapMultiEmpresa(eap);
                }
            }*/

            log.debug(ResourceLogUtil.createMessageDebug(usuarioLogado.getNome(), this.getClass().getName(), FiltroCadastroRma.class.getName(), Util.getNomeMetodoAtual(), filtro, null));

            String aliasRmMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID);
            String aliasRmMaterialStatus = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_STATUS_ID);
            String aliasStatus = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_STATUS_ID, RmMaterialStatus.STATUS_ID);
            String aliasRm = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM);
            String aliasRmGerenteArea = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.GERENTE_AREA);
            String aliasRmGerenteCusto = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.GERENTE_CUSTOS);
            String aliasRmCoordenador = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.COORDENADOR);
            String aliasRmRespRetEstoq = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.RESPONSAVEL_RETIRADA_ESTOQUE);
            String aliasComprador = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.COMPRADOR);
            String aliasPrioridade = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.PRIORIDADE);
            String aliasMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.MATERIAL);
            String aliasTipoMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.MATERIAL, Material.TIPO_MATERIAL);
            String aliasUnidadeMedida = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.MATERIAL, Material.UNIDADE_MEDIDA);
            String aliasRequisitante = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.REQUISITANTE);
            String aliasRequisitanteSubArea = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.REQUISITANTE, Pessoa.SUB_AREA);
            String aliasUsuario = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.USUARIO);
            String aliasUsuarioPessoa = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.USUARIO, Usuario.PESSOA);
            String aliasRmMaterialDeposito = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.DEPOSITO_ID);
            /*String aliasRmMaterialRecebimento = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.QTDE_RECEBIDA);*/

            //VIEW
            propriedades.add(new Propriedade(VwRmMaterial.ID));
            propriedades.add(new Propriedade(VwRmMaterial.CONFIRMAR_RETIRADA));
            propriedades.add(new Propriedade(VwRmMaterial.QTDE_RECEBIDA));
            propriedades.add(new Propriedade(VwRmMaterial.DATA_APROV_COORDENADOR));
            propriedades.add(new Propriedade(VwRmMaterial.DATA_APROV_GERENTE_AREA));
            propriedades.add(new Propriedade(VwRmMaterial.DATA_APROV_GERENTE_CUSTOS));
            propriedades.add(new Propriedade(VwRmMaterial.DATA_APROV_COMPLEMENTO_CUSTOS));
            propriedades.add(new Propriedade(VwRmMaterial.DATA_APROV_RESPONSAVEL_RETIRADA_ESTOQUE));

            //RM MATERIAL
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_REQUISICAO_SAP, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.ITEM_REQUISICAO_SAP, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DATA_PREVISTA_ENTREGA, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.FLUXO_MATERIAL, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_CUSTOS, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_ORDEM, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DIAGRAMA_REDE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.OPERACAO, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DATA_COMPRA, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DATA_RECEBIMENTO_SUPRIMENTOS, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DATA_PREV_ENTREGA_SUPRIMENTOS, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DATA_ULTIMA_NF, RmMaterial.class, aliasRmMaterial));

            NxCriterion nxCriterion = null;
            NxCriterion nxCriterionAux;

            if (filtro != null) {
                if (filtro.getMaterial() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.MATERIAL, filtro.getMaterial(), Filtro.EQUAL, aliasRmMaterial));
                    nxCriterion = nxCriterionAux;
                }
                if (filtro.getFluxoMaterial() != null && !filtro.getFluxoMaterial().isEmpty()) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.FLUXO_MATERIAL, filtro.getFluxoMaterial(), Filtro.EQUAL, aliasRmMaterial));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }
                if (filtro.getRequisitante() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, filtro.getRequisitante().getPessoaId(), Filtro.EQUAL, aliasRequisitante));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getAprovador() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, filtro.getAprovador().getPessoaId(), Filtro.EQUAL, aliasRmGerenteArea));
                    nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, filtro.getAprovador().getPessoaId(), Filtro.EQUAL, aliasRmGerenteCusto)));
                    nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, filtro.getAprovador().getPessoaId(), Filtro.EQUAL, aliasRmRespRetEstoq)));
                    nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, filtro.getAprovador().getPessoaId(), Filtro.EQUAL, aliasRmCoordenador)));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getMaterialChave() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Material.NOME, filtro.getMaterialChave(), Filtro.LIKE, aliasMaterial));
                    nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(Material.CODIGO, filtro.getMaterialChave(), Filtro.LIKE, aliasMaterial)));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }
                if (filtro.getUsuarioCadastrante() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, filtro.getUsuarioCadastrante().getPessoaId(), Filtro.EQUAL, aliasUsuarioPessoa));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getNumero() != null && !filtro.getNumero().isEmpty()) {
                    filtro.setNumero(filtro.getNumero().replaceAll(",", ";"));
                    String[] listaRma = filtro.getNumero().split(";");
                    NxCriterion nx = null;
                    NxCriterion nxAux = null;
                    for (String rma : listaRma) {
                        if (rma == null || rma.isEmpty()) {
                            continue;
                        }
                        nxAux = NxCriterion.montaRestriction(new Filtro(Rm.NUMERO_RM, rma, Filtro.EQUAL, aliasRm));
                        if (nx != null) {
                            nx = NxCriterion.or(nx, nxAux);
                        } else {
                            nx = nxAux;
                        }
                    }
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nx);
                    } else {
                        nxCriterion = nx;
                    }
                }


                if (filtro.getObservacao() != null && !filtro.getObservacao().isEmpty()) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Rm.OBSERVACAO, filtro.getObservacao(), Filtro.LIKE, aliasRm));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getRmMaterial() != null && filtro.getRmMaterial().getObservacao() != null && !filtro.getRmMaterial().getObservacao().isEmpty()) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.OBSERVACAO, filtro.getRmMaterial().getObservacao(), Filtro.LIKE, aliasRmMaterial));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getStatus() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterialStatus.STATUS_ID, filtro.getStatus(), Filtro.EQUAL, aliasRmMaterialStatus));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if ((filtro.getStDataEmissaoDe() != null && !filtro.getStDataEmissaoDe().isEmpty())
                        || (filtro.getStDataEmissaoAte() != null && !filtro.getStDataEmissaoAte().isEmpty())) {
                    Date dataDe = null, dataAte = null;

                    if (filtro.getStDataEmissaoDe() != null && !filtro.getStDataEmissaoDe().isEmpty()) {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(rb.getString("format_date"));
                            LocalDate dataEmissaoInicio = LocalDate.parse(filtro.getStDataEmissaoDe(), formatter);
                            dataDe = Date.from(dataEmissaoInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
                        } catch (Exception e) {
                            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
                        }
                    }

                    if (filtro.getStDataEmissaoAte() != null && !filtro.getStDataEmissaoAte().isEmpty()) {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(rb.getString("format_date"));
                            LocalDate dataEmissaoFim = LocalDate.parse(filtro.getStDataEmissaoAte(), formatter);
                            dataAte = Date.from(dataEmissaoFim.atStartOfDay(ZoneId.systemDefault()).withHour(23).withMinute(59).withSecond(59).withNano(999).toInstant());
                        } catch (Exception e) {
                            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
                        }
                    }

                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Rm.DATA_EMISSAO, dataDe, dataAte, true, Filtro.BETWEEN, aliasRm));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                
                if ((filtro.getStDataNecessidadeDe() != null && !filtro.getStDataNecessidadeDe().isEmpty())
                        || (filtro.getStDataNecessidadeAte() != null && !filtro.getStDataNecessidadeAte().isEmpty())) {
                    Date dataDe = null, dataAte = null;

                    if (filtro.getStDataNecessidadeDe() != null && !filtro.getStDataNecessidadeDe().isEmpty()) {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(rb.getString("format_date"));
                            LocalDate dataEmissaoInicio = LocalDate.parse(filtro.getStDataEmissaoDe(), formatter);
                            dataDe = Date.from(dataEmissaoInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
                        } catch (Exception e) {
                            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
                        }
                    }

                    if (filtro.getStDataNecessidadeAte() != null && !filtro.getStDataNecessidadeAte().isEmpty()) {
                        try {

                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(rb.getString("format_date"));
                            LocalDate dataEmissaoFim = LocalDate.parse(filtro.getStDataEmissaoAte(), formatter);
                            dataAte = Date.from(dataEmissaoFim.atStartOfDay(ZoneId.systemDefault()).withHour(23).withMinute(59).withSecond(59).withNano(999).toInstant());
                        } catch (Exception e) {
                            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
                        }
                    }

                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Rm.DATA_APLICACAO, dataDe, dataAte, true, Filtro.BETWEEN, aliasRm));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getRmMaterial() != null && filtro.getRmMaterial().getNumeroRequisicaoSap() != null && !filtro.getRmMaterial().getNumeroRequisicaoSap().isEmpty()) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.NUMERO_REQUISICAO_SAP, filtro.getRmMaterial().getNumeroRequisicaoSap(), Filtro.LIKE, aliasRmMaterial));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getRmMaterial() != null && filtro.getRmMaterial().getNumeroPedidoCompra() != null && !filtro.getRmMaterial().getNumeroPedidoCompra().isEmpty()) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.NUMERO_PEDIDO_COMPRA, filtro.getRmMaterial().getNumeroPedidoCompra(), Filtro.LIKE, aliasRmMaterial));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getNaoExibirComComprador() != null && filtro.getNaoExibirComComprador()) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.COMPRADOR, null, Filtro.IS_NULL, aliasRmMaterial));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getComprador() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.COMPRADOR, filtro.getComprador(), Filtro.EQUAL, aliasRmMaterial));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getPrioridade() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Rm.PRIORIDADE, filtro.getPrioridade(), Filtro.EQUAL, aliasRm));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }
            }


            Usuario usuario = LoginService.getUsuarioLogado(request);
            if (filtro != null && filtro.getUsuario() != null) {
                usuario = filtro.getUsuario();
            }


            if (!possuiRoleLeituraTodasRm && !possuiRoleVisualizarTodasRm) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Usuario.USUARIO_ID, usuario.getUsuarioId(), Filtro.EQUAL, aliasUsuario));

                if (usuario.getPessoa() != null && usuario.getPessoa().getPessoaId() != null && usuario.getPessoa().getPessoaId() > 0) {
                    nxCriterionAux = NxCriterion.or(nxCriterionAux,
                            NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, usuario.getPessoa().getPessoaId(), Filtro.EQUAL, aliasRequisitante))
                    );
                }

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }


            VwConsultaRmaDAO vwConsultaRmaDAO = new VwConsultaRmaDAO();
            
            List<Centro> lstCentro = centroDAO.getCentrosByUsuario(usuarioLogado.getUsuarioId());
            
            if(lstCentro.size() > 0) {
            	filtro.setCentros(lstCentro);
            }else {
            	filtro.setCentros(null);
            }
            
            if (paginado) {
                List<Object> vwConsultaRmas = vwConsultaRmaDAO.listaTudo(filtro, paginado);
                int qtdRegistros = vwConsultaRmaDAO.contaTudo(filtro);

                filtro.getPaginacaoVo().setQtdeRegistros(qtdRegistros);
                filtro.getPaginacaoVo().setItensConsulta(vwConsultaRmas);

                return filtro.getPaginacaoVo();
            } else {
                return vwConsultaRmaDAO.listaTudo(filtro, paginado);
            }

        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return null;
    }

    /***
     * Lista os rmMaterial
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("listarConsultaRma")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object listarConsultaRma(FiltroCadastroRma filtro) {
        return listar(filtro, true);
    }

    private Object listarConsultaRma(FiltroCadastroRma filtro, Boolean paginado) {
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

        try {
            List<Propriedade> propriedades = new ArrayList<>();

            String aliasRmMaterial = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_NUMERO_RM);
            String aliasPrioridadeDescricao = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.PRIORIDADE_DESCRICAO);
            String aliasMaterialCodigo = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.MATERIAL_CODIGO);
            String aliasMaterialNome = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.MATERIAL_NOME);
            String aliasMaterialQuantidade = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_MATERIAL_QUANTIDADE);
            String aliasUnidadeSigla = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.UNIDADE_MEDIDA_SIGLA);
            String aliasMaterialDescricao = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.TIPO_MATERIAL_DESCRICAO);
            String aliasStatusNome = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.STATUS_NOME);
            String aliasStatusCodigo = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.STATUS_CODIGO);
            String aliasTipoRequisicaoDescricao = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.TIPO_REQUISICAO_DESCRICAO);
            String aliasMaterialColetorCustos = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_MATERIAL_COLETOR_CUSTOS);
            String aliasMaterialColetorEstoque = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_MATERIAL_COLETOR_ESTOQUE);
            String aliasMaterialDiagramaRede = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_MATERIAL_DIAGRAMA_REDE);
            String aliasMaterialOperacao = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_MATERIAL_OPERACAO);
            String aliasUsuarioPessoaNome = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.USUARIO_PESSOA_NOME);
            String aliasUsuarioPessoaReferencia = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.USUARIO_PESSOA_REFERENCIA);
            String aliasRequisitanteNome = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.REQUISITANTE_NOME);
            String aliasRequisitanteReferencia = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.REQUISITANTE_REFERENCIA);
            String aliasSubAreaCodigo = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.SUB_AREA_CODIGO);
            String aliasSubAreaDescricao = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.SUB_AREA_DESCRICAO);
            String aliasSetorCodigo = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.SETOR_CODIGO);
            String aliasSetorDescricao = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.SETOR_DESCRICAO);
            String aliasRmaDataEmissao = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_DATA_EMISSAO);
            String aliasRmaDataAplicacao = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_DATA_APLICACAO);
            String aliasRmaMaterialDataRecSup = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_MATERIAL_DATA_REC_SUP);
            String aliasRmaMaterialDataPrevEntr = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_MATERIAL_DATA_PREV_ENTR);
            String aliasRmaMaterialDataCompra = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_MATERIAL_DATA_COMPRA);
            String aliasRmaMaterialDataUltimaNF = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_MATERIAL_DATA_ULTIMA_NF);
            String aliasCompradorNome = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.COMPRADOR_NOME);
            String aliasMaterialNumeroRequisicaoSAP = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_MATERIAL_NUMERO_REQUISICAO_SAP);
            String aliasMaterialItemRequisicaoSAP = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_MATERIAL_ITEM_REQUISICAO_SAP);
            String aliasMaterialNumeroDocEntrada = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_MATERIAL_NUMERO_DOC_ENTRADA);
            String aliasMaterialNumeroPedidoCompra = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_MATERIAL_NUMERO_PEDIDO_COMPRA);
            String aliasMaterialDataPrevistaEntr = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_MATERIAL_DATA_PREVISTA_ENTR);
            String aliasDiasPrevistos = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.DIAS_PREVISTOS);
            String aliasDataAprovGerenteArea = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.DATA_APROV_GERENTE_AREA);
            String aliasDataAprovGerenteCustos = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.DATA_APROV_GERENTE_CUSTOS);
            String aliasDataAprovCoordenador = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.DATA_APROV_COORDENADOR);
            String aliasDataAprovResponsavelRetiradaEstoque = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.DATA_APROV_RESPONSAVEL_RETIRADA_ESTOQUE);
            String aliasDataAprovComplementoCustos = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.DATA_APROV_COMPLEMENTO_CUSTOS);
            String aliasConfirmarRetirada = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.CONFIRMAR_RETIRADA);
            String aliasQtdRecebida = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.QTDE_RECEBIDA);
            String aliasUsuarioId = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.USUARIO_USUARIO_ID);
            String aliasTipoRequisicao = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.TIPO_REQUISICAO_CODIGO);
            String aliasMaterialIsServico = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.MATERIAL_IS_SERVICO);
            String aliasTipoMaterialCodigo = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.TIPO_MATERIAL_CODIGO);
            String aliasRmId = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_RM_ID);
            String aliasMaterialJustAltQuant = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_MATERIAL_JUST_ALT_QUANT);
            String aliasMaterialRmMaterialId = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_MATERIAL_RM_MATERIAL_ID);
            String aliasMaterialColetorOrdem = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_MATERIAL_COLETOR_ORDEM);
            String aliasDataEnvioAprovacao = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_DATA_ENVIO_APROVACAO);
            String aliasDataReprovacao = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_DATA_REPROVACAO);
            String aliasDataCancelamento = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_DATA_CANCELAMENTO);
            String aliasObservacao = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_OBSERVACAO);
            String aliasMaterialObservacao = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_MATERIAL_OBSERVACAO);
            String aliasEAPMultiEmpresaID = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_EAP_MULTI_EMPRESA_ID);
            String aliasEAPMultiEmpresaDescricao = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_EAP_MULTI_EMPRESA_DESCRICAO);
            String aliasMaterialStatusObservacao = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_MATERIAL_STATUS_OBSERVACAO);
            String aliasCentroId = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_CENTRO_ID);
            String aliasCentroNome = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_CENTRO_NOME);
            String aliasAreaId = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_AREA_ID);
            String aliasAreaNome = NxCriterion.montaAlias(VwConsultaRma.ALIAS_CLASSE, VwConsultaRma.RM_AREA_NOME);
            
            
            propriedades.add(new Propriedade(VwConsultaRma.RM_NUMERO_RM, VwConsultaRma.class, aliasRmMaterial));
            propriedades.add(new Propriedade(VwConsultaRma.PRIORIDADE_DESCRICAO, VwConsultaRma.class, aliasPrioridadeDescricao));
            propriedades.add(new Propriedade(VwConsultaRma.MATERIAL_CODIGO, VwConsultaRma.class, aliasMaterialCodigo));
            propriedades.add(new Propriedade(VwConsultaRma.MATERIAL_NOME,VwConsultaRma.class, aliasMaterialNome));
            propriedades.add(new Propriedade(VwConsultaRma.RM_MATERIAL_QUANTIDADE,VwConsultaRma.class, aliasMaterialQuantidade));
            propriedades.add(new Propriedade(VwConsultaRma.UNIDADE_MEDIDA_SIGLA,VwConsultaRma.class, aliasUnidadeSigla));
            propriedades.add(new Propriedade(VwConsultaRma.TIPO_MATERIAL_DESCRICAO,VwConsultaRma.class, aliasMaterialDescricao));
            propriedades.add(new Propriedade(VwConsultaRma.STATUS_NOME ,VwConsultaRma.class, aliasStatusNome));
            propriedades.add(new Propriedade(VwConsultaRma.STATUS_CODIGO, VwConsultaRma.class, aliasStatusCodigo));
            propriedades.add(new Propriedade(VwConsultaRma.TIPO_REQUISICAO_DESCRICAO, VwConsultaRma.class, aliasTipoRequisicaoDescricao));
            propriedades.add(new Propriedade(VwConsultaRma.RM_MATERIAL_COLETOR_CUSTOS, VwConsultaRma.class, aliasMaterialColetorCustos));
            propriedades.add(new Propriedade(VwConsultaRma.RM_MATERIAL_COLETOR_ESTOQUE, VwConsultaRma.class, aliasMaterialColetorEstoque));
            propriedades.add(new Propriedade(VwConsultaRma.RM_MATERIAL_DIAGRAMA_REDE,VwConsultaRma.class, aliasMaterialDiagramaRede));
            propriedades.add(new Propriedade(VwConsultaRma.RM_MATERIAL_OPERACAO,VwConsultaRma.class, aliasMaterialOperacao));
            propriedades.add(new Propriedade(VwConsultaRma.USUARIO_PESSOA_NOME,VwConsultaRma.class, aliasUsuarioPessoaNome));
            propriedades.add(new Propriedade(VwConsultaRma.USUARIO_PESSOA_REFERENCIA,VwConsultaRma.class, aliasUsuarioPessoaReferencia));
            propriedades.add(new Propriedade(VwConsultaRma.REQUISITANTE_NOME,VwConsultaRma.class, aliasRequisitanteNome));
            propriedades.add(new Propriedade(VwConsultaRma.REQUISITANTE_REFERENCIA,VwConsultaRma.class, aliasRequisitanteReferencia));
            propriedades.add(new Propriedade(VwConsultaRma.SUB_AREA_CODIGO,VwConsultaRma.class, aliasSubAreaCodigo));
            propriedades.add(new Propriedade(VwConsultaRma.SUB_AREA_DESCRICAO,VwConsultaRma.class, aliasSubAreaDescricao));
            propriedades.add(new Propriedade(VwConsultaRma.SETOR_CODIGO,VwConsultaRma.class, aliasSetorCodigo));
            propriedades.add(new Propriedade(VwConsultaRma.SETOR_DESCRICAO,VwConsultaRma.class, aliasSetorDescricao));
            propriedades.add(new Propriedade(VwConsultaRma.RM_DATA_EMISSAO,VwConsultaRma.class, aliasRmaDataEmissao));
            propriedades.add(new Propriedade(VwConsultaRma.RM_DATA_APLICACAO,VwConsultaRma.class, aliasRmaDataAplicacao));
            propriedades.add(new Propriedade(VwConsultaRma.RM_MATERIAL_DATA_REC_SUP,VwConsultaRma.class, aliasRmaMaterialDataRecSup));
            propriedades.add(new Propriedade(VwConsultaRma.RM_MATERIAL_DATA_PREV_ENTR,VwConsultaRma.class, aliasRmaMaterialDataPrevEntr));
            propriedades.add(new Propriedade(VwConsultaRma.RM_MATERIAL_DATA_COMPRA, VwConsultaRma.class, aliasRmaMaterialDataCompra));
            propriedades.add(new Propriedade(VwConsultaRma.RM_MATERIAL_DATA_ULTIMA_NF, VwConsultaRma.class, aliasRmaMaterialDataUltimaNF));
            propriedades.add(new Propriedade(VwConsultaRma.COMPRADOR_NOME, VwConsultaRma.class, aliasCompradorNome));
            propriedades.add(new Propriedade(VwConsultaRma.RM_MATERIAL_NUMERO_REQUISICAO_SAP, VwConsultaRma.class, aliasMaterialNumeroRequisicaoSAP));
            propriedades.add(new Propriedade(VwConsultaRma.RM_MATERIAL_ITEM_REQUISICAO_SAP, VwConsultaRma.class, aliasMaterialItemRequisicaoSAP));
            propriedades.add(new Propriedade(VwConsultaRma.RM_MATERIAL_NUMERO_DOC_ENTRADA, VwConsultaRma.class, aliasMaterialNumeroDocEntrada));
            propriedades.add(new Propriedade(VwConsultaRma.RM_MATERIAL_NUMERO_PEDIDO_COMPRA, VwConsultaRma.class, aliasMaterialNumeroPedidoCompra));
            propriedades.add(new Propriedade(VwConsultaRma.RM_MATERIAL_DATA_PREVISTA_ENTR, VwConsultaRma.class, aliasMaterialDataPrevistaEntr));
            propriedades.add(new Propriedade(VwConsultaRma.DIAS_PREVISTOS, VwConsultaRma.class, aliasDiasPrevistos));
            propriedades.add(new Propriedade(VwConsultaRma.DATA_APROV_GERENTE_AREA, VwConsultaRma.class, aliasDataAprovGerenteArea));
            propriedades.add(new Propriedade(VwConsultaRma.DATA_APROV_GERENTE_CUSTOS,VwConsultaRma.class, aliasDataAprovGerenteCustos));
            propriedades.add(new Propriedade(VwConsultaRma.DATA_APROV_COORDENADOR,VwConsultaRma.class, aliasDataAprovCoordenador));
            propriedades.add(new Propriedade(VwConsultaRma.DATA_APROV_RESPONSAVEL_RETIRADA_ESTOQUE,VwConsultaRma.class, aliasDataAprovResponsavelRetiradaEstoque));
            propriedades.add(new Propriedade(VwConsultaRma.DATA_APROV_COMPLEMENTO_CUSTOS,VwConsultaRma.class, aliasDataAprovComplementoCustos));
            propriedades.add(new Propriedade(VwConsultaRma.CONFIRMAR_RETIRADA,VwConsultaRma.class, aliasConfirmarRetirada));
            propriedades.add(new Propriedade(VwConsultaRma.QTDE_RECEBIDA,VwConsultaRma.class, aliasQtdRecebida));
            propriedades.add(new Propriedade(VwConsultaRma.USUARIO_USUARIO_ID,VwConsultaRma.class, aliasUsuarioId));
            propriedades.add(new Propriedade(VwConsultaRma.TIPO_REQUISICAO_CODIGO,VwConsultaRma.class, aliasTipoRequisicao));
            propriedades.add(new Propriedade(VwConsultaRma.MATERIAL_IS_SERVICO,VwConsultaRma.class, aliasMaterialIsServico));
            propriedades.add(new Propriedade(VwConsultaRma.TIPO_MATERIAL_CODIGO,VwConsultaRma.class, aliasTipoMaterialCodigo));
            propriedades.add(new Propriedade(VwConsultaRma.RM_RM_ID,VwConsultaRma.class, aliasRmId));
            propriedades.add(new Propriedade(VwConsultaRma.RM_MATERIAL_JUST_ALT_QUANT,VwConsultaRma.class, aliasMaterialJustAltQuant));
            propriedades.add(new Propriedade(VwConsultaRma.RM_MATERIAL_RM_MATERIAL_ID,VwConsultaRma.class, aliasMaterialRmMaterialId));
            propriedades.add(new Propriedade(VwConsultaRma.RM_MATERIAL_COLETOR_ORDEM,VwConsultaRma.class, aliasMaterialColetorOrdem));
            propriedades.add(new Propriedade(VwConsultaRma.RM_DATA_ENVIO_APROVACAO,VwConsultaRma.class, aliasDataEnvioAprovacao));
            propriedades.add(new Propriedade(VwConsultaRma.RM_DATA_REPROVACAO,VwConsultaRma.class, aliasDataReprovacao));
            propriedades.add(new Propriedade(VwConsultaRma.RM_DATA_CANCELAMENTO,VwConsultaRma.class, aliasDataCancelamento));
            propriedades.add(new Propriedade(VwConsultaRma.RM_OBSERVACAO,VwConsultaRma.class, aliasObservacao));
            propriedades.add(new Propriedade(VwConsultaRma.RM_MATERIAL_OBSERVACAO,VwConsultaRma.class, aliasMaterialObservacao));
            propriedades.add(new Propriedade(VwConsultaRma.RM_EAP_MULTI_EMPRESA_ID,VwConsultaRma.class, aliasEAPMultiEmpresaID));
            propriedades.add(new Propriedade(VwConsultaRma.RM_EAP_MULTI_EMPRESA_DESCRICAO,VwConsultaRma.class, aliasEAPMultiEmpresaDescricao));
            propriedades.add(new Propriedade(VwConsultaRma.RM_MATERIAL_STATUS_OBSERVACAO,VwConsultaRma.class, aliasMaterialStatusObservacao));
            propriedades.add(new Propriedade(VwConsultaRma.RM_CENTRO_ID,VwConsultaRma.class, aliasCentroId));
            propriedades.add(new Propriedade(VwConsultaRma.RM_CENTRO_NOME,VwConsultaRma.class, aliasCentroNome));
            propriedades.add(new Propriedade(VwConsultaRma.RM_AREA_ID,VwConsultaRma.class, aliasAreaId));
            propriedades.add(new Propriedade(VwConsultaRma.RM_AREA_NOME,VwConsultaRma.class, aliasAreaNome));
            propriedades.add(new Propriedade(VwConsultaRma.RM_AREA_IDIOMA,VwConsultaRma.class, aliasAreaNome));

            NxCriterion nxCriterion = null;
            NxCriterion nxCriterionAux;

            Usuario usuario = LoginService.getUsuarioLogado(request);
            Pessoa pessoa = getPessoaByUsuario(usuario);
            usuario.setPessoa(pessoa);
            
            //Filtro para tipo de usuario
            //Administrador podera visualizar todas as RMs
            //Requisitante corporativo podera visualizar todas RMs de sua obra
            //Requisitante podera visualizar apenas suas proprias RMs
            List<Role> roles = LoginService.getUsuarioLogado(request).getRoles();
            boolean isAdm = false;
            boolean isReqCorp = false;
            boolean isReq = false;
            for(Role r : roles) {
            	if(r.getNome().equals(Role.ROLE_ADMINISTRADOR)) {
            		isAdm=true;
            	}else if(r.getNome().equals(Role.ROLE_REQUISITANTE_CORPORATIVO)) {
            		isReqCorp=true;
            	}else if(r.getNome().equals(Role.ROLE_REQUISITANTE)) {
            		isReq = true;
            	}
            }
            
            if(isAdm) {
            	//Consulta completa
            }else if(isReqCorp) {
            	nxCriterion = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.RM_CENTRO_ID, usuario.getCentro().getCentroId(), Filtro.EQUAL));
            }else if(isReq) {
            	nxCriterion = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.USUARIO_USUARIO_ID, usuario.getUsuarioId(), Filtro.EQUAL));
            }
            
            if (filtro != null) {

                if (filtro.getMaterial() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.MATERIAL_CODIGO, filtro.getMaterial().getCodigo(), Filtro.EQUAL));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }
                if (filtro.getFluxoMaterial() != null && !filtro.getFluxoMaterial().isEmpty()) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.RM_MATERIAL_FLUXO_MATERIAL, filtro.getFluxoMaterial(), Filtro.EQUAL));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }
                if (filtro.getRequisitante() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.REQUISITANTE_REFERENCIA, filtro.getRequisitante().getReferencia(), Filtro.EQUAL));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getAprovador() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.GERENTE_AREA_ID, filtro.getAprovador().getPessoaId(), Filtro.EQUAL));
                    nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(VwConsultaRma.GERENTE_CUSTO_ID, filtro.getAprovador().getPessoaId(), Filtro.EQUAL)));
                    nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(VwConsultaRma.RESPONSAVEL_RETIRADA_ESTOQUE_ID, filtro.getAprovador().getPessoaId(), Filtro.EQUAL)));
                    nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(VwConsultaRma.COORDENADOR_ID, filtro.getAprovador().getPessoaId(), Filtro.EQUAL)));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getMaterialChave() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.MATERIAL_NOME, filtro.getMaterialChave(), Filtro.LIKE));
                    nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(VwConsultaRma.MATERIAL_CODIGO, filtro.getMaterialChave(), Filtro.LIKE)));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }
                if (filtro.getUsuarioCadastrante() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.USUARIO_PESSOA_REFERENCIA, filtro.getUsuarioCadastrante().getReferencia(), Filtro.EQUAL));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getEapMultiEmpresa() != null && filtro.getEapMultiEmpresa().getId() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.RM_EAP_MULTI_EMPRESA_ID, filtro.getEapMultiEmpresa().getId(), Filtro.EQUAL));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getNumero() != null && !filtro.getNumero().isEmpty()) {
                    filtro.setNumero(filtro.getNumero().replaceAll(",", ";"));
                    String[] listaRma = filtro.getNumero().split(";");
                    NxCriterion nx = null;
                    NxCriterion nxAux = null;
                    for (String rma : listaRma) {
                        if (rma == null || rma.isEmpty()) {
                            continue;
                        }
                        nxAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.RM_NUMERO_RM, rma, Filtro.EQUAL));
                        if (nx != null) {
                            nx = NxCriterion.or(nx, nxAux);
                        } else {
                            nx = nxAux;
                        }
                    }
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nx);
                    } else {
                        nxCriterion = nx;
                    }
                }

                if (filtro.getObservacao() != null && !filtro.getObservacao().isEmpty()) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.RM_OBSERVACAO, filtro.getObservacao(), Filtro.LIKE));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getRmMaterial() != null && filtro.getRmMaterial().getObservacao() != null && !filtro.getRmMaterial().getObservacao().isEmpty()) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.RM_MATERIAL_OBSERVACAO, filtro.getRmMaterial().getObservacao(), Filtro.LIKE));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getStatus() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.STATUS_CODIGO, filtro.getStatus().getCodigo(), Filtro.EQUAL));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if ((filtro.getStDataEmissaoDe() != null && !filtro.getStDataEmissaoDe().isEmpty())
                        || (filtro.getStDataEmissaoAte() != null && !filtro.getStDataEmissaoAte().isEmpty())) {
                    Date dataDe = null, dataAte = null;

                    if (filtro.getStDataEmissaoDe() != null && !filtro.getStDataEmissaoDe().isEmpty()) {
                        try {
                            dataDe = Util.parseData(filtro.getStDataEmissaoDe(), rb.getString("format_date"));
                            dataDe.setHours(0);
                            dataDe.setMinutes(0);
                            dataDe.setSeconds(0);
                        } catch (Exception e) {
                            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
                        }
                    }

                    if (filtro.getStDataEmissaoAte() != null && !filtro.getStDataEmissaoAte().isEmpty()) {
                        try {
                            dataAte = Util.parseData(filtro.getStDataEmissaoAte(), rb.getString("format_date"));
                            dataAte.setHours(23);
                            dataAte.setMinutes(59);
                            dataAte.setSeconds(59);
                        } catch (Exception e) {
                            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
                        }
                    }

                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.RM_DATA_EMISSAO, dataDe, dataAte, true, Filtro.BETWEEN, null));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if ((filtro.getStDataNecessidadeDe() != null && !filtro.getStDataNecessidadeDe().isEmpty())
                        || (filtro.getStDataNecessidadeAte() != null && !filtro.getStDataNecessidadeAte().isEmpty())) {
                    Date dataDe = null, dataAte = null;

                    if (filtro.getStDataNecessidadeDe() != null && !filtro.getStDataNecessidadeDe().isEmpty()) {
                        try {
                            dataDe = Util.parseData(filtro.getStDataNecessidadeDe(), rb.getString("format_date"));
                            dataDe.setHours(0);
                            dataDe.setMinutes(0);
                            dataDe.setSeconds(0);
                        } catch (Exception e) {
                            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
                        }
                    }

                    if (filtro.getStDataNecessidadeAte() != null && !filtro.getStDataNecessidadeAte().isEmpty()) {
                        try {
                            dataAte = Util.parseData(filtro.getStDataNecessidadeAte(), rb.getString("format_date"));
                            dataAte.setHours(23);
                            dataAte.setMinutes(59);
                            dataAte.setSeconds(59);
                        } catch (Exception e) {
                            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
                        }
                    }

                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.RM_DATA_APLICACAO, dataDe, dataAte, true, Filtro.BETWEEN, null));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getRmMaterial() != null && filtro.getRmMaterial().getNumeroRequisicaoSap() != null && !filtro.getRmMaterial().getNumeroRequisicaoSap().isEmpty()) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.RM_MATERIAL_NUMERO_REQUISICAO_SAP, filtro.getRmMaterial().getNumeroRequisicaoSap(), Filtro.LIKE));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getRmMaterial() != null && filtro.getRmMaterial().getNumeroPedidoCompra() != null && !filtro.getRmMaterial().getNumeroPedidoCompra().isEmpty()) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.RM_MATERIAL_NUMERO_PEDIDO_COMPRA, filtro.getRmMaterial().getNumeroPedidoCompra(), Filtro.LIKE));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getNaoExibirComComprador() != null && filtro.getNaoExibirComComprador()) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.COMPRADOR_COMPRADOR_ID, null, Filtro.IS_NULL));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getComprador() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.COMPRADOR_COMPRADOR_ID, filtro.getComprador().getCompradorId(), Filtro.EQUAL));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

                if (filtro.getPrioridade() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.PRIORIDADE_CODIGO, filtro.getPrioridade().getCodigo(), Filtro.EQUAL));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }
            }

            /**Boolean possuiRoleVisualizarTodasRm = false;
            Boolean possuiRoleLeituraTodasRm = false;
            if (filtro != null && filtro.getUsuario() != null) {
                usuario = filtro.getUsuario();
                usuario.setPessoa(getPessoaByUsuario(usuario));
            }

            for (Role role : LoginService.getUsuarioLogado(request).getRoles()) {
                if (role.getNome().equals(Role.ROLE_ADMINISTRADOR)) {
                    possuiRoleVisualizarTodasRm = true;
                }
                if (role.getNome().equals(Role.ROLE_ADMINISTRADOR)) {
                    possuiRoleLeituraTodasRm = true;
                }
            }

            if (!possuiRoleLeituraTodasRm && !possuiRoleVisualizarTodasRm) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwConsultaRma.USUARIO_USUARIO_ID, usuario.getUsuarioId(), Filtro.EQUAL));

                if (usuario.getPessoa() != null && usuario.getPessoa().getPessoaId() != null && usuario.getPessoa().getPessoaId() > 0) {
                    nxCriterionAux = NxCriterion.or(nxCriterionAux,
                            NxCriterion.montaRestriction(new Filtro(VwConsultaRma.REQUISITANTE_REFERENCIA, usuario.getPessoa().getReferencia(), Filtro.EQUAL))
                    );
                }

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }*/

            List<NxOrder> orders = Arrays.asList(new NxOrder(VwConsultaRma.RM_MATERIAL_RM_MATERIAL_ID, NxOrder.NX_ORDER.DESC));

            if (paginado) {
                Paginacao.build(propriedades, orders, VwConsultaRma.class, nxCriterion, filtro.getPaginacaoVo());
                return filtro.getPaginacaoVo();
            } else {
                GenericDao<VwConsultaRma> genericDao = new GenericDao<>();
                return genericDao.listarByFilter(propriedades, orders, VwConsultaRma.class, Constantes.NO_LIMIT, nxCriterion);
            }

        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return null;


    }

    /**
     * Salva uma rm com os depositos e materiais selecionados
     *
     * @param cadastroRmVo
     * @return
     */
    @POST
    @Path("salvar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info salvar(CadastroRmVo cadastroRmVo) {
        Info info = new Info();
        Date data = new Date();
        RmDao rmDAO = new RmDao();
        RmaService rmaService = new RmaService(request);
        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);
        RmService rmService = new RmService();
        GenericDao genericDao = new GenericDao();
        Boolean envEmailCriacao = false;

        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

        try {
            log.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), CadastroRmVo.class.getName(), Util.getNomeMetodoAtual(), cadastroRmVo, null));

            Date dataAplicacao = Util.parseData(cadastroRmVo.getRm().getStDataAplicacao(), rb.getString("format_date"));
            cadastroRmVo.getRm().setDataAplicacao(dataAplicacao);

            cadastroRmVo.getRm().setDataRecebimento(new Date());

            if (cadastroRmVo.getRm().getNumeroRm() == null) {
                cadastroRmVo.getRm().setDataEmissao(new Date());
                cadastroRmVo.getRm().setStDataEmissao(Util.dateToString(cadastroRmVo.getRm().getDataEmissao(), rb.getString("format_date")));

                cadastroRmVo.getRm().setTipoRm("MAT");
                envEmailCriacao = true;
            }

            List<RmMaterial> rmMateriaisExcluir = rmaService.buscarRmMateriaisParaExcluir(cadastroRmVo);
            List<RmDeposito> rmDepositosExcluir = rmaService.buscarRmDepositosParaExcluir(cadastroRmVo);

            if(rmMateriaisExcluir!=null && rmMateriaisExcluir.size()>0) {
            	rastrearService.rastrearMaterialRetirado(cadastroRmVo.getRm(), rmMateriaisExcluir.size(), LoginService.getUsuarioLogado(request).getLogin());
            }
            
            if(rmDepositosExcluir!=null&&rmDepositosExcluir.size()>0) {
                rastrearService.rastrearDepositoRetirado(cadastroRmVo.getRm(), rmDepositosExcluir.size(),
                                LoginService.getUsuarioLogado(request).getLogin());
            }

            List<RmMaterialStatus> rmMateriaisStatusExcluir = new ArrayList<>();
            for (RmMaterial rmMaterial : rmMateriaisExcluir) {
                rmMateriaisStatusExcluir.addAll(rmMaterialStatusService.listarMaterialStatus(rmMaterial, Boolean.TRUE));
            }

            //Busca a prioridade da requisição de acordo com a configuração determinada de dias
            List<Prioridade> prioridades = listarPrioridadeConf(
                            cadastroRmVo.getRm().getCentro().getCentroId(),
                            cadastroRmVo.getRm().getArea().getAreaId());
            Integer diferencDiasPrioridade = Util.diferencaDias(cadastroRmVo.getRm().getDataEmissao(), dataAplicacao) + 1;
            Prioridade prioridadeRm = null;
            for (Prioridade prioridade : prioridades) {
                if (prioridade.getConfDiasIniPrioridade() != null && prioridade.getConfDiasFimPrioridade() != null) {
                    if (prioridade.getConfDiasIniPrioridade() <= diferencDiasPrioridade &&
                                    prioridade.getConfDiasFimPrioridade() >= diferencDiasPrioridade) {
                        prioridadeRm = prioridade;
                        break;
                    }
                }
            }
            if (prioridadeRm == null && !prioridades.isEmpty()) {
                prioridadeRm = prioridades.get(0);
            }
            cadastroRmVo.getRm().setPrioridade(prioridadeRm);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO));
            propriedades.add(new Propriedade(Rm.DATA_EMISSAO));
            propriedades.add(new Propriedade(Rm.NUMERO_RM));
            propriedades.add(new Propriedade(Rm.OBSERVACAO));
            propriedades.add(new Propriedade(Rm.PRIORIDADE));
            propriedades.add(new Propriedade(Rm.REQUISITANTE));
            propriedades.add(new Propriedade(Rm.USUARIO));
            propriedades.add(new Propriedade(Rm.LOCAL_ENTREGA));
            propriedades.add(new Propriedade(Rm.TIPO_REQUISICAO));
            propriedades.add(new Propriedade(Rm.EAP_MULTI_EMPRESA));
            propriedades.add(new Propriedade(Rm.TIPO_RM));
            
            cadastroRmVo.getRm().setTipoRm("MAT");
            
            if(cadastroRmVo.getRm().getCentro()==null) {
            	Usuario usuario = LoginService.getUsuarioLogado(request);
            	cadastroRmVo.getRm().setCentro(usuario.getCentro());
            }

            rmService.salvar(cadastroRmVo.getRm(), LoginService.getUsuarioLogado(request).getLogin());
            if(cadastroRmVo.getRmServico()!=null) {
            	cadastroRmVo.getRmServico().setRm(cadastroRmVo.getRm());
            	rmServicoDAO.saveOrUpdate(cadastroRmVo.getRmServico());
            	if(cadastroRmVo.getRmServico()!=null&&cadastroRmVo.getRmServico().getFornecedores()!=null) {
	            	for(FornecedorServico fs : cadastroRmVo.getRmServico().getFornecedores()) {
	            		fs.setRmServico(null);
	            	}
            	}
            }

            genericDao.beginTransaction();
            
            for (RmMaterialStatus rmMaterialStatus : rmMateriaisStatusExcluir) {
                genericDao.deleteWithCurrentTransaction(rmMaterialStatus);
            }

            for (RmMaterial rmMaterial : rmMateriaisExcluir) {
                genericDao.deleteWithCurrentTransaction(rmMaterial);
            }

            propriedades.clear();
            propriedades.add(new Propriedade(RmMaterial.MATERIAL));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO));
            propriedades.add(new Propriedade(RmMaterial.LOCAL_ENTREGA));
            propriedades.add(new Propriedade(RmMaterial.ORDEM));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_REQUISICAO_SAP));
            propriedades.add(new Propriedade(RmMaterial.ITEM_REQUISICAO_SAP));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_CUSTOS));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_ESTOQUE));
            propriedades.add(new Propriedade(RmMaterial.OPERACAO));
            propriedades.add(new Propriedade(RmMaterial.DIAGRAMA_REDE));

            //Para cada material, gera um status de cadastrada com data e um
            //status de enviado para aprovacao(proximo passo)
            int qtdeIncluido=0;
            for (RmMaterial rmMaterial : cadastroRmVo.getRmMateriais()) {
                rmMaterial.setRm(cadastroRmVo.getRm());
                rmMaterial.setCentro(cadastroRmVo.getRm().getCentro().getCodigo());

                if (rmMaterial.getMaterial().getMaterialId() == null || rmMaterial.getMaterial().getMaterialId() < 0) {
                    genericDao.persistWithCurrentTransaction(rmMaterial.getMaterial());
                }

                if (rmMaterial.getRmMaterialId() != null && rmMaterial.getRmMaterialId() > 0) {
                    genericDao.updateWithCurrentTransaction(rmMaterial, propriedades);
                } else {
                	qtdeIncluido++;
                    genericDao.persistWithCurrentTransaction(rmMaterial);
                    rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_CADASTRADA, genericDao, data, null, null);
                    rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_ENVIADA_APROVACAO, genericDao, null, null, null);
                }
            }
            
            if(qtdeIncluido>0) {
            	rastrearService.rastrearMaterialIncluido(cadastroRmVo.getRm(), qtdeIncluido, LoginService.getUsuarioLogado(request).getLogin());
            }

            for (RmDeposito rmDeposito : rmDepositosExcluir) {
                genericDao.deleteWithCurrentTransaction(rmDeposito);
            }

            propriedades.clear();
            propriedades.add(new Propriedade(RmDeposito.RM));
            propriedades.add(new Propriedade(RmDeposito.DEPOSITO));

            int qtdeDeposito=0;
            for (RmDeposito rmDeposito : cadastroRmVo.getRmDepositos()) {
                rmDeposito.setRm(cadastroRmVo.getRm());
                if (rmDeposito.getId() != null && rmDeposito.getId() > 0) {
                    genericDao.updateWithCurrentTransaction(rmDeposito, propriedades);
                } else {
                	qtdeDeposito++;
                    genericDao.persistWithCurrentTransaction(rmDeposito);
                }
            }
            if(qtdeDeposito>0) {
            	rastrearService.rastrearDepositoIncluido(cadastroRmVo.getRm(), qtdeDeposito, LoginService.getUsuarioLogado(request).getLogin());
            }
            genericDao.commitCurrentTransaction();

            info.setObjeto(cadastroRmVo);
            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setErro(null);
            
            //Envio de e-mail
            if(envEmailCriacao) {
            	
	            Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();
	            String link;
	
	            if (configuracao.getPrefUrlExternaSist() != null && !configuracao.getPrefUrlExternaSist().isEmpty()) {
	                link = configuracao.getPrefUrlExternaSist();
	            } else if (request != null) {
	                link = LoginService.getUrlServidor(request);
	            } else {
	                link = "";
	            }
	            List<Pessoa> requisitante = rmDAO.selectEmailPessoa(cadastroRmVo.getRm().getRequisitante().getPessoaId());
	            List<Pessoa> solicitante = rmDAO.selectEmailPessoa(cadastroRmVo.getRm().getUsuario().getPessoa().getPessoaId());
	            String recipientRequisitante = requisitante.get(0).getEmail();
	            String nomeRequisitante = requisitante.get(0).getNome();
	            String recipientSolicitante = solicitante.get(0).getEmail();
	            
	            StringBuilder materiaisComplBody = new StringBuilder();
	            materiaisComplBody.append("Materiais:<br/><br/>");
	            
	            for (RmMaterial material : cadastroRmVo.getRmMateriais()) {
	            	materiaisComplBody.append(
	                material.getOrdem() + 
	            			" - " +
	            	"Código Material: " +
	            	material.getMaterial().getCodigo() +
	            			" | " +
	            	"Material: " +
	            	material.getMaterial().getNome() +
	            			" | " +
	            	"Quantidade: " +
	            	material.getQuantidade());
	            	materiaisComplBody.append("<br/><br/>");
				}	            
	            
	            if(recipientSolicitante == null) {
	            	recipientSolicitante = "";
	            }
	            if(recipientRequisitante == null) {
	            	recipientRequisitante = "";
	            }
	            
	            String tituloEmail = Util.setParamsLabel(
	  	              getProperty("msg_titulo_email_cadastro_rma_material"), cadastroRmVo.getRm().getNumeroRm());
	            
	  	        String mensagemEmail = Util
	  	              .setParamsLabel(getProperty("msg_email_rma_cadastro_rma_material"), cadastroRmVo.getRm().getNumeroRm(),
	  	                nomeRequisitante, materiaisComplBody, link);
	            
	            final String recipientAll = String.join(",", recipientSolicitante, recipientRequisitante);
	            final String subject = tituloEmail;
	            final String recipients = recipientAll;
	            final String body = mensagemEmail;
	            
	            this.emailService.enviarEmail(subject, recipients, body);
            }
            
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
        }

        return info;
    }

    /**
     * Busca uma lista de Prioridades e suas configurações de dias por centro e área.
     **/
    private List<Prioridade> listarPrioridadeConf(Integer centroId, Integer areaId) {
        List<Prioridade> list = new ArrayList<>();
        try {
            PrioridadeService prioridadeService = new PrioridadeService();
            list = prioridadeService.getPrioridadesCentroAndArea(centroId, areaId);
        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(),
                            Util.getNomeMetodoAtual(), e));
        }

        return list;
    }

    /**
     * Realiza a chamada do service para listar as informacoes da rm e suas
     * listas filho
     *
     * @param rm
     * @return
     */
    @POST
    @Path("listarRm")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CadastroRmVo listarRm(Rm rm) {
        RmaService rmaService = new RmaService(request);
        return rmaService.listarCadastroRmVo(rm);
    }


    /**
     * Realiza a chamada do service para listar as informacoes da rm e suas
     * listas filho
     *
     * @param rmId
     * @return
     */
    @POST
    @Path("listarRmById")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CadastroRmVo listarRmById(Integer rmId) {
        RmaService rmaService = new RmaService(request);
        return rmaService.listarCadastroRmVoById(rmId);
    }

    /**
     * Valida os materiais que serão comprados e os que possuem quantidades em
     * outros depositos
     *
     * @param rm
     * @return
     */
    @POST
    @Path("verificarDepositos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ValidacaoMaterialVo verificarDepositos(Rm rm) {
        ValidacaoMaterialVo validacaoMaterialVo;

        RmaService rmaService = new RmaService(request);
        validacaoMaterialVo = rmaService.validarMateriais(rm, null);

        return validacaoMaterialVo;
    }


    /**
     * Valida os materiais que serão comprados e os que possuem quantidades em
     * outros depositos
     *
     * @param rm
     * @return
     */
    @POST
    @Path("validarAprovacaoCoordenador")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info validarAprovacaoCoordenador(Rm rm) {
        Boolean habilitaAprovacaoCoordenador = true;

        if(rm != null && rm.getRmId() > 0){
            RmaService rmaService = new RmaService(request);
            rm = rmaService.getRmEapMultiempresa(rm.getRmId());

            if(rm != null && rm.getEapMultiEmpresa() != null){
                if( rm.getEapMultiEmpresa().getAprovacaoCoordenador() != null && rm.getEapMultiEmpresa().getAprovacaoCoordenador() == false ) {
                    habilitaAprovacaoCoordenador = false;
                }
            }else{
                EapMultiEmpresaService eapService = new EapMultiEmpresaService();
                EapMultiEmpresa eap = eapService.getFirstEapMultiEmpresa();
                if(eap != null && eap.getAprovacaoCoordenador() != null && eap.getAprovacaoCoordenador() == false ){
                    habilitaAprovacaoCoordenador = false;
                }
            }
        }
        Info info = new Info();
        info.setObjeto(habilitaAprovacaoCoordenador);
        return info;
    }

    /**
     * Valida os materiais que serão comprados e os que possuem quantidades em
     * outros depositos
     *
     * @param cadastroRmVo
     * @return
     */
    @POST
    @Path("verificarDepositosEstoque")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ValidacaoMaterialVo verificarDepositosEstoque(CadastroRmVo cadastroRmVo) {
        ValidacaoMaterialVo validacaoMaterialVo;

        RmaService rmaService = new RmaService(request);
        validacaoMaterialVo = rmaService.validarMateriais(cadastroRmVo.getRm(), cadastroRmVo.getRmMaterial());

        return validacaoMaterialVo;
    }

    /**
     * Verifica todas as quantidades nos depositos
     *
     * @param listaRmMaterial
     * @return
     */
    @POST
    @Path("verificaQtdsMatDepositos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info verificaQtdsMatDepositos(List<RmMaterial> listaRmMaterial) {
        Info info = new Info();
        GenericDao genericDao = new GenericDao();

        Map<Material, Double> mapListaMateriais = new HashMap<>();

        try {
            for (RmMaterial rmMaterial : listaRmMaterial) {
                Double count = mapListaMateriais.containsKey(rmMaterial.getMaterial()) ? mapListaMateriais.get(rmMaterial.getMaterial()) : 0.0;
                mapListaMateriais.put(rmMaterial.getMaterial(), count + rmMaterial.getQuantidade());
                //rmMaterial.setQuantidade(count);
            }

            List<Propriedade> propriedades = new ArrayList<>();
            List<MaterialDeposito> materiaisDepositos;

            String aliasMaterial = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.DEPOSITO);
            String aliasUnidadeMedida = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL, Material.UNIDADE_MEDIDA);

            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.IS_SERVICO, Material.class, aliasMaterial));

            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            NxCriterion nxCriterion = null;
            nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, mapListaMateriais.keySet(), Filtro.IN));

            materiaisDepositos = genericDao.listarByFilter(propriedades, null, MaterialDeposito.class, Constantes.NO_LIMIT, nxCriterion);

            for (MaterialDeposito materiaisDeposito : materiaisDepositos) {
                if (materiaisDeposito.getQuantidade() == mapListaMateriais.get(materiaisDeposito.getMaterial())) {

                }
            }

        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return info;
    }

    /**
     * Envia a rm para aprovacao, gerando status para cada material e cria o
     * aprovador de acordo com o aprovador selecionado
     *
     * @param cadastroRmVo
     * @return
     */
    @POST
    @Path("mandarAprovar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info mandarAprovar(CadastroRmVo cadastroRmVo) {
        Info info = new Info();
        Date data = new Date();
        GenericDao<RmAprovador> genericDao = new GenericDao<>();
        RmMaterialStatus rmMaterialStatusAtual = new RmMaterialStatus();
        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);
        List<RmMaterialStatus> materiaisStatusAtual = new ArrayList<>();
        List<RmMaterialStatus> materiaisStatusAtualNew = new ArrayList<>();
        List<Propriedade> propriedades = new ArrayList<>();

        Usuario aprovador = cadastroRmVo.getUsuario();
        
        RmMaterialService rmMaterialService = new RmMaterialService();
        
        RmaService rmaService = new RmaService();
        Rm rm = rmaService.listarRm(cadastroRmVo.getRm());
        
        Workflow workflow = workflowService.getWorkflowRm(rm.getArea().getAreaId(), rm.getCentro().getCentroId());
        WorkflowEnum proximaEtapa = workflowService.getNextStep(rm, null);
        if(proximaEtapa==null) {
        	info.setCodigo(Info.INFO_002);
            info.setErro("");
            info.setMensagem(Constantes.ERRO_SEM_APROVADOR);
            info.setTipo(Info.TIPO_MSG_DANGER);
            return info;
        }
        
        try {
            List<RmMaterial> rmMateriais = rmMaterialService.listaRmMaterialByNumeroRm(rm.getNumeroRm());
            
            RmAprovador rmAprovador = new RmAprovador();
            rmAprovador.setRm(rm);
            rmAprovador.setNivelAprovador(proximaEtapa.getStep());
            rmAprovador.setAprovador(aprovador.getPessoa());

            String status = "";
            if(proximaEtapa == WorkflowEnum.WORKFLOW_AREA) {
            	rmAprovador.setUsuarioAprovadorArea(aprovador);
            	rmAprovador.setTipoAprovador(Constantes.TIPO_ATUACAO_COORDENADOR);
                status = Constantes.STATUS_RM_MATERIAL_APROVADA_A;
            }else if(proximaEtapa == WorkflowEnum.WORKFLOW_GERENTE_AREA) {
            	rmAprovador.setUsuarioAprovadorGerenteArea(aprovador);
              rmAprovador.setTipoAprovador(Constantes.TIPO_ATUACAO_GERENTE_AREA);
                status = Constantes.STATUS_RM_MATERIAL_APROVADA_GA;
            }else if(proximaEtapa == WorkflowEnum.WORKFLOW_CUSTO) {
            	rmAprovador.setUsuarioAprovadorCusto(aprovador);
                status = Constantes.STATUS_RM_MATERIAL_APROVADA_GC;
            }else if(proximaEtapa == WorkflowEnum.WORKFLOW_EMERGENCIAL) {
            	rmAprovador.setUsuarioAprovadorEmergencial(aprovador);
                status = Constantes.STATUS_RM_MATERIAL_APROVADA_E;
            }
            rmAprovador.setAprovadorVez(Boolean.TRUE);
            rmAprovador.setOrdem(1);

            materiaisStatusAtual = rmMaterialStatusService.listarStatusAtual(rmMateriais);

            genericDao.beginTransaction();
            if (rm.getDataReprovacao() != null) {
                rm.setDataReprovacao(null);
                rm.setReprovadoPor(null);

                propriedades.add(new Propriedade(RmMaterialStatus.IS_HISTORICO));

                //Gera um status para cada material
                for (RmMaterial rmMaterial : rmMateriais) {

                    List<RmMaterialStatus> listaMaterialStatus = rmMaterialStatusService.listarMaterialStatus(rmMaterial, Boolean.TRUE);

                    //Caso a requisicao tenha sido reprovada,
                    //atualizo os status antigos como historico
                    for (RmMaterialStatus materialStatus : listaMaterialStatus) {
                        if (!materialStatus.getStatus().getCodigo().equals(Constantes.STATUS_RM_MATERIAL_CADASTRADA)) {
                            materialStatus.setHistorico(Boolean.TRUE);
                            //genericDao.updateWithCurrentTransaction(materialStatus, propriedades);
                            materiaisStatusAtualNew.add(materialStatus);
                        }
                    }

                    //Gero o novo status de enviado para aprovacao
                    RmMaterialStatus ms = rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_ENVIADA_APROVACAO, null, data, null, null);
                    materiaisStatusAtualNew.add(ms);
                }
            }

            propriedades.clear();
            propriedades.add(new Propriedade(Rm.DATA_ENVIO_APROVACAO));
            propriedades.add(new Propriedade(Rm.JUSTIFICATIVA_ENVIO_APROVACAO));
            propriedades.add(new Propriedade(Rm.JUST_MATERIAIS_CAUTELADOS));
            propriedades.add(new Propriedade(Rm.DATA_REPROVACAO));
            propriedades.add(new Propriedade(Rm.REPROVADO_POR));
            /**if(enviaDiretoGerente){
                propriedades.add(new Propriedade(Rm.GERENTE_AREA));
                rm.setGerenteArea(cadastroRmVo.getAprovador());
            }else{
                propriedades.add(new Propriedade(Rm.COORDENADOR));
                rm.setCoordenador(cadastroRmVo.getAprovador());
            }*/
            rm.setDataEnvioAprovacao(data);
            
            rastrearService.rastrear(rm, LoginService.getUsuarioLogado(request).getLogin());
            
            genericDao.updateWithCurrentTransaction(rm, propriedades);

            genericDao.persistWithCurrentTransaction(rmAprovador);

            propriedades.clear();
            propriedades.add(new Propriedade(RmMaterialStatus.IS_HISTORICO));
            for (RmMaterialStatus statusNovo : materiaisStatusAtualNew) {
                if (statusNovo.getId() != null) {
                    genericDao.updateWithCurrentTransaction(statusNovo, propriedades);
                } else {
                    genericDao.persistWithCurrentTransaction(statusNovo);
                }
            }
            
            for (RmMaterial material : rmMateriais) {
                rmMaterialStatusAtual = rmMaterialStatusService.listarStatusAtual(materiaisStatusAtual, material);
                rmMaterialStatusService.finalizarStatus(rmMaterialStatusAtual, genericDao, data);
                rmMaterialStatusService.gerarStatus(material, status, genericDao, null, null, null);
            }

            genericDao.commitCurrentTransaction();

            Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();
            String link;

            if (configuracao.getPrefUrlExternaSist() != null && !configuracao.getPrefUrlExternaSist().isEmpty()) {
                link = configuracao.getPrefUrlExternaSist();
            } else if (request != null) {
                link = LoginService.getUrlServidor(request);
            } else {
                link = "";
            }

            String tituloEmail = Util.setParamsLabel(
              getProperty("msg_titulo_email_aprovacao_rma"), rmAprovador.getRm().getPrioridade().getDescricao());
            String mensagemEmail = Util
              .setParamsLabel(getProperty("msg_email_rma_envio"), rmAprovador.getRm().getNumeroRm(),
                rmAprovador.getRm().getRequisitante().getNome(), link);

            final String subject = tituloEmail + " - " + proximaEtapa.getStep();
            final String recipients = rmAprovador.getAprovador().getEmail();
            final String body = mensagemEmail;
            this.emailService.enviarEmail(subject, recipients, body);

            info.setCodigo(Info.INFO_001);
            info.setErro(null);
            info.setMensagem(Constantes.MSG_REQUISICAO_ENVIADO_APROVACAO_SUCESSO_I18N);
            info.setTipo(Info.TIPO_MSG_SUCCESS);

        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            info.setCodigo(Info.INFO_002);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.ERRO_OPERACAO_I18N);
            info.setTipo(Info.TIPO_MSG_DANGER);
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    /**
     * Envia email ao aprovador, com informacoes da rm e o link de aprovacao
     *
     * @param rmAprovador
     */
    private void enviarEmailAprovador(RmAprovador rmAprovador) {
        String email = "";
        String mensagemEmail = "";
        String tituloEmail;
        String link;

        try {
            log.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), RmAprovador.class.getName(), Util.getNomeMetodoAtual(), rmAprovador, null));

            email = rmAprovador.getAprovador().getEmail();

            Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();

            tituloEmail = Util.setParamsLabel(
              getProperty("msg_titulo_email_aprovacao_rma"), rmAprovador.getRm().getPrioridade().getDescricao());

            if (configuracao.getPrefUrlExternaSist() != null && !configuracao.getPrefUrlExternaSist().isEmpty()) {
                link = configuracao.getPrefUrlExternaSist();
            } else {
                link = LoginService.getUrlServidor(request);
            }

            //String parametro = Criptografia.cript("id=" + rmAprovador.getId());
            //link += request.getContextPath() + "/html5/rma/rmAprovacao.html#aprovacaoRma?id=" + parametro;
            link += request.getContextPath() + "/html5/rma/index.html";

            mensagemEmail = Util.setParamsLabel(getProperty("msg_email_rma_envio"), rmAprovador.getRm().getNumeroRm(), rmAprovador.getRm().getRequisitante().getNome(), link);

            /**Email2.enviaEmailPorThread(email, tituloEmail, mensagemEmail, null,
                    configuracao.getEmailHost(), configuracao.getEmailPorta(),
                    configuracao.getEmailOrigem(), configuracao.getEmailUser(),
                    configuracao.getEmailPasswd(),
                    configuracao.getEmailChave(), null, null);*/

            log.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), RmAprovador.class.getName(), Util.getNomeMetodoAtual(), rmAprovador, null));
        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
    }

    /**
     * Lista os status atuais do material para montar o grafico dos status
     *
     * @param rmMaterial
     * @return
     */
    @POST
    @Path("listarStatusRmMaterial")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<RmMaterialStatus> listarStatusRmMaterial(RmMaterial rmMaterial) {
        List<RmMaterialStatus> lista;

        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);
        lista = rmMaterialStatusService.listarMaterialStatus(rmMaterial, false);

        return lista;
    }

    /**
     * Exibe as informações do material em cada deposito selecionado
     *
     * @param cadastroRmVo
     * @return
     */
    @POST
    @Path("listarInformacoesMaterial")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<MaterialDeposito> listarInformacoesMaterial(CadastroRmVo cadastroRmVo) {
        GenericDao<MaterialDeposito> genericDao = new GenericDao<>();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

        List<MaterialDeposito> lista = new ArrayList<>();
        List<Propriedade> propriedades = new ArrayList<>();

        List<Deposito> depositos = new ArrayList<>();
        depositos.add(cadastroRmVo.getDeposito());
        
        try {
            String aliasDeposito = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.DEPOSITO);
            String aliasMaterial = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL);

            //MATERIAL DEPOSITO
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

            //MATERIAL
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.IS_SERVICO, Material.class, aliasMaterial));

            //DEPOSITO
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, cadastroRmVo.getMaterial(), Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.DEPOSITO, depositos, Filtro.IN)));

            lista = genericDao.listarByFilter(propriedades, null, MaterialDeposito.class, Constantes.NO_LIMIT, nxCriterion);

            //Para cada deposito selecionado, verifico se possui material
            //Caso não possua, adiciono o deposito na lista com quantidade 0
            for (final Deposito deposito : depositos) {
                if (!CollectionUtils.exists(lista, new Predicate() {
                    @Override
                    public boolean evaluate(Object object) {
                        MaterialDeposito materialDeposito = (MaterialDeposito) object;
                        return materialDeposito.getDeposito().equals(deposito);
                    }
                })) {
                    MaterialDeposito materialDeposito = new MaterialDeposito();
                    materialDeposito.setMaterial(cadastroRmVo.getMaterial());
                    materialDeposito.setDeposito(deposito);
                    materialDeposito.setQuantidade(0.0);

                    lista.add(materialDeposito);
                }
            }

            Double total = 0.0;
            for (MaterialDeposito materialDeposito : lista) {
                total += materialDeposito.getQuantidade();
            }

            //Adiciono um item na lista com o total dos materiais
            MaterialDeposito materialDeposito = new MaterialDeposito();
            materialDeposito.setMaterial(cadastroRmVo.getMaterial());
            materialDeposito.setDeposito(new Deposito(0, getProperty("label_total_material")));
            materialDeposito.setQuantidade(total);

            lista.add(materialDeposito);

            log.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), CadastroRmVo.class.getName(), Util.getNomeMetodoAtual(), cadastroRmVo, null));
        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    /**
     * Lista os materiais com a mesma hierarquia
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("listarMateriaisSimilares")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MateriaisSimilaresVo listarMateriaisSimilares(FiltroMateriaisSimilares filtro) {
        List<Propriedade> propriedades = new ArrayList<>();
        GenericDao<MaterialDeposito> genericDao = new GenericDao<>();
        MateriaisSimilaresVo materiaisSimilaresVo = new MateriaisSimilaresVo();
        List<MaterialDeposito> listaMaterialDeposito = new ArrayList<>();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

        try {
            propriedades.add(new Propriedade(Material.MATERIAL_ID));
            propriedades.add(new Propriedade(Material.NOME));
            propriedades.add(new Propriedade(Material.HIERARQUIA));
            propriedades.add(new Propriedade(Material.CODIGO));
            propriedades.add(new Propriedade(Material.IS_SERVICO));
			propriedades.add(new Propriedade(Material.GRUPO_MERCADORIA));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Material.MATERIAL_ID, filtro.getMaterial().getMaterialId(), Filtro.NOT_EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Material.GRUPO_MERCADORIA, filtro.getMaterial().getGrupoMercadoria(), Filtro.EQUAL)));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Material.CENTRO, filtro.getCodigoCentro(), Filtro.EQUAL)));

			// TODO - Avaliar se esse filtro de hierarquia realmente faz parte do negócio,
			// pois o front não manda esse dado preenchido!
			if (filtro.getMaterial().getHierarquia() != null) {
				nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(
						new Filtro(Material.HIERARQUIA, filtro.getMaterial().getHierarquia(), Filtro.EQUAL)));
			}

            List<NxOrder> orders = Arrays.asList(new NxOrder(Material.NOME, NxOrder.NX_ORDER.ASC));

            Paginacao paginacao = new Paginacao(propriedades, orders, Material.class, nxCriterion, filtro.getPaginacaoVo());

            List<Deposito> listaDepositos = listarDepositosPorCentro(filtro.getCentroId());

            List<Material> listaMateriais = (List<Material>) (Object) paginacao.getItensConsulta();

            if (!listaMateriais.isEmpty()) {
                Deposito depositoAux = new Deposito(0, getProperty("label_total").toUpperCase(), "0");

                listaDepositos.add(0, depositoAux);
                materiaisSimilaresVo.setDepositos(new ArrayList<Deposito>());
                materiaisSimilaresVo.getDepositos().addAll(listaDepositos);
                materiaisSimilaresVo.setMateriais(new ArrayList<MaterialDepositoQuantidadeVo>());

                for (final Material material : listaMateriais) {

                    MaterialDepositoQuantidadeVo materialDepositoQuantidadeVo = new MaterialDepositoQuantidadeVo();
                    materialDepositoQuantidadeVo.setDepositoQuantidade(new ArrayList<DepositoQuantidadeVo>());

                    for (final Deposito deposito : listaDepositos) {
                        materialDepositoQuantidadeVo.setMaterial(material);
                        materialDepositoQuantidadeVo.getDepositoQuantidade().add(new DepositoQuantidadeVo(deposito.getCodigo(), 0.0));
                    }
                    materiaisSimilaresVo.getMateriais().add(materialDepositoQuantidadeVo);
                }
            }

            materiaisSimilaresVo.setPaginacaoVo(filtro.getPaginacaoVo());

            log.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroMateriaisSimilares.class.getName(), Util.getNomeMetodoAtual(), filtro, null));
        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return materiaisSimilaresVo;
    }

    private List<Deposito> listarDepositosPorCentro(final Integer centro) {
        List<Deposito> depositos = new ArrayList<>();

        try {
            log.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), null, null));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));
            propriedades.add(new Propriedade(Deposito.NOME));
            propriedades.add(new Propriedade(Deposito.CODIGO));

            GenericDao<Deposito> genericDao = new GenericDao<>();

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Deposito.CENTRO_ID, centro, Filtro.EQUAL));

            depositos = genericDao.listarByFilter(propriedades, null, Deposito.class, Constantes.NO_LIMIT, nxCriterion);

            log.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return depositos;
    }

    @Deprecated
    private List<Deposito> listarDepositos() {
        List<Deposito> depositos = new ArrayList<>();

        try {
            log.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), null, null));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));
            propriedades.add(new Propriedade(Deposito.NOME));
            propriedades.add(new Propriedade(Deposito.CODIGO));

            GenericDao<Deposito> genericDao = new GenericDao<>();

            depositos = genericDao.listarByFilter(propriedades, null, Deposito.class, Constantes.NO_LIMIT, null);

            log.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return depositos;
    }

    /**
     * Cancela uma rm, setando data e justificativa e gerando status para cada
     * material
     *
     * @param rm
     * @return
     */
    @POST
    @Path("cancelarRequisicao")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info cancelarRequisicao(Rm rm) {
        Info info = new Info();
        RmMaterialStatus rmMaterialStatusAtual = null;
        GenericDao<VwRmMaterial> genericDao = new GenericDao<>();
        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);
        RmaService rmaService = new RmaService(request);

        List<RmMaterial> listaMateriais = rmaService.listarRmMateriais(rm);
        List<RmMaterialStatus> materiaisStatus = rmMaterialStatusService.listarStatusAtual(listaMateriais);

        List<RmMaterial> listaEnviarEmail = new ArrayList<>();
        try {

            List<RmMaterialStatus> rmCancelados = new ArrayList<>();
            rmCancelados = verificaStatusTravaCancelamento(materiaisStatus);

            if (rmCancelados.size() > 0) {
                info.setCodigo(Info.INFO_001);
                info.setErro(null);
                info.setMensagem(Constantes.MSG_REQUISICAO_NAO_CANCELADA_I18N);
                info.setTipo(Info.TIPO_MSG_DANGER);
                return info;
            }

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Rm.JUSTIFICATIVA_CANCELAMENTO));
            propriedades.add(new Propriedade(Rm.DATA_CANCELAMENTO));

            List<Propriedade> propUpdateRmMaterial = new ArrayList<>();
            propUpdateRmMaterial.add(new Propriedade(RmMaterial.OBSERVACAO_CANCELAMENTO));
            propUpdateRmMaterial.add(new Propriedade(RmMaterial.DATA_CANCELAMENTO));

            genericDao.beginTransaction();

            rm.setDataCancelamento(new Date());
            
            rastrearService.rastrear(rm, LoginService.getUsuarioLogado(request).getLogin());

            genericDao.updateWithCurrentTransaction(rm, propriedades);

            //Gera status para cada material
            Date data = new Date();
            for (RmMaterial rmMaterial : listaMateriais) {
                rmMaterialStatusAtual = rmMaterialStatusService.listarStatusAtual(materiaisStatus, rmMaterial);

                if (!rmMaterialStatusAtual.getStatus().getCodigo().equals("Can")) {
                    rmMaterialStatusService.finalizarStatus(rmMaterialStatusAtual, genericDao, data);
                    rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_CANCELADA, genericDao, data, rm.getJustificativaCancelamento(), null);

                    rmMaterial.setDataCancelamento(new Date());
                    rmMaterial.setObservacaoCancelamento(rm.getJustificativaCancelamento());
                    genericDao.updateWithCurrentTransaction(rmMaterial,propUpdateRmMaterial);

                    listaEnviarEmail.add(rmMaterial);
                }

            }

            genericDao.commitCurrentTransaction();

            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

            for (RmMaterial rmm: listaEnviarEmail) {
                RmMaterial rmmComprador = getRmMaterialComprador(rmm.getRmMaterialId());
                if(rmmComprador != null && rmmComprador.getComprador() != null && rmmComprador.getComprador().getEmail() != null) {
                    String dataCancelamento = Util.dateToString(rmm.getDataCancelamento(), getProperty("format_date"));

                    String tituloEmail = getProperty("msg_titulo_email_cancelamento_material_comprador");

                    String material = "";
                    if (rmm.getMaterial().getCodigo() != null && !rmm.getMaterial().getCodigo().isEmpty()){
                        material = rmm.getMaterial().getCodigo();
                    }
                    if (rmm.getMaterial().getNome() != null && !rmm.getMaterial().getNome().isEmpty()){
                        material = material + " " + rmm.getMaterial().getNome();
                    }

                    String mensagemEmail = Util.setParamsLabel(getProperty("msg_email_cancelamento_material_comprador"), material, rmm.getRm().getNumeroRm(), LoginService.getUsuarioLogado(request).getNome(),dataCancelamento,rmm.getObservacaoCancelamento());

                    String email = rmmComprador.getComprador().getEmail();

                    if (Email2.validaEmail(email)) {
                        final String subject = tituloEmail;
                        final String recipients = email;
                        final String body = mensagemEmail;
                        this.emailService.enviarEmail(subject, recipients, body);
                    }
                }
            }

            info.setCodigo(Info.INFO_001);
            info.setErro(null);
            info.setMensagem(Constantes.MSG_REQUISICAO_CANCELADA_SUCESSO_I18N);
            info.setTipo(Info.TIPO_MSG_SUCCESS);

            log.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), rm, null));
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            info.setCodigo(Info.INFO_002);
            info.setErro(null);
            info.setMensagem(Constantes.MSG_REQUISICAO_CANCELADA_ERRO_I18N);
            info.setTipo(Info.TIPO_MSG_DANGER);
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    /**
     * Cancela um material, setando data e justificativa e gerando status para o
     * mesmo
     *
     * @param rmMaterial
     * @return
     */
    @POST
    @Path("cancelarMaterial")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info cancelarMaterial(RmMaterial rmMaterial) {
        Info info = new Info();
        RmMaterialStatus rmMaterialStatusAtual = null;
        GenericDao<VwRmMaterial> genericDao = new GenericDao<>();
        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);
        rmMaterialStatusAtual = rmMaterialStatusService.listarStatusAtual(rmMaterial);
        RmaService rmaService = new RmaService(request);

        List<Propriedade> propriedades = new ArrayList<>();

        try {
            List<RmMaterial> listaMateriais = rmaService.listarRmMateriais(rmMaterial.getRm());
            //verifica se a requisição possui apenas um material, se sim, chamará o método para cancelamento da requisição
            if (listaMateriais.size() == 1) {
                rmMaterial.getRm().setJustificativaCancelamento(rmMaterial.getObservacaoCancelamento());
                return (cancelarRequisicao(rmMaterial.getRm()));
            }

            List<RmMaterialStatus> materiaisCancelados = new ArrayList<>();
            materiaisCancelados = verificaStatusTravaCancelamentoMaterial(rmMaterialStatusAtual);

            if (materiaisCancelados.size() > 0) {
                info.setCodigo(Info.INFO_001);
                info.setErro(null);
                info.setMensagem(Constantes.MSG_MATERIAL_NAO_CANCELADO_I18N);
                info.setTipo(Info.TIPO_MSG_DANGER);
                return info;
            }

            List<RmMaterialStatus> materiaisStatus = rmMaterialStatusService.listarStatusAtual(listaMateriais);
            List<RmMaterialStatus> materialCancelado = new ArrayList<>();
            materialCancelado = verificaQuantidadeStatus(materiaisStatus);

            if (listaMateriais.size() == materialCancelado.size() + 1) {
                rmMaterial.getRm().setJustificativaCancelamento(rmMaterial.getObservacaoCancelamento());
                return (cancelarRequisicao(rmMaterial.getRm()));
            }

            genericDao.beginTransaction();

            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO_CANCELAMENTO));
            propriedades.add(new Propriedade(RmMaterial.DATA_CANCELAMENTO));

            rmMaterial.setDataCancelamento(new Date());

            genericDao.updateWithCurrentTransaction(rmMaterial, propriedades);

            //Gera status para o material
            Date data = new Date();

            rmMaterialStatusService.finalizarStatus(rmMaterialStatusAtual, genericDao, data);
            rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_CANCELADA, genericDao, data, rmMaterial.getObservacaoCancelamento(), null);

            genericDao.commitCurrentTransaction();

            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

            RmMaterial rmmComprador = getRmMaterialComprador(rmMaterial.getRmMaterialId());
            if(rmmComprador != null && rmmComprador.getComprador() != null && rmmComprador.getComprador().getEmail() != null) {
                String dataCancelamento = Util.dateToString(rmMaterial.getDataCancelamento(), getProperty("format_date"));

                String tituloEmail = getProperty("msg_titulo_email_cancelamento_material_comprador");

                String material = "";
                if (rmMaterial.getMaterial().getCodigo() != null && !rmMaterial.getMaterial().getCodigo().isEmpty()){
                    material = rmMaterial.getMaterial().getCodigo();
                }
                if (rmMaterial.getMaterial().getNome() != null && !rmMaterial.getMaterial().getNome().isEmpty()){
                    material = material + " " + rmMaterial.getMaterial().getNome();
                }

                String mensagemEmail = Util.setParamsLabel(getProperty("msg_email_cancelamento_material_comprador"), material, rmMaterial.getRm().getNumeroRm(), LoginService.getUsuarioLogado(request).getNome(),dataCancelamento,rmMaterial.getObservacaoCancelamento());

                String email = rmmComprador.getComprador().getEmail();

                if (Email2.validaEmail(email)) {
                    final String subject = tituloEmail;
                    final String recipients = email;
                    final String body = mensagemEmail;
                    this.emailService.enviarEmail(subject, recipients, body);
                }
            }


            info.setCodigo(Info.INFO_001);
            info.setErro(null);
            info.setMensagem(Constantes.MSG_MATERIAL_CANCELADO_SUCESSO_I18N);
            info.setTipo(Info.TIPO_MSG_SUCCESS);

            log.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), rmMaterial, null));
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            info.setCodigo(Info.INFO_002);
            info.setErro(null);
            info.setMensagem(Constantes.MSG_MATERIAL_CANCELADO_ERRO_I18N);
            info.setTipo(Info.TIPO_MSG_DANGER);
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }
    
    
    @POST
    @Path("cancelarMaterialSap")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info cancelarMaterialSap(RmMaterial rmMaterial) {
        Info info = new Info();
        RmMaterialStatus rmMaterialStatusAtual = null;
        GenericDao<VwRmMaterial> genericDao = new GenericDao<>();
        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);
        rmMaterialStatusAtual = rmMaterialStatusService.listarStatusAtual(rmMaterial);
        RmaService rmaService = new RmaService(request);

        List<Propriedade> propriedades = new ArrayList<>();

        try {
            List<RmMaterial> listaMateriais = rmaService.listarRmMateriais(rmMaterial.getRm());
            //verifica se a requisição possui apenas um material, se sim, chamará o método para cancelamento da requisição
            if (listaMateriais.size() == 1) {
                rmMaterial.getRm().setJustificativaCancelamento(rmMaterial.getObservacaoCancelamento());
                return (cancelarRequisicao(rmMaterial.getRm()));
            }

            List<RmMaterialStatus> materiaisCancelados = new ArrayList<>();
            materiaisCancelados = verificaStatusTravaCancelamentoMaterial(rmMaterialStatusAtual);

            if (materiaisCancelados.size() > 0) {
                info.setCodigo(Info.INFO_001);
                info.setErro(null);
                info.setMensagem(Constantes.MSG_MATERIAL_JA_CANCELADO_I18N);
                info.setTipo(Info.TIPO_MSG_DANGER);
                return info;
            }

            List<RmMaterialStatus> materiaisStatus = rmMaterialStatusService.listarStatusAtual(listaMateriais);
            List<RmMaterialStatus> materialCancelado = new ArrayList<>();
            materialCancelado = verificaQuantidadeStatus(materiaisStatus);

            if (listaMateriais.size() == materialCancelado.size() + 1) {
                rmMaterial.getRm().setJustificativaCancelamento(rmMaterial.getObservacaoCancelamento());
                return (cancelarRequisicao(rmMaterial.getRm()));
            }

            genericDao.beginTransaction();

            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO_CANCELAMENTO));
            propriedades.add(new Propriedade(RmMaterial.DATA_CANCELAMENTO));

            rmMaterial.setDataCancelamento(new Date());

            genericDao.updateWithCurrentTransaction(rmMaterial, propriedades);

            //Gera status para o material
            Date data = new Date();

            rmMaterialStatusService.finalizarStatus(rmMaterialStatusAtual, genericDao, data);
            rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_CANCELADA, genericDao, data, rmMaterial.getObservacaoCancelamento(), null);

            genericDao.commitCurrentTransaction();

            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

            RmMaterial rmmComprador = getRmMaterialComprador(rmMaterial.getRmMaterialId());
            if(rmmComprador != null && rmmComprador.getComprador() != null && rmmComprador.getComprador().getEmail() != null) {
                String dataCancelamento = Util.dateToString(rmMaterial.getDataCancelamento(), getProperty("format_date"));

                String tituloEmail = getProperty("msg_titulo_email_cancelamento_material_comprador");

                String material = "";
                if (rmMaterial.getMaterial().getCodigo() != null && !rmMaterial.getMaterial().getCodigo().isEmpty()){
                    material = rmMaterial.getMaterial().getCodigo();
                }
                if (rmMaterial.getMaterial().getNome() != null && !rmMaterial.getMaterial().getNome().isEmpty()){
                    material = material + " " + rmMaterial.getMaterial().getNome();
                }

                String mensagemEmail = Util.setParamsLabel(getProperty("msg_email_cancelamento_material_comprador"), material, rmMaterial.getRm().getNumeroRm(), LoginService.getUsuarioLogado(request).getNome(),dataCancelamento,rmMaterial.getObservacaoCancelamento());

                String email = rmmComprador.getComprador().getEmail();

                if (Email2.validaEmail(email)) {
                    final String subject = tituloEmail;
                    final String recipients = email;
                    final String body = mensagemEmail;
                    this.emailService.enviarEmail(subject, recipients, body);
                }
            }


            info.setCodigo(Info.INFO_001);
            info.setErro(null);
            info.setMensagem(Constantes.MSG_MATERIAL_CANCELADO_SUCESSO_I18N);
            info.setTipo(Info.TIPO_MSG_SUCCESS);

            log.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), rmMaterial, null));
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            info.setCodigo(Info.INFO_002);
            info.setErro(null);
            info.setMensagem(Constantes.MSG_MATERIAL_CANCELADO_ERRO_I18N);
            info.setTipo(Info.TIPO_MSG_DANGER);
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    /**
     * Envia a rm para um gerete da obra, com uma justificativa
     *
     * @param cadastroRmVo
     * @return
     */
    @POST
    @Path("redigirJustificativa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info redigirJustificativa(CadastroRmVo cadastroRmVo) {
        Info info = new Info();

        GenericDao<RmAprovador> genericDao = new GenericDao<>();
        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);

        try {
            RmAprovador rmAprovador = new RmAprovador();
            rmAprovador.setRm(cadastroRmVo.getRm());
            rmAprovador.setAprovador(cadastroRmVo.getAprovador());
            rmAprovador.setAprovadorVez(Boolean.TRUE);
            rmAprovador.setOrdem(2);
            rmAprovador.setTipoAprovador(Constantes.TIPO_ATUACAO_GERENTE_OBRA);

            if (cadastroRmVo.getRmMateriais() == null) {
                RmaService rmaService = new RmaService(request);
                cadastroRmVo.setRmMateriais(rmaService.listarRmMateriais(cadastroRmVo.getRm()));
            }

            genericDao.beginTransaction();

            //Gera um status para cada material
            for (RmMaterial rmMaterial : cadastroRmVo.getRmMateriais()) {
                rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_APROVADA_GO, genericDao, null, null, null);
            }

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Rm.JUSTIFICATIVA_GERENTE_OBRA));
            propriedades.add(new Propriedade(Rm.DATA_REPROVACAO));
            propriedades.add(new Propriedade(Rm.REPROVADO_POR));
            propriedades.add(new Propriedade(Rm.GERENTE_CUSTOS));

            cadastroRmVo.getRm().setDataReprovacao(null);
            cadastroRmVo.getRm().setReprovadoPor(null);
            cadastroRmVo.getRm().setGerenteCustos(null);

            genericDao.updateWithCurrentTransaction(cadastroRmVo.getRm(), propriedades);

            genericDao.persistWithCurrentTransaction(rmAprovador);

            genericDao.commitCurrentTransaction();

            enviarEmailAprovador(rmAprovador);

            info.setCodigo(Info.INFO_001);
            info.setErro(null);
            info.setMensagem(Constantes.MSG_REQUISICAO_ENVIADO_APROVACAO_SUCESSO_I18N);
            info.setTipo(Info.TIPO_MSG_SUCCESS);

        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            info.setCodigo(Info.INFO_002);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.ERRO_OPERACAO_I18N);
            info.setTipo(Info.TIPO_MSG_DANGER);
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return info;
    }

    /**
     * Cadastro rapido de material pela tela de cadastro de rma
     *
     * @param cadastroMaterial
     * @return
     */
    @POST
    @Path("cadastrarMaterial")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info cadastrarMaterial(CadastroMaterial cadastroMaterial) {
        Info info = new Info();
        GenericDao<Material> genericDao = new GenericDao<>();

        try {
            if ((cadastroMaterial.getMaterial().getNome() == null || cadastroMaterial.getMaterial().getNome().isEmpty())) {
                info.setObjeto(cadastroMaterial.getMaterial());
                info.setCodigo(Info.INFO_002);
                info.setTipo(Info.TIPO_MSG_DANGER);
                info.setMensagem(Constantes.MSG_PREENCHER_NOME_MATERIAL);

                return info;
            }
            if (cadastroMaterial.getMaterial().getUnidadeMedida() == null &&
                    (cadastroMaterial.getMaterial().getIsServico() == null || cadastroMaterial.getMaterial().getIsServico() != true)) {
                info.setObjeto(cadastroMaterial.getMaterial());
                info.setCodigo(Info.INFO_002);
                info.setTipo(Info.TIPO_MSG_DANGER);
                info.setMensagem(Constantes.MSG_PREENCHER_UNIDADE_MEDIDA);

                return info;
            }

            TipoMaterialIntegracaoService tipoMaterialIntegracaoService = new TipoMaterialIntegracaoService();
            cadastroMaterial.getMaterial().setTipoMaterial(tipoMaterialIntegracaoService.searchTipoMaterial(Constantes.NAO_CAUTELAVEL));

            genericDao.persist(cadastroMaterial.getMaterial());

            info.setObjeto(cadastroMaterial.getMaterial());
            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setErro(null);
        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(Constantes.ERRO_OPERACAO_I18N);
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);

        }
        return info;
    }

    /**
     * Cadastro rapido de material pela tela de cadastro de rma
     *
     * @param cadastroMaterial
     * @return
     */
    @POST
    @Path("enviarEmailSuprimentos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info enviarEmailSuprimentos(CadastroMaterial cadastroMaterial) {
        Info info = new Info();
        RmaService rmaService = new RmaService(request);
        String emails = "";
        String mensagemEmail = "";
        String tituloEmail;

        try {
            log.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Material.class.getName(), Util.getNomeMetodoAtual(), cadastroMaterial.getMaterial(), null));

            for (Pessoa p : rmaService.listarPessoaRole(Constantes.ROLE_MATERIAIS_SEM_CODIGO_SAP)) {
                if (p.getEmail() != null) {
                    emails += p.getEmail() + ";";
                }
            }

            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

            tituloEmail = getProperty("msg_titulo_email_material_para_validacao");

            mensagemEmail = Util.setParamsLabel(
              getProperty("msg_email_material_para_validacao"), cadastroMaterial.getUsuario().getNome(), cadastroMaterial.getMaterial().getNome(), cadastroMaterial.getRm().getNumeroRm());

            if (cadastroMaterial.getMaterial().getIsServico() == null || (cadastroMaterial != null && cadastroMaterial.getMaterial() != null && cadastroMaterial.getMaterial().getIsServico() != null && cadastroMaterial.getMaterial().getIsServico() == false)) {

                final String subject = tituloEmail;
                final String recipients = emails;
                final String body = mensagemEmail;
                this.emailService.enviarEmail(subject, recipients, body);
            }

            log.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Material.class.getName(), Util.getNomeMetodoAtual(), cadastroMaterial.getMaterial(), null));

            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setErro(null);
        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
        }

        return info;
    }

    /**
     * @param rm
     * @return
     */
    @POST
    @Path("verificaReprovador")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RmAprovador verificaReprovador(Rm rm) {

        GenericDao<RmAprovador> genericDao = new GenericDao<>();
        RmAprovador rmAprovador = new RmAprovador();

        try {
            String aliasRm = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID);
            String aliasPessoa = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.APROVADOR_PESSOA_ID);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmAprovador.ID));
            propriedades.add(new Propriedade(RmAprovador.TIPO_APROVADOR));

            //RM
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));

            //Pessoa
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasPessoa));

            //Faz a consulta para gerar a unica rm com os dados solicitados
            NxCriterion nxCriterion = null;
            NxCriterion nxCriterionAux;

            nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmAprovador.TIPO_APROVADOR, rm.getReprovadoPor(), Filtro.EQUAL));
            nxCriterion = nxCriterionAux;

            nxCriterionAux = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Rm.RM_ID, rm.getRmId(), Filtro.EQUAL, aliasRm)));
            if (nxCriterion != null) {
                nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
            } else {
                nxCriterion = nxCriterionAux;
            }

            List<NxOrder> orders = Arrays.asList(new NxOrder(RmAprovador.ID, NxOrder.NX_ORDER.DESC));

            rmAprovador = genericDao.listarByFilter(propriedades, orders, RmAprovador.class, 1, nxCriterion).get(0);

        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));

        }
        return rmAprovador;
    }

    /**
     * Atualiza os rmMaterial com o comprador selecionado
     *
     * @param listaMaterial
     * @return
     */
    @POST
    @Path("atribuirComprador")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info atribuirComprador(List<RmMaterial> listaMaterial) {
        Info info = new Info();
        Date data = new Date();
        GenericDao<RmMaterial> genericDao = new GenericDao<>();
        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(RmMaterial.COMPRADOR));
        propriedades.add(new Propriedade(RmMaterial.DATA_ATRIBUIR_COMPRADOR));
        propriedades.add(new Propriedade(RmMaterial.CONCLUIDA_COMPRADOR));

        try {
            genericDao.beginTransaction();
            for (RmMaterial rmMaterial : listaMaterial) {
//                Boolean isRequisiçãoManuAndCodReqSapNotNull;
//                //Se caso na verificação for requisição de manutenção e tiver o código de requisição SAP, então recebo true.
//                isRequisiçãoManuAndCodReqSapNotNull = verificaTipoRequisicaoAndCodigoSap(rmMaterial.getRmMaterialId());
//
//                if (isRequisiçãoManuAndCodReqSapNotNull != null && isRequisiçãoManuAndCodReqSapNotNull == true) {
//                    rmMaterial.setConcluidaComprador(true);
//                }
                rmMaterial.setDataAtribuirComprador(data);
                genericDao.updateWithCurrentTransaction(rmMaterial, propriedades);
            }
            genericDao.commitCurrentTransaction();

            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setErro(null);
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
        }

        return info;
    }

    /**
     * Lista os aprovadores da rm
     *
     * @param rm
     * @return
     */
    @POST
    @Path("listarAprovadores")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<RmAprovador> listarAprovadores(Rm rm) {
        List<RmAprovador> listaAprovadores = null;

        try {
            String aliasRequisicao = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID);
            String aliasAprovador = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.APROVADOR_PESSOA_ID);
            String aliasRequisitante = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID, Rm.REQUISITANTE);
            String aliasUsuario = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID, Rm.USUARIO);
            String aliasUsuarioPessoa = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID, Rm.USUARIO, Usuario.PESSOA);
            String aliasPrioridade = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID, Rm.PRIORIDADE);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmAprovador.ID));
            propriedades.add(new Propriedade(RmAprovador.APROVADOR_VEZ));
            propriedades.add(new Propriedade(RmAprovador.TIPO_APROVADOR));
            propriedades.add(new Propriedade(RmAprovador.DATA_AVALIACAO));

            //Requisicao
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRequisicao));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRequisicao));

            //uSUARIO
            propriedades.add(new Propriedade(Usuario.USUARIO_ID, Usuario.class, aliasUsuario));

            //Usuario Pessoa
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasUsuarioPessoa));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasUsuarioPessoa));
            propriedades.add(new Propriedade(Pessoa.EMAIL, Pessoa.class, aliasUsuarioPessoa));

            //Requisitante
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.EMAIL, Pessoa.class, aliasRequisitante));

            //aprovador
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasAprovador));
            propriedades.add(new Propriedade(Pessoa.EMAIL, Pessoa.class, aliasAprovador));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasAprovador));

            //prioridade
            propriedades.add(new Propriedade(Prioridade.PRIORIDADE_ID, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.DESCRICAO, Prioridade.class, aliasPrioridade));

            NxCriterion nxCriterion;

            nxCriterion = NxCriterion.montaRestriction(new Filtro(RmAprovador.RM_ID, rm, Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmAprovador.TIPO_APROVADOR, Constantes.TIPO_ATUACAO_LIDER_CUSTOS, Filtro.NOT_EQUAL)));
            //nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmAprovador.TIPO_APROVADOR, Constantes.TIPO_ATUACAO_CUSTOS, Filtro.NOT_EQUAL)));

            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(RmAprovador.ORDEM, NxOrder.NX_ORDER.ASC));

            GenericDao<RmAprovador> genericDao = new GenericDao<>();
            listaAprovadores = genericDao.listarByFilter(propriedades, nxOrders, RmAprovador.class, Constantes.NO_LIMIT, nxCriterion);
        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return listaAprovadores;
    }

    /**
     * Altera o aprovador por outra pessoa de mesmo cargo
     *
     * @param rmAprovador
     * @return
     */
    @POST
    @Path("alterarAprovador")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info alterarAprovador(RmAprovador rmAprovador) {
        Info info = new Info();
        GenericDao<RmAprovador> genericDao = new GenericDao<>();
        GenericDao<Rm> genericDaoRm = new GenericDao<>();

        try {

            if (rmAprovador.getTipoAprovador().equals(Constantes.TIPO_ATUACAO_GERENTE_AREA)) {
                List<Propriedade> propriedadesRmAtualiza = new ArrayList<>();
                propriedadesRmAtualiza.add(new Propriedade(Rm.GERENTE_AREA));

                Rm rmAtualiza = new Rm();
                rmAtualiza.setRmId(rmAprovador.getRm().getRmId());
                rmAtualiza.setGerenteArea(rmAprovador.getAprovador());

                genericDaoRm.update(rmAtualiza, propriedadesRmAtualiza);
            }
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmAprovador.APROVADOR_PESSOA_ID));

            genericDao.update(rmAprovador, propriedades);

            enviarEmailAprovador(rmAprovador);

            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem("msg_aprovador_alterado_sucesso");
            info.setErro(null);

        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem("msg_aprovador_alterado_erro");
        }

        return info;
    }

    @POST
    @Path("geraExcel")
    @Consumes(MediaType.APPLICATION_JSON)
    public TemplateRmGerarExcelResponse geraExcel(FiltroCadastroRma filtro) {
        try {
            final List<VwConsultaRma> listar = (List<VwConsultaRma>) listar(filtro, false);

            TemplateRmGerarExcelResponse xls = new TemplateRmGerarExcelResponse(
              Arrays.asList(
                "Número",
                "Centro",
                "Área",
                "Prioridade",
                "Código Material",
                "Material",
                "Unidade Medida",
                "Quantidade Solicitada",
                "Quantidade Comprada",
                "Quantidade Recebida",
                "Data Recebimento",
                "Saldo a Receber",
                "Opção da Requisição",
                "Observações da Rm",
                "Observação Status",
                "Status",
                "Usuário Cadastrante",
                "Requisitante",
                "Emissão",
                "Necessidade",
                "Número Requisição SAP",
                "Item requisição Sap",
                "Número Pedido Compra SAP",
                "Pedido Criado em",
                "Data Previsão Entrega",
                "Número documento",
                "Grupo Comprador",
                "Coordenador de Área",
                "Aprovação Coordenador",
                "Gerente de Área",
                "Aprovação Gerente de Area",
                "Gerente de Custos",
                "Aprovação Gerente de Custos",
                "Aprovador Máquina Parada",
                "Aprovação Máquina Parada",
                "Complemento de Custos"
              ));

            final List<LinhaRmGerarExcelResponse> linhas = Optional.ofNullable(listar).orElse(Collections.emptyList()).stream().map(mapperConsultaParaResponse::apply).collect(Collectors.toList());

            if(!linhas.isEmpty()) {
                preencherInformacoesAprovadores(listar, linhas);
                preencherInformacoesSaldoMateriaisCompradosERecebidos(listar, linhas);
                xls.setLinhas(linhas);
            }
            return xls;
        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(),
                    this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return null;
    }

    private void preencherInformacoesAprovadores(List<VwConsultaRma> lista, List<LinhaRmGerarExcelResponse> linhas) {
        try {
            List<ObterAprovadoresRmMaterialRequest> request =
              Optional.ofNullable(lista).orElse(Collections.emptyList()).stream().map(
                consulta -> new ObterAprovadoresRmMaterialRequest(consulta.getRmRmId(),
                  Integer.parseInt(consulta.getRmMaterialRmMaterialId()))).collect(Collectors.toList());

            List<ObterAprovadoresRmMaterialResponse> aprovadores =
              this.obterAprovadoresRmMaterialService.obterAprovadoresMaterial(request);


            Map<String, ObterAprovadoresRmMaterialResponse> mapAprovadoresRm =
              Optional.ofNullable(aprovadores).orElse(Collections.emptyList())
                .stream()
                .collect(
                    Collectors.toMap(
                      aprovador -> aprovador.getIdRm() + "-" + aprovador.getIdRmMaterial() , aprovador-> aprovador
                    )
                );


            if(mapAprovadoresRm != null && !mapAprovadoresRm.isEmpty()) {
                // obter dados aprovadores rma
                Optional.ofNullable(linhas).orElse(Collections.emptyList()).stream().forEach(l -> {

                    ObterAprovadoresRmMaterialResponse aprov =
                      mapAprovadoresRm.get(l.getIdRm() + "-" + l.getIdRmMaterial());

                    if(aprov != null) {
                        l.setNomeCoordenador(string(aprov.getNomeAprovCoordenador()));
                        l.setAprovacaoCoordenador(string(aprov.getDataAprovCoordenador()));

                        l.setAprovacaoGerenteArea(string(aprov.getDataAprovGerenteArea()));
                        l.setNomeGerenteArea(string(aprov.getNomeAprovGerenteArea()));

                        l.setNomeGerenteCustos(string(aprov.getNomeAprovGerenteCusto()));
                        l.setAprovacaoGerenteCustos(string(aprov.getDataAprovGerenteCusto()));

                        l.setAprovacaoMaquinaParada(string(aprov.getDataAprovMaquinaParada()));
                        l.setNomeMaquinaParada(string(aprov.getNomeAprovMaquinaParada()));
                    }

                });
            }

        } catch (Exception e) {
            log.error("Erro ao montar response XLS com dado dos aprovadores.", e);
        }
    }

    private void preencherInformacoesSaldoMateriaisCompradosERecebidos(List<VwConsultaRma> lista, List<LinhaRmGerarExcelResponse> linhas) {
        try {
            List<Integer> rmas =
              Optional.ofNullable(lista).orElse(Collections.emptyList()).stream().map(
                consulta -> Integer.parseInt(consulta.getRmMaterialRmMaterialId())).collect(Collectors.toList());

            Set<ObterSaldoRequisicaoMaterialResponse> saldos =
              this.obterSaldoRequisicaoMaterialService.obterSaldo(rmas).stream().collect(Collectors.toSet());


            Map<Integer, ObterSaldoRequisicaoMaterialResponse> mapSaldoRma =
              Optional.ofNullable(saldos).orElse(Collections.emptySet())
                .stream()
                .collect(
                  Collectors.toMap(
                    saldo -> saldo.getIdRmMaterial(), saldo -> saldo
                  )
                );


            if(mapSaldoRma != null && !mapSaldoRma.isEmpty()) {
                // obter dados aprovadores rma
                Optional.ofNullable(linhas).orElse(Collections.emptyList()).stream().forEach(l -> {

                    ObterSaldoRequisicaoMaterialResponse saldo =
                      mapSaldoRma.get(l.getIdRmMaterial());

                    if(saldo != null) {
                        l.setQtdeComprada(string(saldo.getQuantidadeComprada()));
                        l.setQtdeRecebida(string(saldo.getQuantidadeRecebida()));
                        l.setSaldoReceber(
                            l.getStatus().equals("Aguardando Retirada") ? string(saldo.getSaldoAReceber()) : "---"
                        );
                    }
                    l.setIdRm(null);
                    l.setIdRmMaterial(null);
                });
            }

        } catch (Exception e) {
            log.error("Erro ao montar response XLS com dado dos aprovadores.", e);
        }
    }

    Function<VwConsultaRma, LinhaRmGerarExcelResponse> mapperConsultaParaResponse = consulta -> {
        LinhaRmGerarExcelResponse response = new LinhaRmGerarExcelResponse();
        response.setNumeroRm(string(consulta.getRmNumeroRm()));
        response.setPrioridade(string(consulta.getPrioridadeDescricao()));
        response.setCodigoMaterial(string(consulta.getMaterialCodigo()));
        response.setNomeMaterial(string(consulta.getMaterialNome()));
        response.setUnidadeMedida(string(consulta.getUnidadeMedidaSigla()));
        response.setCentro(string(consulta.getCentroNome()));
        response.setArea(string(consulta.getAreaNome()));
        response.setQtdeSolicitada(string(consulta.getRmMaterialQuantidade()));
        response.setDataRecebimento(stringDate(consulta.getRmDataRecebimento()));
        response.setStatus(string(consulta.getStatusNome()));
        response.setObservacaoStatus(string(consulta.getRmMaterialStatusObservacao()));
        final String opcao = preencherOpcaoRequisicao(consulta);
        response.setOpcaoDaRequisicao(string(opcao));
        response.setUsuarioCadastrante(string(consulta.getUsuarioPessoaNome()));
        response.setRequisitante(string(consulta.getRequisitanteNome()));
        response.setObservacoes(string(consulta.getRmObservacao()));
        response.setEmissao(stringDate(consulta.getRmDataEmissao()));
        response.setNecessidade(stringDate((consulta.getRmDataAplicacao())));
        response.setDataCriacaoPedido(stringDate(consulta.getRmMaterialDataCompra()));
        response.setNumeroRequisicaoSap(string(consulta.getRmMaterialNumeroRequisicaoSap()));
        response.setItemRequisicaoSap(string(consulta.getRmMaterialItemRequisicaoSap()));
        response.setNumeroPedidoCompraSap(string(consulta.getRmMaterialNumeroPedidoCompra()));
        response.setDataPrevistaEntrega(stringDate(consulta.getRmMaterialDataPrevistaEntr()));
        
        Integer idRmMaterial =
          consulta.getRmMaterialRmMaterialId() != null ? Integer.parseInt(consulta.getRmMaterialRmMaterialId()) : null;
        response.setIdRm(consulta.getRmRmId());
        response.setIdRmMaterial(idRmMaterial);

        response.setComplementoCustos(stringDate(consulta.getDataAprovComplementoCustos()));
        response.setNumeroDocumento(string(consulta.getRmMaterialNumeroDocEntrada()));
        response.setGrupoComprador(string(consulta.getRmMaterialGrupoComprador()));

        return response;
    };

    private String preencherOpcaoRequisicao(VwConsultaRma consulta) {
        String opcaoDaRequisicao = string(consulta.getRmMaterialColetorCustos());

        /**
         * Elemento EP
         */
        if (!opcaoDaRequisicao.isEmpty()) {
            opcaoDaRequisicao = "Elemento PEP: " + opcaoDaRequisicao;
        }
        /**
         * ESTOQUE
         */
        if (opcaoDaRequisicao.isEmpty()) {
            opcaoDaRequisicao = string(consulta.getRmMaterialColetorOrdem());
            if (!opcaoDaRequisicao.isEmpty()) {
                opcaoDaRequisicao = "Estoque: " + opcaoDaRequisicao;
            }
        }
        /**
         * CENTRO CUSTO
         */
        if (opcaoDaRequisicao.isEmpty()) {
            opcaoDaRequisicao = string(consulta.getRmMaterialDiagramaRede());
            if (!opcaoDaRequisicao.isEmpty()) {
                opcaoDaRequisicao = "Centro de Custo: " + opcaoDaRequisicao;
            }
        }

        return opcaoDaRequisicao;
    }

    @POST
    @Path("alterarQuantidade")
    @Consumes("application/json")
    public Info alterarQuantidade(RmMaterial rmMaterial) {
        Info info = new Info();

        GenericDao<RmMaterial> genericDao = new GenericDao<>();

        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));
            propriedades.add(new Propriedade(RmMaterial.JUSTIFICATIVA_ALTERACAO_QUANTIDADE));

            genericDao.update(rmMaterial, propriedades);

            info.setObjeto(rmMaterial);
            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setErro(null);
        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));

            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
        }
        return info;
    }

    @POST
    @Path("alterarPrioridade")
    @Consumes("application/json")
    public Info alterarPrioridade(Rm rm) {
        Info info = new Info();
        GenericDao<Rm> genericDao = new GenericDao<>();
        RmaService rmaService = new RmaService(request);

        try {
            List<RmMaterial> listaRmMaterial = rmaService.listarRmMateriais(rm);

            genericDao.beginTransaction();

            //Altero a prioridade da rm
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Rm.PRIORIDADE));
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO));

            if(rm.getStDataAplicacao() != null) {
                final Date dataAplicacao = Util.parseData(rm.getStDataAplicacao(), getProperty("format_date"));
                rm.setDataAplicacao(dataAplicacao);
            }

            Rm rmDb = rmService.selectByUnique(rm.getRmId());
            rmDb.setPrioridade(rm.getPrioridade());
            rmDb.setDataAplicacao(calcularDataAplicacaoPorPrioridade(rmDb));



            genericDao.updateWithCurrentTransaction(rmDb, propriedades);

            //Altero a data prev entrega de suprimetos dos rmMateriais
            propriedades.clear();
            propriedades.add(new Propriedade(RmMaterial.DATA_PREV_ENTREGA_SUPRIMENTOS));

            for (RmMaterial rmMaterial : listaRmMaterial) {
                if (rmMaterial.getDataRecebimentoSuprimentos() != null) {
                    rmMaterial.setDataPrevEntregaSuprimentos(Util.addDia(rmMaterial.getDataRecebimentoSuprimentos(), rm.getPrioridade().getConfDiasPrevEntrega()));
                    genericDao.updateWithCurrentTransaction(rmMaterial, propriedades);
                }
            }

            genericDao.commitCurrentTransaction();

            info.setObjeto(rm);
            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setErro(null);
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));

            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
        }
        return info;
    }

    private Date calcularDataAplicacaoPorPrioridade(Rm rm) throws Exception {
        try {
            final List<Prioridade> prioridades = listarPrioridadeConf(rm.getCentro().getCentroId(), rm.getArea().getAreaId());

            if(prioridades != null && !prioridades.isEmpty()) {
                final Map<String, Prioridade> mapPrioridades = prioridades
                  .stream()
                  .collect
                    (Collectors.toMap(Prioridade::getCodigo, Function.identity()));

                final Prioridade maquinaParada = mapPrioridades.get(MAQ_PARADA.name());
                final Prioridade urgente = mapPrioridades.get(URGENTE.name());
                final Prioridade normal = mapPrioridades.get(NORMAL.name());

                if(rm.getPrioridade().getCodigo().equals(MAQ_PARADA.name())) {
                    return Util.addDia(new Date(), maquinaParada.getConfDiasPrevEntrega());
                } else if(rm.getPrioridade().getCodigo().equals(URGENTE.name())) {
                    return Util.addDia(new Date(), maquinaParada.getConfDiasPrevEntrega() + urgente.getConfDiasPrevEntrega());
                } else if(rm.getPrioridade().getCodigo().equals(NORMAL.name())) {
                    return Util.addDia(new Date(), maquinaParada.getConfDiasPrevEntrega() + urgente.getConfDiasPrevEntrega() + normal.getConfDiasPrevEntrega());
                }
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return rm.getDataAplicacao();
    }

    private List<RmMaterialStatus> verificaStatusTravaCancelamento(List<RmMaterialStatus> rm) {
        List<RmMaterialStatus> rmMateriaisCanc = new ArrayList<>();
        for (RmMaterialStatus rmCancelado : rm) {
            if ((rmCancelado.getStatus().getCodigo().equals("Fim") || rmCancelado.getStatus().getCodigo().equals("AgRet")
                    || rmCancelado.getStatus().getCodigo().equals("MatComAgRec") || rmCancelado.getStatus().getCodigo().equals("RecParc")
                    || rmCancelado.getStatus().getCodigo().equals("Rec") || rmCancelado.getStatus().getCodigo().equals("SolicTransf")
                    || rmCancelado.getStatus().getCodigo().equals("Indisp") || rmCancelado.getStatus().getCodigo().equals("RetParc"))) {
                rmMateriaisCanc.add(rmCancelado);
            }
        }
        return rmMateriaisCanc;
    }

    private List<RmMaterialStatus> verificaStatusTravaCancelamentoMaterial(RmMaterialStatus rmMaterialStatusAtual) {
        List<RmMaterialStatus> MateriaisCanc = new ArrayList<>();
        RmMaterialStatus materialCancelado = rmMaterialStatusAtual;
        if ((materialCancelado.getStatus().getCodigo().equals("Fim") || materialCancelado.getStatus().getCodigo().equals("Can")
                || materialCancelado.getStatus().getCodigo().equals("AgRet") || materialCancelado.getStatus().getCodigo().equals("MatComAgRec")
                || materialCancelado.getStatus().getCodigo().equals("RecParc") || materialCancelado.getStatus().getCodigo().equals("Rec")
                || materialCancelado.getStatus().getCodigo().equals("SolicTransf") || materialCancelado.getStatus().getCodigo().equals("Indisp")
                || materialCancelado.getStatus().getCodigo().equals("RetParc"))) {
            MateriaisCanc.add(materialCancelado);
        }
        return MateriaisCanc;
    }

    private List<RmMaterialStatus> verificaQuantidadeStatus(List<RmMaterialStatus> rmMaterialStatus) {
        List<RmMaterialStatus> MateriaisCancelados = new ArrayList<>();
        for (RmMaterialStatus materialCancelado : rmMaterialStatus) {
            if (materialCancelado.getStatus().getCodigo().equals("Can")) {
                MateriaisCancelados.add(materialCancelado);
            }
        }
        return MateriaisCancelados;
    }

    @POST
    @Path("listarRetiradasNaoPresenciais")
    @Consumes("application/json")
    public List<RmMaterialRetirada> listarRetiradasNaoPresenciais(RmMaterial rmMaterial) {
        List<RmMaterialRetirada> lista = new ArrayList<>();
        GenericDao<RmMaterialRetirada> genericDao = new GenericDao<>();

        try {
            String aliasRetiradoPor = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RETIRADO_POR);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialRetirada.RM_MATERIAL_RETIRADA_ID));
            propriedades.add(new Propriedade(RmMaterialRetirada.DATA_RETIRADA));
            propriedades.add(new Propriedade(RmMaterialRetirada.QUANTIDADE));

            //Retirado Por
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRetiradoPor));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRetiradoPor));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.RM_MATERIAL, rmMaterial, Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.PRE_RETIRADA, Boolean.FALSE, Filtro.EQUAL)));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.DATA_AUTENTICACAO, null, Filtro.IS_NULL)));

            lista = genericDao.listarByFilter(propriedades, null, RmMaterialRetirada.class, Constantes.NO_LIMIT, nxCriterion);
        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    @POST
    @Path("confirmarRetiradaNaoPresencial")
    @Consumes("application/json")
    public Info confirmarRetiradaNaoPresencial(RmMaterial rmMaterial) {
        Info info = new Info();
        GenericDao<RmMaterial> genericDao = new GenericDao<>();

        try {
            rmMaterial.setConfirmaRetNaoPresencial(Boolean.TRUE);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.CONFIRMA_RET_NAO_PRESENCIAL));

            genericDao.update(rmMaterial, propriedades);

            info.setObjeto(rmMaterial);
            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem("msg_retirada_nao_presencial_confirmada_sucesso");
            info.setErro(null);
        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem("msg_retirada_nao_presencial_confirmada_erro");
        }
        return info;
    }

    /**
     * Verifica a prioridade que a RM ficará
     *
     * @param cadastroRmVo
     * @return
     */
    @POST
    @Path("verificaPrioridade")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info verificaPrioridade(CadastroRmVo cadastroRmVo) {
        Info info = new Info();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

        try {
            Date dataAplicacao = Util.parseData(cadastroRmVo.getRm().getStDataAplicacao(), getProperty("format_date"));
            cadastroRmVo.getRm().setDataAplicacao(dataAplicacao);
            Date data = new Date();
            cadastroRmVo.getRm().setDataEmissao(data);

            //Busca a prioridade da requisição de acordo com a configuração determinada de dias
            List<Prioridade> prioridades = listarPrioridadeConf(
                            cadastroRmVo.getRm().getCentro().getCentroId(),
                            cadastroRmVo.getRm().getArea().getAreaId());
            Integer diferencDiasPrioridade = Util.diferencaDias(cadastroRmVo.getRm().getDataEmissao(), dataAplicacao) + 1;
            Prioridade prioridadeRm = null;
            for (Prioridade prioridade : prioridades) {
                if (prioridade.getConfDiasIniPrioridade() != null && prioridade.getConfDiasFimPrioridade() != null) {
                    if (prioridade.getConfDiasIniPrioridade() <= diferencDiasPrioridade &&
                                    prioridade.getConfDiasFimPrioridade() >= diferencDiasPrioridade) {
                        prioridadeRm = prioridade;
                        break;
                    }
                }
            }
            info.setObjeto(prioridadeRm);
        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    @POST
    @Path("alterarColetor")
    @Consumes("application/json")
    public Info alterarColetor(RmMaterial rmMaterial) {
        Info info = new Info();

        GenericDao<RmMaterial> genericDao = new GenericDao<>();

        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.COLETOR_CUSTOS));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_ORDEM));
            propriedades.add(new Propriedade(RmMaterial.DIAGRAMA_REDE));
            propriedades.add(new Propriedade(RmMaterial.OPERACAO));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO));

            genericDao.update(rmMaterial, propriedades);

            info.setObjeto(rmMaterial);
            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setErro(null);
        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));

            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
        }
        return info;
    }

    /**
     * Envia a rm para o painel de estoquista já realizando o aceite material
     * e também envia para a equipe de custos preencher os custos...
     *
     * @param cadastroRmVo
     * @return
     */
    @POST
    @Path("salvarRequisicaoRetiradaEstEstoquista")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info salvarRequisicaoRetiradaEstEstoquista(CadastroRmVo cadastroRmVo) {
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
        RmaService rmaService = new RmaService(request);
        RmAprovador aprovador = new RmAprovador();
        GenericDao<RmAprovador> genericDao = new GenericDao<>();
        RetornoVo retornoVo = new RetornoVo();
        Info info = new Info();

        try {
            //Seto os dados para o cadastro da Rm e logo do RmMaterial
            Date dataAplicacao = Util.parseData(cadastroRmVo.getRm().getStDataAplicacao(), getProperty("format_date"));
            cadastroRmVo.getRm().setDataAplicacao(dataAplicacao);
            cadastroRmVo.getRm().setDataRecebimento(new Date());
            cadastroRmVo.getRm().setDataEmissao(new Date());
            cadastroRmVo.getRm().setStDataEmissao(Util.dateToString(cadastroRmVo.getRm().getDataEmissao(), getProperty("format_date")));
            Integer numeroRmAtual = rmaService.buscarNumeroRmAtual();
            if (numeroRmAtual == null) {
                numeroRmAtual = 0;
            }

            cadastroRmVo.getRm().setNumeroRm(String.valueOf(numeroRmAtual + 1));

            //Busca a prioridade da requisição de acordo com a configuração determinada de dias
            List<Prioridade> prioridades = listarPrioridadeConf(
                            cadastroRmVo.getRm().getCentro().getCentroId(),
                            cadastroRmVo.getRm().getArea().getAreaId());
            Integer diferencDiasPrioridade = Util.diferencaDias(cadastroRmVo.getRm().getDataEmissao(), dataAplicacao) + 1;
            Prioridade prioridadeRm = null;
            for (Prioridade prioridade : prioridades) {
                if (prioridade.getConfDiasIniPrioridade() != null && prioridade.getConfDiasFimPrioridade() != null) {
                    if (prioridade.getConfDiasIniPrioridade() <= diferencDiasPrioridade &&
                                    prioridade.getConfDiasFimPrioridade() >= diferencDiasPrioridade) {
                        prioridadeRm = prioridade;
                        break;
                    }
                }
            }

            if (prioridadeRm == null && !prioridades.isEmpty()) {
                prioridadeRm = prioridades.get(0);
            }
            cadastroRmVo.getRm().setPrioridade(prioridadeRm);

            //Realizo a transaçao para a criação da rm e da rmMaterial
            genericDao.beginTransaction();

            //Seto na rmMaterial a rm, para depois persistir a mesma.
            genericDao.persistWithCurrentTransaction(cadastroRmVo.getRm());
            for (RmMaterial rmMaterial : cadastroRmVo.getRmMateriais()) {
                rmMaterial.setRm(cadastroRmVo.getRm());
                genericDao.persistWithCurrentTransaction(rmMaterial);
            }

            genericDao.commitCurrentTransaction();

            //Seto o aprovador para enviar o mesmo para a Equipe de Custo/Lider de Custos
            aprovador.setRm(cadastroRmVo.getRm());
            aprovador.setAprovador(null);
            aprovador.setAprovadorVez(Boolean.TRUE);
            aprovador.setOrdem(1);
            aprovador.setTipoAprovador(Constantes.TIPO_ATUACAO_LIDER_CUSTOS);

            List<ValidacaoMaterialVo> listaValidacaoMaterialVo = new ArrayList<>();

            for (RmMaterial rmMaterial : cadastroRmVo.getRmMateriais()) {
                listaValidacaoMaterialVo.add(validarMaterialDeposito(cadastroRmVo.getRmDepositos(), rmMaterial));
            }

            List<VwRmMaterial> listaVwRmMaterial = new ArrayList<>();

            for (RmMaterial rmMaterial : cadastroRmVo.getRmMateriais()) {
                VwRmMaterial vw = new VwRmMaterial();
                vw.setRmMaterial(rmMaterial);
                listaVwRmMaterial.add(vw);
            }

            genericDao.beginTransaction();

            //Gero os status de cadastrada e tambem de aguardando retirada
            RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);
            for (RmMaterial rmMaterial : cadastroRmVo.getRmMateriais()) {
                rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_CADASTRADA, genericDao, new Date(), null, null);
            }

            genericDao.persistWithCurrentTransaction(aprovador);

            List<RmDeposito> listaRmDepositos = new ArrayList<>();

            //Realizo o metodo de decidirFluxoMateriais, porem o mesmo será sempre reserva.
            //Se estiver indisponível criará status de Indisponível
            for (ValidacaoMaterialVo validacaoMaterialVo : listaValidacaoMaterialVo) {
                for(MaterialDeposito materialDeposito : validacaoMaterialVo.getDepositosComQuantidade()){
                    RmDeposito rmDeposito = new RmDeposito();

                    rmDeposito.setDeposito(materialDeposito.getDeposito());
                    rmDeposito.setRm(cadastroRmVo.getRm());

                    listaRmDepositos.add(rmDeposito);
                }
                if(validacaoMaterialVo.getMateriaisReserva() != null && !validacaoMaterialVo.getMateriaisReserva().isEmpty()) {
                    rmaService.decidirFluxoMateriais(cadastroRmVo.getRm(), listaVwRmMaterial,
                            listaRmDepositos, validacaoMaterialVo, null, genericDao, retornoVo);
                } else if(validacaoMaterialVo.getMateriaisCompra() != null && !validacaoMaterialVo.getMateriaisCompra().isEmpty()){
                    genericDao.commitCurrentTransaction();
                    for(MaterialVo materialVo : validacaoMaterialVo.getMateriaisCompra()) {
                        RmMaterial rmMaterial = new RmMaterial();
                        RmMaterialStatus rmMaterialStatus = new RmMaterialStatus();
                        rmMaterial.setMaterial(materialVo.getMaterial());
                        rmMaterial.setRmMaterialId(materialVo.getNumRmMaterial());

                        rmMaterialStatus = rmMaterialStatusService.listarStatusAtual(rmMaterial);

                        genericDao.beginTransaction();
                        rmMaterialStatusService.finalizarStatus(rmMaterialStatus, genericDao, new Date());

                        rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_INDISPONIVEL, genericDao, new Date(), null, LoginService.getUsuarioLogado(request).getNome());
                    }
                }
            }

            genericDao.commitCurrentTransaction();

            //Recupero o MaterialDepositoSaidaReserva gerado pela decisão de fluxo de material

            MaterialDepositoSaidaReserva materialDepositoSaidaReserva = new MaterialDepositoSaidaReserva();
            for (RmMaterial rmMaterial : cadastroRmVo.getRmMateriais()) {
                materialDepositoSaidaReserva = recuperaMaterialDepositoSaidaReserva(rmMaterial);
                if(materialDepositoSaidaReserva != null) {
                    materialDepositoSaidaReserva.getRmMaterial().setQtdRetirado(materialDepositoSaidaReserva.getQuantidadeSolicitada());
                    rmaService.salvarAceiteMaterial(materialDepositoSaidaReserva, true, true, true, true, null);
                }
            }

            info.setObjeto(cadastroRmVo);
            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setErro(null);

        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
        }

        return info;
    }

    private ValidacaoMaterialVo validarMaterialDeposito(List<RmDeposito> listaRmDeposito, RmMaterial rmMaterial){
        ValidacaoMaterialVo validacaoMaterialVo = new ValidacaoMaterialVo();
        MaterialVo materialVo = new MaterialVo();
        List<MaterialDeposito> listaMaterialDeposito = new ArrayList<>();
        List<Propriedade> propriedades = new ArrayList<>();
        List<MaterialVo> listaMaterialVo = new ArrayList<>();
        List<Deposito> listaDepositos = new ArrayList<>();
        GenericDao<MaterialDeposito> genericDao = new GenericDao<>();

        try {
            for(RmDeposito rmDeposito : listaRmDeposito){
                listaDepositos.add(rmDeposito.getDeposito());
            }

            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL));
            propriedades.add(new Propriedade(MaterialDeposito.DEPOSITO));
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, rmMaterial.getMaterial(), Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.DEPOSITO, listaDepositos, Filtro.IN)));

            listaMaterialDeposito = genericDao.listarByFilter(propriedades, null, MaterialDeposito.class, Constantes.NO_LIMIT, nxCriterion);

            Double quantidadeTotal = 0.0;
            List<MaterialDeposito> listaMaterialDepositoAux = new ArrayList<>();
            for(MaterialDeposito materialDeposito : listaMaterialDeposito){
                if(materialDeposito.getQuantidade() > 0){
                    quantidadeTotal = quantidadeTotal + materialDeposito.getQuantidade();
                    listaMaterialDepositoAux.add(materialDeposito);
                }
            }

            if(quantidadeTotal < rmMaterial.getQuantidade()){
                materialVo.setNumRmMaterial(rmMaterial.getRmMaterialId());
                materialVo.setMaterial(rmMaterial.getMaterial());
                materialVo.setQuantidade(rmMaterial.getQuantidade());
                materialVo.setReserva(false);

                listaMaterialVo.add(materialVo);

                validacaoMaterialVo.setMateriaisCompra(listaMaterialVo);
            }else if(quantidadeTotal >= rmMaterial.getQuantidade()){
                materialVo.setNumRmMaterial(rmMaterial.getRmMaterialId());
                materialVo.setMaterial(rmMaterial.getMaterial());
                materialVo.setQuantidade(rmMaterial.getQuantidade());
                materialVo.setReserva(true);

                listaMaterialVo.add(materialVo);

                validacaoMaterialVo.setMateriaisReserva(listaMaterialVo);
            }

            validacaoMaterialVo.setDepositosComQuantidade(listaMaterialDepositoAux);

        } catch (Exception e){
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return validacaoMaterialVo;
    }

    //Recupera o materialDepositoSaidaReserva de acordo com a RmMaterial passada.
    private MaterialDepositoSaidaReserva recuperaMaterialDepositoSaidaReserva(RmMaterial rmMaterial) {
        MaterialDepositoSaidaReserva materialDepositoSaidaReserva = new MaterialDepositoSaidaReserva();
        GenericDao<MaterialDepositoSaidaReserva> genericDao = new GenericDao<>();

        try {
            String aliasRmMaterial = NxCriterion.montaAlias(MaterialDepositoSaidaReserva.ALIAS_CLASSE, MaterialDepositoSaidaReserva.RM_MATERIAL);
            String aliasRm = NxCriterion.montaAlias(MaterialDepositoSaidaReserva.ALIAS_CLASSE, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.RM);
            String aliasMaterial = NxCriterion.montaAlias(MaterialDepositoSaidaReserva.ALIAS_CLASSE, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(MaterialDepositoSaidaReserva.ALIAS_CLASSE, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.DEPOSITO_ID);
            String aliasTipoMaterial = NxCriterion.montaAlias(MaterialDepositoSaidaReserva.ALIAS_CLASSE, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.MATERIAL, Material.TIPO_MATERIAL);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.ID));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.DATA_SAIDA));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.QUANTIDADE_SOLICITADA));

            //RmMaterial
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.FLUXO_MATERIAL, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO_ESTOQUISTA, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.ITEM_REQUISICAO_SAP, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_ESTOQUE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DIAGRAMA_REDE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.OPERACAO, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_CUSTOS, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.IS_REC_SAP, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.LOCAL_ENTREGA, RmMaterial.class, aliasRmMaterial));

            //Rm
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO, Rm.class, aliasRm));

            //Material
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.PATRIMONIADO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.STATUS, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.HIERARQUIA, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME_COMPLETO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.IS_SERVICO, Material.class, aliasMaterial));

            //Deposito
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            //TipoMaterial
            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.DESCRICAO, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM_MATERIAL_ID, rmMaterial.getRmMaterialId(), Filtro.EQUAL, aliasRmMaterial));

            materialDepositoSaidaReserva = genericDao.selectUnique(propriedades, MaterialDepositoSaidaReserva.class, nxCriterion);
        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return materialDepositoSaidaReserva;
    }

    /**
     * Pega o usuário logado pelo request
     *
     * @param usuarioLogado
     * @return
     */
    public Pessoa getPessoaByUsuario(Usuario usuarioLogado) {
        Pessoa p = null;
        try {
            p = usuarioLogado.getPessoa();
            if (p == null || p.getPessoaId() == null || p.getReferencia() == null) {
                List<Propriedade> propriedades = new ArrayList<>();
                //Pessoa
                propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
                propriedades.add(new Propriedade(Pessoa.NOME));
                propriedades.add(new Propriedade(Pessoa.CPF));
                propriedades.add(new Propriedade(Pessoa.REFERENCIA));

                NxCriterion nx = NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, usuarioLogado.getPessoa().getPessoaId(), Filtro.EQUAL));

                GenericDao<Pessoa> genericDao = new GenericDao<>();
                p = genericDao.selectUnique(propriedades, Pessoa.class, nx);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    /**
     * Comsulta o comprador da RmMaterial
     *
     * @param id
     * @return RmMaterial
     */
    public RmMaterial getRmMaterialComprador(int id) {
        RmMaterial rmRetorno = null;
        GenericDao<RmMaterial> genericDao = new GenericDao<>();

        try {
            String aliasComprador = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.COMPRADOR);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));

            //REQUISITANTE
            propriedades.add(new Propriedade(Comprador.COMPRADOR_ID, Comprador.class, aliasComprador));
            propriedades.add(new Propriedade(Comprador.NOME, Comprador.class, aliasComprador));
            propriedades.add(new Propriedade(Comprador.EMAIL, Comprador.class, aliasComprador));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM_MATERIAL_ID, id, Filtro.EQUAL));

            rmRetorno = genericDao.selectUnique(propriedades, RmMaterial.class, nxCriterion);
        } catch (Exception e) {
            log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return rmRetorno;
    }
    
    
    
    
    
    
    
    /**
     * <b>listarAprovadoresArea</b><br />
     *
     * @return
     */
    @POST
    @Path("listarAprovadoresArea")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listarAprovadoresArea() {
    	UsuarioService us = new UsuarioService();
    	return us.getAprovadoresArea();
    }
    
    /**
     * <b>listarAprovadoresCusto</b><br />
     *
     * @return
     */
    @POST
    @Path("listarAprovadoresCusto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listarAprovadoresCusto() {
    	UsuarioService us = new UsuarioService();
    	return us.getAprovadoresCusto();
    }
    
    /**
     * <b>listarAprovadoresEmergencial</b><br />
     *
     * @return
     */
    @POST
    @Path("listarAprovadoresEmergencial")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listarAprovadoresEmergencial() {
        UsuarioService us = new UsuarioService();
        return us.getAprovadoresEmergencial();
    }

    /**
     * Exibe as informações do material em cada deposito selecionado
     *
     * @param consultaMaterialSap
     * @return
     */
    @POST
    @Path("consultaInformacoesMaterialSap")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseConsultaInformacoesSap consultanformacoesMaterialSap(ConsultaMaterialSap consultaMaterialSap) {

        ConsultaEstoqueMaterialSap requestSap = new ConsultaEstoqueMaterialSap();
        requestSap.setCentro(consultaMaterialSap.getCentro());
        requestSap.setMateriais(consultaMaterialSap.getMateriais());

        ConsultaEstoqueMaterialSapResponse retornoSap = sapServiceClient.zglRmaBuscaEstoqueMaterial(requestSap);
        List<EstoqueMateriaisSap> estoqueMateriaisSap = retornoSap.getEstoqueMateriaisSap();

        BigDecimal totalMateriaisEmEstoque = estoqueMateriaisSap.stream()
                        .map(EstoqueMateriaisSap::getQuantidadeEstoque)
                        .reduce(BigDecimal::add)
                        .get();

        MaterialSap materialSap = new MaterialSap();
        materialSap.setCodigo(consultaMaterialSap.getMateriais().get(0));
        materialSap.setTotalEstoque(totalMateriaisEmEstoque);

        Centro centro = null;
        try {
            centro = centroService.getByCodigo(consultaMaterialSap.getCentro());
        } catch (Exception e) {
            e.printStackTrace();
        }
        final Set<Deposito> depositos = new HashSet<>();
        if (centro != null) {
            depositos.addAll(depositoService.getDepositosPorCentro(centro.getCentroId()));
        }

        List<DepositoSap> depositosSap = depositos.stream().map(
                        deposito -> {
                            DepositoSap depositoSap = new DepositoSap();
                            depositoSap.setNome(deposito.getNome());
                            depositoSap.setId(deposito.getCodigo());
                            depositoSap.setCodigo(deposito.getCodigo());
                            depositoSap.setDepositoId(Integer.toString(deposito.getDepositoId()));
                            estoqueMateriaisSap.stream()
                                            .filter(estoqueMaterialSap -> deposito.getCodigo().equals(estoqueMaterialSap.getNomeDeposito()))
                                            .findFirst()
                                            .ifPresent(estoqueMaterialSap -> {
                                                depositoSap.setQuantidade(estoqueMaterialSap.getQuantidadeEstoque());
                                            });
                            return depositoSap;
                        }
        ).collect(Collectors.toList());

        //        List<DepositoSap> depositosSap = estoqueMateriaisSap.stream().map(
        //                        estoqueMateriais -> {
        //                            DepositoSap depositoSap = new DepositoSap();
        //                            depositoSap.setId(estoqueMateriais.getNomeDeposito());
        //                            depositoSap.setDepositoId(estoqueMateriais.getNomeDeposito());
        //                            depositos.stream()
        //                                            .filter(deposito -> deposito.getCodigo().equals(estoqueMateriais.getNomeDeposito()))
        //                                            .findFirst()
        //                                            .ifPresent(
        //                                                            deposito -> depositoSap.setNome(deposito.getNome()));
        //                            depositoSap.setQuantidade(estoqueMateriais.getQuantidadeEstoque());
        //                            return depositoSap;
        //                        }
        //        ).collect(Collectors.toList());
        Collections.sort(depositosSap);
        materialSap.setDepositos(depositosSap);

        ResponseConsultaInformacoesSap responseConsultaInformacoesSap = new ResponseConsultaInformacoesSap();
        responseConsultaInformacoesSap.setCentro(requestSap.getCentro());
        responseConsultaInformacoesSap.setMaterial(materialSap); 
        responseConsultaInformacoesSap.setGrupoCompradores(listaCompradores());

        return responseConsultaInformacoesSap;
    }

    /**
     * Retorna as informações de materiais similares do SAP
     *
     * @param consultaMateriaisSimilaresSap
     * @return ResponseConsultaInformacoesSap
     */
    @POST
    @Path("consultaMateriaisSimilaresSap")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseMateriaisSimilaresSap consultaMateriaisSimilaresSap(ConsultaMateriaisSimilaresSap consultaMateriaisSimilaresSap) {

        ConsultaEstoqueMaterialSap requestSap = new ConsultaEstoqueMaterialSap();
        requestSap.setCentro(consultaMateriaisSimilaresSap.getCentro());
        requestSap.setMateriais(consultaMateriaisSimilaresSap.getMateriais());

        ConsultaEstoqueMaterialSapResponse retornoSap = sapServiceClient.zglRmaBuscaEstoqueMaterial(requestSap);

        List<EstoqueMateriaisSap> estoqueMateriaisSap = retornoSap.getEstoqueMateriaisSap();

        Set<String> materiais = estoqueMateriaisSap.stream().map(e -> e.getCodigoMaterial()).collect(Collectors.toSet());
        Map<String, List<EstoqueMateriaisSap>> materialEstoque = new HashMap<>();
        materiais.stream().forEach( m-> {
            List<EstoqueMateriaisSap> collect = estoqueMateriaisSap.stream().filter(e -> e.getCodigoMaterial().equals(m)).collect(Collectors.toList());
            materialEstoque.put(m,collect);
        });

        List<MateriaisSimilaresSap> materiaisSimilaresSapList = new ArrayList<>();
        materialEstoque.forEach((k,v) ->{

            MateriaisSimilaresSap similaresSap = new MateriaisSimilaresSap();
            similaresSap.setCodigoMaterial(k);

            BigDecimal totalMateriaisEmEstoque = v.stream()
                    .map(EstoqueMateriaisSap::getQuantidadeEstoque)
                    .reduce(BigDecimal::add)
                    .get();

            similaresSap.setTotalEstoque(totalMateriaisEmEstoque);

            List<DepositoSap> depositosSap = new ArrayList<>();
            v.stream().forEach(e ->{
                DepositoSap depositoSap = new DepositoSap();
                depositoSap.setQuantidade(e.getQuantidadeEstoque());
                depositoSap.setId(e.getNomeDeposito().isEmpty() ? "0" : e.getNomeDeposito());
                depositosSap.add(depositoSap);
            });

            if (!totalMateriaisEmEstoque.equals(BigDecimal.ZERO)) {
                similaresSap.setDepositos(depositosSap);
            }

            materiaisSimilaresSapList.add(similaresSap);

        });

        ResponseMateriaisSimilaresSap response = new ResponseMateriaisSimilaresSap();
        response.setCentro(retornoSap.getNomeCentro());
        response.setMateriais(materiaisSimilaresSapList);
        return response;
    }

	private List<GrupoComprador> listaCompradores() {
		try {
			GrupoCompradorService grupoCompradorService = new GrupoCompradorService();
			return grupoCompradorService.listar();
		} catch (Exception e) {
			log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(),
					this.getClass().getName(), Util.getNomeMetodoAtual(), e));
		}
		return null;
	}

}
