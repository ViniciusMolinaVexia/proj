
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for configImpPessLogica complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="configImpPessLogica">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="campoLogica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chavesLogica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="configImpPess" type="{http://ws.rhweb_ws.nextage.com.br/}configImpPess" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="propLogica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valorLogica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configImpPessLogica", propOrder = {
    "campoLogica",
    "chavesLogica",
    "configImpPess",
    "id",
    "propLogica",
    "valorLogica"
})
public class ConfigImpPessLogica {

    protected String campoLogica;
    protected String chavesLogica;
    protected ConfigImpPess configImpPess;
    protected Integer id;
    protected String propLogica;
    protected String valorLogica;

    /**
     * Gets the value of the campoLogica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCampoLogica() {
        return campoLogica;
    }

    /**
     * Sets the value of the campoLogica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCampoLogica(String value) {
        this.campoLogica = value;
    }

    /**
     * Gets the value of the chavesLogica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChavesLogica() {
        return chavesLogica;
    }

    /**
     * Sets the value of the chavesLogica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChavesLogica(String value) {
        this.chavesLogica = value;
    }

    /**
     * Gets the value of the configImpPess property.
     * 
     * @return
     *     possible object is
     *     {@link ConfigImpPess }
     *     
     */
    public ConfigImpPess getConfigImpPess() {
        return configImpPess;
    }

    /**
     * Sets the value of the configImpPess property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfigImpPess }
     *     
     */
    public void setConfigImpPess(ConfigImpPess value) {
        this.configImpPess = value;
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
     * Gets the value of the propLogica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropLogica() {
        return propLogica;
    }

    /**
     * Sets the value of the propLogica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropLogica(String value) {
        this.propLogica = value;
    }

    /**
     * Gets the value of the valorLogica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValorLogica() {
        return valorLogica;
    }

    /**
     * Sets the value of the valorLogica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValorLogica(String value) {
        this.valorLogica = value;
    }

}
