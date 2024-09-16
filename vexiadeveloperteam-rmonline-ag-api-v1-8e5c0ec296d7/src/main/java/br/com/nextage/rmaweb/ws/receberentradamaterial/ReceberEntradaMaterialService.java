package br.com.nextage.rmaweb.ws.receberentradamaterial;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Fornecedor;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.entitybean.RmMaterialStatus;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.integracao.RequisicaoMaterialService;
import br.com.nextage.rmaweb.service.integracao.RmMaterialStatusService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.LogInterfaceVo;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author nextage
 */
public class ReceberEntradaMaterialService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    private List<LogInterfaceVo> listaLog;

    public ReceberEntradaMaterialService(){
        this.listaLog = new LinkedList<>();
    }

    public List<LogInterfaceVo> salvar(String login, List<ReceberEntradaMaterialRequest> materiais) {

        GenericDao<RmMaterial> genericDao = new GenericDao<>();
        NxResourceBundle rb = new NxResourceBundle(null);

        Info info = new Info();

        try {

            if (materiais != null && !materiais.isEmpty()) {

                List<Propriedade> propriedades = criarListaDeAlias();

                NxCriterion nxCriterion = null;
                List<RmMaterial> listaRmMaterial = null;
                RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService();
                RequisicaoMaterialService requisicaoMaterialService = new RequisicaoMaterialService();

                for (ReceberEntradaMaterialRequest entradaMaterialRequest : materiais) {

                    if (isMaterialValido(entradaMaterialRequest)) {

                        String aliasMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL);

                            nxCriterion = NxCriterion.montaRestriction(
                              new Filtro(RmMaterial.ITEM_REQUISICAO_SAP,
                                Integer.parseInt(entradaMaterialRequest.getItemRmSap()),
                                Filtro.EQUAL));
                            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(
                              new Filtro(RmMaterial.NUMERO_REQUISICAO_SAP, entradaMaterialRequest.getRmSap(), Filtro.EQUAL)));

                            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(
                              new Filtro(Material.CODIGO, entradaMaterialRequest.getCodigoMaterial(), Filtro.EQUAL, aliasMaterial)));


                            listaRmMaterial = genericDao.listarByFilter(propriedades, null, RmMaterial.class, Constantes.NO_LIMIT, nxCriterion);

                            try {
                                genericDao.beginTransaction();

                                Date date = new Date();

                                if (listaRmMaterial != null && !listaRmMaterial.isEmpty()) {

                                    for (final RmMaterial rmMaterial : listaRmMaterial) {
                                        RmMaterialStatus statusAtual = rmMaterialStatusService.listarStatusAtual(rmMaterial);
                                        rmMaterial.setNumeroDocEntrada(entradaMaterialRequest.getDocumentoEntrada());

                                        // Atualiza o saldo dos materiais recebidos
                                        requisicaoMaterialService.saveMaterialReceivedIfNotExists(rmMaterial, entradaMaterialRequest, genericDao);

                                        if (statusAtual != null && statusAtual.getStatus().getCodigo()
                                                .equals(Constantes.STATUS_RM_MATERIAL_COMPRADO_AGUARDANDO_RECEBIMENTO)) {

                                            boolean atualizou = atualizarDocumentoEntradaRmMaterial(rmMaterial, genericDao);

                                            if(atualizou) {
                                                rmMaterialStatusService.finalizarStatus(statusAtual, genericDao, date);
                                                rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_AGUARDANDO_RETIRADA, genericDao, null, null, login);
                                            }
                                        }



                                    }
                                }
                                genericDao.commitCurrentTransaction();
                            } catch (Exception e) {
                                genericDao.rollbackCurrentTransaction();
                                logger.error("receber entrada material - Erro na execução item" + e.getMessage());
                                info.setCodigo(Info.INFO_002);
                                info.setErro(e.getMessage());
                                info.setTipo("E");
                                info.setMensagem(rb.getString("label_erro_ao_importar_registro"));
                                info.setObjeto(entradaMaterialRequest);
                                LogInterfaceVo logVo = new LogInterfaceVo(info);
                                listaLog.add(logVo);
                            }


                    }
                }
            } else {
                listaLog.add(criarInfoLog("Nenhum material foi informado.", null));
            }

            info = new Info();
            logger.info("receber entrada material - Sucesso");
            info.setTipo("S");
            info.setCodigo(Info.INFO_001);
            info.setMensagem(rb.getString(Constantes.REG_SALVO_SUCESSO_I18N));
            LogInterfaceVo logVo = new LogInterfaceVo(info);
            listaLog.add(logVo);

        } catch (Exception e) {
            logger.error("receber entrada material - Erro metodo geral");
            info.setCodigo(Info.INFO_002);
            info.setErro(e.getMessage());
            info.setTipo("E");
            info.setMensagem(rb.getString("label_erro_ao_importar_registro"));
            LogInterfaceVo logVo = new LogInterfaceVo(info);
            listaLog.add(logVo);
            logger.error(ResourceLogUtil
                    .createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return listaLog;
    }

    private boolean atualizarDocumentoEntradaRmMaterial(RmMaterial rmMaterial, GenericDao<?> genericDao) throws Exception {
        List<Propriedade> propriedadesUpd = new ArrayList<>();
        propriedadesUpd.add(new Propriedade(RmMaterial.NUMERO_DOC_ENTRADA));

        final Integer qtdeUpdt = genericDao.updateWithCurrentTransaction(rmMaterial, propriedadesUpd);

        return qtdeUpdt > 0;
    }

    private List<Propriedade> criarListaDeAlias() {

        List<Propriedade> propriedades = new LinkedList<>();

        String aliasMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL);
        String aliasRm = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);
        String aliasFornecedor = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.FORNECEDOR_ID);

        propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
        propriedades.add(new Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA));
        propriedades.add(new Propriedade(RmMaterial.DATA_COMPRA));
        propriedades.add(new Propriedade(RmMaterial.ITEM_DO_PEDIDO));
        propriedades.add(new Propriedade(RmMaterial.CENTRO));
        propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));
        propriedades.add(new Propriedade(RmMaterial.STATUS));
        propriedades.add(new Propriedade(RmMaterial.VALOR_PEDIDO));
        propriedades.add(new Propriedade(RmMaterial.ORDEM));

        propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
        propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
        propriedades.add(new Propriedade(Material.TIPO_MATERIAL, Material.class, aliasMaterial));

        propriedades.add(new Propriedade(Fornecedor.FORNECEDOR_ID, Fornecedor.class, aliasFornecedor));
        propriedades.add(new Propriedade(Fornecedor.NOME, Fornecedor.class, aliasFornecedor));
        propriedades.add(new Propriedade(Fornecedor.CODIGO, Fornecedor.class, aliasFornecedor));

        propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
        propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));

        propriedades.add(new Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA));
        propriedades.add(new Propriedade(RmMaterial.FORNECEDOR_ID));
        propriedades.add(new Propriedade(RmMaterial.DATA_COMPRA));
        propriedades.add(new Propriedade(RmMaterial.ITEM_DO_PEDIDO));
        propriedades.add(new Propriedade(RmMaterial.CENTRO));
        propriedades.add(new Propriedade(RmMaterial.STATUS));
        propriedades.add(new Propriedade(RmMaterial.VALOR_PEDIDO));
        propriedades.add(new Propriedade(RmMaterial.DATA_PREVISAO_CHEGADA));
        propriedades.add(new Propriedade(RmMaterial.DATA_PREVISTA_ENTREGA));
        propriedades.add(new Propriedade(RmMaterial.DATA_FINALIZACAO_SERVICO));


        propriedades.add(new Propriedade(Material.ULTIMO_VALOR_NEGOCIADO, Material.class, aliasMaterial));

        return propriedades;
    }

    private boolean isMaterialValido(ReceberEntradaMaterialRequest entradaMaterialRequest) {

        boolean requestValido = false;

        if (entradaMaterialRequest == null) {
            listaLog.add(criarInfoLog("Material não informado", entradaMaterialRequest));
            return false;
        } else {
            if (StringUtils.isEmpty(entradaMaterialRequest.getItemRmSap())) {
                listaLog.add(criarInfoLog("Item Rm Sap não informado", entradaMaterialRequest));
                return false;
            } else if (StringUtils.isEmpty(entradaMaterialRequest.getRmSap())) {
                listaLog.add(criarInfoLog("Rm Sap não informado", entradaMaterialRequest));
                return false;
            } else if (StringUtils.isEmpty(entradaMaterialRequest.getDocumentoEntrada())) {
                listaLog.add(criarInfoLog("Documento Entrada não informado", entradaMaterialRequest));
                return false;
            } else if (StringUtils.isEmpty(entradaMaterialRequest.getCodigoMaterial())) {
                listaLog.add(criarInfoLog("Código Material não informado", entradaMaterialRequest));
                return false;
            } else if (Objects.isNull(entradaMaterialRequest.getQuantidade())) {
                listaLog.add(criarInfoLog("Quantidade não informada", entradaMaterialRequest));
                return false;
            }
        }

        return true;
    }

    private LogInterfaceVo criarInfoLog(final String message, final ReceberEntradaMaterialRequest entradaMaterialRequest) {
        Info info = new Info();
        info.setCodigo(Info.INFO_002);
        info.setErro(message);
        info.setTipo("E");
        info.setMensagem(message);

        if(entradaMaterialRequest != null) {
            info.setObjeto(entradaMaterialRequest);
        }
        return new LogInterfaceVo(info);
    }
}
