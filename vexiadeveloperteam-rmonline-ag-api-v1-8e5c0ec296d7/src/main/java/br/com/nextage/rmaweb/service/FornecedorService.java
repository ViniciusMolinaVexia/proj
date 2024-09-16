/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.rmaweb.dao.FornecedorDao;
import br.com.nextage.rmaweb.entitybean.Cidade;
import br.com.nextage.rmaweb.entitybean.Fornecedor;
import br.com.nextage.rmaweb.util.Constantes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe que recebe requisições do flex e faz a ponte para a obtenção de
 * valores da base de dados.
 *
 * @author marcelo
 */
public class FornecedorService {

    private GenericDao<Fornecedor> genericDao;
    private FornecedorDao fornecedorDao;
    private static final List<Propriedade> propriedadesBasicas = Arrays.asList(
            new Propriedade(Fornecedor.FORNECEDOR_ID),
            new Propriedade(Fornecedor.NOME),
            new Propriedade(Fornecedor.CODIGO));

    /**
     * Método construtor
     */
    public FornecedorService() {
        genericDao = new GenericDao<>();
        fornecedorDao = new FornecedorDao();
    }

    /**
     * Método que realiza exclusão de um fornecedor.
     * <br>
     * <br>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <br>
     * <br>
     *
     * @param fornecedor - Fornecedor
     * @return result - boolean
     * @throws Exception - Exception
     */
    public boolean excluir(Fornecedor fornecedor) throws Exception {
        Boolean result = false;
        try {
            result = genericDao.delete(fornecedor);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return result;

    }

    /**
     * Método que realiza uma pesquisa para listar os registros em uma grade de
     * dados no flex.
     * <p/>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <p/>
     * <h4>ALTERAÇÕES:</h4>
     * <p/>
     * <b>Autor:</b> Juliano A. Felipe
     * <b>Data:</b> 17-03-2011
     * <br>
     * <b>Alteração:</b> Inserido o campo de retorno CODIGO
     * <p/>
     *
     *
     * @param nome - String
     * @param qtdRetorno - int
     * @return lista - List<Fornecedor>
     * @throws Exception - Exception
     */
    public List listaDataGrid(String nome, int qtdRetorno) throws Exception {
        List<Fornecedor> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            if (nome != null) {
                filtros.add(new Filtro(Fornecedor.NOME, nome, Filtro.LIKE));
            }

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Fornecedor.FORNECEDOR_ID));
            propriedades.add(new Propriedade(Fornecedor.CODIGO));
            propriedades.add(new Propriedade(Fornecedor.NOME));
            propriedades.add(new Propriedade(Fornecedor.ENDERECO));
            propriedades.add(new Propriedade(Fornecedor.CEP));
            propriedades.add(new Propriedade(Cidade.NOME, Cidade.class, Cidade.ALIAS_CLASSE));

            //Obtem elementos.
            lista = (ArrayList<Fornecedor>) genericDao.listarByFilter(propriedades, filtros, Fornecedor.NOME, false, Fornecedor.class, qtdRetorno);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;

    }

    /**
     * Método que salva os dados de um fornecedor.
     *
     * <br>
     * <br>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <br>
     * <br>
     *
     * @param obj - Fornecedor
     * @return idObjeto - String
     * @throws Exception - Exception
     */
    public String salvar(Fornecedor obj) throws Exception {
        String idObjeto = "0";
        try {
            if (obj.getFornecedorId() > 0) {
                idObjeto = fornecedorDao.update(obj);
            } else {
                idObjeto = fornecedorDao.persist(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    /**
     * Método que Obtem os dados de um fornecedor.
     * <br>
     * <br>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <br>
     * <br>
     *
     * @param fornecedorId - Integer
     * @return obj - Fornecedor
     * @throws Exception - Exception
     */
    public Fornecedor selectByUnique(Integer fornecedorId) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        Fornecedor obj = new Fornecedor();
        try {
            filtros.add(new Filtro(Fornecedor.FORNECEDOR_ID, fornecedorId, Filtro.EQUAL));

            obj = (Fornecedor) genericDao.selectUnique(filtros, Fornecedor.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return obj;
    }

    /**
     * Método que preenche um combo de Fornecedores.
     * <br>
     * <br>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <br>
     * <br>
     *
     * @return lista - List<Fornecedor>
     * @throws Exception - Exception
     */
    public List<Fornecedor> getCombo() throws Exception {
        List<Fornecedor> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Fornecedor.FORNECEDOR_ID));
            propriedades.add(new Propriedade(Fornecedor.NOME));
            propriedades.add(new Propriedade(Fornecedor.CODIGO));

            //Obtem elementos.
            lista = genericDao.listarByFilter(propriedades, filtros, Fornecedor.NOME, false, Fornecedor.class, Constantes.NO_LIMIT);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    public Fornecedor selectByCodigo(String codigoFornecedor) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        Fornecedor obj = new Fornecedor();
        try {
            filtros.add(new Filtro(Fornecedor.CODIGO, codigoFornecedor, Filtro.EQUAL));
            // Seta as propriedades de retorno da consulta.

            obj = (Fornecedor) genericDao.selectUnique(filtros, propriedadesBasicas, Fornecedor.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return obj;
    }
}
