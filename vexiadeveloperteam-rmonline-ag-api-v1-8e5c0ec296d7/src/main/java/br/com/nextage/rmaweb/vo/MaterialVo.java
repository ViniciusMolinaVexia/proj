package br.com.nextage.rmaweb.vo;

import br.com.nextage.rmaweb.entitybean.Material;

/**
 *
 * @author nextage
 */
public class MaterialVo {

    private Material material;
    private Double quantidade;
    private Boolean reserva;
    private Integer numRmMaterial;

    public MaterialVo() {
        reserva = false;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Boolean getReserva() {
        return reserva;
    }

    public void setReserva(Boolean reserva) {
        this.reserva = reserva;
    }

    public Integer getNumRmMaterial() {
        return numRmMaterial;
    }

    public void setNumRmMaterial(Integer numRmMaterial) {
        this.numRmMaterial = numRmMaterial;
    }

}
