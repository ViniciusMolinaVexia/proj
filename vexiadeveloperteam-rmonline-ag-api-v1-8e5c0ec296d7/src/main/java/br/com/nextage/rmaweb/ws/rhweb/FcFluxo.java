
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fcFluxo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fcFluxo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://ws.rhweb_ws.nextage.com.br/}configuracao" minOccurs="0"/>
 *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="inativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isPadrao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fcFluxo", propOrder = {
    "configuracao",
    "descricao",
    "id",
    "inativo",
    "isPadrao"
})
public class FcFluxo {

    @XmlElement(namespace = "http://ws.rhweb_ws.nextage.com.br/")
    protected Configuracao configuracao;
    protected String descricao;
    protected Integer id;
    protected String inativo;
    protected String isPadrao;

    /**
     * Gets the value of the configuracao property.
     * 
     * @return
     *     possible object is
     *     {@link Configuracao }
     *     
     */
    public Configuracao getConfiguracao() {
        return configuracao;
    }

    /**
     * Sets the value of the configuracao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Configuracao }
     *     
     */
    public void setConfiguracao(Configuracao value) {
        this.configuracao = value;
    }

    /**
     * Gets the value of the descricao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Sets the value of the descricao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescricao(String value) {
        this.descricao = value;
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
     * Gets the value of the inativo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInativo() {
        return inativo;
    }

    /**
     * Sets the value of the inativo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInativo(String value) {
        this.inativo = value;
    }

    /**
     * Gets the value of the isPadrao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsPadrao() {
        return isPadrao;
    }

    /**
     * Sets the value of the isPadrao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsPadrao(String value) {
        this.isPadrao = value;
    }

}
