
package br.com.nextage.rmaweb.ws.cpweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sincEquipamentoVo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sincEquipamentoVo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigoRequisicao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="encarSolicitaRmMobile" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="epi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="equipamento" type="{http://ws.cpweb.nextage.com.br/}equipamento" minOccurs="0"/>
 *         &lt;element name="funcionarioEap" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificadorRmMaterial" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="localEstoque" type="{http://ws.cpweb.nextage.com.br/}localEstoque" minOccurs="0"/>
 *         &lt;element name="naoEnviarMovimentacao" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="nomeUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="origem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="patrimoniado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prefixoEquipamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quantidade" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="referenciaCorresponsavel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="referenciaPessoa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoEquipamento" type="{http://ws.cpweb.nextage.com.br/}tipoEquipamento" minOccurs="0"/>
 *         &lt;element name="unidadeMedida" type="{http://ws.cpweb.nextage.com.br/}unidadeMedida" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sincEquipamentoVo", propOrder = {
    "codigoRequisicao",
    "encarSolicitaRmMobile",
    "epi",
    "equipamento",
    "funcionarioEap",
    "identificadorRmMaterial",
    "localEstoque",
    "naoEnviarMovimentacao",
    "nomeUsuario",
    "origem",
    "patrimoniado",
    "prefixoEquipamento",
    "quantidade",
    "referenciaCorresponsavel",
    "referenciaPessoa",
    "tipoEquipamento",
    "unidadeMedida"
})
public class SincEquipamentoVo {

    protected String codigoRequisicao;
    protected Boolean encarSolicitaRmMobile;
    protected String epi;
    protected Equipamento equipamento;
    protected String funcionarioEap;
    protected Integer identificadorRmMaterial;
    protected LocalEstoque localEstoque;
    protected Boolean naoEnviarMovimentacao;
    protected String nomeUsuario;
    protected String origem;
    protected String patrimoniado;
    protected String prefixoEquipamento;
    protected Double quantidade;
    protected String referenciaCorresponsavel;
    protected String referenciaPessoa;
    protected TipoEquipamento tipoEquipamento;
    protected UnidadeMedida unidadeMedida;

    /**
     * Gets the value of the codigoRequisicao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoRequisicao() {
        return codigoRequisicao;
    }

    /**
     * Sets the value of the codigoRequisicao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoRequisicao(String value) {
        this.codigoRequisicao = value;
    }

    /**
     * Gets the value of the encarSolicitaRmMobile property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEncarSolicitaRmMobile() {
        return encarSolicitaRmMobile;
    }

    /**
     * Sets the value of the encarSolicitaRmMobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEncarSolicitaRmMobile(Boolean value) {
        this.encarSolicitaRmMobile = value;
    }

    /**
     * Gets the value of the epi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEpi() {
        return epi;
    }

    /**
     * Sets the value of the epi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEpi(String value) {
        this.epi = value;
    }

    /**
     * Gets the value of the equipamento property.
     * 
     * @return
     *     possible object is
     *     {@link Equipamento }
     *     
     */
    public Equipamento getEquipamento() {
        return equipamento;
    }

    /**
     * Sets the value of the equipamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Equipamento }
     *     
     */
    public void setEquipamento(Equipamento value) {
        this.equipamento = value;
    }

    /**
     * Gets the value of the funcionarioEap property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFuncionarioEap() {
        return funcionarioEap;
    }

    /**
     * Sets the value of the funcionarioEap property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFuncionarioEap(String value) {
        this.funcionarioEap = value;
    }

    /**
     * Gets the value of the identificadorRmMaterial property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdentificadorRmMaterial() {
        return identificadorRmMaterial;
    }

    /**
     * Sets the value of the identificadorRmMaterial property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdentificadorRmMaterial(Integer value) {
        this.identificadorRmMaterial = value;
    }

    /**
     * Gets the value of the localEstoque property.
     * 
     * @return
     *     possible object is
     *     {@link LocalEstoque }
     *     
     */
    public LocalEstoque getLocalEstoque() {
        return localEstoque;
    }

    /**
     * Sets the value of the localEstoque property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalEstoque }
     *     
     */
    public void setLocalEstoque(LocalEstoque value) {
        this.localEstoque = value;
    }

    /**
     * Gets the value of the naoEnviarMovimentacao property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNaoEnviarMovimentacao() {
        return naoEnviarMovimentacao;
    }

    /**
     * Sets the value of the naoEnviarMovimentacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNaoEnviarMovimentacao(Boolean value) {
        this.naoEnviarMovimentacao = value;
    }

    /**
     * Gets the value of the nomeUsuario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    /**
     * Sets the value of the nomeUsuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeUsuario(String value) {
        this.nomeUsuario = value;
    }

    /**
     * Gets the value of the origem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigem() {
        return origem;
    }

    /**
     * Sets the value of the origem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigem(String value) {
        this.origem = value;
    }

    /**
     * Gets the value of the patrimoniado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatrimoniado() {
        return patrimoniado;
    }

    /**
     * Sets the value of the patrimoniado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatrimoniado(String value) {
        this.patrimoniado = value;
    }

    /**
     * Gets the value of the prefixoEquipamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrefixoEquipamento() {
        return prefixoEquipamento;
    }

    /**
     * Sets the value of the prefixoEquipamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrefixoEquipamento(String value) {
        this.prefixoEquipamento = value;
    }

    /**
     * Gets the value of the quantidade property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQuantidade() {
        return quantidade;
    }

    /**
     * Sets the value of the quantidade property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQuantidade(Double value) {
        this.quantidade = value;
    }

    /**
     * Gets the value of the referenciaCorresponsavel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenciaCorresponsavel() {
        return referenciaCorresponsavel;
    }

    /**
     * Sets the value of the referenciaCorresponsavel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenciaCorresponsavel(String value) {
        this.referenciaCorresponsavel = value;
    }

    /**
     * Gets the value of the referenciaPessoa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenciaPessoa() {
        return referenciaPessoa;
    }

    /**
     * Sets the value of the referenciaPessoa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenciaPessoa(String value) {
        this.referenciaPessoa = value;
    }

    /**
     * Gets the value of the tipoEquipamento property.
     * 
     * @return
     *     possible object is
     *     {@link TipoEquipamento }
     *     
     */
    public TipoEquipamento getTipoEquipamento() {
        return tipoEquipamento;
    }

    /**
     * Sets the value of the tipoEquipamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoEquipamento }
     *     
     */
    public void setTipoEquipamento(TipoEquipamento value) {
        this.tipoEquipamento = value;
    }

    /**
     * Gets the value of the unidadeMedida property.
     * 
     * @return
     *     possible object is
     *     {@link UnidadeMedida }
     *     
     */
    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    /**
     * Sets the value of the unidadeMedida property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnidadeMedida }
     *     
     */
    public void setUnidadeMedida(UnidadeMedida value) {
        this.unidadeMedida = value;
    }

}
