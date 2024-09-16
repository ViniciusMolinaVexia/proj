
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for vagaRqsIndicado complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="vagaRqsIndicado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telefone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="indicadorPessoa" type="{http://ws.rhweb_ws.nextage.com.br/}pessoa" minOccurs="0"/>
 *         &lt;element name="vagaRequisicao" type="{http://ws.rhweb_ws.nextage.com.br/}vagaRequisicao" minOccurs="0"/>
 *         &lt;element name="vaga" type="{http://ws.rhweb_ws.nextage.com.br/}vaga" minOccurs="0"/>
 *         &lt;element name="contatoData" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="contatoObservacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contatoAceitou" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cpf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataNascimento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataMobilizacao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataIndicado" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="indicadoRh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuarioCadastro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataCadastro" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataPrevistaChegada" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="curriculo" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="curriculoNome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ufOrigem" type="{http://ws.rhweb_ws.nextage.com.br/}uf" minOccurs="0"/>
 *         &lt;element name="cidadeOrigem" type="{http://ws.rhweb_ws.nextage.com.br/}cidade" minOccurs="0"/>
 *         &lt;element name="ultimoSalario" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="salarioAcertado" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="jaTrabalhouEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataEnvioAtendimento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataEnvioContratacao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="tipoDocumento" type="{http://ws.rhweb_ws.nextage.com.br/}tipoDocumento" minOccurs="0"/>
 *         &lt;element name="projeto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lider" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stContatoData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataNascimento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataMobilizacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataIndicado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataCadastro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataPrevistaChegada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataEnvioAtendimento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stDataEnvioContratacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vagaRqsIndicado", propOrder = {
    "id",
    "nome",
    "telefone",
    "observacao",
    "indicadorPessoa",
    "vagaRequisicao",
    "vaga",
    "contatoData",
    "contatoObservacao",
    "contatoAceitou",
    "cpf",
    "dataNascimento",
    "dataMobilizacao",
    "dataIndicado",
    "indicadoRh",
    "usuarioCadastro",
    "dataCadastro",
    "dataPrevistaChegada",
    "curriculo",
    "curriculoNome",
    "ufOrigem",
    "cidadeOrigem",
    "ultimoSalario",
    "salarioAcertado",
    "jaTrabalhouEmpresa",
    "email",
    "pis",
    "rg",
    "dataEnvioAtendimento",
    "dataEnvioContratacao",
    "tipoDocumento",
    "projeto",
    "lider",
    "stContatoData",
    "stDataNascimento",
    "stDataMobilizacao",
    "stDataIndicado",
    "stDataCadastro",
    "stDataPrevistaChegada",
    "stDataEnvioAtendimento",
    "stDataEnvioContratacao"
})
public class VagaRqsIndicado {

