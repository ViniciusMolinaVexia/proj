package br.com.nextage.rmaweb.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @brief Classe RequisicaoMobileVo
 * @author Alyson X. Leite <a.leite@nextage.com.br>
 * @date 12/01/2016
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequisicaoMobileVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private EncarregadoMobileVo encarregado;
    private DepositoMobileVo deposito;
    private EapMultiEmpresaMobileVo eapMultiEmpresa;
    private Date dataAplicacao;
    private Date dataCriacao;
    private String stDataAplicacao;
    private String stDataCriacao;
    private String localEntrega;
    private String periodo;
    private Integer sincronizada;
    private String erro;
    private List<MaterialMobileVo> materiais;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSincronizada() {
        return sincronizada;
    }

    public void setSincronizada(Integer sincronizada) {
        this.sincronizada = sincronizada;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public DepositoMobileVo getDeposito() {
        return deposito;
    }

    public void setDeposito(DepositoMobileVo deposito) {
        this.deposito = deposito;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getStDataCriacao() {
        return stDataCriacao;
    }

    public void setStDataCriacao(String stDataCriacao) {
        this.stDataCriacao = stDataCriacao;
    }

    public EncarregadoMobileVo getEncarregado() {
        return encarregado;
    }

    public void setEncarregado(EncarregadoMobileVo encarregado) {
        this.encarregado = encarregado;
    }

    public Date getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(Date dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public String getStDataAplicacao() {
        return stDataAplicacao;
    }

    public void setStDataAplicacao(String stDataAplicacao) {
        this.stDataAplicacao = stDataAplicacao;
    }

    public String getLocalEntrega() {
        return localEntrega;
    }

    public void setLocalEntrega(String localEntrega) {
        this.localEntrega = localEntrega;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public List<MaterialMobileVo> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<MaterialMobileVo> materiais) {
        this.materiais = materiais;
    }

    public EapMultiEmpresaMobileVo getEapMultiEmpresa() {
        return eapMultiEmpresa;
    }

    public void setEapMultiEmpresa(EapMultiEmpresaMobileVo eapMultiEmpresa) {
        this.eapMultiEmpresa = eapMultiEmpresa;
    }
}
