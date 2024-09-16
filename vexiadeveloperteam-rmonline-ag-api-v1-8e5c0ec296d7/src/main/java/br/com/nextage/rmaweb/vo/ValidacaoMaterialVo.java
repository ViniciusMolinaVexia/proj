package br.com.nextage.rmaweb.vo;

import br.com.nextage.rmaweb.entitybean.MaterialDeposito;
import java.util.List;

/**
 *
 * @author nextage
 */
public class ValidacaoMaterialVo {

    private List<MaterialDeposito> depositosComQuantidade;
    private List<MaterialVo> materiaisCompra;
    private List<MaterialVo> materiaisReserva;
    private List<MaterialDeposito> materiaisCauteladosEmEstoque;

    public List<MaterialDeposito> getDepositosComQuantidade() {
        return depositosComQuantidade;
    }

    public void setDepositosComQuantidade(List<MaterialDeposito> depositosComQuantidade) {
        this.depositosComQuantidade = depositosComQuantidade;
    }

    public List<MaterialVo> getMateriaisCompra() {
        return materiaisCompra;
    }

    public void setMateriaisCompra(List<MaterialVo> materiaisCompra) {
        this.materiaisCompra = materiaisCompra;
    }

    public List<MaterialVo> getMateriaisReserva() {
        return materiaisReserva;
    }

    public void setMateriaisReserva(List<MaterialVo> materiaisReserva) {
        this.materiaisReserva = materiaisReserva;
    }

    public List<MaterialDeposito> getMateriaisCauteladosEmEstoque() {
        return materiaisCauteladosEmEstoque;
    }

    public void setMateriaisCauteladosEmEstoque(List<MaterialDeposito> materiaisCauteladosEmEstoque) {
        this.materiaisCauteladosEmEstoque = materiaisCauteladosEmEstoque;
    }
}
