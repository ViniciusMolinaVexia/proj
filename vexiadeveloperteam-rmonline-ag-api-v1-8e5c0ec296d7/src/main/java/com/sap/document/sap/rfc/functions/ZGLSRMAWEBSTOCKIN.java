
package com.sap.document.sap.rfc.functions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de ZGLS_RMAWEB_STOCK_IN complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteudo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="ZGLS_RMAWEB_STOCK_IN">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CENTRO" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="T_MATERIAL" type="{urn:sap-com:document:sap:rfc:functions}ZGLS_RMAWEB_TAB_MATERIAL"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZGLS_RMAWEB_STOCK_IN", propOrder = {
    "centro",
    "tmaterial"
})
public class ZGLSRMAWEBSTOCKIN {

    @XmlElement(name = "CENTRO", required = true)
    protected String centro;
    @XmlElement(name = "T_MATERIAL", required = true)
    protected ZGLSRMAWEBTABMATERIAL tmaterial;

    /**
     * obtem o valor da propriedade centro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCENTRO() {
        return centro;
    }

    /**
     * Define o valor da propriedade centro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCENTRO(String value) {
        this.centro = value;
    }

    /**
     * obtem o valor da propriedade tmaterial.
     * 
     * @return
     *     possible object is
     *     {@link ZGLSRMAWEBTABMATERIAL }
     *     
     */
    public ZGLSRMAWEBTABMATERIAL getTMATERIAL() {
        return tmaterial;
    }

    /**
     * Define o valor da propriedade tmaterial.
     * 
     * @param value
     *     allowed object is
     *     {@link ZGLSRMAWEBTABMATERIAL }
     *     
     */
    public void setTMATERIAL(ZGLSRMAWEBTABMATERIAL value) {
        this.tmaterial = value;
    }

}
