package br.com.nextage.rmaweb.controller.rm.servico.status.todos;

public class ObterTodosAprovadoresRmServicoResponse {
    private Integer idRm;
    private Integer idRmServico;
    private String prioridade;
    private String dataAprovCoordenador;
    private String nomeAprovCoordenador;
    private String dataAprovGerenteArea;
    private String nomeAprovGerenteArea;
    private String dataAprovGerenteCusto;
    private String nomeAprovGerenteCusto;
    private String dataAprovMaquinaParada;
    private String nomeAprovMaquinaParada;

    public ObterTodosAprovadoresRmServicoResponse() {
    }

    public Integer getIdRm() {
        return idRm;
    }

    public void setIdRm(Integer idRm) {
        this.idRm = idRm;
    }

    public Integer getIdRmServico() {
        return idRmServico;
    }

    public void setIdRmServico(Integer idRmServico) {
        this.idRmServico = idRmServico;
    }

    public String getNomeAprovCoordenador() {
        return nomeAprovCoordenador;
    }

    public void setNomeAprovCoordenador(String nomeAprovCoordenador) {
        this.nomeAprovCoordenador = nomeAprovCoordenador;
    }

    public String getNomeAprovGerenteArea() {
        return nomeAprovGerenteArea;
    }

    public void setNomeAprovGerenteArea(String nomeAprovGerenteArea) {
        this.nomeAprovGerenteArea = nomeAprovGerenteArea;
    }

    public String getNomeAprovGerenteCusto() {
        return nomeAprovGerenteCusto;
    }

    public void setNomeAprovGerenteCusto(String nomeAprovGerenteCusto) {
        this.nomeAprovGerenteCusto = nomeAprovGerenteCusto;
    }

    public String getNomeAprovMaquinaParada() {
        return nomeAprovMaquinaParada;
    }

    public void setNomeAprovMaquinaParada(String nomeAprovMaquinaParada) {
        this.nomeAprovMaquinaParada = nomeAprovMaquinaParada;
    }

    public String getDataAprovCoordenador() {
        return dataAprovCoordenador;
    }

    public void setDataAprovCoordenador(String dataAprovCoordenador) {
        this.dataAprovCoordenador = dataAprovCoordenador;
    }

    public String getDataAprovGerenteArea() {
        return dataAprovGerenteArea;
    }

    public void setDataAprovGerenteArea(String dataAprovGerenteArea) {
        this.dataAprovGerenteArea = dataAprovGerenteArea;
    }

    public String getDataAprovGerenteCusto() {
        return dataAprovGerenteCusto;
    }

    public void setDataAprovGerenteCusto(String dataAprovGerenteCusto) {
        this.dataAprovGerenteCusto = dataAprovGerenteCusto;
    }

    public String getDataAprovMaquinaParada() {
        return dataAprovMaquinaParada;
    }

    public void setDataAprovMaquinaParada(String dataAprovMaquinaParada) {
        this.dataAprovMaquinaParada = dataAprovMaquinaParada;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
}
