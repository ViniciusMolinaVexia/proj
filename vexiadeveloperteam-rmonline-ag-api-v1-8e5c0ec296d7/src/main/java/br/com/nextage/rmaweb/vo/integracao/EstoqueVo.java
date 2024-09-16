package br.com.nextage.rmaweb.vo.integracao;

import java.util.Date;

/**
 *
 * @author nextage
 */
public class EstoqueVo {

    private String item;
    private String codigoMaterial;
    private String deposito;
    private String centro;
    private String quantidade;
    private String unidadeMedida;
    private String usuario;
    private String documentoMaterial;
    private String documentoReferencia;
    private String pedidoCompra;
    private String reservarMaterial;
    private String estorno;
    private String numeroRma;
    private Date dataEmissaoNf;
    private String numeroNf;
    private String rmSap;
    private String itemRmSap;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCodigoMaterial() {
        return codigoMaterial;
    }

    public void setCodigoMaterial(String codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDocumentoMaterial() {
        return documentoMaterial;
    }

    public void setDocumentoMaterial(String documentoMaterial) {
        this.documentoMaterial = documentoMaterial;
    }

    public String getDocumentoReferencia() {
        return documentoReferencia;
    }

    public void setDocumentoReferencia(String documentoReferencia) {
        this.documentoReferencia = documentoReferencia;
    }

    public String getPedidoCompra() {
        return pedidoCompra;
    }

    public void setPedidoCompra(String pedidoCompra) {
        this.pedidoCompra = pedidoCompra;
    }

    public String getReservarMaterial() {
        return reservarMaterial;
    }

    public void setReservarMaterial(String reservarMaterial) {
        this.reservarMaterial = reservarMaterial;
    }

    public String getEstorno() {
        return estorno;
    }

    public void setEstorno(String estorno) {
        this.estorno = estorno;
    }

    public String getNumeroRma() {
        return numeroRma;
    }

    public void setNumeroRma(String numeroRma) {
        this.numeroRma = numeroRma;
    }

    public Date getDataEmissaoNf() {
        return dataEmissaoNf;
    }

    public void setDataEmissaoNf(Date dataEmissaoNf) {
        this.dataEmissaoNf = dataEmissaoNf;
    }

    public String getNumeroNf() {
        return numeroNf;
    }

    public void setNumeroNf(String numeroNf) {
        this.numeroNf = numeroNf;
    }

    public String getRmSap() {
        return rmSap;
    }

    public void setRmSap(String rmSap) {
        this.rmSap = rmSap;
    }

    public String getItemRmSap() {
        return itemRmSap;
    }

    public void setItemRmSap(String itemRmSap) {
        this.itemRmSap = itemRmSap;
    }
}
