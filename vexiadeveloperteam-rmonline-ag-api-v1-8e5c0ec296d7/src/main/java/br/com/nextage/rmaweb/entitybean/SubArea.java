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

@Entity
@Table(name = "TB_SUB_AREA")
public class SubArea implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "subArea";
    public static final String ALIAS_CLASSE_UNDERLINE = "subArea_";

    public static final String SUB_AREA_ID = "subAreaId";
    public static final String CODIGO = "codigo";
    public static final String DESCRICAO = "descricao";

    @Id
    @Column(name = "SUB_AREA_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_SUB_AREA_ID", allocationSize = 1)
    private Integer subAreaId;

    @Column(name = "CODIGO")
    private String codigo;

    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    private String descricao;

    public SubArea() {
    }

    public SubArea(Integer subAreaId) {
        this.subAreaId = subAreaId;
    }

    public SubArea(Integer subAreaId, String descricao) {
        this.subAreaId = subAreaId;
        this.descricao = descricao;
    }

    public Integer getSubAreaId() {
        return subAreaId;
    }

    public void setSubAreaId(Integer subAreaId) {
        this.subAreaId = subAreaId;
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
        hash += (subAreaId != null ? subAreaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubArea)) {
            return false;
        }
        SubArea other = (SubArea) object;
        return !((this.subAreaId == null && other.subAreaId != null) || (this.subAreaId != null && !this.subAreaId.equals(other.subAreaId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.SubArea[subAreaId=" + subAreaId + "]";
    }
}
