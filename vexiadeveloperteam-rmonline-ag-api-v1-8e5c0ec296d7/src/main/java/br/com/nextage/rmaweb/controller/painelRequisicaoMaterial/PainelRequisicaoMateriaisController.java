package br.com.nextage.rmaweb.controller.painelRequisicaoMaterial;

import static br.com.nextage.rmaweb.util.PropertyUtils.getProperty;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.persistence_2.vo.PaginacaoVo;
import br.com.nextage.rmaweb.controller.notificacao.email.enviar.EmailService;
import br.com.nextage.rmaweb.dao.RmMaterialDao;
import br.com.nextage.rmaweb.entitybean.Deposito;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.entitybean.RmMaterialStatus;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.entitybean.VwConsultaAlmoxarife;
import br.com.nextage.rmaweb.entitybean.VwRmMaterial;
import br.com.nextage.rmaweb.filtro.FiltroPainelRequisicaoMateriais;
import br.com.nextage.rmaweb.service.AlmoxerifeService;
import br.com.nextage.rmaweb.service.DepositoService;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.RmMaterialService;
import br.com.nextage.rmaweb.service.requisicao.compra.SapServiceRequisicaoCompraClient;
import br.com.nextage.rmaweb.service.requisicao.reserva.SapServiceRequisicaoReservaClient;
import br.com.nextage.rmaweb.service.integracao.RastreabilidadeService;
import br.com.nextage.rmaweb.service.integracao.RmMaterialStatusService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.Email2;
import br.com.nextage.util.Info;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.log4j.Logger;

@Path("PainelRequisicaoMateriais")
public class PainelRequisicaoMateriaisController {

	private static final Logger log = Logger.getLogger(PainelRequisicaoMateriaisController.class);

	@Context
	HttpServletRequest request;

	@Inject
	private SapServiceRequisicaoCompraClient sapServiceRequisicaoCompraClient;

	@Inject
	private SapServiceRequisicaoReservaClient sapServiceRequisicaoReservaClient;

	private RastreabilidadeService rastreabilidadeService = RastreabilidadeService.getInstance();
	private AlmoxerifeService almoxerifeService = AlmoxerifeService.getInstance();
	private RastreabilidadeService rastrearService = RastreabilidadeService.getInstance();
	private RmMaterialService rmMaterialService = new RmMaterialService();

	@Inject
	private EmailService emailService;

	@Inject
	private DepositoService depositoService;

	@POST
	@Path("reprovar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response reprovar(ReprovarReqMaterialRequest requestReprovar) throws Exception {

		if (requestReprovar.getIdRmMaterial() == null || requestReprovar.getIdRm() == null) {
			Info info = new Info();
			info.setCodigo(Info.INFO_002);
			info.setErro(null);
			info.setMensagem(Constantes.MSG_REQUISICAO_REPROVADA_ERRO_I18N);
			info.setTipo(Info.TIPO_MSG_DANGER);

			return Response.status(Response.Status.BAD_REQUEST).entity(info).build();
		}

		// buscar rm material pelo id
		RmMaterial rmMaterial = rmMaterialService.selectByUnique(requestReprovar.getIdRmMaterial());

		rastreabilidadeService.rastrearReprovado(rmMaterial.getRm(), LoginService.getUsuarioLogado(request).getLogin(),
				"Almoxarife");

		Info info = new Info();
		RmMaterialStatus rmMaterialStatusAtual = null;
		GenericDao<VwRmMaterial> genericDao = new GenericDao<>();
		RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);

		List<RmMaterial> listaEnviarEmail = new ArrayList<>();
		try {

			// Gera status para cada material
			Date data = new Date();
			rmMaterialStatusAtual = rmMaterialStatusService.listarStatusAtual(rmMaterial);

			genericDao.beginTransaction();

			if (!rmMaterialStatusAtual.getStatus().getCodigo().equals(Constantes.STATUS_RM_MATERIAL_REPROVADA)) {
				rmMaterialStatusService.finalizarStatus(rmMaterialStatusAtual, genericDao, data);
				rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_REPROVADA, genericDao,
						data, requestReprovar.getJustificativaReprovacao(), null);
				listaEnviarEmail.add(rmMaterial);
			}

