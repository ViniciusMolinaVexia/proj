
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for areaRh complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="areaRh">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="acordoColetivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="calendarioFeriado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cdAgrupSubArRhPht" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cdAreaRH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cdDivisaoRh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cdSubAreaRh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dsAreaRh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "areaRh", propOrder = {
    "acordoColetivo",
    "calendarioFeriado",
    "cdAgrupSubArRhPht",
    "cdAreaRH",
    "cdDivisaoRh",
    "cdSubAreaRh",
    "dsAreaRh",
    "id"
})
public class AreaRh {

    protected String acordoColetivo;
    protected String calendarioFeriado;
    protected String cdAgrupSubArRhPht;
    protected String cdAreaRH;
    protected String cdDivisaoRh;
    protected String cdSubAreaRh;
    protected String dsAreaRh;
    protected Integer id;

    /**
     * Gets the value of the acordoColetivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcordoColetivo() {
        return acordoColetivo;
    }

    /**
     * Sets the value of the acordoColetivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcordoColetivo(String value) {
        this.acordoColetivo = value;
    }

    /**
     * Gets the value of the calendarioFeriado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalendarioFeriado() {
        return calendarioFeriado;
    }

    /**
     * Sets the value of the calendarioFeriado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalendarioFeriado(String value) {
        this.calendarioFeriado = value;
    }

    /**
     * Gets the value of the cdAgrupSubArRhPht property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdAgrupSubArRhPht() {
        return cdAgrupSubArRhPht;
    }

    /**
     * Sets the value of the cdAgrupSubArRhPht property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdAgrupSubArRhPht(String value) {
        this.cdAgrupSubArRhPht = value;
    }

    /**
     * Gets the value of the cdAreaRH property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdAreaRH() {
        return cdAreaRH;
    }

    /**
     * Sets the value of the cdAreaRH property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdAreaRH(String value) {
        this.cdAreaRH = value;
    }

    /**
     * Gets the value of the cdDivisaoRh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdDivisaoRh() {
        return cdDivisaoRh;
    }

    /**
     * Sets the value of the cdDivisaoRh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdDivisaoRh(String value) {
        this.cdDivisaoRh = value;
    }

    /**
     * Gets the value of the cdSubAreaRh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdSubAreaRh() {
        return cdSubAreaRh;
    }

    /**
     * Sets the value of the cdSubAreaRh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdSubAreaRh(String value) {
        this.cdSubAreaRh = value;
    }

    /**
     * Gets the value of the dsAreaRh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsAreaRh() {
        return dsAreaRh;
    }

    /**
     * Sets the value of the dsAreaRh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsAreaRh(String value) {
        this.dsAreaRh = value;
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
