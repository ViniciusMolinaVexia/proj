/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.vo;

/**
 * @author nextage
 */
public class DepositoQuantidadeVo {

    private String codigoDeposito;
    private Double quantidade;

    public DepositoQuantidadeVo(String codigoDeposito, Double quantidade) {
        this.codigoDeposito = codigoDeposito;
        this.quantidade = quantidade;
    }

    public String getCodigoDeposito() {
        return codigoDeposito;
    }

    public void setCodigoDeposito(String codigoDeposito) {
        this.codigoDeposito = codigoDeposito;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
}
