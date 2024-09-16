package br.com.nextage.rmaweb.controller.pessoa.obterPorPerfilECentro;

import java.util.function.Function;

public class ObterPessoaPorPerfilECentroModelToResponseMapper {

  public Function<ObterPessoaPorPerfilECentro, ObterPessoaPorPerfilECentroResponse> mapper = model -> {
    ObterPessoaPorPerfilECentroResponse response = new ObterPessoaPorPerfilECentroResponse();
    response.setNome(model.getNome());
    response.setEmail(model.getEmail());
    response.setCentroId(model.getCentroId());
    response.setPerfilId(model.getPerfilId());
    response.setLogin(model.getLogin());
    return response;
  };
}
