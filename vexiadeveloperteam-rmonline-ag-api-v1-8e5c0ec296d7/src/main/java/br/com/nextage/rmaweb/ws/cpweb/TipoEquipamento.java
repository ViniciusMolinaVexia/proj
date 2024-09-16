
package br.com.nextage.rmaweb.ws.cpweb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tipoEquipamento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tipoEquipamento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="classificacaoEquip" type="{http://ws.cpweb.nextage.com.br/}classificacaoEquip" minOccurs="0"/>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fatorDepreciacao" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="hierarquia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="horasTrabalhadasPadrao" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nomeCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="periodicidadeManutencao" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="qtdeLimiteEmprestimo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="quantidadeMinima" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="responsavelCautela" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subGrupo" type="{http://ws.cpweb.nextage.com.br/}subGrupo" minOccurs="0"/>
 *         &lt;element name="tipoEquipFuncoes" type="{http://ws.cpweb.nextage.com.br/}tipoEquipFuncao" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tipoEquipamentoId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="unidadeMedida" type="{http://ws.cpweb.nextage.com.br/}unidadeMedida" minOccurs="0"/>
 *         &lt;element name="validadeEmprestimo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tipoEquipamento", propOrder = {
    "classificacaoEquip",
    "codigo",
    "descricao",
    "fatorDepreciacao",
    "hierarquia",
    "horasTrabalhadasPadrao",
    "nomeCliente",
    "observacao",
    "periodicidadeManutencao",
    "qtdeLimiteEmprestimo",
    "quantidadeMinima",
    "responsavelCautela",
    "subGrupo",
    "tipoEquipFuncoes",
    "tipoEquipamentoId",
    "unidadeMedida",
    "validadeEmprestimo"
})
public class TipoEquipamento {

    protected ClassificacaoEquip classificacaoEquip;
    protected String codigo;
    protected String descricao;
    protected Double fatorDepreciacao;
    protected String hierarquia;
    protected Integer horasTrabalhadasPadrao;
    protected String nomeCliente;
    protected String observacao;
    protected Integer periodicidadeManutencao;
    protected Integer qtdeLimiteEmprestimo;
    protected double quantidadeMinima;
    protected String responsavelCautela;
    protected SubGrupo subGrupo;
    @XmlElement(nillable = true)
    protected List<TipoEquipFuncao> tipoEquipFuncoes;
    protected Integer tipoEquipamentoId;
    protected UnidadeMedida unidadeMedida;
    protected Integer validadeEmprestimo;

    /**
     * Gets the value of the classificacaoEquip property.
     * 
     * @return
     *     possible object is
     *     {@link ClassificacaoEquip }
     *     
     */
    public ClassificacaoEquip getClassificacaoEquip() {
        return classificacaoEquip;
    }

    /**
     * Sets the value of the classificacaoEquip property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassificacaoEquip }
     *     
     */
    public void setClassificacaoEquip(ClassificacaoEquip value) {
        this.classificacaoEquip = value;
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
     * Gets the value of the fatorDepreciacao property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getFatorDepreciacao() {
        return fatorDepreciacao;
    }

    /**
     * Sets the value of the fatorDepreciacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setFatorDepreciacao(Double value) {
        this.fatorDepreciacao = value;
    }

    /**
     * Gets the value of the hierarquia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHierarquia() {
        return hierarquia;
    }

    /**
     * Sets the value of the hierarquia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHierarquia(String value) {
        this.hierarquia = value;
    }

    /**
     * Gets the value of the horasTrabalhadasPadrao property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHorasTrabalhadasPadrao() {
        return horasTrabalhadasPadrao;
    }

    /**
     * Sets the value of the horasTrabalhadasPadrao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHorasTrabalhadasPadrao(Integer value) {
        this.horasTrabalhadasPadrao = value;
    }

    /**
     * Gets the value of the nomeCliente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeCliente() {
        return nomeCliente;
    }

    /**
     * Sets the value of the nomeCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeCliente(String value) {
        this.nomeCliente = value;
    }

    /**
     * Gets the value of the observacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * Sets the value of the observacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacao(String value) {
        this.observacao = value;
    }

    /**
     * Gets the value of the periodicidadeManutencao property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPeriodicidadeManutencao() {
        return periodicidadeManutencao;
    }

    /**
     * Sets the value of the periodicidadeManutencao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPeriodicidadeManutencao(Integer value) {
        this.periodicidadeManutencao = value;
    }

    /**
     * Gets the value of the qtdeLimiteEmprestimo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQtdeLimiteEmprestimo() {
        return qtdeLimiteEmprestimo;
    }

    /**
     * Sets the value of the qtdeLimiteEmprestimo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQtdeLimiteEmprestimo(Integer value) {
        this.qtdeLimiteEmprestimo = value;
    }

    /**
     * Gets the value of the quantidadeMinima property.
     * 
     */
    public double getQuantidadeMinima() {
        return quantidadeMinima;
    }

    /**
     * Sets the value of the quantidadeMinima property.
     * 
     */
    public void setQuantidadeMinima(double value) {
        this.quantidadeMinima = value;
    }

    /**
     * Gets the value of the responsavelCautela property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsavelCautela() {
        return responsavelCautela;
    }

    /**
     * Sets the value of the responsavelCautela property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsavelCautela(String value) {
        this.responsavelCautela = value;
    }

    /**
     * Gets the value of the subGrupo property.
     * 
     * @return
     *     possible object is
     *     {@link SubGrupo }
     *     
     */
    public SubGrupo getSubGrupo() {
        return subGrupo;
    }

    /**
     * Sets the value of the subGrupo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubGrupo }
     *     
     */
    public void setSubGrupo(SubGrupo value) {
        this.subGrupo = value;
    }

    /**
     * Gets the value of the tipoEquipFuncoes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tipoEquipFuncoes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTipoEquipFuncoes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TipoEquipFuncao }
     * 
     * 
     */
    public List<TipoEquipFuncao> getTipoEquipFuncoes() {
        if (tipoEquipFuncoes == null) {
            tipoEquipFuncoes = new ArrayList<TipoEquipFuncao>();
        }
        return this.tipoEquipFuncoes;
    }

    /**
     * Gets the value of the tipoEquipamentoId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTipoEquipamentoId() {
        return tipoEquipamentoId;
    }

    /**
     * Sets the value of the tipoEquipamentoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTipoEquipamentoId(Integer value) {
        this.tipoEquipamentoId = value;
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

    /**
     * Gets the value of the validadeEmprestimo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getValidadeEmprestimo() {
        return validadeEmprestimo;
    }

    /**
     * Sets the value of the validadeEmprestimo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setValidadeEmprestimo(Integer value) {
        this.validadeEmprestimo = value;
    }

}
