package br.com.nextage.rmaweb.mobile.controller;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.Setor;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.filtro.FiltroLogin;
import br.com.nextage.rmaweb.filtro.FiltroLoginMobile;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.Info;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Classe responsável pelo login e validações do usuário ao entrar no aplicativo
 * mobile
 *
 * @author Alyson X. Leite <a.leite@nextage.com.br>
 * @brief Classe LoginMobileController
 * @date 05/01/2016
 */
@Path("LoginMobileController")
public class LoginMobileController {

	@Context
	private HttpServletRequest request;

	@Context
	private SecurityContext securityContext;

	private static final Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

	/**
	 * Realiza o login do usuário no dispositivo móvel do mesmo<br>
	 * Gera um token e atualiza os dados do usuário para confrontar nas outras
	 * requisições
	 *
	 * @param filtro
	 * @return Response para caso o usuário não tenha acesso retorne um erro 401
	 */
	@POST
	@Path("login")
	@Consumes("application/json")
	public Response login(FiltroLoginMobile filtro) {
		try {
			logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroLoginMobile.class.getName(), Util.getNomeMetodoAtual(), filtro, null));

			FiltroLogin login = new FiltroLogin(filtro.getLogin(), filtro.getSenha());
			LoginService service = new LoginService(request);
			// Realiza o login no sistema
			Info info = service.login(login);
			if (info.getCodigo().equals(Info.INFO_001)) {
				// Gera o token e atualiza o usuario
				Usuario user = (Usuario) info.getObjeto();
				Pessoa pessoa = obterPessoa(user);
				if (pessoa == null) {
					info.setCodigo(Info.INFO_002);
					info.setTipo(Info.TIPO_MSG_DANGER);
					info.setMensagem(Constantes.MSG_USUARIO_NAO_POSSUI_PESSOA_I18N);
					info.setErro(Constantes.MSG_USUARIO_NAO_POSSUI_PESSOA_I18N);
					return Response.ok(info).build();
				}
				String token = gerarToken();
				user.setTokenMobile(token);
				info = updateTokenUsuario(user);


				if (info.getCodigo().equals(Info.INFO_001)) {
					filtro.setToken(token);
					filtro.setNome(user.getNome());
					filtro.setPessoaId(pessoa.getPessoaId());
					filtro.setPessoaNome(pessoa.getNome());
					filtro.setPessoaReferencia(pessoa.getReferencia());
					if (pessoa.getSetor() != null) {
						filtro.setPessoaSetor(pessoa.getSetor().getDescricao());
						filtro.setPessoaSetorCodigo(pessoa.getSetor().getCodigo());
						filtro.setPessoaSetorId(pessoa.getSetor().getSetorId());
					}
					// Remove a senha para devolver o usuário autenticado
					filtro.setSenha(null);
					info.setObjeto(filtro);
					filtro.setUsuarioId(user.getUsuarioId());
				}
			} else {
				info.setMensagem("Usuário Julio e/ou senha inválidos.");
			}
			// Return the token on the response
			return Response.ok(info).build();

		} catch (Exception e) {
			logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	private Pessoa obterPessoa(Usuario usuario) {
		try {
			String aliasSetor = NxCriterion.montaAlias(Pessoa.ALIAS_CLASSE, Pessoa.SETOR);

			NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, usuario.getPessoa().getPessoaId(), Filtro.EQUAL));

            /* Filtra as propriedades da Requisição */
			List<Propriedade> propriedades = new ArrayList<>();
			propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
			propriedades.add(new Propriedade(Pessoa.NOME));
			propriedades.add(new Propriedade(Pessoa.REFERENCIA));

			//Comprador
			propriedades.add(new Propriedade(Setor.SETOR_ID, Setor.class, aliasSetor));
			propriedades.add(new Propriedade(Setor.CODIGO, Setor.class, aliasSetor));
			propriedades.add(new Propriedade(Setor.DESCRICAO, Setor.class, aliasSetor));

			GenericDao<Pessoa> genericDao = new GenericDao<>();
			return genericDao.selectUnique(propriedades, Pessoa.class, nxCriterion);
		} catch (Exception e) {
			logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
			return usuario.getPessoa();
		}
	}

	/**
	 * Valida se a url digitada do usuário está correta
	 *
	 * @return
	 * @author Alyson X. Leite
	 */
	@POST
	@Path("validarURL")
	@Consumes("application/json")
	public Info validarURL() {
		Info info = new Info();
		info.setCodigo(Info.INFO_001);
		info.setTipo(Info.TIPO_MSG_SUCCESS);

		return info;
	}

	/**
	 * Gera o token do usuário
	 *
	 * @return
	 * @author Alyson X. Leite
	 */
	private String gerarToken() {
		// TODO - VERIFICAR
		Random random = new SecureRandom();
		String token = new BigInteger(130, random).toString(32);
		return token;
	}

	/**
	 * Atualiza o token do usuário no banco de dados
	 *
	 * @param user
	 * @return
	 * @author Alyson X. Leite
	 */
	private Info updateTokenUsuario(Usuario user) {
		Info info = new Info();
		try {
			//HttpSession session = request.getSession();
			logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Usuario.class.getName(), Util.getNomeMetodoAtual(), user, null));

			GenericDao<Usuario> genericDao = new GenericDao<>();

			List<Propriedade> propriedades = new ArrayList<>();
			//propriedades.add(new Propriedade(Usuario.USUARIO_ID));
			propriedades.add(new Propriedade(Usuario.TOKEN_MOBILE));

			genericDao.update(user, propriedades);

			info.setCodigo(Info.INFO_001);
			info.setTipo(Info.TIPO_MSG_SUCCESS);
			info.setErro(null);

		} catch (Exception e) {
			logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));

			String erro = "Erro atualizar token!";
			info.setCodigo(Info.INFO_002);
			info.setMensagem(erro);
			info.setErro(erro);
		}
		return info;
	}
}
