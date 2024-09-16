
package br.com.nextage.rmaweb.ws.cpweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eapMultiEmpresa complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="eapMultiEmpresa">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="caminhoRhWeb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="centro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="multiEmpresa" type="{http://ws.cpweb.nextage.com.br/}multiEmpresa" minOccurs="0"/>
 *         &lt;element name="pertenceUsuario" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eapMultiEmpresa", propOrder = {
    "caminhoRhWeb",
    "centro",
    "codigo",
    "descricao",
    "id",
        "multiEmpresa",
        "pertenceUsuario"
})
public class EapMultiEmpresa {

    protected String caminhoRhWeb;
    protected String centro;
    protected String codigo;
    protected String descricao;
    protected Integer id;
    protected MultiEmpresa multiEmpresa;
    protected boolean pertenceUsuario;

    /**
     * Gets the value of the caminhoRhWeb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaminhoRhWeb() {
        return caminhoRhWeb;
    }

    /**
     * Sets the value of the caminhoRhWeb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaminhoRhWeb(String value) {
        this.caminhoRhWeb = value;
    }

    /**
     * Gets the value of the centro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCentro() {
        return centro;
    }

    /**
     * Sets the value of the centro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCentro(String value) {
        this.centro = value;
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
     * Gets the value of the multiEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link MultiEmpresa }
     *     
     */
    public MultiEmpresa getMultiEmpresa() {
        return multiEmpresa;
    }

    /**
     * Sets the value of the multiEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultiEmpresa }
     *     
     */
    public void setMultiEmpresa(MultiEmpresa value) {
        this.multiEmpresa = value;
    }

    /**
     * Gets the value of the pertenceUsuario property.
     */
    public boolean isPertenceUsuario() {
        return pertenceUsuario;
    }

    /**
     * Sets the value of the pertenceUsuario property.
     */
    public void setPertenceUsuario(boolean value) {
        this.pertenceUsuario = value;
    }

}
