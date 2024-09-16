
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for certificadoEscolar complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="certificadoEscolar">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cdCertificadoEscolar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dsCertificadoEscolar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "certificadoEscolar", propOrder = {
    "cdCertificadoEscolar",
    "dsCertificadoEscolar",
    "id"
})
public class CertificadoEscolar {

    protected String cdCertificadoEscolar;
    protected String dsCertificadoEscolar;
    protected Integer id;

    /**
     * Gets the value of the cdCertificadoEscolar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdCertificadoEscolar() {
        return cdCertificadoEscolar;
    }

    /**
     * Sets the value of the cdCertificadoEscolar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdCertificadoEscolar(String value) {
        this.cdCertificadoEscolar = value;
    }

    /**
     * Gets the value of the dsCertificadoEscolar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsCertificadoEscolar() {
        return dsCertificadoEscolar;
    }

    /**
     * Sets the value of the dsCertificadoEscolar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsCertificadoEscolar(String value) {
        this.dsCertificadoEscolar = value;
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
