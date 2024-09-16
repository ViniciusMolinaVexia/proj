package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.rmaweb.dao.GrupoMercadoriaDAO;
import br.com.nextage.rmaweb.entitybean.GrupoMercadoria;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.LogInterfaceVo;
import br.com.nextage.rmaweb.vo.integracao.GrupoMercadoriaVO;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GrupoMercadoriaService {
	
private static final Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);
	
	/**
	 * <b>atualizarGrupoMercadoria</b><br />
	 * Atualiza os grupos de mercadorias enviados pelo SAP
	 * @param grupoCompradores
	 * @return
	 */
	public List<LogInterfaceVo> atualizarGrupoMercadoria(List<GrupoMercadoriaVO> grupoMercadorias){
		NxResourceBundle rb = new NxResourceBundle(null);
		List<LogInterfaceVo> logs = new ArrayList<LogInterfaceVo>();
		GrupoMercadoriaDAO gmDAO = new GrupoMercadoriaDAO();
		for(GrupoMercadoriaVO gmVO : grupoMercadorias) {
			Info info = new Info();
			try {
				if(gmVO.getCodigo()==null||"".equals(gmVO.getCodigo())||gmVO.getNome()==null||"".contentEquals(gmVO.getNome())) {
					info.setCodigo(Info.INFO_002);
	                info.setTipo("E");
	                info.setMensagem(rb.getString("msg_requisicao_cancelada_erro"));
	                info.setObjeto(gmVO);
	                logs.add(new LogInterfaceVo(info));
				}else {
					GrupoMercadoria gm = gmDAO.getGrupoMercadoriaPorCodigo(gmVO.getCodigo());
					if(gm==null) {
						gm = new GrupoMercadoria();
						gm.setCodigo(gmVO.getCodigo());
						gm.setNome(gmVO.getNome());
						gm.setDescricao(gmVO.getDescricao());
						gm.setCriado(new Date());
					}else {
						gm.setNome(gmVO.getNome());
						gm.setDescricao(gmVO.getDescricao());
						gm.setModificado(new Date());
					}
					gmDAO.atualizarGrupoMercadoria(gm);
					info.setCodigo(Info.INFO_001);
				    info.setTipo("S");
				    info.setMensagem(rb.getString(Constantes.REG_SALVO_SUCESSO_I18N));
				    logs.add(new LogInterfaceVo(info));
				    logger.debug("Atualizado: "+gm);
				}
			}catch (Exception e) {
				info.setCodigo(Info.INFO_002);
                info.setTipo("E");
                info.setMensagem(rb.getString("msg_requisicao_cancelada_erro"));
                info.setObjeto(e);
                logs.add(new LogInterfaceVo(info));
                logger.error("Erro ao atualizar: "+gmVO, e);
			}
		}
		return logs;
	}

}
