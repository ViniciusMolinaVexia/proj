package br.com.nextage.rmaweb.vo.integracao;

/**
 *
 * @author Lucas Heitor
 */
public class RmMaterialIntegracaoVo {

    private Integer itemRequisicaoSap;
    private String codigoMaterial;
    private Double quantidade;
    private String aplicacao;
    private String localEntrega;
    private String numeroRequisicaoSap;
    private String coletorCustosElementoPEP;
    private String coletorCustosDiagramaRede;
    private String coletorCustosOperacao;
    private Boolean compraEstoque;
    private String fluxoMaterial;
    private String descricao;
    private String depositoEntregaRetirada;

    public RmMaterialIntegracaoVo() {
    }

    public Integer getItemRequisicaoSap() {
        return itemRequisicaoSap;
    }

    public void setItemRequisicaoSap(Integer itemRequisicaoSap) {
        this.itemRequisicaoSap = itemRequisicaoSap;
    }

    public String getCodigoMaterial() {
        return codigoMaterial;
    }

    public void setCodigoMaterial(String codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(String aplicacao) {
        this.aplicacao = aplicacao;
    }

    public String getLocalEntrega() {
        return localEntrega;
    }

    public void setLocalEntrega(String localEntrega) {
        this.localEntrega = localEntrega;
    }

    public String getNumeroRequisicaoSap() {
        return numeroRequisicaoSap;
    }

    public void setNumeroRequisicaoSap(String numeroRequisicaoSap) {
        this.numeroRequisicaoSap = numeroRequisicaoSap;
    }

    public String getColetorCustosElementoPEP() {
        return coletorCustosElementoPEP;
    }

    public void setColetorCustosElementoPEP(String coletorCustosElementoPEP) {
        this.coletorCustosElementoPEP = coletorCustosElementoPEP;
    }

    public String getColetorCustosDiagramaRede() {
        return coletorCustosDiagramaRede;
    }

    public void setColetorCustosDiagramaRede(String coletorCustosDiagramaRede) {
        this.coletorCustosDiagramaRede = coletorCustosDiagramaRede;
    }

    public String getColetorCustosOperacao() {
        return coletorCustosOperacao;
    }

    public void setColetorCustosOperacao(String coletorCustosOperacao) {
        this.coletorCustosOperacao = coletorCustosOperacao;
    }

    public Boolean getCompraEstoque() {
        return compraEstoque;
    }

    public void setCompraEstoque(Boolean compraEstoque) {
        this.compraEstoque = compraEstoque;
    }

    public String getFluxoMaterial() {
        return fluxoMaterial;
    }

    public void setFluxoMaterial(String fluxoMaterial) {
        this.fluxoMaterial = fluxoMaterial;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDepositoEntregaRetirada() {
        return depositoEntregaRetirada;
    }

    public void setDepositoEntregaRetirada(String depositoEntregaRetirada) {
        this.depositoEntregaRetirada = depositoEntregaRetirada;
    }
}
