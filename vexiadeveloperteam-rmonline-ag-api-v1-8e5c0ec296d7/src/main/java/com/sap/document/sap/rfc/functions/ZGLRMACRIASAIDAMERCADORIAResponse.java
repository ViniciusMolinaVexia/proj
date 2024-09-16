
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
 *         &lt;element name="GS_RETURN" type="{urn:sap-com:document:sap:rfc:functions}ZGLS_RMA_MERC_RETURN"/>
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
    "gsreturn"
})
@XmlRootElement(name = "ZGL_RMA_CRIA_SAIDA_MERCADORIAResponse")
public class ZGLRMACRIASAIDAMERCADORIAResponse {

    @XmlElement(name = "GS_RETURN", required = true)
    protected ZGLSRMAMERCRETURN gsreturn;

    /**
     * obtem o valor da propriedade gsreturn.
     * 
     * @return
     *     possible object is
     *     {@link ZGLSRMAMERCRETURN }
     *     
     */
    public ZGLSRMAMERCRETURN getGSRETURN() {
        return gsreturn;
    }

    /**
     * Define o valor da propriedade gsreturn.
     * 
     * @param value
     *     allowed object is
     *     {@link ZGLSRMAMERCRETURN }
     *     
     */
    public void setGSRETURN(ZGLSRMAMERCRETURN value) {
        this.gsreturn = value;
    }

}
