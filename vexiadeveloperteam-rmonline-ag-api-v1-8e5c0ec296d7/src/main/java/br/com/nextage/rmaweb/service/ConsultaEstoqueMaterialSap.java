package br.com.nextage.rmaweb.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConsultaEstoqueMaterialSap implements Serializable {

    /**
        Centro de consulta de estoque
     */
    private String centro;

    /**
     * Lista de materiais que devem ser consultados no centor informado
     */
    private List<String> materiais;

    /**
     * Codigo do deposito que se deseja consultar, este campo é opcional na consulta
     */
    private String deposito;

    public ConsultaEstoqueMaterialSap() {
        this.materiais = new ArrayList<>();
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public List<String> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<String> materiais) {
        this.materiais = materiais;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }
}
