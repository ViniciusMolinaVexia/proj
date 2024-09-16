package br.com.nextage.rmaweb.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import br.com.nextage.controller.config.ObjectMapperProvider;
import br.com.nextage.rmaweb.controller.AdministradorController;
import br.com.nextage.rmaweb.controller.AnexoController;
import br.com.nextage.rmaweb.controller.AnexoServicoController;
import br.com.nextage.rmaweb.controller.AutoCompleteController;
import br.com.nextage.rmaweb.controller.CadastroObraController;
import br.com.nextage.rmaweb.controller.ComboController;
import br.com.nextage.rmaweb.controller.CompradorController;
import br.com.nextage.rmaweb.controller.ConfiguracaoController;
import br.com.nextage.rmaweb.controller.DocumentoResponsabilidadeIndisRecController;
import br.com.nextage.rmaweb.controller.DocumentoResponsabilidadePendController;
import br.com.nextage.rmaweb.controller.HierarquiaCompradorController;
import br.com.nextage.rmaweb.controller.LogInterfaceController;
import br.com.nextage.rmaweb.controller.LoginController;
import br.com.nextage.rmaweb.controller.MateriaisSemCodigoSapController;
import br.com.nextage.rmaweb.controller.PainelEstoquistaController;
import br.com.nextage.rmaweb.controller.PerfilController;
import br.com.nextage.rmaweb.controller.RastreabilidadeController;
import br.com.nextage.rmaweb.controller.ResourceBundleController;
import br.com.nextage.rmaweb.controller.RmAprovacaoController;
import br.com.nextage.rmaweb.controller.UsuarioController;
import br.com.nextage.rmaweb.controller.WorkflowController;
import br.com.nextage.rmaweb.controller.cadastroRma.CadastroRmaController;
import br.com.nextage.rmaweb.controller.cadastroRmaServico.CadastroRmaServicoController;
import br.com.nextage.rmaweb.controller.estoque.BuscarEstoqueMaterialController;
import br.com.nextage.rmaweb.controller.notificacao.email.enviar.EmailController;
import br.com.nextage.rmaweb.controller.notificacao.email.enviar.EmailService;
import br.com.nextage.rmaweb.controller.painelRequisicaoMaterial.PainelRequisicaoMateriaisController;
import br.com.nextage.rmaweb.controller.pessoa.obterPorPerfilECentro.ObterPessoaPorPerfilECentroController;
import br.com.nextage.rmaweb.controller.relatorio.GestaoRmaController;
import br.com.nextage.rmaweb.controller.relatorio.PrevisaoPendenciaRecebimentoController;
import br.com.nextage.rmaweb.controller.relatorio.RelatorioEstoqueInconsistenciaController;
import br.com.nextage.rmaweb.controller.rm.material.status.aprovadores.ObterAprovadoresRmMaterialController;
import br.com.nextage.rmaweb.controller.rm.material.status.aprovadores.ObterAprovadoresRmMaterialDao;
import br.com.nextage.rmaweb.controller.rm.material.status.aprovadores.ObterAprovadoresRmMaterialService;
import br.com.nextage.rmaweb.controller.rm.material.status.saldo.ObterSaldoRequisicaoMaterialController;
import br.com.nextage.rmaweb.controller.rm.material.status.saldo.ObterSaldoRequisicaoMaterialDao;
import br.com.nextage.rmaweb.controller.rm.material.status.saldo.ObterSaldoRequisicaoMaterialService;
import br.com.nextage.rmaweb.controller.rm.material.status.todos.ObterTodosAprovadoresRmMaterialController;
import br.com.nextage.rmaweb.controller.rm.material.status.todos.ObterTodosAprovadoresRmMaterialService;
import br.com.nextage.rmaweb.controller.rm.servico.status.aprovadores.ObterAprovadoresRmServicoController;
import br.com.nextage.rmaweb.controller.rm.servico.status.aprovadores.ObterAprovadoresRmServicoDao;
import br.com.nextage.rmaweb.controller.rm.servico.status.aprovadores.ObterAprovadoresRmServicoService;
import br.com.nextage.rmaweb.controller.rm.servico.status.todos.ObterTodosAprovadoresRmServicoController;
import br.com.nextage.rmaweb.controller.rm.servico.status.todos.ObterTodosAprovadoresRmServicoService;
import br.com.nextage.rmaweb.service.ConfiguracaoService;
import br.com.nextage.rmaweb.service.DepositoService;
import br.com.nextage.rmaweb.service.PessoaService;

@ApplicationPath("v1")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        // add single resources
        register(new ApplicationBinder());
        register(ObjectMapperProvider.class);
        register(JacksonFeature.class);
        register(AuthenticationMobileFilter.class);
        register(CORSResponseFilter.class);
        register(RequestFilter.class);

        register(PainelRequisicaoMateriaisController.class);
        register(GestaoRmaController.class);
        register(PrevisaoPendenciaRecebimentoController.class);
        register(RelatorioEstoqueInconsistenciaController.class);

        register(AdministradorController.class);
        register(AnexoController.class);
        register(AnexoServicoController.class);
        register(AutoCompleteController.class);
        register(CadastroObraController.class);
        register(CadastroRmaController.class);
        register(CadastroRmaServicoController.class);
        register(ComboController.class);
        register(CompradorController.class);
        register(ConfiguracaoController.class);
        register(DocumentoResponsabilidadeIndisRecController.class);
        register(DocumentoResponsabilidadePendController.class);
        register(HierarquiaCompradorController.class);
        register(LoginController.class);
        register(LogInterfaceController.class);
        register(MateriaisSemCodigoSapController.class);
        register(PainelEstoquistaController.class);
        register(PerfilController.class);
        register(RastreabilidadeController.class);
        register(ResourceBundleController.class);
        register(RmAprovacaoController.class);
        register(UsuarioController.class);
        register(WorkflowController.class);
        register(EmailController.class);
        register(ConfiguracaoService.class);
        register(BuscarEstoqueMaterialController.class);



        register(PessoaService.class);
        register(ObterAprovadoresRmMaterialService.class);
        register(ObterAprovadoresRmMaterialDao.class);


        register(EmailService.class);
        register(DepositoService.class);
        register(ObterTodosAprovadoresRmMaterialService.class);

        register(ObterAprovadoresRmMaterialController.class);
        register(ObterTodosAprovadoresRmMaterialController.class);
        register(ObterPessoaPorPerfilECentroController.class);

        register(ObterSaldoRequisicaoMaterialController.class);
        register(ObterSaldoRequisicaoMaterialService.class);
        register(ObterSaldoRequisicaoMaterialDao.class);
        /**Servicos*/
        register(ObterTodosAprovadoresRmServicoService.class);
        register(ObterAprovadoresRmServicoController.class);
        register(ObterTodosAprovadoresRmServicoController.class);
        register(ObterAprovadoresRmServicoService.class);
        register(ObterAprovadoresRmServicoDao.class);
    }
}
