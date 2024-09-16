
package br.com.nextage.rmaweb.ws.cpweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for transferir complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transferir">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sincTransferenciaVo" type="{http://ws.cpweb.nextage.com.br/}sincTransferenciaVo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transferir", propOrder = {
    "sincTransferenciaVo"
})
public class Transferir {

    protected SincTransferenciaVo sincTransferenciaVo;

    /**
     * Gets the value of the sincTransferenciaVo property.
     * 
     * @return
     *     possible object is
     *     {@link SincTransferenciaVo }
     *     
     */
    public SincTransferenciaVo getSincTransferenciaVo() {
        return sincTransferenciaVo;
    }

    /**
     * Sets the value of the sincTransferenciaVo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SincTransferenciaVo }
     *     
     */
    public void setSincTransferenciaVo(SincTransferenciaVo value) {
        this.sincTransferenciaVo = value;
    }

}
