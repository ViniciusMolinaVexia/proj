/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Cidade;
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
public class CidadeService {

    private GenericDao<Cidade> genericDao;

    /**
     * Método construtor
     */
    public CidadeService() {
        genericDao = new GenericDao<>();
    }

    /**
     * Método que realiza a pesquisa de cidades de um estado informado,
     * preenchendo a combo de cidades.
     * <br>
     * <br>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <br>
     * <br>
     *
     * @param uf - Uf
     * @return lista - List<Cidade>
     * @throws Exception - Exception
     */
    public List<Cidade> getCombo(Uf uf) throws Exception {
        List<Cidade> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            if (uf != null) {
                filtros.add(new Filtro(Cidade.UF, uf, Filtro.EQUAL));

                // Seta as propriedades de retorno da consulta.
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(Cidade.CIDADE));
                propriedades.add(new Propriedade(Cidade.NOME));

                //Obtem elementos.
                lista = genericDao.listarByFilter(propriedades, filtros, Cidade.NOME, false, Cidade.class, Constantes.NO_LIMIT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }
}
