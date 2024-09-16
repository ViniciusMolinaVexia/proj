/*
 * NextAge License
 * Copyright 2015 - Nextage
 *
 */
package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.persistence_2.util.Paginacao;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.filtro.FiltroPrevisaoPendenciaRecebimento;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.VwRmMaterialVo;
import br.com.nextage.util.*;
import br.com.nextage.util.vo.ArquivoVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 *
 * @author Luan L F Domingues - <b>l.domingues@nextage.com.br<b>
 */
public class PrevisaoPendenciaRecebimentoService {

    private final HttpServletRequest request;
    private final Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    public PrevisaoPendenciaRecebimentoService(HttpServletRequest request) {
        this.request = request;
    }

    public PrevisaoPendenciaRecebimentoService() {
        request = null;
    }

    /**
     * Gera o Relatorio Excel
     *
     * @author Luan L F Domingues
     * @param lista lista do relatorio
     * @param filtro filtro utilizado para listar
     * @param depositos depositos utilizados na lista
     * @param isListener
     * @return byte[]
     */
    public ArquivoVo gerarExcelPendencia(List<VwRmMaterial> lista, FiltroPrevisaoPendenciaRecebimento filtro, List<Deposito> depositos, boolean isListener) {
        ArquivoVo vo = null;
        try {
            Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
            String labels = "";
            //cria map dos filtros
            NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
            //cria o vo da View, criado parater o RelatorioVo dentro dele
            List<VwRmMaterialVo> listaRel = new ArrayList<>();
            if (lista != null && !lista.isEmpty()) {
                for (VwRmMaterial vwRmMaterial : lista) {
                    listaRel.add(new VwRmMaterialVo(vwRmMaterial));
                }
            }

            Map<String, String> mapListaItens = new HashMap<>();
            int i = 0;
            //Map dos campos do relatorio
            if (configuracao.getHabilitaEapMultiEmpresa() != null && configuracao.getHabilitaEapMultiEmpresa() == true) {
                labels = rb.getString("label_eap_multi_empresa");
                mapListaItens.put(VwRmMaterial.RM_MATERIAL_ID + "." + RmMaterial.RM + "." + Rm.EAP_MULTI_EMPRESA + "." + EapMultiEmpresa.DESCRICAO, i++ + ":" + labels);
            }
            labels = rb.getString("label_referencia");
            mapListaItens.put(VwRmMaterial.RM_MATERIAL_ID + "." + RmMaterial.RM + "." + Rm.NUMERO_RM, i++ + ":" + labels);
            labels = rb.getString("label_requisitante");
            mapListaItens.put(VwRmMaterial.RM_MATERIAL_ID + "." + RmMaterial.RM + "." + Rm.REQUISITANTE + "." + Pessoa.NOME, i++ + ":" + labels);
            labels = rb.getString("label_codigo_material");
            mapListaItens.put(VwRmMaterial.RM_MATERIAL_ID + "." + RmMaterial.MATERIAL + "." + Material.CODIGO, i++ + ":" + labels);
            labels = rb.getString("label_material");
            mapListaItens.put(VwRmMaterial.RM_MATERIAL_ID + "." + RmMaterial.MATERIAL + "." + Material.NOME, i++ + ":" + labels);
            labels = rb.getString("label_deposito");
            mapListaItens.put(VwRmMaterial.RM_MATERIAL_ID + "." + RmMaterial.DEPOSITO_ID + "." + Deposito.NOME, i++ + ":" + labels);
            labels = rb.getString("label_quantidade");
            mapListaItens.put(VwRmMaterial.RM_MATERIAL_ID + "." + RmMaterial.QUANTIDADE, i++ + ":" + labels);
            labels = rb.getString("label_data_previsao_chegada");
            mapListaItens.put(VwRmMaterial.RM_MATERIAL_ID + "." + RmMaterial.DATA_PREVISAO_CHEGADA, i++ + ":" + labels);
            labels = rb.getString("label_dias_previstos");
            mapListaItens.put(VwRmMaterial.DIAS_PREVISTOS, i++ + ":" + labels);

            Map mapFiltro = new HashMap<>();
            String filtroStr = "";
            i = 0;

            if (configuracao.getHabilitaEapMultiEmpresa() != null && configuracao.getHabilitaEapMultiEmpresa() == true) {
                labels = rb.getString("label_eap_multi_empresa");
                if (filtro.getEapMultiEmpresa() != null && filtro.getEapMultiEmpresa().getDescricao() != null) {
                    filtroStr = filtro.getEapMultiEmpresa().getDescricao();
                } else {
                    filtroStr = "";
                }
            }
            mapFiltro.put(i++ + ":" + labels + ":", filtroStr);


            //Map dos filtros do relatorio
            //deposito
            labels = rb.getString("label_deposito");
            if (depositos != null && !depositos.isEmpty()) {
                for (Deposito dep : depositos) {
                    filtroStr += dep.getNome();
                    if (depositos.indexOf(dep) + 1 != depositos.size()) {
                        filtroStr += ", ";
                    }
                }
            } else {
                filtroStr = "";
            }
            mapFiltro.put(i++ + ":" + labels + ":", filtroStr);
            //caso a chamada for do listener não irá adicionar os outros filtros
            if (!isListener) {
                //material
                labels = rb.getString("label_material");
                if (filtro.getMaterial() != null) {
                    filtroStr = filtro.getMaterial().getCodigo() + " - " + filtro.getMaterial().getNome();
                } else {
                    filtroStr = "";
                }
                mapFiltro.put(i++ + ":" + labels + ":", filtroStr);
                labels = rb.getString("label_material_chave");
                if (filtro.getMaterialChave() != null) {
                    filtroStr = filtro.getMaterialChave();
                } else {
                    filtroStr = "";
                }
                mapFiltro.put(i++ + ":" + labels + ":", filtroStr);
                //requisitante
                labels = rb.getString("label_requisitante");
                if (filtro.getRequisitante() != null) {
                    filtroStr = filtro.getRequisitante().getNome();
                } else {
                    filtroStr = "";
                }
                mapFiltro.put(i++ + ":" + labels + ":", filtroStr);
                //numero Rma
                labels = rb.getString("label_numero");
                if (filtro.getNumeroRm() != null) {
                    filtroStr = filtro.getNumeroRm();
                } else {
                    filtroStr = "";
                }
                mapFiltro.put(i++ + ":" + labels + ":", filtroStr);
                //tipo Pendencia
                labels = rb.getString("label_tipo_pendencia");
                if (filtro.getTipoPendencia() != null) {
                    switch (filtro.getTipoPendencia()) {
                        case "":
                            filtroStr = rb.getString("label_todas");
                            break;
                        case Constantes.TIPO_PENDENCIA_ATRASADA:
                            filtroStr = rb.getString("label_atrasadas");
                            break;
                        case Constantes.TIPO_PENDENCIA_NO_PRAZO:
                            filtroStr = rb.getString("label_dentro_do_prazo");
                            break;
                    }
                } else {
                    filtroStr = "";
                }
                mapFiltro.put(i++ + ":" + labels + ":", filtroStr);
                //dias previstos
                labels = rb.getString("label_dias_previstos");
                if (filtro.getDiasPrevistos() != null) {
                    filtroStr = filtro.getDiasPrevistos().toString();
                } else {
                    filtroStr = "";
                }
                mapFiltro.put(i++ + ":" + labels + ":", filtroStr);
                //data
                labels = rb.getString("label_data_previsao_chegada");
                if (filtro.getStDataPrevisaoChegadaInicio() != null
                        || filtro.getStDataPrevisaoChegadaFim() != null) {
                    if (filtro.getStDataPrevisaoChegadaInicio() == null) {
                        filtro.setStDataPrevisaoChegadaInicio("");
                    }
                    if (filtro.getStDataPrevisaoChegadaFim() == null) {
                        filtro.setStDataPrevisaoChegadaFim("");
                    }
                    filtroStr = rb.getString("label_de") + " " + filtro.getStDataPrevisaoChegadaInicio() + " "
                            + rb.getString("label_ate") + " " + filtro.getStDataPrevisaoChegadaFim();
                } else {
                    filtroStr = "";
                }
                mapFiltro.put(i++ + ":" + labels + ":", filtroStr);
            }

            labels = rb.getString("label_previsao_pendencia_recebimento");
            RelatorioBean relatorioBean = new RelatorioBean();
            relatorioBean.setItens(listaRel);
            relatorioBean.setMapaListaItens(mapListaItens);
            relatorioBean.setMapaFiltro(mapFiltro);
            relatorioBean.setTipo(RelatorioBean.TipoRelatorio.XLS);
            relatorioBean.setTitulo(labels);
            relatorioBean.setNome(labels + ".xls");

            RelatorioUtil relatorioUtil = new RelatorioUtil();
            byte[] relatorio = relatorioUtil.geraRelatorio(relatorioBean);
            vo = new ArquivoVo();
            vo.setArquivo(relatorio);
            vo.setNmAnexo(relatorioBean.getNome());
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return vo;
    }

    /**
     * Lista em cima da View os RmMaterial
     *
     * @author Luan L F Domingues
     * @param filtro filto da pesquisa
     * @param isPaginado valida se a lista é paginada ou não
     * @param dataPrevisao data usada no filtro do listener
     * @return
     */
    public Object listar(FiltroPrevisaoPendenciaRecebimento filtro, boolean isPaginado, Date dataPrevisao) {
        Object objRetorno = null;
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
            List<Propriedade> propriedades = new ArrayList<>();

            //VIEW
            propriedades.add(new Propriedade(VwRmMaterial.ID));
            propriedades.add(new Propriedade(VwRmMaterial.DIAS_PREVISTOS));

            //RM MATERIAL
            String aliasRmMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID);
            propriedades.add(new Propriedade(RmMaterial.RM_MATERIAL_ID, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.QUANTIDADE, RmMaterial.class, aliasRmMaterial));
            propriedades.add(new Propriedade(RmMaterial.DATA_PREVISAO_CHEGADA, RmMaterial.class, aliasRmMaterial));

            //RM
            String aliasRm = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM);
            propriedades.add(new Propriedade(Rm.RM_ID, Rm.class, aliasRm));
            propriedades.add(new Propriedade(Rm.NUMERO_RM, Rm.class, aliasRm));

