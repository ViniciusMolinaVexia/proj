package br.com.nextage.rmaweb.vo;

import java.util.Date;

/**
 * @brief Classe FiltroRmMaterialMobileVo
 * @author Alyson X. Leite <a.leite@nextage.com.br>
 * @date 26/01/2016
 */
public class FiltroRmMaterialMobileVo {

    private EncarregadoMobileVo encarregado;
    private UsuarioMobileVo cadastrante;
    private MaterialMobileVo material;
    private String stDataSolicitacaoDe;
    private String stDataSolicitacaoAte;
    private String rma;
    private String protocolo;
    private String somenteTermoEmitido;
    private String status;
    private Date dataSolicitacao;

    public EncarregadoMobileVo getEncarregado() {
        return encarregado;
    }

    public void setEncarregado(EncarregadoMobileVo encarregado) {
        this.encarregado = encarregado;
    }

    public UsuarioMobileVo getCadastrante() {
        return cadastrante;
    }

    public void setCadastrante(UsuarioMobileVo cadastrante) {
        this.cadastrante = cadastrante;
    }

    public MaterialMobileVo getMaterial() {
        return material;
    }

    public void setMaterial(MaterialMobileVo material) {
        this.material = material;
    }

    public String getStDataSolicitacaoDe() {
        return stDataSolicitacaoDe;
    }

    public void setStDataSolicitacaoDe(String stDataSolicitacaoDe) {
        this.stDataSolicitacaoDe = stDataSolicitacaoDe;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getSomenteTermoEmitido() {
        return somenteTermoEmitido;
    }

    public void setSomenteTermoEmitido(String somenteTermoEmitido) {
        this.somenteTermoEmitido = somenteTermoEmitido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStDataSolicitacaoAte() {
        return stDataSolicitacaoAte;
    }

    public void setStDataSolicitacaoAte(String stDataSolicitacaoAte) {
        this.stDataSolicitacaoAte = stDataSolicitacaoAte;
    }

    public String getRma() {
        return rma;
    }

    public void setRma(String rma) {
        this.rma = rma;
    }

}
