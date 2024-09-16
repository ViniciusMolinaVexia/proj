/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.rmaweb.dao.TipoMaterialDao;
import br.com.nextage.rmaweb.entitybean.TipoMaterial;
import br.com.nextage.rmaweb.util.Constantes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marcelo
 */
public class TipoMaterialService {

    private GenericDao<TipoMaterial> genericDao;
    private TipoMaterialDao tipoMaterialDao;

    public TipoMaterialService() {
        genericDao = new GenericDao<>();
        tipoMaterialDao = new TipoMaterialDao();
    }

    public boolean excluir(TipoMaterial tipoMaterial) throws Exception {
        Boolean result = false;
        try {
            result = genericDao.delete(tipoMaterial);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return result;
    }

    public List listaDataGrid(String nome, int qtdRetorno) throws Exception {
        List<TipoMaterial> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            if (nome != null) {
                filtros.add(new Filtro(TipoMaterial.DESCRICAO, nome, Filtro.LIKE));
            }

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID));
            propriedades.add(new Propriedade(TipoMaterial.DESCRICAO));

            //Obtem elementos.
            lista = (ArrayList<TipoMaterial>) genericDao.listarByFilter(propriedades, filtros, TipoMaterial.DESCRICAO, false, TipoMaterial.class, qtdRetorno);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;

    }

    public String salvar(TipoMaterial obj) throws Exception {
        String idObjeto = "0";
        try {
            if (obj.getTipoMaterialId() > 0) {
                idObjeto = tipoMaterialDao.update(obj);
            } else {
                idObjeto = tipoMaterialDao.persist(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    public TipoMaterial selectByUnique(Integer tipoMaterialId) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        TipoMaterial obj = new TipoMaterial();
        try {
            filtros.add(new Filtro(TipoMaterial.TIPO_MATERIAL_ID, tipoMaterialId, Filtro.EQUAL));

            obj = (TipoMaterial) genericDao.selectUnique(filtros, TipoMaterial.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return obj;
    }

    public List<TipoMaterial> getCombo() throws Exception {
        List<TipoMaterial> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID));
            propriedades.add(new Propriedade(TipoMaterial.DESCRICAO));

            //Obtem elementos.
            lista = genericDao.listarByFilter(propriedades, filtros, TipoMaterial.DESCRICAO, false, TipoMaterial.class, Constantes.NO_LIMIT);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

}
