package br.com.nextage.rmaweb.controller;

import java.io.Serializable;
import java.util.List;

public class ResponseMateriaisSimilaresSap implements Serializable {

    /**
     * Nome do centro consultado
     */
    private String centro;

    /**
     * Lista de materiais similares encontrados no SAP
     */
    List<MateriaisSimilaresSap> materiais;


    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public List<MateriaisSimilaresSap> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<MateriaisSimilaresSap> materiais) {
        this.materiais = materiais;
    }

    @Override
    public String toString() {
        return "ResponseMateriaisSimilaresSap{" +
                "centro='" + centro + '\'' +
                ", materiais=" + materiais +
                '}';
    }
}
