package br.com.nextage.rmaweb.rest.client.auth.ag;

import br.com.nextage.rmaweb.rest.client.auth.RestClient;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.apache.log4j.Logger;


public class RestClientAuthorization implements RestClient {

    private static final Logger log = Logger.getLogger(RestClientAuthorization.class);
    private static final String urlAPI = "https://apps.agnet.com.br/agwebapi/api/";
    private static final String path = "login/autenticar";
    public static final String applicationID = "ANDRADE_RM_ONLINE";

//    public static void main(String[] args) {
//        try {
//            AuthorizationRequest request = new AuthorizationRequest();
//            request.setLogin("fernando.pinto");
//            request.setSenha("AGsenha20199");
//            request.setAplicacaoId("ANDRADE_RM_ONLINE");
//
//            RestClientAuthorization restClientAuthorization = new RestClientAuthorization();
//            AuthorizationResponse details = restClientAuthorization.auth(request);
//            log.info(details.getNome());
//        } catch (Exception e) {
//            log.error(e);
//        }
//    }

    public AuthorizationResponse auth(AuthorizationRequest request) throws Exception{
        try {
            /*
             * adriano.gomes - Configuracao para permitir mapeamento de json para um objeto.
             */
            ClientConfig clientConfig = new DefaultClientConfig();
//			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

            Client client = Client.create(clientConfig);
            WebResource resource = client.resource(urlAPI + path);

            Gson gson = new Gson();
            String jsonRequest = gson.toJson(request);

            ClientResponse resp = resource.accept("application/json")
                    .header("Content-Type", "application/json")
//	        		.header("user-agent", "*")//adriano.gomes - Cabecalho adicionado para solucionar problema de erro 403 (CORS)
                    .post(ClientResponse.class, jsonRequest);

            if (resp.getStatus() != 200) {
                log.error("Um problema ocorreu ao tentar autenticar usuário: " + resp.getEntity(String.class));
                throw new Exception("Autenticação não autorizada!");
            }
            final String respStr = resp.getEntity(String.class);
            return gson.fromJson(respStr, AuthorizationResponse.class);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
