package br.com.nextage.rmaweb.vo;

import br.com.nextage.rmaweb.entitybean.RmMaterialRecebimento;

/**
 * @brief Classe RmMaterialRecebimentoVo
 * @author Alyson X. Leite <a.leite@nextage.com.br>
 * @date 09/03/2016
 */
public class RmMaterialRecebimentoVo {

    private RmMaterialRecebimento rmMaterialRecebimento;
    private boolean baixaMaterial;

    public RmMaterialRecebimento getRmMaterialRecebimento() {
        return rmMaterialRecebimento;
    }

    public void setRmMaterialRecebimento(RmMaterialRecebimento rmMaterialRecebimento) {
        this.rmMaterialRecebimento = rmMaterialRecebimento;
    }

    public boolean isBaixaMaterial() {
        return baixaMaterial;
    }

    public void setBaixaMaterial(boolean baixaMaterial) {
        this.baixaMaterial = baixaMaterial;
    }

}
