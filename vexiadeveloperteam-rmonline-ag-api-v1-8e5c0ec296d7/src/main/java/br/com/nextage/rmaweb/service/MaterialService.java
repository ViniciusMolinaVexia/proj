/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.rmaweb.dao.MaterialDao;
import br.com.nextage.rmaweb.entitybean.Deposito;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.entitybean.MaterialDeposito;
import br.com.nextage.rmaweb.entitybean.MaterialDepositoEntrada;
import br.com.nextage.rmaweb.entitybean.MaterialDepositoSaida;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.entitybean.TipoMaterial;
import br.com.nextage.rmaweb.entitybean.UnidadeMedida;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe que recebe requisições do flex e faz o acesso à camada DAO para a
 * obtenção de valores da base de dados.
 *
 * @author marcelo
 */
public class MaterialService {

    private GenericDao<Material> genericDao;
    private MaterialDao materialDao;

    /**
     * Método construtor
     */
    public MaterialService() {
        genericDao = new GenericDao<>();
        materialDao = new MaterialDao();
    }

    /**
     * Método que realiza exclusão de um material.
     * <br>
     * <br>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param material - Material
     * @return result - boolean
     * @throws Exception - Exception
     */
    public boolean excluir(Material material) throws Exception {
        Boolean result = false;
        try {
            result = genericDao.delete(material);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return result;
    }

    /**
     * Método que realiza uma pesquisa para listar os registros em uma grade de
     * dados no flex.
     * <br>
     * <br>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param nome - String
     * @param codigo
     * @param tipoMaterial - TipoMaterial
     * @param qtdRetorno - int
     * @return lista - List<Material>
     * @throws Exception - Exception
     */
    public List listaDataGrid(String nome, String codigo, TipoMaterial tipoMaterial, int qtdRetorno) throws Exception {
        List<Material> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            if (nome != null) {
                filtros.add(new Filtro(Material.NOME, nome, Filtro.LIKE));
            }
            if (nome != null) {
                filtros.add(new Filtro(Material.CODIGO, codigo, Filtro.LIKE));
            }
            if (tipoMaterial != null) {
                filtros.add(new Filtro(Material.TIPO_MATERIAL, tipoMaterial, Filtro.EQUAL));
            }

            if (filtros.size() > 0) {

                // Seta as propriedades de retorno da consulta.
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(Material.MATERIAL_ID));
                propriedades.add(new Propriedade(Material.NOME));
                propriedades.add(new Propriedade(Material.CODIGO));
                propriedades.add(new Propriedade(Material.UNIDADE_MEDIDA));
                propriedades.add(new Propriedade(Material.TIPO_MATERIAL));

                //Obtem elementos.
                lista = (ArrayList<Material>) genericDao.listarByFilter(propriedades, filtros, Material.NOME, false, Material.class, qtdRetorno);

            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;

    }

    /**
     * Método que salva os dados de um material.
     * <br>
     * <br>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param obj - Material
     * @return idObjeto - String
     * @throws Exception - Exception
     */
    public String salvar(Material obj) throws Exception {
        String idObjeto = "0";
        try {
            if (obj.getMaterialId() > 0) {
                idObjeto = materialDao.update(obj);
            } else {
                idObjeto = materialDao.persist(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    /**
     * Método que Obtem os dados de um material.
     * <br>
     * <br>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param materialId - Integer
     * @return filtros - List<Filtro>
     * @throws Exception - Exception
     */
    public Material selectByUnique(Integer materialId) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        Material obj = new Material();
        try {
            filtros.add(new Filtro(Material.MATERIAL_ID, materialId, Filtro.EQUAL));

            obj = (Material) genericDao.selectUnique(filtros, Material.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return obj;
    }

    /**
     * Método para contar a quantidade de registros de uma pesquisa.
     *
     * <br>
     * <br>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param filtros
     * @return Integer - Integer
     * @throws Exception - Exception
     */
    public Integer selectCountByFilter(List<Filtro> filtros) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Método que preenche uma combo de Materiais.
     * <br>
     * <br>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @return lista - List<Material>
     * @throws Exception - Exception
     */
    public List<Material> getCombo() throws Exception {
        List<Material> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();
	    //filtros.add(new Filtro(Material.NOME, nome, Filtro.LIKE));

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Material.MATERIAL_ID));
            propriedades.add(new Propriedade(Material.NOME));
            propriedades.add(new Propriedade(Material.CODIGO));
            propriedades.add(new Propriedade(Material.UNIDADE_MEDIDA));

            //Obtem elementos.
            //lista = genericDao.listarByFilter(propriedades, filtros, Material.NOME, false, Material.class, Constantes.NO_LIMIT);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Método que preenche uma combo de Materiais.
     * <br>
     * <br>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param nome
     * @return lista - List<Material>
     * @throws Exception - Exception
     */
    public List<Material> getAutoComplete(String nome) throws Exception {
        List<Material> lista = new ArrayList<>();
        try {
            // Seta as propriedades de retorno da consulta.
            List<br.com.nextage.persistence_2.classes.Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.MATERIAL_ID));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.NOME));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.CODIGO));

            String aliasPessoa = NxCriterion.montaAlias(Material.ALIAS_CLASSE, Material.UNIDADE_MEDIDA);
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasPessoa));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasPessoa));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasPessoa));

            String aliasTipoMaterial = NxCriterion.montaAlias(Material.ALIAS_CLASSE, Material.TIPO_MATERIAL);

            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(TipoMaterial.DESCRICAO, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));

            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(Material.NOME, NxOrder.NX_ORDER.ASC));

            NxCriterion criterion = NxCriterion.montaRestriction(new br.com.nextage.persistence_2.classes.Filtro(Material.NOME, nome, Filtro.LIKE));
            NxCriterion criterionAux = NxCriterion.montaRestriction(new br.com.nextage.persistence_2.classes.Filtro(Material.CODIGO, nome, Filtro.LIKE));
            criterion = NxCriterion.or(criterion, criterionAux);

            br.com.nextage.persistence_2.dao.GenericDao<Material> dao = new br.com.nextage.persistence_2.dao.GenericDao<>();
            lista = dao.listarByFilter(propriedades, nxOrders, Material.class, 50, criterion);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Método que preenche uma combo de Materiais.
     * <br>
     * <br>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param listaMateriais
     * @param materialAtual
     * @return lista - List<Material>
     * @throws Exception - Exception
     */
    public String salvarAgrupamentoMaterial(List<Material> listaMateriais, Material materialAtual) throws Exception {
        try {
            MaterialDeposito materialDepositoAtual = new MaterialDeposito();
            MaterialDeposito materialDepositoAntigo = new MaterialDeposito();
            //LISTAS
            List<Deposito> listaDeposito = new ArrayList<>();
            List<MaterialDepositoSaida> listaMatDepSaida = new ArrayList<>();
            List<MaterialDepositoEntrada> listaMatDepEntrada = new ArrayList<>();
            List<RmMaterial> listaRmMaterial = new ArrayList<>();
            //SERVICES
            DepositoService depServ = new DepositoService();
            RmMaterialService rmMatServ = new RmMaterialService();
            MaterialDepositoService matDepServ = new MaterialDepositoService();
            MaterialDepositoSaidaService matDepSaidaServ = new MaterialDepositoSaidaService();
            MaterialDepositoEntradaService matDepEntradaServ = new MaterialDepositoEntradaService();

            //Remove o material que e igual.
            listaMateriais.remove(materialAtual);

            //Faz a consulta de todos os Depostitos
            listaDeposito = depServ.selectDepositoIdAll();

            //Faz a consulta de MaterialDeposito por material atual se não existir o objeto ele cria.
            for (Deposito dep : listaDeposito) {
                materialDepositoAtual = matDepServ.selectMaterialDepositoByMaterialAndDeposito(materialAtual, dep);
                if (materialDepositoAtual == null || materialDepositoAtual.getMaterialDepositoId() < 1) {
                    materialDepositoAtual = new MaterialDeposito();
                    materialDepositoAtual.setMaterialDepositoId(-1);
                    materialDepositoAtual.setDeposito(dep);
                    materialDepositoAtual.setMaterial(materialAtual);
                    materialDepositoAtual.setQuantidade(0.0);
                    matDepServ.salvar(materialDepositoAtual);
                }
            }

            for (Material mat : listaMateriais) {
                for (Deposito dep : listaDeposito) {
                    //lista de MaterialDepositoSaida que serão alterados
                    listaMatDepSaida = matDepSaidaServ.selectForAgrupamentoOfMateriais(mat, dep);
                    listaMatDepEntrada = matDepEntradaServ.selectForAgrupamentoOfMateriais(mat, dep);

                    //Consulta o MaterialDeposito por o material Atual.
                    materialDepositoAtual = matDepServ.selectMaterialDepositoByMaterialAndDeposito(materialAtual, dep);
                    //Consulta o MaterialDeposito por o material Antigo.
                    materialDepositoAntigo = matDepServ.selectMaterialDepositoByMaterialAndDeposito(mat, dep);

                    //Faz o agrupamento de material em MATERIAL_DEPOSITO_SAIDA
                    for (MaterialDepositoSaida mDS : listaMatDepSaida) {
                        mDS.setMaterialDeposito(materialDepositoAtual);
                        matDepSaidaServ.salvarMaterialDepositoSaidaAgrupamento(mDS);
                    }

                    //Faz o agrupamento de material em MATERIAL_DEPOSITO_ENTRADA
                    for (MaterialDepositoEntrada mDS : listaMatDepEntrada) {
                        mDS.setMaterialDeposito(materialDepositoAtual);
                        matDepEntradaServ.salvarMaterialDepositoEntradaAgrupamento(mDS);
                    }
                    if (materialDepositoAntigo != null) {
                        //Faz a soma das quantidades do estoque com o material antigo e o novo;
                        materialDepositoAtual.setQuantidade(materialDepositoAtual.getQuantidade() + materialDepositoAntigo.getQuantidade());
                        //Salva a alteração de quantidade no deposito
                        matDepServ.salvarMaterialDepositoAgrupamento(materialDepositoAtual);
                        //Exclui o material antigo no deposito
                        matDepServ.excluir(materialDepositoAntigo);
                    }

                }//FIM DEPOSITO

                //Seleciona todos os materais filtrado por material antigo
                listaRmMaterial = rmMatServ.selectRmMaterialAgrupamento(mat);

                //Faz o agrupamento de material em RmMaterial
                for (RmMaterial rmM : listaRmMaterial) {
                    rmM.setMaterial(materialAtual);
                    rmMatServ.salvarRmMaterialAgrupamento(rmM);
                }

                //Exclui o Material antigo.
                excluir(mat);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return "";
    }

}
