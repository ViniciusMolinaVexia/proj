
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for configuracaoPessoa complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="configuracaoPessoa">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bloqContratacaoVagaLimite" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="bloqDataPrevEntrega" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="bloqDtPrevEntregaFim" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="bloqDtPrevEntregaIni" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="bloqReqVagaQtdeIndic" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="caminhoServidorExportFotos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="excluirAbaBeneficiosVaga" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="horasPadraoAfastamento" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="mostrarDataDiferencaDias" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="mostrarDataDiferencaDiasObservacao" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="notificarEapVaga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="notificarRecrutadorVaga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preencheSalarioContratacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="propriedadeEntityBaseFamiliar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="propriedadeEntityOrigemMo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="propriedadeEntityRmo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qtdeDiasBloqDataPrevEntrega" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configuracaoPessoa", propOrder = {
    "bloqContratacaoVagaLimite",
    "bloqDataPrevEntrega",
    "bloqDtPrevEntregaFim",
    "bloqDtPrevEntregaIni",
    "bloqReqVagaQtdeIndic",
    "caminhoServidorExportFotos",
    "excluirAbaBeneficiosVaga",
    "horasPadraoAfastamento",
    "id",
    "mostrarDataDiferencaDias",
    "mostrarDataDiferencaDiasObservacao",
    "notificarEapVaga",
    "notificarRecrutadorVaga",
    "preencheSalarioContratacao",
    "propriedadeEntityBaseFamiliar",
    "propriedadeEntityOrigemMo",
    "propriedadeEntityRmo",
    "qtdeDiasBloqDataPrevEntrega"
})
public class ConfiguracaoPessoa {

    protected Boolean bloqContratacaoVagaLimite;
    protected Boolean bloqDataPrevEntrega;
    protected Integer bloqDtPrevEntregaFim;
    protected Integer bloqDtPrevEntregaIni;
    protected Boolean bloqReqVagaQtdeIndic;
    protected String caminhoServidorExportFotos;
    protected Boolean excluirAbaBeneficiosVaga;
    protected Double horasPadraoAfastamento;
    protected Integer id;
    protected Boolean mostrarDataDiferencaDias;
    protected Boolean mostrarDataDiferencaDiasObservacao;
    protected String notificarEapVaga;
    protected String notificarRecrutadorVaga;
    protected String preencheSalarioContratacao;
    protected String propriedadeEntityBaseFamiliar;
    protected String propriedadeEntityOrigemMo;
    protected String propriedadeEntityRmo;
    protected Integer qtdeDiasBloqDataPrevEntrega;

