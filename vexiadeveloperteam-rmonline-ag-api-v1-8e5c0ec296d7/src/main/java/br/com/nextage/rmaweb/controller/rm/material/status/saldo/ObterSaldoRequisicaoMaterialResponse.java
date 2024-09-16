package br.com.nextage.rmaweb.controller.rm.material.status.saldo;

import java.util.Objects;

public class ObterSaldoRequisicaoMaterialResponse {
    private Integer idRmMaterial;
    private Integer quantidadeSolicitada;
    private Integer quantidadeComprada;
    private Integer quantidadeRecebida;
    private Integer saldoAReceber;
    private String rmMaterialNumeroDocEntrada;

    public Integer getIdRmMaterial() {
        return idRmMaterial;
    }

    public void setIdRmMaterial(Integer idRmMaterial) {
        this.idRmMaterial = idRmMaterial;
    }

    public Integer getQuantidadeSolicitada() {
        return quantidadeSolicitada;
    }

    public void setQuantidadeSolicitada(Integer quantidadeSolicitada) {
        this.quantidadeSolicitada = quantidadeSolicitada;
    }

    public Integer getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public void setQuantidadeComprada(Integer quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }

    public Integer getQuantidadeRecebida() {
        return quantidadeRecebida;
    }

    public void setQuantidadeRecebida(Integer quantidadeRecebida) {
        this.quantidadeRecebida = quantidadeRecebida;
    }

    public Integer getSaldoAReceber() {
        return quantidadeSolicitada - quantidadeRecebida;
    }

	public void setRmMaterialNumeroDocEntrada(String rmMaterialNumeroDocEntrada) {
		this.rmMaterialNumeroDocEntrada = rmMaterialNumeroDocEntrada;
	}

	public String getRmMaterialNumeroDocEntrada() {
		return rmMaterialNumeroDocEntrada;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ObterSaldoRequisicaoMaterialResponse that = (ObterSaldoRequisicaoMaterialResponse) o;
        return Objects.equals(idRmMaterial, that.idRmMaterial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRmMaterial);
    }
}
