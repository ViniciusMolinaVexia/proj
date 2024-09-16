package br.com.nextage.rmaweb.ws;

import br.com.nextage.rmaweb.entitybean.ConfigAcessoServ;
import br.com.nextage.rmaweb.entitybean.LogInterface;
import br.com.nextage.rmaweb.service.DepositoService;
import br.com.nextage.rmaweb.service.integracao.AreaService;
import br.com.nextage.rmaweb.service.integracao.CentroService;
import br.com.nextage.rmaweb.service.integracao.CompraIntegracaoService;
import br.com.nextage.rmaweb.service.integracao.ConfigAcessoServService;
import br.com.nextage.rmaweb.service.integracao.EstoqueIntegracaoService;
import br.com.nextage.rmaweb.service.integracao.EstornoIntegracaoService;
import br.com.nextage.rmaweb.service.integracao.FornecedorIntegracaoService;
import br.com.nextage.rmaweb.service.integracao.GrupoCompradorService;
import br.com.nextage.rmaweb.service.integracao.GrupoMercadoriaService;
import br.com.nextage.rmaweb.service.integracao.LogInterfaceService;
import br.com.nextage.rmaweb.service.integracao.MaterialIntegracaoService;
import br.com.nextage.rmaweb.service.integracao.RmIntegracaoService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.LogInterfaceVo;
import br.com.nextage.rmaweb.vo.integracao.AreaVO;
import br.com.nextage.rmaweb.vo.integracao.CentroVO;
import br.com.nextage.rmaweb.vo.integracao.CompraVo;
import br.com.nextage.rmaweb.vo.integracao.DepositoResponse;
import br.com.nextage.rmaweb.vo.integracao.DepositoVO;
import br.com.nextage.rmaweb.vo.integracao.EstoqueVo;
import br.com.nextage.rmaweb.vo.integracao.EstornoVo;
import br.com.nextage.rmaweb.vo.integracao.FornecedorVo;
import br.com.nextage.rmaweb.vo.integracao.GrupoCompradorVO;
import br.com.nextage.rmaweb.vo.integracao.GrupoMercadoriaVO;
import br.com.nextage.rmaweb.vo.integracao.MaterialVo;
import br.com.nextage.rmaweb.vo.integracao.MensagemIntegracao;
import br.com.nextage.rmaweb.vo.integracao.RmIntegracaoVo;
import br.com.nextage.rmaweb.ws.receberentradamaterial.ReceberEntradaMaterialRequest;
import br.com.nextage.rmaweb.ws.receberentradamaterial.ReceberEntradaMaterialService;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author l.pordeus
 */
@WebService(serviceName = "IntegracaoWs")
public class IntegracaoWs {

