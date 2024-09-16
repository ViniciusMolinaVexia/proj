/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.rmaweb.dao.MaterialDepositoSaidaDao;
import br.com.nextage.rmaweb.dao.RmMaterialRetiradaDao;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.service.integracao.RastreabilidadeService;
import br.com.nextage.rmaweb.service.integracao.RmMaterialStatusService;
import br.com.nextage.rmaweb.service.integracao.RmaService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.util.RelatorioFlexUtil;
import br.com.nextage.rmaweb.ws.sap.RetiradaMateriaisSapService;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.Util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Classe que permite acesso Ã  persistencia
 *
 * @author Jerry
 */
public class RmMaterialRetiradaService {

    private GenericDao genericDao;
    private RmMaterialRetiradaDao rmMaterialRetiradaDao;
    private GenericDao<MaterialDeposito> materialDepositoDao;
    private RastreabilidadeService rastreabilidadeService = RastreabilidadeService.getInstance();

    /**
     * Construtor
     */
    public RmMaterialRetiradaService() {
        genericDao = new GenericDao();
        rmMaterialRetiradaDao = new RmMaterialRetiradaDao();
        materialDepositoDao = new GenericDao<>();
    }

    /**
     * Método que realiza exclusão de um RmMaterialRetirada.
     * <br>
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 23/03/2011
     * <br>
     * <br>
     *
     * @param rmMaterialRetirada
     * @return result - boolean
     * @throws Exception - Exception
     */
    public boolean excluir(RmMaterialRetirada rmMaterialRetirada) throws Exception {
        Boolean result = false;
        try {
            result = genericDao.delete(rmMaterialRetirada);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return result;

    }

    /**
     * Método que salva os dados de um RmMaterialRetiradaDao. Nao faz a soma no
     * cliente por que nao da.
     * <br>
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 23/03/2011
     * <br>
     * <br>
     *
     * @param obj - rmMaterialRetiradaDao
     * @return idObjeto - String
     * @throws Exception - Exception
     */
    public String salvar(RmMaterialRetirada obj, String usuario) throws Exception {
        String idObjeto = "0";
        //Verificar mudancas feitas no obj
        try {
            if (obj.getRmMaterialRetiradaId() > 0) {
                idObjeto = rmMaterialRetiradaDao.update(obj);
            } else {
                idObjeto = rmMaterialRetiradaDao.persist(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    /**
     * Recupera a liasta de RmMaterialRetirada conforme os filtros passados como
     * parametro.
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 24/03/2011
     * <br>
     *
     * @param rmM
     * @param numeroProtocolo
     * @param qtdRetorno
     * @return idObjeto - String
     * @throws Exception - Exception
     */
    public List listaDataGrid(RmMaterial rmM, String numeroProtocolo, int qtdRetorno) throws Exception {
        List<RmMaterialRetirada> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();
            //if(rmM != null || rmM.getRmMaterialId() > 0 && numeroProtocolo == null){
            if (rmM != null && rmM.getRmMaterialId() > 0) {
                filtros.add(new Filtro(RmMaterialRetirada.RM_MATERIAL, rmM, Filtro.EQUAL));
            }

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialRetirada.RM_MATERIAL_RETIRADA_ID));
            propriedades.add(new Propriedade(RmMaterialRetirada.QUANTIDADE));
            propriedades.add(new Propriedade(RmMaterialRetirada.DATA_RETIRADA));
            propriedades.add(new Propriedade(RmMaterialRetirada.RM_MATERIAL));
            propriedades.add(new Propriedade(RmMaterialRetirada.NUMERO_PROTOCOLO));
            propriedades.add(new Propriedade(RmMaterialRetirada.AUTENTICACAO));

            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, RmMaterialRetirada.RETIRADO_POR));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, RmMaterialRetirada.RETIRADO_POR));

