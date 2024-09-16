/*
 * NextAge License
 * Copyright 2015 - Nextage
 * 
 */
package br.com.nextage.rmaweb.controller.relatorio;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.EapMultiEmpresa;
import br.com.nextage.rmaweb.entitybean.UnidadeMedida;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.filtro.FiltroRelatorioEstoqueInconsistencia;
import br.com.nextage.rmaweb.service.ConfIntegracaoServiceImpl;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.integracao.MaterialService;
import br.com.nextage.rmaweb.service.integracao.RelatorioEstoqueInconsistenciaService;
import br.com.nextage.rmaweb.service.integracao.RmaService;
import br.com.nextage.rmaweb.service.integracao.SincEquipamentoService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.MaterialInconsistenciaVo;
import br.com.nextage.rmaweb.vo.RelatorioEstoqueInconsistenciaVo;
import br.com.nextage.rmaweb.ws.cpweb.*;
import br.com.nextage.rmaweb.ws.sap.SoapHandlerSapService;
import br.com.nextage.rmaweb.ws.sap.consultarEstoque.BSRMAWEBSIConsultaEstoqueSyncOutXX;
import br.com.nextage.rmaweb.ws.sap.consultarEstoque.DTConsultaEstoque;
import br.com.nextage.rmaweb.ws.sap.consultarEstoque.DTConsultaEstoqueResponse;
import br.com.nextage.rmaweb.ws.sap.consultarEstoque.SIConsultaEstoqueSyncOut;
import br.com.nextage.util.Info;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import br.com.nextage.util.vo.ArquivoVo;
import br.com.nextage.util.vo.PrimitivoVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.util.*;

/**
 * @author Yuri Mello da Costa - <b>y.mello@nextage.com.br<b>
 */
@Path("RelatorioEstoqueInconsistencia")
public class RelatorioEstoqueInconsistenciaController {

    @Context
    HttpServletRequest request;

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    private List<Material> listaMaterialCpweb;

