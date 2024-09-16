package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.rmaweb.dao.AreaDAO;
import br.com.nextage.rmaweb.entitybean.Area;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.LogInterfaceVo;
import br.com.nextage.rmaweb.vo.integracao.AreaVO;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AreaService {
	
	private static final Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);
	
	public List<Area> getAreas(){
		try {
			AreaDAO areaDAO = new AreaDAO();
			return areaDAO.getAreas();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * <b>atualizarArea</b><br />
	 * Atualiza as areas enviadas pelo SAP
	 * @param areas
	 * @return
	 */
	public List<LogInterfaceVo> atualizarArea(List<AreaVO> areas) throws Exception{
		NxResourceBundle rb = new NxResourceBundle(null);
		List<LogInterfaceVo> logs = new ArrayList<LogInterfaceVo>();
		AreaDAO areaDAO = new AreaDAO();
		for(AreaVO areaVO : areas) {
			Info info = new Info();
			try {
				if(areaVO.getCodigo()==null||"".equals(areaVO.getCodigo())||areaVO.getNome()==null||"".contentEquals(areaVO.getNome())) {
					info.setCodigo(Info.INFO_002);
	                info.setTipo("E");
	                info.setMensagem(rb.getString("msg_requisicao_cancelada_erro"));
	                info.setObjeto(areaVO);
	                logs.add(new LogInterfaceVo(info));
				}else {
					Area area = areaDAO.getAreaPorCodigo(areaVO.getCodigo());
					if(area==null) {
						area = new Area();
						area.setCodigo(areaVO.getCodigo());
						area.setNome(areaVO.getNome());
						area.setDescricao(areaVO.getDescricao());
						area.setIdioma(areaVO.getIdioma());
						area.setCriado(new Date());
					}else {
						area.setNome(areaVO.getNome());
						area.setDescricao(areaVO.getDescricao());
						area.setIdioma(areaVO.getIdioma());
						area.setModificado(new Date());
					}
					areaDAO.atualizarArea(area);
					info.setCodigo(Info.INFO_001);
				    info.setTipo("S");
				    info.setMensagem(rb.getString(Constantes.REG_SALVO_SUCESSO_I18N));
				    logs.add(new LogInterfaceVo(info));
				    logger.debug("Atualizado: "+area);
				}
			}catch (Exception e) {
				info.setCodigo(Info.INFO_002);
                info.setTipo("E");
                info.setMensagem(rb.getString("msg_requisicao_cancelada_erro"));
                info.setObjeto(e);
                logs.add(new LogInterfaceVo(info));
                logger.error("Erro ao atualizar: "+areaVO, e);
			}
		}
		return logs;
	}

}
