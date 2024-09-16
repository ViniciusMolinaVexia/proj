package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Deposito;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.entitybean.MaterialDeposito;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor R. Pessoa
 */
public class MaterialDepositoService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    @Context
    HttpServletRequest request;

    public MaterialDepositoService() {
    }

    public MaterialDepositoService(HttpServletRequest request) {
        this.request = request;
    }


    /**
     * Pesquisa quantidade de itens do material no deposito utilizando RmMaterial.
     *
     * @param rmMaterial
     * @return
     */
    public double getQuantidadeMaterialDeposito(RmMaterial rmMaterial) {
        MaterialDeposito materialDeposito = null;
        List<MaterialDeposito> lista = new ArrayList<>();
        Material material = rmMaterial.getMaterial();
        Deposito deposito = rmMaterial.getDeposito();
        double quantidadeMaterial = 0;
        try {
            String aliasMaterial = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), MaterialDeposito.class.getName(), Util.getNomeMetodoAtual(), rmMaterial, null));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

            NxCriterion nxCriterion;

            //Listar os aprovadores de acordo com a rm
            nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, material, Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion,NxCriterion.montaRestriction(new Filtro(MaterialDeposito.DEPOSITO, deposito, Filtro.EQUAL)));

            GenericDao<MaterialDeposito> genericDao = new GenericDao<>();
            lista = genericDao.listarByFilter(propriedades,null, MaterialDeposito.class, Constantes.NO_LIMIT, nxCriterion);
            if(lista != null && !lista.isEmpty()){
                materialDeposito = lista.get(0);
            }
            if(materialDeposito!=null && materialDeposito.getQuantidade() != null) {
                quantidadeMaterial = materialDeposito.getQuantidade();
            }else {
                quantidadeMaterial = 0;
            }
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), MaterialDeposito.class.getName(), Util.getNomeMetodoAtual(), rmMaterial, null));

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return quantidadeMaterial;
    }

}
