
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subordinacao complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subordinacao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="areaRdo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="celulaTrabalho" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subordinacaoDescricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subordinacaoId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subordinacao", propOrder = {
    "areaRdo",
    "ativo",
    "celulaTrabalho",
    "codigo",
    "descricao",
    "subordinacaoDescricao",
    "subordinacaoId"
})
public class Subordinacao {

    protected String areaRdo;
    protected String ativo;
    protected String celulaTrabalho;
    protected String codigo;
    protected String descricao;
    protected String subordinacaoDescricao;
    protected Integer subordinacaoId;

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
     * Gets the value of the celulaTrabalho property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCelulaTrabalho() {
        return celulaTrabalho;
    }

    /**
     * Sets the value of the celulaTrabalho property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCelulaTrabalho(String value) {
        this.celulaTrabalho = value;
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
     * Gets the value of the subordinacaoDescricao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubordinacaoDescricao() {
        return subordinacaoDescricao;
    }

    /**
     * Sets the value of the subordinacaoDescricao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubordinacaoDescricao(String value) {
        this.subordinacaoDescricao = value;
    }

    /**
     * Gets the value of the subordinacaoId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSubordinacaoId() {
        return subordinacaoId;
    }

    /**
     * Sets the value of the subordinacaoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSubordinacaoId(Integer value) {
        this.subordinacaoId = value;
    }

}
