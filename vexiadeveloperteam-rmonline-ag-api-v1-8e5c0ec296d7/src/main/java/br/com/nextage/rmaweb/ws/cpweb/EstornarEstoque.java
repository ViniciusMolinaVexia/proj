
package br.com.nextage.rmaweb.ws.cpweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for estornarEstoque complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="estornarEstoque">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="filtro" type="{http://ws.cpweb.nextage.com.br/}sincEquipamentoVo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "estornarEstoque", propOrder = {
    "filtro"
})
public class EstornarEstoque {

    protected SincEquipamentoVo filtro;

    /**
     * Gets the value of the filtro property.
     * 
     * @return
     *     possible object is
     *     {@link SincEquipamentoVo }
     *     
     */
    public SincEquipamentoVo getFiltro() {
        return filtro;
    }

    /**
     * Sets the value of the filtro property.
     * 
     * @param value
     *     allowed object is
     *     {@link SincEquipamentoVo }
     *     
     */
    public void setFiltro(SincEquipamentoVo value) {
        this.filtro = value;
    }

}
