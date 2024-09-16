package br.com.nextage.rmaweb.controller;

import java.io.Serializable;
import java.util.List;

public class ConsultaMateriaisSimilaresSap implements Serializable {

    /**
     * Nome do centro
     */
    private String centro;

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

    public List<String> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<String> materiais) {
        this.materiais = materiais;
    }
}
