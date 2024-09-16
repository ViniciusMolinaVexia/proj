
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for campoMaster complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="campoMaster">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="allowNegative" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chaveLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="classePai" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="escopoCampo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exibirNoHistorico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isFiltro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maxChar" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="modoExibicao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nivelConsulta" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="pertenceUsuario" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="propriedadeComboAux" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="propriedadeEntity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="propriedadeExibicao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="propriedadeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reinicializarContratacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tamanhoCampo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "campoMaster", propOrder = {
    "allowNegative",
    "chaveLabel",
    "classePai",
    "descricao",
    "escopoCampo",
    "exibirNoHistorico",
    "id",
    "idField",
    "isFiltro",
    "maxChar",
    "modoExibicao",
    "nivelConsulta",
    "pertenceUsuario",
    "propriedadeComboAux",
    "propriedadeEntity",
    "propriedadeExibicao",
    "propriedadeId",
    "reinicializarContratacao",
    "tamanhoCampo"
})
@XmlSeeAlso({
    Campo.class
})
public class CampoMaster {

    protected String allowNegative;
    protected String chaveLabel;
    protected String classePai;
    protected String descricao;
    protected String escopoCampo;
    protected String exibirNoHistorico;
    protected Integer id;
    protected String idField;
    protected String isFiltro;
    protected Integer maxChar;
    protected String modoExibicao;
    protected Integer nivelConsulta;
    protected Boolean pertenceUsuario;
    protected String propriedadeComboAux;
    protected String propriedadeEntity;
    protected String propriedadeExibicao;
    protected String propriedadeId;
    protected String reinicializarContratacao;
    protected Integer tamanhoCampo;

    /**
     * Gets the value of the allowNegative property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllowNegative() {
        return allowNegative;
    }

    /**
     * Sets the value of the allowNegative property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllowNegative(String value) {
        this.allowNegative = value;
    }

    /**
     * Gets the value of the chaveLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChaveLabel() {
        return chaveLabel;
    }

    /**
     * Sets the value of the chaveLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChaveLabel(String value) {
        this.chaveLabel = value;
    }

    /**
     * Gets the value of the classePai property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassePai() {
        return classePai;
    }

    /**
     * Sets the value of the classePai property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassePai(String value) {
        this.classePai = value;
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
     * Gets the value of the escopoCampo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEscopoCampo() {
        return escopoCampo;
    }

    /**
     * Sets the value of the escopoCampo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEscopoCampo(String value) {
        this.escopoCampo = value;
    }

    /**
     * Gets the value of the exibirNoHistorico property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExibirNoHistorico() {
        return exibirNoHistorico;
    }

    /**
     * Sets the value of the exibirNoHistorico property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExibirNoHistorico(String value) {
        this.exibirNoHistorico = value;
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
     * Gets the value of the idField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdField() {
        return idField;
    }

    /**
     * Sets the value of the idField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdField(String value) {
        this.idField = value;
    }

    /**
     * Gets the value of the isFiltro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsFiltro() {
        return isFiltro;
    }

    /**
     * Sets the value of the isFiltro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsFiltro(String value) {
        this.isFiltro = value;
    }

    /**
     * Gets the value of the maxChar property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxChar() {
        return maxChar;
    }

    /**
     * Sets the value of the maxChar property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxChar(Integer value) {
        this.maxChar = value;
    }

    /**
     * Gets the value of the modoExibicao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModoExibicao() {
        return modoExibicao;
    }

    /**
     * Sets the value of the modoExibicao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModoExibicao(String value) {
        this.modoExibicao = value;
    }

    /**
     * Gets the value of the nivelConsulta property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNivelConsulta() {
        return nivelConsulta;
    }

    /**
     * Sets the value of the nivelConsulta property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNivelConsulta(Integer value) {
        this.nivelConsulta = value;
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
     * Gets the value of the propriedadeComboAux property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropriedadeComboAux() {
        return propriedadeComboAux;
    }

    /**
     * Sets the value of the propriedadeComboAux property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropriedadeComboAux(String value) {
        this.propriedadeComboAux = value;
    }

    /**
     * Gets the value of the propriedadeEntity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropriedadeEntity() {
        return propriedadeEntity;
    }

    /**
     * Sets the value of the propriedadeEntity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropriedadeEntity(String value) {
        this.propriedadeEntity = value;
    }

    /**
     * Gets the value of the propriedadeExibicao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropriedadeExibicao() {
        return propriedadeExibicao;
    }

    /**
     * Sets the value of the propriedadeExibicao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropriedadeExibicao(String value) {
        this.propriedadeExibicao = value;
    }

    /**
     * Gets the value of the propriedadeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropriedadeId() {
        return propriedadeId;
    }

    /**
     * Sets the value of the propriedadeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropriedadeId(String value) {
        this.propriedadeId = value;
    }

    /**
     * Gets the value of the reinicializarContratacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReinicializarContratacao() {
        return reinicializarContratacao;
    }

    /**
     * Sets the value of the reinicializarContratacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReinicializarContratacao(String value) {
        this.reinicializarContratacao = value;
    }

    /**
     * Gets the value of the tamanhoCampo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTamanhoCampo() {
        return tamanhoCampo;
    }

    /**
     * Sets the value of the tamanhoCampo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTamanhoCampo(Integer value) {
        this.tamanhoCampo = value;
    }

}
