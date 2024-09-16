package br.com.nextage.rmaweb.config;

import br.com.nextage.rmaweb.entitybean.Usuario;
import java.io.IOException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * @brief Classe RequestFilter
 * @author Alyson X. Leite <a.leite@nextage.com.br>
 * @date 19/01/2016
 */
public class RequestFilter implements ContainerRequestFilter {

    /**
     * Filtra as requisições do sistema para desserializar o usuário do header
     * na Session
     *
     * @author Alyson X. Leite
     * @param crc
     * @throws java.io.IOException
     */
    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        try {
            String authorizationHeader = crc.getHeaderString(HttpHeaders.AUTHORIZATION);

            if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
                String token = authorizationHeader.substring("Basic".length()).trim();
                Usuario usuario = ControleAcessoUsuario.converteUsuario(token);
                ControleAcessoUsuario.setaControleUsuario(usuario.getLogin(), usuario);
            }
        } catch (NotAuthorizedException e) {
            // Retorna para o disposito o erro 401 pra quando expirar a sessão
            crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());

        } catch (Exception e) {
        }

    }

}
