package br.com.nextage.rmaweb.controller.cadastroRmaServico;

public class LinhaRmGerarExcelResponse {

    private String numeroRm;
    private String centro;
    private String area;
    private String prioridade;
    private String codigoMaterial;
    private String nomeMaterial;
    private String unidadeMedida;
    private String qtdeSolicitada;
    private String qtdeComprada;
    private String qtdeRecebida;
    private String saldoReceber;
    private String opcaoDaRequisicao;
    private String observacoes;
    private String observacaoStatus;
    private String status;
    private String usuarioCadastrante;
    private String requisitante;
    private String emissao;
    private String necessidade;
    private String numeroRequisicaoSap;
    private String itemRequisicaoSap;
    private String numeroPedidoCompraSap;
    private String dataCriacaoPedido;
    private String dataPrevistaEntrega;
    private String numeroDocumento;
    private String grupoComprador;
    private String nomeCoordenador;
    private String aprovacaoCoordenador;
    private String nomeGerenteArea;
    private String aprovacaoGerenteArea;
    private String nomeGerenteCustos;
    private String aprovacaoGerenteCustos;
    private String nomeMaquinaParada;
    private String aprovacaoMaquinaParada;
    private String complementoCustos;


    private transient Integer idRm;
    private transient Integer idRmMaterial;

    public String getNumeroRm() {
        return numeroRm;
    }

    public void setNumeroRm(String numeroRm) {
        this.numeroRm = numeroRm;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getCodigoMaterial() {
        return codigoMaterial;
    }

    public void setCodigoMaterial(String codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
    }

    public String getNomeMaterial() {
        return nomeMaterial;
    }

    public void setNomeMaterial(String nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public String getQtdeSolicitada() {
        return qtdeSolicitada;
    }

    public void setQtdeSolicitada(String qtdeSolicitada) {
        this.qtdeSolicitada = qtdeSolicitada;
    }

    public String getQtdeComprada() {
        return qtdeComprada;
    }

    public void setQtdeComprada(String qtdeComprada) {
        this.qtdeComprada = qtdeComprada;
    }

    public String getQtdeRecebida() {
        return qtdeRecebida;
    }

    public void setQtdeRecebida(String qtdeRecebida) {
        this.qtdeRecebida = qtdeRecebida;
    }

    public String getSaldoReceber() {
        return saldoReceber;
    }

    public void setSaldoReceber(String saldoReceber) {
        this.saldoReceber = saldoReceber;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getGrupoComprador() {
        return grupoComprador;
    }

    public void setGrupoComprador(String grupoComprador) {
        this.grupoComprador = grupoComprador;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacaoStatus() {
        return observacaoStatus;
    }

    public void setObservacaoStatus(String observacaoStatus) {
        this.observacaoStatus = observacaoStatus;
    }

    public String getUsuarioCadastrante() {
        return usuarioCadastrante;
    }

    public void setUsuarioCadastrante(String usuarioCadastrante) {
        this.usuarioCadastrante = usuarioCadastrante;
    }

    public String getRequisitante() {
        return requisitante;
    }

    public void setRequisitante(String requisitante) {
        this.requisitante = requisitante;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getEmissao() {
        return emissao;
    }

    public void setEmissao(String emissao) {
        this.emissao = emissao;
    }

    public String getNecessidade() {
        return necessidade;
    }

    public void setNecessidade(String necessidade) {
        this.necessidade = necessidade;
    }

    public String getDataCriacaoPedido() {
        return dataCriacaoPedido;
    }

    public void setDataCriacaoPedido(String dataCriacaoPedido) {
        this.dataCriacaoPedido = dataCriacaoPedido;
    }

    public String getNumeroRequisicaoSap() {
        return numeroRequisicaoSap;
    }

    public void setNumeroRequisicaoSap(String numeroRequisicaoSap) {
        this.numeroRequisicaoSap = numeroRequisicaoSap;
    }

    public String getItemRequisicaoSap() {
        return itemRequisicaoSap;
    }

    public void setItemRequisicaoSap(String itemRequisicaoSap) {
        this.itemRequisicaoSap = itemRequisicaoSap;
    }

    public String getNumeroPedidoCompraSap() {
        return numeroPedidoCompraSap;
    }

    public void setNumeroPedidoCompraSap(String numeroPedidoCompraSap) {
        this.numeroPedidoCompraSap = numeroPedidoCompraSap;
    }

    public String getDataPrevistaEntrega() {
        return dataPrevistaEntrega;
    }

    public void setDataPrevistaEntrega(String dataPrevistaEntrega) {
        this.dataPrevistaEntrega = dataPrevistaEntrega;
    }

    public String getAprovacaoGerenteArea() {
        return aprovacaoGerenteArea;
    }

    public void setAprovacaoGerenteArea(String aprovacaoGerenteArea) {
        this.aprovacaoGerenteArea = aprovacaoGerenteArea;
    }

    public String getAprovacaoGerenteCustos() {
        return aprovacaoGerenteCustos;
    }

    public void setAprovacaoGerenteCustos(String aprovacaoGerenteCustos) {
        this.aprovacaoGerenteCustos = aprovacaoGerenteCustos;
    }

    public String getAprovacaoCoordenador() {
        return aprovacaoCoordenador;
    }

    public void setAprovacaoCoordenador(String aprovacaoCoordenador) {
        this.aprovacaoCoordenador = aprovacaoCoordenador;
    }

    public String getComplementoCustos() {
        return complementoCustos;
    }

    public void setComplementoCustos(String complementoCustos) {
        this.complementoCustos = complementoCustos;
    }

    public String getOpcaoDaRequisicao() {
        return opcaoDaRequisicao;
    }

    public void setOpcaoDaRequisicao(String opcaoDaRequisicao) {
        this.opcaoDaRequisicao = opcaoDaRequisicao;
    }

    public String getAprovacaoMaquinaParada() {
        return aprovacaoMaquinaParada;
    }

    public void setAprovacaoMaquinaParada(String aprovacaoMaquinaParada) {
        this.aprovacaoMaquinaParada = aprovacaoMaquinaParada;
    }

    public String getNomeCoordenador() {
        return nomeCoordenador;
    }

    public void setNomeCoordenador(String nomeCoordenador) {
        this.nomeCoordenador = nomeCoordenador;
    }

    public String getNomeGerenteArea() {
        return nomeGerenteArea;
    }

    public void setNomeGerenteArea(String nomeGerenteArea) {
        this.nomeGerenteArea = nomeGerenteArea;
    }

    public String getNomeGerenteCustos() {
        return nomeGerenteCustos;
    }

    public void setNomeGerenteCustos(String nomeGerenteCustos) {
        this.nomeGerenteCustos = nomeGerenteCustos;
    }

    public String getNomeMaquinaParada() {
        return nomeMaquinaParada;
    }

    public void setNomeMaquinaParada(String nomeMaquinaParada) {
        this.nomeMaquinaParada = nomeMaquinaParada;
    }

    public Integer getIdRm() {
        return idRm;
    }

    public void setIdRm(Integer idRm) {
        this.idRm = idRm;
    }

    public Integer getIdRmMaterial() {
        return idRmMaterial;
    }

    public void setIdRmMaterial(Integer idRmMaterial) {
        this.idRmMaterial = idRmMaterial;
    }
}
