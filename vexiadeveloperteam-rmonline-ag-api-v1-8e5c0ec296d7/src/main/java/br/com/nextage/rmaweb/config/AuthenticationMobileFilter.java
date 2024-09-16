package br.com.nextage.rmaweb.config;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.filtro.FiltroLoginMobile;
import br.com.nextage.rmaweb.mobile.inteface.Secured;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.criptografia.Conversion;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Classe responsável para filtrar as requisições nos métodos que estão com a
 * notação @Secured. Assim é possível recuperar o token enviado e verificar se o
 * usuário da requisição está logado
 *
 * @brief Classe AuthenticationMobileFilter
 * @author Alyson X. Leite <a.leite@nextage.com.br>
 * @date 05/01/2016
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationMobileFilter implements ContainerRequestFilter {

    /**
     * Filtra as requisições que tem a anotação @Secured e verifica se o token
     * está de acordo com usuário
     *
     * @author Alyson X. Leite
     * @param requestContext
     * @throws IOException
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        try {
            // Recupera o token no header da requisição
            String authorizationHeader
                    = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

            if (authorizationHeader == null || !authorizationHeader.startsWith("Basic ")) {
                throw new NotAuthorizedException("Sem permissão de acesso!");
            }

            String token = authorizationHeader.substring("Basic".length()).trim();

            // Verifica se o token é um usuário serializado
            final FiltroLoginMobile usuario = validarUsuario(token);

            // Valida se o token do usuário é o mesmo que está no banco de dados
            if (validarTokenUsuario(usuario) == false) {
                throw new NotAuthorizedException("Sem permissão de acesso!");
            }

            // injetar o context na classe que utiliza o rest para recuperar o usuario logado
            requestContext.setSecurityContext(new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return usuario;
                }

                @Override
                public boolean isUserInRole(String s) {
                    return false;
                }

                @Override
                public boolean isSecure() {
                    return false;
                }

                @Override
                public String getAuthenticationScheme() {
                    return null;
                }
            });

        } catch (Exception e) {
            // Retorna para o disposito o erro 401
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    /**
     * Valida se o token recebido por parâmetro é um usuário serializado
     *
     * @author Alyson X. Leite
     * @param token
     * @return
     * @throws Exception
     */
    private FiltroLoginMobile validarUsuario(String token) throws Exception {

        byte[] base64 = Conversion.base64StringToByteArray(token);
        // Corvertendo para uma string em json
        String json = new String(new String(base64).getBytes("ISO-8859-1"));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // Transforma no objeto FiltroLoginMobile
        FiltroLoginMobile usuario = objectMapper.readValue(json, FiltroLoginMobile.class);
        System.out.println(usuario);
        return usuario;
    }

    /**
     * Valida o token do usuario pelo usuario do banco
     *
     * @author Alyson X. Leite
     * @param filtro
     * @return
     */
    private boolean validarTokenUsuario(FiltroLoginMobile filtro) {
        Usuario usuario = selectUser(filtro);
        return usuario != null;
    }

    /**
     * Função utilizada para listar o usuario de acordo com o filtro
     *
     * @author Alyson X. Leite
     * @param filtro
     * @return lista
     *
     */
    private Usuario selectUser(FiltroLoginMobile filtro) {

        NxCriterion nxCriterion;

        nxCriterion = NxCriterion.montaRestriction(new Filtro(Usuario.LOGIN, filtro.getLogin(), Filtro.EQUAL));

        nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Usuario.ATIVO, Constantes.SIM_ABREVIADO, Filtro.EQUAL)));
        nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Usuario.TOKEN_MOBILE, filtro.getToken(), Filtro.EQUAL)));

        /**
         * Lista criada para receber o usuário
         */
        Usuario usuario = null;

        try {
            /* Filtra as propriedades da Requisição */
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Usuario.USUARIO_ID));
            propriedades.add(new Propriedade(Usuario.TOKEN_MOBILE));

            GenericDao<Usuario> genericDao = new GenericDao<>();
            usuario = genericDao.selectUnique(propriedades, Usuario.class, nxCriterion);
        } catch (Exception e) {

        }
        return usuario;
    }
}
