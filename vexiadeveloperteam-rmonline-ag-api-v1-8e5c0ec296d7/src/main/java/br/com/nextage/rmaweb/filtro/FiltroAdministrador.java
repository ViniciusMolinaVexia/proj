/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.filtro;

import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.util.filter.FiltroGeral;

/**
 *
 * @author nextage
 */
public class FiltroAdministrador extends FiltroGeral {

    private String numeroRm;
    private Pessoa requisitante;
    private String status;
    private String sistema;

    public String getNumeroRm() {
        return numeroRm;
    }

    public void setNumeroRm(String numeroRm) {
        this.numeroRm = numeroRm;
    }

    public Pessoa getRequisitante() {
        return requisitante;
    }

    public void setRequisitante(Pessoa requisitante) {
        this.requisitante = requisitante;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }
}
