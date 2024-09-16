package br.com.nextage.rmaweb.filtro;

import br.com.nextage.util.filter.FiltroGeral; 
import java.util.Date;

public class FiltroLogInterface extends FiltroGeral { 
 
	private String sistema; 
	private String interfaceExec; 
	private String numRma; 
	private String codigoDeposito; 
	private String itemRequisicaoSap; 
	private String numRequisicaoSap; 
	private String numPedidoSap; 
	private Date dataHoraInclusaoLogInicio; 
	private Date dataHoraInclusaoLogFim ; 
	private String stDataHoraInclusaoLogInicio; 
	private String stDataHoraInclusaoLogFim ; 
	private String usuarioInclusao; 
	private String json; 
	private String mensagem; 
	private String tipoMensagem; 
	private String erroMensagem; 

 	public String getSistema() { 
		return sistema; 
	} 
	public void setSistema(String sistema) { 
		this.sistema = sistema; 
	} 
	public String getInterfaceExec() { 
		return interfaceExec; 
	} 
	public void setInterfaceExec(String interfaceExec) { 
		this.interfaceExec = interfaceExec; 
	} 
	public String getNumRma() { 
		return numRma; 
	} 
	public void setNumRma(String numRma) { 
		this.numRma = numRma; 
	} 
	public String getCodigoDeposito() { 
		return codigoDeposito; 
	} 
	public void setCodigoDeposito(String codigoDeposito) { 
		this.codigoDeposito = codigoDeposito; 
	} 
	public String getItemRequisicaoSap() { 
		return itemRequisicaoSap; 
	} 
	public void setItemRequisicaoSap(String itemRequisicaoSap) { 
		this.itemRequisicaoSap = itemRequisicaoSap; 
	} 
	public String getNumRequisicaoSap() { 
		return numRequisicaoSap; 
	} 
	public void setNumRequisicaoSap(String numRequisicaoSap) { 
		this.numRequisicaoSap = numRequisicaoSap; 
	} 
	public String getNumPedidoSap() { 
		return numPedidoSap; 
	} 
	public void setNumPedidoSap(String numPedidoSap) { 
		this.numPedidoSap = numPedidoSap; 
	} 
	public String getStDataHoraInclusaoLogInicio () { 
		return stDataHoraInclusaoLogInicio; 
	}
	public void setStDataHoraInclusaoLogInicio( String stDataHoraInclusaoLogInicio) { 
		this.stDataHoraInclusaoLogInicio = stDataHoraInclusaoLogInicio ; 
	} 
	public String getStDataHoraInclusaoLogFim () { 
		return stDataHoraInclusaoLogFim; 
	} 
	public void setStDataHoraInclusaoLogFim (String stDataHoraInclusaoLogFim ) { 
		this.stDataHoraInclusaoLogFim = stDataHoraInclusaoLogFim; 
	} 
	public Date getDataHoraInclusaoLogInicio () { 
		return dataHoraInclusaoLogInicio; 
	}
	public void setDataHoraInclusaoLogInicio( Date dataHoraInclusaoLogInicio) { 
		this.dataHoraInclusaoLogInicio = dataHoraInclusaoLogInicio ; 
	} 
	public Date getDataHoraInclusaoLogFim () { 
		return dataHoraInclusaoLogFim; 
	} 
	public void setDataHoraInclusaoLogFim (Date dataHoraInclusaoLogFim ) { 
		this.dataHoraInclusaoLogFim = dataHoraInclusaoLogFim; 
	} 
	public String getUsuarioInclusao() { 
		return usuarioInclusao; 
	} 
	public void setUsuarioInclusao(String usuarioInclusao) { 
		this.usuarioInclusao = usuarioInclusao; 
	} 
	public String getJson() { 
		return json; 
	} 
	public void setJson(String json) { 
		this.json = json; 
	} 
	public String getMensagem() { 
		return mensagem; 
	} 
	public void setMensagem(String mensagem) { 
		this.mensagem = mensagem; 
	} 
	public String getTipoMensagem() { 
		return tipoMensagem; 
	} 
	public void setTipoMensagem(String tipoMensagem) { 
		this.tipoMensagem = tipoMensagem; 
	} 
	public String getErroMensagem() { 
		return erroMensagem; 
	} 
	public void setErroMensagem(String erroMensagem) { 
		this.erroMensagem = erroMensagem; 
	} 
}
