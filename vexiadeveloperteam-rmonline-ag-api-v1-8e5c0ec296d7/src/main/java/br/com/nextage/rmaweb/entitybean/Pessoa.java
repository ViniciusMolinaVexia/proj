/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.entitybean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "TB_PESSOA")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "pessoa";
    public static final String ALIAS_CLASSE_UNDERLINE = "pessoa_";

    public static final String PESSOA_ID = "pessoaId";
    public static final String REFERENCIA = "referencia";
    public static final String CPF = "cpf";
    public static final String NOME = "nome";
    public static final String IS_REQUISITANTE = "isRequisitante";
    public static final String FUNCAO = "funcao";
    public static final String TELEFONE = "telefone";
    public static final String EMAIL = "email";
    public static final String SETOR = "setor";
    public static final String SUB_AREA = "subArea";
    public static final String TIPO_ATUACAO = "tipoAtuacao";
    public static final String DATA_ADMISSAO = "dataAdmissao";
    public static final String DATA_DEMISSAO = "dataDemissao";
    public static final String EAP_MULTI_EMPRESA = "eapMultiEmpresa";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_PESSOA_ID", allocationSize = 1)
    @Column(name = "PESSOA_ID")
    private Integer pessoaId;

    @Column(name = "CPF")
    private String cpf;

    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;

    @Column(name = "IS_REQUISITANTE")
    private Short isRequisitante;

    @Column(name = "FUNCAO")
    private String funcao;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "REFERENCIA")
    private String referencia;

    @JoinColumn(name = "SETOR_ID", referencedColumnName = "SETOR_ID")
    @ManyToOne
    private Setor setor;

    @JoinColumn(name = "SUB_AREA_ID", referencedColumnName = "SUB_AREA_ID")
    @ManyToOne
    private SubArea subArea;

    @Column(name = "TIPO_ATUACAO", length = 50)
    private String tipoAtuacao;

    @Column(name = "DATA_ADMISSAO")
    @Temporal(TemporalType.DATE)
    private Date dataAdmissao;

    @Column(name = "DATA_DEMISSAO")
    @Temporal(TemporalType.DATE)
    private Date dataDemissao;

    @JoinColumn(name = "EAP_MULTI_EMPRESA_ID", referencedColumnName = "ID")
    @ManyToOne
    private EapMultiEmpresa eapMultiEmpresa;

    public Pessoa() {
    }

    public Pessoa(Integer pessoaId) {
        this.pessoaId = pessoaId;
    }

    public Pessoa(Integer pessoaId, String cpf, String nome) {
        this.pessoaId = pessoaId;
        this.cpf = cpf;
        this.nome = nome;
    }

    public Integer getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Integer pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Short getIsRequisitante() {
        return isRequisitante;
    }

    public void setIsRequisitante(Short isRequisitante) {
        this.isRequisitante = isRequisitante;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getTipoAtuacao() {
        return tipoAtuacao;
    }

    public void setTipoAtuacao(String tipoAtuacao) {
        this.tipoAtuacao = tipoAtuacao;
    }

    public SubArea getSubArea() {
        return subArea;
    }

    public void setSubArea(SubArea subArea) {
        this.subArea = subArea;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Date getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(Date dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    public EapMultiEmpresa getEapMultiEmpresa() {
        return eapMultiEmpresa;
    }

    public void setEapMultiEmpresa(EapMultiEmpresa eapMultiEmpresa) {
        this.eapMultiEmpresa = eapMultiEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pessoaId != null ? pessoaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        return !((this.pessoaId == null && other.pessoaId != null) || (this.pessoaId != null && !this.pessoaId.equals(other.pessoaId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.Pessoa[pessoaId=" + pessoaId + "]";
    }

}
