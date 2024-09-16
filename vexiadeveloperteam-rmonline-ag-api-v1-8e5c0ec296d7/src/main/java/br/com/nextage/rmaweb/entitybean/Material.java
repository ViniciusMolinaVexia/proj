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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "TB_MATERIAL")
public class Material implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "material";
    public static final String ALIAS_CLASSE_UNDERLINE = "material_";

    // Constantes com os nomes dos campos.
    public static final String MATERIAL_ID = "materialId";
    public static final String NOME = "nome";
    public static final String UNIDADE_MEDIDA = "unidadeMedida";
    public static final String TIPO_MATERIAL = "tipoMaterial";
    public static final String QUANTIDADE_MINIMA = "quantidadeMinima";
    public static final String NOME_COMPLETO = "nomeCompleto";
    public static final String CODIGO = "codigo";
    public static final String NCM = "ncm";
    public static final String ULTIMO_VALOR_NEGOCIADO = "ultimoValorNegociado";
    public static final String PATRIMONIADO = "patrimoniado";
    public static final String ESTOQUE_SAP = "estoqueSap";
    public static final String HIERARQUIA = "hierarquia";
    public static final String GRUPO_MERCADORIA = "grupoMercadoria";
    public static final String STATUS = "status";
    public static final String ULTIMA_ATUALIZACAO = "ultimaAtualizacao";
    public static final String CENTRO = "centro";
    public static final String OBSERVACAO = "observacao";
    public static final String NOTA = "nota";
    public static final String IS_SERVICO = "isServico";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_MATERIAL_ID", allocationSize = 1)
    @Column(name = "MATERIAL_ID")
    private Integer materialId;

    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;

    @Column(name = "NOME_COMPLETO", length = 5000)
    private String nomeCompleto;

    @Column(name = "CODIGO", length = 20)
    private String codigo;

    @JoinColumn(name = "UNIDADE_MEDIDA_ID", referencedColumnName = "UNIDADE_MEDIDA_ID")
    @ManyToOne
    private UnidadeMedida unidadeMedida;

    @JoinColumn(name = "TIPO_MATERIAL_ID", referencedColumnName = "TIPO_MATERIAL_ID")
    @ManyToOne(optional = false)
    private TipoMaterial tipoMaterial;

    @Column(name = "QUANTIDADE_MINIMA")
    private Double quantidadeMinima;

    @Column(name = "PATRIMONIADO", length = 1)
    private String patrimoniado;

    @Column(name = "ESTOQUE_SAP", length = 1)
    private String estoqueSap;

    @Column(name = "NCM", length = 20)
    private String ncm;

    @Column(name = "ULTIMO_VALOR_NEGOCIADO")
    private Double ultimoValorNegociado;

    @Column(name = "HIERARQUIA")
    private String hierarquia;

    @Column(name = "GRUPO_MERCADORIA")
    private String grupoMercadoria;

    @Column(name = "STATUS", length = 1)
    private String status;

    @Column(name = "ULTIMA_ATUALIZACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaAtualizacao;

    @Column(name = "CENTRO")
    private String centro;

    @Column(name = "OBSERVACAO", length = 300)
    private String observacao;
    
    @Column(name = "NOTA", length = 300)
    private String nota;

    @Column(name = "IS_SERVICO")
    private Boolean isServico;

    @Transient
    private RmMaterial rmMaterial;

    public Double getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(Double quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Material() {
    }

    public Material(Integer materialId) {
        this.materialId = materialId;
    }

    public Material(Integer materialId, String nome) {
        this.materialId = materialId;
        this.nome = nome;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public TipoMaterial getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(TipoMaterial tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public Double getUltimoValorNegociado() {
        return ultimoValorNegociado;
    }

    public void setUltimoValorNegociado(Double ultimoValorNegociado) {
        this.ultimoValorNegociado = ultimoValorNegociado;
    }

    public String getPatrimoniado() {
        return patrimoniado;
    }

    public void setPatrimoniado(String patrimoniado) {
        this.patrimoniado = patrimoniado;
    }

    public String getHierarquia() {
        return hierarquia;
    }

    public void setHierarquia(String hierarquia) {
        this.hierarquia = hierarquia;
    }

    public String getGrupoMercadoria() {
        return grupoMercadoria;
    }

    public void setGrupoMercadoria(String grupoMercadoria) {
        this.grupoMercadoria = grupoMercadoria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(Date ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public String getEstoqueSap() {
        return estoqueSap;
    }

    public void setEstoqueSap(String estoqueSap) {
        this.estoqueSap = estoqueSap;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public RmMaterial getRmMaterial() {
        return rmMaterial;
    }

    public void setRmMaterial(RmMaterial rmMaterial) {
        this.rmMaterial = rmMaterial;
    }

    public Boolean getIsServico() {
        return isServico;
    }

    public void setIsServico(Boolean isServico) {
        this.isServico = isServico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materialId != null ? materialId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Material)) {
            return false;
        }
        Material other = (Material) object;
        return !((this.materialId == null && other.materialId != null) || (this.materialId != null && !this.materialId.equals(other.materialId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.Material[materialId=" + materialId + "]";
    }
}
