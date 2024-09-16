
package com.sap.document.sap.rfc.functions;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de ZGLS_RMA_REQUISICAO complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteudo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="ZGLS_RMA_REQUISICAO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ITEM" type="{urn:sap-com:document:sap:rfc:functions}numeric5"/>
 *         &lt;element name="DOC_TYPE" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="COD_RMA" type="{urn:sap-com:document:sap:rfc:functions}char50"/>
 *         &lt;element name="MATNR" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="AFNAM" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="WERKS" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="MENGE" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="MEINS" type="{urn:sap-com:document:sap:rfc:functions}unit3"/>
 *         &lt;element name="PS_PSP_PNR" type="{urn:sap-com:document:sap:rfc:functions}char24"/>
 *         &lt;element name="VORNR" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="LGORT" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="EEIND" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="APRO_RMA" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="EKGRP" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="BADAT" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="WGBEZ" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="EBELN" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="PREIS" type="{urn:sap-com:document:sap:rfc:functions}curr11.2"/>
 *         &lt;element name="ZZKOKRS" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="OBSSERVACAO" type="{urn:sap-com:document:sap:rfc:functions}string"/>
 *         &lt;element name="TEXTO_ITEM" type="{urn:sap-com:document:sap:rfc:functions}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZGLS_RMA_REQUISICAO", propOrder = {
    "item",
    "doctype",
    "codrma",
    "matnr",
    "afnam",
    "werks",
    "menge",
    "meins",
    "pspsppnr",
    "vornr",
    "lgort",
    "eeind",
    "aprorma",
    "ekgrp",
    "badat",
    "wgbez",
    "ebeln",
    "preis",
    "zzkokrs",
    "obsservacao",
    "textoitem"
})
public class ZGLSRMAREQUISICAO {

    @XmlElement(name = "ITEM", required = true)
    protected String item;
    @XmlElement(name = "DOC_TYPE", required = true)
    protected String doctype;
    @XmlElement(name = "COD_RMA", required = true)
    protected String codrma;
    @XmlElement(name = "MATNR", required = true)
    protected String matnr;
    @XmlElement(name = "AFNAM", required = true)
    protected String afnam;
    @XmlElement(name = "WERKS", required = true)
    protected String werks;
    @XmlElement(name = "MENGE", required = true)
    protected BigDecimal menge;
    @XmlElement(name = "MEINS", required = true)
    protected String meins;
    @XmlElement(name = "PS_PSP_PNR", required = true)
    protected String pspsppnr;
    @XmlElement(name = "VORNR", required = true)
    protected String vornr;
    @XmlElement(name = "LGORT", required = true)
    protected String lgort;
    @XmlElement(name = "EEIND", required = true)
    protected String eeind;
    @XmlElement(name = "APRO_RMA", required = true)
    protected String aprorma;
    @XmlElement(name = "EKGRP", required = true)
    protected String ekgrp;
    @XmlElement(name = "BADAT", required = true)
    protected String badat;
    @XmlElement(name = "WGBEZ", required = true)
    protected String wgbez;
    @XmlElement(name = "EBELN", required = true)
    protected String ebeln;
    @XmlElement(name = "PREIS", required = true)
    protected BigDecimal preis;
    @XmlElement(name = "ZZKOKRS", required = true)
    protected String zzkokrs;
    @XmlElement(name = "OBSSERVACAO", required = true)
    protected String obsservacao;
    @XmlElement(name = "TEXTO_ITEM", required = true)
    protected String textoitem;

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
     * obtem o valor da propriedade doctype.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDOCTYPE() {
        return doctype;
    }

    /**
     * Define o valor da propriedade doctype.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDOCTYPE(String value) {
        this.doctype = value;
    }

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
     * obtem o valor da propriedade afnam.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAFNAM() {
        return afnam;
    }

    /**
     * Define o valor da propriedade afnam.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAFNAM(String value) {
        this.afnam = value;
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
     * obtem o valor da propriedade aprorma.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPRORMA() {
        return aprorma;
    }

    /**
     * Define o valor da propriedade aprorma.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPRORMA(String value) {
        this.aprorma = value;
    }

    /**
     * obtem o valor da propriedade ekgrp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEKGRP() {
        return ekgrp;
    }

    /**
     * Define o valor da propriedade ekgrp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEKGRP(String value) {
        this.ekgrp = value;
    }

    /**
     * obtem o valor da propriedade badat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBADAT() {
        return badat;
    }

    /**
     * Define o valor da propriedade badat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBADAT(String value) {
        this.badat = value;
    }

    /**
     * obtem o valor da propriedade wgbez.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWGBEZ() {
        return wgbez;
    }

    /**
     * Define o valor da propriedade wgbez.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWGBEZ(String value) {
        this.wgbez = value;
    }

    /**
     * obtem o valor da propriedade ebeln.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEBELN() {
        return ebeln;
    }

    /**
     * Define o valor da propriedade ebeln.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEBELN(String value) {
        this.ebeln = value;
    }

    /**
     * obtem o valor da propriedade preis.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPREIS() {
        return preis;
    }

    /**
     * Define o valor da propriedade preis.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPREIS(BigDecimal value) {
        this.preis = value;
    }

    /**
     * obtem o valor da propriedade zzkokrs.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZZKOKRS() {
        return zzkokrs;
    }

    /**
     * Define o valor da propriedade zzkokrs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZZKOKRS(String value) {
        this.zzkokrs = value;
    }

    /**
     * obtem o valor da propriedade obsservacao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOBSSERVACAO() {
        return obsservacao;
    }

    /**
     * Define o valor da propriedade obsservacao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOBSSERVACAO(String value) {
        this.obsservacao = value;
    }

    /**
     * obtem o valor da propriedade textoitem.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTEXTOITEM() {
        return textoitem;
    }

    /**
     * Define o valor da propriedade textoitem.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTEXTOITEM(String value) {
        this.textoitem = value;
    }

}
