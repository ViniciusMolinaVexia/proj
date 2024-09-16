package br.com.nextage.rmaweb.entitybean;

import java.io.Serializable;
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

/**
 *
 * @author Marlon B. Santana
 */
@Entity
@Table(name = "TB_RM_DEPOSITO")
public class RmDeposito implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "rmDeposito";
    public static final String ALIAS_CLASSE_UNDERLINE = "rmDeposito_";

    public static final String ID = "id";
    public static final String RM = "rm";
    public static final String DEPOSITO = "deposito";

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_RM_DEPOSITO_ID", allocationSize = 1)
    private Integer id;

    @JoinColumn(name = "DEPOSITO_ID", referencedColumnName = "DEPOSITO_ID")
    @ManyToOne
    private Deposito deposito;

    @JoinColumn(name = "RM_ID", referencedColumnName = "RM_ID")
    @ManyToOne
    private Rm rm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public Rm getRm() {
        return rm;
    }

    public void setRm(Rm rm) {
        this.rm = rm;
    }

}
