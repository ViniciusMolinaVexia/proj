package br.com.nextage.rmaweb.controller.painelRequisicaoMaterial;

import java.io.Serializable;
import java.math.BigDecimal;

public class EnviarReqReservaMaterialSapRequest implements Serializable {

  /**
   * COD_RMA
   */
  private String codigoRma;
  /**
   * ITEM
   */
  private String item;

	/**
	 * DATA RESERVA
	 */
	private String dataReserva;

  /**
   * WERKS
   */
  private String centroSap;
  /**
   * AFNAM
   */
  private String nomeSolicitante;
  /**
   * MATNR
   */
  private String codigoMaterialSap;
  /**
   * MENGE
   */
  private BigDecimal quantidade;
  /**
   * MEINS
   */
  private String unidadeMedida;
  /**
   * LGORT
   */
  private String depositoSap;
	/**
	 * EEIND
	 */
	private String dataNecessidade;
	/**
	 * Usuário Cadastro
	 */
	private String usuarioCadastro;

	private String coletorCustos;

    private String diagramaRede;
    
    private String texto_item;

    public String getDiagramaRede() {
        return diagramaRede;
    }

    public void setDiagramaRede(String diagramaRede) {
        this.diagramaRede = diagramaRede;
    }

    public String getColetorCustos() {
        return coletorCustos;
    }

    public void setColetorCustos(String coletorCustos) {
        this.coletorCustos = coletorCustos;
    }

    public String getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(String usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public String getCodigoRma() {
    return codigoRma;
  }

  public void setCodigoRma(String codigoRma) {
    this.codigoRma = codigoRma;
  }

  public String getItem() {
    return item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public String getDataReserva() {
    return dataReserva;
  }

  public void setDataReserva(String dataReserva) {
    this.dataReserva = dataReserva;
  }

  public String getCentroSap() {
    return centroSap;
  }

  public void setCentroSap(String centroSap) {
    this.centroSap = centroSap;
  }

  public String getNomeSolicitante() {
    return nomeSolicitante;
  }

  public void setNomeSolicitante(String nomeSolicitante) {
    this.nomeSolicitante = nomeSolicitante;
  }

  public String getCodigoMaterialSap() {
    return codigoMaterialSap;
  }

  public void setCodigoMaterialSap(String codigoMaterialSap) {
    this.codigoMaterialSap = codigoMaterialSap;
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

  public String getDepositoSap() {
    return depositoSap;
  }

  public void setDepositoSap(String depositoSap) {
    this.depositoSap = depositoSap;
  }

	public String getDataNecessidade() {
		return dataNecessidade;
	}

	public void setDataNecessidade(String dataNecessidade) {
		this.dataNecessidade = dataNecessidade;
	}
	
	 public String getTextoItem() {
        return texto_item;
    }

    public void setTextoItem(String texto_item) {
        this.texto_item = texto_item;
    }
}
