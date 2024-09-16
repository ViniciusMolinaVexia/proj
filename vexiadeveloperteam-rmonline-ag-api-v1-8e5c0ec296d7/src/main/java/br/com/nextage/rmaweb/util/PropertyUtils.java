package br.com.nextage.rmaweb.util;

import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;

public class PropertyUtils {

  public static final String getProperty(String name) {
    ResourceBundle rb = ResourceBundle.getBundle("mensagens", new Locale("pt", "BR"), new EncodedControl("UTF-8"));

    if(rb != null) {
      return rb.getString(name);
    }
    return StringUtils.EMPTY;
  }
}
