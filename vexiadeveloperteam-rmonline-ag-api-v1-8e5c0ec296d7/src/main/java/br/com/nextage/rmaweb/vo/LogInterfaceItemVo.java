package br.com.nextage.rmaweb.vo;

import br.com.nextage.util.Info;

import java.util.List;

/**
 * Created by Marlos on 12/05/2016.
 */
public class LogInterfaceItemVo {

    public LogInterfaceItemVo() {}
    public LogInterfaceItemVo(String nome,String valor,Integer numObj)
    {
        this.nome = nome;
        this.valor = valor;
        this.numObj = numObj;
    }
    public LogInterfaceItemVo(String nome,String valor, Integer numObj,List<LogInterfaceItemVo> subItens)
    {
        this.nome = nome;
        this.valor = valor;
        this.subItens = subItens;
        this.numObj = numObj;
    }

    private String nome;
    private String valor;
    private Integer numObj;
    private List<LogInterfaceItemVo> subItens;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public List<LogInterfaceItemVo> getSubItens() {
        return subItens;
    }

    public void setSubItens(List<LogInterfaceItemVo> subItens) {
        this.subItens = subItens;
    }

    public Integer getNumObj() {
        return numObj;
    }

    public void setNumObj(Integer numObj) {
        this.numObj = numObj;
    }
}
