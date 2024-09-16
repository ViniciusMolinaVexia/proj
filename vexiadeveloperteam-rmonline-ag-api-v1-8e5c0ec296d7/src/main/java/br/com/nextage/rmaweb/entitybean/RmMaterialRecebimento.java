/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.entitybean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "TB_RM_MATERIAL_RECEBIMENTO")
public class RmMaterialRecebimento implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "rmMaterialRecebimento";
    public static final String ALIAS_CLASSE_UNDERLINE = "rmMaterialRecebimento_";

    public static final String RM_MATERIAL_RECEBIMENTO_ID = "rmMaterialRecebimentoId";
    public static final String NUMERO_NOTA_FISCAL = "numeroNotaFiscal";
    public static final String DATA_EMISSAO_NF = "dataEmissaoNf";
    public static final String DATA_RECEBIMENTO_MATERIAL = "dataRecebimentoMaterial";
    public static final String QUANTIDADE = "quantidade";
    public static final String RM_MATERIAL = "rmMaterial";

    @Id
    @Basic(optional = false)
    @Column(name = "RM_MATERIAL_RECEBIMENTO_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_RM_MATERIAL_RECEBIMENTO", allocationSize = 1)
    private Integer rmMaterialRecebimentoId;

    @Column(name = "NUMERO_NOTA_FISCAL")
    private String numeroNotaFiscal;

    @Column(name = "DATA_EMISSAO_NF")
    @Temporal(TemporalType.DATE)
    private Date dataEmissaoNf;

    @Basic(optional = false)
    @Column(name = "DATA_RECEBIMENTO_MATERIAL")
    @Temporal(TemporalType.DATE)
    private Date dataRecebimentoMaterial;

    @Basic(optional = false)
    @Column(name = "QUANTIDADE")
    private Double quantidade;

    @JoinColumn(name = "RM_MATERIAL_ID", referencedColumnName = "RM_MATERIAL_ID")
    @ManyToOne(optional = false)
    private RmMaterial rmMaterial;
    
    @Transient
    private String stDataEmissaoNf;

    public RmMaterialRecebimento() {
    }

    public RmMaterialRecebimento(Integer rmMaterialRecebimentoId) {
        this.rmMaterialRecebimentoId = rmMaterialRecebimentoId;
    }

    public RmMaterialRecebimento(Integer rmMaterialRecebimentoId, String numeroNotaFiscal, Date dataEmissaoNf, Date dataRecebimentoMaterial, Double quantidade) {
        this.rmMaterialRecebimentoId = rmMaterialRecebimentoId;
        this.numeroNotaFiscal = numeroNotaFiscal;
        this.dataEmissaoNf = dataEmissaoNf;
        this.dataRecebimentoMaterial = dataRecebimentoMaterial;
        this.quantidade = quantidade;
    }

    public Integer getRmMaterialRecebimentoId() {
        return rmMaterialRecebimentoId;
    }

    public void setRmMaterialRecebimentoId(Integer rmMaterialRecebimentoId) {
        this.rmMaterialRecebimentoId = rmMaterialRecebimentoId;
    }

    public String getNumeroNotaFiscal() {
        return numeroNotaFiscal;
    }

    public void setNumeroNotaFiscal(String numeroNotaFiscal) {
        this.numeroNotaFiscal = numeroNotaFiscal;
    }

    public Date getDataEmissaoNf() {
        return dataEmissaoNf;
    }

    public void setDataEmissaoNf(Date dataEmissaoNf) {
        this.dataEmissaoNf = dataEmissaoNf;
    }

    public Date getDataRecebimentoMaterial() {
        return dataRecebimentoMaterial;
    }

    public void setDataRecebimentoMaterial(Date dataRecebimentoMaterial) {
        this.dataRecebimentoMaterial = dataRecebimentoMaterial;
    }

    public Double getQuantidade() {
        if (quantidade == null) {
            quantidade = 0.0;
        }
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public RmMaterial getRmMaterial() {
        return rmMaterial;
    }

    public void setRmMaterial(RmMaterial rmMaterial) {
        this.rmMaterial = rmMaterial;
    }

    public String getStDataEmissaoNf() {
        return stDataEmissaoNf;
    }

    public void setStDataEmissaoNf(String stDataEmissaoNf) {
        this.stDataEmissaoNf = stDataEmissaoNf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rmMaterialRecebimentoId != null ? rmMaterialRecebimentoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RmMaterialRecebimento)) {
            return false;
        }
        RmMaterialRecebimento other = (RmMaterialRecebimento) object;
        return !((this.rmMaterialRecebimentoId == null && other.rmMaterialRecebimentoId != null) || (this.rmMaterialRecebimentoId != null && !this.rmMaterialRecebimentoId.equals(other.rmMaterialRecebimentoId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.RmMaterialRecebimento[rmMaterialRecebimentoId=" + rmMaterialRecebimentoId + "]";
    }

}
