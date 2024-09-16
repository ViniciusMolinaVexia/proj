
package br.com.nextage.rmaweb.ws.rhweb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for vaga complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="vaga">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aprovacaoCurriculo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="area" type="{http://ws.rhweb_ws.nextage.com.br/}area" minOccurs="0"/>
 *         &lt;element name="cancelada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="conhecimentoTecnico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataAbertura" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataCadastro" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataFechamento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataFimConvocatoria" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataInicioConvocatoria" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataPrevisaoEntrega" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataRecebimento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataSolicitacao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="descricaoAtividade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eap" type="{http://ws.rhweb_ws.nextage.com.br/}eap" minOccurs="0"/>
 *         &lt;element name="eapMultiEmpresa" type="{http://ws.rhweb_ws.nextage.com.br/}eapMultiEmpresa" minOccurs="0"/>
 *         &lt;element name="empresa" type="{http://ws.rhweb_ws.nextage.com.br/}empresa" minOccurs="0"/>
 *         &lt;element name="encarregado" type="{http://ws.rhweb_ws.nextage.com.br/}pessoa" minOccurs="0"/>
 *         &lt;element name="experiencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formatadoAprovacaoCurriculo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *         &lt;element name="listPessoaVagas" type="{http://ws.rhweb_ws.nextage.com.br/}pessoaVaga" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="localTrabalho" type="{http://ws.rhweb_ws.nextage.com.br/}localTrabalho" minOccurs="0"/>
 *         &lt;element name="necessidadeRecursoTi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nivelConfidencialidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observacaoRh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="origemMo" type="{http://ws.rhweb_ws.nextage.com.br/}origemMo" minOccurs="0"/>
 *         &lt;element name="pep" type="{http://ws.rhweb_ws.nextage.com.br/}pep" minOccurs="0"/>
 *         &lt;element name="perfilCandidato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qtdeAguardandoIndicacao" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="qtdeAndamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="qtdeIndicacao" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="qtdePreenchida" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="qtdeSolicitada" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="recrutador" type="{http://ws.rhweb_ws.nextage.com.br/}pessoa" minOccurs="0"/>
 *         &lt;element name="requisitante" type="{http://ws.rhweb_ws.nextage.com.br/}pessoa" minOccurs="0"/>
 *         &lt;element name="responsabilidades" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rmo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salarioManual" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="salarioProposto" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="sexo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subArea" type="{http://ws.rhweb_ws.nextage.com.br/}subArea" minOccurs="0"/>
 *         &lt;element name="subordinacao" type="{http://ws.rhweb_ws.nextage.com.br/}subordinacao" minOccurs="0"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoContratacao" type="{http://ws.rhweb_ws.nextage.com.br/}tipoContratacao" minOccurs="0"/>
 *         &lt;element name="tipoDivulgacao" type="{http://ws.rhweb_ws.nextage.com.br/}vagaTipoDivulgacao" minOccurs="0"/>
 *         &lt;element name="tipoMo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trabalhoAltura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="turno" type="{http://ws.rhweb_ws.nextage.com.br/}turno" minOccurs="0"/>
 *         &lt;element name="ua" type="{http://ws.rhweb_ws.nextage.com.br/}ua" minOccurs="0"/>
 *         &lt;element name="urgente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuarioIdCancelamento" type="{http://ws.rhweb_ws.nextage.com.br/}usuario" minOccurs="0"/>
 *         &lt;element name="vagaDescricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vagaId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="vagaRequisicao" type="{http://ws.rhweb_ws.nextage.com.br/}vagaRequisicao" minOccurs="0"/>
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
@XmlType(name = "vaga", propOrder = {
    "aprovacaoCurriculo",
    "area",
    "cancelada",
    "codigo",
    "conhecimentoTecnico",
    "dataAbertura",
    "dataCadastro",
    "dataFechamento",
    "dataFimConvocatoria",
    "dataInicioConvocatoria",
    "dataPrevisaoEntrega",
    "dataRecebimento",
    "dataSolicitacao",
    "descricaoAtividade",
    "eap",
    "eapMultiEmpresa",
    "empresa",
    "encarregado",
    "experiencia",
    "fechada",
    "formatadoAprovacaoCurriculo",
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
    "listPessoaVagas",
    "localTrabalho",
    "necessidadeRecursoTi",
    "nivelConfidencialidade",
    "observacao",
    "observacaoRh",
    "origemMo",
    "pep",
    "perfilCandidato",
    "qtdeAguardandoIndicacao",
    "qtdeAndamento",
    "qtdeIndicacao",
    "qtdePreenchida",
    "qtdeSolicitada",
    "recrutador",
    "requisitante",
    "responsabilidades",
    "rmo",
    "salarioManual",
    "salarioProposto",
    "sexo",
    "subArea",
    "subordinacao",
    "tipo",
    "tipoContratacao",
    "tipoDivulgacao",
    "tipoMo",
    "trabalhoAltura",
    "turno",
    "ua",
    "urgente",
    "usuarioIdCancelamento",
    "vagaDescricao",
    "vagaId",
    "vagaRequisicao",
    "vagaTipo"
})
public class Vaga {

    protected String aprovacaoCurriculo;
    protected Area area;
    protected String cancelada;
    protected String codigo;
    protected String conhecimentoTecnico;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAbertura;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataCadastro;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFechamento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFimConvocatoria;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInicioConvocatoria;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataPrevisaoEntrega;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRecebimento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataSolicitacao;
    protected String descricaoAtividade;
    protected Eap eap;
    protected EapMultiEmpresa eapMultiEmpresa;
    protected Empresa empresa;
    protected Pessoa encarregado;
    protected String experiencia;
    protected String fechada;
    protected String formatadoAprovacaoCurriculo;
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
    @XmlElement(nillable = true)
    protected List<PessoaVaga> listPessoaVagas;
    protected LocalTrabalho localTrabalho;
    protected String necessidadeRecursoTi;
    protected String nivelConfidencialidade;
    protected String observacao;
    protected String observacaoRh;
    protected OrigemMo origemMo;
    protected Pep pep;
    protected String perfilCandidato;
    protected Integer qtdeAguardandoIndicacao;
    protected Integer qtdeAndamento;
    protected Integer qtdeIndicacao;
    protected Integer qtdePreenchida;
    protected int qtdeSolicitada;
    protected Pessoa recrutador;
    protected Pessoa requisitante;
    protected String responsabilidades;
    protected String rmo;
    protected Boolean salarioManual;
    protected Double salarioProposto;
    protected String sexo;
    protected SubArea subArea;
    protected Subordinacao subordinacao;
    protected String tipo;
    protected TipoContratacao tipoContratacao;
    protected VagaTipoDivulgacao tipoDivulgacao;
    protected String tipoMo;
    protected String trabalhoAltura;
    protected Turno turno;
    protected Ua ua;
    protected String urgente;
    protected Usuario usuarioIdCancelamento;
    protected String vagaDescricao;
    protected Integer vagaId;
    protected VagaRequisicao vagaRequisicao;
    protected VagaTipo vagaTipo;

    /**
     * Gets the value of the aprovacaoCurriculo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAprovacaoCurriculo() {
        return aprovacaoCurriculo;
    }

    /**
     * Sets the value of the aprovacaoCurriculo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAprovacaoCurriculo(String value) {
        this.aprovacaoCurriculo = value;
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
     * Gets the value of the dataAbertura property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAbertura() {
        return dataAbertura;
    }

    /**
     * Sets the value of the dataAbertura property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAbertura(XMLGregorianCalendar value) {
        this.dataAbertura = value;
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
     * Gets the value of the dataFechamento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFechamento() {
        return dataFechamento;
    }

    /**
     * Sets the value of the dataFechamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFechamento(XMLGregorianCalendar value) {
        this.dataFechamento = value;
    }

    /**
     * Gets the value of the dataFimConvocatoria property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFimConvocatoria() {
        return dataFimConvocatoria;
    }

    /**
     * Sets the value of the dataFimConvocatoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFimConvocatoria(XMLGregorianCalendar value) {
        this.dataFimConvocatoria = value;
    }

    /**
     * Gets the value of the dataInicioConvocatoria property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInicioConvocatoria() {
        return dataInicioConvocatoria;
    }

    /**
     * Sets the value of the dataInicioConvocatoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInicioConvocatoria(XMLGregorianCalendar value) {
        this.dataInicioConvocatoria = value;
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
     * Gets the value of the dataRecebimento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRecebimento() {
        return dataRecebimento;
    }

    /**
     * Sets the value of the dataRecebimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRecebimento(XMLGregorianCalendar value) {
        this.dataRecebimento = value;
    }

    /**
     * Gets the value of the dataSolicitacao property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataSolicitacao() {
        return dataSolicitacao;
    }

    /**
     * Sets the value of the dataSolicitacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataSolicitacao(XMLGregorianCalendar value) {
        this.dataSolicitacao = value;
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
     * Gets the value of the empresa property.
     * 
     * @return
     *     possible object is
     *     {@link Empresa }
     *     
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * Sets the value of the empresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link Empresa }
     *     
     */
    public void setEmpresa(Empresa value) {
        this.empresa = value;
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
     * Gets the value of the formatadoAprovacaoCurriculo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatadoAprovacaoCurriculo() {
        return formatadoAprovacaoCurriculo;
    }

    /**
     * Sets the value of the formatadoAprovacaoCurriculo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatadoAprovacaoCurriculo(String value) {
        this.formatadoAprovacaoCurriculo = value;
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
     * Gets the value of the listPessoaVagas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listPessoaVagas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListPessoaVagas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PessoaVaga }
     * 
     * 
     */
    public List<PessoaVaga> getListPessoaVagas() {
        if (listPessoaVagas == null) {
            listPessoaVagas = new ArrayList<PessoaVaga>();
        }
        return this.listPessoaVagas;
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
     * Gets the value of the pep property.
     * 
     * @return
     *     possible object is
     *     {@link Pep }
     *     
     */
    public Pep getPep() {
        return pep;
    }

    /**
     * Sets the value of the pep property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pep }
     *     
     */
    public void setPep(Pep value) {
        this.pep = value;
    }

    /**
     * Gets the value of the perfilCandidato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerfilCandidato() {
        return perfilCandidato;
    }

    /**
     * Sets the value of the perfilCandidato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerfilCandidato(String value) {
        this.perfilCandidato = value;
    }

    /**
     * Gets the value of the qtdeAguardandoIndicacao property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQtdeAguardandoIndicacao() {
        return qtdeAguardandoIndicacao;
    }

    /**
     * Sets the value of the qtdeAguardandoIndicacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQtdeAguardandoIndicacao(Integer value) {
        this.qtdeAguardandoIndicacao = value;
    }

    /**
     * Gets the value of the qtdeAndamento property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQtdeAndamento() {
        return qtdeAndamento;
    }

    /**
     * Sets the value of the qtdeAndamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQtdeAndamento(Integer value) {
        this.qtdeAndamento = value;
    }

    /**
     * Gets the value of the qtdeIndicacao property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQtdeIndicacao() {
        return qtdeIndicacao;
    }

    /**
     * Sets the value of the qtdeIndicacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQtdeIndicacao(Integer value) {
        this.qtdeIndicacao = value;
    }

    /**
     * Gets the value of the qtdePreenchida property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQtdePreenchida() {
        return qtdePreenchida;
    }

    /**
     * Sets the value of the qtdePreenchida property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQtdePreenchida(Integer value) {
        this.qtdePreenchida = value;
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
     * Gets the value of the recrutador property.
     * 
     * @return
     *     possible object is
     *     {@link Pessoa }
     *     
     */
    public Pessoa getRecrutador() {
        return recrutador;
    }

    /**
     * Sets the value of the recrutador property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pessoa }
     *     
     */
    public void setRecrutador(Pessoa value) {
        this.recrutador = value;
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
     * Gets the value of the rmo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRmo() {
        return rmo;
    }

    /**
     * Sets the value of the rmo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRmo(String value) {
        this.rmo = value;
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
     * Gets the value of the usuarioIdCancelamento property.
     * 
     * @return
     *     possible object is
     *     {@link Usuario }
     *     
     */
    public Usuario getUsuarioIdCancelamento() {
        return usuarioIdCancelamento;
    }

    /**
     * Sets the value of the usuarioIdCancelamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Usuario }
     *     
     */
    public void setUsuarioIdCancelamento(Usuario value) {
        this.usuarioIdCancelamento = value;
    }

    /**
     * Gets the value of the vagaDescricao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVagaDescricao() {
        return vagaDescricao;
    }

    /**
     * Sets the value of the vagaDescricao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVagaDescricao(String value) {
        this.vagaDescricao = value;
    }

    /**
     * Gets the value of the vagaId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVagaId() {
        return vagaId;
    }

    /**
     * Sets the value of the vagaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVagaId(Integer value) {
        this.vagaId = value;
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
