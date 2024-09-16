/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.rmaweb.dao.UnidadeMedidaDao;
import br.com.nextage.rmaweb.entitybean.UnidadeMedida;
import br.com.nextage.rmaweb.util.Constantes;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que recebe requisições do flex e faz a ponte para a obtenção de
 * valores da base de dados.
 *
 * @author marcelo
 */
public class UnidadeMedidaService {

    private GenericDao<UnidadeMedida> genericDao;
    private UnidadeMedidaDao unidadeMedidaDao;

    /**
     *
     */
    public UnidadeMedidaService() {
        genericDao = new GenericDao<>();
        unidadeMedidaDao = new UnidadeMedidaDao();
    }

    /**
     * Método que exclui um registro de unideMedida
     * <p/>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @param unidadeMedida - UnidadeMedida
     * @return result Boolean
     * @throws Exception - Exception
     */
    public boolean excluir(UnidadeMedida unidadeMedida) throws Exception {
        Boolean result = false;
        try {
            result = genericDao.delete(unidadeMedida);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return result;
    }

    /**
     * Método que realiza uma pesquisa para listar os Compradores em uma grade
     * de dados no flex.
     * <p/>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @param sigla
     * @param descricao
     * @param qtdRetorno
     * @return
     * @throws Exception - Exception
     */
    public List listaDataGrid(String sigla, String descricao, int qtdRetorno) throws Exception {
        List<UnidadeMedida> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            if (sigla != null) {
                filtros.add(new Filtro(UnidadeMedida.SIGLA, sigla, Filtro.LIKE));
            }
            if (descricao != null) {
                filtros.add(new Filtro(UnidadeMedida.DESCRICAO, descricao, Filtro.LIKE));
            }

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO));

            //Obtem elementos.
            lista = (ArrayList<UnidadeMedida>) genericDao.listarByFilter(propriedades, filtros, UnidadeMedida.SIGLA, false, UnidadeMedida.class, qtdRetorno);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;

    }

    /**
     * Método que salva um registro de UnidadeMedida.
     * <p/>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @param obj - UnidadeMedida
     * @return idObjeto - String
     * @throws Exception - Exception
     */
    public String salvar(UnidadeMedida obj) throws Exception {
        String idObjeto = "0";
        try {
            if (obj.getUnidadeMedidaId() > 0) {
                idObjeto = unidadeMedidaDao.update(obj);
            } else {
                idObjeto = unidadeMedidaDao.persist(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    /**
     * Método que seleciona uma UnidadeMedida para edição.
     * <p/>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @param unidadeMedidaId
     * @return obj - UnidadeMedida
     * @throws Exception- Exception
     */
    public UnidadeMedida selectByUnique(Integer unidadeMedidaId) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        UnidadeMedida obj = new UnidadeMedida();
        try {
            filtros.add(new Filtro(UnidadeMedida.UNIDADE_MEDIDA_ID, unidadeMedidaId, Filtro.EQUAL));

            obj = (UnidadeMedida) genericDao.selectUnique(filtros, UnidadeMedida.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return obj;
    }

    /**
     * Método que preenche uma combo com uma lista de UnidadeMedida
     * <p/>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @return lista - List<UnidadeMedida>
     * @throws Exception - Exception
     */
    public List<UnidadeMedida> getCombo() throws Exception {
        List<UnidadeMedida> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO));

            //Obtem elementos.
            lista = genericDao.listarByFilter(propriedades, filtros, UnidadeMedida.SIGLA, false, UnidadeMedida.class, Constantes.NO_LIMIT);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

}