    protected Integer id;
    protected String nome;
    protected String telefone;
    protected String observacao;
    protected Pessoa indicadorPessoa;
    protected VagaRequisicao vagaRequisicao;
    protected Vaga vaga;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar contatoData;
    protected String contatoObservacao;
    protected String contatoAceitou;
    protected String cpf;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataNascimento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataMobilizacao;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataIndicado;
    protected String indicadoRh;
    protected String usuarioCadastro;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataCadastro;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataPrevistaChegada;
    protected byte[] curriculo;
    protected String curriculoNome;
    protected Uf ufOrigem;
    protected Cidade cidadeOrigem;
    protected Double ultimoSalario;
    protected Double salarioAcertado;
    protected String jaTrabalhouEmpresa;
    protected String email;
    protected String pis;
    protected String rg;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEnvioAtendimento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEnvioContratacao;
    protected TipoDocumento tipoDocumento;
    protected String projeto;
    protected String lider;
    protected String stContatoData;
    protected String stDataNascimento;
    protected String stDataMobilizacao;
    protected String stDataIndicado;
    protected String stDataCadastro;
    protected String stDataPrevistaChegada;
    protected String stDataEnvioAtendimento;
    protected String stDataEnvioContratacao;

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
     * Gets the value of the telefone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Sets the value of the telefone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefone(String value) {
        this.telefone = value;
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
     * Gets the value of the indicadorPessoa property.
     * 
     * @return
     *     possible object is
     *     {@link Pessoa }
     *     
     */
    public Pessoa getIndicadorPessoa() {
        return indicadorPessoa;
    }

    /**
     * Sets the value of the indicadorPessoa property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pessoa }
     *     
     */
    public void setIndicadorPessoa(Pessoa value) {
        this.indicadorPessoa = value;
    }

    /**
     * Gets the value of the vagaRequisicao property.
     * 
     * @return
     *     possible object is
     *     {@link VagaRequisicao }
     *     
     */
    public VagaRequisicao getVagaRequisicao() {
        return vagaRequisicao;
    }

    /**
     * Sets the value of the vagaRequisicao property.
     * 
     * @param value
     *     allowed object is
     *     {@link VagaRequisicao }
     *     
     */
    public void setVagaRequisicao(VagaRequisicao value) {
        this.vagaRequisicao = value;
    }

    /**
     * Gets the value of the vaga property.
     * 
     * @return
     *     possible object is
     *     {@link Vaga }
     *     
     */
    public Vaga getVaga() {
        return vaga;
    }

    /**
     * Sets the value of the vaga property.
     * 
     * @param value
     *     allowed object is
     *     {@link Vaga }
     *     
     */
    public void setVaga(Vaga value) {
        this.vaga = value;
    }

    /**
     * Gets the value of the contatoData property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getContatoData() {
        return contatoData;
    }

    /**
     * Sets the value of the contatoData property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setContatoData(XMLGregorianCalendar value) {
        this.contatoData = value;
    }

    /**
     * Gets the value of the contatoObservacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContatoObservacao() {
        return contatoObservacao;
    }

    /**
     * Sets the value of the contatoObservacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContatoObservacao(String value) {
        this.contatoObservacao = value;
    }

    /**
     * Gets the value of the contatoAceitou property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContatoAceitou() {
        return contatoAceitou;
    }

    /**
     * Sets the value of the contatoAceitou property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContatoAceitou(String value) {
        this.contatoAceitou = value;
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
     * Gets the value of the dataNascimento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Sets the value of the dataNascimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataNascimento(XMLGregorianCalendar value) {
        this.dataNascimento = value;
    }

    /**
     * Gets the value of the dataMobilizacao property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataMobilizacao() {
        return dataMobilizacao;
    }

    /**
     * Sets the value of the dataMobilizacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataMobilizacao(XMLGregorianCalendar value) {
        this.dataMobilizacao = value;
    }

    /**
     * Gets the value of the dataIndicado property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataIndicado() {
        return dataIndicado;
    }

    /**
     * Sets the value of the dataIndicado property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataIndicado(XMLGregorianCalendar value) {
        this.dataIndicado = value;
    }

    /**
     * Gets the value of the indicadoRh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndicadoRh() {
        return indicadoRh;
    }

    /**
     * Sets the value of the indicadoRh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndicadoRh(String value) {
        this.indicadoRh = value;
    }

    /**
     * Gets the value of the usuarioCadastro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuarioCadastro() {
        return usuarioCadastro;
    }

    /**
     * Sets the value of the usuarioCadastro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuarioCadastro(String value) {
        this.usuarioCadastro = value;
    }

    /**
     * Gets the value of the dataCadastro property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataCadastro() {
        return dataCadastro;
    }

    /**
     * Sets the value of the dataCadastro property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataCadastro(XMLGregorianCalendar value) {
        this.dataCadastro = value;
    }

    /**
     * Gets the value of the dataPrevistaChegada property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataPrevistaChegada() {
        return dataPrevistaChegada;
    }

    /**
     * Sets the value of the dataPrevistaChegada property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataPrevistaChegada(XMLGregorianCalendar value) {
        this.dataPrevistaChegada = value;
    }

    /**
     * Gets the value of the curriculo property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getCurriculo() {
        return curriculo;
    }

    /**
     * Sets the value of the curriculo property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setCurriculo(byte[] value) {
        this.curriculo = value;
    }

    /**
     * Gets the value of the curriculoNome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurriculoNome() {
        return curriculoNome;
    }

    /**
     * Sets the value of the curriculoNome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurriculoNome(String value) {
        this.curriculoNome = value;
    }

    /**
     * Gets the value of the ufOrigem property.
     * 
     * @return
     *     possible object is
     *     {@link Uf }
     *     
     */
    public Uf getUfOrigem() {
        return ufOrigem;
    }

    /**
     * Sets the value of the ufOrigem property.
     * 
     * @param value
     *     allowed object is
     *     {@link Uf }
     *     
     */
    public void setUfOrigem(Uf value) {
        this.ufOrigem = value;
    }

    /**
     * Gets the value of the cidadeOrigem property.
     * 
     * @return
     *     possible object is
     *     {@link Cidade }
     *     
     */
    public Cidade getCidadeOrigem() {
        return cidadeOrigem;
    }

    /**
     * Sets the value of the cidadeOrigem property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cidade }
     *     
     */
    public void setCidadeOrigem(Cidade value) {
        this.cidadeOrigem = value;
    }

    /**
     * Gets the value of the ultimoSalario property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getUltimoSalario() {
        return ultimoSalario;
    }

    /**
     * Sets the value of the ultimoSalario property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setUltimoSalario(Double value) {
        this.ultimoSalario = value;
    }

    /**
     * Gets the value of the salarioAcertado property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSalarioAcertado() {
        return salarioAcertado;
    }

    /**
     * Sets the value of the salarioAcertado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSalarioAcertado(Double value) {
        this.salarioAcertado = value;
    }

    /**
     * Gets the value of the jaTrabalhouEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJaTrabalhouEmpresa() {
        return jaTrabalhouEmpresa;
    }

    /**
     * Sets the value of the jaTrabalhouEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJaTrabalhouEmpresa(String value) {
        this.jaTrabalhouEmpresa = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the pis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPis() {
        return pis;
    }

    /**
     * Sets the value of the pis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPis(String value) {
        this.pis = value;
    }

    /**
     * Gets the value of the rg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRg() {
        return rg;
    }

    /**
     * Sets the value of the rg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRg(String value) {
        this.rg = value;
    }

    /**
     * Gets the value of the dataEnvioAtendimento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEnvioAtendimento() {
        return dataEnvioAtendimento;
    }

    /**
     * Sets the value of the dataEnvioAtendimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEnvioAtendimento(XMLGregorianCalendar value) {
        this.dataEnvioAtendimento = value;
    }

    /**
     * Gets the value of the dataEnvioContratacao property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEnvioContratacao() {
        return dataEnvioContratacao;
    }

    /**
     * Sets the value of the dataEnvioContratacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEnvioContratacao(XMLGregorianCalendar value) {
        this.dataEnvioContratacao = value;
    }

    /**
     * Gets the value of the tipoDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link TipoDocumento }
     *     
     */
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Sets the value of the tipoDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDocumento }
     *     
     */
    public void setTipoDocumento(TipoDocumento value) {
        this.tipoDocumento = value;
    }

