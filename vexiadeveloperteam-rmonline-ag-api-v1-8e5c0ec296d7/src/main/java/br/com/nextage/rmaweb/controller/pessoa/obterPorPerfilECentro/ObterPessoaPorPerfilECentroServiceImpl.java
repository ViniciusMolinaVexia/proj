package br.com.nextage.rmaweb.controller.pessoa.obterPorPerfilECentro;

import java.util.List;
import javax.inject.Inject;

public class ObterPessoaPorPerfilECentroServiceImpl implements ObterPessoaPorPerfilECentroService {

    public ObterPessoaPorPerfilECentroServiceImpl() {
    }

    @Inject
    private ObterPessoaPorPerfilECentroDao dao;

    @Override
    public List<ObterPessoaPorPerfilECentro> obterPorPerfilECentro(Integer perfilId, Integer centroId)
      throws Exception {
        try {
            return dao.obterPessoaPorPerfilECentro(perfilId, centroId);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
