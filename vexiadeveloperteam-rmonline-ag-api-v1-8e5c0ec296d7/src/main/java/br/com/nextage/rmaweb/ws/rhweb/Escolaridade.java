
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for escolaridade complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="escolaridade">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cdGrauInstrucao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flSelecionavel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "escolaridade", propOrder = {
    "cdGrauInstrucao",
    "descricao",
    "flSelecionavel",
    "id"
})
public class Escolaridade {

    protected String cdGrauInstrucao;
    protected String descricao;
    protected String flSelecionavel;
    protected Integer id;

    /**
     * Gets the value of the cdGrauInstrucao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdGrauInstrucao() {
        return cdGrauInstrucao;
    }

    /**
     * Sets the value of the cdGrauInstrucao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdGrauInstrucao(String value) {
        this.cdGrauInstrucao = value;
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
     * Gets the value of the flSelecionavel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlSelecionavel() {
        return flSelecionavel;
    }

    /**
     * Sets the value of the flSelecionavel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlSelecionavel(String value) {
        this.flSelecionavel = value;
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

}
