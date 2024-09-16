
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fcEtapa complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fcEtapa">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bloqBtnConcluir" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bloqVagaLimite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://ws.rhweb_ws.nextage.com.br/}configuracao" minOccurs="0"/>
 *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="enviaAdmSap" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="enviaEmail" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isSubcontratado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="listarAdmitidos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pertenceUsuario" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="primeiraEtapa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tela" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fcEtapa", propOrder = {
    "bloqBtnConcluir",
    "bloqVagaLimite",
    "configuracao",
    "descricao",
    "enviaAdmSap",
    "enviaEmail",
    "id",
    "isSubcontratado",
    "listarAdmitidos",
    "pertenceUsuario",
    "primeiraEtapa",
    "tela"
})
public class FcEtapa {

    protected String bloqBtnConcluir;
    protected String bloqVagaLimite;
    @XmlElement(namespace = "http://ws.rhweb_ws.nextage.com.br/")
    protected Configuracao configuracao;
    protected String descricao;
    protected String enviaAdmSap;
    protected Boolean enviaEmail;
    protected Integer id;
    protected Boolean isSubcontratado;
    protected String listarAdmitidos;
    protected Boolean pertenceUsuario;
    protected String primeiraEtapa;
    protected String tela;

    /**
     * Gets the value of the bloqBtnConcluir property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBloqBtnConcluir() {
        return bloqBtnConcluir;
    }

    /**
     * Sets the value of the bloqBtnConcluir property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBloqBtnConcluir(String value) {
        this.bloqBtnConcluir = value;
    }

    /**
     * Gets the value of the bloqVagaLimite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBloqVagaLimite() {
        return bloqVagaLimite;
    }

    /**
     * Sets the value of the bloqVagaLimite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBloqVagaLimite(String value) {
        this.bloqVagaLimite = value;
    }

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
     * Gets the value of the enviaAdmSap property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnviaAdmSap() {
        return enviaAdmSap;
    }

    /**
     * Sets the value of the enviaAdmSap property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnviaAdmSap(String value) {
        this.enviaAdmSap = value;
    }

    /**
     * Gets the value of the enviaEmail property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEnviaEmail() {
        return enviaEmail;
    }

    /**
     * Sets the value of the enviaEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEnviaEmail(Boolean value) {
        this.enviaEmail = value;
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
     * Gets the value of the isSubcontratado property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsSubcontratado() {
        return isSubcontratado;
    }

    /**
     * Sets the value of the isSubcontratado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSubcontratado(Boolean value) {
        this.isSubcontratado = value;
    }

    /**
     * Gets the value of the listarAdmitidos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListarAdmitidos() {
        return listarAdmitidos;
    }

    /**
     * Sets the value of the listarAdmitidos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListarAdmitidos(String value) {
        this.listarAdmitidos = value;
    }

    /**
     * Gets the value of the pertenceUsuario property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPertenceUsuario() {
        return pertenceUsuario;
    }

    /**
     * Sets the value of the pertenceUsuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPertenceUsuario(Boolean value) {
        this.pertenceUsuario = value;
    }

    /**
     * Gets the value of the primeiraEtapa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimeiraEtapa() {
        return primeiraEtapa;
    }

    /**
     * Sets the value of the primeiraEtapa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimeiraEtapa(String value) {
        this.primeiraEtapa = value;
    }

    /**
     * Gets the value of the tela property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTela() {
        return tela;
    }

    /**
     * Sets the value of the tela property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTela(String value) {
        this.tela = value;
    }

}
