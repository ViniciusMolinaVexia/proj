package br.com.nextage.rmaweb.service.integracao;


import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.EapMultiEmpresa;
import br.com.nextage.rmaweb.entitybean.MultiEmpresa;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.entitybean.UsuarioEapMultiEmpresa;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.*;

/**
 * @author Lucas Heitor
 */
public class EapMultiEmpresaService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    @Context
    HttpServletRequest request;

    /**
     * *
     * <p/>
     * Seleciona as EapsMultiEmpresa do usuario, e as eaps que estão na
     * hierarquia das mesmas
     *
     * @param user
     * @return Lista de EapMultiEmpresa
     */
    public List<EapMultiEmpresa> getEapsMultiEmpresaDoUsuario(Usuario user) {
        //Usa o LinkedHashSet para não repetir os elementos e manter a ordem dos elementos
        Set<EapMultiEmpresa> hash = new LinkedHashSet<>();
        // Seleciona as eaps que estão vinculadas ao usuário
        List<UsuarioEapMultiEmpresa> aux = listarUsuarioEapMultiEmpresa(user);
        if (aux != null && !aux.isEmpty()) {
            for (UsuarioEapMultiEmpresa eapUser : aux) {
                // Pra cada eap do usuário, busca a lista progressiva de sua hierarquia
                List<EapMultiEmpresa> eapMultiAux = listaHierarquiaProgressiva(eapUser.getEapMultiEmpresa());
                if (eapMultiAux != null && !eapMultiAux.isEmpty()) {
                    hash.addAll(eapMultiAux);
                }
            }
        }

        logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Usuario.class.getName(), Util.getNomeMetodoAtual(), user, null));

        return new ArrayList<>(hash);
    }


    /**
     * @param usuario
     * @return
     * @author: Alyson Xavier Leite
     * @date: 13/02/2015
     * <p/>
     * Retorna a lista de USUARIO_EAP_MULTI_EMPRESA
     */
    public List<UsuarioEapMultiEmpresa> listarUsuarioEapMultiEmpresa(Usuario usuario) {
        List<UsuarioEapMultiEmpresa> lista = null;
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Usuario.class.getName(), Util.getNomeMetodoAtual(), usuario, lista));

            String aliasUsuario = NxCriterion.montaAlias(UsuarioEapMultiEmpresa.ALIAS_CLASSE, UsuarioEapMultiEmpresa.USUARIO);
            String aliasEapMultiEmpresa = NxCriterion.montaAlias(UsuarioEapMultiEmpresa.ALIAS_CLASSE, UsuarioEapMultiEmpresa.EAP_MULTI_EMPRESA);
            String aliasMultiEmpresa = NxCriterion.montaAlias(UsuarioEapMultiEmpresa.ALIAS_CLASSE, UsuarioEapMultiEmpresa.EAP_MULTI_EMPRESA, EapMultiEmpresa.MULTI_EMPRESA);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(UsuarioEapMultiEmpresa.ID));

            propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));

            propriedades.add(new Propriedade(Usuario.USUARIO_ID, Usuario.class, aliasUsuario));

            propriedades.add(new Propriedade(MultiEmpresa.ID, MultiEmpresa.class, aliasMultiEmpresa));
            propriedades.add(new Propriedade(MultiEmpresa.DESCRICAO, MultiEmpresa.class, aliasMultiEmpresa));

            Filtro filter = new Filtro(UsuarioEapMultiEmpresa.USUARIO, usuario, Filtro.EQUAL);
            NxCriterion criterion = NxCriterion.montaRestriction(filter);
            NxOrder nx = new NxOrder(UsuarioEapMultiEmpresa.EAP_MULTI_EMPRESA + "." + EapMultiEmpresa.CODIGO, NxOrder.NX_ORDER.ASC);
            List<NxOrder> nxOrders = Arrays.asList(nx);

            GenericDao<UsuarioEapMultiEmpresa> dao = new GenericDao<>();
            lista = dao.listarByFilter(propriedades, nxOrders, UsuarioEapMultiEmpresa.class, Constantes.NO_LIMIT, criterion);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Usuario.class.getName(), Util.getNomeMetodoAtual(), usuario, lista));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    /**
     * @param eap
     * @param filtro
     * @author: Alyson Xavier Leite
     * @data: 13/02/2015
     * <p/>
     * Retorna a lista de TODA a hierarquia da EAP_MULTI_EMPRESA informada
     * @return: Lista EapMultiEmpresa
     */
    public List<EapMultiEmpresa> listaHierarquiaProgressiva(EapMultiEmpresa eap) {
        List<EapMultiEmpresa> listaEaps = null;
        int qtdeMax = Constantes.NO_LIMIT;
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, listaEaps));
            if (eap == null) {
                return null;
            }

            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(EapMultiEmpresa.CODIGO, NxOrder.NX_ORDER.ASC));

            String aliasMultiEmpresa = NxCriterion.montaAlias(EapMultiEmpresa.ALIAS_CLASSE, EapMultiEmpresa.MULTI_EMPRESA);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(EapMultiEmpresa.ID));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO));
            propriedades.add(new Propriedade(EapMultiEmpresa.APROVACAO_COORDENADOR));
            propriedades.add(new Propriedade(EapMultiEmpresa.APROVACAO_EQUIPE_CUSTOS));

            propriedades.add(new Propriedade(MultiEmpresa.ID, MultiEmpresa.class, aliasMultiEmpresa));
            propriedades.add(new Propriedade(MultiEmpresa.DESCRICAO, MultiEmpresa.class, aliasMultiEmpresa));

            // Restriction para pegar as eaps filhas somente da multiEmpresa passada da eap pai
            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(EapMultiEmpresa.MULTI_EMPRESA, eap.getMultiEmpresa(), Filtro.EQUAL));

            // Recupera a hierarquia da eap passada como parâmetro
            NxCriterion nxCriterionEap = NxCriterion.montaCriterionEapProgressiva(eap.getCodigo(), "");
            NxCriterion criterioAux = null;

            nxCriterion = NxCriterion.and(nxCriterion, nxCriterionEap);


            GenericDao<EapMultiEmpresa> dao = new GenericDao<>();
            listaEaps = dao.listarByFilter(propriedades, nxOrders, EapMultiEmpresa.class, qtdeMax, nxCriterion);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, listaEaps));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return listaEaps;
    }

    /**
     * @param eap
     * @param filtro
     * @author: Lucas Heitor
     * @data: 17/08/2016
     * <p/>
     * Retorna a lista com todas EAP'S Multi Empresas cadastradas.
     * @return: Lista EapMultiEmpresa
     */
    public List<EapMultiEmpresa> listarTodasEapMultiEmpresa() {
        List<EapMultiEmpresa> listaEaps = null;
        int qtdeMax = Constantes.NO_LIMIT;
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, listaEaps));


            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(EapMultiEmpresa.CODIGO, NxOrder.NX_ORDER.ASC));

            String aliasMultiEmpresa = NxCriterion.montaAlias(EapMultiEmpresa.ALIAS_CLASSE, EapMultiEmpresa.MULTI_EMPRESA);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(EapMultiEmpresa.ID));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO));

            propriedades.add(new Propriedade(MultiEmpresa.ID, MultiEmpresa.class, aliasMultiEmpresa));
            propriedades.add(new Propriedade(MultiEmpresa.DESCRICAO, MultiEmpresa.class, aliasMultiEmpresa));


            GenericDao<EapMultiEmpresa> dao = new GenericDao<>();
            listaEaps = dao.listarByFilter(propriedades, nxOrders, EapMultiEmpresa.class, qtdeMax, null);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, listaEaps));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return listaEaps;
    }

    /**
     * @param centro
     * @author: Marlos Morbis Novo
     * @data: 22/08/2016
     * <p/>Retorna a EAP de acordo com o centro.
     * @return: EapMultiEmpresa
     */
    public EapMultiEmpresa getEapMultiEmpresaByCentro(String centro) {
        EapMultiEmpresa eap = null;
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(EapMultiEmpresa.ID));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO));

            NxCriterion criterion = NxCriterion.montaRestriction(new Filtro(EapMultiEmpresa.CENTRO, centro, Filtro.EQUAL));

            GenericDao<EapMultiEmpresa> genericDao = new GenericDao<>();
            eap = (EapMultiEmpresa) genericDao.selectUnique(propriedades, EapMultiEmpresa.class, criterion);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return eap;
    }

    /**
     * @param eap
     * @param filtro
     * @author: Lucas Heitor
     * @data: 17/08/2016
     * <p/>
     * Retorna a lista com todas EAP'S Multi Empresas cadastradas.
     * @return: Lista EapMultiEmpresa
     */
    public EapMultiEmpresa getFirstEapMultiEmpresa() {
        List<EapMultiEmpresa> listaEaps = null;
        EapMultiEmpresa eap = null;
        int qtdeMax = Constantes.NO_LIMIT;
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, listaEaps));


            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(EapMultiEmpresa.CODIGO, NxOrder.NX_ORDER.ASC));

            String aliasMultiEmpresa = NxCriterion.montaAlias(EapMultiEmpresa.ALIAS_CLASSE, EapMultiEmpresa.MULTI_EMPRESA);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(EapMultiEmpresa.ID));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO));
            propriedades.add(new Propriedade(EapMultiEmpresa.APROVACAO_EQUIPE_CUSTOS));
            propriedades.add(new Propriedade(EapMultiEmpresa.APROVACAO_COORDENADOR));

            propriedades.add(new Propriedade(MultiEmpresa.ID, MultiEmpresa.class, aliasMultiEmpresa));
            propriedades.add(new Propriedade(MultiEmpresa.DESCRICAO, MultiEmpresa.class, aliasMultiEmpresa));


            GenericDao<EapMultiEmpresa> dao = new GenericDao<>();
            listaEaps = dao.listarByFilter(propriedades, nxOrders, EapMultiEmpresa.class, 1, null);
            if(listaEaps != null && listaEaps.size() > 0){
                eap = listaEaps.get(0);
            }

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, listaEaps));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return eap;
    }
}
