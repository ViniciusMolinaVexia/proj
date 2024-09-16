/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.vo;

import br.com.nextage.rmaweb.entitybean.Deposito;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.RmMaterial;

/**
 *
 * @author Lucas Heitor
 */
public class RmMaterialRetiradaVo {
    
    private RmMaterial rmMaterial;
    private Deposito deposito;
    private Double quantidade;
    private Pessoa retiradoPor;
    private Double quantidadeMaxDepSelecionado;
    private boolean comBiometria;

    public RmMaterial getRmMaterial() {
        return rmMaterial;
    }

    public void setRmMaterial(RmMaterial rmMaterial) {
        this.rmMaterial = rmMaterial;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getQuantidadeMaxDepSelecionado() {
        return quantidadeMaxDepSelecionado;
    }

    public void setQuantidadeMaxDepSelecionado(Double quantidadeMaxDepSelecionado) {
        this.quantidadeMaxDepSelecionado = quantidadeMaxDepSelecionado;
    }    

    public boolean isComBiometria() {
        return comBiometria;
    }

    public void setComBiometria(boolean comBiometria) {
        this.comBiometria = comBiometria;
    }

    public Pessoa getRetiradoPor() {
        return retiradoPor;
    }

    public void setRetiradoPor(Pessoa retiradoPor) {
        this.retiradoPor = retiradoPor;
    }
    
    
}
