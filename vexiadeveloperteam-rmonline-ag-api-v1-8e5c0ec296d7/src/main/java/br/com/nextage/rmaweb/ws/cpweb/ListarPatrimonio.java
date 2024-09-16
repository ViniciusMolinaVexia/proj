
package br.com.nextage.rmaweb.ws.cpweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listarPatrimonio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listarPatrimonio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="prefixoEquipamentoVo" type="{http://ws.cpweb.nextage.com.br/}prefixoEquipamentoVo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listarPatrimonio", propOrder = {
    "prefixoEquipamentoVo"
})
public class ListarPatrimonio {

    protected PrefixoEquipamentoVo prefixoEquipamentoVo;

    /**
     * Gets the value of the prefixoEquipamentoVo property.
     * 
     * @return
     *     possible object is
     *     {@link PrefixoEquipamentoVo }
     *     
     */
    public PrefixoEquipamentoVo getPrefixoEquipamentoVo() {
        return prefixoEquipamentoVo;
    }

    /**
     * Sets the value of the prefixoEquipamentoVo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrefixoEquipamentoVo }
     *     
     */
    public void setPrefixoEquipamentoVo(PrefixoEquipamentoVo value) {
        this.prefixoEquipamentoVo = value;
    }

}
