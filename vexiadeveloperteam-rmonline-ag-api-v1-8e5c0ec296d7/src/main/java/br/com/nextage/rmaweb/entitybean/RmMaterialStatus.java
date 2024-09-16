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

/**
 *
 * @author nextage
 */
@Entity
@Table(name = "TB_RM_MATERIAL_STATUS")
public class RmMaterialStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "rmMaterialStatus";
    public static final String ID = "id";
    public static final String STATUS_ID = "status";
    public static final String RM_MATERIAL_ID = "rmMaterial";
    public static final String DATA_HORA_STATUS = "dataHoraStatus";
    public static final String USUARIO = "usuario";
    public static final String IS_HISTORICO = "historico";
    public static final String OBSERVACAO = "observacao";

    public RmMaterialStatus() {
    }

    public RmMaterialStatus(RmMaterialStatus rmMaterialStatus) {
        status = rmMaterialStatus.getStatus();
        dataHoraStatus = rmMaterialStatus.getDataHoraStatus();
        usuario = rmMaterialStatus.getUsuario();
        observacao = rmMaterialStatus.getObservacao();
        historico = rmMaterialStatus.getHistorico();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_RM_MATERIAL_STATUS_ID", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @JoinColumn(name = "STATUS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Status status;

    @JoinColumn(name = "RM_MATERIAL_ID", referencedColumnName = "RM_MATERIAL_ID")
    @ManyToOne(optional = false)
    private RmMaterial rmMaterial;

    @Column(name = "DATA_HORA_STATUS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraStatus;

    @Basic(optional = false)
    @Column(name = "USUARIO", length = 200)
    private String usuario;

    @Column(name = "OBSERVACAO", length = 400)
    private String observacao;

    @Column(name = "IS_HISTORICO")
    private Boolean historico;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public RmMaterial getRmMaterial() {
        return rmMaterial;
    }

    public void setRmMaterial(RmMaterial rmMaterial) {
        this.rmMaterial = rmMaterial;
    }

    public Date getDataHoraStatus() {
        return dataHoraStatus;
    }

    public void setDataHoraStatus(Date dataHoraStatus) {
        this.dataHoraStatus = dataHoraStatus;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Boolean getHistorico() {
        return historico;
    }

    public void setHistorico(Boolean historico) {
        this.historico = historico;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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
        if (!(object instanceof RmMaterialStatus)) {
            return false;
        }
        RmMaterialStatus other = (RmMaterialStatus) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.Status[id=" + id + "]";
    }
}
