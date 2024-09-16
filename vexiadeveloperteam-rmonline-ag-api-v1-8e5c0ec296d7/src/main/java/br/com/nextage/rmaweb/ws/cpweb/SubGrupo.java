
package br.com.nextage.rmaweb.ws.cpweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subGrupo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subGrupo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="grupo" type="{http://ws.cpweb.nextage.com.br/}grupo" minOccurs="0"/>
 *         &lt;element name="responsavelCautela" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subGrupoId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subGrupo", propOrder = {
    "codigo",
    "descricao",
    "grupo",
    "responsavelCautela",
    "subGrupoId"
})
public class SubGrupo {

    protected String codigo;
    protected String descricao;
    protected Grupo grupo;
    protected String responsavelCautela;
    protected Integer subGrupoId;

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
     * Gets the value of the grupo property.
     * 
     * @return
     *     possible object is
     *     {@link Grupo }
     *     
     */
    public Grupo getGrupo() {
        return grupo;
    }

    /**
     * Sets the value of the grupo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Grupo }
     *     
     */
    public void setGrupo(Grupo value) {
        this.grupo = value;
    }

    /**
     * Gets the value of the responsavelCautela property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsavelCautela() {
        return responsavelCautela;
    }

    /**
     * Sets the value of the responsavelCautela property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsavelCautela(String value) {
        this.responsavelCautela = value;
    }

    /**
     * Gets the value of the subGrupoId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSubGrupoId() {
        return subGrupoId;
    }

    /**
     * Sets the value of the subGrupoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSubGrupoId(Integer value) {
        this.subGrupoId = value;
    }

}
