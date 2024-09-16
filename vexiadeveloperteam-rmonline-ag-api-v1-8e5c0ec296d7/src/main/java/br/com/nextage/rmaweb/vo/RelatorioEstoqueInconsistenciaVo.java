package br.com.nextage.rmaweb.vo;

/**
 * Created by Nextage on 06/05/2016.
 */
public class RelatorioEstoqueInconsistenciaVo {

    // Constantes com os nomes dos campos.
    public static final String CODIGO_MATERIAL = "codMaterial";
    public static final String NOME_MATERIAL = "nomeMaterial";
    public static final String CODIGO_DEPOSITO = "codDeposito";
    public static final String NOME_DEPOSITO = "nomeDeposito";
    public static final String QTDE_RMA = "qtdeRma";
    public static final String QTDE_SAP = "qtdeSap";
    public static final String QTDE_CP = "qtdeCp";
    public static final String UNIDADE_MEDIDA = "unidadeMedida";
    public static final String QTDE_CP_PATRIMONIADO = "qtdeCpPatrimoniado";
    public static final String QTDE_CP_NAO_PATRIMONIADO = "qtdeCpNaoPatrimoniado";

    private String codMaterial;
    private String nomeMaterial;
    private String codDeposito;
    private String nomeDeposito;
    private double qtdeRma;
    private double qtdeSap;
    private double qtdeCp;
    private Boolean isInconsistente;
    private String unidadeMedida;
    private Boolean inconsistenteRmaCp;
    private Boolean inconsistenteRmaSap;
    private Boolean inconsistenteCpSap;
    private double qtdeCpPatrimoniado;
    private double qtdeCpNaoPatrimoniado;


    public String getCodMaterial() {
        return codMaterial;
    }

    public void setCodMaterial(String codMaterial) {
        this.codMaterial = codMaterial;
    }

    public String getNomeMaterial() {
        return nomeMaterial;
    }

    public void setNomeMaterial(String nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
    }

    public double getQtdeRma() {
        return qtdeRma;
    }

    public void setQtdeRma(double qtdeRma) {
        this.qtdeRma = qtdeRma;
    }

    public double getQtdeSap() {
        return qtdeSap;
    }

    public void setQtdeSap(double qtdeSap) {
        this.qtdeSap = qtdeSap;
    }

    public double getQtdeCp() {
        return qtdeCp;
    }

    public void setQtdeCp(double qtdeCp) {
        this.qtdeCp = qtdeCp;
    }

    public String getCodDeposito() {
        return codDeposito;
    }

    public void setCodDeposito(String codDeposito) {
        this.codDeposito = codDeposito;
    }

    public String getNomeDeposito() {
        return nomeDeposito;
    }

    public void setNomeDeposito(String nomeDeposito) {
        this.nomeDeposito = nomeDeposito;
    }

    public Boolean getInconsistente() {
        return isInconsistente;
    }

    public void setInconsistente(Boolean inconsistente) {
        isInconsistente = inconsistente;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Boolean getInconsistenteRmaCp() {
        return inconsistenteRmaCp;
    }

    public void setInconsistenteRmaCp(Boolean inconsistenteRmaCp) {
        this.inconsistenteRmaCp = inconsistenteRmaCp;
    }

    public Boolean getInconsistenteRmaSap() {
        return inconsistenteRmaSap;
    }

    public void setInconsistenteRmaSap(Boolean inconsistenteRmaSap) {
        this.inconsistenteRmaSap = inconsistenteRmaSap;
    }

    public Boolean getInconsistenteCpSap() {
        return inconsistenteCpSap;
    }

    public void setInconsistenteCpSap(Boolean inconsistenteCpSap) {
        this.inconsistenteCpSap = inconsistenteCpSap;
    }

    public double getQtdeCpNaoPatrimoniado() {
        return qtdeCpNaoPatrimoniado;
    }

    public void setQtdeCpNaoPatrimoniado(double qtdeCpNaoPatrimoniado) {
        this.qtdeCpNaoPatrimoniado = qtdeCpNaoPatrimoniado;
    }

    public double getQtdeCpPatrimoniado() {
        return qtdeCpPatrimoniado;
    }

    public void setQtdeCpPatrimoniado(double qtdeCpPatrimoniado) {
        this.qtdeCpPatrimoniado = qtdeCpPatrimoniado;
    }
}
