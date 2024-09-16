
package br.com.nextage.rmaweb.ws.rhweb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for configImpPess complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="configImpPess">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="atulizaTipoMoArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="atulizaTipoMoFuncao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="configImpPessLogicas" type="{http://ws.rhweb_ws.nextage.com.br/}configImpPessLogica" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ws.rhweb_ws.nextage.com.br/}configuracao" minOccurs="0"/>
 *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="importacaoEfetivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importacaoSubcontratado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="naoAtualizarDtPreenchidas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pertenceUsuario" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="startLine" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configImpPess", propOrder = {
    "ativo",
    "atulizaTipoMoArea",
    "atulizaTipoMoFuncao",
    "codigo",
    "configImpPessLogicas",
    "configuracao",
    "descricao",
    "id",
    "importacaoEfetivo",
    "importacaoSubcontratado",
    "naoAtualizarDtPreenchidas",
    "pertenceUsuario",
    "startLine"
})
public class ConfigImpPess {

    protected String ativo;
    protected String atulizaTipoMoArea;
    protected String atulizaTipoMoFuncao;
    protected String codigo;
    @XmlElement(nillable = true)
    protected List<ConfigImpPessLogica> configImpPessLogicas;
    @XmlElement(namespace = "http://ws.rhweb_ws.nextage.com.br/")
    protected Configuracao configuracao;
    protected String descricao;
    protected Integer id;
    protected String importacaoEfetivo;
    protected String importacaoSubcontratado;
    protected String naoAtualizarDtPreenchidas;
    protected Boolean pertenceUsuario;
    protected Integer startLine;

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
     * Gets the value of the atulizaTipoMoArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtulizaTipoMoArea() {
        return atulizaTipoMoArea;
    }

    /**
     * Sets the value of the atulizaTipoMoArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtulizaTipoMoArea(String value) {
        this.atulizaTipoMoArea = value;
    }

    /**
     * Gets the value of the atulizaTipoMoFuncao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtulizaTipoMoFuncao() {
        return atulizaTipoMoFuncao;
    }

    /**
     * Sets the value of the atulizaTipoMoFuncao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtulizaTipoMoFuncao(String value) {
        this.atulizaTipoMoFuncao = value;
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
     * Gets the value of the configImpPessLogicas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the configImpPessLogicas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConfigImpPessLogicas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConfigImpPessLogica }
     * 
     * 
     */
    public List<ConfigImpPessLogica> getConfigImpPessLogicas() {
        if (configImpPessLogicas == null) {
            configImpPessLogicas = new ArrayList<ConfigImpPessLogica>();
        }
        return this.configImpPessLogicas;
    }

    /**
     * Gets the value of the configuracao property.
     * 
     * @return
     *     possible object is
     *     {@link Configuracao }
     *     
     */
    public Configuracao getConfiguracao() {
        return configuracao;
    }

    /**
     * Sets the value of the configuracao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Configuracao }
     *     
     */
    public void setConfiguracao(Configuracao value) {
        this.configuracao = value;
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
     * Gets the value of the importacaoEfetivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImportacaoEfetivo() {
        return importacaoEfetivo;
    }

    /**
     * Sets the value of the importacaoEfetivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImportacaoEfetivo(String value) {
        this.importacaoEfetivo = value;
    }

    /**
     * Gets the value of the importacaoSubcontratado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImportacaoSubcontratado() {
        return importacaoSubcontratado;
    }

    /**
     * Sets the value of the importacaoSubcontratado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImportacaoSubcontratado(String value) {
        this.importacaoSubcontratado = value;
    }

    /**
     * Gets the value of the naoAtualizarDtPreenchidas property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaoAtualizarDtPreenchidas() {
        return naoAtualizarDtPreenchidas;
    }

    /**
     * Sets the value of the naoAtualizarDtPreenchidas property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaoAtualizarDtPreenchidas(String value) {
        this.naoAtualizarDtPreenchidas = value;
    }

    /**
     * Gets the value of the pertenceUsuario property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPertenceUsuario() {
        return pertenceUsuario;
    }

    /**
     * Sets the value of the pertenceUsuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPertenceUsuario(Boolean value) {
        this.pertenceUsuario = value;
    }

    /**
     * Gets the value of the startLine property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStartLine() {
        return startLine;
    }

    /**
     * Sets the value of the startLine property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStartLine(Integer value) {
        this.startLine = value;
    }

}
