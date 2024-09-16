package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.dao.MaterialDepositoSaidaDao;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.RmMaterialRecebimentoService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.LogInterfaceVo;
import br.com.nextage.rmaweb.vo.integracao.EstoqueVo;
import br.com.nextage.rmaweb.ws.cpweb.LocalEstoque;
import br.com.nextage.rmaweb.ws.cpweb.SincEquipamentoVo;
import br.com.nextage.rmaweb.ws.cpweb.SincEstoque_Service;
import br.com.nextage.rmaweb.ws.cpweb.TipoEquipamento;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author nextage
 */
public class EstoqueIntegracaoService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    public List<LogInterfaceVo> atualizar(String login, EstoqueVo estoqueVo) {

        System.out.println("Atualizar estoque - Inicio");
        Info info = new Info();
        List<LogInterfaceVo> listaLog = new ArrayList<>();
        List<Propriedade> propriedades = new ArrayList<>();
        NxResourceBundle rb = new NxResourceBundle(null);

        DepositoIntegracaoService depositoIntegracaoService = new DepositoIntegracaoService();
        RmMaterialRecebimentoService rmMaterialRecebimentoService = new RmMaterialRecebimentoService();
        GenericDao<RmMaterial> genericDao = new GenericDao<>();

        try {


            Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
            //Caso não venha o deposito, pego o deposito da EapmultiEmpresa da Rm ou então, se não tiver habilidado o Eap multi empresa
            //Pego o primeiro registro da TB_EAP_MULTI_EMPRESA
            if (estoqueVo.getDeposito() == null || estoqueVo.getDeposito().isEmpty()) {
                if(conf.getHabilitaEapMultiEmpresa() != null && conf.getHabilitaEapMultiEmpresa() == true){
                    estoqueVo.setDeposito(obterDepositoEapMultiEmpresa(estoqueVo.getNumeroRma()));
                }else{
                    estoqueVo.setDeposito(obterPrimeiroDepositoEapMultiEmpresa());
                }

            }

            String eapDeposito = null;
            if(conf.getHabilitaEapMultiEmpresa() && estoqueVo.getCentro() != null){
                EapMultiEmpresaService eapService = new EapMultiEmpresaService();
                EapMultiEmpresa eapMultiempresa = eapService.getEapMultiEmpresaByCentro(estoqueVo.getCentro());
                eapDeposito = eapMultiempresa.getCodigo();
            }

            Deposito deposito = depositoIntegracaoService.searchDepositoByCodigo(estoqueVo.getDeposito(), eapDeposito);

            if (deposito != null) {

                //Verificando se é um estorno de estoque
                if (estoqueVo.getEstorno() == null || !estoqueVo.getEstorno().equals("X")) {

                    Double quantidade = Double.parseDouble(estoqueVo.getQuantidade());

                    System.out.println("Atualizar estoque - deposito " + estoqueVo.getDeposito());

                    List<RmMaterial> listaRmMaterial = null;
                    System.out.println("Atualizar estoque - Inicio item - " + estoqueVo.getCodigoMaterial() + " - " + estoqueVo.getNumeroNf() + " - " + estoqueVo.getItemRmSap() + " - " + estoqueVo.getRmSap() + " - " + estoqueVo.getNumeroRma() + " - " + estoqueVo.getQuantidade() + " - ");

                    if (estoqueVo.getNumeroRma() != null) {
                        listaRmMaterial = listarRmMaterialToEntradaEstorno(estoqueVo);
                    }

                    RmMaterialRecebimento rmMaterialRecebimento = new RmMaterialRecebimento();
                    rmMaterialRecebimento.setDataRecebimentoMaterial(new Date());
                    rmMaterialRecebimento.setQuantidade(quantidade);
                    rmMaterialRecebimento.setDataEmissaoNf(estoqueVo.getDataEmissaoNf());
                    rmMaterialRecebimento.setNumeroNotaFiscal(estoqueVo.getNumeroNf());

                    if (listaRmMaterial != null) {

                        //Se encontrou material, faz recebimento e atualiza os materiais da RM
                        if (!listaRmMaterial.isEmpty()) {

                            System.out.println("Atualizar estoque - lista encontrada");

                            propriedades.clear();
                            propriedades.add(new Propriedade(RmMaterial.DEPOSITO_ID));
                            propriedades.add(new Propriedade(RmMaterial.DATA_ULTIMA_NF));
                            propriedades.add(new Propriedade(RmMaterial.IS_REC_SAP));

                            for (final RmMaterial rmMaterial : listaRmMaterial) {

                                try {
                                    genericDao.beginTransaction();

                                    //Atualiza Material
                                    Material material = null;
                                    material = searchMaterial(estoqueVo.getCodigoMaterial());

                                    List<Propriedade> propriedadesRmMaterial = new ArrayList<>();
                                    propriedadesRmMaterial.add(new Propriedade(RmMaterial.MATERIAL));
                                    propriedadesRmMaterial.add(new Propriedade(RmMaterial.COD_MATERIAL_ANTERIOR));

                                    //Se caso não encontrar o material, então crio um novo com as informações basicas.
                                    if (material == null && !estoqueVo.getCodigoMaterial().isEmpty()) {
                                        material = new Material();
                                        material.setCodigo(estoqueVo.getCodigoMaterial());
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

                                    genericDao.commitCurrentTransaction();

                                } catch (Exception e) {
                                    genericDao.rollbackCurrentTransaction();
                                    System.out.println("receber compra - Erro metodo geral");
                                    info.setCodigo(Info.INFO_002);
                                    info.setErro(e.getMessage());
                                    info.setTipo("E");
                                    info.setMensagem(rb.getString("label_erro_ao_importar_registro"));
                                    LogInterfaceVo logVoErro = new LogInterfaceVo();
                                    logVoErro = new LogInterfaceVo(info);
                                    listaLog.add(logVoErro);
                                    logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
                                    continue;
                                }

                                System.out.println("Atualizar estoque - item encontrado " + rmMaterial.getItemRequisicaoSap());

                                if (rmMaterial.getIsRecSap() == null) {
                                    //Se caso for igual a null, então quer dizer que ainda não recebeu atualizaçãoEstoque o rmMaterial, tanto pelo sap, quanto pela tela.
                                    //Então o mesmo seta como true o isRecSap para mostrar que foi recebido pelo SAP, para fazer o bloqueio quando receber pela tela
                                    //De painel do estoquista.
                                    rmMaterial.setIsRecSap(Boolean.TRUE);
                                }

                                if (rmMaterial.getIsRecSap() != null && rmMaterial.getIsRecSap().equals(Boolean.TRUE)) {

                                    rmMaterial.setDeposito(deposito);
                                    rmMaterial.setDataUltimaNotaFiscal(estoqueVo.getDataEmissaoNf());

                                    //Caso não possua codigo da compra, atualizo o codigo e gero status de Ag Recebimento.
                                    if (rmMaterial.getNumeroPedidoCompra() == null && estoqueVo.getPedidoCompra() != null) {
                                        propriedades.add(new Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA));
                                        rmMaterial.setNumeroPedidoCompra(estoqueVo.getPedidoCompra());

                                        RmMaterialStatusService rma = new RmMaterialStatusService();
                                        RmMaterialStatus status = rma.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_COMPRADO_AGUARDANDO_RECEBIMENTO, null, new Date(), null, login);
                                        GenericDao<RmMaterialStatus> gdaoStatus = new GenericDao<>();
                                        gdaoStatus.persist(status);
                                    }

                                    rmMaterialRecebimento.setRmMaterial(rmMaterial);

                                    rmMaterialRecebimentoService.salvar(rmMaterialRecebimento, quantidade, Boolean.TRUE, rmMaterial, login);

                                    genericDao.update(rmMaterial, propriedades);
                                }
                            }
                        } else {//Se não encontrou, informa erro para o SAP.

                            info.setTipo("E");
                            info.setMensagem(Util.setParamsLabel(rb.getString("msg_compra_nao_encontrada_para_rma"), estoqueVo.getNumeroRma()));
                            info.setCodigo(estoqueVo.getNumeroRma());
                            info.setObjeto(estoqueVo);
                            LogInterfaceVo logVo = new LogInterfaceVo(info);
                            listaLog.add(logVo);

                        }
                    } else {
                        System.out.println("Atualizar estoque - NÃO INFORMADO NUMERO DA RMA VALIDA");

                        //quer dizer que não foi informada um numero de RMA, então
                        //Somente incrementa a entrada do material no depósito.
                        GenericDao<MaterialDeposito> genericDaoMaterialDeposito = new GenericDao<>();
                        GenericDao<Material> genericDaoMaterial = new GenericDao<>();
                        GenericDao<MaterialDepositoEntrada> genericDaoMaterialDepositoEntrada = new GenericDao<>();
                        try {
                            System.out.println("Atualizar estoque - Material deposito antes");
                            MaterialDeposito materialDeposito = selectMaterialDeposito(estoqueVo.getCodigoMaterial(),null,deposito.getDepositoId());

                            Material material = null;


                            if (materialDeposito == null) {

                                System.out.println("Atualizar estoque - Material deposito NULO");
                                NxCriterion nxCriterionMaterial = null;

                                List<Propriedade> propriedadesMaterial = new ArrayList<>();

                                propriedadesMaterial.add(new Propriedade(Material.MATERIAL_ID));
                                propriedadesMaterial.add(new Propriedade(Material.CODIGO));
                                propriedadesMaterial.add(new Propriedade(Material.PATRIMONIADO));
                                propriedadesMaterial.add(new Propriedade(Material.TIPO_MATERIAL));
                                propriedadesMaterial.add(new Propriedade(Material.NOME));
                                propriedadesMaterial.add(new Propriedade(Material.NOME_COMPLETO));

                                String aliasUnidadeMedida = NxCriterion.montaAlias(Material.ALIAS_CLASSE, Material.UNIDADE_MEDIDA);

                                propriedadesMaterial.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
                                propriedadesMaterial.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));
                                propriedadesMaterial.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));

                                nxCriterionMaterial = NxCriterion.montaRestriction(new Filtro(Material.CODIGO, estoqueVo.getCodigoMaterial(), Filtro.EQUAL));
                                material = genericDaoMaterial.selectUnique(propriedadesMaterial, Material.class, nxCriterionMaterial);
                            }

