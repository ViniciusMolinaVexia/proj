/*
 * NextAge License
 * Copyright 2015 - Nextage
 *
 */
package br.com.nextage.rmaweb.filtro;

import br.com.nextage.rmaweb.entitybean.Deposito;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.util.filter.FiltroGeral;

/**
 *
 * @author Yuri Mello da Costa - <b>y.mello@nextage.com.br<b>
 */
public class FiltroRelatorioEstoqueInconsistencia extends FiltroGeral {

    private Material material;
    private String materialChave;
    private String sistema;
    private Integer materialDepositoId;
    private Double quantidade;
    private String justificativaAlteracaoQuantidade;
    private String justificativaAdicionarEstoque;
    private Deposito deposito;
    private Boolean inclusao;
    private Boolean retirada;
    private Double quantidadeDiferenca;
    private Boolean inconsistencia;
    private Double quantidadeCp;
    private Boolean checkRma;
    private Boolean checkCp;
    private Boolean novoMaterialDeposito;
    private String codDeposito;
    private String operacao;
    private String prefixoEquipamento;
    private Boolean patrimoniado;

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

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public Integer getMaterialDepositoId() {
        return materialDepositoId;
    }

    public void setMaterialDepositoId(Integer materialDepositoId) {
        this.materialDepositoId = materialDepositoId;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getJustificativaAlteracaoQuantidade() {
        return justificativaAlteracaoQuantidade;
    }

    public void setJustificativaAlteracaoQuantidade(String justificativaAlteracaoQuantidade) {
        this.justificativaAlteracaoQuantidade = justificativaAlteracaoQuantidade;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public Boolean getInclusao() {
        return inclusao;
    }

    public void setInclusao(Boolean inclusao) {
        this.inclusao = inclusao;
    }

    public Boolean getRetirada() {
        return retirada;
    }

    public void setRetirada(Boolean retirada) {
        this.retirada = retirada;
    }

    public Double getQuantidadeDiferenca() {
        return quantidadeDiferenca;
    }

    public void setQuantidadeDiferenca(Double quantidadeDiferenca) {
        this.quantidadeDiferenca = quantidadeDiferenca;
    }

    public Boolean getInconsistencia() {
        return inconsistencia;
    }

    public void setInconsistencia(Boolean inconsistencia) {
        this.inconsistencia = inconsistencia;
    }

    public Double getQuantidadeCp() {
        return quantidadeCp;
    }

    public void setQuantidadeCp(Double quantidadeCp) {
        this.quantidadeCp = quantidadeCp;
    }

    public Boolean getCheckRma() {
        return checkRma;
    }

    public void setCheckRma(Boolean checkRma) {
        this.checkRma = checkRma;
    }

    public Boolean getCheckCp() {
        return checkCp;
    }

    public void setCheckCp(Boolean checkCp) {
        this.checkCp = checkCp;
    }

    public Boolean getNovoMaterialDeposito() {
        return novoMaterialDeposito;
    }

    public void setNovoMaterialDeposito(Boolean novoMaterialDeposito) {
        this.novoMaterialDeposito = novoMaterialDeposito;
    }

    public String getCodDeposito() {
        return codDeposito;
    }

    public void setCodDeposito(String codDeposito) {
        this.codDeposito = codDeposito;
    }

    public String getJustificativaAdicionarEstoque() {
        return justificativaAdicionarEstoque;
    }

    public void setJustificativaAdicionarEstoque(String justificativaAdicionarEstoque) {
        this.justificativaAdicionarEstoque = justificativaAdicionarEstoque;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public Boolean getPatrimoniado() {
        return patrimoniado;
    }

    public void setPatrimoniado(Boolean patrimoniado) {
        this.patrimoniado = patrimoniado;
    }

    public String getPrefixoEquipamento() {
        return prefixoEquipamento;
    }

    public void setPrefixoEquipamento(String prefixoEquipamento) {
        this.prefixoEquipamento = prefixoEquipamento;
    }
}
