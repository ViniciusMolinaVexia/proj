/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.entitybean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Lucas Heitor
 */
@Entity
@Table(name = "TB_MULTI_EMPRESA")
public class MultiEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "multiEmpresa";
    public static final String ALIAS_CLASSE_UNDERLINE = "multiEmpresa_";

    // Constantes com os nomes dos campos.
    public static final String ID = "id";
    public static final String DESCRICAO = "descricao";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_MULTI_EMPRESA_ID", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    private String descricao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        if (!(object instanceof MultiEmpresa)) {
            return false;
        }
        MultiEmpresa other = (MultiEmpresa) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.MultiEmpresa[id=" + id + "]";
    }
}
