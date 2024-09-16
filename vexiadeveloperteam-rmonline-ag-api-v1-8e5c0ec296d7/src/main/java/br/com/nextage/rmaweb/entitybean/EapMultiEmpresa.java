/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.entitybean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Lucas Heitor
 */
@Entity
@Table(name = "TB_EAP_MULTI_EMPRESA")
public class EapMultiEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "eapMultiEmpresa";
    public static final String ALIAS_CLASSE_UNDERLINE = "eapMultiEmpresa_";

    // Constantes com os nomes dos campos.
    public static final String ID = "id";
    public static final String CODIGO = "codigo";
    public static final String DESCRICAO = "descricao";
    public static final String CAMINHO_RH_WEB = "caminhoRhWeb";
    public static final String CENTRO = "centro";
    public static final String MULTI_EMPRESA = "multiEmpresa";
    public static final String CODIGO_DEPOSITO_PADRAO = "codigoDepositoPadrao";
    public static final String APROVACAO_COORDENADOR = "aprovacaoCoordenador";
    public static final String APROVACAO_EQUIPE_CUSTOS = "aprovacaoEquipeCustos";

    public EapMultiEmpresa() {
    }

    public EapMultiEmpresa(Integer id) {
        this.id = id;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_EAP_MULTI_EMPRESA_ID", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "CODIGO")
    private String codigo;

    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    private String descricao;

    @Basic(optional = false)
    @Column(name = "CAMINHO_RH_WEB")
    private String caminhoRhWeb;

    @Basic(optional = false)
    @Column(name = "CENTRO")
    private String centro;

    @JoinColumn(name = "MULTI_EMPRESA_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private MultiEmpresa multiEmpresa;

    @Basic
    @Column(name = "CODIGO_DEPOSITO_PADRAO")
    private String codigoDepositoPadrao;

    @Basic
    @Column(name = "APROVACAO_COORDENADOR")
    private Boolean aprovacaoCoordenador;

    @Basic
    @Column(name = "APROVACAO_EQUIPE_CUSTOS")
    private Boolean aprovacaoEquipeCustos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCaminhoRhWeb() {
        return caminhoRhWeb;
    }

    public void setCaminhoRhWeb(String caminhoRhWeb) {
        this.caminhoRhWeb = caminhoRhWeb;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public MultiEmpresa getMultiEmpresa() {
        return multiEmpresa;
    }

    public void setMultiEmpresa(MultiEmpresa multiEmpresa) {
        this.multiEmpresa = multiEmpresa;
    }

    public String getCodigoDepositoPadrao() {
        return codigoDepositoPadrao;
    }

    public void setCodigoDepositoPadrao(String codigoDepositoPadrao) {
        this.codigoDepositoPadrao = codigoDepositoPadrao;
    }

    public Boolean getAprovacaoCoordenador() {
        return aprovacaoCoordenador;
    }

    public void setAprovacaoCoordenador(Boolean aprovacaoCoordenador) {
        this.aprovacaoCoordenador = aprovacaoCoordenador;
    }

    public Boolean getAprovacaoEquipeCustos() {
        return aprovacaoEquipeCustos;
    }

    public void setAprovacaoEquipeCustos(Boolean aprovacaoEquipeCustos) {
        this.aprovacaoEquipeCustos = aprovacaoEquipeCustos;
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
        if (!(object instanceof EapMultiEmpresa)) {
            return false;
        }
        EapMultiEmpresa other = (EapMultiEmpresa) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.EapMultiEmpresa[id=" + id + "]";
    }
}