    /**
     * Gets the value of the bloqContratacaoVagaLimite property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBloqContratacaoVagaLimite() {
        return bloqContratacaoVagaLimite;
    }

    /**
     * Sets the value of the bloqContratacaoVagaLimite property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBloqContratacaoVagaLimite(Boolean value) {
        this.bloqContratacaoVagaLimite = value;
    }

    /**
     * Gets the value of the bloqDataPrevEntrega property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBloqDataPrevEntrega() {
        return bloqDataPrevEntrega;
    }

    /**
     * Sets the value of the bloqDataPrevEntrega property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBloqDataPrevEntrega(Boolean value) {
        this.bloqDataPrevEntrega = value;
    }

    /**
     * Gets the value of the bloqDtPrevEntregaFim property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBloqDtPrevEntregaFim() {
        return bloqDtPrevEntregaFim;
    }

    /**
     * Sets the value of the bloqDtPrevEntregaFim property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBloqDtPrevEntregaFim(Integer value) {
        this.bloqDtPrevEntregaFim = value;
    }

    /**
     * Gets the value of the bloqDtPrevEntregaIni property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBloqDtPrevEntregaIni() {
        return bloqDtPrevEntregaIni;
    }

    /**
     * Sets the value of the bloqDtPrevEntregaIni property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBloqDtPrevEntregaIni(Integer value) {
        this.bloqDtPrevEntregaIni = value;
    }

    /**
     * Gets the value of the bloqReqVagaQtdeIndic property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBloqReqVagaQtdeIndic() {
        return bloqReqVagaQtdeIndic;
    }

    /**
     * Sets the value of the bloqReqVagaQtdeIndic property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBloqReqVagaQtdeIndic(Boolean value) {
        this.bloqReqVagaQtdeIndic = value;
    }

    /**
     * Gets the value of the caminhoServidorExportFotos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaminhoServidorExportFotos() {
        return caminhoServidorExportFotos;
    }

    /**
     * Sets the value of the caminhoServidorExportFotos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaminhoServidorExportFotos(String value) {
        this.caminhoServidorExportFotos = value;
    }

    /**
     * Gets the value of the excluirAbaBeneficiosVaga property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExcluirAbaBeneficiosVaga() {
        return excluirAbaBeneficiosVaga;
    }

    /**
     * Sets the value of the excluirAbaBeneficiosVaga property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExcluirAbaBeneficiosVaga(Boolean value) {
        this.excluirAbaBeneficiosVaga = value;
    }

    /**
     * Gets the value of the horasPadraoAfastamento property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getHorasPadraoAfastamento() {
        return horasPadraoAfastamento;
    }

    /**
     * Sets the value of the horasPadraoAfastamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setHorasPadraoAfastamento(Double value) {
        this.horasPadraoAfastamento = value;
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
     * Gets the value of the mostrarDataDiferencaDias property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMostrarDataDiferencaDias() {
        return mostrarDataDiferencaDias;
    }

    /**
     * Sets the value of the mostrarDataDiferencaDias property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMostrarDataDiferencaDias(Boolean value) {
        this.mostrarDataDiferencaDias = value;
    }

    /**
     * Gets the value of the mostrarDataDiferencaDiasObservacao property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMostrarDataDiferencaDiasObservacao() {
        return mostrarDataDiferencaDiasObservacao;
    }

    /**
     * Sets the value of the mostrarDataDiferencaDiasObservacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMostrarDataDiferencaDiasObservacao(Boolean value) {
        this.mostrarDataDiferencaDiasObservacao = value;
    }

    /**
     * Gets the value of the notificarEapVaga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotificarEapVaga() {
        return notificarEapVaga;
    }

    /**
     * Sets the value of the notificarEapVaga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificarEapVaga(String value) {
        this.notificarEapVaga = value;
    }

    /**
     * Gets the value of the notificarRecrutadorVaga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotificarRecrutadorVaga() {
        return notificarRecrutadorVaga;
    }

    /**
     * Sets the value of the notificarRecrutadorVaga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificarRecrutadorVaga(String value) {
        this.notificarRecrutadorVaga = value;
    }

    /**
     * Gets the value of the preencheSalarioContratacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreencheSalarioContratacao() {
        return preencheSalarioContratacao;
    }

    /**
     * Sets the value of the preencheSalarioContratacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreencheSalarioContratacao(String value) {
        this.preencheSalarioContratacao = value;
    }

    /**
     * Gets the value of the propriedadeEntityBaseFamiliar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropriedadeEntityBaseFamiliar() {
        return propriedadeEntityBaseFamiliar;
    }

    /**
     * Sets the value of the propriedadeEntityBaseFamiliar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropriedadeEntityBaseFamiliar(String value) {
        this.propriedadeEntityBaseFamiliar = value;
    }

    /**
     * Gets the value of the propriedadeEntityOrigemMo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropriedadeEntityOrigemMo() {
        return propriedadeEntityOrigemMo;
    }

    /**
     * Sets the value of the propriedadeEntityOrigemMo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropriedadeEntityOrigemMo(String value) {
        this.propriedadeEntityOrigemMo = value;
    }

    /**
     * Gets the value of the propriedadeEntityRmo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropriedadeEntityRmo() {
        return propriedadeEntityRmo;
    }

    /**
     * Sets the value of the propriedadeEntityRmo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropriedadeEntityRmo(String value) {
        this.propriedadeEntityRmo = value;
    }

    /**
     * Gets the value of the qtdeDiasBloqDataPrevEntrega property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQtdeDiasBloqDataPrevEntrega() {
        return qtdeDiasBloqDataPrevEntrega;
    }

    /**
     * Sets the value of the qtdeDiasBloqDataPrevEntrega property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQtdeDiasBloqDataPrevEntrega(Integer value) {
        this.qtdeDiasBloqDataPrevEntrega = value;
    }

}
