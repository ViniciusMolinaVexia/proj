/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.entitybean;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Leco
 */
@Entity
@Table(name = "TB_DEPOSITO")
@NamedQueries({
    @NamedQuery(name = "Deposito.findAll", query = "SELECT d FROM Deposito d"),
        @NamedQuery(name = "Deposito.findByDepositoId", query = "SELECT d FROM Deposito d WHERE d.depositoId = :depositoId"),
        @NamedQuery(name = "Deposito.findByCodigoAndCentro", query = "SELECT d FROM Deposito d WHERE d.codigo = :codigo AND d.centro.codigo = :centro"),
    @NamedQuery(name = "Deposito.findByNome", query = "SELECT d FROM Deposito d WHERE d.nome = :nome"),
    @NamedQuery(name = "Deposito.findByObservacao", query = "SELECT d FROM Deposito d WHERE d.observacao = :observacao"),
    @NamedQuery(name = "Deposito.findByEndereco", query = "SELECT d FROM Deposito d WHERE d.endereco = :endereco"),
    @NamedQuery(name = "Deposito.findByTelefone", query = "SELECT d FROM Deposito d WHERE d.telefone = :telefone"),
	@NamedQuery(name = "Deposito.getByCentroId", query = "SELECT d FROM Deposito d WHERE d.centro.centroId = :centroId")})
public class Deposito implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "deposito";
    public static final String ALIAS_CLASSE_UNDERLINE = "deposito_";

    // Constantes com os nomes dos campos.
    public static final String DEPOSITO_ID = "depositoId";
    public static final String NOME = "nome";
    public static final String CODIGO = "codigo";
    public static final String OBSERVACAO = "observacao";
    public static final String ENDERECO = "endereco";
    public static final String TELEFONE = "telefone";
    public static final String RESPONSAVEL_ID = "responsavel";
    public static final String IS_TEMPORARIO = "isTemporario";
    public static final String CENTRO_ID = "centro.centroId";

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_DEPOSITO_ID", allocationSize = 1)
    @Column(name = "DEPOSITO_ID")
    private Integer depositoId;

    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;

    @Column(name = "CODIGO", length = 50)
    private String codigo;

    @Column(name = "OBSERVACAO")
    private String observacao;

    @Column(name = "ENDERECO")
    private String endereco;

    @Column(name = "TELEFONE")
    private String telefone;

    @Basic(optional = true)
    @Column(name = "IS_TEMPORARIO")
    private Boolean isTemporario;

    @JoinColumn(name = "RESPONSAVEL_ID", referencedColumnName = "PESSOA_ID")
    @ManyToOne(optional = false)
    private Pessoa responsavel;

    @JoinColumn(name = "EAP_MULTI_EMPRESA_ID", referencedColumnName = "ID")
    @ManyToOne
    private EapMultiEmpresa eapMultiEmpresa;
    
    @JoinColumn(name = "CENTRO_ID", referencedColumnName = "CENTRO_ID")
    @ManyToOne
    private Centro centro;

    public Deposito() {
    }
    
    public Centro getCentro() {
    	return this.centro;
    }
    
    public void setCentro(Centro centro) {
    	this.centro=centro;
    }

    public Deposito(Integer depositoId) {
        this.depositoId = depositoId;
    }

    public Deposito(Integer depositoId, String nome) {
        this.depositoId = depositoId;
        this.nome = nome;
    }

    public Deposito(Integer depositoId, String nome, String codigo) {
        this.depositoId = depositoId;
        this.nome = nome;
        this.codigo = codigo;
    }

    public Integer getDepositoId() {
        return depositoId;
    }

    public void setDepositoId(Integer depositoId) {
        this.depositoId = depositoId;
    }

    public String getNome() {
//        if (eapMultiEmpresa != null && eapMultiEmpresa.getDescricao() != null
//                && nome != null && !nome.contains("- " + eapMultiEmpresa.getDescricao())) {
//            nome = nome + " - " + eapMultiEmpresa.getDescricao();
//        }
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Pessoa getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Pessoa responsavel) {
        this.responsavel = responsavel;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Boolean getIsTemporario() {
        return isTemporario;
    }

    public void setIsTemporario(Boolean isTemporario) {
        this.isTemporario = isTemporario;
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
        hash += (depositoId != null ? depositoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Deposito)) {
            return false;
        }
        Deposito other = (Deposito) object;
        return !((this.depositoId == null && other.depositoId != null) || (this.depositoId != null && !this.depositoId.equals(other.depositoId)));
    }

    @Override
    public String toString() {
        return "Deposito{" +
                "depositoId=" + depositoId +
                ", nome='" + nome + '\'' +
                ", codigo='" + codigo + '\'' +
                ", observacao='" + observacao + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", isTemporario=" + isTemporario +
                ", responsavel=" + responsavel +
                ", eapMultiEmpresa=" + eapMultiEmpresa +
                ", centro=" + centro +
                '}';
    }
}
