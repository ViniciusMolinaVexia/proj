package br.com.nextage.rmaweb.controller.painelRequisicaoMaterial;

import java.util.ArrayList;
import java.util.List;

public class EnviarReqMaterialSapResponse {

    public EnviarReqMaterialSapResponse() {
        itens = new ArrayList<>();
    }

    public Integer quantidadeApontamentos;

    private List<ItemReqMaterialSapResponse> itens;

    public List<ItemReqMaterialSapResponse> getItens() {
        return itens;
    }

    public void setItens(List<ItemReqMaterialSapResponse> itens) {
        this.itens = itens;
    }

    public Integer getQuantidadeApontamentos() {
        return quantidadeApontamentos;
    }

    public void setQuantidadeApontamentos(Integer quantidadeApontamentos) {
        this.quantidadeApontamentos = quantidadeApontamentos;
    }

    public Integer contarItensComApostamentos() {
        quantidadeApontamentos = (int) itens.stream().filter(item -> item.getTipoMensagem().equals("W") || item.getTipoMensagem().equals("E") || item.getTipoMensagem().equals("") || item.getTipoMensagem().equals(null)).count();
        return quantidadeApontamentos;
    }
}
