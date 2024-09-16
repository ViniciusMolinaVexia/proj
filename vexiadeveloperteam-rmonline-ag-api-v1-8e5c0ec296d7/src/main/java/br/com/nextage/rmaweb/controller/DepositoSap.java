package br.com.nextage.rmaweb.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class DepositoSap implements Serializable, Comparable<DepositoSap> {

    /**
     * Identificador do deposito
     */
    private String id;
    private String codigo;

    private String depositoId;
    /**
     * Nome do deposito
     */
    private String nome;

    /**
     * Quantidade de materiais no deposito
     */
    private BigDecimal quantidade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepositoId() {
        return depositoId;
    }

    public void setDepositoId(String depositoId) {
        this.depositoId = depositoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DepositoSap that = (DepositoSap) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(DepositoSap depositoSap) {
        if (getCodigo() == null || depositoSap.getCodigo() == null) {
            return 0;
        }
        return getCodigo().compareTo(depositoSap.getCodigo());
    }
}
