/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.classes.NxCriterion;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.rmaweb.dao.RmMaterialRecebimentoDao;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.service.integracao.RmMaterialStatusService;
import br.com.nextage.rmaweb.service.integracao.RmaService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.Email2;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe que permite acesso Ã  persistencia
 *
 * @author Felipe
 */
public class RmMaterialRecebimentoService {

    private final GenericDao genericDao;
    private final RmMaterialRecebimentoDao rmMaterialRecebimentoDao;

    private static final Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    /**
     * Construtor
     */
    public RmMaterialRecebimentoService() {
        genericDao = new GenericDao();
        rmMaterialRecebimentoDao = new RmMaterialRecebimentoDao();
    }

    /**
     * Salva Rm Material Recebimento e dependendo do parametro envia email ou
     * nÃ£o, chama o metodo que salva no deposito.
     *
     * @param obj
     * @param qtdeInserida
     * @param enviarEmail
     * @return idObjeto
     * @throws Exception
     */
    public String salvar(RmMaterialRecebimento obj, Double qtdeInserida, Boolean enviarEmail, RmMaterial rmMaterial, String login) throws Exception {
        return salvar(obj, qtdeInserida, enviarEmail, rmMaterial, true, true, login);
    }

    public String salvar(RmMaterialRecebimento obj, Double qtdeInserida, Boolean enviarEmail, RmMaterial rmMaterial, boolean atualizaStatus, boolean recTelaPaiEsq, String login) throws Exception {
        String idObjeto = "0";
        RmaService rmaService = new RmaService();
        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(null));
        List<Propriedade> propriedades = new ArrayList<>();
        br.com.nextage.persistence_2.dao.GenericDao<RmMaterialStatus> genericDao2 = new br.com.nextage.persistence_2.dao.GenericDao<>();

        //Utilizado para, se caso for fluxo de compra, o mesmo da o aceite automatico do material
        MaterialDepositoSaidaReserva materialDepositoSaidaReservaFluxoCompra = null;

