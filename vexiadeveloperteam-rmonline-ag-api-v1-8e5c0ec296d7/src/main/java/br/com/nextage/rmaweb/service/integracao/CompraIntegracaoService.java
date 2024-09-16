package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Configuracao;
import br.com.nextage.rmaweb.entitybean.Fornecedor;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.entitybean.MaterialDepositoSaidaReserva;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.entitybean.RmMaterialStatus;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.LogInterfaceVo;
import br.com.nextage.rmaweb.vo.integracao.CompraVo;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author nextage
 */
public class CompraIntegracaoService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    public List<LogInterfaceVo> salvar(String login, List<CompraVo> listaCompraVo) {
        Info info = new Info();
        GenericDao<RmMaterial> genericDao = new GenericDao<>();
        List<LogInterfaceVo> listaLog = new ArrayList<>();
        LogInterfaceVo logVo;
        List<Propriedade> propriedades = new ArrayList<>();
        List<Propriedade> propriedadesUpd = new ArrayList<>();
        List<Propriedade> propriedadesMaterial = new ArrayList<>();
        NxResourceBundle rb = new NxResourceBundle(null);
        Material material = null;
        Integer qtdeUpdt = 0;
        NxCriterion nxCriterion = null;
        List<RmMaterial> listaRmMaterial = null;
        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService();
        RequisicaoMaterialService requisicaoMaterialService = new RequisicaoMaterialService();

        Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();

