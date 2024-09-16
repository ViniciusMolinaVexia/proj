/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.entitybean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "UF")
public class Uf implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "uf";
    public static final String ALIAS_CLASSE_UNDERLINE = "uf_";

    // Constantes com os nomes dos campos.
    public static final String UF = "uf";
    public static final String NOME = "nome";

    @Id
    @Basic(optional = false)
    @Column(name = "UF")
    private String uf;

    @Basic(optional = false)
    @Column(name = "NOME")

    private String nome;
    @Column(name = "PAISSIGLA")
    private String paissigla;

    @Column(name = "NACIONAL")
    private String nacional;

    @Column(name = "CODIGO")
    private Integer codigo;

    public Uf() {
    }

    public Uf(String uf) {
        this.uf = uf;
    }

    public Uf(String uf, String nome) {
        this.uf = uf;
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPaissigla() {
        return paissigla;
    }

    public void setPaissigla(String paissigla) {
        this.paissigla = paissigla;
    }

    public String getNacional() {
        return nacional;
    }

    public void setNacional(String nacional) {
        this.nacional = nacional;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uf != null ? uf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uf)) {
            return false;
        }
        Uf other = (Uf) object;
        return !((this.uf == null && other.uf != null) || (this.uf != null && !this.uf.equals(other.uf)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.Uf[uf=" + uf + "]";
    }

}
