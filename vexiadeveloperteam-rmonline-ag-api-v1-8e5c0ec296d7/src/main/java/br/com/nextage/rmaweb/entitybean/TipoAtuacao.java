/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.entitybean;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Jerry
 */
@Entity
@Table(name = "TB_TIPO_ATUACAO")
public class TipoAtuacao implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "tipoAtuacao";
    public static final String ALIAS_CLASSE_UNDERLINE = "tipoAtuacao_";

    public static final String TIPO_ATUACAO_ID = "tipoAtuacaoId";
    public static final String DESCRICAO = "descricao";
    public static final String CODIGO = "codigo";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_TIPO_ATUACAO_ID", allocationSize = 1)
    @Column(name = "TIPO_ATUACAO_ID")
    private Integer tipoAtuacaoId;

    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    private String descricao;

    @Basic(optional = true)
    @Column(name = "CODIGO", length = 20)
    private String codigo;

    @Transient
    private Boolean selected;

    @Transient
    private Integer pessoaId;

    public TipoAtuacao() {
    }

    public TipoAtuacao(Integer tipoAtuacaoId) {
        this.tipoAtuacaoId = tipoAtuacaoId;
    }

    public TipoAtuacao(Integer tipoAtuacaoId, String descricao) {
        this.tipoAtuacaoId = tipoAtuacaoId;
        this.descricao = descricao;
    }

    public TipoAtuacao(Integer tipoAtuacaoId, String descricao, String codigo) {
        this.tipoAtuacaoId = tipoAtuacaoId;
        this.descricao = descricao;
        this.codigo = codigo;
    }

    public Integer getTipoAtuacaoId() {
        return tipoAtuacaoId;
    }

    public void setTipoAtuacaoId(Integer tipoAtuacaoId) {
        this.tipoAtuacaoId = tipoAtuacaoId;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
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

    public Integer getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Integer pessoaId) {
        this.pessoaId = pessoaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoAtuacaoId != null ? tipoAtuacaoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAtuacao)) {
            return false;
        }
        TipoAtuacao other = (TipoAtuacao) object;
        return !((this.tipoAtuacaoId == null && other.tipoAtuacaoId != null) || (this.tipoAtuacaoId != null && !this.tipoAtuacaoId.equals(other.tipoAtuacaoId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.TipoAtuacao[tipoAtuacaoId=" + tipoAtuacaoId + "]";
    }

}
