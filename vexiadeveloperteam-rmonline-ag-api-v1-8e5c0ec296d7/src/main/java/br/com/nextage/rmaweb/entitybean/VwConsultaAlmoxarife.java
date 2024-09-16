package br.com.nextage.rmaweb.entitybean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "VW_CONSULTA_ALMOXARIFE")
public class VwConsultaAlmoxarife implements Serializable {

	private static final long serialVersionUID = 6334157207781142905L;

	public static final String ID = "id";
	public static final String CENTRO = "centro";
	public static final String COD_CENTRO = "cod_centro";
	public static final String NUMERO = "numero";
	public static final String NUMERO_SAP = "numeroSap";
	public static final String MATERIAL = "material";
	public static final String COD_MATERIAL = "cod_material";
	public static final String CODIGO = "codigo";
	public static final String QUANTIDADE = "quantidade";
	public static final String DATA_EMISSAO = "dataEmissao";
	public static final String DATA_APLICACAO = "dataAplicacao";
	public static final String REQUISITANTE = "requisitante";
	public static final String REQUISITANTE_ID = "requisitante_id";
	public static final String CADASTRANTE = "cadastrante";
	public static final String FLUXO_MATERIAL = "fluxoMaterial";
	public static final String COLETOR_CUSTOS = "coletorCustos";
	public static final String DIAGRAMA_REDE = "diagramaRede";
	public static final String DATA_AVALIACAO = "dataAvaliacao";
	public static final String STATUS = "status";
	public static final String STATUS_ITEM = "statusItem";
	public static final String TIPO_REQUISICAO = "tipoRequisicao";
	public static final String OBSERVACAO = "observacao";
	public static final String RM_MATERIAL_ID = "rmMaterialId";
	public static final String RM_ID = "rmId";
	public static final String UNIDADE_MEDIDA = "unidadeMedida";
	public static final String AREA_CODIGO = "areaCodigo";
	public static final String NOTA = "nota";
	public static final String DEPOSITO_CODIGO = "depositoCodigo";
	public static final String GRUPO_MERCADORIA = "grupoMercadoria";
	public static final String SOLICITANTE = "solicitante";
	public static final String SOLICITANTE_ID = "solicitante_id";
	public static final String DIAS_PREV_ENTREGA = "dias_prev_entrega";
	public static final String DATA_APROVACAO = "data_aprovacao";
	public static final String IS_BAIXA = "is_baixa";
	public static final String NUMERO_BAIXA = "is_baixa";

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "centro")
	private String centro;

	@Column(name = "cod_centro")
	private String codCentro;

	@Column(name = "numero")
	private String numero;

	@Column(name = "numero_sap")
	private String numeroSap;

	@Column(name = "material")
	private String material;

	@Column(name = "cod_material")
	private String codMaterial;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "quantidade")
	private String quantidade;

	@Column(name = "valor_orcado")
	private String valorOrcado;

	@Column(name = "data_emissao")
	private Date dataEmissao;

	@Column(name = "data_aplicacao")
	private Date dataAplicacao;

	@Column(name = "requisitante")
	private String requisitante;

	@Column(name = "requisitante_id")
	private String requisitanteId;

	@Column(name = "cadastrante")
	private String cadastrante;
	
	@Column(name = "cadastranteId")
	private String cadastranteId;

	@Column(name="fluxo_material")
	private String fluxoMaterial;
	
	@Column(name="coletor_custos")
	private String coletorCustos;

	@Column(name="diagrama_rede")
	private String diagramaRede;
	
	@Column(name="data_avaliacao")
	private Date dataAvaliacao;
	
	@Column(name="status")
	private String status;
	
	@Column(name="status_item")
	private String statusItem;
	
	@Column(name="tipo_requisicao")
	private String tipoRequisicao;
	
	@Column(name="observacao")
	private String observacao;
	
	@Column(name="rmMaterialId")
	private Integer rmMaterialId;
	
	@Column(name="rmId")
	private Integer rmId;
	
	@Column(name="unidade_medida")
	private String unidadeMedida;
	
	@Column(name="area_codigo")
	private String areaCodigo;
	
	@Column(name="nota")
	private String nota;
	
	@Column(name="deposito_codigo")
	private String depositoCodigo;
	
	@Column(name="grupo_mercadoria")
	private String grupoMercadoria;
	
	@Column(name="solicitante")
	private String solicitante;

	@Column(name = "solicitante_id")
	private String solicitanteId;

	@Column(name = "codigo_prioridade_sap")
	private String codigoPrioridadeSap;

	@Column(name = "prioridade_desc")
	private String prioridadeDesc;

	@Column(name = "tipo_req_codigo")
	private String tipoRequisicaoCodigo;
	
	@Column(name = "is_baixa")
	private Boolean isBaixa;

	@Column(name = "numero_baixa")
	private String numeroBaixa;

	@Column(name="grupo_comprador")
	private String grupoComprador;

	//"dias_prev_entrega";
	private Integer diasPrevEntrega;

	//"data_aprovacao"
	private Date dataAprovacao;

	@Transient
	private Boolean selected = false;

	public String getSolicitante() {
		return this.solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}
	
	public String getGrupoMercadoria() {
		return this.grupoMercadoria;
	}
	
	public void setGrupoMercadoria(String grupoMercadoria) {
		this.grupoMercadoria=grupoMercadoria;
	}
	
	public String getDepositoCodigo() {
		return this.depositoCodigo;
	}
	
	public void setDepositoCodigo(String depositoCodigo) {
		this.depositoCodigo=depositoCodigo;
	}
	
	public void setAreaCodigo(String areaCodigo) {
		this.areaCodigo=areaCodigo;
	}
	
	public String getAreaCodigo() {
		return this.areaCodigo;
	}
	
	public void setNota(String nota) {
		this.nota=nota;
	}
	
	public String getNota() {
		return this.nota;
	}
	
	public String getUnidadeMedida() {
		return this.unidadeMedida;
	}
	
	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida=unidadeMedida;
	}
	
	public String getCodCentro() {
		return this.codCentro;
	}
	
	public void setCodCentro(String codCentro) {
		this.codCentro = codCentro;
	}
	
	public String getCodMaterial() {
		return this.codMaterial;
	}
	
	public void setCodMaterial(String codMaterial) {
		this.codMaterial=codMaterial;
	}
	
	public Integer getRmId() {
		return this.rmId;
	}
	
	public void setRmId(Integer rmId) {
		this.rmId=rmId;
	}
	
	public Integer getRmMaterialId() {
		return this.rmMaterialId;
	}
	
	public void setRmMaterialId(Integer rmMaterialId) {
		this.rmMaterialId=rmMaterialId;
	}

	public Boolean getSelected() {
		return this.selected;
	}
	
	public void setSelected(Boolean selected) {
		this.selected=selected;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNumeroSap() {
		return numeroSap;
	}

	public void setNumeroSap(String numeroSap) {
		this.numeroSap = numeroSap;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataAplicacao() {
		return dataAplicacao;
	}

	public void setDataAplicacao(Date dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}

	public String getRequisitante() {
		return requisitante;
	}

	public void setRequisitante(String requisitante) {
		this.requisitante = requisitante;
	}

	public String getCadastrante() {
		return cadastrante;
	}

	public void setCadastrante(String cadastrante) {
		this.cadastrante = cadastrante;
	}

	public String getFluxoMaterial() {
		return fluxoMaterial;
	}

	public void setFluxoMaterial(String fluxoMaterial) {
		this.fluxoMaterial = fluxoMaterial;
	}

	public String getColetorCustos() {
		return coletorCustos;
	}

	public void setColetorCustos(String coletorCustos) {
		this.coletorCustos = coletorCustos;
	}

	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusItem() {
		return statusItem;
	}

	public void setStatusItem(String statusItem) {
		this.statusItem = statusItem;
	}

	public String getTipoRequisicao() {
		return tipoRequisicao;
	}

	public void setTipoRequisicao(String tipoRequisicao) {
		this.tipoRequisicao = tipoRequisicao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getRequisitanteId() {
		return requisitanteId;
	}

	public void setRequisitanteId(String requisitanteId) {
		this.requisitanteId = requisitanteId;
	}

	public String getCadastranteId() {
		return cadastranteId;
	}

	public void setCadastranteId(String cadastranteId) {
		this.cadastranteId = cadastranteId;
	}

	public String getSolicitanteId() {
		return solicitanteId;
	}

	public void setSolicitanteId(String solicitanteId) {
		this.solicitanteId = solicitanteId;
	}

	public String getValorOrcado() {
		return valorOrcado;
	}

	public void setValorOrcado(String valorOrcado) {
		this.valorOrcado = valorOrcado;
	}

	public String getCodigoPrioridadeSap() {
		return codigoPrioridadeSap;
	}

	public void setCodigoPrioridadeSap(String codigoPrioridadeSap) {
		this.codigoPrioridadeSap = codigoPrioridadeSap;
	}

	public String getPrioridadeDesc() {
		return prioridadeDesc;
	}

	public void setPrioridadeDesc(String prioridadeDesc) {
		this.prioridadeDesc = prioridadeDesc;
	}

	public String getTipoRequisicaoCodigo() {
		return tipoRequisicaoCodigo;
	}

	public void setTipoRequisicaoCodigo(String tipoRequisicaoCodigo) {
		this.tipoRequisicaoCodigo = tipoRequisicaoCodigo;
	}

	public Integer getDiasPrevEntrega() {
		return diasPrevEntrega;
	}

	public void setDiasPrevEntrega(Integer diasPrevEntrega) {
		this.diasPrevEntrega = diasPrevEntrega;
	}

	public Date getDataAprovacao() {
		return dataAprovacao;
	}

	public void setDataAprovacao(Date dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}

	public String getDiagramaRede() {
		return diagramaRede;
	}

	public void setDiagramaRede(String diagramaRede) {
		this.diagramaRede = diagramaRede;
	}

	public Boolean getIsBaixa() {
		return isBaixa;
	}

	public void setIsBaixa(Boolean isBaixa) {
		this.isBaixa = isBaixa;
	}

	public String getNumeroBaixa() {
		return numeroBaixa;
	}

	public void setNumeroBaixa(String numeroBaixa) {
		this.numeroBaixa = numeroBaixa;
	}

	public String getGrupoComprador() {
		return grupoComprador;
	}

	public void setGrupoComprador(String grupoComprador) {
		this.grupoComprador = grupoComprador;
	}
}
