package br.com.nextage.rmaweb.entitybean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="TB_RM_SERVICO")
@NamedQueries({
	@NamedQuery(name="rmServico.getById",query="select a from RmServico a where a.rmServicoId = :id")
})
public class RmServico implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	public static final String ALIAS_CLASSE = "servico";
    public static final String ALIAS_CLASSE_UNDERLINE = "servico_";
    
    public static final String ORDEM = "ordem";
    public static final String QUANTIDADE = "quantidade";
    public static final String RM = "rm";
    public static final String RM_SERVICO_ID = "rmServicoId";
    public static final String OPCOES_NAT_OPERACAO = "opcoesNatOperacao";
    public static final String OPCOES_TP_SOLICITACAO = "opcoesTpSolicitacao";
    public static final String NUMERO_ACOMPANHAMENTO = "numeroAcompanhamento";
    public static final String DATA_EMISSAO = "dataEmissao";
    public static final String DATA_NECESSIDADE = "dataNecessidade";
    public static final String ELEMENTO_PEP = "elementoPep";
    public static final String CODIGO_SAP = "codigoSap";
    public static final String ESCOPO_SERVICO = "escopoServico";
    public static final String ABRANGENCIA_ESCOPO = "abrangenciaEscopo";
    public static final String LOCAL_PRESTACAO_SERVICO = "localPrestacaoServico";
    public static final String ATESTACAO_EMPRESA = "atestacaoEmpresa";
    public static final String CURRICULO_COLABORADORES = "curriculoColaboradores";
    public static final String CERTIFICADOS_EXIGIDOS = "certificadosExigidos";
    public static final String PARTICULARIDADES_SERVICOS_OUTROS = "particularidadesServicosOutros";
    public static final String ALOJAMENTO = "alojamento";
    public static final String ALIMENTACAO = "alimentacao";
    public static final String TRANSPORTE = "transporte";
    public static final String CESTA_BASICA = "cestaBasica";
    public static final String PLANO_ODONTOLOGICO = "planoOdontologico";
    public static final String EPIS = "epis";
    public static final String EPCS = "epcs";
    public static final String DESPESAS_VIAGEM = "despesasViagem";
    public static final String ESCRITORIO = "escritorio";
    public static final String MOBILIARIO = "mobiliario";
    public static final String SEGURO = "seguro";
    public static final String GARANTIAS = "garantias";
    public static final String VEICULOS = "veiculos";
    public static final String IMPOSTOS = "impostos";
    public static final String BANHEIRO_QUIMICO = "banheiroQuimico";
    public static final String ENERGIA = "energia";
    public static final String AGUA = "agua";
    public static final String EXAMES_ADMISSIONAIS = "examesAdmissionais";
    public static final String CRACHA_UNIFORME = "crachaUniforme";
    public static final String GUINDASTE = "guindaste";
    public static final String ANDAIME = "andaime";
    public static final String PEQUENO_PORTE = " pequenoPorte";
    public static final String RESPONSABILIDADE_OBSERVACOES = "responsabilidadeObservacoes";
    public static final String DATA_INICIO = "dataInicio";
    public static final String DATA_TERMINO = "dataTermino";
    public static final String PRAZO_TOTAL = "prazoTotal";
    public static final String MARCOS_PARCIAIS = "marcosParciais";
    public static final String JORNADA_TRABALHO = "jornadaTrabalho";
    public static final String RESPONSABILIDADES_OUTROS1 = "responsabilidadesOutros1";
    public static final String RESPONSABILIDADES_OUTROS2 = "responsabilidadesOutros2";
    public static final String PERMITIDO_TRABALHO_NOTURNO = "permitidoHorarioNoturno";
    public static final String PERMITIDO_TRABALHO_HORARIO_EXTRAORDINARIO = "permitidoTrabalhoHorarioExtraordinario";
    public static final String PERMITIDO_TRABALHO_DIAS_ESPECIAIS = "permitidoTrabalhoDiasEspeciais";
    public static final String CONDICAO_TRABALHO_OBSERVACAO = "condicaoTrabalhoObservacao";
    public static final String EXAMES_NECESSARIOS = "examesNecessarios";
    public static final String PRAZO_INTEGRACAO = "prazoIntegracao";
    public static final String TEMPO_MEDIO_ESPERA_MATERIAIS = "tempoMedioEsperaMateriais";
    public static final String TEMPO_MEDIO_ESPERA_PESSOAL = "tempoMedioEsperaPessoal";
    
    public static final String ANEXO1_OBS = "anexo1";
    public static final String ANEXO2_OBS = "anexo2";
    public static final String ANEXO3_OBS = "anexo3";
    public static final String ANEXO4_OBS = "anexo4";
    public static final String ANEXO5_OBS = "anexo5";
    public static final String ANEXO_OBSERVACOES = "anexoObservacoes";
    
    public static final String ENV_SAP = "enviadoSap";
    public static final String NUMERO_REQ_SAP = "numeroReqSap";
    
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "GEN_TB_SERVICO_ID", allocationSize = 1)
    @Column(name = "RM_SERVICO_ID")
    private Integer rmServicoId;
	
	@Basic(optional = false)
	@Column(name = "QUANTIDADE")
	private Double quantidade;
	
	@Basic(optional = false)
	@Column(name = "ORDEM")
	private int ordem;
	      
	@Column(name="OPCOES_NAT_OPERACAO")
	private String opcoesNatOperacao;
	
	@Column(name="OPCOES_TP_SOLICITACAO")
	private String opcoesTpSolicitacao;
	
	@Column(name="NUMERO_ACOMPANHAMENTO")
	private String numeroAcompanhamento;
	
	@Column(name="DATA_EMISSAO")
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;
	
	@Column(name="ELEMENTO_PEP")
	private String elementoPep;
	
	@Column(name="ESCOPO_SERVICO")
	private String escopoServico;
	
	@Column(name="ABRANGENCIA_ESCOPO")
	private String abrangenciaEscopo;
	
	@Column(name="LOCAL_PRESTACAO_SERVICO")
	private String localPrestacaoServico;
	
	@Column(name="ATESTACAO_EMPRESA")
	private String atestacaoEmpresa;
	
	@Column(name="CURRICULO_COLABORADORES")
	private String curriculoColaboradores;
	
	@Column(name="CERTIFICADOS_EXIGIDOS")
	private String certificadosExigidos;
	
	@Column(name="PARTICULARIDADES_SERVICOS_OUTROS")
	private String particularidadesServicosOutros;
	
	@Column(name="ALOJAMENTO")
	private String alojamento;
	
	@Column(name="CODIGO_SAP")
	private String codigoSap;
	
	@Column(name="ALIMENTACAO")
	private String alimentacao;
	
	@Column(name="TRANSPORTE")
	private String transporte;
	
	@Column(name="CESTA_BASICA")
	private String cestaBasica;
	
	@Column(name="PLANO_ODONTOLOGICO")
	private String planoOdontologico;
	
	@Column(name="EPIS")
	private String epis;
	
	@Column(name="EPCS")
	private String epcs;
	
	@Column(name="DESPESAS_VIAGEM")
	private String despesasViagem;
	
	@Column(name="ESCRITORIO")
	private String escritorio;
	
	@Column(name="MOBILIARIO")
	private String mobiliario;
	
	@Column(name="SEGURO")
	private String seguro;
	
	@Column(name="GARANTIAS")
	private String garantias;
	
	@Column(name="VEICULOS")
	private String veiculos;
	
	@Column(name="IMPOSTOS")
	private String impostos;
	
	@Column(name="RESPONSABILIDADES_OUTROS1")
	private String responsabilidadesOutros1;
	
	@Column(name="RESPONSABILIDADES_OUTROS2")
	private String responsabilidadesOutros2;
	
	@Column(name="BANHEIRO_QUIMICO")
	private String banheiroQuimico;
	
	@Column(name="ENERGIA")
	private String energia;
	
	@Column(name="AGUA")
	private String agua;
	
	@Column(name="EXAMES_ADMISSIONAIS")
	private String examesAdmissionais;
	
	@Column(name="CRACHA_UNIFORME")
	private String crachaUniforme;
	
	@Column(name="GUINDASTE")
	private String guindaste;
	
	@Column(name="ANDAIME")
	private String andaime;
	
	@Column(name="PEQUENO_PORTE")
	private String pequenoPorte;
	
	@Column(name="RESPONSABILIDADE_OBSERVACOES")
	private String responsabilidadeObservacoes;
	
	@Column(name="DATA_INICIO")
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	
	@Column(name="DATA_NECESSIDADE")
	@Temporal(TemporalType.DATE)
	private Date dataNecessidade;
	
	@Column(name="DATA_TERMINO")
	@Temporal(TemporalType.DATE)
	private Date dataTermino;
	
	@Column(name="PRAZO_TOTAL")
	private String prazoTotal;
	
	@Column(name="MARCOS_PARCIAIS")
	private String marcosParciais;
	
	@Column(name="JORNADA_TRABALHO")
	private String jornadaTrabalho;
	
	@Column(name="PERMITIDO_TRABALHO_NOTURNO")
	private String permitidoTrabalhoNoturno;
	
	@Column(name="PERMITIDO_TRABALHO_HORARIO_EXTRAORDINARIO")
	private String permitidoTrabalhoHorarioExtraordinario;
	
	@Column(name="PERMITIDO_TRABALHO_DIAS_ESPECIAIS")
	private String permitidoTrabalhoDiasEspeciais;
	
	@Column(name="CONDICAO_TRABALHO_OBSERVACAO")
	private String condicaoTrabalhoObservacao;
	
	@Column(name="EXAMES_NECESSARIOS")
	private String examesNecessarios;
	
	@Column(name="PRAZO_INTEGRACAO")
	private String prazoIntegracao;
	
	@Column(name="TEMPO_MEDIO_ESPERA_PESSOAL")
	private String tempoMedioEsperaPessoal;
	
	@Column(name="TEMPO_MEDIO_ESPERA_MATERIAIS")
	private String tempoMedioEsperaMateriais;
	
	
	
	@Column(name="ANEXO1_OBS")
	private String anexo1;
	
	@Column(name="ANEXO2_OBS")
	private String anexo2;
	
	@Column(name="ANEXO3_OBS")
	private String anexo3;
	
	@Column(name="ANEXO4_OBS")
	private String anexo4;
	
	@Column(name="ANEXO5_OBS")
	private String anexo5;
	
	@Column(name="ANEXO_OBSERVACOES")
	private String anexoObservacoes;
	
	@Column(name="ENV_SAP")
	private String enviadoSap;
	
	@Column(name="NUMERO_REQ_SAP")
	private String numeroReqSap;
	
	@JoinColumn(name = "RM_ID", referencedColumnName = "RM_ID")
    @OneToOne(optional = false)
    private Rm rm;
	
	/*
   @JoinColumn(name = "CODIGO_SAP", referencedColumnName = "CODIGO")
   @ManyToOne(optional = false)
   private CodigoSap CodigoSap;*/
	
	@Transient
    private String stDataInicio;
	
	@Transient
    private String stDataEmissao;
	
	@Transient
    private String stDataTermino;

	@Transient
    private int idCodigoSap;
	
	@Transient
	private List<FornecedorServico> fornecedores;
	
	public RmServico() {
		
	}
	
	public RmServico(Integer rmServicoId) {
        this.rmServicoId = rmServicoId;
    }
	
	 public RmServico(Integer rmServicoId, Double quantidade, int ordem) {
	        quantidade = getQuantidade();
	        ordem = getOrdem();
	        rm = getRm();
	}
	 
	public RmServico(RmServico servico) {
		
		rm = servico.getRm();
		this.rmServicoId = servico.getRmServicoId();
        this.quantidade = servico.getQuantidade();
        this.ordem = servico.getOrdem();
        this.opcoesNatOperacao = servico.getOpcoesNatOperacao();
        this.opcoesTpSolicitacao = servico.getOpcoesTpSolicitacao();
        this.numeroAcompanhamento = servico.getNumeroAcompanhamento();
        this.dataEmissao = servico.getDataEmissao();
        this.elementoPep = servico.getElementoPep();
        this.escopoServico = servico.getEscopoServico();
        this.abrangenciaEscopo = servico.getAbrangenciaEscopo();
        this.localPrestacaoServico = servico.getLocalPrestacaoServico();
        this.atestacaoEmpresa = servico.getAtestacaoEmpresa();
        this.curriculoColaboradores = servico.getCurriculoColaboradores();
        this.certificadosExigidos = servico.getCertificadosExigidos();
        this.particularidadesServicosOutros = servico.getParticularidadesServicosOutros();
        this.responsabilidadesOutros1 = servico.getResponsabilidadesOutros1();
        this.responsabilidadesOutros2 = servico.getResponsabilidadesOutros2();
        this.alojamento = servico.getAlojamento();
        this.alimentacao = servico.getAlimentacao();
        this.transporte = servico.getTransporte();
        this.cestaBasica = servico.getCestaBasica();
        this.planoOdontologico = servico.getPlanoOdontologico();
        this.epis = servico.getEpis();
        this.epcs = servico.getEpcs();
        this.codigoSap = servico.getCodigoSap();
        this.despesasViagem = servico.getDespesasViagem();
        this.escritorio = servico.getEscritorio();
        this.mobiliario = servico.getMobiliario();
        this.seguro = servico.getSeguro();
        this.garantias = servico.getGarantias();
        this.veiculos = servico.getVeiculos();
        this.impostos = servico.getImpostos();
        this.banheiroQuimico = servico.getBanheiroQuimico();
        this.energia = servico.getEnergia();
        this.agua = servico.getAgua();
        this.examesAdmissionais = servico.getExamesAdmissionais();
        this.crachaUniforme = servico.getCrachaUniforme();
        this.guindaste = servico.getGuindaste();
        this.andaime = servico.getAndaime();
        this.pequenoPorte = servico.getPequenoPorte();
        this.responsabilidadeObservacoes = servico.getResponsabilidadeObservacoes();
        this.dataInicio = servico.getDataInicio();
        this.dataNecessidade= servico.getDataNecessidade();
        this.dataTermino = servico.getDataTermino();
        this.prazoTotal = servico.getPrazoTotal();
        this.marcosParciais = servico.getMarcosParciais();
        this.jornadaTrabalho = servico.getJornadaTrabalho();
        this.permitidoTrabalhoNoturno = servico.getPermitidoTrabalhoNoturno();
        this.permitidoTrabalhoHorarioExtraordinario = servico.getPermitidoTrabalhoHorarioExtraordinario();
        this.permitidoTrabalhoDiasEspeciais = servico.getPermitidoTrabalhoDiasEspeciais();
        this.condicaoTrabalhoObservacao = servico.getCondicaoTrabalhoObservacao();
        this.examesNecessarios = servico.getExamesNecessarios();
        this.prazoIntegracao = servico.getPrazoIntegracao();
        this.tempoMedioEsperaPessoal = servico.getTempoMedioEsperaPessoal();
        this.tempoMedioEsperaMateriais = servico.getTempoMedioEsperaMateriais();  
        this.anexo1 = servico.getAnexo1();
        this.anexo2 = servico.getAnexo2();
        this.anexo3 = servico.getAnexo3();
        this.anexo4 = servico.getAnexo4();
        this.anexo5 = servico.getAnexo5();
        this.anexoObservacoes = servico.getAnexoObservacoes();
        this.enviadoSap = servico.getEnviadoSap();
        this.numeroReqSap = servico.getNumeroReqSap();
	}
	
	 public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }
    
    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
	
	public Rm getRm() {
		return this.rm;
	}
	
	public void setRm(Rm rm) {
		this.rm=rm;
	}
	
	public List<FornecedorServico> getFornecedores(){
		return this.fornecedores;
	}
	
	public void setFornecedores(List<FornecedorServico> fornecedores) {
		this.fornecedores=fornecedores;
	}

	public Integer getRmServicoId() {
		return rmServicoId;
	}

	public void setRmServicoId(Integer rmServicoId) {
		this.rmServicoId = rmServicoId;
	}

	public String getOpcoesNatOperacao() {
		return opcoesNatOperacao;
	}

	public void setOpcoesNatOperacao(String opcoesNatOperacao) {
		this.opcoesNatOperacao = opcoesNatOperacao;
	}

	public String getOpcoesTpSolicitacao() {
		return opcoesTpSolicitacao;
	}

	public void setOpcoesTpSolicitacao(String opcoesTpSolicitacao) {
		this.opcoesTpSolicitacao = opcoesTpSolicitacao;
	}

	public String getNumeroAcompanhamento() {
		return numeroAcompanhamento;
	}

	public void setNumeroAcompanhamento(String numeroAcompanhamento) {
		this.numeroAcompanhamento = numeroAcompanhamento;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getElementoPep() {
		return elementoPep;
	}

	public void setElementoPep(String elementoPep) {
		this.elementoPep = elementoPep;
	}
	
	public String getCodigoSap() {
		return codigoSap;
	}

	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}

	public String getEscopoServico() {
		return escopoServico;
	}

	public void setEscopoServico(String escopoServico) {
		this.escopoServico = escopoServico;
	}

	public String getAbrangenciaEscopo() {
		return abrangenciaEscopo;
	}

	public void setAbrangenciaEscopo(String abrangenciaEscopo) {
		this.abrangenciaEscopo = abrangenciaEscopo;
	}

	
	public String getResponsabilidadesOutros1() {
		return responsabilidadesOutros1;
	}

	public void setResponsabilidadesOutros1(String responsabilidadesOutros1) {
		this.responsabilidadesOutros1 = responsabilidadesOutros1;
	}
	public String getResponsabilidadesOutros2() {
		return responsabilidadesOutros2;
	}

	public void setResponsabilidadesOutros2(String responsabilidadesOutros2) {
		this.responsabilidadesOutros2 = responsabilidadesOutros2;
	}
	public String getLocalPrestacaoServico() {
		return localPrestacaoServico;
	}

	public void setLocalPrestacaoServico(String localPrestacaoServico) {
		this.localPrestacaoServico = localPrestacaoServico;
	}

	public String getAtestacaoEmpresa() {
		return atestacaoEmpresa;
	}

	public void setAtestacaoEmpresa(String atestacaoEmpresa) {
		this.atestacaoEmpresa = atestacaoEmpresa;
	}

	public String getCurriculoColaboradores() {
		return curriculoColaboradores;
	}

	public void setCurriculoColaboradores(String curriculoColaboradores) {
		this.curriculoColaboradores = curriculoColaboradores;
	}

	public String getCertificadosExigidos() {
		return certificadosExigidos;
	}

	public void setCertificadosExigidos(String certificadosExigidos) {
		this.certificadosExigidos = certificadosExigidos;
	}

	public String getParticularidadesServicosOutros() {
		return particularidadesServicosOutros;
	}

	public void setParticularidadesServicosOutros(String particularidadesServicosOutros) {
		this.particularidadesServicosOutros = particularidadesServicosOutros;
	}

	public String getAlojamento() {
		return alojamento;
	}

	public void setAlojamento(String alojamento) {
		this.alojamento = alojamento;
	}

	public String getAlimentacao() {
		return alimentacao;
	}

	public void setAlimentacao(String alimentacao) {
		this.alimentacao = alimentacao;
	}

	public String getTransporte() {
		return transporte;
	}

	public void setTransporte(String transporte) {
		this.transporte =  transporte;
	}

	public String getCestaBasica() {
		return cestaBasica;
	}

	public void setCestaBasica(String cestaBasica) {
		this.cestaBasica = cestaBasica;
	}

	public String getPlanoOdontologico() {
		return planoOdontologico;
	}

	public void setPlanoOdontologico(String planoOdontologico) {
		this.planoOdontologico =  planoOdontologico;
	}

	public String getEpis() {
		return epis;
	}

	public void setEpis(String epis) {
		this.epis = epis;
	}

	public String getEpcs() {
		return epcs;
	}

	public void setEpcs(String epcs) {
		this.epcs = epcs;
	}

	public String getDespesasViagem() {
		return despesasViagem;
	}

	public void setDespesasViagem(String despesasViagem) {
		this.despesasViagem = despesasViagem;
	}

	public String getEscritorio() {
		return escritorio;
	}

	public void setEscritorio(String escritorio) {
		this.escritorio = escritorio;
	}

	public String getMobiliario() {
		return  mobiliario;
	}

	public void setMobiliario(String mobiliario) {
		this. mobiliario = mobiliario;
	}

	public String getSeguro() {
		return  seguro;
	}

	public void setSeguro(String seguro) {
		this.seguro = seguro;
	}

	public String getGarantias() {
		return garantias;
	}

	public void setGarantias(String garantias) {
		this.garantias = garantias;
	}

	public String getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(String veiculos) {
		this.veiculos = veiculos;
	}

	public String getImpostos() {
		return  impostos;
	}

	public void setImpostos(String impostos) {
		this.impostos =  impostos;
	}
	
	public String getBanheiroQuimico() {
		return banheiroQuimico;
	}

	public void setBanheiroQuimico(String banheiroQuimico) {
		this.banheiroQuimico = banheiroQuimico;
	}

	public String getEnergia() {
		return energia;
	}

	public void setEnergia(String energia) {
		this.energia = energia;
	}

	public String getAgua() {
		return agua;
	}

	public void setAgua(String agua) {
		this.agua = agua;
	}

	public String getExamesAdmissionais() {
		return examesAdmissionais;
	}

	public void setExamesAdmissionais(String examesAdmissionais) {
		this.examesAdmissionais = examesAdmissionais;
	}

	public String getCrachaUniforme() {
		return crachaUniforme;
	}

	public void setCrachaUniforme(String crachaUniforme) {
		this.crachaUniforme = crachaUniforme;
	}

	public String getGuindaste() {
		return guindaste;
	}

	public void setGuindaste(String guindaste) {
		this.guindaste = guindaste;
	}

	public String getAndaime() {
		return andaime;
	}

	public void setAndaime(String andaime) {
		this.andaime = andaime;
	}

	public String getPequenoPorte() {
		return  pequenoPorte;
	}

	public void setPequenoPorte(String pequenoPorte) {
		this.pequenoPorte = pequenoPorte;
	}

	public String getResponsabilidadeObservacoes() {
		return responsabilidadeObservacoes;
	}

	public void setResponsabilidadeObservacoes(String responsabilidadeObservacoes) {
		this.responsabilidadeObservacoes = responsabilidadeObservacoes;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public Date getDataNecessidade() {
		return dataNecessidade;
	}

	public void setDataNecessidade(Date dataNecessidade) {
		this.dataNecessidade = dataNecessidade;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getPrazoTotal() {
		return prazoTotal;
	}

	public void setPrazoTotal(String prazoTotal) {
		this.prazoTotal = prazoTotal;
	}

	public String getMarcosParciais() {
		return marcosParciais;
	}

	public void setMarcosParciais(String marcosParciais) {
		this.marcosParciais = marcosParciais;
	}

	public String getJornadaTrabalho() {
		return jornadaTrabalho;
	}

	public void setJornadaTrabalho(String jornadaTrabalho) {
		this.jornadaTrabalho = jornadaTrabalho;
	}

	public String getPermitidoTrabalhoNoturno() {
		return permitidoTrabalhoNoturno;
	}

	public void setPermitidoTrabalhoNoturno(String permitidoTrabalhoNoturno) {
		this.permitidoTrabalhoNoturno = permitidoTrabalhoNoturno;
	}

	public String getPermitidoTrabalhoHorarioExtraordinario() {
		return permitidoTrabalhoHorarioExtraordinario;
	}

	public void setPermitidoTrabalhoHorarioExtraordinario(String permitidoTrabalhoHorarioExtraordinario) {
		this.permitidoTrabalhoHorarioExtraordinario = permitidoTrabalhoHorarioExtraordinario;
	}

	public String getPermitidoTrabalhoDiasEspeciais() {
		return permitidoTrabalhoDiasEspeciais;
	}

	public void setPermitidoTrabalhoDiasEspeciais(String permitidoTrabalhoDiasEspeciais) {
		this.permitidoTrabalhoDiasEspeciais = permitidoTrabalhoDiasEspeciais;
	}

	public String getCondicaoTrabalhoObservacao() {
		return condicaoTrabalhoObservacao;
	}

	public void setCondicaoTrabalhoObservacao(String condicaoTrabalhoObservacao) {
		this.condicaoTrabalhoObservacao = condicaoTrabalhoObservacao;
	}

	public String getExamesNecessarios() {
		return examesNecessarios;
	}

	public void setExamesNecessarios(String examesNecessarios) {
		this.examesNecessarios = examesNecessarios;
	}

	public String getPrazoIntegracao() {
		return prazoIntegracao;
	}

	public void setPrazoIntegracao(String prazoIntegracao) {
		this.prazoIntegracao = prazoIntegracao;
	}

	public String getTempoMedioEsperaPessoal() {
		return tempoMedioEsperaPessoal;
	}

	public void setTempoMedioEsperaPessoal(String tempoMedioEsperaPessoal) {
		this.tempoMedioEsperaPessoal = tempoMedioEsperaPessoal;
	}

	public String getTempoMedioEsperaMateriais() {
		return tempoMedioEsperaMateriais;
	}

	public void setTempoMedioEsperaMateriais(String tempoMedioEsperaMateriais) {
		this.tempoMedioEsperaMateriais = tempoMedioEsperaMateriais;
	}
	
	public String getStDataInicio() {
		return stDataInicio;
	}

	public void setStDataInicio(String stDataInicio) {
		this.stDataInicio = stDataInicio;
	}
	
	public String getStDataEmissao() {
		return stDataEmissao;
	}

	public void setStDataEmissao(String stDataEmissao) {
		this.stDataEmissao = stDataEmissao;
	}
	
	public String getStDataTermino() {
		return stDataTermino;
	}

	public void setStDataTermino(String stDataTermino) {
		this.stDataTermino = stDataTermino;
	}
	
	//HM -> Trazer ID codigo SAP
	public int getIdCodigoSap() {
		return idCodigoSap;
	}

	public void setIdCodigoSap(int idCodigoSap) {
		this.idCodigoSap = idCodigoSap;
	}
	
	
	public String getAnexo1() {
		return anexo1;
	}

	public void setAnexo1(String anexo1) {
		this.anexo1 = anexo1;
	}
	public String getAnexo2() {
		return anexo2;
	}

	public void setAnexo2(String anexo2) {
		this.anexo2 = anexo2;
	}
	
	public String getAnexo3() {
		return anexo3;
	}

	public void setAnexo3(String anexo3) {
		this.anexo3 = anexo3;
	}
	public String getAnexo4() {
		return anexo4;
	}

	public void setAnexo4(String anexo4) {
		this.anexo4 = anexo4;
	}
	
	public String getAnexo5() {
		return anexo5;
	}

	public void setAnexo5(String anexo5) {
		this.anexo5 = anexo5;
	}
	
	public String getAnexoObservacoes() {
		return anexoObservacoes;
	}

	public void setAnexoObservacoes(String anexoObservacoes) {
		this.anexoObservacoes = anexoObservacoes;
	}
	
	public String getEnviadoSap() {
		return enviadoSap;
	}

	public void setEnviadoSap(String enviadoSap) {
		this.enviadoSap = enviadoSap;
	}

	public String getNumeroReqSap() {
		return numeroReqSap;
	}

	public void setNumeroReqSap(String numeroReqSap) {
		this.numeroReqSap = numeroReqSap;
	}
	
	/*
	public CodigoSap getCodigoSap() {
        return codigoSap;
    }

    public void setCodigoSap(CodigoSap codigoSap) {
        this.codigoSap = codigoSap;
    }
	*/
	 @Override
	public int hashCode() {
	        int hash = 0;
	        hash += (rmServicoId != null ? rmServicoId.hashCode() : 0);
	        return hash;
	}
	 
	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RmServico)) {
            return false;
        }
        RmServico other = (RmServico) object;
        return !((this.rmServicoId == null && other.rmServicoId != null) || (this.rmServicoId != null && !this.rmServicoId.equals(other.rmServicoId)));
    }

    @Override
    public String toString() {
        return "br.com.nextage.rmaweb.entitybean.RmServico[rmServicoId=" + rmServicoId + "]";
    }
	
}
