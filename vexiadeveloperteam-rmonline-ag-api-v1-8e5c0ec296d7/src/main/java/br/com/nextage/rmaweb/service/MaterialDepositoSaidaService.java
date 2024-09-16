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
import br.com.nextage.rmaweb.dao.MaterialDepositoSaidaDao;
import br.com.nextage.rmaweb.entitybean.Deposito;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.entitybean.MaterialDeposito;
import br.com.nextage.rmaweb.entitybean.MaterialDepositoSaida;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.entitybean.UnidadeMedida;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.util.RelatorioFlexUtil;
import br.com.nextage.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author nextage
 */
public class MaterialDepositoSaidaService {

    private GenericDao<MaterialDepositoSaida> genericDao;
    private MaterialDepositoSaidaDao materialDepositoSaidaDao;

    public MaterialDepositoSaidaService() {
        this.genericDao = new GenericDao<>();
        this.materialDepositoSaidaDao = new MaterialDepositoSaidaDao();
    }

    /**
     * Método que realiza exclusão de um MaterialDepositoSaida.
     * <br>
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 09/05/2011
     * <br>
     * <br>
     *
     * @param obj - MaterialDepositoSaida
     * @return result - boolean
     * @throws Exception - Exception
     */
    public boolean excluir(MaterialDepositoSaida obj) throws Exception {
        Boolean result = false;
        try {
            result = genericDao.delete(obj);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return result;

    }

    /**
     * Método que salva os dados de um MaterialDepositoSaida.
     *
     * <br>
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 09/05/2011
     * <br>
     * <br>
     *
     * @param obj - MaterialDepositoSaida
     * @return idObjeto - String
     * @throws Exception - Exception
     */
    public String salvar(MaterialDepositoSaida obj) throws Exception {
        String idObjeto = "0";
        try {
            idObjeto = materialDepositoSaidaDao.persist(obj);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    /**
     * Método que Obtem os dados do historico de saida de matrerial do estoque.
     * <br>
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 11/05/2011
     * <br>
     * <br>
     *
     * @param material - Material
     * @param deposito - Deposito
     * @param opcFilter
     * @param numeroRm
     * @param dataFimSaida
     * @param dataInicioSaida
     * @return filtros - List<Filtro>
     * @throws Exception - Exception
     */
    public List listaDataGridByMaterialAndDeposito(Material material, Deposito deposito, int opcFilter, String numeroRm, Date dataInicioSaida, Date dataFimSaida) throws Exception {
        MaterialDepositoSaida obj = new MaterialDepositoSaida();
        List<MaterialDepositoSaida> lista = new ArrayList<>();
        try {
            String aliasRetiradoPor = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.RETIRADO_POR);
            String aliasRm = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.RM);
            String aliasMaterialDeposito = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO);
            String aliasDeposito = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDeposito.DEPOSITO);
            String aliasMaterial = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDeposito.MATERIAL);
            String aliasUnidMedida = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDeposito.MATERIAL, Material.UNIDADE_MEDIDA);

            NxCriterion nxCriterion = null;
            NxCriterion nxCriterionAux = null;
            //opcFilter -Opção de consulta por radio buton
            //1 - Todos os Registros
            //2 - Por RM
            //3 - Por Inventario
            if (opcFilter == 2) {
                nxCriterion = NxCriterion.montaRestriction(new br.com.nextage.persistence_2.classes.Filtro(MaterialDepositoSaida.RM, null, Filtro.NOT_NULL));
            } else if (opcFilter == 3) {
                nxCriterion = NxCriterion.montaRestriction(new br.com.nextage.persistence_2.classes.Filtro(MaterialDepositoSaida.RM, null, Filtro.IS_NULL));
            }
            if (material != null && material.getMaterialId() > 0) {
                nxCriterionAux = NxCriterion.montaRestriction(new br.com.nextage.persistence_2.classes.Filtro(MaterialDeposito.MATERIAL, material, Filtro.EQUAL, aliasMaterialDeposito));
                if (nxCriterion == null) {
                    nxCriterion = nxCriterionAux;
                } else {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                }
            }
            if (deposito != null && deposito.getDepositoId() > 0) {
                nxCriterionAux = NxCriterion.montaRestriction(new br.com.nextage.persistence_2.classes.Filtro(MaterialDeposito.DEPOSITO, deposito, Filtro.EQUAL, aliasMaterialDeposito));
                if (nxCriterion == null) {
                    nxCriterion = nxCriterionAux;
                } else {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                }
            }
            if (numeroRm != null && !numeroRm.isEmpty()) {
                nxCriterionAux = NxCriterion.montaRestriction(new br.com.nextage.persistence_2.classes.Filtro(Rm.NUMERO_RM, numeroRm, Filtro.LIKE, aliasRm));
                if (nxCriterion == null) {
                    nxCriterion = nxCriterionAux;
                } else {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                }
            }
            if (dataInicioSaida != null && dataFimSaida != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new br.com.nextage.persistence_2.classes.Filtro(MaterialDepositoSaida.DATA_SAIDA, dataInicioSaida, dataFimSaida, Boolean.TRUE, null, null));
                if (nxCriterion == null) {
                    nxCriterion = nxCriterionAux;
                } else {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                }
            }

            if (nxCriterion != null) {

                // Seta as propriedades de retorno da consulta.
                List<br.com.nextage.persistence_2.classes.Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_ID));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDepositoSaida.DATA_SAIDA));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDepositoSaida.OBSERVACAO));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDepositoSaida.QUANTIDADE));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDepositoSaida.NUMERO_PROTOCOLO_SAIDA));

                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.RM_ID, Rm.class, aliasRm));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));

                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRetiradoPor));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Pessoa.NOME, Pessoa.class, aliasRetiradoPor));

                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID, MaterialDeposito.class, aliasMaterialDeposito));

                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.NOME, Material.class, aliasMaterial));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.QUANTIDADE_MINIMA, Material.class, aliasMaterial));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidMedida));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidMedida));

                //Obtem elementos.
                br.com.nextage.persistence_2.dao.GenericDao<MaterialDepositoSaida> dao = new br.com.nextage.persistence_2.dao.GenericDao<>();

                List<NxOrder> nxOrders = Arrays.asList(
                        new NxOrder(MaterialDepositoSaida.MATERIAL_DEPOSITO + "." + MaterialDeposito.MATERIAL_DEPOSITO_ID, NxOrder.NX_ORDER.ASC));

                lista = dao.listarByFilter(propriedades, null, MaterialDepositoSaida.class, -1, nxCriterion);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return lista;
    }

    /**
     * Método que Obtem os dados do historico de saida de matrerial do estoque,
     * filtrado pelo numero do protocolo de saida
     *
     * <br>
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 11/05/2011
     * <br>
     * <br>
     *
     * @param numero
     * @return filtros - List<Filtro>
     * @throws Exception - Exception
     */
    public List listaDataGridByNumeroProtocolo(Integer numero) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        MaterialDepositoSaida obj = new MaterialDepositoSaida();
        List<MaterialDepositoSaida> lista = new ArrayList<>();
        try {

            if (numero > 0) {
                filtros.add(new Filtro(MaterialDepositoSaida.NUMERO_PROTOCOLO_SAIDA, numero, Filtro.EQUAL));
            }

            if (filtros.size() > 0) {

                // Seta as propriedades de retorno da consulta.
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_ID));
                propriedades.add(new Propriedade(MaterialDepositoSaida.DATA_SAIDA));
                propriedades.add(new Propriedade(MaterialDepositoSaida.OBSERVACAO));
                propriedades.add(new Propriedade(MaterialDepositoSaida.QUANTIDADE));
                propriedades.add(new Propriedade(MaterialDepositoSaida.NUMERO_PROTOCOLO_SAIDA));

                propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));
                propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));

                propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, MaterialDepositoSaida.RETIRADO_POR));
                propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, MaterialDepositoSaida.RETIRADO_POR));

                propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID, MaterialDeposito.class, MaterialDeposito.ALIAS_CLASSE_UNDERLINE));
                propriedades.add(new Propriedade(MaterialDeposito.DEPOSITO, MaterialDeposito.class, MaterialDeposito.ALIAS_CLASSE_UNDERLINE));
                propriedades.add(new Propriedade(MaterialDeposito.MATERIAL, MaterialDeposito.class, MaterialDeposito.ALIAS_CLASSE_UNDERLINE));

                //Obtem elementos.
                lista = (ArrayList<MaterialDepositoSaida>) genericDao.listarByFilter(propriedades, filtros, MaterialDepositoSaida.MATERIAL_DEPOSITO, false, MaterialDepositoSaida.class, -1);

            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return lista;
    }

    /**
     * Método que Obtem os dados do para fazer o agrupamento de materiais .
     * <br>
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 11/05/2011
     * <br>
     * <br>
     *
     * @param material - Material
     * @param deposito - Deposito
     * @return filtros - List<Filtro>
     * @throws Exception - Exception
     */
    public List selectForAgrupamentoOfMateriais(Material material, Deposito deposito) throws Exception {
        List<Filtro> filtros = new ArrayList<>();

        List<MaterialDepositoSaida> lista = new ArrayList<>();
        try {
            filtros.add(new Filtro(MaterialDeposito.MATERIAL, material, Filtro.EQUAL, MaterialDeposito.ALIAS_CLASSE));
            filtros.add(new Filtro(MaterialDeposito.DEPOSITO, deposito, Filtro.EQUAL, MaterialDeposito.ALIAS_CLASSE));

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_ID));
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID, MaterialDeposito.class, MaterialDeposito.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(MaterialDeposito.DEPOSITO, MaterialDeposito.class, MaterialDeposito.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL, MaterialDeposito.class, MaterialDeposito.ALIAS_CLASSE_UNDERLINE));

            //Obtem elementos.
            lista = (ArrayList<MaterialDepositoSaida>) genericDao.listarByFilter(propriedades, filtros, null, false, MaterialDepositoSaida.class, -1);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return lista;
    }

    /**
     * Update para o campo MaterialDepositoID ,metodo utilizado para fazer o
     * agrupamento de materiais.
     * <br>
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 20/05/2011
     * <br>
     * <br>
     *
     * @param obj - MaterialDepositoSaida
     * @return "" - String
     * @throws Exception - Exception
     */
    public String salvarMaterialDepositoSaidaAgrupamento(MaterialDepositoSaida obj) throws Exception {
        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDepositoSaida.class, MaterialDepositoSaida.ALIAS_CLASSE_UNDERLINE));
            genericDao.update(obj, propriedades);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return "";
    }

    /**
     * Método responsável por gerar protocolo de saida de materiais em formato
     * PDF.
     * <p/>
     * <b>Autor:</b> Jerry
     * <b>Data:</b> 25-05-2011
     * <p/>
     *
     * @param request
     * @param outputStream - ServletOutputStream
     * @param nomeJasper - String
     * @throws Exception - Exception
     */
    public void gerarProtocoloRetirada(HttpServletRequest request, ServletOutputStream outputStream, String nomeJasper) throws Exception {
        try {
            HashMap parameters = new HashMap();

            // Verifica o id da retirada.
            if (request.getParameter("numeroProtocolo") != null && !request.getParameter("numeroProtocolo").isEmpty()) {
                Integer protocolo = Integer.valueOf(request.getParameter("numeroProtocolo"));

                // Passa ID para o relatório.
                parameters.put("NUMERO_PROTOCOLO", protocolo);

                // Caminho da imagem.
                parameters.put(Constantes.URL_IMAGES, Util.getFileConfig().getString("config.url_images"));

                // Obtem o caminhos dos subrelatórios.
                parameters.put(Constantes.SUBREPORT_DIR, Util.getFileConfig().getString("config.url_relatorios"));

                RelatorioFlexUtil.geraPdf(outputStream, nomeJasper, parameters);

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
