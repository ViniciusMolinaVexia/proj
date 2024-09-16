package br.com.nextage.rmaweb.filtro;

import br.com.nextage.rmaweb.entitybean.RmServicoCodigoSap;
import br.com.nextage.rmaweb.entitybean.TipoRequisicao;
import br.com.nextage.util.filter.FiltroAutoComplete;


public class FiltroAutoCompleteServicosCodigoSap extends FiltroAutoComplete{
    
    private RmServicoCodigoSap rmServicoSap;


    public RmServicoCodigoSap getRmServicoSap() {
        return rmServicoSap;
    }

    public void setRmServicoSap(RmServicoCodigoSap rmServicoSap) {
        this.rmServicoSap = rmServicoSap;
    }
}
