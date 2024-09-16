package br.com.nextage.rmaweb.controller;

import java.util.List;

public class ConsultaMaterialSap {

    /**
     * Nome do centro
     */
    private String centro;

    /**
     * Codigo de identificao do deposito
     */
    private String deposito;


    /**
     * Lista de codigo de materiais que devem ser consultados
     */
    private List<String> materiais;

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public List<String> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<String> materiais) {
        this.materiais = materiais;
    }
}
