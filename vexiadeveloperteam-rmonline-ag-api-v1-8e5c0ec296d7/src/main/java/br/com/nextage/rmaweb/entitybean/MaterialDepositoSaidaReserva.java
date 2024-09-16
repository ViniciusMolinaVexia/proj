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
 * @author nextage
 */
@Entity
@Table(name = "TB_MATERIAL_DEPOSITO_SAIDA_RESERVA")
public class MaterialDepositoSaidaReserva implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "materialDepositoSaidaReserva";

    public static final String ID = "id";
    public static final String DATA_SAIDA = "dataSaida";
    public static final String RM_MATERIAL = "rmMaterial";
    public static final String DATA_AVALIACAO = "dataAvaliacao";
    public static final String STATUS = "status";
    public static final String QUANTIDADE_SOLICITADA = "quantidadeSolicitada";
    public static final String OBSERVACAO_PAINEL_ESTOQUISTA = "observacaoPainelEstoquista";
    public static final String ITEM_INDISPONIVEL = "itemIndisponivel";

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_RM_APROVADOR_ID", allocationSize = 1)
    private Integer id;

    @Column(name = "DATA_SAIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataSaida;

    @JoinColumn(name = "RM_MATERIAL", referencedColumnName = "RM_MATERIAL_ID")
    @ManyToOne
    private RmMaterial rmMaterial;

    @Column(name = "DATA_AVALIACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAvaliacao;

    @Column(name = "STATUS", length = 1)
    private String status;

    @Column(name = "QUANTIDADE_SOLICITADA")
    private double quantidadeSolicitada;

    @Basic(optional = true)
    @Column(name = "OBSERVACAO_PAINEL_ESTOQUISTA", length = 1000)
    private String observacaoPainelEstoquista;

    @Basic(optional = true)
    @Column(name = "ITEM_INDISPONIVEL")
    private Boolean itemIndisponivel;

    @Transient
    private Deposito deposito;

    @Transient
    private String statusItem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public RmMaterial getRmMaterial() {
        return rmMaterial;
    }

    public void setRmMaterial(RmMaterial rmMaterial) {
        this.rmMaterial = rmMaterial;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getQuantidadeSolicitada() {
        return quantidadeSolicitada;
    }

    public void setQuantidadeSolicitada(double quantidadeSolicitada) {
        this.quantidadeSolicitada = quantidadeSolicitada;
    }

    public String getObservacaoPainelEstoquista() {
        return observacaoPainelEstoquista;
    }

    public void setObservacaoPainelEstoquista(String observacaoPainelEstoquista) {
        this.observacaoPainelEstoquista = observacaoPainelEstoquista;
    }

    public Boolean getItemIndisponivel() {
        return itemIndisponivel;
    }

    public void setItemIndisponivel(Boolean itemIndisponivel) {
        this.itemIndisponivel = itemIndisponivel;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public String getStatusItem() {
        return statusItem;
    }

    public void setStatusItem(String statusItem) {
        this.statusItem = statusItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialDepositoSaidaReserva)) {
            return false;
        }
        MaterialDepositoSaidaReserva other = (MaterialDepositoSaidaReserva) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.MaterialDepositoSaidaReserva[id=" + id + "]";
    }
}
