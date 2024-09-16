/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.classes;

/**
 *
 * @author jerry
 */
public class GraficoResumoCompradores {

    private String nome;
    //EM COMPRA
    private Integer qntdCotacao;
    //PENDENTE DE RECEBIMENTO
    private Integer qntdDataCompra;
    //CANCELADOS
    private Integer qntdDataCancelamento;
    //RECEBIDO PARCIALMENTE
    private Integer qntdRecParcial;
    //RECEBIDO TOTALMENTE
    private Integer qntdRecCompleto;
    //RETIRADO PARCIALMENTE
    private Integer qntdRetParcial;
    //RETIRADO TOTALMENTE
    private Integer qntdRetCompleto;

    public GraficoResumoCompradores(String nome) {
        this.nome = nome;
    }

    public GraficoResumoCompradores() {
    }

    public Integer getQntdRetCompleto() {
        return qntdRetCompleto;
    }

    public void setQntdRetCompleto(Integer qntdRetCompleto) {
        this.qntdRetCompleto = qntdRetCompleto;
    }

    public Integer getQntdRetParcial() {
        return qntdRetParcial;
    }

    public void setQntdRetParcial(Integer qntdRetParcial) {
        this.qntdRetParcial = qntdRetParcial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQntdCotacao() {
        return qntdCotacao;
    }

    public void setQntdCotacao(Integer qntdCotacao) {
        this.qntdCotacao = qntdCotacao;
    }

    public Integer getQntdDataCancelamento() {
        return qntdDataCancelamento;
    }

    public void setQntdDataCancelamento(Integer qntdDataCancelamento) {
        this.qntdDataCancelamento = qntdDataCancelamento;
    }

    public Integer getQntdDataCompra() {
        return qntdDataCompra;
    }

    public void setQntdDataCompra(Integer qntdDataCompra) {
        this.qntdDataCompra = qntdDataCompra;
    }

    public Integer getQntdRecCompleto() {
        return qntdRecCompleto;
    }

    public void setQntdRecCompleto(Integer qntdRecCompleto) {
        this.qntdRecCompleto = qntdRecCompleto;
    }

    public Integer getQntdRecParcial() {
        return qntdRecParcial;
    }

    public void setQntdRecParcial(Integer qntdRecParcial) {
        this.qntdRecParcial = qntdRecParcial;
    }

}
