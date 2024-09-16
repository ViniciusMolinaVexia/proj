
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for configuracaoTreinamento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="configuracaoTreinamento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bloqueioCapacidadeTurma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crachaTreinamentoAprovado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailResponsavel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="horaMinutoModAvalEfic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idsTituloDocumentoFichaTreinamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusPessTreinAprovado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusTreinPess" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configuracaoTreinamento", propOrder = {
    "bloqueioCapacidadeTurma",
    "crachaTreinamentoAprovado",
    "emailResponsavel",
    "horaMinutoModAvalEfic",
    "id",
    "idsTituloDocumentoFichaTreinamento",
    "statusPessTreinAprovado",
    "statusTreinPess"
})
public class ConfiguracaoTreinamento {

    protected String bloqueioCapacidadeTurma;
    protected String crachaTreinamentoAprovado;
    protected String emailResponsavel;
    protected String horaMinutoModAvalEfic;
    protected Integer id;
    protected String idsTituloDocumentoFichaTreinamento;
    protected String statusPessTreinAprovado;
    protected int statusTreinPess;

    /**
     * Gets the value of the bloqueioCapacidadeTurma property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBloqueioCapacidadeTurma() {
        return bloqueioCapacidadeTurma;
    }

    /**
     * Sets the value of the bloqueioCapacidadeTurma property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBloqueioCapacidadeTurma(String value) {
        this.bloqueioCapacidadeTurma = value;
    }

    /**
     * Gets the value of the crachaTreinamentoAprovado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrachaTreinamentoAprovado() {
        return crachaTreinamentoAprovado;
    }

    /**
     * Sets the value of the crachaTreinamentoAprovado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrachaTreinamentoAprovado(String value) {
        this.crachaTreinamentoAprovado = value;
    }

    /**
     * Gets the value of the emailResponsavel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailResponsavel() {
        return emailResponsavel;
    }

    /**
     * Sets the value of the emailResponsavel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailResponsavel(String value) {
        this.emailResponsavel = value;
    }

    /**
     * Gets the value of the horaMinutoModAvalEfic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoraMinutoModAvalEfic() {
        return horaMinutoModAvalEfic;
    }

    /**
     * Sets the value of the horaMinutoModAvalEfic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoraMinutoModAvalEfic(String value) {
        this.horaMinutoModAvalEfic = value;
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
     * Gets the value of the idsTituloDocumentoFichaTreinamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdsTituloDocumentoFichaTreinamento() {
        return idsTituloDocumentoFichaTreinamento;
    }

    /**
     * Sets the value of the idsTituloDocumentoFichaTreinamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdsTituloDocumentoFichaTreinamento(String value) {
        this.idsTituloDocumentoFichaTreinamento = value;
    }

    /**
     * Gets the value of the statusPessTreinAprovado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusPessTreinAprovado() {
        return statusPessTreinAprovado;
    }

    /**
     * Sets the value of the statusPessTreinAprovado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusPessTreinAprovado(String value) {
        this.statusPessTreinAprovado = value;
    }

    /**
     * Gets the value of the statusTreinPess property.
     * 
     */
    public int getStatusTreinPess() {
        return statusTreinPess;
    }

    /**
     * Sets the value of the statusTreinPess property.
     * 
     */
    public void setStatusTreinPess(int value) {
        this.statusTreinPess = value;
    }

}
