/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.rmaweb.dao.SetorDao;
import br.com.nextage.rmaweb.entitybean.Setor;
import br.com.nextage.rmaweb.util.Constantes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class SetorService {

    private GenericDao<Setor> genericDao;
    private SetorDao setorDao;

    public SetorService() {
        genericDao = new GenericDao<>();
        setorDao = new SetorDao();
    }

    public boolean excluir(Setor setor) throws Exception {
        Boolean result = false;
        try {
            result = genericDao.delete(setor);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return result;
    }

    public List listaDataGrid(String nome, int qtdRetorno) throws Exception {
        List<Setor> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            if (nome != null) {
                filtros.add(new Filtro(Setor.DESCRICAO, nome, Filtro.LIKE));
            }

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Setor.SETOR_ID));
            propriedades.add(new Propriedade(Setor.DESCRICAO));

            //Obtem elementos.
            lista = (ArrayList<Setor>) genericDao.listarByFilter(propriedades, filtros, Setor.DESCRICAO, false, Setor.class, qtdRetorno);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;

    }

    public String salvar(Setor obj) throws Exception {
        String idObjeto = "0";
        try {
            if (obj.getSetorId() > 0) {
                idObjeto = setorDao.update(obj);
            } else {
                idObjeto = setorDao.persist(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    public Setor selectByUnique(Integer setorId) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        Setor obj = new Setor();
        try {
            filtros.add(new Filtro(Setor.SETOR_ID, setorId, Filtro.EQUAL));

            obj = (Setor) genericDao.selectUnique(filtros, Setor.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return obj;
    }

    public List<Setor> getCombo() throws Exception {
        List<Setor> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Setor.SETOR_ID));
            propriedades.add(new Propriedade(Setor.DESCRICAO));

            //Obtem elementos.
            lista = genericDao.listarByFilter(propriedades, filtros, Setor.DESCRICAO, false, Setor.class, Constantes.NO_LIMIT);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

}
