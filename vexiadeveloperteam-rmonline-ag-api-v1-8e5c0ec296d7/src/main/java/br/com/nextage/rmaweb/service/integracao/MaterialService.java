package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jerry
 */
public class MaterialService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    public MaterialService() {

    }


    /**
     * Pesquisa hierarquia por material.
     *
     * @param material
     * @return
     */
    public String getHierarquiaByMaterial(Material material) {
        String hierarquia = null;
        try {
            if(material != null && material.getMaterialId()!= null && material.getMaterialId() > 0) {
                // Seta as propriedades de retorno da consulta.
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(Material.MATERIAL_ID));
                propriedades.add(new Propriedade(Material.HIERARQUIA));

                NxCriterion nxCrit = NxCriterion.montaRestriction(new Filtro(Material.MATERIAL_ID, material.getMaterialId(), Filtro.EQUAL));

                GenericDao<Material> genericDao = new GenericDao<>();
                Material mat = genericDao.selectUnique(propriedades, Material.class, nxCrit);

                if (mat != null) {
                    hierarquia = mat.getHierarquia();
                }
            }
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return hierarquia;
    }

    /**
     * Pesquisa Material por codigo.
     *
     * @param codigo
     * @return Material
     */
    public Material getMaterialByCodigo(String codigo) {
        Material material = null;
        try {
            if(codigo != null && codigo.trim().length() > 0) {
                // Seta as propriedades de retorno da consulta.
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(Material.MATERIAL_ID));
                propriedades.add(new Propriedade(Material.NOME));
                propriedades.add(new Propriedade(Material.CODIGO));

                NxCriterion nxCrit = NxCriterion.montaRestriction(new Filtro(Material.CODIGO, codigo, Filtro.EQUAL));

                GenericDao<Material> genericDao = new GenericDao<>();
                material = genericDao.selectUnique(propriedades, Material.class, nxCrit);

            }
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return material;
    }

}
