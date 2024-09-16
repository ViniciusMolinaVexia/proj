
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for unidadeOrganizacional complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="unidadeOrganizacional">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="areaRh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cdUnidadeOrganizacional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dsDenominacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="sgUnidadeOrganizacional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "unidadeOrganizacional", propOrder = {
    "areaRh",
    "cdUnidadeOrganizacional",
    "dsDenominacao",
    "id",
    "sgUnidadeOrganizacional"
})
public class UnidadeOrganizacional {

    protected String areaRh;
    protected String cdUnidadeOrganizacional;
    protected String dsDenominacao;
    protected Integer id;
    protected String sgUnidadeOrganizacional;

    /**
     * Gets the value of the areaRh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaRh() {
        return areaRh;
    }

    /**
     * Sets the value of the areaRh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaRh(String value) {
        this.areaRh = value;
    }

    /**
     * Gets the value of the cdUnidadeOrganizacional property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdUnidadeOrganizacional() {
        return cdUnidadeOrganizacional;
    }

    /**
     * Sets the value of the cdUnidadeOrganizacional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdUnidadeOrganizacional(String value) {
        this.cdUnidadeOrganizacional = value;
    }

    /**
     * Gets the value of the dsDenominacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsDenominacao() {
        return dsDenominacao;
    }

    /**
     * Sets the value of the dsDenominacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsDenominacao(String value) {
        this.dsDenominacao = value;
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
     * Gets the value of the sgUnidadeOrganizacional property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSgUnidadeOrganizacional() {
        return sgUnidadeOrganizacional;
    }

    /**
     * Sets the value of the sgUnidadeOrganizacional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSgUnidadeOrganizacional(String value) {
        this.sgUnidadeOrganizacional = value;
    }

}
