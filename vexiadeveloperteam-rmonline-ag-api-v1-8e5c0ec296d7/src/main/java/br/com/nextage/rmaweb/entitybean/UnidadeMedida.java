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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "TB_UNIDADE_MEDIDA")
public class UnidadeMedida implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "unidadeMedida";
    public static final String ALIAS_CLASSE_UNDERLINE = "unidadeMedida_";

    public static final String UNIDADE_MEDIDA_ID = "unidadeMedidaId";
    public static final String SIGLA = "sigla";
    public static final String DESCRICAO = "descricao";

    @Id
    @Column(name = "UNIDADE_MEDIDA_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_UNIDADE_MEDIDA_ID", allocationSize = 1)
    private Integer unidadeMedidaId;

    @Basic(optional = false)
    @Column(name = "SIGLA")
    private String sigla;

    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    private String descricao;

    public UnidadeMedida() {
    }

    public UnidadeMedida(Integer unidadeMedidaId) {
        this.unidadeMedidaId = unidadeMedidaId;
    }

    public UnidadeMedida(Integer unidadeMedidaId, String sigla, String descricao) {
        this.unidadeMedidaId = unidadeMedidaId;
        this.sigla = sigla;
        this.descricao = descricao;
    }

    public Integer getUnidadeMedidaId() {
        return unidadeMedidaId;
    }

    public void setUnidadeMedidaId(Integer unidadeMedidaId) {
        this.unidadeMedidaId = unidadeMedidaId;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
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
        hash += (unidadeMedidaId != null ? unidadeMedidaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadeMedida)) {
            return false;
        }
        UnidadeMedida other = (UnidadeMedida) object;
        return !((this.unidadeMedidaId == null && other.unidadeMedidaId != null) || (this.unidadeMedidaId != null && !this.unidadeMedidaId.equals(other.unidadeMedidaId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.UnidadeMedida[unidadeMedidaId=" + unidadeMedidaId + "]";
    }

}
