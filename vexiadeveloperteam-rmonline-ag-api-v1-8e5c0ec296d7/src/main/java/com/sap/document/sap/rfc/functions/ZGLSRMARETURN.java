
package com.sap.document.sap.rfc.functions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZGLS_RMA_RETURN complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZGLS_RMA_RETURN">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TP_MSG" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="MESSAGE" type="{urn:sap-com:document:sap:rfc:functions}char100"/>
 *         &lt;element name="CODIGO" type="{urn:sap-com:document:sap:rfc:functions}char100"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZGLS_RMA_RETURN", propOrder = {
    "tpmsg",
    "message",
    "codigo"
})
public class ZGLSRMARETURN {

    @XmlElement(name = "TP_MSG", required = true)
    protected String tpmsg;
    @XmlElement(name = "MESSAGE", required = true)
    protected String message;
    @XmlElement(name = "CODIGO", required = true)
    protected String codigo;

    /**
     * Gets the value of the tpmsg property.
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
     * Sets the value of the tpmsg property.
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
     * Gets the value of the message property.
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
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMESSAGE(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the codigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCODIGO() {
        return codigo;
    }

    /**
     * Sets the value of the codigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCODIGO(String value) {
        this.codigo = value;
    }

}
