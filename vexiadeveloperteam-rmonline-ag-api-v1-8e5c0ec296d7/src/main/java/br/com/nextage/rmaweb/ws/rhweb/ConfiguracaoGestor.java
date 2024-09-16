
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for configuracaoGestor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="configuracaoGestor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cidadesDetalhar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descricaoCidadesEntornos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exibirInfAvalOrganograma" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="exibirInfPlrOrganograma" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="infAvalOrganogramaEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="infPlrOrganogramaEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="percentualPdc" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configuracaoGestor", propOrder = {
    "cidadesDetalhar",
    "descricaoCidadesEntornos",
    "exibirInfAvalOrganograma",
    "exibirInfPlrOrganograma",
    "id",
    "infAvalOrganogramaEmpresa",
    "infPlrOrganogramaEmpresa",
    "percentualPdc"
})
public class ConfiguracaoGestor {

    protected String cidadesDetalhar;
    protected String descricaoCidadesEntornos;
    protected Boolean exibirInfAvalOrganograma;
    protected Boolean exibirInfPlrOrganograma;
    protected Integer id;
    protected String infAvalOrganogramaEmpresa;
    protected String infPlrOrganogramaEmpresa;
    protected Float percentualPdc;

    /**
     * Gets the value of the cidadesDetalhar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCidadesDetalhar() {
        return cidadesDetalhar;
    }

    /**
     * Sets the value of the cidadesDetalhar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCidadesDetalhar(String value) {
        this.cidadesDetalhar = value;
    }

    /**
     * Gets the value of the descricaoCidadesEntornos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescricaoCidadesEntornos() {
        return descricaoCidadesEntornos;
    }

    /**
     * Sets the value of the descricaoCidadesEntornos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescricaoCidadesEntornos(String value) {
        this.descricaoCidadesEntornos = value;
    }

    /**
     * Gets the value of the exibirInfAvalOrganograma property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExibirInfAvalOrganograma() {
        return exibirInfAvalOrganograma;
    }

    /**
     * Sets the value of the exibirInfAvalOrganograma property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExibirInfAvalOrganograma(Boolean value) {
        this.exibirInfAvalOrganograma = value;
    }

    /**
     * Gets the value of the exibirInfPlrOrganograma property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExibirInfPlrOrganograma() {
        return exibirInfPlrOrganograma;
    }

    /**
     * Sets the value of the exibirInfPlrOrganograma property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExibirInfPlrOrganograma(Boolean value) {
        this.exibirInfPlrOrganograma = value;
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
     * Gets the value of the infAvalOrganogramaEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfAvalOrganogramaEmpresa() {
        return infAvalOrganogramaEmpresa;
    }

    /**
     * Sets the value of the infAvalOrganogramaEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfAvalOrganogramaEmpresa(String value) {
        this.infAvalOrganogramaEmpresa = value;
    }

    /**
     * Gets the value of the infPlrOrganogramaEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfPlrOrganogramaEmpresa() {
        return infPlrOrganogramaEmpresa;
    }

    /**
     * Sets the value of the infPlrOrganogramaEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfPlrOrganogramaEmpresa(String value) {
        this.infPlrOrganogramaEmpresa = value;
    }

    /**
     * Gets the value of the percentualPdc property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getPercentualPdc() {
        return percentualPdc;
    }

    /**
     * Sets the value of the percentualPdc property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setPercentualPdc(Float value) {
        this.percentualPdc = value;
    }

}
