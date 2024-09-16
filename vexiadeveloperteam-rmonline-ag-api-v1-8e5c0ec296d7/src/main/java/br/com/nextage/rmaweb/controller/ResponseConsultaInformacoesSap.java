package br.com.nextage.rmaweb.controller;

import java.io.Serializable;
import java.util.List;

import br.com.nextage.rmaweb.entitybean.GrupoComprador;

public class ResponseConsultaInformacoesSap implements Serializable {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Nome do centro consultado
     */
    private String centro;

    /**
     * Dados de material do SAP
     */
    private MaterialSap material;

    /**
     * Lista de compradores
     */
    private List<GrupoComprador> grupoCompradores;

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public MaterialSap getMaterial() {
        return material;
    }

    public void setMaterial(MaterialSap material) {
        this.material = material;
    }

	public List<GrupoComprador> getGrupoCompradores() {
		return grupoCompradores;
	}

	public void setGrupoCompradores(List<GrupoComprador> grupoCompradores) {
		this.grupoCompradores = grupoCompradores;
	}
}
