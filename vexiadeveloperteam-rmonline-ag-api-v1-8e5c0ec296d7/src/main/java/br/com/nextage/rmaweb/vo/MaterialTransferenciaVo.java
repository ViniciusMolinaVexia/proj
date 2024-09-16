package br.com.nextage.rmaweb.vo;

import br.com.nextage.rmaweb.entitybean.Deposito;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.entitybean.MaterialDepositoSaidaReserva;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import java.io.Serializable;

/**
 * @brief Classe MaterialTransferenciaVo
 * @author Alyson X. Leite <a.leite@nextage.com.br>
 * @date 18/01/2016
 */
public class MaterialTransferenciaVo implements Serializable {

    private MaterialDepositoSaidaReserva materialDepositoSaidaReserva;
    private RmMaterial rmMaterial;
    private Material material;
    private Deposito depositoOrigem;
    private Deposito depositoDestino;
    private Double quantidadeSolicitada;
    private Double quantidadeTransferencia;
    private String observacao;
    private String nomeUsuario;

    public MaterialTransferenciaVo() {
    }

    public MaterialTransferenciaVo(RmMaterial rmMaterial) {
        this.rmMaterial = rmMaterial;
        if (rmMaterial != null) {
            this.material = rmMaterial.getMaterial();
            this.depositoOrigem = rmMaterial.getDeposito();
            if (rmMaterial.getQuantidade() != null) {
                this.quantidadeSolicitada = rmMaterial.getQuantidade();
            }
            if (rmMaterial.getQtdRetirado() != null) {
                this.quantidadeTransferencia = rmMaterial.getQtdRetirado();
            }
        }
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Deposito getDepositoOrigem() {
        return depositoOrigem;
    }

    public void setDepositoOrigem(Deposito depositoOrigem) {
        this.depositoOrigem = depositoOrigem;
    }

    public Deposito getDepositoDestino() {
        return depositoDestino;
    }

    public void setDepositoDestino(Deposito depositoDestino) {
        this.depositoDestino = depositoDestino;
    }

    public Double getQuantidadeSolicitada() {
        return quantidadeSolicitada;
    }

    public void setQuantidadeSolicitada(Double quantidadeSolicitada) {
        this.quantidadeSolicitada = quantidadeSolicitada;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public MaterialDepositoSaidaReserva getMaterialDepositoSaidaReserva() {
        return materialDepositoSaidaReserva;
    }

    public void setMaterialDepositoSaidaReserva(MaterialDepositoSaidaReserva materialDepositoSaidaReserva) {
        this.materialDepositoSaidaReserva = materialDepositoSaidaReserva;
    }

    public Double getQuantidadeTransferencia() {
        return quantidadeTransferencia;
    }

    public void setQuantidadeTransferencia(Double quantidadeTransferencia) {
        this.quantidadeTransferencia = quantidadeTransferencia;
    }

    public RmMaterial getRmMaterial() {
        return rmMaterial;
    }

    public void setRmMaterial(RmMaterial rmMaterial) {
        this.rmMaterial = rmMaterial;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
}
