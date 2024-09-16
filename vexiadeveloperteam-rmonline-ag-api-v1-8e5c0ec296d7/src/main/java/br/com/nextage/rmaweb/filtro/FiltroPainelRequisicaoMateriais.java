package br.com.nextage.rmaweb.filtro;

import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.Prioridade;
import br.com.nextage.rmaweb.entitybean.TipoRequisicao;
import br.com.nextage.util.filter.FiltroGeral;

public class FiltroPainelRequisicaoMateriais extends FiltroGeral {

  private String centro;
  private String numero;
  private String material;
  private String qtdeSolicitada;
  private Pessoa requisitante;
  private Pessoa usuarioCadastrante;
  private String stDataEmissaoDe, stDataEmissaoAte;
  private String stDataNecessidadeDe, stDataNecessidadeAte;
  private String coletorCusto;
  private TipoRequisicao tipoRequisicao;
  private Prioridade prioridade;
  private String status;

  public String getCentro() {
    return centro;
  }

  public void setCentro(String centro) {
    this.centro = centro;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getMaterial() {
    return material;
  }

  public void setMaterial(String material) {
    this.material = material;
  }

  public String getQtdeSolicitada() {
    return qtdeSolicitada;
  }

  public void setQtdeSolicitada(String qtdeSolicitada) {
    this.qtdeSolicitada = qtdeSolicitada;
  }

  public Pessoa getRequisitante() {
    return requisitante;
  }

  public void setRequisitante(Pessoa requisitante) {
    this.requisitante = requisitante;
  }

  public Pessoa getUsuarioCadastrante() {
    return usuarioCadastrante;
  }

  public void setUsuarioCadastrante(Pessoa usuarioCadastrante) {
    this.usuarioCadastrante = usuarioCadastrante;
  }

  public String getStDataEmissaoDe() {
    return stDataEmissaoDe;
  }

  public void setStDataEmissaoDe(String stDataEmissaoDe) {
    this.stDataEmissaoDe = stDataEmissaoDe;
  }

  public String getStDataEmissaoAte() {
    return stDataEmissaoAte;
  }

  public void setStDataEmissaoAte(String stDataEmissaoAte) {
    this.stDataEmissaoAte = stDataEmissaoAte;
  }

  public String getStDataNecessidadeDe() {
    return stDataNecessidadeDe;
  }

  public void setStDataNecessidadeDe(String stDataNecessidadeDe) {
    this.stDataNecessidadeDe = stDataNecessidadeDe;
  }

  public String getStDataNecessidadeAte() {
    return stDataNecessidadeAte;
  }

  public void setStDataNecessidadeAte(String stDataNecessidadeAte) {
    this.stDataNecessidadeAte = stDataNecessidadeAte;
  }

  public String getColetorCusto() {
    return coletorCusto;
  }

  public void setColetorCusto(String coletorCusto) {
    this.coletorCusto = coletorCusto;
  }

  public TipoRequisicao getTipoRequisicao() {
    return tipoRequisicao;
  }

  public void setTipoRequisicao(TipoRequisicao tipoRequisicao) {
    this.tipoRequisicao = tipoRequisicao;
  }

  public Prioridade getPrioridade() {
    return prioridade;
  }

  public void setPrioridade(Prioridade prioridade) {
    this.prioridade = prioridade;
  }

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}
}
