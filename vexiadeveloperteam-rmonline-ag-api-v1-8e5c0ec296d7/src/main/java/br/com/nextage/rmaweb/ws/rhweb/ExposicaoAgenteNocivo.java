
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for exposicaoAgenteNocivo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="exposicaoAgenteNocivo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cdExpoAgenteNocivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dsExpoAgenteNocivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "exposicaoAgenteNocivo", propOrder = {
    "cdExpoAgenteNocivo",
    "dsExpoAgenteNocivo",
    "id"
})
public class ExposicaoAgenteNocivo {

    protected String cdExpoAgenteNocivo;
    protected String dsExpoAgenteNocivo;
    protected Integer id;

    /**
     * Gets the value of the cdExpoAgenteNocivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdExpoAgenteNocivo() {
        return cdExpoAgenteNocivo;
    }

    /**
     * Sets the value of the cdExpoAgenteNocivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdExpoAgenteNocivo(String value) {
        this.cdExpoAgenteNocivo = value;
    }

    /**
     * Gets the value of the dsExpoAgenteNocivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsExpoAgenteNocivo() {
        return dsExpoAgenteNocivo;
    }

    /**
     * Sets the value of the dsExpoAgenteNocivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsExpoAgenteNocivo(String value) {
        this.dsExpoAgenteNocivo = value;
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
