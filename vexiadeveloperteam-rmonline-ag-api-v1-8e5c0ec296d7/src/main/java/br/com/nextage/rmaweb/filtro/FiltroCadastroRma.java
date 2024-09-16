package br.com.nextage.rmaweb.filtro;

import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.util.filter.FiltroGeral;
import java.util.List;

public class FiltroCadastroRma extends FiltroGeral {

    private String numero;
    private Material material;
    private Pessoa requisitante;
    private Pessoa aprovador;
    private Pessoa usuarioCadastrante;
    private String stDataEmissaoDe, stDataEmissaoAte;
    private String stDataRecebimentoDe, stDataRecebimentoAte;
    private String stDataNecessidadeDe, stDataNecessidadeAte;
    private Status status;
    private RmMaterial rmMaterial;
    private RmServico rmServico;
    private Boolean naoExibirComComprador;
    private String observacao;
    private String fluxoMaterial;
    private TipoRequisicao tipoRequisicao;
    private String materialChave;
    private List<Integer> listaIdRmMaterial;
    private Comprador comprador;
    private Prioridade prioridade;
    private EapMultiEmpresa eapMultiEmpresa;
    private Usuario usuario;
    private Boolean atribuidoComprador;
    private Area area;
    private List<Centro> centros;
    
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public Pessoa getRequisitante() {
		return requisitante;
	}
	public void setRequisitante(Pessoa requisitante) {
		this.requisitante = requisitante;
	}
	public Pessoa getAprovador() {
		return aprovador;
	}
	public void setAprovador(Pessoa aprovador) {
		this.aprovador = aprovador;
	}
	public Pessoa getUsuarioCadastrante() {
		return usuarioCadastrante;
	}
	public void setUsuarioCadastrante(Pessoa usuarioCadastrante) {
		this.usuarioCadastrante = usuarioCadastrante;
	}
	public String getStDataEmissaoDe() {
		return stDataEmissaoDe;
	}
	public void setStDataEmissaoDe(String stDataEmissaoDe) {
		this.stDataEmissaoDe = stDataEmissaoDe;
	}
	public String getStDataEmissaoAte() {
		return stDataEmissaoAte;
	}
	public void setStDataEmissaoAte(String stDataEmissaoAte) {
		this.stDataEmissaoAte = stDataEmissaoAte;
	}
	public String getStDataNecessidadeDe() {
		return stDataNecessidadeDe;
	}
	public void setStDataNecessidadeDe(String stDataNecessidadeDe) {
		this.stDataNecessidadeDe = stDataNecessidadeDe;
	}
	public String getStDataNecessidadeAte() {
		return stDataNecessidadeAte;
	}
	public void setStDataNecessidadeAte(String stDataNecessidadeAte) {
		this.stDataNecessidadeAte = stDataNecessidadeAte;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public RmMaterial getRmMaterial() {
		return rmMaterial;
	}
	public void setRmMaterial(RmMaterial rmMaterial) {
		this.rmMaterial = rmMaterial;
	}
	public RmServico getRmServico() {
		return rmServico;
	}
	public void setRmServico(RmServico rmServico) {
		this.rmServico = rmServico;
	}
	public Boolean getNaoExibirComComprador() {
		return naoExibirComComprador;
	}
	public void setNaoExibirComComprador(Boolean naoExibirComComprador) {
		this.naoExibirComComprador = naoExibirComComprador;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getFluxoMaterial() {
		return fluxoMaterial;
	}
	public void setFluxoMaterial(String fluxoMaterial) {
		this.fluxoMaterial = fluxoMaterial;
	}
	public TipoRequisicao getTipoRequisicao() {
		return tipoRequisicao;
	}
	public void setTipoRequisicao(TipoRequisicao tipoRequisicao) {
		this.tipoRequisicao = tipoRequisicao;
	}
	public String getMaterialChave() {
		return materialChave;
	}
	public void setMaterialChave(String materialChave) {
		this.materialChave = materialChave;
	}
	public List<Integer> getListaIdRmMaterial() {
		return listaIdRmMaterial;
	}
	public void setListaIdRmMaterial(List<Integer> listaIdRmMaterial) {
		this.listaIdRmMaterial = listaIdRmMaterial;
	}
	public Comprador getComprador() {
		return comprador;
	}
	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}
	public Prioridade getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}
	public EapMultiEmpresa getEapMultiEmpresa() {
		return eapMultiEmpresa;
	}
	public void setEapMultiEmpresa(EapMultiEmpresa eapMultiEmpresa) {
		this.eapMultiEmpresa = eapMultiEmpresa;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Boolean getAtribuidoComprador() {
		return atribuidoComprador;
	}
	public void setAtribuidoComprador(Boolean atribuidoComprador) {
		this.atribuidoComprador = atribuidoComprador;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public List<Centro> getCentros() {
		return centros;
	}
	public void setCentros(List<Centro> centros) {
		this.centros = centros;
	}
	public String getStDataRecebimentoDe() {
		return stDataRecebimentoDe;
	}
	public void setStDataRecebimentoDe(String stDataRecebimentoDe) {
		this.stDataRecebimentoDe = stDataRecebimentoDe;
	}
	public String getStDataRecebimentoAte() {
		return stDataRecebimentoAte;
	}
	public void setStDataRecebimentoAte(String stDataRecebimentoAte) {
		this.stDataRecebimentoAte = stDataRecebimentoAte;
	}
	
}
