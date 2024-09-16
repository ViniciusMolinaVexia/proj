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
 * @author Ferri
 */
@Entity
@Table(name = "TB_TIPO_MATERIAL")
public class TipoMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "tipoMaterial";
    public static final String ALIAS_CLASSE_UNDERLINE = "tipoMaterial_";

    // Constantes com os nomes dos campos.
    public static final String TIPO_MATERIAL_ID = "tipoMaterialId";
    public static final String DESCRICAO = "descricao";
    public static final String CODIGO = "codigo";

    @Id
    @Column(name = "TIPO_MATERIAL_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_TIPO_MATERIAL_ID", allocationSize = 1)
    private Integer tipoMaterialId;

    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "CODIGO")
    private String codigo;

    public TipoMaterial() {
    }

    public TipoMaterial(Integer tipoMaterialId) {
        this.tipoMaterialId = tipoMaterialId;
    }

    public TipoMaterial(Integer tipoMaterialId, String tipoMaterial) {
        this.tipoMaterialId = tipoMaterialId;
        this.descricao = tipoMaterial;
    }

    public Integer getTipoMaterialId() {
        return tipoMaterialId;
    }

    public void setTipoMaterialId(Integer tipoMaterialId) {
        this.tipoMaterialId = tipoMaterialId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoMaterialId != null ? tipoMaterialId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMaterial)) {
            return false;
        }
        TipoMaterial other = (TipoMaterial) object;
        return !((this.tipoMaterialId == null && other.tipoMaterialId != null) || (this.tipoMaterialId != null && !this.tipoMaterialId.equals(other.tipoMaterialId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.TipoMaterial[tipoMaterialId=" + tipoMaterialId + "]";
    }
}
