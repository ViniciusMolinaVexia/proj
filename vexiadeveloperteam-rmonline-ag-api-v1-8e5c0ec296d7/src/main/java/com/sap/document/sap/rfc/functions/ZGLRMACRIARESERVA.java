
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
 *         &lt;element name="T_ITENS" type="{urn:sap-com:document:sap:rfc:functions}ZGLS_TAB_RMA_RESERVA"/>
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
    "titens"
})
@XmlRootElement(name = "ZGL_RMA_CRIA_RESERVA")
public class ZGLRMACRIARESERVA {

    @XmlElement(name = "T_ITENS", required = true)
    protected ZGLSTABRMARESERVA titens;

    /**
     * obtem o valor da propriedade titens.
     * 
     * @return
     *     possible object is
     *     {@link ZGLSTABRMARESERVA }
     *     
     */
    public ZGLSTABRMARESERVA getTITENS() {
        return titens;
    }

    /**
     * Define o valor da propriedade titens.
     * 
     * @param value
     *     allowed object is
     *     {@link ZGLSTABRMARESERVA }
     *     
     */
    public void setTITENS(ZGLSTABRMARESERVA value) {
        this.titens = value;
    }

}
