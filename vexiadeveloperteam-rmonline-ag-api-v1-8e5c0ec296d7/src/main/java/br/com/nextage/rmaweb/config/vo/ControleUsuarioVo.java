/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.config.vo;

import br.com.nextage.rmaweb.entitybean.Usuario;
import java.util.Date;

/**
 * @author Marlos Morbis Novo
 */
public class ControleUsuarioVo {

    private Usuario usuario;
    private Date data;

    public ControleUsuarioVo(Usuario usuario) {
        this.usuario = usuario;
        this.data = new Date();
    }

    public ControleUsuarioVo(Usuario usuario, Date data) {
        this.usuario = usuario;
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
