
package br.com.nextage.rmaweb.ws.cpweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for finalizarRmResponse complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="finalizarRmResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://ws.rmaweb.nextage.com.br/}info" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "finalizarRmResponse", propOrder = {
        "_return"
})
public class FinalizarRmResponse {

    @XmlElement(name = "return")
    protected Info _return;

    /**
     * Gets the value of the return property.
     *
     * @return possible object is
     * {@link Info }
     */
    public Info getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     *
     * @param value allowed object is
     *              {@link Info }
     */
    public void setReturn(Info value) {
        this._return = value;
    }

}
