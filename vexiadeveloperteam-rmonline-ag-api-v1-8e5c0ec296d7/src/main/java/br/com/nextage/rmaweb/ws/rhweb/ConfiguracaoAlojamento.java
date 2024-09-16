
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for configuracaoAlojamento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="configuracaoAlojamento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alertaDataFutura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="alojarTemporario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="usarEap" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validaCrachaProvisorio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configuracaoAlojamento", propOrder = {
    "alertaDataFutura",
    "alojarTemporario",
    "id",
    "usarEap",
    "validaCrachaProvisorio"
})
public class ConfiguracaoAlojamento {

    protected String alertaDataFutura;
    protected String alojarTemporario;
    protected Integer id;
    protected String usarEap;
    protected String validaCrachaProvisorio;

    /**
     * Gets the value of the alertaDataFutura property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlertaDataFutura() {
        return alertaDataFutura;
    }

    /**
     * Sets the value of the alertaDataFutura property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlertaDataFutura(String value) {
        this.alertaDataFutura = value;
    }

    /**
     * Gets the value of the alojarTemporario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlojarTemporario() {
        return alojarTemporario;
    }

    /**
     * Sets the value of the alojarTemporario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlojarTemporario(String value) {
        this.alojarTemporario = value;
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
     * Gets the value of the usarEap property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsarEap() {
        return usarEap;
    }

    /**
     * Sets the value of the usarEap property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsarEap(String value) {
        this.usarEap = value;
    }

    /**
     * Gets the value of the validaCrachaProvisorio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidaCrachaProvisorio() {
        return validaCrachaProvisorio;
    }

    /**
     * Sets the value of the validaCrachaProvisorio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidaCrachaProvisorio(String value) {
        this.validaCrachaProvisorio = value;
    }

}
