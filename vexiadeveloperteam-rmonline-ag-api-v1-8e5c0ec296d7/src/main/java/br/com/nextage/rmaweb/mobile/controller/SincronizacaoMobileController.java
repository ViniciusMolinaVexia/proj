package br.com.nextage.rmaweb.mobile.controller;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.dao.RmMaterialRetiradaDao;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.filtro.FiltroLoginMobile;
import br.com.nextage.rmaweb.mobile.inteface.Secured;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.RmMaterialRetiradaService;
import br.com.nextage.rmaweb.service.integracao.RmMaterialStatusService;
import br.com.nextage.rmaweb.service.integracao.RmaService;
import br.com.nextage.rmaweb.service.integracao.SincCautelaService;
import br.com.nextage.rmaweb.service.integracao.UnidadeMedidaIntegracaoService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.*;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import br.com.nextage.util.comparator.AlphanumComparator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.util.*;

/**
 * Classe resposável pela sincronização da RM do dispositivo mobile com o
 * sistema web
 *
 * @brief Classe SincronizacaoMobileController
 * @author Alyson X. Leite <a.leite@nextage.com.br>
 * @date 12/01/2016
 */
@Path("SincronizacaoMobileController")
public class SincronizacaoMobileController {

    @Context
    private HttpServletRequest request;
    private SecurityContext context;

