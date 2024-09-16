
package com.sap.document.sap.rfc.functions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de ZGLS_RMA_REQ_RETURN complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteudo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="ZGLS_RMA_REQ_RETURN">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="COD_RMA" type="{urn:sap-com:document:sap:rfc:functions}char50"/>
 *         &lt;element name="REQUISICAO" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="ITEM" type="{urn:sap-com:document:sap:rfc:functions}numeric5"/>
 *         &lt;element name="MATNR" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="TP_MSG" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="MESSAGE" type="{urn:sap-com:document:sap:rfc:functions}char100"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZGLS_RMA_REQ_RETURN", propOrder = {
    "codrma",
    "requisicao",
    "item",
    "matnr",
    "tpmsg",
    "message"
})
public class ZGLSRMAREQRETURN {

    @XmlElement(name = "COD_RMA", required = true)
    protected String codrma;
    @XmlElement(name = "REQUISICAO", required = true)
    protected String requisicao;
    @XmlElement(name = "ITEM", required = true)
    protected String item;
    @XmlElement(name = "MATNR", required = true)
    protected String matnr;
    @XmlElement(name = "TP_MSG", required = true)
    protected String tpmsg;
    @XmlElement(name = "MESSAGE", required = true)
    protected String message;

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
     * obtem o valor da propriedade requisicao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREQUISICAO() {
        return requisicao;
    }

    /**
     * Define o valor da propriedade requisicao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREQUISICAO(String value) {
        this.requisicao = value;
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
     * obtem o valor da propriedade tpmsg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTPMSG() {
        return tpmsg;
    }

    /**
     * Define o valor da propriedade tpmsg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTPMSG(String value) {
        this.tpmsg = value;
    }

    /**
     * obtem o valor da propriedade message.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMESSAGE() {
        return message;
    }

    /**
     * Define o valor da propriedade message.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMESSAGE(String value) {
        this.message = value;
    }

}
