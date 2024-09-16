package br.com.nextage.rmaweb.entitybean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Lucas Heitor
 */
@Entity
@Table(name = "VW_MATERIAL_DEPOSITO_SAIDA_RESERVA")
public class VwMaterialDepositoSaidaReserva implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "vwMaterialDepositoSaidaReserva";
    public static final String ID = "id";
    public static final String MATERIAL_DEPOSITO_SAIDA_RESERVA_ID = "materialDepositoSaidaReserva";
    public static final String RM_MATERIAL_STATUS_ID = "rmMaterialStatus";
    public static final String PESSOA_ID = "pessoa";

    //O id desta view é o numero da linha no resultado da consulta,
    //foi feito isso pois o jpa exige um id em uma @Entity
    //NÃO DEVE SER UTILIZADO
    @Id
    @Column(name = "ID")
    private Integer id;

    @JoinColumn(name = "MATERIAL_DEPOSITO_SAIDA_RESERVA_ID", referencedColumnName = "ID")
    @ManyToOne
    private MaterialDepositoSaidaReserva materialDepositoSaidaReserva;
    
    @JoinColumn(name = "RM_MATERIAL_STATUS_ID", referencedColumnName = "ID")
    @ManyToOne
    private RmMaterialStatus rmMaterialStatus;

    @JoinColumn(name = "PESSOA_ID", referencedColumnName = "PESSOA_ID")
    @ManyToOne
    private Pessoa pessoa;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public MaterialDepositoSaidaReserva getMaterialDepositoSaidaReserva() {
        return materialDepositoSaidaReserva;
    }

    public void setMaterialDepositoSaidaReserva(MaterialDepositoSaidaReserva materialDepositoSaidaReserva) {
        this.materialDepositoSaidaReserva = materialDepositoSaidaReserva;
    }
    
    public RmMaterialStatus getRmMaterialStatus() {
        return rmMaterialStatus;
    }

    public void setRmMaterialStatus(RmMaterialStatus rmMaterialStatus) {
        this.rmMaterialStatus = rmMaterialStatus;
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.VwMaterialDepositoSaidaReserva[]";
    }
}