                            //Se caso o material Deposito for Null, então não existe ese item para o deposito, então o mesmo faz o persist
                            genericDaoMaterialDeposito.beginTransaction();

                            if (materialDeposito == null) {
                                materialDeposito = new MaterialDeposito();
                                materialDeposito.setMaterial(material);
                                materialDeposito.setDeposito(deposito);
                                materialDeposito.setQuantidade(quantidade);
                                genericDaoMaterialDeposito.persistWithCurrentTransaction(materialDeposito);
                            } else {

                                System.out.println("Atualizar estoque - Material deposito Não nulo");
                                //Se caso já tiver o item no deposito o mesmo então faz o update da quantidade.

                                List<Propriedade> propriedadesMaterialDeposito = new ArrayList<Propriedade>();
                                propriedadesMaterialDeposito.add(new Propriedade(MaterialDeposito.QUANTIDADE));
                                materialDeposito.setQuantidade(materialDeposito.getQuantidade() + quantidade);
                                genericDaoMaterialDeposito.updateWithCurrentTransaction(materialDeposito, propriedadesMaterialDeposito);
                            }
                            //cria o Objeto MaterialDepositoEntrada (historico de entrada de material)
                            String mensagemObservacao = "Por inventário.";

                            MaterialDepositoEntrada materialDepositoEntrada = new MaterialDepositoEntrada();

                            materialDepositoEntrada.setDataEntrada(new Date());
                            materialDepositoEntrada.setMaterialDeposito(materialDeposito);
                            materialDepositoEntrada.setObservacao(mensagemObservacao);
                            materialDepositoEntrada.setQuantidade(quantidade);


                            System.out.println("Atualizar estoque - Lançando entrada");
                            genericDaoMaterialDeposito.persistWithCurrentTransaction(materialDepositoEntrada);

                            genericDaoMaterialDeposito.commitCurrentTransaction();


