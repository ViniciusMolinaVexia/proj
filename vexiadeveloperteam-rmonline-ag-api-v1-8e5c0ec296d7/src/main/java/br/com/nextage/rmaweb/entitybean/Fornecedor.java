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

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "TB_FORNECEDOR")
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;
    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "fornecedor";
    public static final String ALIAS_CLASSE_UNDERLINE = "fornecedor_";
    // Constantes com os nomes dos campos.
    public static final String FORNECEDOR_ID = "fornecedorId";
    public static final String NOME = "nome";
    public static final String ENDERECO = "endereco";
    public static final String CEP = "cep";
    public static final String CONTATO = "contato";
    public static final String TELEFONE = "telefone";
    public static final String CIDADE = "cidade";
    public static final String ORGC = "orgc";
    public static final String CODIGO = "codigo";
    public static final String CNPJ = "cnpj";
    public static final String STATUS = "status";
    public static final String ULTIMA_ATUALIZACAO = "ultimaAtualizacao";
    public static final String CENTRO = "centro";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_FORNECEDOR_ID", allocationSize = 1)
    @Column(name = "FORNECEDOR_ID")
    private Integer fornecedorId;

    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;

    @Column(name = "ENDERECO")
    private String endereco;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "CONTATO")
    private String contato;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "ORGC")
    private String orgc;

    @JoinColumn(name = "CIDADE", referencedColumnName = "CIDADE")
    @ManyToOne
    private Cidade cidade;

    @Column(name = "CODIGO")
    private String codigo;

    @Column(name = "CNPJ")
    private String cnpj;

    @Column(name = "STATUS", length = 1)
    private String status;

    @Column(name = "ULTIMA_ATUALIZACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaAtualizacao;

    @Column(name = "CENTRO")
    private String centro;

    public Fornecedor() {
    }

    public Fornecedor(Integer fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public Fornecedor(Integer fornecedorId, String nome) {
        this.fornecedorId = fornecedorId;
        this.nome = nome;
    }

    public Integer getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Integer fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getOrgc() {
        return orgc;
    }

    public void setOrgc(String orgc) {
        this.orgc = orgc;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fornecedorId != null ? fornecedorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
        return !((this.fornecedorId == null && other.fornecedorId != null) || (this.fornecedorId != null && !this.fornecedorId.equals(other.fornecedorId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.Fornecedor[fornecedorId=" + fornecedorId + "]";
    }
}
