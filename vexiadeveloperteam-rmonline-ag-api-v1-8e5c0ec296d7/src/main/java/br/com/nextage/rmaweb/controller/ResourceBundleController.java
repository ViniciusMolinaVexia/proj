/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.controller;

import br.com.nextage.rmaweb.util.EncodedControl;
import br.com.nextage.rmaweb.vo.ResourceBundleVo;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author age20
 */
@Path("ResourceBundleController")
public class ResourceBundleController {

    /**
     * <PRE>
     * <b>author:</b> Leonardo Faix Pordeus
     * <b>date :</b> 25/08/2014
     * <b>param :</b> String locale
     * <b>return:</b> lista com propriedades
     * </PRE>
     *
     * <p>
     * Método que busca o bundle com os labels para a aplicação, de acordo com o
     * locale informado.
     *
     * @param locale
     * @return
     */
    @POST
    @Path("/getResourceBundle/")
    @Produces("application/json")
    public ResourceBundleVo getResourceBundle(String locale) {
        ResourceBundleVo resourceBundleVo = new ResourceBundleVo();
        ResourceBundle mensagens = null;
        HashMap<String, String> hashMap = new HashMap<>();

        if (locale != null && !locale.isEmpty()) {
            if (locale.equals("default")) {
                mensagens = ResourceBundle.getBundle("mensagens", new Locale("pt", "BR"), new EncodedControl("UTF-8"));
            } else {
                String[] locales = locale.split("_");

                mensagens = ResourceBundle.getBundle("mensagens", new Locale(locales[0], locales[1]), new EncodedControl("UTF-8"));
            }
            Enumeration<String> keys = mensagens.getKeys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                hashMap.put(key, mensagens.getString(key));
            }
        }
        resourceBundleVo.setLocale(locale);
        resourceBundleVo.setMensagens(hashMap);
        return resourceBundleVo;
    }
}
