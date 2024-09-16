
package br.com.nextage.rmaweb.ws.sap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DT_SolicitaRequisicao_response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DT_SolicitaRequisicao_response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Item" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoMensagem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mensagem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Requisicao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_SolicitaRequisicao_response", namespace = "urn:cccc.rmaweb.requisicao", propOrder = {
    "item",
    "tipoMensagem",
    "mensagem",
    "requisicao"
})
public class DTSolicitaRequisicaoResponse {

    @XmlElement(name = "Item")
    protected String item;
    @XmlElement(name = "TipoMensagem")
    protected String tipoMensagem;
    @XmlElement(name = "Mensagem")
    protected String mensagem;
    @XmlElement(name = "Requisicao")
    protected String requisicao;

    /**
     * Gets the value of the item property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItem() {
        return item;
    }

    /**
     * Sets the value of the item property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItem(String value) {
        this.item = value;
    }

    /**
     * Gets the value of the tipoMensagem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoMensagem() {
        return tipoMensagem;
    }

    /**
     * Sets the value of the tipoMensagem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoMensagem(String value) {
        this.tipoMensagem = value;
    }

    /**
     * Gets the value of the mensagem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * Sets the value of the mensagem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensagem(String value) {
        this.mensagem = value;
    }

    /**
     * Gets the value of the requisicao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequisicao() {
        return requisicao;
    }

    /**
     * Sets the value of the requisicao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequisicao(String value) {
        this.requisicao = value;
    }

}
