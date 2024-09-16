
package com.sap.document.sap.rfc.functions;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de ZGLS_RMA_RESERVA complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteudo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="ZGLS_RMA_RESERVA">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="COD_RMA" type="{urn:sap-com:document:sap:rfc:functions}char50"/>
 *         &lt;element name="ITEM" type="{urn:sap-com:document:sap:rfc:functions}numeric5"/>
 *         &lt;element name="DT_RESERVA" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="WERKS" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="SOLICITANTE" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="APROVADOR" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="MATNR" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="MENGE" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="MEINS" type="{urn:sap-com:document:sap:rfc:functions}unit3"/>
 *         &lt;element name="LGORT" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="EEIND" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="COLETOR_CUSTO" type="{urn:sap-com:document:sap:rfc:functions}char24"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZGLS_RMA_RESERVA", propOrder = {
    "codrma",
    "item",
    "dtreserva",
    "werks",
    "solicitante",
    "aprovador",
    "matnr",
    "menge",
    "meins",
    "lgort",
    "eeind",
    "coletorcusto"
})
public class ZGLSRMARESERVA {

    @XmlElement(name = "COD_RMA", required = true)
    protected String codrma;
    @XmlElement(name = "ITEM", required = true)
    protected String item;
    @XmlElement(name = "DT_RESERVA", required = true)
    protected String dtreserva;
    @XmlElement(name = "WERKS", required = true)
    protected String werks;
    @XmlElement(name = "SOLICITANTE", required = true)
    protected String solicitante;
    @XmlElement(name = "APROVADOR", required = true)
    protected String aprovador;
    @XmlElement(name = "MATNR", required = true)
    protected String matnr;
    @XmlElement(name = "MENGE", required = true)
    protected BigDecimal menge;
    @XmlElement(name = "MEINS", required = true)
    protected String meins;
    @XmlElement(name = "LGORT", required = true)
    protected String lgort;
    @XmlElement(name = "EEIND", required = true)
    protected String eeind;
    @XmlElement(name = "COLETOR_CUSTO", required = true)
    protected String coletorcusto;

    /**
     * obtem o valor da propriedade codrma.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCODRMA() {
        return codrma;
    }

    /**
     * Define o valor da propriedade codrma.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCODRMA(String value) {
        this.codrma = value;
    }

    /**
     * obtem o valor da propriedade item.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getITEM() {
        return item;
    }

    /**
     * Define o valor da propriedade item.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setITEM(String value) {
        this.item = value;
    }

    /**
     * obtem o valor da propriedade dtreserva.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDTRESERVA() {
        return dtreserva;
    }

    /**
     * Define o valor da propriedade dtreserva.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDTRESERVA(String value) {
        this.dtreserva = value;
    }

    /**
     * obtem o valor da propriedade werks.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWERKS() {
        return werks;
    }

    /**
     * Define o valor da propriedade werks.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWERKS(String value) {
        this.werks = value;
    }

    /**
     * obtem o valor da propriedade solicitante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSOLICITANTE() {
        return solicitante;
    }

    /**
     * Define o valor da propriedade solicitante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSOLICITANTE(String value) {
        this.solicitante = value;
    }

    /**
     * obtem o valor da propriedade aprovador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPROVADOR() {
        return aprovador;
    }

    /**
     * Define o valor da propriedade aprovador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPROVADOR(String value) {
        this.aprovador = value;
    }

    /**
     * obtem o valor da propriedade matnr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMATNR() {
        return matnr;
    }

    /**
     * Define o valor da propriedade matnr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMATNR(String value) {
        this.matnr = value;
    }

    /**
     * obtem o valor da propriedade menge.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMENGE() {
        return menge;
    }

    /**
     * Define o valor da propriedade menge.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMENGE(BigDecimal value) {
        this.menge = value;
    }

    /**
     * obtem o valor da propriedade meins.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMEINS() {
        return meins;
    }

    /**
     * Define o valor da propriedade meins.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMEINS(String value) {
        this.meins = value;
    }

    /**
     * obtem o valor da propriedade lgort.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLGORT() {
        return lgort;
    }

    /**
     * Define o valor da propriedade lgort.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLGORT(String value) {
        this.lgort = value;
    }

    /**
     * obtem o valor da propriedade eeind.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEEIND() {
        return eeind;
    }

    /**
     * Define o valor da propriedade eeind.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEEIND(String value) {
        this.eeind = value;
    }

    /**
     * obtem o valor da propriedade coletorcusto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOLETORCUSTO() {
        return coletorcusto;
    }

    /**
     * Define o valor da propriedade coletorcusto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOLETORCUSTO(String value) {
        this.coletorcusto = value;
    }

}
