/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.rmaweb.dao.CentroDAO;
import br.com.nextage.rmaweb.dao.DepositoDao;
import br.com.nextage.rmaweb.entitybean.Centro;
import br.com.nextage.rmaweb.entitybean.Deposito;
import br.com.nextage.rmaweb.entitybean.EapMultiEmpresa;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.integracao.DepositoResponse;
import br.com.nextage.rmaweb.vo.integracao.DepositoVO;
import br.com.nextage.util.Info;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jerry 21/03/2011
 */
public class DepositoService {

    private static final Log logger = LogFactory.getLog(DepositoService.class);

    private GenericDao<Deposito> genericDao;
    private DepositoDao depositoDao;
    private CentroDAO centroDao;

    /**
     * Método construtor
     */
    public DepositoService() {
        genericDao = new GenericDao<>();
        depositoDao = new DepositoDao();
        centroDao = CentroDAO.getInstance();
    }

    public List<Deposito> getDepositosPorCentro(Integer centroId){
    	try {
    		return depositoDao.getDepositosPorCentro(centroId);
    	}catch (Exception e) {
    		return null;
		}
    }

    /**
     * Método que realiza exclusão de um deposito.
     * <br>
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 21/03/2011
     * <br>
     * <br>
     *
     * @param deposito
     * @return result - boolean
     * @throws Exception - Exception
     */
    public boolean excluir(Deposito deposito) throws Exception {
        Boolean result = false;
        try {
            result = genericDao.delete(deposito);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return result;

    }

    /**
     * Método que salva os dados de um deposito.
     *
     * <br>
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 21/03/2011
     * <br>
     * <br>
     *
     * @param obj - Deposito
     * @return idObjeto - String
     * @throws Exception - Exception
     */
    public String salvar(Deposito obj) throws Exception {
        String idObjeto = "0";
        try {
            if (obj.getDepositoId() > 0) {
                idObjeto = depositoDao.update(obj);
            } else {
                idObjeto = depositoDao.persist(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    /**
     * Lista com todos os Depositos que satisfazem o filtro
     *
     * @param deposito
     * @param qtdRetorno
     * @return
     * @throws Exception
     */
    public List listaDataGrid(Deposito deposito, int qtdRetorno) throws Exception {
        List<Deposito> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            if (deposito.getNome() != null) {
                filtros.add(new Filtro(Deposito.NOME, deposito.getNome(), Filtro.LIKE));
            }
            if (deposito.getResponsavel() != null) {
                filtros.add(new Filtro(Deposito.RESPONSAVEL_ID, deposito.getResponsavel(), Filtro.EQUAL));
            }

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));
            propriedades.add(new Propriedade(Deposito.NOME));
            propriedades.add(new Propriedade(Deposito.CODIGO));
            propriedades.add(new Propriedade(Deposito.ENDERECO));
            propriedades.add(new Propriedade(Deposito.OBSERVACAO));
            propriedades.add(new Propriedade(Deposito.TELEFONE));

            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, Deposito.RESPONSAVEL_ID));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, Deposito.RESPONSAVEL_ID));

            //Obtem elementos.
            lista = (ArrayList<Deposito>) genericDao.listarByFilter(propriedades, filtros, Deposito.NOME, false, Deposito.class, qtdRetorno);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Adicionado campo prioridade
     *
     * @param depositoId
     * @return
     * @throws Exception
     */
    public Deposito selectByUnique(Integer depositoId) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        List<Propriedade> propriedades = new ArrayList<>();
        Deposito obj = new Deposito();
        try {
            filtros.add(new Filtro(Deposito.DEPOSITO_ID, depositoId, Filtro.EQUAL));

            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));
            propriedades.add(new Propriedade(Deposito.NOME));
            propriedades.add(new Propriedade(Deposito.CODIGO));
            propriedades.add(new Propriedade(Deposito.ENDERECO));
            propriedades.add(new Propriedade(Deposito.OBSERVACAO));
            propriedades.add(new Propriedade(Deposito.TELEFONE));

            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, Deposito.RESPONSAVEL_ID));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, Deposito.RESPONSAVEL_ID));

            obj = (Deposito) genericDao.selectUnique(filtros, propriedades, Deposito.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return obj;
    }

    /**
     * Método responsável por preencher a combo de depósitos.
     * <p/>
     * <b>Autor:</b> cristiano.
     * <b>Data:</b> 21-03-2011
     * <p/>
     *
     * @return lista - List<Deposito>
     * @throws Exception - Exception
     */
    public List<Deposito> getComboDeposito() throws Exception {
        List<Deposito> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));
            propriedades.add(new Propriedade(Deposito.NOME));
            propriedades.add(new Propriedade(Deposito.CODIGO));

            //Obtem elementos.
            lista = genericDao.listarByFilter(propriedades, null, Deposito.NOME, false, Deposito.class, Constantes.NO_LIMIT);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Método utilizado para agruapamento de material.
     * <p/>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 20-05-2011
     * <p/>
     *
     * @return lista - List<Deposito>
     * @throws Exception - Exception
     */
    public List<Deposito> selectDepositoIdAll() throws Exception {
        List<Deposito> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();
            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));

            //Obtem elementos.
            lista = genericDao.listarByFilter(propriedades, null, Deposito.DEPOSITO_ID, false, Deposito.class, Constantes.NO_LIMIT);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Atualiza os depositos enviados pelo SAP
     *
     * @param depositosVO
     * @return
     */
    public List<DepositoResponse> atualizarDeposito(List<DepositoVO> depositosVO) {
        List<DepositoResponse> depositosResponse = new ArrayList<>();

        for (final DepositoVO depositoVO : depositosVO) {
            Info info = new Info();
            try {
                Deposito deposito = depositoDao.findByCodigoAndCentro(depositoVO.getCodigoDeposito(), depositoVO.getCodigoCentro());
                Centro centro = centroDao.getCentroPorCodigo(depositoVO.getCodigoCentro());

                if (deposito == null) {
                    deposito = new Deposito();
                    deposito.setCodigo(depositoVO.getCodigoDeposito());
                    deposito.setNome(depositoVO.getDescricao());
                    deposito.setEndereco(StringUtils.EMPTY);
                    deposito.setEapMultiEmpresa(new EapMultiEmpresa(5));
                    deposito.setResponsavel(new Pessoa(6057));
                    deposito.setIsTemporario(false);
                    deposito.setObservacao(StringUtils.EMPTY);
                    deposito.setTelefone(StringUtils.EMPTY);
                    deposito.setCentro(centro);
                    depositoDao.persist(deposito);
                    depositosResponse.add(new DepositoResponse("S", "Deposito criado com sucesso.", Info.INFO_001, depositoVO.getCodigoCentro(), depositoVO.getCodigoDeposito(), depositoVO.getDescricao()));
                } else {
                    deposito.setNome(depositoVO.getDescricao());
                    depositoDao.update(deposito);
                    depositosResponse.add(new DepositoResponse("S", "Deposito atualizado com sucesso.", Info.INFO_001, depositoVO.getCodigoCentro(), depositoVO.getCodigoDeposito(), depositoVO.getDescricao()));
                }
                logger.debug("Deposito atualizado: " + deposito);
            } catch (Exception e) {
                depositosResponse.add(new DepositoResponse("E", "Erro ao tentar atualizar deposito: ".concat(e.getMessage()), Info.INFO_002, depositoVO.getCodigoCentro(), depositoVO.getCodigoDeposito(), depositoVO.getDescricao()));
                logger.error("Erro ao processar interface de deposito: " + depositoVO, e);
            }
        }
        return depositosResponse;
    }
}
