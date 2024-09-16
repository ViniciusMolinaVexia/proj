package br.com.nextage.rmaweb.vo.integracao;

/**
 *
 * @author l.pordeus
 */
public class FornecedorVo {

    private String nome;
    private String codigo;
    private String cnpj;
    private String cidade;
    private String estado;
    private String status;
    private String ultimaDataAtualizacao;
    private String ultimaHoraAtualizacao;
    private String centro;

    public FornecedorVo() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUltimaDataAtualizacao() {
        return ultimaDataAtualizacao;
    }

    public void setUltimaDataAtualizacao(String ultimaDataAtualizacao) {
        this.ultimaDataAtualizacao = ultimaDataAtualizacao;
    }

    public String getUltimaHoraAtualizacao() {
        return ultimaHoraAtualizacao;
    }

    public void setUltimaHoraAtualizacao(String ultimaHoraAtualizacao) {
        this.ultimaHoraAtualizacao = ultimaHoraAtualizacao;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

}
