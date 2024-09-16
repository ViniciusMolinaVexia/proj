/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.vo;

import java.util.HashMap;

/**
 *
 * @author age20
 */
public class ResourceBundleVo {

    private String locale;
    private HashMap<String, String> mensagens;

    public ResourceBundleVo() {
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public HashMap<String, String> getMensagens() {
        return mensagens;
    }

    public void setMensagens(HashMap<String, String> mensagens) {
        this.mensagens = mensagens;
    }

}
