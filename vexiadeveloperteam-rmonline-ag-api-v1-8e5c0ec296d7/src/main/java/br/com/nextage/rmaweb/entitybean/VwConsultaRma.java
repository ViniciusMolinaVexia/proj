package br.com.nextage.rmaweb.entitybean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Lucas Heitor M. M.
 */
@Entity
@Table(name = "VW_CONSULTA_RMA")
public class VwConsultaRma implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "vwDocumentoResponsabilidade";
    public static final String ID = "id";
    public static final String RM_NUMERO_RM = "rmNumeroRm";
    public static final String RM_CENTRO_ID = "centroId";
    public static final String RM_CENTRO_IDIOMA = "centroIdioma";
    public static final String RM_CENTRO_NOME = "centroNome";
    public static final String RM_AREA_ID = "areaId";
    public static final String RM_AREA_NOME = "areaNome";
    public static final String RM_AREA_CODIGO = "areaCodigo";
    public static final String RM_AREA_IDIOMA = "areaIdioma";
    public static final String PRIORIDADE_DESCRICAO = "prioridadeDescricao";
    public static final String MATERIAL_CODIGO = "materialCodigo";
    public static final String MATERIAL_NOME = "materialNome";
    public static final String RM_MATERIAL_QUANTIDADE = "rmMaterialQuantidade";
    public static final String UNIDADE_MEDIDA_SIGLA = "unidadeMedidaSigla";
    public static final String TIPO_MATERIAL_DESCRICAO = "tipoMaterialDescricao";
    public static final String TIPO_MATERIAL_CODIGO = "tipoMaterialCodigo";
    public static final String STATUS_NOME = "statusNome";
    public static final String TIPO_REQUISICAO_DESCRICAO = "tipoRequisicaoDescricao";
    public static final String DEPOSITO_CODIGO = "depositoCodigo";
    public static final String DEPOSITO_DESCRICAO = "depositoDescricao";
    public static final String RM_MATERIAL_COLETOR_CUSTOS = "rmMaterialColetorCustos";
    public static final String RM_MATERIAL_COLETOR_ESTOQUE = "rmMaterialColetorEstoque";
    public static final String RM_MATERIAL_DIAGRAMA_REDE = "rmMaterialDiagramaRede";
    public static final String RM_MATERIAL_OPERACAO = "rmMaterialOperacao";
    public static final String RM_MATERIAL_COLETOR_ORDEM = "rmMaterialColetorOrdem";
    public static final String USUARIO_PESSOA_NOME = "usuarioPessoaNome";
    public static final String USUARIO_PESSOA_REFERENCIA = "usuarioPessoaReferencia";
    public static final String USUARIO_USUARIO_ID = "usuarioUsuarioId";
    public static final String REQUISITANTE_NOME = "requisitanteNome";
    public static final String REQUISITANTE_REFERENCIA = "requisitanteReferencia";
    public static final String SETOR_CODIGO = "setorCodigo";
    public static final String SETOR_DESCRICAO = "setorDescricao";
    public static final String SUB_AREA_CODIGO = "subAreaCodigo";
    public static final String SUB_AREA_DESCRICAO = "subAreaDescricao";
    public static final String RM_DATA_EMISSAO = "rmDataEmissao";
    public static final String RM_DATA_APLICACAO = "rmDataAplicacao";
    public static final String RM_MATERIAL_DATA_REC_SUP = "rmMaterialDataRecSup";
    public static final String RM_MATERIAL_DATA_PREV_ENTR = "rmMaterialDataPrevEntr";
    public static final String RM_MATERIAL_DATA_COMPRA = "rmMaterialDataCompra";
    public static final String RM_MATERIAL_DATA_ULTIMA_NF = "rmMaterialDataUltimaNf";
    public static final String COMPRADOR_NOME = "compradorNome";
    public static final String DATA_COMPRA = "dataCompra";
    public static final String RM_MATERIAL_NUMERO_REQUISICAO_SAP = "rmMaterialNumeroRequisicaoSap";
    public static final String RM_MATERIAL_ITEM_REQUISICAO_SAP = "rmMaterialItemRequisicaoSap";
    public static final String RM_MATERIAL_NUMERO_DOC_ENTRADA = "rmMaterialNumeroDocEntrada";
    public static final String RM_MATERIAL_NUMERO_PEDIDO_COMPRA = "rmMaterialNumeroPedidoCompra";
    public static final String RM_MATERIAL_DATA_PREVISTA_ENTR = "rmMaterialDataPrevistaEntr";
    public static final String DIAS_PREVISTOS = "diasPrevistos";
    public static final String DATA_APROV_GERENTE_AREA = "dataAprovGerenteArea";
    public static final String GERENTE_AREA = "nomeAprovGerenteArea";
    public static final String GERENTE_AREA_ID = "gerenteAreaId";
    public static final String DATA_APROV_MAQUINA_PARADA = "dataAprovMaquinaParada";
    public static final String NOME_APROV_MAQUINA_PARADA = "nomeAprovMaquinaParada";
    public static final String DATA_APROV_GERENTE_CUSTOS = "dataAprovGerenteCustos";
    public static final String GERENTE_CUSTO = "nomeAprovGerenteCustos";
    public static final String GERENTE_CUSTO_ID = "gerenteCustoId";
    public static final String DATA_APROV_COORDENADOR = "dataAprovCoordenador";
    public static final String COORDENADOR = "nomeAprovCoordenador";
    public static final String COORDENADOR_ID = "coordenadorId";
    public static final String DATA_APROV_RESPONSAVEL_RETIRADA_ESTOQUE = "dataAprovResponsavelRetiradaEstoque";
    public static final String RESPONSAVEL_RETIRADA_ESTOQUE = "responsavelRetiradaEstoque";
    public static final String RESPONSAVEL_RETIRADA_ESTOQUE_ID = "responsavelRetiradaEstoqueId";
    public static final String DATA_APROV_COMPLEMENTO_CUSTOS = "dataAprovComplementoCustos";
    public static final String CONFIRMAR_RETIRADA = "confirmarRetirada";
    public static final String QTDE_RECEBIDA = "qtdeRecebida";
    public static final String MATERIAL_IS_SERVICO = "materialIsServico";
    public static final String TIPO_REQUISICAO_CODIGO = "tipoRequisicaoCodigo";
    public static final String RM_MATERIAL_FLUXO_MATERIAL = "rmMaterialFluxoMaterial";
    public static final String RM_OBSERVACAO = "rmObservacao";
    public static final String NOTA = "nota";
    public static final String RM_DATA_ENVIO_APROVACAO = "rmDataEnvioAprovacao";
    public static final String RM_DATA_REPROVACAO = "rmDataReprovacao";
    public static final String TRMR_DATA_RECEBIMENTO = "trmrDataRecebimento";
    public static final String RM_DATA_CANCELAMENTO = "rmDataCancelamento";
    public static final String RM_MATERIAL_OBSERVACAO = "rmMaterialObservacao";
    public static final String STATUS_CODIGO = "statusCodigo";
    public static final String COMPRADOR_COMPRADOR_ID = "compradorCompradorId";
    public static final String COMPRADOR_EAP = "compradorEap";
    public static final String PRIORIDADE_CODIGO = "prioridadeCodigo";
    public static final String RM_RM_ID = "rmRmId";
    public static final String RM_MATERIAL_JUST_ALT_QUANT = "rmMaterialJustAltQuant";
    public static final String RM_MATERIAL_RM_MATERIAL_ID = "rmMaterialRmMaterialId";
    public static final String RM_EAP_MULTI_EMPRESA_DESCRICAO = "rmEapMultiEmpresaDescricao";
    public static final String RM_EAP_MULTI_EMPRESA_ID = "rmEapMultiEmpresaId";
    public static final String DEPOSITO_EAP_MULTI_EMPRESA_DESCRICAO = "depositoEapMultiEmpresaDescricao";
    public static final String RM_MATERIAL_STATUS_OBSERVACAO = "rmMaterialStatusObservacao";

    //O id desta view é o numero da linha no resultado da consulta,
    //foi feito isso pois o jpa exige um id em uma @Entity
    //NÃO DEVE SER UTILIZADO E NAO DEVE SER GERADO O CONTRUTOR
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "RM_NUMERO_RM")
    private String rmNumeroRm;

    @Column(name = "PRIORIDADE_DESCRICAO")
    private String prioridadeDescricao;

    @Column(name = "MATERIAL_CODIGO")
    private String materialCodigo;

    @Column(name = "MATERIAL_NOME")
    private String materialNome;

    @Column(name = "RM_MATERIAL_QUANTIDADE")
    private Double rmMaterialQuantidade;

    @Column(name = "UNIDADE_MEDIDA_SIGLA")
    private String unidadeMedidaSigla;

    @Column(name = "TIPO_MATERIAL_DESCRICAO")
    private String tipoMaterialDescricao;

    @Column(name = "STATUS_NOME")
    private String statusNome;

    @Column(name = "TIPO_REQUISICAO_DESCRICAO")
    private String tipoRequisicaoDescricao;

    @Column(name = "DEPOSITO_CODIGO")
    private String depositoCodigo;

    @Column(name = "DEPOSITO_DESCRICAO")
    private String depositoDescricao;

    @Column(name = "RM_MATERIAL_COLETOR_CUSTOS")
    private String rmMaterialColetorCustos;

    @Column(name = "RM_MATERIAL_COLETOR_ESTOQUE")
    private Boolean rmMaterialColetorEstoque;

    @Column(name = "RM_MATERIAL_COLETOR_ORDEM")
    private String rmMaterialColetorOrdem;

    @Column(name = "RM_MATERIAL_DIAGRAMA_REDE")
    private String rmMaterialDiagramaRede;

    @Column(name = "RM_MATERIAL_OPERACAO")
    private String rmMaterialOperacao;

    @Column(name = "USUARIO_PESSOA_NOME")
    private String usuarioPessoaNome;

    @Column(name = "USUARIO_USUARIO_ID")
    private Integer usuarioUsuarioId;

    @Column(name = "USUARIO_PESSOA_REFERENCIA")
    private String usuarioPessoaReferencia;

    @Column(name = "REQUISITANTE_NOME")
    private String requisitanteNome;

    @Column(name = "REQUISITANTE_REFERENCIA")
    private String requisitanteReferencia;

    @Column(name = "SETOR_CODIGO")
    private String setorCodigo;

    @Column(name = "SETOR_DESCRICAO")
    private String setorDescricao;

    @Column(name = "SUB_AREA_CODIGO")
    private String subAreaCodigo;

    @Column(name = "SUB_AREA_DESCRICAO")
    private String subAreaDescricao;

    @Column(name = "RM_DATA_EMISSAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rmDataEmissao;

    @Column(name = "RM_DATA_APLICACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rmDataAplicacao;

    @Column(name = "RM_MATERIAL_DATA_REC_SUP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rmMaterialDataRecSup;

    @Column(name = "RM_MATERIAL_DATA_PREV_ENTR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rmMaterialDataPrevEntr;

    @Column(name = "RM_MATERIAL_DATA_COMPRA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rmMaterialDataCompra;

    @Column(name = "RM_MATERIAL_DATA_ULTIMA_NF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rmMaterialDataUltimaNf;

    @Column(name = "COMPRADOR_NOME")
    private String compradorNome;

    @Column(name = "COMPRADOR_EAP")
    private String compradorEap;

    @Column(name = "DATA_COMPRA")
    private String dataCompra;

    @Column(name = "RM_MATERIAL_NUMERO_REQUISICAO_SAP")
    private String rmMaterialNumeroRequisicaoSap;

    @Column(name = "RM_MATERIAL_ITEM_REQUISICAO_SAP")
    private Integer rmMaterialItemRequisicaoSap;

    @Column(name = "RM_MATERIAL_NUMERO_DOC_ENTRADA")
    private String rmMaterialNumeroDocEntrada;

    @Column(name = "RM_MATERIAL_NUMERO_PEDIDO_COMPRA")
    private String rmMaterialNumeroPedidoCompra;

    @Column(name = "RM_MATERIAL_DATA_PREVISTA_ENTR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rmMaterialDataPrevistaEntr;
    
    @Column(name = "RM_MATERIAL_GRUPO_COMPRADOR")
    private String rmMaterialGrupoComprador;

    @Column(name = "DIAS_PREVISTOS")
    private Integer diasPrevistos;

    @Column(name = "DATA_APROV_GERENTE_AREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAprovGerenteArea;

    @Column(name = "NOME_APROV_GERENTE_AREA")
    private String nomeAprovGerenteArea;

    @Column(name = "GERENTE_AREA_ID")
    private Integer gerenteAreaId;

    @Column(name = "DATA_APROV_MAQUINA_PARADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAprovMaquinaParada;

    @Column(name = "NOME_APROV_MAQUINA_PARADA")
    private String nomeAprovMaquinaParada;

    @Column(name = "DATA_APROV_GERENTE_CUSTOS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAprovGerenteCustos;

    @Column(name = "NOME_APROV_GERENTE_CUSTOS")
    private String nomeAprovGerenteCustos;

    @Column(name = "GERENTE_CUSTO_ID")
    private Integer gerenteCustoId;

    @Column(name = "DATA_APROV_COORDENADOR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAprovCoordenador;

    @Column(name = "NOME_APROV_COORDENADOR")
    private String nomeAprovCoordenador;

    @Column(name = "COORDENADOR_ID")
    private Integer coordenadorId;

    @Column(name = "DATA_APROV_RESPONSAVEL_RETIRADA_ESTOQUE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAprovResponsavelRetiradaEstoque;

    @Column(name = "RESPONSAVEL_RETIRADA_ESTOQUE")
    private String responsavelRetiradaEstoque;

    @Column(name = "RESPONSAVEL_RETIRADA_ESTOQUE_ID")
    private Integer responsavelRetiradaEstoqueId;

    @Column(name = "DATA_APROV_COMPLEMENTO_CUSTOS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAprovComplementoCustos;

    @Column(name = "CONFIRMAR_RETIRADA")
    private Boolean confirmarRetirada;

    @Column(name = "MATERIAL_IS_SERVICO")
    private Boolean materialIsServico;

    @Column(name = "QTDE_RECEBIDA")
    private Double qtdeRecebida;

    @Column(name = "TIPO_REQUISICAO_CODIGO")
    private String tipoRequisicaoCodigo;

    @Column(name = "RM_MATERIAL_FLUXO_MATERIAL")
    private String rmMaterialFluxoMaterial;

    @Column(name = "RM_OBSERVACAO")
    private String rmObservacao;
    
    @Column(name = "NOTA")
    private String nota;

    @Column(name = "RM_DATA_ENVIO_APROVACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rmDataEnvioAprovacao;

    @Column(name = "RM_DATA_REPROVACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rmDataReprovacao;

    @Column(name = "TRMR_DATA_RECEBIMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rmDataRecebimento;
    
    @Column(name = "RM_DATA_CANCELAMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rmDataCancelamento;

    @Column(name = "RM_MATERIAL_OBSERVACAO")
    private String rmMaterialObservacao;

    @Column(name = "STATUS_CODIGO")
    private String statusCodigo;

    @Column(name = "COMPRADOR_COMPRADOR_ID")
    private Integer compradorCompradorId;

    @Column(name = "PRIORIDADE_CODIGO")
    private String prioridadeCodigo;

    @Column(name = "TIPO_MATERIAL_CODIGO")
    private String tipoMaterialCodigo;

    @Column(name = "RM_RM_ID")
    private Integer rmRmId;

    @Column(name = "RM_MATERIAL_JUST_ALT_QUANT")
    private String rmMaterialJustAltQuant;

    @Column(name = "RM_MATERIAL_RM_MATERIAL_ID")
    private String rmMaterialRmMaterialId;

    @Column(name = "RM_EAP_MULTI_EMPRESA_ID")
    private Integer rmEapMultiEmpresaId;

    @Column(name = "RM_EAP_MULTI_EMPRESA_DESCRICAO")
    private String rmEapMultiEmpresaDescricao;

    @Column(name = "DEPOSITO_EAP_MULTI_EMPRESA_DESCRICAO")
    private String depositoEapMultiEmpresaDescricao;

    @Column(name = "RM_MATERIAL_STATUS_OBSERVACAO")
    private String rmMaterialStatusObservacao;
    
    @Column(name="RM_CENTRO_ID")
    private Integer centroId;
    
    @Column(name="RM_CENTRO_NOME")
    private String centroNome;
    
    @Column(name="RM_CENTRO_IDIOMA")
    private String centroIdioma;
    
    
    @Column(name="RM_AREA_ID")
    private String areaId;
    
    @Column(name="RM_AREA_NOME")
    private String areaNome;
    
    @Column(name="RM_AREA_IDIOMA")
    private String areaIdioma;
    
    @Column(name="RM_AREA_CODIGO")
    private String areaCodigo;
    
    @Transient
    private String numeroReqSap;
    
    @Transient
    private String envSap;
    
    public String getNumeroReqSap() {
		return numeroReqSap;
	}

	public void setNumeroReqSap(String numeroReqSap) {
		this.numeroReqSap = numeroReqSap;
	}
	
    public String getEnvSap() {
		return envSap;
	}

	public void setEnvSap(String envSap) {
		this.envSap = envSap;
	}
    
    public String getAreaNome() {
    	return this.areaNome;
    }
    
    public void setAreaNome(String areaNome) {
    	this.areaNome=areaNome;
    }
    
    
    public String getAreaIdioma() {
    	return this.areaIdioma;
    }
    
    public void setAreaIdioma(String areaIdioma) {
    	this.areaIdioma=areaIdioma;
    }
    
    public String getAreaCodigo() {
    	return this.areaCodigo;
    }
    
    public void setAreaCodigo(String areaCodigo) {
    	this.areaCodigo=areaCodigo;
    }
    
    public String getAreaId() {
    	return this.areaId;
    }
    
    public void setAreaId(String areaId) {
    	this.areaId=areaId;
    }
    
    public String getCentroNome() {
    	return this.centroNome;
    }
    
    public void setCentroNome(String centroNome) {
    	this.centroNome=centroNome;
    }
    
    public String getCentroIdioma() {
    	return this.centroNome;
    }
    
    public void setCentroIdioma(String centroIdioma) {
    	this.centroIdioma=centroIdioma;
    }
    
    public void setCentroId(Integer centroId) {
    	this.centroId=centroId;
    }
    
    public Integer getCentroId() {
    	return this.centroId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRmNumeroRm() {
        return rmNumeroRm;
    }

    public void setRmNumeroRm(String rmNumeroRm) {
        this.rmNumeroRm = rmNumeroRm;
    }

    public String getPrioridadeDescricao() {
        return prioridadeDescricao;
    }

    public void setPrioridadeDescricao(String prioridadeDescricao) {
        this.prioridadeDescricao = prioridadeDescricao;
    }

    public String getMaterialCodigo() {
        return materialCodigo;
    }

    public void setMaterialCodigo(String materialCodigo) {
        this.materialCodigo = materialCodigo;
    }

    public String getMaterialNome() {
        return materialNome;
    }

    public void setMaterialNome(String materialNome) {
        this.materialNome = materialNome;
    }

    public Double getRmMaterialQuantidade() {
        return rmMaterialQuantidade;
    }

    public void setRmMaterialQuantidade(Double rmMaterialQuantidade) {
        this.rmMaterialQuantidade = rmMaterialQuantidade;
    }

    public String getUnidadeMedidaSigla() {
        return unidadeMedidaSigla;
    }

    public void setUnidadeMedidaSigla(String unidadeMedidaSigla) {
        this.unidadeMedidaSigla = unidadeMedidaSigla;
    }

    public String getTipoMaterialDescricao() {
        return tipoMaterialDescricao;
    }

    public void setTipoMaterialDescricao(String tipoMaterialDescricao) {
        this.tipoMaterialDescricao = tipoMaterialDescricao;
    }

    public String getStatusNome() {
        return statusNome;
    }

    public void setStatusNome(String statusNome) {
        this.statusNome = statusNome;
    }

    public String getTipoRequisicaoDescricao() {
        return tipoRequisicaoDescricao;
    }

    public void setTipoRequisicaoDescricao(String tipoRequisicaoDescricao) {
        this.tipoRequisicaoDescricao = tipoRequisicaoDescricao;
    }

    public String getDepositoCodigo() {
        return depositoCodigo;
    }

    public void setDepositoCodigo(String depositoCodigo) {
        this.depositoCodigo = depositoCodigo;
    }

    public String getDepositoDescricao() {
        return depositoDescricao;
    }

    public void setDepositoDescricao(String depositoDescricao) {
        this.depositoDescricao = depositoDescricao;
    }

    public String getRmMaterialColetorCustos() {
        return rmMaterialColetorCustos;
    }

    public void setRmMaterialColetorCustos(String rmMaterialColetorCustos) {
        this.rmMaterialColetorCustos = rmMaterialColetorCustos;
    }

    public Boolean getRmMaterialColetorEstoque() {
        return rmMaterialColetorEstoque;
    }

    public void setRmMaterialColetorEstoque(Boolean rmMaterialColetorEstoque) {
        this.rmMaterialColetorEstoque = rmMaterialColetorEstoque;
    }

    public String getRmMaterialColetorOrdem() {
        return rmMaterialColetorOrdem;
    }

    public void setRmMaterialColetorOrdem(String rmMaterialColetorOrdem) {
        this.rmMaterialColetorOrdem = rmMaterialColetorOrdem;
    }

    public String getRmMaterialDiagramaRede() {
        return rmMaterialDiagramaRede;
    }

    public void setRmMaterialDiagramaRede(String rmMaterialDiagramaRede) {
        this.rmMaterialDiagramaRede = rmMaterialDiagramaRede;
    }

    public String getRmMaterialOperacao() {
        return rmMaterialOperacao;
    }

    public void setRmMaterialOperacao(String rmMaterialOperacao) {
        this.rmMaterialOperacao = rmMaterialOperacao;
    }

    public String getRequisitanteNome() {
        return requisitanteNome;
    }

    public void setRequisitanteNome(String requisitanteNome) {
        this.requisitanteNome = requisitanteNome;
    }

    public String getRequisitanteReferencia() {
        return requisitanteReferencia;
    }

    public void setRequisitanteReferencia(String requisitanteReferencia) {
        this.requisitanteReferencia = requisitanteReferencia;
    }

    public String getSetorCodigo() {
        return setorCodigo;
    }

    public void setSetorCodigo(String setorCodigo) {
        this.setorCodigo = setorCodigo;
    }

    public String getSetorDescricao() {
        return setorDescricao;
    }

    public void setSetorDescricao(String setorDescricao) {
        this.setorDescricao = setorDescricao;
    }

    public String getSubAreaCodigo() {
        return subAreaCodigo;
    }

    public void setSubAreaCodigo(String subAreaCodigo) {
        this.subAreaCodigo = subAreaCodigo;
    }

    public String getSubAreaDescricao() {
        return subAreaDescricao;
    }

    public void setSubAreaDescricao(String subAreaDescricao) {
        this.subAreaDescricao = subAreaDescricao;
    }

    public Date getRmDataEmissao() {
        return rmDataEmissao;
    }

    public void setRmDataEmissao(Date rmDataEmissao) {
        this.rmDataEmissao = rmDataEmissao;
    }

    public Date getRmDataAplicacao() {
        return rmDataAplicacao;
    }

    public void setRmDataAplicacao(Date rmDataAplicacao) {
        this.rmDataAplicacao = rmDataAplicacao;
    }

    public Date getRmMaterialDataRecSup() {
        return rmMaterialDataRecSup;
    }

    public void setRmMaterialDataRecSup(Date rmMaterialDataRecSup) {
        this.rmMaterialDataRecSup = rmMaterialDataRecSup;
    }

    public Date getRmMaterialDataPrevEntr() {
        return rmMaterialDataPrevEntr;
    }

    public void setRmMaterialDataPrevEntr(Date rmMaterialDataPrevEntr) {
        this.rmMaterialDataPrevEntr = rmMaterialDataPrevEntr;
    }

    public Date getRmMaterialDataCompra() {
        return rmMaterialDataCompra;
    }

    public void setRmMaterialDataCompra(Date rmMaterialDataCompra) {
        this.rmMaterialDataCompra = rmMaterialDataCompra;
    }

    public Date getRmMaterialDataUltimaNf() {
        return rmMaterialDataUltimaNf;
    }

    public void setRmMaterialDataUltimaNf(Date rmMaterialDataUltimaNf) {
        this.rmMaterialDataUltimaNf = rmMaterialDataUltimaNf;
    }

    public String getCompradorNome() {
        return compradorNome;
    }

    public void setCompradorNome(String compradorNome) {
        this.compradorNome = compradorNome;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getRmMaterialNumeroRequisicaoSap() {
        return rmMaterialNumeroRequisicaoSap;
    }

    public void setRmMaterialNumeroRequisicaoSap(String rmMaterialNumeroRequisicaoSap) {
        this.rmMaterialNumeroRequisicaoSap = rmMaterialNumeroRequisicaoSap;
    }

    public String getRmMaterialNumeroPedidoCompra() {
        return rmMaterialNumeroPedidoCompra;
    }

    public void setRmMaterialNumeroPedidoCompra(String rmMaterialNumeroPedidoCompra) {
        this.rmMaterialNumeroPedidoCompra = rmMaterialNumeroPedidoCompra;
    }

    public Date getRmMaterialDataPrevistaEntr() {
        return rmMaterialDataPrevistaEntr;
    }

    public void setRmMaterialDataPrevistaEntr(Date rmMaterialDataPrevistaEntr) {
        this.rmMaterialDataPrevistaEntr = rmMaterialDataPrevistaEntr;
    }
    
    public String getRmMaterialGrupoComprador() {
		return rmMaterialGrupoComprador;
	}

	public void setRmMaterialGrupoComprador(String rmMaterialGrupoComprador) {
		this.rmMaterialGrupoComprador = rmMaterialGrupoComprador;
	}

	public Integer getDiasPrevistos() {
        return diasPrevistos;
    }

    public void setDiasPrevistos(Integer diasPrevistos) {
        this.diasPrevistos = diasPrevistos;
    }

    public Date getDataAprovGerenteArea() {
        return dataAprovGerenteArea;
    }

    public void setDataAprovGerenteArea(Date dataAprovGerenteArea) {
        this.dataAprovGerenteArea = dataAprovGerenteArea;
    }

    public String getNomeAprovGerenteArea() {
        return nomeAprovGerenteArea;
    }

    public void setNomeAprovGerenteArea(String gerenteArea) {
        this.nomeAprovGerenteArea = gerenteArea;
    }

    public Integer getGerenteAreaId() {
        return gerenteAreaId;
    }

    public void setGerenteAreaId(Integer gerenteAreaId) {
        this.gerenteAreaId = gerenteAreaId;
    }

    public Date getDataAprovMaquinaParada() {
        return dataAprovMaquinaParada;
    }

    public void setDataAprovMaquinaParada(Date dataAprovMaquinaParada) {
        this.dataAprovMaquinaParada = dataAprovMaquinaParada;
    }

    public String getNomeAprovMaquinaParada() {
        return nomeAprovMaquinaParada;
    }

    public void setNomeAprovMaquinaParada(String nomeAprovMaquinaParada) {
        this.nomeAprovMaquinaParada = nomeAprovMaquinaParada;
    }

    public Date getDataAprovGerenteCustos() {
        return dataAprovGerenteCustos;
    }

    public void setDataAprovGerenteCustos(Date dataAprovGerenteCustos) {
        this.dataAprovGerenteCustos = dataAprovGerenteCustos;
    }

    public String getNomeAprovGerenteCustos() {
        return nomeAprovGerenteCustos;
    }

    public void setNomeAprovGerenteCustos(String nomeAprovGerenteCustos) {
        this.nomeAprovGerenteCustos = nomeAprovGerenteCustos;
    }

    public Integer getGerenteCustoId() {
        return gerenteCustoId;
    }

    public void setGerenteCustoId(Integer gerenteCustoId) {
        this.gerenteCustoId = gerenteCustoId;
    }

    public Date getDataAprovCoordenador() {
        return dataAprovCoordenador;
    }

    public void setDataAprovCoordenador(Date dataAprovCoordenador) {
        this.dataAprovCoordenador = dataAprovCoordenador;
    }

    public String getNomeAprovCoordenador() {
        return nomeAprovCoordenador;
    }

    public void setNomeAprovCoordenador(String nomeAprovCoordenador) {
        this.nomeAprovCoordenador = nomeAprovCoordenador;
    }

    public Integer getCoordenadorId() {
        return coordenadorId;
    }

    public void setCoordenadorId(Integer coordenadorId) {
        this.coordenadorId = coordenadorId;
    }

    public Date getDataAprovResponsavelRetiradaEstoque() {
        return dataAprovResponsavelRetiradaEstoque;
    }

    public void setDataAprovResponsavelRetiradaEstoque(Date dataAprovResponsavelRetiradaEstoque) {
        this.dataAprovResponsavelRetiradaEstoque = dataAprovResponsavelRetiradaEstoque;
    }

    public String getResponsavelRetiradaEstoque() {
        return responsavelRetiradaEstoque;
    }

    public void setResponsavelRetiradaEstoque(String responsavelRetiradaEstoque) {
        this.responsavelRetiradaEstoque = responsavelRetiradaEstoque;
    }

    public Integer getResponsavelRetiradaEstoqueId() {
        return responsavelRetiradaEstoqueId;
    }

    public void setResponsavelRetiradaEstoqueId(Integer responsavelRetiradaEstoqueId) {
        this.responsavelRetiradaEstoqueId = responsavelRetiradaEstoqueId;
    }

    public Integer getRmMaterialItemRequisicaoSap() {
        return rmMaterialItemRequisicaoSap;
    }

    public void setRmMaterialItemRequisicaoSap(Integer rmMaterialItemRequisicaoSap) {
        this.rmMaterialItemRequisicaoSap = rmMaterialItemRequisicaoSap;
    }

    public String getRmMaterialNumeroDocEntrada() {
        return rmMaterialNumeroDocEntrada;
    }

    public void setRmMaterialNumeroDocEntrada(String rmMaterialNumeroDocEntrada) {
        this.rmMaterialNumeroDocEntrada = rmMaterialNumeroDocEntrada;
    }

    public Date getDataAprovComplementoCustos() {
        return dataAprovComplementoCustos;
    }

    public void setDataAprovComplementoCustos(Date dataAprovComplementoCustos) {
        this.dataAprovComplementoCustos = dataAprovComplementoCustos;
    }

    public Boolean getConfirmarRetirada() {
        return confirmarRetirada;
    }

    public void setConfirmarRetirada(Boolean confirmarRetirada) {
        this.confirmarRetirada = confirmarRetirada;
    }

    public Double getQtdeRecebida() {
        return qtdeRecebida;
    }

    public void setQtdeRecebida(Double qtdeRecebida) {
        this.qtdeRecebida = qtdeRecebida;
    }

    public String getUsuarioPessoaNome() {
        return usuarioPessoaNome;
    }

    public void setUsuarioPessoaNome(String usuarioPessoaNome) {
        this.usuarioPessoaNome = usuarioPessoaNome;
    }

    public Integer getUsuarioUsuarioId() {
        return usuarioUsuarioId;
    }

    public void setUsuarioUsuarioId(Integer usuarioUsuarioId) {
        this.usuarioUsuarioId = usuarioUsuarioId;
    }

    public String getUsuarioPessoaReferencia() {
        return usuarioPessoaReferencia;
    }

    public void setUsuarioPessoaReferencia(String usuarioPessoaReferencia) {
        this.usuarioPessoaReferencia = usuarioPessoaReferencia;
    }

    public Boolean getMaterialIsServico() {
        return materialIsServico;
    }

    public void setMaterialIsServico(Boolean materialIsServico) {
        this.materialIsServico = materialIsServico;
    }

    public String getTipoRequisicaoCodigo() {
        return tipoRequisicaoCodigo;
    }

    public void setTipoRequisicaoCodigo(String tipoRequisicaoCodigo) {
        this.tipoRequisicaoCodigo = tipoRequisicaoCodigo;
    }

    public String getRmMaterialFluxoMaterial() {
        return rmMaterialFluxoMaterial;
    }

    public void setRmMaterialFluxoMaterial(String rmMaterialFluxoMaterial) {
        this.rmMaterialFluxoMaterial = rmMaterialFluxoMaterial;
    }

    public String getRmObservacao() {
        return rmObservacao;
    }

    public void setRmObservacao(String rmObservacao) {
        this.rmObservacao = rmObservacao;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    
    public Date getRmDataEnvioAprovacao() {
        return rmDataEnvioAprovacao;
    }

    public void setRmDataEnvioAprovacao(Date rmDataEnvioAprovacao) {
        this.rmDataEnvioAprovacao = rmDataEnvioAprovacao;
    }

    public Date getRmDataReprovacao() {
        return rmDataReprovacao;
    }

    public void setRmDataReprovacao(Date rmDataReprovacao) {
        this.rmDataReprovacao = rmDataReprovacao;
    }

    public Date getRmDataCancelamento() {
        return rmDataCancelamento;
    }

    public void setRmDataCancelamento(Date rmDataCancelamento) {
        this.rmDataCancelamento = rmDataCancelamento;
    }

    public String getRmMaterialObservacao() {
        return rmMaterialObservacao;
    }

    public void setRmMaterialObservacao(String rmMaterialObservacao) {
        this.rmMaterialObservacao = rmMaterialObservacao;
    }

    public Integer getCompradorCompradorId() {
        return compradorCompradorId;
    }

    public void setCompradorCompradorId(Integer compradorCompradorId) {
        this.compradorCompradorId = compradorCompradorId;
    }

    public String getStatusCodigo() {
        return statusCodigo;
    }

    public void setStatusCodigo(String statusCodigo) {
        this.statusCodigo = statusCodigo;
    }

    public String getPrioridadeCodigo() {
        return prioridadeCodigo;
    }

    public void setPrioridadeCodigo(String prioridadeCodigo) {
        this.prioridadeCodigo = prioridadeCodigo;
    }

    public String getTipoMaterialCodigo() {
        return tipoMaterialCodigo;
    }

    public void setTipoMaterialCodigo(String tipoMaterialCodigo) {
        this.tipoMaterialCodigo = tipoMaterialCodigo;
    }

    public Integer getRmRmId() {
        return rmRmId;
    }

    public void setRmRmId(Integer rmRmId) {
        this.rmRmId = rmRmId;
    }

    public String getRmMaterialJustAltQuant() {
        return rmMaterialJustAltQuant;
    }

    public void setRmMaterialJustAltQuant(String rmMaterialJustAltQuant) {
        this.rmMaterialJustAltQuant = rmMaterialJustAltQuant;
    }

    public String getRmMaterialRmMaterialId() {
        return rmMaterialRmMaterialId;
    }

    public void setRmMaterialRmMaterialId(String rmMaterialRmMaterialId) {
        this.rmMaterialRmMaterialId = rmMaterialRmMaterialId;
    }

    public Integer getRmEapMultiEmpresaId() {
        return rmEapMultiEmpresaId;
    }

    public void setRmEapMultiEmpresaId(Integer rmEapMultiEmpresaId) {
        this.rmEapMultiEmpresaId = rmEapMultiEmpresaId;
    }

    public String getRmEapMultiEmpresaDescricao() {
        return rmEapMultiEmpresaDescricao;
    }

    public void setRmEapMultiEmpresaDescricao(String rmEapMultiEmpresaDescricao) {
        this.rmEapMultiEmpresaDescricao = rmEapMultiEmpresaDescricao;
    }

    public String getCompradorEap() {
        return compradorEap;
    }

    public void setCompradorEap(String compradorEap) {
        this.compradorEap = compradorEap;
    }

    public void setDepositoEapMultiEmpresaDescricao(String depositoEapMultiEmpresaDescricao) {
        this.depositoEapMultiEmpresaDescricao = depositoEapMultiEmpresaDescricao;
    }

    public String getDepositoEapMultiEmpresaDescricao() {
        return depositoEapMultiEmpresaDescricao;
    }

    public String getRmMaterialStatusObservacao() {
        return rmMaterialStatusObservacao;
    }

    public void setRmMaterialStatusObservacao(String rmMaterialStatusObservacao) {
        this.rmMaterialStatusObservacao = rmMaterialStatusObservacao;
    }

	public Date getRmDataRecebimento() {
		return rmDataRecebimento;
	}

	public void setRmDataRecebimento(Date rmDataRecebimento) {
		this.rmDataRecebimento = rmDataRecebimento;
	}

	@Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.VwConsultaRma[]";
    }

	
}