    /**
     * Gets the value of the projeto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjeto() {
        return projeto;
    }

    /**
     * Sets the value of the projeto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjeto(String value) {
        this.projeto = value;
    }

    /**
     * Gets the value of the lider property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLider() {
        return lider;
    }

    /**
     * Sets the value of the lider property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLider(String value) {
        this.lider = value;
    }

    /**
     * Gets the value of the stContatoData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStContatoData() {
        return stContatoData;
    }

    /**
     * Sets the value of the stContatoData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStContatoData(String value) {
        this.stContatoData = value;
    }

    /**
     * Gets the value of the stDataNascimento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataNascimento() {
        return stDataNascimento;
    }

    /**
     * Sets the value of the stDataNascimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataNascimento(String value) {
        this.stDataNascimento = value;
    }

    /**
     * Gets the value of the stDataMobilizacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataMobilizacao() {
        return stDataMobilizacao;
    }

    /**
     * Sets the value of the stDataMobilizacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataMobilizacao(String value) {
        this.stDataMobilizacao = value;
    }

    /**
     * Gets the value of the stDataIndicado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataIndicado() {
        return stDataIndicado;
    }

    /**
     * Sets the value of the stDataIndicado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataIndicado(String value) {
        this.stDataIndicado = value;
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
     * Gets the value of the stDataPrevistaChegada property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataPrevistaChegada() {
        return stDataPrevistaChegada;
    }

    /**
     * Sets the value of the stDataPrevistaChegada property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataPrevistaChegada(String value) {
        this.stDataPrevistaChegada = value;
    }

    /**
     * Gets the value of the stDataEnvioAtendimento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataEnvioAtendimento() {
        return stDataEnvioAtendimento;
    }

    /**
     * Sets the value of the stDataEnvioAtendimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataEnvioAtendimento(String value) {
        this.stDataEnvioAtendimento = value;
    }

    /**
     * Gets the value of the stDataEnvioContratacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStDataEnvioContratacao() {
        return stDataEnvioContratacao;
    }

    /**
     * Sets the value of the stDataEnvioContratacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStDataEnvioContratacao(String value) {
        this.stDataEnvioContratacao = value;
    }

}
