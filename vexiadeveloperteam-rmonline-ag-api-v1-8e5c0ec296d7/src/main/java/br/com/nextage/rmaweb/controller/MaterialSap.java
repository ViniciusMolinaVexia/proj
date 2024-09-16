package br.com.nextage.rmaweb.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class MaterialSap implements Serializable {

    /**
     * Codigo do material
     */
    private String codigo;

    /**
     * Total de materiais em estoque no centro
     */
    private BigDecimal totalEstoque;

    /**
     * Lista de depositos que o material está disponivel
     */
    private List<DepositoSap> depositos;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
