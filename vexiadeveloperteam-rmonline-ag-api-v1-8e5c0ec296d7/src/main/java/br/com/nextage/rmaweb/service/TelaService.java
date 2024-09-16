/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Tela;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro
 */
public class TelaService {

    private GenericDao<Tela> genericDao;

    public TelaService() {
        genericDao = new GenericDao<>();
    }

    public List listaAll() throws Exception {
        List<Tela> lista = new ArrayList<>();
        try {

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Tela.TELA_ID));
            propriedades.add(new Propriedade(Tela.DESCRICAO));
            propriedades.add(new Propriedade(Tela.OBSERVACAO));

            //Obtem elementos.
            lista = (ArrayList<Tela>) genericDao.listarByFilter(propriedades, null, Tela.TELA_ID, false, Tela.class, -1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Integer selectCountByFilter(List<Filtro> filtros) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
