/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.classes.NxCriterion;
import br.com.nextage.persistence.classes.NxOrder;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.entitybean.RmMaterialRecebimento;
import br.com.nextage.rmaweb.entitybean.RmMaterialRetirada;
import br.com.nextage.rmaweb.util.Constantes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jerry
 */
public class AlterarDatasRmService {

    private GenericDao<RmMaterialRecebimento> recebimentoDao;
    private GenericDao<RmMaterialRetirada> retiradaDao;
    private Integer numeroRm;

    public AlterarDatasRmService() {
        this.recebimentoDao = new GenericDao<>();
        this.retiradaDao = new GenericDao<>();
    }

    public Integer getNumeroRm() {
        return numeroRm;
    }

    public void setNumeroRm(Integer numeroRm) {
        this.numeroRm = numeroRm;
    }

    /**
     * Lista todos os materias recebidos ,filtrado especificamente por numero da
     * rm
     *
     * <p/>
     * <b>Autor:</b> Jerry
     * <b>Data:</b> 01-12-2011
     * <p/>
     *
     * @param rm
     * @return
     * @throws java.lang.Exception
     */
    public List<RmMaterialRecebimento> listaRmMaterialRecebimento(Rm rm) throws Exception {

        String aliasRmMaterial = NxCriterion.montaAlias(RmMaterialRecebimento.ALIAS_CLASSE, RmMaterialRecebimento.RM_MATERIAL);
        String aliasRm = NxCriterion.montaAlias(RmMaterialRecebimento.ALIAS_CLASSE, RmMaterialRecebimento.RM_MATERIAL, RmMaterial.RM);
        String aliasMaterial = NxCriterion.montaAlias(RmMaterialRecebimento.ALIAS_CLASSE, RmMaterialRecebimento.RM_MATERIAL, RmMaterial.MATERIAL);

        List<RmMaterialRecebimento> lista = new ArrayList<>();

        try {
            if (rm != null) {

                // Seta as propriedades de retorno da consulta.
                List<Propriedade> propriedades = new ArrayList<>();
                //1º nivel
                propriedades.add(new Propriedade(RmMaterialRecebimento.RM_MATERIAL_RECEBIMENTO_ID));
                propriedades.add(new Propriedade(RmMaterialRecebimento.QUANTIDADE));
                propriedades.add(new Propriedade(RmMaterialRecebimento.NUMERO_NOTA_FISCAL));
                propriedades.add(new Propriedade(RmMaterialRecebimento.DATA_EMISSAO_NF));
                propriedades.add(new Propriedade(RmMaterialRecebimento.DATA_RECEBIMENTO_MATERIAL));
                //2° nivel
                propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));
                //3º nivel
                propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
                propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));
                //3º nivel
                propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
                propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
                propriedades.add(new Propriedade(Material.UNIDADE_MEDIDA, Material.class, aliasMaterial));

                NxCriterion nxCriterionPrincipal = null;
                nxCriterionPrincipal = NxCriterion.montaRestriction(new Filtro(Rm.NUMERO_RM, rm.getNumeroRm(), Filtro.EQUAL));

                List<NxOrder> nxOrders = Arrays.asList(
                        new NxOrder(RmMaterialRecebimento.RM_MATERIAL + "." + RmMaterial.MATERIAL + "." + Material.NOME, NxOrder.NX_ORDER.ASC));

                //Obtem elementos.
                lista = recebimentoDao.listarByFilter(propriedades, nxOrders, RmMaterialRecebimento.class, Constantes.NO_LIMIT, nxCriterionPrincipal);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Método lista todos os materias retirados
     * <br>
     * <br>
     * <b>Autor:</b> Jerry.
     * <b>Data:</b> 1/12/2011
     * <br>
     * <br>
     *
     * @param rm
     * @return List<RmMaterialRetirados>
     * @throws Exception - Exception
     */
    public List listaRmMaterialRetirada(Rm rm) throws Exception {

        String aliasPessoa = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RETIRADO_POR);
        String aliasRmMaterial = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RM_MATERIAL);
        String aliasRm = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RM_MATERIAL, RmMaterial.RM);
        String aliasMaterial = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RM_MATERIAL, RmMaterial.MATERIAL);

        List<RmMaterialRetirada> lista = new ArrayList<>();
        try {
            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            //1º nivel
            propriedades.add(new Propriedade(RmMaterialRetirada.RM_MATERIAL_RETIRADA_ID));
            propriedades.add(new Propriedade(RmMaterialRetirada.QUANTIDADE));
            propriedades.add(new Propriedade(RmMaterialRetirada.DATA_RETIRADA));
            propriedades.add(new Propriedade(RmMaterialRetirada.NUMERO_PROTOCOLO));
            //2° nivel
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));

            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasPessoa));

            //3º nivel
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));
            //3º nivel
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.UNIDADE_MEDIDA, Material.class, aliasMaterial));

            NxCriterion nxCriterionPrincipal = null;
            nxCriterionPrincipal = NxCriterion.montaRestriction(new Filtro(Rm.NUMERO_RM, rm.getNumeroRm(), Filtro.EQUAL));

            List<NxOrder> nxOrders = Arrays.asList(
                    new NxOrder(RmMaterialRetirada.RM_MATERIAL + "." + RmMaterial.MATERIAL + "." + Material.NOME, NxOrder.NX_ORDER.ASC));

            //Obtem elementos.
            lista = (ArrayList<RmMaterialRetirada>) retiradaDao.listarByFilter(propriedades, nxOrders, RmMaterialRetirada.class, Constantes.NO_LIMIT, nxCriterionPrincipal);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Atualiza as datas dos materiais recebidos.
     * <br>
     * <br>
     * <b>Autor:</b> Jerry.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param lista
     * @return lista - List<RmMaterialRecebimento>
     * @throws Exception - Exception
     */
    public String salvarRmMaterialRecebimentos(List<RmMaterialRecebimento> lista) throws Exception {
        try {

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialRecebimento.DATA_EMISSAO_NF));
            propriedades.add(new Propriedade(RmMaterialRecebimento.DATA_RECEBIMENTO_MATERIAL));
            for (RmMaterialRecebimento obj : lista) {
                recebimentoDao.update(obj, propriedades);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return "";
    }

    /**
     * Atualiza as datas dos materiais retirados.
     * <br>
     * <br>
     * <b>Autor:</b> Jerry.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param lista
     * @return lista - List<RmMaterialRetirada>
     * @throws Exception - Exception
     */
    public String salvarRmMaterialRetirada(List<RmMaterialRetirada> lista) throws Exception {
        try {

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialRetirada.DATA_RETIRADA));
            for (RmMaterialRetirada obj : lista) {
                retiradaDao.update(obj, propriedades);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return "";
    }

}
