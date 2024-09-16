/*
 * NextAge License
 * Copyright 2015 - Nextage
 * 
 */
package br.com.nextage.rmaweb.controller.relatorio;

import br.com.nextage.persistence_2.vo.PaginacaoVo;
import br.com.nextage.rmaweb.entitybean.VwRmMaterial;
import br.com.nextage.rmaweb.filtro.FiltroPrevisaoPendenciaRecebimento;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.integracao.PrevisaoPendenciaRecebimentoService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import br.com.nextage.util.vo.ArquivoVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * @author Luan L F Domingues - <b>l.domingues@nextage.com.br<b>
 */
@Path("PrevisaoPendenciaRecebimento")
public class PrevisaoPendenciaRecebimentoController {

    @Context
    HttpServletRequest request;

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    /**
     * Lista os materiais que nao possuem codigo sap
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PaginacaoVo listar(FiltroPrevisaoPendenciaRecebimento filtro) {
        PaginacaoVo retorno = null;
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroPrevisaoPendenciaRecebimento.class.getName(), Util.getNomeMetodoAtual(), filtro, null));
            PrevisaoPendenciaRecebimentoService service = new PrevisaoPendenciaRecebimentoService(request);
            retorno = (PaginacaoVo) service.listar(filtro, true, null);
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroPrevisaoPendenciaRecebimento.class.getName(), Util.getNomeMetodoAtual(), filtro, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return retorno;
    }

    /**
     * Gera o arquivo Excel
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("gerarXLS")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArquivoVo gerarXLS(FiltroPrevisaoPendenciaRecebimento filtro) {
        ArquivoVo vo = null;
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroPrevisaoPendenciaRecebimento.class.getName(), Util.getNomeMetodoAtual(), filtro, null));
            PrevisaoPendenciaRecebimentoService service = new PrevisaoPendenciaRecebimentoService(request);
            List<VwRmMaterial> lista = (List<VwRmMaterial>) service.listar(filtro, false, null);
            vo = service.gerarExcelPendencia(lista, filtro, filtro.getDeposito(), false);
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroPrevisaoPendenciaRecebimento.class.getName(), Util.getNomeMetodoAtual(), filtro, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return vo;
    }

}
