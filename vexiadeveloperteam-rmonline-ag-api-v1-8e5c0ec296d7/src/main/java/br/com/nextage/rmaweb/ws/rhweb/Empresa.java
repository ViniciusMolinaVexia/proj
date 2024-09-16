
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for empresa complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="empresa">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="atividade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bairro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cidade" type="{http://ws.rhweb_ws.nextage.com.br/}cidade" minOccurs="0"/>
 *         &lt;element name="cnpj" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="complemento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contatos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataFimContrato" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataInicioContrato" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="empresaId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="empresaResponsavel" type="{http://ws.rhweb_ws.nextage.com.br/}empresa" minOccurs="0"/>
 *         &lt;element name="endereco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pais" type="{http://ws.rhweb_ws.nextage.com.br/}pais" minOccurs="0"/>
 *         &lt;element name="quarteirizada" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="razaoSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataFimContrato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataInicioContrato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subcontratada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="uf" type="{http://ws.rhweb_ws.nextage.com.br/}uf" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "empresa", propOrder = {
    "atividade",
    "ativo",
    "bairro",
    "cep",
    "cidade",
    "cnpj",
    "complemento",
    "contatos",
    "dataFimContrato",
    "dataInicioContrato",
    "empresaId",
    "empresaResponsavel",
    "endereco",
    "nome",
    "numero",
    "pais",
    "quarteirizada",
    "razaoSocial",
    "stDataFimContrato",
    "stDataInicioContrato",
    "subcontratada",
    "uf"
})
public class Empresa {

    protected String atividade;
    protected String ativo;
    protected String bairro;
    protected String cep;
    protected Cidade cidade;
    protected String cnpj;
    protected String complemento;
    protected String contatos;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFimContrato;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInicioContrato;
    protected Integer empresaId;
    protected Empresa empresaResponsavel;
    protected String endereco;
    protected String nome;
    protected String numero;
    protected Pais pais;
    protected boolean quarteirizada;
    protected String razaoSocial;
    protected String stDataFimContrato;
    protected String stDataInicioContrato;
    protected String subcontratada;
    protected Uf uf;

    /**
     * Gets the value of the atividade property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtividade() {
        return atividade;
    }

    /**
     * Sets the value of the atividade property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtividade(String value) {
        this.atividade = value;
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
     * Gets the value of the bairro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * Sets the value of the bairro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBairro(String value) {
        this.bairro = value;
    }

    /**
     * Gets the value of the cep property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCep() {
        return cep;
    }

    /**
     * Sets the value of the cep property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCep(String value) {
        this.cep = value;
    }

    /**
     * Gets the value of the cidade property.
     * 
     * @return
     *     possible object is
     *     {@link Cidade }
     *     
     */
    public Cidade getCidade() {
        return cidade;
    }

    /**
     * Sets the value of the cidade property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cidade }
     *     
     */
    public void setCidade(Cidade value) {
        this.cidade = value;
    }

    /**
     * Gets the value of the cnpj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Sets the value of the cnpj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCnpj(String value) {
        this.cnpj = value;
    }

    /**
     * Gets the value of the complemento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * Sets the value of the complemento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComplemento(String value) {
        this.complemento = value;
    }

    /**
     * Gets the value of the contatos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContatos() {
        return contatos;
    }

    /**
     * Sets the value of the contatos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContatos(String value) {
        this.contatos = value;
    }

    /**
     * Gets the value of the dataFimContrato property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFimContrato() {
        return dataFimContrato;
    }

    /**
     * Sets the value of the dataFimContrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFimContrato(XMLGregorianCalendar value) {
        this.dataFimContrato = value;
    }

    /**
     * Gets the value of the dataInicioContrato property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInicioContrato() {
        return dataInicioContrato;
    }

    /**
     * Sets the value of the dataInicioContrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInicioContrato(XMLGregorianCalendar value) {
        this.dataInicioContrato = value;
    }

    /**
     * Gets the value of the empresaId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEmpresaId() {
        return empresaId;
    }

    /**
     * Sets the value of the empresaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEmpresaId(Integer value) {
        this.empresaId = value;
    }

    /**
     * Gets the value of the empresaResponsavel property.
     * 
     * @return
     *     possible object is
     *     {@link Empresa }
     *     
     */
    public Empresa getEmpresaResponsavel() {
        return empresaResponsavel;
    }

    /**
     * Sets the value of the empresaResponsavel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Empresa }
     *     
     */
    public void setEmpresaResponsavel(Empresa value) {
        this.empresaResponsavel = value;
    }

    /**
     * Gets the value of the endereco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Sets the value of the endereco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndereco(String value) {
        this.endereco = value;
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
     * Gets the value of the numero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Sets the value of the numero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumero(String value) {
        this.numero = value;
    }

    /**
     * Gets the value of the pais property.
     * 
     * @return
     *     possible object is
     *     {@link Pais }
     *     
     */
    public Pais getPais() {
        return pais;
    }

    /**
     * Sets the value of the pais property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pais }
     *     
     */
    public void setPais(Pais value) {
        this.pais = value;
    }

    /**
     * Gets the value of the quarteirizada property.
     * 
     */
    public boolean isQuarteirizada() {
        return quarteirizada;
    }

    /**
     * Sets the value of the quarteirizada property.
     * 
     */
    public void setQuarteirizada(boolean value) {
        this.quarteirizada = value;
    }

    /**
     * Gets the value of the razaoSocial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazaoSocial() {
        return razaoSocial;
    }

    /**
     * Sets the value of the razaoSocial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazaoSocial(String value) {
        this.razaoSocial = value;
    }

    /**
     * Gets the value of the stDataFimContrato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataFimContrato() {
        return stDataFimContrato;
    }

    /**
     * Sets the value of the stDataFimContrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataFimContrato(String value) {
        this.stDataFimContrato = value;
    }

    /**
     * Gets the value of the stDataInicioContrato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataInicioContrato() {
        return stDataInicioContrato;
    }

    /**
     * Sets the value of the stDataInicioContrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataInicioContrato(String value) {
        this.stDataInicioContrato = value;
    }

    /**
     * Gets the value of the subcontratada property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubcontratada() {
        return subcontratada;
    }

    /**
     * Sets the value of the subcontratada property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubcontratada(String value) {
        this.subcontratada = value;
    }

    /**
     * Gets the value of the uf property.
     * 
     * @return
     *     possible object is
     *     {@link Uf }
     *     
     */
    public Uf getUf() {
        return uf;
    }

    /**
     * Sets the value of the uf property.
     * 
     * @param value
     *     allowed object is
     *     {@link Uf }
     *     
     */
    public void setUf(Uf value) {
        this.uf = value;
    }

}
