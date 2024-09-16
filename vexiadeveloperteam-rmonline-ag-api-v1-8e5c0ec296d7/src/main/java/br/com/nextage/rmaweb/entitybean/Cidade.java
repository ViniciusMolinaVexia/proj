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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "CIDADE")
public class Cidade implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "cidade";
    public static final String ALIAS_CLASSE_UNDERLINE = "cidade_";

    // Constantes com os nomes dos campos.
    public static final String CIDADE = "cidade";
    public static final String NOME = "nome";
    public static final String UF = "uf";

    @Id
    @Basic(optional = false)
    @Column(name = "CIDADE")
    private String cidade;

    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;

    @Column(name = "DDD")
    private String ddd;

    @Column(name = "OFICIAL")
    private Integer oficial;

    @Column(name = "CEP")
    private String cep;

    @JoinColumn(name = "UF", referencedColumnName = "UF")
    @ManyToOne
    private Uf uf;

    public Cidade() {
    }

    public Cidade(String cidade) {
        this.cidade = cidade;
    }

    public Cidade(String cidade, String nome) {
        this.cidade = cidade;
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public Integer getOficial() {
        return oficial;
    }

    public void setOficial(Integer oficial) {
        this.oficial = oficial;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cidade != null ? cidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cidade)) {
            return false;
        }
        Cidade other = (Cidade) object;
        return !((this.cidade == null && other.cidade != null) || (this.cidade != null && !this.cidade.equals(other.cidade)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.Cidade[cidade=" + cidade + "]";
    }

}
