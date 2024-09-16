package br.com.nextage.rmaweb.controller.painelRequisicaoMaterial;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class EnviarReqMaterialSapRequest implements Serializable {

	private static final long serialVersionUID = 5615176740852609406L;
	/**
	 * ITEM
	 */
	@NotNull
	private String item;
	/**
	 * DOC_TYPE
	 */
	private String codigoPrioridadeSap;
	/**
	 * COD_RMA
	 */
	private String codigoRma;
	/**
	 * MATNR
	 */
	private String codigoMaterialSap;
	/**
	 * AFNAM COD
	 */
	private String codigoSolicitante;
	/**
	 * AFNAM
	 */
	private String nomeSolicitante;
	/**
	 * WERKS
	 */
	private String centroSap;
	/**
	 * MENGE
	 */
	private BigDecimal quantidade;
	/**
	 * MEINS
	 */
	private String unidadeMedida;
	/**
	 * VORNR
	 */
	private String elementoEp;
	/**
	 * LGORT
	 */
	private String depositoSap;
	/**
	 * EEINF
	 */
	private String dataRemessa;
	/**
	 * APRO_RMA
	 */
	private String usuarioCadastro;
	/**
	 * EKGRP
	 */
	private String grupoCompradores;
	/**
	 * BATAD
	 */
	private String dataSolicitacao;
	/**
	 * WGBEZ
	 */
	private String grupoMercadoria;
	/**
	 * PREIS
	 */
	private BigDecimal valorOrcado;
	/**
	 * EBELN
	 */
	private String codigoContratoSap;
	/**
	 * ZZKOKRS
	 */
	private String textoItem;
	/**
	 * TEXTO_ITEM
	 */
	private String areaSap;

	private String observacaoRm;

	private Integer diasPrevEntrega;

	private String dataAprovacao;

	private String coletorCustos;

	private String diagramaRede;
	
	private String rmId;
	
	private String anexo;

	public String getDiagramaRede() {
		return diagramaRede;
	}

	public void setDiagramaRede(String diagramaRede) {
		this.diagramaRede = diagramaRede;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getCodigoRma() {
		return codigoRma;
	}

	public void setCodigoRma(String codigoRma) {
		this.codigoRma = codigoRma;
	}

	public String getCodigoMaterialSap() {
		return codigoMaterialSap;
	}

	public void setCodigoMaterialSap(String codigoMaterialSap) {
		this.codigoMaterialSap = codigoMaterialSap;
	}

	public String getCodigoSolicitante() {
		return codigoSolicitante;
	}

	public void setCodigoSolicitante(String codigoSolicitante) {
		this.codigoSolicitante = codigoSolicitante;
	}

	public String getCentroSap() {
		return centroSap;
	}

	public void setCentroSap(String centroSap) {
		this.centroSap = centroSap;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public String getElementoEp() {
		return elementoEp;
	}

	public void setElementoEp(String elementoEp) {
		this.elementoEp = elementoEp;
	}

	public String getDepositoSap() {
		return depositoSap;
	}

	public void setDepositoSap(String depositoSap) {
		this.depositoSap = depositoSap;
	}

	public String getDataRemessa() {
		return dataRemessa;
	}

	public void setDataRemessa(String dataRemessa) {
		this.dataRemessa = dataRemessa;
	}

	public String getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(String usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public String getGrupoCompradores() {
		return grupoCompradores;
	}

	public void setGrupoCompradores(String grupoCompradores) {
		this.grupoCompradores = grupoCompradores;
	}

	public String getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(String dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public String getGrupoMercadoria() {
		return grupoMercadoria;
	}

	public void setGrupoMercadoria(String grupoMercadoria) {
		this.grupoMercadoria = grupoMercadoria;
	}

	public BigDecimal getValorOrcado() {
		return valorOrcado;
	}

	public void setValorOrcado(BigDecimal valorOrcado) {
		this.valorOrcado = valorOrcado;
	}

	public String getCodigoContratoSap() {
		return codigoContratoSap;
	}

	public void setCodigoContratoSap(String codigoContratoSap) {
		this.codigoContratoSap = codigoContratoSap;
	}
	
	public String getTextoItem() {
		return textoItem;
	}

	public void setTextoItem(String textoItem) {
		this.textoItem = textoItem;
	}

	public String getAreaSap() {
		return areaSap;
	}

	public void setAreaSap(String areaSap) {
		this.areaSap = areaSap;
	}

	public String getCodigoPrioridadeSap() {
		return codigoPrioridadeSap;
	}

	public void setCodigoPrioridadeSap(String codigoPrioridadeSap) {
		this.codigoPrioridadeSap = codigoPrioridadeSap;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public String getObservacaoRm() {
		return observacaoRm;
	}

	public void setObservacaoRm(String observacaoRm) {
		this.observacaoRm = observacaoRm;
	}

	public Integer getDiasPrevEntrega() {
		return diasPrevEntrega;
	}

	public void setDiasPrevEntrega(Integer diasPrevEntrega) {
		this.diasPrevEntrega = diasPrevEntrega;
	}

	public String getDataAprovacao() {
		return dataAprovacao;
	}

	public void setDataAprovacao(String dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}

	public String getColetorCustos() {
		return coletorCustos;
	}

	public void setColetorCustos(String coletorCustos) {
		this.coletorCustos = coletorCustos;
	}
	
	public String getRmId() {
		return rmId;
	}

	public void setRmId(String rmId) {
		this.rmId = rmId;
	}

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}
}
