
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
 * <p>Java class for configuracao complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="configuracao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configuracaoId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="cnpj" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="empresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomeCompleto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="logomarca" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="cep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="uf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endereco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telefone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="website" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailProjeto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contrato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="caixaPostal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="caminhoJasper" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="metaTreinamento" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="emailHost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailPorta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailOrigem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailPasswd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailChave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sincronizacaoEfetivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoFaltaForPonto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prevVisitFamiliaFormCalc" type="{http://ws.rhweb_ws.nextage.com.br/}prevVisitFamiliaFormCalc" minOccurs="0"/>
 *         &lt;element name="funcoesEquivalentes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vagaCodigoFormulaCalc" type="{http://ws.rhweb_ws.nextage.com.br/}vagaCodigoFormulaCalc" minOccurs="0"/>
 *         &lt;element name="caminhoRealInstalacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="auxMoradiaFormulaCalc" type="{http://ws.rhweb_ws.nextage.com.br/}auxMoradiaFormulaCalc" minOccurs="0"/>
 *         &lt;element name="validacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ultimoAcesso" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="caminhoNxLog" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="caminhoNxAuth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="treinamentoIntegracaoId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="funcionalidadesHabilitadas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importarFotos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="caminhoFotos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="caminhoImportacaoEfetivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="horaImportacaoEfetivo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="minutoImportacaoEfetivo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="viagemRqsCodigoFormCalc" type="{http://ws.rhweb_ws.nextage.com.br/}viagemRqsCodigoFormCalc" minOccurs="0"/>
 *         &lt;element name="viagemDiariaCodigoFormCalc" type="{http://ws.rhweb_ws.nextage.com.br/}viagemDiariaCodigoFormCalc" minOccurs="0"/>
 *         &lt;element name="configuracaoRefeitorio" type="{http://ws.rhweb_ws.nextage.com.br/}configuracaoRefeitorio" minOccurs="0"/>
 *         &lt;element name="configuracaoAlojamento" type="{http://ws.rhweb_ws.nextage.com.br/}configuracaoAlojamento" minOccurs="0"/>
 *         &lt;element name="emailRelatorio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="diaBaseRelatorio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="caminhoNxManager" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idConfigImportacaoPessoa" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="configRequisicaoVaga" type="{http://ws.rhweb_ws.nextage.com.br/}configRequisicaoVaga" minOccurs="0"/>
 *         &lt;element name="cidadePrincipal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cdProjeto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="datasInativaUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataUltAtuEfetivo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="enviaAdmSapUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="enviaAdmSapPasswd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="enviaAdmSapUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlAvaliacaoEficacia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="diasNotifAvaliacaoEfic" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="calcDtRetornoVisFamilia" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="configuracaoTreinamento" type="{http://ws.rhweb_ws.nextage.com.br/}configuracaoTreinamento" minOccurs="0"/>
 *         &lt;element name="configuracaoGestor" type="{http://ws.rhweb_ws.nextage.com.br/}configuracaoGestor" minOccurs="0"/>
 *         &lt;element name="configuracaoViagem" type="{http://ws.rhweb_ws.nextage.com.br/}configuracaoViagem" minOccurs="0"/>
 *         &lt;element name="configuracaoSubcontratados" type="{http://ws.rhweb_ws.nextage.com.br/}configuracaoSubcontratados" minOccurs="0"/>
 *         &lt;element name="urlConsultaCep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tiposIntegracao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modoAutenticacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="configuracaoPessoa" type="{http://ws.rhweb_ws.nextage.com.br/}configuracaoPessoa" minOccurs="0"/>
 *         &lt;element name="urlConexaoBancoRhweb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userConexaoBancoRhweb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="passConexaoBancoRhweb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="configuracaoHoraExtra" type="{http://ws.rhweb_ws.nextage.com.br/}configuracaoHoraExtra" minOccurs="0"/>
 *         &lt;element name="prefUrlExternaSist" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="habCancelReqDesligados" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="localeDefault" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlServidor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="listaFuncionalidadesHabilitadas" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="logoB64" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configuracao", propOrder = {
    "configuracaoId",
    "cnpj",
    "empresa",
    "nome",
    "nomeCompleto",
    "logomarca",
    "cep",
    "uf",
    "cidade",
    "endereco",
    "telefone",
    "website",
    "emailProjeto",
    "contrato",
    "caixaPostal",
    "caminhoJasper",
    "metaTreinamento",
    "emailHost",
    "emailPorta",
    "emailOrigem",
    "emailUser",
    "emailPasswd",
    "emailChave",
    "sincronizacaoEfetivo",
    "codigoFaltaForPonto",
    "prevVisitFamiliaFormCalc",
    "funcoesEquivalentes",
    "vagaCodigoFormulaCalc",
    "caminhoRealInstalacao",
    "auxMoradiaFormulaCalc",
    "validacao",
    "ultimoAcesso",
    "caminhoNxLog",
    "caminhoNxAuth",
    "treinamentoIntegracaoId",
    "funcionalidadesHabilitadas",
    "importarFotos",
    "caminhoFotos",
    "caminhoImportacaoEfetivo",
    "horaImportacaoEfetivo",
    "minutoImportacaoEfetivo",
    "viagemRqsCodigoFormCalc",
    "viagemDiariaCodigoFormCalc",
    "configuracaoRefeitorio",
    "configuracaoAlojamento",
    "emailRelatorio",
    "diaBaseRelatorio",
    "caminhoNxManager",
    "idConfigImportacaoPessoa",
    "configRequisicaoVaga",
    "cidadePrincipal",
    "cdProjeto",
    "datasInativaUsuario",
    "dataUltAtuEfetivo",
    "enviaAdmSapUrl",
    "enviaAdmSapPasswd",
    "enviaAdmSapUser",
    "urlAvaliacaoEficacia",
    "diasNotifAvaliacaoEfic",
    "calcDtRetornoVisFamilia",
    "configuracaoTreinamento",
    "configuracaoGestor",
    "configuracaoViagem",
    "configuracaoSubcontratados",
    "urlConsultaCep",
    "tiposIntegracao",
    "modoAutenticacao",
    "configuracaoPessoa",
    "urlConexaoBancoRhweb",
    "userConexaoBancoRhweb",
    "passConexaoBancoRhweb",
    "configuracaoHoraExtra",
    "prefUrlExternaSist",
    "habCancelReqDesligados",
    "localeDefault",
    "urlServidor",
    "listaFuncionalidadesHabilitadas",
    "logoB64"
})
public class Configuracao {

    protected Integer configuracaoId;
    protected String cnpj;
    protected String empresa;
    protected String nome;
    protected String nomeCompleto;
    protected byte[] logomarca;
    protected String cep;
    protected String uf;
    protected String cidade;
    protected String endereco;
    protected String telefone;
    protected String website;
    protected String emailProjeto;
    protected String contrato;
    protected String caixaPostal;
    protected String caminhoJasper;
    protected Double metaTreinamento;
    protected String emailHost;
    protected String emailPorta;
    protected String emailOrigem;
    protected String emailUser;
    protected String emailPasswd;
    protected String emailChave;
    protected String sincronizacaoEfetivo;
    protected String codigoFaltaForPonto;
    protected PrevVisitFamiliaFormCalc prevVisitFamiliaFormCalc;
    protected String funcoesEquivalentes;
    protected VagaCodigoFormulaCalc vagaCodigoFormulaCalc;
    protected String caminhoRealInstalacao;
    protected AuxMoradiaFormulaCalc auxMoradiaFormulaCalc;
    protected String validacao;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ultimoAcesso;
    protected String caminhoNxLog;
    protected String caminhoNxAuth;
    protected Integer treinamentoIntegracaoId;
    protected String funcionalidadesHabilitadas;
    protected String importarFotos;
    protected String caminhoFotos;
    protected String caminhoImportacaoEfetivo;
    protected Integer horaImportacaoEfetivo;
    protected Integer minutoImportacaoEfetivo;
    protected ViagemRqsCodigoFormCalc viagemRqsCodigoFormCalc;
    protected ViagemDiariaCodigoFormCalc viagemDiariaCodigoFormCalc;
    protected ConfiguracaoRefeitorio configuracaoRefeitorio;
    protected ConfiguracaoAlojamento configuracaoAlojamento;
    protected String emailRelatorio;
    protected Integer diaBaseRelatorio;
    protected String caminhoNxManager;
    protected Integer idConfigImportacaoPessoa;
    protected ConfigRequisicaoVaga configRequisicaoVaga;
    protected String cidadePrincipal;
    protected String cdProjeto;
    protected String datasInativaUsuario;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataUltAtuEfetivo;
    protected String enviaAdmSapUrl;
    protected String enviaAdmSapPasswd;
    protected String enviaAdmSapUser;
    protected String urlAvaliacaoEficacia;
    protected Integer diasNotifAvaliacaoEfic;
    protected Integer calcDtRetornoVisFamilia;
    protected ConfiguracaoTreinamento configuracaoTreinamento;
    protected ConfiguracaoGestor configuracaoGestor;
    protected ConfiguracaoViagem configuracaoViagem;
    protected ConfiguracaoSubcontratados configuracaoSubcontratados;
    protected String urlConsultaCep;
    protected String tiposIntegracao;
    protected String modoAutenticacao;
    protected ConfiguracaoPessoa configuracaoPessoa;
    protected String urlConexaoBancoRhweb;
    protected String userConexaoBancoRhweb;
    protected String passConexaoBancoRhweb;
    protected ConfiguracaoHoraExtra configuracaoHoraExtra;
    protected String prefUrlExternaSist;
    protected Boolean habCancelReqDesligados;
    protected String localeDefault;
    protected String urlServidor;
    @XmlElement(nillable = true)
    protected List<String> listaFuncionalidadesHabilitadas;
    protected String logoB64;

    /**
     * Gets the value of the configuracaoId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getConfiguracaoId() {
        return configuracaoId;
    }

    /**
     * Sets the value of the configuracaoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setConfiguracaoId(Integer value) {
        this.configuracaoId = value;
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
     * Gets the value of the empresa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * Sets the value of the empresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmpresa(String value) {
        this.empresa = value;
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
     * Gets the value of the nomeCompleto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    /**
     * Sets the value of the nomeCompleto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeCompleto(String value) {
        this.nomeCompleto = value;
    }

    /**
     * Gets the value of the logomarca property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getLogomarca() {
        return logomarca;
    }

    /**
     * Sets the value of the logomarca property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setLogomarca(byte[] value) {
        this.logomarca = value;
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
     * Gets the value of the uf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUf() {
        return uf;
    }

    /**
     * Sets the value of the uf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUf(String value) {
        this.uf = value;
    }

    /**
     * Gets the value of the cidade property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Sets the value of the cidade property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCidade(String value) {
        this.cidade = value;
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
     * Gets the value of the website property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Sets the value of the website property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebsite(String value) {
        this.website = value;
    }

    /**
     * Gets the value of the emailProjeto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailProjeto() {
        return emailProjeto;
    }

    /**
     * Sets the value of the emailProjeto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailProjeto(String value) {
        this.emailProjeto = value;
    }

    /**
     * Gets the value of the contrato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrato() {
        return contrato;
    }

    /**
     * Sets the value of the contrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrato(String value) {
        this.contrato = value;
    }

    /**
     * Gets the value of the caixaPostal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaixaPostal() {
        return caixaPostal;
    }

    /**
     * Sets the value of the caixaPostal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaixaPostal(String value) {
        this.caixaPostal = value;
    }

    /**
     * Gets the value of the caminhoJasper property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaminhoJasper() {
        return caminhoJasper;
    }

    /**
     * Sets the value of the caminhoJasper property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaminhoJasper(String value) {
        this.caminhoJasper = value;
    }

    /**
     * Gets the value of the metaTreinamento property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMetaTreinamento() {
        return metaTreinamento;
    }

    /**
     * Sets the value of the metaTreinamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMetaTreinamento(Double value) {
        this.metaTreinamento = value;
    }

    /**
     * Gets the value of the emailHost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailHost() {
        return emailHost;
    }

    /**
     * Sets the value of the emailHost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailHost(String value) {
        this.emailHost = value;
    }

    /**
     * Gets the value of the emailPorta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailPorta() {
        return emailPorta;
    }

    /**
     * Sets the value of the emailPorta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailPorta(String value) {
        this.emailPorta = value;
    }

    /**
     * Gets the value of the emailOrigem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailOrigem() {
        return emailOrigem;
    }

    /**
     * Sets the value of the emailOrigem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailOrigem(String value) {
        this.emailOrigem = value;
    }

    /**
     * Gets the value of the emailUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailUser() {
        return emailUser;
    }

    /**
     * Sets the value of the emailUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailUser(String value) {
        this.emailUser = value;
    }

    /**
     * Gets the value of the emailPasswd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailPasswd() {
        return emailPasswd;
    }

    /**
     * Sets the value of the emailPasswd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailPasswd(String value) {
        this.emailPasswd = value;
    }

    /**
     * Gets the value of the emailChave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailChave() {
        return emailChave;
    }

    /**
     * Sets the value of the emailChave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailChave(String value) {
        this.emailChave = value;
    }

    /**
     * Gets the value of the sincronizacaoEfetivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSincronizacaoEfetivo() {
        return sincronizacaoEfetivo;
    }

    /**
     * Sets the value of the sincronizacaoEfetivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSincronizacaoEfetivo(String value) {
        this.sincronizacaoEfetivo = value;
    }

    /**
     * Gets the value of the codigoFaltaForPonto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoFaltaForPonto() {
        return codigoFaltaForPonto;
    }

    /**
     * Sets the value of the codigoFaltaForPonto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoFaltaForPonto(String value) {
        this.codigoFaltaForPonto = value;
    }

    /**
     * Gets the value of the prevVisitFamiliaFormCalc property.
     * 
     * @return
     *     possible object is
     *     {@link PrevVisitFamiliaFormCalc }
     *     
     */
    public PrevVisitFamiliaFormCalc getPrevVisitFamiliaFormCalc() {
        return prevVisitFamiliaFormCalc;
    }

    /**
     * Sets the value of the prevVisitFamiliaFormCalc property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrevVisitFamiliaFormCalc }
     *     
     */
    public void setPrevVisitFamiliaFormCalc(PrevVisitFamiliaFormCalc value) {
        this.prevVisitFamiliaFormCalc = value;
    }

    /**
     * Gets the value of the funcoesEquivalentes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFuncoesEquivalentes() {
        return funcoesEquivalentes;
    }

    /**
     * Sets the value of the funcoesEquivalentes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFuncoesEquivalentes(String value) {
        this.funcoesEquivalentes = value;
    }

    /**
     * Gets the value of the vagaCodigoFormulaCalc property.
     * 
     * @return
     *     possible object is
     *     {@link VagaCodigoFormulaCalc }
     *     
     */
    public VagaCodigoFormulaCalc getVagaCodigoFormulaCalc() {
        return vagaCodigoFormulaCalc;
    }

    /**
     * Sets the value of the vagaCodigoFormulaCalc property.
     * 
     * @param value
     *     allowed object is
     *     {@link VagaCodigoFormulaCalc }
     *     
     */
    public void setVagaCodigoFormulaCalc(VagaCodigoFormulaCalc value) {
        this.vagaCodigoFormulaCalc = value;
    }

    /**
     * Gets the value of the caminhoRealInstalacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaminhoRealInstalacao() {
        return caminhoRealInstalacao;
    }

    /**
     * Sets the value of the caminhoRealInstalacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaminhoRealInstalacao(String value) {
        this.caminhoRealInstalacao = value;
    }

    /**
     * Gets the value of the auxMoradiaFormulaCalc property.
     * 
     * @return
     *     possible object is
     *     {@link AuxMoradiaFormulaCalc }
     *     
     */
    public AuxMoradiaFormulaCalc getAuxMoradiaFormulaCalc() {
        return auxMoradiaFormulaCalc;
    }

    /**
     * Sets the value of the auxMoradiaFormulaCalc property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuxMoradiaFormulaCalc }
     *     
     */
    public void setAuxMoradiaFormulaCalc(AuxMoradiaFormulaCalc value) {
        this.auxMoradiaFormulaCalc = value;
    }

    /**
     * Gets the value of the validacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidacao() {
        return validacao;
    }

    /**
     * Sets the value of the validacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidacao(String value) {
        this.validacao = value;
    }

    /**
     * Gets the value of the ultimoAcesso property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUltimoAcesso() {
        return ultimoAcesso;
    }

    /**
     * Sets the value of the ultimoAcesso property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUltimoAcesso(XMLGregorianCalendar value) {
        this.ultimoAcesso = value;
    }

    /**
     * Gets the value of the caminhoNxLog property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaminhoNxLog() {
        return caminhoNxLog;
    }

    /**
     * Sets the value of the caminhoNxLog property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaminhoNxLog(String value) {
        this.caminhoNxLog = value;
    }

    /**
     * Gets the value of the caminhoNxAuth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaminhoNxAuth() {
        return caminhoNxAuth;
    }

    /**
     * Sets the value of the caminhoNxAuth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaminhoNxAuth(String value) {
        this.caminhoNxAuth = value;
    }

    /**
     * Gets the value of the treinamentoIntegracaoId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTreinamentoIntegracaoId() {
        return treinamentoIntegracaoId;
    }

    /**
     * Sets the value of the treinamentoIntegracaoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTreinamentoIntegracaoId(Integer value) {
        this.treinamentoIntegracaoId = value;
    }

    /**
     * Gets the value of the funcionalidadesHabilitadas property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFuncionalidadesHabilitadas() {
        return funcionalidadesHabilitadas;
    }

    /**
     * Sets the value of the funcionalidadesHabilitadas property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFuncionalidadesHabilitadas(String value) {
        this.funcionalidadesHabilitadas = value;
    }

    /**
     * Gets the value of the importarFotos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImportarFotos() {
        return importarFotos;
    }

    /**
     * Sets the value of the importarFotos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImportarFotos(String value) {
        this.importarFotos = value;
    }

    /**
     * Gets the value of the caminhoFotos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaminhoFotos() {
        return caminhoFotos;
    }

    /**
     * Sets the value of the caminhoFotos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaminhoFotos(String value) {
        this.caminhoFotos = value;
    }

    /**
     * Gets the value of the caminhoImportacaoEfetivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaminhoImportacaoEfetivo() {
        return caminhoImportacaoEfetivo;
    }

    /**
     * Sets the value of the caminhoImportacaoEfetivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaminhoImportacaoEfetivo(String value) {
        this.caminhoImportacaoEfetivo = value;
    }

    /**
     * Gets the value of the horaImportacaoEfetivo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHoraImportacaoEfetivo() {
        return horaImportacaoEfetivo;
    }

    /**
     * Sets the value of the horaImportacaoEfetivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHoraImportacaoEfetivo(Integer value) {
        this.horaImportacaoEfetivo = value;
    }

    /**
     * Gets the value of the minutoImportacaoEfetivo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinutoImportacaoEfetivo() {
        return minutoImportacaoEfetivo;
    }

    /**
     * Sets the value of the minutoImportacaoEfetivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinutoImportacaoEfetivo(Integer value) {
        this.minutoImportacaoEfetivo = value;
    }

    /**
     * Gets the value of the viagemRqsCodigoFormCalc property.
     * 
     * @return
     *     possible object is
     *     {@link ViagemRqsCodigoFormCalc }
     *     
     */
    public ViagemRqsCodigoFormCalc getViagemRqsCodigoFormCalc() {
        return viagemRqsCodigoFormCalc;
    }

    /**
     * Sets the value of the viagemRqsCodigoFormCalc property.
     * 
     * @param value
     *     allowed object is
     *     {@link ViagemRqsCodigoFormCalc }
     *     
     */
    public void setViagemRqsCodigoFormCalc(ViagemRqsCodigoFormCalc value) {
        this.viagemRqsCodigoFormCalc = value;
    }

    /**
     * Gets the value of the viagemDiariaCodigoFormCalc property.
     * 
     * @return
     *     possible object is
     *     {@link ViagemDiariaCodigoFormCalc }
     *     
     */
    public ViagemDiariaCodigoFormCalc getViagemDiariaCodigoFormCalc() {
        return viagemDiariaCodigoFormCalc;
    }

    /**
     * Sets the value of the viagemDiariaCodigoFormCalc property.
     * 
     * @param value
     *     allowed object is
     *     {@link ViagemDiariaCodigoFormCalc }
     *     
     */
    public void setViagemDiariaCodigoFormCalc(ViagemDiariaCodigoFormCalc value) {
        this.viagemDiariaCodigoFormCalc = value;
    }

    /**
     * Gets the value of the configuracaoRefeitorio property.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracaoRefeitorio }
     *     
     */
    public ConfiguracaoRefeitorio getConfiguracaoRefeitorio() {
        return configuracaoRefeitorio;
    }

    /**
     * Sets the value of the configuracaoRefeitorio property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracaoRefeitorio }
     *     
     */
    public void setConfiguracaoRefeitorio(ConfiguracaoRefeitorio value) {
        this.configuracaoRefeitorio = value;
    }

    /**
     * Gets the value of the configuracaoAlojamento property.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracaoAlojamento }
     *     
     */
    public ConfiguracaoAlojamento getConfiguracaoAlojamento() {
        return configuracaoAlojamento;
    }

    /**
     * Sets the value of the configuracaoAlojamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracaoAlojamento }
     *     
     */
    public void setConfiguracaoAlojamento(ConfiguracaoAlojamento value) {
        this.configuracaoAlojamento = value;
    }

    /**
     * Gets the value of the emailRelatorio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailRelatorio() {
        return emailRelatorio;
    }

    /**
     * Sets the value of the emailRelatorio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailRelatorio(String value) {
        this.emailRelatorio = value;
    }

    /**
     * Gets the value of the diaBaseRelatorio property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDiaBaseRelatorio() {
        return diaBaseRelatorio;
    }

    /**
     * Sets the value of the diaBaseRelatorio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDiaBaseRelatorio(Integer value) {
        this.diaBaseRelatorio = value;
    }

    /**
     * Gets the value of the caminhoNxManager property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaminhoNxManager() {
        return caminhoNxManager;
    }

    /**
     * Sets the value of the caminhoNxManager property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaminhoNxManager(String value) {
        this.caminhoNxManager = value;
    }

    /**
     * Gets the value of the idConfigImportacaoPessoa property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdConfigImportacaoPessoa() {
        return idConfigImportacaoPessoa;
    }

    /**
     * Sets the value of the idConfigImportacaoPessoa property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdConfigImportacaoPessoa(Integer value) {
        this.idConfigImportacaoPessoa = value;
    }

    /**
     * Gets the value of the configRequisicaoVaga property.
     * 
     * @return
     *     possible object is
     *     {@link ConfigRequisicaoVaga }
     *     
     */
    public ConfigRequisicaoVaga getConfigRequisicaoVaga() {
        return configRequisicaoVaga;
    }

    /**
     * Sets the value of the configRequisicaoVaga property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfigRequisicaoVaga }
     *     
     */
    public void setConfigRequisicaoVaga(ConfigRequisicaoVaga value) {
        this.configRequisicaoVaga = value;
    }

    /**
     * Gets the value of the cidadePrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCidadePrincipal() {
        return cidadePrincipal;
    }

    /**
     * Sets the value of the cidadePrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCidadePrincipal(String value) {
        this.cidadePrincipal = value;
    }

    /**
     * Gets the value of the cdProjeto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdProjeto() {
        return cdProjeto;
    }

    /**
     * Sets the value of the cdProjeto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdProjeto(String value) {
        this.cdProjeto = value;
    }

    /**
     * Gets the value of the datasInativaUsuario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatasInativaUsuario() {
        return datasInativaUsuario;
    }

    /**
     * Sets the value of the datasInativaUsuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatasInativaUsuario(String value) {
        this.datasInativaUsuario = value;
    }

    /**
     * Gets the value of the dataUltAtuEfetivo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataUltAtuEfetivo() {
        return dataUltAtuEfetivo;
    }

    /**
     * Sets the value of the dataUltAtuEfetivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataUltAtuEfetivo(XMLGregorianCalendar value) {
        this.dataUltAtuEfetivo = value;
    }

    /**
     * Gets the value of the enviaAdmSapUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnviaAdmSapUrl() {
        return enviaAdmSapUrl;
    }

    /**
     * Sets the value of the enviaAdmSapUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnviaAdmSapUrl(String value) {
        this.enviaAdmSapUrl = value;
    }

    /**
     * Gets the value of the enviaAdmSapPasswd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnviaAdmSapPasswd() {
        return enviaAdmSapPasswd;
    }

    /**
     * Sets the value of the enviaAdmSapPasswd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnviaAdmSapPasswd(String value) {
        this.enviaAdmSapPasswd = value;
    }

    /**
     * Gets the value of the enviaAdmSapUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnviaAdmSapUser() {
        return enviaAdmSapUser;
    }

    /**
     * Sets the value of the enviaAdmSapUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnviaAdmSapUser(String value) {
        this.enviaAdmSapUser = value;
    }

    /**
     * Gets the value of the urlAvaliacaoEficacia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlAvaliacaoEficacia() {
        return urlAvaliacaoEficacia;
    }

    /**
     * Sets the value of the urlAvaliacaoEficacia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlAvaliacaoEficacia(String value) {
        this.urlAvaliacaoEficacia = value;
    }

    /**
     * Gets the value of the diasNotifAvaliacaoEfic property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDiasNotifAvaliacaoEfic() {
        return diasNotifAvaliacaoEfic;
    }

    /**
     * Sets the value of the diasNotifAvaliacaoEfic property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDiasNotifAvaliacaoEfic(Integer value) {
        this.diasNotifAvaliacaoEfic = value;
    }

    /**
     * Gets the value of the calcDtRetornoVisFamilia property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcDtRetornoVisFamilia() {
        return calcDtRetornoVisFamilia;
    }

    /**
     * Sets the value of the calcDtRetornoVisFamilia property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcDtRetornoVisFamilia(Integer value) {
        this.calcDtRetornoVisFamilia = value;
    }

    /**
     * Gets the value of the configuracaoTreinamento property.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracaoTreinamento }
     *     
     */
    public ConfiguracaoTreinamento getConfiguracaoTreinamento() {
        return configuracaoTreinamento;
    }

    /**
     * Sets the value of the configuracaoTreinamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracaoTreinamento }
     *     
     */
    public void setConfiguracaoTreinamento(ConfiguracaoTreinamento value) {
        this.configuracaoTreinamento = value;
    }

    /**
     * Gets the value of the configuracaoGestor property.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracaoGestor }
     *     
     */
    public ConfiguracaoGestor getConfiguracaoGestor() {
        return configuracaoGestor;
    }

    /**
     * Sets the value of the configuracaoGestor property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracaoGestor }
     *     
     */
    public void setConfiguracaoGestor(ConfiguracaoGestor value) {
        this.configuracaoGestor = value;
    }

    /**
     * Gets the value of the configuracaoViagem property.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracaoViagem }
     *     
     */
    public ConfiguracaoViagem getConfiguracaoViagem() {
        return configuracaoViagem;
    }

    /**
     * Sets the value of the configuracaoViagem property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracaoViagem }
     *     
     */
    public void setConfiguracaoViagem(ConfiguracaoViagem value) {
        this.configuracaoViagem = value;
    }

    /**
     * Gets the value of the configuracaoSubcontratados property.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracaoSubcontratados }
     *     
     */
    public ConfiguracaoSubcontratados getConfiguracaoSubcontratados() {
        return configuracaoSubcontratados;
    }

    /**
     * Sets the value of the configuracaoSubcontratados property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracaoSubcontratados }
     *     
     */
    public void setConfiguracaoSubcontratados(ConfiguracaoSubcontratados value) {
        this.configuracaoSubcontratados = value;
    }

    /**
     * Gets the value of the urlConsultaCep property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlConsultaCep() {
        return urlConsultaCep;
    }

    /**
     * Sets the value of the urlConsultaCep property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlConsultaCep(String value) {
        this.urlConsultaCep = value;
    }

    /**
     * Gets the value of the tiposIntegracao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTiposIntegracao() {
        return tiposIntegracao;
    }

    /**
     * Sets the value of the tiposIntegracao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTiposIntegracao(String value) {
        this.tiposIntegracao = value;
    }

    /**
     * Gets the value of the modoAutenticacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModoAutenticacao() {
        return modoAutenticacao;
    }

    /**
     * Sets the value of the modoAutenticacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModoAutenticacao(String value) {
        this.modoAutenticacao = value;
    }

    /**
     * Gets the value of the configuracaoPessoa property.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracaoPessoa }
     *     
     */
    public ConfiguracaoPessoa getConfiguracaoPessoa() {
        return configuracaoPessoa;
    }

    /**
     * Sets the value of the configuracaoPessoa property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracaoPessoa }
     *     
     */
    public void setConfiguracaoPessoa(ConfiguracaoPessoa value) {
        this.configuracaoPessoa = value;
    }

    /**
     * Gets the value of the urlConexaoBancoRhweb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlConexaoBancoRhweb() {
        return urlConexaoBancoRhweb;
    }

    /**
     * Sets the value of the urlConexaoBancoRhweb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlConexaoBancoRhweb(String value) {
        this.urlConexaoBancoRhweb = value;
    }

    /**
     * Gets the value of the userConexaoBancoRhweb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserConexaoBancoRhweb() {
        return userConexaoBancoRhweb;
    }

    /**
     * Sets the value of the userConexaoBancoRhweb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserConexaoBancoRhweb(String value) {
        this.userConexaoBancoRhweb = value;
    }

    /**
     * Gets the value of the passConexaoBancoRhweb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassConexaoBancoRhweb() {
        return passConexaoBancoRhweb;
    }

    /**
     * Sets the value of the passConexaoBancoRhweb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassConexaoBancoRhweb(String value) {
        this.passConexaoBancoRhweb = value;
    }

    /**
     * Gets the value of the configuracaoHoraExtra property.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracaoHoraExtra }
     *     
     */
    public ConfiguracaoHoraExtra getConfiguracaoHoraExtra() {
        return configuracaoHoraExtra;
    }

    /**
     * Sets the value of the configuracaoHoraExtra property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracaoHoraExtra }
     *     
     */
    public void setConfiguracaoHoraExtra(ConfiguracaoHoraExtra value) {
        this.configuracaoHoraExtra = value;
    }

    /**
     * Gets the value of the prefUrlExternaSist property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrefUrlExternaSist() {
        return prefUrlExternaSist;
    }

    /**
     * Sets the value of the prefUrlExternaSist property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrefUrlExternaSist(String value) {
        this.prefUrlExternaSist = value;
    }

    /**
     * Gets the value of the habCancelReqDesligados property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHabCancelReqDesligados() {
        return habCancelReqDesligados;
    }

    /**
     * Sets the value of the habCancelReqDesligados property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHabCancelReqDesligados(Boolean value) {
        this.habCancelReqDesligados = value;
    }

    /**
     * Gets the value of the localeDefault property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocaleDefault() {
        return localeDefault;
    }

    /**
     * Sets the value of the localeDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocaleDefault(String value) {
        this.localeDefault = value;
    }

    /**
     * Gets the value of the urlServidor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlServidor() {
        return urlServidor;
    }

    /**
     * Sets the value of the urlServidor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlServidor(String value) {
        this.urlServidor = value;
    }

    /**
     * Gets the value of the listaFuncionalidadesHabilitadas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaFuncionalidadesHabilitadas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaFuncionalidadesHabilitadas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getListaFuncionalidadesHabilitadas() {
        if (listaFuncionalidadesHabilitadas == null) {
            listaFuncionalidadesHabilitadas = new ArrayList<String>();
        }
        return this.listaFuncionalidadesHabilitadas;
    }

    /**
     * Gets the value of the logoB64 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogoB64() {
        return logoB64;
    }

    /**
     * Sets the value of the logoB64 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogoB64(String value) {
        this.logoB64 = value;
    }

}
