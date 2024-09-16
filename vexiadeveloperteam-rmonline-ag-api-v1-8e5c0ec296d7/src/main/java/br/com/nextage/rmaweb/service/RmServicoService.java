/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.classes.NxCriterion;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.rmaweb.dao.RmDao;
import br.com.nextage.rmaweb.dao.WorkflowAreaDAO;
import br.com.nextage.rmaweb.dao.WorkflowCustoDAO;
import br.com.nextage.rmaweb.dao.WorkflowDAO;
import br.com.nextage.rmaweb.dao.WorkflowEmergencialDAO;
import br.com.nextage.rmaweb.dao.WorkflowGerenteAreaDAO;
import br.com.nextage.rmaweb.entitybean.Centro;
import br.com.nextage.rmaweb.entitybean.Comprador;
import br.com.nextage.rmaweb.entitybean.Deposito;
import br.com.nextage.rmaweb.entitybean.EapMultiEmpresa;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.entitybean.MultiEmpresa;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.Prioridade;
import br.com.nextage.rmaweb.entitybean.Rastreabilidade;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.entitybean.RmServico;
import br.com.nextage.rmaweb.entitybean.RmServicoCodigoSap;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.entitybean.Workflow;
import br.com.nextage.rmaweb.service.integracao.RastreabilidadeService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.utils.ConnectionFactory;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;

/**
 * @author Henrique
 */
public class RmServicoService {

    private GenericDao<Rm> genericDao;
    private RmDao rmDao;
    private GenericDao<RmServico> genericDaoServico;
    private GenericDao<RmMaterial> genericDaoRmMaterial;
    private RastreabilidadeService rastreabilidadeService = RastreabilidadeService.getInstance();
    private WorkflowDAO workflowDAO = WorkflowDAO.getInstance();
    private WorkflowAreaDAO workflowAreaDAO = WorkflowAreaDAO.getInstance();
    private WorkflowGerenteAreaDAO workflowGerenteAreaDAO = WorkflowGerenteAreaDAO.getInstance();
    private WorkflowCustoDAO workflowCustoDAO = WorkflowCustoDAO.getInstance();
    private WorkflowEmergencialDAO workflowEmergencialDAO = WorkflowEmergencialDAO.getInstance();

    public RmServicoService() {
        genericDaoRmMaterial = new GenericDao<>();
        genericDaoServico = new GenericDao<>();
        genericDao = new GenericDao<>();
        rmDao = new RmDao();
    }
    
