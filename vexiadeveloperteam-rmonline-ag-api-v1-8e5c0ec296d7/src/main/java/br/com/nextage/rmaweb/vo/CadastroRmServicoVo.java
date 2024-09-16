package br.com.nextage.rmaweb.vo;

import java.util.ArrayList;
import java.util.List;

import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.entitybean.RmServico;
import br.com.nextage.rmaweb.entitybean.Usuario;

/**
 *
 * @author HM
 */
public class CadastroRmServicoVo {

    /*public CadastroRmServicoVo() {
        //rmServico = new ArrayList<>();
    	RmServico
    }*/
  //private List<RmDeposito> rmDepositos;
    private Pessoa aprovador;
    //private Material material;
    //private RmMaterial rmMaterial;
    //private RmServico rmServico;
    private Usuario usuario;
    //private Deposito deposito;
    private List<RmServico> rmServico;
    private RmServico rmServico2;
    private Rm rm;
    
    public RmServico getRmServico2() {
    	return this.rmServico2;
    }
    
    public void setRmServico2(RmServico rmServico2) {
    	this.rmServico2=rmServico2;
    }
    
    public List<RmServico> getRmServico() {
		return rmServico;
	}
	public void setRmServico(List<RmServico> rmServico) {
		this.rmServico = rmServico;
	}
	public Rm getRm() {
		return rm;
	}
	public void setRm(Rm rm) {
		this.rm = rm;
	}
	public Pessoa getAprovador() {
		return aprovador;
	}
	public void setAprovador(Pessoa aprovador) {
		this.aprovador = aprovador;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}    
    
}
