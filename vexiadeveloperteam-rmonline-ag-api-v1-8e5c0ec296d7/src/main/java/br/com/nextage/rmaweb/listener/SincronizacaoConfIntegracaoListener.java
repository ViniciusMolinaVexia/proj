package br.com.nextage.rmaweb.listener;

import br.com.nextage.rmaweb.service.integracao.SincCautelaService;
import br.com.nextage.rmaweb.service.integracao.SincEquipamentoService;
import br.com.nextage.rmaweb.service.integracao.SincReservaService;
import br.com.nextage.util.GenericImportacaoListener;

/**
 *
 * @author l.pordeus
 */
public class SincronizacaoConfIntegracaoListener extends GenericImportacaoListener {

    public SincronizacaoConfIntegracaoListener() {
        super("SincronizacaoCpweb");
    }

    /**
     * A ordem de sincronização deve ser 1-Equipamento, 2-Reserva e 3-Cautela, senão pode ocorrer problemas porque um registro depende do outro.
     */
    @Override
    protected void executar() {
        SincEquipamentoService sincEquipamentoService = new SincEquipamentoService();
        sincEquipamentoService.sincronizar();
        sincEquipamentoService.sincronizarTransferencia();

        SincReservaService sincReservaService = new SincReservaService();
        sincReservaService.sincronizar();

        SincCautelaService sincCautelaService = new SincCautelaService();
        sincCautelaService.sincronizar();
    }
}
