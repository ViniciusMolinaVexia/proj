package br.com.nextage.rmaweb.vo;

import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.entitybean.Usuario;

/**
 *
 * @author Lucas Heitor
 */
public class CadastroMaterial {

    private Material Material;
    private Rm rm;
    private Usuario usuario;

    public Material getMaterial() {
        return Material;
    }

    public void setMaterial(Material Material) {
        this.Material = Material;
    }

    public Rm getRm() {
        return rm;
    }

    public void setRm(Rm rm) {
        this.rm = rm;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
