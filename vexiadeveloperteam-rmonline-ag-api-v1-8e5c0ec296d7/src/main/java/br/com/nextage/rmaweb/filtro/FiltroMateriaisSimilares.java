package br.com.nextage.rmaweb.filtro;

import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.util.filter.FiltroGeral;

/**
 *
 * @author nextage
 */
public class FiltroMateriaisSimilares extends FiltroGeral {

    private Material material;
    private String codigoCentro;
    private Integer centroId;

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getCodigoCentro() {
        return codigoCentro;
    }

    public void setCodigoCentro(String codigoCentro) {
        this.codigoCentro = codigoCentro;
    }

    public Integer getCentroId() {
        return centroId;
    }

    public void setCentroId(Integer centroId) {
        this.centroId = centroId;
    }
}
