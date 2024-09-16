
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eapMultiEmpresa complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="eapMultiEmpresa">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.rhweb_ws.nextage.com.br/}eapMultiEmpresaMaster">
 *       &lt;sequence>
 *         &lt;element name="multiEmpresa" type="{http://ws.rhweb_ws.nextage.com.br/}multiEmpresa" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eapMultiEmpresa", propOrder = {
    "multiEmpresa"
})
public class EapMultiEmpresa
    extends EapMultiEmpresaMaster
{

    protected MultiEmpresa multiEmpresa;

    /**
     * Gets the value of the multiEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link MultiEmpresa }
     *     
     */
    public MultiEmpresa getMultiEmpresa() {
        return multiEmpresa;
    }

    /**
     * Sets the value of the multiEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultiEmpresa }
     *     
     */
    public void setMultiEmpresa(MultiEmpresa value) {
        this.multiEmpresa = value;
    }

}
