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
@Table(name = "TB_RM")
@NamedQueries({
	@NamedQuery(name="rm.byId",query="select r from Rm r where r.rmId = :id order by dataEmissao")
})
public class Rm implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ALIAS_CLASSE = "rm";
    public static final String ALIAS_CLASSE_UNDERLINE = "rm_";

    public static final String RM_ID = "rmId";
    public static final String NUMERO_RM = "numeroRm";
    public static final String DATA_EMISSAO = "dataEmissao";
    public static final String DATA_APLICACAO = "dataAplicacao";
    public static final String DATA_APROVACAO_COMPRA = "dataAprovacaoCompra";
    public static final String DATA_RECEBIMENTO = "dataRecebimento";
    public static final String DATA_CANCELAMENTO = "dataCancelamento";
    public static final String DATA_ENVIO_COMPRADOR = "dataEnvioComprador";
    public static final String DATA_ACEITE_COMPRADOR = "dataAceiteComprador";
    public static final String DATA_REPROVACAO_COMPRA = "dataReprovacaoCompra";
    public static final String OBSERVACAO = "observacao";
    public static final String COMPRADOR = "comprador";
    public static final String REQUISITANTE = "requisitante";
    public static final String DEPOSITO = "deposito";
    public static final String PRIORIDADE = "prioridade";
    public static final String JUSTIFICATIVA_CANCELAMENTO = "justificativaCancelamento";
    public static final String JUSTIFICATIVA_ENVIO_APROVACAO = "justificativaEnvioAprovacao";
    public static final String JUSTIFICATIVA_GERENTE_OBRA = "justificativaGerenteObra";
    public static final String USUARIO = "usuario";
    public static final String GERENTE_CUSTOS = "gerenteCustos";
    public static final String DATA_ENVIO_APROVACAO = "dataEnvioAprovacao";
    public static final String DATA_REPROVACAO = "dataReprovacao";
    public static final String REPROVADO_POR = "reprovadoPor";
    public static final String LOCAL_ENTREGA = "localEntrega";
    public static final String GERENTE_OBRA = "gerenteObra";
    public static final String JUST_RETORNO_EQUIPE_CUSTOS = "justRetornoEquipeCustos";
    public static final String ATRIBUIDO_PARA = "atribuidoPara";
    public static final String NUMERO_RM_MOBILE = "numeroRmMobile";
    public static final String PERIODO = "periodo";
    public static final String GERENTE_AREA = "gerenteArea";
    public static final String JUST_MATERIAIS_CAUTELADOS = "justMateriaisCautelados";
    public static final String RESPONSAVEL_RETIRADA_ESTOQUE = "responsavelRetiradaEstoque";
    public static final String COORDENADOR = "coordenador";
    public static final String EAP_MULTI_EMPRESA = "eapMultiEmpresa";
    public static final String TIPO_REQUISICAO = "tipoRequisicao";
    public static final String CENTRO = "centro";
    public static final String AREA = "area";
    public static final String TIPO_RM = "tipoRm";
    public static final String NUMERO_RM_SERVICO = "numeroRmServico";

    @Id
    @Basic(optional = false)
    @Column(name = "RM_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_RM_ID", allocationSize = 1)
    private Integer rmId;

    //@Basic(optional = false)
    @Column(name = "NUMERO_RM")
    private String numeroRm;
    
    @Basic(optional = false)
    @Column(name = "NUMERO_RM_SERVICO")
    private String numeroRmServico;

    @Basic(optional = false)
    @Column(name = "DATA_EMISSAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEmissao;

    @Basic(optional = false)
    @Column(name = "DATA_APLICACAO")
    @Temporal(TemporalType.DATE)
    private Date dataAplicacao;

    @Column(name = "DATA_APROVACAO_COMPRA")
    @Temporal(TemporalType.DATE)
    private Date dataAprovacaoCompra;

    @Column(name = "DATA_REPROVACAO_COMPRA")
    @Temporal(TemporalType.DATE)
    private Date dataReprovacaoCompra;

    @Basic(optional = false)
    @Column(name = "DATA_RECEBIMENTO")
    @Temporal(TemporalType.DATE)
    private Date dataRecebimento;

    @Column(name = "DATA_ENVIO_APROVACAO")
    @Temporal(TemporalType.DATE)
    private Date dataEnvioAprovacao;

    @Column(name = "OBSERVACAO", length = 1000)
    private String observacao;

    @Column(name = "DATA_CANCELAMENTO")
    @Temporal(TemporalType.DATE)
    private Date dataCancelamento;

    @Column(name = "DATA_ENVIO_COMPRADOR")
    @Temporal(TemporalType.DATE)
    private Date dataEnvioComprador;

    @Column(name = "DATA_ACEITE_COMPRADOR")
    @Temporal(TemporalType.DATE)
    private Date dataAceiteComprador;

    @JoinColumn(name = "COMPRADOR_ID", referencedColumnName = "COMPRADOR_ID")
    @ManyToOne
    private Comprador comprador;

    @JoinColumn(name = "REQUISITANTE_ID", referencedColumnName = "PESSOA_ID")
    @ManyToOne(optional = false)
    private Pessoa requisitante;

    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne
    private Usuario usuario;

    @JoinColumn(name = "PRIORIDADE_ID", referencedColumnName = "PRIORIDADE_ID")
    @ManyToOne(optional = false)
    private Prioridade prioridade;

    @JoinColumn(name = "DEPOSITO_ID", referencedColumnName = "DEPOSITO_ID")
    @ManyToOne
    private Deposito deposito;

    @Column(name = "JUSTIFICATIVA_CANCELAMENTO", length = 200)
    private String justificativaCancelamento;

    @Basic(optional = true)
    @Column(name = "PERIODO", length = 6)
    private String periodo;

    @Column(name = "JUSTIFICATIVA_ENVIO_APROVACAO", length = 200)
    private String justificativaEnvioAprovacao;

    @JoinColumn(name = "GERENTE_CUSTOS", referencedColumnName = "PESSOA_ID")
    @ManyToOne
    private Pessoa gerenteCustos;

    @Column(name = "DATA_REPROVACAO")
    @Temporal(TemporalType.DATE)
    private Date dataReprovacao;

    @Column(name = "REPROVADO_POR", length = 10)
    private String reprovadoPor;

    @Column(name = "JUSTIFICATIVA_GERENTE_OBRA", length = 200)
    private String justificativaGerenteObra;

    @Column(name = "LOCAL_ENTREGA", length = 200)
    private String localEntrega;

    @Basic(optional = true)
    @Column(name = "NUMERO_RM_MOBILE")
    private Integer numeroRmMobile;

    @JoinColumn(name = "GERENTE_OBRA", referencedColumnName = "PESSOA_ID")
    @ManyToOne
    private Pessoa gerenteObra;

    @Column(name = "JUST_RETORNO_EQUIPE_CUSTOS", length = 300)
    private String justRetornoEquipeCustos;

    @JoinColumn(name = "ATRIBUIDO_PARA", referencedColumnName = "PESSOA_ID")
    @ManyToOne
    private Pessoa atribuidoPara;

    @JoinColumn(name = "GERENTE_AREA", referencedColumnName = "PESSOA_ID")
    @ManyToOne
    private Pessoa gerenteArea;

    @Column(name = "JUST_MATERIAIS_CAUTELADOS", length = 500)
    private String justMateriaisCautelados;

    @Column(name = "TIPO_RM")
    private String tipoRm;
    
    @JoinColumn(name = "RESPONSAVEL_RETIRADA_ESTOQUE", referencedColumnName = "PESSOA_ID")
    @ManyToOne
    private Pessoa responsavelRetiradaEstoque;

    @JoinColumn(name = "COORDENADOR", referencedColumnName = "PESSOA_ID")
    @ManyToOne
    private Pessoa coordenador;

    @JoinColumn(name = "EAP_MULTI_EMPRESA_ID", referencedColumnName = "ID")
    @ManyToOne
    private EapMultiEmpresa eapMultiEmpresa;
    
    @JoinColumn(name = "CENTRO_ID", referencedColumnName = "CENTRO_ID")
    @ManyToOne
    private Centro centro;
    
    @JoinColumn(name = "AREA_ID", referencedColumnName = "AREA_ID")
    @ManyToOne
    private Area area;
    
    @JoinColumn(name = "TIPO_REQUISICAO_ID", referencedColumnName = "TIPO_REQUISICAO_ID")
    @ManyToOne
    private TipoRequisicao tipoRequisicao;
    
    @Transient
    private String stDataAplicacao;

    @Transient
    private String stDataEmissao;

    @Transient
    private String statusCompra;
    
    public Rm() {
    }

    public Rm(Integer rmId) {
        this.rmId = rmId;
    }

    public Rm(Integer rmId, String numeroRm) {
        this.rmId = rmId;
        this.numeroRm = numeroRm;
    }
    
    public Area getArea() {
    	return this.area;
    }
    
    public void setArea(Area area) {
    	this.area=area;
    }
    
    public void setTipoRequisicao(TipoRequisicao tipoRequisicao) {
    	this.tipoRequisicao=tipoRequisicao;
    }
    
    public TipoRequisicao getTipoRequisicao() {
    	return this.tipoRequisicao;
    }

    public void setCentro(Centro centro) {
    	this.centro=centro;
    }
    
    public Centro getCentro() {
    	return this.centro;
    }

    public Pessoa getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Pessoa coordenador) {
        this.coordenador = coordenador;
    }

    public Pessoa getResponsavelRetiradaEstoque() {
        return responsavelRetiradaEstoque;
    }

    public void setResponsavelRetiradaEstoque(Pessoa responsavelRetiradaEstoque) {
        this.responsavelRetiradaEstoque = responsavelRetiradaEstoque;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Integer getNumeroRmMobile() {
        return numeroRmMobile;
    }

    public void setNumeroRmMobile(Integer numeroRmMobile) {
        this.numeroRmMobile = numeroRmMobile;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
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
    
    public String getNumeroRmServico() {
        return numeroRmServico;
    }

    public void setNumeroRmServico(String numeroRmServico) {
        this.numeroRmServico = numeroRmServico;
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

    public Date getDataAprovacaoCompra() {
        return dataAprovacaoCompra;
    }

    public void setDataAprovacaoCompra(Date dataAprovacaoCompra) {
        this.dataAprovacaoCompra = dataAprovacaoCompra;
    }

    public Date getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(Date dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public Date getDataEnvioComprador() {
        return dataEnvioComprador;
    }

    public void setDataEnvioComprador(Date dataEnvioComprador) {
        this.dataEnvioComprador = dataEnvioComprador;
    }

    public Date getDataAceiteComprador() {
        return dataAceiteComprador;
    }

    public void setDataAceiteComprador(Date dataAceiteComprador) {
        this.dataAceiteComprador = dataAceiteComprador;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Pessoa getRequisitante() {
        return requisitante;
    }

    public void setRequisitante(Pessoa requisitante) {
        this.requisitante = requisitante;
    }

    public String getJustificativaCancelamento() {
        return justificativaCancelamento;
    }

    public void setJustificativaCancelamento(String justificativaCancelamento) {
        this.justificativaCancelamento = justificativaCancelamento;
    }
    
    public String getTipoRm() {
        return tipoRm;
    }

    public void setTipoRm(String tipoRm) {
        this.tipoRm = tipoRm;
    }

    public void setStatusCompra(String statusCompra) {
        this.statusCompra = statusCompra;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Date getDataReprovacaoCompra() {
        return dataReprovacaoCompra;
    }

    public void setDataReprovacaoCompra(Date dataReprovacaoCompra) {
        this.dataReprovacaoCompra = dataReprovacaoCompra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getStDataAplicacao() {
        return stDataAplicacao;
    }

    public void setStDataAplicacao(String stDataAplicacao) {
        this.stDataAplicacao = stDataAplicacao;
    }

    public String getStDataEmissao() {
        return stDataEmissao;
    }

    public void setStDataEmissao(String stDataEmissao) {
        this.stDataEmissao = stDataEmissao;
    }

    public String getStatusCompra() {
//        try{
//            if (rmId != null && rmId > 0) {
//                    GenericDao<RmMaterial> genericDao=new GenericDao<RmMaterial>();
//                    List<RmMaterial> lista=new ArrayList<RmMaterial>();
//                    List<Filtro> filtros = new ArrayList<Filtro>();
//
//                    filtros.add(new Filtro(RmMaterial.RM, new Rm(rmId), Filtro.EQUAL));
//                    filtros.add(new Filtro(RmMaterial.DATA_COMPRA, null, Filtro.IS_NULL));
//
//                    // Seta as propriedades de retorno da consulta.
//                    List<Propriedade> propriedades = new ArrayList<Propriedade>();
//                    propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
//
//                    //Obtem elementos.
//                    lista = (ArrayList<RmMaterial>) genericDao.listarByFilter(propriedades, filtros, RmMaterial.ORDEM, false, RmMaterial.class, -1);
//                    if(lista==null || lista.isEmpty()){
//                        statusCompra = Constantes.DATA_COMPRA_RM_MATERIAL_SIM;
//                    }else{
//                        statusCompra = Constantes.DATA_COMPRA_RM_MATERIAL_NAO;
//                    }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
        return statusCompra;
    }

    public Pessoa getGerenteCustos() {
        return gerenteCustos;
    }

    public void setGerenteCustos(Pessoa gerenteCustos) {
        this.gerenteCustos = gerenteCustos;
    }

    public String getJustificativaEnvioAprovacao() {
        return justificativaEnvioAprovacao;
    }

    public void setJustificativaEnvioAprovacao(String justificativaEnvioAprovacao) {
        this.justificativaEnvioAprovacao = justificativaEnvioAprovacao;
    }

    public Date getDataEnvioAprovacao() {
        return dataEnvioAprovacao;
    }

    public void setDataEnvioAprovacao(Date dataEnvioAprovacao) {
        this.dataEnvioAprovacao = dataEnvioAprovacao;
    }

    public Date getDataReprovacao() {
        return dataReprovacao;
    }

    public void setDataReprovacao(Date dataReprovacao) {
        this.dataReprovacao = dataReprovacao;
    }

    public String getReprovadoPor() {
        return reprovadoPor;
    }

    public void setReprovadoPor(String reprovadoPor) {
        this.reprovadoPor = reprovadoPor;
    }

    public String getJustificativaGerenteObra() {
        return justificativaGerenteObra;
    }

    public void setJustificativaGerenteObra(String justificativaGerenteObra) {
        this.justificativaGerenteObra = justificativaGerenteObra;
    }

    public String getLocalEntrega() {
        return localEntrega;
    }

    public void setLocalEntrega(String localEntrega) {
        this.localEntrega = localEntrega;
    }

    public Pessoa getGerenteObra() {
        return gerenteObra;
    }

    public void setGerenteObra(Pessoa gerenteObra) {
        this.gerenteObra = gerenteObra;
    }

    public String getJustRetornoEquipeCustos() {
        return justRetornoEquipeCustos;
    }

    public void setJustRetornoEquipeCustos(String justRetornoEquipeCustos) {
        this.justRetornoEquipeCustos = justRetornoEquipeCustos;
    }

    public Pessoa getAtribuidoPara() {
        return atribuidoPara;
    }

    public void setAtribuidoPara(Pessoa atribuidoPara) {
        this.atribuidoPara = atribuidoPara;
    }

    public Pessoa getGerenteArea() {
        return gerenteArea;
    }

    public void setGerenteArea(Pessoa gerenteArea) {
        this.gerenteArea = gerenteArea;
    }

    public String getJustMateriaisCautelados() {
        return justMateriaisCautelados;
    }

    public void setJustMateriaisCautelados(String justMateriaisCautelados) {
        this.justMateriaisCautelados = justMateriaisCautelados;
    }

    public EapMultiEmpresa getEapMultiEmpresa() {
        return eapMultiEmpresa;
    }

    public void setEapMultiEmpresa(EapMultiEmpresa eapMultiEmpresa) {
        this.eapMultiEmpresa = eapMultiEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rmId != null ? rmId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rm)) {
            return false;
        }
        Rm other = (Rm) object;
        return !((this.rmId == null && other.rmId != null) || (this.rmId != null && !this.rmId.equals(other.rmId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.Rm[rmId=" + rmId + "]";
    }
}