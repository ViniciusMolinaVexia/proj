
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pep complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pep">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cdDivisao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cdPep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dsPep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nmEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pep", propOrder = {
    "cdDivisao",
    "cdPep",
    "dsPep",
    "id",
    "nmEmpresa"
})
public class Pep {

    protected String cdDivisao;
    protected String cdPep;
    protected String dsPep;
    protected Integer id;
    protected String nmEmpresa;

    /**
     * Gets the value of the cdDivisao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdDivisao() {
        return cdDivisao;
    }

    /**
     * Sets the value of the cdDivisao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdDivisao(String value) {
        this.cdDivisao = value;
    }

    /**
     * Gets the value of the cdPep property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdPep() {
        return cdPep;
    }

    /**
     * Sets the value of the cdPep property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdPep(String value) {
        this.cdPep = value;
    }

    /**
     * Gets the value of the dsPep property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsPep() {
        return dsPep;
    }

    /**
     * Sets the value of the dsPep property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsPep(String value) {
        this.dsPep = value;
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
     * Gets the value of the nmEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNmEmpresa() {
        return nmEmpresa;
    }

    /**
     * Sets the value of the nmEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNmEmpresa(String value) {
        this.nmEmpresa = value;
    }

}
