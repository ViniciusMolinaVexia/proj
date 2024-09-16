
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
 *         &lt;element name="OUTPUT" type="{urn:sap-com:document:sap:rfc:functions}ZGLS_RMAWEB_STOCK_OUT"/>
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
    "output"
})
@XmlRootElement(name = "ZGL_RMA_BUSCA_ESTOQUEResponse")
public class ZGLRMABUSCAESTOQUEResponse {

    @XmlElement(name = "OUTPUT", required = true)
    protected ZGLSRMAWEBSTOCKOUT output;

    /**
     * obtem o valor da propriedade output.
     * 
     * @return
     *     possible object is
     *     {@link ZGLSRMAWEBSTOCKOUT }
     *     
     */
    public ZGLSRMAWEBSTOCKOUT getOUTPUT() {
        return output;
    }

    /**
     * Define o valor da propriedade output.
     * 
     * @param value
     *     allowed object is
     *     {@link ZGLSRMAWEBSTOCKOUT }
     *     
     */
    public void setOUTPUT(ZGLSRMAWEBSTOCKOUT value) {
        this.output = value;
    }

}
