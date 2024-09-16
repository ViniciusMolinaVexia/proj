package br.com.nextage.rmaweb.controller;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.persistence_2.util.Paginacao;
import br.com.nextage.persistence_2.vo.PaginacaoVo;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.filtro.FiltroDocumentoResponsabilidade;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.integracao.RmaService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.DocumentoResponsabilidadeVo;
import br.com.nextage.util.*;
import br.com.nextage.util.vo.ArquivoVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * @brief Classe DocumentoResponsabilidadePendController
 * @author Alyson X. Leite <a.leite@nextage.com.br>
 * @date 04/02/2016
 */
@Path("DocumentoResponsabilidadePend")
public class DocumentoResponsabilidadePendController {

    @Context
    private HttpServletRequest request;

    private static final Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    /**
     * Método rest para recuperar as requisições de campo com paginação
     *
     * @author Alyson X. Leite
     * @param filtro
     * @return PaginacaoVo - paginação de VwRmMaterial
     */
    @POST
    @Path("listarRmMaterialCampo")
    @Consumes("application/json")
    public PaginacaoVo listarRmMaterialCampo(FiltroDocumentoResponsabilidade filtro) {
        return (PaginacaoVo) listarRmMaterialCampoView(filtro, true);
    }

    /**
     * Responsavel por listar as rms que já estão finalizadas e que são do tipo
     * "Campo" de acordo com o filtro, com base na view VW_DOCUMENTO_RESPONSABILIDADE
     *
     * @author Yuri Mello
     * @param filtro
     * @param paginado
     * @return Object - lista ou paginacao de VwDocumentoResponsabilidade
     */
    public Object listarRmMaterialCampoView(FiltroDocumentoResponsabilidade filtro, boolean paginado) {
        try {
            List<Propriedade> propriedades = new ArrayList<>();

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), FiltroDocumentoResponsabilidade.class.getName(), Util.getNomeMetodoAtual(), filtro, null));

            //VIEW
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.ID));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.RM_MATERIAL_ID));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.FLUXO_MATERIAL));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.CF_RESPONSAVEL));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.QUANTIDADE));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.DOCUMENTO_RESPONSAVEL_IMPRESSO));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.PROTOCOLO_RESPONSABILIDADE));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.PREFIXO_EQUIPAMENTO));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.RM_MATERIAL_STATUS_ID));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.STATUS_NOME));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.STATUS_CODIGO));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.TIPO_MATERIAL_CODIGO));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.TIPO_MATERIAL_DESCRICAO));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.UNIDADE_MEDIDA_SIGLA));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.UNIDADE_MEDIDA_DESCRICAO));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.CF_RESPONSAVEL));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.NOME_RESPONSAVEL));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.RM_ID));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.NUMERO_RM));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.DATA_APLICACAO));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.DATA_EMISSAO));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.TIPO_REQUISICAO_CODIGO));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.MATERIAL_ID));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.MATERIAL_NOME));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.MATERIAL_CODIGO));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.MATERIAL_PATRIMONIADO));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.MATERIAL_HIERARQUIA));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.REQUISITANTE_NOME));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.REQUISITANTE_REFERENCIA));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.REQUISITANTE_SETOR));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.REQUISITANTE_FUNCAO));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.USUARIO_NOME));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.USUARIO_REFERENCIA));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.REQUISITANTE_ID));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.USUARIO_ID));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.DEPOSITO_NOME));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.DEPOSITO_EAP_MULTI_EMPRESA_DESCRICAO));
            propriedades.add(new Propriedade(VwDocumentoResponsabilidade.RM_EAP_MULTI_EMPRESA_DESCRICAO));

            NxCriterion nxCriterionAux = null;
            NxCriterion nxCriterion = null;
            //Fluxo Material
            nxCriterion = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.FLUXO_MATERIAL, Constantes.FLUXO_MATERIAL_CAMPO, Filtro.EQUAL));
            nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);

            //Codigo status
            if (filtro.getExibirImpresso() != null && filtro.getExibirImpresso().equals("F")) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.STATUS_CODIGO, Constantes.STATUS_RM_MATERIAL_FINALIZADO, Filtro.EQUAL));
            }else{
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.STATUS_CODIGO, Constantes.STATUS_RM_MATERIAL_AGUARDANDO_RETIRADA, Filtro.EQUAL));
            }
            nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);

            //Numero Rma
            if (filtro.getNumeroRm() != null && !filtro.getNumeroRm().isEmpty()) {
                filtro.setNumeroRm(filtro.getNumeroRm().replaceAll(",", ";"));
                String[] listaRma = filtro.getNumeroRm().split(";");
                NxCriterion nxAux = null;
                nxCriterionAux = null;
                for (String rma : listaRma) {
                    if (rma == null || rma.isEmpty()) {
                        continue;
                    }
                    nxAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.NUMERO_RM, rma, Filtro.EQUAL));
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

            // Requisitante
            if (filtro.getRequisitante() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.REQUISITANTE_ID, filtro.getRequisitante().getPessoaId(), Filtro.EQUAL));
                if (nxCriterion == null) {
                    nxCriterion = nxCriterionAux;
                } else {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                }
            }

            if (filtro.getEapMultiEmpresa() != null && filtro.getEapMultiEmpresa().getId() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.RM_EAP_MULTI_EMPRESA_ID, filtro.getEapMultiEmpresa().getId(), Filtro.EQUAL));
                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }


            // Cadastrante
            if (filtro.getUsuarioCadastrante() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.USUARIO_ID, filtro.getUsuarioCadastrante().getPessoaId(), Filtro.EQUAL));
                if (nxCriterion == null) {
                    nxCriterion = nxCriterionAux;
                } else {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                }
            }
            // Material
            if (filtro.getMaterial() != null) {
                Material mat = new Material(filtro.getMaterial().getMaterialId());
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.MATERIAL_ID, mat.getMaterialId(), Filtro.EQUAL));
                if (nxCriterion == null) {
                    nxCriterion = nxCriterionAux;
                } else {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                }
            }
            //Material Chave
            if (filtro.getMaterialChave() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.MATERIAL_NOME, filtro.getMaterialChave(), Filtro.LIKE));
                nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.MATERIAL_CODIGO, filtro.getMaterialChave(), Filtro.LIKE)));
                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }
            //Depósito
            if (filtro.getDeposito() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.DEPOSITO_CODIGO, filtro.getDeposito().getCodigo(), Filtro.EQUAL));
                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }
            // Protocolo
            if (filtro.getProtocolo() != null && !filtro.getProtocolo().isEmpty()) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.PROTOCOLO_RESPONSABILIDADE, filtro.getProtocolo(), Filtro.EQUAL));
                if (nxCriterion == null) {
                    nxCriterion = nxCriterionAux;
                } else {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                }
            }
            // Exibe os materiais que já foram impressos de acordo com o filtro
            // S -> Exibe materiais impressos
            // N -> Não exibe materiais impressos
            // T -> Exibe todos os materiais
            if (filtro.getExibirImpresso() != null && !filtro.getExibirImpresso().isEmpty()) {
                nxCriterionAux = null;
                switch (filtro.getExibirImpresso()) {
                    case Constantes.SIM_ABREVIADO:
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.DOCUMENTO_RESPONSAVEL_IMPRESSO, filtro.getExibirImpresso(), Filtro.EQUAL));
                        break;
                    case Constantes.NAO_ABREVIADO:
                        nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.DOCUMENTO_RESPONSAVEL_IMPRESSO, Constantes.NAO_ABREVIADO, Filtro.EQUAL));
                        nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.DOCUMENTO_RESPONSAVEL_IMPRESSO, null, Filtro.IS_NULL)));
                        break;

                }
                if (nxCriterionAux != null) {
                    if (nxCriterion == null) {
                        nxCriterion = nxCriterionAux;
                    } else {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    }
                }
            }

            Date dataEmissaoDe = null;
            Date dataEmissaoAte = null;

            Date dataAplicacaoDe = null;
            Date dataAplicacaoAte = null;
            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

            if (filtro.getStDataAplicacaoDe() != null && !filtro.getStDataAplicacaoDe().isEmpty()) {
                dataAplicacaoDe = Util.parseData(filtro.getStDataAplicacaoDe(), rb.getString("format_date"));
            }
            if (filtro.getStDataAplicacaoAte() != null && !filtro.getStDataAplicacaoAte().isEmpty()) {
                dataAplicacaoAte = Util.parseData(filtro.getStDataAplicacaoAte(), rb.getString("format_date"));
                dataAplicacaoAte.setHours(23);
                dataAplicacaoAte.setMinutes(59);
                dataAplicacaoAte.setSeconds(59);
            }

            if (filtro.getStDataEmissaoDe() != null && !filtro.getStDataEmissaoDe().isEmpty()) {
                dataEmissaoDe = Util.parseData(filtro.getStDataEmissaoDe(), rb.getString("format_date"));
            }
            if (filtro.getStDataEmissaoAte() != null && !filtro.getStDataEmissaoAte().isEmpty()) {
                dataEmissaoAte = Util.parseData(filtro.getStDataEmissaoAte(), rb.getString("format_date"));
                dataEmissaoAte.setHours(23);
                dataEmissaoAte.setMinutes(59);
                dataEmissaoAte.setSeconds(59);
            }


            if (dataEmissaoDe != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.DATA_EMISSAO, dataEmissaoDe, Filtro.MAIOR_IGUAL));

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }
            if (dataEmissaoAte != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.DATA_EMISSAO, dataEmissaoAte, Filtro.MENOR_IGUAL));

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            if (dataAplicacaoDe != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.DATA_APLICACAO, dataAplicacaoDe, Filtro.MAIOR_IGUAL));

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }
            if (dataAplicacaoAte != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(VwDocumentoResponsabilidade.DATA_APLICACAO, dataAplicacaoAte, Filtro.MENOR_IGUAL));

                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            List<NxOrder> nxOrder = Arrays.asList(new NxOrder(VwDocumentoResponsabilidade.RM_ID, NxOrder.NX_ORDER.DESC));

            if (paginado) {
                Paginacao.build(propriedades, nxOrder, VwDocumentoResponsabilidade.class, nxCriterion, filtro.getPaginacaoVo());
                for(Object vw  : filtro.getPaginacaoVo().getItensConsulta()) {
                    VwDocumentoResponsabilidade vwDocumentoResponsabilidade = (VwDocumentoResponsabilidade) vw;
                    if (vwDocumentoResponsabilidade.getDataAplicacao() != null) {
                        GregorianCalendar calendar = new GregorianCalendar();
                        calendar.setTime(vwDocumentoResponsabilidade.getDataAplicacao());
                        calendar.set(GregorianCalendar.HOUR_OF_DAY, 12);
                        vwDocumentoResponsabilidade.setDataAplicacao(calendar.getTime());
                    }
                    if (vwDocumentoResponsabilidade.getDataEmissao() != null) {
                        GregorianCalendar calendar = new GregorianCalendar();
                        calendar.setTime(vwDocumentoResponsabilidade.getDataEmissao());
                        calendar.set(GregorianCalendar.HOUR_OF_DAY, 12);
                        vwDocumentoResponsabilidade.setDataEmissao(calendar.getTime());
                    }
                }
                return filtro.getPaginacaoVo();
            } else {
                GenericDao<VwDocumentoResponsabilidade> dao = new GenericDao<>();
                List<VwDocumentoResponsabilidade> lista = dao.listarByFilter(propriedades, nxOrder, VwDocumentoResponsabilidade.class, Constantes.NO_LIMIT, nxCriterion);
                return lista;
            }
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
            return null;
        }

    }

    /**
     * Método rest para gerar os documentos de responsabilidade para os
     * materiais que já estão finalidos pelo estoquista
     *
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     * @param lista Lista com os materiais selecionados para serem
     * impressos
     * @return Info
     */
    @POST
    @Path("gerarDocumento")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Info gerarDocumento(List<VwDocumentoResponsabilidade> lista) {
        Info info;
        try {
            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));

            //cria lista de VwRmMaterial com base na lista de VwDocumentoResponsabilidade para trabalhar encima dela.
            List<VwRmMaterial> listaRmMaterial = new ArrayList<>();

            for (VwDocumentoResponsabilidade vw : lista) {
                VwRmMaterial vwRmMaterial = new VwRmMaterial();
                RmMaterial rmMaterial = new RmMaterial();
                Pessoa pessoaResponsavel = new Pessoa();
                Rm rm = new Rm();
                Pessoa requisitante = new Pessoa();
                Material material = new Material();
                UnidadeMedida unidadeMedida = new UnidadeMedida();
                TipoMaterial tipoMaterial = new TipoMaterial();
                //UnidadeMedida
                unidadeMedida.setUnidadeMedidaId(vw.getUnidadeMedidaId());
                unidadeMedida.setDescricao(vw.getUnidadeMedidaDescricao());
                unidadeMedida.setSigla(vw.getUnidadeMedidaSigla());
                //TipoMaterial
                tipoMaterial.setTipoMaterialId(vw.getTipoMaterialId());
                tipoMaterial.setCodigo(vw.getTipoMaterialCodigo());
                tipoMaterial.setDescricao(vw.getTipoMaterialDescricao());
                //Material
                material.setMaterialId(vw.getMaterialId());
                material.setCodigo(vw.getMaterialCodigo());
                material.setNome(vw.getMaterialNome());
                material.setPatrimoniado(vw.getMaterialPatrimoniado());
                material.setHierarquia(vw.getMaterialHierarquia());
                material.setHierarquia(vw.getMaterialHierarquia());
                material.setUnidadeMedida(unidadeMedida);
                material.setTipoMaterial(tipoMaterial);
                //Requisitante
                requisitante.setPessoaId(vw.getRequisitanteId());
                requisitante.setReferencia(vw.getRequisitanteReferencia());
                //Rm
                rm.setRmId(vw.getRmId());
                rm.setRequisitante(requisitante);
                rm.setNumeroRm(vw.getNumeroRm());
                rm.setDataAplicacao(vw.getDataAplicacao());
                rm.setDataEmissao(vw.getDataEmissao());
                //Responsável
                pessoaResponsavel.setPessoaId(vw.getResponsavelPessoaId());
                pessoaResponsavel.setReferencia(vw.getCfResponsavel());
                pessoaResponsavel.setNome(vw.getNomeResponsavel());
                //RmMaterial
                rmMaterial.setRmMaterialId(vw.getRmMaterialId());
                rmMaterial.setPessoaResponsavel(pessoaResponsavel);
                rmMaterial.setRm(rm);
                rmMaterial.setMaterial(material);
                rmMaterial.setQuantidade(vw.getQuantidade());
                rmMaterial.setPrefixoEquipamento(vw.getPrefixoEquipamento());
                //VwRmMaterial
                vwRmMaterial.setRmMaterial(rmMaterial);
                //Lista de VwRmMaterial
                listaRmMaterial.add(vwRmMaterial);
            }

            // Agrupo os materiais pelos responsáveis
            List<DocumentoResponsabilidadeVo> listaDoc = agruparRmMaterialPorCF(listaRmMaterial);

            // Agrupo os materiais pelos requisitantes
            List<DocumentoResponsabilidadeVo> listaProtocolo = agruparRmMaterialPorRequisitante(listaRmMaterial);

            //Gero número de protocolo
            Integer protocolo = gerarNumeroProtocolo();
            for (DocumentoResponsabilidadeVo prot : listaProtocolo) {
                for (RmMaterial rmMat : prot.getListaRmMaterial()) {
                    rmMat.setProtocoloResponsabilidade(protocolo.toString());
                }
                prot.setProtocolo(protocolo.toString());
                protocolo++;
            }
            for (DocumentoResponsabilidadeVo porCf : listaDoc) {
                for (RmMaterial rmMat : porCf.getListaRmMaterial()) {
                    for (DocumentoResponsabilidadeVo porEncarregado : listaProtocolo) {
                        if(rmMat.getRm().getRequisitante().equals(porEncarregado.getPessoa())){
                            rmMat.setProtocoloResponsabilidade(porEncarregado.getProtocolo());
                        }
                    }
                }
            }

            // Recupero os equipamentos (Com patrimonio e cautelaveis)
            List<DocumentoResponsabilidadeVo> listaEquipamento = getListaEquipamento(listaDoc);

            // Recupero os materiais de consumo
            List<DocumentoResponsabilidadeVo> listaMaterialConsumo = getListaMaterialConsumo(listaDoc);

            // Recupero os equipamentos EPI (Sem patrimonio e cautelaveis)
            List<DocumentoResponsabilidadeVo> listaEpi = getListaEquipamentoEpi(listaDoc);

            if (!listaEquipamento.isEmpty() || !listaEpi.isEmpty() || !listaMaterialConsumo.isEmpty() || !listaProtocolo.isEmpty()) {

                // Gero o relatorio
                RelatorioBean relatorioBean = new RelatorioBean();
                List<RelatorioBean> listaBeans = new ArrayList<>();

                if (!listaProtocolo.isEmpty()) {
                    // Recupero o relatorio jasper do protocolo
                    listaBeans.add(getConfigRelatorioProtocolo(listaProtocolo, rb));
                }

                if (!listaEquipamento.isEmpty()) {
                    // Recupero o relatorio jasper de equipamentos
                    listaBeans.add(getConfigRelatorioEquipamento(listaEquipamento, rb));
                }
                if (!listaEpi.isEmpty()) {
                    // Recupero o relatorio jasper de equipamentos epi
                    listaBeans.add(getConfigRelatorioEpi(listaEpi, rb));
                }

                if (!listaMaterialConsumo.isEmpty()) {
                    // Recupero o relatorio jasper dos materiais de consumo
                    listaBeans.add(getConfigRelatorioMaterialConsumo(listaMaterialConsumo, rb));
                }

                relatorioBean.setListaRelatorioBeanItens(listaBeans);
                relatorioBean.setTipo(RelatorioBean.TipoRelatorio.PDF);

                RelatorioUtil relatorioBeanUtil = new RelatorioUtil();
                byte[] conteudoRelatorio = relatorioBeanUtil.geraRelatorio(relatorioBean);
                ArquivoVo vo = new ArquivoVo();
                vo.setArquivo(conteudoRelatorio);
                vo.setNmAnexo(rb.getString("label_documento_responsabilidade") + ".pdf");

                // Gravo no rmMaterial o campo DOCUMENTO_RESPONSAVEL_IMPRESSO como S
                // Para saber quais materiais já foram impressos
                info = gravaRmMaterialImpresso(listaProtocolo);
                if (info.getCodigo().equals(Info.INFO_001)) {
                    info.setObjeto(vo);
                }

            } else {
                info = Info.GetError(rb.getString("label_selecione_um_material"));
            }
        } catch (Exception e) {
            info = Info.GetError(Constantes.ERRO_OPERACAO_I18N);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    /**
     * Recupero as informações do relatório de equipamentos (Com patrimonio e
     * cautelaveis)
     *
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     * @param listaDoc Lista que será exibida no relatório
     * @param rb resourceBundle
     * @return
     * @throws Exception
     */
    private RelatorioBean getConfigRelatorioEquipamento(List<DocumentoResponsabilidadeVo> listaDoc, NxResourceBundle rb) throws Exception {
        // Gera o relatorio
        RelatorioBean relatorioBean = new RelatorioBean();
        relatorioBean.setTitulo(rb.getString("label_documento_responsabilidade"));
        relatorioBean.setTipo(RelatorioBean.TipoRelatorio.PDF);

        relatorioBean.setNome("relatorio/DocumentoResponsabilidadeEquipamento.jasper");

        Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
        relatorioBean.setCaminhoJasper(conf.getCaminhoRealInstalacao());

        HashMap parameters = new HashMap();

        parameters.put("SUBREPORT_DIR", conf.getCaminhoRealInstalacao() + "relatorio/");
        parameters.put("IMAGES_DIR", conf.getCaminhoPathImagemRel());
        parameters.put("UNIDADE", conf.getNome());
        parameters.put("CENTRO", conf.getCentroCod());
        parameters.put("DATA_ENTREGA", Util.dateToString(new Date(), rb.getString("format_date")));

        relatorioBean.setParameters(parameters);

        Object objeto = listaDoc;
        relatorioBean.setListaJasperBeanCollection(objeto);
        return relatorioBean;
    }

    /**
     * Recupero as informações do relatório dos materiais de consumo
     *
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     * @param listaDoc Lista que será exibida no relatório
     * @param rb resourceBundle
     * @return
     * @throws Exception
     */
    private RelatorioBean getConfigRelatorioMaterialConsumo(List<DocumentoResponsabilidadeVo> listaDoc, NxResourceBundle rb) throws Exception {
        // Gera o relatorio
        RelatorioBean relatorioBean = new RelatorioBean();
        relatorioBean.setTitulo(rb.getString("label_documento_responsabilidade"));
        relatorioBean.setTipo(RelatorioBean.TipoRelatorio.PDF);

        relatorioBean.setNome("/relatorio/DocumentoResponsabilidadeMaterialConsumo.jasper");

        Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
        relatorioBean.setCaminhoJasper(conf.getCaminhoRealInstalacao());

        HashMap parameters = new HashMap();

        parameters.put("SUBREPORT_DIR", conf.getCaminhoRealInstalacao() + "relatorio/");
        parameters.put("IMAGES_DIR", conf.getCaminhoPathImagemRel());
        parameters.put("UNIDADE", conf.getNome());
        parameters.put("CENTRO", conf.getCentroCod());
        parameters.put("DATA_ENTREGA", Util.dateToString(new Date(), rb.getString("format_date")));

        relatorioBean.setParameters(parameters);

        Object objeto = listaDoc;
        relatorioBean.setListaJasperBeanCollection(objeto);
        return relatorioBean;
    }

    /**
     * Recupero as informações do relatório do protocolo
     *
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     * @param listaDoc Lista que será exibida no relatório
     * @param rb resourceBundle
     * @return
     * @throws Exception
     */
    private RelatorioBean getConfigRelatorioProtocolo(List<DocumentoResponsabilidadeVo> listaDoc, NxResourceBundle rb) throws Exception {
        // Gera o relatorio
        RelatorioBean relatorioBean = new RelatorioBean();
        relatorioBean.setTitulo(rb.getString("label_documento_responsabilidade"));
        relatorioBean.setTipo(RelatorioBean.TipoRelatorio.PDF);

        relatorioBean.setNome("/relatorio/DocumentoResponsabilidadeProtocolo.jasper");

        Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
        relatorioBean.setCaminhoJasper(conf.getCaminhoRealInstalacao());

        HashMap parameters = new HashMap();

        parameters.put("SUBREPORT_DIR", conf.getCaminhoRealInstalacao() + "relatorio/");
        parameters.put("IMAGES_DIR", conf.getCaminhoPathImagemRel());
        parameters.put("UNIDADE", conf.getNome());
        parameters.put("CENTRO", conf.getCentroCod());

        relatorioBean.setParameters(parameters);

        Object objeto = listaDoc;
        relatorioBean.setListaJasperBeanCollection(objeto);
        return relatorioBean;
    }

    /**
     * Recupero as informações do relatório de equipamentos epi (Sem patrimonio
     * e cautelaveis)
     *
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     * @param listaDoc Lista que será exibida no relatório
     * @param rb resourceBundle
     * @return
     * @throws Exception
     */
    private RelatorioBean getConfigRelatorioEpi(List<DocumentoResponsabilidadeVo> listaDoc, NxResourceBundle rb) throws Exception {
        // Gera o relatorio
        RelatorioBean relatorioBean = new RelatorioBean();
        relatorioBean.setTitulo(rb.getString("label_documento_responsabilidade"));
        relatorioBean.setTipo(RelatorioBean.TipoRelatorio.PDF);

        relatorioBean.setNome("/relatorio/DocumentoResponsabilidadeEpi.jasper");

        Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
        relatorioBean.setCaminhoJasper(conf.getCaminhoRealInstalacao());

        HashMap parameters = new HashMap();

        parameters.put("SUBREPORT_DIR", conf.getCaminhoRealInstalacao() + "relatorio/");
        parameters.put("IMAGES_DIR", conf.getCaminhoPathImagemRel());
        parameters.put("UNIDADE", conf.getNome());
        parameters.put("CENTRO", conf.getCentroCod());
        parameters.put("EMPRESA", conf.getEmpresa());
        parameters.put("DATA_ENTREGA", Util.dateToString(new Date(), rb.getString("format_date")));

        relatorioBean.setParameters(parameters);

        Object objeto = listaDoc;
        relatorioBean.setListaJasperBeanCollection(objeto);
        return relatorioBean;
    }

    /**
     * Agrupo os materiais pela Cf do responsavel E depois recupero o
     * responsavel de cada Cf
     *
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     * @param listaMaterial Lista de materiais que serão agrupados pelo CF
     * @return List<DocumentoResponsabilidadeVo>
     * @throws Exception
     */
    private List<DocumentoResponsabilidadeVo> agruparRmMaterialPorCF(List<VwRmMaterial> listaMaterial) throws Exception {

        List<String> listaReferencia = new ArrayList<>();
        // Agrupar os materiais por CF
        Map<String, List<RmMaterial>> group = new HashMap<>();
        for (VwRmMaterial vw : listaMaterial) {
            String cf = vw.getRmMaterial().getPessoaResponsavel().getReferencia();
            if (cf != null && !cf.isEmpty()) {
                if (group.get(cf) == null) {
                    group.put(cf, new ArrayList<RmMaterial>());
                }
                group.get(cf).add(vw.getRmMaterial());
                // Adicionar todas as referencias
                if (!listaReferencia.contains(cf)) {
                    listaReferencia.add(cf);
                }
            }
        }

        List<DocumentoResponsabilidadeVo> listaDoc = new ArrayList<>();
        if (listaReferencia.isEmpty()) {
            return listaDoc;
        }
        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
        propriedades.add(new Propriedade(Pessoa.REFERENCIA));
        propriedades.add(new Propriedade(Pessoa.NOME));
        propriedades.add(new Propriedade(Pessoa.FUNCAO));

        String aliasSetor = NxCriterion.montaAlias(Pessoa.ALIAS_CLASSE, Pessoa.SETOR);

        propriedades.add(new Propriedade(Setor.SETOR_ID, Setor.class, aliasSetor));
        propriedades.add(new Propriedade(Setor.CODIGO, Setor.class, aliasSetor));
        propriedades.add(new Propriedade(Setor.DESCRICAO, Setor.class, aliasSetor));

        // Listar as pessoas de acordo com as cfs agrupadas
        NxCriterion nxCrit = NxCriterion.montaRestriction(new Filtro(Pessoa.REFERENCIA, listaReferencia, Filtro.IN));

        GenericDao<Pessoa> gDao = new GenericDao<>();
        List<Pessoa> listaPessoa = gDao.listarByFilter(propriedades, null, Pessoa.class, listaReferencia.size(), nxCrit);

        // Adiciono as pessoas de acordo com seus materiais
        for (Map.Entry<String, List<RmMaterial>> entry : group.entrySet()) {
            final String cf = entry.getKey();
            List<RmMaterial> listaRmMaterial = entry.getValue();

            Pessoa pessoa = (Pessoa) CollectionUtils.find(listaPessoa, new Predicate() {
                @Override
                public boolean evaluate(Object o) {
                    Pessoa p = (Pessoa) o;
                    return p.getReferencia().equals(cf);
                }
            });
            if (pessoa != null) {
                listaDoc.add(new DocumentoResponsabilidadeVo(pessoa, listaRmMaterial));
            }
        }
        return listaDoc;
    }

    /**
     * Agrupo os materiais pelo Requisitante
     *
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     * @param listaMaterial Lista de materiais que serão agrupados pelo CF
     * @return List<DocumentoResponsabilidadeVo>
     * @throws Exception
     */
    private List<DocumentoResponsabilidadeVo> agruparRmMaterialPorRequisitante(List<VwRmMaterial> listaMaterial) throws Exception {

        List<String> listaReferencia = new ArrayList<>();
        // Agrupar os materiais por CF
        Map<String, List<RmMaterial>> group = new HashMap<>();
        for (VwRmMaterial vw : listaMaterial) {
            if (vw.getRmMaterial().getRm() != null && vw.getRmMaterial().getRm().getRequisitante() != null) {
                String cf = vw.getRmMaterial().getRm().getRequisitante().getReferencia();
                if (cf != null && !cf.isEmpty()) {
                    if (group.get(cf) == null) {
                        group.put(cf, new ArrayList<RmMaterial>());
                    }
                    group.get(cf).add(vw.getRmMaterial());
                    // Adicionar todas as referencias
                    if (!listaReferencia.contains(cf)) {
                        listaReferencia.add(cf);
                    }
                }
            }
        }

        List<DocumentoResponsabilidadeVo> listaDoc = new ArrayList<>();
        if (listaReferencia.isEmpty()) {
            return listaDoc;
        }
        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
        propriedades.add(new Propriedade(Pessoa.REFERENCIA));
        propriedades.add(new Propriedade(Pessoa.NOME));
        propriedades.add(new Propriedade(Pessoa.FUNCAO));

        String aliasSetor = NxCriterion.montaAlias(Pessoa.ALIAS_CLASSE, Pessoa.SETOR);

        propriedades.add(new Propriedade(Setor.SETOR_ID, Setor.class, aliasSetor));
        propriedades.add(new Propriedade(Setor.CODIGO, Setor.class, aliasSetor));
        propriedades.add(new Propriedade(Setor.DESCRICAO, Setor.class, aliasSetor));

        // Listar as pessoas de acordo com as cfs agrupadas
        NxCriterion nxCrit = NxCriterion.montaRestriction(new Filtro(Pessoa.REFERENCIA, listaReferencia, Filtro.IN));

        GenericDao<Pessoa> gDao = new GenericDao<>();
        List<Pessoa> listaPessoa = gDao.listarByFilter(propriedades, null, Pessoa.class, listaReferencia.size(), nxCrit);

        int i = 0;
        // Adiciono as pessoas de acordo com seus materiais
        for (Map.Entry<String, List<RmMaterial>> entry : group.entrySet()) {
            final String cf = entry.getKey();
            List<RmMaterial> listaRmMaterial = entry.getValue();

            Pessoa pessoa = (Pessoa) CollectionUtils.find(listaPessoa, new Predicate() {
                @Override
                public boolean evaluate(Object o) {
                    Pessoa p = (Pessoa) o;
                    return p.getReferencia().equals(cf);
                }
            });

            if (pessoa != null) {
                DocumentoResponsabilidadeVo doc = new DocumentoResponsabilidadeVo(pessoa, listaRmMaterial);
                listaDoc.add(doc);
            }
            i++;
        }

        return listaDoc;
    }

    /**
     * Recupero os equipamentos da lista (Com patrimonio e cautelaveis)
     *
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     * @param lista Lista que será filtrada para encontrar os equipamentos
     * @return
     */
    private List<DocumentoResponsabilidadeVo> getListaEquipamento(List<DocumentoResponsabilidadeVo> lista) throws Exception {

        List<DocumentoResponsabilidadeVo> retorno = new ArrayList<>();
        List<DocumentoResponsabilidadeVo> listaDoc = new ArrayList<>();
        listaDoc.addAll(lista);
        for (DocumentoResponsabilidadeVo vo : listaDoc) {
            List<RmMaterial> listaRmMaterial = new ArrayList<>();
            listaRmMaterial.addAll(vo.getListaRmMaterial());

            List<RmMaterial> listaRmMaterialRetorno = new ArrayList<>();
            for (RmMaterial rmMaterial : listaRmMaterial) {
                if (isCautelavelSemEpi(rmMaterial)) {
                    listaRmMaterialRetorno.add(rmMaterial);
                }
            }
            if (!listaRmMaterialRetorno.isEmpty()) {
                DocumentoResponsabilidadeVo voCopy = new DocumentoResponsabilidadeVo(vo.getPessoa(), listaRmMaterialRetorno);

                //Monta os proocolos gerados para o Encarregado da iteração.
                String protocolos = "";
                for(RmMaterial material : listaRmMaterial){
                    if(!protocolos.contains(material.getProtocoloResponsabilidade() + ";")) {
                        if (!protocolos.isEmpty()) {
                            protocolos = protocolos + material.getProtocoloResponsabilidade() + "; ";
                        } else {
                            protocolos = material.getProtocoloResponsabilidade() + "; ";
                        }
                    }
                }
                voCopy.setProtocolo(protocolos);
                retorno.add(voCopy);
            }
        }

        return retorno;
    }

    /**
     * Recupero os materiais de consumo da lista
     *
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     * @param lista Lista que será filtrada para encontrar os materiais de
     * consumo
     * @return
     */
    private List<DocumentoResponsabilidadeVo> getListaMaterialConsumo(List<DocumentoResponsabilidadeVo> lista) throws Exception {

        List<DocumentoResponsabilidadeVo> retorno = new ArrayList<>();
        List<DocumentoResponsabilidadeVo> listaDoc = new ArrayList<>();
        listaDoc.addAll(lista);
        for (DocumentoResponsabilidadeVo vo : listaDoc) {
            List<RmMaterial> listaRmMaterial = new ArrayList<>();
            listaRmMaterial.addAll(vo.getListaRmMaterial());
            List<RmMaterial> listaRmMaterialRetorno = new ArrayList<>();
            for (RmMaterial rmMaterial : listaRmMaterial) {
                if (isMaterialConsumo(rmMaterial)) {
                    listaRmMaterialRetorno.add(rmMaterial);
                }
            }
            if (!listaRmMaterialRetorno.isEmpty()) {
                DocumentoResponsabilidadeVo voCopy = new DocumentoResponsabilidadeVo(vo.getPessoa(), listaRmMaterialRetorno);

                //Monta os proocolos gerados para o Encarregado da iteração.
                String protocolos = "";
                for(RmMaterial material : listaRmMaterial){
                    if(!protocolos.contains(material.getProtocoloResponsabilidade() + ";")) {
                        if (!protocolos.isEmpty()) {
                            protocolos = protocolos + material.getProtocoloResponsabilidade() + "; ";
                        } else {
                            protocolos = material.getProtocoloResponsabilidade() + "; ";
                        }
                    }
                }
                voCopy.setProtocolo(protocolos);
                retorno.add(voCopy);
            }
        }

        return retorno;
    }

    /**
     * Recupero os equipamentos epi da lista (Sem patrimonio e cautelaveis)
     *
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     * @param lista Lista que será filtrada para encontrar os equipamentos epi
     * @return
     */
    private List<DocumentoResponsabilidadeVo> getListaEquipamentoEpi(List<DocumentoResponsabilidadeVo> lista) throws Exception {

        List<DocumentoResponsabilidadeVo> retorno = new ArrayList<>();
        List<DocumentoResponsabilidadeVo> listaDoc = new ArrayList<>();
        listaDoc.addAll(lista);
        for (DocumentoResponsabilidadeVo vo : listaDoc) {
            List<RmMaterial> listaRmMaterial = new ArrayList<>();
            listaRmMaterial.addAll(vo.getListaRmMaterial());
            List<RmMaterial> listaRmMaterialRetorno = new ArrayList<>();
            for (RmMaterial rmMaterial : listaRmMaterial) {
                if (isEpi(rmMaterial)) {
                    listaRmMaterialRetorno.add(rmMaterial);
                }
            }
            if (!listaRmMaterialRetorno.isEmpty()) {
                DocumentoResponsabilidadeVo voCopy = new DocumentoResponsabilidadeVo(vo.getPessoa(), listaRmMaterialRetorno);

                //Monta os proocolos gerados para o Encarregado da iteração.
                String protocolos = "";
                for(RmMaterial material : listaRmMaterial){
                    if(!protocolos.contains(material.getProtocoloResponsabilidade() + ";")) {
                        if (!protocolos.isEmpty()) {
                            protocolos = protocolos + material.getProtocoloResponsabilidade() + "; ";
                        } else {
                            protocolos = material.getProtocoloResponsabilidade() + "; ";
                        }
                    }
                }
                voCopy.setProtocolo(protocolos);
                retorno.add(voCopy);
            }
        }

        return retorno;
    }

    /**
     * Gravo os materiais com a coluna DOCUMENTO_RESPONSAVEL_IMPRESSO = S Para
     * saber quais materiais já foram impressos futuramente E gravo o numero do
     * protocolo gerado na lista de materiais
     *
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     *   Lista de materiais que será atualizada no banco de
     * dados
     * @return
     * @throws Exception
     */
    private Info gravaRmMaterialImpresso(List<DocumentoResponsabilidadeVo> listaProtocolo) throws Exception {
        Info info;
        GenericDao<RmMaterial> dao = new GenericDao<>();
        try {

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.DOCUMENTO_RESPONSAVEL_IMPRESSO));
            propriedades.add(new Propriedade(RmMaterial.PROTOCOLO_RESPONSABILIDADE));

            dao.beginTransaction();
            for (DocumentoResponsabilidadeVo prot : listaProtocolo) {
                for (RmMaterial rmMat : prot.getListaRmMaterial()) {
                    rmMat.setDocumentoResponsavelImpresso(Constantes.SIM_ABREVIADO);
                    dao.updateWithCurrentTransaction(rmMat, propriedades);
                }
            }
            dao.commitCurrentTransaction();
            info = Info.GetSuccess();
        } catch (Exception e) {
            dao.rollbackCurrentTransaction();
            info = Info.GetError(Constantes.ERRO_OPERACAO_I18N);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    /**
     * Verifico se o material é um equipamento
     *
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     * @param rmMaterial
     * @return
     */
    private boolean isEquipamento(RmMaterial rmMaterial) {
        return RmaService.isEquipamento(rmMaterial);
    }

    /**
     * Verifico se o material é cautelável.
     *
     * @param rmMaterial
     * @return
     * @author Marlos Morbis Novo
     */
    public boolean isCautelavelSemEpi(RmMaterial rmMaterial) {
        return RmaService.isCautelavelSemEpi(rmMaterial);
    }

    /**
     * Verifico se o material é um Equipamento EPI
     *
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     * @param rmMaterial
     * @return
     */
    private boolean isEpi(RmMaterial rmMaterial) {
        return RmaService.isEpi(rmMaterial);
    }

    /**
     * Verifico se o material é um Material de consumo
     *
     * @author Alyson X. Leite <a.leite@nextage.com.br>
     * @param rmMaterial
     * @return
     */
    private boolean isMaterialConsumo(RmMaterial rmMaterial) {
        return RmaService.isMaterialConsumo(rmMaterial);
    }

    private Integer gerarNumeroProtocolo() {
        Integer protocolo = 1;
        try {

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID));
            propriedades.add(new Propriedade(RmMaterial.PROTOCOLO_RESPONSABILIDADE));

            List<NxOrder> orders = new ArrayList<>();
            orders.add(new NxOrder(RmMaterial.RM_MATERIAL_ID, NxOrder.NX_ORDER.DESC));

            GenericDao<RmMaterial> genericDao = new GenericDao();

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.PROTOCOLO_RESPONSABILIDADE, null, Filtro.NOT_NULL));
            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterial.DOCUMENTO_RESPONSAVEL_IMPRESSO, Constantes.SIM_ABREVIADO, Filtro.NOT_NULL)));

            List<RmMaterial> lista = genericDao.listarByFilter(propriedades, orders, RmMaterial.class, Constantes.NO_LIMIT, nxCriterion);
            if (lista == null || lista.isEmpty()) {
                return protocolo;
            }
            for (RmMaterial rmMaterial : lista) {
                Integer num = rmMaterial.getProtocoloResponsabilidade() == null ? 1 : Integer.valueOf(rmMaterial.getProtocoloResponsabilidade()) + 1;
                if (num > protocolo) {
                    protocolo = num;
                }
            }
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return protocolo;
    }
}
