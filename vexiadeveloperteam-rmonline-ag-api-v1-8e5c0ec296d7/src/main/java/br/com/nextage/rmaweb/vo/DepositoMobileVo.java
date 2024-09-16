package br.com.nextage.rmaweb.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

/**
 * @brief Classe DepositoMobileVo
 * @author Alyson X. Leite <a.leite@nextage.com.br>
 * @date 13/01/2016
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositoMobileVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer depositoId;
    private String nome;
    private String codigo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepositoId() {
        return depositoId;
    }

    public void setDepositoId(Integer depositoId) {
        this.depositoId = depositoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
