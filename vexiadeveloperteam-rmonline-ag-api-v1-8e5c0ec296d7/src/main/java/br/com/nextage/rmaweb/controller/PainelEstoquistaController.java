package br.com.nextage.rmaweb.controller;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.persistence_2.util.Paginacao;
import br.com.nextage.persistence_2.vo.PaginacaoVo;
import br.com.nextage.rmaweb.controller.notificacao.email.enviar.EmailService;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.filtro.FiltroPainelEstoquista;
import br.com.nextage.rmaweb.filtro.FiltroRmAprovacao;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.RmMaterialRecebimentoService;
import br.com.nextage.rmaweb.service.RmMaterialRetiradaService;
import br.com.nextage.rmaweb.service.integracao.MaterialDepositoService;
import br.com.nextage.rmaweb.service.integracao.RmMaterialStatusService;
import br.com.nextage.rmaweb.service.integracao.RmaService;
import br.com.nextage.rmaweb.service.integracao.SincEquipamentoService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.*;
import br.com.nextage.util.*;
import br.com.nextage.util.vo.PrimitivoVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 *
 * @author Daniel H. Parisotto
 */
@Path("PainelEstoquista")
public class PainelEstoquistaController {

    @Context
    HttpServletRequest request;

    @Inject
    private EmailService emailService;

    private static final Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    /**
     * Lista as reservas que ainda nao receberam aceite pelo estoquista
     *
     * @param filtro
     * @return
     */
    @POST
    @Path("listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PaginacaoVo listar(FiltroPainelEstoquista filtro) {
        List<Propriedade> propriedades = new ArrayList<>();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

        try {
        	Usuario usuario = LoginService.getUsuarioLogado(request);
        	boolean comprador = false;
        	boolean estoquista = false;
        	
        	for(Role r : usuario.getRoles()) {
        		if(r.getNome().equals(Role.ROLE_COMPRADOR)) {
        			comprador = true;
        		}else if(r.getNome().equals(Role.ROLE_ALMOXARIFE)) {
        			estoquista = true;
        		}
        	}
        	
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroPainelEstoquista.class.getName(), Util.getNomeMetodoAtual(), filtro, null));

