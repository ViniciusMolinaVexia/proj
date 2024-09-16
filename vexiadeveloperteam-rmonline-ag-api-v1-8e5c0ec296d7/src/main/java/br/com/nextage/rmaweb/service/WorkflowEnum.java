package br.com.nextage.rmaweb.service;

import java.util.Arrays;

public enum WorkflowEnum {
    WORKFLOW_AREA("AREA"),
    WORKFLOW_GERENTE_AREA("GERENTE_AREA"),
    WORKFLOW_CUSTO("CUSTO"),
    WORKFLOW_EMERGENCIAL("EMERGENCIAL"),
    WORKFLOW_REPROVADO("REPROVADO");

    private String step;

    public String getStep() {
        return step;
    }

    public static WorkflowEnum getByStep(String step) {
        return Arrays.stream(values()).filter(w -> w.getStep().equals(step)).findFirst().orElse(null);
    }

    WorkflowEnum(String step) {
        this.step = step;
    }
}
