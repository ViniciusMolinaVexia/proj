package br.com.nextage.rmaweb.config;

import br.com.nextage.rmaweb.config.vo.ControleUsuarioVo;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.Util;
import br.com.nextage.util.criptografia.Conversion;
import java.io.File;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.NotAuthorizedException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathFactory;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @brief Classe ControleAcessoUsuario
 * @author Alyson X. Leite <a.leite@nextage.com.br>
 * @date 19/01/2016
 */
public class ControleAcessoUsuario {

    private static Map<String, ControleUsuarioVo> listaSession;
    private static Integer timeout;
    private static Boolean sessionExpired;

    /**
     * <PRE>
     *<b>author:</b>  Marlos Morbis Novo
     *<b>date  :</b>  09/03/2015
     *<b>param :</b>  id, ControleUsuarioVo
     *<b>return :</b>
     * </PRE>
     *
     * <p>
     * Adiciona a lista de session/conexao de acordo com o id session da
     * request.
     *
     * @param id
     * @param vo
     */
    public static void setaControleUsuario(String id, Usuario vo) throws NotAuthorizedException {
        setaControleUsuario(id, vo, false);
    }

    public static void setaControleUsuario(String id, Usuario vo, boolean ignoraSessao) throws NotAuthorizedException {
        try {
            if (listaSession == null) {
                listaSession = new HashMap<>();
            }

            ControleUsuarioVo user = recuperaControleUsuario(id);
            if (user == null) {
                if (getSessionExpired() && ignoraSessao == false) {
                    throw new NotAuthorizedException(Constantes.SESSAO_EXPIRADA_I18N);
                } else {
                    setUser(id, vo);
                }
            } else {
                if (user.getData() != null) {
                    int retorno = Util.compareDateTo(user.getData(), new Date());
                    if (retorno != 1) {
                        listaSession.remove(id);
                        if (getSessionExpired() && ignoraSessao == false) {
                            throw new NotAuthorizedException(Constantes.SESSAO_EXPIRADA_I18N);
                        } else {
                            setUser(id, vo);
                        }
                    }
                }

            }

        } catch (NotAuthorizedException e) {
            throw e;

        } catch (Exception e) {
        } finally {
            removeExpirados();
        }
    }

    /**
     * Seta o usuário na sessão já incluindo o tempo de expiração
     *
     * @param id
     * @param vo
     * @throws Exception
     */
    private static void setUser(String id, Usuario vo) throws Exception {
        int tempo = getSessionTimeout();
        Date expiracao = null;
        if (tempo != -1) {
            expiracao = addMin(new Date(), tempo);
        }
        listaSession.put(id, new ControleUsuarioVo(vo, expiracao));
    }

    /**
     * Adiciona minutos na data passada como parâmetro
     *
     * @param data
     * @param min
     * @return
     */
    private static Date addMin(Date data, int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.MINUTE, min);

        return new Date(calendar.getTime().getTime());

    }

    /**
     * <PRE>
     *<b>author:</b>  Marlos Morbis Novo
     *<b>date  :</b>  09/03/2015
     *<b>param :</b>  ContainerRequestContext
     *<b>return :</b>
     * </PRE>
     *
     * <p>
     * Recupera a url de conexão de acordo com o Id sessio informado.
     *
     * @param id
     * @return
     */
    public static Usuario getUsuario(String id) {

        if (listaSession != null && listaSession.containsKey(id)) {
            ControleUsuarioVo vo = listaSession.get(id);
            if (vo != null) {
                return vo.getUsuario();
            }
        }

        return null;
    }

    /**
     * Recupera o usuário da sessão através do id
     *
     * @param id
     * @return
     */
    private static ControleUsuarioVo recuperaControleUsuario(String id) {
        if (listaSession != null && listaSession.containsKey(id)) {
            return listaSession.get(id);
        }

        return null;
    }

    /**
     * Recupera o tempo em minutos do timeout configurado no web.xml
     *
     * @return
     */
    private static int getSessionTimeout() {
        if (timeout != null && timeout != -1) {
            return timeout;
        }
        try {
            String webInf = "WEB-INF";
            String caminho = URLDecoder.decode(ControleAcessoUsuario.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF8");
            caminho = caminho.substring(0, caminho.lastIndexOf(webInf) + webInf.length());

            File file = new File(caminho + "/web.xml");
            int sessionTimeoutFromWebXml = Integer.parseInt(XPathFactory.newInstance().newXPath().compile("web-app/session-config/session-timeout").evaluate(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file)).trim());

            timeout = sessionTimeoutFromWebXml;
        } catch (Exception e) {
            timeout = -1;
        }
        return timeout;
    }

    /**
     * Recupera o parâmetro setado no campo session-expired do web.xml para
     * verificar se a sesão expira 0 - Não expira 1 - expira
     *
     * @return
     */
    public static boolean getSessionExpired() {
        if (sessionExpired != null) {
            return sessionExpired;
        }
        try {
            sessionExpired = false;
            String webInf = "WEB-INF";
            String caminho = URLDecoder.decode(ControleAcessoUsuario.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF8");
            caminho = caminho.substring(0, caminho.lastIndexOf(webInf) + webInf.length());

            File file = new File(caminho + "/web.xml");

            int sessionTimeoutFromWebXml = Integer.parseInt(XPathFactory.newInstance().newXPath().compile("web-app/session-config/session-expired").evaluate(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file)).trim());
            if (sessionTimeoutFromWebXml == 1) {
                sessionExpired = true;
            }

        } catch (Exception e) {
            sessionExpired = false;
        }
        return sessionExpired;
    }

    /**
     * Remove os usuário que já estão expirados no sistema
     */
    private synchronized static void removeExpirados() {
        Map<String, ControleUsuarioVo> listaTmp = new HashMap<>(listaSession);
        for (Map.Entry<String, ControleUsuarioVo> entry : listaSession.entrySet()) {
            String key = entry.getKey();
            ControleUsuarioVo value = entry.getValue();
            if (value.getData() != null) {
                int retorno = Util.compareDateTo(value.getData(), new Date());
                if (retorno != 1) {
                    listaTmp.remove(key);
                }
            }
        }
        listaSession = new HashMap<>(listaTmp);
    }

    /**
     * Valida se o token recebido por parâmetro é um usuário serializado
     *
     * @author Alyson X. Leite
     * @param token
     * @return
     * @throws Exception
     */
    public static Usuario converteUsuario(String token) throws Exception {

        byte[] base64 = Conversion.base64StringToByteArray(token);
        // Corvertendo para uma string em json
        String json = new String(new String(base64).getBytes("ISO-8859-1"));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // Transforma no objeto Usuario
        Usuario usuario = objectMapper.readValue(json, Usuario.class);

        return usuario;
    }

}
