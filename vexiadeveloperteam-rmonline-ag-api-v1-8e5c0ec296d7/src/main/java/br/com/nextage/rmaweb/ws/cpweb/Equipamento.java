
package br.com.nextage.rmaweb.ws.cpweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for equipamento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="equipamento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoRequisicao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataFabricacao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="equipamentoId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="excluido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fornecedor" type="{http://ws.cpweb.nextage.com.br/}fornecedor" minOccurs="0"/>
 *         &lt;element name="funcionarioEmUso" type="{http://ws.cpweb.nextage.com.br/}funcionario" minOccurs="0"/>
 *         &lt;element name="gerarAutomaticamente" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="numPatrimonio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroSerie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://ws.cpweb.nextage.com.br/}status" minOccurs="0"/>
 *         &lt;element name="subNumero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoEquipamento" type="{http://ws.cpweb.nextage.com.br/}tipoEquipamento" minOccurs="0"/>
 *         &lt;element name="tipoPropriedade" type="{http://ws.cpweb.nextage.com.br/}tipoPropriedade" minOccurs="0"/>
 *         &lt;element name="valorEstimado" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "equipamento", propOrder = {
    "ca",
    "codigoRequisicao",
    "dataFabricacao",
    "equipamentoId",
    "excluido",
    "fornecedor",
    "funcionarioEmUso",
    "gerarAutomaticamente",
    "numPatrimonio",
    "numeroSerie",
    "status",
    "subNumero",
    "tag",
    "tipoEquipamento",
    "tipoPropriedade",
    "valorEstimado"
})
public class Equipamento {

    protected String ca;
    protected String codigoRequisicao;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFabricacao;
    protected Integer equipamentoId;
    protected String excluido;
    protected Fornecedor fornecedor;
    protected Funcionario funcionarioEmUso;
    protected Boolean gerarAutomaticamente;
    protected String numPatrimonio;
    protected String numeroSerie;
    protected Status status;
    protected String subNumero;
    protected String tag;
    protected TipoEquipamento tipoEquipamento;
    protected TipoPropriedade tipoPropriedade;
    protected Double valorEstimado;

    /**
     * Gets the value of the ca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCa() {
        return ca;
    }

    /**
     * Sets the value of the ca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCa(String value) {
        this.ca = value;
    }

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
     * Gets the value of the dataFabricacao property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFabricacao() {
        return dataFabricacao;
    }

    /**
     * Sets the value of the dataFabricacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFabricacao(XMLGregorianCalendar value) {
        this.dataFabricacao = value;
    }

    /**
     * Gets the value of the equipamentoId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEquipamentoId() {
        return equipamentoId;
    }

    /**
     * Sets the value of the equipamentoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEquipamentoId(Integer value) {
        this.equipamentoId = value;
    }

    /**
     * Gets the value of the excluido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExcluido() {
        return excluido;
    }

    /**
     * Sets the value of the excluido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExcluido(String value) {
        this.excluido = value;
    }

    /**
     * Gets the value of the fornecedor property.
     * 
     * @return
     *     possible object is
     *     {@link Fornecedor }
     *     
     */
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    /**
     * Sets the value of the fornecedor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Fornecedor }
     *     
     */
    public void setFornecedor(Fornecedor value) {
        this.fornecedor = value;
    }

    /**
     * Gets the value of the funcionarioEmUso property.
     * 
     * @return
     *     possible object is
     *     {@link Funcionario }
     *     
     */
    public Funcionario getFuncionarioEmUso() {
        return funcionarioEmUso;
    }

    /**
     * Sets the value of the funcionarioEmUso property.
     * 
     * @param value
     *     allowed object is
     *     {@link Funcionario }
     *     
     */
    public void setFuncionarioEmUso(Funcionario value) {
        this.funcionarioEmUso = value;
    }

    /**
     * Gets the value of the gerarAutomaticamente property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGerarAutomaticamente() {
        return gerarAutomaticamente;
    }

    /**
     * Sets the value of the gerarAutomaticamente property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGerarAutomaticamente(Boolean value) {
        this.gerarAutomaticamente = value;
    }

    /**
     * Gets the value of the numPatrimonio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumPatrimonio() {
        return numPatrimonio;
    }

    /**
     * Sets the value of the numPatrimonio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumPatrimonio(String value) {
        this.numPatrimonio = value;
    }

    /**
     * Gets the value of the numeroSerie property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroSerie() {
        return numeroSerie;
    }

    /**
     * Sets the value of the numeroSerie property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroSerie(String value) {
        this.numeroSerie = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Status }
     *     
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Status }
     *     
     */
    public void setStatus(Status value) {
        this.status = value;
    }

    /**
     * Gets the value of the subNumero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubNumero() {
        return subNumero;
    }

    /**
     * Sets the value of the subNumero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubNumero(String value) {
        this.subNumero = value;
    }

    /**
     * Gets the value of the tag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTag() {
        return tag;
    }

    /**
     * Sets the value of the tag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTag(String value) {
        this.tag = value;
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
     * Gets the value of the tipoPropriedade property.
     * 
     * @return
     *     possible object is
     *     {@link TipoPropriedade }
     *     
     */
    public TipoPropriedade getTipoPropriedade() {
        return tipoPropriedade;
    }

    /**
     * Sets the value of the tipoPropriedade property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoPropriedade }
     *     
     */
    public void setTipoPropriedade(TipoPropriedade value) {
        this.tipoPropriedade = value;
    }

    /**
     * Gets the value of the valorEstimado property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getValorEstimado() {
        return valorEstimado;
    }

    /**
     * Sets the value of the valorEstimado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setValorEstimado(Double value) {
        this.valorEstimado = value;
    }

}
