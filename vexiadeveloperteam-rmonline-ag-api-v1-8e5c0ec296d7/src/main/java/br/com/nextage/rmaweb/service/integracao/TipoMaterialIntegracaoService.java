package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.TipoMaterial;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author l.pordeus
 */
public class TipoMaterialIntegracaoService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    private List<TipoMaterial> listaTipoMaterial;

    public TipoMaterialIntegracaoService() {
        listaTipoMaterial = new ArrayList<>();
    }

    /**
     * Lista todas as tipoMaterial do BD.
     */
    private void listarTipoMaterials() {
        try {

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID));
            propriedades.add(new Propriedade(TipoMaterial.DESCRICAO));
            propriedades.add(new Propriedade(TipoMaterial.CODIGO));

            //Obtem elementos.
            GenericDao<TipoMaterial> genericDao = new GenericDao<>();
            listaTipoMaterial = genericDao.listarByFilter(propriedades, null, TipoMaterial.class, Constantes.NO_LIMIT, null);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
    }

    /**
     * Pesquisa tipoMaterial com base no codigo passado por parametro.
     *
     * @param codigo
     * @return
     */
    public TipoMaterial searchTipoMaterial(String codigo) {
        try {
            if (listaTipoMaterial == null || listaTipoMaterial.isEmpty()) {
                listarTipoMaterials();
            }

            for (TipoMaterial tipoMaterial : listaTipoMaterial) {
                if (tipoMaterial.getCodigo().equals(codigo)) {
                    return tipoMaterial;
                }
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return null;
    }
}
