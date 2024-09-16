
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for categoriaTrabalhador complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="categoriaTrabalhador">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cdCategoriaTrabalhador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dsCategoriaTrabalhador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "categoriaTrabalhador", propOrder = {
    "cdCategoriaTrabalhador",
    "dsCategoriaTrabalhador",
    "id"
})
public class CategoriaTrabalhador {

    protected String cdCategoriaTrabalhador;
    protected String dsCategoriaTrabalhador;
    protected Integer id;

    /**
     * Gets the value of the cdCategoriaTrabalhador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdCategoriaTrabalhador() {
        return cdCategoriaTrabalhador;
    }

    /**
     * Sets the value of the cdCategoriaTrabalhador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdCategoriaTrabalhador(String value) {
        this.cdCategoriaTrabalhador = value;
    }

    /**
     * Gets the value of the dsCategoriaTrabalhador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsCategoriaTrabalhador() {
        return dsCategoriaTrabalhador;
    }

    /**
     * Sets the value of the dsCategoriaTrabalhador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsCategoriaTrabalhador(String value) {
        this.dsCategoriaTrabalhador = value;
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
