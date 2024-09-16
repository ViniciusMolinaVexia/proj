package br.com.nextage.rmaweb.vo.integracao;

/**
 * @author l.pordeus
 */
public class DepositoResponse {

    private String tipoMensagem;
    private String mensagem;
    private String codigo;
    private String codigoCentro;
    private String codigoDeposito;
    private String descricao;

    public DepositoResponse() {
    }

    public DepositoResponse(String tipoMensagem, String mensagem, String codigo, String codigoCentro, String codigoDeposito, String descricao) {
        this.tipoMensagem = tipoMensagem;
        this.mensagem = mensagem;
        this.codigo = codigo;
        this.codigoCentro = codigoCentro;
        this.codigoDeposito = codigoDeposito;
        this.descricao = descricao;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

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
}
