package br.com.nextage.rmaweb.controller.rm.servico.status.aprovadores;

import java.io.Serializable;

public class ObterAprovadoresRmServicoRequest implements Serializable {
    private Integer idRm;
    private Integer idRmServico;

    public ObterAprovadoresRmServicoRequest() {
    }

    public ObterAprovadoresRmServicoRequest(Integer idRm, Integer idRmServico) {
        this.idRm = idRm;
        this.idRmServico = idRmServico;
    }

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
}
