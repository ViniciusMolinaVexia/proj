package br.com.nextage.rmaweb.service.integracao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.nextage.rmaweb.dao.AreaDAO;
import br.com.nextage.rmaweb.dao.CentroDAO;
import br.com.nextage.rmaweb.dao.UsuarioDao;
import br.com.nextage.rmaweb.entitybean.Centro;
import br.com.nextage.rmaweb.entitybean.Role;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.LogInterfaceVo;
import br.com.nextage.rmaweb.vo.integracao.CentroVO;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CentroService {
	
	private static final Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);
	private CentroDAO centroDAO = CentroDAO.getInstance();
	private static CentroService centroService = null;
	private CentroService() {}
	public static CentroService getInstance() {
		if(centroService==null) {
			centroService = new CentroService();
		}
		return centroService;
	}
	
	/**
	 * <b>getCentrosPorUsuario</b><br />
	 * Recupera todos os centros dos usuarios<br />
	 * em caso de perfil de ADM serao retornados todos os centros
	 * @param usuarioId
	 * @param roles
	 * @return
	 */
	public List<Centro> getCentrosPorUsuario(Integer usuarioId, List<Role> roles){
		UsuarioDao usuDAO = new UsuarioDao();
		List<Centro> centros = new ArrayList<Centro>();
		try {
			Usuario usu = usuDAO.getUsuarioPorUsuarioId(usuarioId);
			boolean verTodos = false;
			for(Role r : roles) {
				if(r.getNome().equals(Role.ROLE_ADMINISTRADOR) || r.getNome().equals(Role.ROLE_APROVADOR) || r.getNome().equals(Role.ROLE_REQUISITANTE_CORPORATIVO)) {
					verTodos = true;
				}
			}
			if(verTodos) {
				centros = centroDAO.getAll();
			}else {
				if(usu.getCentro()!=null) {
					centros = centroDAO.getCentrosByUsuario(usu.getUsuarioId());
					//centros.add(usu.getCentro());
				}
			}
		}catch (Exception e) {
			
		}
		return centros;
	}
	
	/**
	 * <b>atualizarCentro</b><br />
	 * Atualiza os centros enviados pelo SAP
	 * @param centros
	 * @return
	 */
	public List<LogInterfaceVo> atualizarCentro(List<CentroVO> centros){
		NxResourceBundle rb = new NxResourceBundle(null);
		List<LogInterfaceVo> logs = new ArrayList<LogInterfaceVo>();
		for(CentroVO cdVO : centros) {
			Info info = new Info();
			try {
				if(cdVO.getCodigo()==null||"".equals(cdVO.getCodigo())||cdVO.getNome()==null||"".contentEquals(cdVO.getNome())) {
					info.setCodigo(Info.INFO_002);
	                info.setTipo("E");
	                info.setMensagem(rb.getString("msg_requisicao_cancelada_erro"));
	                info.setObjeto(cdVO);
	                logs.add(new LogInterfaceVo(info));
				}else {
					Centro cd = centroDAO.getCentroPorCodigo(cdVO.getCodigo());
					if(cd==null) {
						cd = new Centro();
						cd.setCodigo(cdVO.getCodigo());
						cd.setNome(cdVO.getNome());
						cd.setDescricao(cdVO.getDescricao());
						cd.setIdioma(cdVO.getIdioma());
						cd.setCriado(new Date());
					}else {
						cd.setNome(cdVO.getNome());
						cd.setDescricao(cdVO.getDescricao());
						cd.setIdioma(cdVO.getIdioma());
						cd.setModificado(new Date());
					}
					centroDAO.atualizarCentroDeposito(cd);
					info.setCodigo(Info.INFO_001);
				    info.setTipo("S");
				    info.setMensagem(rb.getString(Constantes.REG_SALVO_SUCESSO_I18N));
				    logs.add(new LogInterfaceVo(info));
				    logger.debug("Atualizado: "+cd);
				}
			}catch (Exception e) {
				info.setCodigo(Info.INFO_002);
				info.setTipo("E");
				info.setMensagem(rb.getString("msg_integracao_atualizar_centro_error"));
				info.setObjeto(e);
				logs.add(new LogInterfaceVo(info));
				logger.error("Erro ao atualizar: " + cdVO, e);
			}
		}
		return logs;
	}

	public List<Centro> getAll() throws Exception {
		AreaDAO areaDAO = new AreaDAO();
		List<Centro> centros = centroDAO.getAll();
		for (Centro c : centros) {
			c.setAreas(areaDAO.getAreas());
		}
		return centros;
	}

	public Centro getByCodigo(String codigo) throws Exception {
		return centroDAO.getCentroPorCodigo(codigo);
	}
}
