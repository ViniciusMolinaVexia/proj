/*
 * NextAge License
 * Copyright 2015 - Nextage
 * 
 */
package br.com.nextage.rmaweb.vo;

import br.com.nextage.rmaweb.entitybean.RmMaterial;
import br.com.nextage.rmaweb.entitybean.RmMaterialStatus;
import br.com.nextage.rmaweb.entitybean.VwDocumentoResponsabilidade;
import br.com.nextage.rmaweb.entitybean.VwRmMaterial;
import br.com.nextage.util.*;
import jxl.format.Colour;

import java.util.*;

/**
 *
 *
 */
public class DocumentoResponsabilidadeIndisRecVo {

    private List<VwDocumentoResponsabilidade> listaVwDocumentoResponsabilidade;
    private String requisitanteNome;
    private String requisitanteReferencia;
    private String requisitanteFuncao;
    private String requisitanteSetor;

    public List<VwDocumentoResponsabilidade> getListaVwDocumentoResponsabilidade() {
        return listaVwDocumentoResponsabilidade;
    }

    public void setListaVwDocumentoResponsabilidade(List<VwDocumentoResponsabilidade> listaVwDocumentoResponsabilidade) {
        this.listaVwDocumentoResponsabilidade = listaVwDocumentoResponsabilidade;
    }

    public String getRequisitanteNome() {
        return requisitanteNome;
    }

    public void setRequisitanteNome(String requisitanteNome) {
        this.requisitanteNome = requisitanteNome;
    }

    public String getRequisitanteReferencia() {
        return requisitanteReferencia;
    }

    public void setRequisitanteReferencia(String requisitanteReferencia) {
        this.requisitanteReferencia = requisitanteReferencia;
    }

    public String getRequisitanteFuncao() {
        return requisitanteFuncao;
    }

    public void setRequisitanteFuncao(String requisitanteFuncao) {
        this.requisitanteFuncao = requisitanteFuncao;
    }

    public String getRequisitanteSetor() {
        return requisitanteSetor;
    }

    public void setRequisitanteSetor(String requisitanteSetor) {
        this.requisitanteSetor = requisitanteSetor;
    }
}
