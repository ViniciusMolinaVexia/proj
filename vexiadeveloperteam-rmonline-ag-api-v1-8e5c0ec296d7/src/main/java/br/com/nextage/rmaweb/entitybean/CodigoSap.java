package br.com.nextage.rmaweb.entitybean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_SERVICO_CODIGO_SAP")
public class CodigoSap implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "servicoCodigoSap";
    public static final String ALIAS_CLASSE_UNDERLINE = "servicoCodigoSap_";

    public static final String CODIGO_ID = "codigoId";
    public static final String CODIGO = "codigo";
    public static final String CRIADO = "criado";
    public static final String DESCRICAO = "descricao";

    @Id
    @Column(name = "CODIGO_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_SERVICO_CODIGO_SAP_ID", allocationSize = 1)
    private Integer codigoId;

    @Column(name = "CODIGO")
    private String codigo;
    
    @Column(name = "CRIADO")
    private String criado;

    @Column(name = "DESCRICAO")
    private String descricao;

    public CodigoSap() {
    }

    public CodigoSap(Integer codigoId) {
        this.codigoId = codigoId;
    }

    public CodigoSap(Integer codigo, String descricao) {
        this.codigoId = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigoId() {
        return codigoId;
    }

    public void setCodigoId(Integer codigoId) {
        this.codigoId = codigoId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCriado() {
        return criado;
    }

    public void setCriado(String criado) {
        this.criado = criado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoId != null ? codigoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RmServicoCodigoSap)) {
            return false;
        }
        CodigoSap other = (CodigoSap) object;
        return !((this.codigoId == null && other.codigoId != null) || (this.codigoId != null && !this.codigoId.equals(other.codigoId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.CodigoSap[codigoId=" + codigoId + "]";
    }
}
