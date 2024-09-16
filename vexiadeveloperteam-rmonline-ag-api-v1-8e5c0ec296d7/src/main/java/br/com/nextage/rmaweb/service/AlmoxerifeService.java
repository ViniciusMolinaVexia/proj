package br.com.nextage.rmaweb.service;

import br.com.nextage.rmaweb.dao.VwConsultaAlmoxarifeDao;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.filtro.FiltroPainelRequisicaoMateriais;

import java.util.List;

public class AlmoxerifeService {
	
	private VwConsultaAlmoxarifeDao vwConsultaAlmoxarifeDao = VwConsultaAlmoxarifeDao.getInstance();
	private static AlmoxerifeService almoxerifeService = null;
	
	public static AlmoxerifeService getInstance() {
		if(almoxerifeService == null) {
			almoxerifeService = new AlmoxerifeService();
		}
		return almoxerifeService;
	}

	public List<Object> vwConsultaAlmoxarife(final FiltroPainelRequisicaoMateriais filtro, final Usuario usuarioLogado) {
		return vwConsultaAlmoxarifeDao.consultaAlmoxarife(filtro, usuarioLogado);
	}

}
