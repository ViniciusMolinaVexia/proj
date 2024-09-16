/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.vo;

import br.com.nextage.rmaweb.entitybean.*;

import java.util.List;

/**
 *
 * @author nextage
 */
public class AdministradorVo {

    private List<RmMaterial> rmMateriais;
    private Rm rm;
    private SincRegistro sincRegistro;
    private MaterialDepositoSaida materialDepositoSaida;
    private Object objeto;
    private Boolean recebidoCp;

    public List<RmMaterial> getRmMateriais() {
        return rmMateriais;
    }

    public void setRmMateriais(List<RmMaterial> rmMateriais) {
        this.rmMateriais = rmMateriais;
    }

    public Rm getRm() {
        return rm;
    }

    public void setRm(Rm rm) {
        this.rm = rm;
    }

    public SincRegistro getSincRegistro() {
        return sincRegistro;
    }

    public void setSincRegistro(SincRegistro sincRegistro) {
        this.sincRegistro = sincRegistro;
    }

    public Boolean getRecebidoCp() {
        return recebidoCp;
    }

    public void setRecebidoCp(Boolean recebidoCp) {
        this.recebidoCp = recebidoCp;
    }

    public MaterialDepositoSaida getMaterialDepositoSaida() {
        return materialDepositoSaida;
    }

    public void setMaterialDepositoSaida(MaterialDepositoSaida materialDepositoSaida) {
        this.materialDepositoSaida = materialDepositoSaida;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }
}
