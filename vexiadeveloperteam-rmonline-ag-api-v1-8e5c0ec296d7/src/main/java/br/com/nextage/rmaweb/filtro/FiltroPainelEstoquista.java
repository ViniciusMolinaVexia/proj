package br.com.nextage.rmaweb.filtro;

import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.util.filter.FiltroGeral;

/**
 *
 * @author nextage
 */
public class FiltroPainelEstoquista extends FiltroGeral {

    private String numeroRma;
    private Pessoa solicitante;
    private Material material;
    private String materialChave;
    private Deposito deposito;
    private Status statusItem;
    private String status;
    private String stDataInicio;
    private String stDataFim;
    private String stDataInicioAplicacao;
    private String stDataFimAplicacao;
    private String stDataInicioRecebSolic;
    private String stDataFimRecebSolic;
    private Pessoa cadastrante;
    private String fluxoMaterial;
    private TipoRequisicao tipoRequisicao;
    private String numeroPedidoSap;
    private EapMultiEmpresa eapMultiEmpresa;

    public String getNumeroRma() {
        return numeroRma;
    }

    public void setNumeroRma(String numeroRma) {
        this.numeroRma = numeroRma;
    }

    public Pessoa getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Pessoa solicitante) {
        this.solicitante = solicitante;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getMaterialChave() {
        return materialChave;
    }

    public void setMaterialChave(String materialChave) {
        this.materialChave = materialChave;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public Status getStatusItem() {
        return statusItem;
    }

    public void setStatusItem(Status statusItem) {
        this.statusItem = statusItem;
    }

    public String getStDataInicio() {
        return stDataInicio;
    }

    public void setStDataInicio(String stDataInicio) {
        this.stDataInicio = stDataInicio;
    }

    public String getStDataFim() {
        return stDataFim;
    }

    public void setStDataFim(String stDataFim) {
        this.stDataFim = stDataFim;
    }

    public Pessoa getCadastrante() {
        return cadastrante;
    }

    public void setCadastrante(Pessoa cadastrante) {
        this.cadastrante = cadastrante;
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

    public String getNumeroPedidoSap() {
        return numeroPedidoSap;
    }

    public void setNumeroPedidoSap(String numeroPedidoSap) {
        this.numeroPedidoSap = numeroPedidoSap;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStDataInicioRecebSolic() {
        return stDataInicioRecebSolic;
    }

    public void setStDataInicioRecebSolic(String stDataInicioRecebSolic) {
        this.stDataInicioRecebSolic = stDataInicioRecebSolic;
    }

    public String getStDataFimRecebSolic() {
        return stDataFimRecebSolic;
    }

    public void setStDataFimRecebSolic(String stDataFimRecebSolic) {
        this.stDataFimRecebSolic = stDataFimRecebSolic;
    }

    public String getStDataInicioAplicacao() {
        return stDataInicioAplicacao;
    }

    public void setStDataInicioAplicacao(String stDataInicioAplicacao) {
        this.stDataInicioAplicacao = stDataInicioAplicacao;
    }

    public String getStDataFimAplicacao() {
        return stDataFimAplicacao;
    }

    public void setStDataFimAplicacao(String stDataFimAplicacao) {
        this.stDataFimAplicacao = stDataFimAplicacao;
    }

    public EapMultiEmpresa getEapMultiEmpresa() {
        return eapMultiEmpresa;
    }

    public void setEapMultiEmpresa(EapMultiEmpresa eapMultiEmpresa) {
        this.eapMultiEmpresa = eapMultiEmpresa;
    }
}
