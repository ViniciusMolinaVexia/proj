package br.com.nextage.rmaweb.controller.cadastroRma;

import java.util.ArrayList;
import java.util.List;

public class CabecalhoRmGerarExcelResponse {

    private List<String> titulos;

    public CabecalhoRmGerarExcelResponse() {
        titulos = new ArrayList<>();
    }

    public List<String> getTitulos() {
        return titulos;
    }

    public void setTitulos(List<String> titulos) {
        this.titulos = titulos;
    }
}
