package br.com.nextage.rmaweb.filtro;

import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.vo.RmAprovadorVo;
import br.com.nextage.util.filter.FiltroGeral;

/**
 *
 * @author nextage
 */
public class FiltroRmAprovacao extends FiltroGeral {

    private String idRmAprovacao;
    private String idRmAprovacaoCriptografada;

    private Boolean aprovadoresDaVez;
    private RmAprovador rmAprovador;
    private RmAprovadorVo rmAprovadorVo;

    private String numeroRma;
    private String tipoRm;
    private String status;
    private Pessoa solicitante;
    private Pessoa cadastrante;
    private Usuario usuario;
    private TipoRequisicao tipoRequisicao;
    private Centro centro;
    
    public void setCentro(Centro centro) {
    	this.centro=centro;
    }
    
    public Centro getCentro() {
    	return this.centro;
    }

    public String getIdRmAprovacao() {
        return idRmAprovacao;
    }

    public void setIdRmAprovacao(String idRmAprovacao) {
        this.idRmAprovacao = idRmAprovacao;
    }

    public String getIdRmAprovacaoCriptografada() {
        return idRmAprovacaoCriptografada;
    }

    public void setIdRmAprovacaoCriptografada(String idRmAprovacaoCriptografada) {
        this.idRmAprovacaoCriptografada = idRmAprovacaoCriptografada;
    }

    public Boolean getAprovadoresDaVez() {
        return aprovadoresDaVez;
    }

    public void setAprovadoresDaVez(Boolean aprovadoresDaVez) {
        this.aprovadoresDaVez = aprovadoresDaVez;
    }

    public RmAprovador getRmAprovador() {
        return rmAprovador;
    }

    public void setRmAprovador(RmAprovador rmAprovador) {
        this.rmAprovador = rmAprovador;
    }

    public RmAprovadorVo getRmAprovadorVo() {
        return rmAprovadorVo;
    }

    public void setRmAprovadorVo(RmAprovadorVo rmAprovadorVo) {
        this.rmAprovadorVo = rmAprovadorVo;
    }

    public String getNumeroRma() {
        return numeroRma;
    }

    public void setNumeroRma(String numeroRma) {
        this.numeroRma = numeroRma;
    }

    public Pessoa getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Pessoa solicitante) {
        this.solicitante = solicitante;
    }

    public Pessoa getCadastrante() {
        return cadastrante;
    }

    public void setCadastrante(Pessoa cadastrante) {
        this.cadastrante = cadastrante;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setTipoRm(String tipoRm) {
        this.tipoRm = tipoRm;
    }

    public String getTipoRm() {
        return tipoRm;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoRequisicao getTipoRequisicao() {
        return tipoRequisicao;
    }

    public void setTipoRequisicao(TipoRequisicao tipoRequisicao) {
        this.tipoRequisicao = tipoRequisicao;
    }
}
