/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.filtro;

import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.util.filter.FiltroGeral;

import java.util.List;

/**
 *
 * @author nextage
 */
public class FiltroComprador extends FiltroGeral {

    private String numeroRm;
    private String localEntrega;
    private String coletorCustos;
    private Material material;
    private String materialChave;
    private Pessoa requisitante;
    private Pessoa usuario;
    private String status;
    private String numeroPedido;
    private String numeroRequisicao;
    private TipoRequisicao tipoRequisicao;
    private Prioridade prioridade;
    private String itensConcluidos;
    private EapMultiEmpresa eapMultiEmpresa;
    private String tpMaterialServico;
    private List<Integer> idsSelecionados;
    private String exibirCanceladas;

    public String getItensConcluidos() {
        return itensConcluidos;
    }

    public void setItensConcluidos(String itensConcluidos) {
        this.itensConcluidos = itensConcluidos;
    }

    public String getNumeroRm() {
        return numeroRm;
    }

    public void setNumeroRm(String numeroRm) {
        this.numeroRm = numeroRm;
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

    public String getLocalEntrega() {
        return localEntrega;
    }

    public void setLocalEntrega(String localEntrega) {
        this.localEntrega = localEntrega;
    }

    public String getColetorCustos() {
        return coletorCustos;
    }

    public void setColetorCustos(String coletorCustos) {
        this.coletorCustos = coletorCustos;
    }

    public Pessoa getUsuario() {
        return usuario;
    }

    public void setUsuario(Pessoa usuario) {
        this.usuario = usuario;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getNumeroRequisicao() {
        return numeroRequisicao;
    }

    public void setNumeroRequisicao(String numeroRequisicao) {
        this.numeroRequisicao = numeroRequisicao;
    }

    public TipoRequisicao getTipoRequisicao() {
        return tipoRequisicao;
    }

    public void setTipoRequisicao(TipoRequisicao tipoRequisicao) {
        this.tipoRequisicao = tipoRequisicao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public EapMultiEmpresa getEapMultiEmpresa() {
        return eapMultiEmpresa;
    }

    public void setEapMultiEmpresa(EapMultiEmpresa eapMultiEmpresa) {
        this.eapMultiEmpresa = eapMultiEmpresa;
    }

    public String getTpMaterialServico() {
        return tpMaterialServico;
    }

    public void setTpMaterialServico(String tpMaterialServico) {
        this.tpMaterialServico = tpMaterialServico;
    }

    public List<Integer> getIdsSelecionados() {
        return idsSelecionados;
    }

    public void setIdsSelecionados(List<Integer> idsSelecionados) {
        this.idsSelecionados = idsSelecionados;
    }

    public String getExibirCanceladas() {
        return exibirCanceladas;
    }

    public void setExibirCanceladas(String exibirCanceladas) {
        this.exibirCanceladas = exibirCanceladas;
    }
}
