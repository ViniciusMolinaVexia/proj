package br.com.nextage.rmaweb.enumerator;

public enum PrioridadeEnum {
  MAQ_PARADA("MAQ_PARADA", "ZR03"),
  URGENTE("URGENTE", "ZR02"),
  NORMAL("NORMAL", "ZR01");

  private String codigo;
  private String codigSap;

  PrioridadeEnum(String codigo, String codigSap) {
    this.codigo = codigo;
    this.codigSap = codigSap;
  }

  public String getCodigo() {
    return codigo;
  }

  public String getCodigSap() {
    return codigSap;
  }
}
