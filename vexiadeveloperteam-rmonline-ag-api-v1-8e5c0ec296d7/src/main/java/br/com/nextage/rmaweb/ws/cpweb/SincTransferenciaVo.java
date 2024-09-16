
package br.com.nextage.rmaweb.ws.cpweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sincTransferenciaVo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sincTransferenciaVo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigoRequisicao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="equipamento" type="{http://ws.cpweb.nextage.com.br/}equipamento" minOccurs="0"/>
 *         &lt;element name="funcionarioEap" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="localEstoqueDestino" type="{http://ws.cpweb.nextage.com.br/}localEstoque" minOccurs="0"/>
 *         &lt;element name="localEstoqueOrigem" type="{http://ws.cpweb.nextage.com.br/}localEstoque" minOccurs="0"/>
 *         &lt;element name="naoEnviarMovimentacao" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="nomeUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="patrimoniado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quantidadeSolicitada" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="quantidadeTransferencia" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
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
@XmlType(name = "sincTransferenciaVo", propOrder = {
    "codigoRequisicao",
    "equipamento",
    "funcionarioEap",
    "localEstoqueDestino",
    "localEstoqueOrigem",
    "naoEnviarMovimentacao",
    "nomeUsuario",
    "patrimoniado",
    "quantidadeSolicitada",
    "quantidadeTransferencia",
    "referenciaPessoa",
    "tipoEquipamento",
    "unidadeMedida"
})
public class SincTransferenciaVo {

    protected String codigoRequisicao;
    protected Equipamento equipamento;
    protected String funcionarioEap;
    protected LocalEstoque localEstoqueDestino;
    protected LocalEstoque localEstoqueOrigem;
    protected Boolean naoEnviarMovimentacao;
    protected String nomeUsuario;
    protected String patrimoniado;
    protected Double quantidadeSolicitada;
    protected Double quantidadeTransferencia;
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
     * Gets the value of the localEstoqueDestino property.
     * 
     * @return
     *     possible object is
     *     {@link LocalEstoque }
     *     
     */
    public LocalEstoque getLocalEstoqueDestino() {
        return localEstoqueDestino;
    }

    /**
     * Sets the value of the localEstoqueDestino property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalEstoque }
     *     
     */
    public void setLocalEstoqueDestino(LocalEstoque value) {
        this.localEstoqueDestino = value;
    }

    /**
     * Gets the value of the localEstoqueOrigem property.
     * 
     * @return
     *     possible object is
     *     {@link LocalEstoque }
     *     
     */
    public LocalEstoque getLocalEstoqueOrigem() {
        return localEstoqueOrigem;
    }

    /**
     * Sets the value of the localEstoqueOrigem property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalEstoque }
     *     
     */
    public void setLocalEstoqueOrigem(LocalEstoque value) {
        this.localEstoqueOrigem = value;
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
     * Gets the value of the quantidadeSolicitada property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQuantidadeSolicitada() {
        return quantidadeSolicitada;
    }

    /**
     * Sets the value of the quantidadeSolicitada property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQuantidadeSolicitada(Double value) {
        this.quantidadeSolicitada = value;
    }

    /**
     * Gets the value of the quantidadeTransferencia property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQuantidadeTransferencia() {
        return quantidadeTransferencia;
    }

    /**
     * Sets the value of the quantidadeTransferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQuantidadeTransferencia(Double value) {
        this.quantidadeTransferencia = value;
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
