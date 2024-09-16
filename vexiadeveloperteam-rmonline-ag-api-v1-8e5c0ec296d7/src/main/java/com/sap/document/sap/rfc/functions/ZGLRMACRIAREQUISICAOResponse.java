
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
 *         &lt;element name="T_RETURN" type="{urn:sap-com:document:sap:rfc:functions}ZGLS_TAB_RMA_REQ_RETURN"/>
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
    "treturn"
})
@XmlRootElement(name = "ZGL_RMA_CRIA_REQUISICAOResponse")
public class ZGLRMACRIAREQUISICAOResponse {

    @XmlElement(name = "T_RETURN", required = true)
    protected ZGLSTABRMAREQRETURN treturn;

    /**
     * obtem o valor da propriedade treturn.
     * 
     * @return
     *     possible object is
     *     {@link ZGLSTABRMAREQRETURN }
     *     
     */
    public ZGLSTABRMAREQRETURN getTRETURN() {
        return treturn;
    }

    /**
     * Define o valor da propriedade treturn.
     * 
     * @param value
     *     allowed object is
     *     {@link ZGLSTABRMAREQRETURN }
     *     
     */
    public void setTRETURN(ZGLSTABRMAREQRETURN value) {
        this.treturn = value;
    }

}
