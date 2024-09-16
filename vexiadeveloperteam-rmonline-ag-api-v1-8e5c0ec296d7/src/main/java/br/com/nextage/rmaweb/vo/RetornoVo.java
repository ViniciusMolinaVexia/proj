package br.com.nextage.rmaweb.vo;

import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.entitybean.RmMaterialStatus;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nextage
 */
public class RetornoVo {

    private List<RmMaterialStatus> listaMaterialStatus;

    private List<RmMaterial> listaRmMaterialReserva;
    private List<RmMaterial> listaRmMaterialCompra;

    public RetornoVo() {
        this.listaMaterialStatus = new ArrayList<>();
        this.listaRmMaterialReserva = new ArrayList<>();
        this.listaRmMaterialCompra = new ArrayList<>();
    }

    public List<RmMaterialStatus> getListaMaterialStatus() {
        return listaMaterialStatus;
    }

    public void setListaMaterialStatus(List<RmMaterialStatus> listaMaterialStatus) {
        this.listaMaterialStatus = listaMaterialStatus;
    }

    public List<RmMaterial> getListaRmMaterialReserva() {
        return listaRmMaterialReserva;
    }

    public void setListaRmMaterialReserva(List<RmMaterial> listaRmMaterialReserva) {
        this.listaRmMaterialReserva = listaRmMaterialReserva;
    }

    public List<RmMaterial> getListaRmMaterialCompra() {
        return listaRmMaterialCompra;
    }

    public void setListaRmMaterialCompra(List<RmMaterial> listaRmMaterialCompra) {
        this.listaRmMaterialCompra = listaRmMaterialCompra;
    }
}
