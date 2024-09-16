package br.com.nextage.rmaweb.service.integracao;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.persistence_2.util.Paginacao;
import br.com.nextage.rmaweb.controller.notificacao.email.enviar.EmailService;
import br.com.nextage.rmaweb.controller.rm.servico.status.aprovadores.ObterAprovadoresRmServico;
import br.com.nextage.rmaweb.dao.VwConsultaRmaDAO;
import br.com.nextage.rmaweb.entitybean.CodigoSap;
import br.com.nextage.rmaweb.entitybean.Comprador;
import br.com.nextage.rmaweb.entitybean.Configuracao;
import br.com.nextage.rmaweb.entitybean.Deposito;
import br.com.nextage.rmaweb.entitybean.EapMultiEmpresa;
import br.com.nextage.rmaweb.entitybean.Fornecedor;
import br.com.nextage.rmaweb.entitybean.FornecedorServico;
import br.com.nextage.rmaweb.entitybean.HierarquiaComprador;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.entitybean.MaterialDeposito;
import br.com.nextage.rmaweb.entitybean.MaterialDepositoEntrada;
import br.com.nextage.rmaweb.entitybean.MaterialDepositoSaida;
import br.com.nextage.rmaweb.entitybean.MaterialDepositoSaidaReserva;
import br.com.nextage.rmaweb.entitybean.MultiEmpresa;
import br.com.nextage.rmaweb.entitybean.Perfil;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.Prioridade;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.entitybean.RmAprovador;
import br.com.nextage.rmaweb.entitybean.RmDeposito;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.entitybean.RmMaterialRetirada;
import br.com.nextage.rmaweb.entitybean.RmMaterialStatus;
import br.com.nextage.rmaweb.entitybean.RmServico;
import br.com.nextage.rmaweb.entitybean.RmServicoCodigoSap;
import br.com.nextage.rmaweb.entitybean.RmServicoStatus;
import br.com.nextage.rmaweb.entitybean.Role;
import br.com.nextage.rmaweb.entitybean.SincRegistro;
import br.com.nextage.rmaweb.entitybean.Status;
import br.com.nextage.rmaweb.entitybean.TipoMaterial;
import br.com.nextage.rmaweb.entitybean.UnidadeMedida;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.entitybean.VwRmMaterial;
import br.com.nextage.rmaweb.entitybean.VwRmServico;
import br.com.nextage.rmaweb.filtro.FiltroCadastroRma;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.UsuarioService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.utils.ConnectionFactory;
import br.com.nextage.rmaweb.vo.CadastroRmVo;
import br.com.nextage.rmaweb.vo.LogInterfaceVo;
import br.com.nextage.rmaweb.vo.MaterialTransferenciaVo;
import br.com.nextage.rmaweb.vo.MaterialVo;
import br.com.nextage.rmaweb.vo.RetornoVo;
import br.com.nextage.rmaweb.vo.ValidacaoMaterialVo;
import br.com.nextage.rmaweb.ws.cpweb.LocalEstoque;
import br.com.nextage.rmaweb.ws.cpweb.SincEquipamentoVo;
import br.com.nextage.rmaweb.ws.cpweb.SincEstoque_Service;
import br.com.nextage.rmaweb.ws.cpweb.TipoEquipamento;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;

/**
 * @author Daniel H. Parisotto
 * @date 07/10/2015
 */
public class RmaServicoService {

    @Context
    HttpServletRequest request;

    @Inject
    private EmailService emailService;

    private static final Logger LOG = Logger.getLogger(RmaServicoService.class);

    public RmaServicoService() {
    }

