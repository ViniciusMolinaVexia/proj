/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.entitybean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Jerry Adriano
 * @Date 13/06/2012
 */
@Entity
@Table(name = "TB_CONFIGURACAO", catalog = "", schema = "")
public class Configuracao implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "configuracao";
    public static final String ALIAS_CLASSE_UNDERLINE = "configuracao_";

    public static final String CONFIGURACAO_ID = "configuracaoId";
    public static final String NOME = "nome";
    public static final String EMPRESA = "empresa";
    public static final String EMAIL_HOST = "emailHost";
    public static final String EMAIL_PORTA = "emailPorta";
    public static final String EMAIL_ORIGEM = "emailOrigem";
    public static final String EMAIL_USER = "emailUser";
    public static final String EMAIL_PASSWD = "emailPasswd";
    public static final String EMAIL_CHAVE = "emailChave";
    public static final String MODO_AUTENTICACAO = "modoAutenticacao";
    public static final String CAMINHO_URL_RHWEB = "caminhoUrlRhweb";
    public static final String PREF_URL_EXTERNA_SIST = "prefUrlExternaSist";
    public static final String FILTRO_PAINEL_SUPRI_APROVADA = "filtroPainelSupriAprovada";
    public static final String CAMINHO_URL_CPWEB = "caminhoUrlCpweb";
    public static final String DESABILITA_CAD_MATERIAL = "desabilitaCadFornecedor";
    public static final String DESABILITA_CAD_FORNECEDOR = "desabilitaCadMaterial";

    public static final String IDS_FUNCAO_GERENTE_AREA = "idFuncaoGerenteArea";
    public static final String IDS_FUNCAO_ENCARREGADO = "idFuncaoEncarregado";
    public static final String IDS_FUNCAO_GERENTE_OBRA = "idFuncaoGerenteObra";
    public static final String IDS_FUNCAO_APROVADOR_CUSTOS = "idFuncaoAprovadorCustos";
    public static final String CENTRO_COD = "centroCod";
    public static final String DIAS_ALERTA_PREVISAO = "diasAlertaPrevisao";
    public static final String QUANT_CASAS_COD_SAP = "quantCasasCodSap";

    public static final String CAMINHO_REAL_INSTALACAO = "caminhoRealInstalacao";
    public static final String CAMINHO_PATH_IMAGEM_REL = "caminhoPathImagemRel";
    public static final String CODIGO_EPI = "codigoEpi";
    public static final String COLETOR_CUSTO_EPI = "coletorCustoEpi";
    public static final String DEPOSITO_PADRAO = "depositoPadrao";
    public static final String ENCAR_SOLICITA_RMA_MOBILE = "encarSolicitaRmaMobile";
    public static final String HABILITA_EAP_MULTI_EMPRESA = "habilitaEapMultiEmpresa";

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_CONFIGURACAO_ID", allocationSize = 1)
    @Column(name = "CONFIGURACAO_ID")
    private Integer configuracaoId;

    @Basic(optional = false)
    @Column(name = "EMPRESA")
    private String empresa;

    @Column(name = "EMAIL_HOST")
    private String emailHost;

    @Column(name = "EMAIL_PORTA")
    private String emailPorta;

    @Column(name = "EMAIL_ORIGEM")
    private String emailOrigem;

    @Column(name = "EMAIL_USER")
    private String emailUser;

    @Column(name = "EMAIL_PASSWD")
    private String emailPasswd;

    @Column(name = "EMAIL_CHAVE")
    private String emailChave;

    @Column(name = "CAMINHO_URL_RHWEB")
    private String caminhoUrlRhweb;

    @Column(name = "MODO_AUTENTICACAO", length = 200)
    private String modoAutenticacao;

    @Column(name = "PREF_URL_EXTERNA_SIST", length = 200)
    private String prefUrlExternaSist;

    @Column(name = "FILTRO_PAINEL_SUPRI_APROVADA ")
    private Boolean filtroPainelSupriAprovada;

    @Column(name = "CAMINHO_URL_CPWEB")
    private String caminhoUrlCpweb;

    @Column(name = "IDS_FUNCAO_GERENTE_AREA")
    private String idFuncaoGerenteArea;

    @Column(name = "IDS_FUNCAO_ENCARREGADO")
    private String idFuncaoEncarregado;

    @Column(name = "IDS_FUNCAO_GERENTE_OBRA")
    private String idFuncaoGerenteObra;

    @Column(name = "IDS_FUNCAO_APROVADOR_CUSTOS")
    private String idFuncaoAprovadorCustos;

    @Column(name = "CENTRO_COD", length = 50)
    private String centroCod;

    @Column(name = "DESABILITA_CAD_FORNECEDOR")
    private Boolean desabilitaCadFornecedor;

    @Column(name = "DESABILITA_CAD_MATERIAL")
    private Boolean desabilitaCadMaterial;

    @Column(name = "DIAS_ALERTA_PREVISAO")
    private Integer diasAlertaPrevisao;

    @Column(name = "QUANT_CASAS_COD_SAP")
    private Integer quantCasasCodSap;

    @Basic(optional = true)
    @Column(name = "CAMINHO_REAL_INSTALACAO", length = 200)
    private String caminhoRealInstalacao;

    @Basic(optional = true)
    @Column(name = "CAMINHO_PATH_IMAGEM_REL", length = 200)
    private String caminhoPathImagemRel;

    @Basic(optional = true)
    @Column(name = "CODIGO_EPI")
    private String codigoEpi;

    @Basic(optional = true)
    @Column(name = "NOME", length = 100)
    private String nome;

    @Basic(optional = true)
    @Column(name = "COLETOR_CUSTO_EPI")
    private String coletorCustoEpi;

    @Basic(optional = true)
    @Column(name = "DEPOSITO_PADRAO")
    private String depositoPadrao;

    @Column(name = "ENCAR_SOLICITA_RMA_MOBILE")
    private Boolean encarSolicitaRmaMobile;

    @Column(name = "HABILITA_EAP_MULTI_EMPRESA")
    private Boolean habilitaEapMultiEmpresa;

    public Configuracao() {

    }

    public Configuracao(Integer configuracaoId) {
        this.configuracaoId = configuracaoId;
    }

    public Configuracao(Integer configuracaoId, String empresa) {
        this.configuracaoId = configuracaoId;
        this.empresa = empresa;
    }

    public Integer getConfiguracaoId() {
        return configuracaoId;
    }

    public void setConfiguracaoId(Integer configuracaoId) {
        this.configuracaoId = configuracaoId;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getEmailHost() {
        return emailHost;
    }

    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    public String getEmailPorta() {
        return emailPorta;
    }

    public void setEmailPorta(String emailPorta) {
        this.emailPorta = emailPorta;
    }

    public String getEmailOrigem() {
        return emailOrigem;
    }

    public void setEmailOrigem(String emailOrigem) {
        this.emailOrigem = emailOrigem;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getEmailPasswd() {
        return emailPasswd;
    }

    public void setEmailPasswd(String emailPasswd) {
        this.emailPasswd = emailPasswd;
    }

    public String getEmailChave() {
        return emailChave;
    }

    public void setEmailChave(String emailChave) {
        this.emailChave = emailChave;
    }

    public String getModoAutenticacao() {
        return modoAutenticacao;
    }

    public void setModoAutenticacao(String modoAutenticacao) {
        this.modoAutenticacao = modoAutenticacao;
    }

    public String getCaminhoUrlRhweb() {
        return caminhoUrlRhweb;
    }

    public void setCaminhoUrlRhweb(String caminhoUrlRhweb) {
        this.caminhoUrlRhweb = caminhoUrlRhweb;
    }

    public String getPrefUrlExternaSist() {
        return prefUrlExternaSist;
    }

    public void setPrefUrlExternaSist(String prefUrlExternaSist) {
        this.prefUrlExternaSist = prefUrlExternaSist;
    }

    public Boolean getFiltroPainelSupriAprovada() {
        return filtroPainelSupriAprovada;
    }

    public void setFiltroPainelSupriAprovada(Boolean filtroPainelSupriAprovada) {
        this.filtroPainelSupriAprovada = filtroPainelSupriAprovada;
    }

    public String getCaminhoUrlCpweb() {
        return caminhoUrlCpweb;
    }

    public void setCaminhoUrlCpweb(String caminhoUrlCpweb) {
        this.caminhoUrlCpweb = caminhoUrlCpweb;
    }

    public String getIdFuncaoGerenteArea() {
        return idFuncaoGerenteArea;
    }

    public void setIdFuncaoGerenteArea(String idFuncaoGerenteArea) {
        this.idFuncaoGerenteArea = idFuncaoGerenteArea;
    }

    public String getIdFuncaoEncarregado() {
        return idFuncaoEncarregado;
    }

    public void setIdFuncaoEncarregado(String idFuncaoEncarregado) {
        this.idFuncaoEncarregado = idFuncaoEncarregado;
    }

    public String getIdFuncaoGerenteObra() {
        return idFuncaoGerenteObra;
    }

    public void setIdFuncaoGerenteObra(String idFuncaoGerenteObra) {
        this.idFuncaoGerenteObra = idFuncaoGerenteObra;
    }

    public String getIdFuncaoAprovadorCustos() {
        return idFuncaoAprovadorCustos;
    }

    public void setIdFuncaoAprovadorCustos(String idFuncaoAprovadorCustos) {
        this.idFuncaoAprovadorCustos = idFuncaoAprovadorCustos;
    }

    public String getCentroCod() {
        return centroCod;
    }

    public void setCentroCod(String centroCod) {
        this.centroCod = centroCod;
    }

    public Boolean getDesabilitaCadFornecedor() {
        return desabilitaCadFornecedor;
    }

    public void setDesabilitaCadFornecedor(Boolean desabilitaCadFornecedor) {
        this.desabilitaCadFornecedor = desabilitaCadFornecedor;
    }

    public Boolean getDesabilitaCadMaterial() {
        return desabilitaCadMaterial;
    }

    public void setDesabilitaCadMaterial(Boolean desabilitaCadMaterial) {
        this.desabilitaCadMaterial = desabilitaCadMaterial;
    }

    public Integer getDiasAlertaPrevisao() {
        return diasAlertaPrevisao;
    }

    public void setDiasAlertaPrevisao(Integer diasAlertaPrevisao) {
        this.diasAlertaPrevisao = diasAlertaPrevisao;
    }

    public Integer getQuantCasasCodSap() {
        return quantCasasCodSap;
    }

    public void setQuantCasasCodSap(Integer quantCasasCodSap) {
        this.quantCasasCodSap = quantCasasCodSap;
    }

    public String getCaminhoRealInstalacao() {
        return caminhoRealInstalacao;
    }

    public void setCaminhoRealInstalacao(String caminhoRealInstalacao) {
        this.caminhoRealInstalacao = caminhoRealInstalacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoEpi() {
        return codigoEpi;
    }

    public void setCodigoEpi(String codigoEpi) {
        this.codigoEpi = codigoEpi;
    }

    public String getColetorCustoEpi() {
        return coletorCustoEpi;
    }

    public void setColetorCustoEpi(String coletorCustoEpi) {
        this.coletorCustoEpi = coletorCustoEpi;
    }

    public String getDepositoPadrao() {
        return depositoPadrao;
    }

    public void setDepositoPadrao(String depositoPadrao) {
        this.depositoPadrao = depositoPadrao;
    }

    public Boolean getEncarSolicitaRmaMobile() {
        return encarSolicitaRmaMobile;
    }

    public void setEncarSolicitaRmaMobile(Boolean encarSolicitaRmaMobile) {
        this.encarSolicitaRmaMobile = encarSolicitaRmaMobile;
    }

    public Boolean getHabilitaEapMultiEmpresa() {
        return habilitaEapMultiEmpresa;
    }

    public void setHabilitaEapMultiEmpresa(Boolean habilitaEapMultiEmpresa) {
        this.habilitaEapMultiEmpresa = habilitaEapMultiEmpresa;
    }

    public String getCaminhoPathImagemRel() {
        return caminhoPathImagemRel;
    }

    public void setCaminhoPathImagemRel(String caminhoPathImagemRel) {
        this.caminhoPathImagemRel = caminhoPathImagemRel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (configuracaoId != null ? configuracaoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Configuracao)) {
            return false;
        }
        Configuracao other = (Configuracao) object;
        return !((this.configuracaoId == null && other.configuracaoId != null) || (this.configuracaoId != null && !this.configuracaoId.equals(other.configuracaoId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.Configuracao[configuracaoId=" + configuracaoId + "]";
    }
}
