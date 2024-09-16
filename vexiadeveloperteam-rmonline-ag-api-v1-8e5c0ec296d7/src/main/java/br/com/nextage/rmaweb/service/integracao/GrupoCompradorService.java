package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.rmaweb.dao.GrupoCompradorDAO;
import br.com.nextage.rmaweb.entitybean.GrupoComprador;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.LogInterfaceVo;
import br.com.nextage.rmaweb.vo.integracao.GrupoCompradorVO;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GrupoCompradorService {
	
	private static final Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);
	
	/**
	 * <b>atualizarGrupoComprador</b><br />
	 * Atualiza os grupos de compradores enviados pelo SAP
	 * @param grupoCompradores
	 * @return
	 */
	public List<LogInterfaceVo> atualizarGrupoComprador(List<GrupoCompradorVO> grupoCompradores){
		NxResourceBundle rb = new NxResourceBundle(null);
		List<LogInterfaceVo> logs = new ArrayList<LogInterfaceVo>();
		GrupoCompradorDAO gcDAO = new GrupoCompradorDAO();
		for(GrupoCompradorVO gcVO : grupoCompradores) {
			Info info = new Info();
			try {
				if(gcVO.getCodigo()==null||"".equals(gcVO.getCodigo())||gcVO.getNome()==null||"".contentEquals(gcVO.getNome())) {
					info.setCodigo(Info.INFO_002);
	                info.setTipo("E");
	                info.setMensagem(rb.getString("msg_requisicao_cancelada_erro"));
	                info.setObjeto(gcVO);
	                logs.add(new LogInterfaceVo(info));
				}else {
					GrupoComprador gc = gcDAO.getGrupoCompradorPorCodigo(gcVO.getCodigo());
					if(gc==null) {
						gc = new GrupoComprador();
						gc.setCodigo(gcVO.getCodigo());
						gc.setNome(gcVO.getNome());
						gc.setDescricao(gcVO.getDescricao());
						gc.setCriado(new Date());
					}else {
						gc.setNome(gcVO.getNome());
						gc.setDescricao(gcVO.getDescricao());
						gc.setModificado(new Date());
					}
					gcDAO.atualizarGrupoComprador(gc);
					info.setCodigo(Info.INFO_001);
				    info.setTipo("S");
				    info.setMensagem(rb.getString(Constantes.REG_SALVO_SUCESSO_I18N));
				    logs.add(new LogInterfaceVo(info));
				    logger.debug("Atualizado: "+gc);
				}
			}catch (Exception e) {
				info.setCodigo(Info.INFO_002);
                info.setTipo("E");
                info.setMensagem(rb.getString("msg_requisicao_cancelada_erro"));
                info.setObjeto(e);
                logs.add(new LogInterfaceVo(info));
                logger.error("Erro ao atualizar: "+gcVO, e);
			}
		}
		return logs;
	}

	public List<GrupoComprador> listar() throws Exception{
		GrupoCompradorDAO gcDAO = new GrupoCompradorDAO();
		return gcDAO.listar();
	}

}