    private static final Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    /**
     * Recupera o nome do usuário logado no sistema
     *
     * @author Alyson X. Leite
     * @return
     */
    private String getNomeUsuarioLogado() {
        return getUsuarioLogado().getName();
    }

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
     * Realiza o cadastramento das RMs que estavam cadastradas no dispositivo
     * mobile, porém ainda não foram sincronizadas com o sistema web
     *
     * @author Alyson X. Leite
     * @param lista lista de RMs enviadas do dispositivo
     * @param context Para recuperar o usuario logado no sistema
     * @return
     */
    @POST
    @Secured
    @Path("sincronizar")
    @Consumes("application/json")
    public Info sincronizar(List<RequisicaoMobileVo> lista, @Context SecurityContext context) {
        Info retorno = Info.GetSuccess();
        try {
            this.context = context;
            List<Info> listaInfo = new ArrayList<>();
            for (RequisicaoMobileVo req : lista) {
                Info info = cadastrarRequisicao(req);
                if (info.getCodigo().equals(Info.INFO_001)) {
                    req.setSincronizada(1);
                    req.setErro("");
                } else {
                    req.setSincronizada(0);
                    req.setErro(info.getMensagem());
                }
                info.setObjeto(req);
                listaInfo.add(info);
            }
            retorno.setObjeto(listaInfo);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(getNomeUsuarioLogado(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            retorno = Info.GetError("Erro ao sincronizar requisição");
        }
        return retorno;
    }

    /**
     * Método rest para cadastrar a RM gerada no dispositivo mobile no sistema
     * web
     *
     * @author Alyson X. Leite
     * @param requisicao RM enviada do dispositivo
     * @param context Para recuperar o usuario logado no sistema
     * @return
     */
    @POST
    @Secured
    @Path("cadastrarRequisicao")
    @Consumes("application/json")
    public Info cadastrarRequisicao(RequisicaoMobileVo requisicao, @Context SecurityContext context) {
        try {
            this.context = context;
            Info info = cadastrarRequisicao(requisicao);
            if (info.getCodigo().equals(Info.INFO_001)) {
                requisicao.setSincronizada(1);
                requisicao.setErro("");
            } else {
                requisicao.setSincronizada(0);
                requisicao.setErro(info.getMensagem());
            }
            info.setObjeto(requisicao);
            return info;
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(getNomeUsuarioLogado(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            return Info.GetError("Erro ao cadastrar requisição");
        }
    }

    /**
     * Realiza o tratamento e o cadastramento da RM gerada no dispositivo mobile
     * no sistema web
     *
     * @param requisicao
     * @return
     * @throws Exception
     */
    private Info cadastrarRequisicao(RequisicaoMobileVo requisicao) throws Exception {

        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

        // Transforma as datas em texto para o tipo Date
        Date dataAplicacao = null;
        Date dataCriacao = null;

        if (requisicao.getStDataAplicacao() != null && !requisicao.getStDataAplicacao().isEmpty()) {
            try {
                dataAplicacao = Util.parseData(requisicao.getStDataAplicacao(), rb.getString("format_date"));
            } catch (Exception e) {
                logger.error(ResourceLogUtil.createMessageError(getNomeUsuarioLogado(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            }
        }
        if (requisicao.getStDataCriacao() != null && !requisicao.getStDataCriacao().isEmpty()) {
            try {
                dataCriacao = Util.parseData(requisicao.getStDataCriacao(), rb.getString("format_date_hora_minuto_segundo"));
            } catch (Exception e) {
                logger.error(ResourceLogUtil.createMessageError(getNomeUsuarioLogado(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            }
        }
        requisicao.setDataAplicacao(dataAplicacao);
        requisicao.setDataCriacao(dataCriacao);
        // Converte a RequisicaoMobileVo para um CadastroRmVo
        CadastroRmVo vo = convertRequisicaoToCadastroRm(requisicao);
        // Salva a RM no sistema
        Info info = salvar(vo);

        return info;
    }

    /**
     * Salva a RM no sistema web
     *
     * @param cadastroRmVo
     * @return
     * @throws Exception
     */
    private Info salvar(CadastroRmVo cadastroRmVo) throws Exception {
        Info info = new Info();
        GenericDao genericDao = new GenericDao();
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(getNomeUsuarioLogado(), this.getClass().getName(), CadastroRmVo.class.getName(), Util.getNomeMetodoAtual(), cadastroRmVo, null));
            // Verifica se a rm já está cadastrada
            Rm rmCadastrada = verificaCadastroRm(cadastroRmVo.getRm());

            if (rmCadastrada != null) {
                cadastroRmVo.setRm(rmCadastrada);
                return Info.GetSuccess("Registro já está cadastrado.", cadastroRmVo);
            }

            cadastroRmVo.getRm().setDataRecebimento(new Date());
            cadastroRmVo.getRm().setDataEnvioAprovacao(new Date());

            RmaService rmaService = new RmaService(request);

            // Gero o numero da RM
            Integer numeroRmAtual = rmaService.buscarNumeroRmAtual();
            numeroRmAtual = numeroRmAtual == null ? 1 : numeroRmAtual + 1;
            cadastroRmVo.getRm().setNumeroRm(numeroRmAtual.toString());

            RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);

            genericDao.beginTransaction();

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO));
            propriedades.add(new Propriedade(Rm.DATA_EMISSAO));
            propriedades.add(new Propriedade(Rm.NUMERO_RM));
            propriedades.add(new Propriedade(Rm.NUMERO_RM_MOBILE));
            propriedades.add(new Propriedade(Rm.PERIODO));
            propriedades.add(new Propriedade(Rm.DEPOSITO));
            propriedades.add(new Propriedade(Rm.OBSERVACAO));
            propriedades.add(new Propriedade(Rm.PRIORIDADE));
            propriedades.add(new Propriedade(Rm.REQUISITANTE));
            propriedades.add(new Propriedade(Rm.USUARIO));
            propriedades.add(new Propriedade(Rm.LOCAL_ENTREGA));

            genericDao.persistWithCurrentTransaction(cadastroRmVo.getRm());

            propriedades.clear();
            propriedades.add(new Propriedade(RmMaterial.MATERIAL));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO));
            propriedades.add(new Propriedade(RmMaterial.LOCAL_ENTREGA));
            propriedades.add(new Propriedade(RmMaterial.ORDEM));
            propriedades.add(new Propriedade(RmMaterial.CF_RESPONSAVEL));
            propriedades.add(new Propriedade(RmMaterial.PESSOA_RESPONSAVEL));

            // Deve haver somente um único depósito
            Deposito deposito = cadastroRmVo.getRmDepositos().get(0).getDeposito();

            //Para cada material, gera dois status: cadastrada e aguardando o estoquista
            int ordem = 0;
            String usuario = getNomeUsuarioLogado();
            for (RmMaterial rmMaterial : cadastroRmVo.getRmMateriais()) {
                ordem++;
                rmMaterial.setRm(cadastroRmVo.getRm());
                rmMaterial.setFluxoMaterial(Constantes.FLUXO_MATERIAL_CAMPO);
                rmMaterial.setOrdem(ordem);
                rmMaterial.setLocalEntrega(cadastroRmVo.getRm().getLocalEntrega());
                // Verifica se existe a quantidade de material necessário no depósito
                String obsEstoquista = validaEstoqueMaterial(rmMaterial, deposito);
                genericDao.persistWithCurrentTransaction(rmMaterial);
                Boolean itemIndisponivel = false;
                rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_CADASTRADA, genericDao, new Date(), null, usuario);
                if (obsEstoquista == null || obsEstoquista.isEmpty()) {
                    rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_AGUARDANDO_ESTOQUE, genericDao, null, null, usuario);
                } else {
                    itemIndisponivel = true;
                    rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_INDISPONIVEL, genericDao, null, null, usuario);
                }

                //Gera uma saida reserva, para ficar como historico
                MaterialDepositoSaidaReserva materialDepositoSaidaReserva = new MaterialDepositoSaidaReserva();
                materialDepositoSaidaReserva.setDataSaida(cadastroRmVo.getRm().getDataEmissao());
                materialDepositoSaidaReserva.setRmMaterial(rmMaterial);
                materialDepositoSaidaReserva.setQuantidadeSolicitada(rmMaterial.getQuantidade());
                materialDepositoSaidaReserva.setObservacaoPainelEstoquista(obsEstoquista);
                materialDepositoSaidaReserva.setItemIndisponivel(itemIndisponivel);

                genericDao.persistWithCurrentTransaction(materialDepositoSaidaReserva);
            }

            for (RmDeposito rmDeposito : cadastroRmVo.getRmDepositos()) {
                rmDeposito.setRm(cadastroRmVo.getRm());
                genericDao.persistWithCurrentTransaction(rmDeposito);
            }

            List<RmMaterial> lista;
            lista = listarMateriaisEstocaveisSap(cadastroRmVo.getRmMateriais());
            if(lista.size() > 0){
                cadastroRmVo.setRmMateriais(lista);
                info = enviaLiderCustos(cadastroRmVo, genericDao);

                if (info.getCodigo().equals(Info.INFO_001)) {
                    genericDao.commitCurrentTransaction();
                } else {
                    genericDao.rollbackCurrentTransaction();
                }
            } else {
                genericDao.commitCurrentTransaction();
            }
            //info = Info.GetSuccess("Registro Salvo com Sucesso.", cadastroRmVo);
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            logger.error(ResourceLogUtil.createMessageError(getNomeUsuarioLogado(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            throw e;
        }

        return info;
    }

    /**
     * Converto o objeto RequisicaoMobileVo para um objeto CadastroRmVo Pois o
     * mesmo já contem as entidades que serão persistidas
     *
     * @author Alyson X. Leite
     * @param req
     * @return
     */
    private CadastroRmVo convertRequisicaoToCadastroRm(RequisicaoMobileVo req) {

        CadastroRmVo cadastroRmVo = new CadastroRmVo();
        cadastroRmVo.setAprovador(null);
        Rm rm = new Rm();

        rm.setNumeroRmMobile(req.getId());
        rm.setPeriodo(req.getPeriodo());
        rm.setLocalEntrega(req.getLocalEntrega());
        if(req.getEapMultiEmpresa() != null && req.getEapMultiEmpresa().getEapMultiEmpresaId() != null && req.getEapMultiEmpresa().getEapMultiEmpresaId() > 0) {
            rm.setEapMultiEmpresa(selectEapMultiEmpresaById(req.getEapMultiEmpresa().getEapMultiEmpresaId()));
        }
        // Requisitante
        Pessoa requisitante = selectPessoaByPessoaId(req.getEncarregado().getPessoaId());
        rm.setRequisitante(requisitante);

        // Datas
        rm.setDataAplicacao(req.getDataAplicacao());
        rm.setDataEmissao(req.getDataCriacao());
        rm.setStDataAplicacao(req.getStDataAplicacao());
        rm.setStDataEmissao(req.getStDataCriacao());

        // Prioridade
        rm.setPrioridade(selectPrioridadeMedia());

        // Deposito
        Deposito dep = selectDepostitoByDepositoId(req.getDeposito().getDepositoId());
        RmDeposito rmDep = new RmDeposito();
        rmDep.setDeposito(dep);
        List<RmDeposito> listaDep = new ArrayList<>();
        listaDep.add(rmDep);
        cadastroRmVo.setRmDepositos(listaDep);

        rm.setDeposito(dep);

        // Usuario
        String login = getNomeUsuarioLogado();
        Usuario usuario = selectUsuario(login);
        rm.setUsuario(usuario);

        cadastroRmVo.setRm(rm);

        UnidadeMedidaIntegracaoService medidaIntegracaoService = new UnidadeMedidaIntegracaoService();

        // Materiais
        List<RmMaterial> lista = new ArrayList<>();
        for (MaterialMobileVo material : req.getMateriais()) {
            RmMaterial rmMat = new RmMaterial();
            rmMat.setQuantidade(material.getQuantidade());
            Material mat = new Material(material.getMaterialId());
            if (material.getUnidadeMedida() != null && !material.getUnidadeMedida().isEmpty()) {
                UnidadeMedida medida = medidaIntegracaoService.searchUnidadeMedida(material.getUnidadeMedida());
                mat.setUnidadeMedida(medida);
            }

            mat.setCodigo(material.getCodigo());
            mat.setMaterialId(material.getMaterialId());
            rmMat.setMaterial(mat);
            rmMat.setDeposito(dep);

            if (material.getPessoa() != null) {
                rmMat.setCfResponsavel(material.getPessoa().getReferencia());
                if (material.getPessoa().getPessoaId() != null) {
                    rmMat.setPessoaResponsavel(new Pessoa(material.getPessoa().getPessoaId()));
                }
            }
            lista.add(rmMat);
        }
        cadastroRmVo.setRmMateriais(lista);

        return cadastroRmVo;
    }

    /**
     * Recupera a Prioridade do tipo Media
     *
     * @author Alyson X. Leite
     * @return Prioridade
     */
    private Prioridade selectPrioridadeMedia() {
        Prioridade p = null;

        try {
            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Prioridade.CODIGO, Constantes.CODIGO_PRIORIDADE_MEDIA, Filtro.EQUAL));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Prioridade.PRIORIDADE_ID));
            propriedades.add(new Propriedade(Prioridade.CODIGO));
            propriedades.add(new Propriedade(Prioridade.DESCRICAO));

            GenericDao<Prioridade> genericDao = new GenericDao<>();

            p = genericDao.selectUnique(propriedades, Prioridade.class, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(getNomeUsuarioLogado(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return p;
    }

    /**
     * Recupera um usuário do sistema de acordo com o login passado por
     * parametro
     *
     * @author Alyson X. Leite
     * @param login
     * @return Usuario
     */
    private Usuario selectUsuario(String login) {
        Usuario usuario = null;

        try {
            NxCriterion nxCriterion;

            nxCriterion = NxCriterion.montaRestriction(new Filtro(Usuario.LOGIN, login, Filtro.EQUAL));

            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Usuario.ATIVO, Constantes.SIM_ABREVIADO, Filtro.EQUAL)));

            String aliasPessoa = NxCriterion.montaAlias(Usuario.ALIAS_CLASSE, Usuario.PESSOA);

            /* Filtra as propriedades da Requisição */
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Usuario.USUARIO_ID));
            propriedades.add(new Propriedade(Usuario.TOKEN_MOBILE));

