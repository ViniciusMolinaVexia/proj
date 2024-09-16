/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Uf;
import br.com.nextage.rmaweb.util.Constantes;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que recebe requisições do flex e faz a ponte para a obtenção de
 * valores da base de dados.
 *
 * @author marcelo
 */
public class UfService {

    private GenericDao<Uf> genericDao;

    /**
     * Método construtor.
     */
    public UfService() {
        genericDao = new GenericDao<>();
    }

    /**
     * Método responsável por preencher a combo de estados.
     * <p/>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @return lista - List<Uf>
     * @throws Exception - Exception
     */
    public List<Uf> getCombo() throws Exception {
        List<Uf> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Uf.UF));
            propriedades.add(new Propriedade(Uf.NOME));

            //Obtem elementos.
            lista = genericDao.listarByFilter(propriedades, filtros, Uf.NOME, false, Uf.class, Constantes.NO_LIMIT);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }
}
