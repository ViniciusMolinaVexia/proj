/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.singleton;

import br.com.nextage.rmaweb.entitybean.Configuracao;
import br.com.nextage.rmaweb.service.ConfiguracaoService;
import flex.messaging.FlexContext;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 *<b>author:</b> Marlos M. Novo
 *<b>date:  </b> 26/06/2012
 * </pre>
 *
 * <PRE>
 * Classe Singleton para configuração, tem como objetivo:
 *  - Assegurar que esta classe tenha uma única instância e prover um ponto de
 *    acesso global a esta instância.
 * </PRE>
 */
public class ConfiguracaoSingleton {

    //Objeto singleton(Único).
    private static Configuracao configuracao = null;

    private ConfiguracaoSingleton() {
    }//O compilador não vai gerar um construtor default público

    /**
     * Recupera a instancia do objeto singleton.
     *
     * @return
     */
    public static Configuracao getConfiguracao() {
        //Se o objeto estiver null, gera uma nova instância de objeto.
        if (configuracao == null) {
            ConfiguracaoService confService = new ConfiguracaoService();
            configuracao = confService.getConfiguracao();
        }
//        if (configuracao != null && configuracao.getUrlServidor() == null) {
//            //Recupera a url do servidor, setando campo transiente na configuração.
//            //Ex.: localhost OU 192.168.0.1
//            configuracao.setUrlServidor(urlServidor());
//        }
        return configuracao;
    }

    /**
     * @author Marlon Bahniuk
     * @since 27/11/2014
     * @param conf
     * @description Metodo utilizado para fazer o refresh da configuracao.
     */
    public static void setConfiguracao(Configuracao conf) {
        if (Objects.equals(configuracao.getConfiguracaoId(), conf.getConfiguracaoId())) {
            configuracao = conf;
        }
    }

    public static void alterarConfiguracao(Configuracao conf) {
        configuracao = conf;
    }

    /**
     * <PRE>
     *<b>author:</b>  Marlos M. Novo
     *<b>date  :</b>  25/06/2012
     *<b>param :</b>
     *<b>return:</b>  String
     * </PRE>
     *
     * <p>
     * Responsável por recuperar a url do servidor. EX.: se tiver rodando local
     * irá pegar localhost:8084 No server irá pegar 192.168.0.1 por exemplo.
     */
    private static String urlServidor() {
        String str = null;

        HttpServletRequest requestFlex = FlexContext.getHttpRequest();

        if (requestFlex != null) {
            String caminhoTotal = requestFlex.getRequestURL().toString();
            String[] arrays = caminhoTotal.split("/");

            str = "http://" + arrays[2];
        }
        return str;
    }
}
