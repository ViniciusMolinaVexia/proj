/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.dao.MaterialDepositoSaidaDao;
import br.com.nextage.rmaweb.dao.RmMaterialRetiradaDao;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.RmMaterialService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.ws.sap.RetiradaMateriaisSapService;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.Util;
import br.com.nextage.util.criptografia.Criptografia;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author Daniel H. Parisotto
 */
public class PessoaDigitalService {

    /**
     * Retorna um objeto de Pessoa serializado em JSON de acordo com a
     * referencia passada como parametro.
     *
     * @param referencia
     * @return
     */
    public String uniquePessoaByReferencia(String referencia) {
        Pessoa pessoa;
        NxCriterion nxCriterion;
        Gson gson = new Gson();
        String pessoaJson = null;
        try {
            if (referencia != null) {
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
                propriedades.add(new Propriedade(Pessoa.NOME));
                propriedades.add(new Propriedade(Pessoa.REFERENCIA));
                propriedades.add(new Propriedade(Pessoa.CPF));

                nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.REFERENCIA, referencia, Filtro.EQUAL));

                GenericDao<Pessoa> genericDao = new GenericDao<>();
                pessoa = genericDao.selectUnique(propriedades, Pessoa.class, nxCriterion);
                if (pessoa != null) {
                    pessoaJson = gson.toJson(pessoa);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pessoaJson;
    }

    /**
     * Retorna um objeto de Pessoa serializado em JSON de acordo com a
     * referencia passada como parametro.
     *
     * @param referencia
     * @return
     */
    public String uniquePessoaByReferencia(String referencia,String eapMultiEmpresaJson) {
        Pessoa pessoa;
        NxCriterion nxCriterion;
        Gson gson = new Gson();
        String pessoaJson = null;
        try {
            if (referencia != null) {
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
                propriedades.add(new Propriedade(Pessoa.NOME));
                propriedades.add(new Propriedade(Pessoa.REFERENCIA));
                propriedades.add(new Propriedade(Pessoa.CPF));

                nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.REFERENCIA, referencia, Filtro.EQUAL));

                if(eapMultiEmpresaJson != null && eapMultiEmpresaJson.length() > 0){
                    EapMultiEmpresa eapMultiEmpresa =  gson.fromJson(eapMultiEmpresaJson,EapMultiEmpresa.class);
                    if(eapMultiEmpresa != null){
                        nxCriterion = NxCriterion.and(nxCriterion,NxCriterion.montaRestriction(new Filtro(Pessoa.EAP_MULTI_EMPRESA, eapMultiEmpresa, Filtro.EQUAL)));
                    }
                }

                GenericDao<Pessoa> genericDao = new GenericDao<>();
                pessoa = genericDao.selectUnique(propriedades, Pessoa.class, nxCriterion);
                if (pessoa != null) {
                    pessoaJson = gson.toJson(pessoa);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pessoaJson;
    }

    /**
     * lista EapMultiEmpresa.
     *
     * @param
     * @return Lista Gson
     */
    public String listarEapMultiEmpresa() {
        NxCriterion nxCriterion;
        Gson gson = new Gson();
        String listaGson = null;
        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(EapMultiEmpresa.ID));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO));

            GenericDao<EapMultiEmpresa> genericDao = new GenericDao<>();
            List<EapMultiEmpresa> lista = genericDao.listarByFilter(propriedades,null, EapMultiEmpresa.class,-1, null);

            if (lista != null) {
                listaGson = gson.toJson(lista);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaGson;
    }

