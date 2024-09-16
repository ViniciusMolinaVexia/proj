/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.classes;

/**
 *
 * @author jerry
 */
public class GraficoFluxo {

    private String nome;

    /**
     * Média de dias.
     */
    private Integer qtdDias;

    /**
     * Campo Auxiliar para montar o gráfico.
     */
    private Integer auxBase;

    public GraficoFluxo(String nome) {
        this.nome = nome;
    }

    public GraficoFluxo() {
    }

    public Integer getAuxBase() {
        return auxBase;
    }

    public void setAuxBase(Integer auxBase) {
        this.auxBase = auxBase;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQtdDias() {
        return qtdDias;
    }

    public void setQtdDias(Integer qtdDias) {
        this.qtdDias = qtdDias;
    }
}
