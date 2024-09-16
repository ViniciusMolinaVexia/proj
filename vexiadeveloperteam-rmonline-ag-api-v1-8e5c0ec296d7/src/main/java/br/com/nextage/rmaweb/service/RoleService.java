/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Role;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro
 */
public class RoleService {

    private final GenericDao<Role> genericDao;

    public RoleService() {
        genericDao = new GenericDao<>();
    }

    public List<Role> getAll(){
    	List<Role> lista = new ArrayList<>();
        try {

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Role.ROLE_ID));
            propriedades.add(new Propriedade(Role.DESCRICAO));
            propriedades.add(new Propriedade(Role.NOME));
            propriedades.add(new Propriedade(Role.ATIVO));

            //Obtem elementos.
            lista = (List<Role>) genericDao.listarByFilter(propriedades, null, Role.ROLE_ID, false, Role.class, -1);

        } catch (Exception e) {
            return null;
        }
        return lista;
    }
    
    public List listaAll() throws Exception {
        List<Role> lista = new ArrayList<>();
        try {

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Role.ROLE_ID));
            propriedades.add(new Propriedade(Role.DESCRICAO));
            propriedades.add(new Propriedade(Role.NOME));
            propriedades.add(new Propriedade(Role.ATIVO));

            //Obtem elementos.
            lista = (List<Role>) genericDao.listarByFilter(propriedades, null, Role.ROLE_ID, false, Role.class, -1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
