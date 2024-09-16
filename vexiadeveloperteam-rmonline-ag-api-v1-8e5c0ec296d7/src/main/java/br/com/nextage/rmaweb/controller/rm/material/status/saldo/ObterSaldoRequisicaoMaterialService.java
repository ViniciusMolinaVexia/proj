package br.com.nextage.rmaweb.controller.rm.material.status.saldo;

import java.util.Collection;
import java.util.List;

public interface ObterSaldoRequisicaoMaterialService {
    List<ObterSaldoRequisicaoMaterialResponse> obterSaldo(Collection<Integer> idsRms) throws Exception;
}
