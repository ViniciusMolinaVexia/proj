package br.com.nextage.rmaweb.controller.rm.material.status.aprovadores;

import java.io.Serializable;

public class ObterAprovadoresRmMaterialRequest implements Serializable {
    private Integer idRm;
    private Integer idRmMaterial;

    public ObterAprovadoresRmMaterialRequest() {
    }

    public ObterAprovadoresRmMaterialRequest(Integer idRm, Integer idRmMaterial) {
        this.idRm = idRm;
        this.idRmMaterial = idRmMaterial;
    }

    public Integer getIdRm() {
        return idRm;
    }

    public void setIdRm(Integer idRm) {
        this.idRm = idRm;
    }

    public Integer getIdRmMaterial() {
        return idRmMaterial;
    }

    public void setIdRmMaterial(Integer idRmMaterial) {
        this.idRmMaterial = idRmMaterial;
    }
}
