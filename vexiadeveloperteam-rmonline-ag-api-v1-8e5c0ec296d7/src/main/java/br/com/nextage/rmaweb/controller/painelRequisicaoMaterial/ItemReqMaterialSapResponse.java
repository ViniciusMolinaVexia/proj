package br.com.nextage.rmaweb.controller.painelRequisicaoMaterial;

public class ItemReqMaterialSapResponse {
    /**
     * CODIGO RMA
     */
    protected String codigoRma;
    /**
     * CODIGO REQUISICAO SAP
     */
    protected String requisicao;
    /**
     * CODIGO ITEM SAP
     */
    protected String item;
    /**
     * NUMERO MATERIAL
     */
    protected String numeroMaterial;
    /**
     * S -> Sucesso
     * W -> Warning
     * E -> Error
     */
    protected String tipoMensagem;
    /**
     * Preenchido quando temos um tipo de msg W ou E.
     */
    protected String mensagem;

    public String getCodigoRma() {
        return codigoRma;
    }

    public void setCodigoRma(String codigoRma) {
        this.codigoRma = codigoRma;
    }

    public String getRequisicao() {
        return requisicao;
    }

    public void setRequisicao(String requisicao) {
        this.requisicao = requisicao;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getNumeroMaterial() {
        return numeroMaterial;
    }

    public void setNumeroMaterial(String numeroMaterial) {
        this.numeroMaterial = numeroMaterial;
    }

    public String getTipoMensagem() {
        return tipoMensagem;
    }

    public void setTipoMensagem(String tipoMensagem) {
        this.tipoMensagem = tipoMensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return "ItemReqMaterialSapResponse{" +
                "codigoRma='" + codigoRma + '\'' +
                ", requisicao='" + requisicao + '\'' +
                ", item='" + item + '\'' +
                ", numeroMaterial='" + numeroMaterial + '\'' +
                ", tipoMensagem='" + tipoMensagem + '\'' +
                ", mensagem='" + mensagem + '\'' +
                '}';
    }
}
