package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.EapMultiEmpresa;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.MaterialDepositoEntradaService;
import br.com.nextage.rmaweb.service.RmMaterialRetiradaService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.MaterialDepositoVo;
import br.com.nextage.rmaweb.vo.PrefixoEquipamentoVo;
import br.com.nextage.rmaweb.vo.*;
import br.com.nextage.rmaweb.ws.cpweb.UnidadeMedida;
import br.com.nextage.rmaweb.ws.cpweb.*;
import br.com.nextage.rmaweb.ws.sap.RetiradaMateriaisSapService;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author l.pordeus
 */
public class SincEquipamentoService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    @Context
    HttpServletRequest request;


    public void sincronizar() {
        try {
            SincRegistroService sincRegistroService = new SincRegistroService();
            MaterialDepositoEntradaService materialDepositoEntradaService = new MaterialDepositoEntradaService();
            List<SincRegistro> listaSincRegistro = sincRegistroService.listar(Constantes.SINC_EQUIPAMENTO);

            if (listaSincRegistro != null) {
                List<Integer> ids = new ArrayList<>();
                for (SincRegistro sr : listaSincRegistro) {
                    ids.add(sr.getIdRegistro());
                }

                List<MaterialDepositoEntrada> entradas = materialDepositoEntradaService.listaMaterialDepositoEntrada(ids);
                for (MaterialDepositoEntrada registro : entradas) {
                    Info info = enviaMaterial(registro,false);

                    //Se o reenvio deu sucesso então desativa o SincRegistro
                    if (Info.INFO_001.equals(info.getCodigo())) {
                        for (SincRegistro sr : listaSincRegistro) {
                            if(sr.getIdRegistro().equals(registro.getMaterialDepositoEntradaId())) {
                                sincRegistroService.desativar(sr);
                                break;
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(getUsuarioNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
    }


    public void sincronizarTransferencia() {
        try {
            SincRegistroService sincRegistroService = new SincRegistroService();
            MaterialDepositoEntradaService materialDepositoEntradaService = new MaterialDepositoEntradaService();
            List<SincRegistro> listaSincRegistro = sincRegistroService.listar(Constantes.SINC_TRANF_EQUIPAMENTO);

            Gson gson = new Gson();
            if (listaSincRegistro != null) {
                for (SincRegistro sr : listaSincRegistro) {
                    try {
                        //Recupera obj do json
                        MaterialTransferenciaVo obj = gson.fromJson(sr.getJson(), MaterialTransferenciaVo.class);

                        Info info = transferirMaterial(obj, false);

                        //Se o reenvio deu sucesso então desativa o SincRegistro
                        if (Info.INFO_001.equals(info.getCodigo())) {
                            sincRegistroService.desativar(sr);
                        }
                    }catch (Exception ex){
                        ex.printStackTrace();
                        logger.error(ResourceLogUtil.createMessageError(getUsuarioNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), ex));
                    }
                }
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(getUsuarioNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
    }

    public Info enviaMaterial(MaterialDepositoEntrada materialDepositoEntrada, boolean gerarSincRegistro) {
        Info info = Info.GetSuccess(Constantes.SUCESSO_OPERACAO_I18N, null);
        SincRegistroService sincRegistroService = new SincRegistroService();
        LogInterfaceVo logVo = null;
        try {

            String confCaminhoUrl = ConfiguracaoSingleton.getConfiguracao().getCaminhoUrlCpweb();
            //Envia para o CPWEB, somente se o caminho estiver na TB_CONFIGURACAO
            if (confCaminhoUrl != null) {
                // Somente material cautelavel vai para o CPWEB
                RmaService rmaService = new RmaService();
                if (materialDepositoEntrada.getMaterialDeposito() != null
                        && materialDepositoEntrada.getMaterialDeposito().getDeposito() != null
                        && materialDepositoEntrada.getMaterialDeposito().getDeposito().getNome() != null
                        && materialDepositoEntrada.getMaterialDeposito().getMaterial() != null
                        && materialDepositoEntrada.getMaterialDeposito().getMaterial().getTipoMaterial() != null
                        && materialDepositoEntrada.getMaterialDeposito().getMaterial().getTipoMaterial().getCodigo() != null
                        && rmaService.verificaEnvioParaCpweb(materialDepositoEntrada.getMaterialDeposito().getMaterial())
                        && materialDepositoEntrada.getQuantidade() > 0.0) {
                    SincEquipamentoVo equipamentoVo = new SincEquipamentoVo();

                    //Configura TipoEquipamento
                    TipoEquipamento tipoEquipamento = new TipoEquipamento();
                    tipoEquipamento.setDescricao(materialDepositoEntrada.getMaterialDeposito().getMaterial().getNome());
                    tipoEquipamento.setCodigo(materialDepositoEntrada.getMaterialDeposito().getMaterial().getCodigo());
                    tipoEquipamento.setHierarquia(materialDepositoEntrada.getMaterialDeposito().getMaterial().getHierarquia());
                    if(tipoEquipamento.getHierarquia() == null || tipoEquipamento.getHierarquia().trim().length() == 0) {
                        MaterialService matService = new MaterialService();
                        tipoEquipamento.setHierarquia(matService.getHierarquiaByMaterial(materialDepositoEntrada.getMaterialDeposito().getMaterial()));
                    }
                    equipamentoVo.setTipoEquipamento(tipoEquipamento);
                    equipamentoVo.setPatrimoniado(materialDepositoEntrada.getMaterialDeposito().getMaterial().getPatrimoniado());

                    //Configura Estoque
                    LocalEstoque estoque = new LocalEstoque();
                    estoque.setDescricao(materialDepositoEntrada.getMaterialDeposito().getDeposito().getNome());
                    estoque.setCodigo(materialDepositoEntrada.getMaterialDeposito().getDeposito().getCodigo());
                    equipamentoVo.setLocalEstoque(estoque);

                    //Configura Estoque
                    equipamentoVo.setQuantidade(materialDepositoEntrada.getQuantidade());

                    //Configura Unidade Medida
                    UnidadeMedida unidadeMedida = new UnidadeMedida();
                    unidadeMedida.setDescricao(materialDepositoEntrada.getMaterialDeposito().getMaterial().getUnidadeMedida().getDescricao());
                    equipamentoVo.setUnidadeMedida(unidadeMedida);

                    if (materialDepositoEntrada.getRm() != null && materialDepositoEntrada.getRm().getNumeroRm() != null) {
                        equipamentoVo.setCodigoRequisicao(materialDepositoEntrada.getRm().getNumeroRm());
                    }

                    // Limpa as propriedades do TOMCAT
                    // Pois a sincronização com o SAP seta estas propriedades
                    // E quando o sistema necessita fazer outra requisição, o mesmo
                    // tenta acessar o proxy que era setado, causando erro
                    System.setProperty("proxySet", "false");
                    System.clearProperty("proxySet");
                    System.clearProperty("http.proxyHost");
                    System.clearProperty("http.proxyPort");
                    System.clearProperty("http.proxyUser");
                    System.clearProperty("http.proxyPassword");

                    Boolean habilitaEapMultiEmpresa = ConfiguracaoSingleton.getConfiguracao().getHabilitaEapMultiEmpresa();
                    if (habilitaEapMultiEmpresa != null && habilitaEapMultiEmpresa == true) {

                        if (materialDepositoEntrada.getMaterialDeposito() != null && materialDepositoEntrada.getMaterialDeposito().getDeposito() != null) {
                            equipamentoVo.getLocalEstoque().setLocalEstoqueEap(obterCodigoEapMultiEmpresaDeposito(materialDepositoEntrada.getMaterialDeposito().getDeposito().getDepositoId()));
                        }
                    }

                    SincEstoque_Service sincEstoque = new SincEstoque_Service(new URL(confCaminhoUrl));
                    br.com.nextage.rmaweb.ws.cpweb.Info infoCp = sincEstoque.getSincEstoquePort().enviaEquipamento(equipamentoVo);

                    if (infoCp != null) {
                        info.setCodigo(infoCp.getCodigo());
                        info.setErro(infoCp.getErro());
                        info.setMensagem(infoCp.getMensagem());
                        info.setTipo(infoCp.getTipo());
                        info.setIdObjetoSalvo(materialDepositoEntrada.getMaterialDepositoEntradaId());
                        //IF criado para não regerar registro na tabela de SincRegistro quando está reenviando os registros que já deram problema
                        if (info.getErro() != null) {
                            if(gerarSincRegistro){
                                sincRegistroService.salvar(info, Constantes.SINC_EQUIPAMENTO, Constantes.SISTEMA_CPWEB);
                            }
                            info.setTipo(Info.TIPO_MSG_DANGER);
                        }else{
                            info.setTipo(Info.TIPO_MSG_SUCCESS);
                        }
                        logVo = new LogInterfaceVo(info);
                    }
                }else {
                    logger.error(ResourceLogUtil.createMessageError(getUsuarioNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), new Exception("Erro ao recuperar configuração da base")));
                    info.setCodigo(Info.INFO_002);
                    info.setTipo(Info.TIPO_MSG_DANGER);
                    info.setMensagem(Constantes.ERRO_OPERACAO_I18N);
                    info.setErro(Constantes.ERRO_OPERACAO_I18N);
                    logVo = new LogInterfaceVo(info);
                }
            }else {
                logger.error(ResourceLogUtil.createMessageError(getUsuarioNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), new Exception("Erro ao recuperar configuração da base")));
                info.setCodigo(Info.INFO_002);
                info.setTipo(Info.TIPO_MSG_DANGER);
                info.setMensagem(Constantes.ERRO_COMUNICACAO_CPWEB_CONTATE_ADMINISTRADOR_I18N);
                info.setErro(Constantes.ERRO_COMUNICACAO_CPWEB_CONTATE_ADMINISTRADOR_I18N);
                logVo = new LogInterfaceVo(info);
            }
        } catch (Exception e) {
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setMensagem(Constantes.ERRO_OPERACAO_I18N);
            info.setErro(e.getMessage());
            info.setIdObjetoSalvo(materialDepositoEntrada.getMaterialDepositoEntradaId());
            //IF criado para não regerar registro na tabela de SincRegistro quando está reenviando os registros que já deram problema
            if(gerarSincRegistro) {
                sincRegistroService.salvar(info, Constantes.SINC_EQUIPAMENTO, Constantes.SISTEMA_CPWEB);
            }
            logger.error(ResourceLogUtil.createMessageError(getUsuarioNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            logVo = new LogInterfaceVo(info);
        }
        if(gerarSincRegistro) {
            logVo.setObjeto(materialDepositoEntrada);
            LogInterfaceService.inserirLog(Constantes.SISTEMA_CPWEB, Constantes.INTERFACE_ENVIAR_MATERIAL, getUsuarioNome(), logVo);
        }
        return info;
    }

    /**
     * Realiza a transferencia de material entre o sistema RMAWEB com o CPWEB
     * através de um web service
     *
     * @param vo
     * @return
     */
    public Info transferirMaterial(MaterialTransferenciaVo vo,boolean gerarSincRegistro) {
        Info info = Info.GetSuccess(Constantes.SUCESSO_OPERACAO_I18N, null);
        SincRegistroService sincRegistroService = new SincRegistroService();
        LogInterfaceVo logVo = null;
        RmaService rmaService = new RmaService();
        try {

            String confCaminhoUrl = ConfiguracaoSingleton.getConfiguracao().getCaminhoUrlCpweb();
            //Envia para o CPWEB, somente se o caminho estiver na TB_CONFIGURACAO
            if (confCaminhoUrl != null && rmaService.verificaEnvioParaCpweb(vo.getMaterial())) {

                //SincEquipamentoVo equipamentoVo = new SincEquipamentoVo();
                SincTransferenciaVo transfVo = new SincTransferenciaVo();
                //Configura Estoque
                LocalEstoque estoqueOrigem = new LocalEstoque();
                estoqueOrigem.setDescricao(vo.getDepositoOrigem().getNome());
                estoqueOrigem.setCodigo(vo.getDepositoOrigem().getCodigo());
                transfVo.setLocalEstoqueOrigem(estoqueOrigem);

                LocalEstoque estoqueDestino = new LocalEstoque();
                estoqueDestino.setDescricao(vo.getDepositoDestino().getNome());
                estoqueDestino.setCodigo(vo.getDepositoDestino().getCodigo());
                transfVo.setLocalEstoqueDestino(estoqueDestino);

                transfVo.setQuantidadeSolicitada(vo.getQuantidadeSolicitada());
                transfVo.setQuantidadeTransferencia(vo.getQuantidadeTransferencia());

                //Configura TipoEquipamento
                TipoEquipamento tipoEquipamento = new TipoEquipamento();
                tipoEquipamento.setDescricao(vo.getMaterial().getNome());
                tipoEquipamento.setCodigo(vo.getMaterial().getCodigo());
                if(vo.getMaterial().getHierarquia() == null || vo.getMaterial().getHierarquia().trim().length() == 0) {
                    MaterialService matService = new MaterialService();
                    tipoEquipamento.setHierarquia(matService.getHierarquiaByMaterial(vo.getMaterial()));
                }else{
                    tipoEquipamento.setHierarquia(vo.getMaterial().getHierarquia());
                }
                transfVo.setTipoEquipamento(tipoEquipamento);
                transfVo.setPatrimoniado(vo.getMaterial().getPatrimoniado());

                //Configura Unidade Medida
                UnidadeMedida unidadeMedida = new UnidadeMedida();
                unidadeMedida.setDescricao(vo.getMaterial().getUnidadeMedida().getDescricao());
                transfVo.setUnidadeMedida(unidadeMedida);

                if (vo.getRmMaterial() != null && vo.getRmMaterial() != null && vo.getRmMaterial().getRm().getNumeroRm() != null) {
                    transfVo.setCodigoRequisicao(vo.getRmMaterial().getRm().getNumeroRm());
                }

                if (vo.getNomeUsuario() != null) {
                    transfVo.setNomeUsuario(vo.getNomeUsuario());
                }

                // Limpa as propriedades do TOMCAT
                // Pois a sincronização com o SAP seta estas propriedades
                // E quando o sistema necessita fazer outra requisição, o mesmo
                // tenta acessar o proxy que era setado, causando erro
                System.setProperty("proxySet", "false");
                System.clearProperty("proxySet");
                System.clearProperty("http.proxyHost");
                System.clearProperty("http.proxyPort");
                System.clearProperty("http.proxyUser");
                System.clearProperty("http.proxyPassword");

                Boolean habilitaEapMultiEmpresa = ConfiguracaoSingleton.getConfiguracao().getHabilitaEapMultiEmpresa();
                if (habilitaEapMultiEmpresa != null && habilitaEapMultiEmpresa == true) {
                    if (vo.getRmMaterial() != null && vo.getRmMaterial().getRm() != null && vo.getRmMaterial().getRm().getRequisitante() != null) {
                        transfVo.setFuncionarioEap(obterCodigoEapMultiEmpresaPessoa(vo.getRmMaterial().getRm().getRequisitante().getPessoaId()));
                    }

                    if (estoqueDestino.getLocalEstoqueId() != null) {
                        transfVo.getLocalEstoqueDestino().setLocalEstoqueEap(obterCodigoEapMultiEmpresaDeposito(estoqueDestino.getLocalEstoqueId()));
                    }
                    if (estoqueOrigem.getLocalEstoqueId() != null) {
                        transfVo.getLocalEstoqueOrigem().setLocalEstoqueEap(obterCodigoEapMultiEmpresaDeposito(estoqueOrigem.getLocalEstoqueId()));
                    }
                }

                SincEstoque_Service sincEstoque = new SincEstoque_Service(new URL(confCaminhoUrl));
                br.com.nextage.rmaweb.ws.cpweb.Info infoCp = sincEstoque.getSincEstoquePort().transferir(transfVo);

                if (infoCp != null) {
                    info.setCodigo(infoCp.getCodigo());
                    info.setErro(infoCp.getErro());
                    info.setMensagem(infoCp.getMensagem());
                    info.setTipo(infoCp.getTipo());
                    if (info.getErro() != null && gerarSincRegistro) {
                        Gson gson = new Gson();
                        sincRegistroService.salvar(info, Constantes.SINC_TRANF_EQUIPAMENTO, Constantes.SISTEMA_CPWEB,null,null,null,gson.toJson(vo),vo.getClass().getName());
                        logger.error(ResourceLogUtil.createMessageError(getUsuarioNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), new Exception(info.getErro())));
                    }
                    logVo = new LogInterfaceVo(info);
                }

            } else {
                logger.error(ResourceLogUtil.createMessageError(getUsuarioNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), new Exception("Erro ao recuperar configuração da base")));
                info.setCodigo(Info.INFO_002);
                info.setTipo(Info.TIPO_MSG_DANGER);
                info.setMensagem(Constantes.ERRO_COMUNICACAO_CPWEB_CONTATE_ADMINISTRADOR_I18N);
                info.setErro(Constantes.ERRO_COMUNICACAO_CPWEB_CONTATE_ADMINISTRADOR_I18N);
                logVo = new LogInterfaceVo(info);
            }
        } catch (Exception e) {
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setMensagem(Constantes.ERRO_COMUNICACAO_CPWEB_CONTATE_ADMINISTRADOR_I18N);
            info.setErro(e.getMessage());
            if(gerarSincRegistro) {
                Gson gson = new Gson();
                sincRegistroService.salvar(info, Constantes.SINC_TRANF_EQUIPAMENTO, Constantes.SISTEMA_CPWEB,null,null,null,gson.toJson(vo),vo.getClass().getName());
            }
            logger.error(ResourceLogUtil.createMessageError(getUsuarioNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            e.printStackTrace();
            logVo = new LogInterfaceVo(info);
        }
        if(gerarSincRegistro) {
            //Gerando Log de interface
            logVo.setObjeto(vo);
            LogInterfaceService.inserirLog(Constantes.SISTEMA_CPWEB, Constantes.INTERFACE_TRANSFERIR_MATERIAL, getUsuarioNome(), logVo);
        }
        return info;
    }

    /**
     * Da entrada de materiais em um determinado deposito
     *
     * @param depositoVo
     * @return
     */
    public Info entradaMaterial(MaterialDepositoVo depositoVo, Boolean enviaCp) {
        Info info = null;
        try {
            MaterialDepositoIntegracaoService materialDepositoIntegracaoService = new MaterialDepositoIntegracaoService();
            MaterialIntegracaoService materialIntegracaoService = new MaterialIntegracaoService();
            DepositoIntegracaoService depositoIntegracaoService = new DepositoIntegracaoService();

            Material material = materialIntegracaoService.searchMaterial(depositoVo.getMaterial());
            Deposito deposito = depositoIntegracaoService.searchDeposito(depositoVo.getDeposito(), depositoVo.getDepositoEap());
            info = materialDepositoIntegracaoService.entrataDeposito(material, deposito, depositoVo.getQtdeInserida(),enviaCp,depositoVo);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(getUsuarioNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    /**
     * Da saida de materiais em um determinado deposito
     *
     * @param depositoVo
     * @return
     */
    public Info saidaMaterial(MaterialDepositoVo depositoVo) {
        Info info = null;
        try {
            MaterialDepositoIntegracaoService materialDepositoIntegracaoService = new MaterialDepositoIntegracaoService();
            MaterialIntegracaoService materialIntegracaoService = new MaterialIntegracaoService();
            DepositoIntegracaoService depositoIntegracaoService = new DepositoIntegracaoService();

            Material material = materialIntegracaoService.searchMaterial(depositoVo.getMaterial());
            Deposito deposito = depositoIntegracaoService.searchDeposito(depositoVo.getDeposito(), depositoVo.getDepositoEap());
            if (depositoVo.getReaproveitadosEpi() != null) {
                info = materialDepositoIntegracaoService.retiradaDeposito(material, deposito, depositoVo.getQtdeInserida(),
                        depositoVo.getCodigoRequisicao(), depositoVo.getRetiradoPor(), depositoVo.getReaproveitadosEpi(), depositoVo);
            } else {
                info = materialDepositoIntegracaoService.retiradaDeposito(material, deposito, depositoVo.getQtdeInserida(),
                        depositoVo.getCodigoRequisicao(), depositoVo.getRetiradoPor(), false, depositoVo);
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(getUsuarioNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    /**
     * Realiza a buscar dos patrimonios no CPWEB com equipamentos disponiveis em um Like de acordo com a string passada
     *
     * @param prefixoEquipamentoVo
     * @returnList<String> lista = new ArrayList<>();
     */
    public Info listaPrefixoEquipamento(PrefixoEquipamentoVo prefixoEquipamentoVo) {
        SincRegistroService sincRegistroService = new SincRegistroService();
        List<String> lista = new ArrayList<>();
        Info info = new Info();

        Boolean habilitaEapMultiEmpresa = ConfiguracaoSingleton.getConfiguracao().getHabilitaEapMultiEmpresa();

        try {
            String confCaminhoUrl = ConfiguracaoSingleton.getConfiguracao().getCaminhoUrlCpweb();
            if (confCaminhoUrl != null) {
                SincEstoque_Service sincEstoque = new SincEstoque_Service(new URL(confCaminhoUrl));
                br.com.nextage.rmaweb.ws.cpweb.PrefixoEquipamentoVo vo = new br.com.nextage.rmaweb.ws.cpweb.PrefixoEquipamentoVo();
                if (habilitaEapMultiEmpresa != null && habilitaEapMultiEmpresa == true) {
                    vo.setEapDeposito(prefixoEquipamentoVo.getEapDeposito());
                }
                vo.setCodigoEquipamento(prefixoEquipamentoVo.getCodigoEquipamento());
                vo.setPrefixoEquipamento(prefixoEquipamentoVo.getPrefixoEquipamento());
                vo.setCodigoDeposito(prefixoEquipamentoVo.getCodigoDeposito());
                br.com.nextage.rmaweb.ws.cpweb.Info infoCp = sincEstoque.getSincEstoquePort().listarPatrimonio(vo);
                if(infoCp.getErro() != null){
                    info.setErro(infoCp.getErro());
                }else{
                    info.setObjeto(infoCp.getObjeto().toString());
                }
            }

        } catch (Exception e) {
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setMensagem(Constantes.ERRO_OPERACAO_I18N);
            if(info.getErro() == null) {
                info.setErro(e.getMessage());
            }
            logger.error(ResourceLogUtil.createMessageError(getUsuarioNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return info;
    }

    /**
     * Acumula as quantidades da Rm vinda do CPWEB, quando fechar a quantidade, o mesmo finaliza.
     * Gero a retirada do mesmo também.
     *
     * @param materialDepositoVo
     * @return
     */
    public Info finalizarRm(MaterialDepositoVo materialDepositoVo) {
        Info info = new Info();
        RmMaterialRetirada rmMaterialRetirada = new RmMaterialRetirada();
        RmMaterial rmMaterial = new RmMaterial();
        RmaService rmaService = new RmaService();
        GenericDao<RmMaterialRetirada> genericDao = new GenericDao<>();
        List<RmMaterialRetirada> listaRmMaterialRetirada = new ArrayList<>();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(null));

        //Recupero a RmMaterial para realizar uma retirada
        rmMaterial = rmaService.recuperarRmMaterial(materialDepositoVo.getIdentificadorRmMaterial());

        try {
            RmMaterialRetirada retirada = new RmMaterialRetirada();
            retirada.setPreRetirada(Boolean.TRUE);
            retirada.setDataRetirada(new Date());
            retirada.setQuantidade(1.0);
            retirada.setRetiradoPor(rmaService.getPessoaByCF(rmMaterial.getCfResponsavel()));
            if (retirada.getRetiradoPor() == null) {
                retirada.setRetiradoPor(rmMaterial.getPessoaResponsavel());
            }
            retirada.setRmMaterial(rmMaterial);

            genericDao.beginTransaction();

            RmMaterialRetiradaService service = new RmMaterialRetiradaService();
            info = service.preRetirada(retirada, genericDao, true, true);

            boolean success = false;
            if (info.getCodigo().equals(Info.INFO_001)) {
                success = true;
            }
            if (success) {
                genericDao.commitCurrentTransaction();
            } else {
                genericDao.rollbackCurrentTransaction();
                return info;
            }

            //Verifico se já possui alguma retirada para este item, para verificar se o mesmo vai finalizar ou não
            listaRmMaterialRetirada = rmaService.recuperarRmRetirada(materialDepositoVo.getIdentificadorRmMaterial());

            //Verifico a quantidade que possui juntando todas as rmMateriaLRetiradas
            Double quantidadeRmMaterialRetirada = 0.0;
            for (RmMaterialRetirada rmmr : listaRmMaterialRetirada) {
                quantidadeRmMaterialRetirada = quantidadeRmMaterialRetirada + rmmr.getQuantidade();
            }

            //Listo o Status Atual
            RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService();
            RmMaterialStatus rmMaterialStatusAtual = new RmMaterialStatus();
            rmMaterialStatusAtual = rmMaterialStatusService.listarStatusAtual(rmMaterial);

            String observacaoStatus = (Util.setParamsLabel(rb.getString("label_retirado_x_materiais"), 1.0, rmMaterial.getMaterial().getUnidadeMedida().getSigla()));

            genericDao.beginTransaction();
            if (quantidadeRmMaterialRetirada.equals(rmMaterial.getQuantidade())) {
                rmMaterialStatusService.finalizarStatus(rmMaterialStatusAtual, genericDao, new Date());
                rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_RETIRADO_PARCIALMENTE, genericDao, new Date(), observacaoStatus, materialDepositoVo.getRetiradoPor());
                rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_FINALIZADO, genericDao, new Date(), null, materialDepositoVo.getRetiradoPor());

            } else {
                rmMaterialStatusService.finalizarStatus(rmMaterialStatusAtual, genericDao, new Date());
                rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_RETIRADO_PARCIALMENTE, genericDao, new Date(), observacaoStatus, materialDepositoVo.getRetiradoPor());
            }
            genericDao.commitCurrentTransaction();

            //Realiza o Envio para o SAP
            RetiradaMateriaisSapService retiradaMateriaisSapService = new RetiradaMateriaisSapService();
            EnviaRetiradaVo enviaRetiradaVo = new EnviaRetiradaVo();
            enviaRetiradaVo.setDeposito(rmaService.recuperarDeposito(materialDepositoVo.getDeposito()));
            enviaRetiradaVo.setMaterial(rmMaterial.getMaterial());
            enviaRetiradaVo.setRmMaterial(rmMaterial);
            enviaRetiradaVo.setUnidadeMedida(rmMaterial.getMaterial().getUnidadeMedida());
            enviaRetiradaVo.setQuantidade(1.0);
            enviaRetiradaVo.setRecebidoCp(true);
            info = retiradaMateriaisSapService.enviarRetiradaByVo(enviaRetiradaVo);

        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setMensagem(Constantes.ERRO_OPERACAO_I18N);
        }
        return info;
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
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        if (deposito != null && deposito.getEapMultiEmpresa() != null && deposito.getEapMultiEmpresa().getCodigo() != null) {
            return deposito.getEapMultiEmpresa().getCodigo();
        }

        return null;
    }

    private String obterCodigoEapMultiEmpresaPessoa(Integer pessoaId) {

        if (pessoaId == null) {
            return null;
        }

        GenericDao<Pessoa> genericDao = new GenericDao<>();

        Pessoa pessoa = new Pessoa();

        String aliasEapMultiEmpresa = NxCriterion.montaAlias(Pessoa.ALIAS_CLASSE, Pessoa.EAP_MULTI_EMPRESA);

        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
        propriedades.add(new Propriedade(Pessoa.NOME));
        propriedades.add(new Propriedade(Pessoa.REFERENCIA));

        propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
        propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
        propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));

        NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, pessoaId, Filtro.EQUAL));

        try {
            pessoa = genericDao.selectUnique(propriedades, Pessoa.class, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        if (pessoa != null && pessoa.getEapMultiEmpresa() != null && pessoa.getEapMultiEmpresa().getCodigo() != null) {
            return pessoa.getEapMultiEmpresa().getCodigo();
        }

        return null;
    }

    public String getUsuarioNome() {
        String nome = LoginService.getUsuarioLogado(request).getNome();
        if(request == null){
            nome = "LISTENER";
        }
        return nome;
    }
}


