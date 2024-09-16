
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sindicato complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sindicato">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cdChaveSindicato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cdPostalSindicato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="localSindicato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nmSindicato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sindicato", propOrder = {
    "cdChaveSindicato",
    "cdPostalSindicato",
    "id",
    "localSindicato",
    "nmSindicato"
})
public class Sindicato {

    protected String cdChaveSindicato;
    protected String cdPostalSindicato;
    protected Integer id;
    protected String localSindicato;
    protected String nmSindicato;

    /**
     * Gets the value of the cdChaveSindicato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdChaveSindicato() {
        return cdChaveSindicato;
    }

    /**
     * Sets the value of the cdChaveSindicato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdChaveSindicato(String value) {
        this.cdChaveSindicato = value;
    }

    /**
     * Gets the value of the cdPostalSindicato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdPostalSindicato() {
        return cdPostalSindicato;
    }

    /**
     * Sets the value of the cdPostalSindicato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdPostalSindicato(String value) {
        this.cdPostalSindicato = value;
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
     * Gets the value of the localSindicato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalSindicato() {
        return localSindicato;
    }

    /**
     * Sets the value of the localSindicato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalSindicato(String value) {
        this.localSindicato = value;
    }

    /**
     * Gets the value of the nmSindicato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNmSindicato() {
        return nmSindicato;
    }

    /**
     * Sets the value of the nmSindicato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNmSindicato(String value) {
        this.nmSindicato = value;
    }

}
