package br.com.nextage.rmaweb.vo.integracao;

/**
 *
 * @author Igor R. Pessoa
 */
public class EstornoVo {

    private String numeroRma;
    private String codigoMaterial;
    private String rmSap;
    private String itemRmSap; //Ordem
    private String centroCod;

    public String getNumeroRma() {
        return numeroRma;
    }

    public void setNumeroRma(String numeroRma) {
        this.numeroRma = numeroRma;
    }

    public String getCodigoMaterial() {
        return codigoMaterial;
    }

    public void setCodigoMaterial(String codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
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

    public String getCentroCod() {
        return centroCod;
    }

    public void setCentroCod(String centroCod) {
        this.centroCod = centroCod;
    }
}
