
package com.sap.document.sap.rfc.functions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de ZGLS_RMA_RES_RETURN complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteudo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="ZGLS_RMA_RES_RETURN">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="COD_RMA" type="{urn:sap-com:document:sap:rfc:functions}char50"/>
 *         &lt;element name="RESERVA" type="{urn:sap-com:document:sap:rfc:functions}numeric10"/>
 *         &lt;element name="ITEM" type="{urn:sap-com:document:sap:rfc:functions}numeric5"/>
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
@XmlType(name = "ZGLS_RMA_RES_RETURN", propOrder = {
    "codrma",
    "reserva",
    "item",
    "tpmsg",
    "message"
})
public class ZGLSRMARESRETURN {

    @XmlElement(name = "COD_RMA", required = true)
    protected String codrma;
    @XmlElement(name = "RESERVA", required = true)
    protected String reserva;
    @XmlElement(name = "ITEM", required = true)
    protected String item;
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
     * obtem o valor da propriedade reserva.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRESERVA() {
        return reserva;
    }

    /**
     * Define o valor da propriedade reserva.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRESERVA(String value) {
        this.reserva = value;
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
