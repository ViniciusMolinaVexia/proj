package br.com.nextage.rmaweb.vo;

/**
 *
 * @author l.pordeus
 */
public class MaterialDepositoVo {

    private String material;
    private String deposito;
    private Double qtdeInserida;
    private String codigoRequisicao;
    private String retiradoPor;
    private Boolean reaproveitadosEpi;
    //Campos para identificação
    private String origemMovimentacao;
    private String origemSincRegistro;
    private Integer identificadorRmMaterial;
    private String depositoEap;
    private String pessoaEap;

    public MaterialDepositoVo() {
    }

    public Boolean getReaproveitadosEpi() {
        return reaproveitadosEpi;
    }

    public void setReaproveitadosEpi(Boolean reaproveitadosEpi) {
        this.reaproveitadosEpi = reaproveitadosEpi;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public Double getQtdeInserida() {
        return qtdeInserida;
    }

    public void setQtdeInserida(Double qtdeInserida) {
        this.qtdeInserida = qtdeInserida;
    }

    public String getCodigoRequisicao() {
        return codigoRequisicao;
    }

    public void setCodigoRequisicao(String codigoRequisicao) {
        this.codigoRequisicao = codigoRequisicao;
    }

    public String getRetiradoPor() {
        return retiradoPor;
    }

    public void setRetiradoPor(String retiradoPor) {
        this.retiradoPor = retiradoPor;
    }

    public String getOrigemMovimentacao() {
        return origemMovimentacao;
    }

    public void setOrigemMovimentacao(String origemMovimentacao) {
        this.origemMovimentacao = origemMovimentacao;
    }

    public String getOrigemSincRegistro() {
        return origemSincRegistro;
    }

    public void setOrigemSincRegistro(String origemSincRegistro) {
        this.origemSincRegistro = origemSincRegistro;
    }

    public Integer getIdentificadorRmMaterial() {
        return identificadorRmMaterial;
    }

    public void setIdentificadorRmMaterial(Integer identificadorRmMaterial) {
        this.identificadorRmMaterial = identificadorRmMaterial;
    }

    public String getDepositoEap() {
        return depositoEap;
    }

    public void setDepositoEap(String depositoEap) {
        this.depositoEap = depositoEap;
    }

    public String getPessoaEap() {
        return pessoaEap;
    }

    public void setPessoaEap(String pessoaEap) {
        this.pessoaEap = pessoaEap;
    }
}
