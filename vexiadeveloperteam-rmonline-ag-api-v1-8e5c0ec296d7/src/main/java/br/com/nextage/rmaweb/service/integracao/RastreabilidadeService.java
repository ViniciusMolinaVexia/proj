package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.rmaweb.dao.RastreabilidadeDAO;
import br.com.nextage.rmaweb.dao.RmDao;
import br.com.nextage.rmaweb.entitybean.Rastreabilidade;
import br.com.nextage.rmaweb.entitybean.Rm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RastreabilidadeService {
	
	private static RastreabilidadeService rastreabilidadeService = null;
	private RastreabilidadeDAO rastreabilidadeDAO = RastreabilidadeDAO.getInstance();
	
	public static RastreabilidadeService getInstance() {
		if(rastreabilidadeService==null) {
			rastreabilidadeService = new RastreabilidadeService();
		}
		return rastreabilidadeService;
	}
	
	private RastreabilidadeService() {}
	
	public List<Rastreabilidade> getListaRastreabilidade(String numero) throws Exception{
		return rastreabilidadeDAO.getRastreabilidadePorNumeroRm(numero);
	}
	public void rastrear(Rm rm, String usuario) {
		rastrear(rm, usuario, Rastreabilidade.MODIFICADO);
	}
	public void rastrear(Rm rm, String usuario, String acao) {
		RmDao rmDAO = new RmDao();
		try {
			if((acao != null && acao.equals(Rastreabilidade.CRIADO)) ||
					(rm.getRmId()==null || rm.getRmId() == 0)) {
				Rastreabilidade r = new Rastreabilidade();
				r.setNumero(rm.getNumeroRm());
				r.setCampo("");
				r.setValor("");
				r.setAcao(Rastreabilidade.CRIADO);
				r.setModificacao(new Date());
				r.setUsuario(usuario);
				rastreabilidadeDAO.incluirRastro(r);
			}else {
				Rm rmAntiga = rmDAO.getRmPorId(rm.getRmId());
				Map<String, String> campos = camposAlterados(rmAntiga, rm);
				for(String key : campos.keySet()) {
					Rastreabilidade r = new Rastreabilidade();
					r.setNumero(rm.getNumeroRm());
					r.setCampo(key);
					r.setValor(campos.get(key));
					r.setAcao(Rastreabilidade.MODIFICADO);
					r.setModificacao(new Date());
					r.setUsuario(usuario);
					rastreabilidadeDAO.incluirRastro(r);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void rastrearQuantidadeAlterada(Rm rm, String qtde, String usuario) {
		try {
			Rastreabilidade r = new Rastreabilidade();
			r.setNumero(rm.getNumeroRm());
			r.setCampo("Quantidade material alterado");
			r.setValor("Qtde:"+qtde);
			r.setAcao(Rastreabilidade.MODIFICADO);
			r.setModificacao(new Date());
			r.setUsuario(usuario);
			rastreabilidadeDAO.incluirRastro(r);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void rastrearMaterialBaixa(Rm rm, String numeroBaixa, String usuario) {
		try {
			Rastreabilidade r = new Rastreabilidade();
			r.setNumero(rm.getNumeroRm());
			r.setCampo("Baixa de Material");
			r.setValor("Número Baixa:"+numeroBaixa);
			r.setAcao(Rastreabilidade.MODIFICADO);
			r.setModificacao(new Date());
			r.setUsuario(usuario);
			rastreabilidadeDAO.incluirRastro(r);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void rastrearMaterialRetirado(Rm rm, Integer qtde, String usuario) {
		try {
			Rastreabilidade r = new Rastreabilidade();
			r.setNumero(rm.getNumeroRm());
			r.setCampo("Materiais retirados");
			r.setValor("Qtde:"+qtde);
			r.setAcao(Rastreabilidade.MODIFICADO);
			r.setModificacao(new Date());
			r.setUsuario(usuario);
			rastreabilidadeDAO.incluirRastro(r);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void rastrearMaterialIncluido(Rm rm, Integer qtde, String usuario) {
		try {
			Rastreabilidade r = new Rastreabilidade();
			r.setNumero(rm.getNumeroRm());
			r.setCampo("Materiais incluido");
			r.setValor("Qtde:"+qtde);
			r.setAcao(Rastreabilidade.MODIFICADO);
			r.setModificacao(new Date());
			r.setUsuario(usuario);
			rastreabilidadeDAO.incluirRastro(r);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void rastrearDepositoRetirado(Rm rm, Integer qtde, String usuario) {
		try {
			Rastreabilidade r = new Rastreabilidade();
			r.setNumero(rm.getNumeroRm());
			r.setCampo("Depósito retirado");
			r.setValor("Qtde:"+qtde);
			r.setAcao(Rastreabilidade.MODIFICADO);
			r.setModificacao(new Date());
			r.setUsuario(usuario);
			rastreabilidadeDAO.incluirRastro(r);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void rastrearDepositoIncluido(Rm rm, Integer qtde, String usuario) {
		try {
			Rastreabilidade r = new Rastreabilidade();
			r.setNumero(rm.getNumeroRm());
			r.setCampo("Depósito incluido");
			r.setValor("Qtde:"+qtde);
			r.setAcao(Rastreabilidade.MODIFICADO);
			r.setModificacao(new Date());
			r.setUsuario(usuario);
			rastreabilidadeDAO.incluirRastro(r);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void rastrearAprovado(Rm rm, String usuario, String nivel) {
		try {
			Rastreabilidade r = new Rastreabilidade();
			r.setNumero(rm.getNumeroRm());
			r.setCampo("Aprovado");
			r.setValor(nivel);
			r.setAcao(Rastreabilidade.MODIFICADO);
			r.setModificacao(new Date());
			r.setUsuario(usuario);
			rastreabilidadeDAO.incluirRastro(r);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void rastrearReprovado(Rm rm, String usuario, String nivel) {
		try {
			Rastreabilidade r = new Rastreabilidade();
			r.setNumero(rm.getNumeroRm());
			r.setCampo("Reprovado");
			r.setValor(nivel);
			r.setAcao(Rastreabilidade.MODIFICADO);
			r.setModificacao(new Date());
			r.setUsuario(usuario);
			rastreabilidadeDAO.incluirRastro(r);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//Serviço -> Restreabilidade
	
	public void rastrearServicoAprovado(Rm rm, String usuario, String nivel) {
		try {
			Rastreabilidade r = new Rastreabilidade();
			r.setNumero(rm.getNumeroRmServico());
			r.setCampo("Aprovado");
			r.setValor(nivel);
			r.setAcao(Rastreabilidade.MODIFICADO);
			r.setModificacao(new Date());
			r.setUsuario(usuario);
			rastreabilidadeDAO.incluirRastro(r);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void rastrearServicoReprovado(Rm rm, String usuario, String nivel) {
		try {
			Rastreabilidade r = new Rastreabilidade();
			r.setNumero(rm.getNumeroRmServico());
			r.setCampo("Reprovado");
			r.setValor(nivel);
			r.setAcao(Rastreabilidade.MODIFICADO);
			r.setModificacao(new Date());
			r.setUsuario(usuario);
			rastreabilidadeDAO.incluirRastro(r);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	private Map<String, String> camposAlterados(Rm rmAntiga, Rm rmNova){
		Map<String, String> campos = new HashMap<String, String>();
		//Acao de cancelar RM
		if(rmAntiga.getDataCancelamento() != rmNova.getDataCancelamento()) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			campos.put("Cancelamento", sdf.format(rmNova.getDataCancelamento()));
			campos.put("Justificativa", rmNova.getJustificativaCancelamento());
			return campos;
		}
		//Acao de envio para aprovacao
		if(rmAntiga.getDataEnvioAprovacao() == null && rmNova.getDataEnvioAprovacao() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			campos.put("Enviado para aprovação", sdf.format(rmNova.getDataEnvioAprovacao()));
			return campos;
		}
		
		return campos;
	}
}
