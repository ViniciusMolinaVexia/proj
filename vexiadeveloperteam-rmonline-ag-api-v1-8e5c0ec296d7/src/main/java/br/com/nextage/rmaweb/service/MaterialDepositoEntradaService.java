/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.rmaweb.dao.MaterialDepositoEntradaDao;
import br.com.nextage.rmaweb.entitybean.Deposito;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.entitybean.MaterialDeposito;
import br.com.nextage.rmaweb.entitybean.MaterialDepositoEntrada;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.entitybean.TipoMaterial;
import br.com.nextage.rmaweb.util.Constantes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jerry
 */
public class MaterialDepositoEntradaService {

    private GenericDao<MaterialDepositoEntrada> genericDao;
    private MaterialDepositoEntradaDao matDepEntradaDao;

    public MaterialDepositoEntradaService() {
        this.genericDao = new GenericDao<>();
        this.matDepEntradaDao = new MaterialDepositoEntradaDao();
    }

    /**
     * Método que realiza exclusão de um MaterialDeposito.
     * <br>
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 22/03/2011
     * <br>
     * <br>
     *
     * @param obj
     * @return result - boolean
     * @throws Exception - Exception
     */
    public boolean excluir(MaterialDepositoEntrada obj) throws Exception {
        Boolean result = false;
        try {
            result = genericDao.delete(obj);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return result;

    }

    /**
     * Método que salva os dados de um MaterialDeposito. Nao faz a soma no
     * cliente por que nao da.
     * <br>
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 22/03/2011
     * <br>
     * <br>
     *
     * @param obj - MaterialDeposito
     * @return idObjeto - String
     * @throws Exception - Exception
     */
    public String salvar(MaterialDepositoEntrada obj) throws Exception {
        String idObjeto = "0";
        try {
            if (obj.getMaterialDepositoEntradaId() > 0) {
                idObjeto = matDepEntradaDao.update(obj);
            } else {
                idObjeto = matDepEntradaDao.persist(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    /**
     * Lista o DataGrid de RmMaterialRecebimento
     *
     * @param materialDeposito
     * @param tipoEntrada
     * @return List<MaterialDepositoEntrada>
     * @throws Exception - Exception
     */
    public List<MaterialDepositoEntrada> listaDataGrid(MaterialDeposito materialDeposito, int tipoEntrada) throws Exception {

        List<MaterialDepositoEntrada> lista = new ArrayList<>();

        try {
            List<Filtro> filtros = new ArrayList<>();

            if (materialDeposito != null) {
                filtros.add(new Filtro(MaterialDepositoEntrada.MATERIAL_DEPOSITO, materialDeposito, Filtro.EQUAL));
            }
            if (tipoEntrada == 2) {
                filtros.add(new Filtro(Rm.RM_ID, materialDeposito, Filtro.NOT_NULL, Rm.ALIAS_CLASSE));
            }
            if (tipoEntrada == 3) {
                filtros.add(new Filtro(Rm.RM_ID, materialDeposito, Filtro.IS_NULL, Rm.ALIAS_CLASSE));
            }

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();

            propriedades.add(new Propriedade(MaterialDepositoEntrada.MATERIAL_DEPOSITO_ENTRADA_ID));
            propriedades.add(new Propriedade(MaterialDepositoEntrada.QUANTIDADE));
            propriedades.add(new Propriedade(MaterialDepositoEntrada.DATA_ENTRADA));
            propriedades.add(new Propriedade(MaterialDepositoEntrada.OBSERVACAO));
            propriedades.add(new Propriedade(MaterialDepositoEntrada.MATERIAL_DEPOSITO));

            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));

            //Obtem elementos.
            lista = genericDao.listarByFilter(propriedades, filtros, MaterialDepositoEntrada.DATA_ENTRADA, false, MaterialDepositoEntrada.class, Constantes.NO_LIMIT);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Lista que retorna todos os registro por material para Agrupamento de
     * material
     *
     * @param material
     * @return List<MaterialDepositoEntrada>
     * @throws Exception - Exception
     */
    public List<MaterialDepositoEntrada> listaDataGrid(Material material) throws Exception {

        List<MaterialDepositoEntrada> lista = new ArrayList<>();

        try {
            List<Filtro> filtros = new ArrayList<>();

            filtros.add(new Filtro(MaterialDeposito.MATERIAL, material, Filtro.EQUAL, Material.ALIAS_CLASSE));

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();

            propriedades.add(new Propriedade(MaterialDepositoEntrada.MATERIAL_DEPOSITO_ENTRADA_ID));
            propriedades.add(new Propriedade(MaterialDepositoEntrada.MATERIAL_DEPOSITO));

            //Obtem elementos.
            lista = genericDao.listarByFilter(propriedades, filtros, MaterialDepositoEntrada.DATA_ENTRADA, false, MaterialDepositoEntrada.class, Constantes.NO_LIMIT);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Método que Obtem os dados do para fazer o agrupamento de materiais .
     * <br>
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 11/05/2011
     * <br>
     * <br>
     *
     * @param material - Material
     * @param deposito - Deposito
     * @return filtros - List<Filtro>
     * @throws Exception - Exception
     */
    public List selectForAgrupamentoOfMateriais(Material material, Deposito deposito) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        List<MaterialDepositoEntrada> lista = new ArrayList<>();
        try {

            filtros.add(new Filtro(MaterialDeposito.MATERIAL, material, Filtro.EQUAL, MaterialDeposito.ALIAS_CLASSE));
            filtros.add(new Filtro(MaterialDeposito.DEPOSITO, deposito, Filtro.EQUAL, MaterialDeposito.ALIAS_CLASSE));

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDepositoEntrada.MATERIAL_DEPOSITO_ENTRADA_ID));

            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID, MaterialDeposito.class, MaterialDeposito.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(MaterialDeposito.DEPOSITO, MaterialDeposito.class, MaterialDeposito.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL, MaterialDeposito.class, MaterialDeposito.ALIAS_CLASSE_UNDERLINE));

            //Obtem elementos.
            lista = (ArrayList<MaterialDepositoEntrada>) genericDao.listarByFilter(propriedades, filtros, null, false, MaterialDepositoEntrada.class, -1);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return lista;
    }

    /**
     * Update para o campo MaterialDepositoID ,metodo utilizado para fazer o
     * agrupamento de materiais.
     * <br>
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 20/05/2011
     * <br>
     * <br>
     *
     * @param obj - MaterialDepositoSaida
     * @return "" - String
     * @throws Exception - Exception
     */
    public String salvarMaterialDepositoEntradaAgrupamento(MaterialDepositoEntrada obj) throws Exception {
        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDepositoEntrada.MATERIAL_DEPOSITO, MaterialDepositoEntrada.class, MaterialDepositoEntrada.ALIAS_CLASSE_UNDERLINE));
            genericDao.update(obj, propriedades);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return "";
    }

    /**
     * Lista que retorna todos os registro por material para uma lista de Ids
     *
     * @param ids
     * @return List<MaterialDepositoEntrada>
     * @throws Exception - Exception
     */
    public List<MaterialDepositoEntrada> listaMaterialDepositoEntrada(List<Integer> ids) throws Exception {

        List<MaterialDepositoEntrada> lista = new ArrayList<>();

        try {

            if (ids != null && !ids.isEmpty()) {
                NxCriterion nxCriterion = NxCriterion.montaRestriction(new br.com.nextage.persistence_2.classes.Filtro(MaterialDepositoEntrada.MATERIAL_DEPOSITO_ENTRADA_ID, ids, Filtro.IN));

                String aliasRm = NxCriterion.montaAlias(MaterialDepositoEntrada.ALIAS_CLASSE, MaterialDepositoEntrada.RM);
                String aliasMaterialDeposito = NxCriterion.montaAlias(MaterialDepositoEntrada.ALIAS_CLASSE, MaterialDepositoEntrada.MATERIAL_DEPOSITO);
                String aliasMaterial = NxCriterion.montaAlias(MaterialDepositoEntrada.ALIAS_CLASSE, MaterialDepositoEntrada.MATERIAL_DEPOSITO, MaterialDeposito.MATERIAL);
                String aliasDeposito = NxCriterion.montaAlias(MaterialDepositoEntrada.ALIAS_CLASSE, MaterialDepositoEntrada.MATERIAL_DEPOSITO, MaterialDeposito.DEPOSITO);

                // Seta as propriedades de retorno da consulta.
                List<br.com.nextage.persistence_2.classes.Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDepositoEntrada.MATERIAL_DEPOSITO_ENTRADA_ID));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDepositoEntrada.QUANTIDADE));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDepositoEntrada.DATA_ENTRADA));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDepositoEntrada.OBSERVACAO));

                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.RM_ID, Rm.class, aliasRm));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));

                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID, MaterialDeposito.class, aliasMaterialDeposito));

                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.NOME, Material.class, aliasMaterial));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.CODIGO, Material.class, aliasMaterial));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.PATRIMONIADO, Material.class, aliasMaterial));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.UNIDADE_MEDIDA, Material.class, aliasMaterial));

                String aliasMatTipo = NxCriterion.montaAlias(MaterialDepositoEntrada.ALIAS_CLASSE, MaterialDepositoEntrada.MATERIAL_DEPOSITO, MaterialDeposito.MATERIAL, Material.TIPO_MATERIAL);
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasMatTipo));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasMatTipo));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(TipoMaterial.DESCRICAO, TipoMaterial.class, aliasMatTipo));

                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

                br.com.nextage.persistence_2.dao.GenericDao<MaterialDepositoEntrada> dao;
                dao = new br.com.nextage.persistence_2.dao.GenericDao<>();
                lista = dao.listarByFilter(propriedades, null, MaterialDepositoEntrada.class, Constantes.NO_LIMIT, nxCriterion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }
}
