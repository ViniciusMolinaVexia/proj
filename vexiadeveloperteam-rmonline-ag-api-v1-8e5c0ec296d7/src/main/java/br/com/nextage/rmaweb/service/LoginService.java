package br.com.nextage.rmaweb.service;

import br.com.nextage.ldap.InfoLDAP;
import br.com.nextage.ldap.UserLDAP;
import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.persistence_2.util.HibernateUtil;
import br.com.nextage.rmaweb.config.ControleAcessoUsuario;
import br.com.nextage.rmaweb.dao.PerfilRoleDao;
import br.com.nextage.rmaweb.entitybean.Comprador;
import br.com.nextage.rmaweb.entitybean.Configuracao;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.Role;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.filtro.FiltroLogin;
import br.com.nextage.rmaweb.rest.client.auth.ag.AuthorizationRequest;
import br.com.nextage.rmaweb.rest.client.auth.ag.AuthorizationResponse;
import br.com.nextage.rmaweb.rest.client.auth.ag.RestClientAuthorization;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.service.ConfiguracaoLdapService;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.criptografia.Criptografia;
import br.com.nextage.util.vo.ConfiguracaoLdapVo;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.HttpHeaders;

import static br.com.nextage.rmaweb.rest.client.auth.ag.RestClientAuthorization.*;

/**
 *
 * @autor Bruno Fernandes <b.fernandes@nextage.com.br>
 * @data 26/02/2015
 */
public class LoginService {

    private RestClientAuthorization restClient;

    private final HttpServletRequest request;

    public LoginService() {
        this.request = null;
    }

