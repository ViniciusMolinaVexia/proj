package br.com.nextage.rmaweb.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.nextage.rmaweb.entitybean.Centro;
import br.com.nextage.rmaweb.entitybean.Role;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.entitybean.VwConsultaAlmoxarife;
import br.com.nextage.rmaweb.filtro.FiltroPainelRequisicaoMateriais;
import br.com.nextage.rmaweb.utils.ConnectionFactory;
import org.apache.log4j.Logger;

public class VwConsultaAlmoxarifeDao {

	private static final Logger LOG = Logger.getLogger(VwConsultaAlmoxarifeDao.class);
	private static final Integer STATUS_MAT_APROVADO = 1004;
	private static VwConsultaAlmoxarifeDao vwConsultaAlmoxarifeDao = null;
	private static final String START_HOUR = " 00:00";
	private static final String END_HOUR = " 23:59";
	private CentroDAO centroDAO = CentroDAO.getInstance();

	public VwConsultaAlmoxarifeDao() {
		// TODO Auto-generated constructor stub
	}

	public static VwConsultaAlmoxarifeDao getInstance() {
		if (vwConsultaAlmoxarifeDao == null) {
			vwConsultaAlmoxarifeDao = new VwConsultaAlmoxarifeDao();
		}
		return vwConsultaAlmoxarifeDao;
	}

