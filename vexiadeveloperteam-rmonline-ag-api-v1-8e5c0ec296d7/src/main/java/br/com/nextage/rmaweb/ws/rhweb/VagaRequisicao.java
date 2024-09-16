
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for vagaRequisicao complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="vagaRequisicao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aprovadorOrdemAtual" type="{http://ws.rhweb_ws.nextage.com.br/}pessoa" minOccurs="0"/>
 *         &lt;element name="area" type="{http://ws.rhweb_ws.nextage.com.br/}area" minOccurs="0"/>
 *         &lt;element name="cadastrante" type="{http://ws.rhweb_ws.nextage.com.br/}pessoa" minOccurs="0"/>
 *         &lt;element name="cancelada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cidadeTrabalho" type="{http://ws.rhweb_ws.nextage.com.br/}cidade" minOccurs="0"/>
 *         &lt;element name="conhecimentoTecnico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataAprovacao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataEnvioAprovacao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataFechada" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataPrevisaoEntrega" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataRequisicao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="descricaoAtividade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eap" type="{http://ws.rhweb_ws.nextage.com.br/}eap" minOccurs="0"/>
 *         &lt;element name="eapMultiEmpresa" type="{http://ws.rhweb_ws.nextage.com.br/}eapMultiEmpresa" minOccurs="0"/>
 *         &lt;element name="encarregado" type="{http://ws.rhweb_ws.nextage.com.br/}pessoa" minOccurs="0"/>
 *         &lt;element name="escolaridade" type="{http://ws.rhweb_ws.nextage.com.br/}escolaridade" minOccurs="0"/>
 *         &lt;element name="experiencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formatadoCancelado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formatadoFechado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formatadoHoristaMensalista" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formatadoNecesRecursoTi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formatadoNivelConfidencial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formatadoSexo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formatadoTipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formatadoTrabalhoEmAltura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formatadoUrgente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="funcao" type="{http://ws.rhweb_ws.nextage.com.br/}funcao" minOccurs="0"/>
 *         &lt;element name="funcaoSalario" type="{http://ws.rhweb_ws.nextage.com.br/}funcaoSalario" minOccurs="0"/>
 *         &lt;element name="horistaMensalista" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="localTrabalho" type="{http://ws.rhweb_ws.nextage.com.br/}localTrabalho" minOccurs="0"/>
 *         &lt;element name="necessidadeRecursoTi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nivelConfidencialidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observacaoRh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="origemMo" type="{http://ws.rhweb_ws.nextage.com.br/}origemMo" minOccurs="0"/>
 *         &lt;element name="qtdeIndicacao" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="qtdeSolicitada" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="requisitante" type="{http://ws.rhweb_ws.nextage.com.br/}pessoa" minOccurs="0"/>
 *         &lt;element name="responsabilidades" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salarioManual" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="salarioProposto" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="salarioRh" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="sexo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subArea" type="{http://ws.rhweb_ws.nextage.com.br/}subArea" minOccurs="0"/>
 *         &lt;element name="subordinacao" type="{http://ws.rhweb_ws.nextage.com.br/}subordinacao" minOccurs="0"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoContratacao" type="{http://ws.rhweb_ws.nextage.com.br/}tipoContratacao" minOccurs="0"/>
 *         &lt;element name="tipoDivulgacao" type="{http://ws.rhweb_ws.nextage.com.br/}vagaTipoDivulgacao" minOccurs="0"/>
 *         &lt;element name="trabalhoAltura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="turno" type="{http://ws.rhweb_ws.nextage.com.br/}turno" minOccurs="0"/>
 *         &lt;element name="ua" type="{http://ws.rhweb_ws.nextage.com.br/}ua" minOccurs="0"/>
 *         &lt;element name="ufTrabalho" type="{http://ws.rhweb_ws.nextage.com.br/}uf" minOccurs="0"/>
 *         &lt;element name="urgente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vagaRequisicaoFluxo" type="{http://ws.rhweb_ws.nextage.com.br/}vagaRequisicaoFluxo" minOccurs="0"/>
 *         &lt;element name="vagaTipo" type="{http://ws.rhweb_ws.nextage.com.br/}vagaTipo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vagaRequisicao", propOrder = {
    "aprovadorOrdemAtual",
    "area",
    "cadastrante",
    "cancelada",
    "cidadeTrabalho",
    "conhecimentoTecnico",
    "dataAprovacao",
    "dataEnvioAprovacao",
    "dataFechada",
    "dataPrevisaoEntrega",
    "dataRequisicao",
    "descricaoAtividade",
    "eap",
    "eapMultiEmpresa",
    "encarregado",
    "escolaridade",
    "experiencia",
    "fechada",
    "formatadoCancelado",
    "formatadoFechado",
    "formatadoHoristaMensalista",
    "formatadoNecesRecursoTi",
    "formatadoNivelConfidencial",
    "formatadoSexo",
    "formatadoTipo",
    "formatadoTrabalhoEmAltura",
    "formatadoUrgente",
    "funcao",
    "funcaoSalario",
    "horistaMensalista",
    "id",
    "localTrabalho",
    "necessidadeRecursoTi",
    "nivelConfidencialidade",
    "observacao",
    "observacaoRh",
    "origemMo",
    "qtdeIndicacao",
    "qtdeSolicitada",
    "requisitante",
    "responsabilidades",
    "salarioManual",
    "salarioProposto",
    "salarioRh",
    "sexo",
    "status",
    "subArea",
    "subordinacao",
    "tipo",
    "tipoContratacao",
    "tipoDivulgacao",
    "trabalhoAltura",
    "turno",
    "ua",
    "ufTrabalho",
    "urgente",
    "vagaRequisicaoFluxo",
    "vagaTipo"
})
public class VagaRequisicao {

    protected Pessoa aprovadorOrdemAtual;
    protected Area area;
    protected Pessoa cadastrante;
    protected String cancelada;
    protected Cidade cidadeTrabalho;
    protected String conhecimentoTecnico;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAprovacao;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEnvioAprovacao;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFechada;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataPrevisaoEntrega;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRequisicao;
    protected String descricaoAtividade;
    protected Eap eap;
    protected EapMultiEmpresa eapMultiEmpresa;
    protected Pessoa encarregado;
    protected Escolaridade escolaridade;
    protected String experiencia;
    protected String fechada;
    protected String formatadoCancelado;
    protected String formatadoFechado;
    protected String formatadoHoristaMensalista;
    protected String formatadoNecesRecursoTi;
    protected String formatadoNivelConfidencial;
    protected String formatadoSexo;
    protected String formatadoTipo;
    protected String formatadoTrabalhoEmAltura;
    protected String formatadoUrgente;
    protected Funcao funcao;
    protected FuncaoSalario funcaoSalario;
    protected String horistaMensalista;
    protected Integer id;
    protected LocalTrabalho localTrabalho;
    protected String necessidadeRecursoTi;
    protected String nivelConfidencialidade;
    protected String observacao;
    protected String observacaoRh;
    protected OrigemMo origemMo;
    protected int qtdeIndicacao;
    protected int qtdeSolicitada;
    protected Pessoa requisitante;
    protected String responsabilidades;
    protected Boolean salarioManual;
    protected Double salarioProposto;
    protected Double salarioRh;
    protected String sexo;
    protected String status;
    protected SubArea subArea;
    protected Subordinacao subordinacao;
    protected String tipo;
    protected TipoContratacao tipoContratacao;
    protected VagaTipoDivulgacao tipoDivulgacao;
    protected String trabalhoAltura;
    protected Turno turno;
    protected Ua ua;
    protected Uf ufTrabalho;
    protected String urgente;
    protected VagaRequisicaoFluxo vagaRequisicaoFluxo;
    protected VagaTipo vagaTipo;

    /**
     * Gets the value of the aprovadorOrdemAtual property.
     * 
     * @return
     *     possible object is
     *     {@link Pessoa }
     *     
     */
    public Pessoa getAprovadorOrdemAtual() {
        return aprovadorOrdemAtual;
    }

    /**
     * Sets the value of the aprovadorOrdemAtual property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pessoa }
     *     
     */
    public void setAprovadorOrdemAtual(Pessoa value) {
        this.aprovadorOrdemAtual = value;
    }

    /**
     * Gets the value of the area property.
     * 
     * @return
     *     possible object is
     *     {@link Area }
     *     
     */
    public Area getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     * 
     * @param value
     *     allowed object is
     *     {@link Area }
     *     
     */
    public void setArea(Area value) {
        this.area = value;
    }

    /**
     * Gets the value of the cadastrante property.
     * 
     * @return
     *     possible object is
     *     {@link Pessoa }
     *     
     */
    public Pessoa getCadastrante() {
        return cadastrante;
    }

    /**
     * Sets the value of the cadastrante property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pessoa }
     *     
     */
    public void setCadastrante(Pessoa value) {
        this.cadastrante = value;
    }

    /**
     * Gets the value of the cancelada property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancelada() {
        return cancelada;
    }

    /**
     * Sets the value of the cancelada property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancelada(String value) {
        this.cancelada = value;
    }

    /**
     * Gets the value of the cidadeTrabalho property.
     * 
     * @return
     *     possible object is
     *     {@link Cidade }
     *     
     */
    public Cidade getCidadeTrabalho() {
        return cidadeTrabalho;
    }

    /**
     * Sets the value of the cidadeTrabalho property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cidade }
     *     
     */
    public void setCidadeTrabalho(Cidade value) {
        this.cidadeTrabalho = value;
    }

    /**
     * Gets the value of the conhecimentoTecnico property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConhecimentoTecnico() {
        return conhecimentoTecnico;
    }

    /**
     * Sets the value of the conhecimentoTecnico property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConhecimentoTecnico(String value) {
        this.conhecimentoTecnico = value;
    }

    /**
     * Gets the value of the dataAprovacao property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAprovacao() {
        return dataAprovacao;
    }

    /**
     * Sets the value of the dataAprovacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAprovacao(XMLGregorianCalendar value) {
        this.dataAprovacao = value;
    }

    /**
     * Gets the value of the dataEnvioAprovacao property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEnvioAprovacao() {
        return dataEnvioAprovacao;
    }

    /**
     * Sets the value of the dataEnvioAprovacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEnvioAprovacao(XMLGregorianCalendar value) {
        this.dataEnvioAprovacao = value;
    }

    /**
     * Gets the value of the dataFechada property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFechada() {
        return dataFechada;
    }

    /**
     * Sets the value of the dataFechada property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFechada(XMLGregorianCalendar value) {
        this.dataFechada = value;
    }

    /**
     * Gets the value of the dataPrevisaoEntrega property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataPrevisaoEntrega() {
        return dataPrevisaoEntrega;
    }

    /**
     * Sets the value of the dataPrevisaoEntrega property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataPrevisaoEntrega(XMLGregorianCalendar value) {
        this.dataPrevisaoEntrega = value;
    }

    /**
     * Gets the value of the dataRequisicao property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRequisicao() {
        return dataRequisicao;
    }

    /**
     * Sets the value of the dataRequisicao property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRequisicao(XMLGregorianCalendar value) {
        this.dataRequisicao = value;
    }

    /**
     * Gets the value of the descricaoAtividade property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescricaoAtividade() {
        return descricaoAtividade;
    }

    /**
     * Sets the value of the descricaoAtividade property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescricaoAtividade(String value) {
        this.descricaoAtividade = value;
    }

    /**
     * Gets the value of the eap property.
     * 
     * @return
     *     possible object is
     *     {@link Eap }
     *     
     */
    public Eap getEap() {
        return eap;
    }

    /**
     * Sets the value of the eap property.
     * 
     * @param value
     *     allowed object is
     *     {@link Eap }
     *     
     */
    public void setEap(Eap value) {
        this.eap = value;
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
     *     {@link Pessoa }
     *     
     */
    public Pessoa getEncarregado() {
        return encarregado;
    }

    /**
     * Sets the value of the encarregado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pessoa }
     *     
     */
    public void setEncarregado(Pessoa value) {
        this.encarregado = value;
    }

    /**
     * Gets the value of the escolaridade property.
     * 
     * @return
     *     possible object is
     *     {@link Escolaridade }
     *     
     */
    public Escolaridade getEscolaridade() {
        return escolaridade;
    }

    /**
     * Sets the value of the escolaridade property.
     * 
     * @param value
     *     allowed object is
     *     {@link Escolaridade }
     *     
     */
    public void setEscolaridade(Escolaridade value) {
        this.escolaridade = value;
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
     * Gets the value of the fechada property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechada() {
        return fechada;
    }

    /**
     * Sets the value of the fechada property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechada(String value) {
        this.fechada = value;
    }

    /**
     * Gets the value of the formatadoCancelado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatadoCancelado() {
        return formatadoCancelado;
    }

    /**
     * Sets the value of the formatadoCancelado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatadoCancelado(String value) {
        this.formatadoCancelado = value;
    }

    /**
     * Gets the value of the formatadoFechado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatadoFechado() {
        return formatadoFechado;
    }

    /**
     * Sets the value of the formatadoFechado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatadoFechado(String value) {
        this.formatadoFechado = value;
    }

    /**
     * Gets the value of the formatadoHoristaMensalista property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatadoHoristaMensalista() {
        return formatadoHoristaMensalista;
    }

    /**
     * Sets the value of the formatadoHoristaMensalista property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatadoHoristaMensalista(String value) {
        this.formatadoHoristaMensalista = value;
    }

    /**
     * Gets the value of the formatadoNecesRecursoTi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatadoNecesRecursoTi() {
        return formatadoNecesRecursoTi;
    }

    /**
     * Sets the value of the formatadoNecesRecursoTi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatadoNecesRecursoTi(String value) {
        this.formatadoNecesRecursoTi = value;
    }

    /**
     * Gets the value of the formatadoNivelConfidencial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatadoNivelConfidencial() {
        return formatadoNivelConfidencial;
    }

    /**
     * Sets the value of the formatadoNivelConfidencial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatadoNivelConfidencial(String value) {
        this.formatadoNivelConfidencial = value;
    }

    /**
     * Gets the value of the formatadoSexo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatadoSexo() {
        return formatadoSexo;
    }

    /**
     * Sets the value of the formatadoSexo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatadoSexo(String value) {
        this.formatadoSexo = value;
    }

    /**
     * Gets the value of the formatadoTipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatadoTipo() {
        return formatadoTipo;
    }

    /**
     * Sets the value of the formatadoTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatadoTipo(String value) {
        this.formatadoTipo = value;
    }

    /**
     * Gets the value of the formatadoTrabalhoEmAltura property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatadoTrabalhoEmAltura() {
        return formatadoTrabalhoEmAltura;
    }

    /**
     * Sets the value of the formatadoTrabalhoEmAltura property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatadoTrabalhoEmAltura(String value) {
        this.formatadoTrabalhoEmAltura = value;
    }

    /**
     * Gets the value of the formatadoUrgente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatadoUrgente() {
        return formatadoUrgente;
    }

    /**
     * Sets the value of the formatadoUrgente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatadoUrgente(String value) {
        this.formatadoUrgente = value;
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
     * Gets the value of the funcaoSalario property.
     * 
     * @return
     *     possible object is
     *     {@link FuncaoSalario }
     *     
     */
    public FuncaoSalario getFuncaoSalario() {
        return funcaoSalario;
    }

    /**
     * Sets the value of the funcaoSalario property.
     * 
     * @param value
     *     allowed object is
     *     {@link FuncaoSalario }
     *     
     */
    public void setFuncaoSalario(FuncaoSalario value) {
        this.funcaoSalario = value;
    }

    /**
     * Gets the value of the horistaMensalista property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoristaMensalista() {
        return horistaMensalista;
    }

    /**
     * Sets the value of the horistaMensalista property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoristaMensalista(String value) {
        this.horistaMensalista = value;
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
     * Gets the value of the localTrabalho property.
     * 
     * @return
     *     possible object is
     *     {@link LocalTrabalho }
     *     
     */
    public LocalTrabalho getLocalTrabalho() {
        return localTrabalho;
    }

    /**
     * Sets the value of the localTrabalho property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalTrabalho }
     *     
     */
    public void setLocalTrabalho(LocalTrabalho value) {
        this.localTrabalho = value;
    }

    /**
     * Gets the value of the necessidadeRecursoTi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNecessidadeRecursoTi() {
        return necessidadeRecursoTi;
    }

    /**
     * Sets the value of the necessidadeRecursoTi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNecessidadeRecursoTi(String value) {
        this.necessidadeRecursoTi = value;
    }

    /**
     * Gets the value of the nivelConfidencialidade property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNivelConfidencialidade() {
        return nivelConfidencialidade;
    }

    /**
     * Sets the value of the nivelConfidencialidade property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNivelConfidencialidade(String value) {
        this.nivelConfidencialidade = value;
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
     * Gets the value of the observacaoRh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacaoRh() {
        return observacaoRh;
    }

    /**
     * Sets the value of the observacaoRh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacaoRh(String value) {
        this.observacaoRh = value;
    }

    /**
     * Gets the value of the origemMo property.
     * 
     * @return
     *     possible object is
     *     {@link OrigemMo }
     *     
     */
    public OrigemMo getOrigemMo() {
        return origemMo;
    }

    /**
     * Sets the value of the origemMo property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrigemMo }
     *     
     */
    public void setOrigemMo(OrigemMo value) {
        this.origemMo = value;
    }

    /**
     * Gets the value of the qtdeIndicacao property.
     * 
     */
    public int getQtdeIndicacao() {
        return qtdeIndicacao;
    }

    /**
     * Sets the value of the qtdeIndicacao property.
     * 
     */
    public void setQtdeIndicacao(int value) {
        this.qtdeIndicacao = value;
    }

    /**
     * Gets the value of the qtdeSolicitada property.
     * 
     */
    public int getQtdeSolicitada() {
        return qtdeSolicitada;
    }

    /**
     * Sets the value of the qtdeSolicitada property.
     * 
     */
    public void setQtdeSolicitada(int value) {
        this.qtdeSolicitada = value;
    }

    /**
     * Gets the value of the requisitante property.
     * 
     * @return
     *     possible object is
     *     {@link Pessoa }
     *     
     */
    public Pessoa getRequisitante() {
        return requisitante;
    }

    /**
     * Sets the value of the requisitante property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pessoa }
     *     
     */
    public void setRequisitante(Pessoa value) {
        this.requisitante = value;
    }

    /**
     * Gets the value of the responsabilidades property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsabilidades() {
        return responsabilidades;
    }

    /**
     * Sets the value of the responsabilidades property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsabilidades(String value) {
        this.responsabilidades = value;
    }

    /**
     * Gets the value of the salarioManual property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSalarioManual() {
        return salarioManual;
    }

    /**
     * Sets the value of the salarioManual property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSalarioManual(Boolean value) {
        this.salarioManual = value;
    }

    /**
     * Gets the value of the salarioProposto property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSalarioProposto() {
        return salarioProposto;
    }

    /**
     * Sets the value of the salarioProposto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSalarioProposto(Double value) {
        this.salarioProposto = value;
    }

    /**
     * Gets the value of the salarioRh property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSalarioRh() {
        return salarioRh;
    }

    /**
     * Sets the value of the salarioRh property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSalarioRh(Double value) {
        this.salarioRh = value;
    }

    /**
     * Gets the value of the sexo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Sets the value of the sexo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSexo(String value) {
        this.sexo = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the subArea property.
     * 
     * @return
     *     possible object is
     *     {@link SubArea }
     *     
     */
    public SubArea getSubArea() {
        return subArea;
    }

    /**
     * Sets the value of the subArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubArea }
     *     
     */
    public void setSubArea(SubArea value) {
        this.subArea = value;
    }

    /**
     * Gets the value of the subordinacao property.
     * 
     * @return
     *     possible object is
     *     {@link Subordinacao }
     *     
     */
    public Subordinacao getSubordinacao() {
        return subordinacao;
    }

    /**
     * Sets the value of the subordinacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Subordinacao }
     *     
     */
    public void setSubordinacao(Subordinacao value) {
        this.subordinacao = value;
    }

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Gets the value of the tipoContratacao property.
     * 
     * @return
     *     possible object is
     *     {@link TipoContratacao }
     *     
     */
    public TipoContratacao getTipoContratacao() {
        return tipoContratacao;
    }

    /**
     * Sets the value of the tipoContratacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoContratacao }
     *     
     */
    public void setTipoContratacao(TipoContratacao value) {
        this.tipoContratacao = value;
    }

    /**
     * Gets the value of the tipoDivulgacao property.
     * 
     * @return
     *     possible object is
     *     {@link VagaTipoDivulgacao }
     *     
     */
    public VagaTipoDivulgacao getTipoDivulgacao() {
        return tipoDivulgacao;
    }

    /**
     * Sets the value of the tipoDivulgacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link VagaTipoDivulgacao }
     *     
     */
    public void setTipoDivulgacao(VagaTipoDivulgacao value) {
        this.tipoDivulgacao = value;
    }

    /**
     * Gets the value of the trabalhoAltura property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrabalhoAltura() {
        return trabalhoAltura;
    }

    /**
     * Sets the value of the trabalhoAltura property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrabalhoAltura(String value) {
        this.trabalhoAltura = value;
    }

    /**
     * Gets the value of the turno property.
     * 
     * @return
     *     possible object is
     *     {@link Turno }
     *     
     */
    public Turno getTurno() {
        return turno;
    }

    /**
     * Sets the value of the turno property.
     * 
     * @param value
     *     allowed object is
     *     {@link Turno }
     *     
     */
    public void setTurno(Turno value) {
        this.turno = value;
    }

    /**
     * Gets the value of the ua property.
     * 
     * @return
     *     possible object is
     *     {@link Ua }
     *     
     */
    public Ua getUa() {
        return ua;
    }

    /**
     * Sets the value of the ua property.
     * 
     * @param value
     *     allowed object is
     *     {@link Ua }
     *     
     */
    public void setUa(Ua value) {
        this.ua = value;
    }

    /**
     * Gets the value of the ufTrabalho property.
     * 
     * @return
     *     possible object is
     *     {@link Uf }
     *     
     */
    public Uf getUfTrabalho() {
        return ufTrabalho;
    }

    /**
     * Sets the value of the ufTrabalho property.
     * 
     * @param value
     *     allowed object is
     *     {@link Uf }
     *     
     */
    public void setUfTrabalho(Uf value) {
        this.ufTrabalho = value;
    }

    /**
     * Gets the value of the urgente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrgente() {
        return urgente;
    }

    /**
     * Sets the value of the urgente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrgente(String value) {
        this.urgente = value;
    }

    /**
     * Gets the value of the vagaRequisicaoFluxo property.
     * 
     * @return
     *     possible object is
     *     {@link VagaRequisicaoFluxo }
     *     
     */
    public VagaRequisicaoFluxo getVagaRequisicaoFluxo() {
        return vagaRequisicaoFluxo;
    }

    /**
     * Sets the value of the vagaRequisicaoFluxo property.
     * 
     * @param value
     *     allowed object is
     *     {@link VagaRequisicaoFluxo }
     *     
     */
    public void setVagaRequisicaoFluxo(VagaRequisicaoFluxo value) {
        this.vagaRequisicaoFluxo = value;
    }

    /**
     * Gets the value of the vagaTipo property.
     * 
     * @return
     *     possible object is
     *     {@link VagaTipo }
     *     
     */
    public VagaTipo getVagaTipo() {
        return vagaTipo;
    }

    /**
     * Sets the value of the vagaTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link VagaTipo }
     *     
     */
    public void setVagaTipo(VagaTipo value) {
        this.vagaTipo = value;
    }

}
