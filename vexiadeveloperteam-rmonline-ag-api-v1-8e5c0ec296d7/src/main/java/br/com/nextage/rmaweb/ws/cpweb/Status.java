
package br.com.nextage.rmaweb.ws.cpweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for status complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="status">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ativoMovimentacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="presenteEstoque" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "status", propOrder = {
    "ativoMovimentacao",
    "descricao",
    "operacao",
    "presenteEstoque",
    "statusId"
})
public class Status {

    protected String ativoMovimentacao;
    protected String descricao;
    protected String operacao;
    protected String presenteEstoque;
    protected Integer statusId;

    /**
     * Gets the value of the ativoMovimentacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtivoMovimentacao() {
        return ativoMovimentacao;
    }

    /**
     * Sets the value of the ativoMovimentacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtivoMovimentacao(String value) {
        this.ativoMovimentacao = value;
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
     * Gets the value of the operacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperacao() {
        return operacao;
    }

    /**
     * Sets the value of the operacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperacao(String value) {
        this.operacao = value;
    }

    /**
     * Gets the value of the presenteEstoque property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPresenteEstoque() {
        return presenteEstoque;
    }

    /**
     * Sets the value of the presenteEstoque property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPresenteEstoque(String value) {
        this.presenteEstoque = value;
    }

    /**
     * Gets the value of the statusId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStatusId() {
        return statusId;
    }

    /**
     * Sets the value of the statusId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStatusId(Integer value) {
        this.statusId = value;
    }

}
