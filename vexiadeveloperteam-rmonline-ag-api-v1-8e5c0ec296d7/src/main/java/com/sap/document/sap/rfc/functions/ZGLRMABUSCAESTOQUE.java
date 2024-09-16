
package com.sap.document.sap.rfc.functions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteudo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="INPUT" type="{urn:sap-com:document:sap:rfc:functions}ZGLS_RMAWEB_STOCK_IN"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "input"
})
@XmlRootElement(name = "ZGL_RMA_BUSCA_ESTOQUE")
public class ZGLRMABUSCAESTOQUE {

    @XmlElement(name = "INPUT", required = true)
    protected ZGLSRMAWEBSTOCKIN input;

    /**
     * obtem o valor da propriedade input.
     * 
     * @return
     *     possible object is
     *     {@link ZGLSRMAWEBSTOCKIN }
     *     
     */
    public ZGLSRMAWEBSTOCKIN getINPUT() {
        return input;
    }

    /**
     * Define o valor da propriedade input.
     * 
     * @param value
     *     allowed object is
     *     {@link ZGLSRMAWEBSTOCKIN }
     *     
     */
    public void setINPUT(ZGLSRMAWEBSTOCKIN value) {
        this.input = value;
    }

}
