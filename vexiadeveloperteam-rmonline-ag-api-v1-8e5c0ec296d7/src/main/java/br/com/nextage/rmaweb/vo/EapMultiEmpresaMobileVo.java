package br.com.nextage.rmaweb.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @brief Classe DepositoMobileVo
 * @author Jerry
 * @date 19/08/2016
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EapMultiEmpresaMobileVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer eapMultiEmpresaId;
    private String descricao;
    private String codigo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEapMultiEmpresaId() {
        return eapMultiEmpresaId;
    }

    public void setEapMultiEmpresaId(Integer eapMultiEmpresaId) {
        this.eapMultiEmpresaId = eapMultiEmpresaId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