            String aliasMaterialDepositoSaidaReserva = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID);
            String aliasRmMaterial = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL);
            String aliasRmMaterialStatus = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.RM_MATERIAL_STATUS_ID);
            String aliasStatus = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.RM_MATERIAL_STATUS_ID, RmMaterialStatus.STATUS_ID);
            String aliasRm = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.RM);
            String aliasMaterial = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.MATERIAL);
            String aliasTipoMaterial = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.MATERIAL, Material.TIPO_MATERIAL);
            String aliasUnidadeMedida = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.MATERIAL, Material.UNIDADE_MEDIDA);
            String aliasDeposito = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.DEPOSITO_ID);
            String aliasSolicitante = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.RM, Rm.REQUISITANTE);
            String aliasTipoRequisicao = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.RM, Rm.TIPO_REQUISICAO);
            String aliasEapMultiEmpresa = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.RM, Rm.EAP_MULTI_EMPRESA);
            String aliasUsuario = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.RM, Rm.USUARIO);
            String aliasUsuarioPessoa = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.RM, Rm.USUARIO, Usuario.PESSOA);
            String aliasPessoaResponsavel = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.PESSOA_RESPONSAVEL);

            //VIEW
            propriedades.add(new Propriedade(VwMaterialDepositoSaidaReserva.ID));

            //RM MATERIAL STATUS
            propriedades.add(new Propriedade(RmMaterialStatus.ID, RmMaterialStatus.class, aliasRmMaterialStatus));

            //STATUS
            propriedades.add(new Propriedade(Status.ID, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.NOME, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.CODIGO, Status.class, aliasStatus));

            //Material deposito saida reserva
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.ID, MaterialDepositoSaidaReserva.class, aliasMaterialDepositoSaidaReserva));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.DATA_SAIDA, MaterialDepositoSaidaReserva.class, aliasMaterialDepositoSaidaReserva));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.DATA_AVALIACAO, MaterialDepositoSaidaReserva.class, aliasMaterialDepositoSaidaReserva));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.STATUS, MaterialDepositoSaidaReserva.class, aliasMaterialDepositoSaidaReserva));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.QUANTIDADE_SOLICITADA, MaterialDepositoSaidaReserva.class, aliasMaterialDepositoSaidaReserva));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.OBSERVACAO_PAINEL_ESTOQUISTA, MaterialDepositoSaidaReserva.class, aliasMaterialDepositoSaidaReserva));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.ITEM_INDISPONIVEL, MaterialDepositoSaidaReserva.class, aliasMaterialDepositoSaidaReserva));

            //RmMaterial
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.FLUXO_MATERIAL, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_CUSTOS, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.OPERACAO, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_ESTOQUE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DIAGRAMA_REDE, RmMaterial.class, aliasRmMaterial));

            //Pessoa Responsavel
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasPessoaResponsavel));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasPessoaResponsavel));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasPessoaResponsavel));

            //Rm
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO, Rm.class, aliasRm));

            //Eap multi empresa
            propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));

            //Material
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME_COMPLETO, Material.class, aliasMaterial));

            // Unidade Medida
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));

            //Tipo Material
            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));

            //Deposito
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            //Solicitate
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasSolicitante));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasSolicitante));

            //Usuario
            propriedades.add(new Propriedade(Usuario.USUARIO_ID, Usuario.class, aliasUsuario));
            propriedades.add(new Propriedade(Usuario.NOME, Usuario.class, aliasUsuario));

            //Usuario Pessoa
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasUsuarioPessoa));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasUsuarioPessoa));

            NxCriterion nxCriterion = null;
            NxCriterion nxCriterionAux = null;

            if (filtro.getNumeroRma() != null && !filtro.getNumeroRma().isEmpty()) {
                filtro.setNumeroRma(filtro.getNumeroRma().replaceAll(",", ";"));
                String[] listaRma = filtro.getNumeroRma().split(";");
                NxCriterion nxAux;
                for (String rma : listaRma) {
                    if (rma == null || rma.isEmpty()) {
                        continue;
                    }
                    nxAux = NxCriterion.montaRestriction(new Filtro(Rm.NUMERO_RM, rma, Filtro.EQUAL, aliasRm));
                    if (nxCriterionAux != null) {
                        nxCriterionAux = NxCriterion.or(nxCriterionAux, nxAux);
                    } else {
                        nxCriterionAux = nxAux;
                    }
                }
                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (filtro.getNumeroPedidoSap() != null && !filtro.getNumeroPedidoSap().isEmpty()) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.NUMERO_PEDIDO_COMPRA, filtro.getNumeroPedidoSap(), Filtro.EQUAL, aliasRmMaterial));

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (filtro.getSolicitante() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Rm.REQUISITANTE, filtro.getSolicitante(), Filtro.EQUAL, aliasRm));

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (filtro.getCadastrante() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Usuario.PESSOA, filtro.getCadastrante(), Filtro.EQUAL, aliasUsuario));

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (filtro.getMaterial() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.MATERIAL, filtro.getMaterial(), Filtro.EQUAL, aliasRmMaterial));

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (filtro.getMaterialChave() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Material.NOME, filtro.getMaterialChave(), Filtro.LIKE, aliasMaterial));
                nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(Material.CODIGO, filtro.getMaterialChave(), Filtro.LIKE, aliasMaterial)));
                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (filtro.getFluxoMaterial() != null && !filtro.getFluxoMaterial().isEmpty()) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.FLUXO_MATERIAL, filtro.getFluxoMaterial(), Filtro.EQUAL, aliasRmMaterial));

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (filtro.getDeposito() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterial.DEPOSITO_ID, filtro.getDeposito(), Filtro.EQUAL, aliasRmMaterial));

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (filtro.getStDataInicio() != null && !filtro.getStDataInicio().isEmpty()) {
                try {
                    Date data = Util.parseData(filtro.getStDataInicio(), rb.getString("format_date"));

                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaidaReserva.DATA_AVALIACAO, data, Filtro.MAIOR_IGUAL, aliasMaterialDepositoSaidaReserva));

                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                } catch (Exception e) {

                }
            }

            if (filtro.getStDataFim() != null && !filtro.getStDataFim().isEmpty()) {
                try {
                    Date data = Util.parseData(filtro.getStDataFim(), rb.getString("format_date"));

                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaidaReserva.DATA_AVALIACAO, data, Filtro.MENOR_IGUAL, aliasMaterialDepositoSaidaReserva));

                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                } catch (Exception e) {

                }
            }

            if (filtro.getStDataInicioAplicacao() != null) {
                try {
                    Date data = Util.parseData(filtro.getStDataInicioAplicacao(), rb.getString("format_date"));
                    data = Util.convertDateHrInicial(data);

                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Rm.DATA_APLICACAO, data, Filtro.MAIOR_IGUAL, aliasRm));

                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                } catch (Exception e) {

                }
            }

            if (filtro.getStDataFimAplicacao() != null) {
                try {
                    Date data = Util.parseData(filtro.getStDataFimAplicacao(), rb.getString("format_date"));
                    data = Util.convertDateHrFinal(data);

                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Rm.DATA_APLICACAO, data, Filtro.MENOR_IGUAL, aliasRm));

                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                } catch (Exception e) {

                }
            }

            if (filtro.getStDataInicioRecebSolic() != null) {
                try {
                    Date data = Util.parseData(filtro.getStDataInicioRecebSolic(), rb.getString("format_date"));
                    data = Util.convertDateHrInicial(data);

                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaidaReserva.DATA_SAIDA, data, Filtro.MAIOR_IGUAL, aliasMaterialDepositoSaidaReserva));

                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                } catch (Exception e) {

                }
            }

            if (filtro.getStDataFimRecebSolic() != null) {
                try {
                    Date data = Util.parseData(filtro.getStDataFimRecebSolic(), rb.getString("format_date"));
                    data = Util.convertDateHrFinal(data);

                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaidaReserva.DATA_SAIDA, data, Filtro.MENOR_IGUAL, aliasMaterialDepositoSaidaReserva));

                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                } catch (Exception e) {

                }
            }

            if (filtro.getEapMultiEmpresa() != null && filtro.getEapMultiEmpresa().getId() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(EapMultiEmpresa.ID, filtro.getEapMultiEmpresa().getId(), Filtro.EQUAL, aliasEapMultiEmpresa));
                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (filtro.getStatusItem() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(RmMaterialStatus.STATUS_ID, filtro.getStatusItem(), Filtro.EQUAL, aliasRmMaterialStatus));
                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            NxCriterion nxCriterionOr = null;
                if (filtro.getStatus() != null && !filtro.getStatus().isEmpty()) {
                switch (filtro.getStatus()) {
                    case "T":
                        break;
                    case "C":
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Status.CODIGO, Constantes.STATUS_MATERIAL_RESERVA_REPROVADO, Filtro.EQUAL, aliasStatus));
                        nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(Status.CODIGO, Constantes.STATUS_RM_MATERIAL_FINALIZADO, Filtro.EQUAL, aliasStatus)));

                        nxCriterionAux = NxCriterion.and(nxCriterionAux, nxCriterionOr);
                        break;
                    case "P":
                        // nxCriterionAux = NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaidaReserva.STATUS, "R", Filtro.NOT_EQUAL, aliasMaterialDepositoSaidaReserva));
                        nxCriterionAux = NxCriterion.and(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(RmMaterial.FLUXO_MATERIAL, Constantes.FLUXO_MATERIAL_CAMPO, Filtro.NOT_EQUAL, aliasRmMaterial)));
                        nxCriterionAux = NxCriterion.and(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(Status.CODIGO, Constantes.STATUS_RM_MATERIAL_FINALIZADO, Filtro.NOT_EQUAL, aliasStatus)));
                        nxCriterionAux = NxCriterion.and(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(Status.CODIGO, Constantes.STATUS_RM_MATERIAL_REPROVADA, Filtro.NOT_EQUAL, aliasStatus)));
                        // nxCriterionAux = NxCriterion.and(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaidaReserva.STATUS, "A", Filtro.NOT_EQUAL, aliasMaterialDepositoSaidaReserva)));

                        //Se for requisição de campo
                        nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaidaReserva.STATUS, null, Filtro.IS_NULL, aliasMaterialDepositoSaidaReserva)));

                        break;
                }

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            //Ordena pelo numero da rm
            List<NxOrder> orders = Arrays.asList(new NxOrder(VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID + "."
                    + MaterialDepositoSaidaReserva.RM_MATERIAL + "."
                    + RmMaterial.RM, NxOrder.NX_ORDER.ASC));

            Paginacao.build(propriedades, orders, VwMaterialDepositoSaidaReserva.class, nxCriterion, filtro.getPaginacaoVo());

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroPainelEstoquista.class.getName(), Util.getNomeMetodoAtual(), filtro, null));

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return filtro.getPaginacaoVo();
    }

    /**
     * Carrega as informacoes da reserva
     *
     * @param materialDepositoSaidaReserva
     * @return
     */
    @POST
    @Path("selectById")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public VwMaterialDepositoSaidaReserva selectById(MaterialDepositoSaidaReserva materialDepositoSaidaReserva) {
        List<Propriedade> propriedades = new ArrayList<>();
        GenericDao<VwMaterialDepositoSaidaReserva> genericDao = new GenericDao<>();

        VwMaterialDepositoSaidaReserva vwMaterialDepositoSaidaReserva = new VwMaterialDepositoSaidaReserva();

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), VwMaterialDepositoSaidaReserva.class.getName(), Util.getNomeMetodoAtual(), vwMaterialDepositoSaidaReserva, null));

            String aliasMaterialDepositoSaidaReserva = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID);

            String aliasRmMaterial = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL);
            String aliasRm = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.RM);
            String aliasMaterial = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.MATERIAL);
            String aliasTipoMaterial = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.MATERIAL, Material.TIPO_MATERIAL);
            String aliasUnidadeMedida = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.MATERIAL, Material.UNIDADE_MEDIDA);
            String aliasDeposito = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.DEPOSITO_ID);
            String aliasSolicitante = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.RM, Rm.REQUISITANTE);
            String aliasTipoRequisicao = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.RM, Rm.TIPO_REQUISICAO);
            String aliasUsuario = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.RM, Rm.USUARIO);
            String aliasUsuarioPessoa = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.RM, Rm.USUARIO, Usuario.PESSOA);
            String aliasComprador = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.COMPRADOR);
            String aliasRmMaterialStatus = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.RM_MATERIAL_STATUS_ID);
            String aliasPessoa = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.PESSOA_ID);
            String aliasStatus = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.RM_MATERIAL_STATUS_ID, RmMaterialStatus.STATUS_ID);
            String aliasPessoaResponsavel = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.PESSOA_RESPONSAVEL);
            String aliasEapMultiEmpresa = NxCriterion.montaAlias(VwMaterialDepositoSaidaReserva.ALIAS_CLASSE, VwMaterialDepositoSaidaReserva.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.RM, Rm.EAP_MULTI_EMPRESA + "$2");

            //VIEW
            propriedades.add(new Propriedade(VwMaterialDepositoSaidaReserva.ID));

            //RM MATERIAL STATUS
            propriedades.add(new Propriedade(RmMaterialStatus.ID, RmMaterialStatus.class, aliasRmMaterialStatus));

            //PESSOA
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasPessoa));

            //STATUS
            propriedades.add(new Propriedade(Status.ID, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.NOME, Status.class, aliasStatus));
            propriedades.add(new Propriedade(Status.CODIGO, Status.class, aliasStatus));

            //Material deposito saida reserva
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.ID, MaterialDepositoSaidaReserva.class, aliasMaterialDepositoSaidaReserva));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.DATA_SAIDA, MaterialDepositoSaidaReserva.class, aliasMaterialDepositoSaidaReserva));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.DATA_AVALIACAO, MaterialDepositoSaidaReserva.class, aliasMaterialDepositoSaidaReserva));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.STATUS, MaterialDepositoSaidaReserva.class, aliasMaterialDepositoSaidaReserva));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.QUANTIDADE_SOLICITADA, MaterialDepositoSaidaReserva.class, aliasMaterialDepositoSaidaReserva));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.OBSERVACAO_PAINEL_ESTOQUISTA, MaterialDepositoSaidaReserva.class, aliasMaterialDepositoSaidaReserva));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.ITEM_INDISPONIVEL, MaterialDepositoSaidaReserva.class, aliasMaterialDepositoSaidaReserva));

            //RmMaterial
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.FLUXO_MATERIAL, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO_ESTOQUISTA, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_ESTOQUE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.IS_REC_SAP, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.LOCAL_ENTREGA, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.CF_RESPONSAVEL, RmMaterial.class, aliasRmMaterial));

            //Pessoa Responsavel
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasPessoaResponsavel));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasPessoaResponsavel));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasPessoaResponsavel));

            //Rm
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.DATA_APLICACAO, Rm.class, aliasRm));

            //Eap multi empresa
            propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));

            //Material
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.PATRIMONIADO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.STATUS, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.HIERARQUIA, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME_COMPLETO, Material.class, aliasMaterial));

            //COMPRADOR
            propriedades.add(new Propriedade(Comprador.COMPRADOR_ID, Comprador.class, aliasComprador));
            propriedades.add(new Propriedade(Comprador.NOME, Comprador.class, aliasComprador));

            //Usuario
            propriedades.add(new Propriedade(Usuario.USUARIO_ID, Usuario.class, aliasUsuario));
            propriedades.add(new Propriedade(Usuario.NOME, Usuario.class, aliasUsuario));

            //Usuario Pessoa
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasUsuarioPessoa));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasUsuarioPessoa));

            //Tipo Material
            propriedades.add(new Propriedade(TipoMaterial.TIPO_MATERIAL_ID, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.DESCRICAO, TipoMaterial.class, aliasTipoMaterial));
            propriedades.add(new Propriedade(TipoMaterial.CODIGO, TipoMaterial.class, aliasTipoMaterial));

            //Unidade Medid
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            //Deposito
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            //Solicitate
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasSolicitante));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasSolicitante));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasSolicitante));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaidaReserva.ID, materialDepositoSaidaReserva.getId(), Filtro.EQUAL, aliasMaterialDepositoSaidaReserva));

            vwMaterialDepositoSaidaReserva = genericDao.selectUnique(propriedades, VwMaterialDepositoSaidaReserva.class, nxCriterion);

            //Verifica se item existe em estoque somente se nao for cautelavel;
            if (vwMaterialDepositoSaidaReserva != null
                    && vwMaterialDepositoSaidaReserva.getMaterialDepositoSaidaReserva() != null
                    && vwMaterialDepositoSaidaReserva.getMaterialDepositoSaidaReserva().getRmMaterial() != null
                    && vwMaterialDepositoSaidaReserva.getMaterialDepositoSaidaReserva().getRmMaterial().getFluxoMaterial().equals(Constantes.FLUXO_MATERIAL_CAMPO)
                    && vwMaterialDepositoSaidaReserva.getMaterialDepositoSaidaReserva().getRmMaterial().getMaterial() != null
                    && vwMaterialDepositoSaidaReserva.getMaterialDepositoSaidaReserva().getRmMaterial().getMaterial().getTipoMaterial() != null
                    && vwMaterialDepositoSaidaReserva.getMaterialDepositoSaidaReserva().getRmMaterial().getMaterial().getTipoMaterial().getCodigo() != null
                    && !vwMaterialDepositoSaidaReserva.getMaterialDepositoSaidaReserva().getRmMaterial().getMaterial().getTipoMaterial().getCodigo().equals(Constantes.CAUTELAVEL)) {
                boolean retorno = validaEstoqueMaterial(vwMaterialDepositoSaidaReserva.getMaterialDepositoSaidaReserva().getRmMaterial());
                vwMaterialDepositoSaidaReserva.getMaterialDepositoSaidaReserva().setItemIndisponivel(!retorno);
            }

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), VwMaterialDepositoSaidaReserva.class.getName(), Util.getNomeMetodoAtual(), vwMaterialDepositoSaidaReserva, null));

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return vwMaterialDepositoSaidaReserva;
    }

    /**
     * Salva o aceite do estoquista
     *
     * @param materialDepositoSaidaReserva
     * @return
     */
    @POST
    @Path("salvarAceite")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info salvarAceite(MaterialDepositoSaidaReserva materialDepositoSaidaReserva) {
        Info info = null;
        RmaService rmaService = new RmaService(request);
        boolean aceitar = true;
        String cf = null;
        if (materialDepositoSaidaReserva.getRmMaterial() != null && materialDepositoSaidaReserva.getRmMaterial().getPessoaResponsavel() != null
                && materialDepositoSaidaReserva.getRmMaterial().getPessoaResponsavel().getReferencia() != null) {
            cf = materialDepositoSaidaReserva.getRmMaterial().getPessoaResponsavel().getReferencia();
        }
        if(rmaService.verificaEnvioParaCpweb(materialDepositoSaidaReserva.getRmMaterial().getMaterial())) {
            if( cf == null || cf.equals("undefined") || cf.trim().length() == 0){
                aceitar = false;
                info = Info.GetError("msg_aceite_material_obrigatorio_cf_responsavel",null);
            }
        }
        if(aceitar) {
              //Apenas para verificacao se caso o metodo vier do SAP pela atualizacao de estoque, para saber se vai ser adicionado
              //A quantia exata ou maior. ou entao nao, para saber se vai finalizar o material ou apenas aguardar o proximo recebimento.
              Boolean verificaQtdExataMaior = true;
              Boolean verificaSaida = true;
              info = rmaService.salvarAceiteMaterial(materialDepositoSaidaReserva, verificaSaida, verificaQtdExataMaior, true, false, null);

            //Como a mensagem vem personalizada do cpweb, é necessário setar outra mensagem
            if(info.getMensagem().contains("mas o RMA não está enviado a referência do responsável.")){
                info.setMensagem("msg_erro_cf_responsavel_cpweb");
            }
        }

        return info;
    }

    /**
     * Salva a recusa do estoquista
     *
     * @param materialDepositoSaidaReserva
     * @return
     */
    @POST
    @Path("salvarRecusar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info salvarRecusar(MaterialDepositoSaidaReserva materialDepositoSaidaReserva) {
        Info info = new Info();
        GenericDao<RmMaterial> genericDao = new GenericDao<>();
        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);

        RmMaterial rmMaterial = materialDepositoSaidaReserva.getRmMaterial();

        materialDepositoSaidaReserva.setDataAvaliacao(new Date());
        materialDepositoSaidaReserva.setStatus(Constantes.STATUS_MATERIAL_RESERVA_REPROVADO);
        materialDepositoSaidaReserva.setObservacaoPainelEstoquista(null);

        List<RmMaterialStatus> listaMaterialStatus = rmMaterialStatusService.listarMaterialStatus(rmMaterial, Boolean.TRUE);
        try {
            genericDao.beginTransaction();

            //Atualizo o material deposito saida reserva
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.DATA_AVALIACAO));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.STATUS));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.OBSERVACAO_PAINEL_ESTOQUISTA));

            genericDao.updateWithCurrentTransaction(materialDepositoSaidaReserva, propriedades);

            propriedades.clear();
            propriedades.add(new Propriedade(RmMaterialStatus.IS_HISTORICO));
            for (RmMaterialStatus rmMaterialStatus : listaMaterialStatus) {
                if (rmMaterialStatus.getStatus().getCodigo().equals(Constantes.STATUS_RM_MATERIAL_AGUARDANDO_ESTOQUE)) {
                    rmMaterialStatus.setHistorico(Boolean.TRUE);

                    genericDao.updateWithCurrentTransaction(rmMaterialStatus, propriedades);
                }
            }
            boolean tipoRequisicaoFrenteServico = false;
                rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_AGUARDANDO_COMPRA, genericDao, null, null, null);
                rmMaterial.setFluxoMaterial(Constantes.FLUXO_MATERIAL_COMPRA);

                propriedades.clear();
                propriedades.add(new Propriedade(RmMaterial.OBSERVACAO_ESTOQUISTA));
                propriedades.add(new Propriedade(RmMaterial.FLUXO_MATERIAL));

                genericDao.updateWithCurrentTransaction(rmMaterial, propriedades);

            genericDao.commitCurrentTransaction();

            info.setCodigo(Info.INFO_001);
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem("msg_recusa_material_sucesso");
            info.setErro(null);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            genericDao.rollbackCurrentTransaction();
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setMensagem(Constantes.ERRO_OPERACAO_I18N);
        }
        return info;
    }

    /**
     * Obtem o material deposito de acordo com os filtros
     *
     * @param materialDepositoSaidaReserva
     * @return
     */
    @POST
    @Path("getMaterialDeposito")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MaterialDeposito getMaterialDeposito(MaterialDepositoSaidaReserva materialDepositoSaidaReserva) {
        return listarMaterialDeposito(materialDepositoSaidaReserva.getRmMaterial().getMaterial(), materialDepositoSaidaReserva.getRmMaterial().getDeposito());
    }

    private MaterialDeposito listarMaterialDeposito(Material material, Deposito deposito) {
        GenericDao<MaterialDeposito> genericDao = new GenericDao<>();
        MaterialDeposito materialDeposito = null;

        try {
            //Listo as quantidades dos itens solicitados nos depositos selecionados
            String aliasMaterial = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.DEPOSITO);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));

            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, material, Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.DEPOSITO, deposito, Filtro.EQUAL)));

            materialDeposito = genericDao.selectUnique(propriedades, MaterialDeposito.class, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return materialDeposito;
    }

    /**
     * Lista os depositos que contenham o material disponivel
     *
     * @param materialDepositoSaidaReserva
     * @return
     */
    @POST
    @Path("listarDepositosDisponiveis")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<MaterialDeposito> listarDepositosDisponiveis(MaterialDepositoSaidaReserva materialDepositoSaidaReserva) {
        List<MaterialDeposito> lista = new ArrayList<>();
        GenericDao genericDao = new GenericDao();

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), MaterialDepositoSaidaReserva.class.getName(), Util.getNomeMetodoAtual(), materialDepositoSaidaReserva, null));

            RmMaterial rmMaterial = materialDepositoSaidaReserva.getRmMaterial();

            //Listo as quantidades dos itens solicitados nos depositos que nao
            //foram selecionados
            String aliasMaterial = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.DEPOSITO);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));

            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            NxCriterion nxCriterion;
            nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, rmMaterial.getMaterial(), Filtro.EQUAL));

            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.DEPOSITO, rmMaterial.getDeposito(), Filtro.NOT_EQUAL)));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Deposito.IS_TEMPORARIO, false, Filtro.EQUAL, aliasDeposito)));
            nxCriterion = NxCriterion.or(nxCriterion, NxCriterion.montaRestriction(new Filtro(Deposito.IS_TEMPORARIO, null, Filtro.IS_NULL, aliasDeposito)));

            List<MaterialDeposito> materiaisDepositoNaoSelecionados = genericDao.listarByFilter(propriedades, null, MaterialDeposito.class, Constantes.NO_LIMIT, nxCriterion);

            //Verifico se em algum deposito nao selecionado possui quantidade > 0
            for (MaterialDeposito materialDeposito : materiaisDepositoNaoSelecionados) {
                if (rmMaterial.getMaterial().equals(materialDeposito.getMaterial())
                        && materialDeposito.getQuantidade() > 0
                        && !rmMaterial.getDeposito().equals(materialDeposito.getDeposito())) {
                    lista.add(materialDeposito);
                }
            }

            //Ordeno a lista para garantir que a lista ficara agrupada por
            //item/deposito
            Collections.sort(lista, new Comparator<MaterialDeposito>() {
                @Override
                public int compare(MaterialDeposito o1, MaterialDeposito o2) {
                    return o1.getMaterialDepositoId().compareTo(o2.getMaterialDepositoId());
                }
            });

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), MaterialDepositoSaidaReserva.class.getName(), Util.getNomeMetodoAtual(), materialDepositoSaidaReserva, null));

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return lista;
    }

    /**
     * Lista os depositos que contenham o material disponivel
     *
     * @param deposito
     * @return
     */
    public Deposito getDeposito(Deposito deposito) {
        Deposito retorno = null;
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Deposito.class.getName(), Util.getNomeMetodoAtual(), deposito, null));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));
            propriedades.add(new Propriedade(Deposito.NOME));
            propriedades.add(new Propriedade(Deposito.CODIGO));
            propriedades.add(new Propriedade(Deposito.IS_TEMPORARIO));
            propriedades.add(new Propriedade(Deposito.OBSERVACAO));

            NxCriterion nxCriterion;
            if (deposito.getCodigo() != null && !deposito.getCodigo().isEmpty()) {
                nxCriterion = NxCriterion.montaRestriction(new Filtro(Deposito.CODIGO, deposito.getCodigo(), Filtro.EQUAL));
            } else {
                nxCriterion = NxCriterion.montaRestriction(new Filtro(Deposito.NOME, deposito.getNome(), Filtro.LIKE));
            }

            GenericDao<Deposito> genericDao = new GenericDao<>();
            retorno = genericDao.selectUnique(propriedades, Deposito.class, nxCriterion);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Deposito.class.getName(), Util.getNomeMetodoAtual(), retorno, null));

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return retorno;
    }

    /**
     * Gera um status de Solicitacao de transferencia na [TB_RM_MATERIAL_STATUS]
     *
     * @param materialDepositoSaidaReserva
     * @return
     */
    @POST
    @Path("solicitarTransferenciaMaterial")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info solicitarTransferenciaMaterial(MaterialDepositoSaidaReserva materialDepositoSaidaReserva) {
        Info info;
        GenericDao<RmMaterial> genericDao = new GenericDao<>();
        try {
            RmMaterial rmMaterial = materialDepositoSaidaReserva.getRmMaterial();

            genericDao.beginTransaction();
            RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);
            // label_solicitado_transferencia_estoque
            rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_SOLICITAR_TRANSFERENCIA, genericDao, new Date(), materialDepositoSaidaReserva.getRmMaterial().getObservacaoEstoquista(), null);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.ID));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.DATA_SAIDA));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.DATA_AVALIACAO));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.STATUS));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.QUANTIDADE_SOLICITADA));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.OBSERVACAO_PAINEL_ESTOQUISTA));
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.ITEM_INDISPONIVEL));

            NxCriterion crt = NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaidaReserva.RM_MATERIAL, rmMaterial, Filtro.EQUAL));

            GenericDao<MaterialDepositoSaidaReserva> genericDaoMdsr = new GenericDao<>();

            MaterialDepositoSaidaReserva mdsr = genericDaoMdsr.selectUnique(propriedades, MaterialDepositoSaidaReserva.class, crt);

            if (mdsr != null) {
                NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
                propriedades.clear();
                propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.OBSERVACAO_PAINEL_ESTOQUISTA));
                mdsr.setObservacaoPainelEstoquista(rb.getString("label_solicitado_transferencia_estoque") + " - " + mdsr.getObservacaoPainelEstoquista());
                genericDao.updateWithCurrentTransaction(mdsr, propriedades);
            }

            genericDao.commitCurrentTransaction();

            info = Info.GetSuccess(Constantes.SUCESSO_OPERACAO_I18N, null);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            genericDao.rollbackCurrentTransaction();

            info = Info.GetError(Constantes.ERRO_OPERACAO_I18N);
        }
        return info;
    }

    /**
     * Recupera o ultimo status do rmMaterial e verificar se e uma solicitacao
     * de transferencia
     *
     * @param materialDepositoSaidaReserva
     * @return
     */
    @POST
    @Path("getUltimoStatusTransferenciaRmMaterial")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RmMaterialStatus getUltimoStatusTransferenciaRmMaterial(MaterialDepositoSaidaReserva materialDepositoSaidaReserva) {

        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);
        RmMaterial rmMaterial = materialDepositoSaidaReserva.getRmMaterial();
        List<RmMaterialStatus> lista = rmMaterialStatusService.listarMaterialStatus(rmMaterial, false);
        RmMaterialStatus status = null;
        if (lista != null && !lista.isEmpty()) {
            status = lista.get(lista.size() - 1);
            if (!status.getStatus().getCodigo().equals(Constantes.STATUS_RM_MATERIAL_SOLICITAR_TRANSFERENCIA)) {
                status = null;
            }
        }
        return status;
    }

    /**
     * Realiza a transferencia do material de um estoque para outro
     *
     * @param materialVo
     * @return Info
     */
    @POST
    @Path("salvarTransferencia")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info salvarTransferencia(MaterialTransferenciaVo materialVo) {
        Info info;
        GenericDao<RmMaterial> genericDao = new GenericDao<>();
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), MaterialTransferenciaVo.class.getName(), Util.getNomeMetodoAtual(), materialVo, null));
            genericDao.beginTransaction();
            info = transferenciaMaterial(materialVo, true, genericDao);
            if (info.getCodigo().equals(Info.INFO_001)) {
                genericDao.commitCurrentTransaction();
            } else {
                genericDao.rollbackCurrentTransaction();
            }
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            info = Info.GetError(Constantes.REG_SALVO_ERRO_I18N);
        }
        return info;
    }

    /**
     * Realiza a transferencia do material de um estoque para outro Caso o
     * parametro enviaCpweb == true envia a transferencia para o sistema CPWEB
     *
     * @author Alyson X. Leite
     * @param materialVo
     * @param enviaCpweb
     * @return
     */
    private Info transferenciaMaterial(MaterialTransferenciaVo materialVo, boolean enviaCpweb, GenericDao genericDao) {
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), MaterialTransferenciaVo.class.getName(), Util.getNomeMetodoAtual(), materialVo, null));
            MaterialDeposito depositoNovo;
            GenericDao<MaterialDeposito> gdaoConsulta = new GenericDao<>();
            // Recupero o depÃ³sito antigo
            String aliasMaterial = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.MATERIAL);
            String aliasDeposito = NxCriterion.montaAlias(MaterialDeposito.ALIAS_CLASSE, MaterialDeposito.DEPOSITO);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));

            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));

            NxCriterion nxCriterion;
            nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, materialVo.getMaterial(), Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.DEPOSITO, materialVo.getDepositoOrigem(), Filtro.EQUAL)));

            MaterialDeposito depositoAnt = gdaoConsulta.selectUnique(propriedades, MaterialDeposito.class, nxCriterion);

            // Validacoes
            if (depositoAnt == null) {
                return Info.GetError("msg_necessario_selecionar_deposito");
            }

            // Verifico se a quantidade de transferencia e igual a zero
            if (materialVo.getQuantidadeTransferencia() == 0) {
                return Info.GetError("msg_material_quantidade_zero");
            }

            // Caso os depositos sejam iguais
            if (materialVo.getDepositoOrigem().getDepositoId().equals(materialVo.getDepositoDestino().getDepositoId())) {
                return Info.GetError("msg_selecione_depositos_diferentes");
            }

            // Verifico se a quantidade disponivel e igual a zero
            if (depositoAnt.getQuantidade().equals(0.0)) {
                return Info.GetError("label_item_indisponivel_no_deposito");
            }

            // Verifico se o deposito tem a quantidade de transferencia
            if (depositoAnt.getQuantidade() < materialVo.getQuantidadeTransferencia()) {
                return Info.GetError("msg_quantidade_retirada_maior_quantidade_solicitada");
            }

            materialVo.setDepositoDestino(getDeposito(materialVo.getDepositoDestino()));
            materialVo.setDepositoOrigem(getDeposito(materialVo.getDepositoOrigem()));

            // Recupero o MaterialDeposito selecionado para a movimentacao
            nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, materialVo.getMaterial(), Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.DEPOSITO, materialVo.getDepositoDestino(), Filtro.EQUAL)));

            depositoNovo = gdaoConsulta.selectUnique(propriedades, MaterialDeposito.class, nxCriterion);

            Double qtdAtualDepositoNovo;

            if (depositoNovo == null) {
                depositoNovo = new MaterialDeposito();
                depositoNovo.setDeposito(materialVo.getDepositoDestino());
                depositoNovo.setMaterial(materialVo.getMaterial());
                depositoNovo.setQuantidade(materialVo.getQuantidadeTransferencia());
                qtdAtualDepositoNovo = 0.0;
            } else {
                qtdAtualDepositoNovo = depositoNovo.getQuantidade();
                depositoNovo.setDeposito(materialVo.getDepositoDestino());
                depositoNovo.setQuantidade(depositoNovo.getQuantidade() + materialVo.getQuantidadeTransferencia());
            }
            // Atualizo ou persisto o deposito novo escolhido
            if (depositoNovo.getMaterialDepositoId() != null && depositoNovo.getMaterialDepositoId() > 0) {

                propriedades.clear();
                propriedades.add(new Propriedade(MaterialDeposito.DEPOSITO));
                propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));
                genericDao.updateWithCurrentTransaction(depositoNovo, propriedades);
            } else {
                genericDao.persistWithCurrentTransaction(depositoNovo);
            }

            // Verifica se o item ficara indisponivel mesmo com a transferencia do material
            Double qtdTransferencia = materialVo.getQuantidadeTransferencia();
            Double qtdSolicitada = materialVo.getQuantidadeSolicitada();

            boolean itemIndisponivel = false;
            if (qtdSolicitada != null && qtdAtualDepositoNovo != null && qtdTransferencia != null) {
                itemIndisponivel = qtdTransferencia + qtdAtualDepositoNovo < qtdSolicitada;
            }

            // Calcula a quantidade retirada do deposito
            propriedades.clear();
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));
            depositoAnt.setQuantidade(depositoAnt.getQuantidade() - materialVo.getQuantidadeTransferencia());
            genericDao.updateWithCurrentTransaction(depositoAnt, propriedades);

            propriedades.clear();
            propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));
            genericDao.updateWithCurrentTransaction(depositoAnt, propriedades);

            Rm rm = null;
            if (materialVo.getRmMaterial() != null && materialVo.getRmMaterial().getRm() != null) {
                rm = materialVo.getRmMaterial().getRm();
            }
            // Salvo o hitorico de saida de materiais no deposito antigo
            MaterialDepositoSaida objMDS = new MaterialDepositoSaida();
            objMDS.setMaterialDeposito(depositoAnt);
            objMDS.setDataSaida(new Date());
            // Esta transferindo de um deposito para o outro
            // entao nao e uma reserva
            objMDS.setReserva(true);
            objMDS.setQuantidade(materialVo.getQuantidadeTransferencia());
            objMDS.setObservacao(Constantes.SAIDA_DE_MATERIAL_POR_MOVIMENTACAO);
            objMDS.setRm(rm);
            if (materialVo.getMaterialDepositoSaidaReserva() != null && materialVo.getMaterialDepositoSaidaReserva().getId() != null) {
                objMDS.setMaterialDepositoSaidaReserva(materialVo.getMaterialDepositoSaidaReserva());
            }

            genericDao.persistWithCurrentTransaction(objMDS);

            // Salvo o hitorico de entrada de materiais no deposito novo
            MaterialDepositoEntrada objMDE = new MaterialDepositoEntrada();
            objMDE.setMaterialDeposito(depositoNovo);
            objMDE.setDataEntrada(new Date());
            objMDE.setQuantidade(materialVo.getQuantidadeTransferencia());
            objMDE.setObservacao(Constantes.ENTRADA_DE_MATERIAL_POR_MOVIMENTACAO);
            objMDE.setRm(rm);

            genericDao.persistWithCurrentTransaction(objMDE);

            if (materialVo.getMaterialDepositoSaidaReserva() != null && materialVo.getMaterialDepositoSaidaReserva().getId() != null) {
                // Atualizo a reserva
                MaterialDepositoSaidaReserva mdsr = materialVo.getMaterialDepositoSaidaReserva();
                mdsr.setItemIndisponivel(itemIndisponivel);
                //mdsr.setObservacaoPainelEstoquista(null);

                propriedades.clear();
                propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.ITEM_INDISPONIVEL));
                genericDao.updateWithCurrentTransaction(mdsr, propriedades);
            }

            Info info = Info.GetSuccess(Constantes.SUCESSO_OPERACAO_I18N, null);

            // FAZ A SAIDA DO ESTOQUE ANTIGO E A ENTRADA NO NOVO ESTOQUE
            // NO SISTEMA CPWEB
            if (enviaCpweb) {
                //Pego o nome do Usuário para mandar para o CPWEB
                String nomeUsuario;
                nomeUsuario = LoginService.getUsuarioLogado(request).getNome();
                if (nomeUsuario != null) {
                    materialVo.setNomeUsuario(nomeUsuario);
                }
                transferirMaterial(materialVo);
            }

            return info;
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            return Info.GetError(Constantes.REG_SALVO_ERRO_I18N);
        }
    }

    /**
     * Alterado o transferirMaterial para iniciar em uma thread
     *
     * @param vo
     */
    private void transferirMaterial(final MaterialTransferenciaVo vo) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                SincEquipamentoService sincReservaService = new SincEquipamentoService();
                sincReservaService.transferirMaterial(vo,true);
            }
        });
        t.start();
    }

    /**
     * Verifica se o deposito da RmMaterial tem a quantidade necessaria para o
     * material da RmMaterial
     *
     * @author Alyson X. Leite
     * @param rmMaterial Contem o material e a quantidade solitada do mesmo
     * @return boolean
     * @throws Exception
     */
    private boolean validaEstoqueMaterial(RmMaterial rmMaterial) throws Exception {
        NxCriterion nxCriterion;

        nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDeposito.DEPOSITO, rmMaterial.getDeposito(), Filtro.EQUAL));
        nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDeposito.MATERIAL, rmMaterial.getMaterial(), Filtro.EQUAL)));
        /* Filtra as propriedades da RequisiÃ§Ã£o */
        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID));
        propriedades.add(new Propriedade(MaterialDeposito.QUANTIDADE));

        GenericDao<MaterialDeposito> genericDao = new GenericDao<>();
        MaterialDeposito retorno = genericDao.selectUnique(propriedades, MaterialDeposito.class, nxCriterion);

        return retorno != null && retorno.getQuantidade() >= rmMaterial.getQuantidade();
    }

    /**
     * Recupera a quantidade de recebimentos referente a rmMaterial passada como
     * parametro
     *
     * @param rmMaterial
     * @return Info
     */
    @POST
    @Path("getQuantidadeRecebimentos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info getQuantidadeRecebimentos(RmMaterial rmMaterial) {
        try {
            RmMaterialRecebimentoService service = new RmMaterialRecebimentoService();
            Double q = service.getQuantidadeRecebimentos(rmMaterial);
            rmMaterial.setQtdRecebido(q);
            return Info.GetSuccess(rmMaterial);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            return Info.GetError(Constantes.REG_SALVO_ERRO_I18N);
        }
    }

    /**
     * Realiza apenas o recebimento do material Lucas Heitor
     *
     * @param obj
     * @return Info
     */
    @POST
    @Path("salvarRecebimentoMat")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info salvarRecebimentoMat(RmMaterialRecebimentoVo obj) {
        Info info;
        GenericDao<RmMaterialRecebimento> genericDao = new GenericDao<>();
        GenericDao<RmMaterial> genericDaoRmMaterial = new GenericDao<>();
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), RmMaterialRecebimentoVo.class.getName(), Util.getNomeMetodoAtual(), obj, null));

            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(null));

            RmMaterialRecebimentoService recebimentoServiceService = new RmMaterialRecebimentoService();
            RmMaterialRecebimento recebimento = obj.getRmMaterialRecebimento();
            recebimento.setDataEmissaoNf(Util.parseData(obj.getRmMaterialRecebimento().getStDataEmissaoNf(), rb.getString("format_date")));
            RmMaterial rmMaterial = recebimento.getRmMaterial();
            rmMaterial.setDataUltimaNotaFiscal(Util.parseData(obj.getRmMaterialRecebimento().getStDataEmissaoNf(), rb.getString("format_date")));
            RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService();

            if (rmMaterial.getIsRecSap() == null) {
                rmMaterial.setIsRecSap(Boolean.FALSE);
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(RmMaterial.IS_REC_SAP));
                genericDaoRmMaterial.update(rmMaterial, propriedades);
            }

            // Recupero a quantidade de recebimentos do material
            Double qtdRecebimento = recebimentoServiceService.getQuantidadeRecebimentos(rmMaterial);

            // Se for a mesma quantidade entao recebeu todos os materiais solicitados
            boolean atualizaStatus = false;
            if (rmMaterial.getQuantidade().equals((qtdRecebimento + recebimento.getQuantidade()))) {
                atualizaStatus = true;
            }

            recebimento.setDataRecebimentoMaterial(new Date());

            boolean recTelaPaiEsq = true;

            // Salva o recebimento e realiza o aceite do material
            String id = recebimentoServiceService.salvar(recebimento, recebimento.getQuantidade(), false, recebimento.getRmMaterial(), atualizaStatus, recTelaPaiEsq, null);

            genericDao.beginTransaction();
            RmMaterialStatus statusAtual = rmMaterialStatusService.listarStatusAtual(rmMaterial);
            rmMaterialStatusService.finalizarStatus(statusAtual, genericDao, new Date());

            if (!statusAtual.getStatus().getCodigo().equals(Constantes.STATUS_RM_MATERIAL_AGUARDANDO_RETIRADA) && rmMaterial.getColetorEstoque() == null || (rmMaterial.getColetorEstoque() != null && !rmMaterial.getColetorEstoque())) {
                rmMaterialStatusService.gerarStatus(rmMaterial, Constantes.STATUS_RM_MATERIAL_AGUARDANDO_RETIRADA, genericDao, null, null, null);
            }

            recebimentoServiceService.enviaEmail(Integer.parseInt(id), recebimento.getRmMaterial().getDeposito());

            genericDao.commitCurrentTransaction();

            info = Info.GetSuccess(Constantes.REGS_SALVOS_SUCESSO_I18N, obj);
            info.setIdObjetoSalvo(Integer.parseInt(id));
            return info;
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            return Info.GetError(Constantes.REG_SALVO_ERRO_I18N);
        }
    }

    /**
     * Realiza apenas a retirada do material
     *
     * @author Lucas Heitor
     *
     * @param obj
     * @return Info
     */
    @POST
    @Path("salvarRetiroMaterial")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info salvarRetiroMaterial(RmMaterialRetiradaVo obj) {
        Info info;
        GenericDao<RmMaterialRecebimento> genericDao = new GenericDao<>();

        Boolean finalizouRmMaterial = false;

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), RmMaterialRecebimentoVo.class.getName(), Util.getNomeMetodoAtual(), obj, null));

            obj.setQuantidadeMaxDepSelecionado(obj.getQuantidade());
            // Recupero a quantidade de recebimentos do material
            RmMaterialRecebimentoService recebimentoServiceService = new RmMaterialRecebimentoService();
            Double qtdRecebimento = recebimentoServiceService.getQuantidadeRecebimentos(obj.getRmMaterial());

            RmMaterialRetiradaService service = new RmMaterialRetiradaService();
            RmMaterialRetirada retirada = new RmMaterialRetirada();
            retirada.setPreRetirada(Boolean.TRUE);
            retirada.setDataRetirada(new Date());
            retirada.setQuantidade(obj.getQuantidade());
            retirada.setDeposito(obj.getDeposito());
            retirada.setRetiradoPor(obj.getRetiradoPor());
            retirada.setRmMaterial(obj.getRmMaterial());

            RmaService rmaService = new RmaService();
            Double qtdeTotalJaRecebida = rmaService.qtdeRmMatRetirada(retirada, false, false);

            if (!obj.isComBiometria()) {
                retirada.setPreRetirada(false);
            }

            GenericDao dao = new GenericDao<>();
            dao.beginTransaction();
            // Faco a pre-retirada, depois realizo a retirada
            info = service.preRetirada(retirada, dao, obj.isComBiometria(), false);
            boolean success = false;
            if (info.getCodigo().equals(Info.INFO_001)) {
                info = service.retirarEstoque(retirada, dao, false, false, qtdRecebimento, obj.getDeposito(), true, obj.getQuantidadeMaxDepSelecionado());
                success = info.getCodigo().equals(Info.INFO_001);
            }

            if (success) {
                if (!obj.isComBiometria()) {
                    if ((qtdeTotalJaRecebida + obj.getQuantidade()) == obj.getRmMaterial().getQuantidade()) {
                        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService();
                        Date data = new Date();
                        rmMaterialStatusService.gerarStatus(retirada.getRmMaterial(), Constantes.STATUS_RM_MATERIAL_FINALIZADO, dao, data, null, retirada.getRetiradoPor().getNome());

                        finalizouRmMaterial = true;
                    }
                }

                dao.commitCurrentTransaction();

                if (finalizouRmMaterial) {
                    rmaService.validarRetiradasNaoPresenciais(obj.getRmMaterial());
                }

            } else {
                dao.rollbackCurrentTransaction();
                return info;
            }

            info = Info.GetSuccess(Constantes.REGS_SALVOS_SUCESSO_I18N, obj);
            return info;
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            return Info.GetError(Constantes.REG_SALVO_ERRO_I18N);
        }
    }

    /**
     * Verifico quantas quantidades tem para realizar a retirada Lucas Heitor
     *
     * @param rmMaterial
     * @return Info
     */
    @POST
    @Path("getQuantidadeRetirada")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PrimitivoVo getQuantidadeRetirada(RmMaterial rmMaterial) {
        PrimitivoVo primitivoVo = new PrimitivoVo();

        try {
            GenericDao<RmMaterialRetirada> genericDao = new GenericDao();
            RmMaterialRecebimentoService recebimentoServiceService = new RmMaterialRecebimentoService();
            primitivoVo.setValorDouble(Double.MAX_VALUE);

            List<RmMaterialRetirada> lista = new ArrayList<>();

            String aliasRmMaterial = NxCriterion.montaAlias(RmMaterialRetirada.ALIAS_CLASSE, RmMaterialRetirada.RM_MATERIAL);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterialRetirada.RM_MATERIAL_RETIRADA_ID));
            propriedades.add(new Propriedade(RmMaterialRetirada.QUANTIDADE));

            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.RM_MATERIAL_ID, rmMaterial.getRmMaterialId(), Filtro.EQUAL, aliasRmMaterial));

            lista = genericDao.listarByFilter(propriedades, null, RmMaterialRetirada.class, Constantes.NO_LIMIT, nxCriterion);

            double quantidade = 0;
            for (RmMaterialRetirada rmMaterialRetirada : lista) {
                quantidade = quantidade + rmMaterialRetirada.getQuantidade();
            }

            Double quantidadeRecebida = recebimentoServiceService.getQuantidadeRecebimentos(rmMaterial);

            primitivoVo.setValorDouble(quantidadeRecebida - quantidade);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return primitivoVo;
    }

    /**
     * Lista os depositos que existem esse material para retirada de acordo com
     * o depositoSaidaReserva
     *
     * @param materialDepositoSaidaReserva
     * @return
     */
    @POST
    @Path("listaMaterialDepositoSaida")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<MaterialDepositoSaida> listaMaterialDepositoSaida(MaterialDepositoSaidaReserva materialDepositoSaidaReserva) {
        List<MaterialDepositoSaida> lista = new ArrayList<>();
        GenericDao genericDao = new GenericDao();

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), MaterialDepositoSaidaReserva.class.getName(), Util.getNomeMetodoAtual(), materialDepositoSaidaReserva, null));

            //Listo as quantidades dos itens solicitados nos depositos que nao
            //foram selecionados
            String aliasMaterialDeposito = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO);
            String aliasDeposito = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDeposito.DEPOSITO);
            String aliasMaterial = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDeposito.MATERIAL);
            String aliasUnidadeMedida = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO, MaterialDeposito.MATERIAL, Material.UNIDADE_MEDIDA);
            String aliasMaterialDepositoSaidaReserva = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID);
            String aliasMaterialDepositoSaidaReservaRmMaterial = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL);
            String aliasMaterialDepositoSaidaReservaMaterial = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.MATERIAL);
            String aliasMaterialDepositoSaidaReservaMaterialUM = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.MATERIAL, Material.UNIDADE_MEDIDA);
            String aliasMaterialDepositoSaidaReservaRmMaterialRm = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.RM);
            String aliasMaterialDepositoSaidaReservaRmMaterialRmRequisitante = NxCriterion.montaAlias(MaterialDepositoSaida.ALIAS_CLASSE, MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_RESERVA_ID, MaterialDepositoSaidaReserva.RM_MATERIAL, RmMaterial.RM, Rm.REQUISITANTE);

            //MaterialDepositoSaida
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(MaterialDepositoSaida.MATERIAL_DEPOSITO_SAIDA_ID));
            propriedades.add(new Propriedade(MaterialDepositoSaida.QUANTIDADE));
            propriedades.add(new Propriedade(MaterialDepositoSaida.RESERVA));
            propriedades.add(new Propriedade(MaterialDepositoSaida.RM));

            //MaterialDepositoSaida > MaterialDeposito
            propriedades.add(new Propriedade(MaterialDeposito.MATERIAL_DEPOSITO_ID, MaterialDeposito.class, aliasMaterialDeposito));

            //MaterialDepositoSaida > MaterialDeposito Saida Reserva
            propriedades.add(new Propriedade(MaterialDepositoSaidaReserva.ID, MaterialDepositoSaidaReserva.class, aliasMaterialDepositoSaidaReserva));

            //MaterialDepositoSaida > MaterialDeposito > Deposito
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasDeposito));

            //MaterialDepositoSaida > MaterialDeposito > Material
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME_COMPLETO, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP, Material.class, aliasMaterial));

            //Unidade Medid
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasUnidadeMedida));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasUnidadeMedida));

            //MaterialDepositoSaida > MaterialDepositoSaidaReserva > RmMaterial
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasMaterialDepositoSaidaReservaRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE, RmMaterial.class, aliasMaterialDepositoSaidaReservaRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.FLUXO_MATERIAL, RmMaterial.class, aliasMaterialDepositoSaidaReservaRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.OBSERVACAO_ESTOQUISTA, RmMaterial.class, aliasMaterialDepositoSaidaReservaRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.NUMERO_PEDIDO_COMPRA, RmMaterial.class, aliasMaterialDepositoSaidaReservaRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.COLETOR_ESTOQUE, RmMaterial.class, aliasMaterialDepositoSaidaReservaRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.IS_REC_SAP, RmMaterial.class, aliasMaterialDepositoSaidaReservaRmMaterial));

            //MaterialDepositoSaida > MaterialDepositoSaidaReserva > RmMaterial > Materiall
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterialDepositoSaidaReservaMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterialDepositoSaidaReservaMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterialDepositoSaidaReservaMaterial));
            propriedades.add(new Propriedade(Material.NOME_COMPLETO, Material.class, aliasMaterialDepositoSaidaReservaMaterial));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP, Material.class, aliasMaterialDepositoSaidaReservaMaterial));

            //Unidade Medid
            propriedades.add(new Propriedade(UnidadeMedida.UNIDADE_MEDIDA_ID, UnidadeMedida.class, aliasMaterialDepositoSaidaReservaMaterialUM));
            propriedades.add(new Propriedade(UnidadeMedida.DESCRICAO, UnidadeMedida.class, aliasMaterialDepositoSaidaReservaMaterialUM));
            propriedades.add(new Propriedade(UnidadeMedida.SIGLA, UnidadeMedida.class, aliasMaterialDepositoSaidaReservaMaterialUM));

            //Rm
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasMaterialDepositoSaidaReservaRmMaterialRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasMaterialDepositoSaidaReservaRmMaterialRm));

            //Requisitante
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasMaterialDepositoSaidaReservaRmMaterialRmRequisitante));
            propriedades.add(new Propriedade(Pessoa.EMAIL, Pessoa.class, aliasMaterialDepositoSaidaReservaRmMaterialRmRequisitante));

            NxCriterion nxCriterion;
            nxCriterion = NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaida.RM, materialDepositoSaidaReserva.getRmMaterial().getRm(), Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaida.RESERVA, true, Filtro.EQUAL)));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(MaterialDepositoSaidaReserva.RM_MATERIAL, materialDepositoSaidaReserva.getRmMaterial(), Filtro.EQUAL, aliasMaterialDepositoSaidaReserva)));
            lista = genericDao.listarByFilter(propriedades, null, MaterialDepositoSaida.class, Constantes.NO_LIMIT, nxCriterion);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), MaterialDepositoSaida.class.getName(), Util.getNomeMetodoAtual(), materialDepositoSaidaReserva, null));

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return lista;
    }

    /**
     * Se caso quando for fazer a retirada, o mesmo verifica se  é equipamento e epi, se retornar falso, não deixa realizar a ação.
     *
     * @param material
     * @return Info
     */
    @POST
    @Path("verificaEquipamentoEpi")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PrimitivoVo verificaEquipamentoEpi(Material material) {
        try {
            RmaService rmaService = new RmaService(request);
            boolean verificaEnvio;
            verificaEnvio = rmaService.verificaEnvioParaCpweb(material);
            if(verificaEnvio){
                return new PrimitivoVo(true);
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return new PrimitivoVo(false);
    }

    /**
     * Busca o RmMaterialStatus passando RmMaterial como parâmetro
     *
     * @param rmMaterial
     * @return
     */
    @POST
    @Path("verificaMaterialIndisponivel")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info verificaMaterialIndisponivel(RmMaterial rmMaterial) {
        Info info = new Info();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
        double quantidadeMaterialDeposito;
        try {
            MaterialDepositoService materialDepositoService = new MaterialDepositoService(request);
            quantidadeMaterialDeposito = materialDepositoService.getQuantidadeMaterialDeposito(rmMaterial);

            if (quantidadeMaterialDeposito <=0) {
                info.setCodigo(Info.INFO_002);
                info.setTipo(Info.TIPO_MSG_DANGER);
                info.setErro(null);
                info.setMensagem(rb.getString("label_item_indisponivel_no_deposito"));
            }else {

                info.setObjeto(quantidadeMaterialDeposito);
                info.setCodigo(Info.INFO_001);
                info.setTipo(Info.TIPO_MSG_SUCCESS);
                info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
                info.setErro(null);
            }
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
     * Recebe do CPWEB os números de patrimonio que tem disponiveis em estoque nos equipamentos.
     *
     * @param prefixoEquipamentoVo
     * @return
     */
    @POST
    @Path("autoCompletePrefixoEquipamento")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info autoCompletePrefixoEquipamento(PrefixoEquipamentoVo prefixoEquipamentoVo) {
        Info info = new Info();
        List<String> lista = new ArrayList<>();

        SincEquipamentoService sincReservaService = new SincEquipamentoService();
        info = sincReservaService.listaPrefixoEquipamento(prefixoEquipamentoVo);

        if(info.getErro() != null){
            return info;
        }else{
            //Se caso não der erro, o mesmo desconcatena a string que tem ; separando em uma lista o mesmo que veio do CPWEB
            lista = new ArrayList<String>(Arrays.asList(info.getObjeto().toString().split(";")));
            lista.removeAll(Arrays.asList("",null," "));
            info.setObjeto(lista);
        }
        return info;
    }

    /**
     * Recebe Vo com material anterior e novo material a ser atualizado na requisição
     * @author Igor R. Pessoa
     * @param painelEstoquistaVo
     * @return
     */
    @POST
    @Path("alterarMaterial")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info alterarMaterial(PainelEstoquistaVo painelEstoquistaVo) {
        Info info = new Info();
        RmMaterialStatusService rmMaterialStatusService = new RmMaterialStatusService(request);

        RmMaterialStatus rmMaterialStatusAnterior = new RmMaterialStatus();
        RmMaterialStatus rmMaterialStatusAtual = new RmMaterialStatus();
        GenericDao<RmMaterial> genericDao = new GenericDao<>();
        Date data = new Date();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

        try {
            info = verificaMaterialIndisponivel(painelEstoquistaVo.getMaterialAtual());
            if(!info.getCodigo().equals(Info.INFO_002)) {

                RmMaterial materialAtual = painelEstoquistaVo.getMaterialAtual();
                RmMaterial materialAnterior = painelEstoquistaVo.getMaterialAnterior();
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(RmMaterial.MATERIAL));
                propriedades.add(new Propriedade(RmMaterial.JUSTIFICATIVA_ALTERACAO_MATERIAL));
                propriedades.add(new Propriedade(RmMaterial.COD_MATERIAL_ANTERIOR));

                //Busca status atual (a ser feita antes para não ter problema com a transaction)
                rmMaterialStatusAtual = rmMaterialStatusService.listarStatusAtual(materialAtual);

                genericDao.beginTransaction();
                genericDao.updateWithCurrentTransaction(materialAtual, propriedades);

                //Finaliza o status de validação de estoque e indisponível
                rmMaterialStatusService.finalizarStatus(rmMaterialStatusAtual, genericDao, data);

                //Mensagem de observação
                String observacao = Util.setParamsLabel(rb.getString("msg_observacao_material_alterado"),
                        materialAnterior.getMaterial().getCodigo(), materialAtual.getMaterial().getCodigo(),
                        materialAtual.getJustificativaAlteracaoMaterial());
                //Gera status de alteração de material
                rmMaterialStatusService.gerarStatus(painelEstoquistaVo.getMaterialAtual(),
                        Constantes.STATUS_RM_MATERIAL_MATERIAL_ALTERADO,
                        genericDao,
                        data,
                        observacao,
                        null);
                //Gera estatus de validação de estoque
                rmMaterialStatusService.gerarStatus(painelEstoquistaVo.getMaterialAtual(),
                        Constantes.STATUS_RM_MATERIAL_AGUARDANDO_ESTOQUE,
                        genericDao,
                        null,
                        null,
                        null);

                //Envia email para os aprovadores avisando a mudança de material
                enviarEmailAprovador(painelEstoquistaVo);

                genericDao.commitCurrentTransaction();
                info.setObjeto(painelEstoquistaVo.getMaterialAtual());
                info.setCodigo(Info.INFO_001);
                info.setTipo(Info.TIPO_MSG_SUCCESS);
                info.setMensagem(Constantes.REG_SALVO_SUCESSO_I18N);
                info.setErro(null);
            }
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            genericDao.rollbackCurrentTransaction();
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(Constantes.REG_SALVO_ERRO_I18N);
        }
        return info;
    }

    /**
     * Lista os aprovadores na rm selecionada
     *@author Igor R. Pessoa
     * @param filtro
     * @return
     */
    private List<RmAprovador> listarAprovadores(Rm filtro) {
        List<RmAprovador> lista = null;
        try {
            String aliasRm = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID);
            String aliasRequisitante = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID,Rm.REQUISITANTE);
            String aliasAprovador = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.APROVADOR_PESSOA_ID);
            String aliasPrioridade = NxCriterion.montaAlias(RmAprovador.ALIAS_CLASSE, RmAprovador.RM_ID,Rm.PRIORIDADE);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroRmAprovacao.class.getName(), Util.getNomeMetodoAtual(), filtro, lista));
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmAprovador.ID));
            propriedades.add(new Propriedade(RmAprovador.ORDEM));
            propriedades.add(new Propriedade(RmAprovador.OBSERVACAO));
            propriedades.add(new Propriedade(RmAprovador.TIPO_APROVADOR));

            //RM
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));

            //Aprovador
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasAprovador));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasAprovador));
            propriedades.add(new Propriedade(Pessoa.EMAIL, Pessoa.class, aliasAprovador));

            NxCriterion nxCriterion;
            NxCriterion nxCriterionOr;

            //Listar os aprovadores de acordo com a rm
            nxCriterion = NxCriterion.montaRestriction(new Filtro(RmAprovador.RM_ID, filtro, Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion,NxCriterion.montaRestriction(new Filtro(RmAprovador.APROVADOR_PESSOA_ID, null, Filtro.NOT_NULL)));

            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(RmAprovador.ORDEM, NxOrder.NX_ORDER.ASC));

            GenericDao<RmAprovador> genericDao = new GenericDao<>();
            lista = genericDao.listarByFilter(propriedades, nxOrders, RmAprovador.class, Constantes.NO_LIMIT, nxCriterion);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroRmAprovacao.class.getName(), Util.getNomeMetodoAtual(), filtro, lista));

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    /**
     *
     * Envia email ao aprovador com as informacoes da rm e o link de aprovacao
     * @author Igor R. Pessoa
     * @param vo
     * */
    private void enviarEmailAprovador(PainelEstoquistaVo vo) {
        RmaService rmaService = new RmaService(request);
        String emails = "";
        String mensagemEmail = "";
        String tituloEmail;
        String link;

        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), RmMaterial.class.getName(), Util.getNomeMetodoAtual(), vo, null));

            //Email dos lideres de custo
            for (Pessoa p : rmaService.listarPessoaRole(Constantes.ROLE_LIDER_CUSTOS)) {
                if (p.getEmail() != null) {
                    emails += p.getEmail() + ";";
                }
            }
            List<RmAprovador> listaAprovadores = listarAprovadores(vo.getMaterialAnterior().getRm());
            //Email dos aprovadores dessa requisição
            for (RmAprovador rmA : listaAprovadores) {

                if (rmA.getAprovador() != null && rmA.getAprovador().getEmail() != null) {
                    emails += rmA.getAprovador().getEmail() + ";";
                }
            }


            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
            Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();


            tituloEmail = Util.setParamsLabel(rb.getString("msg_titulo_email_troca_material"));

            if (configuracao.getPrefUrlExternaSist() != null && !configuracao.getPrefUrlExternaSist().isEmpty()) {
                link = configuracao.getPrefUrlExternaSist();
            } else {
                link = LoginService.getUrlServidor(request);
            }

            //String parametro = Criptografia.cript("id=" + rmAprovador.getId());
            //link += request.getContextPath() + "/html5/rma/rmAprovacao.html#aprovacaoRma?id=" + parametro;
            link += request.getContextPath() + "/html5/rma/index.html";

            //Monta a mensagem do corpo do email
            mensagemEmail = Util.setParamsLabel(rb.getString("msg_email_rma_troca_material"),
                    vo.getMaterialAtual().getRm().getNumeroRm(),
                    vo.getMaterialAtual().getRm().getRequisitante().getNome(),
                    LoginService.getUsuarioLogado(request).getNome(),
                    vo.getMaterialAtual().getJustificativaAlteracaoMaterial(),
                    vo.getMaterialAnterior().getMaterial().getCodigo() + " - " + vo.getMaterialAnterior().getMaterial().getNome(),
                    vo.getMaterialAtual().getMaterial().getCodigo() + " - " + vo.getMaterialAtual().getMaterial().getNome(),
                    vo.getMaterialAtual().getQuantidade(),
                    link);

            final String subject = tituloEmail;
            final String recipients = emails;
            final String body = mensagemEmail;
            this.emailService.enviarEmail(subject, recipients, body);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), RmMaterial.class.getName(), Util.getNomeMetodoAtual(), vo, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
    }
}
