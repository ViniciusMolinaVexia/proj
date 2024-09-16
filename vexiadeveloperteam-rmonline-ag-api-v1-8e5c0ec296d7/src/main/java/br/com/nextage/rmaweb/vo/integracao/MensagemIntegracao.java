package br.com.nextage.rmaweb.vo.integracao;

/**
 *
 * @author l.pordeus
 */
public class MensagemIntegracao {

    private String tipoMensagem;
    private String mensagem;
    private String codigo;

    public MensagemIntegracao() {
    }

    public MensagemIntegracao(String tipoMensagem, String mensagem,String codigo) {
        this.tipoMensagem = tipoMensagem;
        this.mensagem = mensagem;
        this.codigo = codigo;
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
}
