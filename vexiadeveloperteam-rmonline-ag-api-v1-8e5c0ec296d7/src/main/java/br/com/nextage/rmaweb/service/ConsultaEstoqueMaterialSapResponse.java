package br.com.nextage.rmaweb.service;

import java.io.Serializable;
import java.util.List;

public class ConsultaEstoqueMaterialSapResponse implements Serializable {

    private String nomeCentro;

    private List<EstoqueMateriaisSap> estoqueMateriaisSap;

    public String getNomeCentro() {
        return nomeCentro;
    }

    public void setNomeCentro(String nomeCentro) {
        this.nomeCentro = nomeCentro;
    }

    public List<EstoqueMateriaisSap> getEstoqueMateriaisSap() {
        return estoqueMateriaisSap;
    }

    public void setEstoqueMateriaisSap(List<EstoqueMateriaisSap> estoqueMateriaisSap) {
        this.estoqueMateriaisSap = estoqueMateriaisSap;
    }
}
