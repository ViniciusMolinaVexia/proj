package br.com.nextage.rmaweb.filtro;

import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.vo.RmAprovadorVo;
import br.com.nextage.util.filter.FiltroGeral;


public class FiltroHierarquiaComprador extends FiltroGeral {

    private Comprador comprador;
    private String hierarquia;
    private String hierarquiaAdicionar;
    private String hierarquiaBuscar;

    public String getHierarquiaBuscar() {
        return hierarquiaBuscar;
    }

    public void setHierarquiaBuscar(String hierarquiaBuscar) {
        this.hierarquiaBuscar = hierarquiaBuscar;
    }

    public String getHierarquiaAdicionar() {
        return hierarquiaAdicionar;
    }

    public void setHierarquiaAdicionar(String hierarquiaAdicionar) {
        this.hierarquiaAdicionar = hierarquiaAdicionar;
    }

    public String getHierarquia() {
        return hierarquia;
    }

    public void setHierarquia(String hierarquia) {
        this.hierarquia = hierarquia;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }
}
