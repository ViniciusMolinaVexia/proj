
package br.com.nextage.rmaweb.ws.rhweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for vwRelPessoa complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="vwRelPessoa">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alojamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="alojamentoReserva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="calcAdd1DataAssinaturaContrato" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="calcAdd1DataLiberacaoCracha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="calcAdd2DiasLiberacaoCracha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="calcAdd4DiasDataAssinaturaContrato" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="calcAdd4DiasDataAux3" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="calcDifDataAdmissaoDataAux3" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifDataAdmissaoDataAux6" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifDataAssinaturaContratoDataEntradaArea" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifDataAux1DataAux3" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifDataAux3DataLiberacaoCracha" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifDataCadastroDataChegadaPrevista" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifDataCadastroVagaDataCadastroPessoa" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifDataEncaminhadoExameDataEntregaDocs" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifDataEncaminhadoExameDataExameMedico" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifDataEncaminhadoExameDataRecebimentoAso" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifDataEntregaDocsDpDataAdmissao" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifDataEntregaDocsDpDataAux1" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifDataExameMedicoDataRecebimentoAso" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifDataInicioTreinDataFimTrein" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifDataLiberacaoCrachaDataAssinaturaContrato" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifDataRecebimentoDataEntradaArea" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifTbAlojamentoQuartoPessoaDataEntradaTbAlojamentoQuartoPessoaDataSaida" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifTbVagaDataAberturaTbVagaDataRecebimento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifTbVagaDataCadastroTbVagaDataAbertura" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifTbVagaDataRecebimentoDataAdmissao" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifTbVagaDataRecebimentoDataAux6" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifTbVagaDataSolicitacaoTbVagaDataAbertura" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDifTbVagasDataRecebimentoDataEntregaDocsDp" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="dataEntradaAlojamento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataSaidaAlojamento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="enderecoAlojamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gerenteArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://ws.rhweb_ws.nextage.com.br/}pessoa" minOccurs="0"/>
 *         &lt;element name="statusPrazo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoAlojamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vwRelPessoa", propOrder = {
    "alojamento",
    "alojamentoReserva",
    "calcAdd1DataAssinaturaContrato",
    "calcAdd1DataLiberacaoCracha",
    "calcAdd2DiasLiberacaoCracha",
    "calcAdd4DiasDataAssinaturaContrato",
    "calcAdd4DiasDataAux3",
    "calcDifDataAdmissaoDataAux3",
    "calcDifDataAdmissaoDataAux6",
    "calcDifDataAssinaturaContratoDataEntradaArea",
    "calcDifDataAux1DataAux3",
    "calcDifDataAux3DataLiberacaoCracha",
    "calcDifDataCadastroDataChegadaPrevista",
    "calcDifDataCadastroVagaDataCadastroPessoa",
    "calcDifDataEncaminhadoExameDataEntregaDocs",
    "calcDifDataEncaminhadoExameDataExameMedico",
    "calcDifDataEncaminhadoExameDataRecebimentoAso",
    "calcDifDataEntregaDocsDpDataAdmissao",
    "calcDifDataEntregaDocsDpDataAux1",
    "calcDifDataExameMedicoDataRecebimentoAso",
    "calcDifDataInicioTreinDataFimTrein",
    "calcDifDataLiberacaoCrachaDataAssinaturaContrato",
    "calcDifDataRecebimentoDataEntradaArea",
    "calcDifTbAlojamentoQuartoPessoaDataEntradaTbAlojamentoQuartoPessoaDataSaida",
    "calcDifTbVagaDataAberturaTbVagaDataRecebimento",
    "calcDifTbVagaDataCadastroTbVagaDataAbertura",
    "calcDifTbVagaDataRecebimentoDataAdmissao",
    "calcDifTbVagaDataRecebimentoDataAux6",
    "calcDifTbVagaDataSolicitacaoTbVagaDataAbertura",
    "calcDifTbVagasDataRecebimentoDataEntregaDocsDp",
    "dataEntradaAlojamento",
    "dataSaidaAlojamento",
    "enderecoAlojamento",
    "gerenteArea",
    "id",
    "nome",
    "pessoa",
    "statusPrazo",
    "tipoAlojamento"
})
public class VwRelPessoa {

