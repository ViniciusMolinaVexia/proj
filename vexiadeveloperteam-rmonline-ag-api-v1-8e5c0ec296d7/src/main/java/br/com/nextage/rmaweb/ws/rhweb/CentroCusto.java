
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for centroCusto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="centroCusto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cdAreaContabCustos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cdDivisao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cdEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="observacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="responsavel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "centroCusto", propOrder = {
    "cdAreaContabCustos",
    "cdDivisao",
    "cdEmpresa",
    "codigo",
    "descricao",
    "id",
    "observacao",
    "responsavel"
})
public class CentroCusto {

    protected String cdAreaContabCustos;
    protected String cdDivisao;
    protected String cdEmpresa;
    protected String codigo;
    protected String descricao;
    protected Integer id;
    protected String observacao;
    protected String responsavel;

    /**
     * Gets the value of the cdAreaContabCustos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdAreaContabCustos() {
        return cdAreaContabCustos;
    }

    /**
     * Sets the value of the cdAreaContabCustos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdAreaContabCustos(String value) {
        this.cdAreaContabCustos = value;
    }

    /**
     * Gets the value of the cdDivisao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdDivisao() {
        return cdDivisao;
    }

    /**
     * Sets the value of the cdDivisao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdDivisao(String value) {
        this.cdDivisao = value;
    }

    /**
     * Gets the value of the cdEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdEmpresa() {
        return cdEmpresa;
    }

    /**
     * Sets the value of the cdEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdEmpresa(String value) {
        this.cdEmpresa = value;
    }

    /**
     * Gets the value of the codigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sets the value of the codigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigo(String value) {
        this.codigo = value;
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
     * Gets the value of the responsavel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsavel() {
        return responsavel;
    }

    /**
     * Sets the value of the responsavel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsavel(String value) {
        this.responsavel = value;
    }

}
