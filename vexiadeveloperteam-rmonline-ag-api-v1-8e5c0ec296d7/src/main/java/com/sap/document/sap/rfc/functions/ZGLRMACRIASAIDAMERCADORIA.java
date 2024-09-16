
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
 *         &lt;element name="GS_SAID_MERC" type="{urn:sap-com:document:sap:rfc:functions}ZGLS_RMA_SAIDA_MERCADORIA"/>
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
    "gssaidmerc"
})
@XmlRootElement(name = "ZGL_RMA_CRIA_SAIDA_MERCADORIA")
public class ZGLRMACRIASAIDAMERCADORIA {

    @XmlElement(name = "GS_SAID_MERC", required = true)
    protected ZGLSRMASAIDAMERCADORIA gssaidmerc;

    /**
     * obtem o valor da propriedade gssaidmerc.
     * 
     * @return
     *     possible object is
     *     {@link ZGLSRMASAIDAMERCADORIA }
     *     
     */
    public ZGLSRMASAIDAMERCADORIA getGSSAIDMERC() {
        return gssaidmerc;
    }

    /**
     * Define o valor da propriedade gssaidmerc.
     * 
     * @param value
     *     allowed object is
     *     {@link ZGLSRMASAIDAMERCADORIA }
     *     
     */
    public void setGSSAIDMERC(ZGLSRMASAIDAMERCADORIA value) {
        this.gssaidmerc = value;
    }

}
