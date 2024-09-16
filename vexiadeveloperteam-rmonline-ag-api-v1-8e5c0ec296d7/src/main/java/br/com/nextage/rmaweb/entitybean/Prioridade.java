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
 * @author Felipe
 */
@Entity
@Table(name = "TB_PRIORIDADE")
@NamedQueries({
	@NamedQuery(name="prioridade.getById",query="select p from Prioridade p where p.prioridadeId = :prioridadeId"),
	@NamedQuery(name="prioridade.getAll",query="select a from Area a order by a.nome")
})
public class Prioridade implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "prioridade";
    public static final String ALIAS_CLASSE_UNDERLINE = "prioridade_";

    public static final String PRIORIDADE_ID = "prioridadeId";
    public static final String DESCRICAO = "descricao";
    public static final String CODIGO = "codigo";
    public static final String CONF_DIAS_PREV_ENTREGA = "confDiasPrevEntrega";
    public static final String CONF_DIAS_INI_PRIORIDADE = "confDiasIniPrioridade";
    public static final String CONF_DIAS_FIM_PRIORIDADE = "confDiasFimPrioridade";
    public static final String CENTRO = "centro";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_PRIORIDADE_ID", allocationSize = 1)
    @Column(name = "PRIORIDADE_ID")
    private Integer prioridadeId;

    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "CODIGO", length = 20)
    private String codigo;

    @Column(name = "CONF_DIAS_PREV_ENTREGA")
    private Integer confDiasPrevEntrega;

    @Column(name = "CONF_DIAS_INI_PRIORIDADE")
    private Integer confDiasIniPrioridade;

    @Column(name = "CONF_DIAS_FIM_PRIORIDADE")
    private Integer confDiasFimPrioridade;
    
    @JoinColumn(name = "CENTRO_ID", referencedColumnName = "CENTRO_ID")
    @ManyToOne
    private Centro centro;

    @JoinColumn(name = "AREA_ID", referencedColumnName = "AREA_ID")
    @ManyToOne
    private Area area;

    @Column(name = "CODIGO_SAP")
    private String codigoSap;

    @Column(name = "ATIVO_FG")
    private boolean ativo;

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getCodigoSap() {
        return codigoSap;
    }

    public void setCodigoSap(String codigoSap) {
        this.codigoSap = codigoSap;
    }

    public Centro getCentro() {
    	return this.centro;
    }
    
    public void setCentro(Centro centro) {
    	this.centro=centro;
    }

    public Prioridade() {
    }

    public Prioridade(Integer prioridadeId) {
        this.prioridadeId = prioridadeId;
    }

    public Prioridade(String codigo) {
        this.codigo = codigo;
    }

    public Prioridade(Integer prioridadeId, String descricao) {
        this.prioridadeId = prioridadeId;
        this.descricao = descricao;
    }

    public Integer getPrioridadeId() {
        return prioridadeId;
    }

    public void setPrioridadeId(Integer prioridadeId) {
        this.prioridadeId = prioridadeId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getConfDiasPrevEntrega() {
        return confDiasPrevEntrega;
    }

    public void setConfDiasPrevEntrega(Integer confDiasPrevEntrega) {
        this.confDiasPrevEntrega = confDiasPrevEntrega;
    }

    public Integer getConfDiasIniPrioridade() {
        return confDiasIniPrioridade;
    }

    public void setConfDiasIniPrioridade(Integer confDiasIniPrioridade) {
        this.confDiasIniPrioridade = confDiasIniPrioridade;
    }

    public Integer getConfDiasFimPrioridade() {
        return confDiasFimPrioridade;
    }

    public void setConfDiasFimPrioridade(Integer confDiasFimPrioridade) {
        this.confDiasFimPrioridade = confDiasFimPrioridade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prioridadeId != null ? prioridadeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prioridade)) {
            return false;
        }
        Prioridade other = (Prioridade) object;
        return !((this.prioridadeId == null && other.prioridadeId != null) || (this.prioridadeId != null && !this.prioridadeId.equals(other.prioridadeId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.Prioridade[prioridadeId=" + prioridadeId + "]";
    }

}