	public List<Object> consultaAlmoxarife(final FiltroPainelRequisicaoMateriais filtro, final Usuario usuarioLogado) {
		List<Object> vwConsultaAlmoxarife = new ArrayList<Object>();
		// GET Usuario Centro
		int contador = 0;
		try {

			List<Centro> lstCentro = centroDAO.getCentrosByUsuario(usuarioLogado.getUsuarioId());

			String sql = sqlConsultaAlmoxarife(usuarioLogado, lstCentro);
			sql = sqlComplementConsultAlmoxarife(filtro, sql);
			PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);
			smt = sqlSetComplementConsultAlmoxarife(smt, filtro, contador);

			ResultSet resultSet = smt.executeQuery();
			while (resultSet.next()) {
				VwConsultaAlmoxarife almoxarife = incrementoResultSet(resultSet);
				vwConsultaAlmoxarife.add(almoxarife);
			}
			smt.close();
			resultSet.close();
		} catch (SQLException e) {
			LOG.error("consultaAlmoxarife", e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vwConsultaAlmoxarife;
	}

	private PreparedStatement sqlSetComplementConsultAlmoxarife(PreparedStatement smt,
			FiltroPainelRequisicaoMateriais filtro, int contador) {
		try {
			int cont = 1;
			if (filtro != null) {
				if (filtro.getCentro() != null && !filtro.getCentro().isEmpty()) {
					smt.setString(cont, filtro.getCentro());
					cont += 1;
				}
				if (filtro.getNumero() != null && !filtro.getNumero().isEmpty()) {
					smt.setString(cont, filtro.getNumero());
					cont += 1;
				}
				if (filtro.getMaterial() != null && !filtro.getMaterial().isEmpty()) {
					smt.setString(cont, filtro.getMaterial());
					cont += 1;
				}
				if (filtro.getQtdeSolicitada() != null && !filtro.getQtdeSolicitada().isEmpty()) {
					smt.setString(cont, filtro.getQtdeSolicitada());
					cont += 1;
				}
				if (filtro.getStDataEmissaoDe() != null && !filtro.getStDataEmissaoDe().isEmpty()) {
					smt.setString(cont, filtro.getStDataEmissaoDe().concat(START_HOUR));
					cont += 1;
				}
				if (filtro.getStDataEmissaoAte() != null && !filtro.getStDataEmissaoAte().isEmpty()) {
					smt.setString(cont, filtro.getStDataEmissaoAte().concat(END_HOUR));
					cont += 1;
				}

				if (filtro.getStDataNecessidadeDe() != null && !filtro.getStDataNecessidadeDe().isEmpty()) {
					smt.setString(cont, filtro.getStDataNecessidadeDe().concat(START_HOUR));
					cont += 1;
				}
				if (filtro.getStDataNecessidadeAte() != null && !filtro.getStDataNecessidadeAte().isEmpty()) {
					smt.setString(cont, filtro.getStDataNecessidadeAte().concat(END_HOUR));
					cont += 1;
				}

				if (filtro.getRequisitante() != null && filtro.getRequisitante().getNome() != null
						&& !filtro.getRequisitante().getNome().isEmpty()) {
					smt.setString(cont, filtro.getRequisitante().getNome());
					cont += 1;
				}
				if (filtro.getUsuarioCadastrante() != null && filtro.getUsuarioCadastrante().getNome() != null
						&& !filtro.getUsuarioCadastrante().getNome().isEmpty()) {
					smt.setString(cont, filtro.getUsuarioCadastrante().getNome());
					cont += 1;
				}

				if (filtro.getColetorCusto() != null && !filtro.getColetorCusto().isEmpty()) {
					smt.setString(cont, filtro.getColetorCusto());
					cont += 1;
				}

				if (filtro.getTipoRequisicao() != null) {
					smt.setString(cont, "%" + filtro.getTipoRequisicao().getCodigo() + "%");
					cont += 1;
				}
				if (filtro.getPrioridade() != null) {
					smt.setString(cont, filtro.getPrioridade().getCodigo());
					cont += 1;
				}
				if (filtro.getStatus() != null && !filtro.getStatus().isEmpty()) {
					smt.setString(cont, filtro.getStatus());
					cont += 1;
				} else {
					smt.setString(cont, STATUS_MAT_APROVADO.toString());
					cont += 1;
				}
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
		return smt;
	}

	private VwConsultaAlmoxarife incrementoResultSet(ResultSet resultSet) {
		VwConsultaAlmoxarife almoxarife = new VwConsultaAlmoxarife();
		try {
			almoxarife.setAreaCodigo(resultSet.getString("AREA_CODIGO"));
			almoxarife.setCadastrante(resultSet.getString("CADASTRANTE"));
			almoxarife.setCadastranteId(resultSet.getString("CADASTRANTE_ID"));
			almoxarife.setCentro(resultSet.getString("CENTRO"));
			almoxarife.setCodCentro(resultSet.getString("COD_CENTRO"));
			almoxarife.setNota(resultSet.getString("NOTA"));
			almoxarife.setCodigo(resultSet.getString("CODIGO"));
			almoxarife.setCodMaterial(resultSet.getString("COD_MATERIAL"));
			almoxarife.setColetorCustos(resultSet.getString("COLETOR_CUSTOS"));
			almoxarife.setDiagramaRede(resultSet.getString("DIAGRAMA_REDE"));
			almoxarife.setDataAplicacao(resultSet.getDate("DATA_APLICACAO"));
			almoxarife.setDataAvaliacao(resultSet.getDate("DATA_AVALIACAO"));
			almoxarife.setDataEmissao(resultSet.getDate("DATA_EMISSAO"));
			almoxarife.setDepositoCodigo(resultSet.getString("DEPOSITO_CODIGO"));
			almoxarife.setFluxoMaterial(resultSet.getString("FLUXO_MATERIAL"));
			almoxarife.setGrupoMercadoria(resultSet.getString("GRUPO_MERCADORIA"));
			almoxarife.setId(resultSet.getInt("ID"));
			almoxarife.setMaterial(resultSet.getString("MATERIAL"));
			almoxarife.setNumero(resultSet.getString("NUMERO"));
			almoxarife.setNumeroSap(resultSet.getString("NUMERO_SAP"));
			almoxarife.setObservacao(resultSet.getString("OBSERVACAO"));
			almoxarife.setGrupoComprador(resultSet.getString("GRUPO_COMPRADOR"));

			try {
				BigDecimal quantidade = resultSet.getBigDecimal("QUANTIDADE");
				if (quantidade != null) {
					quantidade = quantidade.setScale(2, BigDecimal.ROUND_DOWN);
				} else {
					quantidade = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN);
				}
				almoxarife.setQuantidade(quantidade.toString());
			} catch (Exception e) {
				LOG.error("QUANTIDADE: ", e);
			}

			try {
				BigDecimal valorOrcado = resultSet.getBigDecimal("VALOR_ORCADO");
				if (valorOrcado != null) {
					valorOrcado = valorOrcado.setScale(2, BigDecimal.ROUND_DOWN);
				} else {
					valorOrcado = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN);
				}
				almoxarife.setValorOrcado(valorOrcado.toString());
			} catch (Exception e) {
				LOG.error("VALOR_ORCADO: ", e);
			}

			almoxarife.setRequisitante(resultSet.getString("REQUISITANTE"));
			almoxarife.setRequisitanteId(resultSet.getString("REQUISITANTE_ID"));
			almoxarife.setRmId(resultSet.getInt("RMID"));
			almoxarife.setRmMaterialId(resultSet.getInt("RMMATERIALID"));
			almoxarife.setSolicitante(resultSet.getString("SOLICITANTE"));
			almoxarife.setSolicitanteId(resultSet.getString("SOLICITANTE_ID"));
			almoxarife.setStatus(resultSet.getString("STATUS"));
			almoxarife.setStatusItem(resultSet.getString("STATUS_ITEM"));
			almoxarife.setTipoRequisicao(resultSet.getString("TIPO_REQUISICAO"));
			almoxarife.setTipoRequisicaoCodigo(resultSet.getString("TIPO_REQ_CODIGO"));
			almoxarife.setUnidadeMedida(resultSet.getString("UNIDADE_MEDIDA"));
			almoxarife.setCodigoPrioridadeSap(resultSet.getString("CODIGO_PRIORIDADE_SAP"));
			almoxarife.setPrioridadeDesc(resultSet.getString("PRIORIDADE_DESC"));
			almoxarife.setDiasPrevEntrega(resultSet.getInt("dias_prev_entrega"));
			almoxarife.setDataAprovacao(resultSet.getDate("data_aprovacao"));
			almoxarife.setIsBaixa(resultSet.getBoolean("is_baixa"));
			almoxarife.setNumeroBaixa(resultSet.getString("numero_baixa"));

		} catch (SQLException e) {
			LOG.error("incrementoResultSet: ", e);
		}
		return almoxarife;
	}

	private String sqlComplementConsultAlmoxarife(FiltroPainelRequisicaoMateriais filtro, String sql) {
		if (filtro != null) {
			if (filtro.getCentro() != null && !filtro.getCentro().isEmpty()) {
				sql = sql + " AND centro.CODIGO = ?";
			}
			if (filtro.getNumero() != null && !filtro.getNumero().isEmpty()) {
				sql = sql + " AND req.NUMERO_RM = ?";
			}
			if (filtro.getMaterial() != null && !filtro.getMaterial().isEmpty()) {
				sql = sql + " AND mat.NOME = ?";
			}
			if (filtro.getQtdeSolicitada() != null && !filtro.getQtdeSolicitada().isEmpty()) {
				sql = sql + " AND rma.QUANTIDADE = ?";
			}
			if (filtro.getStDataEmissaoDe() != null && !filtro.getStDataEmissaoAte().isEmpty()) {
				sql = sql + " AND req.DATA_EMISSAO BETWEEN convert(datetime, ?, 103) AND convert(datetime, ?, 103)";
			}
			if (filtro.getStDataNecessidadeDe() != null && !filtro.getStDataNecessidadeDe().isEmpty()) {
				sql = sql + " AND req.DATA_APLICACAO BETWEEN convert(datetime, ?, 103) AND convert(datetime, ?, 103)";
			}
			if (filtro.getRequisitante() != null && filtro.getRequisitante().getNome() != null) {
				sql = sql + " AND pessoareq.NOME = ?";
			}
			if (filtro.getUsuarioCadastrante() != null && filtro.getUsuarioCadastrante().getNome() != null) {
				sql = sql + " AND usu_cadastrante.NOME = ?";
			}
			if (filtro.getColetorCusto() != null && !filtro.getColetorCusto().isEmpty()) {
				sql = sql + " AND rma.COLETOR_CUSTOS = ?";
			}
			if (filtro.getTipoRequisicao() != null) {
				sql = sql + " AND tp_req.CODIGO like ?";
			}
			if (filtro.getPrioridade() != null) {
				sql = sql + " AND prio.CODIGO = ?";
			}
			if (filtro.getStatus() != null && !filtro.getStatus().isEmpty()) {
				sql = sql + " AND mat_status.STATUS_ID = ?";
			} else {
				sql = sql + " AND mat_status.STATUS_ID = ?";
			}
		}
		return sql;
	}

	private String sqlConsultaAlmoxarife(final Usuario usuario, final List<Centro> centro) {
		
		StringBuffer newSql = new StringBuffer(
				"select ROW_NUMBER() OVER( ORDER BY rma.RM_MATERIAL_ID) AS id, centro.NOME as centro, centro.IDIOMA AS idioma_centro,  centro.CODIGO as cod_centro, req.NUMERO_RM as numero, rma.NUMERO_REQUISICAO_SAP as numero_sap, rma.NOTA, mat.NOME as material, mat.CODIGO as cod_material, un_medida.SIGLA as unidade_medida, tp_mat.CODIGO as codigo, rma.QUANTIDADE as quantidade, rma.VALOR_ORCADO as VALOR_ORCADO, req.DATA_EMISSAO as data_emissao, req.DATA_APLICACAO as data_aplicacao, pessoareq.NOME as requisitante, pessoareq.PESSOA_ID as REQUISITANTE_ID, usu_cadastrante.NOME as cadastrante, usu_cadastrante.PESSOA_ID as cadastrante_id, rma.FLUXO_MATERIAL as fluxo_material, rma.COLETOR_CUSTOS as coletor_custos, rma.DIAGRAMA_REDE as diagrama_rede, req.DATA_EMISSAO as data_avaliacao, rma.STATUS as status, mat_status.STATUS_ID as status_item, tp_req.DESCRICAO as tipo_requisicao, req.OBSERVACAO as observacao, rma.RM_MATERIAL_ID as rmMaterialId, req.RM_ID as rmId, area.CODIGO as area_codigo, area.IDIOMA AS idioma_area,   depo.CODIGO as deposito_codigo, mat.GRUPO_MERCADORIA as grupo_mercadoria, usu_cadastrante.LOGIN as solicitante, usu_cadastrante.PESSOA_ID as solicitante_id, prio.CODIGO_SAP as CODIGO_PRIORIDADE_SAP, prio.DESCRICAO as prioridade_desc, prio.CODIGO as prioridade_cod, prio.CONF_DIAS_PREV_ENTREGA as dias_prev_entrega, tp_req.CODIGO as tipo_req_codigo, mat_status.DATA_HORA_STATUS as data_aprovacao, rma.IS_BAIXA as is_baixa, rma.NUMERO_BAIXA as numero_baixa, rma.GRUPO_COMPRADOR as grupo_comprador from TB_RM_MATERIAL rma inner join TB_RM req on rma.RM_ID = req.RM_ID inner join TB_CENTRO centro on centro.CENTRO_ID = req.CENTRO_ID inner join TB_MATERIAL mat on mat.MATERIAL_ID = rma.MATERIAL_ID inner join TB_TIPO_MATERIAL tp_mat on tp_mat.TIPO_MATERIAL_ID = mat.TIPO_MATERIAL_ID inner join TB_PESSOA pessoareq on pessoareq.PESSOA_ID = req.REQUISITANTE_ID inner join TB_USUARIO usu_cadastrante on usu_cadastrante.USUARIO_ID = req.USUARIO_ID inner join TB_TIPO_REQUISICAO tp_req on req.TIPO_REQUISICAO_ID = tp_req.TIPO_REQUISICAO_ID inner join TB_UNIDADE_MEDIDA un_medida on un_medida.UNIDADE_MEDIDA_ID = mat.UNIDADE_MEDIDA_ID inner join TB_AREA area on area.AREA_ID = req.AREA_ID inner join TB_DEPOSITO depo on depo.DEPOSITO_ID = rma.DEPOSITO_ID inner join TB_PRIORIDADE prio on prio.PRIORIDADE_ID = req.PRIORIDADE_ID inner join TB_RM_MATERIAL_STATUS mat_status on mat_status.RM_MATERIAL_ID = rma.RM_MATERIAL_ID where mat_status.ID = (SELECT MAX(ID) FROM TB_RM_MATERIAL_STATUS mat_status WHERE mat_status.RM_MATERIAL_ID = rma.RM_MATERIAL_ID) ");
		if (usuario.hasProfile(Role.ROLE_ALMOXARIFE) && !usuario.hasProfile(Role.ROLE_ADMINISTRADOR)) {
			if (centro.size() > 0) {
				List<Integer> centrosIds = new ArrayList<>();
				StringBuffer sqlCentro = new StringBuffer();
				String centrosIdsString = "";
				newSql.append(" and req.CENTRO_ID in ( ");

				for (Centro centros : centro) {
					centrosIds.add(centros.getCentroId());
					//sqlCentro.append(centros.getCentroId());
					
				}
				centrosIdsString = centrosIds.stream().map(Object::toString).collect(Collectors.joining(","));
				newSql.append(centrosIdsString);
				newSql.append(" )");
			} else {
				newSql.append(" and req.CENTRO_ID =");
				newSql.append(usuario.getCentro().getCentroId());
			}
		}

		return newSql.toString();
	}

}
