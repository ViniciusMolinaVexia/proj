package br.com.nextage.rmaweb.controller.rm.servico.status.todos;

public class AprovadorRmServico {
  private String nome;
  private String tipoAprovador;
  private String dataAprovacao;

  public AprovadorRmServico() {
  }

  public AprovadorRmServico(String nome, String tipoAprovador, String dataAprovacao) {
    this.nome = nome;
    this.tipoAprovador = tipoAprovador;
    this.dataAprovacao = dataAprovacao;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getTipoAprovador() {
    return tipoAprovador;
  }

  public void setTipoAprovador(String tipoAprovador) {
    this.tipoAprovador = tipoAprovador;
  }

  public String getDataAprovacao() {
    return dataAprovacao;
  }

  public void setDataAprovacao(String dataAprovacao) {
    this.dataAprovacao = dataAprovacao;
  }
}