    public RmaServicoService(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * Lista as informacoes da rm com suas listas filho
     *
     * @param rm
     * @return
     */
    public Rm listarRm(Rm rm) {
        Rm rmRetorno = null;
        GenericDao<Rm> genericDao = new GenericDao<>();

        try {
            String aliasRequisitante = NxCriterion.montaAlias(Rm.ALIAS_CLASSE, Rm.REQUISITANTE);
            String aliasUsuarioPessoa = NxCriterion.montaAlias(Rm.ALIAS_CLASSE, Rm.USUARIO, Usuario.PESSOA);
            String aliasUsuario = NxCriterion.montaAlias(Rm.ALIAS_CLASSE, Rm.USUARIO);
            String aliasEapMultiEmpresa = NxCriterion.montaAlias(Rm.ALIAS_CLASSE, Rm.EAP_MULTI_EMPRESA);
            String aliasMultiEmpresa = NxCriterion.montaAlias(Rm.ALIAS_CLASSE, Rm.EAP_MULTI_EMPRESA, EapMultiEmpresa.MULTI_EMPRESA);
            String aliasPrioridade = NxCriterion.montaAlias(Rm.ALIAS_CLASSE, Rm.PRIORIDADE);
            String aliasTipoRequisicao = NxCriterion.montaAlias(Rm.ALIAS_CLASSE, Rm.TIPO_REQUISICAO);
            //String aliasNumeroRmServico = NxCriterion.montaAlias(Rm.ALIAS_CLASSE, Rm.NUMERO_RM_SERVICO);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Rm.RM_ID));
            propriedades.add(new Propriedade(Rm.NUMERO_RM));
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO));
            propriedades.add(new Propriedade(Rm.DATA_EMISSAO));
            propriedades.add(new Propriedade(Rm.DATA_ENVIO_APROVACAO));
            propriedades.add(new Propriedade(Rm.DATA_REPROVACAO));
            propriedades.add(new Propriedade(Rm.DATA_CANCELAMENTO));
            propriedades.add(new Propriedade(Rm.REPROVADO_POR));
            propriedades.add(new Propriedade(Rm.OBSERVACAO));
            propriedades.add(new Propriedade(Rm.LOCAL_ENTREGA));
            propriedades.add(new Propriedade(Rm.CENTRO));
            propriedades.add(new Propriedade(Rm.AREA));
            propriedades.add(new Propriedade(Rm.TIPO_REQUISICAO));
            propriedades.add(new Propriedade(Rm.NUMERO_RM_SERVICO));

            //PRIORIDADE
            propriedades.add(new Propriedade(Prioridade.PRIORIDADE_ID, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.CODIGO, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.DESCRICAO, Prioridade.class, aliasPrioridade));

            //REQUISITANTE
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasRequisitante));

            //USUARIO
            propriedades.add(new Propriedade(Usuario.USUARIO_ID, Usuario.class, aliasUsuario));

            //EAP MULTI EMPRESA
            propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));

            //MULTI EMPRESA
            propriedades.add(new Propriedade(MultiEmpresa.ID, MultiEmpresa.class, aliasMultiEmpresa));
            propriedades.add(new Propriedade(MultiEmpresa.DESCRICAO, MultiEmpresa.class, aliasMultiEmpresa));

            //USUARIO PESSOA
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasUsuarioPessoa));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasUsuarioPessoa));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasUsuarioPessoa));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Rm.RM_ID, rm.getRmId(), Filtro.EQUAL));

            rmRetorno = genericDao.selectUnique(propriedades, Rm.class, nxCriterion);
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return rmRetorno;
    }

    /**
     * Lista as informacoes da rm com suas listas filho
     *
     * @param rm
     * @return
     */
    
    public CodigoSap listarCodigoSapByDescricao1(String descricao) {
    	
    	CodigoSap codigoSapRetorno = null;
        GenericDao<CodigoSap> genericDao = new GenericDao<>();

        try {
        	
            String aliasCodigoSapDescricao = NxCriterion.montaAlias(CodigoSap.ALIAS_CLASSE, CodigoSap.DESCRICAO);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(CodigoSap.DESCRICAO));
            propriedades.add(new Propriedade(CodigoSap.CODIGO_ID));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(CodigoSap.DESCRICAO, descricao, Filtro.EQUAL));

            codigoSapRetorno = genericDao.selectUnique(propriedades, CodigoSap.class, nxCriterion);
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return codigoSapRetorno;
    	
    }
    
    public List<RmServicoCodigoSap> listarCodigoSapByDescricao(String descricao) {
    	Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);
    	List<RmServicoCodigoSap> list = new ArrayList<>();
        GenericDao genericDao = new GenericDao();
        try {
        	
        	String sql = "SELECT CRIADO, CODIGO_ID, CODIGO, DESCRICAO FROM TB_SERVICO_CODIGO_SAP "
        			+ " WHERE DESCRICAO = " + "'"+descricao+"';";
        	
             PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet resultSet = smt.executeQuery();

             while (resultSet.next()) {
            	 RmServicoCodigoSap codigoSap = new RmServicoCodigoSap();
                 codigoSap.setCriado(resultSet.getString("CRIADO"));
                 codigoSap.setDescricao(resultSet.getString("DESCRICAO"));
                 codigoSap.setCodigo(resultSet.getString("CODIGO"));
                 codigoSap.setCodigoId(Integer.parseInt(resultSet.getString("CODIGO_ID")));
                 list.add(codigoSap);
             }

             smt.close();
             resultSet.close();	
            
        } catch (Exception e) {
            System.out.println("*************************************************ERRO AO GERAR CONSULTA CODIGO SAP*************************************************");
        }
        return list;
    }
    
    
    
    public Rm recuperaRmById(Integer rm) {
        Rm rmRetorno = null;
        GenericDao<Rm> genericDao = new GenericDao<>();

        try {
            String aliasRequisitante = NxCriterion.montaAlias(Rm.ALIAS_CLASSE, Rm.REQUISITANTE);
            String aliasUsuarioPessoa = NxCriterion.montaAlias(Rm.ALIAS_CLASSE, Rm.USUARIO, Usuario.PESSOA);
            String aliasUsuario = NxCriterion.montaAlias(Rm.ALIAS_CLASSE, Rm.USUARIO);
            String aliasPrioridade = NxCriterion.montaAlias(Rm.ALIAS_CLASSE, Rm.PRIORIDADE);
            String aliasTipoRequisicao = NxCriterion.montaAlias(Rm.ALIAS_CLASSE, Rm.TIPO_REQUISICAO);
            String aliasEapMultiEmpresa = NxCriterion.montaAlias(Rm.ALIAS_CLASSE, Rm.EAP_MULTI_EMPRESA);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Rm.RM_ID));
            propriedades.add(new Propriedade(Rm.NUMERO_RM));
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO));
            propriedades.add(new Propriedade(Rm.DATA_EMISSAO));
            propriedades.add(new Propriedade(Rm.DATA_ENVIO_APROVACAO));
            propriedades.add(new Propriedade(Rm.DATA_REPROVACAO));
            propriedades.add(new Propriedade(Rm.DATA_CANCELAMENTO));
            propriedades.add(new Propriedade(Rm.REPROVADO_POR));
            propriedades.add(new Propriedade(Rm.OBSERVACAO));
            propriedades.add(new Propriedade(Rm.LOCAL_ENTREGA));

            //PRIORIDADE
            propriedades.add(new Propriedade(Prioridade.PRIORIDADE_ID, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.CODIGO, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.DESCRICAO, Prioridade.class, aliasPrioridade));

            //EAP MULTI EMPRESA
            propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));

            //REQUISITANTE
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasRequisitante));

            //USUARIO
            propriedades.add(new Propriedade(Usuario.USUARIO_ID, Usuario.class, aliasUsuario));

            //USUARIO PESSOA
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasUsuarioPessoa));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasUsuarioPessoa));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasUsuarioPessoa));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Rm.RM_ID, rm, Filtro.EQUAL));

            rmRetorno = genericDao.selectUnique(propriedades, Rm.class, nxCriterion);
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return rmRetorno;
    }

    /**
     * Lista todas as informacoes da rm
     *
     * @param rm
     * @return
     */
    public CadastroRmVo listarCadastroRmVo(Rm rm) {
        CadastroRmVo rmVo = new CadastroRmVo();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

        try {
            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), rm, null));

            rmVo.setRm(listarRm(rm));

            if (rmVo.getRm() != null) {
                rmVo.getRm().setStDataAplicacao(Util.dateToString(rmVo.getRm().getDataAplicacao(), rb.getString("format_date")));
                rmVo.getRm().setStDataEmissao(Util.dateToString(rmVo.getRm().getDataEmissao(), rb.getString("format_date")));

                List<RmServico> listaServicos = listarRmServicos(rm);
                rmVo.setRmServicos(listaServicos);
                rmVo.setRmServico(listaServicos.get(0));
                
                List<RmServicoCodigoSap> listaCodigoSap = listarCodigoSapByDescricao(rmVo.getRmServico().getCodigoSap());
                rmVo.setCodigoSap(listaCodigoSap.get(0));
                
                rmVo.getRmServico().setFornecedores(listarFornecedores(rmVo.getRmServico()));
                rmVo.getRmServico().setStDataInicio(Util.dateToString(rmVo.getRmServico().getDataInicio(), rb.getString("format_date")));
                rmVo.getRmServico().setStDataTermino(Util.dateToString(rmVo.getRmServico().getDataTermino(), rb.getString("format_date")));
            }

            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), rm, null));

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return rmVo;
    }

    /**
     * Lista todas as informacoes da rm pelo id
     *
     * @param rmId
     * @return
     */
    public CadastroRmVo listarCadastroRmVoById(Integer rmId) {
        CadastroRmVo rmVo = new CadastroRmVo();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

        try {
            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), null, null));

            rmVo.setRm(recuperaRmById(rmId));

            if (rmVo.getRm() != null) {
                rmVo.getRm().setStDataAplicacao(Util.dateToString(rmVo.getRm().getDataAplicacao(), rb.getString("format_date")));
                rmVo.getRm().setStDataEmissao(Util.dateToString(rmVo.getRm().getDataEmissao(), rb.getString("format_date")));

                System.out.println(rmVo.getRm().getStDataEmissao());
                List<RmServico> listRmServicos = listarRmServicos(rmVo.getRm());
                rmVo.setRmServicos(listRmServicos);
                rmVo.setRmServico(listRmServicos.get(0));
            }

            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), null, null));

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return rmVo;
    }

    /**
     * Busca o numero atual da rm para gerar o proximo numero
     *
     * @return
     */
    public Integer buscarNumeroRmAtual() {
        Integer numero = null;

        try {
            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), this.getClass().getName(), Util.getNomeMetodoAtual(), null, null));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Rm.RM_ID));
            propriedades.add(new Propriedade(Rm.NUMERO_RM));

            List<NxOrder> orders = new ArrayList<>();
            orders.add(new NxOrder(Rm.RM_ID, NxOrder.NX_ORDER.DESC));

            GenericDao genericDao = new GenericDao();

            Rm rm = (Rm) genericDao.listarByFilter(propriedades, orders, Rm.class, 1, null).get(0);
            numero = Integer.valueOf(rm.getNumeroRm());

            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), CadastroRmVo.class.getName(), Util.getNomeMetodoAtual(), null, null));

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return numero;
    }
    
    
    public Integer buscarNumeroRmServicoAtual() {
        Integer numero = null;

        try {
            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), this.getClass().getName(), Util.getNomeMetodoAtual(), null, null));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Rm.RM_ID));
            propriedades.add(new Propriedade(Rm.NUMERO_RM_SERVICO));

            List<NxOrder> orders = new ArrayList<>();
            orders.add(new NxOrder(Rm.NUMERO_RM_SERVICO, NxOrder.NX_ORDER.DESC));

            GenericDao genericDao = new GenericDao();

            Rm rm = (Rm) genericDao.listarByFilter(propriedades, orders, Rm.class, 1, null).get(0);
            numero = Integer.valueOf(rm.getNumeroRmServico());

            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), CadastroRmVo.class.getName(), Util.getNomeMetodoAtual(), null, null));

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return numero;
    }

    /**
     * Verifica se os materiais solicitados possuem quantidades nos estoques
     * selecionados.<br/><br/>
     * <p/>
     * Verifica se os materiais que nao possuem quantidade suficiente existem
     * nos estoques que nao foram selecionados.<br/><br/>
     * <p/>
     * Verifica quais materiais sera gerado uma compra obrigatoria(nao possui
     * quantidade em nenhum estoque).<br/><br/>
     * <p/>
     * Verifica quais materiais sera gerado uma reserva(possui quantidade em
     * estoque).<br/><br/>
     * <p/>
     * Retorna um Vo com: <br/>
     * Uma lista de material/deposito (materiais que possuem quantidades em
     * estoques nao selecionados).<br/>
     * Uma lista de materiais/quantidade (materiais que serao comprados)<br/>
     * Uma lista de materiais/quantidade (materiais que serao reservados)<br/>
     *
     * @param rm
     * @param rmMaterial
     * @return
     */
    public ValidacaoMaterialVo validarMateriais(Rm rm, RmMaterial rmMaterial) {
        ValidacaoMaterialVo validacaoMaterialVo = new ValidacaoMaterialVo();

        List<MaterialVo> listaMateriaisParaCompra = verificarMateriaisCompra(rm, rmMaterial);
        List<MaterialVo> listaMateriaisParaReserva = verificarMateriaisReserva(rm, rmMaterial);
        List<MaterialDeposito> listaDepositosComQuantidade = listarDepositosComQuantidade(listaMateriaisParaCompra, rm, rmMaterial);

        validacaoMaterialVo.setMateriaisCompra(listaMateriaisParaCompra);
        validacaoMaterialVo.setMateriaisReserva(listaMateriaisParaReserva);
        validacaoMaterialVo.setDepositosComQuantidade(listaDepositosComQuantidade);

        for (MaterialVo materialVoReserva : listaMateriaisParaReserva) {
            for (MaterialVo materialVoCompra : listaMateriaisParaCompra) {
                if (materialVoReserva.getMaterial().equals(materialVoCompra.getMaterial())
                        && materialVoReserva.getNumRmMaterial().equals(materialVoCompra.getNumRmMaterial())) {
                    materialVoCompra.setReserva(Boolean.TRUE);
                }
            }
        }

        return validacaoMaterialVo;
    }

    /**
     * Indica se o item pode ser decidido no fluxo de materiais para uma reserva
     * em estoque Elimina itens que nao podem ir para reserva, ex: Cautelaveis,
     * pedidos para estoque, etc. [Marlos M. Novo]
     */
    private boolean itemPodeSerReserva(RmMaterial rmMaterial, Rm rm, List<RmDeposito> rmDepositos) {

        //Se caso vier com a flag reserva no fluxo de material do rmMaterial, então retorno true, que pode ser reserva.
        if (rmMaterial.getFluxoMaterial() != null && rmMaterial.getFluxoMaterial().equals(Constantes.FLUXO_MATERIAL_RESERVA)) {
            return true;
        }

        //Caso a minha RM não tenha nenhum deposito selecionado, não poderá ser reserva.
        if (rmDepositos == null || rmDepositos.isEmpty()) {
            return false;
        }

        //Se for material cautelável, vai sempre para fluxo de compra.
        if (rmMaterial.getMaterial().getTipoMaterial().getCodigo().equals(Constantes.CAUTELAVEL)) {
            return false;
        }

        //Se for flegado como compra para estoque, vai sempre para fluxo de compra.
        if (rmMaterial.getColetorEstoque() != null && rmMaterial.getColetorEstoque()) {
            return false;
        }

        //Se for requisição de manutenção e possuir códio sap, vai sempre para fluxo de compra.
        return !rm.getPrioridade().getCodigo().equals(Constantes.CODIGO_PRIORIDADE_ALTA);

    }

    /**
     * Verifica quais e a quantidade de materiais que precisarao serem comprados
     *
     * @param rm
     * @return
     */
    private List<MaterialVo> verificarMateriaisCompra(Rm rm, RmMaterial rmMaterial) {
        GenericDao genericDao = new GenericDao();
        List<MaterialDeposito> materiaisDeposito;

        List<MaterialVo> rmMateriaisCompra = new ArrayList<>();
        List<RmDeposito> rmDepositos = new ArrayList<>();
        List<RmMaterial> rmMateriais = new ArrayList<>();
        try {
            if (!rm.getPrioridade().getCodigo().equals(Constantes.CODIGO_PRIORIDADE_ALTA)) {
                rmDepositos = listarRmDepositos(rm);
            }

            //Crio uma lista sem os materiais cautelados, pois eles serao sempre compra
            List<RmMaterial> rmMateriaisAux = new ArrayList<>();
            List<RmMaterial> rmMateriaisNaoPodemSerReserva = new ArrayList<>();
            //Se caso a rmMaterial (Que vem do cadastro da RM do tipo de retirada
            //de estoque, que precisa validar sem uma rmMaterial cadastrada
            //O mesmo adiciona na lista o rmMaterial em vez de fazer a busca.
            //caso tiver o Campo SAP ou Coletor Estoque ira direto para comrpra
            if (rmMaterial != null) {
                rmMateriais.add(rmMaterial);
                rmMateriaisAux.add(rmMaterial);
            } else {
                rmMateriais = listarRmMateriais(rm);

                for (RmMaterial material : rmMateriais) {
                    if (itemPodeSerReserva(material, rm, rmDepositos)) {
                        rmMateriaisAux.add(material);
                    } else {
                        //Crio uma lista com os materiais que serao
                        //obrigatoriamente compra
                        rmMateriaisNaoPodemSerReserva.add(material);
                    }
                }
            }

            materiaisDeposito = listarMaterialDeposito(rmMateriaisAux, rmDepositos, genericDao);

            Boolean isReserva = false;
            Map<RmMaterial, MaterialVo> mapMateriaisSolicitados = new HashMap<>();
            for (RmMaterial rmMaterialAux : rmMateriaisAux) {
                MaterialVo materialVo = new MaterialVo();
                materialVo.setMaterial(rmMaterialAux.getMaterial());
                materialVo.setQuantidade(rmMaterialAux.getQuantidade());
                materialVo.setNumRmMaterial(rmMaterialAux.getRmMaterialId());

                mapMateriaisSolicitados.put(rmMaterialAux, materialVo);

                if (rmMaterialAux.getFluxoMaterial() != null && rmMaterialAux.getFluxoMaterial().equals(Constantes.FLUXO_MATERIAL_RESERVA)) {
                    isReserva = true;
                }
            }

            //Agrupo os materiais de todos os depositos selecionados, somando
            //suas quantidades
            Map<Material, MaterialVo> mapMateriaisDepositos = new HashMap<>();
            for (MaterialDeposito materialDeposito : materiaisDeposito) {
                if (mapMateriaisDepositos.containsKey(materialDeposito.getMaterial())) {
                    MaterialVo materialVo = mapMateriaisDepositos.get(materialDeposito.getMaterial());
                    materialVo.setQuantidade(materialVo.getQuantidade() + materialDeposito.getQuantidade());
                } else {
                    MaterialVo materialVo = new MaterialVo();
                    materialVo.setMaterial(materialDeposito.getMaterial());
                    materialVo.setQuantidade(materialDeposito.getQuantidade());

                    mapMateriaisDepositos.put(materialDeposito.getMaterial(), materialVo);
                }
            }

            rmMateriaisCompra = new ArrayList<>();
            for (Map.Entry<RmMaterial, MaterialVo> mapSolicitado : mapMateriaisSolicitados.entrySet()) {
                if (mapSolicitado.getKey().getFluxoMaterial() == null || !mapSolicitado.getKey().getFluxoMaterial().equals(Constantes.FLUXO_MATERIAL_RESERVA)) {
                    if (mapMateriaisDepositos.containsKey(mapSolicitado.getKey().getMaterial())) {
                        MaterialVo deposito = mapMateriaisDepositos.get(mapSolicitado.getKey().getMaterial());
                        if (deposito.getQuantidade() < mapSolicitado.getValue().getQuantidade()) {
                            MaterialVo materialVo = new MaterialVo();
                            materialVo.setMaterial(mapSolicitado.getKey().getMaterial());
                            if (deposito.getQuantidade() <= 0) {
                                materialVo.setQuantidade(mapSolicitado.getValue().getQuantidade());
                            } else {
                                materialVo.setQuantidade(mapSolicitado.getValue().getQuantidade() - deposito.getQuantidade());
                            }
                            materialVo.setNumRmMaterial(mapSolicitado.getValue().getNumRmMaterial());

                            rmMateriaisCompra.add(materialVo);

                            deposito.setQuantidade(0d);
                        } else {
                            deposito.setQuantidade(deposito.getQuantidade() - mapSolicitado.getValue().getQuantidade());
                        }
                    } else {
                        MaterialVo materialVo = new MaterialVo();
                        materialVo.setMaterial(mapSolicitado.getKey().getMaterial());
                        materialVo.setQuantidade(mapSolicitado.getValue().getQuantidade());
                        materialVo.setNumRmMaterial(mapSolicitado.getValue().getNumRmMaterial());

                        rmMateriaisCompra.add(materialVo);
                    }
                }
            }

            for (RmMaterial material : rmMateriaisNaoPodemSerReserva) {
                MaterialVo materialVo = new MaterialVo();
                materialVo.setMaterial(material.getMaterial());
                materialVo.setQuantidade(material.getQuantidade());
                materialVo.setNumRmMaterial(material.getRmMaterialId());

                rmMateriaisCompra.add(materialVo);
            }

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return rmMateriaisCompra;
    }

    /**
     * Verifica quais e a quantidade de materiais que existem em estoque para
     * serem gerados as reservas
     *
     * @param rm
     * @return
     */
    private List<MaterialVo> verificarMateriaisReserva(Rm rm, RmMaterial rmMaterial) {
        GenericDao genericDao = new GenericDao();
        List<MaterialDeposito> materiaisDeposito;

        List<MaterialVo> materiaisReserva = new ArrayList<>();
        List<RmDeposito> rmDepositos = new ArrayList<>();
        try {
            if (!rm.getPrioridade().getCodigo().equals(Constantes.CODIGO_PRIORIDADE_ALTA)) {
                rmDepositos = listarRmDepositos(rm);
            }
            List<RmMaterial> rmMateriais = new ArrayList<>();

            //Crio uma lista sem os materiais cautelados, pois eles serao sempre compra
            List<RmMaterial> rmMateriaisAux = new ArrayList<>();

            //Se caso a rmMaterial (Que vem do cadastro da RM do tipo de retirada
            //de estoque, que precisa validar sem uma rmMaterial cadastrada
            //O mesmo adiciona na lista o rmMaterial em vez de fazer a busca.
            if (rmMaterial != null) {
                rmMateriais.add(rmMaterial);
                rmMateriaisAux.add(rmMaterial);
            } else {
                rmMateriais = listarRmMateriais(rm);

                for (RmMaterial material : rmMateriais) {
                    if (itemPodeSerReserva(material, rm, rmDepositos)) {
                        rmMateriaisAux.add(material);
                    }
                }
            }

            materiaisDeposito = listarMaterialDeposito(rmMateriaisAux, rmDepositos, genericDao);

            //Agrupo todos materiais solicitados iguais e somo suas quantidades
            Map<RmMaterial, MaterialVo> mapMateriaisSolicitados = new HashMap<>();
            for (RmMaterial rmMaterialAux : rmMateriaisAux) {
                MaterialVo materialVo = new MaterialVo();
                materialVo.setMaterial(rmMaterialAux.getMaterial());
                materialVo.setQuantidade(rmMaterialAux.getQuantidade());
                materialVo.setNumRmMaterial(rmMaterialAux.getRmMaterialId());

                mapMateriaisSolicitados.put(rmMaterialAux, materialVo);
            }

            //Agrupo os materiais de todos os depositos selecionados, somando
            //suas quantidades
            Map<Material, MaterialVo> mapMateriaisDepositos = new HashMap<>();
            for (MaterialDeposito materialDeposito : materiaisDeposito) {
                if (mapMateriaisDepositos.containsKey(materialDeposito.getMaterial())) {
                    MaterialVo materialVo = mapMateriaisDepositos.get(materialDeposito.getMaterial());
                    materialVo.setQuantidade(materialVo.getQuantidade() + materialDeposito.getQuantidade());
                } else {
                    MaterialVo materialVo = new MaterialVo();
                    materialVo.setMaterial(materialDeposito.getMaterial());
                    materialVo.setQuantidade(materialDeposito.getQuantidade());

                    mapMateriaisDepositos.put(materialDeposito.getMaterial(), materialVo);
                }
            }


            //Verifico se os itens da rmMateriaisAux possuem a flag de Reserva no fluxoMaterial
            //Se tiver, então já separo o mesmo como um material para a reserva.
            materiaisReserva = new ArrayList<>();
            for (RmMaterial rmMaterialAux : rmMateriaisAux) {
                if (rmMaterialAux.getFluxoMaterial() != null && rmMaterialAux.getFluxoMaterial().equals(Constantes.FLUXO_MATERIAL_RESERVA)) {
                    MaterialVo materialVo = new MaterialVo();
                    materialVo.setMaterial(rmMaterialAux.getMaterial());
                    materialVo.setQuantidade(rmMaterialAux.getQuantidade());
                    materialVo.setNumRmMaterial(rmMaterialAux.getRmMaterialId());

                    materiaisReserva.add(materialVo);
                }

            }

            //Verifico se as quantidades nos depositos selecionados cobrem a
            //quantidade solicitada
            for (Map.Entry<RmMaterial, MaterialVo> mapSolicitado : mapMateriaisSolicitados.entrySet()) {
                if (mapSolicitado.getKey().getFluxoMaterial() == null || ((!mapSolicitado.getKey().getFluxoMaterial().equals(Constantes.FLUXO_MATERIAL_RESERVA) && !mapSolicitado.getKey().getFluxoMaterial().equals(Constantes.FLUXO_MATERIAL_COMPRA)))) {
                    if (mapMateriaisDepositos.containsKey(mapSolicitado.getKey().getMaterial())) {
                        MaterialVo deposito = mapMateriaisDepositos.get(mapSolicitado.getKey().getMaterial());
                        if (deposito.getQuantidade() >= mapSolicitado.getValue().getQuantidade()) {
                            MaterialVo materialVo = new MaterialVo();
                            materialVo.setMaterial(mapSolicitado.getKey().getMaterial());
                            materialVo.setQuantidade(mapSolicitado.getValue().getQuantidade());
                            materialVo.setNumRmMaterial(mapSolicitado.getValue().getNumRmMaterial());

                            materiaisReserva.add(materialVo);

                            deposito.setQuantidade(deposito.getQuantidade() - mapSolicitado.getValue().getQuantidade());
                        } else if (deposito.getQuantidade() > 0) {
                            MaterialVo materialVo = new MaterialVo();
                            materialVo.setMaterial(mapSolicitado.getKey().getMaterial());
                            materialVo.setQuantidade(deposito.getQuantidade());
                            materialVo.setNumRmMaterial(mapSolicitado.getValue().getNumRmMaterial());

                            materiaisReserva.add(materialVo);

                            deposito.setQuantidade(0d);
                        }
                    }
                }
            }

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return materiaisReserva;
    }

    /**
     * Verifica se os itens que nao possuem estoque nos depositos selecionados,
     * possuem estoque em outros depositos que nao foram selecionados
     *
     * @param materiaisSemQuantidade
     * @param rm
     * @param rmMaterial
     * @return
     */
    public List<MaterialDeposito> listarDepositosComQuantidade(List<MaterialVo> materiaisSemQuantidade, Rm rm, RmMaterial rmMaterial) {
        GenericDao genericDao = new GenericDao();

        List<MaterialDeposito> materiaisDepositoNaoSelecionados;
        Set<MaterialDeposito> setMateriaisDepositoPossuiQuantidade = new HashSet<>();
        List<MaterialDeposito> materiaisDepositoPossuiQuantidade = new ArrayList<>();

        try {
            List<RmDeposito> rmDepositos = listarRmDepositos(rm);
            List<RmMaterial> rmMateriais = new ArrayList<>();
            List<Deposito> depositos = new ArrayList<>();

            //Crio uma lista sem os materiais cautelados, pois eles serao sempre compra
            List<RmMaterial> rmMateriaisAux = new ArrayList<>();

            //Se caso a rmMaterial (Que vem do cadastro da RM do tipo de retirada
            //de estoque, que precisa validar sem uma rmMaterial cadastrada
            //O mesmo adiciona na lista o rmMaterial em vez de fazer a busca.
            if (rmMaterial != null) {
                rmMateriais.add(rmMaterial);
                rmMateriaisAux.add(rmMaterial);
            } else {
                rmMateriais = listarRmMateriais(rm);

                for (RmMaterial material : rmMateriais) {
                    if (itemPodeSerReserva(material, rm, rmDepositos)) {
                        rmMateriaisAux.add(material);
                    }
                }
            }

            for (RmDeposito rmDeposito : rmDepositos) {
                depositos.add(rmDeposito.getDeposito());
            }

            Set<Material> setMaterial = new HashSet<>();
            for (RmMaterial rmMaterialAux : rmMateriaisAux) {
                setMaterial.add(rmMaterialAux.getMaterial());
            }

            if (setMaterial.isEmpty()) {
                return materiaisDepositoPossuiQuantidade;
            }

            //Listo as quantidades dos itens solicitados nos depositos que nao
            //foram selecionados
            String aliasMaterial = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.DEPOSITO);
            String aliasUnidadeMedida = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL, Material.UNIDADE_MEDIDA);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));

            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            NxCriterion nxCriterion = null;
            nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, setMaterial, Filtro.IN));

            if (!depositos.isEmpty()) {
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.DEPOSITO, depositos, Filtro.NOT_IN)));
            }

            materiaisDepositoNaoSelecionados = genericDao.listarByFilter(propriedades, null, MaterialDeposito.class, Constantes.NO_LIMIT, nxCriterion);

            //Verifico se em algum deposito nao selecionado possui as quantidades
            //que nao possuem nos depositos selecionados
            for (MaterialVo material : materiaisSemQuantidade) {
                for (MaterialDeposito materialDeposito : materiaisDepositoNaoSelecionados) {
                    if (material.getMaterial().equals(materialDeposito.getMaterial())
                            && material.getQuantidade() <= materialDeposito.getQuantidade()) {
                        setMateriaisDepositoPossuiQuantidade.add(materialDeposito);
                    }
                }
            }

            materiaisDepositoPossuiQuantidade.addAll(setMateriaisDepositoPossuiQuantidade);

            //Ordeno a lista para garantir que a lista ficara agrupada por
            //item/deposito
            Collections.sort(materiaisDepositoPossuiQuantidade, new Comparator<MaterialDeposito>() {
                @Override
                public int compare(MaterialDeposito o1, MaterialDeposito o2) {
                    return o1.getMaterialDepositoId().compareTo(o2.getMaterialDepositoId());
                }
            });

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return materiaisDepositoPossuiQuantidade;
    }

    private List<MaterialDeposito> verificarMateriaisCauteladosEmEstoque(Rm rm) {
        List<MaterialDeposito> listaMaterialDepositos = new ArrayList<>();
        GenericDao genericDao = new GenericDao();

        try {
            List<RmMaterial> rmMateriais = listarRmMateriais(rm);

            Set<Material> setMaterial = new HashSet<>();
            for (RmMaterial rmMaterial : rmMateriais) {
                if (rmMaterial.getMaterial().getTipoMaterial().getCodigo().equals(Constantes.CAUTELAVEL)) {
                    setMaterial.add(rmMaterial.getMaterial());
                }
            }

            if (setMaterial.isEmpty()) {
                return listaMaterialDepositos;
            }

            String aliasMaterial = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.DEPOSITO);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));

            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            NxCriterion nxCriterion = null;
            nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, setMaterial, Filtro.IN));

            listaMaterialDepositos = genericDao.listarByFilter(propriedades, null, MaterialDeposito.class, Constantes.NO_LIMIT, nxCriterion);

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return listaMaterialDepositos;
    }

    /**
     * Lista os materiais ligados a rm
     *
     * @param rm
     * @return
     */
    public List<RmMaterial> listarRmMateriais(Rm rm) {
        List<RmMaterial> rmMateriais = new ArrayList<>();
        try {
            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class
                    .getName(), Util.getNomeMetodoAtual(), rm, null));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM, rm, Filtro.EQUAL));

            List<NxOrder> orders = Arrays.asList(new NxOrder(RmMaterial.ORDEM, NxOrder.NX_ORDER.DESC));
            rmMateriais = listarRmMateriais(nxCriterion, orders);

            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(),
                    this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), rm, null));
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(),
                    this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return rmMateriais;
    }
    
    public List<RmServico> listarRmServicos(Rm rm) {
        List<RmServico> rmServicos = new ArrayList<>();
        try {
            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class
                    .getName(), Util.getNomeMetodoAtual(), rm, null));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmServico.RM, rm, Filtro.EQUAL));

            List<NxOrder> orders = Arrays.asList(new NxOrder(RmServico.ORDEM, NxOrder.NX_ORDER.DESC));
            rmServicos = listarRmServicos(rm.getRmId());

            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(),
                    this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), rm, null));
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(),
                    this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return rmServicos;
    }
    
    
    public List<ObterAprovadoresRmServico> listarStatusServico(Integer idRm, Integer idRmServico) {
    	Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);
    	List<ObterAprovadoresRmServico> listaStatus = new ArrayList<>();
        GenericDao genericDao = new GenericDao();
        try {
        	
        	String sql = "SELECT\n" +
                    "        rm.RM_ID,\n" +
                    "                rm_mat.RM_SERVICO_ID,\n" +
                    "                mat_st.DATA_HORA_STATUS,\n" +
                    "                mat_st.USUARIO,\n" +
                    "                st.CODIGO,\n" +
                    "                st.ID as STATUS_ID,\n" +
                    "        st.NOME as STATUS_NAME\n" +
                    "        FROM TB_RM_SERVICO_STATUS mat_st\n" +
                    "        INNER JOIN TB_STATUS st ON st.ID = mat_st.STATUS_ID\n" +
                    "        INNER JOIN TB_RM_SERVICO rm_mat ON rm_mat.RM_SERVICO_ID = mat_st.RM_SERVICO_ID\n" +
                    "        INNER JOIN TB_RM rm ON rm.RM_ID = rm_mat.RM_ID\n" +
                    "        WHERE  1=1";
        	
        	if (idRm != null) {
                sql = sql + " AND rm.RM_ID = " + idRm;
            }
        	
             PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet resultSet = smt.executeQuery();

             while (resultSet.next()) {
            	 ObterAprovadoresRmServico statusServico = new ObterAprovadoresRmServico();
            	 statusServico.setIdRm(resultSet.getInt("RM_ID"));
                 statusServico.setIdRmServico(resultSet.getInt("RM_SERVICO_ID"));
                 statusServico.setDataStatus(resultSet.getDate("DATA_HORA_STATUS"));
                 statusServico.setNomeUsuario(resultSet.getString("USUARIO"));
                 statusServico.setCodigoStatus(resultSet.getString("CODIGO"));
                 statusServico.setIdStatus(resultSet.getInt("STATUS_ID"));
                 statusServico.setNomeStatus(resultSet.getString("STATUS_NAME"));
                 listaStatus.add(statusServico);
             }

             smt.close();
             resultSet.close();	
            
        } catch (Exception e) {
            System.out.println("*************************************************ERRO AO GERAR CONSULTA CODIGO SAP*************************************************");
        }
        return listaStatus;
    }
    
    
    public List<RmServicoStatus> listarStatusAtualServico(RmServico Rms) {
        GenericDao<VwRmServico> genericDao = new GenericDao<>();
        List<RmServicoStatus> lista = new ArrayList<>();
        List<VwRmServico> listaVwRmServico = new ArrayList<>();
        try {
        	
        String sql = "select TOP(1) MS.ID, MS.STATUS_ID, MS.RM_SERVICO_ID, MS.RM_SERVICO_ID, MS.DATA_HORA_STATUS, MS.USUARIO, MS.IS_HISTORICO, MS.OBSERVACAO FROM TB_RM_SERVICO_STATUS MS WHERE RM_SERVICO_ID = (?) ORDER BY STATUS_ID DESC;";
        	
       	 sql = sql.replace("(?)", Rms.getRmServicoId().toString());
            PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);
            ResultSet resultSet = smt.executeQuery();

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
                lista.add(rmMatS);
            }
            
            smt.close();
            resultSet.close();	

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
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
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return status;
    }
    
    
    
    public List<FornecedorServico> listarFornecedores(RmServico rmServico) {
        List<FornecedorServico> fornecedor = new ArrayList<>();
        GenericDao genericDao = new GenericDao();
        
        try {
        	String sql = "SELECT CONTATO, EMAIL, FORNECEDOR, TELEFONE, RM_SERVICO_ID FROM TB_FORNECEDOR_SERVICO WHERE RM_SERVICO_ID = '(?)';";
        	
        	sql = sql.replace("(?)", rmServico.getRmServicoId().toString());
            PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);
            ResultSet resultSet = smt.executeQuery();

            while (resultSet.next()) {
           	 FornecedorServico fornecedorServico = new FornecedorServico();
           	fornecedorServico.setContato(resultSet.getString("CONTATO"));
           	fornecedorServico.setEmail(resultSet.getString("EMAIL"));
           	fornecedorServico.setFornecedor(resultSet.getString("FORNECEDOR"));
           	fornecedorServico.setTelefone(resultSet.getString("TELEFONE"));
           	fornecedor.add(fornecedorServico);
            }

            smt.close();
            resultSet.close();	
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(),
                    this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return fornecedor;
    }

    /**
     * Lista os materiais ligados ao agrupador
     *
     * @param codigoAgrupador
     * @return
     */
    public List<RmMaterial> listarRmMateriais(Integer codigoAgrupador) {
        List<RmMaterial> rmMateriais = new ArrayList<>();
        try {
            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(),
                    this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.AGRUPADOR_ERRO, codigoAgrupador, Filtro.EQUAL));
            List<NxOrder> orders = Arrays.asList(new NxOrder(RmMaterial.ORDEM, NxOrder.NX_ORDER.DESC));
            rmMateriais = listarRmMateriais(nxCriterion, orders);

            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(),
                    this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(),
                    this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return rmMateriais;
    }

    /**
     * Lista os materiais ligados à RM
     *
     * @param rm
     * @return
     */
    public List<RmMaterial> listarRmMateriaisByRm(Rm rm) {
        List<RmMaterial> rmMateriais = new ArrayList<>();
        try {
            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(),
                    this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM, rm, Filtro.EQUAL));
            List<NxOrder> orders = Arrays.asList(new NxOrder(RmMaterial.ORDEM, NxOrder.NX_ORDER.DESC));
            rmMateriais = listarRmMateriais(nxCriterion, orders);

            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(),
                    this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(),
                    this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return rmMateriais;
    }

    /**
     * Lista os materiais Pelos ids passados
     *
     * @param ids
     * @return
     * @author Luan  domingues
     */
    public List<RmMaterial> listarRmMateriais(List<Integer> ids) {
        List<RmMaterial> rmMateriais = new ArrayList<>();
        try {
            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(),
                    this.getClass().getName(), Rm.class
                            .getName(), Util.getNomeMetodoAtual(), null, null));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM_MATERIAL_ID, ids, Filtro.IN));
            List<NxOrder> orders = Arrays.asList(new NxOrder(RmMaterial.ORDEM, NxOrder.NX_ORDER.DESC));
            rmMateriais = listarRmMateriais(nxCriterion, orders);

            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(),
                    this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(),
                    this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return rmMateriais;
    }

    /**
     * Lista os materiais Pelo NxCriterion Passado por parametro
     *
     * @param nxCriterion
     * @param orders
     * @return
     * @author Luan  domingues
     */
    public List<RmMaterial> listarRmMateriais(NxCriterion nxCriterion, List<NxOrder> orders) {
        List<RmMaterial> rmMateriais = new ArrayList<>();
        GenericDao<RmMaterial> genericDao = new GenericDao<>();

        try {
            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class
                    .getName(), Util.getNomeMetodoAtual(), null, null));

            String aliasRm = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);
            String aliasRmEapMultiEmpresa = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.EAP_MULTI_EMPRESA);
            String aliasMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.DEPOSITO_ID);
            String aliasFornecedor = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.FORNECEDOR_ID);
            String aliasUnidadeMedida = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL, Material.UNIDADE_MEDIDA);
            String aliasTipoMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL, Material.TIPO_MATERIAL);
            String aliasRequisitante = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.REQUISITANTE);
            String aliasGerenteObra = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.GERENTE_OBRA);
            String aliasGerenteCusto = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.GERENTE_CUSTOS);
            String aliasGerenteArea = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.GERENTE_AREA);
            String aliasTipoRequisicao = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.TIPO_REQUISICAO);


            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO));
            propriedades.add(new Propriedade(RmMaterial.ORDEM));
            propriedades.add(new Propriedade(RmMaterial.FLUXO_MATERIAL));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_CUSTOS));
            propriedades.add(new Propriedade(RmMaterial.OPERACAO));
            propriedades.add(new Propriedade(RmMaterial.DIAGRAMA_REDE));
            propriedades.add(new Propriedade(RmMaterial.LOCAL_ENTREGA));
            propriedades.add(new Propriedade(RmMaterial.ESTA_NO_ORCAMENTO));
            propriedades.add(new Propriedade(RmMaterial.VALOR_ORCADO));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO_CUSTO));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_REQUISICAO_SAP));
            propriedades.add(new Propriedade(RmMaterial.ITEM_REQUISICAO_SAP));
            propriedades.add(new Propriedade(RmMaterial.DATA_RECEBIMENTO_SUPRIMENTOS));
            propriedades.add(new Propriedade(RmMaterial.DATA_PREV_ENTREGA_SUPRIMENTOS));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_ESTOQUE));
            propriedades.add(new Propriedade(RmMaterial.AGRUPADOR_ERRO));
            propriedades.add(new Propriedade(RmMaterial.DATA_CANCELAMENTO));

            //RM
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_EMISSAO, Rm.class, aliasRm));

            //EAP MULTI EMPRESA
            propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasRmEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasRmEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasRmEapMultiEmpresa));

            //MATERIAL
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.PATRIMONIADO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.IS_SERVICO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.STATUS, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.HIERARQUIA, Material.class, aliasMaterial));

            //DEPOSITO
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            //Fornecedor
            propriedades.add(new Propriedade(Fornecedor.FORNECEDOR_ID, Fornecedor.class, aliasFornecedor));
            propriedades.add(new Propriedade(Fornecedor.CODIGO, Fornecedor.class, aliasFornecedor));

            //UNIDADE MEDIDA
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            //TIPO MATERIAL
            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));

            //REQUISITANTE
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasRequisitante));

            //Gerente Area
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasGerenteArea));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasGerenteArea));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasGerenteArea));

            //GERENTE OBRA
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasGerenteObra));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasGerenteObra));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasGerenteObra));

            //GERENTE CUSTO
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasGerenteCusto));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasGerenteCusto));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasGerenteCusto));

            rmMateriais = genericDao.listarByFilter(propriedades, orders, RmMaterial.class,
                    Constantes.NO_LIMIT, nxCriterion);

            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(),
                    this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(),
                    this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return rmMateriais;
    }
    
    
    
    public List<RmServico> listarRmServicos(int idRm) {
        List<RmServico> rmServicos = new ArrayList<>();
        GenericDao<RmServico> genericDao = new GenericDao<>();

        try {
            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class
                    .getName(), Util.getNomeMetodoAtual(), null, null));

            VwConsultaRmaDAO vwConsultaRmaDAO = new VwConsultaRmaDAO();
            rmServicos = vwConsultaRmaDAO.dadosServicos(idRm);
            
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(),
                    this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return rmServicos;
    }


    /**
     * Lista os depositos ligados a rm
     *
     * @param rm
     * @return
     */
    public List<RmDeposito> listarRmDepositos(Rm rm) {
        GenericDao genericDao = new GenericDao();
        List<RmDeposito> lista = new ArrayList();

        try {
            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class
                    .getName(), Util.getNomeMetodoAtual(), rm, null));

            String aliasDeposito = NxCriterion.montaAlias(RmDeposito.ALIAS_CLASSE, RmDeposito.DEPOSITO);
            String aliasRm = NxCriterion.montaAlias(RmDeposito.ALIAS_CLASSE, RmDeposito.RM);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmDeposito.ID));

            //RM
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));

            //DEPOSITO
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            NxCriterion criterion = NxCriterion.montaRestriction(new Filtro(Rm.RM_ID, rm.getRmId(), Filtro.EQUAL, aliasRm));

            List<NxOrder> orders = Arrays.asList(new NxOrder(RmDeposito.DEPOSITO + "." + Deposito.NOME, NxOrder.NX_ORDER.ASC));

            lista = genericDao.listarByFilter(propriedades, orders, RmDeposito.class, Constantes.NO_LIMIT, criterion);

            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class
                    .getName(), Util.getNomeMetodoAtual(), rm, null));
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return lista;
    }
    
   

    /**
     * Lista os materiais para excluir
     *
     * @param cadastroRmVo
     * @return
     */
    public List<RmMaterial> buscarRmMateriaisParaExcluir(CadastroRmVo cadastroRmVo) {
        List<RmMaterial> lista = new ArrayList<>();
        GenericDao genericDao = new GenericDao();
        Set<Integer> ids = new HashSet<>();

        if (cadastroRmVo.getRm().getRmId() == null) {
            //se for uma nova rm, nao existem materiais para serem excluidos
            //estava gerando log pois a rm ainda era um transient
            return lista;
        }

        try {
            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), CadastroRmVo.class
                    .getName(), Util.getNomeMetodoAtual(), cadastroRmVo, null));

            for (RmMaterial rmMaterial : cadastroRmVo.getRmMateriais()) {
                if (rmMaterial.getRmMaterialId() != null && rmMaterial.getRmMaterialId() > 0) {
                    ids.add(rmMaterial.getRmMaterialId());
                }
            }

            String aliasRm = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));

            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));

            NxCriterion criterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM, cadastroRmVo.getRm(), Filtro.EQUAL));
            if (!ids.isEmpty()) {
                criterion = NxCriterion.and(criterion, NxCriterion.montaRestriction(new Filtro(RmMaterial.RM_MATERIAL_ID, ids, Filtro.NOT_IN)));
            }

            lista = genericDao.listarByFilter(propriedades, null, RmMaterial.class, Constantes.NO_LIMIT, criterion);

            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), CadastroRmVo.class
                    .getName(), Util.getNomeMetodoAtual(), cadastroRmVo, null));
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return lista;
    }

    /**
     * Lista os depositos para excluir
     *
     * @param cadastroRmVo
     * @return
     */
    public List<RmDeposito> buscarRmDepositosParaExcluir(CadastroRmVo cadastroRmVo) {
        List<RmDeposito> lista = new ArrayList<>();
        GenericDao genericDao = new GenericDao();
        Set<Integer> ids = new HashSet<>();

        if (cadastroRmVo.getRm().getRmId() == null) {
            //se for uma nova rm, nao existem materiais para serem excluidos
            //estava gerando log pois a rm ainda era um transient
            return lista;
        }
        try {
            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), CadastroRmVo.class
                    .getName(), Util.getNomeMetodoAtual(), cadastroRmVo, null));

            for (RmDeposito rmDeposito : cadastroRmVo.getRmDepositos()) {
                if (rmDeposito.getId() != null && rmDeposito.getId() > 0) {
                    ids.add(rmDeposito.getId());
                }
            }

            String aliasRm = NxCriterion.montaAlias(RmDeposito.ALIAS_CLASSE, RmDeposito.RM);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmDeposito.ID));

            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));

            NxCriterion criterion = NxCriterion.montaRestriction(new Filtro(RmDeposito.RM, cadastroRmVo.getRm(), Filtro.EQUAL));

            if (!ids.isEmpty()) {
                criterion = NxCriterion.and(criterion, NxCriterion.montaRestriction(new Filtro(RmDeposito.ID, ids, Filtro.NOT_IN)));
            }

            lista = genericDao.listarByFilter(propriedades, null, RmDeposito.class, Constantes.NO_LIMIT, criterion);

            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), CadastroRmVo.class
                    .getName(), Util.getNomeMetodoAtual(), cadastroRmVo, null));
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return lista;
    }

    /**
     * Verifica se possui materiais a serem comprados/reservados e chama o
     * metodo responsavel pela atividade
     *
     * @param rm
     * @param listaRmMaterial
     * @param listaRmDepositos
     * @param validacaoMaterialVo
     * @param listaMaterialStatus
     * @param genericDao
     * @param retornoVo
     * @throws Exception
     */
    public void decidirFluxoMateriais(Rm rm, List<VwRmMaterial> listaRmMaterial, List<RmDeposito> listaRmDepositos,
                                      ValidacaoMaterialVo validacaoMaterialVo, List<RmMaterialStatus> listaMaterialStatus, GenericDao<?> genericDao,
                                      RetornoVo retornoVo) throws Exception {

        List<RmMaterial> rmMateriais = new ArrayList<>();

        rmMateriais.addAll(retornoVo.getListaRmMaterialCompra());
        rmMateriais.addAll(retornoVo.getListaRmMaterialReserva());

        //Separa a ordem para nao ficar duplicada
        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(RmMaterial.ORDEM));

        int maiorOrdem = 0;
        try {
            for (RmMaterial rmMaterial : rmMateriais) {
                if (rmMaterial.getOrdem() > maiorOrdem) {
                    maiorOrdem = rmMaterial.getOrdem();
                }
            }

            for (RmMaterial rmMaterialCompra : retornoVo.getListaRmMaterialCompra()) {
                for (RmMaterial rmMaterialReserva : retornoVo.getListaRmMaterialReserva()) {
                    if (rmMaterialReserva.getOrdem() == rmMaterialCompra.getOrdem()) {
                        rmMaterialReserva.setOrdem(maiorOrdem + 1);
                    }
                }
            }
            for (RmMaterial reserva : retornoVo.getListaRmMaterialReserva()) {
                genericDao.updateWithCurrentTransaction(reserva, propriedades);
            }

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

    }

    /**
     * Altera o fluxo do material para compra e gera o status
     *
     * @param listaMateriais
     * @param listaRmMaterial
     * @param genericDao
     * @param retornoVo
     * @throws Exception
     */
    private void comprarMateriais(List<MaterialVo> listaMateriais, List<VwRmMaterial> listaRmMaterial,
                                  List<RmMaterialStatus> listaMaterialStatusGeral, GenericDao<?> genericDao, RetornoVo retornoVo) throws Exception {

        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);

        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(RmMaterial.FLUXO_MATERIAL));
        propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));
        propriedades.add(new Propriedade(RmMaterial.DATA_RECEBIMENTO_SUPRIMENTOS));
        propriedades.add(new Propriedade(RmMaterial.DATA_PREV_ENTREGA_SUPRIMENTOS));

        Date dataRecebimentoSuprimentos = new Date();
        Date dataPrevEntregaSuprimentos = null;

        for (MaterialVo materialVo : listaMateriais) {
            for (VwRmMaterial vwRmMaterial : listaRmMaterial) {
                if (materialVo.getMaterial().equals(vwRmMaterial.getRmMaterial().getMaterial()) && materialVo.getNumRmMaterial().equals(vwRmMaterial.getRmMaterial().getRmMaterialId())) {
                    if (!materialVo.getReserva()) {
                        vwRmMaterial.getRmMaterial().setFluxoMaterial(Constantes.FLUXO_MATERIAL_COMPRA);
                        vwRmMaterial.getRmMaterial().setDataRecebimentoSuprimentos(dataRecebimentoSuprimentos);
                        if (vwRmMaterial.getRmMaterial().getRm() != null
                                && vwRmMaterial.getRmMaterial().getRm().getPrioridade() != null
                                && vwRmMaterial.getRmMaterial().getRm().getPrioridade().getConfDiasPrevEntrega() != null) {
                            dataPrevEntregaSuprimentos = Util.addDia(dataRecebimentoSuprimentos, vwRmMaterial.getRmMaterial().getRm().getPrioridade().getConfDiasPrevEntrega());
                            vwRmMaterial.getRmMaterial().setDataPrevEntregaSuprimentos(dataPrevEntregaSuprimentos);
                        }

                        genericDao.updateWithCurrentTransaction(vwRmMaterial.getRmMaterial(), propriedades);

                        retornoVo.getListaRmMaterialCompra().add(vwRmMaterial.getRmMaterial());

                        retornoVo.getListaMaterialStatus().add(rmMaterialStatusService.gerarStatus(vwRmMaterial.getRmMaterial(), Constantes.STATUS_RM_MATERIAL_AGUARDANDO_COMPRA, null, null, null, null));
                        break;
                    } else {
                        RmMaterial novoRmMaterial = new RmMaterial(vwRmMaterial.getRmMaterial());
                        novoRmMaterial.setQuantidade(materialVo.getQuantidade());
                        novoRmMaterial.setFluxoMaterial(Constantes.FLUXO_MATERIAL_COMPRA);
                        novoRmMaterial.setDataRecebimentoSuprimentos(dataRecebimentoSuprimentos);
                        if (vwRmMaterial.getRmMaterial().getRm() != null
                                && vwRmMaterial.getRmMaterial().getRm().getPrioridade() != null
                                && vwRmMaterial.getRmMaterial().getRm().getPrioridade().getConfDiasPrevEntrega() != null) {
                            dataPrevEntregaSuprimentos = Util.addDia(dataRecebimentoSuprimentos, vwRmMaterial.getRmMaterial().getRm().getPrioridade().getConfDiasPrevEntrega());
                            vwRmMaterial.getRmMaterial().setDataPrevEntregaSuprimentos(dataPrevEntregaSuprimentos);
                        }

                        genericDao.persistWithCurrentTransaction(novoRmMaterial);

                        retornoVo.getListaRmMaterialCompra().add(novoRmMaterial);

                        List<RmMaterialStatus> listaMaterialStatus = rmMaterialStatusService.selecionarStatusMaterial(listaMaterialStatusGeral, vwRmMaterial.getRmMaterial());
                        for (RmMaterialStatus rmMaterialStatus : listaMaterialStatus) {
                            RmMaterialStatus rmMaterialStatusNovo = new RmMaterialStatus(rmMaterialStatus);
                            rmMaterialStatusNovo.setRmMaterial(novoRmMaterial);

                            retornoVo.getListaMaterialStatus().add(rmMaterialStatusNovo);
                        }

                        retornoVo.getListaMaterialStatus().add(rmMaterialStatusService.gerarStatus(novoRmMaterial, Constantes.STATUS_RM_MATERIAL_AGUARDANDO_COMPRA, null, null, null, null));
                        break;
                    }
                }
            }
        }
    }

    /**
     * Verifica se o material esta em apenas um deposito ou em varios, caso
     * esteja em varios, sera necessario gerar um rmMaterial por deposito
     *
     * @param listaRmMaterial
     * @param listaRmDeposito
     * @param materiaisVo
     * @param genericDao
     * @param retornoVo
     * @throws Exception
     */
    private void reservarMateriais(List<VwRmMaterial> listaRmMaterial, List<RmDeposito> listaRmDeposito,
                                   List<MaterialVo> materiaisVo, List<RmMaterialStatus> listaMaterialStatus, GenericDao<?> genericDao,
                                   RetornoVo retornoVo) throws Exception {

        List<MaterialDeposito> listaMateriaisDeposito = listarMaterialDeposito2(listaRmMaterial, listaRmDeposito, genericDao);

        //O mesmo gera a quantidade de materias, pelo fato de ter que passar uma nova ordem, sendo a ultima sempre
        //depois que gerar um novo item, se nao, o mesmo coloca a ordem igual ao item anterior.
        Integer quantidadeNovaOrdemReserva = listaRmMaterial.size();

        for (MaterialVo materialVo : materiaisVo) {
            loopMaterial:
            for (VwRmMaterial vwRmMaterial : listaRmMaterial) {
                if (materialVo.getMaterial().equals(vwRmMaterial.getRmMaterial().getMaterial()) && materialVo.getNumRmMaterial().equals(vwRmMaterial.getRmMaterial().getRmMaterialId())) {
                    for (MaterialDeposito materialDeposito : listaMateriaisDeposito) {
                        if (materialVo.getMaterial().equals(materialDeposito.getMaterial())) {
                            if (materialDeposito.getQuantidade() >= materialVo.getQuantidade() || vwRmMaterial.getRmMaterial().getFluxoMaterial().equals(Constantes.FLUXO_MATERIAL_RESERVA)) {
                                gerarReserva(materialDeposito, vwRmMaterial.getRmMaterial(), materialVo.getQuantidade(), genericDao, retornoVo);
                                break loopMaterial;
                            } else {
                                quantidadeNovaOrdemReserva = quantidadeNovaOrdemReserva + 1;
                                materialVo.setQuantidade(materialVo.getQuantidade() - materialDeposito.getQuantidade());
                                gerarReservaMultiplosDepositos(materialDeposito, vwRmMaterial.getRmMaterial(), materialDeposito.getQuantidade(), listaMaterialStatus, genericDao, retornoVo, quantidadeNovaOrdemReserva);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Gera a reserva do material caso ele esteja em apenas um deposito
     *
     * @param materialDeposito
     * @param rmMaterial
     * @param quantidade
     * @param genericDao
     * @param retornoVo
     * @throws Exception
     */
    private void gerarReserva(MaterialDeposito materialDeposito, RmMaterial rmMaterial, Double quantidade,
                              GenericDao<?> genericDao, RetornoVo retornoVo) throws Exception {

        List<Propriedade> propriedades = new ArrayList<>();
        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);

        //Gera uma saida reserva, para ficar como historico
        MaterialDepositoSaidaReserva materialDepositoSaidaReserva = new MaterialDepositoSaidaReserva();
        materialDepositoSaidaReserva.setDataSaida(new Date());
        materialDepositoSaidaReserva.setRmMaterial(rmMaterial);
        materialDepositoSaidaReserva.setQuantidadeSolicitada(quantidade);

        genericDao.persistWithCurrentTransaction(materialDepositoSaidaReserva);

        //Gera uma saida do material com a flag "reserva" como true
//        MaterialDepositoSaida materialDepositoSaida = new MaterialDepositoSaida();
//
//        materialDepositoSaida.setDataSaida(new Date());
//        materialDepositoSaida.setMaterialDeposito(materialDeposito);
//        materialDepositoSaida.setObservacao("Reserva");
//        materialDepositoSaida.setRm(rmMaterial.getRm());
//        materialDepositoSaida.setQuantidade(quantidade);
//        materialDepositoSaida.setReserva(Boolean.TRUE);
//        materialDepositoSaida.setMaterialDepositoSaidaReserva(materialDepositoSaidaReserva);
//
//        genericDao.persistWithCurrentTransaction(materialDepositoSaida);
        //Diminuo a quantidade do material do estoque
//        materialDeposito.setQuantidade(materialDeposito.getQuantidade() - quantidade);
//        propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));
//        genericDao.updateWithCurrentTransaction(materialDeposito, propriedades);
        //Altero o fluxo do material para reserva
        rmMaterial.setDeposito(materialDeposito.getDeposito());
        rmMaterial.setQuantidade(quantidade);
        rmMaterial.setFluxoMaterial(Constantes.FLUXO_MATERIAL_RESERVA);

        retornoVo.getListaMaterialStatus().add(rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_AGUARDANDO_ESTOQUE, null, null, null, null));

        propriedades.clear();
        propriedades.add(new Propriedade(RmMaterial.DEPOSITO_ID));
        propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));
        propriedades.add(new Propriedade(RmMaterial.FLUXO_MATERIAL));
        genericDao.updateWithCurrentTransaction(rmMaterial, propriedades);

        retornoVo.getListaRmMaterialReserva().add(rmMaterial);
    }

    /**
     * Gera a reserva do material caso ele esteja em varios depositos
     *
     * @param materialDeposito
     * @param rmMaterial
     * @param quantidade
     * @param genericDao
     * @param retornoVo
     * @throws Exception
     */
    private void gerarReservaMultiplosDepositos(MaterialDeposito materialDeposito, RmMaterial rmMaterial, Double quantidade,
                                                List<RmMaterialStatus> listaMaterialStatusGeral, GenericDao<?> genericDao, RetornoVo retornoVo, Integer quantidadeNovaOrdemReserva) throws Exception {

        List<Propriedade> propriedades = new ArrayList<>();
        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);

        //Gero um novo rmMaterial com a quantidade disponivel no deposito
        RmMaterial novoRmMaterial = new RmMaterial(rmMaterial);
        novoRmMaterial.setQuantidade(quantidade);
        novoRmMaterial.setDeposito(materialDeposito.getDeposito());
        novoRmMaterial.setFluxoMaterial(Constantes.FLUXO_MATERIAL_RESERVA);
        novoRmMaterial.setOrdem(quantidadeNovaOrdemReserva);

        genericDao.persistWithCurrentTransaction(novoRmMaterial);

        retornoVo.getListaRmMaterialReserva().add(novoRmMaterial);

        //Faco uma copia de todos os status do material que sera quebrado em dois
        List<RmMaterialStatus> listaMaterialStatus = rmMaterialStatusService.selecionarStatusMaterial(listaMaterialStatusGeral, rmMaterial);
        for (RmMaterialStatus rmMaterialStatus : listaMaterialStatus) {
            RmMaterialStatus rmMaterialStatusNovo = new RmMaterialStatus(rmMaterialStatus);
            rmMaterialStatusNovo.setRmMaterial(novoRmMaterial);

            retornoVo.getListaMaterialStatus().add(rmMaterialStatusNovo);
        }
        retornoVo.getListaMaterialStatus().add(rmMaterialStatusService.gerarStatus(novoRmMaterial, Constantes.STATUS_RM_MATERIAL_AGUARDANDO_ESTOQUE, null, null, null, null));

        //Gera uma saida reserva, para ficar como historico
        MaterialDepositoSaidaReserva materialDepositoSaidaReserva = new MaterialDepositoSaidaReserva();
        materialDepositoSaidaReserva.setDataSaida(new Date());
        materialDepositoSaidaReserva.setRmMaterial(novoRmMaterial);

        genericDao.persistWithCurrentTransaction(materialDepositoSaidaReserva);

        //Gera uma saida do material com a flag "reserva" como true
//        MaterialDepositoSaida materialDepositoSaida = new MaterialDepositoSaida();
//
//        materialDepositoSaida.setDataSaida(new Date());
//        materialDepositoSaida.setMaterialDeposito(materialDeposito);
//        materialDepositoSaida.setObservacao(Constantes.FLUXO_MATERIAL_RESERVA);
//        materialDepositoSaida.setRm(rmMaterial.getRm());
//        materialDepositoSaida.setQuantidade(quantidade);
//        materialDepositoSaida.setReserva(Boolean.TRUE);
//        materialDepositoSaida.setMaterialDepositoSaidaReserva(materialDepositoSaidaReserva);
//
//        genericDao.persistWithCurrentTransaction(materialDepositoSaida);
        //Diminuo a quantidade do material do estoque
//        materialDeposito.setQuantidade(materialDeposito.getQuantidade() - quantidade);
//        propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));
//        genericDao.updateWithCurrentTransaction(materialDeposito, propriedades);
        rmMaterial.setQuantidade(rmMaterial.getQuantidade() - quantidade);

        propriedades.clear();
        propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));
        genericDao.updateWithCurrentTransaction(rmMaterial, propriedades);
    }

    /**
     * Lista os materialDeposito dos materiais e dos depositos selecionados
     *
     * @param rmMateriais
     * @param rmDepositos
     * @param genericDao
     * @return
     */
    private List<MaterialDeposito> listarMaterialDeposito(List<RmMaterial> rmMateriais, List<RmDeposito> rmDepositos, GenericDao<?> genericDao) {
        List<Deposito> depositos = new ArrayList<>();
        List<Material> materiais = new ArrayList<>();
        List<MaterialDeposito> materiaisDeposito = new ArrayList<>();

        for (RmDeposito rmDeposito : rmDepositos) {
            depositos.add(rmDeposito.getDeposito());
        }

        for (RmMaterial rmMaterial : rmMateriais) {
            materiais.add(rmMaterial.getMaterial());
        }

        try {
            //Listo as quantidades dos itens solicitados nos depositos selecionados
            String aliasMaterial = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.DEPOSITO);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));

            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            NxCriterion nxCriterion = null;

            if (!materiais.isEmpty()) {
                nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, materiais, Filtro.IN));

                if (!depositos.isEmpty()) {
                    nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.DEPOSITO, depositos, Filtro.IN)));
                }
            }

            if (nxCriterion != null) {
                materiaisDeposito = (List<MaterialDeposito>) genericDao.listarByFilter(propriedades, null, MaterialDeposito.class, Constantes.NO_LIMIT, nxCriterion);
            }

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return materiaisDeposito;
    }

    /**
     * Lista os materialDeposito dos materiais e dos depositos selecionados
     *
     * @param rmMateriais
     * @param rmDepositos
     * @param genericDao
     * @return
     */
    private List<MaterialDeposito> listarMaterialDeposito2(List<VwRmMaterial> rmMateriais, List<RmDeposito> rmDepositos, GenericDao<?> genericDao) {
        List<Deposito> depositos = new ArrayList<>();
        List<Material> materiais = new ArrayList<>();
        List<MaterialDeposito> materiaisDeposito = new ArrayList<>();

        for (RmDeposito rmDeposito : rmDepositos) {
            depositos.add(rmDeposito.getDeposito());
        }

        for (VwRmMaterial vwRmMaterial : rmMateriais) {
            materiais.add(vwRmMaterial.getRmMaterial().getMaterial());
        }

        try {
            //Listo as quantidades dos itens solicitados nos depositos selecionados
            String aliasMaterial = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.DEPOSITO);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));

            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, materiais, Filtro.IN));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.DEPOSITO, depositos, Filtro.IN)));

            materiaisDeposito = (List<MaterialDeposito>) genericDao.listarByFilter(propriedades, null, MaterialDeposito.class, Constantes.NO_LIMIT, nxCriterion);

            Boolean achou;
            //Se caso não encontrar o materialDeposito, então, crio um com a quantidade 0
            GenericDao<MaterialDeposito> genericDaoNew = new GenericDao<>();
            for (Material material : materiais) {
                for (Deposito deposito : depositos) {
                    //Verifico se não trouxe o mesmo em materiaisDeposito, para não adicionar duplicados no materialDeposito
                    //e sim oq o mesmo não achou
                    achou = false;
                    for (MaterialDeposito md : materiaisDeposito) {
                        if (md.getMaterial().equals(material) && md.getDeposito().equals(deposito)) {
                            achou = true;
                        }
                    }
                    if (achou == false) {
                        MaterialDeposito materialDepositoNovo = new MaterialDeposito();
                        materialDepositoNovo.setDeposito(deposito);
                        materialDepositoNovo.setMaterial(material);
                        materialDepositoNovo.setQuantidade(0.0);
                        genericDaoNew.persist(materialDepositoNovo);
                        materiaisDeposito.add(materialDepositoNovo);
                    }
                }
            }

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return materiaisDeposito;
    }


    public List<Pessoa> listarPessoaRole(String role) {
        return listarPessoaRole(role, null);
    }

    /**
     * Lista as pessoas que possuem ligacao com o usuario que possua a role
     * passada por parametro
     *
     * @param role
     * @return
     */
    public List<Pessoa> listarPessoaRole(String role, Integer eapId) {
        List<Pessoa> pessoas = new ArrayList<>();
        try {
        	PerfilService perfilService = new PerfilService();
        	UsuarioService usuarioService = new UsuarioService();
        	List<Perfil> perfis = perfilService.getPerfisPorRole(role);
        	List<Usuario> usuarios = new ArrayList<Usuario>();
        	for(Perfil p : perfis) {
        		usuarios.addAll(usuarioService.getUsuariosPorPerfil(p.getPerfilId()));
        	}
        	for(Usuario usu : usuarios) {
        		if(usu.getPessoa()!=null) {
        			pessoas.add(usu.getPessoa());
        		}
        	}
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return pessoas;
    }

    /**
     * Lista o ultimo aprovador que foi cadastrado para a rm
     *
     * @param rm
     * @return
     */
    public RmAprovador listarUltimoAprovador(Rm rm) {
        RmAprovador rmAprovador = new RmAprovador();
        GenericDao<RmAprovador> genericDao = new GenericDao<>();

        try {
            String aliasRm = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID);
            String aliasPessoa = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.APROVADOR_PESSOA_ID);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmAprovador.ID));

            //Rm
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));

            //Aprovador
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasPessoa));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmAprovador.RM_ID, rm, Filtro.EQUAL));

            List<NxOrder> orders = Arrays.asList(new NxOrder(RmAprovador.RM_ID, NxOrder.NX_ORDER.DESC));

            rmAprovador = genericDao.listarByFilter(propriedades, orders, RmAprovador.class, Constantes.NO_LIMIT, nxCriterion).get(0);

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return rmAprovador;
    }

    /**
     * Lista as informacoes da rm com suas listas filho
     *
     * @param numeroRma
     * @return
     */
    public Rm selectRmByNumero(String numeroRma) {
        Rm rmRetorno = null;
        GenericDao<Rm> genericDao = new GenericDao<>();

        try {
            String aliasRequisitante = NxCriterion.montaAlias(Rm.ALIAS_CLASSE, Rm.REQUISITANTE);
            String aliasUsuarioPessoa = NxCriterion.montaAlias(Rm.ALIAS_CLASSE, Rm.USUARIO, Usuario.PESSOA);
            String aliasUsuario = NxCriterion.montaAlias(Rm.ALIAS_CLASSE, Rm.USUARIO);
            String aliasPrioridade = NxCriterion.montaAlias(Rm.ALIAS_CLASSE, Rm.PRIORIDADE);
            String aliasTipoRequisicao = NxCriterion.montaAlias(Rm.ALIAS_CLASSE, Rm.TIPO_REQUISICAO);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Rm.RM_ID));
            propriedades.add(new Propriedade(Rm.NUMERO_RM));
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO));
            propriedades.add(new Propriedade(Rm.DATA_EMISSAO));
            propriedades.add(new Propriedade(Rm.DATA_ENVIO_APROVACAO));
            propriedades.add(new Propriedade(Rm.DATA_REPROVACAO));
            propriedades.add(new Propriedade(Rm.DATA_CANCELAMENTO));
            propriedades.add(new Propriedade(Rm.REPROVADO_POR));

            //PRIORIDADE
            propriedades.add(new Propriedade(Prioridade.PRIORIDADE_ID, Prioridade.class, aliasPrioridade));

            //REQUISITANTE
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.EMAIL, Pessoa.class, aliasRequisitante));

            //USUARIO
            propriedades.add(new Propriedade(Usuario.USUARIO_ID, Usuario.class, aliasUsuario));

            //USUARIO PESSOA
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasUsuarioPessoa));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasUsuarioPessoa));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasUsuarioPessoa));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Rm.NUMERO_RM, numeroRma, Filtro.EQUAL));

            rmRetorno = genericDao.selectUnique(propriedades, Rm.class, nxCriterion);
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return rmRetorno;
    }

    /**
     * Seleciona um rmMaterial de acordo com os parametros
     *
     * @param deposito
     * @param material
     * @param codigoRequisicao
     * @return
     */
    public RmMaterial listarRmMaterial(Deposito deposito, Material material, String codigoRequisicao) {
        RmMaterial rmMaterial = null;
        GenericDao<RmMaterial> genericDao = new GenericDao<>();

        try {
            String aliasDeposito = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.DEPOSITO_ID);
            String aliasMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL);
            String aliasRm = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));

            //Material
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));

            //Deposito
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            //Rm
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Rm.NUMERO_RM, codigoRequisicao, Filtro.EQUAL, aliasRm));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterial.DEPOSITO_ID, deposito, Filtro.EQUAL)));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterial.MATERIAL, material, Filtro.EQUAL)));

            rmMaterial = genericDao.selectUnique(propriedades, RmMaterial.class, nxCriterion);
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return rmMaterial;
    }

    /**
     * Seleciona um materialDepositoSaida de acordo com os parametros
     *
     * @param rm
     * @param material
     * @param deposito
     * @return
     */
    public MaterialDepositoSaida listarMaterialDepositoSaida(Rm rm, Material material, Deposito deposito, RmMaterial rmMaterial) {
        List<MaterialDepositoSaida> listMaterialDepositoSaida = new ArrayList<>();
        MaterialDepositoSaida materialDepositoSaida = null;
        GenericDao<MaterialDepositoSaida> genericDao = new GenericDao<>();

        try {
            String aliasRm = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.RM);
            String aliasMaterialDeposito = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO);
            String aliasDepositoSaidaReserva = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID);
            String aliasMaterial = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDeposito.MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDeposito.DEPOSITO);
            String aliasRetiradoPor = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.RETIRADO_POR);
            String aliasRmMaterial = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL);
            String aliasUnidadeMedida = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDeposito.MATERIAL, Material.UNIDADE_MEDIDA);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_ID));
            propriedades.add(new Propriedade(MaterialDepositoSaida.DATA_SAIDA));
            propriedades.add(new Propriedade(MaterialDepositoSaida.NUMERO_PROTOCOLO_SAIDA));
            propriedades.add(new Propriedade(MaterialDepositoSaida.OBSERVACAO));
            propriedades.add(new Propriedade(MaterialDepositoSaida.QUANTIDADE));
            propriedades.add(new Propriedade(MaterialDepositoSaida.QUANTIDADE_ORIGINAL));

            //Rm
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));

            //Retirado por
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRetiradoPor));

            //Material deposito
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID, MaterialDeposito.class, aliasMaterialDeposito));

            //deposito saida reserva
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.ID, MaterialDepositoSaidaReserva.class, aliasDepositoSaidaReserva));

            //deposito
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            //material
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP, Material.class, aliasMaterial));

            //Unidade medida
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            //RmMaterial
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_CUSTOS, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DIAGRAMA_REDE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.OPERACAO, RmMaterial.class, aliasRmMaterial));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaida.RM, rm, Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, material, Filtro.EQUAL, aliasMaterialDeposito)));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.DEPOSITO, deposito, Filtro.EQUAL, aliasMaterialDeposito)));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaida.RESERVA, Boolean.TRUE, Filtro.EQUAL)));

            if (rmMaterial != null) {
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaidaReserva.RM_MATERIAL, rmMaterial, Filtro.EQUAL, aliasDepositoSaidaReserva)));
            }

            listMaterialDepositoSaida
                    = genericDao.listarByFilter(propriedades, null, MaterialDepositoSaida.class, 1, nxCriterion);

            if (listMaterialDepositoSaida != null && !listMaterialDepositoSaida.isEmpty()) {
                materialDepositoSaida = listMaterialDepositoSaida.get(0);
            }
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return materialDepositoSaida;
    }

    /**
     * Seleciona um materialDepositoSaida de acordo com os parametros
     *
     * @param rmMaterial
     * @return
     */
    public MaterialDepositoSaida listarMaterialDepositoSaidaByRmMaterial(RmMaterial rmMaterial, Deposito deposito) {
        MaterialDepositoSaida materialDepositoSaida = null;
        GenericDao<MaterialDepositoSaida> genericDao = new GenericDao<>();

        try {
            String aliasRm = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.RM);
            String aliasMaterialDeposito = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO);
            String aliasDepositoSaidaReserva = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID);
            String aliasMaterial = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDeposito.MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDeposito.DEPOSITO);
            String aliasRetiradoPor = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.RETIRADO_POR);
            String aliasRmMaterial = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL);
            String aliasPessoaResponsavel = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.PESSOA_RESPONSAVEL);
            String aliasUnidadeMedida = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDeposito.MATERIAL, Material.UNIDADE_MEDIDA);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_ID));
            propriedades.add(new Propriedade(MaterialDepositoSaida.DATA_SAIDA));
            propriedades.add(new Propriedade(MaterialDepositoSaida.NUMERO_PROTOCOLO_SAIDA));
            propriedades.add(new Propriedade(MaterialDepositoSaida.OBSERVACAO));
            propriedades.add(new Propriedade(MaterialDepositoSaida.QUANTIDADE));
            propriedades.add(new Propriedade(MaterialDepositoSaida.QUANTIDADE_ORIGINAL));

            //Rm
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));

            //Retirado por
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRetiradoPor));

            //Material deposito
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID, MaterialDeposito.class, aliasMaterialDeposito));

            //deposito saida reserva
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.ID, MaterialDepositoSaidaReserva.class, aliasDepositoSaidaReserva));

            //deposito
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            //material
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP, Material.class, aliasMaterial));

            //Unidade medida
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            //RmMaterial
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_CUSTOS, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DIAGRAMA_REDE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.OPERACAO, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.PROTOCOLO_RESPONSABILIDADE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DOCUMENTO_RESPONSAVEL_IMPRESSO, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.CF_RESPONSAVEL, RmMaterial.class, aliasRmMaterial));

            //Pessoa Responsavel
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasPessoaResponsavel));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasPessoaResponsavel));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasPessoaResponsavel));


            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaida.RM, rmMaterial.getRm(), Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, rmMaterial.getMaterial(), Filtro.EQUAL, aliasMaterialDeposito)));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaidaReserva.RM_MATERIAL, rmMaterial, Filtro.EQUAL, aliasDepositoSaidaReserva)));

            //Para a retirada, os depositos podem vir diferentes do deposito na
            //rmMaterial, se caso o mesmo vier preenchido entao, nao o nxCriterion
            //verificar pelo deposito e nÃƒÂ£o pelo deposito_id da rmMaterial.
            if (deposito != null) {
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterial.DEPOSITO_ID, deposito, Filtro.EQUAL, aliasMaterialDeposito)));
            } else {
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterial.DEPOSITO_ID, rmMaterial.getDeposito(), Filtro.EQUAL, aliasMaterialDeposito)));
            }
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaida.RESERVA, Boolean.TRUE, Filtro.EQUAL)));

            List<MaterialDepositoSaida> lista = genericDao.listarByFilter(propriedades, null, MaterialDepositoSaida.class, Constantes.NO_LIMIT, nxCriterion);
            if (lista == null || lista.isEmpty()) {
                return null;
            }
            if (lista.size() == 1) {
                materialDepositoSaida = lista.get(0);
            } else if (rmMaterial.getPessoaResponsavel() != null && rmMaterial.getPessoaResponsavel().getReferencia() != null) {
                for (MaterialDepositoSaida d : lista) {
                    if (d.getMaterialDepositoSaidaReserva() != null
                            && d.getMaterialDepositoSaidaReserva().getRmMaterial() != null
                            && d.getMaterialDepositoSaidaReserva().getRmMaterial().getPessoaResponsavel() != null
                            && d.getMaterialDepositoSaidaReserva().getRmMaterial().getPessoaResponsavel().getReferencia() != null
                            && d.getMaterialDepositoSaidaReserva().getRmMaterial().getPessoaResponsavel().getReferencia().equals(rmMaterial.getPessoaResponsavel().getReferencia())) {
                        materialDepositoSaida = d;
                    }
                }
            }
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return materialDepositoSaida;
    }

    /**
     * Carrega a pessoa de acordo com a referencia
     *
     * @param referencia
     * @return
     */
    public Pessoa listarPessoa(String referencia, String eap) {
        Pessoa pessoa = null;
        List<Pessoa> lista = new ArrayList<>();
        GenericDao<Pessoa> genericDao = new GenericDao<>();
        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
            propriedades.add(new Propriedade(Pessoa.NOME));

            String aliasEapMultiEmpresa = NxCriterion.montaAlias(Pessoa.ALIAS_CLASSE, Pessoa.EAP_MULTI_EMPRESA);
            propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.REFERENCIA, referencia, Filtro.EQUAL));
            if (eap != null) {
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(EapMultiEmpresa.CODIGO, eap, Filtro.EQUAL, aliasEapMultiEmpresa)));
            }

            lista = genericDao.listarByFilter(propriedades, null, Pessoa.class, 1, nxCriterion);
            if (lista != null && !lista.isEmpty()) {
                pessoa = lista.get(0);
            }

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return pessoa;
    }

    /**
     * Envia email ao requisitante do material caso a pessoa que fez a retirada
     * nao tenha sido ela
     *
     * @param rm
     * @param pessoa
     * @param rmMaterial
     * @param qtdeRetirada
     */
    public void enviarEmailRequisitanteRetiradaMaterial(Rm rm, Pessoa pessoa, RmMaterial rmMaterial, Double qtdeRetirada) {
        String mensagemEmail = "";
        String tituloEmail;

        try {

            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(null));
            Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();

            tituloEmail = rb.getString("msg_titulo_email_retirada_material_nao_requisitante");

            mensagemEmail = Util.setParamsLabel(rb.getString("msg_email_retirada_material_nao_requisitante"), rm.getNumeroRm(), rmMaterial.getMaterial().getNome(), pessoa.getNome(), qtdeRetirada.intValue());


            final String subject = tituloEmail;
            final String recipients = rm.getRequisitante().getEmail();
            final String body = mensagemEmail;
            this.emailService.enviarEmail(subject, recipients, body);

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
    }

    /**
     * Lista o materialDeposito do material e do deposito
     *
     * @param rmMaterial
     * @param deposito
     * @return
     */
    public MaterialDeposito listarMaterialDeposito(RmMaterial rmMaterial, Deposito deposito) {
        MaterialDeposito materiailDeposito = null;
        GenericDao<MaterialDeposito> genericDao = new GenericDao<>();

        try {
            //Listo as quantidades dos itens solicitados nos depositos selecionados
            String aliasMaterial = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.DEPOSITO);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));

            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, rmMaterial.getMaterial(), Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.DEPOSITO, deposito, Filtro.EQUAL)));

            materiailDeposito = genericDao.selectUnique(propriedades, MaterialDeposito.class, nxCriterion);
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return materiailDeposito;
    }

    /**
     * Lista todas as saidas do rmMaterial
     *
     * @param rmMaterial
     * @return
     */
    public List<MaterialDepositoSaida> listarMaterialDepositoSaida(RmMaterial rmMaterial) {
        List<MaterialDepositoSaida> listaMaterialDepositoSaida = new ArrayList<>();
        GenericDao<MaterialDepositoSaida> genericDao = new GenericDao<>();

        try {
            String aliasRm = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.RM);
            String aliasMaterialDeposito = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO);
            String aliasMaterialDepositoSaidaReserva = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID);
            String aliasMaterial = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDeposito.MATERIAL);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_ID));
            propriedades.add(new Propriedade(MaterialDepositoSaida.QUANTIDADE));
            propriedades.add(new Propriedade(MaterialDepositoSaida.RESERVA));

            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));

            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID, MaterialDeposito.class, aliasMaterialDeposito));

            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.ID, MaterialDepositoSaidaReserva.class, aliasMaterialDepositoSaidaReserva));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.STATUS, MaterialDepositoSaidaReserva.class, aliasMaterialDepositoSaidaReserva));

            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaida.RM, rmMaterial.getRm(), Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, rmMaterial.getMaterial(), Filtro.EQUAL, aliasMaterialDeposito)));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaida.RESERVA, Boolean.TRUE, Filtro.EQUAL)));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaidaReserva.STATUS, Constantes.STATUS_MATERIAL_RESERVA_APROVADO, Filtro.EQUAL, aliasMaterialDepositoSaidaReserva)));

            listaMaterialDepositoSaida
                    = genericDao.listarByFilter(propriedades, null, MaterialDepositoSaida.class, Constantes.NO_LIMIT, nxCriterion);
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return listaMaterialDepositoSaida;
    }

    /**
     * Verifica se todos os materiais da rm ja foi retirado
     *
     * @param rm
     * @return
     */
    public boolean validarRmEnviada(Rm rm) {
        GenericDao<SincRegistro> genericDao = new GenericDao<>();

        String aliasRm = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.RM);

        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_ID));

        //Rm
        propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));

        NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaida.DATA_ENVIO_SAP, null, Filtro.IS_NULL));
        nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaida.RM, rm, Filtro.EQUAL)));

        try {
            return genericDao.selectCountByFilter(nxCriterion, MaterialDepositoSaida.class, propriedades) == 0;
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return false;
    }

    /**
     * Envia email a todos os usuarios com a role de administrador, informando
     * que existe um registro com problemas no envio ao sap
     *
     * @param erro
     * @param mensagem
     */
    public void enviarEmailAdministrador(String erro, String mensagem) {
        String mensagemEmail = "";
        String tituloEmail;
        String emails = "";

        for (Pessoa p : listarPessoaRole(Role.ROLE_ADMINISTRADOR)) {
            if (p.getEmail() != null) {
                if (emails.isEmpty()) {
                    emails = p.getEmail();
                } else {
                    emails += ";" + p.getEmail();
                }
            }
        }

        if (emails.isEmpty()) {
            return;
        }

        try {
            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(null));
            Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();

            tituloEmail = rb.getString("msg_titulo_email_administrador");

            mensagemEmail = Util.setParamsLabel(rb.getString("msg_email_administrador"), erro, mensagem);


            final String subject = tituloEmail;
            final String recipients = emails;
            final String body = mensagemEmail;
            this.emailService.enviarEmail(subject, recipients, body);

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
    }

    /**
     * Responsavel por listar os materiais ativos do sistema
     *
     * @return List<Material>
     * @author Alyson X. Leite
     */
    public List<Material> listarMateriais() {
        List<Material> lista = new ArrayList<>();
        GenericDao<Material> dao = new GenericDao<>();

        try {
            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Material.STATUS, Constantes.STATUS_MATERIAL_ATIVO, Filtro.EQUAL));
            nxCriterion = NxCriterion.or(nxCriterion, NxCriterion.montaRestriction(new Filtro(Material.STATUS, null, Filtro.IS_NULL)));

            NxCriterion nxCriterionOr = NxCriterion.montaRestriction(new Filtro(Material.IS_SERVICO, true, Filtro.NOT_EQUAL));
            nxCriterionOr = NxCriterion.or(nxCriterionOr, NxCriterion.montaRestriction(new Filtro(Material.IS_SERVICO, null, Filtro.IS_NULL)));

            nxCriterion = NxCriterion.and(nxCriterion, nxCriterionOr);

            // Seta as propriedades de retorno da consulta.
            String aliasUnidadeMedida = NxCriterion.montaAlias(Material.ALIAS_CLASSE, Material.UNIDADE_MEDIDA);
            String aliasTipoMaterial = NxCriterion.montaAlias(Material.ALIAS_CLASSE, Material.TIPO_MATERIAL);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Material.MATERIAL_ID));
            propriedades.add(new Propriedade(Material.NOME));
            propriedades.add(new Propriedade(Material.CODIGO));
            propriedades.add(new Propriedade(Material.HIERARQUIA));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP));

            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));

            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.DESCRICAO, TipoMaterial.class, aliasTipoMaterial));



            lista = dao.listarByFilter(propriedades, null, Material.class, Constantes.NO_LIMIT, nxCriterion);
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
            return lista;

    }

    /**
     * Verifico se o material e um equipamento
     *
     * @param rmMaterial
     * @return
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     */
    public static boolean isEquipamento(RmMaterial rmMaterial) {
        return rmMaterial != null && rmMaterial.getMaterial() != null
                && rmMaterial.getMaterial().getPatrimoniado() != null
                && rmMaterial.getMaterial().getPatrimoniado().equals(Constantes.SIM_ABREVIADO)
                && (rmMaterial.getMaterial().getTipoMaterial() != null
                && rmMaterial.getMaterial().getTipoMaterial().getCodigo() != null
                && rmMaterial.getMaterial().getTipoMaterial().getCodigo().equals(Constantes.CAUTELAVEL));
    }

    /**
     * Verifico se o material e um Equipamento EPI
     *
     * @param rmMaterial
     * @return
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     */
    public static boolean isEpi(RmMaterial rmMaterial) {
        Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
        if (rmMaterial.getMaterial() != null && conf.getCodigoEpi() != null) {
            return isEpi(rmMaterial.getMaterial());
        }
        return false;
    }

    /**
     * Método que carrega hierarquia de material.
     * <br>
     * <br>
     * <b>Autor:</b> Jerry.
     * <b>Data:</b> 06/04/2016
     * <br>
     * <br>
     *
     * @param id
     * @return Material
     */
    public static Material selectMaterialByIdMaterial(int id) {
        Material m = null;
        try {
            // Seta as propriedades de retorno da consulta.
            List<br.com.nextage.persistence_2.classes.Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.MATERIAL_ID));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.NOME));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.CODIGO));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.HIERARQUIA));

            NxCriterion criterion = NxCriterion.montaRestriction(new br.com.nextage.persistence_2.classes.Filtro(Material.MATERIAL_ID, id, Filtro.EQUAL));

            br.com.nextage.persistence_2.dao.GenericDao<Material> dao = new br.com.nextage.persistence_2.dao.GenericDao<>();
            m = dao.selectUnique(propriedades, Material.class, criterion);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }

    public static boolean isEpi(Material material) {
        Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
        if (material != null && conf.getCodigoEpi() != null) {
            if (material.getHierarquia() == null) {
                Material aux = selectMaterialByIdMaterial(material.getMaterialId());
                if (aux != null) {
                    material.setHierarquia(aux.getHierarquia());
                }
            }
            if (material.getHierarquia() != null) {
                String[] listaEpi = conf.getCodigoEpi().split(";");
                for (String epi : listaEpi) {
                    if (material.getHierarquia().contains(epi)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Verifico se o material e um Material de consumo
     *
     * @param rmMaterial
     * @return
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     */
    public static boolean isMaterialConsumo(RmMaterial rmMaterial) {
        return rmMaterial != null && !isEpi(rmMaterial) && !isCautelavelSemEpi(rmMaterial);
    }

    /**
     * Verifico se o material é cautelável.
     *
     * @param rmMaterial
     * @return
     * @author Marlos Morbis Novo
     */
    public static boolean isCautelavelSemEpi(RmMaterial rmMaterial) {
        return rmMaterial != null && rmMaterial.getMaterial() != null
                && (rmMaterial.getMaterial().getTipoMaterial() != null
                && rmMaterial.getMaterial().getTipoMaterial().getCodigo() != null
                && rmMaterial.getMaterial().getTipoMaterial().getCodigo().equals(Constantes.CAUTELAVEL) && !isEpi(rmMaterial));
    }

    /**
     * Botao de salvar aceita, o mesmo e utilizado no Painel de Estoquista, foi
     * implementado o service, pois sera utilizado tb na integracao de atualizar
     * estoque
     *
     * @param materialDepositoSaidaReserva
     * @param verificaNovaSaida
     * @param verificaQtdExataMaior
     * @param atualizarStatus
     * @param recTelaPaiEsq
     * @return
     */
    public Info salvarAceiteMaterial(MaterialDepositoSaidaReserva materialDepositoSaidaReserva, Boolean verificaNovaSaida, Boolean verificaQtdExataMaior, boolean atualizarStatus, boolean recTelaPaiEsq, String login) {
        Info info = new Info();
        GenericDao<RmMaterial> genericDao = new GenericDao<>();
        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);

        List<Propriedade> propriedades = new ArrayList<>();
        List<RmMaterial> listaRmMateriais = listarRmMateriais(materialDepositoSaidaReserva.getRmMaterial().getRm());

        try {
            RmMaterial rmMaterial = materialDepositoSaidaReserva.getRmMaterial();

            RmMaterialStatus rmMaterialStatus = null;
            if (atualizarStatus) {
                rmMaterialStatus = rmMaterialStatusService.listarStatusAtual(rmMaterial);
            }

            MaterialDeposito materialDeposito = listarDepositoMaterial(rmMaterial.getMaterial(), rmMaterial.getDeposito());
            List<RmMaterialStatus> listaMaterialStatus = rmMaterialStatusService.listarMaterialStatus(rmMaterial, Boolean.TRUE);

            genericDao.beginTransaction();

            boolean tipoRequisicaoFrenteServico = false;
            if (verificaNovaSaida == true) {
                //Gera uma saida do material com a flag "reserva" como true
                MaterialDepositoSaida materialDepositoSaida = new MaterialDepositoSaida();

                materialDepositoSaida.setDataSaida(new Date());
                materialDepositoSaida.setObservacao(Constantes.FLUXO_MATERIAL_RESERVA);
                materialDepositoSaida.setRm(rmMaterial.getRm());
                materialDepositoSaida.setQuantidade(rmMaterial.getQtdRetirado());
                materialDepositoSaida.setQuantidadeOriginal(rmMaterial.getQtdRetirado());
                materialDepositoSaida.setReserva(Boolean.TRUE);
                materialDepositoSaida.setMaterialDepositoSaidaReserva(materialDepositoSaidaReserva);
                materialDepositoSaida.setMaterialDeposito(materialDeposito);

                genericDao.persistWithCurrentTransaction(materialDepositoSaida);
            }

            //Atualizo a quantidade requisitada
            propriedades.clear();
            if (recTelaPaiEsq == false) {
                rmMaterial.setQuantidadeOriginal(rmMaterial.getQuantidade());
                rmMaterial.setQuantidade(rmMaterial.getQtdRetirado());

                propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));
                propriedades.add(new Propriedade(RmMaterial.QUANTIDADE_ORIGINAL));
            }
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO_ESTOQUISTA));
            propriedades.add(new Propriedade(RmMaterial.PREFIXO_EQUIPAMENTO));

            genericDao.updateWithCurrentTransaction(rmMaterial, propriedades);

            if (atualizarStatus) {
                //Atualizo o material deposito saida reserva
                materialDepositoSaidaReserva.setDataAvaliacao(new Date());
                materialDepositoSaidaReserva.setStatus(Constantes.STATUS_MATERIAL_RESERVA_APROVADO);
                materialDepositoSaidaReserva.setObservacaoPainelEstoquista(null);

                propriedades.clear();
                propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.DATA_AVALIACAO));
                propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.STATUS));
                propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.OBSERVACAO_PAINEL_ESTOQUISTA));

                genericDao.updateWithCurrentTransaction(materialDepositoSaidaReserva, propriedades);

                //Gero status finalizado
                rmMaterialStatusService.finalizarStatus(rmMaterialStatus, genericDao, new Date());
            }
            //Se caso for do tipo estoque e tiver a quantidade exata solicitada ou maior, finaliza o material.
            if (atualizarStatus && rmMaterial.getColetorEstoque() != null && rmMaterial.getColetorEstoque() && verificaQtdExataMaior) {
                rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_FINALIZADO, genericDao, new Date(), null, login);
            } else if (rmMaterial.getColetorEstoque() == null || (rmMaterial.getColetorEstoque() != null && !rmMaterial.getColetorEstoque())) {

                //Somente cria reservar para itens que não são enviados apara o CPweb.
                //[Marlos Morbis Novo] 24/05/2016
                if (tipoRequisicaoFrenteServico || !validaEnvioParaCpweb(rmMaterial.getMaterial())) {
                    //Diminuo a quantidade do material do estoque
                    propriedades.clear();
                    materialDeposito.setQuantidade(materialDeposito.getQuantidade() - rmMaterial.getQtdRetirado());
                    propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));
                    genericDao.updateWithCurrentTransaction(materialDeposito, propriedades);
                    if (atualizarStatus) {
                        rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_AGUARDANDO_RETIRADA, genericDao, null, null, login);
                    }
                }
            }

            if (tipoRequisicaoFrenteServico == false && verificaNovaSaida != false && rmMaterial.getQtdRetirado() < materialDepositoSaidaReserva.getQuantidadeSolicitada()) {

                RmMaterial novoRmMaterial = new RmMaterial(rmMaterial);
                novoRmMaterial.setDeposito(null);
                novoRmMaterial.setQuantidade(materialDepositoSaidaReserva.getQuantidadeSolicitada() - rmMaterial.getQtdRetirado());
                novoRmMaterial.setFluxoMaterial(Constantes.FLUXO_MATERIAL_COMPRA);

                int maiorOrdem = 0;
                for (RmMaterial material : listaRmMateriais) {
                    if (material.getOrdem() > maiorOrdem) {
                        maiorOrdem = material.getOrdem();
                    }
                }

                novoRmMaterial.setOrdem(maiorOrdem + 1);

                genericDao.persistWithCurrentTransaction(novoRmMaterial);

                for (RmMaterialStatus rmMaterialStatusParaCopia : listaMaterialStatus) {
                    if (!rmMaterialStatusParaCopia.getStatus().getCodigo().equals(Constantes.STATUS_RM_MATERIAL_AGUARDANDO_ESTOQUE)) {
                        RmMaterialStatus rmMaterialStatusNovo = new RmMaterialStatus(rmMaterialStatusParaCopia);
                        rmMaterialStatusNovo.setRmMaterial(novoRmMaterial);

                        genericDao.persistWithCurrentTransaction(rmMaterialStatusNovo);
                    }
                }

                rmMaterialStatusService.gerarStatus(novoRmMaterial, Constantes.STATUS_RM_MATERIAL_AGUARDANDO_COMPRA, genericDao, null, null, login);
            }

            genericDao.commitCurrentTransaction();

            //Só envia reserva se for requisição de Frente de Serviço
            if (tipoRequisicaoFrenteServico == true) {
                if (rmMaterial.getMaterial().getTipoMaterial() != null && rmMaterial.getMaterial().getTipoMaterial().getCodigo() != null) {
                    if (validaEnvioParaCpweb(rmMaterial.getMaterial())) {
                        //Somente envia reserva para o CP se não for uma compra para estoque.
                        if (rmMaterial.getColetorEstoque() == null || !rmMaterial.getColetorEstoque()) {
                            SincReservaService sincReservaService = new SincReservaService();
                            String nomeUsuario = LoginService.getUsuarioLogado(request).getNome();
                            sincReservaService.enviarReserva(rmMaterial, nomeUsuario, true);
                        }

                        Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();
                        //Ao retornar, verifico a configuração do mesmo de ENCAR_SOLICITA_RMA_MOBILE
                        //Se caso a confi for true, então, o mesmo não ira enviar para o mobile finalizar e sim finalizara
                        //direto.
                        if (configuracao.getEncarSolicitaRmaMobile() != null && configuracao.getEncarSolicitaRmaMobile() == true) {
                            info = finalizaFrenteServico(rmMaterial);
                            if (info.getErro() != null) {
                                return info;
                            }
                        }
                    }
                }
            }


            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem("msg_aceite_material_sucesso");
            info.setErro(null);
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setMensagem(Constantes.ERRO_OPERACAO_I18N);
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;

    }


    /**
     * Verifica se o item deve ser enviado para o CPWEB.
     *
     * @param material
     * @return boolean
     * @author Marlos Morbis Novo <m.novo@nextage.com.br>
     */
    public boolean verificaEnvioParaCpweb(Material material) {
        return validaEnvioParaCpweb(material);
    }

    private boolean validaEnvioParaCpweb(Material material) {
/*        if((tipoRequisicaoFrenteServico &&
                (rmMaterial.getMaterial().getTipoMaterial().getCodigo().equals(Constantes.CAUTELAVEL)
                        || isEpi(rmMaterial.getMaterial())))
                || (rmMaterial.getFluxoMaterial() != null
                && rmMaterial.getFluxoMaterial().equals(Constantes.FLUXO_MATERIAL_RESERVA)
                && (rmMaterial.getMaterial().getTipoMaterial().getCodigo().equals(Constantes.CAUTELAVEL)
                || isEpi(rmMaterial.getMaterial())))){*/

        if (material.getTipoMaterial().getCodigo().equals(Constantes.CAUTELAVEL)
                || isEpi(material)) {
            //Envia CP
            return true;
        }

        //não envia CP
        return false;
    }

    public MaterialDeposito listarDepositoMaterial(Material material, Deposito deposito) {
        GenericDao<MaterialDeposito> genericDao = new GenericDao<>();
        MaterialDeposito materialDeposito = null;

        try {
            //Listo as quantidades dos itens solicitados nos depositos selecionados
            String aliasMaterial = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.DEPOSITO);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));

            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, material, Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.DEPOSITO, deposito, Filtro.EQUAL)));

            materialDeposito
                    = genericDao.selectUnique(propriedades, MaterialDeposito.class, nxCriterion);
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return materialDeposito;

    }

    /**
     * Realiza a transferencia do material de um estoque para outro Caso o
     * parametro enviaCpweb == true envia a transferencia para o sistema CPWEB
     *
     * @param materialVo
     * @param enviaCpweb
     * @return
     * @author Alyson X. Leite
     */
    private Info transferenciaMaterial(MaterialTransferenciaVo materialVo, boolean enviaCpweb, GenericDao genericDao) {
        try {
            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), MaterialTransferenciaVo.class
                    .getName(), Util.getNomeMetodoAtual(), materialVo, null));

            MaterialDeposito depositoNovo;
            GenericDao<MaterialDeposito> gdaoConsulta = new GenericDao<>();
            // Recupero o deposito antigo
            String aliasMaterial = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.DEPOSITO);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));

            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            NxCriterion nxCriterion;
            nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, materialVo.getMaterial(), Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.DEPOSITO, materialVo.getDepositoOrigem(), Filtro.EQUAL)));

            MaterialDeposito depositoAnt = gdaoConsulta.selectUnique(propriedades, MaterialDeposito.class, nxCriterion);

            // Validacoes
            if (depositoAnt == null) {
                return Info.GetError("msg_necessario_selecionar_deposito");
            }

            // Verifico se a quantidade de transferencia e igual a zero
            if (materialVo.getQuantidadeTransferencia().equals(0)) {
                return Info.GetError("msg_material_quantidade_zero");
            }

            // Caso os depositos sejam iguais
            if (materialVo.getDepositoOrigem().getDepositoId().equals(materialVo.getDepositoDestino().getDepositoId())) {
                return Info.GetError("msg_selecione_depositos_diferentes");
            }

            // Verifico se a quantidade disponivel e igual a zero
            if (depositoAnt.getQuantidade().equals(0.0)) {
                return Info.GetError("label_item_indisponivel_no_deposito");
            }

            // Verifico se o deposito tem a quantidade de transferencia
            if (depositoAnt.getQuantidade() < materialVo.getQuantidadeTransferencia()) {
                return Info.GetError("msg_quantidade_retirada_maior_quantidade_solicitada");
            }

            materialVo.setDepositoDestino(getDeposito(materialVo.getDepositoDestino()));
            materialVo.setDepositoOrigem(getDeposito(materialVo.getDepositoOrigem()));

            // Recupero o MaterialDeposito selecionado para a movimentacao
            nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, materialVo.getMaterial(), Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.DEPOSITO, materialVo.getDepositoDestino(), Filtro.EQUAL)));

            depositoNovo = gdaoConsulta.selectUnique(propriedades, MaterialDeposito.class, nxCriterion);

            Double qtdAtualDepositoNovo;

            if (depositoNovo == null) {
                depositoNovo = new MaterialDeposito();
                depositoNovo.setDeposito(materialVo.getDepositoDestino());
                depositoNovo.setMaterial(materialVo.getMaterial());
                depositoNovo.setQuantidade(materialVo.getQuantidadeTransferencia());
                qtdAtualDepositoNovo = 0.0;
            } else {
                qtdAtualDepositoNovo = depositoNovo.getQuantidade();
                depositoNovo.setDeposito(materialVo.getDepositoDestino());
                depositoNovo.setQuantidade(depositoNovo.getQuantidade() + materialVo.getQuantidadeTransferencia());
            }
            // Atualizo ou persisto o deposito novo escolhido
            if (depositoNovo.getMaterialDepositoId() != null && depositoNovo.getMaterialDepositoId() > 0) {

                propriedades.clear();
                propriedades.add(new Propriedade(MaterialDeposito.DEPOSITO));
                propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));
                genericDao.updateWithCurrentTransaction(depositoNovo, propriedades);
            } else {
                genericDao.persistWithCurrentTransaction(depositoNovo);
            }

            // Verifica se o item ficara indisponavel mesmo com a transferencia do material
            Double qtdTransferencia = materialVo.getQuantidadeTransferencia();
            Double qtdSolicitada = materialVo.getQuantidadeSolicitada();

            boolean itemIndisponivel = false;
            if (qtdSolicitada != null && qtdAtualDepositoNovo != null && qtdTransferencia != null) {
                itemIndisponivel = qtdTransferencia + qtdAtualDepositoNovo < qtdSolicitada;
            }

            // Calcula a quantidade retirada do deposito
            propriedades.clear();
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));
            depositoAnt.setQuantidade(depositoAnt.getQuantidade() - materialVo.getQuantidadeTransferencia());
            genericDao.updateWithCurrentTransaction(depositoAnt, propriedades);

            propriedades.clear();
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));
            genericDao.updateWithCurrentTransaction(depositoAnt, propriedades);

            if (materialVo.getRmMaterial() != null && materialVo.getRmMaterial().getRm() != null) {
                Rm rm = materialVo.getRmMaterial().getRm();

                // Salvo o hitorico de saida de materiais no deposito antigo
                MaterialDepositoSaida objMDS = new MaterialDepositoSaida();
                objMDS.setMaterialDeposito(depositoAnt);
                objMDS.setDataSaida(new Date());
                // Esta transferindo de um deposito para o outro
                // entao nao e uma reserva
                objMDS.setReserva(true);
                objMDS.setQuantidade(materialVo.getQuantidadeTransferencia());
                objMDS.setObservacao(Constantes.SAIDA_DE_MATERIAL_POR_MOVIMENTACAO);
                objMDS.setRm(rm);
                objMDS.setMaterialDepositoSaidaReserva(materialVo.getMaterialDepositoSaidaReserva());

                genericDao.persistWithCurrentTransaction(objMDS);

                // Salvo o hitorico de entrada de materiais no deposito novo
                MaterialDepositoEntrada objMDE = new MaterialDepositoEntrada();
                objMDE.setMaterialDeposito(depositoNovo);
                objMDE.setDataEntrada(new Date());
                objMDE.setQuantidade(materialVo.getQuantidadeTransferencia());
                objMDE.setObservacao(Constantes.ENTRADA_DE_MATERIAL_POR_MOVIMENTACAO);
                objMDE.setRm(rm);

                genericDao.persistWithCurrentTransaction(objMDE);

                // Atualizo a reserva
                MaterialDepositoSaidaReserva mdsr = materialVo.getMaterialDepositoSaidaReserva();
                mdsr.setItemIndisponivel(itemIndisponivel);
                //mdsr.setObservacaoPainelEstoquista(null);

                propriedades.clear();
                propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.ITEM_INDISPONIVEL));
                genericDao.updateWithCurrentTransaction(mdsr, propriedades);
            }

            Info info = Info.GetSuccess(Constantes.SUCESSO_OPERACAO_I18N, null);

            // FAZ A SAIDA DO ESTOQUE ANTIGO E A ENTRADA NO NOVO ESTOQUE
            // NO SISTEMA CPWEB
            if (enviaCpweb) {
                SincEquipamentoService sincReservaService = new SincEquipamentoService();
                sincReservaService.transferirMaterial(materialVo, true);
            }

            return info;
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            return Info.GetError(Constantes.REG_SALVO_ERRO_I18N);
        }
    }

    /**
     * Lista os depositos que contenham o material disponivel
     *
     * @param deposito
     * @return
     */
    public Deposito getDeposito(Deposito deposito) {
        Deposito retorno = null;

        try {
            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Deposito.class
                    .getName(), Util.getNomeMetodoAtual(), deposito, null));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));
            propriedades.add(new Propriedade(Deposito.NOME));
            propriedades.add(new Propriedade(Deposito.CODIGO));
            propriedades.add(new Propriedade(Deposito.IS_TEMPORARIO));
            propriedades.add(new Propriedade(Deposito.OBSERVACAO));

            NxCriterion nxCriterion;
            if (deposito.getCodigo() != null && !deposito.getCodigo().isEmpty()) {
                nxCriterion = NxCriterion.montaRestriction(new Filtro(Deposito.CODIGO, deposito.getCodigo(), Filtro.EQUAL));

            } else {
                nxCriterion = NxCriterion.montaRestriction(new Filtro(Deposito.NOME, deposito.getNome(), Filtro.LIKE));

            }
            GenericDao<Deposito> genericDao = new GenericDao<>();
            retorno = genericDao.selectUnique(propriedades, Deposito.class, nxCriterion);

            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Deposito.class
                    .getName(), Util.getNomeMetodoAtual(), retorno, null));

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return retorno;
    }

    public Object listarVwRmMaterial(FiltroCadastroRma filtro, Boolean paginado) {
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
        List<Propriedade> propriedades = new ArrayList<>();

        try {
            LOG.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroCadastroRma.class.getName(), Util.getNomeMetodoAtual(), filtro, null));

            String aliasRmMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID);
            String aliasRmMaterialStatus = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_STATUS_ID);
            String aliasStatus = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_STATUS_ID, RmMaterialStatus.STATUS_ID);
            String aliasRm = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM);
            String aliasComprador = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.COMPRADOR);
            String aliasPrioridade = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.PRIORIDADE);
            String aliasTipoRequisicao = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.TIPO_REQUISICAO);
            String aliasMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.MATERIAL);
            String aliasUnidadeMedida = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.MATERIAL, Material.UNIDADE_MEDIDA);
            String aliasRequisitante = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.REQUISITANTE);
            String aliasUsuario = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.USUARIO);
            String aliasUsuarioPessoa = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.USUARIO, Usuario.PESSOA);
            String aliasRmMaterialDeposito = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.DEPOSITO_ID);

            //VIEW
            propriedades.add(new Propriedade(VwRmMaterial.ID));

            //RM MATERIAL
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_REQUISICAO_SAP, RmMaterial.class, aliasRmMaterial));
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

            //RM MATERIAL STATUS
            propriedades.add(new Propriedade(RmMaterialStatus.ID, RmMaterialStatus.class, aliasRmMaterialStatus));

            //STATUS
            propriedades.add(new Propriedade(Status.ID, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.NOME, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.CODIGO, Status.class, aliasStatus));

            //RM
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_EMISSAO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_ENVIO_APROVACAO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_REPROVACAO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_CANCELAMENTO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.LOCAL_ENTREGA, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.OBSERVACAO, Rm.class, aliasRm));

            //COMPRADOR
            propriedades.add(new Propriedade(Comprador.COMPRADOR_ID, Comprador.class, aliasComprador));
            propriedades.add(new Propriedade(Comprador.NOME, Comprador.class, aliasComprador));

            //PRIORIDADE
            propriedades.add(new Propriedade(Prioridade.PRIORIDADE_ID, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.CODIGO, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.DESCRICAO, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.CONF_DIAS_PREV_ENTREGA, Prioridade.class, aliasPrioridade));

            //MATERIAL
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.HIERARQUIA, Material.class, aliasMaterial));

            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            //REQUISITANTE
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasRequisitante));

            //USUARIO
            propriedades.add(new Propriedade(Usuario.USUARIO_ID, Usuario.class, aliasUsuario));
            propriedades.add(new Propriedade(Usuario.NOME, Usuario.class, aliasUsuario));

            //USUARIO PESSOA
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasUsuarioPessoa));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasUsuarioPessoa));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasUsuarioPessoa));

            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasRmMaterialDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasRmMaterialDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasRmMaterialDeposito));

            NxCriterion nxCriterion = null;
            NxCriterion nxCriterionAux;

            Usuario usuario = LoginService.getUsuarioLogado(request);

            if (filtro != null) {

                if (filtro.getUsuario() == null) {
                    usuario = LoginService.getUsuarioLogado(request);
                }

                if (filtro.getListaIdRmMaterial() != null && !filtro.getListaIdRmMaterial().isEmpty()) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM_MATERIAL_ID, filtro.getListaIdRmMaterial(), Filtro.IN, aliasRmMaterial));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }
                if (filtro.getMaterial() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.MATERIAL, filtro.getMaterial(), Filtro.EQUAL, aliasRmMaterial));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
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
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Rm.NUMERO_RM, filtro.getNumero(), Filtro.EQUAL, aliasRm));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
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
                            dataDe = Util.parseData(filtro.getStDataEmissaoDe(), rb.getString("format_date"));
                        } catch (Exception e) {
                            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
                        }
                    }

                    if (filtro.getStDataEmissaoAte() != null && !filtro.getStDataEmissaoAte().isEmpty()) {
                        try {
                            dataAte = Util.parseData(filtro.getStDataEmissaoAte(), rb.getString("format_date"));
                        } catch (Exception e) {
                            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
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
                            dataDe = Util.parseData(filtro.getStDataNecessidadeDe(), rb.getString("format_date"));
                        } catch (Exception e) {
                            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
                        }
                    }

                    if (filtro.getStDataNecessidadeAte() != null && !filtro.getStDataNecessidadeAte().isEmpty()) {
                        try {
                            dataAte = Util.parseData(filtro.getStDataNecessidadeAte(), rb.getString("format_date"));
                        } catch (Exception e) {
                            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
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
            }

            Boolean possuiRoleVisualizarTodasRm = false;

            for (Role role : LoginService.getUsuarioLogado(request).getRoles()) {
                if (role.getNome().equals(Role.ROLE_ADMINISTRADOR)) {
                    possuiRoleVisualizarTodasRm = true;
                    break;
                }
            }

            if (!possuiRoleVisualizarTodasRm) {
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

            List<NxOrder> orders = Arrays.asList(new NxOrder(VwRmMaterial.RM_MATERIAL_ID + "." + RmMaterial.RM + "." + Rm.RM_ID, NxOrder.NX_ORDER.DESC));

            if (paginado) {
                Paginacao.build(propriedades, orders, VwRmMaterial.class, nxCriterion, filtro.getPaginacaoVo());
                return filtro.getPaginacaoVo();
            } else {
                GenericDao<VwRmMaterial> genericDao = new GenericDao<>();
                return genericDao.listarByFilter(propriedades, orders, VwRmMaterial.class, Constantes.NO_LIMIT, nxCriterion);
            }

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return null;
    }

    public Double qtdeRmMatRetirada(RmMaterialRetirada rmMaterialRetirada, boolean verificaQtdeBioNAutenticada, boolean verificaQtdeTotalDep) {
        List<Propriedade> propriedades = new ArrayList<>();
        GenericDao<RmMaterialRetirada> genericDao = new GenericDao<>();
        Double quantidade = 0.0;
        List<RmMaterialRetirada> rmMaterialRetiradaLista = new ArrayList<>();

        try {
            String aliasRmMaterial = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RM_MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.DEPOSITO);

            //VIEW
            propriedades.add(new Propriedade(RmMaterialRetirada.RM_MATERIAL_RETIRADA_ID));
            propriedades.add(new Propriedade(RmMaterialRetirada.QUANTIDADE));
            propriedades.add(new Propriedade(RmMaterialRetirada.PRE_RETIRADA));
            propriedades.add(new Propriedade(RmMaterialRetirada.DATA_AUTENTICACAO));

            //RM MATERIAL
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));

            //DEPOSITO
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));

            NxCriterion nxCriterion = null;
            NxCriterion nxCriterionAux = null;

            if (verificaQtdeTotalDep) {
                nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.RM_MATERIAL, rmMaterialRetirada.getRmMaterial(), Filtro.EQUAL));

            } else if (verificaQtdeBioNAutenticada) {
                nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.RM_MATERIAL, rmMaterialRetirada.getRmMaterial(), Filtro.EQUAL));
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.PRE_RETIRADA, Boolean.TRUE, Filtro.EQUAL)));
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.DATA_AUTENTICACAO, null, Filtro.IS_NULL)));
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.DEPOSITO, rmMaterialRetirada.getDeposito(), Filtro.EQUAL)));

            } else {

                //Separo rmMaterialRetirada que nÃ£o vieram do leitor
                nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.RM_MATERIAL, rmMaterialRetirada.getRmMaterial(), Filtro.EQUAL));
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.PRE_RETIRADA, Boolean.FALSE, Filtro.EQUAL)));

                //Separo rmMaterialRetirada que vieram do leitor
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.RM_MATERIAL, rmMaterialRetirada.getRmMaterial(), Filtro.EQUAL));
                nxCriterionAux = NxCriterion.and(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.PRE_RETIRADA, Boolean.TRUE, Filtro.EQUAL)));
                nxCriterionAux = NxCriterion.and(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.DATA_AUTENTICACAO, null, Filtro.NOT_NULL)));

                nxCriterion = NxCriterion.or(nxCriterion, nxCriterionAux);

                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.DEPOSITO, rmMaterialRetirada.getDeposito(), Filtro.EQUAL)));
            }
            rmMaterialRetiradaLista = genericDao.listarByFilter(propriedades, null, RmMaterialRetirada.class, Constantes.NO_LIMIT, nxCriterion);

            for (RmMaterialRetirada rm : rmMaterialRetiradaLista) {
                quantidade = quantidade + rm.getQuantidade();
            }

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return quantidade;
    }

    public void validarRetiradasNaoPresenciais(RmMaterial rmMaterial) {
        try {
            GenericDao<RmMaterialRetirada> genericDao = new GenericDao<>();
            List<Propriedade> propriedades = new ArrayList<>();

            propriedades.add(new Propriedade(RmMaterialRetirada.RM_MATERIAL_RETIRADA_ID));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.PRE_RETIRADA, true, Filtro.EQUAL));

            NxCriterion nxCriterionOr = NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.PRE_RETIRADA, false, Filtro.EQUAL));
            nxCriterionOr = NxCriterion.and(nxCriterionOr, NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.AUTENTICACAO, null, Filtro.IS_NULL)));

            nxCriterion = NxCriterion.or(nxCriterion, nxCriterionOr);

            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.RM_MATERIAL, rmMaterial, Filtro.EQUAL)));

            if (genericDao.selectCountByFilter(nxCriterion, RmMaterialRetirada.class, propriedades) > 0) {
                NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(null));
                Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();

                String tituloEmail = rb.getString("msg_titulo_email_retirada_finalizada");

                String mensagemEmail = Util.setParamsLabel(rb.getString("msg_email_retirada_finalizada"), rmMaterial.getRm().getNumeroRm(), rmMaterial.getMaterial().getNome());

                final String subject = tituloEmail;
                final String recipients = rmMaterial.getRm().getRequisitante().getEmail();
                final String body = mensagemEmail;
                this.emailService.enviarEmail(subject, recipients, body);
            }

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
    }

    /*
    * Verifica a hierarquia do comprador
    *
    * @param  HierarquiaComprador
    * @return info
    *
    * */

    public Info verificaHierarquia(List<RmMaterial> listaRmMaterial) {

        List<Propriedade> propriedades = new ArrayList<>();
        List<HierarquiaComprador> lista = new ArrayList<>();
        List<HierarquiaComprador> listaAux = new ArrayList<>();
        List<RmMaterial> listaRmMat = new ArrayList<>();
        List<Comprador> listaComprador = new ArrayList<>();
        Info info = new Info();
        GenericDao<RmMaterial> genericDaoRmMaterial = new GenericDao<>();
        GenericDao genericDao = new GenericDao();

        Boolean habilitaEapMultiEmpresa = ConfiguracaoSingleton.getConfiguracao().getHabilitaEapMultiEmpresa();

        try {

            for (RmMaterial rmMaterial : listaRmMaterial) {

                RmMaterialStatus rmMaterialStatus = new RmMaterialStatus();
                RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);
                rmMaterialStatus = rmMaterialStatusService.listarStatusAtual(rmMaterial);

                if (rmMaterialStatus.getStatus().getCodigo().equals(Constantes.STATUS_RM_MATERIAL_AGUARDANDO_COMPRA)) {
                    //Adiciona propriedades de Hierarquia
                    propriedades.clear();

                    String aliasComprador = NxCriterion.montaAlias(HierarquiaComprador.ALIAS_CLASSE, HierarquiaComprador.COMPRADOR);
                    propriedades.add(new Propriedade(HierarquiaComprador.HIERARQUIA_COMPRADOR_ID));
                    propriedades.add(new Propriedade(HierarquiaComprador.HIERARQUIA));
                    propriedades.add(new Propriedade(HierarquiaComprador.COMPRADOR));
                    propriedades.add(new Propriedade(HierarquiaComprador.COMPRADOR_VEZ));

                    propriedades.add(new Propriedade(Comprador.COMPRADOR_ID, Comprador.class, aliasComprador));

                    NxCriterion nxCriterion = null;

                    //Se o mesmo tiver Eap Multi Empresa Habilitado, então, verifica o mesmo também ao realizar a busca do comprador
                    if (habilitaEapMultiEmpresa != null && habilitaEapMultiEmpresa == true) {
                        String aliasEapMultiEmpresa = NxCriterion.montaAlias(HierarquiaComprador.ALIAS_CLASSE, HierarquiaComprador.COMPRADOR, Comprador.EAP_MULTI_EMPRESA);

                        propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
                        propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
                        propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));

                        if (rmMaterial != null && rmMaterial.getRm() != null && rmMaterial.getRm().getEapMultiEmpresa() != null) {
                            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(EapMultiEmpresa.ID, rmMaterial.getRm().getEapMultiEmpresa().getId(), Filtro.EQUAL, aliasEapMultiEmpresa)));
                        }
                    }

                    listaAux = (List<HierarquiaComprador>) genericDao.listarByFilter(propriedades, null, HierarquiaComprador.class, Constantes.NO_LIMIT, nxCriterion);

                    //Depois de ter listado todas as hierarquias do Comprador, utilizo a mesma para verificar qual se encaixa no Start With.

                    //Ordeno a lista pelos length da hierarquia de maior para menor
                    Collections.sort(listaAux,
                            new Comparator<HierarquiaComprador>() {
                                @Override
                                public int compare(HierarquiaComprador o1, HierarquiaComprador o2) {
                                    return Integer.compare(o1.getHierarquia().length(), o2.getHierarquia().length());
                                }
                            });
                    int quantidadeCaracterAtual = 0;
                    if (!listaAux.isEmpty() && listaAux.size() >= 0) {
                        quantidadeCaracterAtual = listaAux.get(1).getHierarquia().length();
                    }
                    for (int i = listaAux.size() - 1; i >= 0; i--) {

                        if (!lista.isEmpty() && lista.size() > 0 && quantidadeCaracterAtual > listaAux.get(i).getHierarquia().length()) {
                            break;
                        }
                        if (rmMaterial.getMaterial().getHierarquia() != null) {
                            if (listaAux.get(i).getHierarquia() != null) {
                                quantidadeCaracterAtual = listaAux.get(i).getHierarquia().length();
                            }
                            if (rmMaterial.getMaterial().getHierarquia().toUpperCase().startsWith(listaAux.get(i).getHierarquia().toUpperCase())) {
                                lista.add(listaAux.get(i));
                            }
                        }


                    }

                    HierarquiaComprador primeiro = new HierarquiaComprador();
                    if (lista != null && lista.size() > 0 && !lista.isEmpty()) {
                        primeiro = lista.get(0);
                    }
                    boolean proxComprador = false;
                    HierarquiaComprador hComprador = null;

                    if (lista != null && lista.size() > 0 && !lista.isEmpty()) {
                        genericDao.beginTransaction();
                        if (lista.size() == 1) {
                            propriedades.clear();
                            propriedades.add(new Propriedade(RmMaterial.COMPRADOR));
                            propriedades.add(new Propriedade(RmMaterial.DATA_ATRIBUIR_COMPRADOR));
//                            if (rmMaterial.getRm() != null && rmMaterial.getRm().getTipoRequisicao() != null && rmMaterial.getRm().getTipoRequisicao().getCodigo() != null &&
//                                    rmMaterial.getRm().getTipoRequisicao().getCodigo().equals(Constantes.TIPO_REQUISICAO_MANUTENCAO) && rmMaterial.getNumeroRequisicaoSap() != null) {
//                                propriedades.add(new Propriedade(RmMaterial.CONCLUIDA_COMPRADOR));
//                                rmMaterial.setConcluidaComprador(true);
//                            }
                            rmMaterial.setComprador(lista.get(0).getComprador());
                            rmMaterial.setDataAtribuirComprador(new Date());
                            genericDao.updateWithCurrentTransaction(rmMaterial, propriedades);
                        } else if (lista.size() > 1) {
                            for (HierarquiaComprador hierarquiaComprador : lista) {
                                if (proxComprador) {
                                    hComprador = hierarquiaComprador;
                                    break;
                                }
                                if (hierarquiaComprador.getCompradorVez()) {
                                    proxComprador = true;
                                    propriedades.clear();
                                    propriedades.add(new Propriedade(HierarquiaComprador.COMPRADOR_VEZ));
                                    hierarquiaComprador.setCompradorVez(false);
                                    genericDao.updateWithCurrentTransaction(hierarquiaComprador, propriedades);
                                }
                            }
                            if (proxComprador) {
                                if (hComprador != null) {
                                    propriedades.clear();
                                    propriedades.add(new Propriedade(HierarquiaComprador.COMPRADOR_VEZ));
                                    hComprador.setCompradorVez(true);
                                    genericDao.updateWithCurrentTransaction(hComprador, propriedades);
                                    propriedades.clear();
                                    propriedades.add(new Propriedade(RmMaterial.COMPRADOR));
                                    propriedades.add(new Propriedade(RmMaterial.DATA_ATRIBUIR_COMPRADOR));
//                                    if (rmMaterial.getRm() != null && rmMaterial.getRm().getTipoRequisicao() != null && rmMaterial.getRm().getTipoRequisicao().getCodigo() != null &&
//                                            rmMaterial.getRm().getTipoRequisicao().getCodigo().equals(Constantes.TIPO_REQUISICAO_MANUTENCAO) && rmMaterial.getNumeroRequisicaoSap() != null) {
//                                        propriedades.add(new Propriedade(RmMaterial.CONCLUIDA_COMPRADOR));
//                                        rmMaterial.setConcluidaComprador(true);
//                                    }
                                    rmMaterial.setComprador(hComprador.getComprador());
                                    rmMaterial.setDataAtribuirComprador(new Date());
                                    genericDao.updateWithCurrentTransaction(rmMaterial, propriedades);
                                } else {
                                    hComprador = primeiro;
                                    propriedades.clear();
                                    propriedades.add(new Propriedade(HierarquiaComprador.COMPRADOR_VEZ));
                                    hComprador.setCompradorVez(true);
                                    genericDao.updateWithCurrentTransaction(hComprador, propriedades);
                                    propriedades.clear();
                                    propriedades.add(new Propriedade(RmMaterial.COMPRADOR));
                                    propriedades.add(new Propriedade(RmMaterial.DATA_ATRIBUIR_COMPRADOR));
//                                    if (rmMaterial.getRm() != null && rmMaterial.getRm().getTipoRequisicao() != null && rmMaterial.getRm().getTipoRequisicao().getCodigo() != null &&
//                                            rmMaterial.getRm().getTipoRequisicao().getCodigo().equals(Constantes.TIPO_REQUISICAO_MANUTENCAO) && rmMaterial.getNumeroRequisicaoSap() != null) {
//                                        propriedades.add(new Propriedade(RmMaterial.CONCLUIDA_COMPRADOR));
//                                        rmMaterial.setConcluidaComprador(true);
//                                    }
                                    rmMaterial.setComprador(hComprador.getComprador());
                                    rmMaterial.setDataAtribuirComprador(new Date());
                                    genericDao.updateWithCurrentTransaction(rmMaterial, propriedades);
                                }
                            } else if (!proxComprador) {
                                hComprador = primeiro;
                                propriedades.clear();
                                propriedades.add(new Propriedade(HierarquiaComprador.COMPRADOR_VEZ));
                                hComprador.setCompradorVez(true);
                                genericDao.updateWithCurrentTransaction(hComprador, propriedades);
                                propriedades.clear();
                                propriedades.add(new Propriedade(RmMaterial.COMPRADOR));
                                propriedades.add(new Propriedade(RmMaterial.DATA_ATRIBUIR_COMPRADOR));
//                                if (rmMaterial.getRm() != null && rmMaterial.getRm().getTipoRequisicao() != null && rmMaterial.getRm().getTipoRequisicao().getCodigo() != null &&
//                                        rmMaterial.getRm().getTipoRequisicao().getCodigo().equals(Constantes.TIPO_REQUISICAO_MANUTENCAO) && rmMaterial.getNumeroRequisicaoSap() != null) {
//                                    propriedades.add(new Propriedade(RmMaterial.CONCLUIDA_COMPRADOR));
//                                    rmMaterial.setConcluidaComprador(true);
//                                }
                                rmMaterial.setComprador(hComprador.getComprador());
                                rmMaterial.setDataAtribuirComprador(new Date());
                                genericDao.updateWithCurrentTransaction(rmMaterial, propriedades);
                            }
                        }

                        genericDao.commitCurrentTransaction();
                    }
                }
            }
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setMensagem(Constantes.ERRO_OPERACAO_I18N);
        }
        return info;
    }
    
    
    //HM
    public Info verificaHierarquiaServico(List<RmServico> listaRmServico) {

        List<Propriedade> propriedades = new ArrayList<>();
        List<HierarquiaComprador> lista = new ArrayList<>();
        List<HierarquiaComprador> listaAux = new ArrayList<>();
        List<Comprador> listaComprador = new ArrayList<>();
        Info info = new Info();
        GenericDao<RmServico> genericDaoRmServico = new GenericDao<>();
        GenericDao genericDao = new GenericDao();

        Boolean habilitaEapMultiEmpresa = ConfiguracaoSingleton.getConfiguracao().getHabilitaEapMultiEmpresa();

        try {

            for (RmServico rmServico : listaRmServico) {

                RmServicoStatus rmServicoStatus = new RmServicoStatus();
                RmServicoStatusService rmServicoStatusService = new RmServicoStatusService(request);
                rmServicoStatus = rmServicoStatusService.listarStatusAtual(rmServico);

                if (rmServicoStatus.getStatus().getCodigo().equals(Constantes.STATUS_RM_MATERIAL_AGUARDANDO_COMPRA)) {
                    //Adiciona propriedades de Hierarquia
                    propriedades.clear();

                    String aliasComprador = NxCriterion.montaAlias(HierarquiaComprador.ALIAS_CLASSE, HierarquiaComprador.COMPRADOR);
                    propriedades.add(new Propriedade(HierarquiaComprador.HIERARQUIA_COMPRADOR_ID));
                    propriedades.add(new Propriedade(HierarquiaComprador.HIERARQUIA));
                    propriedades.add(new Propriedade(HierarquiaComprador.COMPRADOR));
                    propriedades.add(new Propriedade(HierarquiaComprador.COMPRADOR_VEZ));

                    propriedades.add(new Propriedade(Comprador.COMPRADOR_ID, Comprador.class, aliasComprador));

                    NxCriterion nxCriterion = null;

                    //Se o mesmo tiver Eap Multi Empresa Habilitado, então, verifica o mesmo também ao realizar a busca do comprador
                    if (habilitaEapMultiEmpresa != null && habilitaEapMultiEmpresa == true) {
                        String aliasEapMultiEmpresa = NxCriterion.montaAlias(HierarquiaComprador.ALIAS_CLASSE, HierarquiaComprador.COMPRADOR, Comprador.EAP_MULTI_EMPRESA);

                        propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
                        propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
                        propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));

                        if (rmServico != null && rmServico.getRm() != null && rmServico.getRm().getEapMultiEmpresa() != null) {
                            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(EapMultiEmpresa.ID, rmServico.getRm().getEapMultiEmpresa().getId(), Filtro.EQUAL, aliasEapMultiEmpresa)));
                        }
                    }

                    listaAux = (List<HierarquiaComprador>) genericDao.listarByFilter(propriedades, null, HierarquiaComprador.class, Constantes.NO_LIMIT, nxCriterion);

                    //Depois de ter listado todas as hierarquias do Comprador, utilizo a mesma para verificar qual se encaixa no Start With.

                    //Ordeno a lista pelos length da hierarquia de maior para menor
                    Collections.sort(listaAux,
                            new Comparator<HierarquiaComprador>() {
                                @Override
                                public int compare(HierarquiaComprador o1, HierarquiaComprador o2) {
                                    return Integer.compare(o1.getHierarquia().length(), o2.getHierarquia().length());
                                }
                            });
                    int quantidadeCaracterAtual = 0;
                    if (!listaAux.isEmpty() && listaAux.size() >= 0) {
                        quantidadeCaracterAtual = listaAux.get(1).getHierarquia().length();
                    }
                    for (int i = listaAux.size() - 1; i >= 0; i--) {

                        if (!lista.isEmpty() && lista.size() > 0 && quantidadeCaracterAtual > listaAux.get(i).getHierarquia().length()) {
                            break;
                        }
                        /*if (rmServico.getServico().getHierarquia() != null) {
                            if (listaAux.get(i).getHierarquia() != null) {
                                quantidadeCaracterAtual = listaAux.get(i).getHierarquia().length();
                            }
                            if (rmServico.getServico().getHierarquia().toUpperCase().startsWith(listaAux.get(i).getHierarquia().toUpperCase())) {
                                lista.add(listaAux.get(i));
                            }
                        }*/


                    }

                    HierarquiaComprador primeiro = new HierarquiaComprador();
                    if (lista != null && lista.size() > 0 && !lista.isEmpty()) {
                        primeiro = lista.get(0);
                    }
                    boolean proxComprador = false;
                    HierarquiaComprador hComprador = null;

                    if (lista != null && lista.size() > 0 && !lista.isEmpty()) {
                        genericDao.beginTransaction();
                        if (lista.size() == 1) {
                            propriedades.clear();
                            //propriedades.add(new Propriedade(RmServico.COMPRADOR));
                            //propriedades.add(new Propriedade(RmServico.DATA_ATRIBUIR_COMPRADOR));
//                            if (rmServico.getRm() != null && rmServico.getRm().getTipoRequisicao() != null && rmServico.getRm().getTipoRequisicao().getCodigo() != null &&
//                                    rmServico.getRm().getTipoRequisicao().getCodigo().equals(Constantes.TIPO_REQUISICAO_MANUTENCAO) && rmServico.getNumeroRequisicaoSap() != null) {
//                                propriedades.add(new Propriedade(RmServico.CONCLUIDA_COMPRADOR));
//                                rmServico.setConcluidaComprador(true);
//                            }
                            //rmServico.setComprador(lista.get(0).getComprador());
                            //rmServico.setDataAtribuirComprador(new Date());
                            genericDao.updateWithCurrentTransaction(rmServico, propriedades);
                        } else if (lista.size() > 1) {
                            for (HierarquiaComprador hierarquiaComprador : lista) {
                                if (proxComprador) {
                                    hComprador = hierarquiaComprador;
                                    break;
                                }
                                if (hierarquiaComprador.getCompradorVez()) {
                                    proxComprador = true;
                                    propriedades.clear();
                                    propriedades.add(new Propriedade(HierarquiaComprador.COMPRADOR_VEZ));
                                    hierarquiaComprador.setCompradorVez(false);
                                    genericDao.updateWithCurrentTransaction(hierarquiaComprador, propriedades);
                                }
                            }
                            if (proxComprador) {
                                if (hComprador != null) {
                                    propriedades.clear();
                                    propriedades.add(new Propriedade(HierarquiaComprador.COMPRADOR_VEZ));
                                    hComprador.setCompradorVez(true);
                                    genericDao.updateWithCurrentTransaction(hComprador, propriedades);
                                    propriedades.clear();
                                    //propriedades.add(new Propriedade(RmServico.COMPRADOR));
                                    //propriedades.add(new Propriedade(RmServico.DATA_ATRIBUIR_COMPRADOR));
//                                    if (rmServico.getRm() != null && rmServico.getRm().getTipoRequisicao() != null && rmServico.getRm().getTipoRequisicao().getCodigo() != null &&
//                                            rmServico.getRm().getTipoRequisicao().getCodigo().equals(Constantes.TIPO_REQUISICAO_MANUTENCAO) && rmServico.getNumeroRequisicaoSap() != null) {
//                                        propriedades.add(new Propriedade(RmServico.CONCLUIDA_COMPRADOR));
//                                        rmServico.setConcluidaComprador(true);
//                                    }
                                    //rmServico.setComprador(hComprador.getComprador());
                                    //rmServico.setDataAtribuirComprador(new Date());
                                    genericDao.updateWithCurrentTransaction(rmServico, propriedades);
                                } else {
                                    hComprador = primeiro;
                                    propriedades.clear();
                                    propriedades.add(new Propriedade(HierarquiaComprador.COMPRADOR_VEZ));
                                    hComprador.setCompradorVez(true);
                                    genericDao.updateWithCurrentTransaction(hComprador, propriedades);
                                    propriedades.clear();
                                    //propriedades.add(new Propriedade(RmServico.COMPRADOR));
                                    //propriedades.add(new Propriedade(RmServico.DATA_ATRIBUIR_COMPRADOR));
//                                    if (rmServico.getRm() != null && rmServico.getRm().getTipoRequisicao() != null && rmServico.getRm().getTipoRequisicao().getCodigo() != null &&
//                                            rmServico.getRm().getTipoRequisicao().getCodigo().equals(Constantes.TIPO_REQUISICAO_MANUTENCAO) && rmServico.getNumeroRequisicaoSap() != null) {
//                                        propriedades.add(new Propriedade(RmServico.CONCLUIDA_COMPRADOR));
//                                        rmServico.setConcluidaComprador(true);
//                                    }
                                    //rmServico.setComprador(hComprador.getComprador());
                                    //rmServico.setDataAtribuirComprador(new Date());
                                    genericDao.updateWithCurrentTransaction(rmServico, propriedades);
                                }
                            } else if (!proxComprador) {
                                hComprador = primeiro;
                                propriedades.clear();
                                propriedades.add(new Propriedade(HierarquiaComprador.COMPRADOR_VEZ));
                                hComprador.setCompradorVez(true);
                                genericDao.updateWithCurrentTransaction(hComprador, propriedades);
                                propriedades.clear();
                                //propriedades.add(new Propriedade(RmServico.COMPRADOR));
                                //propriedades.add(new Propriedade(RmServico.DATA_ATRIBUIR_COMPRADOR));
//                                if (rmServico.getRm() != null && rmServico.getRm().getTipoRequisicao() != null && rmServico.getRm().getTipoRequisicao().getCodigo() != null &&
//                                        rmServico.getRm().getTipoRequisicao().getCodigo().equals(Constantes.TIPO_REQUISICAO_MANUTENCAO) && rmServico.getNumeroRequisicaoSap() != null) {
//                                    propriedades.add(new Propriedade(RmServico.CONCLUIDA_COMPRADOR));
//                                    rmServico.setConcluidaComprador(true);
//                                }
                                //rmServico.setComprador(hComprador.getComprador());
                                //rmServico.setDataAtribuirComprador(new Date());
                                genericDao.updateWithCurrentTransaction(rmServico, propriedades);
                            }
                        }

                        genericDao.commitCurrentTransaction();
                    }
                }
            }
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setMensagem(Constantes.ERRO_OPERACAO_I18N);
        }
        return info;
    }
    
    

    private Info finalizaFrenteServico(RmMaterial rmMaterial) {
        GenericDao<RmMaterialRetirada> genericDao = new GenericDao<>();
        Info info = new Info();
        VwRmMaterial vwRmMaterial = selectVwRmMaterial(rmMaterial);
        RmMaterial rmMaterialVw = vwRmMaterial.getRmMaterial();
        String confCaminhoUrl = ConfiguracaoSingleton.getConfiguracao().getCaminhoUrlCpweb();
        List<SincEquipamentoVo> lista = new ArrayList<>();

        try {
            // Somente material cautelavel vai para o CPWEB
            if (rmMaterial != null
                    && rmMaterial.getMaterial() != null
                    && rmMaterial.getMaterial().getTipoMaterial() != null
                    && rmMaterial.getMaterial().getTipoMaterial().getCodigo() != null
                    && rmMaterial.getMaterial().getTipoMaterial().getCodigo().equals(Constantes.CAUTELAVEL)
                    && rmMaterial.getQuantidade() > 0.0) {

                SincEquipamentoVo equipamentoVo = new SincEquipamentoVo();
                // Verifico se o equipamento é EPI ou não
                String isEpi = isEpi(rmMaterial) ? Constantes.SIM_ABREVIADO : Constantes.NAO_ABREVIADO;
                equipamentoVo.setEpi(isEpi);

                //Configura TipoEquipamento
                TipoEquipamento tipoEquipamento = new TipoEquipamento();
                tipoEquipamento.setDescricao(rmMaterial.getMaterial().getNome());
                tipoEquipamento.setCodigo(rmMaterial.getMaterial().getCodigo());
                equipamentoVo.setTipoEquipamento(tipoEquipamento);
                equipamentoVo.setPatrimoniado(rmMaterial.getMaterial().getPatrimoniado());
                equipamentoVo.setEncarSolicitaRmMobile(true);
                if (rmMaterial.getPrefixoEquipamento() != null) {
                    equipamentoVo.setPrefixoEquipamento(rmMaterial.getPrefixoEquipamento());
                }

                // Pessoa responsável
                if (rmMaterial.getPessoaResponsavel() != null && !rmMaterial.getPessoaResponsavel().getReferencia().equals(rmMaterial.getCfResponsavel())) {
                    equipamentoVo.setReferenciaCorresponsavel(rmMaterial.getPessoaResponsavel().getReferencia());
                } else if (rmMaterial.getCfResponsavel() != null && !rmMaterial.getCfResponsavel().isEmpty()) {
                    equipamentoVo.setReferenciaCorresponsavel(rmMaterial.getCfResponsavel());
                }

                if (rmMaterial.getRm().getRequisitante() != null) {
                    equipamentoVo.setReferenciaPessoa(rmMaterial.getRm().getRequisitante().getReferencia());
                }

                //Configura Estoque
                LocalEstoque estoque = new LocalEstoque();
                estoque.setDescricao(rmMaterial.getDeposito().getNome());
                estoque.setCodigo(rmMaterial.getDeposito().getCodigo());
                equipamentoVo.setLocalEstoque(estoque);

                //Configura Quantidade
                equipamentoVo.setQuantidade(rmMaterial.getQuantidade());

                //Configura Unidade Medida
                br.com.nextage.rmaweb.ws.cpweb.UnidadeMedida unidadeMedida = new br.com.nextage.rmaweb.ws.cpweb.UnidadeMedida();
                unidadeMedida.setDescricao(rmMaterial.getMaterial().getUnidadeMedida().getDescricao());
                equipamentoVo.setUnidadeMedida(unidadeMedida);

                if (rmMaterial.getRm() != null && rmMaterial.getRm().getNumeroRm() != null) {
                    equipamentoVo.setCodigoRequisicao(rmMaterial.getRm().getNumeroRm());
                }

                if (rmMaterial.getRmMaterialId() != null) {
                    equipamentoVo.setIdentificadorRmMaterial(rmMaterial.getRmMaterialId());
                }

                Boolean habilitaEapMultiEmpresa = ConfiguracaoSingleton.getConfiguracao().getHabilitaEapMultiEmpresa();
                if (habilitaEapMultiEmpresa != null && habilitaEapMultiEmpresa == true) {

                    if (rmMaterial.getRm() != null && rmMaterial.getRm().getRequisitante() != null) {
                        equipamentoVo.setFuncionarioEap(obterCodigoEapMultiEmpresaPessoa(rmMaterial.getRm().getRequisitante().getPessoaId()));
                    }

                    if (rmMaterial.getDeposito() != null) {
                        equipamentoVo.getLocalEstoque().setLocalEstoqueEap(obterCodigoEapMultiEmpresaDeposito(rmMaterial.getDeposito().getDepositoId()));
                    }
                }

                lista.add(equipamentoVo);

                // Sincroniza com o CPWEB

                // Limpa as propriedades do TOMCAT
                // Pois a sincronização com o SAP seta estas propriedades
                // E quando o sistema necessita fazer outra requisição, o mesmo
                // tenta acessar o proxy que era setado, causando erro
                System.setProperty("proxySet", "false");
                System.clearProperty("proxySet");
                System.clearProperty("http.proxyHost");
                System.clearProperty("http.proxyPort");
                System.clearProperty("http.proxyUser");
                System.clearProperty("http.proxyPassword");

                SincEstoque_Service sincEstoque = new SincEstoque_Service(new URL(confCaminhoUrl));
                br.com.nextage.rmaweb.ws.cpweb.Info infoCp = sincEstoque.getSincEstoquePort().gerarCautela(lista);

                if (infoCp != null) {
                    info.setCodigo(infoCp.getCodigo());
                    info.setErro(infoCp.getErro());
                    info.setMensagem(infoCp.getMensagem());
                    info.setTipo(Info.TIPO_MSG_DANGER);
                    if (info.getErro() != null) {
                        SincRegistroService sincRegistroService = new SincRegistroService();
                        sincRegistroService.salvar(info, Constantes.SINC_GERA_CAUTELA, Constantes.SISTEMA_CPWEB);
                    }
                }
            } else {
                info.setCodigo(Info.INFO_002);
                info.setTipo(Info.TIPO_MSG_DANGER);
                info.setMensagem(Constantes.ERRO_OPERACAO_I18N);
            }
        } catch (Exception e) {
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setMensagem(Constantes.ERRO_OPERACAO_I18N);
        }
        try {
            info.setObjeto(rmMaterial);
            //Recupera logs de retorno.
            LogInterfaceVo logInterfaceVo = new LogInterfaceVo(info);
            //Gerando Log de interface
            LogInterfaceService.inserirLog(Constantes.SISTEMA_RMAWEB,
                    Constantes.INTERFACE_FINALIZA_FRENTE, LoginService.getUsuarioLogado(request).getNome(), logInterfaceVo);

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError("",
                    this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            e.printStackTrace();
        }
        return info;
    }

    private String obterCodigoEapMultiEmpresaPessoa(Integer pessoaId) {

        if (pessoaId == null) {
            return null;
        }

        GenericDao<Pessoa> genericDao = new GenericDao<>();

        Pessoa pessoa = new Pessoa();

        String aliasEapMultiEmpresa = NxCriterion.montaAlias(Pessoa.ALIAS_CLASSE, Pessoa.EAP_MULTI_EMPRESA);

        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
        propriedades.add(new Propriedade(Pessoa.NOME));
        propriedades.add(new Propriedade(Pessoa.REFERENCIA));

        propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
        propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
        propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));

        NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, pessoaId, Filtro.EQUAL));

        try {
            pessoa = genericDao.selectUnique(propriedades, Pessoa.class, nxCriterion);
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        if (pessoa != null && pessoa.getEapMultiEmpresa() != null && pessoa.getEapMultiEmpresa().getCodigo() != null) {
            return pessoa.getEapMultiEmpresa().getCodigo();
        }

        return null;
    }

    private String obterCodigoEapMultiEmpresaDeposito(Integer depositoId) {

        GenericDao<Deposito> genericDao = new GenericDao<>();

        if (depositoId == null) {
            return null;
        }

        Deposito deposito = new Deposito();

        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));
        propriedades.add(new Propriedade(Deposito.NOME));
        propriedades.add(new Propriedade(Deposito.CODIGO));

        NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Deposito.DEPOSITO_ID, depositoId, Filtro.EQUAL));

        try {
            deposito = genericDao.selectUnique(propriedades, Deposito.class, nxCriterion);
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        if (deposito != null && deposito.getEapMultiEmpresa() != null && deposito.getEapMultiEmpresa().getCodigo() != null) {
            return deposito.getEapMultiEmpresa().getCodigo();
        }

        return null;
    }

    private VwRmMaterial selectVwRmMaterial(RmMaterial rmMaterial) {
        VwRmMaterial vwRmMaterial = new VwRmMaterial();
        try {

            List<Propriedade> propriedades = new ArrayList<>();

            String aliasRmMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID);
            String aliasRmMaterialStatus = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_STATUS_ID);
            String aliasStatus = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_STATUS_ID, RmMaterialStatus.STATUS_ID);
            String aliasRm = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM);
            String aliasMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.MATERIAL);
            String aliasRequisitante = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.REQUISITANTE);
            String aliasUsuario = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.USUARIO);
            String aliasRmMaterialDeposito = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.DEPOSITO_ID);
            String aliasTipoMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.MATERIAL, Material.TIPO_MATERIAL);
            String aliasUnidadeMedida = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.MATERIAL, Material.UNIDADE_MEDIDA);

            //VIEW
            propriedades.add(new Propriedade(VwRmMaterial.ID));

            //RM MATERIAL
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE_ORIGINAL, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.LOCAL_ENTREGA, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.PROTOCOLO_RESPONSABILIDADE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DOCUMENTO_RESPONSAVEL_IMPRESSO, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.CF_RESPONSAVEL, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.PREFIXO_EQUIPAMENTO, RmMaterial.class, aliasRmMaterial));

            //RM MATERIAL STATUS
            propriedades.add(new Propriedade(RmMaterialStatus.ID, RmMaterialStatus.class, aliasRmMaterialStatus));
            propriedades.add(new Propriedade(RmMaterialStatus.OBSERVACAO, RmMaterialStatus.class, aliasRmMaterialStatus));

            //STATUS
            propriedades.add(new Propriedade(Status.ID, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.NOME, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.CODIGO, Status.class, aliasStatus));

            //RM
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM_MOBILE, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_EMISSAO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_RECEBIMENTO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.LOCAL_ENTREGA, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.OBSERVACAO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.PERIODO, Rm.class, aliasRm));

            //MATERIAL
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.HIERARQUIA, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.PATRIMONIADO, Material.class, aliasMaterial));

            //UNIDADE MEDIDA
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            //TIPO MATERIAL
            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.DESCRICAO, TipoMaterial.class, aliasTipoMaterial));

            //REQUISITANTE
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasRequisitante));

            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasRmMaterialDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasRmMaterialDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasRmMaterialDeposito));

            //USUARIO
            propriedades.add(new Propriedade(Usuario.USUARIO_ID, Usuario.class, aliasUsuario));
            propriedades.add(new Propriedade(Usuario.NOME, Usuario.class, aliasUsuario));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.FLUXO_MATERIAL, Constantes.FLUXO_MATERIAL_CAMPO, Filtro.EQUAL, aliasRmMaterial));
            NxCriterion nxCriterionAux;

            if (rmMaterial.getRmMaterialId() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM_MATERIAL_ID, rmMaterial.getRmMaterialId(), Filtro.EQUAL, aliasRmMaterial));
                if (nxCriterion == null) {
                    nxCriterion = nxCriterionAux;
                } else {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                }
            }

            GenericDao<VwRmMaterial> genericDao = new GenericDao<>();
            vwRmMaterial = genericDao.selectUnique(propriedades, VwRmMaterial.class, nxCriterion);
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return vwRmMaterial;
    }

    public Pessoa getPessoaByCF(String cfResponsavel) {
        Pessoa p = null;
        try {
            List<Propriedade> lista = new ArrayList<>();
            lista.add(new Propriedade(Pessoa.PESSOA_ID));
            lista.add(new Propriedade(Pessoa.NOME));
            lista.add(new Propriedade(Pessoa.REFERENCIA));
            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.REFERENCIA, cfResponsavel, Filtro.EQUAL));

            GenericDao<Pessoa> dao = new GenericDao<>();

            List<Pessoa> listaPessoa = new ArrayList<>();
            listaPessoa = dao.listarByFilter(lista, null, Pessoa.class, 1, nxCriterion);
            if (listaPessoa != null && !listaPessoa.isEmpty()) {
                p = listaPessoa.get(0);
            }

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return p;
    }

    //Recupera a RmMaterialRetirada de acordo com uma rmMaterialId, o mesmo é utilizado
    //Quando vem do cp para finalizar ou verificar as quantidades de quando o mesmo é feito para
    //Cpb
    public List<RmMaterialRetirada> recuperarRmRetirada(Integer rmMaterialId) {
        GenericDao<RmMaterialRetirada> genericDao = new GenericDao<>();
        List<RmMaterialRetirada> listaRmMaterialRetirada = new ArrayList<>();

        try {
            List<Propriedade> propriedades = new ArrayList<>();

            String aliasRmMaterial = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RM_MATERIAL);

            //VIEW
            propriedades.add(new Propriedade(RmMaterialRetirada.RM_MATERIAL_RETIRADA_ID));
            propriedades.add(new Propriedade(RmMaterialRetirada.QUANTIDADE));

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM_MATERIAL_ID, rmMaterialId, Filtro.EQUAL, aliasRmMaterial));

            listaRmMaterialRetirada = genericDao.listarByFilter(propriedades, null, RmMaterialRetirada.class, Constantes.NO_LIMIT, nxCriterion);

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return listaRmMaterialRetirada;
    }

    public Deposito recuperarDeposito(String codigoDeposito) {
        GenericDao<Deposito> genericDao = new GenericDao<>();
        Deposito deposito = new Deposito();

        try {
            List<Propriedade> propriedades = new ArrayList<>();

            //VIEW
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));
            propriedades.add(new Propriedade(Deposito.CODIGO));
            propriedades.add(new Propriedade(Deposito.NOME));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Deposito.CODIGO, codigoDeposito, Filtro.EQUAL));

            deposito = genericDao.selectUnique(propriedades, Deposito.class, nxCriterion);

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return deposito;
    }


    public RmMaterial recuperarRmMaterial(Integer rmMaterialId) {
        GenericDao<RmMaterial> genericDao = new GenericDao<>();
        RmMaterial rmMaterial = new RmMaterial();

        try {
            List<Propriedade> propriedades = new ArrayList<>();

            String aliasMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL);
            String aliasUnidadeMedida = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL, Material.UNIDADE_MEDIDA);
            String aliasRm = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);
            String aliasPessoaResponsavel = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.PESSOA_RESPONSAVEL);

            //VIEW
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
            propriedades.add(new Propriedade(RmMaterial.CF_RESPONSAVEL));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE_ORIGINAL));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_CUSTOS));
            propriedades.add(new Propriedade(RmMaterial.DIAGRAMA_REDE));
            propriedades.add(new Propriedade(RmMaterial.OPERACAO));
            propriedades.add(new Propriedade(RmMaterial.LOCAL_ENTREGA));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO));

            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.LOCAL_ENTREGA, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.OBSERVACAO, Rm.class, aliasRm));

            //Pessoa Responsavel
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasPessoaResponsavel));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasPessoaResponsavel));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasPessoaResponsavel));

            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.PATRIMONIADO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME_COMPLETO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));

            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM_MATERIAL_ID, rmMaterialId, Filtro.EQUAL));

            rmMaterial = genericDao.selectUnique(propriedades, RmMaterial.class, nxCriterion);
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return rmMaterial;
    }

    /**
     * Fornece uma lista de depósitos sem considerar os temporários
     **/
    public List<Deposito> listarDepositosNaoTemporario() {
        List<Deposito> depositos = new ArrayList<>();

        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));
            propriedades.add(new Propriedade(Deposito.CODIGO));
            propriedades.add(new Propriedade(Deposito.NOME));
            propriedades.add(new Propriedade(Deposito.OBSERVACAO));

            GenericDao<Deposito> genericDao = new GenericDao<>();

            NxCriterion nc = NxCriterion.montaRestriction(new Filtro(Deposito.IS_TEMPORARIO, false, Filtro.EQUAL));
            nc = NxCriterion.or(nc, NxCriterion.montaRestriction(new Filtro(Deposito.IS_TEMPORARIO, null, Filtro.IS_NULL)));

            depositos = genericDao.listarByFilter(propriedades, null, Deposito.class, Constantes.NO_LIMIT, nc);
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return depositos;
    }

    /**
     * Adicionado campo prioridade
     *
     * @param rmId
     * @return
     * @throws Exception
     */
    public Rm getRmEapMultiempresa(Integer rmId)  {
        List<Propriedade> propriedades = new ArrayList<>();
        Rm obj = new Rm();
        try {
            NxCriterion nc = NxCriterion.montaRestriction(new Filtro(Rm.RM_ID, rmId, Filtro.EQUAL));

            propriedades.add(new Propriedade(Rm.RM_ID));

            String aliasEap = NxCriterion.montaAlias(Rm.ALIAS_CLASSE,Rm.EAP_MULTI_EMPRESA);
            propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEap));
            propriedades.add(new Propriedade(EapMultiEmpresa.APROVACAO_EQUIPE_CUSTOS, EapMultiEmpresa.class, aliasEap));
            propriedades.add(new Propriedade(EapMultiEmpresa.APROVACAO_COORDENADOR, EapMultiEmpresa.class, aliasEap));

            GenericDao<Rm> genericDao = new GenericDao<>();

            obj = (Rm) genericDao.selectUnique(propriedades, Rm.class,nc);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }


}
