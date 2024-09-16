/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.controller;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.persistence_2.util.Paginacao;
import br.com.nextage.persistence_2.vo.PaginacaoVo;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.filtro.FiltroAdministrador;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.MaterialDepositoEntradaService;
import br.com.nextage.rmaweb.service.integracao.*;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.AdministradorVo;
import br.com.nextage.rmaweb.vo.EnviaRetiradaVo;
import br.com.nextage.rmaweb.vo.MaterialTransferenciaVo;
import br.com.nextage.rmaweb.ws.sap.CompraMateriaisSapService;
import br.com.nextage.rmaweb.ws.sap.ReservaMateriaisSapService;
import br.com.nextage.rmaweb.ws.sap.RetiradaMateriaisSapService;
import br.com.nextage.util.Info;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import com.google.gson.Gson;
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
import java.util.Date;
import java.util.List;

/**
 * @author Daniel H. Parisotto
 */
@Path("Administrador")
public class AdministradorController {

    @Context
    HttpServletRequest request;

    private static final Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    /**
     * Lista os sincregistros que estão ativos
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PaginacaoVo listar(FiltroAdministrador filtro) {
        List<Propriedade> propriedades = new ArrayList<>();
        Usuario user = LoginService.getUsuarioLogado(request);
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroAdministrador.class.getName(), Util.getNomeMetodoAtual(), filtro, null));

            propriedades.add(new Propriedade(SincRegistro.ID));
            propriedades.add(new Propriedade(SincRegistro.ATIVO));
            propriedades.add(new Propriedade(SincRegistro.DATA_HORA));
            propriedades.add(new Propriedade(SincRegistro.FUNCIONALIDADE));
            propriedades.add(new Propriedade(SincRegistro.OBSERVACAO));
            propriedades.add(new Propriedade(SincRegistro.SISTEMA));
            propriedades.add(new Propriedade(SincRegistro.QUANTIDADE));
            propriedades.add(new Propriedade(SincRegistro.AGRUPADOR_ERRO));
            propriedades.add(new Propriedade(SincRegistro.ID_REGISTRO));
            propriedades.add(new Propriedade(SincRegistro.RMA_CONCATENA));
            propriedades.add(new Propriedade(SincRegistro.RM_MATERIAL_CONCATENA));
            propriedades.add(new Propriedade(SincRegistro.JSON));
            propriedades.add(new Propriedade(SincRegistro.NOME_CLASSE));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(SincRegistro.ATIVO, Constantes.SIM_ABREVIADO, Filtro.EQUAL));

            if("S".equals(user.getFlAdmin())) {
                if(Constantes.SISTEMA_SAP.equals(filtro.getSistema())) {
                    nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(SincRegistro.SISTEMA, Constantes.SISTEMA_SAP, Filtro.EQUAL)));
                }else if(Constantes.SISTEMA_CPWEB.equals(filtro.getSistema())){
                    nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(SincRegistro.SISTEMA, Constantes.SISTEMA_CPWEB, Filtro.EQUAL)));
                }
            }else{
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(SincRegistro.SISTEMA, Constantes.SISTEMA_SAP, Filtro.EQUAL)));
            }

            if (filtro.getNumeroRm() != null && !filtro.getNumeroRm().isEmpty()) {
                filtro.setNumeroRm(filtro.getNumeroRm().replaceAll(",", ";"));
                String[] listaRma = filtro.getNumeroRm().split(";");
                NxCriterion nx = null;
                for (String rma : listaRma) {
                    if (rma == null || rma.isEmpty()) {
                        continue;
                    }
                    rma = rma.trim();
                    NxCriterion nxAux = NxCriterion.montaRestriction(new Filtro(SincRegistro.RMA_CONCATENA, rma, Filtro.LIKE));
                    nxAux = NxCriterion.or(nxAux, NxCriterion.montaRestriction(new Filtro(SincRegistro.RMA_CONCATENA, rma, Filtro.LIKE)));
                    if (nx != null) {
                        nx = NxCriterion.or(nx, nxAux);
                    } else {
                        nx = nxAux;
                    }
                }
                nxCriterion = NxCriterion.and(nx, nxCriterion);
            }

            if (filtro.getStatus() != null && !filtro.getStatus().isEmpty()) {
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(SincRegistro.FUNCIONALIDADE, filtro.getStatus(), Filtro.EQUAL)));
            }

            Paginacao.build(propriedades, null, SincRegistro.class, nxCriterion, filtro.getPaginacaoVo());

            logger.debug(ResourceLogUtil.createMessageDebug(user.getNome(), this.getClass().getName(), FiltroAdministrador.class.getName(), Util.getNomeMetodoAtual(), filtro, null));

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(user.getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return filtro.getPaginacaoVo();
    }

    /**
     * Carrega as informações de materiais do registro selecionado
     *
     * @param sincRegistro
     * @return
     */
    @POST
    @Path("selectById")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AdministradorVo selectById(SincRegistro sincRegistro) {
        AdministradorVo administradorVo = new AdministradorVo();
        administradorVo.setRecebidoCp(false);
        List<RmMaterial> listaRmMaterial = new ArrayList<>();
        NxCriterion nxCriterion = null;

        String aliasRmMaterial = null;
        Class rmMClass = null;
        String aliasRm = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);
        String aliasRmDeposito = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.DEPOSITO_ID);
        String aliasMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL);
        String aliasUnidadeMedida = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL, Material.UNIDADE_MEDIDA);


        if (sincRegistro.getFuncionalidade().equals(Constantes.CODIGO_INTEGRACAO_RETIRADA_MATERIAL)) {
            rmMClass = RmMaterial.class;
            aliasRm = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.RM);
            aliasRmMaterial = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL);
            aliasRmDeposito = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.RM, Rm.DEPOSITO);
            aliasMaterial = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.MATERIAL);
            aliasUnidadeMedida = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.MATERIAL, Material.UNIDADE_MEDIDA);

        }
        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, rmMClass, aliasRmMaterial));
        propriedades.add(new Propriedade(RmMaterial.QUANTIDADE, rmMClass, aliasRmMaterial));
        propriedades.add(new Propriedade(RmMaterial.LOCAL_ENTREGA, rmMClass, aliasRmMaterial));
        propriedades.add(new Propriedade(RmMaterial.OBSERVACAO, rmMClass, aliasRmMaterial));
        propriedades.add(new Propriedade(RmMaterial.FLUXO_MATERIAL, rmMClass, aliasRmMaterial));
        propriedades.add(new Propriedade(RmMaterial.ESTA_NO_ORCAMENTO, rmMClass, aliasRmMaterial));
        propriedades.add(new Propriedade(RmMaterial.VALOR_ORCADO, rmMClass, aliasRmMaterial));
        propriedades.add(new Propriedade(RmMaterial.OBSERVACAO_CUSTO, rmMClass, aliasRmMaterial));
        propriedades.add(new Propriedade(RmMaterial.COLETOR_CUSTOS, rmMClass, aliasRmMaterial));
        propriedades.add(new Propriedade(RmMaterial.COLETOR_ORDEM, rmMClass, aliasRmMaterial));
        propriedades.add(new Propriedade(RmMaterial.DIAGRAMA_REDE, rmMClass, aliasRmMaterial));
        propriedades.add(new Propriedade(RmMaterial.OPERACAO, rmMClass, aliasRmMaterial));
        propriedades.add(new Propriedade(RmMaterial.ORDEM, rmMClass, aliasRmMaterial));

        //RM
        propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
        propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));

        //Deposito
        propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasRmDeposito));
        propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasRmDeposito));
        propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasRmDeposito));

        //Material
        propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
        propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
        propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
        propriedades.add(new Propriedade(Material.NOME_COMPLETO, Material.class, aliasMaterial));

        propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
        propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));
        propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));


        try {
            switch (sincRegistro.getFuncionalidade()) {
                case Constantes.CODIGO_INTEGRACAO_RESERVA_MATERIAL: {
                    GenericDao<RmMaterial> genericDao = new GenericDao<>();

                    nxCriterion = NxCriterion.montaRestriction(new Filtro(Rm.RM_ID, sincRegistro.getIdRegistro(), Filtro.EQUAL, aliasRm));
                    nxCriterion = NxCriterion.and(nxCriterion,
                            NxCriterion.montaRestriction(new Filtro(RmMaterial.FLUXO_MATERIAL, Constantes.FLUXO_MATERIAL_RESERVA, Filtro.EQUAL)));

                    listaRmMaterial = genericDao.listarByFilter(propriedades, null, RmMaterial.class, Constantes.NO_LIMIT, nxCriterion);

                    administradorVo.setRmMateriais(listaRmMaterial);
                    break;
                }
                case Constantes.CODIGO_INTEGRACAO_COMPRA_MATERIAL: {
                    GenericDao<RmMaterial> genericDao = new GenericDao<>();

                    if (sincRegistro.getRmMaterialConcatena() != null && !sincRegistro.getRmMaterialConcatena().isEmpty()) {

                        String[] idsRmMaterial = sincRegistro.getRmMaterialConcatena().split(";");
                        List<Integer> ids = new ArrayList<>();
                        for (String idRmMat : idsRmMaterial) {
                            if (idRmMat != null && !idRmMat.isEmpty()) {
                                ids.add(Integer.parseInt(idRmMat));
                            }
                        }

                        nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM_MATERIAL_ID, ids, Filtro.IN));

                        listaRmMaterial = genericDao.listarByFilter(propriedades, null, RmMaterial.class, Constantes.NO_LIMIT, nxCriterion);

                        administradorVo.setRmMateriais(listaRmMaterial);
                    }
                    break;
                }
                case Constantes.CODIGO_INTEGRACAO_RETIRADA_MATERIAL: {
                    if (sincRegistro.getJson() != null && sincRegistro.getNomeClasse() != null && sincRegistro.getAtivo().equals(Constantes.SIM_ABREVIADO)) {
                        Gson converterGson = new Gson();

                        EnviaRetiradaVo enviaRetiradaVo = converterGson.fromJson(sincRegistro.getJson(), EnviaRetiradaVo.class);

                        GenericDao<MaterialDepositoSaida> genericDao = new GenericDao<>();

                        if (enviaRetiradaVo.getRecebidoCp()) {
                            RmMaterial RmMaterial = new RmMaterial();
                            RmMaterial.setMaterial(enviaRetiradaVo.getMaterial());
                            RmMaterial.setDeposito(enviaRetiradaVo.getDeposito());
                            RmMaterial.setQuantidade(enviaRetiradaVo.getQuantidade());
                            if (enviaRetiradaVo.getRmMaterial().getLocalEntrega() != null) {
                                RmMaterial.setLocalEntrega(enviaRetiradaVo.getRmMaterial().getLocalEntrega());
                            }
                            if (enviaRetiradaVo.getRmMaterial().getObservacao() != null) {
                                RmMaterial.setObservacao(enviaRetiradaVo.getRmMaterial().getObservacao());
                            }
                            if (enviaRetiradaVo.getRmMaterial().getRm() != null && enviaRetiradaVo.getRmMaterial().getRm().getNumeroRm() != null) {
                                RmMaterial.setRm(enviaRetiradaVo.getRmMaterial().getRm());
                                RmMaterial.getRm().setNumeroRm(enviaRetiradaVo.getRmMaterial().getRm().getNumeroRm());
                            }

                            Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();
                            if (configuracao.getColetorCustoEpi() != null) {
                                RmMaterial.setColetorCustos(configuracao.getColetorCustoEpi());
                            }
                            administradorVo.setRmMateriais(Arrays.asList(RmMaterial));

                            administradorVo.setRecebidoCp(true);
                        }

                    } else {

                        GenericDao<MaterialDepositoSaida> genericDao = new GenericDao<>();

                        String aliasSaidaReserva = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID);
                        String aliasRm1 = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, RmMaterial.RM);
                        String aliasRmMat = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL);
                        String aliasRmTipoReq = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.RM, Rm.TIPO_REQUISICAO);
                        String aliasMatDeposito = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO);
                        String aliasMat = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDeposito.MATERIAL);
                        String aliasDeposito = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDeposito.DEPOSITO);

                        propriedades.add(new Propriedade(MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_ID));
                        propriedades.add(new Propriedade(MaterialDepositoSaida.IS_RECEBIDO_CP));
                        propriedades.add(new Propriedade(MaterialDepositoSaida.QUANTIDADE));

                        propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID, MaterialDeposito.class, aliasMatDeposito));

                        propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMat));
                        propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMat));
                        propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMat));
                        propriedades.add(new Propriedade(Material.NOME_COMPLETO, Material.class, aliasMat));

                        propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
                        propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));
                        propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));

                        propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
                        propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));

                        //Material deposito saida reserva
                        propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.ID, MaterialDepositoSaidaReserva.class, aliasSaidaReserva));

                        propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMat));

                        nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_ID, sincRegistro.getIdRegistro(), Filtro.EQUAL));
                        MaterialDepositoSaida materialDepositoSaida = genericDao.selectUnique(propriedades, MaterialDepositoSaida.class, nxCriterion);

                        administradorVo.setMaterialDepositoSaida(materialDepositoSaida);

                        if (materialDepositoSaida.getRecebidoCp() != null && materialDepositoSaida.getRecebidoCp()) {
                            RmMaterial material = new RmMaterial();
                            material.setMaterial(materialDepositoSaida.getMaterialDeposito().getMaterial());
                            material.setDeposito(materialDepositoSaida.getMaterialDeposito().getDeposito());
                            material.setQuantidade(materialDepositoSaida.getQuantidade());
                            Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();
                            if (configuracao.getColetorCustoEpi() != null) {
                                material.setColetorCustos(configuracao.getColetorCustoEpi());
                            }
                            administradorVo.setRmMateriais(Arrays.asList(material));
                            administradorVo.setRecebidoCp(true);
                        } else {
                            administradorVo.setRmMateriais(Arrays.asList(materialDepositoSaida.getMaterialDepositoSaidaReserva().getRmMaterial()));
                        }
                    }

                    break;
                }
                case Constantes.SINC_EQUIPAMENTO: {
                    if (sincRegistro.getIdRegistro() != null){
                        List<Integer> listaIds = new ArrayList<>();
                        listaIds.add(sincRegistro.getIdRegistro());
                        MaterialDepositoEntradaService materialDepositoEntradaService = new MaterialDepositoEntradaService();
                        List<MaterialDepositoEntrada> entradas = materialDepositoEntradaService.listaMaterialDepositoEntrada(listaIds);
                        if(entradas != null && entradas.size() > 0){
                            administradorVo.setObjeto(entradas.get(0));
                            administradorVo.setRm(entradas.get(0).getRm());
                        }
                    }
                    break;
                }
                case Constantes.SINC_TRANF_EQUIPAMENTO: {
                    if(sincRegistro.getJson() != null) {
                        //Recupera obj do json
                        Gson gson = new Gson();
                        MaterialTransferenciaVo obj = gson.fromJson(sincRegistro.getJson(), MaterialTransferenciaVo.class);
                        administradorVo.setObjeto(obj);
                    }
                    break;
                }
                case Constantes.SINC_GERA_CAUTELA: {
                    SincCautelaService sincCautelaService = new SincCautelaService();
                    if(sincRegistro.getRmMaterialConcatena() != null) {
                        List<Integer> ids = new ArrayList<Integer>();
                        String[] strIds = sincRegistro.getRmMaterialConcatena().split(";");
                        for (String id : strIds) {
                            if (id.trim().length() > 0) {
                                ids.add(Integer.parseInt(id));
                            }
                        }
                        administradorVo.setRmMateriais(sincCautelaService.listarRmMateriais(ids));
                    }
                    break;
                }
                case Constantes.SINC_RESERVA: {
                    SincReservaService sincReservaService = new SincReservaService();
                    if(sincRegistro.getIdRegistro() != null) {
                        List<Integer> ids = new ArrayList<Integer>();
                        ids.add(sincRegistro.getIdRegistro());
                        administradorVo.setRmMateriais(sincReservaService.listarRmMateriais(ids));
                    }
                    break;
                }
            }

            administradorVo.setSincRegistro(sincRegistro);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return administradorVo;
    }

    /**
     * Atualiza os materiais com as novas informações
     *
     * @param administradorVo
     * @return
     */
    @POST
    @Path("salvar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info salvar(AdministradorVo administradorVo) {
        Info info = new Info();
        GenericDao<Rm> genericDao = new GenericDao<>();

        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(RmMaterial.COLETOR_CUSTOS));
        propriedades.add(new Propriedade(RmMaterial.COLETOR_ORDEM));
        propriedades.add(new Propriedade(RmMaterial.DIAGRAMA_REDE));
        propriedades.add(new Propriedade(RmMaterial.OPERACAO));

        try {
            genericDao.beginTransaction();

            for (RmMaterial rmMaterial : administradorVo.getRmMateriais()) {
                genericDao.updateWithCurrentTransaction(rmMaterial, propriedades);
            }

            genericDao.commitCurrentTransaction();

            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setErro(null);
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setMensagem(Constantes.ERRO_OPERACAO_I18N);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    /**
     * Realiza o envio do registro para o sap
     *
     * @param sincRegistro
     * @return
     */
    @POST
    @Path("reenviarParaSap")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info reenviarParaSap(SincRegistro sincRegistro) {
        Info info = null;
        GenericDao<SincRegistro> genericDao = new GenericDao<>();
        SincRegistroService sincRegistroService = new SincRegistroService();
        RmaService rmaService = new RmaService(request);

        switch (sincRegistro.getFuncionalidade()) {
            case Constantes.CODIGO_INTEGRACAO_RESERVA_MATERIAL: {
                Rm rm = rmaService.listarRm(new Rm(sincRegistro.getIdRegistro()));
//                info = enviarReserva(rm);
                //if (info.getErro() == null) {
                List<SincRegistro> listaSincRegistro = sincRegistroService.listarSincRegistro(rm.getRmId(), Constantes.CODIGO_INTEGRACAO_RESERVA_MATERIAL);
                salvarAlteracoes(listaSincRegistro, genericDao, sincRegistro);
                //}
                break;
            }
            case Constantes.CODIGO_INTEGRACAO_COMPRA_MATERIAL: {
                info = enviarCompra(sincRegistro);
                //List<SincRegistro> listaSincRegistro = sincRegistroService.listarSincRegistro(sincRegistro.getAgrupadorErro());
                //salvarAlteracoes(listaSincRegistro, genericDao, sincRegistro);
                sincRegistroService.desativar(sincRegistro);
                break;
            }
            case Constantes.CODIGO_INTEGRACAO_RETIRADA_MATERIAL: {
                info = enviarRetirada(sincRegistro);
                sincRegistroService.desativar(sincRegistro);

//                if (info.getErro() != null) {
//                    List<SincRegistro> listaSincRegistro = sincRegistroService.listarSincRegistro(sincRegistro.getIdRegistro(), Constantes.CODIGO_INTEGRACAO_RETIRADA_MATERIAL);
//
//                    salvarAlteracoes(listaSincRegistro,genericDao,sincRegistro);  genericDao.rollbackCurrentTransaction();
//                    }
//                }
                break;
            }

            case Constantes.SINC_EQUIPAMENTO:{
                try {
                    MaterialDepositoEntradaService materialDepositoEntradaService = new MaterialDepositoEntradaService();
                    SincEquipamentoService sincEquipamentoService = new SincEquipamentoService();

                    List<Integer> listaIds = new ArrayList<>();
                    listaIds.add(sincRegistro.getIdRegistro());

                    List<MaterialDepositoEntrada> entradas = materialDepositoEntradaService.listaMaterialDepositoEntrada(listaIds);

                    if (entradas != null && entradas.size() > 0) {
                        info = sincEquipamentoService.enviaMaterial(entradas.get(0), false);
                        //Se o reenvio deu sucesso então desativa o SincRegistro
                        if (Info.INFO_001.equals(info.getCodigo())) {
                            sincRegistroService.desativar(sincRegistro);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            }
            case Constantes.SINC_TRANF_EQUIPAMENTO:{
                SincEquipamentoService sincEquipamentoService = new SincEquipamentoService();
                Gson gson = new Gson();
                //Recupera obj do json
                MaterialTransferenciaVo obj = gson.fromJson(sincRegistro.getJson(), MaterialTransferenciaVo.class);

                info = sincEquipamentoService.transferirMaterial(obj, false);

                //Se o reenvio deu sucesso então desativa o SincRegistro
                if (Info.INFO_001.equals(info.getCodigo())) {
                    sincRegistroService.desativar(sincRegistro);
                }
                break;
            }
            case Constantes.SINC_GERA_CAUTELA:{
                SincCautelaService sincCautelaService = new SincCautelaService();
                if (sincRegistro.getRmMaterialConcatena() != null && sincRegistro.getRmMaterialConcatena().trim().length() > 0) {

                    List<Integer> ids = new ArrayList<Integer>();
                    String[] strIds = sincRegistro.getRmMaterialConcatena().split(";");
                    for (String id : strIds) {
                        if (id.trim().length() > 0) {
                            ids.add(Integer.parseInt(id));
                        }
                    }

                    info = sincCautelaService.enviaCautelaCp(sincCautelaService.listarRmMateriais(ids), false);

                    //Só desativa os registros se sincronizar com sucesso
                    if (Info.INFO_001.equals(info.getCodigo())) {
                        sincRegistroService.desativar(sincRegistro);
                    }
                }

                break;
            }
            case Constantes.SINC_RESERVA:{
                SincReservaService sincReservaService = new SincReservaService();
                if (sincRegistro.getIdRegistro() != null) {
                    List<Integer> ids = new ArrayList<Integer>();
                    ids.add(sincRegistro.getIdRegistro());

                    List<RmMaterial> listaRmMaterial = sincReservaService.listarRmMateriais(ids);

                    if (listaRmMaterial != null && listaRmMaterial.size() > 0) {
                        info = sincReservaService.enviarReserva(listaRmMaterial.get(0),LoginService.getUsuarioLogado(request).getNome(),false);

                        //Só desativa os registros se sincronizar com sucesso
                        if (Info.INFO_001.equals(info.getCodigo())) {
                            sincRegistroService.desativar(sincRegistro);
                        }
                    }
                }


                break;
            }

        }

        if (info == null) {
            //ERRO, NAO ENCONTROU OS MATERIAIS DA REQUISICAO
            //NAO DEVE ACONTECER
        }

        if (info != null && info.getErro() == null) {
            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem("label_rm_enviado_sap_sucesso");
            info.setErro(null);

            try {
                List<SincRegistro> listaSincRegistro = sincRegistroService.listarSincRegistro(sincRegistro.getAgrupadorErro());
                genericDao.beginTransaction();

                List<Propriedade> propriedadesSync = new ArrayList<>();
                propriedadesSync.add(new Propriedade(SincRegistro.ATIVO));

                for (SincRegistro sr : listaSincRegistro) {
                    sr.setAtivo(Constantes.NAO_ABREVIADO);
                    genericDao.updateWithCurrentTransaction(sr, propriedadesSync);
                }
                genericDao.commitCurrentTransaction();
            } catch (Exception e) {
                genericDao.rollbackCurrentTransaction();
            }

        }
        return info;
    }


    private void salvarAlteracoes(List<SincRegistro> listaSincRegistro, GenericDao genericDao, SincRegistro sincRegistro) {
        try {
            genericDao.beginTransaction();

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(SincRegistro.ATIVO));

            for (SincRegistro sr : listaSincRegistro) {
                if (!sr.equals(sincRegistro)) {
                    sr.setAtivo(Constantes.NAO_ABREVIADO);
                    genericDao.updateWithCurrentTransaction(sr, propriedades);
                }
            }
            genericDao.commitCurrentTransaction();
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
        }
    }

    /**
     * Monta as informações de compra necessarias para o envio ao sap
     *
     * @param sincRegistro
     * @return
     */
    private Info enviarCompra(SincRegistro sincRegistro) {
        RmaService rmaService = new RmaService(request);
        CompraMateriaisSapService compraMateriaisSapService = new CompraMateriaisSapService();

        GenericDao<RmMaterial> genericDao = new GenericDao<>();
        List<RmMaterial> listaRmMaterial = new ArrayList<>();
        NxCriterion nxCriterion = null;
        Info info = null;

        try {
            String aliasRmMaterial = null;
            Class rmMClass = null;
            List<Propriedade> propriedades = new ArrayList<>();
            String aliasMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL);
            String aliasTipoMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL, Material.TIPO_MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.DEPOSITO_ID);
            String aliasRm = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);
            String aliasRequisitante = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.REQUISITANTE);
            String aliasUnidadeMedida = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL, Material.UNIDADE_MEDIDA);
            String aliasPrioridade = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.PRIORIDADE);
            String aliasGerenteObra = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.GERENTE_OBRA);
            String aliasGerenteCusto = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.GERENTE_CUSTOS);
            String aliasGerenteArea = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.GERENTE_AREA);
            String aliasTipoRequisicao = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.TIPO_REQUISICAO);

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));
            propriedades.add(new Propriedade(RmMaterial.ORDEM));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_CUSTOS));
            propriedades.add(new Propriedade(RmMaterial.OPERACAO));
            propriedades.add(new Propriedade(RmMaterial.DIAGRAMA_REDE));
            propriedades.add(new Propriedade(RmMaterial.LOCAL_ENTREGA));
            propriedades.add(new Propriedade(RmMaterial.FLUXO_MATERIAL));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA));
            propriedades.add(new Propriedade(RmMaterial.AGRUPADOR_ERRO));
            propriedades.add(new Propriedade(RmMaterial.DATA_PREVISTA_ENTREGA));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_REQUISICAO_SAP));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_ORDEM));
            propriedades.add(new Propriedade(RmMaterial.DATA_FINALIZACAO_SERVICO));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_ESTOQUE));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO_ESTOQUISTA));
            propriedades.add(new Propriedade(RmMaterial.DATA_ATRIBUIR_COMPRADOR));
            propriedades.add(new Propriedade(RmMaterial.DATA_ULTIMA_NF));
            propriedades.add(new Propriedade(RmMaterial.CONCLUIDA_COMPRADOR));

            //Material
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.IS_SERVICO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.ULTIMO_VALOR_NEGOCIADO, Material.class, aliasMaterial));

            //Tipo Material
            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));

            //Deposito
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            //Rm
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_EMISSAO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.LOCAL_ENTREGA, Rm.class, aliasRm));

            //Gerente Area
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasGerenteArea));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasGerenteArea));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasGerenteArea));

            //Gerente custo
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasGerenteCusto));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasGerenteCusto));

            //Gerente obra
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasGerenteObra));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasGerenteObra));

            //Unidade medida
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            //Prioridade
            propriedades.add(new Propriedade(Prioridade.PRIORIDADE_ID, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.DESCRICAO, Prioridade.class, aliasPrioridade));

            //Requisitante
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRequisitante));

            if (sincRegistro.getRmMaterialConcatena() != null && !sincRegistro.getRmMaterialConcatena().isEmpty()) {

                String[] idsRmMaterial = sincRegistro.getRmMaterialConcatena().split(";");
                List<Integer> ids = new ArrayList<>();
                for (String idRmMat : idsRmMaterial) {
                    if (idRmMat != null && !idRmMat.isEmpty()) {
                        ids.add(Integer.parseInt(idRmMat));
                    }
                }

                nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM_MATERIAL_ID, ids, Filtro.IN));

                listaRmMaterial = genericDao.listarByFilter(propriedades, null, RmMaterial.class, Constantes.NO_LIMIT, nxCriterion);
            }
        }catch(Exception e){

        }

        info = compraMateriaisSapService.enviarCompra(listaRmMaterial);
        return info;
    }

    /**
     * Monta as informações de reserva necessarias para o envio ao sap
     *
     * @param rm
     * @return
     */
    private Info enviarReserva(Rm rm) {
        RmaService rmaService = new RmaService(request);
        ReservaMateriaisSapService reservaMateriaisSapService = new ReservaMateriaisSapService();

        List<RmMaterial> listaRmMaterial = rmaService.listarRmMateriais(rm);
        Pessoa aprovador = rmaService.listarUltimoAprovador(rm).getAprovador();

        Info info = reservaMateriaisSapService.enviarReserva(listaRmMaterial, rm, aprovador);
        return info;
    }

    /**
     * Monta as informações de retirada necessarias para o envio ao sap
     *
     * @param sincRegistro
     * @return
     */
    private Info enviarRetirada(SincRegistro sincRegistro) {
        Info info = new Info();
        GenericDao<MaterialDepositoSaida> genericDao = new GenericDao<>();
        SincRegistroService sincRegistroService = new SincRegistroService();

        RetiradaMateriaisSapService retiradaMateriaisSapService = new RetiradaMateriaisSapService();

        List<Propriedade> propriedades = new ArrayList<>();

        List<MaterialDepositoSaida> listaMaterialDepositoSaida = listarMaterialDepositoSaida(sincRegistro);

        for (MaterialDepositoSaida materialDepositoSaida : listaMaterialDepositoSaida) {
            info = retiradaMateriaisSapService.enviarRetirada(materialDepositoSaida);

            if (info.getErro() == null) {
                try {
                    propriedades.clear();
                    propriedades.add(new Propriedade(MaterialDepositoSaida.DATA_ENVIO_SAP));

                    materialDepositoSaida.setDataEnvioSap(new Date());
                    genericDao.update(materialDepositoSaida, propriedades);

                    List<SincRegistro> listaSincRegistro = sincRegistroService.listarSincRegistro(materialDepositoSaida.getMaterialDepositoSaidaId(), Constantes.CODIGO_INTEGRACAO_RETIRADA_MATERIAL);

                    propriedades.clear();
                    propriedades.add(new Propriedade(SincRegistro.ATIVO));

                    for (SincRegistro sr : listaSincRegistro) {
                        GenericDao<SincRegistro> genericDaoSR = new GenericDao<>();

                        sr.setAtivo(Constantes.NAO_ABREVIADO);

                        genericDaoSR.update(sr, propriedades);
                    }
                } catch (Exception e) {
                    logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
                }
            }
        }

        return info;
    }

    /**
     * Lista materialDepositoSaida de acordo com a informação salva no
     * sincRegistro
     *
     * @param sincRegistro
     * @return
     */
    private List<MaterialDepositoSaida> listarMaterialDepositoSaida(SincRegistro sincRegistro) {
        List<MaterialDepositoSaida> listaMaterialDepositoSaida = new ArrayList<>();
        GenericDao<MaterialDepositoSaida> genericDao = new GenericDao<>();

        try {
            String aliasMaterialDeposito = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO);
            String aliasMaterialDepositoRm = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.RM);
            String aliasMaterialDepositoSaidaReserva = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID);
            String aliasRmMaterial = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL);
            String aliasRm = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.RM);
            String aliasMaterial = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDeposito.MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDeposito.DEPOSITO);
            String aliasUnidadeMedida = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDeposito.MATERIAL, Material.UNIDADE_MEDIDA);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_ID));
            propriedades.add(new Propriedade(MaterialDepositoSaida.QUANTIDADE));
            propriedades.add(new Propriedade(MaterialDepositoSaida.IS_RECEBIDO_CP));

            //material deposito
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID, MaterialDeposito.class, aliasMaterialDeposito));

            //material deposito -> rm
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasMaterialDepositoRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasMaterialDepositoRm));

            //material deposito saida reserva
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.ID, MaterialDepositoSaidaReserva.class, aliasMaterialDepositoSaidaReserva));

            //material
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP, Material.class, aliasMaterial));

            //deposito
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            //Rm material
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_CUSTOS, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DIAGRAMA_REDE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.OPERACAO, RmMaterial.class, aliasRmMaterial));

            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));

            //Unidade medida
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_ID, sincRegistro.getIdRegistro(), Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion,
                    NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaida.DATA_ENVIO_SAP, null, Filtro.IS_NULL)));

            listaMaterialDepositoSaida = genericDao.listarByFilter(propriedades, null, MaterialDepositoSaida.class, Constantes.NO_LIMIT, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return listaMaterialDepositoSaida;
    }

    /**
     * Realiza a alteração do Ativo para N
     *
     * @param sincRegistro
     * @return
     */
    @POST
    @Path("desativarRegistro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info desativarRegistro(SincRegistro sincRegistro) {
        GenericDao<SincRegistro> genericDao = new GenericDao<>();
        Info info = new Info();

        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(SincRegistro.ATIVO));
            propriedades.add(new Propriedade(SincRegistro.OBSERVACAO));
            sincRegistro.setAtivo("N");
            sincRegistro.setObservacao(Constantes.EXCLUIDO_ENVIO_SAP_COM_ERRO_RETIRADA + ": " + LoginService.getUsuarioLogado(request).getNome());

            genericDao.beginTransaction();
            genericDao.updateWithCurrentTransaction(sincRegistro, propriedades);
            genericDao.commitCurrentTransaction();

            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setErro(null);
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setMensagem(Constantes.ERRO_OPERACAO_I18N);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return info;
    }
}
