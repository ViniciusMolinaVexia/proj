/*
 * NextAge License
 * Copyright 2014 - Nextage
 *
 */
package br.com.nextage.rmaweb.controller;

import br.com.nextage.rmaweb.entitybean.Configuracao;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("ConfiguracaoController")
public class ConfiguracaoController {

    @POST
    @Path("getConfiguracao")
    @Consumes("application/json")
    public Configuracao getConfiguracao() {
        Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
        return conf;
    }
}
