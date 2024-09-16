package br.com.nextage.rmaweb.vo;

import br.com.nextage.rmaweb.entitybean.CodigoSap;
import br.com.nextage.rmaweb.entitybean.Deposito;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.entitybean.RmDeposito;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.entitybean.RmServico;
import br.com.nextage.rmaweb.entitybean.RmServicoCodigoSap;
import br.com.nextage.rmaweb.entitybean.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nextage3
 */
public class CadastroRmVo {

    public CadastroRmVo() {
        rmMateriais = new ArrayList<>();
        rmDepositos = new ArrayList<>();
        rmServicos = new ArrayList<>();
    }

    private List<RmMaterial> rmMateriais;
    private Rm rm;
    private List<RmDeposito> rmDepositos;
    private List<RmServico> rmServicos;
    private Pessoa aprovador;
    private Material material;
    private RmMaterial rmMaterial;
    private RmServico rmServico;
    private Usuario usuario;
    private Deposito deposito;
    
    private RmServicoCodigoSap codigoSap;
    
    public void setDeposito(Deposito deposito) {
    	this.deposito=deposito;
    }
    
    public Deposito getDeposito() {
    	return this.deposito;
    }
    
    public Usuario getUsuario() {
    	return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
    	this.usuario=usuario;
    }
    
    public RmServico getRmServico() {
        return rmServico;
    }

    public void setRmServico(RmServico rmServico) {
        this.rmServico = rmServico;
    }
    
    public List<RmServico> getRmServicos() {
        return rmServicos;
    }

    public void setRmServicos(List<RmServico> rmServicos) {
        this.rmServicos = rmServicos;
    }

    public List<RmMaterial> getRmMateriais() {
        return rmMateriais;
    }

    public void setRmMateriais(List<RmMaterial> rmMateriais) {
        this.rmMateriais = rmMateriais;
    }

    public Rm getRm() {
        return rm;
    }

    public void setRm(Rm rm) {
        this.rm = rm;
    }

    public List<RmDeposito> getRmDepositos() {
        return rmDepositos;
    }

    public void setRmDepositos(List<RmDeposito> rmDepositos) {
        this.rmDepositos = rmDepositos;
    }

    public Pessoa getAprovador() {
        return aprovador;
    }

    public void setAprovador(Pessoa aprovador) {
        this.aprovador = aprovador;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public RmMaterial getRmMaterial() {
        return rmMaterial;
    }

    public void setRmMaterial(RmMaterial rmMaterial) {
        this.rmMaterial = rmMaterial;
    }

	public RmServicoCodigoSap getCodigoSap() {
		return codigoSap;
	}

	public void setCodigoSap(RmServicoCodigoSap codigoSap) {
		this.codigoSap = codigoSap;
	}

}
