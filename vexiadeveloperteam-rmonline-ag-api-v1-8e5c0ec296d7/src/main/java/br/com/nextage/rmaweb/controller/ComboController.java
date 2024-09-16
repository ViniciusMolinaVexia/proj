package br.com.nextage.rmaweb.controller;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.dao.TipoRequisicaoDAO;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.service.DepositoService;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.RmService;
import br.com.nextage.rmaweb.service.RmServicoService;
import br.com.nextage.rmaweb.service.UsuarioService;
import br.com.nextage.rmaweb.service.integracao.AreaService;
import br.com.nextage.rmaweb.service.integracao.CentroService;
import br.com.nextage.rmaweb.service.integracao.EapMultiEmpresaService;
import br.com.nextage.rmaweb.service.integracao.RmaService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author nextage
 */
@Path("ComboController")
public class ComboController {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);
    
    private CentroService centroService = CentroService.getInstance();
    private TipoRequisicaoDAO tipoRequisicaoDAO = TipoRequisicaoDAO.getInstance();

    @Context
    HttpServletRequest request;

    /**
     * Lista as pessoas em que o tipoAtuacao seja igual a GC(gerente de custos)
     *
     * @return
     */
    @POST
    @Path("listarGerentesCustos")
    @Consumes("application/json")
    public List<Pessoa> listarGerentesCustos(Integer eapId) {
        List<Pessoa> lista = null;
        try {

            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(Pessoa.NOME, NxOrder.NX_ORDER.ASC));

            String aliasEapMultiEmpresa = NxCriterion.montaAlias(Pessoa.ALIAS_CLASSE, Pessoa.EAP_MULTI_EMPRESA);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
            propriedades.add(new Propriedade(Pessoa.NOME));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA));
            propriedades.add(new Propriedade(Pessoa.EMAIL));

            //Se caso a pessoa tiver habilitado eap multi empresa, então o mesmo é listado para ser exibido junto no autoComplete
            Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
            if (conf.getHabilitaEapMultiEmpresa() != null && conf.getHabilitaEapMultiEmpresa() == true) {
                //EAP MULTI EMPRESA
                propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
                propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
                propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            }

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.TIPO_ATUACAO, Constantes.TIPO_ATUACAO_GERENTE_CUSTO, Filtro.LIKE));

            if (conf.getHabilitaEapMultiEmpresa() != null && conf.getHabilitaEapMultiEmpresa() == true && eapId != null) {
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(EapMultiEmpresa.ID, eapId, Filtro.EQUAL, aliasEapMultiEmpresa)));
            }

            GenericDao<Pessoa> dao = new GenericDao<>();
            lista = dao.listarByFilter(propriedades, nxOrders, Pessoa.class, Constantes.NO_LIMIT, nxCriterion);

            if (conf.getHabilitaEapMultiEmpresa() != null && conf.getHabilitaEapMultiEmpresa() == true) {
                for (Pessoa pessoa : lista) {
                    if (pessoa.getEapMultiEmpresa() != null && pessoa.getEapMultiEmpresa().getDescricao() != null) {
                        pessoa.setNome(pessoa.getNome() + " - " + pessoa.getEapMultiEmpresa().getDescricao());
                    }
                }
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    /**
     * Lista as pessoas em que o tipoAtuacao seja igual a GO(gerente de obra)
     *
     * @return
     */
    @POST
    @Path("listarGerentesObra")
    @Consumes("application/json")
    public List<Pessoa> listarGerentesObra() {
        List<Pessoa> lista = null;
        try {

            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(Pessoa.NOME, NxOrder.NX_ORDER.ASC));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
            propriedades.add(new Propriedade(Pessoa.NOME));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA));
            propriedades.add(new Propriedade(Pessoa.EMAIL));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.TIPO_ATUACAO, Constantes.TIPO_ATUACAO_GERENTE_OBRA, Filtro.LIKE));

            GenericDao<Pessoa> dao = new GenericDao<>();
            lista = dao.listarByFilter(propriedades, nxOrders, Pessoa.class, Constantes.NO_LIMIT, nxCriterion);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    /**
     * Lista combo de prioridade
     *
     * @return
     */
    @POST
    @Path("listarPrioridades")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prioridade> listarPrioridade() {
        List<Prioridade> list = new ArrayList<>();

        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Prioridade.PRIORIDADE_ID));
            propriedades.add(new Propriedade(Prioridade.CODIGO));
            propriedades.add(new Propriedade(Prioridade.DESCRICAO));
            propriedades.add(new Propriedade(Prioridade.CONF_DIAS_PREV_ENTREGA));

            List<NxOrder> orders = new ArrayList<>();
            orders.add(new NxOrder(Prioridade.PRIORIDADE_ID, NxOrder.NX_ORDER.DESC));

            GenericDao<Prioridade> genericDao = new GenericDao<>();

            list = genericDao.listarByFilter(propriedades, orders, Prioridade.class, Constantes.NO_LIMIT, null);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return list;
    }

    /**
     * Lista combo de prioridade
     *
     * @return
     */
    @POST
    @Path("listarTipoRequisicao")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoRequisicao> listarTipoRequisicao() {
    	return tipoRequisicaoDAO.getAll();
    }
    
    /**
     * Lista combo de depositos
     *
     * @return
     */
    @POST
    @Path("listarDepositos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Deposito> listarDepositos() {
        List<Deposito> depositos = new ArrayList<>();

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), null, null));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));
            propriedades.add(new Propriedade(Deposito.CODIGO));
            propriedades.add(new Propriedade(Deposito.ENDERECO));
            propriedades.add(new Propriedade(Deposito.NOME));
            propriedades.add(new Propriedade(Deposito.CODIGO));
            propriedades.add(new Propriedade(Deposito.OBSERVACAO));
            propriedades.add(new Propriedade(Deposito.TELEFONE));

            GenericDao<Deposito> genericDao = new GenericDao<>();

            NxCriterion nc = NxCriterion.montaRestriction(new Filtro(Deposito.IS_TEMPORARIO, false, Filtro.EQUAL));
            nc = NxCriterion.or(nc, NxCriterion.montaRestriction(new Filtro(Deposito.IS_TEMPORARIO, null, Filtro.IS_NULL)));

            depositos = genericDao.listarByFilter(propriedades, null, Deposito.class, Constantes.NO_LIMIT, nc);


            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return depositos;
    }

    /**
     * Lista combo de depositos temporarios
     *
     * @return
     */
    @POST
    @Path("listarDepositosTemporarios")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Deposito> listarDepositosTemporarios() {
        List<Deposito> depositos = new ArrayList<>();

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), null, null));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));
            propriedades.add(new Propriedade(Deposito.CODIGO));
            propriedades.add(new Propriedade(Deposito.ENDERECO));
            propriedades.add(new Propriedade(Deposito.NOME));
            propriedades.add(new Propriedade(Deposito.OBSERVACAO));
            propriedades.add(new Propriedade(Deposito.TELEFONE));

            GenericDao<Deposito> genericDao = new GenericDao<>();

            NxCriterion nc = NxCriterion.montaRestriction(new Filtro(Deposito.IS_TEMPORARIO, true, Filtro.EQUAL));

            depositos = genericDao.listarByFilter(propriedades, null, Deposito.class, Constantes.NO_LIMIT, nc);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return depositos;
    }

    /**
     * Lista combo de unidade medida
     *
     * @return
     */
    @POST
    @Path("listarUnidadeMedida")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UnidadeMedida> listarUnidadeMedida() {
        List<UnidadeMedida> listaUnidadeMedida = new ArrayList<>();

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO));

            GenericDao<UnidadeMedida> genericDao = new GenericDao<>();

            listaUnidadeMedida = genericDao.listarByFilter(propriedades, null, UnidadeMedida.class, Constantes.NO_LIMIT, null);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return listaUnidadeMedida;
    }

    /**
     * Lista combo de unidade medida
     *
     * @return
     */
    @POST
    @Path("listarStatus")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Status> listarStatus() {
        List<Status> listaStatus = new ArrayList<>();

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Status.ID));
            propriedades.add(new Propriedade(Status.CODIGO));
            propriedades.add(new Propriedade(Status.NOME));

            GenericDao<Status> genericDao = new GenericDao<>();

            listaStatus = genericDao.listarByFilter(propriedades, null, Status.class, Constantes.NO_LIMIT, null);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return listaStatus;
    }

    /**
     * Lista combo de comprador
     *
     * @return
     */
    @POST
    @Path("listarComprador")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comprador> listarComprador() {
        List<Comprador> listaComprador = new ArrayList<>();

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));

            String aliasEapMultiEmpresa = NxCriterion.montaAlias(Comprador.ALIAS_CLASSE, Comprador.EAP_MULTI_EMPRESA);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Comprador.COMPRADOR_ID));
            propriedades.add(new Propriedade(Comprador.NOME));

            //Se caso a pessoa tiver habilitado eap multi empresa, então o mesmo é listado para ser exibido junto no autoComplete
            Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
            if (conf.getHabilitaEapMultiEmpresa() != null && conf.getHabilitaEapMultiEmpresa() == true) {
                //EAP MULTI EMPRESA
                propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
                propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
                propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            }

            GenericDao<Comprador> genericDao = new GenericDao<>();
            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(Pessoa.NOME, NxOrder.NX_ORDER.ASC));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Comprador.ATIVO, Constantes.NAO_ABREVIADO, Filtro.NOT_EQUAL));

            listaComprador = genericDao.listarByFilter(propriedades, nxOrders, Comprador.class, Constantes.NO_LIMIT, nxCriterion);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return listaComprador;
    }

    /**
     * Lista combo de pessoas com a role de custos
     *
     * @return
     */
    @POST
    @Path("listarEquipeCustos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pessoa> listarEquipeCustos(Integer eapId) {
        List<Pessoa> lista = new ArrayList<>();
        RmaService rmaService = new RmaService(request);

        lista = rmaService.listarPessoaRole(Role.ROLE_APROVADOR_CUSTO, eapId);

        Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
        if (conf.getHabilitaEapMultiEmpresa() != null && conf.getHabilitaEapMultiEmpresa() == true) {
            for (Pessoa pessoa : lista) {
                if (pessoa.getEapMultiEmpresa() != null && pessoa.getEapMultiEmpresa().getDescricao() != null) {
                    pessoa.setNome(pessoa.getNome() + " - " + pessoa.getEapMultiEmpresa().getDescricao());
                }
            }
        }

        return lista;
    }

    /**
     * Lista as pessoas em que o tipoAtuacao seja igual a CO(Coordenador)
     *
     * @return
     */
    @POST
    @Path("listarCoordenadores")
    @Consumes("application/json")
    public List<Pessoa> listarCoordenadores() {
        List<Pessoa> lista = null;
        try {

            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(Pessoa.NOME, NxOrder.NX_ORDER.ASC));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
            propriedades.add(new Propriedade(Pessoa.NOME));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA));
            propriedades.add(new Propriedade(Pessoa.EMAIL));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.TIPO_ATUACAO, Constantes.TIPO_ATUACAO_COORDENADOR, Filtro.LIKE));

            GenericDao<Pessoa> dao = new GenericDao<>();
            lista = dao.listarByFilter(propriedades, nxOrders, Pessoa.class, Constantes.NO_LIMIT, nxCriterion);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    /**
     * Lista combo de pessoas com a role de lider de custos
     *
     * @return
     */
    @POST
    @Path("listarLiderCustos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pessoa> listarLiderCustos() {
        List<Pessoa> lista = new ArrayList<>();
        RmaService rmaService = new RmaService(request);

        lista = rmaService.listarPessoaRole(Constantes.ROLE_LIDER_CUSTOS);

        return lista;
    }

    /**
     * Lista para combo de EapMultiEmpresa.
     *
     * @return
     * @author: Alyson Xavier Leite
     * @data: 13/02/2015
     */
    @POST
    @Path("listarEapMultiEmpresa")
    @Consumes("application/json")
    public List<EapMultiEmpresa> listarEapMultiEmpresa() {
        List<EapMultiEmpresa> lista = new ArrayList<>();
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, lista));
            Usuario usuarioLogado = LoginService.getUsuarioLogado(request);

            EapMultiEmpresaService service = new EapMultiEmpresaService();
            lista = service.getEapsMultiEmpresaDoUsuario(usuarioLogado);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, lista));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    /**
     * Lista para combo de EapMultiEmpresa.
     *
     * @return
     * @author: Alyson Xavier Leite
     * @data: 13/02/2015
     */
    @POST
    @Path("listarTodasEapMultiEmpresa")
    @Consumes("application/json")
    public List<EapMultiEmpresa> listarTodasEapMultiEmpresa() {
        List<EapMultiEmpresa> lista = new ArrayList<>();
        try {
            EapMultiEmpresaService service = new EapMultiEmpresaService();
            lista = service.listarTodasEapMultiEmpresa();

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, lista));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }
    
    @POST
    @Path("listarCentrosDoUsuario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Centro> getCentrosPorUsuario(){
    	Integer usuarioId = LoginService.getUsuarioLogado(request).getUsuarioId();
    	List<Role> roles = LoginService.getUsuarioLogado(request).getRoles();
    	return centroService.getCentrosPorUsuario(usuarioId, roles);
    }
    
    @POST
    @Path("listarDepositosDoCentro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Deposito> getDepositosPorCentro(Integer centroId){
    	DepositoService depositoService = new DepositoService();
    	return depositoService.getDepositosPorCentro(centroId);
    }
    
    @POST
    @Path("listarAreas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Area> getAreas(){
    	AreaService areaService = new AreaService();
    	return areaService.getAreas();
    }
    
    @POST
    @Path("listarAprovadoresAreas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getAprovadoresAreas(){
    	UsuarioService usuarioService = new UsuarioService();
    	return usuarioService.getAprovadoresArea();
    }
    
    @POST
    @Path("listarAprovadoresGerenteAreas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getAprovadoresGerenteAreas(){
    	UsuarioService usuarioService = new UsuarioService();
    	return usuarioService.getAprovadoresGerenteArea();
    }
    
    @POST
    @Path("listarAprovadoresCustos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getAprovadoresCustos(){
    	UsuarioService usuarioService = new UsuarioService();
    	return usuarioService.getAprovadoresCusto();
    }
    
    @POST
    @Path("listarAprovadoresEmergenciais")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getAprovadoresEmergenciais(){
    	UsuarioService usuarioService = new UsuarioService();
    	return usuarioService.getAprovadoresEmergencial();
    }
    
    @POST
    @Path("listarAprovadoresRm")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getAprovadoresRm(Integer rmId){
    	RmService rmService = new RmService();
    	return rmService.getAprovadoresRm(rmId);
    }
    
    @POST
    @Path("listarCodigoSap")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<RmServicoCodigoSap> getCodigoSap(){
    	RmServicoService rmServicoService = new RmServicoService();
    	return rmServicoService.getCodigoSap();
    }
    
    
}
