package br.com.nextage.rmaweb.mobile.controller;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.filtro.FiltroLoginMobile;
import br.com.nextage.rmaweb.mobile.inteface.Secured;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.integracao.EapMultiEmpresaService;
import br.com.nextage.rmaweb.service.integracao.RmaService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.MaterialMobileVo;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe responsável por listar as funcionalides de combo do dispositivo mobile
 *
 * @brief Classe ComboMobileController
 * @author Alyson X. Leite <a.leite@nextage.com.br>
 * @date 06/01/2016
 */
@Path("ComboMobileController")
public class ComboMobileController {

    @Context
    private HttpServletRequest request;
    private SecurityContext context;

    private static final Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    /**
     * Recupera o usuário logado no sistema
     *
     * @author Alyson X. Leite
     * @return
     */
    private FiltroLoginMobile getUsuarioLogado() {
        FiltroLoginMobile principal = (FiltroLoginMobile) context.getUserPrincipal();
        return principal;
    }

    /**
     * Responsavel por listar os encarregados do sistema
     *
     * @author Alyson X. Leite
     * @return List<Pessoa>
     */
    @POST
    @Secured
    @Path("listarEncarregados")
    @Consumes("application/json")
    public List<Pessoa> listarEncarregados() {
        List<Pessoa> lista = null;
        try {

            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(Pessoa.NOME, NxOrder.NX_ORDER.ASC));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
            propriedades.add(new Propriedade(Pessoa.NOME));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA));

            String aliasSubarea = NxCriterion.montaAlias(Pessoa.ALIAS_CLASSE, Pessoa.SUB_AREA);

            propriedades.add(new Propriedade(SubArea.SUB_AREA_ID, SubArea.class, aliasSubarea));
            propriedades.add(new Propriedade(SubArea.CODIGO, SubArea.class, aliasSubarea));
            propriedades.add(new Propriedade(SubArea.DESCRICAO, SubArea.class, aliasSubarea));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.TIPO_ATUACAO, Constantes.TIPO_ATUACAO_ENCARREGADO, Filtro.LIKE));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Pessoa.DATA_ADMISSAO, null, Filtro.NOT_NULL)));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Pessoa.DATA_DEMISSAO, null, Filtro.IS_NULL)));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Pessoa.REFERENCIA, null, Filtro.NOT_NULL)));

            GenericDao<Pessoa> dao = new GenericDao<>();
            lista = dao.listarByFilter(propriedades, nxOrders, Pessoa.class, Constantes.NO_LIMIT, nxCriterion);

            /**
             * feito dessa forma para não precisar atualizar o campo Subarea no Mobile, 
             * necessário para não precisar gerar versão nos mobiles, será criada atividade para posteriormente
             * arrumarmos no mobile.
             * [Marlos]
             **/
            for (Pessoa pessoa : lista) {
                if (pessoa.getSubArea() != null) {
                    Setor newSetor = new Setor(pessoa.getSubArea().getSubAreaId(), pessoa.getSubArea().getDescricao());
                    pessoa.setSetor(newSetor);
                }
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    /**
     * Responsavel por listar as pessoas do sistema
     *
     * @author Alyson X. Leite
     * @return List<Pessoa>
     */
    @POST
    @Secured
    @Path("listarPessoas")
    @Consumes("application/json")
    public List<Pessoa> listarPessoas() {
        List<Pessoa> lista = null;
        try {

            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(Pessoa.NOME, NxOrder.NX_ORDER.ASC));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
            propriedades.add(new Propriedade(Pessoa.NOME));
            propriedades.add(new Propriedade(Pessoa.CPF));
            propriedades.add(new Propriedade(Pessoa.EMAIL));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA));

            NxCriterion criterion = NxCriterion.montaRestriction(new Filtro(Pessoa.DATA_ADMISSAO, null, Filtro.NOT_NULL));
            criterion = NxCriterion.and(criterion, NxCriterion.montaRestriction(new Filtro(Pessoa.DATA_DEMISSAO, null, Filtro.IS_NULL)));
            criterion = NxCriterion.and(criterion, NxCriterion.montaRestriction(new Filtro(Pessoa.REFERENCIA, null, Filtro.NOT_NULL)));

            GenericDao<Pessoa> dao = new GenericDao<>();
            lista = dao.listarByFilter(propriedades, nxOrders, Pessoa.class, Constantes.NO_LIMIT, criterion);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    /**
     * Responsavel por listar os materiais do sistema
     *
     * @author Alyson X. Leite
     * @return List<Pessoa>
     */
    @POST
    @Secured
    @Path("listarMateriais")
    @Consumes("application/json")
    public List<MaterialMobileVo> listarMateriais() {
        List<MaterialMobileVo> lista = new ArrayList<>();
        try {
            RmaService service = new RmaService();
            List<Material> listaMaterial = service.listarMateriais();
            if (listaMaterial != null && !listaMaterial.isEmpty()) {
                for (Material material : listaMaterial) {
                    MaterialMobileVo vo = new MaterialMobileVo(material);
                    String epi = isEpi(material) ? Constantes.SIM_ABREVIADO : Constantes.NAO_ABREVIADO;
                    vo.setEpi(epi);
                    lista.add(vo);
                }
            }
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    /**
     * Verifico se o material é um Equipamento EPI
     *
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     * @param material
     * @return
     */
    private boolean isEpi(Material material) {
        Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
        if (material != null && conf.getCodigoEpi() != null
                && material.getHierarquia() != null) {
            String[] listaEpi = conf.getCodigoEpi().split(";");
            for (String epi : listaEpi) {
                if (material.getHierarquia().contains(epi)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Lista combo de depositos
     *
     * @return
     */
    @POST
    @Path("listarDepositos")
    @Consumes("application/json")
    public List<Deposito> listarDepositos() {
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
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return depositos;
    }

    /**
     * Lista combo de depositos
     *
     * @return
     */
    @POST
    @Path("listarCadastrante")
    @Consumes("application/json")
    public List<Usuario> listarCadastrante() {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            String aliasPessoa = NxCriterion.montaAlias(Usuario.ALIAS_CLASSE, Usuario.PESSOA);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Usuario.USUARIO_ID));
            propriedades.add(new Propriedade(Usuario.ATIVO));
            propriedades.add(new Propriedade(Usuario.LOGIN));
            propriedades.add(new Propriedade(Usuario.NOME));

            //Pessoa
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.CPF, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.EMAIL, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasPessoa));

            GenericDao<Usuario> genericDao = new GenericDao<>();

            NxCriterion nc = NxCriterion.montaRestriction(new Filtro(Usuario.ATIVO, Constantes.SIM_ABREVIADO, Filtro.EQUAL));
            nc = NxCriterion.and(nc, NxCriterion.montaRestriction(new Filtro(Pessoa.REFERENCIA, null, Filtro.NOT_NULL, aliasPessoa)));

            usuarios = genericDao.listarByFilter(propriedades, null, Usuario.class, Constantes.NO_LIMIT, nc);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return usuarios;
    }


    /**
     * Responsavel por listar os materiais do sistema
     *
     * @author Jerry Adriano
     * @return List<EapMultiEmpresa>
     */
    @POST
    @Secured
    @Path("listarEapMultiEmpresas")
    @Consumes("application/json")
    public List<EapMultiEmpresa> listarEapMultiEmpresas(@Context SecurityContext context) {
        this.context = context;
        List<EapMultiEmpresa> lista = new ArrayList<EapMultiEmpresa>();
        try {
            FiltroLoginMobile filtroUser = getUsuarioLogado();
            Usuario user = new Usuario();
            user.setUsuarioId(filtroUser.getUsuarioId());
            user.setNome(filtroUser.getNome());

            EapMultiEmpresaService service = new EapMultiEmpresaService();
            List<UsuarioEapMultiEmpresa> listaAux = service.listarUsuarioEapMultiEmpresa(user);
            for (UsuarioEapMultiEmpresa u : listaAux) {
                lista.add(u.getEapMultiEmpresa());
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }
}
