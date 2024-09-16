package br.com.nextage.rmaweb.entitybean;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author nextage
 */
@Entity
@Table(name = "VW_DOCUMENTO_RESPONSABILIDADE")
public class VwDocumentoResponsabilidade implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "vwDocumentoResponsabilidade";
    public static final String ID = "id";
    public static final String RM_MATERIAL_ID = "rmMaterialId";
    public static final String FLUXO_MATERIAL = "fluxoMaterial";
    public static final String QUANTIDADE = "quantidade";
    public static final String CF_RESPONSAVEL = "cfResponsavel";
    public static final String RM_MATERIAL_STATUS_ID = "rmMaterialStatusId";
    public static final String RM_ID = "rmId";
    public static final String NUMERO_RM = "numeroRm";
    public static final String DATA_EMISSAO = "dataEmissao";
    public static final String DATA_APLICACAO = "dataAplicacao";
    public static final String DIAS_PREVISTOS = "diasPrevistos";
    public static final String MATERIAL_ID = "materialId";
    public static final String STATUS_CODIGO = "statusCodigo";
    public static final String STATUS_NOME = "statusNome";
    public static final String MATERIAL_CODIGO = "materialCodigo";
    public static final String MATERIAL_NOME = "materialNome";
    public static final String USUARIO_NOME = "usuarioNome";
    public static final String USUARIO_REFERENCIA = "usuarioReferencia";
    public static final String REQUISITANTE_NOME = "requisitanteNome";
    public static final String REQUISITANTE_REFERENCIA = "requisitanteReferencia";
    public static final String REQUISITANTE_FUNCAO = "requisitanteFuncao";
    public static final String REQUISITANTE_SETOR = "requisitanteSetor";
    public static final String TIPO_REQUISICAO_CODIGO = "tipoRequisicaoCodigo";
    public static final String MATERIAL_PATRIMONIADO = "materialPatrimoniado";
    public static final String MATERIAL_HIERARQUIA = "materialHierarquia";
    public static final String OBSERVACAO = "observacao";
    public static final String TIPO_MATERIAL_CODIGO = "tipoMaterialCodigo";
    public static final String DATA_APROV_GERENTE_AREA = "dataAprovGerenteArea";
    public static final String DATA_APROV_GERENTE_CUSTOS = "dataAprovGerenteCustos";
    public static final String DATA_APROV_COORDENADOR = "dataAprovCoordenador";
    public static final String DATA_APROV_RESPONSAVEL_RETIRADA_ESTOQUE = "dataAprovResponsavelRetiradaEstoque";
    public static final String DATA_APROV_COMPLEMENTO_CUSTOS = "dataAprovComplementoCustos";
    public static final String CONFIRMAR_RETIRADA = "confirmarRetirada";
    public static final String QTDE_RECEBIDA = "qtdeRecebida";
    public static final String DOCUMENTO_RESPONSAVEL_IMPRESSO = "documentoResponsavelImpresso";
    public static final String PROTOCOLO_RESPONSABILIDADE = "protocoloResponsabilidade";
    public static final String PREFIXO_EQUIPAMENTO = "prefixoEquipamento";
    public static final String TIPO_MATERIAL_DESCRICAO = "tipoMaterialDescricao";
    public static final String UNIDADE_MEDIDA_SIGLA = "unidadeMedidaSigla";
    public static final String UNIDADE_MEDIDA_DESCRICAO = "unidadeMedidaDescricao";
    public static final String NOME_RESPONSAVEL = "nomeResponsavel";
    public static final String REFERENCIA_RESPONSAVEL = "referenciaResponsavel";
    public static final String USUARIO_ID = "usuarioId";
    public static final String REQUISITANTE_ID = "requisitanteId";
    public static final String DEPOSITO_ID = "depositoId";
    public static final String DEPOSITO_NOME = "depositoNome";
    public static final String DEPOSITO_CODIGO = "depositoCodigo";
    public static final String RESPONSAVEL_PESSOA_ID = "responsavelPessoaId";
    public static final String UNIDADE_MEDIDA_ID = "unidadeMedidaId";
    public static final String TIPO_MATERIAL_ID = "tipoMaterialId";
    public static final String RM_EAP_MULTI_EMPRESA_ID = "rmEapMultiEmpresaId";
    public static final String RM_EAP_MULTI_EMPRESA_DESCRICAO = "rmEapMultiEmpresaDescricao";
    public static final String DEPOSITO_EAP_MULTI_EMPRESA_DESCRICAO = "depositoEapMultiEmpresaDescricao";

    //O id desta view é o numero da linha no resultado da consulta,
    //foi feito isso pois o jpa exige um id em uma @Entity
    //NÃO DEVE SER UTILIZADO E NAO DEVE SER GERADO O CONTRUTOR
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "RM_MATERIAL_ID")
    private Integer rmMaterialId;

    @Column(name = "FLUXO_MATERIAL")
    private String fluxoMaterial;

    @Column(name = "CF_RESPONSAVEL")
    private String cfResponsavel;

    @Column(name = "QUANTIDADE")
    private Double quantidade;

    @Column(name = "RM_MATERIAL_STATUS_ID")
    private Integer rmMaterialStatusId;

    @Column(name = "RM_ID")
    private Integer rmId;

    @Column(name = "NUMERO_RM")
    private String numeroRm;

    @Column(name = "DATA_EMISSAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEmissao;

    @Column(name = "DATA_APLICACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAplicacao;

    @Column(name = "MATERIAL_ID")
    private Integer materialId;

    @Column(name = "MATERIAL_PATRIMONIADO")
    private String materialPatrimoniado;

    @Column(name = "MATERIAL_HIERARQUIA")
    private String materialHierarquia;

    @Column(name = "MATERIAL_CODIGO")
    private String materialCodigo;

    @Column(name = "MATERIAL_NOME")
    private String materialNome;

    @Column(name = "STATUS_CODIGO")
    private String statusCodigo;

    @Column(name = "STATUS_NOME")
    private String statusNome;

    @Column(name = "DIAS_PREVISTOS")
    private Integer diasPrevistos;

    @Column(name = "USUARIO_NOME")
    private String usuarioNome;

    @Column(name = "OBSERVACAO")
    private String observacao;

    @Column(name = "USUARIO_REFERENCIA")
    private String usuarioReferencia;

    @Column(name = "REQUISITANTE_NOME")
    private String requisitanteNome;

    @Column(name = "REQUISITANTE_REFERENCIA")
    private String requisitanteReferencia;

    @Column(name = "REQUISITANTE_FUNCAO")
    private String requisitanteFuncao;

    @Column(name = "REQUISITANTE_SETOR")
    private String requisitanteSetor;

    @Column(name = "TIPO_REQUISICAO_CODIGO")
    private String tipoRequisicaoCodigo;

    @Column(name = "TIPO_MATERIAL_CODIGO")
    private String tipoMaterialCodigo;

    @Column(name = "DATA_APROV_GERENTE_CUSTOS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAprovGerenteCustos;

    @Column(name = "DATA_APROV_COORDENADOR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAprovCoordenador;

    @Column(name = "DATA_APROV_RESPONSAVEL_RETIRADA_ESTOQUE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAprovResponsavelRetiradaEstoque;

    @Column(name = "DATA_APROV_COMPLEMENTO_CUSTOS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAprovComplementoCustos;

    @Column(name = "CONFIRMAR_RETIRADA")
    private Boolean confirmarRetirada;

    @Column(name = "QTDE_RECEBIDA")
    private Double qtdeRecebida;

    @Column(name = "DOCUMENTO_RESPONSAVEL_IMPRESSO")
    private String documentoResponsavelImpresso;

    @Column(name = "PROTOCOLO_RESPONSABILIDADE")
    private String protocoloResponsabilidade;

    @Column(name = "PREFIXO_EQUIPAMENTO")
    private String prefixoEquipamento;

    @Column(name = "TIPO_MATERIAL_DESCRICAO")
    private String tipoMaterialDescricao;

    @Column(name = "UNIDADE_MEDIDA_SIGLA")
    private String unidadeMedidaSigla;

    @Column(name = "UNIDADE_MEDIDA_DESCRICAO")
    private String unidadeMedidaDescricao;

    @Column(name = "NOME_RESPONSAVEL")
    private String nomeResponsavel;

    @Column(name = "USUARIO_ID")
    private Integer usuarioId;

    @Column(name = "REQUISITANTE_ID")
    private Integer requisitanteId;

    @Column(name = "DEPOSITO_ID")
    private Integer depositoId;

    @Column(name = "DEPOSITO_NOME")
    private String depositoNome;

    @Column(name = "DEPOSITO_CODIGO")
    private String depositoCodigo;

    @Column(name = "RESPONSAVEL_PESSOA_ID")
    private Integer responsavelPessoaId;

    @Column(name = "UNIDADE_MEDIDA_ID")
    private Integer unidadeMedidaId;

    @Column(name = "TIPO_MATERIAL_ID")
    private Integer tipoMaterialId;

    @Column(name = "RM_EAP_MULTI_EMPRESA_ID")
    private Integer rmEapMultiEmpresaId;

    @Column(name = "RM_EAP_MULTI_EMPRESA_DESCRICAO")
    private String rmEapMultiEmpresaDescricao;

    @Column(name = "DEPOSITO_EAP_MULTI_EMPRESA_DESCRICAO")
    private String depositoEapMultiEmpresaDescricao;

    @Column(name = "REFERENCIA_RESPONSAVEL")
    private String referenciaResponsavel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRmMaterialId() {
        return rmMaterialId;
    }

    public void setRmMaterialId(Integer rmMaterialId) {
        this.rmMaterialId = rmMaterialId;
    }

    public Integer getRmMaterialStatusId() {
        return rmMaterialStatusId;
    }

    public void setRmMaterialStatusId(Integer rmMaterialStatusId) {
        this.rmMaterialStatusId = rmMaterialStatusId;
    }

    public Integer getRmId() {
        return rmId;
    }

    public void setRmId(Integer rmId) {
        this.rmId = rmId;
    }

    public String getNumeroRm() {
        return numeroRm;
    }

    public void setNumeroRm(String numeroRm) {
        this.numeroRm = numeroRm;
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

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
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

    public String getStatusCodigo() {
        return statusCodigo;
    }

    public void setStatusCodigo(String statusCodigo) {
        this.statusCodigo = statusCodigo;
    }

    public Integer getDiasPrevistos() {
        return diasPrevistos;
    }

    public void setDiasPrevistos(Integer diasPrevistos) {
        this.diasPrevistos = diasPrevistos;
    }

    public Date getDataAprovGerenteCustos() {
        return dataAprovGerenteCustos;
    }

    public void setDataAprovGerenteCustos(Date dataAprovGerenteCustos) {
        this.dataAprovGerenteCustos = dataAprovGerenteCustos;
    }

    public Date getDataAprovCoordenador() {
        return dataAprovCoordenador;
    }

    public void setDataAprovCoordenador(Date dataAprovCoordenador) {
        this.dataAprovCoordenador = dataAprovCoordenador;
    }

    public Date getDataAprovResponsavelRetiradaEstoque() {
        return dataAprovResponsavelRetiradaEstoque;
    }

    public void setDataAprovResponsavelRetiradaEstoque(Date dataAprovResponsavelRetiradaEstoque) {
        this.dataAprovResponsavelRetiradaEstoque = dataAprovResponsavelRetiradaEstoque;
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

    public String getFluxoMaterial() {
        return fluxoMaterial;
    }

    public void setFluxoMaterial(String fluxoMaterial) {
        this.fluxoMaterial = fluxoMaterial;
    }

    public String getCfResponsavel() {
        return cfResponsavel;
    }

    public void setCfResponsavel(String cfResponsavel) {
        this.cfResponsavel = cfResponsavel;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public String getUsuarioReferencia() {
        return usuarioReferencia;
    }

    public void setUsuarioReferencia(String usuarioReferencia) {
        this.usuarioReferencia = usuarioReferencia;
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

    public String getTipoRequisicaoCodigo() {
        return tipoRequisicaoCodigo;
    }

    public void setTipoRequisicaoCodigo(String tipoRequisicaoCodigo) {
        this.tipoRequisicaoCodigo = tipoRequisicaoCodigo;
    }

    public String getMaterialPatrimoniado() {
        return materialPatrimoniado;
    }

    public void setMaterialPatrimoniado(String materialPatrimoniado) {
        this.materialPatrimoniado = materialPatrimoniado;
    }

    public String getTipoMaterialCodigo() {
        return tipoMaterialCodigo;
    }

    public void setTipoMaterialCodigo(String tipoMaterialCodigo) {
        this.tipoMaterialCodigo = tipoMaterialCodigo;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getStatusNome() {
        return statusNome;
    }

    public void setStatusNome(String statusNome) {
        this.statusNome = statusNome;
    }

    public String getMaterialHierarquia() {
        return materialHierarquia;
    }

    public void setMaterialHierarquia(String materialHierarquia) {
        this.materialHierarquia = materialHierarquia;
    }

    public String getRequisitanteFuncao() {
        return requisitanteFuncao;
    }

    public void setRequisitanteFuncao(String requisitanteFuncao) {
        this.requisitanteFuncao = requisitanteFuncao;
    }

    public String getRequisitanteSetor() {
        return requisitanteSetor;
    }

    public void setRequisitanteSetor(String requisitanteSetor) {
        this.requisitanteSetor = requisitanteSetor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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

    public String getPrefixoEquipamento() {
        return prefixoEquipamento;
    }

    public void setPrefixoEquipamento(String prefixoEquipamento) {
        this.prefixoEquipamento = prefixoEquipamento;
    }

    public String getTipoMaterialDescricao() {
        return tipoMaterialDescricao;
    }

    public void setTipoMaterialDescricao(String tipoMaterialDescricao) {
        this.tipoMaterialDescricao = tipoMaterialDescricao;
    }

    public String getUnidadeMedidaSigla() {
        return unidadeMedidaSigla;
    }

    public void setUnidadeMedidaSigla(String unidadeMedidaSigla) {
        this.unidadeMedidaSigla = unidadeMedidaSigla;
    }

    public String getUnidadeMedidaDescricao() {
        return unidadeMedidaDescricao;
    }

    public void setUnidadeMedidaDescricao(String unidadeMedidaDescricao) {
        this.unidadeMedidaDescricao = unidadeMedidaDescricao;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getRequisitanteId() {
        return requisitanteId;
    }

    public void setRequisitanteId(Integer requisitanteId) {
        this.requisitanteId = requisitanteId;
    }

    public Integer getDepositoId() {
        return depositoId;
    }

    public void setDepositoId(Integer depositoId) {
        this.depositoId = depositoId;
    }

    public String getDepositoNome() {
        return depositoNome;
    }

    public void setDepositoNome(String depositoNome) {
        this.depositoNome = depositoNome;
    }

    public String getDepositoCodigo() {
        return depositoCodigo;
    }

    public void setDepositoCodigo(String depositoCodigo) {
        this.depositoCodigo = depositoCodigo;
    }

    public Integer getResponsavelPessoaId() {
        return responsavelPessoaId;
    }

    public void setResponsavelPessoaId(Integer responsavelPessoaId) {
        this.responsavelPessoaId = responsavelPessoaId;
    }

    public Integer getUnidadeMedidaId() {
        return unidadeMedidaId;
    }

    public void setUnidadeMedidaId(Integer unidadeMedidaId) {
        this.unidadeMedidaId = unidadeMedidaId;
    }

    public Integer getTipoMaterialId() {
        return tipoMaterialId;
    }

    public void setTipoMaterialId(Integer tipoMaterialId) {
        this.tipoMaterialId = tipoMaterialId;
    }

    public String getRmEapMultiEmpresaDescricao() {
        return rmEapMultiEmpresaDescricao;
    }

    public void setRmEapMultiEmpresaDescricao(String rmEapMultiEmpresaDescricao) {
        this.rmEapMultiEmpresaDescricao = rmEapMultiEmpresaDescricao;
    }

    public String getDepositoEapMultiEmpresaDescricao() {
        return depositoEapMultiEmpresaDescricao;
    }

    public void setDepositoEapMultiEmpresaDescricao(String depositoEapMultiEmpresaDescricao) {
        this.depositoEapMultiEmpresaDescricao = depositoEapMultiEmpresaDescricao;
    }

    public Integer getRmEapMultiEmpresaId() {
        return rmEapMultiEmpresaId;
    }

    public void setRmEapMultiEmpresaId(Integer rmEapMultiEmpresaId) {
        this.rmEapMultiEmpresaId = rmEapMultiEmpresaId;
    }

    public String getReferenciaResponsavel() {
        return referenciaResponsavel;
    }

    public void setReferenciaResponsavel(String referenciaResponsavel) {
        this.referenciaResponsavel = referenciaResponsavel;
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.VwDocumentoResponsabilidade[]";
    }
}
