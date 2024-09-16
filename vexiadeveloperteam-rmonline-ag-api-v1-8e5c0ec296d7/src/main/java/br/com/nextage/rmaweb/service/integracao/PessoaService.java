/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.Setor;
import br.com.nextage.rmaweb.entitybean.SubArea;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jerry
 */
public class PessoaService {

    /**
     * Retorna um objeto de Pessoa serializado em JSON de acordo com a
     * referencia passada como parametro.
     *
     * @param id
     * @return Pessoa
     */
    public Pessoa selectPessoaById(Integer id) {
        Pessoa pessoa = null;
        try {
            if (id != null && id > 0) {
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
                propriedades.add(new Propriedade(Pessoa.NOME));
                propriedades.add(new Propriedade(Pessoa.REFERENCIA));
                propriedades.add(new Propriedade(Pessoa.CPF));
                propriedades.add(new Propriedade(Pessoa.TIPO_ATUACAO));
                propriedades.add(new Propriedade(Pessoa.EMAIL));
                propriedades.add(new Propriedade(Pessoa.IS_REQUISITANTE));
                propriedades.add(new Propriedade(Pessoa.TELEFONE));
                propriedades.add(new Propriedade(Pessoa.FUNCAO));

                String aliasSetor = NxCriterion.montaAlias(Pessoa.ALIAS_CLASSE,Pessoa.SETOR);
                propriedades.add(new Propriedade(Setor.SETOR_ID,Setor.class,aliasSetor));

                String aliasSubArea = NxCriterion.montaAlias(Pessoa.ALIAS_CLASSE,Pessoa.SUB_AREA);
                propriedades.add(new Propriedade(SubArea.SUB_AREA_ID,SubArea.class,aliasSubArea));

                NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID, id, Filtro.EQUAL));

                GenericDao<Pessoa> genericDao = new GenericDao<>();
                pessoa = genericDao.selectUnique(propriedades, Pessoa.class, nxCriterion);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pessoa;
    }


}
