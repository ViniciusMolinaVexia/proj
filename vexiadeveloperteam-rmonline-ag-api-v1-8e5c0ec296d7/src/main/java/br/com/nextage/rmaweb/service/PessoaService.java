/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.rmaweb.dao.PessoaDao;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.Setor;
import br.com.nextage.rmaweb.util.Constantes;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que recebe requisições do flex e faz o acesso à camada DAO para a
 * obtenção de valores da base de dados.
 *
 * @author Felipe
 */
public class PessoaService {

    private GenericDao<Pessoa> genericDao;
    private PessoaDao pessoaDao;

    /**
     * Método construtor
     */
    public PessoaService() {
        genericDao = new GenericDao<>();
        pessoaDao = new PessoaDao();
    }

    /**
     * Método que realiza exclusão de uma pessoa.
     * <p/>
     * <b>Autor:</b> Juliano A. Felipe.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @param pessoa - Pessoa
     * @return result - Boolean
     * @throws Exception - Exception
     */
    public boolean excluir(Pessoa pessoa) throws Exception {
        Boolean result = false;
        try {
            result = genericDao.delete(pessoa);
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
     * <b>Autor:</b> Juliano A. Felipe.
     * <b>Data:</b> 15-03-2011
     * <p/>
     * <h4>ALTERAÇÕES:</h4>
     * <p/>
     * <b>Autor:</b> Juliano A. Felipe
     * <b>Data:</b> 18-03-2011
     * <br>
     * <b>Alteração:</b> Inserido o campo de retorno REFERENCIA
     * <p/>
     *
     * @param nome - String
     * @param qtdRetorno - int
     * @return lista
     * @throws Exception - Exception
     */
    public List listaDataGrid(String nome, int qtdRetorno) throws Exception {
        List<Pessoa> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            if (nome != null) {
                filtros.add(new Filtro(Pessoa.NOME, nome, Filtro.LIKE));
            }

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA));
            propriedades.add(new Propriedade(Pessoa.CPF));
            propriedades.add(new Propriedade(Pessoa.NOME));
            propriedades.add(new Propriedade(Pessoa.IS_REQUISITANTE));
            propriedades.add(new Propriedade(Pessoa.FUNCAO));
            propriedades.add(new Propriedade(Pessoa.TELEFONE));
            propriedades.add(new Propriedade(Pessoa.EMAIL));

            propriedades.add(new Propriedade(Setor.SETOR_ID, Setor.class, Setor.ALIAS_CLASSE));
            propriedades.add(new Propriedade(Setor.DESCRICAO, Setor.class, Setor.ALIAS_CLASSE));

            //Obtem elementos.
            lista = (ArrayList<Pessoa>) genericDao.listarByFilter(propriedades, filtros, Pessoa.NOME, false, Pessoa.class, qtdRetorno);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;

    }

    /**
     * Método que salva os dados de uma pessoa.
     * <p/>
     * <b>Autor:</b> Juliano A. Felipe.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @param obj - Pessoa
     * @return idObjeto - String
     * @throws Exception - Exception
     */
    public String salvar(Pessoa obj) throws Exception {
        String idObjeto = "0";
        try {
            if (obj.getEmail() != null) {
                obj.setEmail(obj.getEmail().trim());
            }
            if (obj.getPessoaId() > 0) {
                idObjeto = pessoaDao.update(obj);
            } else {
                idObjeto = pessoaDao.persist(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    /**
     * Método que Obtem os dados de uma pessoa.
     * <p/>
     * <b>Autor:</b> Juliano A. Felipe.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @param pessoaId - Integer
     * @return obj - Pessoa
     * @throws Exception - Exception
     */
    public Pessoa selectByUnique(Integer pessoaId) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        Pessoa obj = new Pessoa();
        try {
            filtros.add(new Filtro(Pessoa.PESSOA_ID, pessoaId, Filtro.EQUAL));

            obj = (Pessoa) genericDao.selectUnique(filtros, Pessoa.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return obj;
    }

    /**
     * Método que preenche uma combo de Pessoas.
     * <p/>
     * <b>Autor:</b> Juliano A. Felipe.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @return lista
     * @throws Exception - Exception
     */
    public List<Pessoa> getCombo() throws Exception {
        List<Pessoa> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
            propriedades.add(new Propriedade(Pessoa.NOME));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA));
            propriedades.add(new Propriedade(Pessoa.FUNCAO));

            //Obtem elementos.
            lista = genericDao.listarByFilter(propriedades, filtros, Pessoa.NOME, false, Pessoa.class, Constantes.NO_LIMIT);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Retorna uma lista de Pessoas que são Requisitantes de RM's.
     * <p/>
     * <b>Autor:</b> Juliano A. Felipe.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @return lista
     * @throws Exception - Exception
     */
    public List<Pessoa> getComboRequisitante() throws Exception {
        List<Pessoa> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();
            filtros.add(new Filtro(Pessoa.IS_REQUISITANTE, (short) 1, Filtro.EQUAL));

            List<Propriedade> prop = new ArrayList<>();

            prop.add(new Propriedade(Pessoa.PESSOA_ID));
            prop.add(new Propriedade(Pessoa.NOME));
            prop.add(new Propriedade(Pessoa.REFERENCIA));
            prop.add(new Propriedade(Pessoa.FUNCAO));

            lista = genericDao.listarByFilter(prop, filtros, Pessoa.NOME, false, Pessoa.class, Constantes.NO_LIMIT);

        } catch (Exception e) {
            lista = null;
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }
}
