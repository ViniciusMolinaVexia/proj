/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.entitybean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "TB_RM_MATERIAL")
@NamedQueries({
	@NamedQuery(name="rmMaterial.getById",query="select a from RmMaterial a where a.rmMaterialId = :id")
})
public class RmMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "rmMaterial";
    public static final String ALIAS_CLASSE_UNDERLINE = "rmMaterial_";

    public static final String RM_MATERIAL_ID = "rmMaterialId";
    public static final String QUANTIDADE = "quantidade";
    public static final String ORDEM = "ordem";
    public static final String OBSERVACAO = "observacao";
    public static final String NOTA = "nota";
    public static final String RM = "rm";
    public static final String MATERIAL = "material";
    public static final String NUMERO_PEDIDO_COMPRA = "numeroPedidoCompra";
    public static final String NUMERO_REQUISICAO_SAP = "numeroRequisicaoSap";
    public static final String ITEM_REQUISICAO_SAP = "itemRequisicaoSap";
    public static final String NUMERO_DOC_ENTRADA = "numeroDocEntrada";
    public static final String NUMERO_RESERVA = "numeroReserva";
    public static final String DATA_COMPRA = "dataCompra";
    public static final String DATA_PREVISAO_CHEGADA = "dataPrevisaoChegada";
    public static final String FORNECEDOR_ID = "fornecedor";
    public static final String COLETOR_CUSTOS = "coletorCustos";
    public static final String OPERACAO = "operacao";
    public static final String DIAGRAMA_REDE = "diagramaRede";
    public static final String ESTA_NO_ORCAMENTO = "estaNoOrcamento";
    public static final String VALOR_ORCADO = "valorOrcado";
    public static final String OBSERVACAO_CUSTO = "observacaoCusto";
    public static final String FLUXO_MATERIAL = "fluxoMaterial";
    public static final String DEPOSITO_ID = "deposito";
    public static final String OBSERVACAO_ESTOQUISTA = "observacaoEstoquista";
    public static final String QUANTIDADE_ORIGINAL = "quantidadeOriginal";
    public static final String ITEM_DO_PEDIDO = "itemDoPedido";
    public static final String CENTRO = "centro";
    public static final String STATUS = "status";
    public static final String VALOR_PEDIDO = "valorPedido";
    public static final String DATA_RECEBIMENTO_TOTAL = "dataRecebimentoTotal";
    public static final String LOCAL_ENTREGA = "localEntrega";
    public static final String COLETOR_ORDEM = "coletorOrdem";
    public static final String COMPRADOR = "comprador";
    public static final String AGRUPADOR_ERRO = "agrupadorErro";
    public static final String DATA_PREVISTA_ENTREGA = "dataPrevistaEntrega";
    public static final String CF_RESPONSAVEL = "cfResponsavel";
    public static final String DOCUMENTO_RESPONSAVEL_IMPRESSO = "documentoResponsavelImpresso";
    public static final String PROTOCOLO_RESPONSABILIDADE = "protocoloResponsabilidade";
    public static final String DATA_RECEBIMENTO_SUPRIMENTOS = "dataRecebimentoSuprimentos";
    public static final String DATA_PREV_ENTREGA_SUPRIMENTOS = "dataPrevEntregaSuprimentos";
    public static final String PREFIXO_EQUIPAMENTO = "prefixoEquipamento";
    public static final String JUSTIFICATIVA_ALTERACAO_MATERIAL = "justificativaAlteracaoMaterial";
    public static final String JUSTIFICATIVA_ALTERACAO_QUANTIDADE = "justificativaAlteracaoQuantidade";
    public static final String COLETOR_ESTOQUE = "coletorEstoque";
    public static final String DATA_FINALIZACAO_SERVICO = "dataFinalizacaoServico";
    public static final String DATA_ATRIBUIR_COMPRADOR = "dataAtribuirComprador";
    public static final String DATA_CANCELAMENTO = "dataCancelamento";
    public static final String OBSERVACAO_CANCELAMENTO = "observacaoCancelamento";
    public static final String DATA_ULTIMA_NF = "dataUltimaNotaFiscal";
    public static final String IS_REC_SAP = "isRecSap";
    public static final String CONFIRMA_RET_NAO_PRESENCIAL = "confirmaRetNaoPresencial";
    public static final String CONCLUIDA_COMPRADOR = "concluidaComprador";
    public static final String COD_MATERIAL_ANTERIOR = "codMaterialAnterior";
    public static final String PESSOA_RESPONSAVEL = "pessoaResponsavel";
	public static final String GRUPO_COMPRADOR = "grupoComprador";
	public static final String IS_BAIXA = "isBaixa";
	public static final String NUMERO_BAIXA = "numeroBaixa";

    @Id
    @Column(name = "RM_MATERIAL_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_RM_MATERIAL_ID", allocationSize = 1)
    private Integer rmMaterialId;

    @Basic(optional = false)
    @Column(name = "QUANTIDADE")
    private Double quantidade;

    @Basic(optional = false)
    @Column(name = "ORDEM")
    private int ordem;

    @Column(name = "OBSERVACAO", length = 1000)
    private String observacao;
    
    @Column(name = "NOTA", length = 1000)
    private String nota;

    @Column(name = "DATA_COMPRA")
    @Temporal(TemporalType.DATE)
    private Date dataCompra;

    @Column(name = "DATA_PREVISAO_CHEGADA")
    @Temporal(TemporalType.DATE)
    private Date dataPrevisaoChegada;

    @Column(name = "NUMERO_PEDIDO_COMPRA")
    private String numeroPedidoCompra;

    @Column(name = "NUMERO_REQUISICAO_SAP")
    private String numeroRequisicaoSap;

    @Column(name = "ITEM_REQUISICAO_SAP")
    private Integer itemRequisicaoSap;

    @Column(name = "NUMERO_DOC_ENTRADA", length = 20)
    private String numeroDocEntrada;

    @Column(name = "NUMERO_RESERVA")
    private String numeroReserva;

    @JoinColumn(name = "FORNECEDOR_ID", referencedColumnName = "FORNECEDOR_ID")
    @ManyToOne
    private Fornecedor fornecedor;

    @Basic(optional = true)
    @Column(name = "CF_RESPONSAVEL")
    private String cfResponsavel;

    @JoinColumn(name = "RM_ID", referencedColumnName = "RM_ID")
    @ManyToOne(optional = false)
    private Rm rm;

    @JoinColumn(name = "MATERIAL_ID", referencedColumnName = "MATERIAL_ID")
    @ManyToOne(optional = false)
    private Material material;

    @Column(name = "COLETOR_CUSTOS", length = 200)
    private String coletorCustos;

    @Column(name = "OPERACAO", length = 200)
    private String operacao;

    @Column(name = "DIAGRAMA_REDE", length = 200)
    private String diagramaRede;

    @Column(name = "ESTA_NO_ORCAMENTO", length = 20)
    private String estaNoOrcamento;

    @Column(name = "VALOR_ORCADO")
    private Double valorOrcado;

    @Column(name = "OBSERVACAO_CUSTO", length = 1000)
    private String observacaoCusto;

    @Column(name = "FLUXO_MATERIAL", length = 30)
    private String fluxoMaterial;

    @JoinColumn(name = "DEPOSITO_ID", referencedColumnName = "DEPOSITO_ID")
    @ManyToOne
    private Deposito deposito;

    @Column(name = "OBSERVACAO_ESTOQUISTA", length = 1000)
    private String observacaoEstoquista;

    @Column(name = "QUANTIDADE_ORIGINAL")
    private Double quantidadeOriginal;

    @Column(name = "ITEM_DO_PEDIDO", length = 200)
    private String itemDoPedido;

    @Column(name = "CENTRO", length = 200)
    private String centro;

    @Column(name = "STATUS", length = 1)
    private String status;

    @Column(name = "VALOR_PEDIDO")
    private Double valorPedido;

    @Column(name = "DATA_RECEBIMENTO_TOTAL")
    @Temporal(TemporalType.DATE)
    private Date dataRecebimentoTotal;

    @Column(name = "LOCAL_ENTREGA", length = 200)
    private String localEntrega;

    @Column(name = "COLETOR_ORDEM", length = 200)
    private String coletorOrdem;

    @JoinColumn(name = "COMPRADOR_ID", referencedColumnName = "COMPRADOR_ID")
    @ManyToOne
    private Comprador comprador;

    @Column(name = "AGRUPADOR_ERRO")
    private Integer agrupadorErro;

    @Column(name = "DATA_PREVISTA_ENTREGA")
    @Temporal(TemporalType.DATE)
    private Date dataPrevistaEntrega;

    @Column(name = "DATA_RECEBIMENTO_SUPRIMENTOS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRecebimentoSuprimentos;

    @Column(name = "DATA_PREV_ENTREGA_SUPRIMENTOS")
    @Temporal(TemporalType.DATE)
    private Date dataPrevEntregaSuprimentos;

    @Column(name = "DOCUMENTO_RESPONSAVEL_IMPRESSO", length = 1)
    @Basic(optional = true)
    private String documentoResponsavelImpresso;

    @Column(name = "PROTOCOLO_RESPONSABILIDADE", length = 20)
    private String protocoloResponsabilidade;

    @Basic(optional = true)
    @Column(name = "PREFIXO_EQUIPAMENTO", length = 400)
    private String prefixoEquipamento;

    @Column(name = "JUSTIFICATIVA_ALTERACAO_MATERIAL", length = 500)
    private String justificativaAlteracaoMaterial;

    @Column(name = "JUSTIFICATIVA_ALTERACAO_QUANTIDADE", length = 500)
    private String justificativaAlteracaoQuantidade;

    @Column(name = "COLETOR_ESTOQUE")
    private Boolean coletorEstoque;

    @Column(name = "DATA_FINALIZACAO_SERVICO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFinalizacaoServico;

    @Column(name = "DATA_ATRIBUIR_COMPRADOR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtribuirComprador;

    @Column(name = "DATA_ULTIMA_NF")
    @Temporal(TemporalType.DATE)
    private Date dataUltimaNotaFiscal;

    @Column(name = "DATA_CANCELAMENTO")
    @Temporal(TemporalType.DATE)
    private Date dataCancelamento;

    @Column(name = "OBSERVACAO_CANCELAMENTO", length = 200)
    private String observacaoCancelamento;

    @Column(name = "IS_REC_SAP")
    private Boolean isRecSap;

	@Column(name = "IS_BAIXA")
    private Boolean isBaixa;
    
    @Column(name = "NUMERO_BAIXA")
    private String numeroBaixa;
    
    
    @Column(name = "CONFIRMA_RET_NAO_PRESENCIAL")
    private Boolean confirmaRetNaoPresencial;

    @Column(name = "CONCLUIDA_COMPRADOR")
    private Boolean concluidaComprador;

    @Column(name = "COD_MATERIAL_ANTERIOR")
    private String codMaterialAnterior;

    @JoinColumn(name = "PESSOA_RESPONSAVEL_ID", referencedColumnName = "PESSOA_ID")
    @ManyToOne
    private Pessoa pessoaResponsavel;
    
    @Column(name= "GRUPO_COMPRADOR", length = 20)
    private String grupoComprador;
    
    @Transient
    private String stDataPrevistaEntrega;

    @Transient
    private Double qtdRecebido;

    @Transient
    private Double qtdRetirado;

    @Transient
    private Boolean possuiAnexoMaterial;

    @Transient
    private Boolean possuiAnexoRm;

    @Transient
    private String justificativaReprovacao;

    public RmMaterial() {
    }

    public RmMaterial(Integer rmMaterialId) {
        this.rmMaterialId = rmMaterialId;
    }

    public RmMaterial(Integer rmMaterialId, Double quantidade, int ordem) {
        this.rmMaterialId = rmMaterialId;
        this.quantidade = quantidade;
        this.ordem = ordem;
    }

    public RmMaterial(RmMaterial rmMaterial) {
        quantidade = rmMaterial.getQuantidade();
        numeroRequisicaoSap = rmMaterial.getNumeroRequisicaoSap();
        ordem = rmMaterial.getOrdem();
        observacao = rmMaterial.getObservacao();
        nota = rmMaterial.getNota();
        dataCompra = rmMaterial.getDataCompra();
        dataPrevisaoChegada = rmMaterial.getDataPrevisaoChegada();
        numeroPedidoCompra = rmMaterial.getNumeroPedidoCompra();
        fornecedor = rmMaterial.getFornecedor();
        rm = rmMaterial.getRm();
        material = rmMaterial.getMaterial();
        coletorCustos = rmMaterial.getColetorCustos();
        diagramaRede = rmMaterial.getDiagramaRede();
        operacao = rmMaterial.getOperacao();
        estaNoOrcamento = rmMaterial.getEstaNoOrcamento();
        valorOrcado = rmMaterial.getValorOrcado();
        observacaoCusto = rmMaterial.getObservacaoCusto();
        deposito = rmMaterial.getDeposito();
        localEntrega = rmMaterial.getLocalEntrega();
        dataUltimaNotaFiscal = rmMaterial.getDataUltimaNotaFiscal();
        dataCancelamento = rmMaterial.getDataCancelamento();
        observacaoCancelamento = rmMaterial.getObservacaoCancelamento();
        codMaterialAnterior = rmMaterial.getCodMaterialAnterior();
    }

    public String getNumeroDocEntrada() {
        return numeroDocEntrada;
    }

    public void setNumeroDocEntrada(String numeroDocEntrada) {
        this.numeroDocEntrada = numeroDocEntrada;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getRmMaterialId() {
        return rmMaterialId;
    }

    public void setRmMaterialId(Integer rmMaterialId) {
        this.rmMaterialId = rmMaterialId;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Rm getRm() {
        return rm;
    }

    public void setRm(Rm rm) {
        this.rm = rm;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Date getDataPrevisaoChegada() {
        return dataPrevisaoChegada;
    }

    public void setDataPrevisaoChegada(Date dataPrevisaoChegada) {
        this.dataPrevisaoChegada = dataPrevisaoChegada;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getNumeroPedidoCompra() {
        return numeroPedidoCompra;
    }

    public void setNumeroPedidoCompra(String numeroPedidoCompra) {
        this.numeroPedidoCompra = numeroPedidoCompra;
    }

    public String getNumeroRequisicaoSap() {
        return numeroRequisicaoSap;
    }

    public void setNumeroRequisicaoSap(String numeroRequisicaoSap) {
        this.numeroRequisicaoSap = numeroRequisicaoSap;
    }

    public Integer getItemRequisicaoSap() {
        return itemRequisicaoSap;
    }

    public void setItemRequisicaoSap(Integer itemRequisicaoSap) {
        this.itemRequisicaoSap = itemRequisicaoSap;
    }

    public Double getQtdRecebido() {
        if (qtdRecebido == null) {
            qtdRecebido = 0.0;
        }
        return qtdRecebido;
    }

    public void setQtdRecebido(Double qtdRecebido) {
        this.qtdRecebido = qtdRecebido;
    }

    public void setQtdRetirado(Double qtdRetirado) {
        this.qtdRetirado = qtdRetirado;
    }

    public Double getQtdRetirado() {
        if (qtdRetirado == null) {
            qtdRetirado = 0.0;
        }
        return qtdRetirado;
    }

    public String getColetorCustos() {
        return coletorCustos;
    }

    public void setColetorCustos(String coletorCustos) {
        this.coletorCustos = coletorCustos;
    }

    public String getEstaNoOrcamento() {
        return estaNoOrcamento;
    }

    public void setEstaNoOrcamento(String estaNoOrcamento) {
        this.estaNoOrcamento = estaNoOrcamento;
    }

    public Double getValorOrcado() {
        return valorOrcado;
    }

    public void setValorOrcado(Double valorOrcado) {
        this.valorOrcado = valorOrcado;
    }

    public String getObservacaoCusto() {
        return observacaoCusto;
    }

    public void setObservacaoCusto(String observacaoCusto) {
        this.observacaoCusto = observacaoCusto;
    }

    public String getFluxoMaterial() {
        return fluxoMaterial;
    }

    public void setFluxoMaterial(String fluxoMaterial) {
        this.fluxoMaterial = fluxoMaterial;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public String getObservacaoEstoquista() {
        return observacaoEstoquista;
    }

    public void setObservacaoEstoquista(String observacaoEstoquista) {
        this.observacaoEstoquista = observacaoEstoquista;
    }

    public Double getQuantidadeOriginal() {
        return quantidadeOriginal;
    }

    public void setQuantidadeOriginal(Double quantidadeOriginal) {
        this.quantidadeOriginal = quantidadeOriginal;
    }

    public String getItemDoPedido() {
        return itemDoPedido;
    }

    public void setItemDoPedido(String itemDoPedido) {
        this.itemDoPedido = itemDoPedido;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(Double valorPedido) {
        this.valorPedido = valorPedido;
    }

    public String getNumeroReserva() {
        return numeroReserva;
    }

    public void setNumeroReserva(String numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getDiagramaRede() {
        return diagramaRede;
    }

    public void setDiagramaRede(String diagramaRede) {
        this.diagramaRede = diagramaRede;
    }

    public Date getDataRecebimentoTotal() {
        return dataRecebimentoTotal;
    }

    public void setDataRecebimentoTotal(Date dataRecebimentoTotal) {
        this.dataRecebimentoTotal = dataRecebimentoTotal;
    }

    public String getLocalEntrega() {
        return localEntrega;
    }

    public void setLocalEntrega(String localEntrega) {
        this.localEntrega = localEntrega;
    }

    public String getColetorOrdem() {
        return coletorOrdem;
    }

    public void setColetorOrdem(String coletorOrdem) {
        this.coletorOrdem = coletorOrdem;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Integer getAgrupadorErro() {
        return agrupadorErro;
    }

    public void setAgrupadorErro(Integer agrupadorErro) {
        this.agrupadorErro = agrupadorErro;
    }

    public Date getDataPrevistaEntrega() {
        return dataPrevistaEntrega;
    }

    public void setDataPrevistaEntrega(Date dataPrevistaEntrega) {
        this.dataPrevistaEntrega = dataPrevistaEntrega;
    }

    public Date getDataUltimaNotaFiscal() {
        return dataUltimaNotaFiscal;
    }

    public void setDataUltimaNotaFiscal(Date dataUltimaNotaFiscal) {
        this.dataUltimaNotaFiscal = dataUltimaNotaFiscal;
    }

    public String getStDataPrevistaEntrega() {
        return stDataPrevistaEntrega;
    }

    public void setStDataPrevistaEntrega(String stDataPrevistaEntrega) {
        this.stDataPrevistaEntrega = stDataPrevistaEntrega;
    }

    public String getCfResponsavel() {
        return cfResponsavel;
    }

    public void setCfResponsavel(String cfResponsavel) {
        this.cfResponsavel = cfResponsavel;
    }

    public String getDocumentoResponsavelImpresso() {
        return documentoResponsavelImpresso;
    }

    public void setDocumentoResponsavelImpresso(String documentoResponsavelImpresso) {
        this.documentoResponsavelImpresso = documentoResponsavelImpresso;
    }

    public String getProtocoloResponsabilidade() {
        return protocoloResponsabilidade;
    }

    public void setProtocoloResponsabilidade(String protocoloResponsabilidade) {
        this.protocoloResponsabilidade = protocoloResponsabilidade;
    }

    public Date getDataRecebimentoSuprimentos() {
        return dataRecebimentoSuprimentos;
    }

    public void setDataRecebimentoSuprimentos(Date dataRecebimentoSuprimentos) {
        this.dataRecebimentoSuprimentos = dataRecebimentoSuprimentos;
    }

    public Date getDataPrevEntregaSuprimentos() {
        return dataPrevEntregaSuprimentos;
    }

    public void setDataPrevEntregaSuprimentos(Date dataPrevEntregaSuprimentos) {
        this.dataPrevEntregaSuprimentos = dataPrevEntregaSuprimentos;
    }

    public String getPrefixoEquipamento() {
        return prefixoEquipamento;
    }

    public void setPrefixoEquipamento(String prefixoEquipamento) {
        this.prefixoEquipamento = prefixoEquipamento;
    }

    public String getJustificativaAlteracaoMaterial() {
        return justificativaAlteracaoMaterial;
    }

    public void setJustificativaAlteracaoMaterial(String justificativaAlteracaoMaterial) {
        this.justificativaAlteracaoMaterial = justificativaAlteracaoMaterial;
    }

    public String getJustificativaAlteracaoQuantidade() {
        return justificativaAlteracaoQuantidade;
    }

    public void setJustificativaAlteracaoQuantidade(String justificativaAlteracaoQuantidade) {
        this.justificativaAlteracaoQuantidade = justificativaAlteracaoQuantidade;
    }

    public Boolean getColetorEstoque() {
        return coletorEstoque;
    }

    public void setColetorEstoque(Boolean coletorEstoque) {
        this.coletorEstoque = coletorEstoque;
    }

    public Date getDataFinalizacaoServico() {
        return dataFinalizacaoServico;
    }

    public void setDataFinalizacaoServico(Date dataFinalizacaoServico) {
        this.dataFinalizacaoServico = dataFinalizacaoServico;
    }

    public Date getDataAtribuirComprador() {
        return dataAtribuirComprador;
    }

    public void setDataAtribuirComprador(Date dataAtribuirComprador) {
        this.dataAtribuirComprador = dataAtribuirComprador;
    }

    public Date getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(Date dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public String getObservacaoCancelamento() {
        return observacaoCancelamento;
    }

    public void setObservacaoCancelamento(String observacaoCancelamento) {
        this.observacaoCancelamento = observacaoCancelamento;
    }

    public Boolean getIsRecSap() {
        return isRecSap;
    }

    public void setIsRecSap(Boolean isRecSap) {
        this.isRecSap = isRecSap;
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
    
    public Boolean getConfirmaRetNaoPresencial() {
        return confirmaRetNaoPresencial;
    }

    public void setConfirmaRetNaoPresencial(Boolean confirmaRetNaoPresencial) {
        this.confirmaRetNaoPresencial = confirmaRetNaoPresencial;
    }

    public Boolean getPossuiAnexoMaterial() {
        return possuiAnexoMaterial;
    }

    public void setPossuiAnexoMaterial(Boolean possuiAnexoMaterial) {
        this.possuiAnexoMaterial = possuiAnexoMaterial;
    }

    public Boolean getPossuiAnexoRm() {
        return possuiAnexoRm;
    }

    public void setPossuiAnexoRm(Boolean possuiAnexoRm) {
        this.possuiAnexoRm = possuiAnexoRm;
    }

    public Boolean getConcluidaComprador() {
        return concluidaComprador;
    }

    public void setConcluidaComprador(Boolean concluidaComprador) {
        this.concluidaComprador = concluidaComprador;
    }

    public String getCodMaterialAnterior() {
        return codMaterialAnterior;
    }

    public void setCodMaterialAnterior(String codMaterialAnterior) {
        this.codMaterialAnterior = codMaterialAnterior;
    }

    public Pessoa getPessoaResponsavel() {
        return pessoaResponsavel;
    }

    public void setPessoaResponsavel(Pessoa pessoaResponsavel) {
        this.pessoaResponsavel = pessoaResponsavel;
    }
    
    public String getGrupoComprador() {
		return grupoComprador;
	}

	public void setGrupoComprador(String grupoComprador) {
		this.grupoComprador = grupoComprador;
	}

    public String getJustificativaReprovacao() {
        return justificativaReprovacao;
    }

    public void setJustificativaReprovacao(String justificativaReprovacao) {
        this.justificativaReprovacao = justificativaReprovacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rmMaterialId != null ? rmMaterialId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RmMaterial)) {
            return false;
        }
        RmMaterial other = (RmMaterial) object;
        return !((this.rmMaterialId == null && other.rmMaterialId != null) || (this.rmMaterialId != null && !this.rmMaterialId.equals(other.rmMaterialId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.RmMaterial[rmMaterialId=" + rmMaterialId + "]";
    }
}
