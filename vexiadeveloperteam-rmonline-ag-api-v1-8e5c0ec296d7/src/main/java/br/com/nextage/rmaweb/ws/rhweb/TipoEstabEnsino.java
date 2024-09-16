
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tipoEstabEnsino complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tipoEstabEnsino">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dsTipoEstabEnsino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tpEstabEnsino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tipoEstabEnsino", propOrder = {
    "dsTipoEstabEnsino",
    "id",
    "tpEstabEnsino"
})
public class TipoEstabEnsino {

    protected String dsTipoEstabEnsino;
    protected Integer id;
    protected String tpEstabEnsino;

    /**
     * Gets the value of the dsTipoEstabEnsino property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsTipoEstabEnsino() {
        return dsTipoEstabEnsino;
    }

    /**
     * Sets the value of the dsTipoEstabEnsino property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsTipoEstabEnsino(String value) {
        this.dsTipoEstabEnsino = value;
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

    /**
     * Gets the value of the tpEstabEnsino property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpEstabEnsino() {
        return tpEstabEnsino;
    }

    /**
     * Sets the value of the tpEstabEnsino property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpEstabEnsino(String value) {
        this.tpEstabEnsino = value;
    }

}
