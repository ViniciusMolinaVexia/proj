/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlInlineBinaryData;

/**
 *
 * @author nextage
 */
@Entity
@Table(name = "TB_SERVICO_ANEXO")
// Para não dar o erro IllegalAnnotationsException: Class has two properties of same name
// com o atributo anexo
// @see http://stackoverflow.com/questions/12392235/illegalannotationsexception-class-has-two-properties-of-same-name
@XmlAccessorType(XmlAccessType.FIELD)
public class AnexoServico implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "anexoServico";

    public static final String ID = "id";
    public static final String ESCOPO = "escopo";
    public static final String ESCOPO_ID = "escopoId";
    public static final String NOME = "nome";
    public static final String ANEXO = "anexo";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_ANEXO_ID", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "ESCOPO", length = 100)
    private String escopo;

    @Basic(optional = false)
    @Column(name = "ESCOPO_ID")
    private Integer escopoId;

    @Basic(optional = false)
    @Column(name = "NOME", length = 200)
    private String nome;

    @Basic(optional = false)
    @XmlInlineBinaryData
    @Lob
    @Column(name = "ANEXO")
    private byte[] anexo;

    @Transient
    private String anexoBase64;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEscopo() {
        return escopo;
    }

    public void setEscopo(String escopo) {
        this.escopo = escopo;
    }

    public Integer getEscopoId() {
        return escopoId;
    }

    public void setEscopoId(Integer escopoId) {
        this.escopoId = escopoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getAnexo() {
        return anexo;
    }

    public void setAnexo(byte[] anexo) {
        this.anexo = anexo;
    }

    public String getAnexoBase64() {
        return anexoBase64;
    }

    public void setAnexoBase64(String anexoBase64) {
        this.anexoBase64 = anexoBase64;
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
        if (!(object instanceof AnexoServico)) {
            return false;
        }
        AnexoServico other = (AnexoServico) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.AnexoServico[id=" + id + "]";
    }
}
