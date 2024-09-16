
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for configuracaoSubcontratados complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="configuracaoSubcontratados">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bloqueiaEntregaDuplicadaDocs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="docStatusFinalId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="docStatusInicialId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="propTeAuxEntregaDoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configuracaoSubcontratados", propOrder = {
    "bloqueiaEntregaDuplicadaDocs",
    "docStatusFinalId",
    "docStatusInicialId",
    "id",
    "propTeAuxEntregaDoc"
})
public class ConfiguracaoSubcontratados {

    protected String bloqueiaEntregaDuplicadaDocs;
    protected Integer docStatusFinalId;
    protected Integer docStatusInicialId;
    protected Integer id;
    protected String propTeAuxEntregaDoc;

    /**
     * Gets the value of the bloqueiaEntregaDuplicadaDocs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBloqueiaEntregaDuplicadaDocs() {
        return bloqueiaEntregaDuplicadaDocs;
    }

    /**
     * Sets the value of the bloqueiaEntregaDuplicadaDocs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBloqueiaEntregaDuplicadaDocs(String value) {
        this.bloqueiaEntregaDuplicadaDocs = value;
    }

    /**
     * Gets the value of the docStatusFinalId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDocStatusFinalId() {
        return docStatusFinalId;
    }

    /**
     * Sets the value of the docStatusFinalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDocStatusFinalId(Integer value) {
        this.docStatusFinalId = value;
    }

    /**
     * Gets the value of the docStatusInicialId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDocStatusInicialId() {
        return docStatusInicialId;
    }

    /**
     * Sets the value of the docStatusInicialId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDocStatusInicialId(Integer value) {
        this.docStatusInicialId = value;
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
     * Gets the value of the propTeAuxEntregaDoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropTeAuxEntregaDoc() {
        return propTeAuxEntregaDoc;
    }

    /**
     * Sets the value of the propTeAuxEntregaDoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropTeAuxEntregaDoc(String value) {
        this.propTeAuxEntregaDoc = value;
    }

}
