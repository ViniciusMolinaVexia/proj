/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.ws.sap;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.service.ConfIntegracaoServiceImpl;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.integracao.LogInterfaceService;
import br.com.nextage.rmaweb.service.integracao.SincRegistroService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.EnviaRetiradaVo;
import br.com.nextage.rmaweb.vo.LogInterfaceVo;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel H. Parisotto
 */
public class RetiradaMateriaisSapService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    /**
     * Utilizado pelo listener que tenta enviar novamente as retiradas que deram
     * erro em uma outra tentativa
     */
    public void sincronizar() {
        SincRegistroService sincRegistroService = new SincRegistroService();
        List<SincRegistro> listaRegistros = sincRegistroService.listar(Constantes.CODIGO_INTEGRACAO_RETIRADA_MATERIAL);

        MaterialDepositoSaida materialDepositoSaida;
        try {
            for (SincRegistro sincRegistro : listaRegistros) {
                if (sincRegistro.getJson() != null && sincRegistro.getNomeClasse() != null) {
                    Gson converterGson = new Gson();

                    EnviaRetiradaVo enviaRetiradaVo = converterGson.fromJson(sincRegistro.getJson(), EnviaRetiradaVo.class);

                    RetiradaMateriaisSapService retiradaMateriaisSapService = new RetiradaMateriaisSapService();
                    retiradaMateriaisSapService.enviarRetiradaByVo(enviaRetiradaVo);


                } else {
                    materialDepositoSaida = listarMaterialDepositoSaida(sincRegistro.getIdRegistro());
                    enviarRetirada(materialDepositoSaida);
                }
            }

            sincRegistroService.desativar(listaRegistros);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError("", this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
    }

    public void rodarBaixaEmLote() {

        List<VwRmMaterial> lista = listarRmMaterialLote();


        int cont = 0;
        for (VwRmMaterial mat : lista) {
            if (cont == 1 || cont == 2) {
                Info info = enviarRetiradaTemp(mat.getRmMaterial());
                System.out.println(mat.getRmMaterial().getMaterial().getCodigo());
                System.out.println(mat.getRmMaterial().getDeposito().getCodigo());
                System.out.println(info.getMensagem() + "-" + info.getCodigo() + "-" + cont);
            }
            cont = cont + 1;

            if (cont > 10) {
                break;
            }

        }

    }

    private List<VwRmMaterial> listarRmMaterialLote() {
        MaterialDepositoSaida materialDepositoSaida = new MaterialDepositoSaida();
        GenericDao<VwRmMaterial> genericDao = new GenericDao<>();

        List<VwRmMaterial> lista = new ArrayList<>();
        try {

            String aliasRmMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID);
            String aliasRmMaterialStatus = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_STATUS_ID);
            String aliasStatus = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_STATUS_ID, RmMaterialStatus.STATUS_ID);
            String aliasRm = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM);
            String aliasRmGerenteArea = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.GERENTE_AREA);
            String aliasRmGerenteCusto = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.GERENTE_CUSTOS);
            String aliasRmCoordenador = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.COORDENADOR);
            String aliasRmRespRetEstoq = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.RESPONSAVEL_RETIRADA_ESTOQUE);
            String aliasComprador = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.COMPRADOR);
            String aliasPrioridade = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.PRIORIDADE);
            String aliasTipoRequisicao = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.TIPO_REQUISICAO);
            String aliasMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.MATERIAL);
            String aliasTipoMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.MATERIAL, Material.TIPO_MATERIAL);
            String aliasUnidadeMedida = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.MATERIAL, Material.UNIDADE_MEDIDA);
            String aliasRequisitante = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.REQUISITANTE);
            String aliasRequisitanteSubArea = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.REQUISITANTE, Pessoa.SUB_AREA);
            String aliasUsuario = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.USUARIO);
            String aliasUsuarioPessoa = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.USUARIO, Usuario.PESSOA);
            String aliasRmMaterialDeposito = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.DEPOSITO_ID);
            /*String aliasRmMaterialRecebimento = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.QTDE_RECEBIDA);*/

            List<Propriedade> propriedades = new ArrayList<>();
            //VIEW
            propriedades.add(new Propriedade(VwRmMaterial.ID));
            propriedades.add(new Propriedade(VwRmMaterial.CONFIRMAR_RETIRADA));
            propriedades.add(new Propriedade(VwRmMaterial.QTDE_RECEBIDA));
            propriedades.add(new Propriedade(VwRmMaterial.DATA_APROV_COORDENADOR));
            propriedades.add(new Propriedade(VwRmMaterial.DATA_APROV_GERENTE_AREA));
            propriedades.add(new Propriedade(VwRmMaterial.DATA_APROV_GERENTE_CUSTOS));
            propriedades.add(new Propriedade(VwRmMaterial.DATA_APROV_COMPLEMENTO_CUSTOS));
            propriedades.add(new Propriedade(VwRmMaterial.DATA_APROV_RESPONSAVEL_RETIRADA_ESTOQUE));

            //RM MATERIAL
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_REQUISICAO_SAP, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DATA_PREVISTA_ENTREGA, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.FLUXO_MATERIAL, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_CUSTOS, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_ORDEM, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DIAGRAMA_REDE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.OPERACAO, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DATA_COMPRA, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DATA_RECEBIMENTO_SUPRIMENTOS, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DATA_PREV_ENTREGA_SUPRIMENTOS, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DATA_ULTIMA_NF, RmMaterial.class, aliasRmMaterial));

            //RM MATERIAL STATUS
            propriedades.add(new Propriedade(RmMaterialStatus.ID, RmMaterialStatus.class, aliasRmMaterialStatus));

            //STATUS
            propriedades.add(new Propriedade(Status.ID, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.NOME, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.CODIGO, Status.class, aliasStatus));

            //RM
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_EMISSAO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_ENVIO_APROVACAO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_REPROVACAO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_CANCELAMENTO, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.LOCAL_ENTREGA, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.OBSERVACAO, Rm.class, aliasRm));

            //COMPRADOR
            propriedades.add(new Propriedade(Comprador.COMPRADOR_ID, Comprador.class, aliasComprador));
            propriedades.add(new Propriedade(Comprador.NOME, Comprador.class, aliasComprador));

            //PRIORIDADE
            propriedades.add(new Propriedade(Prioridade.PRIORIDADE_ID, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.CODIGO, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.DESCRICAO, Prioridade.class, aliasPrioridade));
            propriedades.add(new Propriedade(Prioridade.CONF_DIAS_PREV_ENTREGA, Prioridade.class, aliasPrioridade));

            //MATERIAL
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME_COMPLETO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.IS_SERVICO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP, Material.class, aliasMaterial));

            //TIPO MATERIAL
            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));

            //UNIDADE MEDIDA
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            //REQUISITANTE
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasRequisitante));


            //RM -> GERENTE AREA
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRmGerenteArea));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRmGerenteArea));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasRmGerenteArea));

            //RM -> GERENTE CUSTOS
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRmGerenteCusto));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRmGerenteCusto));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasRmGerenteCusto));

            //RM -> RESPONSAVEL RETIRADA ESTOQUE
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRmRespRetEstoq));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRmRespRetEstoq));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasRmRespRetEstoq));

            //RM -> COORDENADOR
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRmCoordenador));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRmCoordenador));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasRmCoordenador));

            //USUARIO
            propriedades.add(new Propriedade(Usuario.USUARIO_ID, Usuario.class, aliasUsuario));
            propriedades.add(new Propriedade(Usuario.NOME, Usuario.class, aliasUsuario));

            //USUARIO PESSOA
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasUsuarioPessoa));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasUsuarioPessoa));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasUsuarioPessoa));

            //DEPOSITO
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasRmMaterialDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasRmMaterialDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasRmMaterialDeposito));

            //REQUISITANTE -> SUB ÁREA
            propriedades.add(new Propriedade(Pessoa.ALIAS_CLASSE, SubArea.SUB_AREA_ID, SubArea.class, aliasRequisitanteSubArea));
            propriedades.add(new Propriedade(Pessoa.ALIAS_CLASSE, SubArea.CODIGO, SubArea.class, aliasRequisitanteSubArea));
            propriedades.add(new Propriedade(Pessoa.ALIAS_CLASSE, SubArea.DESCRICAO, SubArea.class, aliasRequisitanteSubArea));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Rm.DATA_APLICACAO, Util.montaData(7, 5, 2016), Filtro.MAIOR, aliasRm));
            Status status = new Status();
            status.setId(15);
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterialStatus.STATUS_ID, status, Filtro.EQUAL, aliasRmMaterialStatus)));

            lista = genericDao.listarByFilter(propriedades, null, VwRmMaterial.class, Constantes.NO_LIMIT, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return lista;
    }

    public Info enviarRetiradaTemp(RmMaterial rmMaterial) {
		ConfIntegracaoServiceImpl confIntegracaoService = new ConfIntegracaoServiceImpl();

        ConfIntegracao confIntegracao = confIntegracaoService.listarConfIntegracao(Constantes.CODIGO_INTEGRACAO_RETIRADA_MATERIAL);
        Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();
        Info info = Info.GetSuccess();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(null));

        DTSolicitaSaidaResponse dTSolicitaSaidaResponse;

        try {

            if (rmMaterial.getMaterial().getEstoqueSap() != null &&
                    rmMaterial.getMaterial().getEstoqueSap().equals(Constantes.SIM_ABREVIADO)) {

                //Verifica se o mesmo é do tipo EPI

                DTSolicitaSaida dTSolicitaSaida = new DTSolicitaSaida();

                dTSolicitaSaida.setCentro(configuracao.getCentroCod().split(";")[0]);
                dTSolicitaSaida.setDeposito(rmMaterial.getDeposito().getCodigo());
                dTSolicitaSaida.setMaterial(rmMaterial.getMaterial().getCodigo());

                //Verifica se o mesmo foi recebido do CP, se caso for, então utiliza o coletor de custo epi defenido na configuração.

                dTSolicitaSaida.setColetor(rmMaterial.getColetorCustos());
                dTSolicitaSaida.setDiagRede(rmMaterial.getDiagramaRede());
                dTSolicitaSaida.setOperacao(rmMaterial.getOperacao());

                if (dTSolicitaSaida.getOperacao() != null && dTSolicitaSaida.getOperacao().length() < 4) {
                    for (int i = dTSolicitaSaida.getOperacao().length(); i < 4; i++) {
                        dTSolicitaSaida.setOperacao('0' + dTSolicitaSaida.getOperacao());
                    }
                }


                BigDecimal bigDecimalQuantidade = new BigDecimal(rmMaterial.getQuantidade());
                bigDecimalQuantidade = bigDecimalQuantidade.setScale(3, BigDecimal.ROUND_HALF_EVEN);
                dTSolicitaSaida.setQuantidade(String.valueOf(bigDecimalQuantidade));


                dTSolicitaSaida.setUnidMedida(rmMaterial.getMaterial().getUnidadeMedida() != null
                        ? rmMaterial.getMaterial().getUnidadeMedida().getSigla() : null);

                confIntegracaoService.autenticar(confIntegracao.getLogin(), confIntegracao.getSenha(), confIntegracao.getUrl());

                //Busca o serviço SAP instanciando com a URL do WSDL configurada no BD, caso não houver, usa a padrão que foi gerada.
                BSRMAWEBSICriaSaidaSyncOutXX bSRMAWEBSICriaSaidaSyncOutXX = null;
                if (confIntegracao.getUrlWsdlService() != null) {
                    bSRMAWEBSICriaSaidaSyncOutXX = new BSRMAWEBSICriaSaidaSyncOutXX(new URL(confIntegracao.getUrlWsdlService()));
                } else {
                    bSRMAWEBSICriaSaidaSyncOutXX = new BSRMAWEBSICriaSaidaSyncOutXX();
                }
                SICriaSaidaSyncOut sICriaSaidaSyncOut = bSRMAWEBSICriaSaidaSyncOutXX.getHTTPPort();

                BindingProvider bp = (BindingProvider) sICriaSaidaSyncOut;
                bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, confIntegracao.getUrl());

                Binding binding = ((BindingProvider) sICriaSaidaSyncOut).getBinding();
                List handlers = binding.getHandlerChain();
                handlers.add(new SoapHandlerSapService());
                binding.setHandlerChain(handlers);

                dTSolicitaSaidaResponse = sICriaSaidaSyncOut.siCriaSaidaSyncOut(dTSolicitaSaida);

                if (dTSolicitaSaidaResponse.getTipoMensagem().equals(Constantes.TIPO_MENSAGEM_ERRO)) {
                    info = SalvarMudancaRegistroSincTemp(info, rmMaterial, rb, dTSolicitaSaidaResponse, null);
                }
            }

        } catch (Exception e) {
            info = SalvarMudancaRegistroSincTemp(info, rmMaterial, rb, null, e);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            e.printStackTrace();
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
            logInterfaceVo.setObjeto(rmMaterial);
            LogInterfaceService.inserirLog(Constantes.SISTEMA_RMAWEB, Constantes.INTERFACE_RETIRADA_MATERIAIS, "SistemaRMA", logInterfaceVo);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }


    /**
     * Monta o objeto retirada e envia para o sap
     *
     * @param materialDepositoSaida
     * @return
     */
    public Info enviarRetirada(MaterialDepositoSaida materialDepositoSaida) {
		ConfIntegracaoServiceImpl confIntegracaoService = new ConfIntegracaoServiceImpl();

        ConfIntegracao confIntegracao = confIntegracaoService.listarConfIntegracao(Constantes.CODIGO_INTEGRACAO_RETIRADA_MATERIAL);
        Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();
        Info info = Info.GetSuccess();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(null));

        DTSolicitaSaidaResponse dTSolicitaSaidaResponse;

        try {

            boolean enviaSAP = false;
            if (materialDepositoSaida.getRecebidoCp() != null && materialDepositoSaida.getRecebidoCp() == true) {
                if (materialDepositoSaida.getMaterialDeposito().getMaterial().getPatrimoniado() == null ||
                        materialDepositoSaida.getMaterialDeposito().getMaterial().getPatrimoniado().equals(Constantes.NAO_ABREVIADO)) {
                    enviaSAP = true;
                }
            } else {
                enviaSAP = true;
            }

            if (enviaSAP == true && materialDepositoSaida.getMaterialDeposito().getMaterial().getEstoqueSap() != null &&
                    materialDepositoSaida.getMaterialDeposito().getMaterial().getEstoqueSap().equals(Constantes.SIM_ABREVIADO)) {

                DTSolicitaSaida dTSolicitaSaida = new DTSolicitaSaida();

                dTSolicitaSaida.setDeposito(materialDepositoSaida.getMaterialDeposito().getDeposito().getCodigo());
                dTSolicitaSaida.setMaterial(materialDepositoSaida.getMaterialDeposito().getMaterial().getCodigo());

                //CENTRO
                if(configuracao.getHabilitaEapMultiEmpresa() != null && configuracao.getHabilitaEapMultiEmpresa()){
                    if(materialDepositoSaida.getMaterialDeposito().getDeposito() != null) {
                        String centroAux = "";
                        centroAux = getEapMultiEmpresabyDeposito(materialDepositoSaida.getMaterialDeposito().getDeposito()).getCentro();
                        dTSolicitaSaida.setCentro(centroAux);
                    }
                }else{
                    dTSolicitaSaida.setCentro(configuracao.getCentroCod().split(";")[0]);
                }

                //Verifica se o mesmo foi recebido do CP, se caso for, então utiliza o coletor de custo epi defenido na configuração.
                if (materialDepositoSaida.getRecebidoCp() != null && materialDepositoSaida.getRecebidoCp() == true) {
                    if (configuracao.getColetorCustoEpi() != null) {
                        dTSolicitaSaida.setColetor(configuracao.getColetorCustoEpi());
                    }
                } else {
                    dTSolicitaSaida.setColetor(materialDepositoSaida.getMaterialDepositoSaidaReserva().getRmMaterial().getColetorCustos());
                    dTSolicitaSaida.setDiagRede(materialDepositoSaida.getMaterialDepositoSaidaReserva().getRmMaterial().getDiagramaRede());
                    dTSolicitaSaida.setOperacao(materialDepositoSaida.getMaterialDepositoSaidaReserva().getRmMaterial().getOperacao());

                    if (dTSolicitaSaida.getOperacao() != null && dTSolicitaSaida.getOperacao().length() < 4) {
                        for (int i = dTSolicitaSaida.getOperacao().length(); i < 4; i++) {
                            dTSolicitaSaida.setOperacao('0' + dTSolicitaSaida.getOperacao());
                        }
                    }
                }


                BigDecimal bigDecimalQuantidade = new BigDecimal(materialDepositoSaida.getQuantidade());
                bigDecimalQuantidade = bigDecimalQuantidade.setScale(3, BigDecimal.ROUND_HALF_EVEN);
                dTSolicitaSaida.setQuantidade(String.valueOf(bigDecimalQuantidade));


                dTSolicitaSaida.setUnidMedida(materialDepositoSaida.getMaterialDeposito().getMaterial().getUnidadeMedida() != null
                        ? materialDepositoSaida.getMaterialDeposito().getMaterial().getUnidadeMedida().getSigla() : null);

                confIntegracaoService.autenticar(confIntegracao.getLogin(), confIntegracao.getSenha(), confIntegracao.getUrl());

                //Busca o serviço SAP instanciando com a URL do WSDL configurada no BD, caso não houver, usa a padrão que foi gerada.
                BSRMAWEBSICriaSaidaSyncOutXX bSRMAWEBSICriaSaidaSyncOutXX = null;
                if (confIntegracao.getUrlWsdlService() != null) {
                    bSRMAWEBSICriaSaidaSyncOutXX = new BSRMAWEBSICriaSaidaSyncOutXX(new URL(confIntegracao.getUrlWsdlService()));
                } else {
                    bSRMAWEBSICriaSaidaSyncOutXX = new BSRMAWEBSICriaSaidaSyncOutXX();
                }
                SICriaSaidaSyncOut sICriaSaidaSyncOut = bSRMAWEBSICriaSaidaSyncOutXX.getHTTPPort();

                BindingProvider bp = (BindingProvider) sICriaSaidaSyncOut;
                bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, confIntegracao.getUrl());

                Binding binding = ((BindingProvider) sICriaSaidaSyncOut).getBinding();
                List handlers = binding.getHandlerChain();
                handlers.add(new SoapHandlerSapService());
                binding.setHandlerChain(handlers);

                dTSolicitaSaidaResponse = sICriaSaidaSyncOut.siCriaSaidaSyncOut(dTSolicitaSaida);

                if (dTSolicitaSaidaResponse.getTipoMensagem().equals(Constantes.TIPO_MENSAGEM_ERRO)) {
                    info = SalvarMudancaRegistroSinc(info, materialDepositoSaida, rb, dTSolicitaSaidaResponse, null);
                }
            }

        } catch (Exception e) {
            info = SalvarMudancaRegistroSinc(info, materialDepositoSaida, rb, null, e);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            e.printStackTrace();
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
            logInterfaceVo.setObjeto(materialDepositoSaida);
            LogInterfaceService.inserirLog(Constantes.SISTEMA_RMAWEB, Constantes.INTERFACE_RETIRADA_MATERIAIS, "SistemaRMA", logInterfaceVo);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    /**
     * Carrega a eap multi empresa por deposito
     *
     * @param deposito
     * @return EapMultiEmpresa
     */
    public EapMultiEmpresa getEapMultiEmpresabyDeposito(Deposito deposito) {
        EapMultiEmpresa eap = new EapMultiEmpresa();

        GenericDao<Deposito> genericDao = new GenericDao<>();

        try {
            List<Propriedade> propriedades = new ArrayList<>();

            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));

            NxCriterion nxCriterion;
            nxCriterion = NxCriterion.montaRestriction(new Filtro(Deposito.DEPOSITO_ID, deposito.getDepositoId(), Filtro.EQUAL));

            Deposito d = genericDao.selectUnique(propriedades, Deposito.class, nxCriterion);

            eap = d.getEapMultiEmpresa();

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return eap;
    }

    /**
     * Recebe um Vo, logo, envia o mesmo para o SAP, se caso der erro, o mesmo armazena um Json
     *
     * @param enviaRetiradaVo
     * @return
     */
    public Info enviarRetiradaByVo(EnviaRetiradaVo enviaRetiradaVo) {
		ConfIntegracaoServiceImpl confIntegracaoService = new ConfIntegracaoServiceImpl();

        ConfIntegracao confIntegracao = confIntegracaoService.listarConfIntegracao(Constantes.CODIGO_INTEGRACAO_RETIRADA_MATERIAL);
        Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();
        Info info = Info.GetSuccess();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(null));

        DTSolicitaSaidaResponse dTSolicitaSaidaResponse;

        try {

            boolean enviaSAP = false;
            if (enviaRetiradaVo.getRecebidoCp() != null && enviaRetiradaVo.getRecebidoCp() == true) {
                if (enviaRetiradaVo.getMaterial().getPatrimoniado() == null ||
                        enviaRetiradaVo.getMaterial().getPatrimoniado().equals(Constantes.NAO_ABREVIADO)) {
                    enviaSAP = true;
                }
            } else {
                enviaSAP = true;
            }

            if (enviaSAP == true && enviaRetiradaVo.getMaterial().getEstoqueSap() != null &&
                    enviaRetiradaVo.getMaterial().getEstoqueSap().equals(Constantes.SIM_ABREVIADO)) {

                //Verifica se o mesmo é do tipo EPI

                DTSolicitaSaida dTSolicitaSaida = new DTSolicitaSaida();

                //CENTRO
                if(configuracao.getHabilitaEapMultiEmpresa() != null && configuracao.getHabilitaEapMultiEmpresa()){
                    if(enviaRetiradaVo.getDeposito().getDepositoId() > 0) {
                        String centroAux = "";
                        centroAux = getEapMultiEmpresabyDeposito(enviaRetiradaVo.getDeposito()).getCentro();
                        dTSolicitaSaida.setCentro(centroAux);
                    }
                }else{
                    dTSolicitaSaida.setCentro(configuracao.getCentroCod().split(";")[0]);
                }

                dTSolicitaSaida.setDeposito(enviaRetiradaVo.getDeposito().getCodigo());
                dTSolicitaSaida.setMaterial(enviaRetiradaVo.getMaterial().getCodigo());

                //Verifica se o mesmo foi recebido do CP, se caso for, então utiliza o coletor de custo epi defenido na configuração.
                if (enviaRetiradaVo.getRecebidoCp() != null && enviaRetiradaVo.getRecebidoCp() == true) {
                    if (configuracao.getColetorCustoEpi() != null) {
                        dTSolicitaSaida.setColetor(configuracao.getColetorCustoEpi());
                    }
                } else {
                    dTSolicitaSaida.setColetor(enviaRetiradaVo.getRmMaterial().getColetorCustos());
                    dTSolicitaSaida.setDiagRede(enviaRetiradaVo.getRmMaterial().getDiagramaRede());
                    dTSolicitaSaida.setOperacao(enviaRetiradaVo.getRmMaterial().getOperacao());

                    if (dTSolicitaSaida.getOperacao() != null && dTSolicitaSaida.getOperacao().length() < 4) {
                        for (int i = dTSolicitaSaida.getOperacao().length(); i < 4; i++) {
                            dTSolicitaSaida.setOperacao('0' + dTSolicitaSaida.getOperacao());
                        }
                    }
                }

                BigDecimal bigDecimalQuantidade = new BigDecimal(enviaRetiradaVo.getQuantidade());
                bigDecimalQuantidade = bigDecimalQuantidade.setScale(3, BigDecimal.ROUND_HALF_EVEN);
                dTSolicitaSaida.setQuantidade(String.valueOf(bigDecimalQuantidade));

                dTSolicitaSaida.setUnidMedida(enviaRetiradaVo.getMaterial().getUnidadeMedida() != null
                        ? enviaRetiradaVo.getMaterial().getUnidadeMedida().getSigla() : null);

                confIntegracaoService.autenticar(confIntegracao.getLogin(), confIntegracao.getSenha(), confIntegracao.getUrl());

                //Busca o serviço SAP instanciando com a URL do WSDL configurada no BD, caso não houver, usa a padrão que foi gerada.
                BSRMAWEBSICriaSaidaSyncOutXX bSRMAWEBSICriaSaidaSyncOutXX = null;
                if (confIntegracao.getUrlWsdlService() != null) {
                    bSRMAWEBSICriaSaidaSyncOutXX = new BSRMAWEBSICriaSaidaSyncOutXX(new URL(confIntegracao.getUrlWsdlService()));
                } else {
                    bSRMAWEBSICriaSaidaSyncOutXX = new BSRMAWEBSICriaSaidaSyncOutXX();
                }
                SICriaSaidaSyncOut sICriaSaidaSyncOut = bSRMAWEBSICriaSaidaSyncOutXX.getHTTPPort();

                BindingProvider bp = (BindingProvider) sICriaSaidaSyncOut;
                bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, confIntegracao.getUrl());

                Binding binding = ((BindingProvider) sICriaSaidaSyncOut).getBinding();
                List handlers = binding.getHandlerChain();
                handlers.add(new SoapHandlerSapService());
                binding.setHandlerChain(handlers);

                dTSolicitaSaidaResponse = sICriaSaidaSyncOut.siCriaSaidaSyncOut(dTSolicitaSaida);

                if (dTSolicitaSaidaResponse.getTipoMensagem().equals(Constantes.TIPO_MENSAGEM_ERRO)) {
                    info = SalvarMudancaRegistroSincByVo(info, enviaRetiradaVo, rb, dTSolicitaSaidaResponse, null);
                }
            }

        } catch (Exception e) {
            info = SalvarMudancaRegistroSincByVo(info, enviaRetiradaVo, rb, null, e);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            e.printStackTrace();
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
            logInterfaceVo.setObjeto(enviaRetiradaVo);
            LogInterfaceService.inserirLog(Constantes.SISTEMA_RMAWEB, Constantes.INTERFACE_RETIRADA_MATERIAIS, "SistemaRMA", logInterfaceVo);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    private Info SalvarMudancaRegistroSincTemp(Info info, RmMaterial materialDepositoSaida, NxResourceBundle rb,
                                               DTSolicitaSaidaResponse dTSolicitaSaidaResponse, Exception e) {

        try {
            String rmAgrupado = null;
            String rmMaterialAgrupado = null;
            SincRegistroService sincRegistroService = new SincRegistroService();

            info.setIdObjetoSalvo(materialDepositoSaida.getRmMaterialId());
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setCodigo(Info.INFO_002);

            if (dTSolicitaSaidaResponse != null && dTSolicitaSaidaResponse.getMensagem() != null) {
                info.setMensagem(dTSolicitaSaidaResponse.getMensagem());
                info.setErro(dTSolicitaSaidaResponse.getMensagem());
            } else {
                String erro = Util.stackTraceToString(e);
                info.setErro(erro);
                info.setMensagem(rb.getString(Constantes.ERRO_COMUNICACAO_SAP_CONTATE_ADMINISTRADOR_I18N));
            }

            if (materialDepositoSaida != null && materialDepositoSaida.getRm() != null && materialDepositoSaida.getRm().getNumeroRm() != null) {
                rmAgrupado = materialDepositoSaida.getRm().getNumeroRm();
            }

            sincRegistroService.salvar(info, Constantes.CODIGO_INTEGRACAO_RETIRADA_MATERIAL, Constantes.SISTEMA_SAP, null, rmAgrupado, rmMaterialAgrupado);
        } catch (Exception ex) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    private Info SalvarMudancaRegistroSinc(Info info, MaterialDepositoSaida materialDepositoSaida, NxResourceBundle rb,
                                           DTSolicitaSaidaResponse dTSolicitaSaidaResponse, Exception e) {

        try {
            String rmAgrupado = null;
            String rmMaterialAgrupado = null;
            SincRegistroService sincRegistroService = new SincRegistroService();

            info.setIdObjetoSalvo(materialDepositoSaida.getMaterialDepositoSaidaId());
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setCodigo(Info.INFO_002);

            if (dTSolicitaSaidaResponse != null && dTSolicitaSaidaResponse.getMensagem() != null) {
                info.setMensagem(dTSolicitaSaidaResponse.getMensagem());
                info.setErro(dTSolicitaSaidaResponse.getMensagem());
            } else {
                String erro = Util.stackTraceToString(e);
                info.setErro(erro);
                info.setMensagem(rb.getString(Constantes.ERRO_COMUNICACAO_SAP_CONTATE_ADMINISTRADOR_I18N));
            }

            if (materialDepositoSaida != null && materialDepositoSaida.getRm() != null && materialDepositoSaida.getRm().getNumeroRm() != null) {
                rmAgrupado = materialDepositoSaida.getRm().getNumeroRm();
            }

            sincRegistroService.salvar(info, Constantes.CODIGO_INTEGRACAO_RETIRADA_MATERIAL, Constantes.SISTEMA_SAP, null, rmAgrupado, rmMaterialAgrupado);
        } catch (Exception ex) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    /*
    Salva o registro que deu ero no Sinc Registro junto com um Json do objeto do EnviaRetiradaVo.
     */
    private Info SalvarMudancaRegistroSincByVo(Info info, EnviaRetiradaVo enviaRetiradaVo, NxResourceBundle rb,
                                               DTSolicitaSaidaResponse dTSolicitaSaidaResponse, Exception e) {

        try {
            String rmAgrupado = null;
            String rmMaterialAgrupado = null;
            SincRegistroService sincRegistroService = new SincRegistroService();


            //Realizo a criação do Json do enviaRetiradaVo
            Object objetoenviaRetiradaVoJson = enviaRetiradaVo;
            String json = new String();
            String nomeClasse = new String();
            if (objetoenviaRetiradaVoJson != null) {
                json = new Gson().toJson(objetoenviaRetiradaVoJson);
                nomeClasse = objetoenviaRetiradaVoJson.getClass().toString();
            }

            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setCodigo(Info.INFO_002);

            if (dTSolicitaSaidaResponse != null && dTSolicitaSaidaResponse.getMensagem() != null) {
                info.setMensagem(dTSolicitaSaidaResponse.getMensagem());
                info.setErro(dTSolicitaSaidaResponse.getMensagem());

            } else {
                String erro = Util.stackTraceToString(e);
                info.setMensagem(rb.getString(Constantes.ERRO_COMUNICACAO_SAP_CONTATE_ADMINISTRADOR_I18N));
                info.setErro(erro);
            }

            if (enviaRetiradaVo.getRmMaterial().getRm() != null && enviaRetiradaVo.getRmMaterial().getRm().getNumeroRm() != null) {
                rmAgrupado = enviaRetiradaVo.getRmMaterial().getRm().getNumeroRm();
            }

            sincRegistroService.salvar(info, Constantes.CODIGO_INTEGRACAO_RETIRADA_MATERIAL, Constantes.SISTEMA_SAP, null, rmAgrupado, rmMaterialAgrupado, json, nomeClasse);
        } catch (Exception ex) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }


    private MaterialDepositoSaida listarMaterialDepositoSaida(Integer id) {
        MaterialDepositoSaida materialDepositoSaida = new MaterialDepositoSaida();
        GenericDao<MaterialDepositoSaida> genericDao = new GenericDao<>();

        try {
            String aliasMaterialDepositoSaidaReserva = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID);
            String aliasMaterialDeposito = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO);
            String aliasDeposito = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDeposito.DEPOSITO);
            String aliasMaterial = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDeposito.MATERIAL);
            String aliasRm = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.RM);
            String aliasRmMaterial = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL);
            String aliasUnidadeMedida = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDeposito.MATERIAL, Material.UNIDADE_MEDIDA);

            List<Propriedade> propriedades = new ArrayList<>();

            propriedades.add(new Propriedade(MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_ID));
            propriedades.add(new Propriedade(MaterialDepositoSaida.QUANTIDADE));
            propriedades.add(new Propriedade(MaterialDepositoSaida.IS_RECEBIDO_CP));

            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.ID, MaterialDepositoSaidaReserva.class, aliasMaterialDepositoSaidaReserva));

            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID, MaterialDeposito.class, aliasMaterialDeposito));

            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP, Material.class, aliasMaterial));

            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_CUSTOS, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DIAGRAMA_REDE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.OPERACAO, RmMaterial.class, aliasRmMaterial));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_ID, id, Filtro.EQUAL));

            materialDepositoSaida = genericDao.selectUnique(propriedades, MaterialDepositoSaida.class, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return materialDepositoSaida;
    }
}
