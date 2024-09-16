
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for formaPagamento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="formaPagamento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cdChavePais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dsFormaPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formaPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "formaPagamento", propOrder = {
    "cdChavePais",
    "dsFormaPagamento",
    "formaPagamento",
    "id"
})
public class FormaPagamento {

    protected String cdChavePais;
    protected String dsFormaPagamento;
    protected String formaPagamento;
    protected Integer id;

    /**
     * Gets the value of the cdChavePais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdChavePais() {
        return cdChavePais;
    }

    /**
     * Sets the value of the cdChavePais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdChavePais(String value) {
        this.cdChavePais = value;
    }

    /**
     * Gets the value of the dsFormaPagamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsFormaPagamento() {
        return dsFormaPagamento;
    }

    /**
     * Sets the value of the dsFormaPagamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsFormaPagamento(String value) {
        this.dsFormaPagamento = value;
    }

    /**
     * Gets the value of the formaPagamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormaPagamento() {
        return formaPagamento;
    }

    /**
     * Sets the value of the formaPagamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormaPagamento(String value) {
        this.formaPagamento = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

}
