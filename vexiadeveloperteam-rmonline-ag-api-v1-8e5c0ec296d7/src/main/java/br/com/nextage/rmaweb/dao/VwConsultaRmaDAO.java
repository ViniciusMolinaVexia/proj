package br.com.nextage.rmaweb.dao;

import br.com.nextage.rmaweb.entitybean.Centro;
import br.com.nextage.rmaweb.entitybean.CodigoSap;
import br.com.nextage.rmaweb.entitybean.RmServico;
import br.com.nextage.rmaweb.entitybean.VwConsultaRma;
import br.com.nextage.rmaweb.filtro.FiltroCadastroRma;
import br.com.nextage.rmaweb.utils.ConnectionFactory;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VwConsultaRmaDAO {
	private static final Logger log = Logger.getLogger(VwConsultaRmaDAO.class);

	private String getParameters(FiltroCadastroRma filtro){

		List<String> joinTables = new ArrayList<>();

		String sql = "FROM TB_RM RM"
				+ "         LEFT JOIN"
				+ "     TB_RM_MATERIAL RMM ON RMM.RM_ID = RM.RM_ID"
				+ "         LEFT JOIN"
				+ "     (select  MAX(ID) id, RM_MATERIAL_ID from TB_RM_MATERIAL_STATUS group by RM_MATERIAL_ID) RMMS ON RMMS.RM_MATERIAL_ID = RMM.RM_MATERIAL_ID"
				+ "	    LEFT JOIN"
				+ "  TB_RM_SERVICO TRS ON TRS.RM_ID = RM.RM_ID";

		StringBuilder parametros = new StringBuilder() ;
		if (filtro.getNumero() != null && !filtro.getNumero().isEmpty()) {
			parametros.append(" AND RM.NUMERO_RM = " + filtro.getNumero());
		}

		if (filtro.getRmMaterial() != null) {
			if (filtro.getRmMaterial().getNumeroRequisicaoSap() != null
					&& !filtro.getRmMaterial().getNumeroRequisicaoSap().isEmpty()) {

				parametros.append(" AND RMM.NUMERO_REQUISICAO_SAP LIKE '%"
						+ filtro.getRmMaterial().getNumeroRequisicaoSap() + "%'");
			}

			if (filtro.getRmMaterial().getNumeroPedidoCompra() != null
					&& !filtro.getRmMaterial().getNumeroPedidoCompra().isEmpty()) {
				parametros.append(" AND  RMM.NUMERO_PEDIDO_COMPRA LIKE '%"
						+ filtro.getRmMaterial().getNumeroPedidoCompra() + "%'");
			}
		}

		if (filtro.getEapMultiEmpresa() != null) {
			joinTables.add("LEFT JOIN TB_CENTRO CENTRO ON CENTRO.CENTRO_ID = RM.CENTRO_ID");
			if ((filtro.getEapMultiEmpresa().getDescricao() != null)
					&& (!filtro.getEapMultiEmpresa().getDescricao().isEmpty())) {
				parametros.append(" AND CENTRO.NOME = '" + filtro.getEapMultiEmpresa().getDescricao() + "'");
			}
		} else {

			if (filtro.getCentros() != null) {
				String centrosIdsString = "";
				List<Integer> centrosIds = new ArrayList<>();
				parametros.append(" AND RM.CENTRO_ID in ( ");

				for (Centro centros : filtro.getCentros()) {
					centrosIds.add(centros.getCentroId());
				}

				centrosIdsString = centrosIds.stream().map(Object::toString).collect(Collectors.joining(","));
				parametros.append(centrosIdsString);
				parametros.append(" ) ");

			}
		}


		if (filtro.getMaterial() != null) {
			joinTables.add("LEFT JOIN TB_MATERIAL M ON M.MATERIAL_ID = RMM.MATERIAL_ID");
			if ((filtro.getMaterial().getCodigo() != null) && (!filtro.getMaterial().getCodigo().isEmpty())) {
				parametros.append(" AND M.CODIGO  = '" + filtro.getMaterial().getCodigo() + "'");
			}
		}

		if (filtro.getRequisitante() != null) {
			joinTables.add("LEFT JOIN TB_PESSOA REQUISITANTE ON REQUISITANTE.PESSOA_ID = RM.REQUISITANTE_ID");
			if ((filtro.getRequisitante().getNome() != null) && (!filtro.getRequisitante().getNome().isEmpty())) {
				parametros.append(" AND REQUISITANTE.NOME = '" + filtro.getRequisitante().getNome() + "'");
			}
		}

		if (filtro.getUsuarioCadastrante() != null) {
			joinTables.add("LEFT JOIN  TB_USUARIO USUARIO ON USUARIO.USUARIO_ID = RM.USUARIO_ID");
			joinTables.add("LEFT JOIN TB_PESSOA USUARIO_PESSOA ON USUARIO.PESSOA_ID = USUARIO_PESSOA.PESSOA_ID");
			if ((filtro.getUsuarioCadastrante().getNome()) != null
					&& (!filtro.getUsuarioCadastrante().getNome().isEmpty())) {
				parametros.append(" AND USUARIO_PESSOA.NOME = '" + filtro.getUsuarioCadastrante().getNome() + "'");
			}
		}

		if (filtro.getAprovador() != null) {
			String parametro ="";
			if ((filtro.getAprovador().getNome() != null) && (!filtro.getAprovador().getNome().isEmpty())) {
				parametro="AND USER_G.NOME = '" + filtro.getAprovador().getNome() + "'"
						+ " OR USER_G.LOGIN = '"+ filtro.getAprovador().getNome() + "'"
						+ " OR USER_P.NOME = '"+ filtro.getAprovador().getNome() + "'";
			}
			joinTables.add( "INNER JOIN (SELECT MAX (RMS.USUARIO) NOME, RMS.RM_MATERIAL_ID MATERIAL_ID FROM TB_RM_MATERIAL_STATUS RMS WITH (NOLOCK) "
					+ " LEFT JOIN  TB_USUARIO USER_G ON USER_G.NOME = RMS.USUARIO "
					+ " LEFT JOIN  TB_PESSOA USER_P ON USER_P.REFERENCIA = USER_G.LOGIN "
					+ " LEFT JOIN  TB_STATUS S ON RMS.STATUS_ID = S.ID "
					+ " WHERE S.CODIGO in ('AprGc', 'AprE','Apr','AprGa') "
					+ parametro
					+ " group by RMS.RM_MATERIAL_ID )  NOME_APROV  ON   NOME_APROV.MATERIAL_ID=RMMS.RM_MATERIAL_ID ") ;
		}

		if (filtro.getStatus() != null) {
			joinTables.add("LEFT JOIN TB_RM_MATERIAL_STATUS MSTATUS ON MSTATUS.ID = RMMS.ID");
			joinTables.add("LEFT JOIN TB_STATUS S ON S.ID = MSTATUS.STATUS_ID");
			if ((filtro.getStatus().getNome() != null) && (!filtro.getStatus().getNome().isEmpty())) {
				parametros.append(" AND S.NOME = '" + filtro.getStatus().getNome() + "'");
			}
		}

		if ((filtro.getObservacao() != null) && (!filtro.getObservacao().isEmpty())) {
			parametros.append(" AND RM.OBSERVACAO = '" + filtro.getObservacao() + "'");
		}

		if (filtro.getArea() != null) {
			joinTables.add("LEFT JOIN TB_AREA AREA ON AREA.AREA_ID = RM.AREA_ID");
			if ((filtro.getArea().getDescricao() != null) && (!filtro.getArea().getDescricao().isEmpty())) {
				parametros.append(" AND AREA.NOME = '" + filtro.getArea().getDescricao() + "'");
			}
		}

		if (filtro.getTipoRequisicao() != null) {
			joinTables.add("LEFT JOIN TB_TIPO_REQUISICAO TR ON TR.TIPO_REQUISICAO_ID = RM.TIPO_REQUISICAO_ID");
			if ((filtro.getTipoRequisicao() != null)) {
				parametros.append(" AND TR.DESCRICAO = '" + filtro.getTipoRequisicao().getDescricao() + "'");
			}
		}

		if (filtro.getMaterialChave() != null) {
			joinTables.add("LEFT JOIN TB_MATERIAL M ON M.MATERIAL_ID = RMM.MATERIAL_ID");
			if ((filtro.getMaterialChave() != null) && (!filtro.getMaterialChave().isEmpty())) {
				parametros.append(" AND M.NOME LIKE '%" + filtro.getMaterialChave().toUpperCase() + "%'");
			}
		}

		if (filtro.getPrioridade() != null) {
			joinTables.add("LEFT JOIN TB_PRIORIDADE P ON P.PRIORIDADE_ID = RM.PRIORIDADE_ID");
			if ((filtro.getPrioridade().getDescricao() != null)
					&& (!filtro.getPrioridade().getDescricao().isEmpty())) {
				parametros.append(" AND P.DESCRICAO = '" + filtro.getPrioridade().getDescricao() + "'");
			}
		}

		if (filtro.getComprador() != null) {
			joinTables.add("LEFT JOIN TB_COMPRADOR C ON C.COMPRADOR_ID = RMM.COMPRADOR_ID");
			if ((filtro.getComprador().getNome() != null) && (!filtro.getComprador().getNome().isEmpty())) {
				parametros.append(" AND  C.NOME  = '" + filtro.getPrioridade().getDescricao() + "'");
			}
		}

		// 10/23/2016 23:01:05
		String formatDateTime = "yyyy-MM-dd HH:mm:ss";
		String formatDate = "dd/MM/yyyy";

		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern(formatDate);
		DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern(formatDateTime);

		if (filtro.getStDataEmissaoAte() != null && !filtro.getStDataEmissaoAte().isEmpty()) {
			ZonedDateTime dataEmissaoFim = LocalDate.parse(filtro.getStDataEmissaoAte(), formatterDate)
					.atStartOfDay(ZoneId.systemDefault()).withHour(23).withMinute(59).withSecond(59).withNano(999);
			parametros.append(" AND RM.DATA_EMISSAO <= Convert(datetime,'" + dataEmissaoFim.format(formatterDateTime)
					+ "', 20)");
		}

		if (filtro.getStDataEmissaoDe() != null && !filtro.getStDataEmissaoDe().isEmpty()) {
			ZonedDateTime dataEmissaoInicio = LocalDate.parse(filtro.getStDataEmissaoDe(), formatterDate)
					.atStartOfDay(ZoneId.systemDefault());
			parametros.append(" AND RM.DATA_EMISSAO >= Convert(datetime,'" + dataEmissaoInicio.format(formatterDateTime)
					+ "', 20)");
		}
		
		if (!StringUtils.isEmpty(filtro.getStDataRecebimentoAte()) || !StringUtils.isEmpty(filtro.getStDataRecebimentoDe())) {
			joinTables.add("LEFT JOIN TB_REQ_MAT_RECEBIDO TRMR ON TRMR.RM_ID = RM.RM_ID");	
			
			if (!StringUtils.isEmpty(filtro.getStDataRecebimentoAte())) {
				ZonedDateTime dataRecebimentoFim = LocalDate.parse(filtro.getStDataRecebimentoAte(), formatterDate)
						.atStartOfDay(ZoneId.systemDefault()).withHour(23).withMinute(59).withSecond(59).withNano(999);
				parametros.append(" AND TRMR.DATA_RECEBIMENTO <= Convert(datetime,'" + dataRecebimentoFim.format(formatterDateTime)
				+ "', 20)");
			}
			
			if (!StringUtils.isEmpty(filtro.getStDataRecebimentoDe())) {
				ZonedDateTime dataRecebimentoInicio = LocalDate.parse(filtro.getStDataRecebimentoDe(), formatterDate)
						.atStartOfDay(ZoneId.systemDefault());
				parametros.append(" AND TRMR.DATA_RECEBIMENTO >= Convert(datetime,'" + dataRecebimentoInicio.format(formatterDateTime)
				+ "', 20)");
			}
		}

		if (filtro.getStDataNecessidadeAte() != null && !filtro.getStDataNecessidadeAte().isEmpty()) {
			ZonedDateTime dataEmissaoFim = LocalDate.parse(filtro.getStDataNecessidadeAte(), formatterDate)
					.atStartOfDay(ZoneId.systemDefault()).withHour(23).withMinute(59).withSecond(59).withNano(999);
			parametros.append(" AND RM.DATA_APLICACAO <= Convert(datetime,'" + dataEmissaoFim.format(formatterDateTime)
					+ "', 20)");
		}

		if (filtro.getStDataNecessidadeDe() != null && !filtro.getStDataNecessidadeDe().isEmpty()) {
			ZonedDateTime dataEmissaoInicio = LocalDate.parse(filtro.getStDataNecessidadeDe(), formatterDate)
					.atStartOfDay(ZoneId.systemDefault());
			parametros.append(" AND RM.DATA_APLICACAO >= Convert(datetime,'" + dataEmissaoInicio.format(formatterDateTime)
					+ "', 20)");
		}

		for (String table : joinTables)
		{
			sql = sql + " " + table ;
		}
		sql = sql + " where TIPO_RM = 'MAT' " + parametros.toString();

		return sql;
	}

	public int contaTudo(FiltroCadastroRma filtro) {

		String sql = "Select count(RMMS.id) as qtde " + getParameters(filtro);
		int qtdRegistros = 0;

		try {

			PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);
			ResultSet result = smt.executeQuery();

			while (result.next()) {
				qtdRegistros = Integer.parseInt(result.getString("qtde"));
			}

			smt.close();
			result.close();
		} catch (SQLException e) {
			log.error(e);
		}
		return qtdRegistros;
	}

	public List<Object> listaTudo(FiltroCadastroRma filtro, final boolean paginacao) {
		List<Object> vwConsultaRmas = new ArrayList<>();

		String sqlFinds = "select RMMS.ID ID " + getParameters(filtro);

		String sql = ""
				+ "Select ID,RM_NUMERO_RM,PRIORIDADE_DESCRICAO,MATERIAL_CODIGO,MATERIAL_NOME,RM_MATERIAL_QUANTIDADE,UNIDADE_MEDIDA_SIGLA,TIPO_MATERIAL_DESCRICAO, NOTA"
				+ ", STATUS_NOME, TIPO_REQUISICAO_DESCRICAO, DEPOSITO_CODIGO, DEPOSITO_DESCRICAO, RM_MATERIAL_COLETOR_CUSTOS, RM_MATERIAL_COLETOR_ESTOQUE"
				+ ", RM_MATERIAL_COLETOR_ORDEM, RM_MATERIAL_DIAGRAMA_REDE, RM_MATERIAL_OPERACAO, USUARIO_PESSOA_NOME, USUARIO_USUARIO_ID, USUARIO_PESSOA_REFERENCIA, REQUISITANTE_NOME"
				+ ", REQUISITANTE_REFERENCIA, SETOR_CODIGO, SETOR_DESCRICAO, SUB_AREA_CODIGO, SUB_AREA_DESCRICAO, CAST(RM_DATA_EMISSAO AS DATE) as RM_DATA_EMISSAO, RM_DATA_APLICACAO, CAST(RM_MATERIAL_DATA_REC_SUP AS DATE) as RM_MATERIAL_DATA_REC_SUP"
				+ ", RM_MATERIAL_DATA_PREV_ENTR, RM_MATERIAL_DATA_COMPRA, RM_MATERIAL_DATA_ULTIMA_NF, COMPRADOR_NOME, COMPRADOR_EAP, DATA_COMPRA, RM_MATERIAL_NUMERO_REQUISICAO_SAP"
				+ ", RM_MATERIAL_ITEM_REQUISICAO_SAP, RM_MATERIAL_NUMERO_DOC_ENTRADA, RM_MATERIAL_NUMERO_PEDIDO_COMPRA, RM_MATERIAL_DATA_PREVISTA_ENTR, RM_MATERIAL_GRUPO_COMPRADOR, DIAS_PREVISTOS, CAST(DATA_APROV_GERENTE_AREA AS DATE) as DATA_APROV_GERENTE_AREA, NOME_APROV_GERENTE_AREA, GERENTE_AREA"
				+ ", GERENTE_AREA_ID,CAST(DATA_APROV_MAQUINA_PARADA AS DATE) as DATA_APROV_MAQUINA_PARADA, NOME_APROV_MAQUINA_PARADA, CAST(DATA_APROV_GERENTE_CUSTOS AS DATE)  as DATA_APROV_GERENTE_CUSTOS, NOME_APROV_GERENTE_CUSTOS, GERENTE_CUSTO, GERENTE_CUSTO_ID,CAST(DATA_APROV_COORDENADOR AS DATE) as DATA_APROV_COORDENADOR, NOME_APROV_COORDENADOR, COORDENADOR, COORDENADOR_ID, DATA_APROV_RESPONSAVEL_RETIRADA_ESTOQUE"
				+ ", RESPONSAVEL_RETIRADA_ESTOQUE, RESPONSAVEL_RETIRADA_ESTOQUE_ID,CAST(DATA_APROV_COMPLEMENTO_CUSTOS AS DATE) AS DATA_APROV_COMPLEMENTO_CUSTOS, CONFIRMAR_RETIRADA, MATERIAL_IS_SERVICO, QTDE_RECEBIDA, TIPO_REQUISICAO_CODIGO, RM_MATERIAL_FLUXO_MATERIAL"
				+ ", RM_OBSERVACAO, RM_DATA_ENVIO_APROVACAO, RM_DATA_REPROVACAO, TRMR_DATA_RECEBIMENTO, RM_DATA_CANCELAMENTO, RM_MATERIAL_OBSERVACAO, STATUS_CODIGO, COMPRADOR_COMPRADOR_ID, PRIORIDADE_CODIGO"
				+ ", TIPO_MATERIAL_CODIGO, RM_RM_ID, RM_MATERIAL_JUST_ALT_QUANT, RM_MATERIAL_RM_MATERIAL_ID, RM_EAP_MULTI_EMPRESA_ID, RM_EAP_MULTI_EMPRESA_DESCRICAO, DEPOSITO_EAP_MULTI_EMPRESA_DESCRICAO"
				+ ", RM_MATERIAL_STATUS_OBSERVACAO, RM_CENTRO_ID, RM_CENTRO_NOME,RM_CENTRO_IDIOMA, RM_AREA_ID,RM_AREA_IDIOMA, RM_AREA_NOME, (row_number() over (order by RM_DATA_EMISSAO DESC)) as row_lin From VW_CONSULTA_RMA"
				+ " where ID IN(?) ";

		try {

			if (filtro.getPaginacaoVo() != null && paginacao) {
				if (filtro.getPaginacaoVo().getPagina() > 0) {
					int pagina = filtro.getPaginacaoVo().getPagina();
					int qtdeReg = filtro.getPaginacaoVo().getQtdeRegPagina();

					int inicio = ((pagina * qtdeReg) - qtdeReg);

					sqlFinds = sqlFinds +" order by RM.DATA_EMISSAO DESC OFFSET "+ inicio +" ROWS FETCH NEXT "+ qtdeReg +" ROWS ONLY ";
				}
			}

			PreparedStatement  smt = ConnectionFactory.getConnection().prepareStatement(sqlFinds);

			ResultSet resultSet = smt.executeQuery();
			List<Integer> ids = new ArrayList<>();
			while (resultSet.next()) {
				ids.add(resultSet.getInt("ID"));

			}
			if(ids.isEmpty()) {
				return vwConsultaRmas;
			}
			String sqlIN = ids.stream()
					.map(x -> String.valueOf(x))
					.collect(Collectors.joining(",", "(", ")"));

			sql = sql.replace("(?)", sqlIN);

			smt = ConnectionFactory.getConnection().prepareStatement(sql);
			resultSet = smt.executeQuery();

			while (resultSet.next()) {
				VwConsultaRma vwConsultaRma = new VwConsultaRma();
				vwConsultaRma.setId(resultSet.getInt("ID"));
				vwConsultaRma.setRmNumeroRm(resultSet.getString("RM_NUMERO_RM"));
				vwConsultaRma.setPrioridadeDescricao(resultSet.getString("PRIORIDADE_DESCRICAO"));
				vwConsultaRma.setMaterialCodigo(resultSet.getString("MATERIAL_CODIGO"));
				vwConsultaRma.setMaterialNome(resultSet.getString("MATERIAL_NOME"));
				vwConsultaRma.setRmMaterialQuantidade(resultSet.getDouble("RM_MATERIAL_QUANTIDADE"));
				vwConsultaRma.setUnidadeMedidaSigla(resultSet.getString("UNIDADE_MEDIDA_SIGLA"));
				vwConsultaRma.setTipoMaterialDescricao(resultSet.getString("TIPO_MATERIAL_DESCRICAO"));
				vwConsultaRma.setNota(resultSet.getString("NOTA"));
				vwConsultaRma.setStatusNome(resultSet.getString("STATUS_NOME"));
				vwConsultaRma.setTipoRequisicaoDescricao(resultSet.getString("TIPO_REQUISICAO_DESCRICAO"));
				vwConsultaRma.setDepositoCodigo(resultSet.getString("DEPOSITO_CODIGO"));
				vwConsultaRma.setDepositoDescricao(resultSet.getString("DEPOSITO_DESCRICAO"));
				vwConsultaRma.setRmMaterialColetorCustos(resultSet.getString("RM_MATERIAL_COLETOR_CUSTOS"));
				vwConsultaRma.setRmMaterialColetorEstoque(resultSet.getBoolean("RM_MATERIAL_COLETOR_ESTOQUE"));
				vwConsultaRma.setRmMaterialColetorOrdem(resultSet.getString("RM_MATERIAL_COLETOR_ORDEM"));
				vwConsultaRma.setRmMaterialDiagramaRede(resultSet.getString("RM_MATERIAL_DIAGRAMA_REDE"));
				vwConsultaRma.setRmMaterialOperacao(resultSet.getString("RM_MATERIAL_OPERACAO"));
				vwConsultaRma.setUsuarioPessoaNome(resultSet.getString("USUARIO_PESSOA_NOME"));
				vwConsultaRma.setUsuarioUsuarioId(resultSet.getInt("USUARIO_USUARIO_ID"));
				vwConsultaRma.setUsuarioPessoaReferencia(resultSet.getString("USUARIO_PESSOA_REFERENCIA"));
				vwConsultaRma.setRequisitanteNome(resultSet.getString("REQUISITANTE_NOME"));
				vwConsultaRma.setRequisitanteReferencia(resultSet.getString("REQUISITANTE_REFERENCIA"));
				vwConsultaRma.setSetorCodigo(resultSet.getString("SETOR_CODIGO"));
				vwConsultaRma.setSetorDescricao(resultSet.getString("SETOR_DESCRICAO"));
				vwConsultaRma.setSubAreaCodigo(resultSet.getString("SUB_AREA_CODIGO"));
				vwConsultaRma.setSubAreaDescricao(resultSet.getString("SUB_AREA_DESCRICAO"));
				vwConsultaRma.setRmDataEmissao(resultSet.getDate("RM_DATA_EMISSAO"));
				vwConsultaRma.setRmDataAplicacao(resultSet.getDate("RM_DATA_APLICACAO"));
				vwConsultaRma.setRmMaterialDataRecSup(resultSet.getDate("RM_MATERIAL_DATA_REC_SUP"));
				vwConsultaRma.setRmMaterialDataPrevEntr(resultSet.getDate("RM_MATERIAL_DATA_PREV_ENTR"));
				vwConsultaRma.setRmMaterialDataCompra(resultSet.getDate("RM_MATERIAL_DATA_COMPRA"));
				vwConsultaRma.setRmMaterialDataUltimaNf(resultSet.getDate("RM_MATERIAL_DATA_ULTIMA_NF"));
				vwConsultaRma.setCompradorNome(resultSet.getString("COMPRADOR_NOME"));
				vwConsultaRma.setCompradorEap(resultSet.getString("COMPRADOR_EAP"));
				vwConsultaRma.setDataCompra(resultSet.getString("DATA_COMPRA"));
				vwConsultaRma
						.setRmMaterialNumeroRequisicaoSap(resultSet.getString("RM_MATERIAL_NUMERO_REQUISICAO_SAP"));
				vwConsultaRma.setRmMaterialItemRequisicaoSap(resultSet.getInt("RM_MATERIAL_ITEM_REQUISICAO_SAP"));
				vwConsultaRma.setRmMaterialNumeroPedidoCompra(resultSet.getString("RM_MATERIAL_NUMERO_PEDIDO_COMPRA"));
				vwConsultaRma.setRmMaterialNumeroDocEntrada(resultSet.getString("RM_MATERIAL_NUMERO_DOC_ENTRADA"));
				vwConsultaRma.setRmMaterialDataPrevistaEntr(resultSet.getDate("RM_MATERIAL_DATA_PREVISTA_ENTR"));
				vwConsultaRma.setRmMaterialGrupoComprador(resultSet.getString("RM_MATERIAL_GRUPO_COMPRADOR"));
				vwConsultaRma.setDiasPrevistos(resultSet.getInt("DIAS_PREVISTOS"));

				vwConsultaRma.setGerenteAreaId(resultSet.getInt("GERENTE_AREA_ID"));

//				vwConsultaRma.setDataAprovGerenteArea(resultSet.getDate("DATA_APROV_GERENTE_AREA"));
//				vwConsultaRma.setNomeAprovGerenteArea(resultSet.getString("NOME_APROV_GERENTE_AREA"));
//				vwConsultaRma.setDataAprovMaquinaParada(resultSet.getDate("DATA_APROV_MAQUINA_PARADA"));
//				vwConsultaRma.setNomeAprovMaquinaParada(resultSet.getString("NOME_APROV_MAQUINA_PARADA"));
//				vwConsultaRma.setDataAprovGerenteCustos(resultSet.getDate("DATA_APROV_GERENTE_CUSTOS"));
//				vwConsultaRma.setNomeAprovGerenteCustos(resultSet.getString("NOME_APROV_GERENTE_CUSTOS"));
//				vwConsultaRma.setDataAprovCoordenador(resultSet.getDate("DATA_APROV_COORDENADOR"));
//				vwConsultaRma.setNomeAprovCoordenador(resultSet.getString("NOME_APROV_COORDENADOR"));

				vwConsultaRma.setGerenteCustoId(resultSet.getInt("GERENTE_CUSTO_ID"));
				vwConsultaRma.setCoordenadorId(resultSet.getInt("COORDENADOR_ID"));
				vwConsultaRma.setDataAprovResponsavelRetiradaEstoque(
						resultSet.getDate("DATA_APROV_RESPONSAVEL_RETIRADA_ESTOQUE"));
				vwConsultaRma.setResponsavelRetiradaEstoque(resultSet.getString("RESPONSAVEL_RETIRADA_ESTOQUE"));
				vwConsultaRma.setResponsavelRetiradaEstoqueId(resultSet.getInt("RESPONSAVEL_RETIRADA_ESTOQUE_ID"));
				vwConsultaRma.setDataAprovComplementoCustos(resultSet.getDate("DATA_APROV_COMPLEMENTO_CUSTOS"));
				vwConsultaRma.setConfirmarRetirada(resultSet.getBoolean("CONFIRMAR_RETIRADA"));
				vwConsultaRma.setMaterialIsServico(resultSet.getBoolean("MATERIAL_IS_SERVICO"));
				vwConsultaRma.setQtdeRecebida(resultSet.getDouble("QTDE_RECEBIDA"));
				vwConsultaRma.setRmMaterialFluxoMaterial(resultSet.getString("TIPO_REQUISICAO_CODIGO"));
				vwConsultaRma.setRmObservacao(resultSet.getString("RM_OBSERVACAO"));
				vwConsultaRma.setRmDataEnvioAprovacao(resultSet.getDate("RM_DATA_ENVIO_APROVACAO"));
				vwConsultaRma.setRmDataReprovacao(resultSet.getDate("RM_DATA_REPROVACAO"));
					vwConsultaRma.setRmDataRecebimento(resultSet.getDate("TRMR_DATA_RECEBIMENTO"));
				vwConsultaRma.setRmDataCancelamento(resultSet.getDate("RM_DATA_CANCELAMENTO"));
				vwConsultaRma.setRmMaterialObservacao(resultSet.getString("RM_MATERIAL_OBSERVACAO"));
				vwConsultaRma.setStatusCodigo(resultSet.getString("STATUS_CODIGO"));
				vwConsultaRma.setCompradorCompradorId(resultSet.getInt("COMPRADOR_COMPRADOR_ID"));
				vwConsultaRma.setPrioridadeCodigo(resultSet.getString("PRIORIDADE_CODIGO"));
				vwConsultaRma.setTipoMaterialCodigo(resultSet.getString("TIPO_MATERIAL_CODIGO"));
				vwConsultaRma.setRmRmId(resultSet.getInt("RM_RM_ID"));
				vwConsultaRma.setRmMaterialJustAltQuant(resultSet.getString("RM_MATERIAL_JUST_ALT_QUANT"));
				vwConsultaRma.setRmMaterialRmMaterialId(resultSet.getString("RM_MATERIAL_RM_MATERIAL_ID"));
				vwConsultaRma.setRmEapMultiEmpresaId(resultSet.getInt("RM_EAP_MULTI_EMPRESA_ID"));
				vwConsultaRma.setRmEapMultiEmpresaDescricao(resultSet.getString("RM_EAP_MULTI_EMPRESA_DESCRICAO"));
				vwConsultaRma.setDepositoEapMultiEmpresaDescricao(
						resultSet.getString("DEPOSITO_EAP_MULTI_EMPRESA_DESCRICAO"));
				vwConsultaRma.setRmMaterialStatusObservacao(resultSet.getString("RM_MATERIAL_STATUS_OBSERVACAO"));
				vwConsultaRma.setCentroId(resultSet.getInt("RM_CENTRO_ID"));
				vwConsultaRma.setCentroNome(resultSet.getString("RM_CENTRO_NOME"));
				vwConsultaRma.setCentroIdioma(resultSet.getString("RM_CENTRO_IDIOMA"));
				vwConsultaRma.setAreaId(resultSet.getString("RM_AREA_ID"));
				vwConsultaRma.setAreaNome(resultSet.getString("RM_AREA_NOME"));
				vwConsultaRma.setAreaIdioma(resultSet.getString("RM_AREA_IDIOMA"));
				vwConsultaRmas.add(vwConsultaRma);

			}

			smt.close();
			resultSet.close();
		} catch (SQLException e) {
			log.error(e);
		}

		return vwConsultaRmas;
	}

	////////////////////// Serviços ///////////////////////

	public int contaServicos(FiltroCadastroRma filtro) {

		String sql = "Select count(id) as qtde from VW_CONSULTA_RSE WHERE 1 = 1 ";
		int qtdRegistros = 0;

		try {
			if (filtro.getNumero() != null && !filtro.getNumero().isEmpty()) {
				sql = sql + " AND RM_NUMERO_RM = " + filtro.getNumero();
			}

			if (filtro.getEapMultiEmpresa() != null) {
				if ((filtro.getEapMultiEmpresa().getDescricao() != null)
						&& (!filtro.getEapMultiEmpresa().getDescricao().isEmpty())) {
					sql = sql + " AND RM_CENTRO_NOME = '" + filtro.getEapMultiEmpresa().getDescricao() + "'";
				}
			} else {

				if (filtro.getCentros() != null) {
					String centrosIdsString = "";
					List<Integer> centrosIds = new ArrayList<>();
					sql = sql + " AND RM_CENTRO_ID in ( ";

					for (Centro centros : filtro.getCentros()) {
						centrosIds.add(centros.getCentroId());
					}

					centrosIdsString = centrosIds.stream().map(Object::toString).collect(Collectors.joining(","));
					sql = sql + centrosIdsString;
					sql = sql + " ) ";

				}
			}

			if (filtro.getUsuarioCadastrante() != null) {
				if ((filtro.getUsuarioCadastrante().getNome()) != null
						&& (!filtro.getUsuarioCadastrante().getNome().isEmpty())) {
					sql = sql + " AND USUARIO_PESSOA_NOME = '" + filtro.getUsuarioCadastrante().getNome() + "'";
				}
			}

			if (filtro.getAprovador() != null) {
				if ((filtro.getAprovador().getNome() != null) && (!filtro.getAprovador().getNome().isEmpty())) {
					sql = sql + " AND (NOME_APROV_GERENTE_AREA = '" + filtro.getAprovador().getNome()
							+ "' OR NOME_APROV_GERENTE_CUSTOS = '" + filtro.getAprovador().getNome()
							+ "' OR NOME_APROV_MAQUINA_PARADA = '" + filtro.getAprovador().getNome()
							+ "' OR NOME_APROV_COORDENADOR = '" + filtro.getAprovador().getNome() + "')";
				}
			}

			if (filtro.getStatus() != null) {
				if ((filtro.getStatus().getNome() != null) && (!filtro.getStatus().getNome().isEmpty())) {
					sql = sql + " AND STATUS_NOME = '" + filtro.getStatus().getNome() + "'";
				}
			}

			if (filtro.getArea() != null) {
				if ((filtro.getArea().getDescricao() != null) && (!filtro.getArea().getDescricao().isEmpty())) {
					sql = sql + " AND RM_AREA_NOME = '" + filtro.getArea().getDescricao() + "'";
				}
			}

			if (filtro.getTipoRequisicao() != null) {
				if ((filtro.getTipoRequisicao() != null)) {
					sql = sql + " AND TIPO_REQUISICAO_DESCRICAO = '" + filtro.getTipoRequisicao().getDescricao() + "'";
				}
			}

			if (filtro.getPrioridade() != null) {
				if ((filtro.getPrioridade().getDescricao() != null)
						&& (!filtro.getPrioridade().getDescricao().isEmpty())) {
					sql = sql + " AND PRIORIDADE_DESCRICAO = '" + filtro.getPrioridade().getDescricao() + "'";
				}
			}

			if (filtro.getComprador() != null) {
				if ((filtro.getComprador().getNome() != null) && (!filtro.getComprador().getNome().isEmpty())) {
					sql = sql + " AND PRIORIDADE_DESCRICAO = '" + filtro.getPrioridade().getDescricao() + "'";
				}
			}

			if (filtro.getStDataEmissaoAte() != null && !filtro.getStDataEmissaoAte().isEmpty()) {
				sql = sql + " AND RM_DATA_EMISSAO <= Convert(date,'" + filtro.getStDataEmissaoAte() + "', 103)";
			}

			if (filtro.getStDataEmissaoDe() != null && !filtro.getStDataEmissaoDe().isEmpty()) {
				sql = sql + " AND RM_DATA_EMISSAO >= Convert(date,'" + filtro.getStDataEmissaoDe() + "', 103)";
			}

			if (filtro.getStDataNecessidadeAte() != null && !filtro.getStDataNecessidadeAte().isEmpty()) {
				sql = sql + " AND RM_DATA_APLICACAO <= Convert(date,'" + filtro.getStDataNecessidadeAte() + "', 103)";
			}

			if (filtro.getStDataNecessidadeDe() != null && !filtro.getStDataNecessidadeDe().isEmpty()) {
				sql = sql + " AND RM_DATA_APLICACAO >= Convert(date,'" + filtro.getStDataNecessidadeDe() + "', 103)";
			}

			PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);
			ResultSet result = smt.executeQuery();

			while (result.next()) {
				qtdRegistros = Integer.parseInt(result.getString("qtde"));
			}

			smt.close();
			result.close();
		} catch (SQLException e) {
			log.error(e);
		}
		return qtdRegistros;
	}

	public List<Object> listaServicos(FiltroCadastroRma filtro, final boolean paginacao) {
		List<Object> vwConsultaRmas = new ArrayList<>();

		String sql = ""
				+ "Select * FROM (Select ID, RM_AREA_CODIGO, RM_NUMERO_RM, PRIORIDADE_DESCRICAO, STATUS_NOME, TIPO_REQUISICAO_DESCRICAO, DEPOSITO_CODIGO, DEPOSITO_DESCRICAO"
				+ ", USUARIO_PESSOA_NOME, USUARIO_USUARIO_ID, USUARIO_PESSOA_REFERENCIA, REQUISITANTE_NOME, REQUISITANTE_REFERENCIA, SETOR_CODIGO, SETOR_DESCRICAO, SUB_AREA_CODIGO, SUB_AREA_DESCRICAO, CAST(RM_DATA_EMISSAO AS DATE) as RM_DATA_EMISSAO, RM_DATA_APLICACAO"
				+ ", CAST(DATA_APROV_GERENTE_AREA AS DATE) as DATA_APROV_GERENTE_AREA, NOME_APROV_GERENTE_AREA, GERENTE_AREA"
				+ ", GERENTE_AREA_ID,CAST(DATA_APROV_MAQUINA_PARADA AS DATE) as DATA_APROV_MAQUINA_PARADA, NOME_APROV_MAQUINA_PARADA, CAST(DATA_APROV_GERENTE_CUSTOS AS DATE)  as DATA_APROV_GERENTE_CUSTOS, NOME_APROV_GERENTE_CUSTOS, GERENTE_CUSTO, GERENTE_CUSTO_ID,CAST(DATA_APROV_COORDENADOR AS DATE) as DATA_APROV_COORDENADOR, NOME_APROV_COORDENADOR, COORDENADOR, COORDENADOR_ID, DATA_APROV_RESPONSAVEL_RETIRADA_ESTOQUE"
				+ ", RESPONSAVEL_RETIRADA_ESTOQUE, RESPONSAVEL_RETIRADA_ESTOQUE_ID,CAST(DATA_APROV_COMPLEMENTO_CUSTOS AS DATE) AS DATA_APROV_COMPLEMENTO_CUSTOS, QTDE_RECEBIDA, TIPO_REQUISICAO_CODIGO"
				+ ", RM_OBSERVACAO, RM_DATA_ENVIO_APROVACAO, RM_DATA_REPROVACAO, RM_DATA_CANCELAMENTO, STATUS_CODIGO, PRIORIDADE_CODIGO"
				+ ", RM_RM_ID, RM_EAP_MULTI_EMPRESA_ID, RM_EAP_MULTI_EMPRESA_DESCRICAO, DEPOSITO_EAP_MULTI_EMPRESA_DESCRICAO"
				+ ", RM_MATERIAL_STATUS_OBSERVACAO, RM_CENTRO_ID,RM_CENTRO_ID, RM_CENTRO_NOME, RM_AREA_ID,RM_AREA_IDIOMA, RM_AREA_NOME, "
				+ "RM_SERVICO_ID ,ABRANGENCIA_ESCOPO, AGUA, ALIMENTACAO ,ALOJAMENTO, ANDAIME"
				+ ",ATESTACAO_EMPRESA,BANHEIRO_QUIMICO,CERTIFICADOS_EXIGIDOS,CESTA_BASICA"
				+ ",CODIGO_SAP,CONDICAO_TRABALHO_OBSERVACOES,CRACHA_UNIFORME,CURRICULO_COLABORADORES,DATA_EMISSAO,DATA_INICIO,"
				+ "DATA_NECESSIDADE,DATA_TERMINO,DESPESAS_VIAGEM,ELEMENTO_PEP,ENERGIA,EPCS,EPIS,ESCOPO_SERVICO,ESCRITORIO,EXAMES_ADMISSIONAIS"
				+ ",EXAMES_NECESSARIOS,GARANTIAS,GUINDASTE,IMPOSTOS,JORNADA_TRABALHO,LOCAL_PRESTACAO_SERVICO,MARCOS_PARCIAIS,MOBILIARIO"
				+ ",NUMERO_ACOMPANHAMENTO,OPCOES_NAT_OPERACAO,OPCOES_TP_SOLICITACAO,PARTICULARIDADES_SERVICOS_OUTROS"
				+ ",PEQUENO_PORTE,PERMITIDO_TRABALHO_DIAS_ESPECIAIS,PERMITIDO_TRABALHO_HORARIO_EXTRAORDINARIO"
				+ ",PERMITIDO_TRABALHO_NOTURNO,PLANO_ODONTOLOGICO,PRAZO_INTEGRACAO,PRAZO_TOTAL,RESPONSABILIDADE_OBSERVACOES"
				+ ",SEGURO,TEMPO_MEDIO_ESPERA_MATERIAIS,TEMPO_MEDIO_ESPERA_PESSOAL,TRANSPORTE,VEICULOS,ORDEM"
				+ ",PRAZO_INICIO,QUANTIDADE,CONDICAO_TRABALHO_OBSERVACAO ,RESPONSABILIDADES_OUTROS1 ,RESPONSABILIDADES_OUTROS2,NUMERO_REQ_SAP, ENV_SAP, "
				+ "(row_number() over (order by RM_DATA_EMISSAO DESC)) as row_lin From VW_CONSULTA_RSE"
				+ " where 1 = 1 ";

		try {
			if (filtro.getNumero() != null && !filtro.getNumero().isEmpty()) {
				sql = sql + " AND RM_NUMERO_RM = " + filtro.getNumero();
			}

			
			if (filtro.getEapMultiEmpresa() != null) {
				if ((filtro.getEapMultiEmpresa().getDescricao() != null)
						&& (!filtro.getEapMultiEmpresa().getDescricao().isEmpty())) {
					sql = sql + " AND RM_CENTRO_NOME = '" + filtro.getEapMultiEmpresa().getDescricao() + "'";
				}
			} else {

				if (filtro.getCentros() != null) {
					String centrosIdsString = "";
					List<Integer> centrosIds = new ArrayList<>();
					sql = sql + " AND RM_CENTRO_ID in ( ";

					for (Centro centros : filtro.getCentros()) {
						centrosIds.add(centros.getCentroId());
					}

					centrosIdsString = centrosIds.stream().map(Object::toString).collect(Collectors.joining(","));
					sql = sql + centrosIdsString;
					sql = sql + " ) ";

				}
			}

			if (filtro.getUsuarioCadastrante() != null) {
				if ((filtro.getUsuarioCadastrante().getNome()) != null
						&& (!filtro.getUsuarioCadastrante().getNome().isEmpty())) {
					sql = sql + " AND USUARIO_PESSOA_NOME = '" + filtro.getUsuarioCadastrante().getNome() + "'";
				}
			}

			if (filtro.getAprovador() != null) {
				if ((filtro.getAprovador().getNome() != null) && (!filtro.getAprovador().getNome().isEmpty())) {
					sql = sql + " AND (NOME_APROV_GERENTE_AREA = '" + filtro.getAprovador().getNome()
							+ "' OR NOME_APROV_GERENTE_CUSTOS = '" + filtro.getAprovador().getNome()
							+ "' OR NOME_APROV_MAQUINA_PARADA = '" + filtro.getAprovador().getNome()
							+ "' OR NOME_APROV_COORDENADOR = '" + filtro.getAprovador().getNome() + "')";
				}
			}

			if (filtro.getStatus() != null) {
				if ((filtro.getStatus().getNome() != null) && (!filtro.getStatus().getNome().isEmpty())) {
					sql = sql + " AND STATUS_NOME = '" + filtro.getStatus().getNome() + "'";
				}
			}

			if (filtro.getArea() != null) {
				if ((filtro.getArea().getDescricao() != null) && (!filtro.getArea().getDescricao().isEmpty())) {
					sql = sql + " AND RM_AREA_NOME = '" + filtro.getArea().getDescricao() + "'";
				}
			}

			if (filtro.getTipoRequisicao() != null) {
				if ((filtro.getTipoRequisicao() != null)) {
					sql = sql + " AND TIPO_REQUISICAO_DESCRICAO = '" + filtro.getTipoRequisicao().getDescricao() + "'";
				}
			}

			if (filtro.getPrioridade() != null) {
				if ((filtro.getPrioridade().getDescricao() != null)
						&& (!filtro.getPrioridade().getDescricao().isEmpty())) {
					sql = sql + " AND PRIORIDADE_DESCRICAO = '" + filtro.getPrioridade().getDescricao() + "'";
				}
			}

			if (filtro.getComprador() != null) {
				if ((filtro.getComprador().getNome() != null) && (!filtro.getComprador().getNome().isEmpty())) {
					sql = sql + " AND PRIORIDADE_DESCRICAO = '" + filtro.getPrioridade().getDescricao() + "'";
				}
			}

			// 10/23/2016 23:01:05
			String formatDateTime = "yyyy-MM-dd HH:mm:ss";
			String formatDate = "dd/MM/yyyy";

			DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern(formatDate);
			DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern(formatDateTime);

			if (filtro.getStDataEmissaoAte() != null && !filtro.getStDataEmissaoAte().isEmpty()) {
				ZonedDateTime dataEmissaoFim = LocalDate.parse(filtro.getStDataEmissaoAte(), formatterDate)
						.atStartOfDay(ZoneId.systemDefault()).withHour(23).withMinute(59).withSecond(59).withNano(999);
				sql = sql + " AND RM_DATA_EMISSAO <= Convert(datetime,'" + dataEmissaoFim.format(formatterDateTime)
						+ "', 20)";
			}

			if (filtro.getStDataEmissaoDe() != null && !filtro.getStDataEmissaoDe().isEmpty()) {
				ZonedDateTime dataEmissaoInicio = LocalDate.parse(filtro.getStDataEmissaoDe(), formatterDate)
						.atStartOfDay(ZoneId.systemDefault());
				sql = sql + " AND RM_DATA_EMISSAO >= Convert(datetime,'" + dataEmissaoInicio.format(formatterDateTime)
						+ "', 20)";
			}

			if (filtro.getStDataNecessidadeAte() != null && !filtro.getStDataNecessidadeAte().isEmpty()) {
				ZonedDateTime dataEmissaoFim = LocalDate.parse(filtro.getStDataNecessidadeAte(), formatterDate)
						.atStartOfDay(ZoneId.systemDefault()).withHour(23).withMinute(59).withSecond(59).withNano(999);
				sql = sql + " AND RM_DATA_APLICACAO <= Convert(datetime,'" + dataEmissaoFim.format(formatterDateTime)
						+ "', 20)";
			}

			if (filtro.getStDataNecessidadeDe() != null && !filtro.getStDataNecessidadeDe().isEmpty()) {
				ZonedDateTime dataEmissaoInicio = LocalDate.parse(filtro.getStDataNecessidadeDe(), formatterDate)
						.atStartOfDay(ZoneId.systemDefault());
				sql = sql + " AND RM_DATA_APLICACAO >= Convert(datetime,'" + dataEmissaoInicio.format(formatterDateTime)
						+ "', 20)";
			}

			sql = sql + " )X ";

			if (filtro.getPaginacaoVo() != null && paginacao) {
				if (filtro.getPaginacaoVo().getPagina() > 0) {
					int pagina = filtro.getPaginacaoVo().getPagina();
					int qtdeReg = filtro.getPaginacaoVo().getQtdeRegPagina();

					int inicio = ((pagina * qtdeReg) - qtdeReg);
					inicio = inicio + 1;

					int fim = (pagina * qtdeReg);

					sql = sql + "WHERE X.row_lin >= " + inicio + " AND X.row_lin <= " + fim;
				}
			}

			PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);

			ResultSet resultSet = smt.executeQuery();

			while (resultSet.next()) {
				VwConsultaRma vwConsultaRma = new VwConsultaRma();
				vwConsultaRma.setId(resultSet.getInt("ID"));
				vwConsultaRma.setAreaCodigo(resultSet.getString("RM_AREA_CODIGO"));
				vwConsultaRma.setRmNumeroRm(resultSet.getString("RM_NUMERO_RM"));
				vwConsultaRma.setPrioridadeDescricao(resultSet.getString("PRIORIDADE_DESCRICAO"));
				vwConsultaRma.setStatusNome(resultSet.getString("STATUS_NOME"));
				vwConsultaRma.setTipoRequisicaoDescricao(resultSet.getString("TIPO_REQUISICAO_DESCRICAO"));
				vwConsultaRma.setDepositoCodigo(resultSet.getString("DEPOSITO_CODIGO"));
				vwConsultaRma.setDepositoDescricao(resultSet.getString("DEPOSITO_DESCRICAO"));
				vwConsultaRma.setUsuarioPessoaNome(resultSet.getString("USUARIO_PESSOA_NOME"));
				vwConsultaRma.setUsuarioUsuarioId(resultSet.getInt("USUARIO_USUARIO_ID"));
				vwConsultaRma.setUsuarioPessoaReferencia(resultSet.getString("USUARIO_PESSOA_REFERENCIA"));
				vwConsultaRma.setRequisitanteNome(resultSet.getString("REQUISITANTE_NOME"));
				vwConsultaRma.setRequisitanteReferencia(resultSet.getString("REQUISITANTE_REFERENCIA"));
				vwConsultaRma.setSetorCodigo(resultSet.getString("SETOR_CODIGO"));
				vwConsultaRma.setSetorDescricao(resultSet.getString("SETOR_DESCRICAO"));
				vwConsultaRma.setSubAreaCodigo(resultSet.getString("SUB_AREA_CODIGO"));
				vwConsultaRma.setSubAreaDescricao(resultSet.getString("SUB_AREA_DESCRICAO"));
				vwConsultaRma.setRmDataEmissao(resultSet.getDate("RM_DATA_EMISSAO"));
				vwConsultaRma.setRmDataAplicacao(resultSet.getDate("RM_DATA_APLICACAO"));

				vwConsultaRma.setGerenteAreaId(resultSet.getInt("GERENTE_AREA_ID"));

//				vwConsultaRma.setDataAprovGerenteArea(resultSet.getDate("DATA_APROV_GERENTE_AREA"));
//				vwConsultaRma.setNomeAprovGerenteArea(resultSet.getString("NOME_APROV_GERENTE_AREA"));
//				vwConsultaRma.setDataAprovMaquinaParada(resultSet.getDate("DATA_APROV_MAQUINA_PARADA"));
//				vwConsultaRma.setNomeAprovMaquinaParada(resultSet.getString("NOME_APROV_MAQUINA_PARADA"));
//				vwConsultaRma.setDataAprovGerenteCustos(resultSet.getDate("DATA_APROV_GERENTE_CUSTOS"));
//				vwConsultaRma.setNomeAprovGerenteCustos(resultSet.getString("NOME_APROV_GERENTE_CUSTOS"));
//				vwConsultaRma.setDataAprovCoordenador(resultSet.getDate("DATA_APROV_COORDENADOR"));
//				vwConsultaRma.setNomeAprovCoordenador(resultSet.getString("NOME_APROV_COORDENADOR"));

				vwConsultaRma.setGerenteCustoId(resultSet.getInt("GERENTE_CUSTO_ID"));
				vwConsultaRma.setCoordenadorId(resultSet.getInt("COORDENADOR_ID"));
				vwConsultaRma.setDataAprovResponsavelRetiradaEstoque(
						resultSet.getDate("DATA_APROV_RESPONSAVEL_RETIRADA_ESTOQUE"));
				vwConsultaRma.setResponsavelRetiradaEstoque(resultSet.getString("RESPONSAVEL_RETIRADA_ESTOQUE"));
				vwConsultaRma.setResponsavelRetiradaEstoqueId(resultSet.getInt("RESPONSAVEL_RETIRADA_ESTOQUE_ID"));
				vwConsultaRma.setDataAprovComplementoCustos(resultSet.getDate("DATA_APROV_COMPLEMENTO_CUSTOS"));
				vwConsultaRma.setRmObservacao(resultSet.getString("RM_OBSERVACAO"));
				vwConsultaRma.setRmDataEnvioAprovacao(resultSet.getDate("RM_DATA_ENVIO_APROVACAO"));
				vwConsultaRma.setRmDataReprovacao(resultSet.getDate("RM_DATA_REPROVACAO"));
				vwConsultaRma.setRmDataCancelamento(resultSet.getDate("RM_DATA_CANCELAMENTO"));
				vwConsultaRma.setStatusCodigo(resultSet.getString("STATUS_CODIGO"));
				vwConsultaRma.setPrioridadeCodigo(resultSet.getString("PRIORIDADE_CODIGO"));
				vwConsultaRma.setRmRmId(resultSet.getInt("RM_RM_ID"));
				vwConsultaRma.setCentroId(resultSet.getInt("RM_CENTRO_ID"));
				vwConsultaRma.setCentroNome(resultSet.getString("RM_CENTRO_NOME"));
				vwConsultaRma.setCentroIdioma(resultSet.getString("RM_CENTRO_IDIOMA"));
				vwConsultaRma.setAreaId(resultSet.getString("RM_AREA_ID"));
				vwConsultaRma.setAreaNome(resultSet.getString("RM_AREA_NOME"));
				vwConsultaRma.setAreaIdioma(resultSet.getString("RM_AREA_IDIOMA"));
				vwConsultaRma.setNumeroReqSap(resultSet.getString("NUMERO_REQ_SAP"));
				vwConsultaRma.setEnvSap(resultSet.getString("ENV_SAP"));
				vwConsultaRmas.add(vwConsultaRma);

			}

			smt.close();
			resultSet.close();
		} catch (SQLException e) {
			log.error(e);
		}

		return vwConsultaRmas;
	}

	public List<RmServico> dadosServicos(int idRm) {
		List<RmServico> vwConsultaRmas = new ArrayList<>();

		String sql = "" + "SELECT [RM_SERVICO_ID] " + "      ,[ABRANGENCIA_ESCOPO] " + "      ,[AGUA] "
				+ "      ,[ALIMENTACAO] " + "      ,[ALOJAMENTO] " + "      ,[ANDAIME] " + "      ,[ATESTACAO_EMPRESA] "
				+ "      ,[BANHEIRO_QUIMICO] " + "      ,[CERTIFICADOS_EXIGIDOS] " + "      ,[CESTA_BASICA] "
				+ "      ,[CODIGO_SAP] " + "      ,[C].[CODIGO_ID] AS ID_CODIGO_SAP"
				+ "      ,[CONDICAO_TRABALHO_OBSERVACOES] " + "      ,[CRACHA_UNIFORME] "
				+ "      ,[CURRICULO_COLABORADORES] " + "      ,[DATA_EMISSAO] " + "      ,[DATA_INICIO] "
				+ "      ,[DATA_NECESSIDADE] " + "      ,[DATA_TERMINO] " + "      ,[DESPESAS_VIAGEM] "
				+ "      ,[ELEMENTO_PEP] " + "      ,[ENERGIA] " + "      ,[EPCS] " + "      ,[EPIS] "
				+ "      ,[ESCOPO_SERVICO] " + "      ,[ESCRITORIO] " + "      ,[EXAMES_ADMISSIONAIS] "
				+ "      ,[EXAMES_NECESSARIOS] " + "      ,[GARANTIAS] " + "      ,[GUINDASTE] " + "      ,[IMPOSTOS] "
				+ "      ,[JORNADA_TRABALHO] " + "      ,[LOCAL_PRESTACAO_SERVICO] " + "      ,[MARCOS_PARCIAIS] "
				+ "      ,[MOBILIARIO] " + "      ,[NUMERO_ACOMPANHAMENTO] " + "      ,[OPCOES_NAT_OPERACAO] "
				+ "      ,[OPCOES_TP_SOLICITACAO] " + "      ,[PARTICULARIDADES_SERVICOS_OUTROS] "
				+ "      ,[PEQUENO_PORTE] " + "      ,[PERMITIDO_TRABALHO_DIAS_ESPECIAIS] "
				+ "      ,[PERMITIDO_TRABALHO_HORARIO_EXTRAORDINARIO] " + "      ,[PERMITIDO_TRABALHO_NOTURNO] "
				+ "      ,[PLANO_ODONTOLOGICO] " + "      ,[PRAZO_INTEGRACAO] " + "      ,[PRAZO_TOTAL] "
				+ "      ,[RESPONSABILIDADE_OBSERVACOES] " + "      ,[SEGURO] "
				+ "      ,[TEMPO_MEDIO_ESPERA_MATERIAIS] " + "      ,[TEMPO_MEDIO_ESPERA_PESSOAL] "
				+ "      ,[TRANSPORTE] " + "      ,[VEICULOS] " + "      ,[RM_ID] " + "      ,[ORDEM] "
				+ "      ,[PRAZO_INICIO] " + "      ,[QUANTIDADE] " + "      ,[CONDICAO_TRABALHO_OBSERVACAO] "
				+ "      ,[RESPONSABILIDADES_OUTROS1] " + "      ,[RESPONSABILIDADES_OUTROS2] " + "      ,[ANEXO1_OBS] "
				+ "      ,[ANEXO2_OBS] " + "      ,[ANEXO3_OBS] " + "      ,[ANEXO4_OBS] " + "      ,[ANEXO5_OBS] "
				+ "      ,[ANEXO_OBSERVACOES] " + "      ,[ENV_SAP] " + "      ,[NUMERO_REQ_SAP] "
				+ "  FROM [dbo].[TB_RM_SERVICO] "
				+ "  INNER JOIN TB_SERVICO_CODIGO_SAP AS C ON CODIGO_SAP = C.DESCRICAO " + " WHERE RM_ID = " + idRm;

		try {
			PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);

			ResultSet resultSet = smt.executeQuery();

			while (resultSet.next()) {
				RmServico vwConsultaRma = new RmServico();

				vwConsultaRma.setRmServicoId(resultSet.getInt("RM_SERVICO_ID"));
				vwConsultaRma.setAbrangenciaEscopo(resultSet.getString("ABRANGENCIA_ESCOPO"));
				vwConsultaRma.setAgua(resultSet.getString("AGUA"));
				vwConsultaRma.setAlimentacao(resultSet.getString("ALIMENTACAO"));
				vwConsultaRma.setAlojamento(resultSet.getString("ALOJAMENTO"));
				vwConsultaRma.setAndaime(resultSet.getString("ANDAIME"));
				vwConsultaRma.setAtestacaoEmpresa(resultSet.getString("ATESTACAO_EMPRESA"));
				vwConsultaRma.setBanheiroQuimico(resultSet.getString("BANHEIRO_QUIMICO"));
				vwConsultaRma.setCertificadosExigidos(resultSet.getString("CERTIFICADOS_EXIGIDOS"));
				vwConsultaRma.setCestaBasica(resultSet.getString("CESTA_BASICA"));
				vwConsultaRma.setCodigoSap(resultSet.getString("CODIGO_SAP"));
				vwConsultaRma.setIdCodigoSap(resultSet.getInt("ID_CODIGO_SAP"));
				vwConsultaRma.setCondicaoTrabalhoObservacao(resultSet.getString("CONDICAO_TRABALHO_OBSERVACOES"));
				vwConsultaRma.setCrachaUniforme(resultSet.getString("CRACHA_UNIFORME"));
				vwConsultaRma.setCurriculoColaboradores(resultSet.getString("CURRICULO_COLABORADORES"));
				vwConsultaRma.setDataEmissao(resultSet.getDate("DATA_EMISSAO"));
				vwConsultaRma.setDataInicio(resultSet.getDate("DATA_INICIO"));
				vwConsultaRma.setDataNecessidade(resultSet.getDate("DATA_NECESSIDADE"));
				vwConsultaRma.setDataTermino(resultSet.getDate("DATA_TERMINO"));
				vwConsultaRma.setDespesasViagem(resultSet.getString("DESPESAS_VIAGEM"));
				vwConsultaRma.setElementoPep(resultSet.getString("ELEMENTO_PEP"));
				vwConsultaRma.setEnergia(resultSet.getString("ENERGIA"));
				vwConsultaRma.setEpcs(resultSet.getString("EPCS"));
				vwConsultaRma.setEpis(resultSet.getString("EPIS"));
				vwConsultaRma.setEscopoServico(resultSet.getString("ESCOPO_SERVICO"));
				vwConsultaRma.setEscritorio(resultSet.getString("ESCRITORIO"));
				vwConsultaRma.setExamesAdmissionais(resultSet.getString("EXAMES_ADMISSIONAIS"));
				vwConsultaRma.setExamesNecessarios(resultSet.getString("EXAMES_NECESSARIOS"));
				vwConsultaRma.setGarantias(resultSet.getString("GARANTIAS"));
				vwConsultaRma.setGuindaste(resultSet.getString("GUINDASTE"));
				vwConsultaRma.setImpostos(resultSet.getString("IMPOSTOS"));
				vwConsultaRma.setJornadaTrabalho(resultSet.getString("JORNADA_TRABALHO"));
				vwConsultaRma.setLocalPrestacaoServico(resultSet.getString("LOCAL_PRESTACAO_SERVICO"));
				vwConsultaRma.setMarcosParciais(resultSet.getString("MARCOS_PARCIAIS"));
				vwConsultaRma.setMobiliario(resultSet.getString("MOBILIARIO"));
				vwConsultaRma.setNumeroAcompanhamento(resultSet.getString("NUMERO_ACOMPANHAMENTO"));
				vwConsultaRma.setOpcoesNatOperacao(resultSet.getString("OPCOES_NAT_OPERACAO"));
				vwConsultaRma.setOpcoesTpSolicitacao(resultSet.getString("OPCOES_TP_SOLICITACAO"));
				vwConsultaRma
						.setParticularidadesServicosOutros(resultSet.getString("PARTICULARIDADES_SERVICOS_OUTROS"));
				vwConsultaRma.setPequenoPorte(resultSet.getString("PEQUENO_PORTE"));
				vwConsultaRma
						.setPermitidoTrabalhoDiasEspeciais(resultSet.getString("PERMITIDO_TRABALHO_DIAS_ESPECIAIS"));
				vwConsultaRma.setPermitidoTrabalhoHorarioExtraordinario(
						resultSet.getString("PERMITIDO_TRABALHO_HORARIO_EXTRAORDINARIO"));
				vwConsultaRma.setPermitidoTrabalhoNoturno(resultSet.getString("PERMITIDO_TRABALHO_NOTURNO"));
				vwConsultaRma.setPlanoOdontologico(resultSet.getString("PLANO_ODONTOLOGICO"));
				vwConsultaRma.setPrazoIntegracao(resultSet.getString("PRAZO_INTEGRACAO"));
				vwConsultaRma.setPrazoTotal(resultSet.getString("PRAZO_TOTAL"));
				vwConsultaRma.setResponsabilidadeObservacoes(resultSet.getString("RESPONSABILIDADE_OBSERVACOES"));
				vwConsultaRma.setResponsabilidadesOutros1(resultSet.getString("RESPONSABILIDADES_OUTROS1"));
				vwConsultaRma.setResponsabilidadesOutros2(resultSet.getString("RESPONSABILIDADES_OUTROS2"));
				vwConsultaRma.setSeguro(resultSet.getString("SEGURO"));
				vwConsultaRma.setTempoMedioEsperaMateriais(resultSet.getString("TEMPO_MEDIO_ESPERA_MATERIAIS"));
				vwConsultaRma.setTempoMedioEsperaPessoal(resultSet.getString("TEMPO_MEDIO_ESPERA_PESSOAL"));
				vwConsultaRma.setTransporte(resultSet.getString("TRANSPORTE"));
				vwConsultaRma.setVeiculos(resultSet.getString("VEICULOS"));
				// vwConsultaRma.setI(resultSet.getInt("RM_ID"));
				vwConsultaRma.setOrdem(resultSet.getInt("ORDEM"));
				vwConsultaRma.setQuantidade(Double.valueOf(resultSet.getInt("QUANTIDADE")));
				vwConsultaRma.setCondicaoTrabalhoObservacao(resultSet.getString("CONDICAO_TRABALHO_OBSERVACAO"));
				vwConsultaRma.setAnexo1(resultSet.getString("ANEXO1_OBS"));
				vwConsultaRma.setAnexo2(resultSet.getString("ANEXO2_OBS"));
				vwConsultaRma.setAnexo3(resultSet.getString("ANEXO3_OBS"));
				vwConsultaRma.setAnexo4(resultSet.getString("ANEXO4_OBS"));
				vwConsultaRma.setAnexo5(resultSet.getString("ANEXO5_OBS"));
				vwConsultaRma.setAnexoObservacoes(resultSet.getString("ANEXO_OBSERVACOES"));

				vwConsultaRma.setEnviadoSap(resultSet.getString("ENV_SAP"));
				vwConsultaRma.setNumeroReqSap(resultSet.getString("NUMERO_REQ_SAP"));

				vwConsultaRmas.add(vwConsultaRma);

			}

			smt.close();
			resultSet.close();
		} catch (SQLException e) {
			log.error(e);
		}
		return vwConsultaRmas;
	}

	public List<CodigoSap> listarIdCodigoSap(String descricao) {
		List<CodigoSap> idCodigoSap = new ArrayList<>();

		return idCodigoSap;
	}
}
