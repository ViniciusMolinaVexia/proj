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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Leco
 */
@Entity
@Table(name = "TB_MATERIAL_DEPOSITO")
@NamedQueries({
    @NamedQuery(name = "MaterialDeposito.findAll", query = "SELECT m FROM MaterialDeposito m"),
    @NamedQuery(name = "MaterialDeposito.findByMaterialDepositoId", query = "SELECT m FROM MaterialDeposito m WHERE m.materialDepositoId = :materialDepositoId"),
    @NamedQuery(name = "MaterialDeposito.findByQuantidade", query = "SELECT m FROM MaterialDeposito m WHERE m.quantidade = :quantidade")})
public class MaterialDeposito implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "materialDeposito";
    public static final String ALIAS_CLASSE_UNDERLINE = "materialDeposito_";

    // Constantes com os nomes dos campos.
    public static final String MATERIAL_DEPOSITO_ID = "materialDepositoId";
    public static final String MATERIAL = "material";
    public static final String DEPOSITO = "deposito";
    public static final String QUANTIDADE = "quantidade";
    public static final String LOCALIZACAO = "localizacao";

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_MATERIAL_DEPOSITO_ID", allocationSize = 1)
    @Column(name = "MATERIAL_DEPOSITO_ID")
    private Integer materialDepositoId;

    @Basic(optional = false)
    @Column(name = "QUANTIDADE")
    private Double quantidade;

    @JoinColumn(name = "MATERIAL_ID", referencedColumnName = "MATERIAL_ID")
    @ManyToOne(optional = false)
    private Material material;

    @JoinColumn(name = "DEPOSITO_ID", referencedColumnName = "DEPOSITO_ID")
    @ManyToOne(optional = false)
    private Deposito deposito;

    @Column(name = "LOCALIZACAO")
    private String localizacao;

    public MaterialDeposito() {
    }

    public MaterialDeposito(Integer materialDepositoId) {
        this.materialDepositoId = materialDepositoId;
    }

    public MaterialDeposito(Integer materialDepositoId, Double quantidade) {
        this.materialDepositoId = materialDepositoId;
        this.quantidade = quantidade;
    }

    public Integer getMaterialDepositoId() {
        return materialDepositoId;
    }

    public void setMaterialDepositoId(Integer materialDepositoId) {
        this.materialDepositoId = materialDepositoId;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materialDepositoId != null ? materialDepositoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialDeposito)) {
            return false;
        }
        MaterialDeposito other = (MaterialDeposito) object;
        return !((this.materialDepositoId == null && other.materialDepositoId != null) || (this.materialDepositoId != null && !this.materialDepositoId.equals(other.materialDepositoId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.MaterialDeposito[materialDepositoId=" + materialDepositoId + "]";
    }

}
