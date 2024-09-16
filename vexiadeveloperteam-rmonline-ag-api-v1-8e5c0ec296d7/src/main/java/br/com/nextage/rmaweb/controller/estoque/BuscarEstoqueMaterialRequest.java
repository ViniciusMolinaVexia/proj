package br.com.nextage.rmaweb.controller.estoque;

import java.math.BigDecimal;

public class BuscarEstoqueMaterialRequest {

    private String centro;
    private String codigoDeposito;
    private String nomeDeposito;
    private String codigoMaterial;
    private String nomeMaterial;
    private BigDecimal quantidade;

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getCodigoDeposito() {
        return codigoDeposito;
    }

    public void setCodigoDeposito(String codigoDeposito) {
        this.codigoDeposito = codigoDeposito;
    }

    public String getNomeDeposito() {
        return nomeDeposito;
    }

    public void setNomeDeposito(String nomeDeposito) {
        this.nomeDeposito = nomeDeposito;
    }

    public String getCodigoMaterial() {
        return codigoMaterial;
    }

    public void setCodigoMaterial(String codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
    }

    public String getNomeMaterial() {
        return nomeMaterial;
    }

    public void setNomeMaterial(String nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }
}
