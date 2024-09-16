/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.rmaweb.dao.MaterialDepositoDao;
import br.com.nextage.rmaweb.dao.MaterialDepositoSaidaDao;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.service.integracao.SincEquipamentoService;
import br.com.nextage.rmaweb.util.Constantes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe que recebe requisições do flex e faz a ponte para a obtenção de
 * valores da base de dados.
 *
 * @author marcelo
 */
public class MaterialDepositoService {

    private GenericDao<MaterialDeposito> genericDao;
    private MaterialDepositoDao materialDepositoDao;

    /**
     * Método construtor.
     */
    public MaterialDepositoService() {
        genericDao = new GenericDao<>();
        materialDepositoDao = new MaterialDepositoDao();
    }

    /**
     * Método que realiza exclusão de um MaterialDeposito.
     * <br>
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 22/03/2011
     * <br>
     * <br>
     *
     * @param materialDeposito
     * @return result - boolean
     * @throws Exception - Exception
     */
    public boolean excluir(MaterialDeposito materialDeposito) throws Exception {
        Boolean result = false;
        try {
            result = genericDao.delete(materialDeposito);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return result;

    }

    /**
     * Método que salva os dados de um MaterialDeposito. OBS:Só utilizar quando
     * não é necessario adicionar no histórico de entrada de material,senão
     * utilize o salvarEntrada(MaterialDeposito obj,MaterialDepositoEntrada
     * objMDE)
     * <br>
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 22/03/2011
     * <br>
     * <br>
     *
     * @param obj - MaterialDeposito
     * @return idObjeto - String
     * @throws Exception - Exception
     */
    public String salvar(MaterialDeposito obj) throws Exception {
        String idObjeto = "0";
        try {
            if (obj.getMaterialDepositoId() > 0) {
                idObjeto = materialDepositoDao.update(obj);
            } else {
                idObjeto = materialDepositoDao.persist(obj);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    /**
     * Método que salva os dados de um MaterialDeposito e no historico de
     * entrada. .
     * <br>
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 26/03/2011
     * <br>
     * <br>
     *
     * @param obj - MaterialDeposito
     * @param objMDE
     * @return idObjeto - String
     * @throws Exception - Exception
     */
    public String salvarEntrada(MaterialDeposito obj, MaterialDepositoEntrada objMDE, Boolean enviaCp) throws Exception {
        String idObjeto = "0";
        try {
            if (obj.getMaterialDepositoId() > 0) {
                idObjeto = materialDepositoDao.update(obj);
            } else {
                idObjeto = materialDepositoDao.persist(obj);
            }

            obj.setMaterialDepositoId(Integer.parseInt(idObjeto));
            objMDE.setMaterialDeposito(obj);
            objMDE.setDataEntrada(new Date());
            //Salva no historico o tipo de Entrada
            salvarMaterialDepositoEntrada(objMDE);

            if(enviaCp == true) {
                SincEquipamentoService sincEquipamentoService = new SincEquipamentoService();
                sincEquipamentoService.enviaMaterial(objMDE,true);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    /**
     * Método que salva os dados de um MaterialDeposito e no historico de
     * entrada. .
     * <br>
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 26/03/2011
     * <br>
     * <br>
     *
     * @param obj - MaterialDeposito
     * @param objMDS
     * @return idObjeto + numeroProtocoloSaida - String
     * @throws Exception - Exception
     */
    public String salvarSaida(MaterialDeposito obj, MaterialDepositoSaida objMDS) throws Exception {
        String idObjeto = "0";
        String nProtocolo = "0";
        try {
            if (obj.getMaterialDepositoId() > 0) {
                idObjeto = materialDepositoDao.update(obj);
            } else {
                idObjeto = materialDepositoDao.persist(obj);
            }

            obj.setMaterialDepositoId(Integer.parseInt(idObjeto));
            objMDS.setMaterialDeposito(obj);
            objMDS.setDataSaida(new Date());
            //Salva no historico o tipo de saida
            nProtocolo = salvarMaterialDepositoSaida(objMDS);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return nProtocolo;
    }

    /**
     * Método que salva MaterialDepositoEntrada,e um historico de entrada de
     * material no deposito.
     *
     * <br>
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 26/04/2011
     * <br>
     * <br>
     *
     * @param obj - MaterialDepositoEntrada
     * @return idObjeto - String
     * @throws Exception - Exception
     */
    public String salvarMaterialDepositoEntrada(MaterialDepositoEntrada obj) throws Exception {
        String idObjeto = "0";
        try {
            MaterialDepositoEntradaService entradaService = new MaterialDepositoEntradaService();
            idObjeto = entradaService.salvar(obj);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    /**
     * Método que salva MaterialDepositoSaida,e um historico de saida de
     * material do deposito.
     *
     * <br>
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 26/04/2011
     * <br>
     * <br>
     *
     * @param obj - MaterialDepositoSaida
     * @return numeroProtocolo - String
     * @throws Exception - Exception
     */
    public String salvarMaterialDepositoSaida(MaterialDepositoSaida obj) throws Exception {
        String idObjeto = "0";
        try {
            MaterialDepositoSaidaService saidaService = new MaterialDepositoSaidaService();
            MaterialDepositoSaidaDao matDepSaidaDao = new MaterialDepositoSaidaDao();
            if (obj.getRm() == null && obj.getNumeroProtocoloSaida() < 1) {
                obj.setNumeroProtocoloSaida(matDepSaidaDao.numeroProtocoloSaidaMax());
                if (obj.getNumeroProtocoloSaida() == null) {
                    obj.setNumeroProtocoloSaida(1);
                } else {
                    obj.setNumeroProtocoloSaida(obj.getNumeroProtocoloSaida() + 1);
                }
            }
            idObjeto = saidaService.salvar(obj);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return String.valueOf(obj.getNumeroProtocoloSaida());
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
     * @param materialDeposito
     * @param qtdRetorno
     * @return filtros - List
     * @throws Exception - Exception
     */
    public MaterialDeposito pesquisaMaterialDepositoByMaterialAndDeposito(MaterialDeposito materialDeposito, int qtdRetorno) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        MaterialDeposito obj = new MaterialDeposito();
        try {
            filtros.add(new Filtro(MaterialDeposito.MATERIAL, materialDeposito.getMaterial(), Filtro.EQUAL));
            filtros.add(new Filtro(MaterialDeposito.DEPOSITO, materialDeposito.getDeposito(), Filtro.EQUAL));
            filtros.add(new Filtro(MaterialDeposito.QUANTIDADE, 0d, Filtro.MAIOR));
            if (filtros.size() > 0) {

                // Seta as propriedades de retorno da consulta.
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
                propriedades.add(new Propriedade(MaterialDeposito.MATERIAL));
                propriedades.add(new Propriedade(MaterialDeposito.DEPOSITO));
                propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

                //Obtem elementos.
                obj = (MaterialDeposito) genericDao.selectUnique(filtros, propriedades, MaterialDeposito.class);

            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return obj;
    }

    /**
     * Método lista todos os materiais de um deposito.
     * <br>
     * <br>
     * <b>Autor:</b> Leandro C. Orsi
     * <b>Data:</b> 25/03/2011
     * <br>
     * <br>
     *
     * @param depositoId - Integer
     * @param materialId
     * @return lista - List
     * @throws Exception - Exception
     */
    public List listarMateriasByDeposito(Integer depositoId, Integer materialId) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        List<MaterialDeposito> lista = new ArrayList<>();
        try {

            if (depositoId != null && depositoId > 0) {
                filtros.add(new Filtro(MaterialDeposito.DEPOSITO, new Deposito(depositoId), Filtro.EQUAL));
                if (materialId > 0) {
                    filtros.add(new Filtro(MaterialDeposito.MATERIAL, new Material(materialId), Filtro.EQUAL));
                }
                filtros.add(new Filtro(MaterialDeposito.QUANTIDADE, 0d, Filtro.MAIOR));

                // Seta as propriedades de retorno da consulta.
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
                propriedades.add(new Propriedade(MaterialDeposito.MATERIAL));
                propriedades.add(new Propriedade(MaterialDeposito.DEPOSITO));
                propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));
                propriedades.add(new Propriedade(MaterialDeposito.LOCALIZACAO));

                //Obtem elementos.
                lista = genericDao.listarByFilter(propriedades, filtros, null, false, MaterialDeposito.class, Constantes.NO_LIMIT);

            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * ***********************************************************************
     */
    /*	Início dos Métodos para a Movimentação de Materiais entre Depósitos   */
    /**
     * ***********************************************************************
     */
    /**
     * Método lista todos os materiais de um deposito para a tela
     * ManutMovimDeposito.
     *
     * <p/>
     * <b>Autor:</b> Juliano A. Felipe
     * <b>Data:</b> 28-03-2011
     * <p/>
     *
     * @param depositoId - Integer
     * @param materialId - Integer
     *
     * @return lista - List
     * @throws Exception - Exception
     */
    public List<MaterialDeposito> listarMovimentacaoMateriais(Integer depositoId, Integer materialId) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        List<MaterialDeposito> lista = new ArrayList<>();
        try {

            if (depositoId != null && depositoId > 0 || materialId != null && materialId > 0) {

                if (depositoId != null && depositoId > 0) {
                    filtros.add(new Filtro(MaterialDeposito.DEPOSITO, new Deposito(depositoId), Filtro.EQUAL));
                }
                if (materialId != null && materialId > 0) {
                    filtros.add(new Filtro(MaterialDeposito.MATERIAL, new Material(materialId), Filtro.EQUAL));
                }
                filtros.add(new Filtro(MaterialDeposito.QUANTIDADE, 0.0, Filtro.MAIOR));

                // Seta as propriedades de retorno da consulta.
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
                propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));
                propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, MaterialDeposito.MATERIAL));
                propriedades.add(new Propriedade(Material.NOME, Material.class, MaterialDeposito.MATERIAL));
                propriedades.add(new Propriedade(Material.QUANTIDADE_MINIMA, Material.class, MaterialDeposito.MATERIAL));
                propriedades.add(new Propriedade(Material.TIPO_MATERIAL, Material.class, MaterialDeposito.MATERIAL));
                propriedades.add(new Propriedade(Material.UNIDADE_MEDIDA, Material.class, MaterialDeposito.MATERIAL));
                propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, MaterialDeposito.DEPOSITO));
                propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, MaterialDeposito.DEPOSITO));
                propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, MaterialDeposito.DEPOSITO));

                //Obtem elementos.
                lista = genericDao.listarByFilter(propriedades, filtros, null, false, MaterialDeposito.class, Constantes.NO_LIMIT);

            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Método que salva os dados de um MaterialDeposito. Nao faz a soma no
     * cliente por que nao da.
     *
     * <p/>
     * <b>Autor:</b> Juliano A. Felipe
     * <b>Data:</b> 31-03-2011
     * <p/>
     *
     * <p>
     * Alteração</p>
     * <b>Autor:</b> Jerry
     * <b>Data:</b> 26-04-2011
     * <p/>
     * Foi adicionado o a inclusão de historico de entrada de materiais.
     *
     * @param materialId
     * @param depositoAntId
     * @param numeroProtocolo
     * @param depositoNovoId
     * @param retiradoPor
     * @param qtdeNova
     * @return idObjeto - String
     * @throws Exception - Exception
     */
    public String salvarMovimentacao(Integer materialId, Integer depositoAntId, Integer depositoNovoId, Double qtdeNova, Pessoa retiradoPor, Integer numeroProtocolo) throws Exception {
        String nProtocoloSaida = "0";
        MaterialDeposito depositoAnt;
        MaterialDeposito depositoNovo;
        List<Filtro> filtros = new ArrayList<>();

        /**
         * Busca o depósito antigo *
         */
        filtros.add(new Filtro(MaterialDeposito.DEPOSITO, new Deposito(depositoAntId), Filtro.EQUAL));
        filtros.add(new Filtro(MaterialDeposito.MATERIAL, new Material(materialId), Filtro.EQUAL));

        depositoAnt = (MaterialDeposito) genericDao.selectUnique(filtros, MaterialDeposito.class);

        /**
         * Verifica se o deposito Antigo não é nulo. Ele deve existir *
         */
        if (depositoAnt == null) {
            throw new Exception();
        }

        /**
         * Busca um novo objeto para o material movido *
         */
        filtros = new ArrayList<>();
        filtros.add(new Filtro(MaterialDeposito.DEPOSITO, new Deposito(depositoNovoId), Filtro.EQUAL));
        filtros.add(new Filtro(MaterialDeposito.MATERIAL, new Material(materialId), Filtro.EQUAL));

        depositoNovo = (MaterialDeposito) genericDao.selectUnique(filtros, MaterialDeposito.class);

        /**
         * Se o deposito novo é nulo após a consulta, quer dizer que é um novo
         * registro. Senão deve-se atualizar o registro já existente *
         */
        if (depositoNovo == null) {
            depositoNovo = new MaterialDeposito();
            depositoNovo.setMaterialDepositoId(0);
            depositoNovo.setDeposito(new Deposito(depositoNovoId));
            depositoNovo.setMaterial(new Material(materialId));
            depositoNovo.setQuantidade(qtdeNova);
        } else {
            depositoNovo.setDeposito(new Deposito(depositoNovoId));
            depositoNovo.setQuantidade(depositoNovo.getQuantidade() + qtdeNova);
        }

        /**
         * Calcula a quantidade retirada do deposito *
         */
        depositoAnt.setQuantidade(depositoAnt.getQuantidade() - qtdeNova);

        /**
         * Salva os objetos *
         */
        try {
            //Historico MaterialDepositoSaida
            MaterialDepositoSaida objMDS = new MaterialDepositoSaida();
            objMDS.setMaterialDeposito(depositoAnt);
            objMDS.setDataSaida(new Date());
            objMDS.setQuantidade(qtdeNova);
            objMDS.setMaterialDepositoSaidaId(-1);
            objMDS.setObservacao(Constantes.SAIDA_DE_MATERIAL_POR_MOVIMENTACAO);
            objMDS.setRetiradoPor(retiradoPor);
            objMDS.setNumeroProtocoloSaida(numeroProtocolo);
            //salva o deposito e o hitorico de saida de materiais e retorna o numero de
            //protocolo gerado.
            nProtocoloSaida = salvarSaida(depositoAnt, objMDS);

            //Historico MaterialDepositoEntrada
            MaterialDepositoEntrada objMDE = new MaterialDepositoEntrada();
            objMDE.setDataEntrada(new Date());
            objMDE.setQuantidade(qtdeNova);
            objMDE.setObservacao(Constantes.ENTRADA_DE_MATERIAL_POR_MOVIMENTACAO);
            objMDE.setMaterialDepositoEntradaId(-1);

            //salva o deposito e o hitorico de entrada de materiais
            salvarEntrada(depositoNovo, objMDE, true);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return nProtocoloSaida;
    }

    /**
     * ***********************************************************************
     */
    /*	  Fim dos Métodos para a Movimentação de Materiais entre Depósitos    */
    /**
     * ***********************************************************************
     */
    /**
     * Método que Obtem os dados de um material.
     * <br>
     * <br>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param materialDeposito
     * @param qtdRetorno
     * @return filtros - List
     * @throws Exception - Exception
     */
    public List pesquisaMaterialIncluir(MaterialDeposito materialDeposito, int qtdRetorno) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        MaterialDeposito obj = new MaterialDeposito();
        List<MaterialDeposito> lista = new ArrayList<>();
        try {
            filtros.add(new Filtro(MaterialDeposito.MATERIAL, materialDeposito.getMaterial(), Filtro.EQUAL));
            filtros.add(new Filtro(MaterialDeposito.DEPOSITO, materialDeposito.getDeposito(), Filtro.EQUAL));

            if (filtros.size() > 0) {

                // Seta as propriedades de retorno da consulta.
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
                propriedades.add(new Propriedade(MaterialDeposito.MATERIAL));
                propriedades.add(new Propriedade(MaterialDeposito.DEPOSITO));
                propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

                //Obtem elementos.
                lista = genericDao.listarByFilter(propriedades, filtros, null, false, MaterialDeposito.class, qtdRetorno);

            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return lista;
    }

    /**
     * Metodo que retorna todos os dados do MaterialDeposito por material
     *
     * @param material
     * @param deposito
     * @return
     * @throws Exception
     */
    public MaterialDeposito selectMaterialDepositoByMaterialAndDeposito(Material material, Deposito deposito) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        MaterialDeposito obj = new MaterialDeposito();
        try {
            filtros.add(new Filtro(MaterialDeposito.MATERIAL, material, Filtro.EQUAL));
            filtros.add(new Filtro(MaterialDeposito.DEPOSITO, deposito, Filtro.EQUAL));

            obj = (MaterialDeposito) genericDao.selectUnique(filtros, MaterialDeposito.class);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return obj;
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
    public String salvarMaterialDepositoAgrupamento(MaterialDeposito obj) throws Exception {
        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE, MaterialDeposito.class, MaterialDeposito.ALIAS_CLASSE_UNDERLINE));
            genericDao.update(obj, propriedades);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return "";
    }

    /**
     * Update para o campo Localizacao
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
    public String updateMaterialDepositoLocalizacao(MaterialDeposito obj) throws Exception {
        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDeposito.LOCALIZACAO, MaterialDeposito.class, MaterialDeposito.ALIAS_CLASSE_UNDERLINE));
            genericDao.update(obj, propriedades);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return "";
    }
}
