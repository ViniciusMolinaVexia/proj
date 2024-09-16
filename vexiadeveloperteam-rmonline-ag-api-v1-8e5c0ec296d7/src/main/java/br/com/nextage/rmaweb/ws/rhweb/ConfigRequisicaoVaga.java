
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for configRequisicaoVaga complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="configRequisicaoVaga">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="criarVagaAutomatico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="notificarReqDesistenciaVaga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configRequisicaoVaga", propOrder = {
    "criarVagaAutomatico",
    "id",
    "notificarReqDesistenciaVaga"
})
public class ConfigRequisicaoVaga {

    protected String criarVagaAutomatico;
    protected Integer id;
    protected String notificarReqDesistenciaVaga;

    /**
     * Gets the value of the criarVagaAutomatico property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCriarVagaAutomatico() {
        return criarVagaAutomatico;
    }

    /**
     * Sets the value of the criarVagaAutomatico property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCriarVagaAutomatico(String value) {
        this.criarVagaAutomatico = value;
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
     * Gets the value of the notificarReqDesistenciaVaga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotificarReqDesistenciaVaga() {
        return notificarReqDesistenciaVaga;
    }

    /**
     * Sets the value of the notificarReqDesistenciaVaga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificarReqDesistenciaVaga(String value) {
        this.notificarReqDesistenciaVaga = value;
    }

}
