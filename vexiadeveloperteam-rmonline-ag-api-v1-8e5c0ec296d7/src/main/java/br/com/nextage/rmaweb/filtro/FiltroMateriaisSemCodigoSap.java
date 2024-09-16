package br.com.nextage.rmaweb.filtro;

import br.com.nextage.util.filter.FiltroGeral;

/**
 *
 * @author nextage
 */
public class FiltroMateriaisSemCodigoSap extends FiltroGeral {

    private String status;
    private String codigo;
    private String nome;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
