
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for formacao complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="formacao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cdFormacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dsFormacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "formacao", propOrder = {
    "cdFormacao",
    "dsFormacao",
    "id"
})
public class Formacao {

    protected String cdFormacao;
    protected String dsFormacao;
    protected Integer id;

    /**
     * Gets the value of the cdFormacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdFormacao() {
        return cdFormacao;
    }

    /**
     * Sets the value of the cdFormacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdFormacao(String value) {
        this.cdFormacao = value;
    }

    /**
     * Gets the value of the dsFormacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsFormacao() {
        return dsFormacao;
    }

    /**
     * Sets the value of the dsFormacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsFormacao(String value) {
        this.dsFormacao = value;
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
