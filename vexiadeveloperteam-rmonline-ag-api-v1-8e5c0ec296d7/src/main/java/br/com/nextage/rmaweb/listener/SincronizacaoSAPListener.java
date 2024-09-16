package br.com.nextage.rmaweb.listener;

import br.com.nextage.rmaweb.ws.sap.CompraMateriaisSapService;
import br.com.nextage.rmaweb.ws.sap.RetiradaMateriaisSapService;
import br.com.nextage.util.GenericImportacaoListener;

    /**
     *
     * @author Jerry
     */
    public class SincronizacaoSAPListener  extends GenericImportacaoListener {

        public SincronizacaoSAPListener() {
            super("SincronizacaoSAP");
        }

        @Override
        protected void executar() {
            //ReservaMateriaisSapService reservaMateriaisSap = new ReservaMateriaisSapService();
            //reservaMateriaisSap.sincronizar();

            CompraMateriaisSapService compraMateriaisSapService = new CompraMateriaisSapService();
            compraMateriaisSapService.sincronizar();

            RetiradaMateriaisSapService retiradaMateriaisService = new RetiradaMateriaisSapService();
            retiradaMateriaisService.sincronizar();
        }
    }
