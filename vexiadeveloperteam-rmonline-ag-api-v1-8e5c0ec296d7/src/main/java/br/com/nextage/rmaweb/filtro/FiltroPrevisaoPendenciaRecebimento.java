/*
 * NextAge License
 * Copyright 2015 - Nextage
 *
 */
package br.com.nextage.rmaweb.filtro;

import br.com.nextage.rmaweb.entitybean.Deposito;
import br.com.nextage.rmaweb.entitybean.EapMultiEmpresa;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.util.filter.FiltroGeral;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Luan L F Domingues - <b>l.domingues@nextage.com.br<b>
 */
public class FiltroPrevisaoPendenciaRecebimento extends FiltroGeral {

    private String numeroRm;
    private Pessoa requisitante;
    private Material material;
    private String materialChave;
    private List<Deposito> deposito;
    private List<Integer> idsDeposito;
    private Integer diasPrevistos;
    private String tipoPendencia;
    private Date dataPrevisaoChegadaInicio;
    private Date dataPrevisaoChegadaFim;
    private String stDataPrevisaoChegadaInicio;
    private String stDataPrevisaoChegadaFim;
    private EapMultiEmpresa eapMultiEmpresa;

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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getMaterialChave() {
        return materialChave;
    }

    public void setMaterialChave(String materialChave) {
        this.materialChave = materialChave;
    }

    public List<Deposito> getDeposito() {
        return deposito;
    }

    public void setDeposito(List<Deposito> deposito) {
        this.deposito = deposito;
    }

    public Integer getDiasPrevistos() {
        return diasPrevistos;
    }

    public void setDiasPrevistos(Integer diasPrevistos) {
        this.diasPrevistos = diasPrevistos;
    }

    public String getTipoPendencia() {
        return tipoPendencia;
    }

    public void setTipoPendencia(String tipoPendencia) {
        this.tipoPendencia = tipoPendencia;
    }

    public Date getDataPrevisaoChegadaInicio() {
        return dataPrevisaoChegadaInicio;
    }

    public void setDataPrevisaoChegadaInicio(Date dataPrevisaoChegadaInicio) {
        this.dataPrevisaoChegadaInicio = dataPrevisaoChegadaInicio;
    }

    public Date getDataPrevisaoChegadaFim() {
        return dataPrevisaoChegadaFim;
    }

    public void setDataPrevisaoChegadaFim(Date dataPrevisaoChegadaFim) {
        this.dataPrevisaoChegadaFim = dataPrevisaoChegadaFim;
    }

    public String getStDataPrevisaoChegadaInicio() {
        return stDataPrevisaoChegadaInicio;
    }

    public void setStDataPrevisaoChegadaInicio(String stDataPrevisaoChegadaInicio) {
        this.stDataPrevisaoChegadaInicio = stDataPrevisaoChegadaInicio;
    }

    public String getStDataPrevisaoChegadaFim() {
        return stDataPrevisaoChegadaFim;
    }

    public void setStDataPrevisaoChegadaFim(String stDataPrevisaoChegadaFim) {
        this.stDataPrevisaoChegadaFim = stDataPrevisaoChegadaFim;
    }

    public List<Integer> getIdsDeposito() {
        return idsDeposito;
    }

    public void setIdsDeposito(List<Integer> idsDeposito) {
        this.idsDeposito = idsDeposito;
    }

    public EapMultiEmpresa getEapMultiEmpresa() {
        return eapMultiEmpresa;
    }

    public void setEapMultiEmpresa(EapMultiEmpresa eapMultiEmpresa) {
        this.eapMultiEmpresa = eapMultiEmpresa;
    }
}
