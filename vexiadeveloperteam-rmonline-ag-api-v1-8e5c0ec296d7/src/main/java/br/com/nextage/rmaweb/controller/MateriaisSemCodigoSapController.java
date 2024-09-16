package br.com.nextage.rmaweb.controller;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.persistence_2.util.Paginacao;
import br.com.nextage.persistence_2.vo.PaginacaoVo;
import br.com.nextage.rmaweb.controller.notificacao.email.enviar.EmailService;
import br.com.nextage.rmaweb.entitybean.Configuracao;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.filtro.FiltroMateriaisSemCodigoSap;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.DeParaVo;
import br.com.nextage.util.Email2;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Daniel H. Parisotto
 */
@Path("MateriaisSemCodigoSap")
public class MateriaisSemCodigoSapController {

    @Context
    HttpServletRequest request;

    @Inject
    private EmailService emailService;

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
    public PaginacaoVo listar(FiltroMateriaisSemCodigoSap filtro) {
        List<Propriedade> propriedades = new ArrayList<>();

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroMateriaisSemCodigoSap.class.getName(), Util.getNomeMetodoAtual(), filtro, null));

            propriedades.add(new Propriedade(Material.MATERIAL_ID));
            propriedades.add(new Propriedade(Material.CODIGO));
            propriedades.add(new Propriedade(Material.NOME));
            propriedades.add(new Propriedade(Material.OBSERVACAO));
            propriedades.add(new Propriedade(Material.STATUS));

            NxCriterion nxCriterion = null;
            NxCriterion nxCriterionAux = null;
            NxCriterion nxCriterionOr = null;

            nxCriterionOr = NxCriterion.montaRestriction(new Filtro(Material.STATUS, null, Filtro.IS_NULL));
            nxCriterionOr = NxCriterion.or(nxCriterionOr, NxCriterion.montaRestriction(new Filtro(Material.STATUS, Constantes.INATIVO_ABREVIADO, Filtro.NOT_EQUAL)));

            if (filtro.getStatus() != null && !filtro.getStatus().equals(Constantes.SIGLA_COM_CODIGO)) {
                nxCriterion = NxCriterion.montaRestriction(new Filtro(Material.CODIGO, null, Filtro.IS_NULL));
            }

            if (filtro.getCodigo() != null && !filtro.getCodigo().isEmpty()) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Material.CODIGO, filtro.getCodigo(), Filtro.LIKE));

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Material.NOME, filtro.getNome(), Filtro.LIKE));

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }


            nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Material.IS_SERVICO, Boolean.FALSE, Filtro.EQUAL));
            nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(Material.IS_SERVICO, null, Filtro.IS_NULL)));

            nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);

            nxCriterion = NxCriterion.and(nxCriterion, nxCriterionOr);

            Paginacao paginacao = new Paginacao(propriedades, null, Material.class, nxCriterion, filtro.getPaginacaoVo());

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroMateriaisSemCodigoSap.class.getName(), Util.getNomeMetodoAtual(), filtro, null));

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return filtro.getPaginacaoVo();
    }

    /**
     * Carrega as informacoes do material solicitado
     *
     * @param material
     * @return
     */
    @POST
    @Path("selectById")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Material selectById(Material material) {
        List<Propriedade> propriedades = new ArrayList<>();
        GenericDao<Material> genericDao = new GenericDao<>();

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Material.class.getName(), Util.getNomeMetodoAtual(), material, null));

            propriedades.add(new Propriedade(Material.MATERIAL_ID));
            propriedades.add(new Propriedade(Material.CODIGO));
            propriedades.add(new Propriedade(Material.NOME));
            propriedades.add(new Propriedade(Material.NOME_COMPLETO));
            propriedades.add(new Propriedade(Material.OBSERVACAO));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Material.MATERIAL_ID, material.getMaterialId(), Filtro.EQUAL));

            material = genericDao.selectUnique(propriedades, Material.class, nxCriterion);

            if (material != null && material.getCodigo() == null) {
                material.setRmMaterial(listarRmMaterial(material));
            }

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Material.class.getName(), Util.getNomeMetodoAtual(), material, null));

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return material;
    }

    /**
     * Altera o codigo do material e envia email ao solicitante e o cadastrante
     * da rm
     *
     * @param material
     * @return
     */
    @POST
    @Path("alterarCodigoSap")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info alterarCodigoSap(Material material) {
        Info info = new Info();
        List<Propriedade> propriedades = new ArrayList<>();
        GenericDao<Material> genericDao = new GenericDao<>();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

        Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();

        if (configuracao != null && configuracao.getQuantCasasCodSap() != null) {
            //Separa a quantidade de 0 necessaria e logo concatena com o código recebido, formando no total o num de casa da conf.
            String aux = material.getCodigo();
            StringBuffer resp = new StringBuffer();
            int fim = configuracao.getQuantCasasCodSap() - aux.length();
            for (int i = 0; i < fim; i++) {
                resp.append('0');
            }
            aux = resp + aux;

            material.setCodigo(aux);
        }

        try {
            propriedades.add(new Propriedade(Material.MATERIAL_ID));
            propriedades.add(new Propriedade(Material.CODIGO));
            propriedades.add(new Propriedade(Material.NOME));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Material.CODIGO, material.getCodigo(), Filtro.EQUAL));

            Material m = genericDao.selectUnique(propriedades, Material.class, nxCriterion);

            //Verifica se ja existe um material com esse codigo
            if (m != null) {
                info.setCodigo(Info.INFO_002);
                info.setTipo(Info.TIPO_MSG_DANGER);
                info.setErro(Util.setParamsLabel(rb.getString("mensagem_codigo_material_cadastrado"), m.getNome()));
                info.setMensagem(Util.setParamsLabel(rb.getString("mensagem_codigo_material_cadastrado"), m.getNome()));

                return info;
            }

            //Atualizo o material
            propriedades.clear();
            propriedades.add(new Propriedade(Material.CODIGO));
            propriedades.add(new Propriedade(Material.NOME));
            propriedades.add(new Propriedade(Material.NOME_COMPLETO));

            genericDao.update(material, propriedades);

            //Listo a rm que este material esta ligado
            String aliasMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL);
            String aliasRm = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);
            String aliasSolicitante = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.REQUISITANTE);
            String aliasUsuario = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.USUARIO);
            String aliasUsuarioPessoa = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.USUARIO, Usuario.PESSOA);

            propriedades.clear();
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));

            //Material
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));

            //Rm
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));

            //Solicitante
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasSolicitante));
            propriedades.add(new Propriedade(Pessoa.EMAIL, Pessoa.class, aliasSolicitante));

            //Usuario
            propriedades.add(new Propriedade(Usuario.USUARIO_ID, Usuario.class, aliasUsuario));

            //Usuario pessoa
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasUsuarioPessoa));
            propriedades.add(new Propriedade(Pessoa.EMAIL, Pessoa.class, aliasUsuarioPessoa));

            nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.MATERIAL, material, Filtro.EQUAL));

            GenericDao<RmMaterial> genericDaoRmMaterial = new GenericDao<>();

            RmMaterial rmMaterial = genericDaoRmMaterial.selectUnique(propriedades, RmMaterial.class, nxCriterion);

            if (rmMaterial != null) {
                enviarEmail(null, material, rmMaterial.getRm(), "codSap");
            }

            info.setObjeto(material);
            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setErro(null);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
        }
        return info;
    }

    /**
     * Substitui o material sem codigo selecionado pelo material selecionado, e
     * envia email ao solicitante e cadastrante da rm selecionada
     *
     * @param vo
     * @return
     */
    @POST
    @Path("realizarDePara")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info realizarDePara(DeParaVo vo) {
        Info info = new Info();
        GenericDao<RmMaterial> genericDao = new GenericDao<>();
        RmMaterial rmMaterial = new RmMaterial();
        List<Propriedade> propriedades = new ArrayList<>();
        NxCriterion nxCriterion = null;

        try {
            String aliasMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL);
            String aliasRm = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);
            String aliasSolicitante = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.REQUISITANTE);
            String aliasUsuario = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.USUARIO);
            String aliasUsuarioPessoa = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.USUARIO, Usuario.PESSOA);

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));

            //Material
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));

            //Rm
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));

            //Solicitante
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasSolicitante));
            propriedades.add(new Propriedade(Pessoa.EMAIL, Pessoa.class, aliasSolicitante));

            //Usuario
            propriedades.add(new Propriedade(Usuario.USUARIO_ID, Usuario.class, aliasUsuario));

            //Usuario pessoa
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasUsuarioPessoa));
            propriedades.add(new Propriedade(Pessoa.EMAIL, Pessoa.class, aliasUsuarioPessoa));

            nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.MATERIAL, vo.getMaterialDe(), Filtro.EQUAL));

            rmMaterial = genericDao.selectUnique(propriedades, RmMaterial.class, nxCriterion);

            if (rmMaterial != null) {
                propriedades.clear();
                propriedades.add(new Propriedade(RmMaterial.MATERIAL));
                genericDao.beginTransaction();

                rmMaterial.setMaterial(vo.getMaterialPara());

                genericDao.updateWithCurrentTransaction(rmMaterial, propriedades);

                genericDao.deleteWithCurrentTransaction(vo.getMaterialDe());

                genericDao.commitCurrentTransaction();

                enviarEmail(vo, null, rmMaterial.getRm(), "dePara");
            }

            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
            info.setErro(null);
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
     * Envia email ao solicitante e o cadastrante da rm, informando que o
     * material foi alterado
     *
     * @param deParaVo
     * @param material
     * @param rm
     * @param operacao
     */
    private void enviarEmail(DeParaVo deParaVo, Material material, Rm rm, String operacao) {
        String email = "";
        String mensagemEmail = "";
        String tituloEmail;

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));

            email = rm.getRequisitante().getEmail();
            if (rm.getUsuario().getPessoa() != null) {
                email += ";" + rm.getUsuario().getPessoa().getEmail();
            }

            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

            tituloEmail = rb.getString("msg_titulo_email_alteracao_material_codigo_sap");

            if (operacao.equals("dePara")) {
                mensagemEmail = Util.setParamsLabel(rb.getString("msg_email_alteracao_material_de_para"), deParaVo.getMaterialDe().getNome(), deParaVo.getMaterialPara().getNome(), rm.getNumeroRm());
            } else {
                mensagemEmail = Util.setParamsLabel(rb.getString("msg_email_alteracao_material_codigo_sap"), material.getNome(), rm.getNumeroRm());
            }

            final String subject = tituloEmail;
            final String recipients = email;
            final String body = mensagemEmail;
            this.emailService.enviarEmail(subject, recipients, body);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
    }

    private RmMaterial listarRmMaterial(Material material) {
        RmMaterial rmMaterial = null;
        GenericDao<RmMaterial> genericDao = new GenericDao<>();

        try {
            String aliasRm = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);
            String aliasRequisitante = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.REQUISITANTE);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));

            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));

            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRequisitante));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.MATERIAL, material, Filtro.EQUAL));

            rmMaterial = genericDao.selectUnique(propriedades, RmMaterial.class, nxCriterion);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return rmMaterial;
    }
}
