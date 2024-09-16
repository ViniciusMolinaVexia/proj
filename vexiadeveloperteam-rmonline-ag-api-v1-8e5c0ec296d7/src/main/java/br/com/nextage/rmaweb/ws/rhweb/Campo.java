
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for campo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="campo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.rhweb_ws.nextage.com.br/}campoMaster">
 *       &lt;sequence>
 *         &lt;element name="tipoCampo" type="{http://ws.rhweb_ws.nextage.com.br/}tipoCampo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "campo", propOrder = {
    "tipoCampo"
})
public class Campo
    extends CampoMaster
{

    protected TipoCampo tipoCampo;

    /**
     * Gets the value of the tipoCampo property.
     * 
     * @return
     *     possible object is
     *     {@link TipoCampo }
     *     
     */
    public TipoCampo getTipoCampo() {
        return tipoCampo;
    }

    /**
     * Sets the value of the tipoCampo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoCampo }
     *     
     */
    public void setTipoCampo(TipoCampo value) {
        this.tipoCampo = value;
    }

}
