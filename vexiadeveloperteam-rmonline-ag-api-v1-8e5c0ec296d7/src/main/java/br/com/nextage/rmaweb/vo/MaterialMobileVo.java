package br.com.nextage.rmaweb.vo;

import br.com.nextage.rmaweb.entitybean.Material;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

/**
 * @brief Classe MaterialMobileVo
 * @author Alyson X. Leite <a.leite@nextage.com.br>
 * @date 12/01/2016
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaterialMobileVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer materialId;
    private String codigo;
    private String nome;
    private Integer id;
    private Double quantidade;
    private PessoaMobileVo pessoa;
    private String tipoMaterial;
    private String epi;
    private String unidadeMedida;

    public MaterialMobileVo() {
    }

    public MaterialMobileVo(Material material) {
        if (material != null) {
            this.materialId = material.getMaterialId();
            this.codigo = material.getCodigo();
            this.nome = material.getNome();
            if (material.getTipoMaterial() != null) {
                this.tipoMaterial = material.getTipoMaterial().getCodigo();
            }
            if (material.getUnidadeMedida() != null) {
                this.unidadeMedida = material.getUnidadeMedida().getSigla();
            }
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PessoaMobileVo getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaMobileVo pessoa) {
        this.pessoa = pessoa;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public String getEpi() {
        return epi;
    }

    public void setEpi(String epi) {
        this.epi = epi;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

}