    /**
     * Lista os materiais
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("listarEstoqueRma")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<MaterialDeposito> listarEstoqueRma(FiltroRelatorioEstoqueInconsistencia filtro) {
        List<MaterialDeposito> retorno = null;
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroRelatorioEstoqueInconsistencia.class.getName(), Util.getNomeMetodoAtual(), filtro, null));
            RelatorioEstoqueInconsistenciaService service = new RelatorioEstoqueInconsistenciaService(request);
            retorno = service.listarEstoqueRma(filtro);
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroRelatorioEstoqueInconsistencia.class.getName(), Util.getNomeMetodoAtual(), filtro, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return retorno;
    }

    /**
     * Lista o material
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object listar(FiltroRelatorioEstoqueInconsistencia filtro) {
        Object retorno = null;
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroRelatorioEstoqueInconsistencia.class.getName(), Util.getNomeMetodoAtual(), filtro, null));
            retorno = listarQuantidadesSistemas(filtro);
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroRelatorioEstoqueInconsistencia.class.getName(), Util.getNomeMetodoAtual(), filtro, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return retorno;
    }

    /**
     * Lista o material
     * Autor: Lucas Heitor
     * Lista os sistemas para passar no modal de Entrada/Saida de Material.
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("listarSistemasEntradaSaida")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object listarSistemasEntradaSaida(FiltroRelatorioEstoqueInconsistencia filtro) {
        Object retorno = null;
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroRelatorioEstoqueInconsistencia.class.getName(), Util.getNomeMetodoAtual(), filtro, null));
            retorno = listarQuantidadesSistemas(filtro);
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroRelatorioEstoqueInconsistencia.class.getName(), Util.getNomeMetodoAtual(), filtro, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return retorno;
    }

    /**
     * Lista o Material Deposito para Alteração
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("listarDepositoAlteracao")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object listarDepositoAlteracao(FiltroRelatorioEstoqueInconsistencia filtro) {
        Object retorno = null;
        try {
            RelatorioEstoqueInconsistenciaService service = new RelatorioEstoqueInconsistenciaService(request);
            retorno = service.listarDepositoAlteracao(filtro);
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroRelatorioEstoqueInconsistencia.class.getName(), Util.getNomeMetodoAtual(), filtro, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return retorno;
    }

    /**
     * Gera o arquivo Excel
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("gerarXLS")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArquivoVo gerarXLS(FiltroRelatorioEstoqueInconsistencia filtro) {
        ArquivoVo vo = null;
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroRelatorioEstoqueInconsistencia.class.getName(), Util.getNomeMetodoAtual(), filtro, null));
            RelatorioEstoqueInconsistenciaService service = new RelatorioEstoqueInconsistenciaService(request);
            List<VwRmMaterial> lista = (List<VwRmMaterial>) listarQuantidadesSistemas(filtro);
            vo = service.gerarExcelPendencia(lista, filtro, filtro.getDeposito(), false);
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroRelatorioEstoqueInconsistencia.class.getName(), Util.getNomeMetodoAtual(), filtro, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return vo;
    }

    /**
     * Autor: Lucas Heitor
     * Verifica se o mesmo vai ser uma entrada ou saida do estoque.
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("salvarEntradaSaidaEstoque")
    @Consumes("application/json")
    public Info salvarEntradaSaidaEstoque(FiltroRelatorioEstoqueInconsistencia filtro) {
        Info info = new Info();
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroRelatorioEstoqueInconsistencia.class.getName(), Util.getNomeMetodoAtual(), null, null));
            RelatorioEstoqueInconsistenciaService service = new RelatorioEstoqueInconsistenciaService(request);

            //Verifico se é entrada ou saida do estoque.
            if (filtro.getOperacao().equals(Constantes.ENTRADA_ESTOQUE)) {
                info = entradaEstoque(filtro);
            } else if (filtro.getOperacao().equals(Constantes.SAIDA_ESTOQUE)) {
                info = saidaEstoque(filtro);
            }
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }


    /**
     * Realiza a entrada de estoque tanto no cp quanto no rma, fazendo as validações necessarias para o mesmo.
     *
     * @param filtro
     */
    private Info entradaEstoque(FiltroRelatorioEstoqueInconsistencia filtro) {
        Info info = new Info();
        Object retorno = null;
        GenericDao<MaterialDeposito> genericDao = new GenericDao<>();
        List<Propriedade> propriedades = new ArrayList<>();
        MaterialDeposito materialDeposito = new MaterialDeposito();
        MaterialDepositoEntrada materialDepositoEntrada = new MaterialDepositoEntrada();
        RmaService rmaService = new RmaService();
        try {
            Usuario user = LoginService.getUsuarioLogado(request);
            genericDao.beginTransaction();

            //Recupero o MaterialDeposito do RMA, se caso encontrar e for uma entrada de deposito do RMA, então
            //utilizo a mesma para adicionar as quantidades, se caso encontrar NULL, então, crio uma nova.

            materialDeposito = recuperaMaterialDeposito(filtro.getMaterial(), filtro.getDeposito());

            if (filtro.getCheckRma() != null && filtro.getCheckRma().equals(true)) {
                //Se caso existir já o materialDeposito, apenas atualizo o mesmo.
                if (materialDeposito != null) {
                    propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));
                    materialDeposito.setQuantidade(materialDeposito.getQuantidade() + filtro.getQuantidade());
                    genericDao.updateWithCurrentTransaction(materialDeposito, propriedades);
                    //Se não realizo o persist.
                } else {
                    materialDeposito = new MaterialDeposito();
                    materialDeposito.setMaterial(filtro.getMaterial());
                    materialDeposito.setQuantidade(filtro.getQuantidade());
                    materialDeposito.setDeposito(filtro.getDeposito());
                    genericDao.persistWithCurrentTransaction(materialDeposito);
                }

                //Após ter feito a atualização ou persist do MaterialDeposito, realizo a entrada do material.
                materialDepositoEntrada.setMaterialDeposito(materialDeposito);
                materialDepositoEntrada.setDataEntrada(new Date());
                materialDepositoEntrada.setQuantidade(filtro.getQuantidade());
                materialDepositoEntrada.setObservacao(filtro.getJustificativaAdicionarEstoque());
                materialDepositoEntrada.setRm(null);
                materialDepositoEntrada.setCdUsuInc((user.getLogin() == null ? "" : user.getLogin() + " - ") + user.getNome());

                genericDao.persistWithCurrentTransaction(materialDepositoEntrada);

            }
            if (filtro.getCheckCp() != null && filtro.getCheckCp().equals(true)) {
                if (rmaService.verificaEnvioParaCpweb(filtro.getMaterial())) {

                    String confCaminhoUrl = ConfiguracaoSingleton.getConfiguracao().getCaminhoUrlCpweb();
                    SincEquipamentoVo equipamentoVo = new SincEquipamentoVo();

                    //Configura TipoEquipamento
                    TipoEquipamento tipoEquipamento = new TipoEquipamento();
                    tipoEquipamento.setDescricao(filtro.getMaterial().getNome());
                    tipoEquipamento.setCodigo(filtro.getMaterial().getCodigo());
                    if (filtro.getMaterial().getHierarquia() == null || filtro.getMaterial().getHierarquia().trim().length() == 0) {
                        MaterialService matService = new MaterialService();
                        tipoEquipamento.setHierarquia(matService.getHierarquiaByMaterial(filtro.getMaterial()));
                    } else {
                        tipoEquipamento.setHierarquia(filtro.getMaterial().getHierarquia());
                    }
                    equipamentoVo.setTipoEquipamento(tipoEquipamento);
                    equipamentoVo.setPatrimoniado(filtro.getMaterial().getPatrimoniado());

                    //Configura Estoque
                    LocalEstoque estoque = new LocalEstoque();
                    estoque.setDescricao(filtro.getDeposito().getNome());
                    estoque.setCodigo(filtro.getDeposito().getCodigo());
                    equipamentoVo.setLocalEstoque(estoque);

                    //Configura Estoque
                    equipamentoVo.setQuantidade(filtro.getQuantidade());

                    //Configura Unidade Medida
                    br.com.nextage.rmaweb.ws.cpweb.UnidadeMedida unidadeMedida = new br.com.nextage.rmaweb.ws.cpweb.UnidadeMedida();
                    unidadeMedida.setDescricao(filtro.getMaterial().getUnidadeMedida().getDescricao());
                    equipamentoVo.setUnidadeMedida(unidadeMedida);
                    equipamentoVo.setNomeUsuario((user.getLogin() == null ? "" : user.getLogin() + " - ") + user.getNome());
                    equipamentoVo.setOrigem("Inventário - " + filtro.getJustificativaAdicionarEstoque());
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

                    Boolean habilitaEapMultiEmpresa = ConfiguracaoSingleton.getConfiguracao().getHabilitaEapMultiEmpresa();
                    if (habilitaEapMultiEmpresa != null && habilitaEapMultiEmpresa == true) {
                        if (filtro.getDeposito() != null && filtro.getDeposito().getDepositoId() != null) {
                            equipamentoVo.getLocalEstoque().setLocalEstoqueEap(obterCodigoEapMultiEmpresaDeposito(filtro.getDeposito().getDepositoId()));
                        }

                    }

                    SincEstoque_Service sincEstoque = new SincEstoque_Service(new URL(confCaminhoUrl));
                    br.com.nextage.rmaweb.ws.cpweb.Info infoCp = sincEstoque.getSincEstoquePort().enviaEquipamento(equipamentoVo);

                    if (infoCp.getErro() != null) {
                        genericDao.rollbackCurrentTransaction();
                        info.setCodigo(Info.INFO_002);
                        info.setTipo(Info.TIPO_MSG_DANGER);
                        info.setErro(infoCp.getErro());
                        info.setMensagem(infoCp.getMensagem());
                        return info;
                    }
                } else {
                    genericDao.rollbackCurrentTransaction();
                    info.setCodigo(Info.INFO_002);
                    info.setTipo(Info.TIPO_MSG_DANGER);
                    info.setErro("msg_material_nao_vai_cp");
                    info.setMensagem("msg_material_nao_vai_cp");
                    return info;
                }
            }

            genericDao.commitCurrentTransaction();

