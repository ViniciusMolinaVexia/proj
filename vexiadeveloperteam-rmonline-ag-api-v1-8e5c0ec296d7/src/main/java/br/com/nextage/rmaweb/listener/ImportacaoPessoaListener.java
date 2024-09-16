package br.com.nextage.rmaweb.listener;

import br.com.nextage.rmaweb.service.SincPessoaService;
import br.com.nextage.util.GenericImportacaoListener;

/**
 *
 * @author l.pordeus
 */
public class ImportacaoPessoaListener extends GenericImportacaoListener {

    public ImportacaoPessoaListener() {
        super("ImportacaoPessoa");
    }

    @Override
    protected void executar() {
        SincPessoaService sincPessoaService = new SincPessoaService();
        sincPessoaService.sincronizar();
    }
}
