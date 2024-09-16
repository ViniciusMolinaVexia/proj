package br.com.nextage.rmaweb.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import br.com.nextage.rmaweb.controller.notificacao.email.enviar.EmailService;
import br.com.nextage.rmaweb.controller.notificacao.email.enviar.EmailServiceImpl;
import br.com.nextage.rmaweb.controller.pessoa.obterPorPerfilECentro.ObterPessoaPorPerfilECentroDao;
import br.com.nextage.rmaweb.controller.pessoa.obterPorPerfilECentro.ObterPessoaPorPerfilECentroModelToResponseMapper;
import br.com.nextage.rmaweb.controller.pessoa.obterPorPerfilECentro.ObterPessoaPorPerfilECentroService;
import br.com.nextage.rmaweb.controller.pessoa.obterPorPerfilECentro.ObterPessoaPorPerfilECentroServiceImpl;
import br.com.nextage.rmaweb.controller.rm.material.status.aprovadores.ObterAprovadoresRmMaterialDao;
import br.com.nextage.rmaweb.controller.rm.material.status.aprovadores.ObterAprovadoresRmMaterialService;
import br.com.nextage.rmaweb.controller.rm.material.status.aprovadores.ObterAprovadoresRmMaterialServiceImpl;
import br.com.nextage.rmaweb.controller.rm.material.status.saldo.ObterSaldoRequisicaoMaterialDao;
import br.com.nextage.rmaweb.controller.rm.material.status.saldo.ObterSaldoRequisicaoMaterialService;
import br.com.nextage.rmaweb.controller.rm.material.status.saldo.ObterSaldoRequisicaoMaterialServiceImpl;
import br.com.nextage.rmaweb.controller.rm.material.status.todos.ObterTodosAprovadoresRmMaterialDao;
import br.com.nextage.rmaweb.controller.rm.material.status.todos.ObterTodosAprovadoresRmMaterialService;
import br.com.nextage.rmaweb.controller.rm.material.status.todos.ObterTodosAprovadoresRmMaterialServiceImpl;
import br.com.nextage.rmaweb.controller.rm.servico.status.aprovadores.ObterAprovadoresRmServicoDao;
import br.com.nextage.rmaweb.controller.rm.servico.status.aprovadores.ObterAprovadoresRmServicoService;
import br.com.nextage.rmaweb.controller.rm.servico.status.aprovadores.ObterAprovadoresRmServicoServiceImpl;
import br.com.nextage.rmaweb.controller.rm.servico.status.todos.ObterTodosAprovadoresRmServicoDao;
import br.com.nextage.rmaweb.controller.rm.servico.status.todos.ObterTodosAprovadoresRmServicoService;
import br.com.nextage.rmaweb.controller.rm.servico.status.todos.ObterTodosAprovadoresRmServicoServiceImpl;
import br.com.nextage.rmaweb.service.ConfiguracaoService;
import br.com.nextage.rmaweb.service.DepositoService;
import br.com.nextage.rmaweb.service.PessoaService;
import br.com.nextage.rmaweb.service.estoque.material.SapServiceBuscarEstoqueMaterialClient;
import br.com.nextage.rmaweb.service.estoque.material.SapServiceBuscarEstoqueMaterialClientImpl;
import br.com.nextage.rmaweb.service.requisicao.compra.SapServiceRequisicaoCompraClient;
import br.com.nextage.rmaweb.service.requisicao.compra.SapServiceRequisicaoCompraClientImpl;
import br.com.nextage.rmaweb.service.requisicao.reserva.SapServiceRequisicaoReservaClient;
import br.com.nextage.rmaweb.service.requisicao.reserva.SapServiceRequisicaoReservaClientImpl;

/**
 * Classe utilizada para declarar os bens que deveja utilizar via @inject com CDI na API.
 *
 * @author adriano.gomes
 */
public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        /**
         * Repository
         */
        bind(ObterAprovadoresRmMaterialDao.class).to(ObterAprovadoresRmMaterialDao.class);
        bind(ObterTodosAprovadoresRmMaterialDao.class).to(ObterTodosAprovadoresRmMaterialDao.class);
        /** Servico */
        bind(ObterAprovadoresRmServicoDao.class).to(ObterAprovadoresRmServicoDao.class);
        bind(ObterTodosAprovadoresRmServicoDao.class).to(ObterTodosAprovadoresRmServicoDao.class);
        
        bind(ObterPessoaPorPerfilECentroDao.class).to(ObterPessoaPorPerfilECentroDao.class);
        bind(ObterSaldoRequisicaoMaterialDao.class).to(ObterSaldoRequisicaoMaterialDao.class);

        /**
         * Service
         */
        bind(ObterAprovadoresRmMaterialServiceImpl.class).to(ObterAprovadoresRmMaterialService.class);
        bind(ObterTodosAprovadoresRmMaterialServiceImpl.class).to(ObterTodosAprovadoresRmMaterialService.class);
        /** Servico */
        bind(ObterAprovadoresRmServicoServiceImpl.class).to(ObterAprovadoresRmServicoService.class);
        bind(ObterTodosAprovadoresRmServicoServiceImpl.class).to(ObterTodosAprovadoresRmServicoService.class);
        
        bind(SapServiceRequisicaoCompraClientImpl.class).to(SapServiceRequisicaoCompraClient.class);
        bind(EmailServiceImpl.class).to(EmailService.class);
        bind(DepositoService.class).to(DepositoService.class);
        bind(ConfiguracaoService.class).to(ConfiguracaoService.class);
        bind(PessoaService.class).to(PessoaService.class);
        bind(ObterPessoaPorPerfilECentroServiceImpl.class).to(ObterPessoaPorPerfilECentroService.class);
        bind(SapServiceRequisicaoReservaClientImpl.class).to(SapServiceRequisicaoReservaClient.class);
        bind(SapServiceRequisicaoCompraClientImpl.class).to(SapServiceRequisicaoCompraClient.class);
        bind(ObterSaldoRequisicaoMaterialServiceImpl.class).to(ObterSaldoRequisicaoMaterialService.class);

        bind(SapServiceBuscarEstoqueMaterialClientImpl.class).to(SapServiceBuscarEstoqueMaterialClient.class);

        /**
         * Mappers
         */
        bind(ObterPessoaPorPerfilECentroModelToResponseMapper.class).to(ObterPessoaPorPerfilECentroModelToResponseMapper.class);
    }
}
