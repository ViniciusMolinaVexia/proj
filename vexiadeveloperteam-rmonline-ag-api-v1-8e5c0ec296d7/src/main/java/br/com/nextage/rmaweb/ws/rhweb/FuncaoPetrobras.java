
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for funcaoPetrobras complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="funcaoPetrobras">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="conselhoRegional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cursoTreinamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descricaoAtividades" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descricaoCompetencias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="educacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="experiencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="funcaoPbId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="habilidades" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="normasSeguranca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="riscoOperAmbientais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "funcaoPetrobras", propOrder = {
    "ativo",
    "codigo",
    "conselhoRegional",
    "cursoTreinamento",
    "descricao",
    "descricaoAtividades",
    "descricaoCompetencias",
    "educacao",
    "experiencia",
    "funcaoPbId",
    "habilidades",
    "normasSeguranca",
    "riscoOperAmbientais"
})
public class FuncaoPetrobras {

    protected String ativo;
    protected String codigo;
    protected String conselhoRegional;
    protected String cursoTreinamento;
    protected String descricao;
    protected String descricaoAtividades;
    protected String descricaoCompetencias;
    protected String educacao;
    protected String experiencia;
    protected Integer funcaoPbId;
    protected String habilidades;
    protected String normasSeguranca;
    protected String riscoOperAmbientais;

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
     * Gets the value of the conselhoRegional property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConselhoRegional() {
        return conselhoRegional;
    }

    /**
     * Sets the value of the conselhoRegional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConselhoRegional(String value) {
        this.conselhoRegional = value;
    }

    /**
     * Gets the value of the cursoTreinamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCursoTreinamento() {
        return cursoTreinamento;
    }

    /**
     * Sets the value of the cursoTreinamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCursoTreinamento(String value) {
        this.cursoTreinamento = value;
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
     * Gets the value of the descricaoAtividades property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescricaoAtividades() {
        return descricaoAtividades;
    }

    /**
     * Sets the value of the descricaoAtividades property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescricaoAtividades(String value) {
        this.descricaoAtividades = value;
    }

    /**
     * Gets the value of the descricaoCompetencias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescricaoCompetencias() {
        return descricaoCompetencias;
    }

    /**
     * Sets the value of the descricaoCompetencias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescricaoCompetencias(String value) {
        this.descricaoCompetencias = value;
    }

    /**
     * Gets the value of the educacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEducacao() {
        return educacao;
    }

    /**
     * Sets the value of the educacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEducacao(String value) {
        this.educacao = value;
    }

    /**
     * Gets the value of the experiencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExperiencia() {
        return experiencia;
    }

    /**
     * Sets the value of the experiencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExperiencia(String value) {
        this.experiencia = value;
    }

    /**
     * Gets the value of the funcaoPbId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFuncaoPbId() {
        return funcaoPbId;
    }

    /**
     * Sets the value of the funcaoPbId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFuncaoPbId(Integer value) {
        this.funcaoPbId = value;
    }

    /**
     * Gets the value of the habilidades property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHabilidades() {
        return habilidades;
    }

    /**
     * Sets the value of the habilidades property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHabilidades(String value) {
        this.habilidades = value;
    }

    /**
     * Gets the value of the normasSeguranca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNormasSeguranca() {
        return normasSeguranca;
    }

    /**
     * Sets the value of the normasSeguranca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNormasSeguranca(String value) {
        this.normasSeguranca = value;
    }

    /**
     * Gets the value of the riscoOperAmbientais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRiscoOperAmbientais() {
        return riscoOperAmbientais;
    }

    /**
     * Sets the value of the riscoOperAmbientais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRiscoOperAmbientais(String value) {
        this.riscoOperAmbientais = value;
    }

}