                            System.out.println("Atualizar estoque - Envia CPWEB");
                            //Envia entrada de equip para o CPWEB
                            SincEquipamentoService sincEquipamentoService = new SincEquipamentoService();
                            sincEquipamentoService.enviaMaterial(materialDepositoEntrada,true);
                        } catch (Exception e) {
                            genericDaoMaterialDeposito.rollbackCurrentTransaction();
                            e.printStackTrace();

                            info.setTipo("E");
                            info.setMensagem(rb.getString("label_erro_ao_importar_registro"));
                            info.setCodigo(estoqueVo.getCodigoMaterial());
                            info.setObjeto(estoqueVo);
                            info.setErro(Util.stackTraceToString(e));
                            LogInterfaceVo logVo = new LogInterfaceVo(info);
                            listaLog.add(logVo);

                        }
                    }

                    if (info.getCodigo() == null) {
                        info.setTipo("S");
                        info.setMensagem(rb.getString(Constantes.REG_SALVO_SUCESSO_I18N));
                        info.setObjeto(estoqueVo);
                        LogInterfaceVo logVo = new LogInterfaceVo(info);
                        listaLog.add(logVo);
                    }
                }else{
                    //Regra de estor de estoque
                    //Remover quantidade do estoque e excluir recebimento
                    Double quantidade = Double.parseDouble(estoqueVo.getQuantidade());

                    System.out.println("Estornar estoque - deposito " + estoqueVo.getDeposito());

                    List<RmMaterial> listaRmMaterial = null;
                    System.out.println("Estornar estoque - Inicio item - " + estoqueVo.getCodigoMaterial() + " - " + estoqueVo.getNumeroNf() + " - " + estoqueVo.getItemRmSap() + " - " + estoqueVo.getRmSap() + " - " + estoqueVo.getNumeroRma() + " - " + estoqueVo.getQuantidade() + " - ");

                    Pessoa pessoaEstorno = selectPessoaByRefeencia(estoqueVo.getUsuario());

                    if (estoqueVo.getNumeroRma() != null && estoqueVo.getNumeroRma().length() > 0) {
                        listaRmMaterial = listarRmMaterialToEntradaEstorno(estoqueVo);
                    }

                    Boolean enviadoCPWEB = false;

                    if (listaRmMaterial != null) {
                        //Se encontrou material, faz recebimento e atualiza os materiais da RM
                        if (!listaRmMaterial.isEmpty()) {
                            for (final RmMaterial rmMaterial : listaRmMaterial) {
                                //ENCONTRA O RECEBIMENTO
                                List<RmMaterialRecebimento> listaRec = null;
                                NxCriterion nxCriterionRec = null;
                                NxCriterion nxCriterionRecAux = null;
                                List<Propriedade> propRec = new ArrayList<>();
                                propRec.add(new Propriedade(RmMaterialRecebimento.RM_MATERIAL_RECEBIMENTO_ID));
                                propRec.add(new Propriedade(RmMaterialRecebimento.DATA_EMISSAO_NF));
                                propRec.add(new Propriedade(RmMaterialRecebimento.DATA_RECEBIMENTO_MATERIAL));
                                propRec.add(new Propriedade(RmMaterialRecebimento.NUMERO_NOTA_FISCAL));
                                propRec.add(new Propriedade(RmMaterialRecebimento.QUANTIDADE));

                                String aliasRmMat = NxCriterion.montaAlias(RmMaterialRecebimento.ALIAS_CLASSE, RmMaterialRecebimento.RM_MATERIAL);
                                propRec.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMat));

                                List<NxOrder> orderRec = Arrays.asList(new NxOrder(RmMaterialRecebimento.DATA_RECEBIMENTO_MATERIAL, NxOrder.NX_ORDER.DESC));

                                nxCriterionRec = NxCriterion.montaRestriction(new Filtro(RmMaterialRecebimento.QUANTIDADE, quantidade, Filtro.EQUAL));
                                nxCriterionRecAux = NxCriterion.montaRestriction(new Filtro(RmMaterialRecebimento.RM_MATERIAL, rmMaterial, Filtro.EQUAL));
                                nxCriterionRec = NxCriterion.and(nxCriterionRec,nxCriterionRecAux);
                                if(estoqueVo.getNumeroNf() != null && estoqueVo.getNumeroNf().length() > 0) {
                                    nxCriterionRecAux = NxCriterion.montaRestriction(new Filtro(RmMaterialRecebimento.NUMERO_NOTA_FISCAL, estoqueVo.getNumeroNf(), Filtro.EQUAL));
                                    nxCriterionRec = NxCriterion.and(nxCriterionRec, nxCriterionRecAux);
                                }
                                //Pega o recebimento mais recente que atende os filtros
                                GenericDao<RmMaterialRecebimento> gDaoRec = new GenericDao<RmMaterialRecebimento>();
                                listaRec = gDaoRec.listarByFilter(propRec,orderRec, RmMaterialRecebimento.class,1, nxCriterionRec);

                                if(listaRec != null && listaRec.size() > 0) {
                                    System.out.println("Estornar estoque - recebimento encontrado " + rmMaterial.getItemRequisicaoSap());

                                    RmMaterialStatusService rma = new RmMaterialStatusService();
                                    RmMaterialStatus status = rma.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_ESTORNO_RECEBIMENTO, null, new Date(), rb.getString("label_estornado"), login);

                                    MaterialDeposito md = selectMaterialDeposito(null,rmMaterial.getMaterial().getMaterialId(),rmMaterial.getDeposito().getDepositoId());

                                    MaterialDepositoSaidaDao matDepSaidaDao = new MaterialDepositoSaidaDao();
                                    Integer numProtocolo = matDepSaidaDao.numeroProtocoloSaidaMax() + 1;

                                    //VALIDA ENVIO PARA O CPWEB
                                    RmaService rmaService = new RmaService();
                                    br.com.nextage.rmaweb.ws.cpweb.Info infoCp = null;
                                    if(rmaService.verificaEnvioParaCpweb(md.getMaterial())){
                                        System.out.println("Estornar estoque - Envia CPWEB");

                                        SincEquipamentoVo vo = new SincEquipamentoVo();
                                        //Configura TipoEquipamento
                                        TipoEquipamento tipoEquipamento = new TipoEquipamento();
                                        tipoEquipamento.setDescricao(rmMaterial.getMaterial().getNome());
                                        tipoEquipamento.setCodigo(rmMaterial.getMaterial().getCodigo());
                                        tipoEquipamento.setHierarquia(rmMaterial.getMaterial().getHierarquia());
                                        if(tipoEquipamento.getHierarquia() == null || tipoEquipamento.getHierarquia().trim().length() == 0) {
                                            MaterialService matService = new MaterialService();
                                            tipoEquipamento.setHierarquia(matService.getHierarquiaByMaterial(rmMaterial.getMaterial()));
                                        }
                                        vo.setTipoEquipamento(tipoEquipamento);
                                        vo.setPatrimoniado(rmMaterial.getMaterial().getPatrimoniado());
                                        if(rmMaterial.getPrefixoEquipamento() != null) {
                                            vo.setPrefixoEquipamento(rmMaterial.getPrefixoEquipamento());
                                        }
                                        if(pessoaEstorno != null && pessoaEstorno.getNome() != null) {
                                            vo.setNomeUsuario(pessoaEstorno.getNome());
                                        }
                                        // Pessoa responsável
                                        vo.setReferenciaPessoa(estoqueVo.getUsuario());

                                        //Configura Estoque
                                        LocalEstoque estoque = new LocalEstoque();
                                        estoque.setDescricao(rmMaterial.getDeposito().getNome());
                                        estoque.setCodigo(rmMaterial.getDeposito().getCodigo());
                                        vo.setLocalEstoque(estoque);

                                        //Configura Quantidade
                                        vo.setQuantidade(quantidade);

                                        //Configura Unidade Medida
                                        br.com.nextage.rmaweb.ws.cpweb.UnidadeMedida unidadeMedida = new br.com.nextage.rmaweb.ws.cpweb.UnidadeMedida();
                                        unidadeMedida.setDescricao(rmMaterial.getMaterial().getUnidadeMedida().getDescricao());
                                        vo.setUnidadeMedida(unidadeMedida);

                                        vo.setCodigoRequisicao(rmMaterial.getRm().getNumeroRm());
                                        vo.setIdentificadorRmMaterial(rmMaterial.getRmMaterialId());
                                        //Altera a Origem no CPWEB
                                        vo.setOrigem(Constantes.ESTORNO);

                                        Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();
                                        SincEstoque_Service sincEstoque = new SincEstoque_Service(new URL(configuracao.getCaminhoUrlCpweb()));

                                        Boolean habilitaEapMultiEmpresa = ConfiguracaoSingleton.getConfiguracao().getHabilitaEapMultiEmpresa();
                                        if (rmMaterial.getDeposito() != null && rmMaterial.getDeposito().getDepositoId() != null && habilitaEapMultiEmpresa != null && habilitaEapMultiEmpresa == true) {
                                            vo.getLocalEstoque().setLocalEstoqueEap(obterCodigoEapMultiEmpresaDeposito(rmMaterial.getDeposito().getDepositoId()));
                                        }

                                        infoCp = sincEstoque.getSincEstoquePort().estornarEstoque(vo);
                                        enviadoCPWEB = true;
                                    }

                                    if(infoCp == null || "success".equals(infoCp.getTipo())) {
                                        try {
                                            if (rmMaterial.getRm() != null) {
                                                List<MaterialDepositoSaida> listaMaterialDepositoSaida = selectMaterialDepositoSaida(rmMaterial.getRm(), md);

                                                if (listaMaterialDepositoSaida != null && !listaMaterialDepositoSaida.isEmpty() && rmMaterial.getColetorEstoque() != null && !rmMaterial.getColetorEstoque().equals(true)) {
                                                    for (MaterialDepositoSaida materialDepositoSaida : listaMaterialDepositoSaida) {
                                                        if (materialDepositoSaida != null && materialDepositoSaida.getReserva().equals(true) &&
                                                                materialDepositoSaida.getMaterialDepositoSaidaReserva() != null &&
                                                                quantidade < materialDepositoSaida.getQuantidade()) {

                                                            gDaoRec.beginTransaction();

                                                            //SALVA STATUS DE ESTORNO
                                                            gDaoRec.persistWithCurrentTransaction(status);

                                                            //UPDATE DE QUANTIDADE EM MATERIAL DEPOSITO
                                                            materialDepositoSaida.setQuantidade(materialDepositoSaida.getQuantidade() - quantidade);
                                                            List<Propriedade> propUpdateMD = new ArrayList<>();
                                                            propUpdateMD.add(new Propriedade(MaterialDepositoSaida.QUANTIDADE));
                                                            gDaoRec.updateWithCurrentTransaction(materialDepositoSaida, propUpdateMD);

                                                            //CRIA UMA SAÍDA DE ESTORNO COM A QUANTIDADE ESTORNADA
                                                            MaterialDepositoSaida mds = new MaterialDepositoSaida();
                                                            mds.setMaterialDepositoSaidaId(-1);
                                                            mds.setDataEnvioSap(new Date());
                                                            mds.setDataSaida(new Date());
                                                            mds.setMaterialDeposito(md);
                                                            mds.setMaterialDepositoSaidaReserva(null);
                                                            mds.setNumeroProtocoloSaida(numProtocolo);
                                                            mds.setQuantidade(quantidade);
                                                            mds.setQuantidadeOriginal(quantidade);
                                                            mds.setRecebidoCp(false);
                                                            mds.setReserva(false);
                                                            mds.setRetiradoPor(pessoaEstorno);
                                                            mds.setObservacao("ESTORNO SAP - Usuário:" + estoqueVo.getUsuario());
                                                            mds.setRm(rmMaterial.getRm());
                                                            gDaoRec.persistWithCurrentTransaction(mds);

                                                            //REMOVE O RECEBIMENTO
                                                            gDaoRec.deleteWithCurrentTransaction(listaRec.get(0));

                                                            gDaoRec.commitCurrentTransaction();

                                                        } else if (materialDepositoSaida != null && materialDepositoSaida.getReserva().equals(true) &&
                                                                materialDepositoSaida.getMaterialDepositoSaidaReserva() != null &&
                                                                quantidade == materialDepositoSaida.getQuantidade()) {
                                                            gDaoRec.beginTransaction();

                                                            //SALVA STATUS DE ESTORNO
                                                            gDaoRec.persistWithCurrentTransaction(status);

                                                            //REMOVE A SAÍDA
                                                            gDaoRec.deleteWithCurrentTransaction(materialDepositoSaida);

                                                            //REMOVE O MATERIAL DEPOSITO SAÍDA RESERVA
                                                            gDaoRec.deleteWithCurrentTransaction(materialDepositoSaida.getMaterialDepositoSaidaReserva());

                                                            //SALVA SAIDA DE ESTORNO
                                                            MaterialDepositoSaida mds = new MaterialDepositoSaida();
                                                            mds.setMaterialDepositoSaidaId(-1);
                                                            mds.setDataEnvioSap(new Date());
                                                            mds.setDataSaida(new Date());
                                                            mds.setMaterialDeposito(md);
                                                            mds.setMaterialDepositoSaidaReserva(null);
                                                            mds.setNumeroProtocoloSaida(numProtocolo);
                                                            mds.setQuantidade(quantidade);
                                                            mds.setQuantidadeOriginal(quantidade);
                                                            mds.setRecebidoCp(false);
                                                            mds.setReserva(false);
                                                            mds.setRetiradoPor(pessoaEstorno);
                                                            mds.setObservacao("ESTORNO SAP - Usuário:" + estoqueVo.getUsuario());
                                                            mds.setRm(rmMaterial.getRm());
                                                            gDaoRec.persistWithCurrentTransaction(mds);

                                                            //REMOVE O RECEBIMENTO
                                                            gDaoRec.deleteWithCurrentTransaction(listaRec.get(0));

                                                            gDaoRec.commitCurrentTransaction();
                                                        }
                                                    }
                                                } else {
                                                    //FAZ O ESTORNO DO RECEBIMENTO
                                                    gDaoRec.beginTransaction();

                                                    //SALVA STATUS DE ESTORNO
                                                    gDaoRec.persistWithCurrentTransaction(status);

                                                    //UPDATE DE QUANTIDADE EM MATERIAL DEPOSITO
                                                    md.setQuantidade(md.getQuantidade() - quantidade);
                                                    List<Propriedade> propUpdateMD = new ArrayList<>();
                                                    propUpdateMD.add(new Propriedade(MaterialDeposito.QUANTIDADE));
                                                    gDaoRec.updateWithCurrentTransaction(md, propUpdateMD);

                                                    //SALVA SAIDA DE ESTORNO
                                                    MaterialDepositoSaida mds = new MaterialDepositoSaida();
                                                    mds.setMaterialDepositoSaidaId(-1);
                                                    mds.setDataEnvioSap(new Date());
                                                    mds.setDataSaida(new Date());
                                                    mds.setMaterialDeposito(md);
                                                    mds.setMaterialDepositoSaidaReserva(null);
                                                    mds.setNumeroProtocoloSaida(numProtocolo);
                                                    mds.setQuantidade(quantidade);
                                                    mds.setQuantidadeOriginal(quantidade);
                                                    mds.setRecebidoCp(false);
                                                    mds.setReserva(false);
                                                    mds.setRetiradoPor(pessoaEstorno);
                                                    mds.setObservacao("ESTORNO SAP - Usuário:" + estoqueVo.getUsuario());
                                                    mds.setRm(rmMaterial.getRm());
                                                    gDaoRec.persistWithCurrentTransaction(mds);

                                                    //REMOVE O RECEBIMENTO
                                                    gDaoRec.deleteWithCurrentTransaction(listaRec.get(0));

                                                    gDaoRec.commitCurrentTransaction();
                                                }
                                            }

                                        }catch (Exception exc){
                                            exc.printStackTrace();
                                            gDaoRec.rollbackCurrentTransaction();

                                            LogInterfaceVo logVo = new LogInterfaceVo();
                                            logVo.setTipoMensagem("E");
                                            logVo.setMensagem(rb.getString(enviadoCPWEB?"label_estornado_cpweb_erro_rma":"label_estornar_erro_rma"));
                                            logVo.setCodigo(estoqueVo.getCodigoMaterial());
                                            listaLog.add(logVo);
                                            info.setCodigo(Info.INFO_002);
                                        }
                                    }else{
                                        LogInterfaceVo logVo = new LogInterfaceVo();
                                        logVo.setTipoMensagem("E");
                                        logVo.setMensagem(rb.getString("label_estornar_erro_cpweb"));
                                        logVo.setCodigo(estoqueVo.getCodigoMaterial());
                                        listaLog.add(logVo);
                                        info.setCodigo(Info.INFO_002);

                                    }
                                }else{
                                    //Recebimento nao enconrado
                                    LogInterfaceVo logVo = new LogInterfaceVo();
                                    logVo.setTipoMensagem("E");
                                    logVo.setMensagem(rb.getString("label_recebimento_nao_encontrado_estorno"));
                                    logVo.setCodigo(estoqueVo.getCodigoMaterial());
                                    listaLog.add(logVo);
                                    info.setCodigo(Info.INFO_002);
                                }
                            }
                        } else {//Se não encontrou, informa erro para o SAP.
                            LogInterfaceVo logVo = new LogInterfaceVo();
                            logVo.setTipoMensagem("E");
                            logVo.setMensagem(Util.setParamsLabel(rb.getString("msg_materiais_rma_nao_encontrados"), estoqueVo.getNumeroRma()));
                            logVo.setCodigo(estoqueVo.getNumeroRma());
                            listaLog.add(logVo);
                            info.setCodigo(Info.INFO_002);
                        }
                    } else {
                        System.out.println("Estornar estoque - NÃO INFORMADO NUMERO DA RMA VALIDA");

                        //quer dizer que não foi informada um numero de RMA, então
                        //Somente incrementa a entrada do material no depósito.
                        GenericDao<MaterialDeposito> genericDaoMd = new GenericDao<>();
                        try {
                            System.out.println("Estornar estoque - Material deposito antes");
                            MaterialDeposito md = selectMaterialDeposito(estoqueVo.getCodigoMaterial(),null,deposito.getDepositoId());

                            MaterialDepositoSaidaDao matDepSaidaDao = new MaterialDepositoSaidaDao();
                            Integer numProtocolo = matDepSaidaDao.numeroProtocoloSaidaMax() + 1;

                            //VALIDA ENVIO PARA O CPWEB
                            RmaService rmaService = new RmaService();
                            br.com.nextage.rmaweb.ws.cpweb.Info infoCp = null;
                            if(rmaService.verificaEnvioParaCpweb(md.getMaterial())){
                                System.out.println("Estornar estoque - Envia CPWEB");

                                SincEquipamentoVo vo = new SincEquipamentoVo();
                                //Configura TipoEquipamento
                                TipoEquipamento tipoEquipamento = new TipoEquipamento();
                                tipoEquipamento.setDescricao(md.getMaterial().getNome());
                                tipoEquipamento.setCodigo(md.getMaterial().getCodigo());
                                tipoEquipamento.setHierarquia(md.getMaterial().getHierarquia());
                                if(tipoEquipamento.getHierarquia() == null || tipoEquipamento.getHierarquia().trim().length() == 0) {
                                    MaterialService matService = new MaterialService();
                                    tipoEquipamento.setHierarquia(matService.getHierarquiaByMaterial(md.getMaterial()));
                                }
                                vo.setTipoEquipamento(tipoEquipamento);
                                vo.setPatrimoniado(md.getMaterial().getPatrimoniado());
                                if(pessoaEstorno != null) {
                                    vo.setNomeUsuario(pessoaEstorno.getNome());
                                }
                                // Pessoa responsável
                                vo.setReferenciaPessoa(pessoaEstorno.getReferencia());

                                //Configura Estoque
                                LocalEstoque estoque = new LocalEstoque();
                                estoque.setDescricao(md.getDeposito().getNome());
                                estoque.setCodigo(md.getDeposito().getCodigo());
                                vo.setLocalEstoque(estoque);

                                //Configura Quantidade
                                vo.setQuantidade(quantidade);

                                //Configura Unidade Medida
                                br.com.nextage.rmaweb.ws.cpweb.UnidadeMedida unidadeMedida = new br.com.nextage.rmaweb.ws.cpweb.UnidadeMedida();
                                unidadeMedida.setDescricao(md.getMaterial().getUnidadeMedida().getDescricao());
                                vo.setUnidadeMedida(unidadeMedida);

                                //Altera a Origem no CPWEB
                                vo.setOrigem(Constantes.ESTORNO);

                                Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();
                                SincEstoque_Service sincEstoque = new SincEstoque_Service(new URL(configuracao.getCaminhoUrlCpweb()));

                                Boolean habilitaEapMultiEmpresa = ConfiguracaoSingleton.getConfiguracao().getHabilitaEapMultiEmpresa();
                                if (md.getDeposito() != null && md.getDeposito().getDepositoId() != null && habilitaEapMultiEmpresa != null && habilitaEapMultiEmpresa == true) {
                                    vo.getLocalEstoque().setLocalEstoqueEap(obterCodigoEapMultiEmpresaDeposito(md.getDeposito().getDepositoId()));
                                }

                                infoCp = sincEstoque.getSincEstoquePort().estornarEstoque(vo);
                            }

                            if(infoCp == null || "success".equals(infoCp.getTipo())) {
                                try{
                                    genericDaoMd.beginTransaction();

                                    //UPDATE DE QUANTIDADE EM MATERIAL DEPOSITO
                                    md.setQuantidade(md.getQuantidade() - quantidade);
                                    List<Propriedade> propUpdateMD = new ArrayList<Propriedade>();
                                    propUpdateMD.add(new Propriedade(MaterialDeposito.QUANTIDADE));
                                    genericDaoMd.updateWithCurrentTransaction(md, propUpdateMD);

                                    //SALVA SAIDA DE ESTORNO
                                    MaterialDepositoSaida mds = new MaterialDepositoSaida();
                                    mds.setMaterialDepositoSaidaId(-1);
                                    mds.setDataEnvioSap(new Date());
                                    mds.setDataSaida(new Date());
                                    mds.setMaterialDeposito(md);
                                    mds.setMaterialDepositoSaidaReserva(null);
                                    mds.setNumeroProtocoloSaida(numProtocolo);
                                    mds.setQuantidade(quantidade);
                                    mds.setQuantidadeOriginal(quantidade);
                                    mds.setRecebidoCp(true);
                                    mds.setReserva(false);
                                    mds.setRetiradoPor(pessoaEstorno);
                                    mds.setObservacao("Por Inventário - ESTORNO SAP - Usuário:" + estoqueVo.getUsuario());
                                    genericDaoMd.persistWithCurrentTransaction(mds);

                                    genericDaoMd.commitCurrentTransaction();
                                }catch (Exception exc){
                                    genericDaoMd.rollbackCurrentTransaction();
                                    exc.printStackTrace();

                                    LogInterfaceVo logVo = new LogInterfaceVo();
                                    logVo.setTipoMensagem("E");
                                    logVo.setMensagem(rb.getString(enviadoCPWEB?"label_estornado_cpweb_erro_rma":"label_estornar_erro_rma"));
                                    logVo.setCodigo(estoqueVo.getCodigoMaterial());
                                    listaLog.add(logVo);
                                    info.setCodigo(Info.INFO_002);
                                }
                            }else{
                                LogInterfaceVo logVo = new LogInterfaceVo();
                                logVo.setTipoMensagem("E");
                                logVo.setMensagem(rb.getString("label_estornar_erro_cpweb"));
                                logVo.setCodigo(estoqueVo.getCodigoMaterial());
                                listaLog.add(logVo);
                                info.setCodigo(Info.INFO_002);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            LogInterfaceVo logVo = new LogInterfaceVo();
                            logVo.setTipoMensagem("E");
                            logVo.setMensagem(rb.getString("label_estornar_erro_rma"));
                            logVo.setCodigo(estoqueVo.getCodigoMaterial());
                            listaLog.add(logVo);
                            info.setCodigo(Info.INFO_002);
                        }
                    }

                    if (info.getMensagem() == null || info.getMensagem() == "") {
                        info.setTipo("S");
                        info.setMensagem(rb.getString(Constantes.REG_SALVO_SUCESSO_I18N));
                        info.setObjeto(estoqueVo);
                        LogInterfaceVo logVo = new LogInterfaceVo(info);
                        listaLog.add(logVo);
                    }
                }
            } else {

                info.setTipo("E");
                info.setMensagem(Util.setParamsLabel(rb.getString("label_deposito_nao_encontrado"), estoqueVo.getDeposito()));
                info.setCodigo(estoqueVo.getCodigoMaterial());
                info.setObjeto(estoqueVo);
                LogInterfaceVo logVo = new LogInterfaceVo(info);
                listaLog.add(logVo);
            }

        } catch (Exception e) {
            e.printStackTrace();
            info.setTipo("E");
            info.setMensagem(rb.getString("label_erro_ao_importar_registro"));
            info.setCodigo(estoqueVo.getCodigoMaterial());
            info.setErro(Util.stackTraceToString(e));
            info.setObjeto(estoqueVo);
            LogInterfaceVo logVo = new LogInterfaceVo(info);
            listaLog.add(logVo);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return listaLog;
    }


    public List<RmMaterial> listarRmMaterialToEntradaEstorno(EstoqueVo estoqueVo) throws Exception{
        //Atualiza RmMaterial
        String aliasMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL);
        String aliasTipoMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL, Material.TIPO_MATERIAL);
        String aliasRm = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);
        String aliasRmReq = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.REQUISITANTE);
        String aliasUnidadeMedida = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL, Material.UNIDADE_MEDIDA);
        String aliasDeposito = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.DEPOSITO_ID);


        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
        propriedades.add(new Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA));
        propriedades.add(new Propriedade(RmMaterial.FORNECEDOR_ID));
        propriedades.add(new Propriedade(RmMaterial.DATA_COMPRA));
        propriedades.add(new Propriedade(RmMaterial.ITEM_DO_PEDIDO));
        propriedades.add(new Propriedade(RmMaterial.CENTRO));
        propriedades.add(new Propriedade(RmMaterial.STATUS));
        propriedades.add(new Propriedade(RmMaterial.VALOR_PEDIDO));
        propriedades.add(new Propriedade(RmMaterial.QUANTIDADE));
        propriedades.add(new Propriedade(RmMaterial.ORDEM));
        propriedades.add(new Propriedade(RmMaterial.FLUXO_MATERIAL));
        propriedades.add(new Propriedade(RmMaterial.COLETOR_ESTOQUE));
        propriedades.add(new Propriedade(RmMaterial.DATA_ULTIMA_NF));
        propriedades.add(new Propriedade(RmMaterial.IS_REC_SAP));
        propriedades.add(new Propriedade(RmMaterial.PREFIXO_EQUIPAMENTO));

        propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
        propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
        propriedades.add(new Propriedade(Material.HIERARQUIA, Material.class, aliasMaterial));
        propriedades.add(new Propriedade(Material.PATRIMONIADO, Material.class, aliasMaterial));

        propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
        propriedades.add(new Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));
        propriedades.add(new Propriedade(TipoMaterial.DESCRICAO, TipoMaterial.class, aliasTipoMaterial));

        propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
        propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));

        propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRmReq));
        propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasRmReq));
        propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRmReq));

        propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
        propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));
        propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));


        propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
        propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));
        propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));

        NxCriterion nxCriterion = null;

        System.out.println("Estornar estoque - antes criterion list");
