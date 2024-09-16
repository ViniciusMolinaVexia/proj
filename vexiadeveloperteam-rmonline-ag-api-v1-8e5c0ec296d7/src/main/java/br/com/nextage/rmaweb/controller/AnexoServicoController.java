/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.controller;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Anexo;
import br.com.nextage.rmaweb.entitybean.AnexoServico;
import br.com.nextage.rmaweb.filtro.FiltroAnexo;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.Info;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Daniel H. Parisotto
 */
@Path("AnexoServico")
public class AnexoServicoController {

    @Context
    HttpServletRequest request;

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    /**
     * Lista todos os anexos do escopo e objeto selecionado
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<AnexoServico> listar(FiltroAnexo filtro) {
        GenericDao<AnexoServico> genericDao = new GenericDao<>();
        List<AnexoServico> listaAnexos = new ArrayList<>();

        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(AnexoServico.ID));
            propriedades.add(new Propriedade(AnexoServico.NOME));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(AnexoServico.ESCOPO, filtro.getEscopo(), Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(AnexoServico.ESCOPO_ID, filtro.getEscopoId(), Filtro.EQUAL)));

            listaAnexos = genericDao.listarByFilter(propriedades, null, AnexoServico.class, Constantes.NO_LIMIT, nxCriterion);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return listaAnexos;
    }

    /**
     * Transforma o arquivo de b64 para byte[] e faz a persistencia do anexo
     *
     * @param anexos
     * @return
     */
    @POST
    @Path("salvar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info salvar(List<AnexoServico> anexos) {
        Info info = new Info();
        GenericDao<AnexoServico> genericDao = new GenericDao<>();

        try {
            genericDao.beginTransaction();

            for (AnexoServico anexo : anexos) {
                String base64 = anexo.getAnexoBase64();
                if (base64 != null) {
                    //Usado para eliminar a substring data:image/jpeg;base64,
                    //caso isso não seja feito, acusa erro de caracter inválido
                    String imageDataParsed = base64.substring(base64.indexOf(',') + 1);
                    //conversao
                    byte[] imageBytes = DatatypeConverter.parseBase64Binary(imageDataParsed);
                    anexo.setAnexo(imageBytes);

                    genericDao.persistWithCurrentTransaction(anexo);
                }
            }

            genericDao.commitCurrentTransaction();

            info.setObjeto(anexos);
            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REGS_SALVOS_SUCESSO_I18N);

        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
        }
        return info;
    }

    /**
     * Carrega o byte[] do anexo
     *
     * @param anexo
     * @return
     */
    @POST
    @Path("carregarAnexo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AnexoServico carregarAnexo(AnexoServico anexo) {
        GenericDao<AnexoServico> genericDao = new GenericDao<>();
        AnexoServico anexos = new AnexoServico();

        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(AnexoServico.ID));
            propriedades.add(new Propriedade(AnexoServico.NOME));
            propriedades.add(new Propriedade(AnexoServico.ANEXO));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(AnexoServico.ID, anexo.getId(), Filtro.EQUAL));

            anexos = genericDao.selectUnique(propriedades, AnexoServico.class, nxCriterion);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return anexos;
    }

    /**
     * Carrega o byte[] do anexo
     *
     * @param anexo
     * @return
     */
    @POST
    @Path("excluirAnexo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info excluirAnexo(AnexoServico anexo) {
        GenericDao<AnexoServico> genericDao = new GenericDao<>();
        Info info = new Info();

        try {
            genericDao.delete(anexo);

            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_EXCLUSAO_SUCESSO_I18N);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setMensagem(Constantes.REG_EXCLUSAO_ERRO_I18N);
        }
        return info;
    }
}
