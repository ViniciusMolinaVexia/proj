package br.com.nextage.rmaweb.vo.integracao;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Lucas Heitor
 */
public class RmIntegracaoVo {

    private String tipoRequisicao;
    private String primeiroAprovador;
    private String usuarioCadastrante;
    private String requisitante;
    private Date dataAplicacao;
    private List<String> listaDeposito;
    private String localEntrega;
    private String observacao;
    private String centro;
    private List<RmMaterialIntegracaoVo> listaMaterial;

    public String getTipoRequisicao() {
        return tipoRequisicao;
    }

    public void setTipoRequisicao(String tipoRequisicao) {
        this.tipoRequisicao = tipoRequisicao;
    }

    public String getPrimeiroAprovador() {
        return primeiroAprovador;
    }

    public void setPrimeiroAprovador(String primeiroAprovador) {
        this.primeiroAprovador = primeiroAprovador;
    }

    public String getUsuarioCadastrante() {
        return usuarioCadastrante;
    }

    public void setUsuarioCadastrante(String usuarioCadastrante) {
        this.usuarioCadastrante = usuarioCadastrante;
    }

    public List<String> getListaDeposito() {
        return listaDeposito;
    }

    public void setListaDeposito(List<String> listaDeposito) {
        this.listaDeposito = listaDeposito;
    }

    public List<RmMaterialIntegracaoVo> getListaMaterial() {
        return listaMaterial;
    }

    public void setListaMaterial(List<RmMaterialIntegracaoVo> listaMaterial) {
        this.listaMaterial = listaMaterial;
    }

    public String getRequisitante() {
        return requisitante;
    }

    public void setRequisitante(String requisitante) {
        this.requisitante = requisitante;
    }

    public Date getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(Date dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public String getLocalEntrega() {
        return localEntrega;
    }

    public void setLocalEntrega(String localEntrega) {
        this.localEntrega = localEntrega;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }
}
