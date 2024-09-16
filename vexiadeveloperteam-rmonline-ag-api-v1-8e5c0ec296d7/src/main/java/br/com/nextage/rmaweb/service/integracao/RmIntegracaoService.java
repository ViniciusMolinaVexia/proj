package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.controller.notificacao.email.enviar.EmailService;
import br.com.nextage.rmaweb.dao.TipoRequisicaoDAO;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.integracao.MensagemIntegracao;
import br.com.nextage.rmaweb.vo.integracao.RmIntegracaoVo;
import br.com.nextage.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Lucas Heitor
 */
public class RmIntegracaoService {

    @Context
    HttpServletRequest request;

    @Inject
    private EmailService emailService;

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);
    
    private TipoRequisicaoDAO tipoRequisicaoDAO = TipoRequisicaoDAO.getInstance();

    public MensagemIntegracao criarRm(String login, RmIntegracaoVo rmIntegracaoVo) {
        NxResourceBundle rb = new NxResourceBundle(null);
        MensagemIntegracao mensagemIntegracao = new MensagemIntegracao();
        GenericDao<Rm> genericDao = new GenericDao<>();

        Configuracao conf = ConfiguracaoSingleton.getConfiguracao();

        try {
            //Separo as letras o usuário cadastrando e do requisitante, pois, quando vem do SAP, por exemplo, o mesmo vem com o CF
            //e a referencia da pessoa não possui CF e sim apenas os números.
            if (rmIntegracaoVo.getUsuarioCadastrante() != null) {
                rmIntegracaoVo.setUsuarioCadastrante(rmIntegracaoVo.getUsuarioCadastrante().replaceAll("\\D", ""));
            }
            if (rmIntegracaoVo.getRequisitante() != null) {
                rmIntegracaoVo.setRequisitante(rmIntegracaoVo.getRequisitante().replaceAll("\\D", ""));
            }

            String centro = null;
            EapMultiEmpresa eap = null;
            if(conf.getHabilitaEapMultiEmpresa() != null && conf.getHabilitaEapMultiEmpresa()){
                centro = rmIntegracaoVo.getCentro();
                eap = recuperarEapMultiEmpresa(centro);
            }


            Rm rm = new Rm();
            rm.setEapMultiEmpresa(eap);
            if (rmIntegracaoVo.getTipoRequisicao() == null || rmIntegracaoVo.getTipoRequisicao().isEmpty()) {
                mensagemIntegracao.setTipoMensagem("E");
                mensagemIntegracao.setMensagem(rb.getString("msg_campo_tipo_requisicao_nao_informado"));
                return mensagemIntegracao;
            }
            genericDao.beginTransaction();
            rm.setTipoRequisicao(tipoRequisicaoDAO.getTipoRequisicaoPorCodigo(rmIntegracaoVo.getTipoRequisicao()));
            if (rm.getTipoRequisicao() != null) {
            	
            } else {
                mensagemIntegracao.setTipoMensagem("E");
                mensagemIntegracao.setMensagem(rb.getString("msg_tipo_requisicao_nao_encontrado"));
            }

            //Se caso em algum campo obrigatorio não estiver o mesmo irar setar o tipo da msg como E
            //Então faço o rollback dos persist realizados durante o procedimento.
            if (mensagemIntegracao.getTipoMensagem() != null && mensagemIntegracao.getTipoMensagem().equals("E")) {
                genericDao.rollbackCurrentTransaction();
            } else {
                mensagemIntegracao.setMensagem(rb.getString("msg_registros_salvo_sucesso"));
                mensagemIntegracao.setCodigo(rm.getNumeroRm());
            }

        } catch (Exception e) {
            e.printStackTrace();
            genericDao.rollbackCurrentTransaction();
            mensagemIntegracao.setTipoMensagem("E");
            mensagemIntegracao.setMensagem(rb.getString("label_erro_ao_importar_registro"));
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return mensagemIntegracao;
    }

    private TipoMaterial recuperarTipoMaterial(String tipo) {
        TipoMaterial tipoMaterial = new TipoMaterial();
        List<TipoMaterial> lista = new ArrayList<>();
        GenericDao<TipoMaterial> genericDao = new GenericDao<>();
        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID));
            propriedades.add(new Propriedade(TipoMaterial.DESCRICAO));
            propriedades.add(new Propriedade(TipoMaterial.CODIGO));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(TipoMaterial.CODIGO, tipo, Filtro.EQUAL));

            lista = genericDao.listarByFilter(propriedades, null, TipoMaterial.class, Constantes.NO_LIMIT, nxCriterion);

            if (!lista.isEmpty()) {
                tipoMaterial = lista.get(0);
            }
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return tipoMaterial;
    }

    private Pessoa recuperarPessoa(String referencia) {
        Pessoa pessoa = new Pessoa();
        List<Pessoa> lista = new ArrayList<>();
        GenericDao<Pessoa> genericDao = new GenericDao<>();
        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
            propriedades.add(new Propriedade(Pessoa.NOME));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.REFERENCIA, referencia, Filtro.EQUAL));

            lista = genericDao.listarByFilter(propriedades, null, Pessoa.class, Constantes.NO_LIMIT, nxCriterion);

            if (!lista.isEmpty()) {
                pessoa = lista.get(0);
            }
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return pessoa;
    }

    private Usuario recuperarUsuario(String referencia, String centro) {
        Usuario usuario = new Usuario();
        GenericDao<Usuario> genericDao = new GenericDao<>();
        List<Usuario> lista = new ArrayList<>();

        String aliasPessoa = NxCriterion.montaAlias(Usuario.ALIAS_CLASSE, Usuario.PESSOA);

        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Usuario.USUARIO_ID));
            propriedades.add(new Propriedade(Usuario.NOME));

            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasPessoa));

            String aliasEapMultiEmpresa = NxCriterion.montaAlias(Usuario.ALIAS_CLASSE, Usuario.PESSOA, Pessoa.EAP_MULTI_EMPRESA);
            propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.REFERENCIA, referencia, Filtro.EQUAL, aliasPessoa));
            if(centro != null){
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(EapMultiEmpresa.CENTRO, centro, Filtro.EQUAL, aliasEapMultiEmpresa)));
            }

            lista = genericDao.listarByFilter(propriedades, null, Usuario.class, Constantes.NO_LIMIT, nxCriterion);

            if (!lista.isEmpty()) {
                usuario = lista.get(0);
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return usuario;
    }

    private Deposito recuperarDeposito(String codigo,EapMultiEmpresa eap) {
        Deposito deposito = new Deposito();
        GenericDao<Deposito> genericDao = new GenericDao<>();
        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));
            propriedades.add(new Propriedade(Deposito.NOME));
            propriedades.add(new Propriedade(Deposito.CODIGO));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Deposito.CODIGO, codigo, Filtro.EQUAL));

            deposito = genericDao.selectUnique(propriedades, Deposito.class, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return deposito;
    }

    private EapMultiEmpresa recuperarEapMultiEmpresa(String centroCusto) {
        EapMultiEmpresa eap = null;
        GenericDao<EapMultiEmpresa> genericDao = new GenericDao<>();
        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(EapMultiEmpresa.ID));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO));
            propriedades.add(new Propriedade(EapMultiEmpresa.CENTRO));
            propriedades.add(new Propriedade(EapMultiEmpresa.APROVACAO_COORDENADOR));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO));
            propriedades.add(new Propriedade(EapMultiEmpresa.APROVACAO_EQUIPE_CUSTOS));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(EapMultiEmpresa.CENTRO, centroCusto, Filtro.EQUAL));

            eap = genericDao.selectUnique(propriedades, EapMultiEmpresa.class, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return eap;
    }

    private Material recuperarMaterial(String codigo) {
        Material material = new Material();
        GenericDao<Material> genericDao = new GenericDao<>();
        try {
            String aliasTipoMaterial = NxCriterion.montaAlias(Material.ALIAS_CLASSE, Material.TIPO_MATERIAL);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Material.MATERIAL_ID));
            propriedades.add(new Propriedade(Material.CODIGO));
            propriedades.add(new Propriedade(Material.NOME));
            propriedades.add(new Propriedade(Material.HIERARQUIA));

            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.DESCRICAO, TipoMaterial.class, aliasTipoMaterial));


            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Material.CODIGO, codigo, Filtro.EQUAL));

            material = genericDao.selectUnique(propriedades, Material.class, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return material;
    }

    private Prioridade verificaPrioridade(Date dataAplicacao) {
        Prioridade prioridade = new Prioridade();
        NxResourceBundle rb = new NxResourceBundle(null);

        try {
            Date dataEmissao = new Date();

            //Busca a prioridade da requisição de acordo com a configuração determinada de dias
            List<Prioridade> prioridades = listarPrioridadeConf();
            Integer diferencDiasPrioridade = Util.diferencaDias(dataEmissao, dataAplicacao) + 1;
            for (Prioridade prioridadeRm : prioridades) {
                if (prioridadeRm.getConfDiasIniPrioridade() != null && prioridadeRm.getConfDiasFimPrioridade() != null) {
                    if (prioridadeRm.getConfDiasIniPrioridade() <= diferencDiasPrioridade &&
                            prioridadeRm.getConfDiasFimPrioridade() >= diferencDiasPrioridade) {
                        prioridade = prioridadeRm;
                        break;
                    }
                }
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return prioridade;
    }

    /**
     * Busca uma lista de Prioridades e suas configurações de dias.
     * [Marlos Morbis Novo]
     **/
    private List<Prioridade> listarPrioridadeConf() {
        List<Prioridade> list = new ArrayList<>();

        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Prioridade.PRIORIDADE_ID));
            propriedades.add(new Propriedade(Prioridade.CONF_DIAS_INI_PRIORIDADE));
            propriedades.add(new Propriedade(Prioridade.CONF_DIAS_FIM_PRIORIDADE));
            propriedades.add(new Propriedade(Prioridade.CODIGO));
            propriedades.add(new Propriedade(Prioridade.DESCRICAO));
            propriedades.add(new Propriedade(Prioridade.CONF_DIAS_PREV_ENTREGA));

            List<NxOrder> orders = new ArrayList<>();
            orders.add(new NxOrder(Prioridade.PRIORIDADE_ID, NxOrder.NX_ORDER.DESC));

            GenericDao<Prioridade> genericDao = new GenericDao<>();

            list = genericDao.listarByFilter(propriedades, orders, Prioridade.class, Constantes.NO_LIMIT, null);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return list;
    }

    /**
     * Busca o numero atual da rm para gerar o proximo numero
     *
     * @return
     */
    public Integer buscarNumeroRmAtual() {
        Integer numero = null;

        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Rm.RM_ID));
            propriedades.add(new Propriedade(Rm.NUMERO_RM));

            List<NxOrder> orders = new ArrayList<>();
            orders.add(new NxOrder(Rm.RM_ID, NxOrder.NX_ORDER.DESC));

            GenericDao genericDao = new GenericDao();

            Rm rm = (Rm) genericDao.listarByFilter(propriedades, orders, Rm.class, 1, null).get(0);
            numero = Integer.valueOf(rm.getNumeroRm());

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return numero;
    }

    private Info enviarParaAprovacao(List<RmMaterial> listaRmMateriais, Pessoa aprovador, Rm rm, GenericDao<?> genericDao) {
        Info info = new Info();
        Date data = new Date();
        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);
        List<RmMaterialStatus> materiaisStatusAtualNew = new ArrayList<>();
        List<Propriedade> propriedades = new ArrayList<>();

        try {
            RmAprovador rmAprovador = new RmAprovador();
            rmAprovador.setRm(rm);
            rmAprovador.setAprovador(aprovador);
            rmAprovador.setAprovadorVez(Boolean.TRUE);
            rmAprovador.setOrdem(1);

            Boolean enviaDiretoGerente = false;
            Boolean habilitaAprovacaoCoordenador = true;
            if(rm != null && rm.getRmId() > 0){
                if(rm != null && rm.getEapMultiEmpresa() != null){
                    if( rm.getEapMultiEmpresa().getAprovacaoCoordenador() != null && rm.getEapMultiEmpresa().getAprovacaoCoordenador() == false ) {
                        habilitaAprovacaoCoordenador = false;
                    }
                }else{
                    EapMultiEmpresaService eapService = new EapMultiEmpresaService();
                    EapMultiEmpresa eap = eapService.getFirstEapMultiEmpresa();
                    if(eap != null && eap.getAprovacaoCoordenador() != null && eap.getAprovacaoCoordenador() == false ){
                        habilitaAprovacaoCoordenador = false;
                    }
                }
            }
            enviaDiretoGerente = !habilitaAprovacaoCoordenador;

            if(enviaDiretoGerente){
                rmAprovador.setTipoAprovador(Constantes.TIPO_ATUACAO_GERENTE_AREA);
            }else{
                rmAprovador.setTipoAprovador(Constantes.TIPO_ATUACAO_COORDENADOR);
            }

            genericDao.beginTransaction();
            if (rm.getDataReprovacao() != null) {
                rm.setDataReprovacao(null);
                rm.setReprovadoPor(null);

                propriedades.add(new Propriedade(RmMaterialStatus.IS_HISTORICO));

                //Gera um status para cada material
                for (RmMaterial rmMaterial : listaRmMateriais) {

                    List<RmMaterialStatus> listaMaterialStatus = rmMaterialStatusService.listarMaterialStatus(rmMaterial, Boolean.TRUE);

                    //Caso a requisicao tenha sido reprovada,
                    //atualizo os status antigos como historico
                    for (RmMaterialStatus materialStatus : listaMaterialStatus) {
                        if (!materialStatus.getStatus().getCodigo().equals(Constantes.STATUS_RM_MATERIAL_CADASTRADA)) {
                            materialStatus.setHistorico(Boolean.TRUE);
                            //genericDao.updateWithCurrentTransaction(materialStatus, propriedades);
                            materiaisStatusAtualNew.add(materialStatus);
                        }
                    }

                    //Gero o novo status de enviado para aprovacao
                    RmMaterialStatus ms = rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_ENVIADA_APROVACAO, null, data, null, "Web Service");
                    materiaisStatusAtualNew.add(ms);
                }
            }

            propriedades.clear();
            propriedades.add(new Propriedade(Rm.DATA_ENVIO_APROVACAO));
            propriedades.add(new Propriedade(Rm.JUSTIFICATIVA_ENVIO_APROVACAO));
            propriedades.add(new Propriedade(Rm.JUST_MATERIAIS_CAUTELADOS));
            propriedades.add(new Propriedade(Rm.DATA_REPROVACAO));
            propriedades.add(new Propriedade(Rm.REPROVADO_POR));
            if(enviaDiretoGerente){
                propriedades.add(new Propriedade(Rm.GERENTE_AREA));
                rm.setGerenteArea(aprovador);
            }else{
                propriedades.add(new Propriedade(Rm.COORDENADOR));
                rm.setCoordenador(aprovador);
            }

            rm.setDataEnvioAprovacao(data);
            genericDao.updateWithCurrentTransaction(rm, propriedades);

            genericDao.persistWithCurrentTransaction(rmAprovador);

            propriedades.clear();
            propriedades.add(new Propriedade(RmMaterialStatus.IS_HISTORICO));
            for (RmMaterialStatus statusNovo : materiaisStatusAtualNew) {
                if (statusNovo.getId() != null) {
                    genericDao.updateWithCurrentTransaction(statusNovo, propriedades);
                } else {
                    genericDao.persistWithCurrentTransaction(statusNovo);
                }
            }

            for (RmMaterial material : listaRmMateriais) {
                if(enviaDiretoGerente) {
                    rmMaterialStatusService.gerarStatus(material, Constantes.STATUS_RM_MATERIAL_APROVADA_GA, genericDao, null, null, "Web Service");
                }else{
                    rmMaterialStatusService.gerarStatus(material, Constantes.STATUS_RM_MATERIAL_APROVADA_CO, genericDao, null, null, "Web Service");
                }
            }

            enviarEmailAprovador(rmAprovador);

            info.setCodigo(Info.INFO_001);
            info.setErro(null);
            info.setMensagem(Constantes.MSG_REQUISICAO_ENVIADO_APROVACAO_SUCESSO_I18N);
            info.setTipo(Info.TIPO_MSG_SUCCESS);

        } catch (Exception e) {
            info.setCodigo(Info.INFO_002);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.ERRO_OPERACAO_I18N);
            info.setTipo(Info.TIPO_MSG_DANGER);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    /**
     * Envia email ao aprovador, com informacoes da rm e o link de aprovacao
     *
     * @param rmAprovador
     */
    private void enviarEmailAprovador(RmAprovador rmAprovador) {
        String email = "";
        String mensagemEmail = "";
        String tituloEmail;
        String link;

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), RmAprovador.class.getName(), Util.getNomeMetodoAtual(), rmAprovador, null));

            email = rmAprovador.getAprovador().getEmail();

            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
            Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();

            tituloEmail = Util.setParamsLabel(rb.getString("msg_titulo_email_aprovacao_rma"), rmAprovador.getRm().getPrioridade().getDescricao());

            if (configuracao.getPrefUrlExternaSist() != null && !configuracao.getPrefUrlExternaSist().isEmpty()) {
                link = configuracao.getPrefUrlExternaSist();
            } else if (request != null) {
                link = LoginService.getUrlServidor(request);
            } else {
                link = "";
            }

            //String parametro = Criptografia.cript("id=" + rmAprovador.getId());
            //link += request.getContextPath() + "/html5/rma/rmAprovacao.html#aprovacaoRma?id=" + parametro;
