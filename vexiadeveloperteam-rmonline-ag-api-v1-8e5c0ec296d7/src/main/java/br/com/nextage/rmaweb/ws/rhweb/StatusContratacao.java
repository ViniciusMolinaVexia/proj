
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for statusContratacao complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="statusContratacao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="campoDataFim" type="{http://ws.rhweb_ws.nextage.com.br/}campo" minOccurs="0"/>
 *         &lt;element name="campoDataInicio" type="{http://ws.rhweb_ws.nextage.com.br/}campo" minOccurs="0"/>
 *         &lt;element name="codigoAgrup" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="diasContrato" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="fcFluxo" type="{http://ws.rhweb_ws.nextage.com.br/}fcFluxo" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isFase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isRelatorioVaga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ordem" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "statusContratacao", propOrder = {
    "campoDataFim",
    "campoDataInicio",
    "codigoAgrup",
    "descricao",
    "diasContrato",
    "fcFluxo",
    "id",
    "isFase",
    "isRelatorioVaga",
    "observacao",
    "ordem",
    "tipo"
})
public class StatusContratacao {

    protected Campo campoDataFim;
    protected Campo campoDataInicio;
    protected Integer codigoAgrup;
    protected String descricao;
    protected Integer diasContrato;
    protected FcFluxo fcFluxo;
    protected Integer id;
    protected String isFase;
    protected String isRelatorioVaga;
    protected String observacao;
    protected Integer ordem;
    protected String tipo;

    /**
     * Gets the value of the campoDataFim property.
     * 
     * @return
     *     possible object is
     *     {@link Campo }
     *     
     */
    public Campo getCampoDataFim() {
        return campoDataFim;
    }

    /**
     * Sets the value of the campoDataFim property.
     * 
     * @param value
     *     allowed object is
     *     {@link Campo }
     *     
     */
    public void setCampoDataFim(Campo value) {
        this.campoDataFim = value;
    }

    /**
     * Gets the value of the campoDataInicio property.
     * 
     * @return
     *     possible object is
     *     {@link Campo }
     *     
     */
    public Campo getCampoDataInicio() {
        return campoDataInicio;
    }

    /**
     * Sets the value of the campoDataInicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Campo }
     *     
     */
    public void setCampoDataInicio(Campo value) {
        this.campoDataInicio = value;
    }

    /**
     * Gets the value of the codigoAgrup property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodigoAgrup() {
        return codigoAgrup;
    }

    /**
     * Sets the value of the codigoAgrup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodigoAgrup(Integer value) {
        this.codigoAgrup = value;
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
     * Gets the value of the diasContrato property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDiasContrato() {
        return diasContrato;
    }

    /**
     * Sets the value of the diasContrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDiasContrato(Integer value) {
        this.diasContrato = value;
    }

    /**
     * Gets the value of the fcFluxo property.
     * 
     * @return
     *     possible object is
     *     {@link FcFluxo }
     *     
     */
    public FcFluxo getFcFluxo() {
        return fcFluxo;
    }

    /**
     * Sets the value of the fcFluxo property.
     * 
     * @param value
     *     allowed object is
     *     {@link FcFluxo }
     *     
     */
    public void setFcFluxo(FcFluxo value) {
        this.fcFluxo = value;
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
     * Gets the value of the isFase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsFase() {
        return isFase;
    }

    /**
     * Sets the value of the isFase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsFase(String value) {
        this.isFase = value;
    }

    /**
     * Gets the value of the isRelatorioVaga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsRelatorioVaga() {
        return isRelatorioVaga;
    }

    /**
     * Sets the value of the isRelatorioVaga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsRelatorioVaga(String value) {
        this.isRelatorioVaga = value;
    }

    /**
     * Gets the value of the observacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * Sets the value of the observacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacao(String value) {
        this.observacao = value;
    }

    /**
     * Gets the value of the ordem property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOrdem() {
        return ordem;
    }

    /**
     * Sets the value of the ordem property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOrdem(Integer value) {
        this.ordem = value;
    }

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

}
