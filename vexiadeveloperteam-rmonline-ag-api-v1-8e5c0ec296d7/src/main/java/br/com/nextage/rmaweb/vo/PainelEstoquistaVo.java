package br.com.nextage.rmaweb.vo;

import br.com.nextage.rmaweb.entitybean.RmMaterial;

/**
 *
 * @author nextage
 */
public class PainelEstoquistaVo {

    private RmMaterial materialAnterior;
    private RmMaterial materialAtual;

    public RmMaterial getMaterialAnterior() {return materialAnterior;}
    public void setMaterialAnterior(RmMaterial materialAnterior) {this.materialAnterior= materialAnterior;}
    public RmMaterial getMaterialAtual() {return materialAtual;}
    public void setMaterialAtual(RmMaterial materialAtual) {this.materialAtual= materialAtual;}
}
