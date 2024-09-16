
package com.sap.document.sap.rfc.functions;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de ZGLS_RMA_SAIDA_MERCADORIA complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteudo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="ZGLS_RMA_SAIDA_MERCADORIA">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WERKS" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="MATNR" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="LGORT" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="PS_PSP_PNR" type="{urn:sap-com:document:sap:rfc:functions}char24"/>
 *         &lt;element name="NPLNR" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="VORNR" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="MENGE" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="MEINS" type="{urn:sap-com:document:sap:rfc:functions}unit3"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZGLS_RMA_SAIDA_MERCADORIA", propOrder = {
    "werks",
    "matnr",
    "lgort",
    "pspsppnr",
    "nplnr",
    "vornr",
    "menge",
    "meins"
})
public class ZGLSRMASAIDAMERCADORIA {

    @XmlElement(name = "WERKS", required = true)
    protected String werks;
    @XmlElement(name = "MATNR", required = true)
    protected String matnr;
    @XmlElement(name = "LGORT", required = true)
    protected String lgort;
    @XmlElement(name = "PS_PSP_PNR", required = true)
    protected String pspsppnr;
    @XmlElement(name = "NPLNR", required = true)
    protected String nplnr;
    @XmlElement(name = "VORNR", required = true)
    protected String vornr;
    @XmlElement(name = "MENGE", required = true)
    protected BigDecimal menge;
    @XmlElement(name = "MEINS", required = true)
    protected String meins;

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
     * obtem o valor da propriedade pspsppnr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPSPSPPNR() {
        return pspsppnr;
    }

    /**
     * Define o valor da propriedade pspsppnr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPSPSPPNR(String value) {
        this.pspsppnr = value;
    }

    /**
     * obtem o valor da propriedade nplnr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNPLNR() {
        return nplnr;
    }

    /**
     * Define o valor da propriedade nplnr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNPLNR(String value) {
        this.nplnr = value;
    }

    /**
     * obtem o valor da propriedade vornr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVORNR() {
        return vornr;
    }

    /**
     * Define o valor da propriedade vornr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVORNR(String value) {
        this.vornr = value;
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

}
