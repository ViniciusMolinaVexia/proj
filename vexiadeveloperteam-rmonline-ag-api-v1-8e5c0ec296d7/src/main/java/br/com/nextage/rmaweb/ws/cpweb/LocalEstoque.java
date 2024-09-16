
package br.com.nextage.rmaweb.ws.cpweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for localEstoque complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="localEstoque">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eapMultiEmpresa" type="{http://ws.cpweb.nextage.com.br/}eapMultiEmpresa" minOccurs="0"/>
 *         &lt;element name="localEstoqueEap" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="localEstoqueId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
@XmlType(name = "localEstoque", propOrder = {
    "codigo",
    "descricao",
    "eapMultiEmpresa",
    "localEstoqueEap",
    "localEstoqueId",
    "pertenceUsuario"
})
public class LocalEstoque {

    protected String codigo;
    protected String descricao;
    protected EapMultiEmpresa eapMultiEmpresa;
    protected String localEstoqueEap;
    protected Integer localEstoqueId;
    protected boolean pertenceUsuario;

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
     * Gets the value of the eapMultiEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link EapMultiEmpresa }
     *     
     */
    public EapMultiEmpresa getEapMultiEmpresa() {
        return eapMultiEmpresa;
    }

    /**
     * Sets the value of the eapMultiEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link EapMultiEmpresa }
     *     
     */
    public void setEapMultiEmpresa(EapMultiEmpresa value) {
        this.eapMultiEmpresa = value;
    }

    /**
     * Gets the value of the localEstoqueEap property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalEstoqueEap() {
        return localEstoqueEap;
    }

    /**
     * Sets the value of the localEstoqueEap property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalEstoqueEap(String value) {
        this.localEstoqueEap = value;
    }

    /**
     * Gets the value of the localEstoqueId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLocalEstoqueId() {
        return localEstoqueId;
    }

    /**
     * Sets the value of the localEstoqueId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLocalEstoqueId(Integer value) {
        this.localEstoqueId = value;
    }

    /**
     * Gets the value of the pertenceUsuario property.
     * 
     */
    public boolean isPertenceUsuario() {
        return pertenceUsuario;
    }

    /**
     * Sets the value of the pertenceUsuario property.
     * 
     */
    public void setPertenceUsuario(boolean value) {
        this.pertenceUsuario = value;
    }

}
