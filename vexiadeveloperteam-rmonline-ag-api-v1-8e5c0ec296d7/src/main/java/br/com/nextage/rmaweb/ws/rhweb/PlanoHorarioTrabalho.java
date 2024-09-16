
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for planoHorarioTrabalho complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="planoHorarioTrabalho">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="calendarioFeriados" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cdAgrupSubArRhPht" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cdAgrupSubEmpregPht" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dsPlanoHorario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="planoHorario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="regraPht" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "planoHorarioTrabalho", propOrder = {
    "calendarioFeriados",
    "cdAgrupSubArRhPht",
    "cdAgrupSubEmpregPht",
    "dsPlanoHorario",
    "id",
    "planoHorario",
    "regraPht"
})
public class PlanoHorarioTrabalho {

    protected String calendarioFeriados;
    protected String cdAgrupSubArRhPht;
    protected String cdAgrupSubEmpregPht;
    protected String dsPlanoHorario;
    protected Integer id;
    protected String planoHorario;
    protected String regraPht;

    /**
     * Gets the value of the calendarioFeriados property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalendarioFeriados() {
        return calendarioFeriados;
    }

    /**
     * Sets the value of the calendarioFeriados property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalendarioFeriados(String value) {
        this.calendarioFeriados = value;
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
     * Gets the value of the cdAgrupSubEmpregPht property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdAgrupSubEmpregPht() {
        return cdAgrupSubEmpregPht;
    }

    /**
     * Sets the value of the cdAgrupSubEmpregPht property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdAgrupSubEmpregPht(String value) {
        this.cdAgrupSubEmpregPht = value;
    }

    /**
     * Gets the value of the dsPlanoHorario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsPlanoHorario() {
        return dsPlanoHorario;
    }

    /**
     * Sets the value of the dsPlanoHorario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsPlanoHorario(String value) {
        this.dsPlanoHorario = value;
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
     * Gets the value of the planoHorario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanoHorario() {
        return planoHorario;
    }

    /**
     * Sets the value of the planoHorario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanoHorario(String value) {
        this.planoHorario = value;
    }

    /**
     * Gets the value of the regraPht property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegraPht() {
        return regraPht;
    }

    /**
     * Sets the value of the regraPht property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegraPht(String value) {
        this.regraPht = value;
    }

}
