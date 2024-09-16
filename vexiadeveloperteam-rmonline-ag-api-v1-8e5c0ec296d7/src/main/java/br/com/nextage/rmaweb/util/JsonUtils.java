package br.com.nextage.rmaweb.util;

import com.google.gson.Gson;

public class JsonUtils {

    private JsonUtils(){

    }

    public static String convertToJSon(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
