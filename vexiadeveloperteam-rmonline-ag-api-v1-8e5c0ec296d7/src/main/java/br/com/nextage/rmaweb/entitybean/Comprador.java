/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.entitybean;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "TB_COMPRADOR")
public class Comprador implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "comprador";
    public static final String ALIAS_CLASSE_UNDERLINE = "comprador_";

    public static final String COMPRADOR_ID = "compradorId";
    public static final String NOME = "nome";
    public static final String ATIVO = "ativo";
    public static final String EAP_MULTI_EMPRESA = "eapMultiEmpresa";
    public static final String EMAIL = "email";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_COMPRADOR_ID", allocationSize = 1)
    @Column(name = "COMPRADOR_ID")
    private Integer compradorId;

    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;

    @Column(name = "ATIVO")
    private String ativo;

    @Column(name = "EMAIL")
    private String email;

    @JoinColumn(name = "EAP_MULTI_EMPRESA_ID", referencedColumnName = "ID")
    @ManyToOne
    private EapMultiEmpresa eapMultiEmpresa;

    public Comprador() {
    }

    public Comprador(Integer compradorId) {
        this.compradorId = compradorId;
    }

    public Comprador(Integer compradorId, String nome) {
        this.compradorId = compradorId;
        this.nome = nome;
    }

    public Integer getCompradorId() {
        return compradorId;
    }

    public void setCompradorId(Integer compradorId) {
        this.compradorId = compradorId;
    }

    public String getNome() {

        if (eapMultiEmpresa != null && eapMultiEmpresa.getDescricao() != null
                && nome != null && !nome.contains("- " + eapMultiEmpresa.getDescricao())) {
            nome = nome + " - " + eapMultiEmpresa.getDescricao();
        }

        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public EapMultiEmpresa getEapMultiEmpresa() {
        return eapMultiEmpresa;
    }

    public void setEapMultiEmpresa(EapMultiEmpresa eapMultiEmpresa) {
        this.eapMultiEmpresa = eapMultiEmpresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compradorId != null ? compradorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comprador)) {
            return false;
        }
        Comprador other = (Comprador) object;
        return !((this.compradorId == null && other.compradorId != null) || (this.compradorId != null && !this.compradorId.equals(other.compradorId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.Comprador[compradorId=" + compradorId + "]";
    }

}
