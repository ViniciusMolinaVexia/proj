package br.com.nextage.rmaweb.filtro;

import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.util.filter.FiltroGeral;

public class FiltroDocumentoResponsabilidadeIndisRec extends FiltroGeral {

    private String numeroRm;
    private Material material;
    private String materialChave;
    private Pessoa requisitante;
    private Pessoa usuarioCadastrante;
    private String stDataEmissaoDe, stDataEmissaoAte;
    private String stDataAplicacaoDe, stDataAplicacaoAte;
    private String exibirImpresso;
    private String protocolo;
    private String tipoMaterial;
    private Deposito deposito;
    private EapMultiEmpresa eapMultiEmpresa;

    private Usuario usuario;

    public String getNumeroRm() {
        return numeroRm;
    }

    public void setNumeroRm(String numeroRm) {
        this.numeroRm = numeroRm;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public String getStDataAplicacaoDe() {
        return stDataAplicacaoDe;
    }

    public void setStDataAplicacaoDe(String stDataAplicacaoDe) {
        this.stDataAplicacaoDe = stDataAplicacaoDe;
    }

    public String getStDataAplicacaoAte() {
        return stDataAplicacaoAte;
    }

    public void setStDataAplicacaoAte(String stDataNecessidadeAte) {
        this.stDataAplicacaoAte = stDataNecessidadeAte;
    }

    public Pessoa getRequisitante() {
        return requisitante;
    }

    public void setRequisitante(Pessoa requisitante) {
        this.requisitante = requisitante;
    }

    public Pessoa getUsuarioCadastrante() {
        return usuarioCadastrante;
    }

    public void setUsuarioCadastrante(Pessoa usuarioCadastrante) {
        this.usuarioCadastrante = usuarioCadastrante;
    }

    public String getExibirImpresso() {
        return exibirImpresso;
    }

    public void setExibirImpresso(String exibirImpresso) {
        this.exibirImpresso = exibirImpresso;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public EapMultiEmpresa getEapMultiEmpresa() {
        return eapMultiEmpresa;
    }

    public void setEapMultiEmpresa(EapMultiEmpresa eapMultiEmpresa) {
        this.eapMultiEmpresa = eapMultiEmpresa;
    }
}
