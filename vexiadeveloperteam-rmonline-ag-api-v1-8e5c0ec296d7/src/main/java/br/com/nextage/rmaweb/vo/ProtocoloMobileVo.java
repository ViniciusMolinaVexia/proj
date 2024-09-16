package br.com.nextage.rmaweb.vo;

import br.com.nextage.rmaweb.entitybean.VwRmMaterial;

import java.util.List;

/**
 * Created by Marlos on 03/05/2016.
 */
public class ProtocoloMobileVo {

    private String protocolo;
    private List<VwRmMaterial> listaVwRmMaterial;

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public List<VwRmMaterial> getListaVwRmMaterial() {
        return listaVwRmMaterial;
    }

    public void setListaVwRmMaterial(List<VwRmMaterial> listaVwRmMaterial) {
        this.listaVwRmMaterial = listaVwRmMaterial;
    }
}
