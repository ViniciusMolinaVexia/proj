/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.rmaweb.dao.UsuarioTelaDao;
import br.com.nextage.rmaweb.entitybean.Role;
import br.com.nextage.rmaweb.entitybean.Tela;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.entitybean.UsuarioTela;
import br.com.nextage.rmaweb.service.integracao.PerfilService;
import br.com.nextage.rmaweb.util.Constantes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marcelo
 */
public class UsuarioTelaService {

    private GenericDao<UsuarioTela> genericDao;
    private UsuarioTelaDao usuarioTelaDao;

    /**
     * Método construtor
     */
    public UsuarioTelaService() {
        genericDao = new GenericDao<>();
        usuarioTelaDao = new UsuarioTelaDao();
    }

    /**
     * Método que persiste uma lista de permissões de um dado usuário
     * <p/>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @param permissoes - List
     * @throws Exception - Exception
     */
    public void salvarPermissoes(List permissoes) throws Exception {
        try {
            if (permissoes != null && permissoes.size() > 0) {
                List<UsuarioTela> listaAux = permissoes;
                // Primeiro todas as permissões do usuário são excluídas.
                usuarioTelaDao.deleteAll(listaAux.get(0).getUsuario().getUsuarioId());

                for (UsuarioTela usuarioTela : listaAux) {
                    if (usuarioTela.getUsuarioTelaId() > -1) {
                        usuarioTelaDao.persist(usuarioTela);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Método que Obtem as telas para um usuário específico.
     * <p/>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @param usuarioId - Integer
     * @return lista
     * @throws Exception - Exception
     */
    public List<UsuarioTela> getTelasByUsuario(Integer usuarioId) throws Exception {
        List<UsuarioTela> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            if (usuarioId != null && usuarioId > 0) {
                filtros.add(new Filtro(UsuarioTela.USUARIO, new Usuario(usuarioId), Filtro.EQUAL));

                // Seta as propriedades de retorno da consulta.
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(UsuarioTela.TELA));
                propriedades.add(new Propriedade(UsuarioTela.USUARIO_TELA_ID));
                propriedades.add(new Propriedade(UsuarioTela.USUARIO));

                //Obtem elementos.
                lista = genericDao.listarByFilter(propriedades, filtros, null, false, UsuarioTela.class, Constantes.NO_LIMIT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Método que lista todas as permissões de acesso que um usuário pode
     * receber. É adicionado na lista todas as permissões que o usuário já
     * possui.
     * <p/>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @param usuarioId - Integer
     * @return lista
     * @throws Exception - Exception
     */
    public List listaUsuarioTelaByUsuario(Integer usuarioId) throws Exception {
        List<UsuarioTela> lista = new ArrayList<>();
        TelaService telaService = new TelaService();
        try {
            if (usuarioId != null && usuarioId > 0) {
                // Lista todas as telas cadastradas no sistema.
                List<Tela> telas = telaService.listaAll();
                if (telas != null && telas.size() > 0) {
                    for (Tela tela : telas) {
                        // Verifica se o usuário possui permissão para a tela.
                        List<Filtro> filtros = new ArrayList<>();
                        filtros.add(new Filtro(UsuarioTela.USUARIO, new Usuario(usuarioId), Filtro.EQUAL));
                        filtros.add(new Filtro(UsuarioTela.TELA, tela, Filtro.EQUAL));
                        UsuarioTela ut = (UsuarioTela) genericDao.selectUnique(filtros, UsuarioTela.class);

                        // Se o usuário não tiver permissão, então cria com ID -1.
                        if (ut == null) {
                            // Cria UsuarioTela temporários.
                            ut = new UsuarioTela(-1);
                            ut.setTela(tela);
                            ut.setUsuario(new Usuario(usuarioId));
                        }
                        lista.add(ut);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

    /**
     * Método que lista todas as roles de acesso que um usuário pode receber. É
     * adicionado na lista todas as roles que o usuário já possui.
     *
     * @param usuarioId - Integer
     * @return lista
     * @throws Exception - Exception
     */
    public List<Role> listaRoleByUsuario(Integer usuarioId) throws Exception {
        List<Role> roles = new ArrayList<Role>();
        PerfilService perfilService = new PerfilService();
        UsuarioService usuarioService = new UsuarioService();
        try {
            if (usuarioId != null && usuarioId > 0) {
            	Usuario usuario = usuarioService.selectByUnique(usuarioId);
            	roles = perfilService.getRolesPorPerfil(usuario.getPerfil().getPerfilId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roles;
    }

}