            //Obtem elementos.
            lista = (ArrayList<RmMaterialRetirada>) genericDao.listarByFilter(propriedades, filtros, RmMaterialRetirada.DATA_RETIRADA, false, RmMaterialRetirada.class, qtdRetorno);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * MÃ©todo que salva os dados de um RmMaterialRetiradaDao. Nao faz a soma no
     * cliente por que nao da.
     * <br>
     * <br>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 24/03/2011
     * <br>
     * <br>alRetiradaDao
     *
     * @param numeroProtocolo
     * @param qtdRetorno
     * @return idObjeto - String
     * @throws Exception - Exception
     */
    public List listaDataGridByNumeroProtocolo(String numeroProtocolo, int qtdRetorno) throws Exception {
        List<RmMaterialRetirada> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            if (numeroProtocolo != null && !numeroProtocolo.isEmpty()) {
                filtros.add(new Filtro(RmMaterialRetirada.NUMERO_PROTOCOLO, Integer.parseInt(numeroProtocolo), Filtro.EQUAL));
            }

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialRetirada.RM_MATERIAL_RETIRADA_ID));
            propriedades.add(new Propriedade(RmMaterialRetirada.QUANTIDADE));
            propriedades.add(new Propriedade(RmMaterialRetirada.DATA_RETIRADA));
            propriedades.add(new Propriedade(RmMaterialRetirada.RM_MATERIAL));
            propriedades.add(new Propriedade(RmMaterialRetirada.NUMERO_PROTOCOLO));
            propriedades.add(new Propriedade(RmMaterialRetirada.AUTENTICACAO));

            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, RmMaterialRetirada.RETIRADO_POR));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, RmMaterialRetirada.RETIRADO_POR));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, RmMaterialRetirada.RETIRADO_POR));
            propriedades.add(new Propriedade(Pessoa.FUNCAO, Pessoa.class, RmMaterialRetirada.RETIRADO_POR));

            //Obtem elementos.
            lista = (ArrayList<RmMaterialRetirada>) genericDao.listarByFilter(propriedades, filtros, RmMaterialRetirada.DATA_RETIRADA, false, RmMaterialRetirada.class, qtdRetorno);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    public Info retirarEstoque(RmMaterialRetirada rmMaterialRetirada) {
        Info info;
        br.com.nextage.persistence_2.dao.GenericDao<RmMaterialRetirada> genericDao = new br.com.nextage.persistence_2.dao.GenericDao<>();
        try {
            genericDao.beginTransaction();
            info = retirarEstoque(rmMaterialRetirada, genericDao);
            genericDao.commitCurrentTransaction();
        } catch (Exception ex) {
            genericDao.rollbackCurrentTransaction();
            info = Info.GetError(ex.getMessage());
        }
        return info;
    }

    /**
     * MÃ©todo que retira o material do estoque e chama salvar
     * RmMaterialRetirada e retorna o numero do protocolo.
     * <p/>
     * <b>Autor:</b> jerry.
     * <b>Data:</b> 24/03/2011
     *
     * @param rmMaterialRetirada
     * @param genericDao
     * @return numeroProtocolo - String
     */
    public Info retirarEstoque(RmMaterialRetirada rmMaterialRetirada, br.com.nextage.persistence_2.dao.GenericDao genericDao) {
        return retirarEstoque(rmMaterialRetirada, genericDao, false, true, 0.0, null, false, null);
    }

    public Info retirarEstoque(RmMaterialRetirada rmMaterialRetirada,
                               br.com.nextage.persistence_2.dao.GenericDao genericDao,
                               boolean mobile, boolean atualizaStatus, double qtdRecebimento, Deposito deposito,
                               boolean verificaDeposito, Double quantidadeMaxDepSelecionado) {

        Info info;
        RmaService rmaService = new RmaService();
        MaterialDeposito mDep;
        MaterialDepositoSaidaDao matDepSaidaDao = new MaterialDepositoSaidaDao();
        MaterialDepositoSaida mDSaida;
        Date data = new Date();
        RmMaterialStatus rmMaterialStatus;
        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(null));

        try {
            if (rmMaterialRetirada.getNumeroProtocolo() == null || rmMaterialRetirada.getNumeroProtocolo() < 1) {
                rmMaterialRetirada.setNumeroProtocolo(rmMaterialRetiradaDao.numeroProtocoloMax() + 1);
            }

            RmMaterial rmMaterial = new RmMaterialService().selectByUnique(rmMaterialRetirada.getRmMaterial().getRmMaterialId());
            if (rmMaterial != null) {

                NxCriterion nxCriterion = NxCriterion.montaRestriction(new br.com.nextage.persistence_2.classes.Filtro(
                        MaterialDeposito.MATERIAL, rmMaterialRetirada.getRmMaterial().getMaterial(),
                        br.com.nextage.persistence_2.classes.Filtro.EQUAL));

                //Caso verifica Deposito igual a true, então o mesmo verifica o deposito da RmMaterial, se não, o deposito passado por parametro
                //Feito para não alterar erro no sincronização Mobile
                if (verificaDeposito == false) {
                    nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new br.com.nextage.persistence_2.classes.Filtro(
                            MaterialDeposito.DEPOSITO, rmMaterial.getDeposito(), br.com.nextage.persistence_2.classes.Filtro.EQUAL)));
                } else {
                    nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new br.com.nextage.persistence_2.classes.Filtro(
                            MaterialDeposito.DEPOSITO, deposito, br.com.nextage.persistence_2.classes.Filtro.EQUAL)));
                }

                //Seta as propriedades de retorno da consulta.
                String aliasMaterial = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL);
                String aliasDeposito = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.DEPOSITO);
                String alaisUnidadeMedida = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL, Material.UNIDADE_MEDIDA);

                List<br.com.nextage.persistence_2.classes.Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDeposito.QUANTIDADE));

                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.CODIGO, Material.class, aliasMaterial));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.NOME, Material.class, aliasMaterial));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.ESTOQUE_SAP, Material.class, aliasMaterial));

                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));

                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, alaisUnidadeMedida));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, alaisUnidadeMedida));

                //Obtem elemento.
                mDep = (MaterialDeposito) genericDao.selectUnique(propriedades, MaterialDeposito.class, nxCriterion);

                if (rmMaterial.getRm().getNumeroRm() != null) {
                    mDSaida = rmaService.listarMaterialDepositoSaidaByRmMaterial(rmMaterial, deposito);

                    boolean retTelaPainEst = false;
                    if (quantidadeMaxDepSelecionado == null) {
                        quantidadeMaxDepSelecionado = rmMaterialRetirada.getQuantidade() + qtdRecebimento;
                    } else {
                        //Se caso vier da tela de Paint de Estoquista, como é por item, o mesmo não pode finalizar o status, então,
                        //faço o retTelaPaintEst e não o atualizaStatus
                        retTelaPainEst = true;
                    }

                    if (mDSaida.getQuantidade() == (quantidadeMaxDepSelecionado)) {
                        propriedades.clear();

                        propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDepositoSaida.NUMERO_PROTOCOLO_SAIDA));

                        if (mDSaida.getRm() == null && mDSaida.getNumeroProtocoloSaida() < 1) {
                            mDSaida.setNumeroProtocoloSaida(matDepSaidaDao.numeroProtocoloSaidaMax());
                            if (mDSaida.getNumeroProtocoloSaida() == null) {
                                mDSaida.setNumeroProtocoloSaida(1);
                            } else {
                                mDSaida.setNumeroProtocoloSaida(mDSaida.getNumeroProtocoloSaida() + 1);
                            }
                        }
                        if (retTelaPainEst) {
                            if (rmMaterialRetirada.getPreRetirada() != null && rmMaterialRetirada.getPreRetirada() == false) {
                                mDSaida.setReserva(Boolean.FALSE);
                                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDepositoSaida.RESERVA));
                                rmMaterialStatus = rmMaterialStatusService.listarStatusAtual(rmMaterialRetirada.getRmMaterial());
                                rmMaterialStatusService.finalizarStatus(rmMaterialStatus, genericDao, data);
                                rmMaterialStatusService.gerarStatus(rmMaterialRetirada.getRmMaterial(), Constantes.STATUS_RM_MATERIAL_RETIRADO_PARCIALMENTE, genericDao, data, Util.setParamsLabel(rb.getString("label_retirado_x_materiais"), rmMaterialRetirada.getQuantidade(), rmMaterial.getMaterial().getUnidadeMedida().getSigla()), rmMaterialRetirada.getRetiradoPor().getNome());
                            }
                        } else if (atualizaStatus) {
                            mDSaida.setReserva(Boolean.FALSE);
                            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDepositoSaida.RESERVA));
                            rmMaterialStatus = rmMaterialStatusService.listarStatusAtual(rmMaterialRetirada.getRmMaterial());
                            rmMaterialStatusService.finalizarStatus(rmMaterialStatus, genericDao, data);
                            rmMaterialStatusService.gerarStatus(rmMaterialRetirada.getRmMaterial(), Constantes.STATUS_RM_MATERIAL_FINALIZADO, genericDao, data, Util.setParamsLabel(rb.getString("label_retirado_x_materiais"), rmMaterialRetirada.getQuantidade() + qtdRecebimento, rmMaterial.getMaterial().getUnidadeMedida().getSigla()), rmMaterialRetirada.getRetiradoPor().getNome());
                        }
                        genericDao.updateWithCurrentTransaction(mDSaida, propriedades);
                    } else {
                        MaterialDepositoSaida materialDepositoSaida = new MaterialDepositoSaida();
                        materialDepositoSaida.setDataSaida(mDSaida.getDataSaida());
                        materialDepositoSaida.setMaterialDeposito(mDep);
                        materialDepositoSaida.setMaterialDepositoSaidaReserva(mDSaida.getMaterialDepositoSaidaReserva());
                        materialDepositoSaida.setNumeroProtocoloSaida(mDSaida.getNumeroProtocoloSaida());
                        materialDepositoSaida.setObservacao(mDSaida.getObservacao());
                        materialDepositoSaida.setReserva(Boolean.TRUE);
                        materialDepositoSaida.setRetiradoPor(rmMaterialRetirada.getRetiradoPor());
                        materialDepositoSaida.setRm(rmMaterial.getRm());
                        materialDepositoSaida.setQuantidade(mDSaida.getQuantidade() - rmMaterialRetirada.getQuantidade());
                        materialDepositoSaida.setQuantidadeOriginal(mDSaida.getQuantidadeOriginal());

                        propriedades.clear();
                        propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDepositoSaida.RESERVA));
                        propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDepositoSaida.QUANTIDADE));
                        propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDepositoSaida.NUMERO_PROTOCOLO_SAIDA));

                        mDSaida.setQuantidade(rmMaterialRetirada.getQuantidade());
                        mDSaida.setReserva(Boolean.FALSE);

                        if (mDSaida.getRm() == null && mDSaida.getNumeroProtocoloSaida() < 1) {
                            mDSaida.setNumeroProtocoloSaida(matDepSaidaDao.numeroProtocoloSaidaMax());
                            if (mDSaida.getNumeroProtocoloSaida() == null) {
                                mDSaida.setNumeroProtocoloSaida(1);
                            } else {
                                mDSaida.setNumeroProtocoloSaida(mDSaida.getNumeroProtocoloSaida() + 1);
                            }
                        }

                        if (rmMaterialRetirada.getPreRetirada() != null && rmMaterialRetirada.getPreRetirada() == false) {
                            rmMaterialStatus = rmMaterialStatusService.listarStatusAtual(rmMaterialRetirada.getRmMaterial());
                            rmMaterialStatusService.finalizarStatus(rmMaterialStatus, genericDao, data);
                            rmMaterialStatusService.gerarStatus(rmMaterialRetirada.getRmMaterial(), Constantes.STATUS_RM_MATERIAL_RETIRADO_PARCIALMENTE, genericDao, data, Util.setParamsLabel(rb.getString("label_retirado_x_materiais"), rmMaterialRetirada.getQuantidade(), rmMaterial.getMaterial().getUnidadeMedida().getSigla()), rmMaterialRetirada.getRetiradoPor().getNome());
                        }
                        genericDao.persistWithCurrentTransaction(materialDepositoSaida);
                        genericDao.updateWithCurrentTransaction(mDSaida, propriedades);
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
                    propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDeposito.QUANTIDADE));
                    //Retira a Quantidade no Deposito
                    mDep.setQuantidade(mDep.getQuantidade() - rmMaterialRetirada.getQuantidade());
                    genericDao.updateWithCurrentTransaction(mDep, propriedades);
                }

                genericDao.persistWithCurrentTransaction(rmMaterialRetirada);


                info = new Info();
                //Se caso preRetirada for null ou false, então o mesmo faz o envio para o SAP
                //Feito o mesmo por problema ao autenticar com o leitor biometrico na retirada, enviava para o SAP
                //Tanto na tela, quanto na autenticação, deste jeito, só irá envia na autenticação biometrica.
                if (mobile == true || rmMaterialRetirada.getPreRetirada() == null || (rmMaterialRetirada.getPreRetirada() != null && !rmMaterialRetirada.getPreRetirada())) {
                    RetiradaMateriaisSapService retiradaMateriaisService = new RetiradaMateriaisSapService();
                    retiradaMateriaisService.enviarRetirada(mDSaida);
                    info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
                    info.setCodigo(Info.INFO_001);
                    if (mobile == false && !rmMaterialRetirada.getRetiradoPor().equals(rmMaterial.getRm().getRequisitante())) {
                        rmaService.enviarEmailRequisitanteRetiradaMaterial(rmMaterial.getRm(), rmMaterialRetirada.getRetiradoPor(), rmMaterial, rmMaterialRetirada.getQuantidade());
                    }
                } else {
                    info = Info.GetSuccess(Constantes.REGS_SALVOS_SUCESSO_I18N, rmMaterialRetirada);
                }

            } else {
                info = Info.GetError(Constantes.ERRO_OPERACAO_I18N);
                info.setObjeto(rmMaterialRetirada.getNumeroProtocolo());
            }

        } catch (Exception e) {
            info = Info.GetError(Constantes.ERRO_OPERACAO_I18N);
            e.printStackTrace();
        }
        return info;
    }

    public Info preRetirada(RmMaterialRetirada rmMaterialRetirada) {
        Info info;
        br.com.nextage.persistence_2.dao.GenericDao<RmMaterialRetirada> genericDao = new br.com.nextage.persistence_2.dao.GenericDao<>();
        try {
            genericDao.beginTransaction();
            info = preRetirada(rmMaterialRetirada, genericDao, true, false);
            genericDao.commitCurrentTransaction();
        } catch (Exception ex) {
            genericDao.rollbackCurrentTransaction();
            info = Info.GetError(ex.getMessage());
        }
        return info;
    }

    public Info preRetirada(RmMaterialRetirada rmMaterialRetirada, br.com.nextage.persistence_2.dao.GenericDao<RmMaterialRetirada> dao, boolean comBiometria, boolean semNumProtocolo) {
        Info info = new Info();

        try {
            if ((rmMaterialRetirada.getNumeroProtocolo() == null || rmMaterialRetirada.getNumeroProtocolo() < 1) && semNumProtocolo == false) {
                rmMaterialRetirada.setNumeroProtocolo(rmMaterialRetiradaDao.numeroProtocoloMax() + 1);
            }

            if (comBiometria) {
                rmMaterialRetirada.setPreRetirada(Boolean.TRUE);
            }
            dao.persistWithCurrentTransaction(rmMaterialRetirada);

            info.setMensagem("Pré retirada realizada com sucesso. Confirme a retirada com a biometria");
            info.setErro(null);
            info.setCodigo(Info.INFO_001);
            info.setObjeto(rmMaterialRetirada.getNumeroProtocolo());
        } catch (Exception e) {
            e.printStackTrace();
            info = Info.GetError(Constantes.ERRO_OPERACAO_I18N);
        }
        return info;
    }

    /**
     * Método responsável por gerar protocolo de retirada de materiais em
     * formato PDF.
     * <p/>
     * <b>Autor:</b> Leandro C. Orsi.
     * <b>Data:</b> 24-03-2011
     * <p/>
     *
     * @param request
     * @param outputStream - ServletOutputStream
     * @param nomeJasper   - String
     * @throws Exception - Exception
     */
    public void gerarProtocoloRetirada(HttpServletRequest request, ServletOutputStream outputStream, String nomeJasper) throws Exception {
        try {
            HashMap parameters = new HashMap();

            // Verifica o id da retirada.
            if (request.getParameter("numeroProtocolo") != null && !request.getParameter("numeroProtocolo").isEmpty()) {
                Integer protocolo = Integer.valueOf(request.getParameter("numeroProtocolo"));

                // Passa ID para o relatÃ³rio.
                parameters.put("NUMERO_PROTOCOLO", protocolo);

                // Caminho da imagem.
                parameters.put(Constantes.URL_IMAGES, Util.getFileConfig().getString("config.url_images"));

                // ObtÃ©m o caminhos dos subrelatÃ³rios.
                parameters.put(Constantes.SUBREPORT_DIR, Util.getFileConfig().getString("config.url_relatorios"));

                RelatorioFlexUtil.geraPdf(outputStream, nomeJasper, parameters);

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    /**
     * MÃ©todo que retorna a Ãºltima retirada do material.
     * <br>
     * <br>
     * <b>Autor:</b> Leandro C. Orsi
     * <b>Data:</b> 26/04/2011
     * <br>
     * <br>
     *
     * @param rmM - RmMaterial
     * @return objeto - RmMaterialRetirada
     * @throws Exception - Exception
     */
    public RmMaterialRetirada getLastRetirada(RmMaterial rmM) throws Exception {
        List<RmMaterialRetirada> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            filtros.add(new Filtro(RmMaterialRetirada.RM_MATERIAL, rmM, Filtro.EQUAL));

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialRetirada.RM_MATERIAL_RETIRADA_ID));
            propriedades.add(new Propriedade(RmMaterialRetirada.QUANTIDADE));
            propriedades.add(new Propriedade(RmMaterialRetirada.DATA_RETIRADA));
            propriedades.add(new Propriedade(RmMaterialRetirada.RM_MATERIAL));
            propriedades.add(new Propriedade(RmMaterialRetirada.AUTENTICACAO));

            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, RmMaterialRetirada.RETIRADO_POR));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, RmMaterialRetirada.RETIRADO_POR));

            //Obtem elementos.
            lista = (ArrayList<RmMaterialRetirada>) genericDao.listarByFilter(propriedades, filtros, RmMaterialRetirada.DATA_RETIRADA, true, RmMaterialRetirada.class, Constantes.NO_LIMIT);

            if (lista != null && lista.size() > 0) {
                return lista.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return null;
    }
}
