package br.com.nextage.rmaweb.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

/**
 * @brief Classe EncarregadoMobileVo
 * @author Alyson X. Leite <a.leite@nextage.com.br>
 * @date 12/01/2016
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EncarregadoMobileVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String pessoaId;
    private String nome;
    private String referencia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(String pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

}
