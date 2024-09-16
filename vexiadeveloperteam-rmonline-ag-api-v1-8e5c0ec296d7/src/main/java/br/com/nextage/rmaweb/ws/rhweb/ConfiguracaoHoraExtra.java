
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for configuracaoHoraExtra complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="configuracaoHoraExtra">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bloquearDesligados" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="intervaloDiasRequisicao" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configuracaoHoraExtra", propOrder = {
    "bloquearDesligados",
    "id",
    "intervaloDiasRequisicao"
})
public class ConfiguracaoHoraExtra {

    protected String bloquearDesligados;
    protected Integer id;
    protected Integer intervaloDiasRequisicao;

    /**
     * Gets the value of the bloquearDesligados property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBloquearDesligados() {
        return bloquearDesligados;
    }

    /**
     * Sets the value of the bloquearDesligados property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBloquearDesligados(String value) {
        this.bloquearDesligados = value;
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
     * Gets the value of the intervaloDiasRequisicao property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntervaloDiasRequisicao() {
        return intervaloDiasRequisicao;
    }

    /**
     * Sets the value of the intervaloDiasRequisicao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntervaloDiasRequisicao(Integer value) {
        this.intervaloDiasRequisicao = value;
    }

}
