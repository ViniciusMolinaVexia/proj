package br.com.nextage.rmaweb.filtro;

import br.com.nextage.rmaweb.entitybean.TipoRequisicao;
import br.com.nextage.util.filter.FiltroAutoComplete;


public class FiltroAutoCompleteCadastroRma extends FiltroAutoComplete{
    
    private TipoRequisicao tipoRequisicao;

    private String codigoCentro;

    public TipoRequisicao getTipoRequisicao() {
        return tipoRequisicao;
    }

    public void setTipoRequisicao(TipoRequisicao tipoRequisicao) {
        this.tipoRequisicao = tipoRequisicao;
    }

    public String getCodigoCentro() {
        return codigoCentro;
    }

    public void setCodigoCentro(String codigoCentro) {
        this.codigoCentro = codigoCentro;
    }
}