    /**
     * Retorna um Objeto de FuncionarioDigital serializado de acordo com o
     * funcionario passado como parametro.
     *
     * @param pessoaId
     * @return
     */
    public String uniquePessoaDigital(Integer pessoaId) {
        PessoaDigital pessoaDigital;
        NxCriterion nxCriterion;
        Gson gson = new Gson();
        String pessoaDigitalJson = null;
        try {

            String aliasPessoa = NxCriterion.montaAlias(PessoaDigital.ALIAS_CLASSE, PessoaDigital.PESSOA_ID);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(PessoaDigital.ID));
            propriedades.add(new Propriedade(PessoaDigital.DIGITAL));
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasPessoa));

            nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, pessoaId, Filtro.EQUAL, aliasPessoa));

            GenericDao<PessoaDigital> genericDao = new GenericDao<>();
            pessoaDigital = genericDao.selectUnique(propriedades, PessoaDigital.class, nxCriterion);
            if (pessoaDigital != null) {
                pessoaDigitalJson = gson.toJson(pessoaDigital);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pessoaDigitalJson;
    }

    /**
     * Retorna lista de retirada de acordo com a pessoa passado como parametro.
     *
     * @param pessoaId
     * @return
     */
    public String listarRetiradaByFuncionario(Integer pessoaId) {
        NxCriterion nxCriterion;
        List<RmMaterialRetirada> listaMaterialRetiradas;
        GenericDao<RmMaterialRetirada> genericDao = new GenericDao<>();
        Set<Rm> setRm = new HashSet<>();
        Gson gson = new Gson();
        String listaJson = null;
        try {

            String aliasRetiradoPor = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RETIRADO_POR);
            String aliasRmMaterial = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RM_MATERIAL);
            String aliasRm = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RM_MATERIAL, RmMaterial.RM);
            String aliasMaterial = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RM_MATERIAL, RmMaterial.MATERIAL);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialRetirada.RM_MATERIAL_RETIRADA_ID));
            propriedades.add(new Propriedade(RmMaterialRetirada.QUANTIDADE));

            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRetiradoPor));

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));

            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));

            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));

            nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, pessoaId, Filtro.EQUAL, aliasRetiradoPor));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.AUTENTICACAO, null, Filtro.IS_NULL)));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.PRE_RETIRADA, Boolean.TRUE, Filtro.EQUAL)));

            listaMaterialRetiradas = genericDao.listarByFilter(propriedades, null, RmMaterialRetirada.class, Constantes.NO_LIMIT, nxCriterion);

            for (RmMaterialRetirada rmMaterialRetirada : listaMaterialRetiradas) {
                setRm.add(rmMaterialRetirada.getRmMaterial().getRm());
            }

            listaJson = gson.toJson(setRm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaJson;
    }

    /**
     * Método para cadastrar a impressão digital de uma pessoa.
     *
     * @param pessoaId
     * @param impressaoDigital
     * @return msg
     */
    public String cadastrarDigitalPessoa(Integer pessoaId, String impressaoDigital) {
        String msg = "";
        GenericDao<PessoaDigital> genericDao = new GenericDao<>();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(null));
        try {
            if (pessoaId != null && pessoaId > 0 && impressaoDigital != null) {
                Pessoa pessoa = new Pessoa(pessoaId);
                PessoaDigital pessoaDigital = listarPessoaDigital(pessoaId);
                if (pessoaDigital != null && pessoaDigital.getId() != null && pessoaDigital.getId() > 0) {
                    pessoaDigital.setDigital(impressaoDigital);
                    List<Propriedade> propriedadesAtualizar = new ArrayList<>();
                    propriedadesAtualizar.add(new Propriedade(PessoaDigital.DIGITAL));
                    genericDao.update(pessoaDigital, propriedadesAtualizar);
                } else {
                    pessoaDigital = new PessoaDigital(-1, pessoa, impressaoDigital);
                    genericDao.persist(pessoaDigital);
                }

                msg = rb.getString("msg_impressoes_cadastradas_sucesso");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = rb.getString("msg_impressoes_cadastradas_erro");
        }

        return msg;
    }

    /**
     * Método para verificar a existencia de cadastro de impressão para uma
     * pessoa
     *
     * @param pessoaId
     * @return
     */
    private PessoaDigital listarPessoaDigital(Integer pessoaId) {
        PessoaDigital pessoaDigital = new PessoaDigital();
        GenericDao<PessoaDigital> genericDao = new GenericDao<>();
        try {
            String aliasFuncionario = NxCriterion.montaAlias(PessoaDigital.ALIAS_CLASSE, PessoaDigital.PESSOA_ID);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(PessoaDigital.ID));
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasFuncionario));

            NxCriterion nxCriterionPrincipal = NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, pessoaId, Filtro.EQUAL, aliasFuncionario));

            pessoaDigital = genericDao.selectUnique(propriedades, PessoaDigital.class, nxCriterionPrincipal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pessoaDigital;
    }

    /**
     * Método para inserir no BD caso a validação da digital seja verdadeira.
     *
     * @param rmId
     * @param pessoaDigitalId
     * @param impressaoDigital
     * @param pessoaId
     * @return msg
     */
    public String autenticarRetirada(Integer rmId, Integer pessoaDigitalId, String impressaoDigital, Integer pessoaId) {
        String msg = "";
        GenericDao<RmMaterialRetirada> genericDao = new GenericDao<>();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(null));
        Info info;

        try {
            if (rmId != null && rmId > 0 && pessoaDigitalId != null && pessoaDigitalId > 0 && impressaoDigital != null && !impressaoDigital.isEmpty()) {

                List<RmMaterialRetirada> listaMaterialRetirada = listarRetiradas(new Rm(rmId), pessoaId);
                for (RmMaterialRetirada rmMaterialRetirada : listaMaterialRetirada) {
                    info = retirarEstoque(rmMaterialRetirada);

                    if (info.getErro() == null) {
                        rmMaterialRetirada.setDataAutenticacao(new Date());

                        String autenticacao = "";
                        autenticacao += rb.getString("label_data_autenticacao") + ": " + dateFormat.format(rmMaterialRetirada.getDataAutenticacao()) + "\r\n";
                        autenticacao += rb.getString("label_numero_rm") + ": " + rmMaterialRetirada.getRmMaterial().getRm().getNumeroRm() + "\r\n";
                        autenticacao += rb.getString("label_codigo_material") + ": " + rmMaterialRetirada.getRmMaterial().getMaterial().getCodigo() + "\r\n";
                        autenticacao += rb.getString("label_material") + ": " + rmMaterialRetirada.getRmMaterial().getMaterial().getNome() + "\r\n";
                        autenticacao += rb.getString("label_quantidade") + ": " + rmMaterialRetirada.getQuantidade() + "\r\n";
                        autenticacao += rb.getString("label_retirado_por") + ": " + rmMaterialRetirada.getRetiradoPor().getNome() + "\r\n";

                        rmMaterialRetirada.setAutenticacao(Criptografia.cript(autenticacao));

                        List<Propriedade> propriedades = new ArrayList<>();
                        propriedades.add(new Propriedade(RmMaterialRetirada.DATA_AUTENTICACAO));
                        propriedades.add(new Propriedade(RmMaterialRetirada.AUTENTICACAO));
                        propriedades.add(new Propriedade(RmMaterialRetirada.PRE_RETIRADA));

                        rmMaterialRetirada.setPreRetirada(Boolean.FALSE);

                        genericDao.update(rmMaterialRetirada, propriedades);

                        msg = rb.getString("label_retirada_autenticada");
                    } else {
                        msg = rb.getString("label_erro_autenticacao");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = rb.getString("label_erro_autenticacao");
        }
        return msg;
    }

    private List<RmMaterialRetirada> listarRetiradas(Rm rm, Integer pessoaId) {
        List<RmMaterialRetirada> listaMaterialRetirada = new ArrayList<>();
        GenericDao<RmMaterialRetirada> genericDao = new GenericDao<>();

        String aliasPessoa = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RETIRADO_POR);
        String aliasRmMaterial = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RM_MATERIAL);
        String aliasMaterial = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RM_MATERIAL, RmMaterial.MATERIAL);
        String aliasRm = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RM_MATERIAL, RmMaterial.RM);
        String aliasDeposito = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.DEPOSITO);

        // Seta as propriedades de retorno da consulta.
        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(RmMaterialRetirada.RM_MATERIAL_RETIRADA_ID));
        propriedades.add(new Propriedade(RmMaterialRetirada.DATA_RETIRADA));
        propriedades.add(new Propriedade(RmMaterialRetirada.QUANTIDADE));
        propriedades.add(new Propriedade(RmMaterialRetirada.NUMERO_PROTOCOLO));

        //Retirado por
        propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasPessoa));
        propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasPessoa));

        //RmMaterial
        propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));

        //Material
        propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
        propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
        propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));

        //DEPOSITO
        propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
        propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));
        propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));

        //Rm
        propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
        propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));

        NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Rm.RM_ID, rm.getRmId(), Filtro.EQUAL, aliasRm));
        nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterialRetirada.PRE_RETIRADA, Boolean.TRUE, Filtro.EQUAL)));
        nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, pessoaId, Filtro.EQUAL, aliasPessoa)));

        try {
            listaMaterialRetirada = genericDao.listarByFilter(propriedades, null, RmMaterialRetirada.class, Constantes.NO_LIMIT, nxCriterion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaMaterialRetirada;
    }

    private Info retirarEstoque(RmMaterialRetirada rmMaterialRetirada) {
        Info info = new Info();
        RmaService rmaService = new RmaService();
        MaterialDeposito mDep = new MaterialDeposito();
        MaterialDepositoSaidaDao matDepSaidaDao = new MaterialDepositoSaidaDao();
        MaterialDepositoSaida mDSaida;
        GenericDao<MaterialDeposito> genericDao = new GenericDao<>();
        Date data = new Date();
        RmMaterialStatus rmMaterialStatus;
        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(null));
        RmMaterialRetiradaDao rmMaterialRetiradaDao = new RmMaterialRetiradaDao();

        Boolean finalizouRmMaterial = false;

        try {
            if (rmMaterialRetirada.getNumeroProtocolo() == null || rmMaterialRetirada.getNumeroProtocolo() < 1) {
                rmMaterialRetirada.setNumeroProtocolo(rmMaterialRetiradaDao.numeroProtocoloMax() + 1);
            }

            RmMaterial rmMaterial = (RmMaterial) new RmMaterialService().selectByUnique(rmMaterialRetirada.getRmMaterial().getRmMaterialId());
            if (rmMaterial != null) {

                NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, rmMaterialRetirada.getRmMaterial().getMaterial(), Filtro.EQUAL));
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.DEPOSITO, rmMaterialRetirada.getDeposito(), Filtro.EQUAL)));

                //Seta as propriedades de retorno da consulta.
                String aliasMaterial = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL);
                String aliasDeposito = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.DEPOSITO);
                String alaisUnidadeMedida = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL, Material.UNIDADE_MEDIDA);

                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
                propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

                propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
                propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
                propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
                propriedades.add(new Propriedade(Material.ESTOQUE_SAP, Material.class, aliasMaterial));

                propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
                propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));
                propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));

                propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, alaisUnidadeMedida));
                propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, alaisUnidadeMedida));

                //Obtem elemento.
                mDep = genericDao.selectUnique(propriedades, MaterialDeposito.class, nxCriterion);

                genericDao.beginTransaction();
                
                if (rmMaterial.getRm().getNumeroRm() != null) {
                    mDSaida = rmaService.listarMaterialDepositoSaida(rmMaterial.getRm(), rmMaterialRetirada.getRmMaterial().getMaterial(), rmMaterialRetirada.getDeposito(), rmMaterial);
                    //Separa a quantidade recebida que já foi autenticada
                    Double qtdeTotalJaRecebida = rmaService.qtdeRmMatRetirada(rmMaterialRetirada, false, false);
                    //Separa a quantidade recebida que não foi autenticada
                    Double qtdeRecBioSemAutTotal = rmaService.qtdeRmMatRetirada(rmMaterialRetirada, true, false);
                    
                    //Separa a quantidade total do deposito deste material deposito saida
                    Double verificaQtdeTotalDep = rmaService.qtdeRmMatRetirada(rmMaterialRetirada, false, true);
                    
                    
                    if (verificaQtdeTotalDep == mDSaida.getQuantidadeOriginal()){
                        propriedades.clear();
                        propriedades.add(new Propriedade(MaterialDepositoSaida.RESERVA));
                        propriedades.add(new Propriedade(MaterialDepositoSaida.NUMERO_PROTOCOLO_SAIDA));

                        mDSaida.setReserva(Boolean.FALSE);
 
                        if (mDSaida.getRm() == null && mDSaida.getNumeroProtocoloSaida() < 1) {
                            mDSaida.setNumeroProtocoloSaida(matDepSaidaDao.numeroProtocoloSaidaMax());
                            if (mDSaida.getNumeroProtocoloSaida() == null) {
                                mDSaida.setNumeroProtocoloSaida(1);
                            } else {
                                mDSaida.setNumeroProtocoloSaida(mDSaida.getNumeroProtocoloSaida() + 1);
                            }
                        }
                        
                        if((rmMaterial.getQuantidade()) == (qtdeTotalJaRecebida + qtdeRecBioSemAutTotal)){
                            rmMaterialStatus = rmMaterialStatusService.listarStatusAtual(rmMaterialRetirada.getRmMaterial());
                            rmMaterialStatusService.finalizarStatus(rmMaterialStatus, genericDao, data);
                            rmMaterialStatusService.gerarStatus(rmMaterialRetirada.getRmMaterial(), Constantes.STATUS_RM_MATERIAL_RETIRADO_PARCIALMENTE, genericDao, data, Util.setParamsLabel(rb.getString("label_retirado_x_materiais"), rmMaterialRetirada.getQuantidade(), rmMaterial.getMaterial().getUnidadeMedida().getSigla()), rmMaterialRetirada.getRetiradoPor().getNome());

                            rmMaterialStatusService.gerarStatus(rmMaterialRetirada.getRmMaterial(), Constantes.STATUS_RM_MATERIAL_FINALIZADO, genericDao, data, null, rmMaterialRetirada.getRetiradoPor().getNome());

                            finalizouRmMaterial = true;
                        } else {
                            rmMaterialStatus = rmMaterialStatusService.listarStatusAtual(rmMaterialRetirada.getRmMaterial());
                            rmMaterialStatusService.finalizarStatus(rmMaterialStatus, genericDao, data);
                            rmMaterialStatusService.gerarStatus(rmMaterialRetirada.getRmMaterial(), Constantes.STATUS_RM_MATERIAL_RETIRADO_PARCIALMENTE, genericDao, data, Util.setParamsLabel(rb.getString("label_retirado_x_materiais"), rmMaterialRetirada.getQuantidade(), rmMaterial.getMaterial().getUnidadeMedida().getSigla()), rmMaterialRetirada.getRetiradoPor().getNome());
                        }

                        genericDao.updateWithCurrentTransaction(mDSaida, propriedades);
                    } else {
                        rmMaterialStatus = rmMaterialStatusService.listarStatusAtual(rmMaterialRetirada.getRmMaterial());
                        rmMaterialStatusService.finalizarStatus(rmMaterialStatus, genericDao, data);
                        rmMaterialStatusService.gerarStatus(rmMaterialRetirada.getRmMaterial(), Constantes.STATUS_RM_MATERIAL_RETIRADO_PARCIALMENTE, genericDao, data, Util.setParamsLabel(rb.getString("label_retirado_x_materiais"), rmMaterialRetirada.getQuantidade(), rmMaterial.getMaterial().getUnidadeMedida().getSigla()), rmMaterialRetirada.getRetiradoPor().getNome());
                    }
                } else {
                    mDSaida = new MaterialDepositoSaida();

                    mDSaida.setDataSaida(new Date());
                    mDSaida.setMaterialDeposito(mDep);
                    mDSaida.setMaterialDepositoSaidaId(0);
                    mDSaida.setQuantidade(rmMaterialRetirada.getQuantidade());
                    mDSaida.setRm(rmMaterialRetirada.getRmMaterial().getRm());

                    if (mDSaida.getRm() == null && mDSaida.getNumeroProtocoloSaida() < 1) {
                        mDSaida.setNumeroProtocoloSaida(matDepSaidaDao.numeroProtocoloSaidaMax());
                        if (mDSaida.getNumeroProtocoloSaida() == null) {
                            mDSaida.setNumeroProtocoloSaida(1);
                        } else {
                            mDSaida.setNumeroProtocoloSaida(mDSaida.getNumeroProtocoloSaida() + 1);
                        }
                    }

                    genericDao.persistWithCurrentTransaction(mDSaida);

                    propriedades.clear();
                    propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));
                    //Retira a Quantidade no Deposito
                    mDep.setQuantidade(mDep.getQuantidade() - rmMaterialRetirada.getQuantidade());
                    genericDao.updateWithCurrentTransaction(mDep, propriedades);
                }

                genericDao.commitCurrentTransaction();

                if (finalizouRmMaterial) {
                    rmaService.validarRetiradasNaoPresenciais(rmMaterial);
                }

                mDSaida.getRm().setNumeroRm(rmMaterialRetirada.getRmMaterial().getRm().getNumeroRm());
                RetiradaMateriaisSapService retiradaMateriaisService = new RetiradaMateriaisSapService();
                info = retiradaMateriaisService.enviarRetirada(mDSaida);

                if (info.getErro() == null) {
                    GenericDao<MaterialDepositoSaida> genericDaoMDS = new GenericDao<>();

                    propriedades.clear();
                    propriedades.add(new Propriedade(MaterialDepositoSaida.DATA_ENVIO_SAP));

                    mDSaida.setDataEnvioSap(new Date());

                    genericDaoMDS.update(mDSaida, propriedades);
                } else {
                    rmaService.enviarEmailAdministrador("Retirada", info.getMensagem());
                }

                info.setMensagem(rb.getString("msg_registro_salvo_sucesso"));
                info.setErro(null);
                info.setCodigo(Info.INFO_001);

                if (!rmMaterialRetirada.getRetiradoPor().equals(rmMaterial.getRm().getRequisitante())) {
                    rmaService.enviarEmailRequisitanteRetiradaMaterial(rmMaterial.getRm(), rmMaterialRetirada.getRetiradoPor(), rmMaterial, rmMaterialRetirada.getQuantidade());
                }

            } else {
                info.setMensagem(rb.getString("msg_erro_realizar_operacao"));
                info.setErro(rb.getString("msg_erro_realizar_operacao"));
                info.setCodigo(Info.INFO_002);
                info.setObjeto(rmMaterialRetirada.getNumeroProtocolo());
            }

        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            info.setMensagem(rb.getString("msg_erro_realizar_operacao"));
            info.setErro(rb.getString("msg_erro_realizar_operacao"));
            info.setCodigo(Info.INFO_002);

            e.printStackTrace();
        }
        return info;
    }
}
