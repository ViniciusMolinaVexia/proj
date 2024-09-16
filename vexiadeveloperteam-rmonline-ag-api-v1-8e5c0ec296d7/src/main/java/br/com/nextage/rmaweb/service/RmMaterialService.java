/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.classes.NxCriterion;
import br.com.nextage.persistence.classes.NxOrder;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.rmaweb.classes.RmMaterialRecebimentoRetirada;
import br.com.nextage.rmaweb.dao.RmMaterialDao;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.service.integracao.RmaService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.AlmoxarifadoVo;

import java.util.*;

/**
 *
 * @author marcelo
 */
public class RmMaterialService {

    private GenericDao<RmMaterialRecebimento> genericDaoRecebimento;
    private GenericDao<RmMaterialRetirada> genericDaoRetirada;
    private GenericDao<RmMaterial> genericDao;
    private RmMaterialDao rmMaterialDao;

    /**
     *
     */
    public RmMaterialService() {
        genericDao = new GenericDao<>();
        rmMaterialDao = new RmMaterialDao();
        genericDaoRecebimento = new GenericDao<>();
        genericDaoRetirada = new GenericDao<>();
    }

    /**
     *
     * @param rmMaterial
     * @return
     * @throws Exception
     */
    public boolean excluir(RmMaterial rmMaterial) throws Exception {
        Boolean result = false;
        try {
            result = genericDao.delete(rmMaterial);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return result;
    }

    /**
     *
     * @param rmId
     * @param qtdRetorno
     * @return
     * @throws Exception
     */
    public List listaDataGrid(Integer rmId, int qtdRetorno) throws Exception {
        List<RmMaterial> lista = new ArrayList<>();
        Rm rm = null;
        try {
            List<Filtro> filtros = new ArrayList<>();

            if (rmId != null && rmId > 0) {
                rm = new Rm(rmId);
                filtros.add(new Filtro(Rm.RM_ID, rmId, Filtro.EQUAL, Rm.ALIAS_CLASSE));
            }

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO));
            propriedades.add(new Propriedade(RmMaterial.ORDEM));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));

            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, Material.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Material.NOME, Material.class, Material.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, Material.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Material.UNIDADE_MEDIDA, Material.class, Material.ALIAS_CLASSE_UNDERLINE));

            //Obtem elementos.
            lista = (ArrayList<RmMaterial>) genericDao.listarByFilter(propriedades, filtros, RmMaterial.ORDEM, false, RmMaterial.class, qtdRetorno);
            //Melhora a perfonce em recuperar as quantidades dos materiais;
            lista = setaQtdeRetiradaRecebimentoForRmMaterial(lista, rm);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public String salvar(RmMaterial obj, String usuario) throws Exception {
        String idObjeto = "0";
        //Verificar mudancas no obj
        try {

            obj.setOrdem(this.proximaOrdem(obj.getRm().getRmId()));

            if (obj.getRmMaterialId() > 0) {
                idObjeto = rmMaterialDao.update(obj);
            } else {
                idObjeto = rmMaterialDao.persist(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    /**
     *
     * @param rmMaterialId
     * @return
     * @throws Exception
     */
    public RmMaterial selectByUnique(Integer rmMaterialId) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        RmMaterial obj = new RmMaterial();
        try {
            filtros.add(new Filtro(RmMaterial.RM_MATERIAL_ID, rmMaterialId, Filtro.EQUAL));

            obj = (RmMaterial) genericDao.selectUnique(filtros, RmMaterial.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return obj;
    }

    /**
     * Retorna uma lista de RmMaterial filtrado por uma ou varias
     * Rms,DATA_COMPRA==null e DATA_ACEITE_COMPRADOR!=NOT NULL
     *
     * @param numeroRm
     * @param comprador
     * @param qtdRetorno
     * @param comCompra
     * @return List<RmMaterial>
     * @throws Exception
     */
    public List listaCompraRmMaterial(String numeroRm, Comprador comprador, Boolean comCompra, int qtdRetorno) throws Exception {
        List<RmMaterial> lista = new ArrayList<>();
        List<RmMaterial> listaRmMat = new ArrayList<>();
        try {
            List<Filtro> filtros;
            List<Propriedade> propriedades;

            //Separa a string por ; para separar varias rms.
            String[] listaNumeroRm = numeroRm.split(";");
            int cont = 0;
            do {
                filtros = new ArrayList<>();
                lista = new ArrayList<>();
                propriedades = new ArrayList<>();

                if (numeroRm != null && !numeroRm.isEmpty()) {
                    filtros.add(new Filtro(Rm.NUMERO_RM, listaNumeroRm[cont], Filtro.LIKE, Rm.ALIAS_CLASSE));
                }
                if (comprador != null) {
                    filtros.add(new Filtro(Rm.COMPRADOR, comprador, Filtro.EQUAL, Rm.ALIAS_CLASSE));
                }

                if (comCompra) {
                    filtros.add(new Filtro(RmMaterial.DATA_COMPRA, null, Filtro.NOT_NULL));
                } else {
                    filtros.add(new Filtro(RmMaterial.DATA_COMPRA, null, Filtro.IS_NULL));
                }

                filtros.add(new Filtro(Rm.DATA_ACEITE_COMPRADOR, null, Filtro.NOT_NULL, Rm.ALIAS_CLASSE));

                // Seta as propriedades de retorno da consulta.
                propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
                propriedades.add(new Propriedade(RmMaterial.OBSERVACAO));
                propriedades.add(new Propriedade(RmMaterial.ORDEM));
                propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));
                propriedades.add(new Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA));
                propriedades.add(new Propriedade(RmMaterial.DATA_PREVISAO_CHEGADA));
                propriedades.add(new Propriedade(RmMaterial.DATA_COMPRA));

                propriedades.add(new Propriedade(RmMaterial.FORNECEDOR_ID));

                propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, Material.ALIAS_CLASSE_UNDERLINE));
                propriedades.add(new Propriedade(Material.NOME, Material.class, Material.ALIAS_CLASSE_UNDERLINE));
                propriedades.add(new Propriedade(Material.UNIDADE_MEDIDA, Material.class, Material.ALIAS_CLASSE_UNDERLINE));

                propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));
                propriedades.add(new Propriedade(Rm.COMPRADOR, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));
                propriedades.add(new Propriedade(Rm.DATA_APLICACAO, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));
                propriedades.add(new Propriedade(Rm.REQUISITANTE, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));
                propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));
                propriedades.add(new Propriedade(Rm.DATA_RECEBIMENTO, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));

                //Obtem elementos.
                String orderBy = Rm.ALIAS_CLASSE + "." + Rm.NUMERO_RM;
                lista = (ArrayList<RmMaterial>) genericDao.listarByFilter(propriedades, filtros, orderBy, false, RmMaterial.class, qtdRetorno);

                //Se o filtro comCompra for igual a true ,no if verifica se os itens do Rm ja foram comprados e ainda não recebidos.
                if (comCompra) {
                    RmMaterialRecebimentoService rmMatRecServ = new RmMaterialRecebimentoService();
                    List<RmMaterialRecebimento> aux = new ArrayList<>();
                    for (RmMaterial rmM : lista) {
                        if (!listaRmMat.contains(rmM)) {
                            aux = rmMatRecServ.listaDataGrid(rmM);
                            if (aux == null || aux.size() < 1) {
                                listaRmMat.add(rmM);
                            }
                        }
                    }

                } else {
                    //verifica se ja foi adicionado a rmMaterial por outro filtro
                    for (RmMaterial rmM : lista) {
                        if (!listaRmMat.contains(rmM)) {
                            listaRmMat.add(rmM);
                        }
                    }
                }
                //conta em qual posição esta no array de rms
                cont++;
            } while (cont < listaNumeroRm.length);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return listaRmMat;
    }

    /**
     *
     * @param lista
     * @return
     * @throws Exception
     */
    public String salvarListaAlteradaRmMaterial(List<RmMaterial> lista) throws Exception {
        try {
            for (RmMaterial obj : lista) {
                rmMaterialDao.update(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return "Sucesso";
    }

    /**
     * Retorna uma lista de RmMaterial ordenados pelo campo ORDEM. Esse método
     * será usado para calcular a ordem do Material dentro da RM
     * automaticamente.
     * <p/>
     * <b>Autor:</b> Juliano A. Felipe
     * <b>Data:</b> 18-03-2011.
     * <p/>
     *
     * @param rmId ID da RM para buscar os materiais ligados a ela.
     * @return
     * <p/>
     * <b>ordem:</b> com a ultima ordem encontrada acrescido de 1. Caso não
     * exista nenhum material na RM, retorna 1.
     * <b>null:</b> Se a quantidade de registros for maior ou igual a 6.
     *
     */
    public Integer proximaOrdem(Integer rmId) {
        Integer ordem = 0;
        List<RmMaterial> lista = new ArrayList<>();
        List<Filtro> filtros = new ArrayList<>();
        List<Propriedade> prop = new ArrayList<>();

        try {
            prop.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
            prop.add(new Propriedade(RmMaterial.ORDEM));

            filtros.add(new Filtro(RmMaterial.RM, new Rm(rmId), Filtro.EQUAL));

            lista = genericDao.listarByFilter(prop, filtros, RmMaterial.ORDEM, true, RmMaterial.class, -1);

            // Se o o número de registros é maior ou igual a 6, retorna -1
            //if (lista.size() >= 6) {
            //    return null;
            //}
            // Se o número de registros é 0, retorna 0;
            if (lista.size() < 1) {
                return ordem + 1;
            }

            // O primeiro objeto da lista é o de maior ordem
            // Acrescenta-se 1 para retornar a ordem certa para o próximo registro
            ordem = lista.get(0).getOrdem() + 1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ordem;
    }

    /**
     * Método que preenche uma combo de Materiais com base no numero da
     * rmMaterial.
     * <br>
     * <br>
     * <b>Autor:</b> Jerry.
     * <b>Data:</b> 24/03/2011
     * <br>
     * <br>
     *
     * @return lista
     * @param numeroRm
     * @throws Exception - Exception
     */
    public List<RmMaterial> getComboByNumeroRm(String numeroRm) throws Exception {
        List<RmMaterial> lista = new ArrayList<>();
        List<RmMaterial> auxLista = new ArrayList<>();
        br.com.nextage.persistence_2.dao.GenericDao<RmMaterial> genericDao = new br.com.nextage.persistence_2.dao.GenericDao<>();
        try {
            String aliasRm = br.com.nextage.persistence_2.classes.NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);
            String aliasMaterial = br.com.nextage.persistence_2.classes.NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL);
            String aliasDeposito = br.com.nextage.persistence_2.classes.NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.DEPOSITO_ID);
            String aliasUnidadeMedida = br.com.nextage.persistence_2.classes.NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL, Material.UNIDADE_MEDIDA);
            String aliasTipoMaterial = br.com.nextage.persistence_2.classes.NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL, Material.TIPO_MATERIAL);

            // Seta as propriedades de retorno da consulta.
            List<br.com.nextage.persistence_2.classes.Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterial.RM_MATERIAL_ID));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterial.QUANTIDADE));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterial.FLUXO_MATERIAL));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterial.ESTA_NO_ORCAMENTO));

            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));

            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.UNIDADE_MEDIDA, Material.class, aliasMaterial));

            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));

            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));

            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));

            br.com.nextage.persistence_2.classes.NxCriterion nxCriterion
                    = br.com.nextage.persistence_2.classes.NxCriterion.montaRestriction(
                            new br.com.nextage.persistence_2.classes.Filtro(Rm.NUMERO_RM, numeroRm, Filtro.EQUAL, aliasRm));

            nxCriterion = br.com.nextage.persistence_2.classes.NxCriterion.and(nxCriterion, br.com.nextage.persistence_2.classes.NxCriterion.montaRestriction(
                    new br.com.nextage.persistence_2.classes.Filtro(RmMaterial.COLETOR_ESTOQUE, true, Filtro.NOT_EQUAL)));

            List<br.com.nextage.persistence_2.classes.NxOrder> orders
                    = Arrays.asList(new br.com.nextage.persistence_2.classes.NxOrder(Rm.ALIAS_CLASSE + "." + Rm.NUMERO_RM,
                                    br.com.nextage.persistence_2.classes.NxOrder.NX_ORDER.ASC));

            auxLista = genericDao.listarByFilter(propriedades, orders, RmMaterial.class, Constantes.NO_LIMIT, nxCriterion);

            RmaService rmaService = new RmaService();
            //List<RmMaterialRecebimento> listaRec;
            List<RmMaterialRetirada> listaRet;
            List<MaterialDepositoSaida> listaMds;
            Double qtdeRec = 0.0;
            Double qtdeRet = 0.0;
            for (RmMaterial rmM : auxLista) {
                qtdeRec = 0.0;
                qtdeRet = 0.0;
                //Recupera a quantidade Recebida e Retirada
                //listaRec = service.listaRmMaterialRecebimentoQtde(rmM, null);
                listaMds = rmaService.listarMaterialDepositoSaida(rmM);
                listaRet = listaRmMaterialRetiradaQtde(rmM);

//                for (RmMaterialRecebimento rmRec : listaRec) {
//                    qtdeRec = qtdeRec + rmRec.getQuantidade();
//                }
                for (MaterialDepositoSaida materialDepositoSaida : listaMds) {
                    qtdeRec += materialDepositoSaida.getQuantidade();
                }

                for (RmMaterialRetirada rmRet : listaRet) {
                    qtdeRet = qtdeRet + rmRet.getQuantidade();
                }

                rmM.setQtdRecebido(qtdeRec);
                rmM.setQtdRetirado(qtdeRet);
                if (rmM.getQtdRecebido() > 0 && !Objects.equals(qtdeRec, qtdeRet)) {
                    lista.add(rmM);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    private List listaRmMaterialRetiradaQtde(RmMaterial rmM) {
        List<RmMaterialRetirada> lista = new ArrayList<>();
        try {
            String aliasRmMaterial = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RM_MATERIAL);
            String aliasRm = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RM_MATERIAL, RmMaterial.RM);

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialRetirada.RM_MATERIAL_RETIRADA_ID));
            propriedades.add(new Propriedade(RmMaterialRetirada.QUANTIDADE));

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM_MATERIAL_ID, rmM.getRmMaterialId(), Filtro.EQUAL, RmMaterialRetirada.RM_MATERIAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.PRE_RETIRADA, Boolean.TRUE, Filtro.EQUAL)));

            //ORDENAÇÃO
            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(RmMaterialRetirada.RM_MATERIAL + "." + RmMaterial.RM_MATERIAL_ID, NxOrder.NX_ORDER.ASC));
            //Obtem elementos.
            lista = genericDaoRetirada.listarByFilter(propriedades, nxOrders, RmMaterialRetirada.class, -1, nxCriterion);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Método que que traz todos os dados referentes a tabela RM Material Faz
     * varias consultas no banco,primeiro tras a rm material, depois trás uma
     * lista de rm material recebimento e tambem uma lista de rm material
     * retirada
     * <br>
     * <br>
     * <b>Autor:</b> Jerry.
     * <b>Data:</b> 29/03/2011
     * <br>
     * <br>
     *
     * @param numeroRm
     * @param dataInicio
     * @param datafim
     * @param comprador
     * @param material
     * @param requisitante
     * @param cadastrante
     * @return lista
     * @throws Exception - Exception
     */
    public List<RmMaterialRecebimentoRetirada> listagemGeralRmMaterial(String numeroRm, Date dataInicio, Date datafim, Comprador comprador,
            Material material, Pessoa requisitante, Pessoa cadastrante) throws Exception {

        List<RmMaterialRecebimentoRetirada> lista = new ArrayList<>();
        RmMaterialRecebimentoRetirada rmMaterialRecRet;
        List<RmMaterial> listaRmMat = new ArrayList<>();
        br.com.nextage.persistence_2.dao.GenericDao<RmMaterial> genericDao = new br.com.nextage.persistence_2.dao.GenericDao<>();

        try {
            String aliasRm = br.com.nextage.persistence_2.classes.NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);
            String aliasFornecedor = br.com.nextage.persistence_2.classes.NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.FORNECEDOR_ID);
            String aliasMaterial = br.com.nextage.persistence_2.classes.NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL);
            String aliasPrioridade = br.com.nextage.persistence_2.classes.NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.PRIORIDADE);
            String aliasRequisitante = br.com.nextage.persistence_2.classes.NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.REQUISITANTE);
            String aliasUnidadeMedida = br.com.nextage.persistence_2.classes.NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL, Material.UNIDADE_MEDIDA);
            String aliasTipoMaterial = br.com.nextage.persistence_2.classes.NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL, Material.TIPO_MATERIAL);
            String aliasUsuario = br.com.nextage.persistence_2.classes.NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.USUARIO);
            String aliasUsuarioPessoa = br.com.nextage.persistence_2.classes.NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.USUARIO, Usuario.PESSOA);
            String aliasComprador = br.com.nextage.persistence_2.classes.NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.COMPRADOR);

            // Seta as propriedades de retorno da consulta RM MATERIAL  .
            List<br.com.nextage.persistence_2.classes.Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterial.RM_MATERIAL_ID));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterial.QUANTIDADE));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterial.DATA_COMPRA));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterial.DATA_PREVISAO_CHEGADA));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterial.OBSERVACAO));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterial.ORDEM));

            //RM
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.DATA_EMISSAO, Rm.class, aliasRm));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.DATA_RECEBIMENTO, Rm.class, aliasRm));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.DATA_APLICACAO, Rm.class, aliasRm));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.OBSERVACAO, Rm.class, aliasRm));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.DATA_CANCELAMENTO, Rm.class, aliasRm));

            //Prioridade
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Prioridade.PRIORIDADE_ID, Prioridade.class, aliasPrioridade));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Prioridade.DESCRICAO, Prioridade.class, aliasPrioridade));

            //requisitante
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRequisitante));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Pessoa.NOME, Pessoa.class, aliasRequisitante));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasRequisitante));

            //Comprador
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Comprador.COMPRADOR_ID, Comprador.class, aliasComprador));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Comprador.NOME, Comprador.class, aliasComprador));

            //Unidade medida
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            //Tipo material
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(TipoMaterial.DESCRICAO, TipoMaterial.class, aliasTipoMaterial));

            //FORNECEDOR
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Fornecedor.FORNECEDOR_ID, Fornecedor.class, aliasFornecedor));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Fornecedor.NOME, Fornecedor.class, aliasFornecedor));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Fornecedor.CODIGO, Fornecedor.class, aliasFornecedor));

            //MATERIAL
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.NOME, Material.class, aliasMaterial));

            //Usuario
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Usuario.USUARIO_ID, Usuario.class, aliasUsuario));

            //Usuario pessoa
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasUsuarioPessoa));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Pessoa.NOME, Pessoa.class, aliasUsuarioPessoa));

            br.com.nextage.persistence_2.classes.NxCriterion nxCriterion = null;
            br.com.nextage.persistence_2.classes.NxCriterion nxCriterionAux = null;

            if (numeroRm != null && !numeroRm.isEmpty()) {
                nxCriterion = br.com.nextage.persistence_2.classes.NxCriterion.montaRestriction(
                        new br.com.nextage.persistence_2.classes.Filtro(Rm.NUMERO_RM, numeroRm,
                                br.com.nextage.persistence_2.classes.Filtro.LIKE, aliasRm));
                if (nxCriterion != null) {
                    nxCriterion = br.com.nextage.persistence_2.classes.NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterionAux = nxCriterion;
                }
            }
            if (dataInicio != null && datafim != null) {
                nxCriterion = br.com.nextage.persistence_2.classes.NxCriterion.montaRestriction(
                        new br.com.nextage.persistence_2.classes.Filtro(Rm.DATA_RECEBIMENTO, dataInicio,
                                datafim, true, br.com.nextage.persistence_2.classes.Filtro.BETWEEN, aliasRm));
                if (nxCriterion != null) {
                    nxCriterion = br.com.nextage.persistence_2.classes.NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterionAux = nxCriterion;
                }
            }
            if (comprador != null) {
                nxCriterion = br.com.nextage.persistence_2.classes.NxCriterion.montaRestriction(
                        new br.com.nextage.persistence_2.classes.Filtro(Rm.COMPRADOR, comprador,
                                br.com.nextage.persistence_2.classes.Filtro.EQUAL, aliasRm));
                if (nxCriterion != null) {
                    nxCriterion = br.com.nextage.persistence_2.classes.NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterionAux = nxCriterion;
                }
            }
            if (material != null) {
                nxCriterion = br.com.nextage.persistence_2.classes.NxCriterion.montaRestriction(
                        new br.com.nextage.persistence_2.classes.Filtro(RmMaterial.MATERIAL, material,
                                br.com.nextage.persistence_2.classes.Filtro.EQUAL));
                if (nxCriterion != null) {
                    nxCriterion = br.com.nextage.persistence_2.classes.NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterionAux = nxCriterion;
                }
            }
            if (requisitante != null) {
                nxCriterion = br.com.nextage.persistence_2.classes.NxCriterion.montaRestriction(
                        new br.com.nextage.persistence_2.classes.Filtro(Rm.REQUISITANTE, requisitante,
                                br.com.nextage.persistence_2.classes.Filtro.EQUAL, aliasRm));
                if (nxCriterion != null) {
                    nxCriterion = br.com.nextage.persistence_2.classes.NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterionAux = nxCriterion;
                }
            }
            if (cadastrante != null) {
                nxCriterion = br.com.nextage.persistence_2.classes.NxCriterion.montaRestriction(
                        new br.com.nextage.persistence_2.classes.Filtro(Usuario.PESSOA, cadastrante,
                                br.com.nextage.persistence_2.classes.Filtro.EQUAL, aliasUsuario));
                if (nxCriterion != null) {
                    nxCriterion = br.com.nextage.persistence_2.classes.NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterionAux = nxCriterion;
                }
            }

            //Obtem elementos.
            List<br.com.nextage.persistence_2.classes.NxOrder> orders = Arrays.asList(new br.com.nextage.persistence_2.classes.NxOrder(Rm.ALIAS_CLASSE + "." + Rm.NUMERO_RM, br.com.nextage.persistence_2.classes.NxOrder.NX_ORDER.ASC));
            listaRmMat = genericDao.listarByFilter(propriedades, orders, RmMaterial.class, Constantes.NO_LIMIT, nxCriterion);

            RmService rmService = new RmService();
            listaRmMat = rmService.setaStatusCompraListaRm(listaRmMat);

            for (RmMaterial rmMaterial : listaRmMat) {
                rmMaterialRecRet = new RmMaterialRecebimentoRetirada();
                //chama o metodo para carregar os dados da rmMaterial
                rmMaterialRecRet.setRmMaterial(rmMaterial);
                lista.add(rmMaterialRecRet);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * <pre>
     * Retorna a lista de Materiais Recebidos
     *
     * <b>Author</b> : Jerry Adriano
     * <b>Data</b>   : 31/05/2012
     * <b>Param</b>  : Integer rmMaterialId
     * <b>Return</b> : List<RmMaterialRecebimento>
     * </pre>
     *
     * @param rmMaterialId
     * @return
     * @throws java.lang.Exception
     */
    public List<RmMaterialRecebimento> carregaListaRecebimentos(Integer rmMaterialId) throws Exception {
        List<RmMaterialRecebimento> lista;
        try {
            String aliasRmMaterial = NxCriterion.montaAlias(RmMaterialRecebimento.ALIAS_CLASSE, RmMaterialRecebimento.RM_MATERIAL);

            NxCriterion nxCriterion;
            nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM_MATERIAL_ID, rmMaterialId, Filtro.EQUAL, RmMaterialRecebimento.RM_MATERIAL));

            //CONSULTA RM MATERIAL RECEBIMENTO
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialRecebimento.RM_MATERIAL_RECEBIMENTO_ID));
            propriedades.add(new Propriedade(RmMaterialRecebimento.QUANTIDADE));
            propriedades.add(new Propriedade(RmMaterialRecebimento.NUMERO_NOTA_FISCAL));
            propriedades.add(new Propriedade(RmMaterialRecebimento.DATA_EMISSAO_NF));
            propriedades.add(new Propriedade(RmMaterialRecebimento.DATA_RECEBIMENTO_MATERIAL));

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));

            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(RmMaterialRecebimento.DATA_RECEBIMENTO_MATERIAL, NxOrder.NX_ORDER.ASC));

            //FAZ A CONSULTA DE RM MATERIAL RECEBIMENTO E SALVA NO OBJETO
            lista = genericDaoRecebimento.listarByFilter(propriedades, nxOrders, RmMaterialRecebimento.class, -1, nxCriterion);

        } catch (Exception e) {
            lista = null;
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * <pre>
     * Retorna a lista de Materiais Retirados
     *
     * <b>Author</b> : Jerry Adriano
     * <b>Data</b>   : 31/05/2012
     * <b>Param</b>  : Integer rmMaterialId
     * <b>Return</b> : List<RmMaterialRetirada>
     * </pre>
     *
     * @param rmMaterialId
     * @return
     * @throws java.lang.Exception
     */
    public List<RmMaterialRetirada> carregaListaRetiradas(Integer rmMaterialId) throws Exception {
        List<RmMaterialRetirada> lista = null;
        try {
            String aliasRmMaterial = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RM_MATERIAL);
            String aliasRetiradoPor = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RETIRADO_POR);

            NxCriterion nxCriterion;
            nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM_MATERIAL_ID, rmMaterialId, Filtro.EQUAL, RmMaterialRetirada.RM_MATERIAL));

            //CONSULTA RM MATERIAL RECEBIMENTO
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialRetirada.RM_MATERIAL_RETIRADA_ID));
            propriedades.add(new Propriedade(RmMaterialRetirada.QUANTIDADE));
            propriedades.add(new Propriedade(RmMaterialRetirada.DATA_RETIRADA));
            propriedades.add(new Propriedade(RmMaterialRetirada.RM_MATERIAL));
            propriedades.add(new Propriedade(RmMaterialRetirada.NUMERO_PROTOCOLO));

            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRetiradoPor));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRetiradoPor));

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));

            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(RmMaterialRetirada.DATA_RETIRADA, NxOrder.NX_ORDER.ASC));

            //FAZ A CONSULTA DE RM MATERIAL RECEBIMENTO E SALVA NO OBJETO
            lista = genericDaoRetirada.listarByFilter(propriedades, nxOrders, RmMaterialRetirada.class, -1, nxCriterion);

        } catch (Exception e) {
            lista = null;
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Método que retorna se aquela rm ja foi finalizadal.
     * <br>
     * <br>
     * <b>Autor:</b> Jerry.
     * <b>Data:</b> 11/04/2011
     * <br>
     * <br>
     *
     * @param numeroRm
     * @return lista - List<RmMaterial>
     * @throws Exception - Exception
     */
    public Boolean rmFinalizada(String numeroRm) throws Exception {

        List<RmMaterial> auxLista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            filtros.add(new Filtro(Rm.NUMERO_RM, numeroRm, Filtro.EQUAL, Rm.ALIAS_CLASSE));
            filtros.add(new Filtro(RmMaterial.DATA_COMPRA, null, Filtro.IS_NULL));

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
            propriedades.add(new Propriedade(RmMaterial.DATA_COMPRA));

            //RM
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));

            //Obtem elementos.
            String orderBy = Rm.ALIAS_CLASSE + "." + Rm.NUMERO_RM;
            auxLista = (ArrayList<RmMaterial>) genericDao.listarByFilter(propriedades, filtros, orderBy, false, RmMaterial.class, 1);

            if (auxLista != null && auxLista.size() > 0) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return true;
    }

    /**
     * Retorna uma lista de RmMaterial filtrado por Rm.
     *
     * @param numeroRm
     * @return List<RmMaterial>
     * @throws Exception
     */
    public List listaRmMaterialByNumeroRm(String numeroRm) throws Exception {
        List<RmMaterial> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            if (numeroRm != null) {
                filtros.add(new Filtro(Rm.NUMERO_RM, numeroRm, Filtro.EQUAL, Rm.ALIAS_CLASSE));
            }

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));
            propriedades.add(new Propriedade(RmMaterial.DATA_COMPRA));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA));

            //RM
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Rm.COMPRADOR, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));

            //MATERIAL
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, Material.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Material.NOME, Material.class, Material.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Material.UNIDADE_MEDIDA, Material.class, Material.ALIAS_CLASSE_UNDERLINE));

            //FORNECEDOR
            propriedades.add(new Propriedade(Fornecedor.FORNECEDOR_ID, Fornecedor.class, Fornecedor.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Fornecedor.NOME, Fornecedor.class, Fornecedor.ALIAS_CLASSE_UNDERLINE));

            //Obtem elementos.
            String orderBy = Rm.ALIAS_CLASSE + "." + Rm.NUMERO_RM;
            lista = (ArrayList<RmMaterial>) genericDao.listarByFilter(propriedades, filtros, orderBy, false, RmMaterial.class, -1);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Retorna uma lista de RmMaterial filtrado por Rm.
     *
     * @param numeroRm
     * @param fluxoMaterial
     * @return List<RmMaterial>
     * @throws Exception\\
     */
    public List listaRmMaterialByNumeroRmMaterial(String numeroRm, String fluxoMaterial) throws Exception {
        List<RmMaterial> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            if (numeroRm != null) {
                filtros.add(new Filtro(Rm.NUMERO_RM, numeroRm, Filtro.LIKE, Rm.ALIAS_CLASSE));
            }
            if (fluxoMaterial != null && !fluxoMaterial.isEmpty()) {
                filtros.add(new Filtro(RmMaterial.FLUXO_MATERIAL, fluxoMaterial, Filtro.EQUAL));
            }
            if (ConfiguracaoSingleton.getConfiguracao().getFiltroPainelSupriAprovada() == true) {
                filtros.add(new Filtro(Rm.DATA_APROVACAO_COMPRA, null, Filtro.NOT_NULL, Rm.ALIAS_CLASSE));
            }

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));
            propriedades.add(new Propriedade(RmMaterial.DATA_COMPRA));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA));
            propriedades.add(new Propriedade(RmMaterial.FLUXO_MATERIAL));

            //RM
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Rm.COMPRADOR, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));

            //MATERIAL
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, Material.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Material.NOME, Material.class, Material.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Material.UNIDADE_MEDIDA, Material.class, Material.ALIAS_CLASSE_UNDERLINE));

            //FORNECEDOR
            propriedades.add(new Propriedade(Fornecedor.FORNECEDOR_ID, Fornecedor.class, Fornecedor.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Fornecedor.NOME, Fornecedor.class, Fornecedor.ALIAS_CLASSE_UNDERLINE));

            //Obtem elementos.
            String orderBy = Rm.ALIAS_CLASSE + "." + Rm.NUMERO_RM;
            lista = (ArrayList<RmMaterial>) genericDao.listarByFilter(propriedades, filtros, orderBy, false, RmMaterial.class, -1);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Método que retorna todos os registros por material para fazer a
     * agrupamento de materiais.
     * <br>
     * <br>
     * <b>Autor:</b> Jerry.
     * <b>Data:</b> 20/05/2011
     * <br>
     * <br>
     *
     * @param material
     * @return lista - List<RmMaterial>
     * @throws Exception - Exception
     */
    public List<RmMaterial> selectRmMaterialAgrupamento(Material material) throws Exception {
        List<RmMaterial> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();
            List<Propriedade> propriedades = new ArrayList<>();

            filtros.add(new Filtro(RmMaterial.MATERIAL, material, Filtro.EQUAL));

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
            propriedades.add(new Propriedade(RmMaterial.MATERIAL));

            lista = (ArrayList<RmMaterial>) genericDao.listarByFilter(propriedades, filtros, null, false, RmMaterial.class, -1);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Update para o campo MaterialID ,metodo utilizado para fazer o agrupamento
     * de materiais.
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
    public String salvarRmMaterialAgrupamento(RmMaterial obj) throws Exception {
        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.MATERIAL, RmMaterial.class, RmMaterial.ALIAS_CLASSE_UNDERLINE));
            genericDao.update(obj, propriedades);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return "";
    }

    /**
     * Lista os Materiais Recebidos filtrado por RmMaterial
     *
     * <p/>
     * <b>Autor:</b> Jerry
     * <b>Data:</b> 12-12-2011
     * <p/>
     *
     * @param rmMaterial
     * @return
     * @throws java.lang.Exception
     */
    public List<RmMaterialRecebimento> listaRmMaterialRecebimento(RmMaterial rmMaterial) throws Exception {
        List<RmMaterialRecebimento> lista = new ArrayList<>();
        try {
            List<Propriedade> propriedades;
            //Propriedades.
            propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialRecebimento.RM_MATERIAL_RECEBIMENTO_ID));
            propriedades.add(new Propriedade(RmMaterialRecebimento.QUANTIDADE));
            propriedades.add(new Propriedade(RmMaterialRecebimento.DATA_EMISSAO_NF));
            propriedades.add(new Propriedade(RmMaterialRecebimento.DATA_RECEBIMENTO_MATERIAL));
            propriedades.add(new Propriedade(RmMaterialRecebimento.NUMERO_NOTA_FISCAL));

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, NxCriterion.montaAlias(RmMaterialRecebimento.ALIAS_CLASSE, RmMaterialRecebimento.RM_MATERIAL)));

            NxCriterion nxCriterion;
            nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM_MATERIAL_ID, rmMaterial.getRmMaterialId(), Filtro.EQUAL, RmMaterial.ALIAS_CLASSE));

            //ordena por data entrada decrecente
            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(RmMaterialRecebimento.DATA_RECEBIMENTO_MATERIAL, NxOrder.NX_ORDER.ASC));

            lista = genericDaoRecebimento.listarByFilter(propriedades, nxOrders, RmMaterialRecebimento.class, Constantes.NO_LIMIT, nxCriterion);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    /**
     * Lista os Materiais Retirados filtrado por RmMaterial
     *
     * <p/>
     * <b>Autor:</b> Jerry
     * <b>Data:</b> 12-12-2011
     * <p/>
     *
     * @param rmMaterial
     * @return
     * @throws java.lang.Exception
     */
    public List<RmMaterialRetirada> listaRmMaterialRetirada(RmMaterial rmMaterial) throws Exception {
        List<RmMaterialRetirada> lista = new ArrayList<>();
        try {
            List<Propriedade> propriedades;
            //Propriedades.
            propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialRetirada.RM_MATERIAL_RETIRADA_ID));
            propriedades.add(new Propriedade(RmMaterialRetirada.DATA_RETIRADA));
            propriedades.add(new Propriedade(RmMaterialRetirada.NUMERO_PROTOCOLO));
            propriedades.add(new Propriedade(RmMaterialRetirada.QUANTIDADE));

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RM_MATERIAL)));

            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RETIRADO_POR)));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RETIRADO_POR)));

            NxCriterion nxCriterion;
            nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM_MATERIAL_ID, rmMaterial.getRmMaterialId(), Filtro.EQUAL, RmMaterial.ALIAS_CLASSE));

            //ordena por data entrada decrecente
            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(RmMaterialRetirada.DATA_RETIRADA, NxOrder.NX_ORDER.ASC));

            lista = genericDaoRetirada.listarByFilter(propriedades, nxOrders, RmMaterialRetirada.class, Constantes.NO_LIMIT, nxCriterion);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    /**
     *
     * @param rmMaterial
     * @return
     * @throws Exception
     */
    public String salvarRmMaterialAlterada(RmMaterial rmMaterial) throws Exception {
        try {
            List<Propriedade> propriedades;

            //Propriedades.
            propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Rm.COMPRADOR));
            GenericDao<Rm> daoRm = new GenericDao<>();
            daoRm.update(rmMaterial.getRm(), propriedades);

            //Propriedades.
            propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.DATA_COMPRA));
            propriedades.add(new Propriedade(RmMaterial.FORNECEDOR_ID));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA));

            GenericDao<RmMaterial> daoRmMaterial = new GenericDao<>();
            daoRmMaterial.update(rmMaterial, propriedades);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return "Sucesso";
    }

    /**
     * Salva as alterações na lista de RmMaterialRecebimento
     *
     * @param lista
     * @return
     * @throws Exception
     */
    public String salvarRmMaterialRecebimento(List<RmMaterialRecebimento> lista) throws Exception {
        try {
            List<Propriedade> propriedades;

            //Propriedades.
            propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialRecebimento.DATA_EMISSAO_NF));
            propriedades.add(new Propriedade(RmMaterialRecebimento.DATA_RECEBIMENTO_MATERIAL));
            GenericDao<RmMaterialRecebimento> dao = new GenericDao<>();
            for (RmMaterialRecebimento obj : lista) {
                dao.update(obj, propriedades);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return "Sucesso";
    }

    /**
     * Salva as alterações na lista de RmMaterialRetirada
     *
     * @param lista
     * @return
     * @throws Exception
     */
    public String salvarRmMaterialRetirada(List<RmMaterialRetirada> lista) throws Exception {
        try {
            List<Propriedade> propriedades;

            //Propriedades.
            propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialRetirada.DATA_RETIRADA));
            GenericDao<RmMaterialRetirada> dao = new GenericDao<>();
            for (RmMaterialRetirada obj : lista) {
                dao.update(obj, propriedades);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return "Sucesso";
    }

    /**
     * <pre>
     * Metodo utilizado para setar as quantidades de materiais
     * que foram recebidos e retirados.
     * Retorna a mesma lista,mas com as quantidades preenchidas.
     *
     * <b>Author</b> : Jerry Adriano
     * <b>Data</b>   : 30/05/2012
     * <b>Param</b>  : List<RmMaterial> lista
     * <b>Param</b>  : Filtro(Rm rm)
     * <b>Return</b> : List<RmMaterial> lista
     * </pre>
     *
     * @param lista
     * @param rm
     * @return
     * @throws java.lang.Exception
     */
    public List<RmMaterial> setaQtdeRetiradaRecebimentoForRmMaterial(List<RmMaterial> lista, Rm rm) throws Exception {
        try {
            /**
             * CONTA RETIRADAS E RECEBIMENTOS DE MATERIAL MATERIAL
             */
            List<RmMaterialRetirada> listaRet;
            List<RmMaterialRecebimento> listaRec;

            //TRAZ AS LISTAS ORDENADAS PELO ID DA RmMaterial
            listaRet = listaRmMaterialRetiradaQtde(null, rm);
            listaRec = listaRmMaterialRecebimentoQtde(null, rm);

            //Verifica se a lista esta null para melhorar o controle das listas
            if (listaRec == null) {
                listaRec = new ArrayList<>();
            }
            if (listaRet == null) {
                listaRet = new ArrayList<>();
            }
            boolean achou = false;
            boolean sair = false;
            int contRet = 0;
            int contRec = 0;
            RmMaterialRetirada rmRet = null;
            RmMaterialRecebimento rmRec = null;
            if (listaRet.size() > 0 && listaRec.size() > 0) {
                //PERCORRE A LISTA DE MATERIAL
                for (RmMaterial rmM : lista) {
                    // RETIRADA *******************************************
                    contRet = 0;
                    achou = false;
                    sair = false;
                    // EXECUTA ENQUANTO NÃO ACHOU RETIRADA DO MATERIAL OU SE JÁ ACHOU TODOS DA RmMaterial..
                    while (sair == false && listaRet.size() > contRet) {
                        rmRet = listaRet.get(contRet);
                        // A lista de Recebimento esta ordenda pelo id da RmMaterial
                        if (rmM.equals(rmRet.getRmMaterial())) {
                            rmM.setQtdRetirado(rmM.getQtdRetirado() + rmRet.getQuantidade());
                            achou = true;

                            //Se ja achou e não tem mais recebimento para a mesma RmMaterial então sai do while
                        } else if (achou) {
                            sair = true;
                        }
                        contRet++;
                    }

                    // RECEBIMENTO ****************************************
                    achou = false;
                    contRec = 0;
                    sair = false;
                    //EXECUTA ENQUANTO NÃO ACHOU RETIRADA DE MATERIAL OU SE JÁ ACHOU TODOS DA RmMaterial.
                    while (sair == false && listaRec.size() > contRec) {
                        rmRec = listaRec.get(contRec);
                        // A lista de Recebimento esta ordenda pelo id da RmMaterial
                        if (rmM.equals(rmRec.getRmMaterial())) {
                            rmM.setQtdRecebido(rmM.getQtdRecebido() + rmRec.getQuantidade());
                            achou = true;

                            //Se ja achou e não tem mais recebimento para a mesma RmMaterial então sai do while
                        } else if (achou) {
                            sair = true;
                        }
                        contRec++;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Recupera a lista de RmMaterialRetirada para ser utilizada na contagem de
     * materias Retirados.
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 30/05/2012
     * <br>
     *
     * @param rmM
     * @param rm
     * @return idObjeto - String
     * @throws Exception - Exception
     */
    public List listaRmMaterialRetiradaQtde(RmMaterial rmM, Rm rm) throws Exception {
        List<RmMaterialRetirada> lista = new ArrayList<>();
        try {
            String aliasRmMaterial = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RM_MATERIAL);
            String aliasRm = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RM_MATERIAL, RmMaterial.RM);

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialRetirada.RM_MATERIAL_RETIRADA_ID));
            propriedades.add(new Propriedade(RmMaterialRetirada.QUANTIDADE));

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));

            NxCriterion nxCriterion = null;
            NxCriterion nxCriterionAux = null;
            if (rmM != null && rmM.getRmMaterialId() > 0) {
                nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM_MATERIAL_ID, rmM.getRmMaterialId(), Filtro.EQUAL, RmMaterialRetirada.RM_MATERIAL));
            }
            if (rm != null && rm.getRmId() > 0) {
                propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Rm.RM_ID, rm.getRmId(), Filtro.EQUAL, RmMaterial.RM));
                if (nxCriterion == null) {
                    nxCriterion = nxCriterionAux;
                } else {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                }
            }

            //ORDENAÇÃO
            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(RmMaterialRetirada.RM_MATERIAL + "." + RmMaterial.RM_MATERIAL_ID, NxOrder.NX_ORDER.ASC));
            //Obtem elementos.
            lista = genericDaoRetirada.listarByFilter(propriedades, nxOrders, RmMaterialRetirada.class, -1, nxCriterion);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Recupera a lista de RmMaterialRecebimento para ser utilizada na contagem
     * de materias Recebidos.
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 30/05/2012
     * <br>
     *
     * @param rmM
     * @param rm
     * @return idObjeto - String
     * @throws Exception - Exception
     */
    public List listaRmMaterialRecebimentoQtde(RmMaterial rmM, Rm rm) throws Exception {
        List<RmMaterialRecebimento> lista = new ArrayList<>();
        try {
            String aliasRmMaterial = NxCriterion.montaAlias(RmMaterialRecebimento.ALIAS_CLASSE, RmMaterialRecebimento.RM_MATERIAL);
            String aliasRm = NxCriterion.montaAlias(RmMaterialRecebimento.ALIAS_CLASSE, RmMaterialRecebimento.RM_MATERIAL, RmMaterial.RM);

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialRecebimento.RM_MATERIAL_RECEBIMENTO_ID));
            propriedades.add(new Propriedade(RmMaterialRecebimento.QUANTIDADE));

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));

            NxCriterion nxCriterion = null;
            NxCriterion nxCriterionAux = null;
            if (rmM != null && rmM.getRmMaterialId() > 0) {
                nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterialRecebimento.RM_MATERIAL, rmM, Filtro.EQUAL));
            }
            if (rm != null && rm.getRmId() > 0) {
                propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Rm.RM_ID, rm.getRmId(), Filtro.EQUAL, RmMaterial.RM));
                if (nxCriterion == null) {
                    nxCriterion = nxCriterionAux;
                } else {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                }
            }

            //ORDENAÇÃO
            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(RmMaterialRecebimento.RM_MATERIAL + "." + RmMaterial.RM_MATERIAL_ID, NxOrder.NX_ORDER.ASC));
            //Obtem elementos.
            lista = genericDaoRecebimento.listarByFilter(propriedades, nxOrders, RmMaterialRecebimento.class, -1, nxCriterion);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public List<AlmoxarifadoVo> listaMateriaisAlmoxarife() throws Exception {
        List<AlmoxarifadoVo> lista = new ArrayList<>();
        try {
        	String sql = "select ";
        	sql += "d.NOME, ";
        	sql += "b.NUMERO_RM, ";
        	sql += "a.NUMERO_REQUISICAO_SAP, ";
        	sql += "e.NOME, ";
        	sql += "f.CODIGO, ";
        	sql += "a.QUANTIDADE, ";
        	sql += "b.DATA_EMISSAO, ";
        	sql += "b.DATA_APLICACAO, ";
        	sql += "g.NOME, ";
        	sql += "h.NOME, ";
        	sql += "a.FLUXO_MATERIAL, ";
        	sql += "a.COLETOR_CUSTOS, ";
        	sql += "b.DATA_EMISSAO, ";
        	sql += "a.STATUS, ";
        	sql += "a.STATUS, ";
        	sql += "i.DESCRICAO, ";
        	sql += "b.OBSERVACAO ";
        	sql += "from TB_RM_MATERIAL a ";
        	sql += "inner join TB_RM b on a.RM_ID=b.RM_ID ";
        	sql += "inner join TB_RM_APROVADOR c on c.RM_ID = b.RM_ID ";
        	sql += "inner join TB_CENTRO d on d.CENTRO_ID=b.CENTRO_ID ";
        	sql += "inner join TB_MATERIAL e on e.MATERIAL_ID = a.MATERIAL_ID ";
        	sql += "inner join TB_TIPO_MATERIAL f on f.TIPO_MATERIAL_ID=e.TIPO_MATERIAL_ID ";
        	sql += "inner join TB_USUARIO g on g.USUARIO_ID = b.REQUISITANTE_ID ";
        	sql += "inner join TB_USUARIO h on h.USUARIO_ID=b.USUARIO_ID ";
        	sql += "inner join TB_TIPO_REQUISICAO i on b.TIPO_REQUISICAO_ID=i.TIPO_REQUISICAO_ID ";
        	sql += "where c.APROVADA = 1 ";
        	
        	
        	lista = rmMaterialDao.getList(sql);
        	
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }
}
