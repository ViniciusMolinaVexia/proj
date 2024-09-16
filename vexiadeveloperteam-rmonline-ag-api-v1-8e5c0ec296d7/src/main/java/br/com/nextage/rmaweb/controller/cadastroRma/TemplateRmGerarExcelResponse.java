package br.com.nextage.rmaweb.controller.cadastroRma;

import java.util.ArrayList;
import java.util.List;

public class TemplateRmGerarExcelResponse {
    private List<String> cabecalho;
    private List<LinhaRmGerarExcelResponse> linhas;

    public TemplateRmGerarExcelResponse(final List<String> cabecalho) {
        linhas = new ArrayList<>();
        this.cabecalho = cabecalho;
    }

    public List<String> getCabecalho() {
        return cabecalho;
    }

    public void setCabecalho(List<String> cabecalho) {
        this.cabecalho = cabecalho;
    }

    public List<LinhaRmGerarExcelResponse> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<LinhaRmGerarExcelResponse> linhas) {
        this.linhas = linhas;
    }
}
