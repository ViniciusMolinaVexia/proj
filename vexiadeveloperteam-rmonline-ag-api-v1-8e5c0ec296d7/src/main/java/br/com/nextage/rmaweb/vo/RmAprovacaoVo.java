
package br.com.nextage.rmaweb.vo;

/**
 *
 * @author nextage
 */
public class RmAprovacaoVo {

    private Boolean isGerenteArea;
    private Integer eapMultiEmpresaId;

    public Boolean getGerenteArea() {
        return isGerenteArea;
    }

    public void setGerenteArea(Boolean gerenteArea) {
        isGerenteArea = gerenteArea;
    }

    public Integer getEapMultiEmpresaId() {
        return eapMultiEmpresaId;
    }

    public void setEapMultiEmpresaId(Integer eapMultiEmpresaId) {
        this.eapMultiEmpresaId = eapMultiEmpresaId;
    }
}