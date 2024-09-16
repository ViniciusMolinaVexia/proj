package br.com.nextage.rmaweb.entitybean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nextage
 */
@Entity
@Table(name = "VW_RM_SERVICO")
public class VwRmServico implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "vwRmServico";
    public static final String ID = "id";
    public static final String RM_SERVICO_ID = "rmServico";
    public static final String RM_SERVICO_STATUS_ID = "rmServicoStatus";
    public static final String DATA_APROV_GERENTE_AREA = "dataAprovGerenteArea";
    public static final String DATA_APROV_GERENTE_CUSTOS = "dataAprovGerenteCustos";
    public static final String DATA_APROV_COORDENADOR = "dataAprovCoordenador";
    public static final String DATA_APROV_RESPONSAVEL_RETIRADA_ESTOQUE = "dataAprovResponsavelRetiradaEstoque";
    public static final String DATA_APROV_COMPLEMENTO_CUSTOS = "dataAprovComplementoCustos";

    //O id desta view é o numero da linha no resultado da consulta,
    //foi feito isso pois o jpa exige um id em uma @Entity
    //NÃO DEVE SER UTILIZADO E NAO DEVE SER GERADO O CONTRUTOR
    @Id
    @Column(name = "ID")
    private Integer id;

    @JoinColumn(name = "RM_SERVICO_ID", referencedColumnName = "RM_SERVICO_ID")
    @ManyToOne
    private RmServico rmServico;

    @JoinColumn(name = "RM_SERVICO_STATUS_ID", referencedColumnName = "ID")
    @ManyToOne
    private RmServicoStatus rmServicoStatus;


    @Column(name = "DATA_APROV_GERENTE_AREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAprovGerenteArea;

    @Column(name = "DATA_APROV_GERENTE_CUSTOS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAprovGerenteCustos;

    @Column(name = "DATA_APROV_COORDENADOR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAprovCoordenador;

    @Column(name = "DATA_APROV_RESPONSAVEL_RETIRADA_ESTOQUE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAprovResponsavelRetiradaEstoque;

    @Column(name = "DATA_APROV_COMPLEMENTO_CUSTOS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAprovComplementoCustos;


    public RmServico getRmServico() {
        return rmServico;
    }

    public void setRmServico(RmServico rmServico) {
        this.rmServico = rmServico;
    }

    public RmServicoStatus getRmServicoStatus() {
        return rmServicoStatus;
    }

    public void setRmServicoStatus(RmServicoStatus rmServicoStatus) {
        this.rmServicoStatus = rmServicoStatus;
    }


    public Date getDataAprovGerenteArea() {
        return dataAprovGerenteArea;
    }

    public void setDataAprovGerenteArea(Date dataAprovGerenteArea) {
        this.dataAprovGerenteArea = dataAprovGerenteArea;
    }

    public Date getDataAprovGerenteCustos() {
        return dataAprovGerenteCustos;
    }

    public void setDataAprovGerenteCustos(Date dataAprovGerenteCustos) {
        this.dataAprovGerenteCustos = dataAprovGerenteCustos;
    }

    public Date getDataAprovComplementoCustos() {
        return dataAprovComplementoCustos;
    }

    public void setDataAprovComplementoCustos(Date dataAprovComplementoCustos) {
        this.dataAprovComplementoCustos = dataAprovComplementoCustos;
    }

    public Date getDataAprovResponsavelRetiradaEstoque() {
        return dataAprovResponsavelRetiradaEstoque;
    }

    public void setDataAprovResponsavelRetiradaEstoque(Date dataAprovResponsavelRetiradaEstoque) {
        this.dataAprovResponsavelRetiradaEstoque = dataAprovResponsavelRetiradaEstoque;
    }

    public Date getDataAprovCoordenador() {
        return dataAprovCoordenador;
    }

    public void setDataAprovCoordenador(Date dataAprovCoordenador) {
        this.dataAprovCoordenador = dataAprovCoordenador;
    }


    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.VwRmServico[]";
    }
}
