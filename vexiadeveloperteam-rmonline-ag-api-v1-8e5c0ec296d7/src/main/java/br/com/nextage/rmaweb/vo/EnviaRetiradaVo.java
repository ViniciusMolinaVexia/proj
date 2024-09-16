package br.com.nextage.rmaweb.vo;

import br.com.nextage.rmaweb.entitybean.UnidadeMedida;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.entitybean.Deposito;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.util.Info;

/**
 * Created by Lucas Heitor on 03/06/2016.
 */
public class EnviaRetiradaVo {

    public EnviaRetiradaVo() {
    }

    private Material material;
    private Deposito deposito;
    private RmMaterial rmMaterial;
    private Double quantidade;
    private UnidadeMedida unidadeMedida;
    private Boolean recebidoCp;

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public RmMaterial getRmMaterial() {
        return rmMaterial;
    }

    public void setRmMaterial(RmMaterial rmMaterial) {
        this.rmMaterial = rmMaterial;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Boolean getRecebidoCp() {
        return recebidoCp;
    }

    public void setRecebidoCp(Boolean recebidoCp) {
        this.recebidoCp = recebidoCp;
    }
}
