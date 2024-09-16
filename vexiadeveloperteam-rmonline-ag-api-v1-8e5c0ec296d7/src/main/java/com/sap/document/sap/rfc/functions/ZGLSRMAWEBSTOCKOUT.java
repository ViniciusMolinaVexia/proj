
package com.sap.document.sap.rfc.functions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de ZGLS_RMAWEB_STOCK_OUT complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteudo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="ZGLS_RMAWEB_STOCK_OUT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="T_ESTOQUE" type="{urn:sap-com:document:sap:rfc:functions}ZGLS_RMAWEB_TAB_MATERIAL_STOCK"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZGLS_RMAWEB_STOCK_OUT", propOrder = {
    "testoque"
})
public class ZGLSRMAWEBSTOCKOUT {

    @XmlElement(name = "T_ESTOQUE", required = true)
    protected ZGLSRMAWEBTABMATERIALSTOCK testoque;

    /**
     * obtem o valor da propriedade testoque.
     * 
     * @return
     *     possible object is
     *     {@link ZGLSRMAWEBTABMATERIALSTOCK }
     *     
     */
    public ZGLSRMAWEBTABMATERIALSTOCK getTESTOQUE() {
        return testoque;
    }

    /**
     * Define o valor da propriedade testoque.
     * 
     * @param value
     *     allowed object is
     *     {@link ZGLSRMAWEBTABMATERIALSTOCK }
     *     
     */
    public void setTESTOQUE(ZGLSRMAWEBTABMATERIALSTOCK value) {
        this.testoque = value;
    }

}
