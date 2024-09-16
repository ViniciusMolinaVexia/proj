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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jerry
 */
@Entity
@Table(name = "TB_RM_MATERIAL_RETIRADA")
@NamedQueries({
    @NamedQuery(name = "RmMaterialRetirada.findAll", query = "SELECT r FROM RmMaterialRetirada r"),
    @NamedQuery(name = "RmMaterialRetirada.findByRmMaterialRetiradaId", query = "SELECT r FROM RmMaterialRetirada r WHERE r.rmMaterialRetiradaId = :rmMaterialRetiradaId"),
    @NamedQuery(name = "RmMaterialRetirada.findByDataRetirada", query = "SELECT r FROM RmMaterialRetirada r WHERE r.dataRetirada = :dataRetirada"),
    @NamedQuery(name = "RmMaterialRetirada.findByQuantidade", query = "SELECT r FROM RmMaterialRetirada r WHERE r.quantidade = :quantidade")})
public class RmMaterialRetirada implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "rmMaterialRetirada";
    public static final String ALIAS_CLASSE_UNDERLINE = "rmMaterialRetirada_";

    public static final String RM_MATERIAL_RETIRADA_ID = "rmMaterialRetiradaId";
    public static final String DATA_RETIRADA = "dataRetirada";
    public static final String QUANTIDADE = "quantidade";
    public static final String RM_MATERIAL = "rmMaterial";
    public static final String RETIRADO_POR = "retiradoPor";
    public static final String NUMERO_PROTOCOLO = "numeroProtocolo";
    public static final String AUTENTICACAO = "autenticacao";
    public static final String DATA_AUTENTICACAO = "dataAutenticacao";
    public static final String PRE_RETIRADA = "preRetirada";
    public static final String DEPOSITO = "deposito";

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_RM_MATERIAL_RETIRADA_ID", allocationSize = 1)
    @Column(name = "RM_MATERIAL_RETIRADA_ID")
    private Integer rmMaterialRetiradaId;

    @Basic(optional = false)
    @Column(name = "DATA_RETIRADA")
    @Temporal(TemporalType.DATE)
    private Date dataRetirada;

    @Basic(optional = false)
    @Column(name = "QUANTIDADE")
    private Double quantidade;

    @Column(name = "NUMERO_PROTOCOLO")
    private Integer numeroProtocolo;

    @JoinColumn(name = "RM_MATERIAL_ID", referencedColumnName = "RM_MATERIAL_ID")
    @ManyToOne(optional = false)
    private RmMaterial rmMaterial;

    @JoinColumn(name = "RETIRADO_POR", referencedColumnName = "PESSOA_ID")
    @ManyToOne(optional = false)
    private Pessoa retiradoPor;

    @Column(name = "AUTENTICACAO", columnDefinition = "TEXT")
    private String autenticacao;

    @Column(name = "DATA_AUTENTICACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAutenticacao;

    @Column(name = "PRE_RETIRADA")
    private Boolean preRetirada;

    @JoinColumn(name = "DEPOSITO_ID", referencedColumnName = "DEPOSITO_ID")
    @ManyToOne
    private Deposito deposito;
    
    public Integer getNumeroProtocolo() {
        return numeroProtocolo;
    }

    public void setNumeroProtocolo(Integer numeroProtocolo) {
        this.numeroProtocolo = numeroProtocolo;
    }

    public RmMaterialRetirada() {
    }

    public RmMaterialRetirada(Integer rmMaterialRetiradaId) {
        this.rmMaterialRetiradaId = rmMaterialRetiradaId;
    }

    public RmMaterialRetirada(Integer rmMaterialRetiradaId, Date dataRetirada, Double quantidade) {
        this.rmMaterialRetiradaId = rmMaterialRetiradaId;
        this.dataRetirada = dataRetirada;
        this.quantidade = quantidade;
    }

    public Integer getRmMaterialRetiradaId() {
        return rmMaterialRetiradaId;
    }

    public void setRmMaterialRetiradaId(Integer rmMaterialRetiradaId) {
        this.rmMaterialRetiradaId = rmMaterialRetiradaId;
    }

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
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

    public Pessoa getRetiradoPor() {
        return retiradoPor;
    }

    public void setRetiradoPor(Pessoa retiradoPor) {
        this.retiradoPor = retiradoPor;
    }

    public String getAutenticacao() {
        return autenticacao;
    }

    public void setAutenticacao(String autenticacao) {
        this.autenticacao = autenticacao;
    }

    public Date getDataAutenticacao() {
        return dataAutenticacao;
    }

    public void setDataAutenticacao(Date dataAutenticacao) {
        this.dataAutenticacao = dataAutenticacao;
    }

    public Boolean getPreRetirada() {
        return preRetirada;
    }

    public void setPreRetirada(Boolean preRetirada) {
        this.preRetirada = preRetirada;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rmMaterialRetiradaId != null ? rmMaterialRetiradaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RmMaterialRetirada)) {
            return false;
        }
        RmMaterialRetirada other = (RmMaterialRetirada) object;
        return !((this.rmMaterialRetiradaId == null && other.rmMaterialRetiradaId != null) || (this.rmMaterialRetiradaId != null && !this.rmMaterialRetiradaId.equals(other.rmMaterialRetiradaId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.RmMaterialRetirada[rmMaterialRetiradaId=" + rmMaterialRetiradaId + "]";
    }
}
