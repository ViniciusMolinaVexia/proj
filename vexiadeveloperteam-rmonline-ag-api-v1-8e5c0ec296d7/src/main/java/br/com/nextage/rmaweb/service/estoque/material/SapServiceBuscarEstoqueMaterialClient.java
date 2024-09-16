package br.com.nextage.rmaweb.service.estoque.material;

import br.com.nextage.rmaweb.service.ConsultaEstoqueMaterialSap;
import br.com.nextage.rmaweb.service.ConsultaEstoqueMaterialSapResponse;

public interface SapServiceBuscarEstoqueMaterialClient {

  ConsultaEstoqueMaterialSapResponse zglRmaBuscaEstoqueMaterial(final ConsultaEstoqueMaterialSap consulta);

}
