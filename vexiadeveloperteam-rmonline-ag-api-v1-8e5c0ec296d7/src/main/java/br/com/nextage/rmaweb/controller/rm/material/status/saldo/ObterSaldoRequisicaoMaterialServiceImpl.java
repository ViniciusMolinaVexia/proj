package br.com.nextage.rmaweb.controller.rm.material.status.saldo;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

public class ObterSaldoRequisicaoMaterialServiceImpl implements ObterSaldoRequisicaoMaterialService {

    @Inject
    private ObterSaldoRequisicaoMaterialDao obterSaldoRequisicaoMaterialDao;

    @Override
    public List<ObterSaldoRequisicaoMaterialResponse> obterSaldo(Collection<Integer> idsRms) throws Exception {
        try {
            return obterSaldoRequisicaoMaterialDao.obterSaldo(idsRms);
        } catch (Exception e) {
            throw e;
        }
    }
}
