/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.entitybean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author nextage
 */
@Entity
@Table(name = "TB_PESSOA_DIGITAL")
public class PessoaDigital implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "pessoaDigital";
    public static final String ID = "id";
    public static final String PESSOA_ID = "pessoa";
    public static final String DIGITAL = "digital";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_PESSOA_DIGITAL_ID", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @JoinColumn(name = "PESSOA_ID", referencedColumnName = "PESSOA_ID")
    @ManyToOne(optional = false)
    private Pessoa pessoa;

    @Column(name = "DIGITAL", columnDefinition = "TEXT")
    private String digital;

    public PessoaDigital() {

    }

    public PessoaDigital(Integer id, Pessoa pessoa, String digital) {
        this.id = id;
        this.pessoa = pessoa;
        this.digital = digital;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getDigital() {
        return digital;
    }

    public void setDigital(String digital) {
        this.digital = digital;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PessoaDigital)) {
            return false;
        }
        PessoaDigital other = (PessoaDigital) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.PessoaDigital[id=" + id + "]";
    }
}
