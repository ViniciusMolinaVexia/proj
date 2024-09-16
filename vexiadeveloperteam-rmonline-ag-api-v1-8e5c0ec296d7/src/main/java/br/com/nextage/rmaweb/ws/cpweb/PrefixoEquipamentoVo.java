
package br.com.nextage.rmaweb.ws.cpweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prefixoEquipamentoVo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prefixoEquipamentoVo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigoDeposito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoEquipamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eapDeposito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prefixoEquipamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prefixoEquipamentoVo", propOrder = {
    "codigoDeposito",
    "codigoEquipamento",
        "eapDeposito",
    "prefixoEquipamento"
})
public class PrefixoEquipamentoVo {

    protected String codigoDeposito;
    protected String codigoEquipamento;
    protected String eapDeposito;
    protected String prefixoEquipamento;

    /**
     * Gets the value of the codigoDeposito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoDeposito() {
        return codigoDeposito;
    }

    /**
     * Sets the value of the codigoDeposito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoDeposito(String value) {
        this.codigoDeposito = value;
    }

    /**
     * Gets the value of the codigoEquipamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoEquipamento() {
        return codigoEquipamento;
    }

    /**
     * Sets the value of the codigoEquipamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoEquipamento(String value) {
        this.codigoEquipamento = value;
    }

    /**
     * Gets the value of the eapDeposito property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getEapDeposito() {
        return eapDeposito;
    }

    /**
     * Sets the value of the eapDeposito property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setEapDeposito(String value) {
        this.eapDeposito = value;
    }

    /**
     * Gets the value of the prefixoEquipamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrefixoEquipamento() {
        return prefixoEquipamento;
    }

    /**
     * Sets the value of the prefixoEquipamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrefixoEquipamento(String value) {
        this.prefixoEquipamento = value;
    }

}
