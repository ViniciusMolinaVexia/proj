package br.com.nextage.rmaweb.vo;

import br.com.nextage.util.Info;

/**
 * Created by Marlos on 12/05/2016.
 */
public class LogInterfaceVo {

    public LogInterfaceVo()
    {}

    public LogInterfaceVo(Info info)
    {
        tipoMensagem = info.getTipo();
        if(tipoMensagem != null && tipoMensagem.length()>1){
            if(tipoMensagem.contains(Info.TIPO_MSG_DANGER))
                tipoMensagem = "E";
            if(tipoMensagem.contains(Info.TIPO_MSG_SUCCESS))
                tipoMensagem = "S";
        }
        mensagem = info.getMensagem();
        codigo = info.getCodigo();
        erroMensagem = info.getErro();
        objeto = info.getObjeto();
    }

    private String tipoMensagem;
    private String mensagem;
    private String codigo;
    private String erroMensagem;
    private Object objeto;

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

    public String getErroMensagem() {
        return erroMensagem;
    }

    public void setErroMensagem(String erroMensagem) {
        this.erroMensagem = erroMensagem;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }
}
