package br.com.nextage.rmaweb.service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.nextage.rmaweb.controller.rm.material.status.todos.ObterTodosAprovadoresRmMaterialDao;
import br.com.nextage.rmaweb.dao.PrioridadeDao;
import br.com.nextage.rmaweb.dao.WorkflowAreaDAO;
import br.com.nextage.rmaweb.dao.WorkflowCustoDAO;
import br.com.nextage.rmaweb.dao.WorkflowDAO;
import br.com.nextage.rmaweb.dao.WorkflowEmergencialDAO;
import br.com.nextage.rmaweb.dao.WorkflowGerenteAreaDAO;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.Prioridade;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.entitybean.Workflow;
import br.com.nextage.rmaweb.entitybean.WorkflowArea;
import br.com.nextage.rmaweb.entitybean.WorkflowCusto;
import br.com.nextage.rmaweb.entitybean.WorkflowEmergencial;
import br.com.nextage.rmaweb.entitybean.WorkflowGerenteArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkflowService {

    private static final Logger log = LoggerFactory.getLogger(WorkflowService.class);

    private static final String PRIORIDADE_NORMAL = "NORMAL";
    private static final String PRIORIDADE_URGENTE = "URGENTE";
    private static final String PRIORIDADE_MAQUINA_PARADA = "MAQ_PARADA";

    private WorkflowDAO workflowDAO = WorkflowDAO.getInstance();
    private WorkflowAreaDAO workflowAreaDAO = WorkflowAreaDAO.getInstance();
    private WorkflowGerenteAreaDAO workflowGerenteAreaDAO = WorkflowGerenteAreaDAO.getInstance();
    private WorkflowCustoDAO workflowCustoDAO = WorkflowCustoDAO.getInstance();
    private WorkflowEmergencialDAO workflowEmergencialDAO = WorkflowEmergencialDAO.getInstance();

    @Inject
    private PessoaService pessoaService;

    @Inject
    private ObterTodosAprovadoresRmMaterialDao obterTodosAprovadoresRmMaterialDao;

    private WorkflowService() {
    }

    private static WorkflowService workflowService = null;

    public static WorkflowService getInstance() {
        if (workflowService == null) {
            workflowService = new WorkflowService();
        }
        return workflowService;
    }

    public Workflow getWorkflowPorId(Integer workflowId) {
        try {
            return workflowDAO.getWorkflowPorId(workflowId);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Workflow> getAll() {
        try {
            List<Workflow> ws = workflowDAO.getAll();
            for (Workflow w : ws) {
                w.setWorkflowAreas(workflowAreaDAO.getWorkflowAreas(w));
                w.setWorkflowCustos(workflowCustoDAO.getWorkflowCustos(w));
                w.setWorkflowEmergenciais(workflowEmergencialDAO.getWorkflowEmergenciais(w));
                w.setWorkflowGerenteAreas(workflowGerenteAreaDAO.getWorkflowGerenteAreas(w));
            }
            return ws;
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(Workflow workflow) {
        try {
            workflowAreaDAO.excluirPorWorkflow(workflow);
            workflowGerenteAreaDAO.excluirPorWorkflow(workflow);
            workflowCustoDAO.excluirPorWorkflow(workflow);
            workflowEmergencialDAO.excluirPorWorkflow(workflow);
            workflowDAO.excluir(workflow);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String salvar(Workflow workflow) throws Exception {
        try {
            Workflow tmp = workflowDAO.getWorkflowPorAreaIdCentroId(workflow.getArea().getAreaId(), workflow.getCentro().getCentroId());
            if (tmp == null || workflow.getWorkflowId() != null) {
                workflowDAO.saveOrUpdate(workflow);
                workflowAreaDAO.excluirPorWorkflow(workflow);
                workflowGerenteAreaDAO.excluirPorWorkflow(workflow);
                workflowCustoDAO.excluirPorWorkflow(workflow);
                workflowEmergencialDAO.excluirPorWorkflow(workflow);
                if (workflow.getWorkflowAreas() != null) {
                    for (WorkflowArea wa : workflow.getWorkflowAreas()) {
                        wa.setWorkflow(workflow);
                        workflowAreaDAO.save(wa);
                    }
                }
                workflow.setWorkflowAreas(null);
                if (workflow.getWorkflowGerenteAreas() != null) {
                    for (WorkflowGerenteArea wa : workflow.getWorkflowGerenteAreas()) {
                        wa.setWorkflow(workflow);
                        workflowGerenteAreaDAO.save(wa);
                    }
                }
                workflow.setWorkflowGerenteAreas(null);
                if (workflow.getWorkflowCustos() != null) {
                    for (WorkflowCusto wc : workflow.getWorkflowCustos()) {
                        wc.setWorkflow(workflow);
                        workflowCustoDAO.save(wc);
                    }
                }
                workflow.setWorkflowCustos(null);
                if (workflow.getWorkflowEmergenciais() != null) {
                    for (WorkflowEmergencial we : workflow.getWorkflowEmergenciais()) {
                        we.setWorkflow(workflow);
                        we.setStatus('N');
                        workflowEmergencialDAO.save(we);
                    }
                }
                workflow.setWorkflowEmergenciais(null);
                return "{\"msg\":\"msg_registros_salvo_sucesso\"}";
            } else {
                return "{\"msg\":\"msg_workflow_ja_cadastrado\"}";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Workflow getWorkflowRm(Integer idArea, Integer idCentro) {
        try {
            System.out.println(">>>>>>>> CENTRO:" + idCentro);
            System.out.println(">>>>>>>> AREA:" + idArea);
            Workflow w = workflowDAO.getWorkflowPorAreaIdCentroId(idArea, idCentro);
            if (w == null) {
                w = workflowDAO.getWorkflowPorCentroId(idCentro);
            }
            if (w == null) {
                return null;
            }
            return w;
        } catch (Exception e) {
            return null;
        }
    }

    public String getProximaEtapaRm(Workflow w, String etapaAnterior, Rm rm, Integer usuarioId) {
        List<Usuario> usuarios = null;
        try {

            if ("GERENTE_AREA".equals(etapaAnterior)) {
                return WorkflowEnum.WORKFLOW_EMERGENCIAL.name();
            }

            usuarios = workflowAreaDAO.getUsuariosWorkflow(w);
            if (usuarios != null && !usuarios.isEmpty() && etapaAnterior == null) {
                return WorkflowEnum.WORKFLOW_AREA.name();
            }

            usuarios = workflowGerenteAreaDAO.getUsuariosWorkflow(w);
            if (usuarios != null && !usuarios.isEmpty() && (etapaAnterior == null || etapaAnterior.equals(WorkflowEnum.WORKFLOW_AREA.name()))) {
                return WorkflowEnum.WORKFLOW_GERENTE_AREA.name();
            }


            WorkflowGerenteArea workflowGerenteArea = workflowGerenteAreaDAO.getWokflowGerenteAreaByUsuario(usuarioId);

            if (workflowGerenteArea.getUsuario().getPerfil().getNome().equals("Aprovador Gerente Area") && etapaAnterior.equals("GERENTE_AREA")) {
                return "GERENTE_AREA_OK";
            }
            usuarios = workflowEmergencialDAO.getUsuariosWorkflow(w);
            WorkflowEmergencial emergencial = workflowEmergencialDAO.getWorkflowByIdWorkFlow(w.getWorkflowId());

            if (emergencial.getStatus() == '0') {
                emergencial.setStatus('S');
                workflowEmergencialDAO.update(emergencial);
                return WorkflowEnum.WORKFLOW_EMERGENCIAL.name();
            }

            if (rm.getPrioridade().getCodigo().equals("MAQ_PARADA")) {
                usuarios = workflowEmergencialDAO.getUsuariosWorkflow(w);
                if (usuarios != null) {
                    return WorkflowEnum.WORKFLOW_EMERGENCIAL.name();
                }
            }
            usuarios = workflowCustoDAO.getUsuariosWorkflow(w);
            if (usuarios != null && (etapaAnterior == null || etapaAnterior.equals(WorkflowEnum.WORKFLOW_AREA.name()) || etapaAnterior.equals(WorkflowEnum.WORKFLOW_GERENTE_AREA.name()))) {
                return WorkflowEnum.WORKFLOW_CUSTO.name();
            }
            if (rm.getPrioridade().getCodigo().equals("MAQ_PARADA")) {
                usuarios = workflowEmergencialDAO.getUsuariosWorkflow(w);
                if (usuarios != null) {
                    return WorkflowEnum.WORKFLOW_EMERGENCIAL.name();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Pessoa getAprovadorRm(Workflow w, WorkflowEnum proximaEtapa) {
        List<Usuario> usuarios = null;
        try {
            log.info("getAprovadorRm");

            Optional<Usuario> usuario = null;

            if (WorkflowEnum.WORKFLOW_AREA == proximaEtapa) {
                usuarios = workflowAreaDAO.getUsuariosWorkflow(w);
                usuario = Optional.ofNullable(usuarios).orElse(Collections.emptyList()).stream().findFirst();
            } else if (WorkflowEnum.WORKFLOW_CUSTO == proximaEtapa) {
                usuarios = workflowCustoDAO.getUsuariosWorkflow(w);
                usuario = Optional.ofNullable(usuarios).orElse(Collections.emptyList()).stream().findFirst();
            } else if (WorkflowEnum.WORKFLOW_GERENTE_AREA == proximaEtapa) {
                usuarios = workflowGerenteAreaDAO.getUsuariosWorkflow(w);
                usuario = Optional.ofNullable(usuarios).orElse(Collections.emptyList()).stream().findFirst();
            } else if (WorkflowEnum.WORKFLOW_EMERGENCIAL == proximaEtapa) {
                usuarios = workflowEmergencialDAO.getUsuariosWorkflow(w);
                usuario = Optional.ofNullable(usuarios).orElse(Collections.emptyList()).stream().findFirst();
            }

            if (usuario.isPresent()) {
                log.info("Aprovador encontrado: " + usuario.get());
                return usuario.get().getPessoa();
            } else {
                log.info("Aprovador não encontrado: " + proximaEtapa);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPerfilAprovador(Usuario usuario) {

        List<String> perfis = this.workflowDAO.getPerfilAprovador(usuario);
        for (String perfil : perfis) {
            if (perfil.equals("Aprovador Custo")) {
                return perfil;
            }
        }
        return null;
    }

    public WorkflowEnum getNextStep(Rm rm, WorkflowEnum etapaAnterior) {
        Integer idArea = rm.getArea().getAreaId();
        Integer idCentro = rm.getCentro().getCentroId();

        List<WorkflowEnum> etapas = getEtapas(idArea, idCentro);
        etapas = this.obterWorkflowPorPrioridade(rm, etapas);

        if (etapaAnterior == null) {
            Optional<WorkflowEnum> firstWorkflowOpt = etapas.stream().findFirst();
            if (firstWorkflowOpt.isPresent()) {
                return firstWorkflowOpt.get();
            }
        }

        WorkflowEnum proximaEtapa = null;
        if (etapas.contains(etapaAnterior)) {
            Integer index = etapas.indexOf(etapaAnterior);
            Integer nextIndex = ++index;
            if (nextIndex < etapas.size()) {
                proximaEtapa = etapas.get(nextIndex);
            }
        }
        return proximaEtapa;
    }

    public List<WorkflowEnum> getCustomWorkflow(Rm rm) {
        Integer idArea = rm.getArea().getAreaId();
        Integer idCentro = rm.getCentro().getCentroId();

        List<WorkflowEnum> etapas = getEtapas(idArea, idCentro);
        return this.obterWorkflowPorPrioridade(rm, etapas);
    }

    private List<WorkflowEnum> obterWorkflowPorPrioridade(Rm rm, List<WorkflowEnum> etapas) {
        try {
            Prioridade prioridade = new PrioridadeDao()
                            .getPrioridadeId(rm.getPrioridade().getPrioridadeId());
            if (prioridade.isAtivo() && etapas != null) {
                if (PRIORIDADE_NORMAL.equals(prioridade.getCodigo())) {
                    /**
                     * coordenador
                     * gerente area
                     * gerente custo
                     */
                    if ("CGEQ".equals(rm.getArea().getCodigo())) {
                        return Optional.of(etapas).orElse(Collections.emptyList()).stream()
                                        .filter(etapa -> !etapa.equals(WorkflowEnum.WORKFLOW_GERENTE_AREA)).collect(
                                                        Collectors.toList());
                    } else {
                        return Optional.of(etapas).orElse(Collections.emptyList()).stream()
                                        .filter(etapa -> !etapa.equals(WorkflowEnum.WORKFLOW_EMERGENCIAL)).collect(
                                                        Collectors.toList());
                    }

                } else if (PRIORIDADE_URGENTE.equals(prioridade.getCodigo())) {
                    /**
                     * coordenador
                     * gerente area
                     * gerente custo
                     * emergencial
                     */
                    return etapas;
                } else if (PRIORIDADE_MAQUINA_PARADA.equals(prioridade.getCodigo())) {
                    /**
                     * emergencial
                     */
                    final List<WorkflowEnum> emergencial = Optional.of(etapas).orElse(Collections.emptyList()).stream()
                      .filter(etapa -> etapa.equals(WorkflowEnum.WORKFLOW_EMERGENCIAL)).collect(
                        Collectors.toList());

                    return emergencial;
                }
            } else {
                throw new Exception("Prioridade da RM não está ativa");
            }
        } catch (Exception e) {
            log.error("Erro ao filtrar etapas do workflow.", e);
        }
        return etapas;
    }

    private List<WorkflowEnum> getEtapas(Integer idArea, Integer idCentro) {
        try {
            Workflow w = getWorkflowRm(idArea, idCentro);
            List<WorkflowEnum> etapas = new ArrayList<>();

            if (Objects.nonNull(w)) {
                addEtapaArea(w, etapas);
                addEtapaGerenteArea(w, etapas);
                addEtapaCusto(w, etapas);
                addEtapaEmergencial(w, etapas);
            }
            return etapas;
        } catch (Exception e) {
            return null;
        }
    }

    private void addEtapaEmergencial(Workflow w, List<WorkflowEnum> etapas) {
        Long countEmergenciais = workflowEmergencialDAO.getCountWorkflowEmergenciais(w);
        if (countEmergenciais > 0) {
            etapas.add(WorkflowEnum.WORKFLOW_EMERGENCIAL);
        }
    }

    private void addEtapaGerenteArea(Workflow w, List<WorkflowEnum> etapas) {
        Long countGerenteArea = workflowGerenteAreaDAO.getCountWorkflowGerenteAreas(w);
        if (countGerenteArea > 0) {
            etapas.add(WorkflowEnum.WORKFLOW_GERENTE_AREA);
        }
    }

    private void addEtapaCusto(Workflow w, List<WorkflowEnum> etapas) {
        Long countCustos = workflowCustoDAO.getCountWorkflowCustos(w);
        if (countCustos > 0) {
            etapas.add(WorkflowEnum.WORKFLOW_CUSTO);
        }
    }

    private void addEtapaArea(Workflow w, List<WorkflowEnum> etapas) {
        Long countArea = workflowAreaDAO.getCountWorkflowAreas(w);
        if (countArea > 0) {
            etapas.add(WorkflowEnum.WORKFLOW_AREA);
        }
    }

}