        try {

            if (qtdeInserida > 0) {

                Double qtdeExistente = 0.0;
                String uniMed = "";

                if (rmMaterial != null) {
                    qtdeExistente = getQuantidadeRecebimentos(rmMaterial);
                    if (rmMaterial.getMaterial() != null && rmMaterial.getMaterial().getUnidadeMedida() != null) {
                        uniMed = rmMaterial.getMaterial().getUnidadeMedida().getSigla() == null ? "" : rmMaterial.getMaterial().getUnidadeMedida().getSigla();
                        uniMed = uniMed.isEmpty() ? rmMaterial.getMaterial().getUnidadeMedida().getDescricao() : uniMed;
                    }
                }

                if (obj.getRmMaterialRecebimentoId() != null && obj.getRmMaterialRecebimentoId() > 0) {
                    idObjeto = rmMaterialRecebimentoDao.update(obj);
                } else {
                    idObjeto = rmMaterialRecebimentoDao.persist(obj);
                }
                salvarDeposito(obj, qtdeInserida);

                RmMaterialStatus statusAtual = rmMaterialStatusService.listarStatusAtual(obj.getRmMaterial());
                Boolean verificaQtdExataMaior;
                //Se caso o mesmo for null, então, não possui esse material deposito saida para o deposito solicitado, então, crio o mesmo se for null no if else abaixo :
                MaterialDepositoSaida materialDepositoSaida = rmaService.listarMaterialDepositoSaida(obj.getRmMaterial().getRm(), obj.getRmMaterial().getMaterial(), obj.getRmMaterial().getDeposito(), null);

                if (materialDepositoSaida != null
                        && (statusAtual.getStatus().getCodigo().equals(Constantes.STATUS_RM_MATERIAL_RECEBIDO_PARCIALMENTE)
                        || statusAtual.getStatus().getCodigo().equals(Constantes.STATUS_RM_MATERIAL_RETIRADO_PARCIALMENTE)
                        || (statusAtual.getStatus().getCodigo().equals(Constantes.STATUS_RM_MATERIAL_AGUARDANDO_RETIRADA)))) {

                    statusAtual.setObservacao(Util.setParamsLabel(rb.getString("label_recebido_x_materiais"), qtdeInserida, uniMed));
                    statusAtual.setDataHoraStatus(new Date());

                    atualizaStatus = false;

                    if (obj.getRmMaterial().getQuantidade() <= (qtdeInserida + materialDepositoSaida.getQuantidade())) {
                        propriedades.add(new Propriedade(MaterialDepositoSaida.QUANTIDADE));
                        propriedades.add(new Propriedade(MaterialDepositoSaida.QUANTIDADE_ORIGINAL));
                        materialDepositoSaida.setQuantidade(qtdeInserida + materialDepositoSaida.getQuantidade());
                        materialDepositoSaida.setQuantidadeOriginal(qtdeInserida + materialDepositoSaida.getQuantidade());
                        genericDao.update(materialDepositoSaida, propriedades);

                        propriedades.clear();
                        propriedades.add(new Propriedade(RmMaterialStatus.IS_HISTORICO));
                        propriedades.add(new Propriedade(RmMaterialStatus.OBSERVACAO));
                        statusAtual.setHistorico(Boolean.TRUE);
                        genericDao.update(statusAtual, propriedades);

                        verificaQtdExataMaior = true;
                        //br.com.nextage.persistence_2.dao.GenericDao<RmMaterialStatus> genericDao2 = new br.com.nextage.persistence_2.dao.GenericDao<>();
                        genericDao2.beginTransaction();
                        rmMaterialStatusService.finalizarStatus(statusAtual, genericDao2, new Date());
                        rmMaterialStatusService.gerarStatus(obj.getRmMaterial(), Constantes.STATUS_RM_MATERIAL_RECEBIDO, genericDao2, new Date(), Util.setParamsLabel(rb.getString("label_recebido_x_materiais"), qtdeInserida, uniMed), login);

                        atualizaStatus = true;

                        genericDao2.commitCurrentTransaction();
                    } else {
                        propriedades.add(new Propriedade(MaterialDepositoSaida.QUANTIDADE));
                        propriedades.add(new Propriedade(MaterialDepositoSaida.QUANTIDADE_ORIGINAL));
                        materialDepositoSaida.setQuantidade(qtdeInserida + materialDepositoSaida.getQuantidade());
                        genericDao.update(materialDepositoSaida, propriedades);

                        propriedades.clear();
                        propriedades.add(new Propriedade(RmMaterialStatus.OBSERVACAO));

                        atualizaStatus = false;

                        if (statusAtual.getStatus().getCodigo().equals(Constantes.STATUS_RM_MATERIAL_AGUARDANDO_RETIRADA)) {
                            propriedades.add(new Propriedade(RmMaterialStatus.STATUS_ID));
                            statusAtual.setObservacao(Util.setParamsLabel(rb.getString("label_recebido_x_materiais"), qtdeInserida, uniMed));
                            Status status = new Status();
                            status = selectStatus(Constantes.STATUS_RM_MATERIAL_RECEBIDO_PARCIALMENTE);
                            statusAtual.setStatus(status);
                        }

                        verificaQtdExataMaior = false;

                        genericDao.persist(statusAtual);
                    }
                } else {
                    //Atualizando ultimo status.
                    //br.com.nextage.persistence_2.dao.GenericDao<RmMaterialStatus> genericDao2 = new br.com.nextage.persistence_2.dao.GenericDao<>();
                    genericDao2.beginTransaction();
                    rmMaterialStatusService.finalizarStatus(statusAtual, genericDao2, new Date());
                    if (obj.getRmMaterial().getQuantidade().intValue() <= (qtdeInserida + qtdeExistente)) {
                        rmMaterialStatusService.gerarStatus(obj.getRmMaterial(), Constantes.STATUS_RM_MATERIAL_RECEBIDO, genericDao2, new Date(), Util.setParamsLabel(rb.getString("label_recebido_x_materiais"), qtdeInserida, uniMed), login);
                        verificaQtdExataMaior = true;
                    } else {
                        rmMaterialStatusService.gerarStatus(obj.getRmMaterial(), Constantes.STATUS_RM_MATERIAL_RECEBIDO_PARCIALMENTE, genericDao2, new Date(), Util.setParamsLabel(rb.getString("label_recebido_x_materiais"), qtdeInserida, uniMed), login);
                        verificaQtdExataMaior = false;
                        atualizaStatus = false;
                    }
                    genericDao2.commitCurrentTransaction();

                    List<MaterialDepositoSaidaReserva> materialDepositoSaidaReserva = getMaterialDepositoSaidaReserva(rmMaterial);
                    MaterialDepositoSaidaReserva matDepSaidaReserva = null;

                    if (materialDepositoSaidaReserva == null || materialDepositoSaidaReserva.isEmpty() && (rmMaterial.getColetorEstoque() == null || !rmMaterial.getColetorEstoque())) {
                        matDepSaidaReserva = new MaterialDepositoSaidaReserva();
                        matDepSaidaReserva.setRmMaterial(obj.getRmMaterial());
                        matDepSaidaReserva.setQuantidadeSolicitada(rmMaterial.getQuantidade());
                        matDepSaidaReserva.setDataSaida(new Date());
                        genericDao.persist(matDepSaidaReserva);
                    } else if (materialDepositoSaidaReserva != null && !materialDepositoSaidaReserva.isEmpty()) {
                        matDepSaidaReserva = materialDepositoSaidaReserva.get(0);
                        matDepSaidaReserva.setRmMaterial(rmMaterial);
                    }

                    //Somente cria reservar para itens que não são enviados apara o CPweb.
                    //[Marlos Morbis Novo] 24/05/2016
                    if (!rmaService.verificaEnvioParaCpweb(rmMaterial.getMaterial()) && (rmMaterial.getColetorEstoque() == null || !rmMaterial.getColetorEstoque())) {
                        //Gera uma saida do material com a flag "reserva" como true
                        MaterialDepositoSaida matDepositoSaida = new MaterialDepositoSaida();

                        matDepositoSaida.setDataSaida(new Date());
                        matDepositoSaida.setMaterialDeposito(rmaService.listarMaterialDeposito(obj.getRmMaterial(), obj.getRmMaterial().getDeposito()));
                        matDepositoSaida.setObservacao("Reserva");
                        matDepositoSaida.setRm(obj.getRmMaterial().getRm());
                        matDepositoSaida.setQuantidade(qtdeInserida);
                        matDepositoSaida.setQuantidadeOriginal(qtdeInserida);
                        matDepositoSaida.setReserva(Boolean.TRUE);
                        matDepositoSaida.setMaterialDepositoSaidaReserva(matDepSaidaReserva);

                        genericDao.persist(matDepositoSaida);
                    }
                }
                if (rmMaterial != null && rmMaterial.getFluxoMaterial() != null && rmMaterial.getFluxoMaterial().equals(Constantes.FLUXO_MATERIAL_COMPRA)) {


                    List<MaterialDepositoSaidaReserva> materialDepositoSaidaReserva = getMaterialDepositoSaidaReserva(rmMaterial);
                    MaterialDepositoSaidaReserva matDepSaidaReserva = null;

                    if (materialDepositoSaidaReserva == null || materialDepositoSaidaReserva.isEmpty() && (rmMaterial.getColetorEstoque() == null || !rmMaterial.getColetorEstoque())) {
                        matDepSaidaReserva = new MaterialDepositoSaidaReserva();
                        matDepSaidaReserva.setRmMaterial(obj.getRmMaterial());
                        matDepSaidaReserva.setQuantidadeSolicitada(rmMaterial.getQuantidade());
                        matDepSaidaReserva.setDataSaida(new Date());
                        genericDao.persist(matDepSaidaReserva);
                    } else if (materialDepositoSaidaReserva != null && !materialDepositoSaidaReserva.isEmpty()) {
                        matDepSaidaReserva = materialDepositoSaidaReserva.get(0);
                        matDepSaidaReserva.setRmMaterial(rmMaterial);
                    }


                    if (matDepSaidaReserva != null) {
                        materialDepositoSaidaReservaFluxoCompra = matDepSaidaReserva;
                        materialDepositoSaidaReservaFluxoCompra.getRmMaterial().setQtdRetirado(qtdeInserida);
                    }
                    Boolean verificaSaida = false;

                    if (rmMaterial.getColetorEstoque() != null && rmMaterial.getColetorEstoque() == true && verificaQtdExataMaior && atualizaStatus) {
                        //br.com.nextage.persistence_2.dao.GenericDao<RmMaterialStatus> genericDao2 = new br.com.nextage.persistence_2.dao.GenericDao<>();
                        RmMaterialStatus rmMaterialStatus = rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_FINALIZADO, null, new Date(), null, login);
                        if (rmMaterialStatus != null) {
                            genericDao2.persist(rmMaterialStatus);
                        }
                    } else {
                        rmaService.salvarAceiteMaterial(materialDepositoSaidaReservaFluxoCompra, verificaSaida, verificaQtdExataMaior, atualizaStatus, recTelaPaiEsq, login);
                    }
                    //Se passar verificaSaida como false, então o mesmo não faz o procedimento de adicionar mais um materialDepositoSaida
                    //A variavel verificaQtdExataMaior, se caso a quantidade do deposito, mais a inseria for maior ou igual a quantidade solicitada, então passa o mesmo como true;
                }

                if (enviarEmail) {
                    // Envia e-mail avisando requisitante do recebimento.
                    this.enviaEmail(new Integer(idObjeto), null);
                }

            }
        } catch (Exception e) {
            genericDao2.rollbackCurrentTransaction();
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    private List<MaterialDepositoSaidaReserva> getMaterialDepositoSaidaReserva(RmMaterial rmMaterial) {
        List<MaterialDepositoSaidaReserva> mdsr = null;
        try {
            // Seta as propriedades de retorno da consulta.
            List<br.com.nextage.persistence_2.classes.Propriedade> propriedades = new ArrayList<>();

            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDepositoSaidaReserva.ID));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDepositoSaidaReserva.DATA_AVALIACAO));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDepositoSaidaReserva.DATA_SAIDA));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDepositoSaidaReserva.ITEM_INDISPONIVEL));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDepositoSaidaReserva.OBSERVACAO_PAINEL_ESTOQUISTA));
            propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(MaterialDepositoSaidaReserva.QUANTIDADE_SOLICITADA));

            br.com.nextage.persistence_2.classes.NxCriterion crt = br.com.nextage.persistence_2.classes.NxCriterion.montaRestriction(new br.com.nextage.persistence_2.classes.Filtro(MaterialDepositoSaidaReserva.RM_MATERIAL, rmMaterial, br.com.nextage.persistence_2.classes.Filtro.EQUAL));

            br.com.nextage.persistence_2.dao.GenericDao<MaterialDepositoSaidaReserva> genericDaoMdsr = new br.com.nextage.persistence_2.dao.GenericDao<>();

            mdsr = genericDaoMdsr.listarByFilter(propriedades, null, MaterialDepositoSaidaReserva.class, 1, crt);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return mdsr;
    }

    public Double getQuantidadeRecebimentos(RmMaterial rmMaterial) {
        Double qtdeRec = 0.0;
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), RmMaterial.class.getName(), Util.getNomeMetodoAtual(), rmMaterial, null));
            RmMaterialService service = new RmMaterialService();

            //Recupera a quantidade Recebida
            List<RmMaterialRecebimento> listaRec = service.listaRmMaterialRecebimentoQtde(rmMaterial, null);
            for (RmMaterialRecebimento rmRec : listaRec) {
                qtdeRec += rmRec.getQuantidade();
            }
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return qtdeRec;
    }

    /**
     * Salva no MaterialDeposito ,faz consulta se ja existe altera somando a
     * quantidade com a quantida a ser inserida,senÃ£o adiciona um novo
     * registro, chama o mÃ©todo que salva o historico do deposito
     * (MaterialdepositoEntrada).
     *
     * @param obj
     * @param qtdeInserida
     * @return idObjeto
     * @throws Exception
     */
    public String salvarDeposito(RmMaterialRecebimento obj, Double qtdeInserida) throws Exception {
        String idObj = "0";
        List<Filtro> filtros = new ArrayList<>();
        try {
            Deposito deposito = null;
            if (obj != null && obj.getRmMaterial() != null) {

                if (obj.getRmMaterial().getRm() != null) {
                    deposito = obj.getRmMaterial().getRm().getDeposito();
                }
                if (deposito == null) {
                    deposito = obj.getRmMaterial().getDeposito();
                }
            }

            // Busca o recebimento para atualizar o estoque no deposito.
            filtros = new ArrayList<>();
            filtros.add(new Filtro(RmMaterialRecebimento.RM_MATERIAL_RECEBIMENTO_ID, obj.getRmMaterialRecebimentoId(), Filtro.EQUAL));

            obj = (RmMaterialRecebimento) genericDao.selectUnique(filtros, RmMaterialRecebimento.class);

            //Adiciona a quantidade no deposito
            GenericDao<MaterialDeposito> gdMaterialDeposito = new GenericDao<>();
            filtros = new ArrayList<>();
            MaterialDeposito matDep = new MaterialDeposito();

            filtros.add(new Filtro(MaterialDeposito.MATERIAL, obj.getRmMaterial().getMaterial(), Filtro.EQUAL));
            filtros.add(new Filtro(MaterialDeposito.DEPOSITO, deposito, Filtro.EQUAL));

            matDep = (MaterialDeposito) gdMaterialDeposito.selectUnique(filtros, MaterialDeposito.class);

            if (matDep == null) {
                matDep = new MaterialDeposito(-1);
                matDep.setMaterial(obj.getRmMaterial().getMaterial());
                matDep.setDeposito(deposito);
                matDep.setQuantidade(qtdeInserida);
            } else {
                matDep.setQuantidade(matDep.getQuantidade() + qtdeInserida);
            }

            //Salva o material no deposito
            MaterialDepositoService mDS = new MaterialDepositoService();
            //cria o Objeto MaterialDepositoEntrada (historico de entrada de material)
            MaterialDepositoEntrada objMDE = new MaterialDepositoEntrada();
            objMDE.setDataEntrada(new Date());
            objMDE.setObservacao(Constantes.ENTRADA_DE_MATERIAL_POR_RM);
            objMDE.setQuantidade(qtdeInserida);
            objMDE.setRm(obj.getRmMaterial().getRm());
            objMDE.setMaterialDepositoEntradaId(-1);
            //Salva os objetos e envia a entrada para o CPWEB
            mDS.salvarEntrada(matDep, objMDE, true);

            //Antes enviava usando o metodo abaixo, porém, o mesmo enviava para o CP no seguinte metodo acima de salvarEntrada, deixei apenas no salvarEntrada, se não o mesmo duplicava no CPWEB
            //SincEquipamentoService sincEquipamentoService = new SincEquipamentoService();
            //sincEquipamentoService.enviaMaterial(objMDE);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return idObj;
    }

    /**
     * @param rmMaterialRecebimento
     * @return
     * @throws Exception
     */
    public boolean excluir(RmMaterialRecebimento rmMaterialRecebimento) throws Exception {
        Boolean result = false;
        try {
            result = genericDao.delete(rmMaterialRecebimento);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return result;
    }

    /**
     * Lista o DataGrid de RmMaterialRecebimento
     * <p/>
     * Modificacao l.pordeus 13-10-2015 Alterado para persitence 2, devido a
     * erro na consulta no 3Âº nivel.
     * <p/>
     * <p/>
     * <b>Autor:</b> Juliano A. Felipe
     * <b>Data:</b> 22-03-2011
     * <p/>
     *
     * @param rmMaterial
     * @return
     * @throws java.lang.Exception
     */
    public List<RmMaterialRecebimento> listaDataGrid(RmMaterial rmMaterial) throws Exception {

        List<RmMaterialRecebimento> lista = new ArrayList<>();

        try {
            if (rmMaterial != null) {
                String aliasRmMaterial = br.com.nextage.persistence_2.classes.NxCriterion.montaAlias(RmMaterialRecebimento.ALIAS_CLASSE, RmMaterialRecebimento.RM_MATERIAL);
                String aliasRm = br.com.nextage.persistence_2.classes.NxCriterion.montaAlias(RmMaterialRecebimento.ALIAS_CLASSE, RmMaterialRecebimento.RM_MATERIAL, RmMaterial.RM);
                String aliasMaterial = br.com.nextage.persistence_2.classes.NxCriterion.montaAlias(RmMaterialRecebimento.ALIAS_CLASSE, RmMaterialRecebimento.RM_MATERIAL, RmMaterial.MATERIAL);
                String aliasComprador = br.com.nextage.persistence_2.classes.NxCriterion.montaAlias(RmMaterialRecebimento.ALIAS_CLASSE, RmMaterialRecebimento.RM_MATERIAL, RmMaterial.RM, Rm.COMPRADOR);
                String aliasRequisitante = br.com.nextage.persistence_2.classes.NxCriterion.montaAlias(RmMaterialRecebimento.ALIAS_CLASSE, RmMaterialRecebimento.RM_MATERIAL, RmMaterial.RM, Rm.REQUISITANTE);

                br.com.nextage.persistence_2.classes.NxCriterion nxCriterion = br.com.nextage.persistence_2.classes.NxCriterion.montaRestriction(new br.com.nextage.persistence_2.classes.Filtro(RmMaterialRecebimento.RM_MATERIAL, rmMaterial, Filtro.EQUAL));

                // Seta as propriedades de retorno da consulta.
                List<br.com.nextage.persistence_2.classes.Propriedade> propriedades = new ArrayList<>();

                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterialRecebimento.RM_MATERIAL_RECEBIMENTO_ID));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterialRecebimento.QUANTIDADE));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterialRecebimento.NUMERO_NOTA_FISCAL));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterialRecebimento.DATA_EMISSAO_NF));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterialRecebimento.DATA_RECEBIMENTO_MATERIAL));

                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));

                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Material.NOME, Material.class, aliasMaterial));

                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.RM_ID, Rm.class, aliasRm));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Rm.DATA_APLICACAO, Rm.class, aliasRm));

                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRequisitante));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Pessoa.NOME, Pessoa.class, aliasRequisitante));

                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Comprador.COMPRADOR_ID, Comprador.class, aliasComprador));
                propriedades.add(new br.com.nextage.persistence_2.classes.Propriedade(Comprador.NOME, Comprador.class, aliasComprador));

                //Obtem elementos.
                br.com.nextage.persistence_2.dao.GenericDao<RmMaterialRecebimento> gDao = new br.com.nextage.persistence_2.dao.GenericDao<>();
                lista = gDao.listarByFilter(propriedades, null, RmMaterialRecebimento.class, Constantes.NO_LIMIT, nxCriterion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * MÃ©todo que preenche uma combo de Materiais com base no numero da
     * rmMaterial. Exibe somente os itens comprados.
     * <br>
     * <br>
     * <b>Autor:</b> Jerry.
     * <b>Data:</b> 24/03/2011
     * <br>
     * <br>
     *
     * @param numeroRm
     * @return
     * @throws Exception - Exception
     */
    public List<RmMaterial> getComboByNumeroRm(String numeroRm) throws Exception {
        List<RmMaterial> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            filtros.add(new Filtro(Rm.NUMERO_RM, numeroRm, Filtro.EQUAL, Rm.ALIAS_CLASSE));
            filtros.add(new Filtro(RmMaterial.DATA_COMPRA, null, Filtro.NOT_NULL));

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));
            //propriedades.add(new Propriedade(RmMaterial.));

            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));

            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, Material.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Material.NOME, Material.class, Material.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Material.UNIDADE_MEDIDA, Material.class, Material.ALIAS_CLASSE_UNDERLINE));

            //Obtem elementos.
            String orderBy = Rm.ALIAS_CLASSE + "." + Rm.NUMERO_RM;
            lista = (ArrayList<RmMaterial>) genericDao.listarByFilter(propriedades, filtros, orderBy, false, RmMaterial.class, Constantes.NO_LIMIT);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * MÃ©todo que preenche um combo de Materiais com base no numero do pedido
     * informado. Exibe somente os itens comprados.
     * <br>
     * <br>
     * <b>Autor:</b> Leandro C. Orsi.
     * <b>Data:</b> 31/03/2011
     * <br>
     * <br>
     *
     * @param numeroPedido
     * @return
     * @throws Exception - Exception
     */
    public List<RmMaterial> getComboByNumeroPedido(String numeroPedido) throws Exception {
        List<RmMaterial> lista = new ArrayList<>();
        List<RmMaterial> listaAux = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            filtros.add(new Filtro(RmMaterial.NUMERO_PEDIDO_COMPRA, numeroPedido, Filtro.EQUAL));
            filtros.add(new Filtro(RmMaterial.DATA_COMPRA, null, Filtro.NOT_NULL));

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA));

            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, Rm.ALIAS_CLASSE_UNDERLINE));

            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, Material.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Material.NOME, Material.class, Material.ALIAS_CLASSE_UNDERLINE));
            propriedades.add(new Propriedade(Material.UNIDADE_MEDIDA, Material.class, Material.ALIAS_CLASSE_UNDERLINE));

            //Obtem elementos.
            String orderBy = Rm.ALIAS_CLASSE + "." + Rm.NUMERO_RM;
            lista = (ArrayList<RmMaterial>) genericDao.listarByFilter(propriedades, filtros, orderBy, false, RmMaterial.class, -1);

            RmMaterialService service = new RmMaterialService();
            List<RmMaterialRecebimento> listaRec;
            Double qtdeRec = 0.0;
            for (RmMaterial rmM : lista) {
                qtdeRec = 0.0;
                //Recupera a quantidade Recebida
                listaRec = service.listaRmMaterialRecebimentoQtde(rmM, null);
                for (RmMaterialRecebimento rmRec : listaRec) {
                    qtdeRec = qtdeRec + rmRec.getQuantidade();
                }
                if (rmM.getQuantidade() - qtdeRec > 0) {
                    rmM.setQtdRecebido(qtdeRec);
                    listaAux.add(rmM);
                }
            }

            lista = listaAux;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * @param rmMaterialId - Integer <i>null</i>.
     * @return <b>lista</b> - Retorna uma lista com os recebimentos do material.
     * <br/>
     * <b>null</b> - Em caso de falha.
     * @author Leandro C. Orsi
     * <p/>
     * Lista todos os recebimentos de um MATERIAL de uma RM.
     */
    public List<RmMaterialRecebimento> listarRecebimentos(Integer rmMaterialId) {
        List<RmMaterialRecebimento> lista = new ArrayList<>();
        List<Propriedade> prop = new ArrayList<>();
        List<Filtro> filtros = new ArrayList<>();

        try {
            // Filtros.
            filtros.add(new Filtro(RmMaterial.RM_MATERIAL_ID, rmMaterialId, Filtro.EQUAL, RmMaterial.ALIAS_CLASSE));

            prop.add(new Propriedade(RmMaterialRecebimento.RM_MATERIAL_RECEBIMENTO_ID));
            prop.add(new Propriedade(RmMaterialRecebimento.DATA_RECEBIMENTO_MATERIAL));
            prop.add(new Propriedade(RmMaterialRecebimento.NUMERO_NOTA_FISCAL));
            prop.add(new Propriedade(RmMaterialRecebimento.DATA_EMISSAO_NF));
            prop.add(new Propriedade(RmMaterialRecebimento.QUANTIDADE));

            prop.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, RmMaterial.ALIAS_CLASSE_UNDERLINE));

            lista = genericDao.listarByFilter(prop, filtros, RmMaterialRecebimento.DATA_RECEBIMENTO_MATERIAL, false, RmMaterialRecebimento.class, Constantes.NO_LIMIT);

        } catch (Exception e) {
            lista = null;
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Envia um email para o Requisitante, avisando que o material solicitado
     * estÃ¡ disponnivel em estoque
     *
     * @param id
     * @return
     */
    public String enviaEmail(Integer id, Deposito depositoRmMaterial) {

        StringBuilder tituloEmail = new StringBuilder();
        StringBuilder mensagemEmail = new StringBuilder();

        try {
            if (id != null && id > 0) {

                /*
                 * Consulta para retornar os campos necessÃ¡rios para construir a mensagem
                 * de email
                 */
                List<Filtro> filtros = new ArrayList<>();
                List<Propriedade> prop = new ArrayList<>();
                RmMaterialRecebimento rmr;

                filtros.add(new Filtro(RmMaterialRecebimento.RM_MATERIAL_RECEBIMENTO_ID, id, Filtro.EQUAL));

                prop.add(new Propriedade(RmMaterialRecebimento.RM_MATERIAL_RECEBIMENTO_ID));
                prop.add(new Propriedade(RmMaterialRecebimento.QUANTIDADE));

                prop.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
                prop.add(new Propriedade(RmMaterial.RM));
                prop.add(new Propriedade(RmMaterial.MATERIAL));
                prop.add(new Propriedade(RmMaterial.DEPOSITO_ID));

                rmr = (RmMaterialRecebimento) genericDao.selectUnique(filtros, RmMaterialRecebimento.class);

                /**
                 * ***********************************************************
                 */
                //          Envia o email.
                /**
                 * ***********************************************************
                 */
                if (rmr != null && rmr.getRmMaterial() != null && rmr.getRmMaterial().getRm() != null
                        && rmr.getRmMaterial().getRm().getRequisitante() != null
                        && rmr.getRmMaterial().getRm().getRequisitante().getEmail() != null
                        && !rmr.getRmMaterial().getRm().getRequisitante().getEmail().isEmpty()) {

                    /**
                     * ***********************************************************
                     */
                /*		Monta a mensagem do Email		      */
                    /**
                     * ***********************************************************
                     */
                    Deposito deposito = null;
//                    if (rmr.getRmMaterial().getRm() != null) {
//                        deposito = rmr.getRmMaterial().getRm().getDeposito();
//                    }
                    if (depositoRmMaterial != null) {
                        deposito = depositoRmMaterial;
                    } else {
                        if (deposito == null) {
                            if (rmr.getRmMaterial().getDeposito() != null) {
                                deposito = rmr.getRmMaterial().getDeposito();
                            }
                        }
                    }
                    String nomeRequisitante = null;
                    if (rmr.getRmMaterial().getRm() != null && rmr.getRmMaterial().getRm().getRequisitante() != null
                            && rmr.getRmMaterial().getRm().getRequisitante().getNome() != null) {
                        nomeRequisitante = rmr.getRmMaterial().getRm().getRequisitante().getNome();
                    }

                    tituloEmail.append("[RMAWEB] - Recebimento de Material");

                    if (nomeRequisitante != null) {
                        mensagemEmail.append("Olá ");
                        mensagemEmail.append(nomeRequisitante);
                        mensagemEmail.append(",");
                    } else {
                        mensagemEmail.append("Olá,");
                    }
                    mensagemEmail.append("\n");
                    mensagemEmail.append("\n");

                    mensagemEmail.append("Material disponível: ");
                    mensagemEmail.append("\n");
                    mensagemEmail.append("\n");
                    mensagemEmail.append("Material: ");
                    mensagemEmail.append(rmr.getRmMaterial().getMaterial().getNome());
                    mensagemEmail.append("\n");
                    mensagemEmail.append("Quantidade: ");
                    mensagemEmail.append(rmr.getQuantidade());
                    mensagemEmail.append("\n");
                    mensagemEmail.append("Nº RM: ");
                    mensagemEmail.append(rmr.getRmMaterial().getRm().getNumeroRm());
                    mensagemEmail.append("\n");
                    if (deposito != null) {
                        mensagemEmail.append("Depósito: ");
                        mensagemEmail.append(deposito.getNome());
                        mensagemEmail.append("\n");
                        mensagemEmail.append("Endereço do Depósito: ");
                        mensagemEmail.append(deposito.getEndereco());
                        mensagemEmail.append(".\n");
                    }
                    mensagemEmail.append("\n");
                    mensagemEmail.append("--------------------------------------------------------------------------- \n");
                    mensagemEmail.append("Este e-mail é gerado automaticamente pelo sistema. Favor Não responder.");

                    //Producao
                    //Email.enviaEmail(rmr.getRmMaterial().getRm().getRequisitante().getEmail(), tituloEmail.toString(), mensagemEmail.toString());
                    //Desenv
                    //Email.enviaEmailAutenticadoSSL(rmr.getRmMaterial().getRm().getRequisitante().getEmail(), tituloEmail.toString(), mensagemEmail.toString());
                    prop = new ArrayList<>();
                    NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Configuracao.CONFIGURACAO_ID, new Integer(1), Filtro.EQUAL));
                    prop.add(new Propriedade(Configuracao.CONFIGURACAO_ID));
                    prop.add(new Propriedade(Configuracao.EMAIL_CHAVE));
                    prop.add(new Propriedade(Configuracao.EMAIL_HOST));
                    prop.add(new Propriedade(Configuracao.EMAIL_ORIGEM));
                    prop.add(new Propriedade(Configuracao.EMAIL_PASSWD));
                    prop.add(new Propriedade(Configuracao.EMAIL_PORTA));
                    prop.add(new Propriedade(Configuracao.EMAIL_USER));
                    prop.add(new Propriedade(Configuracao.EMPRESA));
                    GenericDao<Configuracao> confDao = new GenericDao<>();
                    Configuracao confi = (Configuracao) confDao.selectUnique(prop, Configuracao.class, nxCriterion);

                    Email2.enviaEmail(rmr.getRmMaterial().getRm().getRequisitante().getEmail().trim(), tituloEmail.toString(), mensagemEmail.toString(),
                            null, confi.getEmailHost(),
                            confi.getEmailPorta(), confi.getEmailOrigem(), confi.getEmailUser(), confi.getEmailPasswd(),
                            confi.getEmailChave());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Constantes.FALHA;
        }
        return Constantes.SUCESSO;
    }

    /**
     * Lista o RmDeposito de RmMaterial
     * <p/>
     * <p/>
     * <b>Autor:</b> Leonardo Faix Pordeus
     * <b>Data:</b> 08-10-2015
     * <p/>
     *
     * @param rmMaterial
     * @return
     */
    public List<RmDeposito> listaRmDeposito(RmMaterial rmMaterial) {

        List<RmDeposito> lista = new ArrayList<>();

        try {
            if (rmMaterial != null) {
                RmDepositoService rmDepositoService = new RmDepositoService();
                lista = rmDepositoService.listarDepositosByRm(rmMaterial.getRm());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Lista o status desejado
     *
     * @return
     */
    private Status selectStatus(String codigoStatus) {
        Status status = new Status();
        try {

            GenericDao<Status> genericDao = new GenericDao<>();

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Status.ID));
            propriedades.add(new Propriedade(Status.CODIGO));
            propriedades.add(new Propriedade(Status.NOME));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Status.CODIGO, codigoStatus, Filtro.EQUAL));

            status = (Status) genericDao.selectUnique(propriedades, Status.class, nxCriterion);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return status;
    }
}