    static Logger log = Logger.getLogger(IntegracaoWs.class);
    /**
     * Operação que realiza cadastro de fornecedores, de acordo com lista de
     * fornecedores passada por parâmetro.
     *
     * @param login
     * @param token
     * @param listaFornecedores
     * @return
     */
    @WebMethod(operationName = "cadastroFornecedores")
    public List<MensagemIntegracao> cadastroFornecedores(@WebParam(name = "login") String login,
            @WebParam(name = "token") String token,
            @WebParam(name = "listaFornecedores") List<FornecedorVo> listaFornecedores) {

        MensagemIntegracao mensagemIntegracao = null;
        List<MensagemIntegracao> lista = new ArrayList<>();
        try {
            ConfigAcessoServService configAcessoServService = new ConfigAcessoServService();
            if (configAcessoServService.selectConfigAcessoServ(login, token, Constantes.CODIGO_INTEGRACAO_FORNECEDOR) != null) {
                FornecedorIntegracaoService fornecedorIntegracaoService = new FornecedorIntegracaoService();
                lista = fornecedorIntegracaoService.salvar(listaFornecedores);
            } else {
                NxResourceBundle resourceBundle = new NxResourceBundle(null);
                mensagemIntegracao = new MensagemIntegracao("E", resourceBundle.getString(Constantes.MSG_USUARIO_NAO_ENCONTRADO_I18N),null);
                lista.add(mensagemIntegracao);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Operação que realiza cadastro de materiais, de acordo com lista de
     * fornecedores passada por parâmetro.
     *
     * @param login
     * @param token
     * @param listaMateriais
     * @return
     */
    @WebMethod(operationName = "cadastroMateriais")
    public List<MensagemIntegracao> cadastroMateriais(@WebParam(name = "login") String login,
            @WebParam(name = "token") String token,
            @WebParam(name = "listaMateriais") List<MaterialVo> listaMateriais) {

        MensagemIntegracao mensagemIntegracao = null;
        List<MensagemIntegracao> lista = new ArrayList<>();


        log.info("INTEGRACAO MATERIAL -> RECEBENDO DADOS");
        log.info("login -> " + login);
        log.info("token -> " + token);

        Optional.ofNullable(listaMateriais).orElse(Collections.EMPTY_LIST).forEach(material -> {
            log.info("MaterialVo -> " + material);
        });

        try {
            ConfigAcessoServService configAcessoServService = new ConfigAcessoServService();
            if (configAcessoServService.selectConfigAcessoServ(login, token, Constantes.CODIGO_INTEGRACAO_MATERIAL) != null) {
                MaterialIntegracaoService materialIntegracaoService = new MaterialIntegracaoService();
                lista = materialIntegracaoService.salvar(listaMateriais);
            } else {
                NxResourceBundle resourceBundle = new NxResourceBundle(null);
                mensagemIntegracao = new MensagemIntegracao("E", resourceBundle.getString(Constantes.MSG_USUARIO_NAO_ENCONTRADO_I18N),null);
                lista.add(mensagemIntegracao);
            }

        } catch (Exception e) {
            log.error("cadastroMateriais error -> " + e);
        }

        return lista;
    }

    /**
     * Operação que recebe uma compra do sap
     *
     * @param login
     * @param token
     * @param listaCompra
     * @return
     */
    @WebMethod(operationName = "receberCompra")
    public List<MensagemIntegracao> receberCompra(@WebParam(name = "login") String login,
            @WebParam(name = "token") String token,
            @WebParam(name = "listaCompra") List<CompraVo> listaCompra) {

        MensagemIntegracao mensagemIntegracao = null;
        List<MensagemIntegracao> lista = new ArrayList<>();
        List<LogInterfaceVo> listaLog = new ArrayList<>();

        log.info("INTEGRACAO COMPRA -> RECEBENDO DADOS");
        log.info("login -> " + login);
        log.info("token -> " + token);

        Optional.ofNullable(listaCompra).orElse(Collections.EMPTY_LIST).forEach(compra -> {
            log.info("CompraVO -> " + compra);
        });

        if (listaCompra == null || listaCompra.isEmpty()) {
            log.info("lista de compra nula ou vazia!");
        }

        try {
            ConfigAcessoServService configAcessoServService = new ConfigAcessoServService();
            if (configAcessoServService.selectConfigAcessoServ(login, token, Constantes.CODIGO_INTEGRACAO_COMPRA) != null) {
                CompraIntegracaoService compraIntegracaoService = new CompraIntegracaoService();
                listaLog = compraIntegracaoService.salvar(login, listaCompra);
            } else {
                NxResourceBundle resourceBundle = new NxResourceBundle(null);
                mensagemIntegracao = new MensagemIntegracao("E", resourceBundle.getString(Constantes.MSG_USUARIO_NAO_ENCONTRADO_I18N),null);
                lista.add(mensagemIntegracao);
            }

            //Monta log interface para ser enviado ao banco
            CompraVo aux;
            String numeroRma="";
            for(LogInterfaceVo lgvo : listaLog){
                if(lgvo.getObjeto()!=null) {
                    aux = (CompraVo) lgvo.getObjeto();
                    numeroRma = aux.getNumeroRma();
                }

                //Recupera logs de retorno para transformar no objeto a ser retornado para o SAP.
                mensagemIntegracao = new MensagemIntegracao(lgvo.getTipoMensagem(),lgvo.getMensagem(), numeroRma);
                lista.add(mensagemIntegracao);
                //Gerando Log de interface
                LogInterfaceService.inserirLog(Constantes.SISTEMA_SAP, Constantes.INTERFACE_COMPRA, login, lgvo);

            }
        } catch (Exception e) {
            log.error("ERRO INTEGRACAO COMPRA -> RECEBENDO DADOS", e);
        }

        log.info("FINALIZANDO INTEGRACAO COMPRA...");
        return lista;
    }

    /**
     * Operação que atualiza o estoque de acordo com o sap
     *
     * @param login
     * @param token
     * @param estoqueVo
     * @return
     */
    @WebMethod(operationName = "atualizarEstoque")
    public List<MensagemIntegracao> atualizarEstoque(@WebParam(name = "login") String login,
            @WebParam(name = "token") String token,
            @WebParam(name = "estoqueVo") EstoqueVo estoqueVo) {

        MensagemIntegracao mensagemIntegracao = new MensagemIntegracao();
        List<MensagemIntegracao> lista = new ArrayList<>();
        List<LogInterfaceVo> listaLog = new ArrayList<>();
        try {
            ConfigAcessoServService configAcessoServService = new ConfigAcessoServService();
            if (configAcessoServService.selectConfigAcessoServ(login, token, Constantes.CODIGO_INTEGRACAO_ESTOQUE) != null) {
                EstoqueIntegracaoService estoqueIntegracaoService = new EstoqueIntegracaoService();
                listaLog = estoqueIntegracaoService.atualizar(login, estoqueVo);
            } else {
                NxResourceBundle resourceBundle = new NxResourceBundle(null);
                LogInterfaceVo logVo = new LogInterfaceVo();
                logVo.setTipoMensagem("E");
                logVo.setMensagem(resourceBundle.getString(Constantes.MSG_USUARIO_NAO_ENCONTRADO_I18N));
                logVo.setObjeto(estoqueVo);
                listaLog.add(logVo);
            }

            //Recupera logs de retorno para transformar no objeto a ser retornado para o SAP.
            LogInterfaceVo logInterfaceVo = new LogInterfaceVo();
            if(listaLog != null && !listaLog.isEmpty() && listaLog.get(0) != null){
                logInterfaceVo = listaLog.get(0);
            }
            mensagemIntegracao = new MensagemIntegracao();
            if (logInterfaceVo != null) {
                if (logInterfaceVo.getTipoMensagem() != null) {
                    mensagemIntegracao.setTipoMensagem(logInterfaceVo.getTipoMensagem());
                }
                if (logInterfaceVo.getMensagem() != null) {
                    mensagemIntegracao.setMensagem(logInterfaceVo.getMensagem());
                }
                if (logInterfaceVo.getObjeto() != null && ((EstoqueVo) logInterfaceVo.getObjeto()).getNumeroRma() != null) {
                    mensagemIntegracao.setCodigo(((EstoqueVo) logInterfaceVo.getObjeto()).getNumeroRma());
                }
            }
            lista.add(mensagemIntegracao);

            //Gerando Log de interface
            LogInterfaceService.inserirLog(Constantes.SISTEMA_SAP, Constantes.INTERFACE_ESTOQUE, login, logInterfaceVo);

        } catch (Exception e) {
            log.error("atualizarEstoque error -> " + e);
        }

        return lista;
    }

    /**
     * Operação que recebe um estorno do sap
     *
     * @param login
     * @param token
     * @param estornoVo
     * @return
     */
    @WebMethod(operationName = "estornoRequisicao")
    public List<MensagemIntegracao> estornoRequisicao(@WebParam(name = "login") String login,
                                                      @WebParam(name = "token") String token,
                                                      @WebParam(name = "estornoVo") List<EstornoVo> estornoVo) {

        MensagemIntegracao mensagemIntegracao = null;
        List<MensagemIntegracao> lista = new ArrayList<>();
        List<LogInterfaceVo> listaLog = new ArrayList<>();
        try {
            ConfigAcessoServService configAcessoServService = new ConfigAcessoServService();
            if (configAcessoServService.selectConfigAcessoServ(login, token, Constantes.CODIGO_INTEGRACAO_ESTORNO) != null) {
                EstornoIntegracaoService estornoIntegracaoService = new EstornoIntegracaoService();
                listaLog = estornoIntegracaoService.salvar(login, estornoVo);
            } else {
                NxResourceBundle resourceBundle = new NxResourceBundle(null);
                LogInterfaceVo logVo = new LogInterfaceVo();
                logVo.setTipoMensagem("E");
                logVo.setMensagem(resourceBundle.getString(Constantes.MSG_USUARIO_NAO_ENCONTRADO_I18N));
                listaLog.add(logVo);
            }

            //Monta log interface para ser enviado ao banco
            EstornoVo aux;
            String numeroRma="";
            for(LogInterfaceVo lgvo : listaLog){
                if(lgvo.getObjeto()!=null) {
                    aux = (EstornoVo) lgvo.getObjeto();
                    numeroRma = aux.getNumeroRma();
                }

                //Recupera logs de retorno para transformar no objeto a ser retornado para o SAP.
                mensagemIntegracao = new MensagemIntegracao(lgvo.getTipoMensagem(),lgvo.getMensagem(),numeroRma);
                lista.add(mensagemIntegracao);
                //Gerando Log de interface
                LogInterfaceService.inserirLog(Constantes.SISTEMA_SAP, Constantes.INTERFACE_ESTORNO, login, lgvo);

            }



        } catch (Exception e) {
            log.error("estornoRequisicao error -> " + e);
        }

        return lista;
    }

    /**
     * Operação que criar uma RMA de acordo com o sap
     *
     * @param login
     * @param token
     * @param rmIntegracaoVo
     * @return
     */
    @WebMethod(operationName = "criarRma")
    public MensagemIntegracao criarRma(@WebParam(name = "login") String login,
                                       @WebParam(name = "token") String token,
                                       @WebParam(name = "rmIntegracaoVo") RmIntegracaoVo rmIntegracaoVo) {

        MensagemIntegracao mensagemIntegracao = null;
        try {
            ConfigAcessoServService configAcessoServService = new ConfigAcessoServService();
            if (configAcessoServService.selectConfigAcessoServ(login, token, Constantes.CODIGO_INTEGRACAO_RM) != null) {
                RmIntegracaoService rmIntegracaoService = new RmIntegracaoService();
                mensagemIntegracao = rmIntegracaoService.criarRm(login, rmIntegracaoVo);
            } else {
                NxResourceBundle resourceBundle = new NxResourceBundle(null);
                mensagemIntegracao = new MensagemIntegracao("E", resourceBundle.getString(Constantes.MSG_USUARIO_NAO_ENCONTRADO_I18N),null);
            }

        } catch (Exception e) {
            log.error("criarRma error -> " + e);
        }

        return mensagemIntegracao;
    }

    /**
     * <b>atualizarCentro</b><br />
     * Responsavel por manter a replicacao das informacoes de Centro
     * @param login
     * @param token
     * @param centroVO
     * @return
     */
    @WebMethod(operationName = "atualizarCentro")
    public List<MensagemIntegracao> atualizarCentro(@WebParam(name = "login") String login,
            												@WebParam(name = "token") String token,
            												@WebParam(name = "centroVo") List<CentroVO> centroVO) {

        log.info("Iniciar atualização centro");
        log.info(login);
        log.info(token);
        log.info(centroVO);

    	List<MensagemIntegracao> msgs = new ArrayList<>();
        List<LogInterfaceVo> logs = null;
        CentroService centroService = CentroService.getInstance();
        ConfigAcessoServService configAcessoServService = new ConfigAcessoServService();
        ConfigAcessoServ configAcessoServ =
          configAcessoServService.selectConfigAcessoServ(login, token, Constantes.CODIGO_INTEGRACAO_RM);

        log.info("configAcessoServ: " + configAcessoServ);

        if (configAcessoServ != null) {
            try {
                logs = centroService.atualizarCentro(centroVO);
                for (LogInterfaceVo log : logs) {
                    msgs.add(new MensagemIntegracao(log.getTipoMensagem(), log.getMensagem(), null));
                }
            } catch (Exception e) {
                log.error(e);
            }
        } else {
            log.warn("Configuração integração não encontrada: " +  Constantes.CODIGO_INTEGRACAO_RM);
            NxResourceBundle resourceBundle = new NxResourceBundle(null);
            msgs.add(new MensagemIntegracao("E", resourceBundle.getString(Constantes.MSG_USUARIO_NAO_ENCONTRADO_I18N),null));
        }
    	return msgs;
    }

    /**
     * <b>atualizarArea</b><br />
     * Responsavel por manter a replicacao das informacoes de Area
     * @param login
     * @param token
     * @param areaVO
     * @return
     */
    @WebMethod(operationName = "atualizarArea")
    public List<MensagemIntegracao> atualizarArea(@WebParam(name = "login") String login,
            												@WebParam(name = "token") String token,
            												@WebParam(name = "area") List<AreaVO> areaVO) {
    	List<MensagemIntegracao> msgs = new ArrayList<>();
        List<LogInterfaceVo> logs = null;
        AreaService areaService = new AreaService();
        ConfigAcessoServService configAcessoServService = new ConfigAcessoServService();
        if (configAcessoServService.selectConfigAcessoServ(login, token, Constantes.CODIGO_INTEGRACAO_RM) != null) {
        	try {
        		logs = areaService.atualizarArea(areaVO);
        		for(LogInterfaceVo log : logs) {
        			msgs.add(new MensagemIntegracao(log.getTipoMensagem(),log.getMensagem(),null));
        		}
        	}catch (Exception e) {
              log.error("atualizarArea error -> " + e);
    		}
        } else {
            NxResourceBundle resourceBundle = new NxResourceBundle(null);
            msgs.add(new MensagemIntegracao("E", resourceBundle.getString(Constantes.MSG_USUARIO_NAO_ENCONTRADO_I18N),null));
        }
    	return msgs;
    }

    /**
     * <b>atualizarGrupoComprador</b><br />
     * Responsavel por manter a replicacao das informacoes de grupos de compradores
     * @param login
     * @param token
     * @param grupoCompradorVO
     * @return
     */
    @WebMethod(operationName = "atualizarGrupoComprador")
    public List<MensagemIntegracao> atualizarGrupoComprador(@WebParam(name = "login") String login,
            												@WebParam(name = "token") String token,
            												@WebParam(name = "grupoComprador") List<GrupoCompradorVO> grupoCompradorVO)
      {
    	List<MensagemIntegracao> msgs = new ArrayList<>();
        List<LogInterfaceVo> logs = null;
        GrupoCompradorService grupoCompradorService = new GrupoCompradorService();
        ConfigAcessoServService configAcessoServService = new ConfigAcessoServService();
        if (configAcessoServService.selectConfigAcessoServ(login, token, Constantes.CODIGO_INTEGRACAO_RM) != null) {
        	try {
        		logs = grupoCompradorService.atualizarGrupoComprador(grupoCompradorVO);
        		for(LogInterfaceVo log : logs) {
        			msgs.add(new MensagemIntegracao(log.getTipoMensagem(),log.getMensagem(),null));
        		}
        	}catch (Exception e) {
              log.error("atualizarGrupoComprador error -> " + e);
    		}
        } else {
            NxResourceBundle resourceBundle = new NxResourceBundle(null);
            msgs.add(new MensagemIntegracao("E", resourceBundle.getString(Constantes.MSG_USUARIO_NAO_ENCONTRADO_I18N),null));
        }
    	return msgs;
    }

    /**
     * <b>atualizarGrupoMercadoria</b><br />
     * Responsavel por manter a replicacao das informacoes de grupos de mercadorias
     * @param login
     * @param token
     * @param grupoMercadoriaVO
     * @return
     */
    @WebMethod(operationName = "atualizarGrupoMercadoria")
    public List<MensagemIntegracao> atualizarGrupoMercadoria(@WebParam(name = "login") String login,
            												@WebParam(name = "token") String token,
            												@WebParam(name = "grupoMercadoria") List<GrupoMercadoriaVO> grupoMercadoriaVO)
      {
    	List<MensagemIntegracao> msgs = new ArrayList<>();
        List<LogInterfaceVo> logs = null;
        GrupoMercadoriaService grupoMercadoriaService = new GrupoMercadoriaService();
        ConfigAcessoServService configAcessoServService = new ConfigAcessoServService();
        if (configAcessoServService.selectConfigAcessoServ(login, token, Constantes.CODIGO_INTEGRACAO_RM) != null) {
        	try {
        		logs = grupoMercadoriaService.atualizarGrupoMercadoria(grupoMercadoriaVO);
        		for(LogInterfaceVo log : logs) {
        			msgs.add(new MensagemIntegracao(log.getTipoMensagem(),log.getMensagem(),null));
        		}
        	}catch (Exception e) {
              log.error("atualizarGrupoMercadoria error -> " + e);
    		}
        } else {
            NxResourceBundle resourceBundle = new NxResourceBundle(null);
            msgs.add(new MensagemIntegracao("E", resourceBundle.getString(Constantes.MSG_USUARIO_NAO_ENCONTRADO_I18N),null));
        }
    	return msgs;
    }


    @WebMethod(operationName = "atualizarDeposito")
    public List<DepositoResponse> atualizarDeposito(@WebParam(name = "login") @XmlElement(required = true) String login,
                                                    @WebParam(name = "token") @XmlElement(required = true) String token,
                                                    @WebParam(name = "depositoVo") @XmlElement(required = true) List<DepositoVO> depositosVO)
      {
        List<DepositoResponse> depositosResponse = new ArrayList<>();

        ConfigAcessoServService configAcessoServService = new ConfigAcessoServService();
        if (configAcessoServService.selectConfigAcessoServ(login, token, Constantes.CODIGO_INTEGRACAO_DEPOSITO) != null) {
            try {
                DepositoService depositoService = new DepositoService();

                // Se existirem depositos invalidos
                List<DepositoResponse> depositosInvalidos = validateDeposito(depositosVO);

                if (!depositosInvalidos.isEmpty()) {
                    depositosResponse.addAll(depositosInvalidos);
                }

                // Processar depositos validos
                List<DepositoVO> depositoValidos = reduceOnlyInvalid(depositosVO);

                if (!depositoValidos.isEmpty()) {
                    final List<DepositoResponse> depositosProcessados = depositoService.atualizarDeposito(depositoValidos);
                    if (!depositosProcessados.isEmpty()) {
                        depositosResponse.addAll(depositosProcessados);
                    }
                }
            } catch (Exception e) {
                log.error("Um erro ocorreu ao tentar processar deposito recebido através do SAP: ", e);
            }
        } else {
            Optional.ofNullable(depositosVO).orElse(Collections.emptyList()).stream().forEach(depositoVO -> {
                depositosResponse.add(new DepositoResponse("E", "Usuário ou senha de integração inválido para interface: " + Constantes.CODIGO_INTEGRACAO_DEPOSITO, Info.INFO_002, depositoVO.getCodigoCentro(), depositoVO.getCodigoDeposito(), depositoVO.getDescricao()));
            });
        }
        // Gravar log de interface
        registerLogInterfaceDeposito(depositosResponse);
        return depositosResponse;
    }

    private List<DepositoResponse> validateDeposito(final List<DepositoVO> depositosVO) {

        List<DepositoVO> invalidos = reduceOnlyValid(depositosVO);

        return Optional.ofNullable(invalidos).orElse(Collections.emptyList()).stream().
                map(depositoInvalido -> new DepositoResponse("E", "Dados obrigatorios não informados", Info.INFO_002, depositoInvalido.getCodigoCentro(), depositoInvalido.getCodigoDeposito(), depositoInvalido.getDescricao())).collect(Collectors.toList());
    }

    private List<DepositoVO> reduceOnlyValid(final List<DepositoVO> depositosVO) {
        return Optional.ofNullable(depositosVO).orElse(Collections.emptyList()).stream().
                filter(this::hasValidDeposito)
                .collect(Collectors.toList());
    }

    private List<DepositoVO> reduceOnlyInvalid(final List<DepositoVO> depositosVO) {
        return Optional.ofNullable(depositosVO).orElse(Collections.emptyList()).stream().
                filter(depositoVO -> !hasValidDeposito(depositoVO))
                .collect(Collectors.toList());
    }


    private boolean hasValidDeposito(final DepositoVO depositoVO) {
        return depositoVO.getCodigoCentro() == null
                || "".equals(depositoVO.getCodigoCentro())
                || depositoVO.getCodigoDeposito() == null
                || "".equals(depositoVO.getCodigoDeposito())
                || depositoVO.getDescricao() == null
                || "".contentEquals(depositoVO.getDescricao());
    }

    private void registerLogInterfaceDeposito(final List<DepositoResponse> depositos) {
        try {
            if (depositos != null && !depositos.isEmpty()) {

                for (DepositoResponse item : depositos) {
                    LogInterface log = new LogInterface();
                    log.setDataHoraInclusaoLog(new Date());
                    log.setMensagem(item.getMensagem());
                    log.setSistema(Constantes.SISTEMA_SAP);
                    log.setInterfaceExec(Constantes.CODIGO_INTEGRACAO_DEPOSITO);
                    log.setNomeClasse(item.getClass().getName());
                    log.setUsuarioInclusao(null);
                    log.setNumRma(null);
                    log.setItemRequisicaoSap(null);
                    log.setNumRequisicaoSap(null);
                    log.setTipoMensagem(item.getTipoMensagem());
                    log.setCodigoDeposito(item.getCodigoDeposito());

                    String json = new Gson().toJson(item);
                    log.setJson(json);

                    //Gerando Log de interface
                    LogInterfaceService.inserirLog(log);
                }
            }
        } catch (Exception e) {
            log.error("Não foi possível registrar log de integração da interface de depositos recebidos do SAP", e);
        }
    }

    /**
     * Operação que recebe movimentação de materiais do SAP.
     *
     * @param login
     * @param token
     * @param materiais
     * @return
     */
    @WebMethod(operationName = "receberEntrada")
    public List<MensagemIntegracao> receberEntrada(@WebParam(name = "login") String login,
                                                   @WebParam(name = "token") String token,
                                                   @WebParam(name = "materiais") List<ReceberEntradaMaterialRequest> materiais) {

        MensagemIntegracao mensagemIntegracao = null;
        List<MensagemIntegracao> lista = new ArrayList<>();
        List<LogInterfaceVo> listaLog = new ArrayList<>();

        log.info("INTEGRACAO RECEBE MOVIMENTACAO DE MATERIAIS SAP -> RECEBENDO DADOS");
        log.info("login -> " + login);
        log.info("token -> " +  token);

        Optional.ofNullable(materiais).orElse(Collections.EMPTY_LIST).forEach(compra -> {
            log.info("CompraVO -> " + compra);
        });

        if (materiais == null || materiais.isEmpty()) {
            log.info("lista de materiais nula ou vazia!");
        }

        try {
            ConfigAcessoServService configAcessoServService = new ConfigAcessoServService();

            if (configAcessoServService.selectConfigAcessoServ(login, token, Constantes.CODIGO_INTEGRACAO_RECEBER_ENTRADA_MATERIAL) != null) {
                ReceberEntradaMaterialService service = new ReceberEntradaMaterialService();
                listaLog = service.salvar(login, materiais);
            } else {
                NxResourceBundle resourceBundle = new NxResourceBundle(null);
                mensagemIntegracao = new MensagemIntegracao("E", resourceBundle.getString(Constantes.MSG_USUARIO_NAO_ENCONTRADO_I18N), null);
                lista.add(mensagemIntegracao);
            }

            //Monta log interface para ser enviado ao banco
            ReceberEntradaMaterialRequest aux;
            String numeroRma = "";
            for (LogInterfaceVo lgvo : listaLog) {
                if (lgvo.getObjeto() != null) {
                    aux = (ReceberEntradaMaterialRequest) lgvo.getObjeto();
                    numeroRma = aux.getNumeroRma();
                }

                //Recupera logs de retorno para transformar no objeto a ser retornado para o SAP.
                mensagemIntegracao = new MensagemIntegracao(lgvo.getTipoMensagem(), lgvo.getMensagem(), numeroRma);
                lista.add(mensagemIntegracao);
                //Gerando Log de interface
                LogInterfaceService.inserirLog(Constantes.SISTEMA_SAP, Constantes.INTERFACE_RECEBER_ENTRADA, login, lgvo);

            }
        } catch (Exception e) {
            log.error("ERRO INTEGRACAO RECEBE MOVIMENTACAO DE MATERIAIS SAP -> RECEBER DADOS", e);
        }

        log.info("FINALIZANDO INTEGRACAO RECEBE MOVIMENTACAO DE MATERIAIS SAP...");
        return lista;
    }
}
