/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.entitybean;

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
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "TB_REQ_MAT_COMPRA")
public class RequisicaoCompra implements Serializable {

    public static final String ALIAS_CLASSE = "requisicaoCompra";
    private static final long serialVersionUID = -5900933398960680541L;

    public static final String ID = "requisicaoId";
    public static final String ITEM_RM_SAP = "itemRmSAP";
    public static final String NUMERO_RM_SAP = "numeroRmSAP";
    public static final String DOCUMENTO_COMPRA = "documentoCompra";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_REQ_MAT_COMPRA", allocationSize = 1)
    @Column(name = "ID_MAT_COMPRA")
    private Integer requisicaoId;

    @JoinColumn(name = "RM_ID", referencedColumnName = "RM_ID")
    @ManyToOne
    private Rm rm;

    @JoinColumn(name = "MATERIAL_ID", referencedColumnName = "MATERIAL_ID")
    @ManyToOne
    private Material material;

    @JoinColumn(name = "RM_MATERIAL_ID", referencedColumnName = "RM_MATERIAL_ID")
    @ManyToOne
    private RmMaterial rmMaterial;

    @Column(name = "DOC_COMPRA")
    private String documentoCompra;

    @Column(name = "DATA_CADASTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @Column(name = "QUANTIDADE", precision = 3)
    private BigDecimal quantidade;

    @Column(name = "ITEM_RM_SAP")
    private String itemRmSAP;

    @Column(name = "NUMERO_RM_SAP")
    private String numeroRmSAP;

    public Integer getRequisicaoId() {
        return requisicaoId;
    }

    public void setRequisicaoId(Integer requisicaoId) {
        this.requisicaoId = requisicaoId;
    }

    public Rm getRm() {
        return rm;
    }

    public void setRm(Rm rm) {
        this.rm = rm;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public RmMaterial getRmMaterial() {
        return rmMaterial;
    }

    public void setRmMaterial(RmMaterial rmMaterial) {
        this.rmMaterial = rmMaterial;
    }

    public String getDocumentoCompra() {
        return documentoCompra;
    }

    public void setDocumentoCompra(String documentoCompra) {
        this.documentoCompra = documentoCompra;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getItemRmSAP() {
        return itemRmSAP;
    }

    public void setItemRmSAP(String itemRmSAP) {
        this.itemRmSAP = itemRmSAP;
    }

    public String getNumeroRmSAP() {
        return numeroRmSAP;
    }

    public void setNumeroRmSAP(String numeroRmSAP) {
        this.numeroRmSAP = numeroRmSAP;
    }

}
