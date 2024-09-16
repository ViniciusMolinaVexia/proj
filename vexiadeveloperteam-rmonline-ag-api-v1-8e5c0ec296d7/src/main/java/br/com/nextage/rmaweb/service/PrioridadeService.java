/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.dao.CentroDAO;
import br.com.nextage.rmaweb.dao.PrioridadeDao;
import br.com.nextage.rmaweb.entitybean.Centro;
import br.com.nextage.rmaweb.entitybean.Prioridade;
import br.com.nextage.rmaweb.enumerator.PrioridadeEnum;
import br.com.nextage.rmaweb.util.Constantes;

/**
 * Classe que recebe requisições do flex e faz o acesso à camada DAO para a
 * obtenção de valores da base de dados.
 *
 * @author Felipe
 */
public class PrioridadeService {

    private GenericDao<Prioridade> genericDao;
    private PrioridadeDao prioridadeDao;
    private CentroDAO centroDAO = CentroDAO.getInstance();

    /**
     * Método construtor
     */
    public PrioridadeService() {
        genericDao = new GenericDao<Prioridade>();
        prioridadeDao = new PrioridadeDao();
    }
    
    public void salvarPrioridade(List<Prioridade> prioridades) throws Exception{
        preencherCodigoSap(prioridades);

    	for(Prioridade p : prioridades) {
    		if(p.getPrioridadeId()!=null) {
    			prioridadeDao.update(p);
    		}else {
    			prioridadeDao.persist(p);
    		}
    	}
    }

    /**
     * ZR01
     * ZR02
     * ZR03
     */
    private void preencherCodigoSap(List<Prioridade> prioridades) {
        Optional.ofNullable(prioridades).orElse(Collections.emptyList()).stream().forEach(prioridade -> {
            PrioridadeEnum prioridadeEnum = PrioridadeEnum.valueOf(prioridade.getCodigo());
            prioridade.setCodigoSap(prioridadeEnum.getCodigSap());
        });
    }

    public List<Prioridade> getPrioridadesCentro(Integer centroId) throws Exception {
        List<Prioridade> lista = new ArrayList<>();
        try {
            lista = prioridadeDao.getHql("select p from Prioridade p where p.centro.centroId = " + centroId);
            if (lista == null || lista.size() == 0) {
                Centro c = centroDAO.getCentroPorId(centroId);

                Prioridade p = new Prioridade();
                p.setCodigo("MAQ_PARADA");
                p.setDescricao("Máquina Parada");
                p.setCentro(c);
                lista.add(p);

                p = new Prioridade();
                p.setCodigo("URGENTE");
                p.setDescricao("Urgente");
                p.setCentro(c);
                lista.add(p);

                p = new Prioridade();
                p.setCodigo("NORMAL");
                p.setDescricao("Normal");
                p.setCentro(c);
                lista.add(p);

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;

    }

    public List<Prioridade> getPrioridadesCentroAndArea(Integer centroId, Integer areaId) throws Exception {
        List<Prioridade> lista;
        try {
            lista = prioridadeDao.getHql(
                            "select p from Prioridade p where p.centro.centroId = " + centroId + " and p.area.areaId = " + areaId + " and p.ativo=1");
            if (lista == null) {
                lista = new ArrayList<>();
            }
            if (lista.isEmpty()) {
                Centro c = centroDAO.getCentroPorId(centroId);

                Prioridade p = new Prioridade();
                p.setCodigo("MAQ_PARADA");
                p.setDescricao("Máquina Parada");
                p.setCentro(c);
                lista.add(p);

                p = new Prioridade();
                p.setCodigo("URGENTE");
                p.setDescricao("Urgente");
                p.setCentro(c);
                lista.add(p);

                p = new Prioridade();
                p.setCodigo("NORMAL");
                p.setDescricao("Normal");
                p.setCentro(c);
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;

    }

    /**
     * Método que realiza exclusão de uma Prioridade.
     * <p/>
     * <b>Autor:</b> Felipe.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @param prioridade
     * @return
     * @throws Exception
     */
    public boolean excluir(Prioridade prioridade) throws Exception {
        Boolean result = false;
        try {
            result = genericDao.delete(prioridade);
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
     * <b>Autor:</b> Felipe.
     * <b>Data:</b> 15-03-2011
     * <bp/>
     *
     * @param nome
     * @param qtdRetorno
     * @return
     * @throws Exception
     */
    public List listaDataGrid(String nome, int qtdRetorno) throws Exception {
        List<Prioridade> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            if (nome != null) {
                filtros.add(new Filtro(Prioridade.DESCRICAO, nome, Filtro.LIKE));
            }

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Prioridade.PRIORIDADE_ID));
            propriedades.add(new Propriedade(Prioridade.DESCRICAO));

            //Obtem elementos.
            lista = (ArrayList<Prioridade>) genericDao.listarByFilter(propriedades, filtros, Prioridade.DESCRICAO, false, Prioridade.class, qtdRetorno);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;

    }

    /**
     * Método que salva os dados de uma Prioridade.
     * <p/>
     * <b>Autor:</b> Felipe.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public String salvar(Prioridade obj) throws Exception {
        String idObjeto = "0";
        try {
            if (obj.getPrioridadeId() > 0) {
                idObjeto = prioridadeDao.update(obj);
            } else {
                idObjeto = prioridadeDao.persist(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    /**
     * Método que Obtem os dados de uma Prioridade.
     * <p/>
     * <b>Autor:</b> Felipe.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @param prioridadeId
     * @return
     * @throws Exception
     */
    public Prioridade selectByUnique(Integer prioridadeId) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        Prioridade obj = new Prioridade();
        try {
            filtros.add(new Filtro(Prioridade.PRIORIDADE_ID, prioridadeId, Filtro.EQUAL));

            obj = (Prioridade) genericDao.selectUnique(filtros, Prioridade.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return obj;
    }

    /**
     * Método que preenche uma combo de Prioridades.
     * <p/>
     * <b>Autor:</b> Felipe.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @return @throws Exception
     */
    public List<Prioridade> getCombo() throws Exception {
        List<Prioridade> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Prioridade.PRIORIDADE_ID));
            propriedades.add(new Propriedade(Prioridade.DESCRICAO));

            //Obtem elementos.
            lista = genericDao.listarByFilter(propriedades, filtros, Prioridade.DESCRICAO, false, Prioridade.class, Constantes.NO_LIMIT);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Método que preenche uma combo de Prioridades.
     * <p/>
     * <b>Autor:</b> Felipe.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @return @throws Exception
     */
    public List<Prioridade> getComboNivelPrioridade() throws Exception {
        List<Prioridade> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Prioridade.PRIORIDADE_ID));
            propriedades.add(new Propriedade(Prioridade.DESCRICAO));

            //Obtem elementos.
            lista = genericDao.listarByFilter(propriedades, filtros, Prioridade.DESCRICAO, false, Prioridade.class, Constantes.NO_LIMIT);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

}
