
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for nivelSalarial complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="nivelSalarial">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="agrupSubEmprAcorCol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="agrupamentoPaises" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cdNivel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="faixaSalarial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="montante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="regiaoAcordoColetivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tpTarifa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nivelSalarial", propOrder = {
    "agrupSubEmprAcorCol",
    "agrupamentoPaises",
    "cdNivel",
    "faixaSalarial",
    "id",
    "montante",
    "regiaoAcordoColetivo",
    "tpTarifa"
})
public class NivelSalarial {

    protected String agrupSubEmprAcorCol;
    protected String agrupamentoPaises;
    protected String cdNivel;
    protected String faixaSalarial;
    protected Integer id;
    protected String montante;
    protected String regiaoAcordoColetivo;
    protected String tpTarifa;

    /**
     * Gets the value of the agrupSubEmprAcorCol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgrupSubEmprAcorCol() {
        return agrupSubEmprAcorCol;
    }

    /**
     * Sets the value of the agrupSubEmprAcorCol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgrupSubEmprAcorCol(String value) {
        this.agrupSubEmprAcorCol = value;
    }

    /**
     * Gets the value of the agrupamentoPaises property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgrupamentoPaises() {
        return agrupamentoPaises;
    }

    /**
     * Sets the value of the agrupamentoPaises property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgrupamentoPaises(String value) {
        this.agrupamentoPaises = value;
    }

    /**
     * Gets the value of the cdNivel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdNivel() {
        return cdNivel;
    }

    /**
     * Sets the value of the cdNivel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdNivel(String value) {
        this.cdNivel = value;
    }

    /**
     * Gets the value of the faixaSalarial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaixaSalarial() {
        return faixaSalarial;
    }

    /**
     * Sets the value of the faixaSalarial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaixaSalarial(String value) {
        this.faixaSalarial = value;
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
     * Gets the value of the montante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontante() {
        return montante;
    }

    /**
     * Sets the value of the montante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontante(String value) {
        this.montante = value;
    }

    /**
     * Gets the value of the regiaoAcordoColetivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegiaoAcordoColetivo() {
        return regiaoAcordoColetivo;
    }

    /**
     * Sets the value of the regiaoAcordoColetivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegiaoAcordoColetivo(String value) {
        this.regiaoAcordoColetivo = value;
    }

    /**
     * Gets the value of the tpTarifa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpTarifa() {
        return tpTarifa;
    }

    /**
     * Sets the value of the tpTarifa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpTarifa(String value) {
        this.tpTarifa = value;
    }

}