            info.setObjeto(retorno);
            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
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
     * Realiza uma saida de um estoque tanto no cp quanto no rma, fazendo as validações necessarias para o mesmo.
     *
     * @param filtro
     */
    private Info saidaEstoque(FiltroRelatorioEstoqueInconsistencia filtro) {
        Info info = new Info();
        Object retorno = null;
        GenericDao<MaterialDeposito> genericDao = new GenericDao<>();
        List<Propriedade> propriedades = new ArrayList<>();
        MaterialDeposito materialDeposito = new MaterialDeposito();
        RmaService rmaService = new RmaService();

        try {
            Usuario user = LoginService.getUsuarioLogado(request);
            genericDao.beginTransaction();
            //Verifico se é Rma, Cp
            if (filtro.getCheckRma() != null && filtro.getCheckRma().equals(true)) {
                //Verifico se o mesmo possui material deposito, se caso não possuir, então, não pode realizar a saida
                //Pois não tem este material no deposito solicitado.
                materialDeposito = recuperaMaterialDeposito(filtro.getMaterial(), filtro.getDeposito());

                Double quantidade;
                if (materialDeposito != null) {
                    //Se caso a quantidade for menor que 0, atribuio um valor negativo qualquer (-1.0)
                    //para não deixar realizar a saida, pois, se eu fazer a conta de - com negativo, o mesmo irá somer -(-)
                    //e irá liberar a saida do item.
                    if (materialDeposito.getQuantidade() < 0) {
                        quantidade = -1.0;
                    } else {
                        quantidade = materialDeposito.getQuantidade() - filtro.getQuantidade();
                    }
                    if (quantidade >= 0) {
                        propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));
                        materialDeposito.setQuantidade(materialDeposito.getQuantidade() - filtro.getQuantidade());
                        genericDao.updateWithCurrentTransaction(materialDeposito, propriedades);

                        MaterialDepositoSaida objMDS = new MaterialDepositoSaida();
                        objMDS.setMaterialDeposito(materialDeposito);
                        objMDS.setDataSaida(new Date());
                        objMDS.setReserva(false);
                        objMDS.setQuantidade(filtro.getQuantidade());
                        objMDS.setObservacao(filtro.getJustificativaAdicionarEstoque());
                        objMDS.setRm(null);
                        objMDS.setMaterialDepositoSaidaReserva(null);
                        objMDS.setRetiradoPor(user.getPessoa());
                        objMDS.setCdUsuInc((user.getLogin() == null ? "" : user.getLogin() + " - ") + user.getNome());

                        genericDao.persistWithCurrentTransaction(objMDS);
                    } else {
                        genericDao.rollbackCurrentTransaction();
                        info.setCodigo(Info.INFO_002);
                        info.setTipo(Info.TIPO_MSG_DANGER);
                        info.setErro("msg_qtde_indisponivel_dep");
                        info.setMensagem("msg_qtde_indisponivel_dep");
                        return info;
                    }
                } else {
                    genericDao.rollbackCurrentTransaction();
                    info.setCodigo(Info.INFO_002);
                    info.setTipo(Info.TIPO_MSG_DANGER);
                    info.setErro("label_item_indisponivel_no_deposito");
                    info.setMensagem("label_item_indisponivel_no_deposito");
                    return info;
                }
            }
            //Verifico se vai para o CP
            if (filtro.getCheckCp() != null && filtro.getCheckCp().equals(true)) {
                //Verifico se é valido o envio para o CPWEB
                if (rmaService.verificaEnvioParaCpweb(filtro.getMaterial())) {
                    if (filtro.getQuantidade() <= filtro.getQuantidadeCp()) {
                        br.com.nextage.rmaweb.ws.cpweb.Info infoCp = null;
                        //Verifico se o mesmo é patrimoniado, se for, então, preciso verificar o prefixoEquipamento
                        SincEquipamentoVo vo = new SincEquipamentoVo();
                        Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();

                        TipoEquipamento tipoEquipamento = new TipoEquipamento();
                        tipoEquipamento.setDescricao(filtro.getMaterial().getNome());
                        tipoEquipamento.setCodigo(filtro.getMaterial().getCodigo());
                        tipoEquipamento.setHierarquia(filtro.getMaterial().getHierarquia());
                        if (tipoEquipamento.getHierarquia() == null || tipoEquipamento.getHierarquia().trim().length() == 0) {
                            MaterialService matService = new MaterialService();
                            tipoEquipamento.setHierarquia(matService.getHierarquiaByMaterial(filtro.getMaterial()));
                        }
                        vo.setTipoEquipamento(tipoEquipamento);
                        vo.setPatrimoniado(filtro.getMaterial().getPatrimoniado());
                        if (filtro.getPrefixoEquipamento() != null) {
                            vo.setPrefixoEquipamento(filtro.getPrefixoEquipamento());
                        }

                        vo.setNomeUsuario(user.getNome());

                        //Configura Estoque
                        LocalEstoque estoque = new LocalEstoque();
                        estoque.setDescricao(filtro.getDeposito().getNome());
                        estoque.setCodigo(filtro.getDeposito().getCodigo());
                        vo.setLocalEstoque(estoque);

                        //Configura Quantidade
                        vo.setQuantidade(filtro.getQuantidade());

                        //Configura Unidade Medida
                        br.com.nextage.rmaweb.ws.cpweb.UnidadeMedida unidadeMedida = new br.com.nextage.rmaweb.ws.cpweb.UnidadeMedida();
                        unidadeMedida.setDescricao(filtro.getMaterial().getUnidadeMedida().getDescricao());
                        vo.setUnidadeMedida(unidadeMedida);
                        vo.setNomeUsuario((user.getLogin() == null ? "" : user.getLogin() + " - ") + user.getNome());
                        vo.setOrigem("Inventário - " + filtro.getJustificativaAdicionarEstoque());

                        //Altera a Origem no CPWEB
                        vo.setOrigem(vo.getOrigem() + " - " + Constantes.SAIDA_ESTOQUE_RMA);

                        SincEstoque_Service sincEstoque = new SincEstoque_Service(new URL(configuracao.getCaminhoUrlCpweb()));

                        Boolean habilitaEapMultiEmpresa = ConfiguracaoSingleton.getConfiguracao().getHabilitaEapMultiEmpresa();
                        if (filtro.getDeposito() != null && filtro.getDeposito().getDepositoId() != null && habilitaEapMultiEmpresa != null && habilitaEapMultiEmpresa == true) {
                            vo.getLocalEstoque().setLocalEstoqueEap(obterCodigoEapMultiEmpresaDeposito(filtro.getDeposito().getDepositoId()));
                        }


                        infoCp = sincEstoque.getSincEstoquePort().estornarEstoque(vo);

                        if (infoCp.getErro() != null) {
                            genericDao.rollbackCurrentTransaction();
                            info.setCodigo(Info.INFO_002);
                            info.setTipo(Info.TIPO_MSG_DANGER);
                            info.setErro(infoCp.getErro());
                            info.setMensagem(infoCp.getMensagem());
                            return info;
                        }

                    } else {
                        genericDao.rollbackCurrentTransaction();
                        info.setCodigo(Info.INFO_002);
                        info.setTipo(Info.TIPO_MSG_DANGER);
                        info.setErro("msg_qtde_indisponivel_dep");
                        info.setMensagem("msg_qtde_indisponivel_dep");
                        return info;
                    }
                } else {
                    genericDao.rollbackCurrentTransaction();
                    info.setCodigo(Info.INFO_002);
                    info.setTipo(Info.TIPO_MSG_DANGER);
                    info.setErro("msg_material_nao_vai_cp");
                    info.setMensagem("msg_material_nao_vai_cp");
                    return info;
                }
            }
            genericDao.commitCurrentTransaction();

