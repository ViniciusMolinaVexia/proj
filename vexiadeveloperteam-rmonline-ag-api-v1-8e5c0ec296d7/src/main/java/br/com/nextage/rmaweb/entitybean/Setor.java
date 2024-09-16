/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.entitybean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "TB_SETOR")
public class Setor implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "setor";
    public static final String ALIAS_CLASSE_UNDERLINE = "setor_";

    public static final String SETOR_ID = "setorId";
    public static final String CODIGO = "codigo";
    public static final String DESCRICAO = "descricao";

    @Id
    @Column(name = "SETOR_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_SETOR_ID", allocationSize = 1)
    private Integer setorId;

    @Column(name = "CODIGO")
    private String codigo;

    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    private String descricao;

    public Setor() {
    }

    public Setor(Integer setorId) {
        this.setorId = setorId;
    }

    public Setor(Integer setorId, String descricao) {
        this.setorId = setorId;
        this.descricao = descricao;
    }

    public Integer getSetorId() {
        return setorId;
    }

    public void setSetorId(Integer setorId) {
        this.setorId = setorId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
        hash += (setorId != null ? setorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Setor)) {
            return false;
        }
        Setor other = (Setor) object;
        return !((this.setorId == null && other.setorId != null) || (this.setorId != null && !this.setorId.equals(other.setorId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.Setor[setorId=" + setorId + "]";
    }
}
