
package br.com.nextage.rmaweb.ws.cpweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for funcionario complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="funcionario">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigoArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoSubArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoSubordinacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cpf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataTransferencia" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataTransferenciaSaida" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="descricaoArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descricaoSubArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descricaoSubordinacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dtAdmissao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dtDemissao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="eapMultiEmpresa" type="{http://ws.cpweb.nextage.com.br/}eapMultiEmpresa" minOccurs="0"/>
 *         &lt;element name="encarregado" type="{http://ws.cpweb.nextage.com.br/}funcionario" minOccurs="0"/>
 *         &lt;element name="foto" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="funcao" type="{http://ws.cpweb.nextage.com.br/}funcao" minOccurs="0"/>
 *         &lt;element name="funcionarioId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isEncarregado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="referencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataCadastro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataUltimaEmissaoRecibo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "funcionario", propOrder = {
    "codigoArea",
    "codigoSubArea",
    "codigoSubordinacao",
    "cpf",
    "dataTransferencia",
    "dataTransferenciaSaida",
    "descricaoArea",
    "descricaoSubArea",
    "descricaoSubordinacao",
    "dtAdmissao",
    "dtDemissao",
    "eapMultiEmpresa",
    "encarregado",
    "foto",
    "funcao",
    "funcionarioId",
    "isEncarregado",
    "nome",
    "referencia",
    "stDataCadastro",
    "stDataUltimaEmissaoRecibo"
})
public class Funcionario {

    protected String codigoArea;
    protected String codigoSubArea;
    protected String codigoSubordinacao;
    protected String cpf;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataTransferencia;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataTransferenciaSaida;
    protected String descricaoArea;
    protected String descricaoSubArea;
    protected String descricaoSubordinacao;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dtAdmissao;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dtDemissao;
    protected EapMultiEmpresa eapMultiEmpresa;
    protected Funcionario encarregado;
    protected byte[] foto;
    protected Funcao funcao;
    protected Integer funcionarioId;
    protected String isEncarregado;
    protected String nome;
    protected String referencia;
    protected String stDataCadastro;
    protected String stDataUltimaEmissaoRecibo;

    /**
     * Gets the value of the codigoArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoArea() {
        return codigoArea;
    }

    /**
     * Sets the value of the codigoArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoArea(String value) {
        this.codigoArea = value;
    }

    /**
     * Gets the value of the codigoSubArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoSubArea() {
        return codigoSubArea;
    }

    /**
     * Sets the value of the codigoSubArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoSubArea(String value) {
        this.codigoSubArea = value;
    }

    /**
     * Gets the value of the codigoSubordinacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoSubordinacao() {
        return codigoSubordinacao;
    }

    /**
     * Sets the value of the codigoSubordinacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoSubordinacao(String value) {
        this.codigoSubordinacao = value;
    }

    /**
     * Gets the value of the cpf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Sets the value of the cpf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpf(String value) {
        this.cpf = value;
    }

    /**
     * Gets the value of the dataTransferencia property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataTransferencia() {
        return dataTransferencia;
    }

    /**
     * Sets the value of the dataTransferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataTransferencia(XMLGregorianCalendar value) {
        this.dataTransferencia = value;
    }

    /**
     * Gets the value of the dataTransferenciaSaida property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataTransferenciaSaida() {
        return dataTransferenciaSaida;
    }

    /**
     * Sets the value of the dataTransferenciaSaida property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataTransferenciaSaida(XMLGregorianCalendar value) {
        this.dataTransferenciaSaida = value;
    }

    /**
     * Gets the value of the descricaoArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescricaoArea() {
        return descricaoArea;
    }

    /**
     * Sets the value of the descricaoArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescricaoArea(String value) {
        this.descricaoArea = value;
    }

    /**
     * Gets the value of the descricaoSubArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescricaoSubArea() {
        return descricaoSubArea;
    }

    /**
     * Sets the value of the descricaoSubArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescricaoSubArea(String value) {
        this.descricaoSubArea = value;
    }

    /**
     * Gets the value of the descricaoSubordinacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescricaoSubordinacao() {
        return descricaoSubordinacao;
    }

    /**
     * Sets the value of the descricaoSubordinacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescricaoSubordinacao(String value) {
        this.descricaoSubordinacao = value;
    }

    /**
     * Gets the value of the dtAdmissao property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDtAdmissao() {
        return dtAdmissao;
    }

    /**
     * Sets the value of the dtAdmissao property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDtAdmissao(XMLGregorianCalendar value) {
        this.dtAdmissao = value;
    }

    /**
     * Gets the value of the dtDemissao property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDtDemissao() {
        return dtDemissao;
    }

    /**
     * Sets the value of the dtDemissao property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDtDemissao(XMLGregorianCalendar value) {
        this.dtDemissao = value;
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
     * Gets the value of the encarregado property.
     * 
     * @return
     *     possible object is
     *     {@link Funcionario }
     *     
     */
    public Funcionario getEncarregado() {
        return encarregado;
    }

    /**
     * Sets the value of the encarregado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Funcionario }
     *     
     */
    public void setEncarregado(Funcionario value) {
        this.encarregado = value;
    }

    /**
     * Gets the value of the foto property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFoto() {
        return foto;
    }

    /**
     * Sets the value of the foto property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFoto(byte[] value) {
        this.foto = value;
    }

    /**
     * Gets the value of the funcao property.
     * 
     * @return
     *     possible object is
     *     {@link Funcao }
     *     
     */
    public Funcao getFuncao() {
        return funcao;
    }

    /**
     * Sets the value of the funcao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Funcao }
     *     
     */
    public void setFuncao(Funcao value) {
        this.funcao = value;
    }

    /**
     * Gets the value of the funcionarioId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFuncionarioId() {
        return funcionarioId;
    }

    /**
     * Sets the value of the funcionarioId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFuncionarioId(Integer value) {
        this.funcionarioId = value;
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
     * Gets the value of the nome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the value of the nome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Gets the value of the referencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Sets the value of the referencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferencia(String value) {
        this.referencia = value;
    }

    /**
     * Gets the value of the stDataCadastro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataCadastro() {
        return stDataCadastro;
    }

    /**
     * Sets the value of the stDataCadastro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataCadastro(String value) {
        this.stDataCadastro = value;
    }

    /**
     * Gets the value of the stDataUltimaEmissaoRecibo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataUltimaEmissaoRecibo() {
        return stDataUltimaEmissaoRecibo;
    }

    /**
     * Sets the value of the stDataUltimaEmissaoRecibo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataUltimaEmissaoRecibo(String value) {
        this.stDataUltimaEmissaoRecibo = value;
    }

}
