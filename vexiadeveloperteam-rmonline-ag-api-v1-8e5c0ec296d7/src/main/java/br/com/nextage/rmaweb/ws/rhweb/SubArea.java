
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subArea complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subArea">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="areaRdo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gerente" type="{http://ws.rhweb_ws.nextage.com.br/}pessoa" minOccurs="0"/>
 *         &lt;element name="subAreaDescricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subAreaId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subArea", propOrder = {
    "areaRdo",
    "ativo",
    "codigo",
    "descricao",
    "gerente",
    "subAreaDescricao",
    "subAreaId"
})
public class SubArea {

    protected String areaRdo;
    protected String ativo;
    protected String codigo;
    protected String descricao;
    protected Pessoa gerente;
    protected String subAreaDescricao;
    protected Integer subAreaId;

    /**
     * Gets the value of the areaRdo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaRdo() {
        return areaRdo;
    }

    /**
     * Sets the value of the areaRdo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaRdo(String value) {
        this.areaRdo = value;
    }

    /**
     * Gets the value of the ativo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtivo() {
        return ativo;
    }

    /**
     * Sets the value of the ativo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtivo(String value) {
        this.ativo = value;
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
     * Gets the value of the gerente property.
     * 
     * @return
     *     possible object is
     *     {@link Pessoa }
     *     
     */
    public Pessoa getGerente() {
        return gerente;
    }

    /**
     * Sets the value of the gerente property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pessoa }
     *     
     */
    public void setGerente(Pessoa value) {
        this.gerente = value;
    }

    /**
     * Gets the value of the subAreaDescricao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubAreaDescricao() {
        return subAreaDescricao;
    }

    /**
     * Sets the value of the subAreaDescricao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubAreaDescricao(String value) {
        this.subAreaDescricao = value;
    }

    /**
     * Gets the value of the subAreaId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSubAreaId() {
        return subAreaId;
    }

    /**
     * Sets the value of the subAreaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSubAreaId(Integer value) {
        this.subAreaId = value;
    }

}
