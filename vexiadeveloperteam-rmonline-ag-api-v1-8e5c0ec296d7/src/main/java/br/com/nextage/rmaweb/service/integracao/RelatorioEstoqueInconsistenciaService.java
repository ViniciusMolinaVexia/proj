/*
 * NextAge License
 * Copyright 2015 - Nextage
 *
 */
package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.filtro.FiltroRelatorioEstoqueInconsistencia;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.RelatorioEstoqueInconsistenciaVo;
import br.com.nextage.util.*;
import br.com.nextage.util.vo.ArquivoVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yuri Mello da Costa - <b>y.mello@nextage.com.br<b>
 */
public class RelatorioEstoqueInconsistenciaService {

    private final HttpServletRequest request;
    private final Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    public RelatorioEstoqueInconsistenciaService(HttpServletRequest request) {
        this.request = request;
    }

    public RelatorioEstoqueInconsistenciaService() {
        request = null;
    }

    /**
     * Gera o Relatorio Excel
     *
     * @param lista      lista do relatorio
     * @param filtro     filtro utilizado para listar
     * @param deposito   depositos utilizados na lista
     * @param isListener
     * @return byte[]
     * @author Yuri Mello da Costa
     */
    public ArquivoVo gerarExcelPendencia(List<VwRmMaterial> lista, FiltroRelatorioEstoqueInconsistencia filtro, Deposito deposito, boolean isListener) {
        ArquivoVo vo = null;
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
            String labels = "";
            //cria map dos filtros
            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
            //cria o vo da View, criado para ter o RelatorioVo dentro dele
            /*List<VwRmMaterialVo> listaRel = new ArrayList<>();
            if (lista != null && !lista.isEmpty()) {
                for (VwRmMaterial vwRmMaterial : lista) {
                    listaRel.add(new VwRmMaterialVo(vwRmMaterial));
                }
            }*/

            Map<String, String> mapListaItens = new HashMap<>();
            int i = 0;
            //Map dos campos do relatorio
            labels = rb.getString("label_codigo_material");
            mapListaItens.put(RelatorioEstoqueInconsistenciaVo.CODIGO_MATERIAL, i++ + ":" + labels);
            labels = rb.getString("label_material");
            mapListaItens.put(RelatorioEstoqueInconsistenciaVo.NOME_MATERIAL, i++ + ":" + labels);
            labels = rb.getString("label_deposito");
            mapListaItens.put(RelatorioEstoqueInconsistenciaVo.NOME_DEPOSITO, i++ + ":" + labels);
            labels = rb.getString("label_quantidade_sap");
            mapListaItens.put(RelatorioEstoqueInconsistenciaVo.QTDE_SAP, i++ + ":" + labels);
            labels = rb.getString("label_quantidade_rma");
            mapListaItens.put(RelatorioEstoqueInconsistenciaVo.QTDE_RMA, i++ + ":" + labels);
            labels = rb.getString("label_quantidade_cp");
            mapListaItens.put(RelatorioEstoqueInconsistenciaVo.QTDE_CP, i++ + ":" + labels);
            labels = rb.getString("label_unidade_medida");
            mapListaItens.put(RelatorioEstoqueInconsistenciaVo.UNIDADE_MEDIDA, i++ + ":" + labels);

            Map mapFiltro = new HashMap<>();
            String filtroStr = "";
            i = 0;

            //Map dos filtros do relatorio
            //deposito
            labels = rb.getString("label_deposito");
            if (deposito != null) {
                filtroStr += deposito.getNome();
                filtroStr += ", ";
            } else {
                filtroStr = "";
            }
            mapFiltro.put(i++ + ":" + labels + ":", filtroStr);

            //caso a chamada for do listener não irá adicionar os outros filtros
            if (!isListener) {

                //material
                labels = rb.getString("label_material");
                if (filtro.getMaterial() != null) {
                    filtroStr = filtro.getMaterial().getCodigo() + " - " + filtro.getMaterial().getNome();
                } else {
                    filtroStr = "";
                }
                mapFiltro.put(i++ + ":" + labels + ":", filtroStr);

                //material palavra chave
                labels = rb.getString("label_material_chave");
                if (filtro.getMaterialChave() != null) {
                    filtroStr = filtro.getMaterialChave();
                } else {
                    filtroStr = "";
                }
                mapFiltro.put(i++ + ":" + labels + ":", filtroStr);


                //sistema
//                labels = rb.getString("label_sistema");
//                if (filtro.getSistema() != null && !filtro.getSistema().isEmpty()) {
//                    filtroStr = filtro.getSistema() + " - " + filtro.getSistema();
//                } else {
//                    filtroStr = "";
//                }
//                mapFiltro.put(i++ + ":" + labels + ":", filtroStr);

                //inconsistência
                labels = rb.getString("label_exibir_inconsistencias");
                if (filtro.getInconsistencia() != null) {
                    if (filtro.getInconsistencia() == false) {
                        filtroStr = "Não";
                    } else if (filtro.getInconsistencia() == true) {
                        filtroStr = "Sim";
                    }
                } else {
                    filtroStr = "";
                }
                mapFiltro.put(i++ + ":" + labels + ":", filtroStr);

            }

            labels = rb.getString("label_relatorio_estoque_inconsistencia");
            RelatorioBean relatorioBean = new RelatorioBean();
            relatorioBean.setItens(lista);
            relatorioBean.setMapaListaItens(mapListaItens);
            relatorioBean.setMapaFiltro(mapFiltro);
            relatorioBean.setTipo(RelatorioBean.TipoRelatorio.XLS);
            relatorioBean.setTitulo(labels);
            relatorioBean.setNome(labels + ".xls");

            RelatorioUtil relatorioUtil = new RelatorioUtil();
            byte[] relatorio = relatorioUtil.geraRelatorio(relatorioBean);
            vo = new ArquivoVo();
            vo.setArquivo(relatorio);
            vo.setNmAnexo(relatorioBean.getNome());
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return vo;
    }


