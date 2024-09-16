package br.com.nextage.rmaweb.controller.painelRequisicaoMaterial;

public class ReprovarReqMaterialRequest {
  private Integer idRm;
  private Integer idRmMaterial;
  private String justificativaReprovacao;

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

  public String getJustificativaReprovacao() {
    return justificativaReprovacao;
  }

  public void setJustificativaReprovacao(String justificativaReprovacao) {
    this.justificativaReprovacao = justificativaReprovacao;
  }
}
