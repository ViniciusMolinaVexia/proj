/*
 * NextAge License
 * Copyright 2015 - Nextage
 * 
 */
package br.com.nextage.rmaweb.vo;

import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.entitybean.RmMaterialStatus;
import br.com.nextage.rmaweb.entitybean.VwRmMaterial;
import br.com.nextage.util.vo.RelatorioVo;
import jxl.format.Colour;

/**
 *
 * @author Luan L F Domingues - <b>l.domingues@nextage.com.br<b>
 */
public class VwRmMaterialVo extends RelatorioVo {

    private RmMaterial rmMaterial;
    private RmMaterialStatus rmMaterialStatus;
    private Integer diasPrevistos;

    public VwRmMaterialVo() {
    }

    /**
     * Contrutor usado para criar o vo com a View passada por parametro
     *
     * @param vwRmMaterial
     */
    public VwRmMaterialVo(VwRmMaterial vwRmMaterial) {
        if (vwRmMaterial != null) {
            rmMaterial = vwRmMaterial.getRmMaterial();
            rmMaterialStatus = vwRmMaterial.getRmMaterialStatus();
            diasPrevistos = vwRmMaterial.getDiasPrevistos();
            //caso os dias forem menores que zero já cria a propriedade da fonte em vermelho
            if (diasPrevistos != null && diasPrevistos < 0) {
                setFontColor(Colour.RED);
                setAplicar(true);
            }
        }
    }

    public RmMaterial getRmMaterial() {
        return rmMaterial;
    }

    public void setRmMaterial(RmMaterial rmMaterial) {
        this.rmMaterial = rmMaterial;
    }

    public RmMaterialStatus getRmMaterialStatus() {
        return rmMaterialStatus;
    }

    public void setRmMaterialStatus(RmMaterialStatus rmMaterialStatus) {
        this.rmMaterialStatus = rmMaterialStatus;
    }

    public Integer getDiasPrevistos() {
        return diasPrevistos;
    }

    public void setDiasPrevistos(Integer diasPrevistos) {
        this.diasPrevistos = diasPrevistos;
    }

}