			genericDao.commitCurrentTransaction();

			String emailRequisitante = rmMaterial.getRm().getRequisitante().getEmail();
			String emailCadastrante = rmMaterial.getRm().getUsuario().getPessoa().getEmail();

			if ((emailRequisitante != null && !emailRequisitante.isEmpty())
					|| emailCadastrante != null && !emailCadastrante.isEmpty()) {
				String dataReprovacao = Util.dateToString(data, getProperty("format_date"));

				String tituloEmail = getProperty("msg_titulo_email_reprovacao_material");

				String material = "";
				if (rmMaterial.getMaterial().getCodigo() != null && !rmMaterial.getMaterial().getCodigo().isEmpty()) {
					material = rmMaterial.getMaterial().getCodigo();
				}
				if (rmMaterial.getMaterial().getNome() != null && !rmMaterial.getMaterial().getNome().isEmpty()) {
					material = material + " - " + rmMaterial.getMaterial().getNome();
				}

				String mensagemEmail = Util.setParamsLabel(getProperty("msg_email_reprovacao_material"), material,
						rmMaterial.getRm().getNumeroRm(), LoginService.getUsuarioLogado(request).getNome(),
						dataReprovacao, requestReprovar.getJustificativaReprovacao());

				if (Email2.validaEmail(emailRequisitante)) {
					final String subject = tituloEmail;
					final String recipients = emailRequisitante;
					final String body = mensagemEmail;
					this.emailService.enviarEmail(subject, recipients, body);
				}

				if (Email2.validaEmail(emailCadastrante)) {
					final String subject = tituloEmail;
					final String recipients = emailCadastrante;
					final String body = mensagemEmail;
					this.emailService.enviarEmail(subject, recipients, body);
				}
			}

			info.setCodigo(Info.INFO_001);
			info.setErro(null);
			info.setMensagem(Constantes.MSG_REQUISICAO_REPROVADA_SUCESSO_I18N);
			info.setTipo(Info.TIPO_MSG_SUCCESS);

