package br.com.nextage.rmaweb.ws.sap;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.service.ConfIntegracaoServiceImpl;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.integracao.LogInterfaceService;
import br.com.nextage.rmaweb.service.integracao.RmaService;
import br.com.nextage.rmaweb.service.integracao.SincRegistroService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.CompraMateriaisSapVo;
import br.com.nextage.rmaweb.vo.LogInterfaceVo;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel H. Parisotto
 */
public class CompraMateriaisSapService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    /**
     * Utilizado pelo listener que tenta enviar novamente as compras que deram
     * erro em uma outra tentativa
     */
    public void sincronizar() {
        SincRegistroService sincRegistroService = new SincRegistroService();
        List<SincRegistro> listaRegistros = sincRegistroService.listar(Constantes.CODIGO_INTEGRACAO_COMPRA_MATERIAL);
        List<SincRegistro> listaRegistrosDesativar = new ArrayList<>();
        RmaService rmaService = new RmaService(null);

        for (SincRegistro sincRegistro : listaRegistros) {
            if (sincRegistro.getRmMaterialConcatena() != null && !sincRegistro.getRmMaterialConcatena().trim().isEmpty()) {
                String[] idsSt = sincRegistro.getRmMaterialConcatena().split(";");
                List<Integer> idsRmMaterial = new ArrayList<>();
                for (int i = 0; i < idsSt.length; i++) {
                    if (idsSt[i] != null && !idsSt[i].trim().isEmpty()) {
                        try {
                            idsRmMaterial.add(Integer.parseInt(idsSt[i]));
                        } catch (Exception e) {
                        }
                    }
                }
                if (!idsRmMaterial.isEmpty()) {
                    List<RmMaterial> listaRmMaterial = rmaService.listarRmMateriais(idsRmMaterial);
                    if (listaRmMaterial != null && !listaRmMaterial.isEmpty()) {
                        Info info = enviarCompra(listaRmMaterial);
                        listaRegistrosDesativar.add(sincRegistro);

                    }
                }
            }
        }

        if (!listaRegistrosDesativar.isEmpty()) {
            sincRegistroService.desativar(listaRegistrosDesativar);
        }
    }

    /**
     * Monta o objeto compra e envia para o sap
     *
     * @param listaMaterial
     * @param
     * @return
     */
    public Info enviarCompra(List<RmMaterial> listaMaterial) {
		ConfIntegracaoServiceImpl confIntegracaoService = new ConfIntegracaoServiceImpl();
        ConfIntegracao confIntegracao = confIntegracaoService.listarConfIntegracao(Constantes.CODIGO_INTEGRACAO_COMPRA_MATERIAL);
        Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();
        Info info = new Info();
        Pessoa aprovador = null;
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(null));
        SincRegistroService sincRegistroService = new SincRegistroService();

        String numRmaConcat = "";
        String codDepositoConcat = "";
        String itemRmSapConcat = "";

        DTSolicitaRequisicaoResponse dTSolicitaRequisicaoResponse;

        List<DTSolicitaRequisicao.Itens> itens = new ArrayList<>();

        GenericDao<RmMaterial> genericDao = new GenericDao<>();
        try {
            String centroAux = "";
            if(configuracao.getHabilitaEapMultiEmpresa() != null && configuracao.getHabilitaEapMultiEmpresa() && listaMaterial != null && listaMaterial.size() > 0){
                if(listaMaterial.get(0).getRm().getEapMultiEmpresa().getCentro() != null){
                    centroAux = listaMaterial.get(0).getRm().getEapMultiEmpresa().getCentro();
                }else{
                    centroAux = getEapMultiEmpresabyRm(listaMaterial.get(0).getRm()).getCentro();
                }
            }

            int contadorOrdeItemSap = 1;
            for (RmMaterial rmMaterial : listaMaterial) {

                if (rmMaterial != null && rmMaterial.getLocalEntrega() != null && rmMaterial.getLocalEntrega().length() > 25) {
                    String auxSub = rmMaterial.getLocalEntrega().substring(0, 25);
                    rmMaterial.setLocalEntrega(auxSub);
                }

                if (rmMaterial.getFluxoMaterial() != null && rmMaterial.getFluxoMaterial().equals(Constantes.FLUXO_MATERIAL_COMPRA)) {
                    DTSolicitaRequisicao.Itens item = new DTSolicitaRequisicao.Itens();

                    //item.setItem(String.valueOf(rmMaterial.getOrdem()));
                    item.setMaterial(rmMaterial.getMaterial().getCodigo());
                    item.setCodRma(rmMaterial.getRm().getNumeroRm());
                    numRmaConcat = numRmaConcat + rmMaterial.getRm().getNumeroRm() + "; ";

                    String requisitante = (rmMaterial.getRm().getRequisitante().getNome() != null && rmMaterial.getRm().getRequisitante().getNome().contains(" "))
                            ? rmMaterial.getRm().getRequisitante().getNome().substring(0, 11)
                            : rmMaterial.getRm().getRequisitante().getReferencia();
                    item.setSolicitante(requisitante);

                    if(configuracao.getHabilitaEapMultiEmpresa() != null && configuracao.getHabilitaEapMultiEmpresa()){
                        item.setCentro(centroAux);
                    }else {
                        item.setCentro(configuracao.getCentroCod().split(";")[0]);
                    }
                    //Transforma em BigDecimal, na garantia que se for muito grande, o Double transforma em E
                    if (rmMaterial.getQuantidade() != null) {
                        BigDecimal bigDecimalQuantidade = new BigDecimal(rmMaterial.getQuantidade());
                        bigDecimalQuantidade = bigDecimalQuantidade.setScale(3, BigDecimal.ROUND_HALF_EVEN);
                        item.setQuantidade(String.valueOf(bigDecimalQuantidade));
                    }
                    String codDeposito = rmMaterial.getDeposito() != null ? rmMaterial.getDeposito().getCodigo() : null;
                    item.setDeposito(codDeposito);
                    if (codDeposito != null) {
                        codDepositoConcat = codDepositoConcat + codDeposito + "; ";
                    }
                    if (rmMaterial.getMaterial().getUnidadeMedida() != null) {
                        item.setUnidMedida(rmMaterial.getMaterial().getUnidadeMedida().getSigla());
                    }

                    if (rmMaterial.getColetorCustos() != null && !rmMaterial.getColetorCustos().trim().equals("")) {
                        item.setColetor(rmMaterial.getColetorCustos());
                    } else if (rmMaterial.getDiagramaRede() != null && !rmMaterial.getDiagramaRede().trim().equals("")) {
                        String operacao = null;
                        try {
                            Integer op = Integer.parseInt(rmMaterial.getOperacao());
                            operacao = String.format("%04d", op);
                        } catch (Exception e) {
                            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
                        }

                        item.setOperacao(operacao);
                        item.setDiagRede(rmMaterial.getDiagramaRede());
                    }

                    //Somente funciona, se todos os campos estiverem zerados, nÃ£o retirar.
                    XMLGregorianCalendar data = Util.dateToXMLGregorianCalendar(rmMaterial.getRm().getDataAplicacao());
                    data.setHour(0);
                    data.setMinute(0);
                    data.setSecond(0);
                    data.setMillisecond(0);
                    data.setFractionalSecond(BigDecimal.ZERO);
                    data.setTimezone(0);

                    item.setDtNecessidade(data);
                    if (rmMaterial.getLocalEntrega() != null) {
                        item.setLocalEntrega(rmMaterial.getLocalEntrega().replace("-", " "));
                        item.setLocalEntrega(rmMaterial.getLocalEntrega().replace("–", " "));
                    }
                    item.setFornecedor(null);

                    if (rmMaterial.getRm().getGerenteObra() != null) {
                        aprovador = rmMaterial.getRm().getGerenteObra();
                    } else if (rmMaterial.getRm().getGerenteCustos() != null) {
                        aprovador = rmMaterial.getRm().getGerenteCustos();
                    } else if (rmMaterial.getRm().getGerenteArea() != null) {
                        aprovador = rmMaterial.getRm().getGerenteArea();
                    }

                    if (aprovador != null) {
                        item.setAprovador(aprovador.getReferencia());
                    }

                    //Seta numero sequencial de envio do itens para o SAP
                    item.setItem(String.valueOf(contadorOrdeItemSap));
                    rmMaterial.setItemRequisicaoSap(contadorOrdeItemSap);
                    itemRmSapConcat = itemRmSapConcat + contadorOrdeItemSap + "; ";
                    contadorOrdeItemSap = contadorOrdeItemSap + 1;

                    itens.add(item);
                }
            }

            if (!itens.isEmpty()) {
                DTSolicitaRequisicao dTSolicitaRequisicao = new DTSolicitaRequisicao();

                dTSolicitaRequisicao.getItens().addAll(itens);

                confIntegracaoService.autenticar(confIntegracao.getLogin(), confIntegracao.getSenha(), confIntegracao.getUrl());

                //Busca o serviço SAP instanciando com a URL do WSDL configurada no BD, caso não houver, usa a padrão que foi gerada.
                BSRMAWEBSISolicitaRequisicaoSyncOutXX bSRMAWEBSISolicitaRequisicaoSyncOutXX = null;
                if (confIntegracao.getUrlWsdlService() != null) {
                    bSRMAWEBSISolicitaRequisicaoSyncOutXX = new BSRMAWEBSISolicitaRequisicaoSyncOutXX(new URL(confIntegracao.getUrlWsdlService()));
                } else {
                    bSRMAWEBSISolicitaRequisicaoSyncOutXX = new BSRMAWEBSISolicitaRequisicaoSyncOutXX();
                }

                SISolicitaRequisicaoSyncOut sISolicitaRequisicaoSyncOut = bSRMAWEBSISolicitaRequisicaoSyncOutXX.getHTTPPort();

                BindingProvider bp = (BindingProvider) sISolicitaRequisicaoSyncOut;
                bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, confIntegracao.getUrl());

                Binding binding = ((BindingProvider) sISolicitaRequisicaoSyncOut).getBinding();
                List handlers = binding.getHandlerChain();
                handlers.add(new SoapHandlerSapService());
                binding.setHandlerChain(handlers);

                dTSolicitaRequisicaoResponse = sISolicitaRequisicaoSyncOut.siSolicitaRequisicaoSyncOut(dTSolicitaRequisicao);

                if (dTSolicitaRequisicaoResponse.getTipoMensagem().equals(Constantes.TIPO_MENSAGEM_SUCESSO)) {
                    List<Propriedade> propriedades = new ArrayList<>();
                    propriedades.add(new Propriedade(RmMaterial.NUMERO_REQUISICAO_SAP));
                    propriedades.add(new Propriedade(RmMaterial.ITEM_REQUISICAO_SAP));

                    genericDao.beginTransaction();

                    for (RmMaterial rmMaterial : listaMaterial) {
                        rmMaterial.setNumeroRequisicaoSap(dTSolicitaRequisicaoResponse.getRequisicao());
                        genericDao.updateWithCurrentTransaction(rmMaterial, propriedades);
                    }

                    genericDao.commitCurrentTransaction();

                    info.setMensagem(rb.getString("msg_requisicao_criada_sucesso"));
                    info.setCodigo(Info.INFO_001);
                    info.setTipo(Info.TIPO_MSG_SUCCESS);
                    info.setErro(null);
                    info.setObjeto(dTSolicitaRequisicaoResponse.getRequisicao());
                } else {
                    SalvaMudancas(info, listaMaterial, null, rb, sincRegistroService, dTSolicitaRequisicaoResponse, null);
                }
            }
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            SalvaMudancas(info, listaMaterial, null, rb, sincRegistroService, null, e);
        } finally {
            //É necessário a limpeza dos proxys que foram setados para chamadas dos serviços SAP
            //Sem limpar, causava erroa na chamada dos outros serviços que estão usando o mesmo tomcat pois seta
            //váriaveis do sistema.
            System.setProperty("proxySet", "false");
            System.clearProperty("proxySet");
            System.clearProperty("http.proxyHost");
            System.clearProperty("http.proxyPort");
            System.clearProperty("http.proxyUser");
            System.clearProperty("http.proxyPassword");
        }

        try {
            //Gerando Log de interface
            LogInterfaceVo logInterfaceVo = new LogInterfaceVo(info);
            if (info.getErro() != null) {
                logInterfaceVo.setTipoMensagem(Constantes.TIPO_MENSAGEM_ERRO);
            } else {
                logInterfaceVo.setTipoMensagem(Constantes.TIPO_MENSAGEM_SUCESSO);
            }
            CompraMateriaisSapVo compraMateriaisSapVo = new CompraMateriaisSapVo();
            compraMateriaisSapVo.setCodDepositoConcat(codDepositoConcat);
            compraMateriaisSapVo.setNumRmaConcat(numRmaConcat);
            compraMateriaisSapVo.setItemRmSapConcat(itemRmSapConcat);
            compraMateriaisSapVo.setNumReqSap((String) info.getObjeto());
            logInterfaceVo.setObjeto(compraMateriaisSapVo);
            LogInterfaceService.inserirLog(Constantes.SISTEMA_RMAWEB, Constantes.INTERFACE_REQUISICAO_COMPRA, "SistemaRMA", logInterfaceVo);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return info;
    }

    private Info SalvaMudancas(Info info, List<RmMaterial> listaMaterial,
                               Integer agrupador, NxResourceBundle rb, SincRegistroService sincRegistroService,
                               DTSolicitaRequisicaoResponse dTSolicitaRequisicaoResponse, Exception e) {

        GenericDao<RmMaterial> genericDao = new GenericDao<>();

        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.AGRUPADOR_ERRO));

            genericDao.beginTransaction();

            for (RmMaterial rmMaterial : listaMaterial) {
                rmMaterial.setAgrupadorErro(agrupador);
                genericDao.updateWithCurrentTransaction(rmMaterial, propriedades);
            }

            genericDao.commitCurrentTransaction();

            /*//Concatena as Rma's para enviar para o sincRegistroService
            Set<String> set = new HashSet<>();
            String rmaConcatenadas = new String();
            String rmaMaterialConcatenadas = new String();
            for (RmMaterial rmMaterial : listaMaterial) {
                set.add(rmMaterial.getRm().getNumeroRm());
            }

            for (String s : set) {
                rmaConcatenadas += s + ";";
            }
            set = new HashSet<>();
            for (RmMaterial rmMaterial : listaMaterial) {
                set.add(rmMaterial.getRmMaterialId().toString());
            }

            for (String s : set) {
                rmaMaterialConcatenadas += s + ";";
            }
            */

            if (dTSolicitaRequisicaoResponse != null) {
                info.setMensagem(dTSolicitaRequisicaoResponse.getMensagem());
                info.setErro(dTSolicitaRequisicaoResponse.getMensagem());
                info.setTipo(Info.TIPO_MSG_DANGER);
            } else {
                if (e != null) {
                    info.setErro(Util.stackTraceToString(e));
                } else {
                    info.setErro(rb.getString("msg_requisicao_criada_sucesso_porem_nao_enviada_sap"));
                }
                info.setMensagem(rb.getString(Constantes.ERRO_COMUNICACAO_SAP_CONTATE_ADMINISTRADOR_I18N));
                info.setTipo(Info.TIPO_MSG_DANGER);
            }
            //sincRegistroService.salvar(info, Constantes.CODIGO_INTEGRACAO_COMPRA_MATERIAL, Constantes.SISTEMA_SAP, agrupador, rmaConcatenadas, rmaMaterialConcatenadas);

            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        } catch (Exception ex) {
            genericDao.rollbackCurrentTransaction();
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), ex));
        }
        return info;
    }


    public EapMultiEmpresa getEapMultiEmpresabyRm(Rm rmFiltro) {
        EapMultiEmpresa eap = new EapMultiEmpresa();

        GenericDao<Rm> genericDao = new GenericDao<>();

        try {

            String aliasEap = NxCriterion.montaAlias(Rm.ALIAS_CLASSE, Rm.EAP_MULTI_EMPRESA);

            List<Propriedade> propriedades = new ArrayList<>();
            //Requisicao
            propriedades.add(new Propriedade(Rm.RM_ID));

            //Requisitante
            propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEap));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEap));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEap));
            propriedades.add(new Propriedade(EapMultiEmpresa.CENTRO, EapMultiEmpresa.class, aliasEap));
            propriedades.add(new Propriedade(EapMultiEmpresa.CAMINHO_RH_WEB, EapMultiEmpresa.class, aliasEap));

            NxCriterion nxCriterion;
            nxCriterion = NxCriterion.montaRestriction(new Filtro(Rm.RM_ID, rmFiltro.getRmId(), Filtro.EQUAL));

            Rm rm = genericDao.selectUnique(propriedades, Rm.class, nxCriterion);

            eap = rm.getEapMultiEmpresa();

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return eap;
    }

}