    protected String alojamento;
    protected String alojamentoReserva;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar calcAdd1DataAssinaturaContrato;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar calcAdd1DataLiberacaoCracha;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar calcAdd2DiasLiberacaoCracha;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar calcAdd4DiasDataAssinaturaContrato;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar calcAdd4DiasDataAux3;
    protected Integer calcDifDataAdmissaoDataAux3;
    protected Integer calcDifDataAdmissaoDataAux6;
    protected Integer calcDifDataAssinaturaContratoDataEntradaArea;
    protected Integer calcDifDataAux1DataAux3;
    protected Integer calcDifDataAux3DataLiberacaoCracha;
    protected Integer calcDifDataCadastroDataChegadaPrevista;
    protected Integer calcDifDataCadastroVagaDataCadastroPessoa;
    protected Integer calcDifDataEncaminhadoExameDataEntregaDocs;
    protected Integer calcDifDataEncaminhadoExameDataExameMedico;
    protected Integer calcDifDataEncaminhadoExameDataRecebimentoAso;
    protected Integer calcDifDataEntregaDocsDpDataAdmissao;
    protected Integer calcDifDataEntregaDocsDpDataAux1;
    protected Integer calcDifDataExameMedicoDataRecebimentoAso;
    protected Integer calcDifDataInicioTreinDataFimTrein;
    protected Integer calcDifDataLiberacaoCrachaDataAssinaturaContrato;
    protected Integer calcDifDataRecebimentoDataEntradaArea;
    protected Integer calcDifTbAlojamentoQuartoPessoaDataEntradaTbAlojamentoQuartoPessoaDataSaida;
    protected Integer calcDifTbVagaDataAberturaTbVagaDataRecebimento;
    protected Integer calcDifTbVagaDataCadastroTbVagaDataAbertura;
    protected Integer calcDifTbVagaDataRecebimentoDataAdmissao;
    protected Integer calcDifTbVagaDataRecebimentoDataAux6;
    protected Integer calcDifTbVagaDataSolicitacaoTbVagaDataAbertura;
    protected Integer calcDifTbVagasDataRecebimentoDataEntregaDocsDp;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEntradaAlojamento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataSaidaAlojamento;
    protected String enderecoAlojamento;
    protected String gerenteArea;
    protected Integer id;
    protected String nome;
    @XmlElement(namespace = "http://ws.rhweb_ws.nextage.com.br/")
    protected Pessoa pessoa;
    protected String statusPrazo;
    protected String tipoAlojamento;

