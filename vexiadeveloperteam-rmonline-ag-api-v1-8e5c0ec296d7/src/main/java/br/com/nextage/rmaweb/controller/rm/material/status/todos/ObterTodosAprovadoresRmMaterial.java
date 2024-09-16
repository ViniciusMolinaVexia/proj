package br.com.nextage.rmaweb.controller.rm.material.status.todos;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class ObterTodosAprovadoresRmMaterial {
    private Integer idRm;
    private Integer idRmMaterial;
    private String prioridade;
    private List<AprovadorRmMaterial> aprovadores = new ArrayList<>();

    public Integer getIdRm() {
        return idRm;
    }

    public void setIdRm(Integer idRm) {
        this.idRm = idRm;
    }

    public Integer getIdRmMaterial() {
        return idRmMaterial;
    }

    public void setIdRmMaterial(Integer idRmMaterial) {
        this.idRmMaterial = idRmMaterial;
    }

    public List<AprovadorRmMaterial> getAprovadores() {
        return aprovadores;
    }

    public void setAprovadores(List<AprovadorRmMaterial> aprovadores) {
        this.aprovadores = aprovadores;
    }

    public void adicionaAprovador(AprovadorRmMaterial aprovador) {
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
