package br.com.nextage.rmaweb.mobile.inteface;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.ws.rs.NameBinding;

/**
 * @brief Classe Secured
 * @author Alyson X. Leite <a.leite@nextage.com.br>
 * @date 05/01/2016
 */
@NameBinding
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Secured {
}