    /**
     * Gets the value of the alojamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlojamento() {
        return alojamento;
    }

    /**
     * Sets the value of the alojamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlojamento(String value) {
        this.alojamento = value;
    }

    /**
     * Gets the value of the alojamentoReserva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlojamentoReserva() {
        return alojamentoReserva;
    }

    /**
     * Sets the value of the alojamentoReserva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlojamentoReserva(String value) {
        this.alojamentoReserva = value;
    }

    /**
     * Gets the value of the calcAdd1DataAssinaturaContrato property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCalcAdd1DataAssinaturaContrato() {
        return calcAdd1DataAssinaturaContrato;
    }

    /**
     * Sets the value of the calcAdd1DataAssinaturaContrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCalcAdd1DataAssinaturaContrato(XMLGregorianCalendar value) {
        this.calcAdd1DataAssinaturaContrato = value;
    }

    /**
     * Gets the value of the calcAdd1DataLiberacaoCracha property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCalcAdd1DataLiberacaoCracha() {
        return calcAdd1DataLiberacaoCracha;
    }

    /**
     * Sets the value of the calcAdd1DataLiberacaoCracha property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCalcAdd1DataLiberacaoCracha(XMLGregorianCalendar value) {
        this.calcAdd1DataLiberacaoCracha = value;
    }

    /**
     * Gets the value of the calcAdd2DiasLiberacaoCracha property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCalcAdd2DiasLiberacaoCracha() {
        return calcAdd2DiasLiberacaoCracha;
    }

    /**
     * Sets the value of the calcAdd2DiasLiberacaoCracha property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCalcAdd2DiasLiberacaoCracha(XMLGregorianCalendar value) {
        this.calcAdd2DiasLiberacaoCracha = value;
    }

    /**
     * Gets the value of the calcAdd4DiasDataAssinaturaContrato property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCalcAdd4DiasDataAssinaturaContrato() {
        return calcAdd4DiasDataAssinaturaContrato;
    }

    /**
     * Sets the value of the calcAdd4DiasDataAssinaturaContrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCalcAdd4DiasDataAssinaturaContrato(XMLGregorianCalendar value) {
        this.calcAdd4DiasDataAssinaturaContrato = value;
    }

    /**
     * Gets the value of the calcAdd4DiasDataAux3 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCalcAdd4DiasDataAux3() {
        return calcAdd4DiasDataAux3;
    }

    /**
     * Sets the value of the calcAdd4DiasDataAux3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCalcAdd4DiasDataAux3(XMLGregorianCalendar value) {
        this.calcAdd4DiasDataAux3 = value;
    }

    /**
     * Gets the value of the calcDifDataAdmissaoDataAux3 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifDataAdmissaoDataAux3() {
        return calcDifDataAdmissaoDataAux3;
    }

    /**
     * Sets the value of the calcDifDataAdmissaoDataAux3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifDataAdmissaoDataAux3(Integer value) {
        this.calcDifDataAdmissaoDataAux3 = value;
    }

    /**
     * Gets the value of the calcDifDataAdmissaoDataAux6 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifDataAdmissaoDataAux6() {
        return calcDifDataAdmissaoDataAux6;
    }

    /**
     * Sets the value of the calcDifDataAdmissaoDataAux6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifDataAdmissaoDataAux6(Integer value) {
        this.calcDifDataAdmissaoDataAux6 = value;
    }

    /**
     * Gets the value of the calcDifDataAssinaturaContratoDataEntradaArea property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifDataAssinaturaContratoDataEntradaArea() {
        return calcDifDataAssinaturaContratoDataEntradaArea;
    }

    /**
     * Sets the value of the calcDifDataAssinaturaContratoDataEntradaArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifDataAssinaturaContratoDataEntradaArea(Integer value) {
        this.calcDifDataAssinaturaContratoDataEntradaArea = value;
    }

    /**
     * Gets the value of the calcDifDataAux1DataAux3 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifDataAux1DataAux3() {
        return calcDifDataAux1DataAux3;
    }

    /**
     * Sets the value of the calcDifDataAux1DataAux3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifDataAux1DataAux3(Integer value) {
        this.calcDifDataAux1DataAux3 = value;
    }

    /**
     * Gets the value of the calcDifDataAux3DataLiberacaoCracha property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifDataAux3DataLiberacaoCracha() {
        return calcDifDataAux3DataLiberacaoCracha;
    }

    /**
     * Sets the value of the calcDifDataAux3DataLiberacaoCracha property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifDataAux3DataLiberacaoCracha(Integer value) {
        this.calcDifDataAux3DataLiberacaoCracha = value;
    }

    /**
     * Gets the value of the calcDifDataCadastroDataChegadaPrevista property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifDataCadastroDataChegadaPrevista() {
        return calcDifDataCadastroDataChegadaPrevista;
    }

    /**
     * Sets the value of the calcDifDataCadastroDataChegadaPrevista property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifDataCadastroDataChegadaPrevista(Integer value) {
        this.calcDifDataCadastroDataChegadaPrevista = value;
    }

    /**
     * Gets the value of the calcDifDataCadastroVagaDataCadastroPessoa property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifDataCadastroVagaDataCadastroPessoa() {
        return calcDifDataCadastroVagaDataCadastroPessoa;
    }

    /**
     * Sets the value of the calcDifDataCadastroVagaDataCadastroPessoa property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifDataCadastroVagaDataCadastroPessoa(Integer value) {
        this.calcDifDataCadastroVagaDataCadastroPessoa = value;
    }

    /**
     * Gets the value of the calcDifDataEncaminhadoExameDataEntregaDocs property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifDataEncaminhadoExameDataEntregaDocs() {
        return calcDifDataEncaminhadoExameDataEntregaDocs;
    }

    /**
     * Sets the value of the calcDifDataEncaminhadoExameDataEntregaDocs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifDataEncaminhadoExameDataEntregaDocs(Integer value) {
        this.calcDifDataEncaminhadoExameDataEntregaDocs = value;
    }

    /**
     * Gets the value of the calcDifDataEncaminhadoExameDataExameMedico property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifDataEncaminhadoExameDataExameMedico() {
        return calcDifDataEncaminhadoExameDataExameMedico;
    }

    /**
     * Sets the value of the calcDifDataEncaminhadoExameDataExameMedico property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifDataEncaminhadoExameDataExameMedico(Integer value) {
        this.calcDifDataEncaminhadoExameDataExameMedico = value;
    }

    /**
     * Gets the value of the calcDifDataEncaminhadoExameDataRecebimentoAso property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifDataEncaminhadoExameDataRecebimentoAso() {
        return calcDifDataEncaminhadoExameDataRecebimentoAso;
    }

    /**
     * Sets the value of the calcDifDataEncaminhadoExameDataRecebimentoAso property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifDataEncaminhadoExameDataRecebimentoAso(Integer value) {
        this.calcDifDataEncaminhadoExameDataRecebimentoAso = value;
    }

    /**
     * Gets the value of the calcDifDataEntregaDocsDpDataAdmissao property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifDataEntregaDocsDpDataAdmissao() {
        return calcDifDataEntregaDocsDpDataAdmissao;
    }

    /**
     * Sets the value of the calcDifDataEntregaDocsDpDataAdmissao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifDataEntregaDocsDpDataAdmissao(Integer value) {
        this.calcDifDataEntregaDocsDpDataAdmissao = value;
    }

    /**
     * Gets the value of the calcDifDataEntregaDocsDpDataAux1 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifDataEntregaDocsDpDataAux1() {
        return calcDifDataEntregaDocsDpDataAux1;
    }

    /**
     * Sets the value of the calcDifDataEntregaDocsDpDataAux1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifDataEntregaDocsDpDataAux1(Integer value) {
        this.calcDifDataEntregaDocsDpDataAux1 = value;
    }

    /**
     * Gets the value of the calcDifDataExameMedicoDataRecebimentoAso property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifDataExameMedicoDataRecebimentoAso() {
        return calcDifDataExameMedicoDataRecebimentoAso;
    }

    /**
     * Sets the value of the calcDifDataExameMedicoDataRecebimentoAso property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifDataExameMedicoDataRecebimentoAso(Integer value) {
        this.calcDifDataExameMedicoDataRecebimentoAso = value;
    }

    /**
     * Gets the value of the calcDifDataInicioTreinDataFimTrein property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifDataInicioTreinDataFimTrein() {
        return calcDifDataInicioTreinDataFimTrein;
    }

    /**
     * Sets the value of the calcDifDataInicioTreinDataFimTrein property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifDataInicioTreinDataFimTrein(Integer value) {
        this.calcDifDataInicioTreinDataFimTrein = value;
    }

    /**
     * Gets the value of the calcDifDataLiberacaoCrachaDataAssinaturaContrato property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifDataLiberacaoCrachaDataAssinaturaContrato() {
        return calcDifDataLiberacaoCrachaDataAssinaturaContrato;
    }

    /**
     * Sets the value of the calcDifDataLiberacaoCrachaDataAssinaturaContrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifDataLiberacaoCrachaDataAssinaturaContrato(Integer value) {
        this.calcDifDataLiberacaoCrachaDataAssinaturaContrato = value;
    }

    /**
     * Gets the value of the calcDifDataRecebimentoDataEntradaArea property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifDataRecebimentoDataEntradaArea() {
        return calcDifDataRecebimentoDataEntradaArea;
    }

    /**
     * Sets the value of the calcDifDataRecebimentoDataEntradaArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifDataRecebimentoDataEntradaArea(Integer value) {
        this.calcDifDataRecebimentoDataEntradaArea = value;
    }

    /**
     * Gets the value of the calcDifTbAlojamentoQuartoPessoaDataEntradaTbAlojamentoQuartoPessoaDataSaida property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifTbAlojamentoQuartoPessoaDataEntradaTbAlojamentoQuartoPessoaDataSaida() {
        return calcDifTbAlojamentoQuartoPessoaDataEntradaTbAlojamentoQuartoPessoaDataSaida;
    }

    /**
     * Sets the value of the calcDifTbAlojamentoQuartoPessoaDataEntradaTbAlojamentoQuartoPessoaDataSaida property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifTbAlojamentoQuartoPessoaDataEntradaTbAlojamentoQuartoPessoaDataSaida(Integer value) {
        this.calcDifTbAlojamentoQuartoPessoaDataEntradaTbAlojamentoQuartoPessoaDataSaida = value;
    }

    /**
     * Gets the value of the calcDifTbVagaDataAberturaTbVagaDataRecebimento property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifTbVagaDataAberturaTbVagaDataRecebimento() {
        return calcDifTbVagaDataAberturaTbVagaDataRecebimento;
    }

    /**
     * Sets the value of the calcDifTbVagaDataAberturaTbVagaDataRecebimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifTbVagaDataAberturaTbVagaDataRecebimento(Integer value) {
        this.calcDifTbVagaDataAberturaTbVagaDataRecebimento = value;
    }

    /**
     * Gets the value of the calcDifTbVagaDataCadastroTbVagaDataAbertura property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifTbVagaDataCadastroTbVagaDataAbertura() {
        return calcDifTbVagaDataCadastroTbVagaDataAbertura;
    }

    /**
     * Sets the value of the calcDifTbVagaDataCadastroTbVagaDataAbertura property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifTbVagaDataCadastroTbVagaDataAbertura(Integer value) {
        this.calcDifTbVagaDataCadastroTbVagaDataAbertura = value;
    }

    /**
     * Gets the value of the calcDifTbVagaDataRecebimentoDataAdmissao property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifTbVagaDataRecebimentoDataAdmissao() {
        return calcDifTbVagaDataRecebimentoDataAdmissao;
    }

    /**
     * Sets the value of the calcDifTbVagaDataRecebimentoDataAdmissao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifTbVagaDataRecebimentoDataAdmissao(Integer value) {
        this.calcDifTbVagaDataRecebimentoDataAdmissao = value;
    }

    /**
     * Gets the value of the calcDifTbVagaDataRecebimentoDataAux6 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifTbVagaDataRecebimentoDataAux6() {
        return calcDifTbVagaDataRecebimentoDataAux6;
    }

    /**
     * Sets the value of the calcDifTbVagaDataRecebimentoDataAux6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifTbVagaDataRecebimentoDataAux6(Integer value) {
        this.calcDifTbVagaDataRecebimentoDataAux6 = value;
    }

    /**
     * Gets the value of the calcDifTbVagaDataSolicitacaoTbVagaDataAbertura property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifTbVagaDataSolicitacaoTbVagaDataAbertura() {
        return calcDifTbVagaDataSolicitacaoTbVagaDataAbertura;
    }

    /**
     * Sets the value of the calcDifTbVagaDataSolicitacaoTbVagaDataAbertura property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifTbVagaDataSolicitacaoTbVagaDataAbertura(Integer value) {
        this.calcDifTbVagaDataSolicitacaoTbVagaDataAbertura = value;
    }

    /**
     * Gets the value of the calcDifTbVagasDataRecebimentoDataEntregaDocsDp property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDifTbVagasDataRecebimentoDataEntregaDocsDp() {
        return calcDifTbVagasDataRecebimentoDataEntregaDocsDp;
    }

    /**
     * Sets the value of the calcDifTbVagasDataRecebimentoDataEntregaDocsDp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDifTbVagasDataRecebimentoDataEntregaDocsDp(Integer value) {
        this.calcDifTbVagasDataRecebimentoDataEntregaDocsDp = value;
    }

    /**
     * Gets the value of the dataEntradaAlojamento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEntradaAlojamento() {
        return dataEntradaAlojamento;
    }

    /**
     * Sets the value of the dataEntradaAlojamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEntradaAlojamento(XMLGregorianCalendar value) {
        this.dataEntradaAlojamento = value;
    }

    /**
     * Gets the value of the dataSaidaAlojamento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataSaidaAlojamento() {
        return dataSaidaAlojamento;
    }

    /**
     * Sets the value of the dataSaidaAlojamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataSaidaAlojamento(XMLGregorianCalendar value) {
        this.dataSaidaAlojamento = value;
    }

    /**
     * Gets the value of the enderecoAlojamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnderecoAlojamento() {
        return enderecoAlojamento;
    }

    /**
     * Sets the value of the enderecoAlojamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnderecoAlojamento(String value) {
        this.enderecoAlojamento = value;
    }

    /**
     * Gets the value of the gerenteArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGerenteArea() {
        return gerenteArea;
    }

    /**
     * Sets the value of the gerenteArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGerenteArea(String value) {
        this.gerenteArea = value;
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
     * Gets the value of the statusPrazo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusPrazo() {
        return statusPrazo;
    }

    /**
     * Sets the value of the statusPrazo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusPrazo(String value) {
        this.statusPrazo = value;
    }

    /**
     * Gets the value of the tipoAlojamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoAlojamento() {
        return tipoAlojamento;
    }

    /**
     * Sets the value of the tipoAlojamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoAlojamento(String value) {
        this.tipoAlojamento = value;
    }

}