    public boolean excluir(Rm rm) throws Exception {
        Boolean result = false;
        try {
            result = genericDao.delete(rm);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return result;
    }

    /**
     * Lista com todas as RMs que satisfazem o filtro
     *
     * @param numero
     * @param qtdRetorno
     * @return
     * @throws Exception
     */
    public List listaDataGrid(String numero, int qtdRetorno) throws Exception {
        List<Rm> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            if (numero != null) {
                filtros.add(new Filtro(Rm.NUMERO_RM, numero, Filtro.LIKE));
            }

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Rm.RM_ID));
            propriedades.add(new Propriedade(Rm.NUMERO_RM));
            propriedades.add(new Propriedade(Rm.OBSERVACAO));
            propriedades.add(new Propriedade(Rm.DATA_EMISSAO));
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO));
            propriedades.add(new Propriedade(Rm.DATA_APROVACAO_COMPRA));
            propriedades.add(new Propriedade(Rm.DATA_RECEBIMENTO));
            propriedades.add(new Propriedade(Rm.DATA_CANCELAMENTO));
            propriedades.add(new Propriedade(Rm.DATA_ACEITE_COMPRADOR));
            propriedades.add(new Propriedade(Rm.DATA_ENVIO_COMPRADOR));
            propriedades.add(new Propriedade(Rm.JUSTIFICATIVA_CANCELAMENTO));

            propriedades.add(new Propriedade(Comprador.COMPRADOR_ID, Comprador.class, Comprador.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Comprador.NOME, Comprador.class, Comprador.ALIAS_CLASSE_UNDERLINE));

            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, Rm.REQUISITANTE));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, Rm.REQUISITANTE));

            //Obtem elementos.
            lista = (ArrayList<Rm>) genericDao.listarByFilter(propriedades, filtros, Rm.NUMERO_RM, false, Rm.class, qtdRetorno);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    public List listaRmNaoAprovadas(Integer compradorId) throws Exception {
        List<Rm> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();
            filtros.add(new Filtro(Rm.COMPRADOR, compradorId, Filtro.LIKE));

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Rm.RM_ID));
            propriedades.add(new Propriedade(Rm.NUMERO_RM));
            propriedades.add(new Propriedade(Rm.OBSERVACAO));
            propriedades.add(new Propriedade(Rm.DATA_EMISSAO));
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO));
            propriedades.add(new Propriedade(Rm.DATA_APROVACAO_COMPRA));
            propriedades.add(new Propriedade(Rm.DATA_RECEBIMENTO));
            propriedades.add(new Propriedade(Rm.DATA_CANCELAMENTO));
            propriedades.add(new Propriedade(Rm.DATA_ACEITE_COMPRADOR));
            propriedades.add(new Propriedade(Rm.DATA_ENVIO_COMPRADOR));
            propriedades.add(new Propriedade(Rm.JUSTIFICATIVA_CANCELAMENTO));

            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, Rm.REQUISITANTE));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, Rm.REQUISITANTE));

            //Obtem elementos.
            lista = (ArrayList<Rm>) genericDao.listarByFilter(propriedades, filtros, Rm.NUMERO_RM, false, Rm.class, -1);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    public List listaRmSemComprador() throws Exception {
        List<Rm> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();
            filtros.add(new Filtro(Rm.DATA_ENVIO_COMPRADOR, null, Filtro.EQUAL));
            filtros.add(new Filtro(Rm.COMPRADOR, null, Filtro.EQUAL));

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Rm.RM_ID));
            propriedades.add(new Propriedade(Rm.NUMERO_RM));
            propriedades.add(new Propriedade(Rm.OBSERVACAO));
            propriedades.add(new Propriedade(Rm.DATA_EMISSAO));
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO));
            propriedades.add(new Propriedade(Rm.DATA_APROVACAO_COMPRA));
            propriedades.add(new Propriedade(Rm.DATA_RECEBIMENTO));
            propriedades.add(new Propriedade(Rm.DATA_CANCELAMENTO));
            propriedades.add(new Propriedade(Rm.DATA_ACEITE_COMPRADOR));
            propriedades.add(new Propriedade(Rm.DATA_ENVIO_COMPRADOR));
            propriedades.add(new Propriedade(Rm.JUSTIFICATIVA_CANCELAMENTO));

            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, Rm.REQUISITANTE));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, Rm.REQUISITANTE));

            //Obtem elementos.
            lista = (ArrayList<Rm>) genericDao.listarByFilter(propriedades, filtros, Rm.NUMERO_RM, false, Rm.class, -1);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Salva a Rm no sistema
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public String salvar(Rm obj, String usuario) throws Exception {
        System.out.println("*** Data Emissão   : " + obj.getDataEmissao());
        System.out.println("*** Data Aplicação : " + obj.getDataAplicacao());
        String idObjeto = "0";
        try {
            if (obj.getRmId() != null && obj.getRmId() > 0) {
                rastreabilidadeService.rastrear(obj, usuario);
                idObjeto = rmDao.update(obj);
            } else {
                idObjeto = rmDao.persist(obj);
                rastreabilidadeService.rastrear(obj, usuario, Rastreabilidade.CRIADO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    /**
     * Adicionado campo prioridade
     *
     * @param rmId
     * @return
     * @throws Exception
     */
    public Rm selectByUnique(Integer rmId) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        List<Propriedade> propriedades = new ArrayList<>();
        Rm obj = new Rm();
        try {
            filtros.add(new Filtro(Rm.RM_ID, rmId, Filtro.EQUAL));

            propriedades.add(new Propriedade(Rm.RM_ID));
            propriedades.add(new Propriedade(Rm.NUMERO_RM));
            propriedades.add(new Propriedade(Rm.DATA_EMISSAO));
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO));
            propriedades.add(new Propriedade(Rm.DATA_APROVACAO_COMPRA));
            propriedades.add(new Propriedade(Rm.DATA_RECEBIMENTO));
            propriedades.add(new Propriedade(Rm.DATA_ACEITE_COMPRADOR));
            propriedades.add(new Propriedade(Rm.DATA_ENVIO_COMPRADOR));
            propriedades.add(new Propriedade(Rm.OBSERVACAO));
            propriedades.add(new Propriedade(Rm.TIPO_REQUISICAO));
            propriedades.add(new Propriedade(Rm.DATA_CANCELAMENTO));
            propriedades.add(new Propriedade(Rm.JUSTIFICATIVA_CANCELAMENTO));

            propriedades.add(new Propriedade(Comprador.COMPRADOR_ID, Comprador.class, Rm.COMPRADOR));

            propriedades.add(new Propriedade(Prioridade.PRIORIDADE_ID, Prioridade.class, Rm.PRIORIDADE));
            propriedades.add(new Propriedade(Prioridade.DESCRICAO, Prioridade.class, Rm.PRIORIDADE));
            propriedades.add(new Propriedade(Prioridade.CODIGO, Prioridade.class, Rm.PRIORIDADE));

            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, Rm.DEPOSITO));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, Rm.DEPOSITO));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, Rm.DEPOSITO));
            propriedades.add(new Propriedade(Centro.CODIGO, Centro.class, Rm.CENTRO));
            propriedades.add(new Propriedade(Centro.CENTRO_ID, Centro.class, Rm.CENTRO));

            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, Rm.REQUISITANTE));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, Rm.REQUISITANTE));

            obj = (Rm) genericDao.selectUnique(filtros, propriedades, Rm.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return obj;
    }

    public List<Rm> getCombo() throws Exception {
        List<Rm> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Rm.RM_ID));
            propriedades.add(new Propriedade(Rm.NUMERO_RM));

            //Obtem elementos.
            lista = genericDao.listarByFilter(propriedades, filtros, Rm.NUMERO_RM, false, Rm.class, Constantes.NO_LIMIT);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    public List<Rm> getComboRmNaoAceita(Integer compradorId) throws Exception {
        List<Rm> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();
            filtros.add(new Filtro(Rm.DATA_ACEITE_COMPRADOR, null, Filtro.EQUAL));
            filtros.add(new Filtro(Comprador.COMPRADOR_ID, compradorId, Filtro.EQUAL));

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Rm.RM_ID));
            propriedades.add(new Propriedade(Rm.NUMERO_RM));

            //Obtem elementos.
            lista = genericDao.listarByFilter(propriedades, filtros, Rm.NUMERO_RM, false, Rm.class, Constantes.NO_LIMIT);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    public List<Rm> getComboRmSemComprador() throws Exception {
        List<Rm> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();
            filtros.add(new Filtro(Rm.DATA_ENVIO_COMPRADOR, null, Filtro.EQUAL));
            filtros.add(new Filtro(Rm.COMPRADOR, null, Filtro.EQUAL));

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Rm.RM_ID));
            propriedades.add(new Propriedade(Rm.NUMERO_RM));

            //Obtem elementos.
            lista = genericDao.listarByFilter(propriedades, filtros, Rm.NUMERO_RM, false, Rm.class, Constantes.NO_LIMIT);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Retorna uma lista de Rm que estão sem comprador.
     *
     * @param numero
     * @param rmAprovadas
     * @param compradores
     * @param qtdRetorno
     * @return List<Rm>
     * @throws Exception
     */
    public List listaIndicacaoComprador(String numero, Boolean compradores, int qtdRetorno, Boolean rmAprovadas) throws Exception {
        List<Rm> lista = new ArrayList<>();
        List<Rm> listaRm = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            filtros.add(new Filtro(Rm.DATA_CANCELAMENTO, null, Filtro.IS_NULL));
            if (numero != null) {
                filtros.add(new Filtro(Rm.NUMERO_RM, numero, Filtro.LIKE));
            }
            if (compradores == false) {
                filtros.add(new Filtro(Rm.COMPRADOR, null, Filtro.IS_NULL));
            }
            if (rmAprovadas == true) {
                filtros.add(new Filtro(Rm.DATA_APROVACAO_COMPRA, null, Filtro.NOT_NULL));
            }
            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Rm.RM_ID));
            propriedades.add(new Propriedade(Rm.NUMERO_RM));
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO));
            propriedades.add(new Propriedade(Rm.DATA_RECEBIMENTO));
            propriedades.add(new Propriedade(Rm.TIPO_REQUISICAO));
            propriedades.add(new Propriedade(Rm.DATA_ACEITE_COMPRADOR));
            propriedades.add(new Propriedade(Rm.OBSERVACAO));
            propriedades.add(new Propriedade(Rm.DATA_APROVACAO_COMPRA));
            propriedades.add(new Propriedade(Rm.DATA_CANCELAMENTO));
            propriedades.add(new Propriedade(Rm.DATA_EMISSAO));
            propriedades.add(new Propriedade(Rm.DATA_ENVIO_COMPRADOR));
            propriedades.add(new Propriedade(Rm.JUSTIFICATIVA_CANCELAMENTO));
            propriedades.add(new Propriedade(Rm.DEPOSITO));

            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, Rm.REQUISITANTE));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, Rm.REQUISITANTE));

            propriedades.add(new Propriedade(Comprador.COMPRADOR_ID, Comprador.class, Comprador.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Comprador.NOME, Comprador.class, Comprador.ALIAS_CLASSE_UNDERLINE));

            //Obtem elementos.
            lista = (ArrayList<Rm>) genericDao.listarByFilter(propriedades, filtros, Rm.NUMERO_RM, false, Rm.class, qtdRetorno);

            RmMaterialService rmMS = new RmMaterialService();
            boolean finalizada;
            for (Rm rm : lista) {
                finalizada = rmMS.rmFinalizada(rm.getNumeroRm());
                if (finalizada == false) {
                    listaRm.add(rm);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return listaRm;
    }

    public String salvarRmComprador(Rm r, String usuario) throws Exception {
        String idObjeto = "0";
        rastreabilidadeService.rastrear(r, usuario);
        try {
            Rm rm = new Rm();
            Date dateEnvComprador = new Date();
            rm = selectByUnique(r.getRmId());
            rm.setComprador(r.getComprador());
            rm.setDataEnvioComprador(dateEnvComprador);
            idObjeto = rmDao.update(rm);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    /**
     * Retorna uma lista de Rm que estão sem comprador.
     *
     * @param compradorId
     * @param qtdRetorno
     * @return List<Rm>
     * @throws Exception
     */
    public List listaRmDoComprador(Integer compradorId, int qtdRetorno) throws Exception {
        List<Rm> lista = new ArrayList<>();
        List<Rm> listaRm = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();
            if (compradorId != null) {
                filtros.add(new Filtro(Rm.COMPRADOR, new Comprador(compradorId), Filtro.EQUAL));
                // Seta as propriedades de retorno da consulta.
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(Rm.RM_ID));
                propriedades.add(new Propriedade(Rm.NUMERO_RM));
                propriedades.add(new Propriedade(Rm.DATA_APLICACAO));
                propriedades.add(new Propriedade(Rm.DATA_RECEBIMENTO));
                propriedades.add(new Propriedade(Rm.TIPO_REQUISICAO));
                propriedades.add(new Propriedade(Rm.DATA_ACEITE_COMPRADOR));
                propriedades.add(new Propriedade(Rm.OBSERVACAO));
                propriedades.add(new Propriedade(Rm.DATA_APROVACAO_COMPRA));
                propriedades.add(new Propriedade(Rm.DATA_CANCELAMENTO));
                propriedades.add(new Propriedade(Rm.DATA_EMISSAO));
                propriedades.add(new Propriedade(Rm.DATA_ENVIO_COMPRADOR));

                propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, Rm.REQUISITANTE));
                propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, Rm.REQUISITANTE));

                //Obtem elementos.
                lista = (ArrayList<Rm>) genericDao.listarByFilter(propriedades, filtros, Rm.NUMERO_RM, false, Rm.class, qtdRetorno);

                //Verrifica se a RM esta Ativa
                if (lista != null && lista.size() > 0) {
                    List<RmMaterial> auxLista = new ArrayList<>();
                    filtros = new ArrayList<>();
                    filtros.add(new Filtro(Rm.COMPRADOR, new Comprador(compradorId), Filtro.EQUAL, Rm.ALIAS_CLASSE));
                    filtros.add(new Filtro(RmMaterial.DATA_COMPRA, null, Filtro.IS_NULL));

                    // Seta as propriedades de retorno da consulta.
                    propriedades = new ArrayList<>();
                    propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
                    propriedades.add(new Propriedade(RmMaterial.DATA_COMPRA));
                    //RM
                    propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));
                    propriedades.add(new Propriedade(Rm.COMPRADOR, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));

                    //Obtem elementos.
                    auxLista = genericDaoRmMaterial.listarByFilter(propriedades, filtros, null, false, RmMaterial.class, -1);

                    //Verifica se todos os itens da rm ja foram comprados
                    RmMaterialService rmMS = new RmMaterialService();
                    boolean achou = false;
                    if (auxLista != null && auxLista.size() > 0) {
                        for (Rm rm : lista) {
                            achou = false;
                            for (RmMaterial rmMaterial : auxLista) {
                                if (rmMaterial.getRm().equals(rm)) {
                                    achou = true;
                                }
                            }
                            //se entrar a Rm está ativa
                            if (achou) {
                                listaRm.add(rm);
                            }
                        }
                    } else {
                        listaRm = lista;
                    }
                } else {
                    listaRm = lista;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return listaRm;
    }

    public String cancelarRmComprador(Rm r, String usuario) throws Exception {
        String idObjeto = "0";
        rastreabilidadeService.rastrear(r, usuario);
        try {
            Rm rm = new Rm();
            rm = selectByUnique(r.getRmId());
            rm.setComprador(null);
            rm.setDataEnvioComprador(null);

            idObjeto = rmDao.update(rm);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    public String aceitarRmComprador(Rm r, String usuario) throws Exception {
        String idObjeto = "0";
        rastreabilidadeService.rastrear(r, usuario);
        try {
            Rm rm = new Rm();
            rm = selectByUnique(r.getRmId());
            Date dateEnvComprador = new Date();
            rm.setDataAceiteComprador(dateEnvComprador);
            idObjeto = rmDao.update(rm);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    /**
     * Retorna uma lista de Rm que estão sem comprador.
     *
     * @param compradorId
     * @param qtdRetorno
     * @return List<Rm>
     * @throws Exception
     */
    public List listaPendenciaRmComprador(Integer compradorId, int qtdRetorno) throws Exception {
        List<Rm> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();
            if (compradorId != null) {
                filtros.add(new Filtro(Rm.COMPRADOR, new Comprador(compradorId), Filtro.EQUAL));
                filtros.add(new Filtro(Rm.DATA_CANCELAMENTO, null, Filtro.IS_NULL));
                // Seta as propriedades de retorno da consulta.
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(Rm.RM_ID));
                propriedades.add(new Propriedade(Rm.NUMERO_RM));
                propriedades.add(new Propriedade(Rm.DATA_APLICACAO));
                propriedades.add(new Propriedade(Rm.DATA_RECEBIMENTO));
                propriedades.add(new Propriedade(Rm.TIPO_REQUISICAO));
                propriedades.add(new Propriedade(Rm.DATA_ACEITE_COMPRADOR));
                propriedades.add(new Propriedade(Rm.OBSERVACAO));
                propriedades.add(new Propriedade(Rm.DATA_APROVACAO_COMPRA));
                propriedades.add(new Propriedade(Rm.DATA_CANCELAMENTO));
                propriedades.add(new Propriedade(Rm.DATA_EMISSAO));
                propriedades.add(new Propriedade(Rm.DATA_ENVIO_COMPRADOR));
                propriedades.add(new Propriedade(Rm.COMPRADOR));

                propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, Rm.REQUISITANTE));
                propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, Rm.REQUISITANTE));

                //Obtem elementos.
                lista = (ArrayList<Rm>) genericDao.listarByFilter(propriedades, filtros, Rm.NUMERO_RM, false, Rm.class, qtdRetorno);
                //seta o status da compra aonde esta a RM
                lista = setaStatusCompraListaRm(lista);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        //Método Remove todas as RMs que tem seu processo de compra finalizado.
        return removeRmByDataCompra(lista);
    }

    /**
     * Realiza o cancelamento de uma RM
     *
     * @param obj
     * @return List<Rm>
     * @throws Exception
     */
    public String cancelarRm(Rm obj, String usuario) throws Exception {
        String idObjeto = "0";
        rastreabilidadeService.rastrear(obj, usuario);
        try {
            if (obj.getRmId() > 0) {
                idObjeto = rmDao.update(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    public List listaItensRmComprador(Integer rmId) throws Exception {
        List<RmMaterial> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();
            if (rmId != null) {
                filtros.add(new Filtro(RmMaterial.RM, new Rm(rmId), Filtro.EQUAL));
                // Seta as propriedades de retorno da consulta.
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
                propriedades.add(new Propriedade(RmMaterial.RM));
                propriedades.add(new Propriedade(RmMaterial.OBSERVACAO));
                propriedades.add(new Propriedade(RmMaterial.ORDEM));
                propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));

                propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, Material.ALIAS_CLASSE));
                propriedades.add(new Propriedade(Material.NOME, Material.class, Material.ALIAS_CLASSE));

                //Obtem elementos.
                lista = (ArrayList<RmMaterial>) genericDaoRmMaterial.listarByFilter(propriedades, filtros, RmMaterial.MATERIAL, false, RmMaterial.class, -1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Remove todas as RMs em que o seu processo de compra esta finalizado.
     *
     * @param lista -List<Rm>
     * @return lista -List<Rm>
     * @throws Exception
     */
    public List removeRmByDataCompra(List<Rm> lista) throws Exception {
        List<Rm> listaRm = new ArrayList<>();
        try {
            for (Rm rm : lista) {
                if (rm.getStatusCompra().equals(Constantes.DATA_COMPRA_RM_MATERIAL_NAO)) {
                    listaRm.add(rm);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return listaRm;
    }

    /**
     * <pre>
     * Metodo utilizado para setar o status de Compra de RM para uma List<Rm> ou List<RmMaterial>:
     *  - Finalizado: Foi efetuado todas as compras de material
     *  - Ativo: Falta efetuar a compra de material
     *
     * Retorna a mesma lista,mas com o status preenchido.
     *
     * <b>Author</b> : Jerry Adriano
     * <b>Data</b>   : 30/05/2012
     * <b>Param</b>  : List
     * <b>Return</b> : List
     * </pre>
     *
     * @param listaObj
     * @return
     * @throws java.lang.Exception
     */
    public List setaStatusCompraListaRm(List listaObj) throws Exception {
        try {
            if (listaObj != null && listaObj.size() > 0) {
                List<RmMaterial> listaRmMaterial;
                List<Rm> listaRm;
                boolean achou = false;
                int cont = 0;
                List<RmMaterial> lista;
                List<Filtro> filtros = new ArrayList<>();

                String aliasRm = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);

                NxCriterion nxCriterion;
                nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.DATA_COMPRA, null, Filtro.IS_NULL));

                // Seta as propriedades de retorno da consulta.
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
                propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
                //Obtem elementos.
                lista = genericDaoRmMaterial.listarByFilter(propriedades, null, RmMaterial.class, -1, nxCriterion);

                //Tratamento para a lista de Rm
                if (listaObj.get(0).getClass().getName().equals(Rm.class.getName())) {
                    listaRm = listaObj;
                    for (Rm obj : listaRm) {
                        achou = false;
                        cont = 0;
                        while (!achou && lista.size() > cont) {
                            if (obj.getRmId().equals(lista.get(cont).getRm().getRmId())) {
                                achou = true;
                            }
                            cont++;
                        }
                        if (!achou) {
                            //Finalizado
                            obj.setStatusCompra(Constantes.DATA_COMPRA_RM_MATERIAL_SIM);
                        } else {
                            //Ativo
                            obj.setStatusCompra(Constantes.DATA_COMPRA_RM_MATERIAL_NAO);
                        }
                    }

                    //Tratamento para a lista de RmMaterial
                } else if (listaObj.get(0).getClass().getName().equals(RmMaterial.class.getName())) {
                    listaRmMaterial = listaObj;
                    for (RmMaterial obj : listaRmMaterial) {
                        achou = false;
                        cont = 0;
                        while (!achou && lista.size() > cont) {
                            if (obj.getRm().getRmId().equals(lista.get(cont).getRm().getRmId())) {
                                achou = true;
                            }
                            cont++;
                        }
                        if (achou) {
                            obj.getRm().setStatusCompra(Constantes.DATA_COMPRA_RM_MATERIAL_NAO);
                        } else {
                            obj.getRm().setStatusCompra(Constantes.DATA_COMPRA_RM_MATERIAL_SIM);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return listaObj;
    }

    /**
     * <pre>
     * Metodo utilizado para setar o status de Compra só para uma RM:
     *  - Finalizado: Foi efetuado todas as compras de material
     *  - Ativo: Falta efetuar a compra de material
     *
     *
     *
     * <b>Author</b> : Jerry Adriano
     * <b>Data</b>   : 30/05/2012
     * <b>Param</b>  : Rm rm
     * <b>Return</b> : Rm rm
     * </pre>
     *
     * @param rm
     * @return
     * @throws java.lang.Exception
     */
    public Rm setaStatusCompraRm(Rm rm) throws Exception {
        try {
            if (rm != null) {
                List<RmMaterial> lista;

                NxCriterion nxCriterion;
                NxCriterion nxCriterionAux;
                nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.DATA_COMPRA, null, Filtro.IS_NULL));
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM, rm, Filtro.EQUAL));
                nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);

                // Seta as propriedades de retorno da consulta.
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));

                //Obtem elementos.
                lista = genericDaoRmMaterial.listarByFilter(propriedades, null, RmMaterial.class, 1, nxCriterion);

                if (lista == null || lista.isEmpty()) {
                    rm.setStatusCompra(Constantes.DATA_COMPRA_RM_MATERIAL_SIM);
                } else {
                    rm.setStatusCompra(Constantes.DATA_COMPRA_RM_MATERIAL_NAO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return rm;
    }

    public List<Usuario> getAprovadoresRm(Integer rmId) {
        List<Usuario> usuarios = null;
        try {
            Rm rm = rmDao.getRmPorId(rmId);
            WorkflowService workflowService = WorkflowService.getInstance();
            Workflow w = workflowService.getWorkflowRm(rm.getArea().getAreaId(), rm.getCentro().getCentroId());
//            Workflow w = workflowDAO.getWorkflowPorAreaIdCentroId(rm.getArea().getAreaId(), rm.getCentro().getCentroId());
//            if (w == null) {
//                w = workflowDAO.getWorkflowPorCentroId(rm.getCentro().getCentroId());
//            }
            if (w == null) {
                return null;
            }

            WorkflowEnum prox = workflowService.getNextStep(rm, null);
//            String prox = workflowService.getProximaEtapaRm(w, null, rm,null);

            if (prox.equals(WorkflowEnum.WORKFLOW_AREA)) {
                usuarios = workflowAreaDAO.getUsuariosWorkflow(w);
            } else if (prox.equals(WorkflowEnum.WORKFLOW_GERENTE_AREA)) {
                usuarios = workflowGerenteAreaDAO.getUsuariosWorkflow(w);
            } else if (prox.equals(WorkflowEnum.WORKFLOW_CUSTO)) {
                usuarios = workflowCustoDAO.getUsuariosWorkflow(w);
            } else if (prox.equals(WorkflowEnum.WORKFLOW_EMERGENCIAL)) {
                usuarios = workflowEmergencialDAO.getUsuariosWorkflow(w);
            }

//    		if(usuarios==null || usuarios.isEmpty()) {
//    			usuarios = workflowGerenteAreaDAO.getUsuariosWorkflow(w);
//    			if(usuarios == null || usuarios.isEmpty()) {
//	    			usuarios = workflowCustoDAO.getUsuariosWorkflow(w);
//	    			if((usuarios == null || usuarios.isEmpty()) && rm.getPrioridade().getCodigo().equals("MAQ_PARADA")) {
//	    				usuarios = workflowEmergencialDAO.getUsuariosWorkflow(w);
//	    			}
//    			}
//    		}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }
    
    public List<RmServicoCodigoSap> getCodigoSap() {
    	Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);
    	List<RmServicoCodigoSap> list = new ArrayList<>();
        GenericDao genericDao = new GenericDao();
        try {
        	
        	String sql = "SELECT CRIADO, CODIGO_ID, CODIGO, DESCRICAO FROM TB_SERVICO_CODIGO_SAP ORDER BY DESCRICAO ASC;";
        	
             PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet resultSet = smt.executeQuery();

             while (resultSet.next()) {
            	 RmServicoCodigoSap codigoSap = new RmServicoCodigoSap();
                 codigoSap.setCriado(resultSet.getString("CRIADO"));
                 codigoSap.setDescricao(resultSet.getString("DESCRICAO"));
                 codigoSap.setCodigo(resultSet.getString("CODIGO"));
                 codigoSap.setCodigoId(Integer.parseInt(resultSet.getString("CODIGO_ID")));
                 list.add(codigoSap);
             }

             smt.close();
             resultSet.close();	
            
        } catch (Exception e) {
            System.out.println("*************************************************ERRO AO GERAR CONSULTA CODIGO SAP*************************************************");
        }
        return list;
    }
     
}
