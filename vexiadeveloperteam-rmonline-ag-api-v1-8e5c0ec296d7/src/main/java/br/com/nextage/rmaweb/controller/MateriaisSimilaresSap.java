package br.com.nextage.rmaweb.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class MateriaisSimilaresSap implements Serializable {
    /**
     * Codigo do material
     */
    private String codigoMaterial;

    /**
     * Total de material nos depositos do centro
     */
    private BigDecimal totalEstoque;

    /**
     * Lista de deposito em que o material foi encontrado
     */
    List<DepositoSap> depositos;

    public String getCodigoMaterial() {
        return codigoMaterial;
    }

    public void setCodigoMaterial(String codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
    }

    public BigDecimal getTotalEstoque() {
        return totalEstoque;
    }

    public void setTotalEstoque(BigDecimal totalEstoque) {
        this.totalEstoque = totalEstoque;
    }

    public List<DepositoSap> getDepositos() {
        return depositos;
    }

    public void setDepositos(List<DepositoSap> depositos) {
        this.depositos = depositos;
    }
}
