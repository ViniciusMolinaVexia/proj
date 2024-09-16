package br.com.nextage.rmaweb.ws.receberentradamaterial;

import java.math.BigDecimal;
import java.util.Date;

public class ReceberEntradaMaterialRequest {

    private String codigoMaterial;
    private String numeroRma;
    private String itemRmSap;
    private String rmSap;
    private String documentoEntrada;
    private BigDecimal quantidade;
    private Date dataRecebimento;

    public String getCodigoMaterial() {
        return codigoMaterial;
    }

    public void setCodigoMaterial(String codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
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

    public String getDocumentoEntrada() {
        return documentoEntrada;
    }

    public void setDocumentoEntrada(String documentoEntrada) {
        this.documentoEntrada = documentoEntrada;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	@Override
    public String toString() {
        return "ReceberEntradaMaterialRequest{" +
          "codigoMaterial='" + codigoMaterial + '\'' +
          ", numeroRma='" + numeroRma + '\'' +
          ", itemRmSap='" + itemRmSap + '\'' +
          ", rmSap='" + rmSap + '\'' +
          ", documentoEntrada='" + documentoEntrada + '\'' +
          ", quantidade=" + quantidade  + '\'' +
          ", dataRecebimento='" + dataRecebimento +
          '}';
    }
}
