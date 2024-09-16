/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.entitybean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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

@Entity
@Table(name = "TB_REQ_MAT_RECEBIDO")
public class RequisicaoRecebimento implements Serializable {

    private static final long serialVersionUID = -8424236938962492308L;

    public static final String ID = "requisicaoId";
    public static final String ITEM_RM_SAP = "itemSAP";
    public static final String NUMERO_RM_SAP = "numeroSAP";
    public static final String DOCUMENTO_RECEBIMENTO = "documentoRecebimento";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_REQ_MAT_RECEBIDO", allocationSize = 1)
    @Column(name = "ID_MAT_RECEBIDO")
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

    @Column(name = "DOC_RECEBIMENTO")
    private String documentoRecebimento;

    @Column(name = "DATA_CADASTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @Column(name = "QUANTIDADE", precision = 3)
    private BigDecimal quantidade;

    @Column(name = "ITEM_RM_SAP")
    private String itemSAP;

    @Column(name = "NUMERO_RM_SAP")
    private String numeroSAP;
    
    @Column(name = "DATA_RECEBIMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRecebimento;

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

    public String getDocumentoRecebimento() {
        return documentoRecebimento;
    }

    public void setDocumentoRecebimento(String documentoRecebimento) {
        this.documentoRecebimento = documentoRecebimento;
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

    public String getItemSAP() {
        return itemSAP;
    }

    public void setItemSAP(String itemSAP) {
        this.itemSAP = itemSAP;
    }

    public String getNumeroSAP() {
        return numeroSAP;
    }

    public void setNumeroSAP(String numeroSAP) {
        this.numeroSAP = numeroSAP;
    }

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}
    
    
}
