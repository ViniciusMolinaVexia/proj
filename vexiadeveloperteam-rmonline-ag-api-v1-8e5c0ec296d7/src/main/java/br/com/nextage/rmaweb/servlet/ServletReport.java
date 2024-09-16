package br.com.nextage.rmaweb.servlet;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.rmaweb.entitybean.Comprador;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.relatorio.ControleRmRelatorio;
import br.com.nextage.rmaweb.service.MaterialDepositoSaidaService;
import br.com.nextage.rmaweb.service.RmMaterialRetiradaService;
import br.com.nextage.rmaweb.service.UsuarioService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.Util;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 * @version
 */
@SuppressWarnings("serial")
public class ServletReport extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String nomeArquivoGerado = "";

        // Obtem o relatório que deve ser invocado.
        String relatorio = request.getParameter("relatorio");

        if (relatorio != null) {
            try {

                // VERIFICA QUAL RELATORIO DEVE SER CHAMADO.
                switch (relatorio) {
                    case Constantes.RELATORIO_TESTE: {
                        UsuarioService service = new UsuarioService();
                        service.gerarRelatorioTesteServlet(response.getOutputStream(), relatorio);
                        // definindo que o mime-type será PDF.
                        response.setContentType("application/pdf");
                        nomeArquivoGerado = Long.toString(new Date().getTime()) + ".pdf";
                        response.setHeader("Content-disposition", "attachment; filename=\"" + nomeArquivoGerado + "\"");
                        break;
                    }
                    case Constantes.RELATORIO_CONTROLE_RM:
                        // RELATÓRIO CONTROLE DE RM.
                        ControleRmRelatorio crr = new ControleRmRelatorio();
                        String aliasRm = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM);
                        String aliasMaterial = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.MATERIAL);
                        String aliasComprador = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.COMPRADOR);
                        String aliasRequisitante = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.REQUISITANTE);
                        String aliasUsuario = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.USUARIO);
                        String aliasUsuarioPessoa = NxCriterion.montaAlias(RmMaterial.ALIAS_CLASSE, RmMaterial.RM, Rm.USUARIO, Usuario.PESSOA);
                        // Obtem os filtros a serem passados para o relatório
                        String[] numeroRm = request.getParameter("numeroRm").split("\\|");
                        String[] requisitantes = request.getParameter("requisitante").split("\\|");
                        String[] cadastrantes = request.getParameter("cadastrante").split("\\|");
                        String[] compradores = request.getParameter("comprador").split("\\|");
                        String[] materiais = request.getParameter("material").split("\\|");
                        String[] campos = request.getParameter("campos").split("\\|");
                        NxCriterion nxCriterion = null;
                        NxCriterion nxCriterionAux = null;
                        NxCriterion nxCriterionRm = null;
                        NxCriterion nxCriterionRequisitante = null;
                        NxCriterion nxCriterionCadastrante = null;
                        NxCriterion nxCriterionComprador = null;
                        NxCriterion nxCriterionMateriais = null;
                        NxCriterion nxCriterionDatas = null;
                        String filtro = "";
                        //MONTA FILTROS NUMERO RM
                        if (numeroRm != null && numeroRm.length > 0) {
                            for (String numeroRm1 : numeroRm) {
                                filtro = numeroRm1;
                                if (!filtro.trim().isEmpty()) {
                                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Rm.NUMERO_RM, filtro, Filtro.EQUAL, aliasRm));
                                    if (nxCriterionRm == null) {
                                        nxCriterionRm = nxCriterionAux;
                                    } else {
                                        nxCriterionRm = NxCriterion.or(nxCriterionRm, nxCriterionAux);
                                    }
                                }
                            }
                        }   //MONTA FILTROS POR REQUISITANTES
                        if (requisitantes != null && requisitantes.length > 0) {
                            for (String requisitante : requisitantes) {
                                filtro = requisitante;
                                if (!filtro.trim().isEmpty()) {
                                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, new Integer(filtro), Filtro.EQUAL, aliasRequisitante));
                                    if (nxCriterionRequisitante == null) {
                                        nxCriterionRequisitante = nxCriterionAux;
                                    } else {
                                        nxCriterionRequisitante = NxCriterion.or(nxCriterionRequisitante, nxCriterionAux);
                                    }
                                }
                            }
                        }   //MONTA FILTROS POR CADASTRANTES
                        if (cadastrantes != null && cadastrantes.length > 0) {
                            for (String cadastrante : cadastrantes) {
                                filtro = cadastrante;
                                if (!filtro.trim().isEmpty()) {
                                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, new Integer(filtro), Filtro.EQUAL, aliasUsuarioPessoa));
                                    if (nxCriterionCadastrante == null) {
                                        nxCriterionCadastrante = nxCriterionAux;
                                    } else {
                                        nxCriterionCadastrante = NxCriterion.or(nxCriterionCadastrante, nxCriterionAux);
                                    }
                                }
                            }
                        }   ////MONTA FILTROS COMPRADORES
                        if (compradores != null && compradores.length > 0) {
                            for (String compradore : compradores) {
                                filtro = compradore;
                                if (!filtro.trim().isEmpty()) {
                                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Comprador.COMPRADOR_ID, new Integer(filtro), Filtro.EQUAL, aliasComprador));
                                    if (nxCriterionComprador == null) {
                                        nxCriterionComprador = nxCriterionAux;
                                    } else {
                                        nxCriterionComprador = NxCriterion.or(nxCriterionComprador, nxCriterionAux);
                                    }
                                }
                            }
                        }   ////MONTA FILTROS MATERIAIS
                        if (materiais != null && materiais.length > 0) {
                            for (String materiai : materiais) {
                                filtro = materiai;
                                if (!filtro.trim().isEmpty()) {
                                    nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Material.MATERIAL_ID, new Integer(filtro), Filtro.EQUAL, aliasMaterial));
                                    if (nxCriterionMateriais == null) {
                                        nxCriterionMateriais = nxCriterionAux;
                                    } else {
                                        nxCriterionMateriais = NxCriterion.or(nxCriterionMateriais, nxCriterionAux);
                                    }
                                }
                            }
                        }
                        if (request.getParameter("dataInicioRecebimento") != null && !request.getParameter("dataInicioRecebimento").isEmpty()
                                && request.getParameter("dataFimRecebimento") != null && !request.getParameter("dataFimRecebimento").isEmpty()) {
                            System.out.println("Data" + request.getParameter("dataInicioRecebimento"));
                            nxCriterionDatas = NxCriterion.montaRestriction(new Filtro(Rm.DATA_RECEBIMENTO, Util.parseData(request.getParameter("dataInicioRecebimento"), "dd/MM/yyyy"), Util.parseData(request.getParameter("dataFimRecebimento"), "dd/MM/yyyy"), true, null, aliasRm));
                        }   //JUNTA TODOS OS FILTROS
                        if (nxCriterionDatas != null) {
                            nxCriterion = nxCriterionDatas;
                        }
                        if (nxCriterionComprador != null) {
                            if (nxCriterion == null) {
                                nxCriterion = nxCriterionComprador;
                            } else {
                                nxCriterion = NxCriterion.and(nxCriterion, nxCriterionComprador);
                            }
                        }
                        if (nxCriterionMateriais != null) {
                            if (nxCriterion == null) {
                                nxCriterion = nxCriterionMateriais;
                            } else {
                                nxCriterion = NxCriterion.and(nxCriterion, nxCriterionMateriais);
                            }
                        }
                        if (nxCriterionRequisitante != null) {
                            if (nxCriterion == null) {
                                nxCriterion = nxCriterionRequisitante;
                            } else {
                                nxCriterion = NxCriterion.and(nxCriterion, nxCriterionRequisitante);
                            }
                        }
                        if (nxCriterionCadastrante != null) {
                            if (nxCriterion == null) {
                                nxCriterion = nxCriterionCadastrante;
                            } else {
                                nxCriterion = NxCriterion.and(nxCriterion, nxCriterionCadastrante);
                            }
                        }
                        if (nxCriterionRm != null) {
                            if (nxCriterion == null) {
                                nxCriterion = nxCriterionRm;
                            } else {
                                nxCriterion = NxCriterion.and(nxCriterion, nxCriterionRm);
                            }
                        }   // definindo que o mime-type será Excel.
                        response.setContentType("application/vnd.ms-excel");
                        nomeArquivoGerado = "" + Constantes.RELATORIO_CONTROLE_RM + ".xls";
                        response.setHeader("Content-Disposition", "attachment; filename=" + nomeArquivoGerado + "");
                        crr.geraRelatorioXls(nxCriterion, response.getOutputStream(), campos);
                        break;
                    case Constantes.RELATORIO_PROTOCOLO_RETIRADA_MATERIAL: {
                        /*PROTOCOLO DE RETIRADA */
                        RmMaterialRetiradaService service = new RmMaterialRetiradaService();
                        service.gerarProtocoloRetirada(request, response.getOutputStream(), relatorio);
                        // definindo que o mime-type será PDF.
                        response.setContentType("application/pdf");
                        nomeArquivoGerado = "Protocolo Retirada - " + Long.toString(new Date().getTime()) + ".pdf";
                        response.setHeader("Content-disposition", "attachment; filename=\"" + nomeArquivoGerado + "\"");
                        break;
                    }
                    case Constantes.RELATORIO_PROTOCOLO_SAIDA_MATERIAL: {
                        /*PROTOCOLO DE SAÍDA */
                        MaterialDepositoSaidaService service = new MaterialDepositoSaidaService();
                        service.gerarProtocoloRetirada(request, response.getOutputStream(), relatorio);
                        // definindo que o mime-type será PDF.
                        response.setContentType("application/pdf");
                        nomeArquivoGerado = "Protocolo Saida - " + Long.toString(new Date().getTime()) + ".pdf";
                        response.setHeader("Content-disposition", "attachment; filename=\"" + nomeArquivoGerado + "\"");
                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
