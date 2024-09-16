/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Configuracao;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.LogInterfaceVo;
import br.com.nextage.rmaweb.vo.integracao.EstornoVo;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Igor R. Pessoa
 */
public class EstornoIntegracaoService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    public List<LogInterfaceVo> salvar(String login, List<EstornoVo> estornoVo) {
        Info info = new Info();
        LogInterfaceVo logVo;
        List<LogInterfaceVo> listaLog= new ArrayList<>();
        List<Propriedade> propriedades = new ArrayList<>();
        NxResourceBundle rb = new NxResourceBundle(null);
        GenericDao<RmMaterial> genericDao = new GenericDao<>();
        NxCriterion nxCriterion = null;
        RmMaterial rmMaterial = null;
        Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();
        List<String> codigosCentro = null;

        if (configuracao.getCentroCod() != null) {
            codigosCentro = Arrays.asList(configuracao.getCentroCod().split(";"));
        }

        try {
            for (EstornoVo e : estornoVo) {
                if (codigosCentro != null && !codigosCentro.contains(e.getCentroCod())) {
                    info.setCodigo(Info.INFO_002);
                    info.setTipo("E");
                    info.setMensagem(rb.getString("msg_requisicao_cancelada_erro"));
                    info.setObjeto(e);
                    logVo = new LogInterfaceVo(info);
                    listaLog.add(logVo);

                    continue;
                }
                String aliasRm = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);
                String aliasMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL);
                //RM_MATERIAL
                propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
                propriedades.add(new Propriedade(RmMaterial.NUMERO_REQUISICAO_SAP));
                propriedades.add(new Propriedade(RmMaterial.ORDEM));
                propriedades.add(new Propriedade(RmMaterial.RM));
                propriedades.add(new Propriedade(RmMaterial.MATERIAL));

                //RM
                propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
                propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));
                //MATERIAL
                propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
                propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));

                nxCriterion = NxCriterion.montaRestriction(new Filtro(Rm.NUMERO_RM, e.getNumeroRma(), Filtro.EQUAL, aliasRm));
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Material.CODIGO, e.getCodigoMaterial(), Filtro.EQUAL, aliasMaterial)));
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterial.NUMERO_REQUISICAO_SAP, e.getRmSap(), Filtro.EQUAL)));
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterial.ORDEM, Integer.parseInt(e.getItemRmSap()), Filtro.EQUAL)));

                //Encontro material
                rmMaterial = genericDao.selectUnique(propriedades, RmMaterial.class, nxCriterion);

                //Se o material existir, atualizo o número da requisição SAP para null
                //Assim poderá ser requisitado novamente
                if (rmMaterial != null) {
                    propriedades = new ArrayList<>();
                    propriedades.add(new Propriedade(RmMaterial.NUMERO_REQUISICAO_SAP));
                    rmMaterial.setNumeroRequisicaoSap(null);
                    genericDao.update(rmMaterial, propriedades);
                } else {
                    info.setCodigo(Info.INFO_002);
                    info.setTipo("E");
                    info.setMensagem(rb.getString("msg_requisicao_cancelada_erro"));
                    info.setObjeto(e);
                    logVo = new LogInterfaceVo(info);
                    listaLog.add(logVo);

                }
            }
            //Caso a lista esteja vazia, todos os estornos foram feitos
            if (listaLog.isEmpty()) {
                info.setCodigo(Info.INFO_001);
                info.setTipo("S");
                info.setMensagem(rb.getString(Constantes.REG_SALVO_SUCESSO_I18N));
                logVo = new LogInterfaceVo(info);
                listaLog.add(logVo);

            }
        } catch (Exception e) {
            info.setCodigo(Info.INFO_002);
            info.setTipo("E");
            info.setMensagem(rb.getString("msg_requisicao_cancelada_erro"));
            info.setErro(Util.stackTraceToString(e));
            logVo = new LogInterfaceVo(info);
            listaLog.add(logVo);

            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return listaLog;
    }
}