            //RM EAP MULTI EMPRESA
            String aliasEapMultiEmpresa = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.EAP_MULTI_EMPRESA);
            propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));

            //MATERIAL
            String aliasMaterial = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.MATERIAL);
            propriedades.add(new Propriedade(Material.MATERIAL_ID, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.NOME, Material.class, aliasMaterial));
            propriedades.add(new Propriedade(Material.CODIGO, Material.class, aliasMaterial));

            //REQUISITANTE
            String aliasRequisitante = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.RM, Rm.REQUISITANTE);
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasRequisitante));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasRequisitante));

            // DEPOSITO
            String aliasRmMaterialDeposito = NxCriterion.montaAlias(VwRmMaterial.ALIAS_CLASSE, VwRmMaterial.RM_MATERIAL_ID, RmMaterial.DEPOSITO_ID);
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID, Deposito.class, aliasRmMaterialDeposito));
            propriedades.add(new Propriedade(Deposito.NOME, Deposito.class, aliasRmMaterialDeposito));
            propriedades.add(new Propriedade(Deposito.CODIGO, Deposito.class, aliasRmMaterialDeposito));

            if (filtro != null) {
                NxCriterion nxCriterionAux = null;
                //só irá listar os que ainda não foram entregue na sua totalidade
                NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(RmMaterial.DATA_RECEBIMENTO_TOTAL, null, Filtro.IS_NULL, aliasRmMaterial));
                //Pega somente RmMaterial que foi comprado.
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(RmMaterial.DATA_COMPRA, null, Filtro.NOT_NULL, aliasRmMaterial)));

                // Pode selecionar mais de um depósito
                if (filtro.getIdsDeposito() != null && !filtro.getIdsDeposito().isEmpty()) {
                    nxCriterion = NxCriterion.and(nxCriterion,
                            NxCriterion.montaRestriction(new Filtro(Deposito.DEPOSITO_ID, filtro.getIdsDeposito(), Filtro.IN, aliasRmMaterialDeposito)));
                }

                if (filtro.getRequisitante() != null) {
                    nxCriterion = NxCriterion.and(nxCriterion,
                            NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, filtro.getRequisitante().getPessoaId(), Filtro.EQUAL, aliasRequisitante)));
                }

                if (filtro.getMaterial() != null) {
                    nxCriterion = NxCriterion.and(nxCriterion,
                            NxCriterion.montaRestriction(new Filtro(Material.MATERIAL_ID, filtro.getMaterial().getMaterialId(), Filtro.EQUAL, aliasMaterial)));
                }

                if (filtro.getEapMultiEmpresa() != null) {
                    nxCriterion = NxCriterion.and(nxCriterion,
                            NxCriterion.montaRestriction(new Filtro(EapMultiEmpresa.ID, filtro.getEapMultiEmpresa().getId(), Filtro.EQUAL, aliasEapMultiEmpresa)));
                }

                //Material Chave
                if (filtro.getMaterialChave() != null) {
                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Material.NOME, filtro.getMaterialChave(), Filtro.LIKE, aliasMaterial));
                    nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(Material.CODIGO, filtro.getMaterialChave(), Filtro.LIKE, aliasMaterial)));
                    if (nxCriterion != null) {
                        nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                    } else {
                        nxCriterion = nxCriterionAux;
                    }
                }

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

                if (filtro.getDiasPrevistos() != null) {
                    nxCriterion = NxCriterion.and(nxCriterion,
                            NxCriterion.montaRestriction(new Filtro(VwRmMaterial.DIAS_PREVISTOS, filtro.getDiasPrevistos(), Filtro.MAIOR_IGUAL)));
                }

                if (filtro.getTipoPendencia() != null && !filtro.getTipoPendencia().isEmpty()) {
                    //caso for atrasado irá listar os que diasPrevistos for menor que zero
                    //caso for no prazo irá validar os que for maior ou igual que zero
                    String filtroStatus = filtro.getTipoPendencia().equals(Constantes.TIPO_PENDENCIA_ATRASADA)
                            ? Filtro.MENOR : Filtro.MAIOR_IGUAL;
                    if (!filtroStatus.isEmpty()) {
                        nxCriterion = NxCriterion.and(nxCriterion,
                                NxCriterion.montaRestriction(new Filtro(VwRmMaterial.DIAS_PREVISTOS, 0, filtroStatus)));
                    }
                }

                if ((filtro.getStDataPrevisaoChegadaFim() != null && !filtro.getStDataPrevisaoChegadaFim().trim().isEmpty())
                        || (filtro.getStDataPrevisaoChegadaInicio() != null && !filtro.getStDataPrevisaoChegadaInicio().trim().isEmpty())) {
                    Date inicio = null;
                    Date fim = null;
                    NxResourceBundle rb = new NxResourceBundle(br.com.nextage.util.Constantes.MENSAGENS_UNDERLINE + LoginService.getLocale(request));
                    if (filtro.getStDataPrevisaoChegadaFim() != null && !filtro.getStDataPrevisaoChegadaFim().trim().isEmpty()) {
                        fim = Util.parseData(filtro.getStDataPrevisaoChegadaFim(), rb.getString("format_date"));
                    }
                    if (filtro.getStDataPrevisaoChegadaInicio() != null && !filtro.getStDataPrevisaoChegadaInicio().trim().isEmpty()) {
                        inicio = Util.parseData(filtro.getStDataPrevisaoChegadaInicio(), rb.getString("format_date"));
                    }
                    nxCriterion = NxCriterion.and(nxCriterion,
                            NxCriterion.montaRestriction(new Filtro(RmMaterial.DATA_PREVISAO_CHEGADA, inicio, fim, true, Filtro.BETWEEN, aliasRmMaterial)));
                }
                List<NxOrder> orders = Arrays.asList(new NxOrder(VwRmMaterial.RM_MATERIAL_ID + "." + RmMaterial.RM + "." + Rm.RM_ID, NxOrder.NX_ORDER.DESC));

                //caso for uma lista paginada irá listar paginado
                //caso não for irá listar todo o conjunto
                if (isPaginado) {
                    Paginacao.build(propriedades, orders, VwRmMaterial.class, nxCriterion, filtro.getPaginacaoVo());
                    objRetorno = filtro.getPaginacaoVo();
                } else {
                    if (dataPrevisao != null) {
                        nxCriterion = NxCriterion.and(nxCriterion,
                                NxCriterion.montaRestriction(new Filtro(RmMaterial.DATA_PREVISAO_CHEGADA, dataPrevisao, Filtro.MENOR, aliasRmMaterial)));
                    }
                    GenericDao<VwRmMaterial> gDao = new GenericDao<>();
                    objRetorno = gDao.listarByFilter(propriedades, orders, VwRmMaterial.class, Constantes.NO_LIMIT, nxCriterion);
                }
            }
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return objRetorno;
    }
}
