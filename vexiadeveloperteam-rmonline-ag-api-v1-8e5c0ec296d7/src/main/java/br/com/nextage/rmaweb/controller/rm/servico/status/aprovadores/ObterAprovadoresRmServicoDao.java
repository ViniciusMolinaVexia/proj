package br.com.nextage.rmaweb.controller.rm.servico.status.aprovadores;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.nextage.rmaweb.utils.ConnectionFactory;

public class ObterAprovadoresRmServicoDao implements Serializable {

    public List<ObterAprovadoresRmServico> obterAprovadoresServico(Integer idRm, Integer idRmServico) throws SQLException {
        List<ObterAprovadoresRmServico> listaStatus = new ArrayList<>();


        String sql = "SELECT\n" +
                "        rm.RM_ID,\n" +
                "                rm_mat.RM_SERVICO_ID,\n" +
                "                mat_st.DATA_HORA_STATUS,\n" +
                "                mat_st.USUARIO,\n" +
                "                st.CODIGO,\n" +
                "                st.ID as STATUS_ID,\n" +
                "        st.NOME as STATUS_NAME\n" +
                "        FROM TB_RM_SERVICO_STATUS mat_st\n" +
                "        INNER JOIN TB_STATUS st ON st.ID = mat_st.STATUS_ID\n" +
                "        INNER JOIN TB_RM_SERVICO rm_mat ON rm_mat.RM_SERVICO_ID = mat_st.RM_SERVICO_ID\n" +
                "        INNER JOIN TB_RM rm ON rm.RM_ID = rm_mat.RM_ID\n" +
                "        WHERE  1=1";

        if (idRm != null && idRmServico != null) {
            //sql = sql + " AND mat_st.RM_SERVICO_ID = " + idRmServico;
            sql = sql + " AND rm.RM_ID = " + idRm;
        }

        PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);
        ResultSet resultSet = smt.executeQuery();

        while (resultSet.next()) {
            ObterAprovadoresRmServico statusServico = new ObterAprovadoresRmServico();
            statusServico.setIdRm(resultSet.getInt("RM_ID"));
            statusServico.setIdRmServico(resultSet.getInt("RM_SERVICO_ID"));
            statusServico.setDataStatus(resultSet.getDate("DATA_HORA_STATUS"));
            statusServico.setNomeUsuario(resultSet.getString("USUARIO"));
            statusServico.setCodigoStatus(resultSet.getString("CODIGO"));
            statusServico.setIdStatus(resultSet.getInt("STATUS_ID"));
            statusServico.setNomeStatus(resultSet.getString("STATUS_NAME"));
            listaStatus.add(statusServico);
        }

        smt.close();
        resultSet.close();

        return listaStatus;
    }
}
