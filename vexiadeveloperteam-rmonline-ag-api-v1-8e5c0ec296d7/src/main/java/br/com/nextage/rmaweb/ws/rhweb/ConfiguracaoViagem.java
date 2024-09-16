
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for configuracaoViagem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="configuracaoViagem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="abaItinerarioReqViagem" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="beneficioMobilizacaoId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="bloqTdsCntrCstVgTp" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="desabilitaEmailRespCad" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="desabilitarInfoPessoa" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="desabilitarInfoVisFam" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="exibeDiasAntecedencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="preencherSolicitante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="respCadViagemAutomatica" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tipoViagemMobilizacaoId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tipoViagemVisitaFamiliaId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configuracaoViagem", propOrder = {
    "abaItinerarioReqViagem",
    "beneficioMobilizacaoId",
    "bloqTdsCntrCstVgTp",
    "desabilitaEmailRespCad",
    "desabilitarInfoPessoa",
    "desabilitarInfoVisFam",
    "exibeDiasAntecedencia",
    "id",
    "preencherSolicitante",
    "respCadViagemAutomatica",
    "tipoViagemMobilizacaoId",
    "tipoViagemVisitaFamiliaId"
})
public class ConfiguracaoViagem {

    protected Boolean abaItinerarioReqViagem;
    protected int beneficioMobilizacaoId;
    protected Boolean bloqTdsCntrCstVgTp;
    protected Boolean desabilitaEmailRespCad;
    protected Boolean desabilitarInfoPessoa;
    protected Boolean desabilitarInfoVisFam;
    protected String exibeDiasAntecedencia;
    protected Integer id;
    protected String preencherSolicitante;
    protected Integer respCadViagemAutomatica;
    protected int tipoViagemMobilizacaoId;
    protected Integer tipoViagemVisitaFamiliaId;

    /**
     * Gets the value of the abaItinerarioReqViagem property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAbaItinerarioReqViagem() {
        return abaItinerarioReqViagem;
    }

    /**
     * Sets the value of the abaItinerarioReqViagem property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAbaItinerarioReqViagem(Boolean value) {
        this.abaItinerarioReqViagem = value;
    }

    /**
     * Gets the value of the beneficioMobilizacaoId property.
     * 
     */
    public int getBeneficioMobilizacaoId() {
        return beneficioMobilizacaoId;
    }

    /**
     * Sets the value of the beneficioMobilizacaoId property.
     * 
     */
    public void setBeneficioMobilizacaoId(int value) {
        this.beneficioMobilizacaoId = value;
    }

    /**
     * Gets the value of the bloqTdsCntrCstVgTp property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBloqTdsCntrCstVgTp() {
        return bloqTdsCntrCstVgTp;
    }

    /**
     * Sets the value of the bloqTdsCntrCstVgTp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBloqTdsCntrCstVgTp(Boolean value) {
        this.bloqTdsCntrCstVgTp = value;
    }

    /**
     * Gets the value of the desabilitaEmailRespCad property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDesabilitaEmailRespCad() {
        return desabilitaEmailRespCad;
    }

    /**
     * Sets the value of the desabilitaEmailRespCad property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDesabilitaEmailRespCad(Boolean value) {
        this.desabilitaEmailRespCad = value;
    }

    /**
     * Gets the value of the desabilitarInfoPessoa property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDesabilitarInfoPessoa() {
        return desabilitarInfoPessoa;
    }

    /**
     * Sets the value of the desabilitarInfoPessoa property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDesabilitarInfoPessoa(Boolean value) {
        this.desabilitarInfoPessoa = value;
    }

    /**
     * Gets the value of the desabilitarInfoVisFam property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDesabilitarInfoVisFam() {
        return desabilitarInfoVisFam;
    }

    /**
     * Sets the value of the desabilitarInfoVisFam property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDesabilitarInfoVisFam(Boolean value) {
        this.desabilitarInfoVisFam = value;
    }

    /**
     * Gets the value of the exibeDiasAntecedencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExibeDiasAntecedencia() {
        return exibeDiasAntecedencia;
    }

    /**
     * Sets the value of the exibeDiasAntecedencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExibeDiasAntecedencia(String value) {
        this.exibeDiasAntecedencia = value;
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
     * Gets the value of the preencherSolicitante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreencherSolicitante() {
        return preencherSolicitante;
    }

    /**
     * Sets the value of the preencherSolicitante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreencherSolicitante(String value) {
        this.preencherSolicitante = value;
    }

    /**
     * Gets the value of the respCadViagemAutomatica property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRespCadViagemAutomatica() {
        return respCadViagemAutomatica;
    }

    /**
     * Sets the value of the respCadViagemAutomatica property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRespCadViagemAutomatica(Integer value) {
        this.respCadViagemAutomatica = value;
    }

    /**
     * Gets the value of the tipoViagemMobilizacaoId property.
     * 
     */
    public int getTipoViagemMobilizacaoId() {
        return tipoViagemMobilizacaoId;
    }

    /**
     * Sets the value of the tipoViagemMobilizacaoId property.
     * 
     */
    public void setTipoViagemMobilizacaoId(int value) {
        this.tipoViagemMobilizacaoId = value;
    }

    /**
     * Gets the value of the tipoViagemVisitaFamiliaId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTipoViagemVisitaFamiliaId() {
        return tipoViagemVisitaFamiliaId;
    }

    /**
     * Sets the value of the tipoViagemVisitaFamiliaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTipoViagemVisitaFamiliaId(Integer value) {
        this.tipoViagemVisitaFamiliaId = value;
    }

}