			log.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(),
					this.getClass().getName(), Rm.class.getName(), Util.getNomeMetodoAtual(), rmMaterial, null));
		} catch (Exception e) {
			genericDao.rollbackCurrentTransaction();
			info.setCodigo(Info.INFO_002);
			info.setErro(null);
			info.setMensagem(Constantes.MSG_REQUISICAO_REPROVADA_ERRO_I18N);
			info.setTipo(Info.TIPO_MSG_DANGER);
			log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(),
					this.getClass().getName(), Util.getNomeMetodoAtual(), e));

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(info).build();
		}
		return Response.status(Response.Status.OK).entity(info).build();
	}

	@POST
	@Path("enviarSAP")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response enviarSAP(final List<EnviarReqMaterialSapRequest> lista) {
		String nomeUsuarioLogado = LoginService.getUsuarioLogado(request).getNome();

		EnviarReqMaterialSapResponse response = sapServiceRequisicaoCompraClient.zglRmaCriarRequisicaoCompra(lista,
				nomeUsuarioLogado, request);

		return Response.status(Response.Status.OK).entity(response).build();
	}

	@POST
	@Path("enviarReservaSAP")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response enviarReservaSAP(final List<EnviarReqReservaMaterialSapRequest> lista) {
		String nomeUsuarioLogado = LoginService.getUsuarioLogado(request).getNome();

		EnviarReqReservaMaterialSapResponse response = sapServiceRequisicaoReservaClient
				.zglRmaCriarRequisicaoReserva(lista, nomeUsuarioLogado, request);

		return Response.status(Response.Status.OK).entity(response).build();
	}

	@POST
	@Path("salvarRequisicao")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void atualizarMaterial(VwConsultaAlmoxarife rmMaterial) throws Exception {
		RmMaterialDao rmMaterialDAO = new RmMaterialDao();
		RmMaterial antigo = rmMaterialDAO.getById(rmMaterial.getRmMaterialId());
		antigo.setQuantidade(Double.valueOf(rmMaterial.getQuantidade()));
		antigo.setGrupoComprador(rmMaterial.getGrupoComprador());

		if (!rmMaterial.getDepositoCodigo().equals(antigo.getDeposito().getCodigo())) {
			List<Deposito> depositos = depositoService.getComboDeposito();
			Deposito deposito = depositos.stream().filter(dep -> dep.getCodigo().equals(rmMaterial.getDepositoCodigo()))
					.findFirst().orElse(null);
			antigo.setDeposito(deposito);
		}
		rmMaterialDAO.update(antigo);
		rastreabilidadeService.rastrearQuantidadeAlterada(antigo.getRm(), rmMaterial.getQuantidade(),
				LoginService.getUsuarioLogado(request).getLogin());
	}

	@POST
	@Path("listar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PaginacaoVo listar(FiltroPainelRequisicaoMateriais filtro) throws Exception {
		final Usuario usuarioLogado = LoginService.getUsuarioLogado(request);
		List<Object> vwConsultaAlmoxarife = almoxerifeService.vwConsultaAlmoxarife(filtro, usuarioLogado);
		filtro.getPaginacaoVo().setItensConsulta(vwConsultaAlmoxarife);

		return filtro.getPaginacaoVo();
	}

	@POST
	@Path("salvarBaixa")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Info salvarBaixa(VwConsultaAlmoxarife rmMaterial) throws Exception {

		Info info = new Info();
		RmMaterialDao rmMaterialDAO = new RmMaterialDao();
		RmMaterial antigo = rmMaterialDAO.getById(rmMaterial.getRmMaterialId());
		RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService();
		GenericDao genericDao = new GenericDao();

		String codigoStatus = "Fim";
		Date data = new Date();
		String observacao = "Material Baixa - Número Baixa: "+rmMaterial.getNumeroBaixa();
		String usuarioLogado = LoginService.getUsuarioLogado(request).getLogin();
		try {
			
			//Parametros para Update na tabela RM_Material
			antigo.setQuantidade(Double.valueOf(rmMaterial.getQuantidade()));
			antigo.setIsBaixa(true);
			antigo.setNumeroBaixa(rmMaterial.getNumeroBaixa());

			if (!rmMaterial.getDepositoCodigo().equals(antigo.getDeposito().getCodigo())) {
				List<Deposito> depositos = depositoService.getComboDeposito();
				Deposito deposito = depositos.stream()
						.filter(dep -> dep.getCodigo().equals(rmMaterial.getDepositoCodigo())).findFirst().orElse(null);
				antigo.setDeposito(deposito);
			}
			
			//Update na tabela RM_Material
			rmMaterialDAO.update(antigo);
			
			//Alterar Status
			genericDao.beginTransaction();
			rmMaterialStatusService.gerarStatus(antigo, codigoStatus, genericDao, data, observacao, usuarioLogado);
			genericDao.commitCurrentTransaction();
			
			//Insere rastros no Log
			rastreabilidadeService.rastrearQuantidadeAlterada(antigo.getRm(), rmMaterial.getQuantidade(),
					LoginService.getUsuarioLogado(request).getLogin());
			rastreabilidadeService.rastrearMaterialBaixa(antigo.getRm(), rmMaterial.getNumeroBaixa(),
					LoginService.getUsuarioLogado(request).getLogin());
			
			info.setObjeto(rmMaterial);
            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setErro(null);
            
		} catch (Exception e) {
			log.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
            System.out.println(e.toString());
		}
		
		return info;
	}

}
