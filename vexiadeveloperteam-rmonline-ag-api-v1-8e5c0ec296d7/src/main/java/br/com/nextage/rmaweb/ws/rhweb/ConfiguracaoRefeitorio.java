
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for configuracaoRefeitorio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="configuracaoRefeitorio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cardapioListProcesDemissao" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
@XmlType(name = "configuracaoRefeitorio", propOrder = {
    "cardapioListProcesDemissao",
    "id",
    "validaCrachaProvisorio"
})
public class ConfiguracaoRefeitorio {

    protected Boolean cardapioListProcesDemissao;
    protected Integer id;
    protected String validaCrachaProvisorio;

    /**
     * Gets the value of the cardapioListProcesDemissao property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCardapioListProcesDemissao() {
        return cardapioListProcesDemissao;
    }

    /**
     * Sets the value of the cardapioListProcesDemissao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCardapioListProcesDemissao(Boolean value) {
        this.cardapioListProcesDemissao = value;
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
