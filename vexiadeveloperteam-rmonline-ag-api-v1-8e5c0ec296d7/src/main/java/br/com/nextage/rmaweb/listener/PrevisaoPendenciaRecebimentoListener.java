package br.com.nextage.rmaweb.listener;

import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.controller.notificacao.email.enviar.EmailService;
import br.com.nextage.rmaweb.entitybean.Configuracao;
import br.com.nextage.rmaweb.entitybean.Deposito;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.VwRmMaterial;
import br.com.nextage.rmaweb.filtro.FiltroPrevisaoPendenciaRecebimento;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.integracao.PrevisaoPendenciaRecebimentoService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.*;
import br.com.nextage.util.vo.ArquivoVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;
import javax.inject.Inject;

/**
 * @brief Classe teste
 * @author Alyson X. Leite <a.leite@nextage.com.br>
 * @date 05/11/2015
 */
public class PrevisaoPendenciaRecebimentoListener extends GenericImportacaoListener {

    private final Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    @Inject
    private EmailService emailService;

    public PrevisaoPendenciaRecebimentoListener() {
        super("EnviaEmailPrevisaoPendenciaRecebimento");
    }

    @Override
    protected void executar() {
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
            PrevisaoPendenciaRecebimentoService service = new PrevisaoPendenciaRecebimentoService();
            //lista os depositos
            HashMap<Pessoa, List<Deposito>> hashDepositos = listarDepositos();
            if (hashDepositos != null && !hashDepositos.isEmpty()) {
                //pega a coniguração
                Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
                //cria a data previsaão chegada
                Date dataPrevisao = Util.addDia(new Date(), conf.getDiasAlertaPrevisao());
                for (Pessoa pessoa : hashDepositos.keySet()) {
                    if (pessoa.getEmail() != null) {
                        //lista as pendencia
                        FiltroPrevisaoPendenciaRecebimento filtro = new FiltroPrevisaoPendenciaRecebimento();
                        List<Deposito> depositos = hashDepositos.get(pessoa);
                        filtro.setDeposito(depositos);
                        filtro.setIdsDeposito(new ArrayList<Integer>());
                        for (Deposito dep : depositos) {
                            filtro.getIdsDeposito().add(dep.getDepositoId());
                        }
                        List<VwRmMaterial> lista = (List<VwRmMaterial>) service.listar(filtro, false, dataPrevisao);

                        if (lista != null && !lista.isEmpty()) {
                            //cria o arquivo do anexo
                            ArquivoVo vo = service.gerarExcelPendencia(lista, filtro, depositos, true);
                            List<AnexoEmail> anexo = Arrays.asList(new AnexoEmail(vo.getNmAnexo(), AnexoEmail.XLS, vo.getArquivo()));
                            NxResourceBundle rb = new NxResourceBundle(br.com.nextage.util.Constantes.MENSAGENS_UNDERLINE + LoginService.getLocale(null));
                            String msgEmail = rb.getString("msg_corpo_email_previsao_pendencia");
                            String tituloEmail = rb.getString("msg_titulo_email_previsao_pendencia");

//                            Email2.enviaEmailPorThread(pessoa.getEmail(), tituloEmail, msgEmail, anexo,
//                                    conf.getEmailHost(), conf.getEmailPorta(), conf.getEmailOrigem(),
//                                    conf.getEmailUser(), conf.getEmailPasswd(), conf.getEmailChave(), null, null);

                            // TODO - falta anexar arquivo
                            final String subject = tituloEmail;
                            final String recipients = pessoa.getEmail();
                            final String body = msgEmail;
                            this.emailService.enviarEmail(subject, recipients, body);
                        }
                    }
                }
            }
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
    }

    public void exec() {
        executar();
    }

    private HashMap<Pessoa, List<Deposito>> listarDepositos() {
        HashMap<Pessoa, List<Deposito>> retorno = null;
        List<Deposito> depositos = new ArrayList<>();
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));
            propriedades.add(new Propriedade(Deposito.ENDERECO));
            propriedades.add(new Propriedade(Deposito.NOME));
            propriedades.add(new Propriedade(Deposito.CODIGO));
            propriedades.add(new Propriedade(Deposito.OBSERVACAO));
            propriedades.add(new Propriedade(Deposito.TELEFONE));

            //responsavel
            String aliasResponsavel = NxCriterion.montaAlias(Deposito.ALIAS_CLASSE, Deposito.RESPONSAVEL_ID);
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasResponsavel));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasResponsavel));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasResponsavel));
            propriedades.add(new Propriedade(Pessoa.EMAIL, Pessoa.class, aliasResponsavel));

            GenericDao<Deposito> genericDao = new GenericDao<>();
            depositos = genericDao.listarByFilter(propriedades, null, Deposito.class, Constantes.NO_LIMIT, null);
            //cria um hash de pessoa por deposito
            if (depositos != null && !depositos.isEmpty()) {
                retorno = new HashMap<>();
                for (Deposito dep : depositos) {
                    if (dep.getResponsavel() != null) {
                        if (retorno.isEmpty()
                                || !retorno.containsKey(dep.getResponsavel())) {
                            retorno.put(dep.getResponsavel(), Arrays.asList(dep));
                        } else {
                            //Recupera a lista e seta pra lista aux para evitar exception.
                            List<Deposito> depsAux = retorno.get(dep.getResponsavel());
                            List<Deposito> deps = new ArrayList<>(depsAux);
                            deps.add(dep);
                            retorno.put(dep.getResponsavel(), deps);
                        }
                    }
                }
            }
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return retorno;
    }
}
