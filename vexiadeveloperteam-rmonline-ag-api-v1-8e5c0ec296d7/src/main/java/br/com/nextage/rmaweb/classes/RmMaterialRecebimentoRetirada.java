/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.classes;

import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.entitybean.RmMaterialRecebimento;
import br.com.nextage.rmaweb.entitybean.RmMaterialRetirada;
import java.util.List;

/**
 *
 * @author Jerry
 */
public class RmMaterialRecebimentoRetirada {

    //RM MATERIAL
    private RmMaterial rmMaterial = new RmMaterial();

    private List<RmMaterialRecebimento> listaRecebimento;
    private List<RmMaterialRetirada> listaRetirada;

    public List<RmMaterialRecebimento> getListaRecebimento() {
        return listaRecebimento;
    }

    public void setListaRecebimento(List<RmMaterialRecebimento> listaRecebimento) {
        this.listaRecebimento = listaRecebimento;
    }

    public List<RmMaterialRetirada> getListaRetirada() {
        return listaRetirada;
    }

    public void setListaRetirada(List<RmMaterialRetirada> listaRetirada) {
        this.listaRetirada = listaRetirada;
    }

    public RmMaterial getRmMaterial() {
        return rmMaterial;
    }

    public void setRmMaterial(RmMaterial rmMaterial) {
        this.rmMaterial = rmMaterial;
    }

}
