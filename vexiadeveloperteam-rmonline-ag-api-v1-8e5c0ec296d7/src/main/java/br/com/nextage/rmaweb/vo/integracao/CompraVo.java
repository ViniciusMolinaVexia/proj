package br.com.nextage.rmaweb.vo.integracao;

import java.math.BigDecimal;

/**
 * @author nextage
 */
public class CompraVo {

    private String codigoPedido;
    private String fornecedor;
    private String dataCompra;
    private String dataPrevisao;
    private String itemPedido;
    private String centro;
    private String material;
    private BigDecimal quantidade;
    private String precoUnitario;
    private String valorTotal;
    private String status;
    private String dataUltimaAtualizacao;
    private String horaUltimaAtualizacao;
    private String numeroRma;
    private String itemRmSap;
    private String rmSap;
    private String documentoCompra;
    private String grupoComprador;
    /**
     * Esse atributo vem preenchido quando a RMA foi aprovada no SAP;
     */
    private boolean aprovadoSap;

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(String itemPedido) {
        this.itemPedido = itemPedido;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(String precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(String dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public String getHoraUltimaAtualizacao() {
        return horaUltimaAtualizacao;
    }

    public void setHoraUltimaAtualizacao(String horaUltimaAtualizacao) {
        this.horaUltimaAtualizacao = horaUltimaAtualizacao;
    }

    public String getNumeroRma() {
        return numeroRma;
    }

    public void setNumeroRma(String numeroRma) {
        this.numeroRma = numeroRma;
    }

    public String getItemRmSap() {
        return itemRmSap;
    }

    public void setItemRmSap(String itemRmSap) {
        this.itemRmSap = itemRmSap;
    }

    public String getRmSap() {
        return rmSap;
    }

    public void setRmSap(String rmSap) {
        this.rmSap = rmSap;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getDataPrevisao() {
        return dataPrevisao;
    }

    public void setDataPrevisao(String dataPrevisao) {
        this.dataPrevisao = dataPrevisao;
    }

    public boolean isAprovadoSap() {
        return aprovadoSap;
    }

    public void setAprovadoSap(boolean aprovadoSap) {
        this.aprovadoSap = aprovadoSap;
    }

    public String getDocumentoCompra() {
        return documentoCompra;
    }

    public void setDocumentoCompra(String documentoCompra) {
        this.documentoCompra = documentoCompra;
    }

    public String getGrupoComprador() {
        return grupoComprador;
    }

    public void setGrupoComprador(String grupoComprador) {
        this.grupoComprador = grupoComprador;
    }

    @Override
    public String toString() {
        return "CompraVo{" +
                "codigoPedido='" + codigoPedido + '\'' +
                ", fornecedor='" + fornecedor + '\'' +
                ", dataCompra='" + dataCompra + '\'' +
                ", dataPrevisao='" + dataPrevisao + '\'' +
                ", itemPedido='" + itemPedido + '\'' +
                ", centro='" + centro + '\'' +
                ", material='" + material + '\'' +
                ", quantidade='" + quantidade + '\'' +
                ", precoUnitario='" + precoUnitario + '\'' +
                ", valorTotal='" + valorTotal + '\'' +
                ", status='" + status + '\'' +
                ", dataUltimaAtualizacao='" + dataUltimaAtualizacao + '\'' +
                ", horaUltimaAtualizacao='" + horaUltimaAtualizacao + '\'' +
                ", numeroRma='" + numeroRma + '\'' +
                ", itemRmSap='" + itemRmSap + '\'' +
                ", rmSap='" + rmSap + '\'' +
                ", aprovadoSap=" + aprovadoSap +
                ", documentoCompra=" + documentoCompra +
                '}';
    }
}
