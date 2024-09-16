package br.com.nextage.rmaweb.rest.client.auth;

import br.com.nextage.rmaweb.rest.client.auth.ag.AuthorizationRequest;
import br.com.nextage.rmaweb.rest.client.auth.ag.AuthorizationResponse;
import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.json.JSONConfiguration;

public interface RestClient {

    AuthorizationResponse auth(AuthorizationRequest request) throws Exception;

//    private static final Logger log = Logger.getLogger(RestClient.class);
//    private static final String urlAPI = "http://104.196.207.239:9010/";
//
//    public <T> T getRequest(String path, Class<T> clazz) throws Exception{
//        try {
//            /*
//             * adriano.gomes - Configuracao para permitir mapeamento de json para um objeto.
//             */
//            ClientConfig clientConfig = new DefaultClientConfig();
//            clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
//
//            Client client = Client.create(clientConfig);
//            client.addFilter(new HTTPBasicAuthFilter("flytour_integracao", "fly@2018_aB#ed"));
//            WebResource resource = client.resource(urlAPI + path);
//
//            ClientResponse resp = resource.accept("application/json")
//                    .header("user-agent", "")//adriano.gomes - Cabecalho adicionado para solucionar problema de erro 403 (CORS)
//                    .get(ClientResponse.class);
//
//
//            System.out.println(resp.getStatus());
//            T cli = null;
//
//            if (resp.getStatus() != 200) {
//                throw new Exception("Failed : HTTP error code : "
//                        + resp.getStatus());
//            } else {
//                try {
//                    cli = (T) resp.getEntity(clazz);
//                    System.out.println(cli);
//                } catch (Exception e) {
//                    log.error("Erro ao tentar converter response em object path: " + path, e);
//                    log.error("ClientResponse: " + resp);
//                }
//            }
//
//            return  (T) cli;
//        } catch (Exception e) {
//            throw new Exception(e);
//        }
//    }
}
