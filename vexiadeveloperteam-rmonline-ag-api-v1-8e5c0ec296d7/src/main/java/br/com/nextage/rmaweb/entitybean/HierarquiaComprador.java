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
 * @author Walter
 */
@Entity
@Table(name = "TB_HIERARQUIA_COMPRADOR")
public class HierarquiaComprador implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constantes com os nomes da classe
    public static final String ALIAS_CLASSE = "hierarquiaComprador";
    public static final String ALIAS_CLASSE_UNDERLINE = "hierarquiaComprador_";

    // Constantes com os nomes dos campos.
    public static final String HIERARQUIA_COMPRADOR_ID = "hierarquiaCompradorId";
    public static final String COMPRADOR = "comprador";
    public static final String HIERARQUIA = "hierarquia";
    public static final String COMPRADOR_VEZ = "compradorVez";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_HIERARQUIA_COMPRADOR_ID", allocationSize = 1)
    @Column(name = "HIERARQUIA_COMPRADOR_ID")
    private Integer hierarquiaCompradorId;


    @JoinColumn(name = "COMPRADOR_ID", referencedColumnName = "COMPRADOR_ID")
    @ManyToOne(optional = false)
    private Comprador comprador;

    @Column(name = "HIERARQUIA")
    @Basic(optional = false)
    private String hierarquia;

    @Column(name = "COMPRADOR_VEZ")
    private Boolean compradorVez;

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Integer getHierarquiaCompradorId() {
        return hierarquiaCompradorId;
    }

    public void setHierarquiaCompradorId(Integer hierarquiaCompradorId) {
        this.hierarquiaCompradorId = hierarquiaCompradorId;
    }

    public String getHierarquia() {
        return hierarquia;
    }

    public void setHierarquia(String hierarquia) {
        this.hierarquia = hierarquia;
    }

    public Boolean getCompradorVez() {
        return compradorVez;
    }

    public void setCompradorVez(Boolean compradorVez) {
        this.compradorVez = compradorVez;
    }
}