    /**
     * Método responsável pela listagem no RMAWEB
     *
     * @param filtro
     * @return
     */
    public List<MaterialDeposito> listarEstoqueRma(FiltroRelatorioEstoqueInconsistencia filtro) {
        List<MaterialDeposito> lista = new ArrayList<>();

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
            List<Propriedade> propriedades = new ArrayList<>();

            String aliasMaterial = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.DEPOSITO);
            String aliasUnidadeMedida = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL, Material.UNIDADE_MEDIDA);

            //MATERIAL DEPOSITO
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

            //MATERIAL
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.HIERARQUIA, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.PATRIMONIADO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.TIPO_MATERIAL, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP, Material.class, aliasMaterial));

            //UNIDADE MEDIDA
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            //DEPOSITO
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));


            if (filtro != null) {
                NxCriterion nxCriterionAux = null;
                NxCriterion nxCriterion = null;

                //Depósito
                if (filtro.getDeposito() != null) {
                    nxCriterion = NxCriterion.and(nxCriterion,
                            NxCriterion.montaRestriction(new Filtro(Deposito.DEPOSITO_ID, filtro.getDeposito().getDepositoId(), Filtro.EQUAL, aliasDeposito)));
                }

                //Material
                if (filtro.getMaterial() != null) {
                    nxCriterion = NxCriterion.and(nxCriterion,
                            NxCriterion.montaRestriction(new Filtro(Material.MATERIAL_ID, filtro.getMaterial().getMaterialId(), Filtro.EQUAL, aliasMaterial)));
                }

                //Material Chave
                if (filtro.getMaterialChave() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Material.NOME, filtro.getMaterialChave(), Filtro.LIKE, aliasMaterial));
                    nxCriterionAux = NxCriterion.or(nxCriterionAux,
                            NxCriterion.montaRestriction(new Filtro(Material.CODIGO, filtro.getMaterialChave(), Filtro.LIKE, aliasMaterial)));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }


                GenericDao<MaterialDeposito> gDao = new GenericDao<>();
                lista = gDao.listarByFilter(propriedades, null, MaterialDeposito.class, Constantes.NO_LIMIT, nxCriterion);

            }
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    /**
     * Listar Depósito
     *
     * @param filtro
     * @return
     */
    public Object listarDepositoAlteracao(FiltroRelatorioEstoqueInconsistencia filtro) {
        Object listaRetorno = null;
        Deposito deposito = null;

        try {
            List<Propriedade> propriedades = new ArrayList<>();

            //DEPOSITO
            propriedades.add(new Propriedade(Deposito.CODIGO));
            propriedades.add(new Propriedade(Deposito.NOME));
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));


            if (filtro != null) {
                NxCriterion nxCriterion = null;
                if (filtro.getDeposito() != null) {
                    if (filtro.getDeposito().getCodigo() != null) {
                        nxCriterion = NxCriterion.montaRestriction(new Filtro(Deposito.CODIGO, filtro.getDeposito().getCodigo(), Filtro.EQUAL));
                    }
                    if (filtro.getDeposito().getDepositoId() != null) {
                        NxCriterion.montaRestriction(new Filtro(Deposito.DEPOSITO_ID, filtro.getDeposito().getDepositoId(), Filtro.EQUAL));
                    }
                } else {
                    nxCriterion = NxCriterion.montaRestriction(new Filtro(Deposito.CODIGO, filtro.getCodDeposito(), Filtro.EQUAL));
                }

                GenericDao gDao = new GenericDao<>();
                deposito = (Deposito) gDao.selectUnique(propriedades, Deposito.class, nxCriterion);

                filtro.setDeposito(deposito);

                listaRetorno = listarEstoqueRma(filtro);

            }

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return listaRetorno;
    }


}
