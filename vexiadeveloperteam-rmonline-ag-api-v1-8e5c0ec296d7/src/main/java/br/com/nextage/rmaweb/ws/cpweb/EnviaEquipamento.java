
package br.com.nextage.rmaweb.ws.cpweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for enviaEquipamento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="enviaEquipamento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sincEquipamentoVo" type="{http://ws.cpweb.nextage.com.br/}sincEquipamentoVo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "enviaEquipamento", propOrder = {
    "sincEquipamentoVo"
})
public class EnviaEquipamento {

    protected SincEquipamentoVo sincEquipamentoVo;

    /**
     * Gets the value of the sincEquipamentoVo property.
     * 
     * @return
     *     possible object is
     *     {@link SincEquipamentoVo }
     *     
     */
    public SincEquipamentoVo getSincEquipamentoVo() {
        return sincEquipamentoVo;
    }

    /**
     * Sets the value of the sincEquipamentoVo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SincEquipamentoVo }
     *     
     */
    public void setSincEquipamentoVo(SincEquipamentoVo value) {
        this.sincEquipamentoVo = value;
    }

}
