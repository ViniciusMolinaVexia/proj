package br.com.nextage.rmaweb.vo.integracao;

public class DepositoVO {

    private String codigoCentro;
    private String codigoDeposito;
    private String descricao;

    public String getCodigoCentro() {
        return codigoCentro;
    }

    public void setCodigoCentro(String codigoCentro) {
        this.codigoCentro = codigoCentro;
    }

    public String getCodigoDeposito() {
        return codigoDeposito;
    }

    public void setCodigoDeposito(String codigoDeposito) {
        this.codigoDeposito = codigoDeposito;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "DepositoVO{" +
                "codigoCentro='" + codigoCentro + '\'' +
                ", codigoDeposito='" + codigoDeposito + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
