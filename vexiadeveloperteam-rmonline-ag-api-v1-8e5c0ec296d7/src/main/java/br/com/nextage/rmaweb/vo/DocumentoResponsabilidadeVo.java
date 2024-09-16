package br.com.nextage.rmaweb.vo;

import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import java.io.Serializable;
import java.util.List;

/**
 * @brief Classe DocumentoResponsabilidadeVo
 * @author Alyson X. Leite <a.leite@nextage.com.br>
 * @date 05/02/2016
 */
public class DocumentoResponsabilidadeVo implements Serializable {

    public DocumentoResponsabilidadeVo() {
    }

    public DocumentoResponsabilidadeVo(Pessoa pessoa, List<RmMaterial> rmMaterial) {
        this.pessoa = pessoa;
        this.listaRmMaterial = rmMaterial;
    }

    private String protocolo;
    private Pessoa pessoa;
    private List<RmMaterial> listaRmMaterial;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<RmMaterial> getListaRmMaterial() {
        return listaRmMaterial;
    }

    public void setListaRmMaterial(List<RmMaterial> listaRmMaterial) {
        this.listaRmMaterial = listaRmMaterial;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

}
