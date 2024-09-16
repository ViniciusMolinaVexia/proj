
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for role complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="role">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.rhweb_ws.nextage.com.br/}roleMaster">
 *       &lt;sequence>
 *         &lt;element name="modulo" type="{http://ws.rhweb_ws.nextage.com.br/}modulo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "role", propOrder = {
    "modulo"
})
public class Role
    extends RoleMaster
{

    protected Modulo modulo;

    /**
     * Gets the value of the modulo property.
     * 
     * @return
     *     possible object is
     *     {@link Modulo }
     *     
     */
    public Modulo getModulo() {
        return modulo;
    }

    /**
     * Sets the value of the modulo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Modulo }
     *     
     */
    public void setModulo(Modulo value) {
        this.modulo = value;
    }

}