//                      nxCriterion = NxCriterion.montaRestriction(new Filtro(Material.CODIGO, estoqueVo.getCodigoMaterial(), Filtro.EQUAL, aliasMaterial));
        nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Rm.NUMERO_RM, estoqueVo.getNumeroRma(), Filtro.EQUAL, aliasRm)));
//                      nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterial.FLUXO_MATERIAL, Constantes.FLUXO_MATERIAL_COMPRA, Filtro.EQUAL)));
        nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterial.ITEM_REQUISICAO_SAP, Integer.parseInt(estoqueVo.getItemRmSap()), Filtro.EQUAL)));
        nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterial.NUMERO_REQUISICAO_SAP, estoqueVo.getRmSap(), Filtro.EQUAL)));

        System.out.println("Estornar estoque - antes lista");

        GenericDao<RmMaterial> genericDao = new GenericDao<RmMaterial>();
        List<RmMaterial> listaRmMaterial = genericDao.listarByFilter(propriedades, null, RmMaterial.class, Constantes.NO_LIMIT, nxCriterion);

        return  listaRmMaterial;
    }

    public Pessoa selectPessoaByRefeencia(String referencia) throws Exception {
       Pessoa p = null;
        try {
            if(referencia != null && referencia.trim().length() > 0) {
                NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.REFERENCIA, referencia, Filtro.EQUAL));
                if(referencia.toUpperCase().contains("CF")) {
                    referencia = referencia.toUpperCase().replace("CF","");
                    NxCriterion nxCritAux = NxCriterion.montaRestriction(new Filtro(Pessoa.REFERENCIA, referencia, Filtro.EQUAL));
                    nxCriterion = NxCriterion.or(nxCriterion, nxCritAux);
                }
                // Seta as propriedades de retorno da consulta.
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
                propriedades.add(new Propriedade(Pessoa.REFERENCIA));
                propriedades.add(new Propriedade(Pessoa.CPF));
                propriedades.add(new Propriedade(Pessoa.NOME));
                propriedades.add(new Propriedade(Pessoa.IS_REQUISITANTE));
                propriedades.add(new Propriedade(Pessoa.FUNCAO));
                propriedades.add(new Propriedade(Pessoa.TELEFONE));
                propriedades.add(new Propriedade(Pessoa.EMAIL));

                //Obtem elementos.
                GenericDao<Pessoa> genericDao = new GenericDao<Pessoa>();
                List<Pessoa> lista = genericDao.listarByFilter(propriedades, null, Pessoa.class, 1, nxCriterion);

                if (lista != null && lista.size() > 0) {
                    p = lista.get(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return p;

    }

    public List<MaterialDepositoSaida> selectMaterialDepositoSaida(Rm rm, MaterialDeposito md) {
        List<MaterialDepositoSaida> materialDepositoSaida = null;

        try {
            List<Propriedade> propriedades = new ArrayList<>();

            propriedades.add(new Propriedade(MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_ID));
            propriedades.add(new Propriedade(MaterialDepositoSaida.MATERIAL_DEPOSITO));
            propriedades.add(new Propriedade(MaterialDepositoSaida.RM));
            propriedades.add(new Propriedade(MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID));
            propriedades.add(new Propriedade(MaterialDepositoSaida.RESERVA));
            propriedades.add(new Propriedade(MaterialDepositoSaida.DATA_SAIDA));
            propriedades.add(new Propriedade(MaterialDepositoSaida.OBSERVACAO));
            propriedades.add(new Propriedade(MaterialDepositoSaida.QUANTIDADE));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaida.RM, rm, Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaida.MATERIAL_DEPOSITO, md, Filtro.EQUAL)));

            GenericDao<MaterialDepositoSaida> genericDao = new GenericDao<>();
            materialDepositoSaida = genericDao.listarByFilter(propriedades, null, MaterialDepositoSaida.class, Constantes.NO_LIMIT, nxCriterion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return materialDepositoSaida;
    }

    public MaterialDeposito selectMaterialDeposito(String codigoMaterial,Integer materialId, Integer depositoId )throws Exception {

        MaterialDeposito materialDeposito = null;

        List<Propriedade> propriedadesMaterialDeposito = new ArrayList<>();
        String aliasMaterial = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL);
        String aliasDeposito = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.DEPOSITO);

        propriedadesMaterialDeposito.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
        propriedadesMaterialDeposito.add(new Propriedade(MaterialDeposito.QUANTIDADE));

        propriedadesMaterialDeposito.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
        propriedadesMaterialDeposito.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
        propriedadesMaterialDeposito.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
        propriedadesMaterialDeposito.add(new Propriedade(Material.PATRIMONIADO, Material.class, aliasMaterial));

        String aliasMatTipo = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL, Material.TIPO_MATERIAL);
        propriedadesMaterialDeposito.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasMatTipo));
        propriedadesMaterialDeposito.add(new Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasMatTipo));
        propriedadesMaterialDeposito.add(new Propriedade(TipoMaterial.DESCRICAO, TipoMaterial.class, aliasMatTipo));

        String aliasMatDeposUnidadeMedida = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL, Material.UNIDADE_MEDIDA);
        propriedadesMaterialDeposito.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasMatDeposUnidadeMedida));
        propriedadesMaterialDeposito.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasMatDeposUnidadeMedida));
        propriedadesMaterialDeposito.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasMatDeposUnidadeMedida));

        propriedadesMaterialDeposito.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
        propriedadesMaterialDeposito.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
        propriedadesMaterialDeposito.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

        NxCriterion nxCrit = NxCriterion.montaRestriction(new Filtro(Deposito.DEPOSITO_ID, depositoId, Filtro.EQUAL, aliasDeposito));
        NxCriterion nxCritAux = null;
        if ((materialId != null && materialId > 0) || (codigoMaterial != null && codigoMaterial.trim().length() > 0))
        {
            if (materialId != null && materialId > 0) {
                nxCritAux = NxCriterion.montaRestriction(new Filtro(Material.MATERIAL_ID, materialId, Filtro.EQUAL, aliasMaterial));
            } else {
                nxCritAux = NxCriterion.montaRestriction(new Filtro(Material.CODIGO, codigoMaterial, Filtro.EQUAL, aliasMaterial));
            }
            nxCrit = NxCriterion.and(nxCrit,nxCritAux);
            GenericDao<MaterialDeposito> genericDao = new GenericDao<MaterialDeposito>();
            materialDeposito = genericDao.selectUnique(propriedadesMaterialDeposito, MaterialDeposito.class, nxCrit);
        }

        return  materialDeposito;
    }

    private Material searchMaterial(String codigo) {
        Material material = new Material();

        try {
            String aliasTipoMaterial = NxCriterion.montaAlias(Material.ALIAS_CLASSE, Material.TIPO_MATERIAL);


            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Material.MATERIAL_ID));
            propriedades.add(new Propriedade(Material.NOME));
            propriedades.add(new Propriedade(Material.CODIGO));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP));
            propriedades.add(new Propriedade(Material.PATRIMONIADO));

            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.DESCRICAO, TipoMaterial.class, aliasTipoMaterial));


            NxCriterion nxCriterion = null;
            nxCriterion = NxCriterion.montaRestriction(new Filtro(Material.CODIGO, codigo, Filtro.EQUAL));

            //Obtem elementos.
            GenericDao<Material> genericDao = new GenericDao<>();
            material = genericDao.selectUnique(propriedades, Material.class, nxCriterion);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return material;
    }

    private String obterCodigoEapMultiEmpresaDeposito(Integer depositoId) {

        GenericDao<Deposito> genericDao = new GenericDao<>();

        if (depositoId == null) {
            return null;
        }

        Deposito deposito = new Deposito();

        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));
        propriedades.add(new Propriedade(Deposito.NOME));
        propriedades.add(new Propriedade(Deposito.CODIGO));

        NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Deposito.DEPOSITO_ID, depositoId, Filtro.EQUAL));

        try {
            deposito = genericDao.selectUnique(propriedades, Deposito.class, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        if (deposito != null && deposito.getEapMultiEmpresa() != null && deposito.getEapMultiEmpresa().getCodigo() != null) {
            return deposito.getEapMultiEmpresa().getCodigo();
        }

        return null;
    }

    private String obterDepositoEapMultiEmpresa(String numeroRm) {

        GenericDao<Rm> genericDao = new GenericDao<>();

        if (numeroRm == null) {
            return null;
        }

        Rm rm = new Rm();

        String aliasEapMultiEmpresa = NxCriterion.montaAlias(Rm.ALIAS_CLASSE, Rm.EAP_MULTI_EMPRESA);


        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(Rm.RM_ID));
        propriedades.add(new Propriedade(Rm.NUMERO_RM));

        propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
        propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
        propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
        propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO_DEPOSITO_PADRAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));


        NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Rm.NUMERO_RM, numeroRm, Filtro.EQUAL));

        try {
            rm = genericDao.selectUnique(propriedades, Rm.class, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        if (rm != null && rm.getEapMultiEmpresa() != null && rm.getEapMultiEmpresa().getCodigoDepositoPadrao() != null) {
            return rm.getEapMultiEmpresa().getCodigoDepositoPadrao();
        }

        return null;
    }

    private String obterPrimeiroDepositoEapMultiEmpresa() {

        GenericDao<EapMultiEmpresa> genericDao = new GenericDao<>();

        List<EapMultiEmpresa> listaEapMultiEmpresa = new ArrayList<>();

        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(EapMultiEmpresa.ID));
        propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO));
        propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO));
        propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO_DEPOSITO_PADRAO));

        try {
            listaEapMultiEmpresa = genericDao.listarByFilter(propriedades, null, EapMultiEmpresa.class, 1, null);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        if(!listaEapMultiEmpresa.isEmpty() && listaEapMultiEmpresa.size() > 0){
            if(listaEapMultiEmpresa.get(0).getCodigoDepositoPadrao() != null) {
                return listaEapMultiEmpresa.get(0).getCodigoDepositoPadrao();
            }else{
                return null;
            }
        }


        return null;
    }


}
