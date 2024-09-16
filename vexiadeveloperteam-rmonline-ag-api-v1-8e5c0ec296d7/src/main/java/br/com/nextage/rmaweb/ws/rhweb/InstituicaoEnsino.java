
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for instituicaoEnsino complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="instituicaoEnsino">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cdUniversidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dsUnivCompleta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dsUnivResumida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "instituicaoEnsino", propOrder = {
    "cdUniversidade",
    "dsUnivCompleta",
    "dsUnivResumida",
    "id"
})
public class InstituicaoEnsino {

    protected String cdUniversidade;
    protected String dsUnivCompleta;
    protected String dsUnivResumida;
    protected Integer id;

    /**
     * Gets the value of the cdUniversidade property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdUniversidade() {
        return cdUniversidade;
    }

    /**
     * Sets the value of the cdUniversidade property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdUniversidade(String value) {
        this.cdUniversidade = value;
    }

    /**
     * Gets the value of the dsUnivCompleta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsUnivCompleta() {
        return dsUnivCompleta;
    }

    /**
     * Sets the value of the dsUnivCompleta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsUnivCompleta(String value) {
        this.dsUnivCompleta = value;
    }

    /**
     * Gets the value of the dsUnivResumida property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsUnivResumida() {
        return dsUnivResumida;
    }

    /**
     * Sets the value of the dsUnivResumida property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsUnivResumida(String value) {
        this.dsUnivResumida = value;
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
