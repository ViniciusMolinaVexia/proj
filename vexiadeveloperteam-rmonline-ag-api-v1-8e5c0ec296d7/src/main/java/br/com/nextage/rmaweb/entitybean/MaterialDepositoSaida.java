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
@Table(name = "TB_MATERIAL_DEPOSITO_SAIDA", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "MaterialDepositoSaida.findAll", query = "SELECT m FROM MaterialDepositoSaida m"),
    @NamedQuery(name = "MaterialDepositoSaida.findByMaterialDepositoSaidaId", query = "SELECT m FROM MaterialDepositoSaida m WHERE m.materialDepositoSaidaId = :materialDepositoSaidaId"),
    @NamedQuery(name = "MaterialDepositoSaida.findByQuantidade", query = "SELECT m FROM MaterialDepositoSaida m WHERE m.quantidade = :quantidade"),
    @NamedQuery(name = "MaterialDepositoSaida.findByDataSaida", query = "SELECT m FROM MaterialDepositoSaida m WHERE m.dataSaida = :dataSaida"),
    @NamedQuery(name = "MaterialDepositoSaida.findByObservacao", query = "SELECT m FROM MaterialDepositoSaida m WHERE m.observacao = :observacao")})
public class MaterialDepositoSaida implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "materialDepositoSaida";
    public static final String ALIAS_CLASSE_UNDERLINE = "materialDepositoSaida_";

    // Constantes com os nomes dos campos.
    public static final String MATERIAL_DEPOSITO_SAIDA_ID = "materialDepositoSaidaId";
    public static final String DATA_SAIDA = "dataSaida";
    public static final String OBSERVACAO = "observacao";
    public static final String QUANTIDADE = "quantidade";
    public static final String MATERIAL_DEPOSITO = "materialDeposito";
    public static final String RM = "rm";
    public static final String RETIRADO_POR = "retiradoPor";
    public static final String NUMERO_PROTOCOLO_SAIDA = "numeroProtocoloSaida";
    public static final String RESERVA = "reserva";
    public static final String MATERIAL_DEPOSITO_SAIDA_RESERVA_ID = "materialDepositoSaidaReserva";
    public static final String DATA_ENVIO_SAP = "dataEnvioSap";
    public static final String QUANTIDADE_ORIGINAL = "quantidadeOriginal";
    public static final String IS_RECEBIDO_CP = "isRecebidoCp";
    public static final String ORIGEM_MOVIMENTACAO = "origemMovimentacao";
    public static final String ORIGEM_SINC_REGISTRO = "origemSincRegistro";
    public static final String CD_USU_INC = "cdUsuInc";

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_MAT_DEP_SAIDA_ID", allocationSize = 1)
    @Column(name = "MATERIAL_DEPOSITO_SAIDA_ID")
    private Integer materialDepositoSaidaId;

    @Basic(optional = false)
    @Column(name = "QUANTIDADE")
    private double quantidade;
    
    @Column(name = "QUANTIDADE_ORIGINAL")
    private double quantidadeOriginal;

    @Basic(optional = false)
    @Column(name = "DATA_SAIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataSaida;

    @Basic(optional = false)
    @Column(name = "OBSERVACAO")
    private String observacao;

    @Column(name = "NUMERO_PROTOCOLO_SAIDA")
    private Integer numeroProtocoloSaida;

    @JoinColumn(name = "MATERIAL_DEPOSITO_ID", referencedColumnName = "MATERIAL_DEPOSITO_ID")
    @ManyToOne(optional = false)
    private MaterialDeposito materialDeposito;

    @JoinColumn(name = "RM_ID", referencedColumnName = "RM_ID")
    @ManyToOne
    private Rm rm;

    @JoinColumn(name = "PESSOA_ID", referencedColumnName = "PESSOA_ID")
    @ManyToOne
    private Pessoa retiradoPor;

    @Column(name = "RESERVA")
    private Boolean reserva;

    @JoinColumn(name = "MATERIAL_DEPOSITO_SAIDA_RESERVA_ID", referencedColumnName = "ID")
    @ManyToOne
    private MaterialDepositoSaidaReserva materialDepositoSaidaReserva;

    @Column(name = "DATA_ENVIO_SAP")
    @Temporal(TemporalType.DATE)
    private Date dataEnvioSap;

    @Column(name = "IS_RECEBIDO_CP")
    private Boolean isRecebidoCp;

    @Column(name = "ORIGEM_MOVIMENTACAO")
    private String origemMovimentacao;
    @Column(name = "ORIGEM_SINC_REGISTRO")
    private String origemSincRegistro;

    @Column(name = "CD_USU_INC")
    private String cdUsuInc;

    public MaterialDepositoSaida() {
    }

    public MaterialDepositoSaida(Integer materialDepositoSaidaId) {
        this.materialDepositoSaidaId = materialDepositoSaidaId;
    }

    public MaterialDepositoSaida(Integer materialDepositoSaidaId, double quantidade, Date dataSaida, String observacao) {
        this.materialDepositoSaidaId = materialDepositoSaidaId;
        this.quantidade = quantidade;
        this.dataSaida = dataSaida;
        this.observacao = observacao;
    }

    public Boolean getRecebidoCp() {
        return isRecebidoCp;
    }

    public void setRecebidoCp(Boolean recebidoCp) {
        isRecebidoCp = recebidoCp;
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

    public Pessoa getRetiradoPor() {
        return retiradoPor;
    }

    public void setRetiradoPor(Pessoa retiradoPor) {
        this.retiradoPor = retiradoPor;
    }

    public Integer getMaterialDepositoSaidaId() {
        return materialDepositoSaidaId;
    }

    public void setMaterialDepositoSaidaId(Integer materialDepositoSaidaId) {
        this.materialDepositoSaidaId = materialDepositoSaidaId;
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

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getNumeroProtocoloSaida() {
        return numeroProtocoloSaida;
    }

    public void setNumeroProtocoloSaida(Integer numeroProtocoloSaida) {
        this.numeroProtocoloSaida = numeroProtocoloSaida;
    }

    public Boolean getReserva() {
        return reserva;
    }

    public void setReserva(Boolean reserva) {
        this.reserva = reserva;
    }

    public MaterialDepositoSaidaReserva getMaterialDepositoSaidaReserva() {
        return materialDepositoSaidaReserva;
    }

    public void setMaterialDepositoSaidaReserva(MaterialDepositoSaidaReserva materialDepositoSaidaReserva) {
        this.materialDepositoSaidaReserva = materialDepositoSaidaReserva;
    }

    public Date getDataEnvioSap() {
        return dataEnvioSap;
    }

    public void setDataEnvioSap(Date dataEnvioSap) {
        this.dataEnvioSap = dataEnvioSap;
    }
    
    public double getQuantidadeOriginal() {
        return quantidadeOriginal;
    }

    public void setQuantidadeOriginal(double quantidadeOriginal) {
        this.quantidadeOriginal = quantidadeOriginal;
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
        hash += (materialDepositoSaidaId != null ? materialDepositoSaidaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialDepositoSaida)) {
            return false;
        }
        MaterialDepositoSaida other = (MaterialDepositoSaida) object;
        return !((this.materialDepositoSaidaId == null && other.materialDepositoSaidaId != null) || (this.materialDepositoSaidaId != null && !this.materialDepositoSaidaId.equals(other.materialDepositoSaidaId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.MaterialDepositoSaida[materialDepositoSaidaId=" + materialDepositoSaidaId + "]";
    }

}