        try {

            if (listaCompraVo != null && !listaCompraVo.isEmpty()) {

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

                propriedadesUpd.add(new Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA));
                propriedadesUpd.add(new Propriedade(RmMaterial.FORNECEDOR_ID));
                propriedadesUpd.add(new Propriedade(RmMaterial.DATA_COMPRA));
                propriedadesUpd.add(new Propriedade(RmMaterial.ITEM_DO_PEDIDO));
                propriedadesUpd.add(new Propriedade(RmMaterial.CENTRO));
                propriedadesUpd.add(new Propriedade(RmMaterial.STATUS));
                propriedadesUpd.add(new Propriedade(RmMaterial.VALOR_PEDIDO));
                propriedadesUpd.add(new Propriedade(RmMaterial.DATA_PREVISAO_CHEGADA));
                propriedadesUpd.add(new Propriedade(RmMaterial.DATA_PREVISTA_ENTREGA));
                propriedadesUpd.add(new Propriedade(RmMaterial.DATA_FINALIZACAO_SERVICO));
                propriedadesUpd.add(new Propriedade(RmMaterial.GRUPO_COMPRADOR));

                propriedadesMaterial.add(new Propriedade(Material.ULTIMO_VALOR_NEGOCIADO));

                FornecedorIntegracaoService fornecedorIntegracaoService = new FornecedorIntegracaoService();
                MaterialIntegracaoService materialIntegracaoService = new MaterialIntegracaoService();

                for (CompraVo compraVo : listaCompraVo) {

                    try {
                        //Atualiza Material
                        material = materialIntegracaoService.searchMaterial(compraVo.getMaterial(), compraVo.getCentro());

                        if (compraVo.getRmSap() != null && compraVo.getItemRmSap() != null) {

                            //Atualiza RmMaterial
                            nxCriterion = NxCriterion.montaRestriction(
                                    new Filtro(RmMaterial.ITEM_REQUISICAO_SAP,
                                            Integer.parseInt(compraVo.getItemRmSap()),
                                            Filtro.EQUAL));
                            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(
                                    new Filtro(RmMaterial.NUMERO_REQUISICAO_SAP, compraVo.getRmSap(), Filtro.EQUAL)));

                            listaRmMaterial = genericDao.listarByFilter(propriedades, null, RmMaterial.class, Constantes.NO_LIMIT, nxCriterion);

                            List<RmMaterialStatus> statusAtuais = new ArrayList<>();

                            for (RmMaterial rmMaterial : listaRmMaterial) {
                                RmMaterialStatus materialStatus = rmMaterialStatusService.listarStatusAtual(rmMaterial);
                                statusAtuais.add(materialStatus);
                            }

                            try {
                                genericDao.beginTransaction();

                                for (RmMaterial rmMaterial : listaRmMaterial) {

                                    List<Propriedade> propriedadesRmMaterial = new ArrayList<>();
                                    propriedadesRmMaterial.add(new Propriedade(RmMaterial.MATERIAL));
                                    propriedadesRmMaterial.add(new Propriedade(RmMaterial.COD_MATERIAL_ANTERIOR));

                                    //Se caso não encontrar o material, então crio um novo com as informações basicas.
                                    if (material == null && compraVo.getMaterial() != null && !compraVo.getMaterial().isEmpty()) {
                                        material = new Material();
                                        material.setCodigo(compraVo.getMaterial());
                                        material.setNome("Material cadastrado temporariamente: " + material.getCodigo());
                                        material.setTipoMaterial(rmMaterial.getMaterial().getTipoMaterial());
                                        genericDao.persistWithCurrentTransaction(material);

                                        rmMaterial.setCodMaterialAnterior(rmMaterial.getMaterial().getCodigo());
                                        rmMaterial.setMaterial(material);
                                        genericDao.updateWithCurrentTransaction(rmMaterial, propriedadesRmMaterial);
                                    } else if (material != null && !material.getCodigo().equals(rmMaterial.getMaterial().getCodigo())) {
                                        //Se caso o código for diferente, então o mesmo adiciona o novo código e coloca no campo
                                        //COD_MATERIAL_ANTERIOR o código do material antigo...
                                        rmMaterial.setCodMaterialAnterior(rmMaterial.getMaterial().getCodigo());
                                        rmMaterial.setMaterial(material);
                                        genericDao.updateWithCurrentTransaction(rmMaterial, propriedadesRmMaterial);
                                    }
                                }

                                if (material != null) {
                                    material.setUltimoValorNegociado(Double.valueOf(compraVo.getPrecoUnitario()));
                                    genericDao.updateWithCurrentTransaction(material, propriedadesMaterial);
                                }

                                Date date = new Date();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd hhmmss");

                                if (listaRmMaterial != null && !listaRmMaterial.isEmpty()) {
                                    for (final RmMaterial rmMaterial : listaRmMaterial) {
                                        //Apenas um auxiliar para verificar a dataCompra antes de realizar o set no mesmo, para o materialDepositoSaidaReserva
                                        Boolean possuiDataCompra = false;
                                        if (rmMaterial.getDataCompra() != null) {
                                            possuiDataCompra = true;
                                        }

                                        rmMaterial.setNumeroPedidoCompra(compraVo.getCodigoPedido());
                                        rmMaterial.setFornecedor(fornecedorIntegracaoService.searchFornecedor(compraVo.getFornecedor()));
                                        //Converter string para date

                                        if (!StringUtils.isEmpty(compraVo.getDataCompra())) {
                                            final Date dataCompraFormatada = sdf.parse(compraVo.getDataCompra());
                                            rmMaterial.setDataCompra(dataCompraFormatada);
                                        }

                                        if (!StringUtils.isEmpty(compraVo.getDataPrevisao())) {
                                            final Date dataFormatada = sdf.parse(compraVo.getDataPrevisao());
                                            rmMaterial.setDataPrevistaEntrega(dataFormatada);
                                        }

                                        if (!StringUtils.isEmpty(compraVo.getDataUltimaAtualizacao()) && !StringUtils.isEmpty(compraVo.getHoraUltimaAtualizacao())) {
                                            final Date dataFormatada = sdfDateTime.parse(compraVo.getDataUltimaAtualizacao().concat(" ").concat(compraVo.getHoraUltimaAtualizacao()));
                                            rmMaterial.setDataFinalizacaoServico(dataFormatada);
                                        }


                                        rmMaterial.setItemDoPedido(compraVo.getItemPedido());
                                        rmMaterial.setCentro(compraVo.getCentro());
                                        rmMaterial.setValorPedido(Double.valueOf(compraVo.getValorTotal()));
                                        rmMaterial.setStatus(compraVo.getStatus());
                                        rmMaterial.setGrupoComprador(compraVo.getGrupoComprador());


                                        qtdeUpdt = genericDao.updateWithCurrentTransaction(rmMaterial, propriedadesUpd);


                                        //Crio a saida reserva para aparecer no painel do estoquista
                                        //Se possuir data compra, então o mesmo não precisa realizar a criação de outro MaterialDepositoSaidaReserva
                                        //Pois o mesmo já vai existir
                                        if (possuiDataCompra == false) {
                                            MaterialDepositoSaidaReserva materialDepositoSaidaReserva = new MaterialDepositoSaidaReserva();
                                            materialDepositoSaidaReserva.setRmMaterial(rmMaterial);
                                            materialDepositoSaidaReserva.setQuantidadeSolicitada(rmMaterial.getQuantidade());
                                            materialDepositoSaidaReserva.setDataSaida(date);
                                            genericDao.persistWithCurrentTransaction(materialDepositoSaidaReserva);
                                        }

                                        // Atualiza o saldo dos materiais comprados
                                        requisicaoMaterialService.savePurchaseRequestIfNotExists(rmMaterial, compraVo, genericDao);

                                        //Atualizando ultimo status.
                                        RmMaterialStatus statusAtual = rmMaterialStatusService.listarStatusAtual(statusAtuais, rmMaterial);


                                        if (statusAtual.getStatus().getCodigo().equals(Constantes.STATUS_RM_MATERIAL_AGUARDANDO_COMPRA) && compraVo.isAprovadoSap()) {
                                            rmMaterialStatusService.finalizarStatus(statusAtual, genericDao, date);
                                            rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_COMPRADO_AGUARDANDO_RECEBIMENTO, genericDao, null, null, login);
                                        }
                                    }
                                }
                                genericDao.commitCurrentTransaction();
                            } catch (Exception e) {
                                genericDao.rollbackCurrentTransaction();
                                System.out.println("receber compra - Erro na execução item" + e.getMessage());
                                info.setCodigo(Info.INFO_002);
                                info.setErro(e.getMessage());
                                info.setTipo("E");
                                info.setMensagem(rb.getString("label_erro_ao_importar_registro"));
                                info.setObjeto(compraVo);
                                logVo = new LogInterfaceVo(info);
                                listaLog.add(logVo);
                            }
                        }
                            /*Se caso não tiver o código de RMA e o Item RM SAP preenchido, o mesmo entra no else,
                             atualizando o material, principalmente o precoUnitario.
                             */
                        else {
                            if(material != null) {
                                genericDao.beginTransaction();

                                material.setUltimoValorNegociado(Double.valueOf(compraVo.getPrecoUnitario()));
                                genericDao.updateWithCurrentTransaction(material, propriedadesMaterial);

                                genericDao.commitCurrentTransaction();
                            } else {
                                info.setCodigo(Info.INFO_002);
                                String erroMaterial = "Material não informado";
                                if(compraVo.getMaterial() != null && !compraVo.getMaterial().isEmpty()){
                                    erroMaterial = "Material não encontrado";
                                }
                                info.setErro(erroMaterial);
                                info.setTipo("E");
                                info.setMensagem(erroMaterial);
                                info.setObjeto(compraVo);
                                logVo = new LogInterfaceVo(info);
                                listaLog.add(logVo);
                            }
                            if (material != null && compraVo.getItemRmSap() == null) {
                                info.setCodigo(Info.INFO_002);
                                String erroItemRmSap = "Item Rm Sap não informado";
                                info.setErro(erroItemRmSap);
                                info.setTipo("E");
                                info.setMensagem(erroItemRmSap);
                                info.setObjeto(compraVo);
                                logVo = new LogInterfaceVo(info);
                                listaLog.add(logVo);
                            }
                        }
                    } catch (Exception e) {
                        genericDao.rollbackCurrentTransaction();
                        System.out.println("receber compra - Erro na execução item" + e.getMessage());
                        info.setCodigo(Info.INFO_002);
                        info.setErro(e.getMessage());
                        info.setTipo("E");
                        info.setMensagem(rb.getString("label_erro_ao_importar_registro"));
                        info.setObjeto(compraVo);
                        logVo = new LogInterfaceVo(info);
                        listaLog.add(logVo);
                    }
                }
            }

            if (info.getCodigo() == null) {
                System.out.println("receber compra - Sucesso");
                info.setTipo("S");
                info.setCodigo(Info.INFO_001);
                info.setMensagem(rb.getString(Constantes.REG_SALVO_SUCESSO_I18N));
                logVo = new LogInterfaceVo(info);
                listaLog.add(logVo);
            }
        } catch (Exception e) {
            System.out.println("receber compra - Erro metodo geral");
            info.setCodigo(Info.INFO_002);
            info.setErro(e.getMessage());
            info.setTipo("E");
            info.setMensagem(rb.getString("label_erro_ao_importar_registro"));
            logVo = new LogInterfaceVo(info);
            listaLog.add(logVo);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return listaLog;
    }
}
