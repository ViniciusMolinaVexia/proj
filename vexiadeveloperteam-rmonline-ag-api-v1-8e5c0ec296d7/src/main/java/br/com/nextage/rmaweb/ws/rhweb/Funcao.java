
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for funcao complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="funcao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="acordoColetivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="classificacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="conselhoRegional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cursoTreinamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cvObrigatorio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descricaoAtividades" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descricaoCompetencias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="educacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="epiColetivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="epiIndivEventual" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="epiIndivHabitual" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="experiencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="funcaoId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="grupoDeFuncao" type="{http://ws.rhweb_ws.nextage.com.br/}grupoDeFuncao" minOccurs="0"/>
 *         &lt;element name="habilidades" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isEncarregado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="medidasControle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="medidasPreventivas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="obrigacoes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="procedimentosEspecificos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="riscoOperAmbientais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salario" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="sgCargo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoMo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "funcao", propOrder = {
    "acordoColetivo",
    "ativo",
    "classificacao",
    "codigo",
    "conselhoRegional",
    "cursoTreinamento",
    "cvObrigatorio",
    "descricao",
    "descricaoAtividades",
    "descricaoCompetencias",
    "educacao",
    "epiColetivo",
    "epiIndivEventual",
    "epiIndivHabitual",
    "experiencia",
    "funcaoId",
    "grupoDeFuncao",
    "habilidades",
    "isEncarregado",
    "medidasControle",
    "medidasPreventivas",
    "obrigacoes",
    "procedimentosEspecificos",
    "riscoOperAmbientais",
    "salario",
    "sgCargo",
    "tipoMo"
})
public class Funcao {

    protected String acordoColetivo;
    protected String ativo;
    protected String classificacao;
    protected String codigo;
    protected String conselhoRegional;
    protected String cursoTreinamento;
    protected String cvObrigatorio;
    protected String descricao;
    protected String descricaoAtividades;
    protected String descricaoCompetencias;
    protected String educacao;
    protected String epiColetivo;
    protected String epiIndivEventual;
    protected String epiIndivHabitual;
    protected String experiencia;
    protected Integer funcaoId;
    protected GrupoDeFuncao grupoDeFuncao;
    protected String habilidades;
    protected String isEncarregado;
    protected String medidasControle;
    protected String medidasPreventivas;
    protected String obrigacoes;
    protected String procedimentosEspecificos;
    protected String riscoOperAmbientais;
    protected Double salario;
    protected String sgCargo;
    protected String tipoMo;

    /**
     * Gets the value of the acordoColetivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcordoColetivo() {
        return acordoColetivo;
    }

    /**
     * Sets the value of the acordoColetivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcordoColetivo(String value) {
        this.acordoColetivo = value;
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
     * Gets the value of the classificacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassificacao() {
        return classificacao;
    }

    /**
     * Sets the value of the classificacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassificacao(String value) {
        this.classificacao = value;
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
     * Gets the value of the cvObrigatorio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCvObrigatorio() {
        return cvObrigatorio;
    }

    /**
     * Sets the value of the cvObrigatorio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCvObrigatorio(String value) {
        this.cvObrigatorio = value;
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
     * Gets the value of the epiColetivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEpiColetivo() {
        return epiColetivo;
    }

    /**
     * Sets the value of the epiColetivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEpiColetivo(String value) {
        this.epiColetivo = value;
    }

    /**
     * Gets the value of the epiIndivEventual property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEpiIndivEventual() {
        return epiIndivEventual;
    }

    /**
     * Sets the value of the epiIndivEventual property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEpiIndivEventual(String value) {
        this.epiIndivEventual = value;
    }

    /**
     * Gets the value of the epiIndivHabitual property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEpiIndivHabitual() {
        return epiIndivHabitual;
    }

    /**
     * Sets the value of the epiIndivHabitual property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEpiIndivHabitual(String value) {
        this.epiIndivHabitual = value;
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
     * Gets the value of the funcaoId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFuncaoId() {
        return funcaoId;
    }

    /**
     * Sets the value of the funcaoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFuncaoId(Integer value) {
        this.funcaoId = value;
    }

    /**
     * Gets the value of the grupoDeFuncao property.
     * 
     * @return
     *     possible object is
     *     {@link GrupoDeFuncao }
     *     
     */
    public GrupoDeFuncao getGrupoDeFuncao() {
        return grupoDeFuncao;
    }

    /**
     * Sets the value of the grupoDeFuncao property.
     * 
     * @param value
     *     allowed object is
     *     {@link GrupoDeFuncao }
     *     
     */
    public void setGrupoDeFuncao(GrupoDeFuncao value) {
        this.grupoDeFuncao = value;
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
     * Gets the value of the isEncarregado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsEncarregado() {
        return isEncarregado;
    }

    /**
     * Sets the value of the isEncarregado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsEncarregado(String value) {
        this.isEncarregado = value;
    }

    /**
     * Gets the value of the medidasControle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedidasControle() {
        return medidasControle;
    }

    /**
     * Sets the value of the medidasControle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedidasControle(String value) {
        this.medidasControle = value;
    }

    /**
     * Gets the value of the medidasPreventivas property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedidasPreventivas() {
        return medidasPreventivas;
    }

    /**
     * Sets the value of the medidasPreventivas property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedidasPreventivas(String value) {
        this.medidasPreventivas = value;
    }

    /**
     * Gets the value of the obrigacoes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObrigacoes() {
        return obrigacoes;
    }

    /**
     * Sets the value of the obrigacoes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObrigacoes(String value) {
        this.obrigacoes = value;
    }

    /**
     * Gets the value of the procedimentosEspecificos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcedimentosEspecificos() {
        return procedimentosEspecificos;
    }

    /**
     * Sets the value of the procedimentosEspecificos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcedimentosEspecificos(String value) {
        this.procedimentosEspecificos = value;
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

    /**
     * Gets the value of the salario property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSalario() {
        return salario;
    }

    /**
     * Sets the value of the salario property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSalario(Double value) {
        this.salario = value;
    }

    /**
     * Gets the value of the sgCargo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSgCargo() {
        return sgCargo;
    }

    /**
     * Sets the value of the sgCargo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSgCargo(String value) {
        this.sgCargo = value;
    }

    /**
     * Gets the value of the tipoMo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoMo() {
        return tipoMo;
    }

    /**
     * Sets the value of the tipoMo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoMo(String value) {
        this.tipoMo = value;
    }

}
