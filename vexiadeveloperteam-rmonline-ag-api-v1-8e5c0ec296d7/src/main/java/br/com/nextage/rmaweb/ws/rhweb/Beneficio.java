
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for beneficio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="beneficio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="beneficioId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="cidadeDestino" type="{http://ws.rhweb_ws.nextage.com.br/}cidade" minOccurs="0"/>
 *         &lt;element name="cidadeOrigem" type="{http://ws.rhweb_ws.nextage.com.br/}cidade" minOccurs="0"/>
 *         &lt;element name="dataChegadaPrevista" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exibirContratacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="possuiBeneficio" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="protegido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sigla" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="valor" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "beneficio", propOrder = {
    "beneficioId",
    "cidadeDestino",
    "cidadeOrigem",
    "dataChegadaPrevista",
    "descricao",
    "exibirContratacao",
    "observacao",
    "possuiBeneficio",
    "protegido",
    "sigla",
    "tipo",
    "valor"
})
public class Beneficio {

    protected Integer beneficioId;
    protected Cidade cidadeDestino;
    protected Cidade cidadeOrigem;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataChegadaPrevista;
    protected String descricao;
    protected String exibirContratacao;
    protected String observacao;
    protected boolean possuiBeneficio;
    protected String protegido;
    protected String sigla;
    protected Integer tipo;
    protected Double valor;

    /**
     * Gets the value of the beneficioId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBeneficioId() {
        return beneficioId;
    }

    /**
     * Sets the value of the beneficioId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBeneficioId(Integer value) {
        this.beneficioId = value;
    }

    /**
     * Gets the value of the cidadeDestino property.
     * 
     * @return
     *     possible object is
     *     {@link Cidade }
     *     
     */
    public Cidade getCidadeDestino() {
        return cidadeDestino;
    }

    /**
     * Sets the value of the cidadeDestino property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cidade }
     *     
     */
    public void setCidadeDestino(Cidade value) {
        this.cidadeDestino = value;
    }

    /**
     * Gets the value of the cidadeOrigem property.
     * 
     * @return
     *     possible object is
     *     {@link Cidade }
     *     
     */
    public Cidade getCidadeOrigem() {
        return cidadeOrigem;
    }

    /**
     * Sets the value of the cidadeOrigem property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cidade }
     *     
     */
    public void setCidadeOrigem(Cidade value) {
        this.cidadeOrigem = value;
    }

    /**
     * Gets the value of the dataChegadaPrevista property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataChegadaPrevista() {
        return dataChegadaPrevista;
    }

    /**
     * Sets the value of the dataChegadaPrevista property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataChegadaPrevista(XMLGregorianCalendar value) {
        this.dataChegadaPrevista = value;
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
     * Gets the value of the exibirContratacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExibirContratacao() {
        return exibirContratacao;
    }

    /**
     * Sets the value of the exibirContratacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExibirContratacao(String value) {
        this.exibirContratacao = value;
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
     * Gets the value of the possuiBeneficio property.
     * 
     */
    public boolean isPossuiBeneficio() {
        return possuiBeneficio;
    }

    /**
     * Sets the value of the possuiBeneficio property.
     * 
     */
    public void setPossuiBeneficio(boolean value) {
        this.possuiBeneficio = value;
    }

    /**
     * Gets the value of the protegido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtegido() {
        return protegido;
    }

    /**
     * Sets the value of the protegido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtegido(String value) {
        this.protegido = value;
    }

    /**
     * Gets the value of the sigla property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSigla() {
        return sigla;
    }

    /**
     * Sets the value of the sigla property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSigla(String value) {
        this.sigla = value;
    }

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTipo(Integer value) {
        this.tipo = value;
    }

    /**
     * Gets the value of the valor property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getValor() {
        return valor;
    }

    /**
     * Sets the value of the valor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setValor(Double value) {
        this.valor = value;
    }

}