//            if(request != null) {
//                link += request.getContextPath() + "/html5/rma/index.html";
//            }else{
//                link = "";
//            }
            mensagemEmail = Util.setParamsLabel(rb.getString("msg_email_rma_envio"), rmAprovador.getRm().getNumeroRm(), rmAprovador.getRm().getRequisitante().getNome(), link);

            if(email != null && Email2.validaEmail(email)) {

                final String subject = tituloEmail;
                final String recipients = email;
                final String body = mensagemEmail;
                this.emailService.enviarEmail(subject, recipients, body);
            }
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), RmAprovador.class.getName(), Util.getNomeMetodoAtual(), rmAprovador, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
    }

    private Pessoa recuperarCoordenador(String referencia, String centro) {
        Pessoa pessoa = new Pessoa();
        GenericDao<Pessoa> genericDao = new GenericDao<>();
        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
            propriedades.add(new Propriedade(Pessoa.NOME));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA));
            propriedades.add(new Propriedade(Pessoa.TIPO_ATUACAO));
            propriedades.add(new Propriedade(Pessoa.EMAIL));

            String aliasEapMultiEmpresa = NxCriterion.montaAlias(Pessoa.ALIAS_CLASSE, Pessoa.EAP_MULTI_EMPRESA);
            propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.REFERENCIA, referencia, Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Pessoa.TIPO_ATUACAO, Constantes.TIPO_ATUACAO_COORDENADOR, Filtro.LIKE)));

            if(centro != null){
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(EapMultiEmpresa.CENTRO, centro, Filtro.EQUAL, aliasEapMultiEmpresa)));
            }

            pessoa = genericDao.selectUnique(propriedades, Pessoa.class, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return pessoa;
    }

    /**
     * Verifica se já existe uma RmMaterial cadastrada com as mesmas informações, Nº Requisição, EAP e material
     *
     * @param rmm
     */
    private Boolean existeRmMaterial(RmMaterial rmm) {
       Boolean existe = false;
        GenericDao<RmMaterial> genericDao = new GenericDao<>();
        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_REQUISICAO_SAP));

            String aliasRM = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRM));

            String aliasEapMultiEmpresa = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM,Rm.EAP_MULTI_EMPRESA);
            propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.NUMERO_REQUISICAO_SAP, rmm.getNumeroRequisicaoSap(), Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterial.ITEM_REQUISICAO_SAP, rmm.getItemRequisicaoSap(), Filtro.EQUAL)));

            if(rmm.getRm() != null && rmm.getRm().getEapMultiEmpresa() != null && rmm.getRm().getEapMultiEmpresa().getId() != null){
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(EapMultiEmpresa.ID, rmm.getRm().getEapMultiEmpresa().getId(), Filtro.EQUAL, aliasEapMultiEmpresa)));
            }else{
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(EapMultiEmpresa.ID, null, Filtro.IS_NULL, aliasEapMultiEmpresa)));
            }

            List<RmMaterial> lista = genericDao.listarByFilter(propriedades,null,RmMaterial.class,1,nxCriterion);
            if(lista != null && lista.size() > 0){
                existe = true;
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return existe;
    }


}
