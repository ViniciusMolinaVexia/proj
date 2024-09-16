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
 * @author jerry
 */
@Entity
@Table(name = "TB_MATERIAL_DEPOSITO_ENTRADA")
@NamedQueries({
    @NamedQuery(name = "MaterialDepositoEntrada.findAll", query = "SELECT m FROM MaterialDepositoEntrada m"),
    @NamedQuery(name = "MaterialDepositoEntrada.findByMaterialDepositoEntradaId", query = "SELECT m FROM MaterialDepositoEntrada m WHERE m.materialDepositoEntradaId = :materialDepositoEntradaId"),
    @NamedQuery(name = "MaterialDepositoEntrada.findByQuantidade", query = "SELECT m FROM MaterialDepositoEntrada m WHERE m.quantidade = :quantidade"),
    @NamedQuery(name = "MaterialDepositoEntrada.findByDataEntrada", query = "SELECT m FROM MaterialDepositoEntrada m WHERE m.dataEntrada = :dataEntrada"),
    @NamedQuery(name = "MaterialDepositoEntrada.findByObservacao", query = "SELECT m FROM MaterialDepositoEntrada m WHERE m.observacao = :observacao")})
public class MaterialDepositoEntrada implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "materialDepositoEntrada";
    public static final String ALIAS_CLASSE_UNDERLINE = "materialDepositoEntrada_";

    // Constantes com os nomes dos campos.
    public static final String MATERIAL_DEPOSITO_ENTRADA_ID = "materialDepositoEntradaId";
    public static final String DATA_ENTRADA = "dataEntrada";
    public static final String OBSERVACAO = "observacao";
    public static final String QUANTIDADE = "quantidade";
    public static final String MATERIAL_DEPOSITO = "materialDeposito";
    public static final String RM = "rm";
    public static final String ORIGEM_MOVIMENTACAO = "origemMovimentacao";
    public static final String ORIGEM_SINC_REGISTRO = "origemSincRegistro";
    public static final String CD_USU_INC = "cdUsuInc";
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_MAT_DEPOS_ENTRADA_ID", allocationSize = 1)
    @Column(name = "MATERIAL_DEPOSITO_ENTRADA_ID")
    private Integer materialDepositoEntradaId;

    @Basic(optional = false)
    @Column(name = "QUANTIDADE")
    private double quantidade;

    @Basic(optional = false)
    @Column(name = "DATA_ENTRADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntrada;

    @Basic(optional = false)
    @Column(name = "OBSERVACAO")
    private String observacao;

    @JoinColumn(name = "MATERIAL_DEPOSITO_ID", referencedColumnName = "MATERIAL_DEPOSITO_ID")
    @ManyToOne(optional = false)
    private MaterialDeposito materialDeposito;

    @JoinColumn(name = "RM_ID", referencedColumnName = "RM_ID")
    @ManyToOne
    private Rm rm;

    @Column(name = "ORIGEM_MOVIMENTACAO")
    private String origemMovimentacao;
    @Column(name = "ORIGEM_SINC_REGISTRO")
    private String origemSincRegistro;

    @Column(name = "CD_USU_INC")
    private String cdUsuInc;


    public MaterialDepositoEntrada() {
    }

    public MaterialDepositoEntrada(Integer materialDepositoEntradaId) {
        this.materialDepositoEntradaId = materialDepositoEntradaId;
    }

    public MaterialDepositoEntrada(Integer materialDepositoEntradaId, double quantidade, Date dataEntrada, String observacao, MaterialDeposito materialDeposito, Rm rm) {
        this.materialDepositoEntradaId = materialDepositoEntradaId;
        this.quantidade = quantidade;
        this.dataEntrada = dataEntrada;
        this.observacao = observacao;
        this.materialDeposito = materialDeposito;
        this.rm = rm;
    }

    public MaterialDeposito getMaterialDeposito() {
        return materialDeposito;
    }

    public void setMaterialDeposito(MaterialDeposito materialDeposito) {
        this.materialDeposito = materialDeposito;
    }

    public Rm getRm() {
        return rm;
    }

    public void setRm(Rm rm) {
        this.rm = rm;
    }

    public Integer getMaterialDepositoEntradaId() {
        return materialDepositoEntradaId;
    }

    public void setMaterialDepositoEntradaId(Integer materialDepositoEntradaId) {
        this.materialDepositoEntradaId = materialDepositoEntradaId;
    }

    public String getCdUsuInc() {
        return cdUsuInc;
    }

    public void setCdUsuInc(String cdUsuInc) {
        this.cdUsuInc = cdUsuInc;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getOrigemSincRegistro() {
        return origemSincRegistro;
    }

    public void setOrigemSincRegistro(String origemSincRegistro) {
        this.origemSincRegistro = origemSincRegistro;
    }

    public String getOrigemMovimentacao() {
        return origemMovimentacao;
    }

    public void setOrigemMovimentacao(String origemMovimentacao) {
        this.origemMovimentacao = origemMovimentacao;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materialDepositoEntradaId != null ? materialDepositoEntradaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialDepositoEntrada)) {
            return false;
        }
        MaterialDepositoEntrada other = (MaterialDepositoEntrada) object;
        return !((this.materialDepositoEntradaId == null && other.materialDepositoEntradaId != null) || (this.materialDepositoEntradaId != null && !this.materialDepositoEntradaId.equals(other.materialDepositoEntradaId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.MaterialDepositoEntrada[materialDepositoEntradaId=" + materialDepositoEntradaId + "]";
    }

}
