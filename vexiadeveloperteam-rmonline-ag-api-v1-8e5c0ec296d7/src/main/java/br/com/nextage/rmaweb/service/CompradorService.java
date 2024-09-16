/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.rmaweb.dao.CompradorDao;
import br.com.nextage.rmaweb.entitybean.Comprador;
import br.com.nextage.rmaweb.util.Constantes;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que recebe requisições do flex e faz a ponte para a obtenção de
 * valores da base de dados.
 *
 * @author Felipe
 */
public class CompradorService {

    private GenericDao<Comprador> genericDao;
    private CompradorDao compradorDao;

    /**
     * Método construtor
     */
    public CompradorService() {
        genericDao = new GenericDao<>();
        compradorDao = new CompradorDao();
    }

    /**
     * Método que realiza a exclusão de um Comprador.
     * <br>
     * <br>
     * <b>Autor:</b> Felipe.
     * <b>Data:</b> 15-03-2011
     * <br>
     * <br>
     *
     * @param comprador - Comprador
     * @return result - boolean
     * @throws Exception - Exception
     */
    public boolean excluir(Comprador comprador) throws Exception {
        Boolean result = false;
        try {
            result = genericDao.delete(comprador);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return result;
    }

    /**
     * Método que realiza uma pesquisa para listar os Compradores em uma grade
     * de dados no flex.
     * <br>
     * <br>
     * <b>Autor:</b> Felipe.
     * <b>Data:</b> 15-03-2011
     * <br>
     * <br>
     *
     * @param nome - String
     * @param qtdRetorno - int
     * @return lista - List
     * @throws Exception - Exception
     */
    public List listaDataGrid(String nome, int qtdRetorno) throws Exception {
        List<Comprador> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            if (nome != null) {
                filtros.add(new Filtro(Comprador.NOME, nome, Filtro.LIKE));
            }

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Comprador.COMPRADOR_ID));
            propriedades.add(new Propriedade(Comprador.NOME));
            propriedades.add(new Propriedade(Comprador.ATIVO));

            //Obtem elementos.
            lista = (ArrayList<Comprador>) genericDao.listarByFilter(propriedades, filtros, Comprador.NOME, false, Comprador.class, qtdRetorno);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;

    }

    /**
     * Metodo que salva os dados de um comprador.
     * <br>
     * <br>
     * <b>Autor:</b> Felipe.
     * <b>Data:</b> 15-03-2011
     * <br>
     * <br>
     *
     * @param obj - Comprador
     * @return idObjeto - String
     * @throws Exception - Exception
     */
    public String salvar(Comprador obj) throws Exception {
        String idObjeto = "0";
        try {
            if (obj.getCompradorId() > 0) {
                idObjeto = compradorDao.update(obj);
            } else {
                idObjeto = compradorDao.persist(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    /**
     * Método que Obtem um unico comprador à partir de seu ID.
     * <br>
     * <br>
     * <b>Autor:</b> Felipe.
     * <b>Data:</b> 15-03-2011
     * <br>
     * <br>
     *
     * @param compradorId - Integer
     * @return obj - Comprador
     * @throws Exception - Exception
     */
    public Comprador selectByUnique(Integer compradorId) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        Comprador obj = new Comprador();
        try {
            filtros.add(new Filtro(Comprador.COMPRADOR_ID, compradorId, Filtro.EQUAL));

            obj = (Comprador) genericDao.selectUnique(filtros, Comprador.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return obj;
    }

    /**
     * Método responsável por fazer uma busca para preencher uma combo de
     * compradores.
     * <br>
     * <br>
     * <b>Autor:</b> Felipe.
     * <b>Data:</b> 15-03-2011
     * <br>
     * <br>
     *
     * @return lista - List<Comprador>
     * @throws Exception - Exception
     */
    public List<Comprador> getCombo() throws Exception {
        List<Comprador> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Comprador.COMPRADOR_ID));
            propriedades.add(new Propriedade(Comprador.NOME));
            propriedades.add(new Propriedade(Comprador.ATIVO));

            //Obtem elementos.
            lista = genericDao.listarByFilter(propriedades, filtros, Comprador.NOME, false, Comprador.class, Constantes.NO_LIMIT);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Método que realiza uma pesquisa para listar os Compradores.
     * <br>
     * <br>
     * <b>Autor:</b> Jerry.
     * <b>Data:</b> 14-03-2011
     * <br>
     * <br>
     *
     * @return lista - List
     * @throws Exception - Exception
     */
    public List listaCompradores() throws Exception {
        List<Comprador> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Comprador.COMPRADOR_ID));
            propriedades.add(new Propriedade(Comprador.NOME));
            propriedades.add(new Propriedade(Comprador.ATIVO));

            //Obtem elementos.
            lista = (ArrayList<Comprador>) genericDao.listarByFilter(propriedades, filtros, Comprador.NOME, false, Comprador.class, -1);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;

    }
}
