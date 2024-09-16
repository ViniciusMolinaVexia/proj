
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for pessoaVaga complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pessoaVaga">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aprovadoCv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cnpj" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataAprovacaoCv" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataDesistenteReprovado" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataEncSetorAcesso" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataEncSetorDocumentacao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataEncSetorDp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataEncSetorExame" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataEncSetorIntegracao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataEntradaArea" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataEnvioCv" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataFinalizacao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataLiberacaoCracha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataRecrutamento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataSolicitacaoCracha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="desistenteReprovado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desvinculado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="etapaAdmissao" type="{http://ws.rhweb_ws.nextage.com.br/}etapaAdmissao" minOccurs="0"/>
 *         &lt;element name="folhaPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="motivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observacaoEncSetorAcesso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observacaoEncSetorDoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observacaoEncSetorDp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observacaoEncSetorExame" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observacaoEncSetorIntegracao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observacaoRecrutamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="passaProximaEtapa" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element ref="{http://ws.rhweb_ws.nextage.com.br/}pessoa" minOccurs="0"/>
 *         &lt;element name="pessoaVagaId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="statusExame" type="{http://ws.rhweb_ws.nextage.com.br/}statusExame" minOccurs="0"/>
 *         &lt;element name="tipoContratacao" type="{http://ws.rhweb_ws.nextage.com.br/}tipoContratacao" minOccurs="0"/>
 *         &lt;element name="usuarioDesistenteReprovado" type="{http://ws.rhweb_ws.nextage.com.br/}usuario" minOccurs="0"/>
 *         &lt;element name="usuarioEncAcesso" type="{http://ws.rhweb_ws.nextage.com.br/}usuario" minOccurs="0"/>
 *         &lt;element name="usuarioEncDocumentacao" type="{http://ws.rhweb_ws.nextage.com.br/}usuario" minOccurs="0"/>
 *         &lt;element name="usuarioEncDp" type="{http://ws.rhweb_ws.nextage.com.br/}usuario" minOccurs="0"/>
 *         &lt;element name="usuarioEncExame" type="{http://ws.rhweb_ws.nextage.com.br/}usuario" minOccurs="0"/>
 *         &lt;element name="usuarioEncIntegracao" type="{http://ws.rhweb_ws.nextage.com.br/}usuario" minOccurs="0"/>
 *         &lt;element name="usuarioFinalizado" type="{http://ws.rhweb_ws.nextage.com.br/}usuario" minOccurs="0"/>
 *         &lt;element name="vaga" type="{http://ws.rhweb_ws.nextage.com.br/}vaga" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pessoaVaga", propOrder = {
    "aprovadoCv",
    "cnpj",
    "dataAprovacaoCv",
    "dataDesistenteReprovado",
    "dataEncSetorAcesso",
    "dataEncSetorDocumentacao",
    "dataEncSetorDp",
    "dataEncSetorExame",
    "dataEncSetorIntegracao",
    "dataEntradaArea",
    "dataEnvioCv",
    "dataFinalizacao",
    "dataLiberacaoCracha",
    "dataRecrutamento",
    "dataSolicitacaoCracha",
    "desistenteReprovado",
    "desvinculado",
    "etapaAdmissao",
    "folhaPagamento",
    "motivo",
    "observacaoEncSetorAcesso",
    "observacaoEncSetorDoc",
    "observacaoEncSetorDp",
    "observacaoEncSetorExame",
    "observacaoEncSetorIntegracao",
    "observacaoRecrutamento",
    "passaProximaEtapa",
    "pessoa",
    "pessoaVagaId",
    "statusExame",
    "tipoContratacao",
    "usuarioDesistenteReprovado",
    "usuarioEncAcesso",
    "usuarioEncDocumentacao",
    "usuarioEncDp",
    "usuarioEncExame",
    "usuarioEncIntegracao",
    "usuarioFinalizado",
    "vaga"
})
public class PessoaVaga {

    protected String aprovadoCv;
    protected String cnpj;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAprovacaoCv;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDesistenteReprovado;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEncSetorAcesso;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEncSetorDocumentacao;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEncSetorDp;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEncSetorExame;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEncSetorIntegracao;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEntradaArea;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEnvioCv;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFinalizacao;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataLiberacaoCracha;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRecrutamento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataSolicitacaoCracha;
    protected String desistenteReprovado;
    protected String desvinculado;
    protected EtapaAdmissao etapaAdmissao;
    protected String folhaPagamento;
    protected String motivo;
    protected String observacaoEncSetorAcesso;
    protected String observacaoEncSetorDoc;
    protected String observacaoEncSetorDp;
    protected String observacaoEncSetorExame;
    protected String observacaoEncSetorIntegracao;
    protected String observacaoRecrutamento;
    protected Boolean passaProximaEtapa;
    @XmlElement(namespace = "http://ws.rhweb_ws.nextage.com.br/")
    protected Pessoa pessoa;
    protected Integer pessoaVagaId;
    protected StatusExame statusExame;
    protected TipoContratacao tipoContratacao;
    protected Usuario usuarioDesistenteReprovado;
    protected Usuario usuarioEncAcesso;
    protected Usuario usuarioEncDocumentacao;
    protected Usuario usuarioEncDp;
    protected Usuario usuarioEncExame;
    protected Usuario usuarioEncIntegracao;
    protected Usuario usuarioFinalizado;
    protected Vaga vaga;

    /**
     * Gets the value of the aprovadoCv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAprovadoCv() {
        return aprovadoCv;
    }

    /**
     * Sets the value of the aprovadoCv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAprovadoCv(String value) {
        this.aprovadoCv = value;
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
     * Gets the value of the dataAprovacaoCv property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAprovacaoCv() {
        return dataAprovacaoCv;
    }

    /**
     * Sets the value of the dataAprovacaoCv property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAprovacaoCv(XMLGregorianCalendar value) {
        this.dataAprovacaoCv = value;
    }

    /**
     * Gets the value of the dataDesistenteReprovado property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDesistenteReprovado() {
        return dataDesistenteReprovado;
    }

    /**
     * Sets the value of the dataDesistenteReprovado property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDesistenteReprovado(XMLGregorianCalendar value) {
        this.dataDesistenteReprovado = value;
    }

    /**
     * Gets the value of the dataEncSetorAcesso property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEncSetorAcesso() {
        return dataEncSetorAcesso;
    }

    /**
     * Sets the value of the dataEncSetorAcesso property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEncSetorAcesso(XMLGregorianCalendar value) {
        this.dataEncSetorAcesso = value;
    }

    /**
     * Gets the value of the dataEncSetorDocumentacao property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEncSetorDocumentacao() {
        return dataEncSetorDocumentacao;
    }

    /**
     * Sets the value of the dataEncSetorDocumentacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEncSetorDocumentacao(XMLGregorianCalendar value) {
        this.dataEncSetorDocumentacao = value;
    }

    /**
     * Gets the value of the dataEncSetorDp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEncSetorDp() {
        return dataEncSetorDp;
    }

    /**
     * Sets the value of the dataEncSetorDp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEncSetorDp(XMLGregorianCalendar value) {
        this.dataEncSetorDp = value;
    }

    /**
     * Gets the value of the dataEncSetorExame property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEncSetorExame() {
        return dataEncSetorExame;
    }

    /**
     * Sets the value of the dataEncSetorExame property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEncSetorExame(XMLGregorianCalendar value) {
        this.dataEncSetorExame = value;
    }

    /**
     * Gets the value of the dataEncSetorIntegracao property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEncSetorIntegracao() {
        return dataEncSetorIntegracao;
    }

    /**
     * Sets the value of the dataEncSetorIntegracao property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEncSetorIntegracao(XMLGregorianCalendar value) {
        this.dataEncSetorIntegracao = value;
    }

    /**
     * Gets the value of the dataEntradaArea property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEntradaArea() {
        return dataEntradaArea;
    }

    /**
     * Sets the value of the dataEntradaArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEntradaArea(XMLGregorianCalendar value) {
        this.dataEntradaArea = value;
    }

    /**
     * Gets the value of the dataEnvioCv property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEnvioCv() {
        return dataEnvioCv;
    }

    /**
     * Sets the value of the dataEnvioCv property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEnvioCv(XMLGregorianCalendar value) {
        this.dataEnvioCv = value;
    }

    /**
     * Gets the value of the dataFinalizacao property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFinalizacao() {
        return dataFinalizacao;
    }

    /**
     * Sets the value of the dataFinalizacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFinalizacao(XMLGregorianCalendar value) {
        this.dataFinalizacao = value;
    }

    /**
     * Gets the value of the dataLiberacaoCracha property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataLiberacaoCracha() {
        return dataLiberacaoCracha;
    }

    /**
     * Sets the value of the dataLiberacaoCracha property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataLiberacaoCracha(XMLGregorianCalendar value) {
        this.dataLiberacaoCracha = value;
    }

    /**
     * Gets the value of the dataRecrutamento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRecrutamento() {
        return dataRecrutamento;
    }

    /**
     * Sets the value of the dataRecrutamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRecrutamento(XMLGregorianCalendar value) {
        this.dataRecrutamento = value;
    }

    /**
     * Gets the value of the dataSolicitacaoCracha property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataSolicitacaoCracha() {
        return dataSolicitacaoCracha;
    }

    /**
     * Sets the value of the dataSolicitacaoCracha property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataSolicitacaoCracha(XMLGregorianCalendar value) {
        this.dataSolicitacaoCracha = value;
    }

    /**
     * Gets the value of the desistenteReprovado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesistenteReprovado() {
        return desistenteReprovado;
    }

    /**
     * Sets the value of the desistenteReprovado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesistenteReprovado(String value) {
        this.desistenteReprovado = value;
    }

    /**
     * Gets the value of the desvinculado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesvinculado() {
        return desvinculado;
    }

    /**
     * Sets the value of the desvinculado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesvinculado(String value) {
        this.desvinculado = value;
    }

    /**
     * Gets the value of the etapaAdmissao property.
     * 
     * @return
     *     possible object is
     *     {@link EtapaAdmissao }
     *     
     */
    public EtapaAdmissao getEtapaAdmissao() {
        return etapaAdmissao;
    }

    /**
     * Sets the value of the etapaAdmissao property.
     * 
     * @param value
     *     allowed object is
     *     {@link EtapaAdmissao }
     *     
     */
    public void setEtapaAdmissao(EtapaAdmissao value) {
        this.etapaAdmissao = value;
    }

    /**
     * Gets the value of the folhaPagamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolhaPagamento() {
        return folhaPagamento;
    }

    /**
     * Sets the value of the folhaPagamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolhaPagamento(String value) {
        this.folhaPagamento = value;
    }

    /**
     * Gets the value of the motivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Sets the value of the motivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivo(String value) {
        this.motivo = value;
    }

    /**
     * Gets the value of the observacaoEncSetorAcesso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacaoEncSetorAcesso() {
        return observacaoEncSetorAcesso;
    }

    /**
     * Sets the value of the observacaoEncSetorAcesso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacaoEncSetorAcesso(String value) {
        this.observacaoEncSetorAcesso = value;
    }

    /**
     * Gets the value of the observacaoEncSetorDoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacaoEncSetorDoc() {
        return observacaoEncSetorDoc;
    }

    /**
     * Sets the value of the observacaoEncSetorDoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacaoEncSetorDoc(String value) {
        this.observacaoEncSetorDoc = value;
    }

    /**
     * Gets the value of the observacaoEncSetorDp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacaoEncSetorDp() {
        return observacaoEncSetorDp;
    }

    /**
     * Sets the value of the observacaoEncSetorDp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacaoEncSetorDp(String value) {
        this.observacaoEncSetorDp = value;
    }

    /**
     * Gets the value of the observacaoEncSetorExame property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacaoEncSetorExame() {
        return observacaoEncSetorExame;
    }

    /**
     * Sets the value of the observacaoEncSetorExame property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacaoEncSetorExame(String value) {
        this.observacaoEncSetorExame = value;
    }

    /**
     * Gets the value of the observacaoEncSetorIntegracao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacaoEncSetorIntegracao() {
        return observacaoEncSetorIntegracao;
    }

    /**
     * Sets the value of the observacaoEncSetorIntegracao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacaoEncSetorIntegracao(String value) {
        this.observacaoEncSetorIntegracao = value;
    }

    /**
     * Gets the value of the observacaoRecrutamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacaoRecrutamento() {
        return observacaoRecrutamento;
    }

    /**
     * Sets the value of the observacaoRecrutamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacaoRecrutamento(String value) {
        this.observacaoRecrutamento = value;
    }

    /**
     * Gets the value of the passaProximaEtapa property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPassaProximaEtapa() {
        return passaProximaEtapa;
    }

    /**
     * Sets the value of the passaProximaEtapa property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPassaProximaEtapa(Boolean value) {
        this.passaProximaEtapa = value;
    }

    /**
     * Gets the value of the pessoa property.
     * 
     * @return
     *     possible object is
     *     {@link Pessoa }
     *     
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * Sets the value of the pessoa property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pessoa }
     *     
     */
    public void setPessoa(Pessoa value) {
        this.pessoa = value;
    }

    /**
     * Gets the value of the pessoaVagaId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPessoaVagaId() {
        return pessoaVagaId;
    }

    /**
     * Sets the value of the pessoaVagaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPessoaVagaId(Integer value) {
        this.pessoaVagaId = value;
    }

    /**
     * Gets the value of the statusExame property.
     * 
     * @return
     *     possible object is
     *     {@link StatusExame }
     *     
     */
    public StatusExame getStatusExame() {
        return statusExame;
    }

    /**
     * Sets the value of the statusExame property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusExame }
     *     
     */
    public void setStatusExame(StatusExame value) {
        this.statusExame = value;
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
     * Gets the value of the usuarioDesistenteReprovado property.
     * 
     * @return
     *     possible object is
     *     {@link Usuario }
     *     
     */
    public Usuario getUsuarioDesistenteReprovado() {
        return usuarioDesistenteReprovado;
    }

    /**
     * Sets the value of the usuarioDesistenteReprovado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Usuario }
     *     
     */
    public void setUsuarioDesistenteReprovado(Usuario value) {
        this.usuarioDesistenteReprovado = value;
    }

    /**
     * Gets the value of the usuarioEncAcesso property.
     * 
     * @return
     *     possible object is
     *     {@link Usuario }
     *     
     */
    public Usuario getUsuarioEncAcesso() {
        return usuarioEncAcesso;
    }

    /**
     * Sets the value of the usuarioEncAcesso property.
     * 
     * @param value
     *     allowed object is
     *     {@link Usuario }
     *     
     */
    public void setUsuarioEncAcesso(Usuario value) {
        this.usuarioEncAcesso = value;
    }

    /**
     * Gets the value of the usuarioEncDocumentacao property.
     * 
     * @return
     *     possible object is
     *     {@link Usuario }
     *     
     */
    public Usuario getUsuarioEncDocumentacao() {
        return usuarioEncDocumentacao;
    }

    /**
     * Sets the value of the usuarioEncDocumentacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Usuario }
     *     
     */
    public void setUsuarioEncDocumentacao(Usuario value) {
        this.usuarioEncDocumentacao = value;
    }

    /**
     * Gets the value of the usuarioEncDp property.
     * 
     * @return
     *     possible object is
     *     {@link Usuario }
     *     
     */
    public Usuario getUsuarioEncDp() {
        return usuarioEncDp;
    }

    /**
     * Sets the value of the usuarioEncDp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Usuario }
     *     
     */
    public void setUsuarioEncDp(Usuario value) {
        this.usuarioEncDp = value;
    }

    /**
     * Gets the value of the usuarioEncExame property.
     * 
     * @return
     *     possible object is
     *     {@link Usuario }
     *     
     */
    public Usuario getUsuarioEncExame() {
        return usuarioEncExame;
    }

    /**
     * Sets the value of the usuarioEncExame property.
     * 
     * @param value
     *     allowed object is
     *     {@link Usuario }
     *     
     */
    public void setUsuarioEncExame(Usuario value) {
        this.usuarioEncExame = value;
    }

    /**
     * Gets the value of the usuarioEncIntegracao property.
     * 
     * @return
     *     possible object is
     *     {@link Usuario }
     *     
     */
    public Usuario getUsuarioEncIntegracao() {
        return usuarioEncIntegracao;
    }

    /**
     * Sets the value of the usuarioEncIntegracao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Usuario }
     *     
     */
    public void setUsuarioEncIntegracao(Usuario value) {
        this.usuarioEncIntegracao = value;
    }

    /**
     * Gets the value of the usuarioFinalizado property.
     * 
     * @return
     *     possible object is
     *     {@link Usuario }
     *     
     */
    public Usuario getUsuarioFinalizado() {
        return usuarioFinalizado;
    }

    /**
     * Sets the value of the usuarioFinalizado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Usuario }
     *     
     */
    public void setUsuarioFinalizado(Usuario value) {
        this.usuarioFinalizado = value;
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

}
