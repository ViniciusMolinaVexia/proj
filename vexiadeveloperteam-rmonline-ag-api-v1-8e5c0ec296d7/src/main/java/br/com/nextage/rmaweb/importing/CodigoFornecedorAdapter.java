/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.importing;

import br.com.nextage.rmaweb.entitybean.Fornecedor;
import br.com.nextage.rmaweb.service.FornecedorService;
import br.com.nextage.util.adapter.MappedPropertyAdapter;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Moyses
 */
public class CodigoFornecedorAdapter implements MappedPropertyAdapter {

    private FornecedorService fornecedorService;
    private Map<String, Fornecedor> cacheMap;

    public CodigoFornecedorAdapter() {
        fornecedorService = new FornecedorService();
        cacheMap = new TreeMap<>();
    }

    @Override
    public Object adapt(Object value) throws Exception {
        Fornecedor fornecedor = null;
        String codigoFornecedor = null;

        if (value instanceof Double) {
            codigoFornecedor = String.valueOf(((Double) value).intValue());
        } else if (value instanceof String) {
            codigoFornecedor = value.toString();
        } else if (value instanceof Integer) {
            codigoFornecedor = Integer.toString((Integer) value);
        }

        if (codigoFornecedor != null) {
            fornecedor = cacheMap.get(codigoFornecedor);

            if (fornecedor == null) {
                fornecedor = fornecedorService.selectByCodigo(codigoFornecedor);
                //System.out.print("Fetching fornecedor from database with codigo " + codigoFornecedor);
                if (fornecedor != null) {
                    cacheMap.put(codigoFornecedor, fornecedor);
                    //System.out.println(" ok (id = " + fornecedor.getFornecedorId() + ")");
                } else {
                    //System.out.println(" not found");
                }
            } else {
                //System.out.println("Returning cached Fornecedor with codigo " + codigoFornecedor);
            }
        }
        return fornecedor;
    }
}
