package br.com.nextage.rmaweb.controller;


import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.persistence_2.util.Paginacao;
import br.com.nextage.persistence_2.vo.PaginacaoVo;
import br.com.nextage.rmaweb.entitybean.Comprador;
import br.com.nextage.rmaweb.entitybean.EapMultiEmpresa;
import br.com.nextage.rmaweb.entitybean.HierarquiaComprador;
import br.com.nextage.rmaweb.filtro.FiltroHierarquiaComprador;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Path("HierarquiaCompradorController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HierarquiaCompradorController {

    private static final Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    @Context
    HttpServletRequest request;


    /**
     * Lista as rmAprovador que estao na vez e que o usuario logado seja o
     * aprovador.
     * <p/>
     * Utilizado na tela de aprovação pelo sistema
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PaginacaoVo listar(FiltroHierarquiaComprador filtro) {

        try {
            List<Propriedade> propriedades = new ArrayList<>();
            List<Propriedade> propriedadesAux = new ArrayList<>();
            NxCriterion nxCriterion, nxCriterionAux;
            String aliasEapMultiEmpresa = NxCriterion.montaAlias(Comprador.ALIAS_CLASSE, Comprador.EAP_MULTI_EMPRESA);

            propriedades.add(new Propriedade(Comprador.COMPRADOR_ID));
            propriedades.add(new Propriedade(Comprador.NOME));
            propriedades.add(new Propriedade(Comprador.ATIVO));

            propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));


            nxCriterion = NxCriterion.montaRestriction(new Filtro(Comprador.ATIVO, Constantes.NAO_ABREVIADO, Filtro.NOT_EQUAL));

            List<NxOrder> orders = Arrays.asList(new NxOrder(Comprador.NOME, NxOrder.NX_ORDER.ASC));
            if (filtro.getComprador() != null && filtro.getComprador().getNome() != null && !filtro.getComprador().getNome().isEmpty()) {
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Comprador.NOME, filtro.getComprador().getNome(), Filtro.LIKE)));
            }

            if (filtro.getHierarquia() != null && !filtro.getHierarquia().equals("")) {
                String aliasComprador = NxCriterion.montaAlias(HierarquiaComprador.ALIAS_CLASSE, HierarquiaComprador.COMPRADOR);

                GenericDao<HierarquiaComprador> dao = new GenericDao<>();

                propriedadesAux.add(new Propriedade(HierarquiaComprador.HIERARQUIA_COMPRADOR_ID));

                propriedadesAux.add(new Propriedade(Comprador.COMPRADOR_ID, Comprador.class, aliasComprador));
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(HierarquiaComprador.HIERARQUIA,filtro.getHierarquia(), Filtro.LIKE));
                List<HierarquiaComprador> lista = dao.listarByFilter(propriedadesAux,null, HierarquiaComprador.class,Constantes.NO_LIMIT,nxCriterionAux);
                List<Integer> listaId = new ArrayList<>();
                for(HierarquiaComprador hc : lista)
                    listaId.add(hc.getComprador().getCompradorId());
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Comprador.COMPRADOR_ID, listaId, Filtro.IN)));
            }
            Paginacao.build(propriedades, orders, Comprador.class, nxCriterion, filtro.getPaginacaoVo());
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return filtro.getPaginacaoVo();
    }

    @POST
    @Path("selectById")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PaginacaoVo selectById(FiltroHierarquiaComprador filtro) {
        try {
            List<Propriedade> propriedades = new ArrayList<>();
            String aliasComprador = NxCriterion.montaAlias(HierarquiaComprador.ALIAS_CLASSE, HierarquiaComprador.COMPRADOR);
            String aliasEapMultiEmpresa = NxCriterion.montaAlias(HierarquiaComprador.ALIAS_CLASSE, HierarquiaComprador.COMPRADOR, Comprador.EAP_MULTI_EMPRESA);

            propriedades.add(new Propriedade(HierarquiaComprador.HIERARQUIA_COMPRADOR_ID));
            propriedades.add(new Propriedade(HierarquiaComprador.HIERARQUIA));
            propriedades.add(new Propriedade(HierarquiaComprador.COMPRADOR_VEZ));

            propriedades.add(new Propriedade(Comprador.COMPRADOR_ID, Comprador.class, aliasComprador));
            propriedades.add(new Propriedade(Comprador.NOME, Comprador.class, aliasComprador));
            propriedades.add(new Propriedade(Comprador.ATIVO, Comprador.class, aliasComprador));

            propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Comprador.COMPRADOR_ID, filtro.getComprador().getCompradorId(), Filtro.EQUAL, aliasComprador));
            if (filtro.getHierarquiaBuscar() != null) {
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(HierarquiaComprador.HIERARQUIA, filtro.getHierarquiaBuscar(), Filtro.LIKE)));
            }

            List<NxOrder> orders = Arrays.asList(new NxOrder(Comprador.COMPRADOR_ID, NxOrder.NX_ORDER.ASC));

            Paginacao.build(propriedades, null, HierarquiaComprador.class, nxCriterion, filtro.getPaginacaoVo());

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return filtro.getPaginacaoVo();
    }

    @POST
    @Path("excluir")
    @Consumes("application/json")
    public Info excluir(HierarquiaComprador hierarquiaComprador) {
        GenericDao<HierarquiaComprador> genericDao = new GenericDao<>();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
        Info info;
        try {
            if (hierarquiaComprador != null) {
                genericDao.delete(hierarquiaComprador);
            }
            info = new Info(false,rb.getString("msg_registro_exclusao_sucesso"),hierarquiaComprador);
        } catch (Exception e) {
            info = Info.GetError(Constantes.ERRO_OPERACAO_I18N);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;


    }


    @POST
    @Path("salvar")
    @Consumes("application/json")
    public Info salvar(HierarquiaComprador hierarquiaComprador) {
        GenericDao<HierarquiaComprador> genericDao = new GenericDao<>();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
        Info info;
        try {
            genericDao.persist(hierarquiaComprador);

            info = new Info(false,rb.getString("msg_registro_salvo_sucesso"),hierarquiaComprador);
        } catch (Exception e) {
            info = Info.GetError(Constantes.ERRO_OPERACAO_I18N);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;

    }
}
