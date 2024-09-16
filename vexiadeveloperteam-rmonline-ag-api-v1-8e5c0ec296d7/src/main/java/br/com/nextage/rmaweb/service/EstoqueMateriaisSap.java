package br.com.nextage.rmaweb.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class EstoqueMateriaisSap implements Serializable {

    private String codigoMaterial;

    private String nomeDeposito;

    private BigDecimal quantidadeEstoque;

    public String getCodigoMaterial() {
        return codigoMaterial;
    }

    public void setCodigoMaterial(String codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
    }

    public String getNomeDeposito() {
        return nomeDeposito;
    }

    public void setNomeDeposito(String nomeDeposito) {
        this.nomeDeposito = nomeDeposito;
    }

    public BigDecimal getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(BigDecimal quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EstoqueMateriaisSap)) return false;
        EstoqueMateriaisSap that = (EstoqueMateriaisSap) o;
        return getCodigoMaterial().equals(that.getCodigoMaterial()) &&
                Objects.equals(getNomeDeposito(), that.getNomeDeposito()) &&
                Objects.equals(getQuantidadeEstoque(), that.getQuantidadeEstoque());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigoMaterial(), getNomeDeposito(), getQuantidadeEstoque());
    }
}
