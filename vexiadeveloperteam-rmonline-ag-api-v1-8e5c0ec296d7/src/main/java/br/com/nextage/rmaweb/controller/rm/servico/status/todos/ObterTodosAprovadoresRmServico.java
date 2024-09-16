package br.com.nextage.rmaweb.controller.rm.servico.status.todos;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class ObterTodosAprovadoresRmServico {
    private Integer idRm;
    private Integer idRmServico;
    private String prioridade;
    private List<AprovadorRmServico> aprovadores = new ArrayList<>();

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

    public List<AprovadorRmServico> getAprovadores() {
        return aprovadores;
    }

    public void setAprovadores(List<AprovadorRmServico> aprovadores) {
        this.aprovadores = aprovadores;
    }

    public void adicionaAprovador(AprovadorRmServico aprovador) {
        if (aprovador != null && StringUtils.isNotBlank(aprovador.getNome())) {
            this.aprovadores.add(aprovador);
        }
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
}
