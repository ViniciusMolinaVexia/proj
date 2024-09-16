package br.com.nextage.rmaweb.vo;

import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.RmAprovador;
import br.com.nextage.rmaweb.entitybean.RmServico;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.entitybean.VwRmMaterial;
import br.com.nextage.rmaweb.entitybean.VwRmServico;

import java.util.List;

/**
 *
 * @author nextage
 */
public class RmAprovadorVo {

    private RmAprovador rmAprovador;
    private List<VwRmMaterial> listaRmMaterial;
    private List<VwRmServico> listaRmServico;
    private RmServico rmServico;
    private Pessoa pessoaCusto;
    private Usuario usuario;
    private Pessoa gerenteArea;
    private Boolean verificaGerenteObra;

    public RmAprovador getRmAprovador() {
        return rmAprovador;
    }

    public void setRmAprovador(RmAprovador rmAprovador) {
        this.rmAprovador = rmAprovador;
    }

    public List<VwRmMaterial> getListaRmMaterial() {
        return listaRmMaterial;
    }

    public void setListaRmMaterial(List<VwRmMaterial> listaRmMaterial) {
        this.listaRmMaterial = listaRmMaterial;
    }
    
    public List<VwRmServico> getListaRmServico() {
        return listaRmServico;
    }

    public void setListaRmServico(List<VwRmServico> listaRmServico) {
        this.listaRmServico = listaRmServico;
    }
    
    public RmServico getRmServico() {
        return rmServico;
    }

    public void setRmServico(RmServico rmServico) {
        this.rmServico = rmServico;
    }

    public Pessoa getPessoaCusto() {
        return pessoaCusto;
    }

    public void setPessoaCusto(Pessoa pessoaCusto) {
        this.pessoaCusto = pessoaCusto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pessoa getGerenteArea() {
        return gerenteArea;
    }

    public void setGerenteArea(Pessoa gerenteArea) {
        this.gerenteArea = gerenteArea;
    }

    public Boolean getVerificaGerenteObra() {
        return verificaGerenteObra;
    }

    public void setVerificaGerenteObra(Boolean verificaGerenteObra) {
        this.verificaGerenteObra = verificaGerenteObra;
    }

}
