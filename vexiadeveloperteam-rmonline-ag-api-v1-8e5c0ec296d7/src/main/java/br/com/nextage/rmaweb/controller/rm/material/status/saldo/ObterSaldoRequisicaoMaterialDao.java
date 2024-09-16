package br.com.nextage.rmaweb.controller.rm.material.status.saldo;

import br.com.nextage.rmaweb.utils.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang.ObjectUtils.defaultIfNull;

public class ObterSaldoRequisicaoMaterialDao {

	public List<ObterSaldoRequisicaoMaterialResponse> obterSaldo(Collection<Integer> idsRmas) throws SQLException {
        List<ObterSaldoRequisicaoMaterialResponse> saldosMaterial = new ArrayList<>();

        String sql = "SELECT\n"
			+ "   RM.RM_MATERIAL_ID as RM_MATERIAL_ID,\n"
			+ "   RM.QUANTIDADE as QUANTIDADE_SOLICITADA,\n"
			+ "   QUANTIDADE_COMPRADA,\n"
			+ "   QUANTIDADE_RECEBIDA\n"
			+ "FROM\n"
			+ "   TB_RM_MATERIAL RM \n"
			+ "   left join (select RM_MATERIAL_ID, max(QUANTIDADE) as QUANTIDADE_COMPRADA\n"
			+ "	from TB_REQ_MAT_COMPRA \n"
			+ "	group by RM_MATERIAL_ID)  MC on MC.RM_MATERIAL_ID = RM.RM_MATERIAL_ID \n"
			+ "	left join (select RM_MATERIAL_ID, sum(QUANTIDADE) as QUANTIDADE_RECEBIDA\n"
			+ "	from TB_REQ_MAT_RECEBIDO 	\n"
			+ "	group by RM_MATERIAL_ID) MR on MR.RM_MATERIAL_ID = RM.RM_MATERIAL_ID \n"
			+ "WHERE\n"
			+ "   RM.RM_MATERIAL_ID IN (?);";

        String rmaIds = idsRmas.stream()
                .map(x -> String.valueOf(x))
                .collect(Collectors.joining(",", "(", ")"));

        sql = sql.replace("(?)", rmaIds);
        PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);
        ResultSet resultSet = smt.executeQuery();

        while (resultSet.next()) {
            ObterSaldoRequisicaoMaterialResponse saldoMaterial = new ObterSaldoRequisicaoMaterialResponse();
            saldoMaterial.setIdRmMaterial((Integer) defaultIfNull(resultSet.getInt("RM_MATERIAL_ID"), 0));
            saldoMaterial.setQuantidadeComprada((Integer) defaultIfNull(resultSet.getInt("QUANTIDADE_COMPRADA"), 0));
            saldoMaterial.setQuantidadeRecebida((Integer) defaultIfNull(resultSet.getInt("QUANTIDADE_RECEBIDA"), 0));
            saldoMaterial.setQuantidadeSolicitada((Integer) defaultIfNull(resultSet.getInt("QUANTIDADE_SOLICITADA"), 0));
            saldoMaterial.setRmMaterialNumeroDocEntrada(getGrupoNumeroDocumentos(saldoMaterial.getIdRmMaterial()));
            saldosMaterial.add(saldoMaterial);
        }

        smt.close();
        resultSet.close();

        return saldosMaterial;
    }

	private String getGrupoNumeroDocumentos(Integer idRmMaterial)throws SQLException {
		String sql = "select DOC_RECEBIMENTO from TB_REQ_MAT_RECEBIDO where  RM_MATERIAL_ID = ?";
		PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);
		smt.setInt(1, idRmMaterial);
		ResultSet resultSet = smt.executeQuery();
		String numeroDocumentos = "";
		String separador="";
		while (resultSet.next()) {
			numeroDocumentos +=separador+ resultSet.getString("DOC_RECEBIMENTO");
			separador=",";
		}
		return numeroDocumentos;
	}
}
