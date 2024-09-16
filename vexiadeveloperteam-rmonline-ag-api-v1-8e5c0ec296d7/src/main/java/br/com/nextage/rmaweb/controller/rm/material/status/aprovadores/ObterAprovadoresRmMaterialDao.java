package br.com.nextage.rmaweb.controller.rm.material.status.aprovadores;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.nextage.rmaweb.utils.ConnectionFactory;

public class ObterAprovadoresRmMaterialDao implements Serializable {

    public List<ObterAprovadoresRmMaterial> obterAprovadoresMaterial(Integer idRm, Integer idRmMaterial) throws SQLException {
        List<ObterAprovadoresRmMaterial> listaStatus = new ArrayList<>();


        String sql = "SELECT\n" +
                "        rm.RM_ID,\n" +
                "                rm_mat.RM_MATERIAL_ID,\n" +
                "                mat_st.DATA_HORA_STATUS,\n" +
                "                mat_st.USUARIO,\n" +
                "                st.CODIGO,\n" +
                "                st.ID as STATUS_ID,\n" +
                "        st.NOME as STATUS_NAME\n" +
                "        FROM TB_RM_MATERIAL_STATUS mat_st\n" +
                "        INNER JOIN TB_STATUS st ON st.ID = mat_st.STATUS_ID\n" +
                "        INNER JOIN TB_RM_MATERIAL rm_mat ON rm_mat.RM_MATERIAL_ID = mat_st.RM_MATERIAL_ID\n" +
                "        INNER JOIN TB_RM rm ON rm.RM_ID = rm_mat.RM_ID\n" +
                "        WHERE  1=1";

        if (idRm != null && idRmMaterial != null) {
            sql = sql + " AND mat_st.RM_MATERIAL_ID = " + idRmMaterial;
            sql = sql + " AND rm.RM_ID = " + idRm;
        }

        PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);
        ResultSet resultSet = smt.executeQuery();

        while (resultSet.next()) {
            ObterAprovadoresRmMaterial statusMaterial = new ObterAprovadoresRmMaterial();
            statusMaterial.setIdRm(resultSet.getInt("RM_ID"));
            statusMaterial.setIdRmMaterial(resultSet.getInt("RM_MATERIAL_ID"));
            statusMaterial.setDataStatus(resultSet.getDate("DATA_HORA_STATUS"));
            statusMaterial.setNomeUsuario(resultSet.getString("USUARIO"));
            statusMaterial.setCodigoStatus(resultSet.getString("CODIGO"));
            statusMaterial.setIdStatus(resultSet.getInt("STATUS_ID"));
            statusMaterial.setNomeStatus(resultSet.getString("STATUS_NAME"));
            listaStatus.add(statusMaterial);
        }

        smt.close();
        resultSet.close();

        return listaStatus;
    }
}
