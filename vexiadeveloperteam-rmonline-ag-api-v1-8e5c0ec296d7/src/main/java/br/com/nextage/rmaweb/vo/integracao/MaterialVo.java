package br.com.nextage.rmaweb.vo.integracao;

/**
 *
 * @author l.pordeus
 */
public class MaterialVo {

    private String material;
    private String descricaoBreve;
    private String descricaoLonga;
    private String hierarquia;
    private String grupoMercadoria;
    private String unidadeMedida;
    private String cautela;
    private String patrimoniado;
    private String ncm;
    private String estoqueSap;
    private String status;
    private String ultimaDataAtualizacao;
    private String ultimaHoraAtualizacao;
    private String centro;

    public MaterialVo() {
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescricaoBreve() {
        return descricaoBreve;
    }

    public void setDescricaoBreve(String descricaoBreve) {
        this.descricaoBreve = descricaoBreve;
    }

    public String getDescricaoLonga() {
        return descricaoLonga;
    }

    public void setDescricaoLonga(String descricaoLonga) {
        this.descricaoLonga = descricaoLonga;
    }

    public String getHierarquia() {
        return hierarquia;
    }

    public void setHierarquia(String hierarquia) {
        this.hierarquia = hierarquia;
    }

    public String getGrupoMercadoria() {
        return grupoMercadoria;
    }

    public void setGrupoMercadoria(String grupoMercadoria) {
        this.grupoMercadoria = grupoMercadoria;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public String getCautela() {
        return cautela;
    }

    public void setCautela(String cautela) {
        this.cautela = cautela;
    }

    public String getPatrimoniado() {
        return patrimoniado;
    }

    public void setPatrimoniado(String patrimoniado) {
        this.patrimoniado = patrimoniado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUltimaDataAtualizacao() {
        return ultimaDataAtualizacao;
    }

    public void setUltimaDataAtualizacao(String ultimaDataAtualizacao) {
        this.ultimaDataAtualizacao = ultimaDataAtualizacao;
    }

    public String getUltimaHoraAtualizacao() {
        return ultimaHoraAtualizacao;
    }

    public void setUltimaHoraAtualizacao(String ultimaHoraAtualizacao) {
        this.ultimaHoraAtualizacao = ultimaHoraAtualizacao;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getEstoqueSap() {
        return estoqueSap;
    }

    public void setEstoqueSap(String estoqueSap) {
        this.estoqueSap = estoqueSap;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    @Override
    public String toString() {
        return "MaterialVo{" +
          "material='" + material + '\'' +
          ", descricaoBreve='" + descricaoBreve + '\'' +
          ", descricaoLonga='" + descricaoLonga + '\'' +
          ", hierarquia='" + hierarquia + '\'' +
          ", grupoMercadoria='" + grupoMercadoria + '\'' +
          ", unidadeMedida='" + unidadeMedida + '\'' +
          ", cautela='" + cautela + '\'' +
          ", patrimoniado='" + patrimoniado + '\'' +
          ", ncm='" + ncm + '\'' +
          ", estoqueSap='" + estoqueSap + '\'' +
          ", status='" + status + '\'' +
          ", ultimaDataAtualizacao='" + ultimaDataAtualizacao + '\'' +
          ", ultimaHoraAtualizacao='" + ultimaHoraAtualizacao + '\'' +
          ", centro='" + centro + '\'' +
          '}';
    }
}