            //Pessoa
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.CPF, Pessoa.class, aliasPessoa));

            GenericDao<Usuario> genericDao = new GenericDao<>();
            usuario = genericDao.selectUnique(propriedades, Usuario.class, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(getNomeUsuarioLogado(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return usuario;
    }

    /**
     * Recupera uma pessoa do sistema de acordo com a pessoaId passada por
     * parametro
     *
     * @author Alyson X. Leite
     * @param pessoaId
     * @return Pessoa
     */
    private Pessoa selectPessoaByPessoaId(String pessoaId) {
        Pessoa retorno = null;

        try {
            NxCriterion nxCriterion;

            nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, Integer.parseInt(pessoaId), Filtro.EQUAL));

            /* Filtra as propriedades da Requisição */
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
            propriedades.add(new Propriedade(Pessoa.NOME));
            propriedades.add(new Propriedade(Pessoa.CPF));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA));

            GenericDao<Pessoa> genericDao = new GenericDao<>();
            retorno = genericDao.selectUnique(propriedades, Pessoa.class, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(getNomeUsuarioLogado(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return retorno;
    }

    private Deposito selectDepostitoByDepositoId(Integer depositoId) {
        Deposito retorno = null;

        try {
            NxCriterion nxCriterion;

            nxCriterion = NxCriterion.montaRestriction(new Filtro(Deposito.DEPOSITO_ID, depositoId, Filtro.EQUAL));

            /* Filtra as propriedades da Requisição */
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));
            propriedades.add(new Propriedade(Deposito.NOME));
            propriedades.add(new Propriedade(Deposito.CODIGO));
            propriedades.add(new Propriedade(Deposito.OBSERVACAO));

            GenericDao<Deposito> genericDao = new GenericDao<>();
            retorno = genericDao.selectUnique(propriedades, Deposito.class, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(getNomeUsuarioLogado(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return retorno;
    }

    private EapMultiEmpresa selectEapMultiEmpresaById(Integer id) {
        EapMultiEmpresa retorno = null;

        try {
            NxCriterion nxCriterion;

            nxCriterion = NxCriterion.montaRestriction(new Filtro(EapMultiEmpresa.ID, id, Filtro.EQUAL));

            /* Filtra as propriedades da Requisição */
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(EapMultiEmpresa.ID));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO));

            GenericDao<EapMultiEmpresa> genericDao = new GenericDao<>();
            retorno = genericDao.selectUnique(propriedades, EapMultiEmpresa.class, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(getNomeUsuarioLogado(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return retorno;
    }

    /**
     * Verifica se o depósito passado como parâmetro tem a quantidade necessária
     * para o material passado como parâmetro
     *
     * @author Alyson X. Leite
     * @param rmMaterial Contem o material e a quantidade solitada do mesmo
     * @param deposito Deposito para verificar se tem material
     * @return RmMaterial
     * @throws Exception
     */
    private String validaEstoqueMaterial(RmMaterial rmMaterial, Deposito deposito) throws Exception {
        NxCriterion nxCriterion;

        nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDeposito.DEPOSITO, deposito, Filtro.EQUAL));
        nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, rmMaterial.getMaterial(), Filtro.EQUAL)));
        /* Filtra as propriedades da Requisição */
        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
        propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

        GenericDao<MaterialDeposito> genericDao = new GenericDao<>();
        MaterialDeposito retorno = genericDao.selectUnique(propriedades, MaterialDeposito.class, nxCriterion);

        if (retorno != null && retorno.getQuantidade() >= rmMaterial.getQuantidade()) {
            // Material disponivel em estoque
            return null;
        }
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
        return rb.getString("label_item_indisponivel_no_deposito");
    }

    /**
     * Verifica se a Rm já está cadastrada no sistema
     *
     * @author Alyson X. Leite
     * @param rm
     * @return
     * @throws Exception
     */
    private Rm verificaCadastroRm(Rm rm) throws Exception {
        NxCriterion nxCriterion;

        nxCriterion = NxCriterion.montaRestriction(new Filtro(Rm.USUARIO, rm.getUsuario(), Filtro.EQUAL));
        nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Rm.DATA_EMISSAO, rm.getDataEmissao(), Filtro.EQUAL)));
        nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Rm.NUMERO_RM_MOBILE, rm.getNumeroRmMobile(), Filtro.EQUAL)));
        /* Filtra as propriedades da Requisição */
        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(Rm.NUMERO_RM_MOBILE));
        propriedades.add(new Propriedade(Rm.NUMERO_RM));
        propriedades.add(new Propriedade(Rm.RM_ID));

        GenericDao<Rm> genericDao = new GenericDao<>();
        Rm retorno = genericDao.selectUnique(propriedades, Rm.class, nxCriterion);
        if (retorno != null) {
            rm.setNumeroRmMobile(retorno.getNumeroRmMobile());
            rm.setNumeroRm(retorno.getNumeroRm());
            rm.setRmId(retorno.getRmId());
            return rm;
        }
        return retorno;
    }

    /**
     * Responsavel por listar as rms
     *
     * @author Alyson X. Leite
     * @return List<Pessoa>
     */
    @POST
    @Secured
    @Path("listarRmMaterial")
    @Consumes("application/json")
    public Info listarRmMaterial(FiltroRmMaterialMobileVo filtro) {
        try {
            return Info.GetSuccess(null, listarVwRmMaterial(filtro));
        } catch (Exception e) {
            return Info.GetError(e.getMessage());
        }
    }

    /**
     * Método responsavel por listar os materiais pendentes de baixa e agrupa-los por protocolo em um ValueObject(Vo)
     * para exibir na tela do mobile
     *
     * @author Marlos Morbis Novo
     * @return Info
     */
    @POST
    @Secured
    @Path("listarProtocolo")
    @Consumes("application/json")
    public Info listarProtocolo(FiltroRmMaterialMobileVo filtro) {
        try {
            List<ProtocoloMobileVo> listaProtocoloMobileVo = new ArrayList<>();

            List<VwRmMaterial> lista = listarVwRmMaterial(filtro);

            //Realiza agrupamento do itens listados pelo numero de protocolo.
            Map<String, List<VwRmMaterial>> group = new TreeMap<String, List<VwRmMaterial>>(Collections.reverseOrder(new AlphanumComparator()));
            for (VwRmMaterial vw : lista) {
                if (vw.getRmMaterial() != null && vw.getRmMaterial().getProtocoloResponsabilidade() != null) {
                    String protocolo = vw.getRmMaterial().getProtocoloResponsabilidade();
                    if (protocolo != null && !protocolo.isEmpty()) {
                        if (group.get(protocolo) == null) {
                            group.put(protocolo, new ArrayList<VwRmMaterial>());
                        }
                        if (vw.getRmMaterial() != null && vw.getRmMaterial().getPessoaResponsavel() != null && vw.getRmMaterial().getPessoaResponsavel().getReferencia() != null) {
                            vw.getRmMaterial().setCfResponsavel(vw.getRmMaterial().getPessoaResponsavel().getReferencia());
                        }
                        group.get(protocolo).add(vw);
                    }
                }
            }

            //Transforma os itens agrupados no tipo de objeto necessário para transferencia entre back e front mobile
            //Objeto ProtocoloMobileVo
            for (Map.Entry<String, List<VwRmMaterial>> entry : group.entrySet()) {
                final String protocolo = entry.getKey();
                List<VwRmMaterial> listaRmMaterial = entry.getValue();

                ProtocoloMobileVo vo = new ProtocoloMobileVo();
                vo.setProtocolo(protocolo);
                vo.setListaVwRmMaterial(listaRmMaterial);
                listaProtocoloMobileVo.add(vo);
            }

//            for (ProtocoloMobileVo voLista : listaProtocoloMobileVo){
//
//                //if(listaProtocoloMobileVo != null && !listaProtocoloMobileVo.isEmpty()){
//                    //ProtocoloMobileVo vo = listaProtocoloMobileVo.get(0);
//                    baixaMaterial(voLista.getProtocolo());
//                //}
//            }

            return Info.GetSuccess(null, listaProtocoloMobileVo);
        } catch (Exception e) {
            return Info.GetError(e.getMessage());
        }
    }

    public List<VwRmMaterial> listarVwRmMaterial(FiltroRmMaterialMobileVo filtro) {
        List<VwRmMaterial> lista = new ArrayList<>();
        try {

            List<Propriedade> propriedades = new ArrayList<>();

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroRmMaterialMobileVo.class.getName(), Util.getNomeMetodoAtual(), filtro, null));

            String aliasRmMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID);
            String aliasRmMaterialStatus = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_STATUS_ID);
            String aliasStatus = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_STATUS_ID, RmMaterialStatus.STATUS_ID);
            String aliasRm = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM);
            String aliasMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.MATERIAL);
            String aliasRequisitante = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.REQUISITANTE);
            String aliasPessoaResponsavel = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.PESSOA_RESPONSAVEL);
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

            //PESSOA RESPONSAVEL
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasPessoaResponsavel));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasPessoaResponsavel));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasPessoaResponsavel));

            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasRmMaterialDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasRmMaterialDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasRmMaterialDeposito));

            //USUARIO
            propriedades.add(new Propriedade(Usuario.USUARIO_ID, Usuario.class, aliasUsuario));
            propriedades.add(new Propriedade(Usuario.NOME, Usuario.class, aliasUsuario));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.FLUXO_MATERIAL, Constantes.FLUXO_MATERIAL_CAMPO, Filtro.EQUAL, aliasRmMaterial));
            NxCriterion nxCriterionAux;
            if (filtro.getEncarregado() != null) {
                Pessoa req = new Pessoa(Integer.parseInt(filtro.getEncarregado().getPessoaId()));
                nxCriterion = NxCriterion.montaRestriction(new Filtro(Rm.REQUISITANTE, req, Filtro.EQUAL, aliasRm));
            }
            if (filtro.getCadastrante() != null) {
                Usuario cad = new Usuario(filtro.getCadastrante().getUsuarioId());
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Rm.USUARIO, cad, Filtro.EQUAL, aliasRm));
                if (nxCriterion == null) {
                    nxCriterion = nxCriterionAux;
                } else {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                }
            }
            if (filtro.getMaterial() != null) {
                Material mat = new Material(filtro.getMaterial().getMaterialId());
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.MATERIAL, mat, Filtro.EQUAL, aliasRmMaterial));
                if (nxCriterion == null) {
                    nxCriterion = nxCriterionAux;
                } else {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                }
            }
            if (filtro.getStatus() != null && !filtro.getStatus().isEmpty()) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Status.CODIGO, filtro.getStatus(), Filtro.EQUAL, aliasStatus));
                if (nxCriterion == null) {
                    nxCriterion = nxCriterionAux;
                } else {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                }
            }

            if (filtro.getProtocolo() != null && !filtro.getProtocolo().isEmpty()) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.PROTOCOLO_RESPONSABILIDADE, filtro.getProtocolo(), Filtro.EQUAL, aliasRmMaterial));
                if (nxCriterion == null) {
                    nxCriterion = nxCriterionAux;
                } else {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                }
            }
            if (filtro.getRma() != null && !filtro.getRma().isEmpty()) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Rm.NUMERO_RM, filtro.getRma(), Filtro.EQUAL, aliasRm));
                if (nxCriterion == null) {
                    nxCriterion = nxCriterionAux;
                } else {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                }
            }
            if (filtro.getSomenteTermoEmitido() != null && !filtro.getSomenteTermoEmitido().isEmpty()) {
                nxCriterionAux = null;
                switch (filtro.getSomenteTermoEmitido()) {
                    case Constantes.SIM_ABREVIADO:
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.DOCUMENTO_RESPONSAVEL_IMPRESSO, Constantes.SIM_ABREVIADO, Filtro.EQUAL, aliasRmMaterial));
                        break;
                    case Constantes.NAO_ABREVIADO:
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.DOCUMENTO_RESPONSAVEL_IMPRESSO, Constantes.NAO_ABREVIADO, Filtro.EQUAL, aliasRmMaterial));
                        break;
                }
                if (nxCriterionAux != null) {
                    if (nxCriterion == null) {
                        nxCriterion = nxCriterionAux;
                    } else {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    }
                }
            }
            Date dataSolicitacaoDe = null;
            Date dataSolicitacaoAte = null;
            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
            if (filtro.getStDataSolicitacaoDe() != null && !filtro.getStDataSolicitacaoDe().isEmpty()) {
                dataSolicitacaoDe = Util.parseData(filtro.getStDataSolicitacaoDe(), rb.getString("format_date"));
            }
            if (filtro.getStDataSolicitacaoAte() != null && !filtro.getStDataSolicitacaoAte().isEmpty()) {
                dataSolicitacaoAte = Util.parseData(filtro.getStDataSolicitacaoAte(), rb.getString("format_date"));
                dataSolicitacaoAte = Util.convertDateHrFinal(dataSolicitacaoAte);
            }
            if (dataSolicitacaoDe != null || dataSolicitacaoAte != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Rm.DATA_EMISSAO, dataSolicitacaoDe, dataSolicitacaoAte, true, Filtro.BETWEEN, aliasRm));
                if (nxCriterion == null) {
                    nxCriterion = nxCriterionAux;
                } else {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                }
            }

            List<NxOrder> orders = java.util.Arrays.asList(new NxOrder(VwRmMaterial.RM_MATERIAL_ID + "." + RmMaterial.RM + "." + Rm.DATA_EMISSAO, NxOrder.NX_ORDER.DESC));

            GenericDao<VwRmMaterial> dao = new GenericDao<>();
            lista = dao.listarByFilter(propriedades, null, VwRmMaterial.class, Constantes.NO_LIMIT, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    public List<RmMaterial> listarMateriaisEstocaveisSap(List<RmMaterial> listaMateriais) {
        List<RmMaterial> lista = new ArrayList<>();
        GenericDao genericDao = new GenericDao();
        try {

            List<Propriedade> propriedades = new ArrayList<>();

            //logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroRmMaterialMobileVo.class.getName(), Util.getNomeMetodoAtual(), listaMateriais, null));

            String aliasUnidadeMedida = NxCriterion.montaAlias(Material.ALIAS_CLASSE, Material.UNIDADE_MEDIDA);

            //MATERIAL
            propriedades.add(new Propriedade(Material.MATERIAL_ID));
            propriedades.add(new Propriedade(Material.NOME));
            propriedades.add(new Propriedade(Material.CODIGO));
            propriedades.add(new Propriedade(Material.HIERARQUIA));
            propriedades.add(new Propriedade(Material.PATRIMONIADO));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP));

            //UNIDADE MEDIDA
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            List<Integer> listaIdMateriais = new ArrayList<>();
            for(RmMaterial rmMaterial : listaMateriais){
                listaIdMateriais.add(rmMaterial.getMaterial().getMaterialId());
            }

            NxCriterion nxCriterion;
            nxCriterion = NxCriterion.montaRestriction(new Filtro(Material.MATERIAL_ID, listaIdMateriais, Filtro.IN));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Material.ESTOQUE_SAP, Constantes.SIM_ABREVIADO, Filtro.EQUAL)));


            GenericDao<RmMaterial> dao = new GenericDao<>();
            lista = dao.listarByFilter(propriedades, null, Material.class, Constantes.NO_LIMIT, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    /**
     * Responsavel dar baixa nos materiais
     *
     * @author Alyson X. Leite
     * @return List<Pessoa>
     */
    @POST
    @Secured
    @Path("sincronizarBaixaMaterial")
    @Consumes("application/json")
    public Info sincronizarBaixaMaterial(List<String> lista) {
        Info retorno;
        List<Info> listaInfo = new ArrayList<>();
        try {
            Set<String> set = new HashSet<>(lista);
            for (String prot : set) {
                Info info = baixaMaterial(prot);
                listaInfo.add(info);
            }
            retorno = Info.GetSuccess(listaInfo);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            retorno = Info.GetError(e.getMessage(), listaInfo);
        }
        return retorno;
    }

    /**
     * Responsavel dar baixa nos materiais
     *
     * @author Alyson X. Leite
     * @return List<Pessoa>
     */
    @POST
    @Secured
    @Path("baixaMaterial")
    @Consumes("application/json")
    public Info baixaMaterial(String protocolo) {
        Info info;
        GenericDao<RmMaterialRetirada> dao = new GenericDao<>();
        try {
            FiltroRmMaterialMobileVo filtro = new FiltroRmMaterialMobileVo();
            filtro.setProtocolo(protocolo);
            filtro.setStatus(Constantes.STATUS_RM_MATERIAL_AGUARDANDO_RETIRADA);
            List<VwRmMaterial> lista = listarVwRmMaterial(filtro);
            RmMaterialRetiradaDao rmMaterialRetiradaDao = new RmMaterialRetiradaDao();
            Integer numProtocoloRetirada = rmMaterialRetiradaDao.numeroProtocoloMax() + 1;

            for (VwRmMaterial vw : lista) {
                RmMaterial rmMaterial = vw.getRmMaterial();

                RmMaterialRetiradaService service = new RmMaterialRetiradaService();
                RmMaterialRetirada retirada = new RmMaterialRetirada();
                retirada.setPreRetirada(Boolean.TRUE);
                retirada.setDataRetirada(new Date());
                retirada.setQuantidade(rmMaterial.getQuantidade());
                retirada.setRetiradoPor(getPessoaByCF(rmMaterial.getCfResponsavel()));
                if (retirada.getRetiradoPor() == null) {
                    retirada.setRetiradoPor(rmMaterial.getPessoaResponsavel());
                }
                retirada.setRmMaterial(rmMaterial);
                retirada.setNumeroProtocolo(numProtocoloRetirada);
                dao = new GenericDao<>();
                dao.beginTransaction();
                info = service.preRetirada(retirada, dao, true, false);
                boolean success = false;
                if (info.getCodigo().equals(Info.INFO_001)) {
                    info = service.retirarEstoque(retirada, dao, true, true, 0.0, null, false, null);
                    if (info.getCodigo().equals(Info.INFO_001)) {
                        success = true;
                        numProtocoloRetirada++;
                    }
                }
                if (success) {
                    dao.commitCurrentTransaction();
                } else {
                    dao.rollbackCurrentTransaction();
                    info.setObjeto(protocolo);
                    return info;
                }
            }
            if (!lista.isEmpty()) {
                // Envio para gerar as cautelas no cp
                enviaCautelaCp(lista);
            }
            info = Info.GetSuccess(protocolo);
        } catch (Exception e) {
            dao.rollbackCurrentTransaction();
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            info = Info.GetError(Constantes.ERRO_BAIXA_MATERIAL, protocolo);
        }
        return info;
    }

    private Pessoa getPessoaByCF(String cfResponsavel) {
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
            if(listaPessoa != null && !listaPessoa.isEmpty()){
                p = listaPessoa.get(0);
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return p;
    }

    /**
     * Envio a lista de materiais para o CPWEB gerar as cautelas
     *
     * @param listaMaterial
     */
    private void enviaCautelaCp(final List<VwRmMaterial> listaMaterial) {
        List<RmMaterial> listaRmMaterial = new ArrayList<>();
        for (VwRmMaterial obj : listaMaterial) {
            listaRmMaterial.add(obj.getRmMaterial());
        }
        if (!listaRmMaterial.isEmpty()) {
            SincCautelaService service = new SincCautelaService();
            service.enviaCautelaCpThread(listaRmMaterial);
        }
    }

    /**
     * Gero um Aprovador do tipo Lider de custos para que na tela de "Aprovação
     * Requisição" o lider de custos ou a equipe de custos possa informar o
     * Coletor de custo
     *
     * @author Alyson X. Leite
     * @param cadastroRmVo
     * @param genericDao
     * @return
     */
    private Info enviaLiderCustos(CadastroRmVo cadastroRmVo, GenericDao genericDao) {
        Info info;
        try {
            RmAprovador rmAprovador = new RmAprovador();
            rmAprovador.setRm(cadastroRmVo.getRm());
            rmAprovador.setAprovador(cadastroRmVo.getAprovador());
            rmAprovador.setAprovadorVez(Boolean.TRUE);
            rmAprovador.setOrdem(1);
            rmAprovador.setTipoAprovador(Constantes.TIPO_ATUACAO_LIDER_CUSTOS);

            List<Propriedade> propriedades = new ArrayList<>();
            Date dataAtual = new Date();

            propriedades.clear();
            propriedades.add(new Propriedade(Rm.DATA_ENVIO_APROVACAO));

            cadastroRmVo.getRm().setDataEnvioAprovacao(dataAtual);
            genericDao.updateWithCurrentTransaction(cadastroRmVo.getRm(), propriedades);

            genericDao.persistWithCurrentTransaction(rmAprovador);

            // Não é necessário gerar status de custos
            // Pois pela regra, o estoquista não precisa da aprovação do lider
            // para aceitar o material
            // Assim, o status que tem mais importancia é o aguardando estoque
            info = Info.GetSuccess();

        } catch (Exception e) {
            info = Info.GetError(e.getMessage());
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }
}
