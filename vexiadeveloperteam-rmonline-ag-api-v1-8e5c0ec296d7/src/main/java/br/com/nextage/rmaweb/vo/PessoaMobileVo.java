package br.com.nextage.rmaweb.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

/**
 * @brief Classe PessoaMobileVo
 * @author Alyson X. Leite <a.leite@nextage.com.br>
 * @date 28/01/2016
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PessoaMobileVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer pessoaId;
    private String nome;
    private String referencia;
    private String cpf;
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Integer pessoaId) {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
