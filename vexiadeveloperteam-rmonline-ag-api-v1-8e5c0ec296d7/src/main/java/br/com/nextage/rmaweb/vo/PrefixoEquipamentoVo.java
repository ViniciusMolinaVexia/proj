package br.com.nextage.rmaweb.vo;

import br.com.nextage.rmaweb.entitybean.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nextage3
 */
public class PrefixoEquipamentoVo {


    private String prefixoEquipamento;
    private String codigoEquipamento;
    private String codigoDeposito;
    private String eapDeposito;

    public String getCodigoEquipamento() {
        return codigoEquipamento;
    }

    public void setCodigoEquipamento(String codigoEquipamento) {
        this.codigoEquipamento = codigoEquipamento;
    }

    public String getPrefixoEquipamento() {
        return prefixoEquipamento;
    }

    public void setPrefixoEquipamento(String prefixoEquipamento) {
        this.prefixoEquipamento = prefixoEquipamento;
    }

    public String getCodigoDeposito() {
        return codigoDeposito;
    }

    public void setCodigoDeposito(String codigoDeposito) {
        this.codigoDeposito = codigoDeposito;
    }

    public String getEapDeposito() {
        return eapDeposito;
    }

    public void setEapDeposito(String eapDeposito) {
        this.eapDeposito = eapDeposito;
    }
}
