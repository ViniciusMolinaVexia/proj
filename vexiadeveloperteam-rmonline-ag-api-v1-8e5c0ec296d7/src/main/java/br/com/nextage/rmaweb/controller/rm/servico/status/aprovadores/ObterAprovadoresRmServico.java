package br.com.nextage.rmaweb.controller.rm.servico.status.aprovadores;

import java.util.Date;

public class ObterAprovadoresRmServico {
    private Integer idRm;
    private Integer idRmServico;
    private String nomeUsuario;
    private Integer idStatus;
    private String codigoStatus;
    private String nomeStatus;
    private Date dataStatus;

    public Integer getIdRm() {
        return idRm;
    }

    public void setIdRm(Integer idRm) {
        this.idRm = idRm;
    }

    public Integer getIdRmServico() {
        return idRmServico;
    }

    public void setIdRmServico(Integer idRmServico) {
        this.idRmServico = idRmServico;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public String getCodigoStatus() {
        return codigoStatus;
    }

    public void setCodigoStatus(String codigoStatus) {
        this.codigoStatus = codigoStatus;
    }

    public String getNomeStatus() {
        return nomeStatus;
    }

    public void setNomeStatus(String nomeStatus) {
        this.nomeStatus = nomeStatus;
    }

    public Date getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Date dataStatus) {
        this.dataStatus = dataStatus;
    }
}