    public LoginService(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * Pega o usuário logado pelo request
     *
     * @param request
     * @return
     */
    public static Usuario getUsuarioLogado(HttpServletRequest request) {
        Usuario usuarioLogado = null;
        try {

            NxResourceBundle labels = new NxResourceBundle("mensagens_" + getLocale(request));
            if (request != null) {
                String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
                if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
                    String token = authorizationHeader.substring("Basic".length()).trim();
                    usuarioLogado = ControleAcessoUsuario.converteUsuario(token);
                    return usuarioLogado;
                }
            }

            usuarioLogado = new Usuario();
            usuarioLogado.setNome(labels.getString("label_usuario_anonimo"));
            usuarioLogado.setRoles(new ArrayList<Role>());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarioLogado;
    }

    /**
     * Pega o usuário logado pelo request
     *
     * @param request
     * @return
     */
    public static Pessoa getPessoaLogada(HttpServletRequest request) {
        Pessoa p = null;
        try {
            Usuario usuarioLogado = getUsuarioLogado(request);
            if (usuarioLogado != null && usuarioLogado.getUsuarioId() != null) {
                p = usuarioLogado.getPessoa();
                if (p == null || p.getPessoaId() == null) {
                    List<Propriedade> propriedades = new ArrayList<>();
                    //Pessoa
                    propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
                    propriedades.add(new Propriedade(Pessoa.NOME));
                    propriedades.add(new Propriedade(Pessoa.CPF));

                    NxCriterion nx = NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, usuarioLogado.getPessoa().getPessoaId(), Filtro.EQUAL));

                    GenericDao<Pessoa> genericDao = new GenericDao<>();
                    p = genericDao.selectUnique(propriedades, Pessoa.class, nx);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    /**
     * Pega a localização via request
     *
     * @param request
     * @return
     */
    public static String getLocale(HttpServletRequest request) {
        String locale = null;
        try {
            if (request != null) {
                HttpSession session = request.getSession();
                if (session != null) {
                    if (session.getAttribute(Constantes.LOCALE) != null) {
                        locale = (String) session.getAttribute(Constantes.LOCALE);
                        session.setAttribute(Constantes.LOCALE, locale);
                        if (locale == null) {
                            locale = new Locale("pt", "BR").toString();
                        }
                        return locale;
                    }
                }
            }
            locale = new Locale("pt", "BR").toString();

        } catch (Exception e) {
            locale = new Locale("pt", "BR").toString();
            e.printStackTrace();
        }
        return locale;
    }

    /**
     * Pega a url do servidor via request
     *
     * @param request
     * @return
     */
    public static String getUrlServidor(HttpServletRequest request) {
        String url = null;
        if (request != null) {
            if (request.getHeader(Constantes.ORIGIN) != null) {

                url = request.getHeader(Constantes.ORIGIN);

            } else if (request.getHeader(Constantes.REFERER) != null) {

                url = request.getHeader(Constantes.REFERER).substring(0,
                        request.getHeader(Constantes.REFERER).indexOf("/rmaweb"));

            } else if (request.getHeader(Constantes.HOST) != null) {

                url = "http://" + request.getHeader(Constantes.HOST);
            }
        }
        return url;
    }

    public Info login(FiltroLogin filtro) {
        Info info = new Info();
        Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
        System.out.println(">>>>>>>>>> AUTENTICAR");
        if (conf != null && conf.getModoAutenticacao() != null) {
            if (conf.getModoAutenticacao().isEmpty()
                    || Constantes.SISTEMA.equalsIgnoreCase(conf.getModoAutenticacao())
                    || filtro.getLogin().equals("age")) {
                System.out.println(">>>>>>>>>> AUTENTICAR LOCAL");
                filtro.setLdap(false);
                info = efetuarLoginSistema(filtro);
            } else {
                System.out.println(">>>>>>>>>> AUTENTICAR API");
                filtro.setLdap(true);

                AuthorizationRequest request = new AuthorizationRequest();
                request.setLogin(filtro.getLogin());
                request.setSenha(filtro.getSenha());
                request.setAplicacaoId(applicationID);

                AuthorizationResponse response = null;
                try {
                    restClient = new RestClientAuthorization();
                    response = restClient.auth(request);
                } catch (Exception e) {
                    info.setCodigo(Info.INFO_002);
                    info.setTipo(Info.TIPO_MSG_DANGER);
                    info.setMensagem(Constantes.MSG_USUARIO_SENHA_INVALIDOS_I18N);
                    info.setErro(e.getMessage());
                }

                if (response != null) {
                    info = efetuarLoginSistema(filtro);
                }
            }
        } else {
            filtro.setLdap(false);
            info = efetuarLoginSistema(filtro);
        }
        return info;
    }

    @Deprecated
    public Info loginOrigin(FiltroLogin filtro) {
        Info info = new Info();
        Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
        System.out.println(">>>>>>>>>> AUTENTICAR");
        if (conf != null && conf.getModoAutenticacao() != null) {
            if (conf.getModoAutenticacao().isEmpty()
                    || Constantes.SISTEMA.equalsIgnoreCase(conf.getModoAutenticacao())
                    || filtro.getLogin().equals("age")) {
            	System.out.println(">>>>>>>>>> AUTENTICAR LOCAL");
                filtro.setLdap(false);
                info = efetuarLoginSistema(filtro);
            } else {
            	System.out.println(">>>>>>>>>> AUTENTICAR LDAP");
                filtro.setLdap(true);
                ConfiguracaoLdapVo confLdap = new ConfiguracaoLdapVo();
                confLdap.setUsrSystem(filtro.getLogin());
                confLdap.setPasswordSystem(filtro.getSenha());

                ConfiguracaoLdapService ldapServ = new ConfiguracaoLdapService(request);
                
                InfoLDAP infoLDAP = ldapServ.loginLdap(confLdap);
                
                System.out.println(">>>>>>>>>> AUTENTICAR LDAP: "+infoLDAP);

                UserLDAP userLdap = null;

                if (infoLDAP != null) {
                	System.out.println(">>>>>>>>>> AUTENTICAR LDAP RETORNO: "+infoLDAP.getMensagem());
                	System.out.println(">>>>>>>>>> AUTENTICAR LDAP RETORNO: "+infoLDAP.getUserLDAP());
                    if (infoLDAP.getMensagem() == null && infoLDAP.getUserLDAP() != null) {
                        userLdap = infoLDAP.getUserLDAP();
                    } else {
                        info.setCodigo(Info.INFO_002);
                        info.setTipo(Info.TIPO_MSG_DANGER);
                        info.setMensagem(Constantes.MSG_USUARIO_SENHA_INVALIDOS_I18N);
                        info.setErro(infoLDAP.getMensagem());
                    }
                } else {
                    info.setCodigo(Info.INFO_002);
                    info.setTipo(Info.TIPO_MSG_DANGER);
                    info.setMensagem(Constantes.MSG_USUARIO_NAO_ENCONTRADO_I18N);
                    info.setErro(Constantes.MSG_USUARIO_NAO_ENCONTRADO_I18N);
                }

                if (userLdap != null) {
                    info = efetuarLoginSistema(filtro);
                }
            }
        } else {
            filtro.setLdap(false);
            info = efetuarLoginSistema(filtro);
        }
        return info;
    }

    /**
     * Função utilizada para verificar o usuário
     *
     * @param filtro
     * @return obj
     *
     */
    private Info efetuarLoginSistema(FiltroLogin filtro) {
        Info info = new Info();

        //filtro.setSenha(Criptografia.criptMD5(filtro.getSenha()));
        HttpSession session = null;
        if (request != null) {
            session = request.getSession();
        }

        Usuario user = selectUser(filtro);
        try {

            if (user != null && user.getAtivo() != null && user.getAtivo().equals("S")) {
                if (user.getUsuarioId() != null && info.getErro() == null) {
                    if (session != null) {
                        ControleAcessoUsuario.setaControleUsuario(user.getLogin(), user, true);
                        session.setAttribute(Constantes.USERNAME, user.getNome() != null ? user.getNome() : "");
                        session.setAttribute(Constantes.LOCALE, LoginService.getLocale(request));
                    }
                    /* Lista de acordo com o filtro */
                    PerfilRoleDao perfilRoleDao = new PerfilRoleDao();
                    List<Role> roles = new ArrayList<Role>();
                    if(user.getPerfil()!=null&&user.getPerfil().getPerfilId()!=null) {
                    	roles = perfilRoleDao.getRolesPorPerfilId(user.getPerfil().getPerfilId());
                    }
                    user.setRoles(roles);

                    info.setCodigo(Info.INFO_001);
                    info.setObjeto(user);
                }
            } else {
                info.setCodigo(Info.INFO_002);
                info.setTipo(Info.TIPO_MSG_DANGER);
                info.setMensagem(Constantes.MSG_USUARIO_SENHA_INVALIDOS_I18N);
                info.setErro(Constantes.MSG_USUARIO_SENHA_INVALIDOS_I18N);
            }
        } catch (Exception e) {
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setMensagem(Constantes.ERRO_OPERACAO_I18N);
            info.setErro(Constantes.ERRO_OPERACAO_I18N);
        }
        return info;
    }

    /**
     * Função utilizada para listar de acordo com o filtro
     *
     * @param filtro
     * @return lista
     *
     */
    private Usuario selectUser(FiltroLogin filtro) {

        NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Usuario.LOGIN, filtro.getLogin(), Filtro.EQUAL));
        if (!filtro.isLdap()) {
            filtro.setSenha(Criptografia.criptMD5(filtro.getSenha()));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Usuario.SENHA, filtro.getSenha(), Filtro.EQUAL)));
        }
        nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Usuario.ATIVO, Constantes.SIM_ABREVIADO, Filtro.EQUAL)));

        /**
         * Lista criada para receber o usuário
         */
        Usuario usuario = new Usuario();

        try {

            String aliasPessoa = NxCriterion.montaAlias(Usuario.ALIAS_CLASSE, Usuario.PESSOA);
            String aliasComprador = NxCriterion.montaAlias(Usuario.ALIAS_CLASSE, Usuario.COMPRADOR);

            /* Filtra as propriedades da Requisição */
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Usuario.USUARIO_ID));
            propriedades.add(new Propriedade(Usuario.NOME));
            propriedades.add(new Propriedade(Usuario.LOGIN));
            propriedades.add(new Propriedade(Usuario.SENHA));
            propriedades.add(new Propriedade(Usuario.ATIVO));
            propriedades.add(new Propriedade(Usuario.COMPRADOR));
            propriedades.add(new Propriedade(Usuario.FL_ADMIN));
            propriedades.add(new Propriedade(Usuario.PERFIL));
            propriedades.add(new Propriedade(Usuario.CENTRO));
            propriedades.add(new Propriedade(Usuario.AREA));

            //Pessoa
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.CPF, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasPessoa));
            
            //Comprador
            propriedades.add(new Propriedade(Comprador.COMPRADOR_ID, Comprador.class, aliasComprador));

            //Altera conexão passando null para que ele pegue a connection padrão.
            HibernateUtil.alterarConexaoUsuario(null, null, null);

            GenericDao<Usuario> genericDao = new GenericDao<>();
            usuario = genericDao.selectUnique(propriedades, Usuario.class, nxCriterion);
            if (filtro.isLdap() && usuario != null) {
                usuario.setSenha(filtro.getSenha());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

}
