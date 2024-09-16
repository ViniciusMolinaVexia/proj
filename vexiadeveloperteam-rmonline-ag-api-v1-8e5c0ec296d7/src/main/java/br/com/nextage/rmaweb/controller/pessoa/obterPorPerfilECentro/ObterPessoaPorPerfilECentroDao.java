package br.com.nextage.rmaweb.controller.pessoa.obterPorPerfilECentro;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.nextage.rmaweb.utils.ConnectionFactory;

public class ObterPessoaPorPerfilECentroDao implements Serializable {

    public List<ObterPessoaPorPerfilECentro> obterPessoaPorPerfilECentro(Integer perfilId, Integer centroId) throws SQLException {
        List<ObterPessoaPorPerfilECentro> pessoas = new ArrayList<>();


        String sql = "SELECT p.NOME, p.EMAIL, u.LOGIN, u.PERFIL_ID, u.CENTRO_ID\n"
          + "FROM TB_PESSOA p\n"
          + "INNER JOIN TB_USUARIO u on p.PESSOA_ID = u.PESSOA_ID\n"
          + "WHERE u.PERFIL_ID = " + perfilId
          + "  and u.CENTRO_ID = " + centroId;


        PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);
        ResultSet resultSet = smt.executeQuery();

        while (resultSet.next()) {
            ObterPessoaPorPerfilECentro pessoa = new ObterPessoaPorPerfilECentro();
            pessoa.setNome(resultSet.getString("NOME"));
            pessoa.setEmail(resultSet.getString("EMAIL"));
            pessoa.setLogin(resultSet.getString("LOGIN"));
            pessoa.setPerfilId(resultSet.getInt("PERFIL_ID"));
            pessoa.setCentroId(resultSet.getInt("CENTRO_ID"));
            pessoas.add(pessoa);
        }

        smt.close();
        resultSet.close();

        return pessoas;
    }
}
