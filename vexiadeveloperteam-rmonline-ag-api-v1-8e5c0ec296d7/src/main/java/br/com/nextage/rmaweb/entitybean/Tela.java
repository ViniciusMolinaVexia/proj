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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Leco
 */
@Entity
@Table(name = "TB_TELA")
@NamedQueries({
    @NamedQuery(name = "Tela.findAll", query = "SELECT t FROM Tela t"),
    @NamedQuery(name = "Tela.findByTelaId", query = "SELECT t FROM Tela t WHERE t.telaId = :telaId"),
    @NamedQuery(name = "Tela.findByDescricao", query = "SELECT t FROM Tela t WHERE t.descricao = :descricao"),
    @NamedQuery(name = "Tela.findByObservacao", query = "SELECT t FROM Tela t WHERE t.observacao = :observacao")})
public class Tela implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "tela";
    public static final String ALIAS_CLASSE_UNDERLINE = "tela_";

    // Constantes com os nomes dos campos.
    public static final String TELA_ID = "telaId";
    public static final String DESCRICAO = "descricao";
    public static final String OBSERVACAO = "observacao";

    @Id
    @Basic(optional = false)
    @Column(name = "TELA_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_TELA_ID", allocationSize = 1)
    private Integer telaId;

    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "OBSERVACAO")
    private String observacao;

    public Tela() {
    }

    public Tela(Integer telaId) {
        this.telaId = telaId;
    }

    public Tela(Integer telaId, String descricao) {
        this.telaId = telaId;
        this.descricao = descricao;
    }

    public Integer getTelaId() {
        return telaId;
    }

    public void setTelaId(Integer telaId) {
        this.telaId = telaId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        hash += (telaId != null ? telaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tela)) {
            return false;
        }
        Tela other = (Tela) object;
        return !((this.telaId == null && other.telaId != null) || (this.telaId != null && !this.telaId.equals(other.telaId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.Tela[telaId=" + telaId + "]";
    }

}
