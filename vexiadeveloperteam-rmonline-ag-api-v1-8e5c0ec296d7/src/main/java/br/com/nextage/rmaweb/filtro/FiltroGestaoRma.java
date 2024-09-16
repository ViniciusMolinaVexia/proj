/*
 * NextAge License
 * Copyright 2015 - Nextage
 *
 */
package br.com.nextage.rmaweb.filtro;

import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.util.filter.FiltroGeral;

/**
 *
 * @author Luan L F Domingues - <b>l.domingues@nextage.com.br<b>
 */
public class FiltroGestaoRma extends FiltroGeral {

    private String numeroRm;
    private Material material;
    private String materialChave;
    private Pessoa requisitante;
    private Pessoa aprovador;
    private Setor setor;
    private SubArea subArea;
    private String stDataEmissaoDe, stDataEmissaoAte;
    private String stDataNecessidadeDe, stDataNecessidadeAte;
    private Prioridade prioridade;
    private TipoRequisicao tipoRequisicao;
    private EapMultiEmpresa eapMultiEmpresa;

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

    public Pessoa getRequisitante() {
        return requisitante;
    }

    public void setRequisitante(Pessoa requisitante) {
        this.requisitante = requisitante;
    }

    public String getNumeroRm() {
        return numeroRm;
    }

    public void setNumeroRm(String numeroRm) {
        this.numeroRm = numeroRm;
    }

    public Pessoa getAprovador() {
        return aprovador;
    }

    public void setAprovador(Pessoa aprovador) {
        this.aprovador = aprovador;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public SubArea getSubArea() {
        return subArea;
    }

    public void setSubArea(SubArea subArea) {
        this.subArea = subArea;
    }

    public String getStDataEmissaoDe() {
        return stDataEmissaoDe;
    }

    public void setStDataEmissaoDe(String stDataEmissaoDe) {
        this.stDataEmissaoDe = stDataEmissaoDe;
    }

    public String getStDataEmissaoAte() {
        return stDataEmissaoAte;
    }

    public void setStDataEmissaoAte(String stDataEmissaoAte) {
        this.stDataEmissaoAte = stDataEmissaoAte;
    }

    public String getStDataNecessidadeDe() {
        return stDataNecessidadeDe;
    }

    public void setStDataNecessidadeDe(String stDataNecessidadeDe) {
        this.stDataNecessidadeDe = stDataNecessidadeDe;
    }

    public String getStDataNecessidadeAte() {
        return stDataNecessidadeAte;
    }

    public void setStDataNecessidadeAte(String stDataNecessidadeAte) {
        this.stDataNecessidadeAte = stDataNecessidadeAte;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public TipoRequisicao getTipoRequisicao() {
        return tipoRequisicao;
    }

    public void setTipoRequisicao(TipoRequisicao tipoRequisicao) {
        this.tipoRequisicao = tipoRequisicao;
    }

    public EapMultiEmpresa getEapMultiEmpresa() {
        return eapMultiEmpresa;
    }

    public void setEapMultiEmpresa(EapMultiEmpresa eapMultiEmpresa) {
        this.eapMultiEmpresa = eapMultiEmpresa;
    }
}
