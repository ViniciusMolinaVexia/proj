
package br.com.nextage.rmaweb.ws.sap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DT_SolicitaReserva_response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DT_SolicitaReserva_response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TipoMensagem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mensagem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Reserva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_SolicitaReserva_response", namespace = "urn:cccc.rmaweb.reservamat", propOrder = {
    "tipoMensagem",
    "mensagem",
    "reserva"
})
public class DTSolicitaReservaResponse {

    @XmlElement(name = "TipoMensagem")
    protected String tipoMensagem;
    @XmlElement(name = "Mensagem")
    protected String mensagem;
    @XmlElement(name = "Reserva")
    protected String reserva;

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
     * Gets the value of the reserva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserva() {
        return reserva;
    }

    /**
     * Sets the value of the reserva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserva(String value) {
        this.reserva = value;
    }

}
