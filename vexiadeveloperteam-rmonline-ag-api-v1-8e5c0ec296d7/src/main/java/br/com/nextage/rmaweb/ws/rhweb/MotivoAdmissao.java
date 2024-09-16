
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for motivoAdmissao complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="motivoAdmissao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cdMotivoAdmissao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dsMotivoAdmissao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "motivoAdmissao", propOrder = {
    "cdMotivoAdmissao",
    "dsMotivoAdmissao",
    "id"
})
public class MotivoAdmissao {

    protected String cdMotivoAdmissao;
    protected String dsMotivoAdmissao;
    protected Integer id;

    /**
     * Gets the value of the cdMotivoAdmissao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdMotivoAdmissao() {
        return cdMotivoAdmissao;
    }

    /**
     * Sets the value of the cdMotivoAdmissao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdMotivoAdmissao(String value) {
        this.cdMotivoAdmissao = value;
    }

    /**
     * Gets the value of the dsMotivoAdmissao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsMotivoAdmissao() {
        return dsMotivoAdmissao;
    }

    /**
     * Sets the value of the dsMotivoAdmissao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsMotivoAdmissao(String value) {
        this.dsMotivoAdmissao = value;
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
