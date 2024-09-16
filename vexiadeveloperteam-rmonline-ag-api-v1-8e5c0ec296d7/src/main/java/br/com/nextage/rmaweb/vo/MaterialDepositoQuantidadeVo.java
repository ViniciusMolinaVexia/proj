/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.vo;

import br.com.nextage.rmaweb.entitybean.Material;

import java.util.List;

/**
 *
 * @author nextage
 */
public class MaterialDepositoQuantidadeVo {

    private Material material;
    private List<DepositoQuantidadeVo> depositoQuantidade;

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public List<DepositoQuantidadeVo> getDepositoQuantidade() {
        return depositoQuantidade;
    }

    public void setDepositoQuantidade(List<DepositoQuantidadeVo> depositoQuantidade) {
        this.depositoQuantidade = depositoQuantidade;
    }
}
