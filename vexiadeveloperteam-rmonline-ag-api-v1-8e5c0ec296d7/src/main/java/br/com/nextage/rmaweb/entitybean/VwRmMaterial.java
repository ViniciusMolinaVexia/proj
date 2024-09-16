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
@Table(name = "VW_RM_MATERIAL")
public class VwRmMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "vwRmMaterial";
    public static final String ID = "id";
    public static final String RM_MATERIAL_ID = "rmMaterial";
    public static final String RM_MATERIAL_STATUS_ID = "rmMaterialStatus";
    public static final String DIAS_PREVISTOS = "diasPrevistos";
    public static final String DATA_APROV_GERENTE_AREA = "dataAprovGerenteArea";
    public static final String DATA_APROV_GERENTE_CUSTOS = "dataAprovGerenteCustos";
    public static final String DATA_APROV_COORDENADOR = "dataAprovCoordenador";
    public static final String DATA_APROV_RESPONSAVEL_RETIRADA_ESTOQUE = "dataAprovResponsavelRetiradaEstoque";
    public static final String DATA_APROV_COMPLEMENTO_CUSTOS = "dataAprovComplementoCustos";
    public static final String CONFIRMAR_RETIRADA = "confirmarRetirada";
    public static final String QTDE_RECEBIDA = "qtdeRecebida";

    //O id desta view é o numero da linha no resultado da consulta,
    //foi feito isso pois o jpa exige um id em uma @Entity
    //NÃO DEVE SER UTILIZADO E NAO DEVE SER GERADO O CONTRUTOR
    @Id
    @Column(name = "ID")
    private Integer id;

    @JoinColumn(name = "RM_MATERIAL_ID", referencedColumnName = "RM_MATERIAL_ID")
    @ManyToOne
    private RmMaterial rmMaterial;

    @JoinColumn(name = "RM_MATERIAL_STATUS_ID", referencedColumnName = "ID")
    @ManyToOne
    private RmMaterialStatus rmMaterialStatus;

    @Column(name = "DIAS_PREVISTOS")
    private Integer diasPrevistos;

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

    @Column(name = "CONFIRMAR_RETIRADA")
    private Boolean confirmarRetirada;

    @Column(name = "QTDE_RECEBIDA")
    private Double qtdeRecebida;

    public RmMaterial getRmMaterial() {
        return rmMaterial;
    }

    public void setRmMaterial(RmMaterial rmMaterial) {
        this.rmMaterial = rmMaterial;
    }

    public RmMaterialStatus getRmMaterialStatus() {
        return rmMaterialStatus;
    }

    public void setRmMaterialStatus(RmMaterialStatus rmMaterialStatus) {
        this.rmMaterialStatus = rmMaterialStatus;
    }

    public Integer getDiasPrevistos() {
        return diasPrevistos;
    }

    public void setDiasPrevistos(Integer diasPrevistos) {
        this.diasPrevistos = diasPrevistos;
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

    public Boolean getConfirmarRetirada() {
        return confirmarRetirada;
    }

    public void setConfirmarRetirada(Boolean confirmarRetirada) {
        this.confirmarRetirada = confirmarRetirada;
    }

    public Double getQtdeRecebida() {
        return qtdeRecebida;
    }

    public void setQtdeRecebida(Double qtdeRecebida) {
        this.qtdeRecebida = qtdeRecebida;
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.VwRmMaterial[]";
    }
}
