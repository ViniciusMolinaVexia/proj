/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.vo;

import br.com.nextage.persistence_2.vo.PaginacaoVo;
import br.com.nextage.rmaweb.entitybean.Deposito;
import java.util.List;

/**
 *
 * @author nextage
 */
public class MateriaisSimilaresVo {

    private List<Deposito> depositos;
    private List<MaterialDepositoQuantidadeVo> materiais;

    private PaginacaoVo paginacaoVo;

    public List<Deposito> getDepositos() {
        return depositos;
    }

    public void setDepositos(List<Deposito> depositos) {
        this.depositos = depositos;
    }

    public List<MaterialDepositoQuantidadeVo> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<MaterialDepositoQuantidadeVo> materiais) {
        this.materiais = materiais;
    }

    public PaginacaoVo getPaginacaoVo() {
        return paginacaoVo;
    }

    public void setPaginacaoVo(PaginacaoVo paginacaoVo) {
        this.paginacaoVo = paginacaoVo;
    }
}