            info.setObjeto(retorno);
            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
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

    private MaterialDeposito recuperaMaterialDeposito(Material material, Deposito deposito) {
        MaterialDeposito materialDeposito = new MaterialDeposito();
        GenericDao<MaterialDeposito> genericDao = new GenericDao<>();
        try {
            List<Propriedade> propriedades = new ArrayList<>();
            String aliasDeposito = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.DEPOSITO);
            String aliasMaterial = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL);

            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Deposito.DEPOSITO_ID, deposito.getDepositoId(), Filtro.EQUAL, aliasDeposito));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Material.MATERIAL_ID, material.getMaterialId(), Filtro.EQUAL, aliasMaterial)));

            materialDeposito = genericDao.selectUnique(propriedades, MaterialDeposito.class, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return materialDeposito;
    }

    /**
     * Lista os materiais dos sistemas
     *
     * @param filtro filtro da pesquisa
     * @return
     * @author Yuri Mello da Costa
     */
    private Object listarQuantidadesSistemas(FiltroRelatorioEstoqueInconsistencia filtro) {
        List<RelatorioEstoqueInconsistenciaVo> lista = new ArrayList<RelatorioEstoqueInconsistenciaVo>();


        List<MaterialDeposito> listaRma = new ArrayList<>();
        List<DTConsultaEstoqueResponse.MaterialDeposito> listaSap = new ArrayList<>();
        List<DTConsultaEstoqueResponse.MaterialDeposito> listaSapAux = new ArrayList<>();
        List<EquipamentoEstoque> listaCp = new ArrayList<>();
        List<EquipamentoEstoque> listaCpAux = new ArrayList<>();
        RmaService rmaService = new RmaService();


        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));

            //Lista estoques sistemas
            listaRma = listarEstoqueRma(filtro);
            listaSap = listarEstoqueSap(filtro);
            listaCp = listarEstoqueCp(filtro);

            //Busca uma lista com os depósitos não temporários
            Map<String, Deposito> hashDepositos = new TreeMap<String, Deposito>();
            for (Deposito deposito : rmaService.listarDepositosNaoTemporario()) {
                hashDepositos.put(deposito.getCodigo(), deposito);
            }

            Map<String, String> materiaisDeposito = new TreeMap<String, String>();
            Map<String, MaterialInconsistenciaVo> materiais = new TreeMap<String, MaterialInconsistenciaVo>();
            //Cria chave em hash para eliminar materiais depositos duplicados
            for (MaterialDeposito matDeposito : listaRma) {

                String chave = matDeposito.getMaterial().getCodigo() + ";" + matDeposito.getDeposito().getCodigo();
                String chave2 = null;
                if (matDeposito.getMaterial().getUnidadeMedida() != null && matDeposito.getMaterial().getUnidadeMedida().getDescricao() != null) {
                    chave2 = matDeposito.getMaterial().getNome() + ";" + matDeposito.getDeposito().getNome()
                            + ";" + matDeposito.getMaterial().getUnidadeMedida().getSigla();
                } else {
                    chave2 = matDeposito.getMaterial().getNome() + ";" + matDeposito.getDeposito().getNome();
                }

                materiaisDeposito.put(chave, chave2);

                MaterialInconsistenciaVo matVo = new MaterialInconsistenciaVo();
                matVo.setRma(true);
                if (matDeposito.getMaterial().getCodigo() != null) {
                    String key = matDeposito.getMaterial().getCodigo();
                    materiais.put(key, matVo);
                }

                if (matDeposito.getMaterial() != null) {
                    if (rmaService.verificaEnvioParaCpweb(matDeposito.getMaterial())) {
                        matVo.setCp(true);
                    }
                    if (matDeposito.getMaterial().getEstoqueSap() != null && matDeposito.getMaterial().getEstoqueSap().equals(Constantes.SIM_ABREVIADO)) {
                        matVo.setSap(true);
                    }
                }
            }

            //Agrupa valores de equipamento iguais
            Map<String, DTConsultaEstoqueResponse.MaterialDeposito> hashEstoqueSap = new TreeMap<String, DTConsultaEstoqueResponse.MaterialDeposito>();
            for (DTConsultaEstoqueResponse.MaterialDeposito voSap : listaSap) {
                //Concatenar cod material com o cod deposito.
                String chave = voSap.getCodMaterial() + ";" + voSap.getCodDeposito();

                if (!hashEstoqueSap.containsKey(chave)) {
                    hashEstoqueSap.put(chave, voSap);
                } else {
                    DTConsultaEstoqueResponse.MaterialDeposito get = hashEstoqueSap.get(chave);
                    Double qtdeAux = Double.parseDouble(get.getQuantidade()) + Double.parseDouble(voSap.getQuantidade());
                    get.setQuantidade(qtdeAux.toString());
                }

                if (!materiais.containsKey(voSap.getCodMaterial())) {
                    MaterialInconsistenciaVo matVo = new MaterialInconsistenciaVo();
                    matVo.setRma(true);
                    matVo.setSap(true);

                    if(searchMaterialEnviCpweb(voSap.getCodMaterial()) != null){
                        matVo.setCp(true);
                    }

                    materiais.put(voSap.getCodMaterial(), matVo);
                } else {
                    MaterialInconsistenciaVo get = materiais.get(voSap.getCodMaterial());
                    get.setSap(true);
                }
            }
            //Adiciona hash com agrupamento do valor
            listaSapAux = new ArrayList<>(hashEstoqueSap.values());
            for (DTConsultaEstoqueResponse.MaterialDeposito voSap : listaSapAux) {
                //Concatenar cod material com o cod deposito.
                String chave = voSap.getCodMaterial() + ";" + voSap.getCodDeposito();
                //busca o deposito para puxar as informações do mesmo.
                Deposito deposito = null;
                if (hashDepositos.containsKey(voSap.getCodDeposito())) {
                    deposito = hashDepositos.get(voSap.getCodDeposito());
                }
                String chave2 = voSap.getNomeMaterial() + ";" + deposito.getNome()
                        + ";-";

                if (!materiaisDeposito.containsKey(chave)) {
                    materiaisDeposito.put(chave, chave2);
                }
            }

            //Agrupa valores de equipamento iguais
            Map<String, EquipamentoEstoque> hashEstoqueCp = new TreeMap<String, EquipamentoEstoque>();
            for (EquipamentoEstoque voCp : listaCp) {
                //Concatenar cod material com o cod deposito.
                String chave = voCp.getEquipamento().getTipoEquipamento().getCodigo() + ";" + voCp.getLocalEstoque().getCodigo();

                if (!hashEstoqueCp.containsKey(chave)) {
                    hashEstoqueCp.put(chave, voCp);
                } else {
                    EquipamentoEstoque get = hashEstoqueCp.get(chave);
                    Double qtdeAux = get.getQtde() + voCp.getQtde();
                    get.setQtde(qtdeAux);
                }

                if (!materiais.containsKey(voCp.getEquipamento().getTipoEquipamento().getCodigo())) {
                    MaterialInconsistenciaVo matVo = new MaterialInconsistenciaVo();
                    matVo.setRma(true);
                    matVo.setCp(true);
                    materiais.put(voCp.getEquipamento().getTipoEquipamento().getCodigo(), matVo);
                } else {
                    MaterialInconsistenciaVo get = materiais.get(voCp.getEquipamento().getTipoEquipamento().getCodigo());
                    get.setCp(true);
                }
            }

            //Adiciona hash com agrupamento do valor
            listaCpAux = new ArrayList<>(hashEstoqueCp.values());
            for (EquipamentoEstoque voCp : listaCpAux) {
                //Concatenar cod material com o cod deposito.
                String chave = voCp.getEquipamento().getTipoEquipamento().getCodigo() + ";" + voCp.getLocalEstoque().getCodigo();
                String chave2 = voCp.getEquipamento().getTipoEquipamento().getDescricao() + ";" + voCp.getLocalEstoque().getDescricao()
                        + ";" + voCp.getEquipamento().getTipoEquipamento().getUnidadeMedida().getDescricao();

                if (!materiaisDeposito.containsKey(chave)) {
                    materiaisDeposito.put(chave, chave2);
                }
            }


            //Monta VO com as quantidades para o material deposito em cada sistema.
            for (String chave : materiaisDeposito.keySet()) {

                String valor = materiaisDeposito.get(chave);
                String[] splitValue = valor.split(";");
                final String nomeMat = splitValue[0];
                final String nomeDep = splitValue[1];
                String unMed = null;
                if (splitValue.length > 2) {
                    unMed = splitValue[2];
                }
                RelatorioEstoqueInconsistenciaVo vo = new RelatorioEstoqueInconsistenciaVo();
                vo.setNomeMaterial(nomeMat);
                vo.setNomeDeposito(nomeDep);
                if (unMed != null) {
                    vo.setUnidadeMedida(unMed);
                }
                String[] splitChave = chave.split(";");
                final String codMaterial = splitChave[0];
                final String codDeposito = splitChave[1];

                vo.setCodMaterial(codMaterial);
                vo.setCodDeposito(codDeposito);

                //Set qtde Rma
                MaterialDeposito materialDeposito = (MaterialDeposito) CollectionUtils.find(listaRma, new Predicate() {
                    @Override
                    public boolean evaluate(Object o) {
                        MaterialDeposito obj = (MaterialDeposito) o;
                        return obj.getMaterial() != null && obj.getMaterial().getCodigo() != null &&
                                obj.getMaterial().getCodigo().equals(codMaterial) &&
                                obj.getDeposito().getCodigo().equals(codDeposito);
                    }
                });
                if (materialDeposito != null) {
                    double qtdeRma = materialDeposito.getQuantidade();
                    vo.setQtdeRma(qtdeRma);
                }

                //Qtde SAP
                DTConsultaEstoqueResponse.MaterialDeposito materialDepositoSap = (DTConsultaEstoqueResponse.MaterialDeposito) CollectionUtils.find(listaSapAux, new Predicate() {
                    @Override
                    public boolean evaluate(Object o) {
                        DTConsultaEstoqueResponse.MaterialDeposito obj = (DTConsultaEstoqueResponse.MaterialDeposito) o;
                        return obj.getCodMaterial() != null && obj.getCodMaterial().equals(codMaterial) && obj.getCodDeposito().equals(codDeposito);
                    }
                });
                if (materialDepositoSap != null) {
                    double qtdeSap = materialDepositoSap.getQuantidade() != null ? Double.parseDouble(materialDepositoSap.getQuantidade()) : 0.0;
                    vo.setQtdeSap(qtdeSap);
                }

                //Qtde CPWEB
                EquipamentoEstoque equipamentoEstoque = (EquipamentoEstoque) CollectionUtils.find(listaCpAux, new Predicate() {
                    @Override
                    public boolean evaluate(Object o) {
                        EquipamentoEstoque obj = (EquipamentoEstoque) o;
                        return obj.getEquipamento() != null && obj.getEquipamento().getTipoEquipamento().getCodigo() != null &&
                                obj.getEquipamento().getTipoEquipamento().getCodigo().equals(codMaterial) &&
                                obj.getLocalEstoque().getCodigo().equals(codDeposito);
                    }
                });
                if (equipamentoEstoque != null) {
                    double qtdeCp = equipamentoEstoque.getQtde();
                    vo.setQtdeCp(qtdeCp);
                }

                MaterialInconsistenciaVo matVo = null;
                if (materiais.containsKey(codMaterial)) {
                    matVo = materiais.get(codMaterial);
                }

                if (matVo != null) {
                    //Valida inconsistencia RMA - CP
                    if (matVo.getRma() != null && matVo.getCp() != null && matVo.getRma() == true && matVo.getCp() == true) {
                        if (vo.getQtdeCp() != vo.getQtdeRma()) {
                            vo.setInconsistenteRmaCp(true);
                        } else {
                            vo.setInconsistenteRmaCp(false);
                        }
                    }

                    //Valida incnsistencia RMA - SAP
                    if (matVo.getRma() != null && matVo.getSap() != null && matVo.getRma() == true && matVo.getSap() == true) {
                        if (vo.getQtdeSap() != vo.getQtdeRma()) {
                            vo.setInconsistenteRmaSap(true);
                        } else {
                            vo.setInconsistenteRmaSap(false);
                        }
                    }

                    //Valida inconsistência CP - SAP
                    if (matVo.getCp() != null && matVo.getSap() != null && matVo.getCp() == true && matVo.getSap() == true) {
                        if (vo.getQtdeSap() != vo.getQtdeCp()) {
                            vo.setInconsistenteCpSap(true);
                        } else {
                            vo.setInconsistenteCpSap(false);
                        }
                    }
                }

                if (filtro.getInconsistencia() != null && filtro.getInconsistencia() == true) {
                    if ((vo.getInconsistente() != null && vo.getInconsistente() == true) ||
                            (vo.getInconsistenteRmaCp() != null && vo.getInconsistenteRmaCp() == true) ||
                            (vo.getInconsistenteCpSap() != null && vo.getInconsistenteCpSap() == true) ||
                            (vo.getInconsistenteRmaSap() != null && vo.getInconsistenteRmaSap() == true)) {
                        lista.add(vo);
                    }
                } else {
                    lista.add(vo);
                }
            }

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    private List<DTConsultaEstoqueResponse.MaterialDeposito> listarEstoqueSap(FiltroRelatorioEstoqueInconsistencia filtro) {

        List<DTConsultaEstoqueResponse.MaterialDeposito> lista = new ArrayList<>();
        try {
            //recupera configurações do sistema.
            Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();
			ConfIntegracaoServiceImpl confIntegracaoService = new ConfIntegracaoServiceImpl();
            ConfIntegracao confIntegracao = confIntegracaoService.listarConfIntegracao(Constantes.CODIGO_INTEGRACAO_CONSULTAR_ESTOQUE);

            FiltroEquipamentoEstoqueVo filtroVo = new FiltroEquipamentoEstoqueVo();
            filtroVo.setCodDep(filtro.getDeposito().getCodigo());

            DTConsultaEstoque dTConsultaEstoque = new DTConsultaEstoque();
            dTConsultaEstoque.setCodDeposito(filtro.getDeposito().getCodigo());

            if (configuracao.getHabilitaEapMultiEmpresa() && filtro.getDeposito() != null) {
                dTConsultaEstoque.setCentro(getEapMultiEmpresabyDeposito(filtro.getDeposito()).getCentro());
            } else{
                dTConsultaEstoque.setCentro(configuracao.getCentroCod().split(";")[0]);
            }

            if (filtro.getMaterial() != null) {
                dTConsultaEstoque.getCodMaterial().add(filtro.getMaterial().getCodigo());
            }


            if(filtro.getMaterialChave() != null && !filtro.getMaterialChave().isEmpty()){
                List<Material> materiaisLike = listarMateriais(filtro.getMaterialChave());
                for(Material materialLike : materiaisLike){
                    dTConsultaEstoque.getCodMaterial().add(materialLike.getCodigo());
                }
            }

            confIntegracaoService.autenticar(confIntegracao.getLogin(), confIntegracao.getSenha(), confIntegracao.getUrl());

            //Busca o serviço SAP instanciando com a URL do WSDL configurada no BD, caso não houver, usa a padrão que foi gerada.
            BSRMAWEBSIConsultaEstoqueSyncOutXX bSRMAWEBSIConsultaEstoqueSyncOutXX = null;
            if (confIntegracao.getUrlWsdlService() != null) {
                bSRMAWEBSIConsultaEstoqueSyncOutXX = new BSRMAWEBSIConsultaEstoqueSyncOutXX(new URL(confIntegracao.getUrlWsdlService()));
            } else {
                bSRMAWEBSIConsultaEstoqueSyncOutXX = new BSRMAWEBSIConsultaEstoqueSyncOutXX();
            }
            SIConsultaEstoqueSyncOut sICriaSaidaSyncOut = bSRMAWEBSIConsultaEstoqueSyncOutXX.getHTTPPort();

            BindingProvider bp = (BindingProvider) sICriaSaidaSyncOut;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, confIntegracao.getUrl());

            Binding binding = ((BindingProvider) sICriaSaidaSyncOut).getBinding();
            List handlers = binding.getHandlerChain();
            handlers.add(new SoapHandlerSapService());
            binding.setHandlerChain(handlers);

            DTConsultaEstoqueResponse dTConsultaEstoqueResponse = sICriaSaidaSyncOut.siConsultaEstoqueSyncOut(dTConsultaEstoque);
            lista = dTConsultaEstoqueResponse.getMaterialDeposito();

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return lista;
    }

    /**
     * Carrega a eap multi empresa por deposito
     *
     * @param deposito
     * @return EapMultiEmpresa
     */
    public EapMultiEmpresa getEapMultiEmpresabyDeposito(Deposito deposito) {
        EapMultiEmpresa eap = new EapMultiEmpresa();

        GenericDao<Deposito> genericDao = new GenericDao<>();

        try {
            List<Propriedade> propriedades = new ArrayList<>();

            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));

            NxCriterion nxCriterion;
            nxCriterion = NxCriterion.montaRestriction(new Filtro(Deposito.DEPOSITO_ID, deposito.getDepositoId(), Filtro.EQUAL));

            Deposito d = genericDao.selectUnique(propriedades, Deposito.class, nxCriterion);

            eap = d.getEapMultiEmpresa();

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return eap;
    }

    /**
     * Lista todas os materiais do BD.
     */
    private List<Material> listarMateriais(String nomeLike) {
        List<Material> list = new ArrayList<>();
        try {
            String aliasTipoMaterial = NxCriterion.montaAlias(Material.ALIAS_CLASSE, Material.TIPO_MATERIAL);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Material.MATERIAL_ID));
            propriedades.add(new Propriedade(Material.CODIGO));
            propriedades.add(new Propriedade(Material.HIERARQUIA));

            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));

            NxCriterion criterion;
            //Somente ativos
            NxCriterion criterionAux = NxCriterion.montaRestriction(new Filtro(Material.STATUS, Constantes.ATIVO_ABREVIADO, Filtro.EQUAL));
            criterionAux = NxCriterion.or(criterionAux, NxCriterion.montaRestriction(new Filtro(Material.STATUS, null, Filtro.IS_NULL)));
            criterion = criterionAux;

            //Somente com codigos
            criterionAux = NxCriterion.montaRestriction(new Filtro(Material.CODIGO, null, Filtro.NOT_NULL));
            criterion = NxCriterion.and(criterion, criterionAux);

            if(nomeLike != null){
                criterionAux = NxCriterion.montaRestriction(new Filtro(Material.NOME, nomeLike, Filtro.LIKE));
                criterion = NxCriterion.and(criterion, criterionAux);
            }else{
                //Somente não cauteláveis.
                criterionAux = NxCriterion.montaRestriction(new Filtro(TipoMaterial.CODIGO, Constantes.CAUTELAVEL, Filtro.EQUAL, aliasTipoMaterial));
                //E somente diferentes de EPI
                Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
                if (conf != null && conf.getCodigoEpi() != null && !conf.getCodigoEpi().isEmpty()) {
                    List<String> listaEpi = new ArrayList(Arrays.asList(conf.getCodigoEpi().split(";")));
                    criterionAux = NxCriterion.or(criterionAux, NxCriterion.montaRestriction(new Filtro(Material.HIERARQUIA, listaEpi, Filtro.IN)));
                    criterion = NxCriterion.and(criterion, criterionAux);
                }
            }

            GenericDao genericDao = new GenericDao();

            List<NxOrder> orders = Arrays.asList(new NxOrder(Material.NOME_COMPLETO, NxOrder.NX_ORDER.ASC));

            list = genericDao.listarByFilter(propriedades, orders, Material.class, Constantes.NO_LIMIT, criterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return list;
    }

    /**
     * Pesquisa unidade com base no nome e na sigla passado por parametro.
     *
     * @param codigo
     * @return
     */
    private Material searchMaterialEnviCpweb(String codigo) {
        Material material = null;
        try {
            if (codigo != null) {
                if (listaMaterialCpweb == null || listaMaterialCpweb.isEmpty()) {
                    listaMaterialCpweb = listarMateriais(null);
                }

                for (Material m : listaMaterialCpweb) {
                    if (codigo.equals(m.getCodigo())) {
                        return m;
                    }
                }
            }

            //material = novoMaterial(codigo);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return material;
    }

    /**
     * Método responsável para listagem do CPWEB
     *
     * @param filtro
     * @return
     */
    private List<EquipamentoEstoque> listarEstoqueCp(FiltroRelatorioEstoqueInconsistencia filtro) {
        List<EquipamentoEstoque> lista = new ArrayList<>();
        try {
            FiltroEquipamentoEstoqueVo filtroVo = new FiltroEquipamentoEstoqueVo();
            filtroVo.setCodDep(filtro.getDeposito().getCodigo());

            if (filtro.getMaterial() != null) {
                filtroVo.setCodMat(filtro.getMaterial().getCodigo());
            }

            if (filtro.getMaterialChave() != null) {
                filtroVo.setNomeMat(filtro.getMaterialChave());
            }



            String confCaminhoUrl = ConfiguracaoSingleton.getConfiguracao().getCaminhoUrlCpweb();

            SincEstoque_Service sincEstoque = new SincEstoque_Service(new URL(confCaminhoUrl));
            lista = sincEstoque.getSincEstoquePort().listarEstoque(filtroVo);

        } catch (Exception e) {
        	e.printStackTrace();
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    @POST
    @Path("listarCpQtdePatrimoniados")
    @Consumes("application/json")
    public PrimitivoVo listarCpQtdePatrimoniados(FiltroRelatorioEstoqueInconsistencia filtro) {
        FiltroEquipamentoEstoqueVo filtroEquipamentoEstoqueVo = new FiltroEquipamentoEstoqueVo();
        PrimitivoVo primitivoVo = new PrimitivoVo();
        primitivoVo.setValorDouble(Double.MAX_VALUE);


        try {
            Boolean habilitaEapMultiEmpresa = ConfiguracaoSingleton.getConfiguracao().getHabilitaEapMultiEmpresa();
            if (habilitaEapMultiEmpresa != null && habilitaEapMultiEmpresa == true) {
                if (filtro.getDeposito() != null && filtro.getDeposito().getEapMultiEmpresa() != null && filtro.getDeposito().getEapMultiEmpresa().getCodigo() != null) {
                    filtroEquipamentoEstoqueVo.setEapDeposito(filtro.getDeposito().getEapMultiEmpresa().getCodigo());
                }
            }

            String confCaminhoUrl = ConfiguracaoSingleton.getConfiguracao().getCaminhoUrlCpweb();
            filtroEquipamentoEstoqueVo.setCodDep(filtro.getDeposito().getCodigo());
            filtroEquipamentoEstoqueVo.setCodMat(filtro.getMaterial().getCodigo());
            SincEstoque_Service sincEstoque = new SincEstoque_Service(new URL(confCaminhoUrl));
            Double quantidade = sincEstoque.getSincEstoquePort().listarCpPatrimoniados(filtroEquipamentoEstoqueVo);

            primitivoVo.setValorDouble(quantidade);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return primitivoVo;
    }

    @POST
    @Path("listarCpQtdeNaoPatrimoniados")
    @Consumes("application/json")
    public PrimitivoVo listarCpQtdeNaoPatrimoniados(FiltroRelatorioEstoqueInconsistencia filtro) {
        FiltroEquipamentoEstoqueVo filtroEquipamentoEstoqueVo = new FiltroEquipamentoEstoqueVo();
        PrimitivoVo primitivoVo = new PrimitivoVo();
        primitivoVo.setValorDouble(Double.MAX_VALUE);

        try {
            String confCaminhoUrl = ConfiguracaoSingleton.getConfiguracao().getCaminhoUrlCpweb();
            Boolean habilitaEapMultiEmpresa = ConfiguracaoSingleton.getConfiguracao().getHabilitaEapMultiEmpresa();
            if (habilitaEapMultiEmpresa != null && habilitaEapMultiEmpresa == true) {
                if (filtro.getDeposito() != null && filtro.getDeposito().getEapMultiEmpresa() != null && filtro.getDeposito().getEapMultiEmpresa().getCodigo() != null) {
                    filtroEquipamentoEstoqueVo.setEapDeposito(filtro.getDeposito().getEapMultiEmpresa().getCodigo());
                }
            }

            filtroEquipamentoEstoqueVo.setCodDep(filtro.getDeposito().getCodigo());
            filtroEquipamentoEstoqueVo.setCodMat(filtro.getMaterial().getCodigo());
            SincEstoque_Service sincEstoque = new SincEstoque_Service(new URL(confCaminhoUrl));
            Double quantidade = sincEstoque.getSincEstoquePort().listarCpNaoPatrimoniados(filtroEquipamentoEstoqueVo);

            primitivoVo.setValorDouble(quantidade);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return primitivoVo;
    }

    @POST
    @Path("listarCpAndRma")
    @Consumes("application/json")
    public RelatorioEstoqueInconsistenciaVo listarCpAndRma(FiltroRelatorioEstoqueInconsistencia filtro) {
        RelatorioEstoqueInconsistenciaVo vo = new RelatorioEstoqueInconsistenciaVo();
        PrimitivoVo primitivoVo;

        try {
            //Verifico quantidade do cp não patrimoniada
            primitivoVo = listarCpQtdeNaoPatrimoniados(filtro);
            if (primitivoVo.getValorDouble() != null) {
                vo.setQtdeCpNaoPatrimoniado(primitivoVo.getValorDouble());
            }
            //Verifico quanitade no rma
            Double quantidadeRma = listarQtdeRma(filtro);
            if (quantidadeRma != null) {
                vo.setQtdeRma(quantidadeRma);
            }
            //Verifico quanitade no cp patrimoniada
            primitivoVo.setValorDouble(null);
            primitivoVo = listarCpQtdePatrimoniados(filtro);
            if (primitivoVo.getValorDouble() != null) {
                vo.setQtdeCpPatrimoniado(primitivoVo.getValorDouble());
            }
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return vo;
    }

    @POST
    @Path("listarMatAndDep")
    @Consumes("application/json")
    public MaterialDeposito listarMatAndDep(FiltroRelatorioEstoqueInconsistencia filtro) {
        MaterialDeposito materialDeposito = new MaterialDeposito();
        Material material = new Material();
        Deposito deposito = new Deposito();

        try {
            List<Propriedade> propriedades = new ArrayList<>();

            String aliasUnidadeMedida = NxCriterion.montaAlias(Material.ALIAS_CLASSE, Material.UNIDADE_MEDIDA);
            String aliasTipoMaterial = NxCriterion.montaAlias(Material.ALIAS_CLASSE, Material.TIPO_MATERIAL);

            //Material
            propriedades.add(new Propriedade(Material.MATERIAL_ID));
            propriedades.add(new Propriedade(Material.CODIGO));
            propriedades.add(new Propriedade(Material.NOME));
            propriedades.add(new Propriedade(Material.NOME_COMPLETO));
            propriedades.add(new Propriedade(Material.PATRIMONIADO));

            //materialDeposito -> material -> Unidade Medida
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            //materialDeposito -> material -> tipoMaterial
            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.DESCRICAO, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));

            NxCriterion nxCriterion = null;
            nxCriterion = NxCriterion.montaRestriction(new Filtro(Material.CODIGO, filtro.getMaterial().getCodigo(), Filtro.EQUAL));

            GenericDao<Material> genericDaoMat = new GenericDao<>();
            material = genericDaoMat.selectUnique(propriedades, Material.class, nxCriterion);

            propriedades.clear();
            //Deposito
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));
            propriedades.add(new Propriedade(Deposito.CODIGO));
            propriedades.add(new Propriedade(Deposito.NOME));

            nxCriterion = null;
            nxCriterion = NxCriterion.montaRestriction(new Filtro(Deposito.CODIGO, filtro.getDeposito().getCodigo(), Filtro.EQUAL));

            GenericDao<Deposito> genericDaoDep = new GenericDao<>();
            deposito = genericDaoDep.selectUnique(propriedades, Deposito.class, nxCriterion);

            materialDeposito.setMaterial(material);
            materialDeposito.setDeposito(deposito);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return materialDeposito;
    }


    /**
     * Recebe do CPWEB os equipamentos que estão pastrimoniados e os que não tem patrimonio
     *
     * @param prefixoEquipamentoVo
     * @return
     * @Autor Lucas Heitor
     */
    @POST
    @Path("autoCompletePrefixoEquipamento")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info autoCompletePrefixoEquipamento(br.com.nextage.rmaweb.vo.PrefixoEquipamentoVo prefixoEquipamentoVo) {
        Info info = new Info();
        List<String> lista = new ArrayList<>();

        SincEquipamentoService sincReservaService = new SincEquipamentoService();
        info = sincReservaService.listaPrefixoEquipamento(prefixoEquipamentoVo);

        if (info.getErro() != null) {
            return info;
        } else {
            //Se caso não der erro, o mesmo desconcatena a string que tem ; separando em uma lista o mesmo que veio do CPWEB
            lista = new ArrayList<String>(Arrays.asList(info.getObjeto().toString().split(";")));
            lista.removeAll(Arrays.asList("", null, " "));
            info.setObjeto(lista);
        }
        return info;
    }

    /**
     * Responsável de trazer a quantidade do material e deposito solicitado no RMA
     *
     * @param filtro
     * @return
     */
    public Double listarQtdeRma(FiltroRelatorioEstoqueInconsistencia filtro) {
        MaterialDeposito materialDeposito = new MaterialDeposito();

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
            List<Propriedade> propriedades = new ArrayList<>();

            String aliasMaterial = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.DEPOSITO);
            String aliasUnidadeMedida = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL, Material.UNIDADE_MEDIDA);

            //materialDeposito
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

            //materialDeposito -> material
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));

            //materialDeposito > deposito
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            //materialDeposito -> material -> Unidade Medida
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            NxCriterion nxCriterionAux = null;
            NxCriterion nxCriterion = null;

            if (filtro != null) {
                if (filtro.getDeposito() != null) {
                    nxCriterion = NxCriterion.and(nxCriterion,
                            NxCriterion.montaRestriction(new Filtro(Deposito.DEPOSITO_ID, filtro.getDeposito().getDepositoId(), Filtro.EQUAL, aliasDeposito)));
                }
                if (filtro.getMaterial() != null) {
                    nxCriterion = NxCriterion.and(nxCriterion,
                            NxCriterion.montaRestriction(new Filtro(Material.CODIGO, filtro.getMaterial().getCodigo(), Filtro.EQUAL, aliasMaterial)));
                }
            }

            GenericDao<MaterialDeposito> genericDao = new GenericDao<>();
            materialDeposito = genericDao.selectUnique(propriedades, MaterialDeposito.class, nxCriterion);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        if (materialDeposito == null) {
            return 0.0;
        }
        return materialDeposito.getQuantidade();
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
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        if (deposito != null && deposito.getEapMultiEmpresa() != null && deposito.getEapMultiEmpresa().getCodigo() != null) {
            return deposito.getEapMultiEmpresa().getCodigo();
        }

        return null;
    }

}
