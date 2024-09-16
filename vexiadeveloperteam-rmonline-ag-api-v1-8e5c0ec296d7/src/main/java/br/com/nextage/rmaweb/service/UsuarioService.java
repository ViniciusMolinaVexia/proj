/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.rmaweb.dao.UsuarioDao;
import br.com.nextage.rmaweb.entitybean.Role;
import br.com.nextage.rmaweb.entitybean.Usuario;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.util.RelatorioFlexUtil;
import br.com.nextage.util.Util;
import br.com.nextage.util.criptografia.Criptografia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletOutputStream;

/**
 * Classe que recebe requisições do flex e faz a ponte para a obtenção de
 * valores da base de dados.
 *
 * @author marcelo
 */
public class UsuarioService {

    private GenericDao<Usuario> genericDao;
    private UsuarioDao usuarioDao;

    /**
     * Método construtor.
     */
    public UsuarioService() {
        genericDao = new GenericDao<>();
        usuarioDao = new UsuarioDao();
    }
    /**
     * Método que exclui um registro de Usuário.
     * <p/>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @param usuario - Usuario
     * @return result - Boolean
     * @throws Exception - Exception
     */
    public boolean excluir(Usuario usuario) throws Exception {
        Boolean result = false;
        try {
            result = genericDao.delete(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return result;
    }

    /**
     * Método que realiza uma pesquisa para listar os Compradores em uma grade
     * de dados no flex.
     * <p/>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @param nome - String
     * @param qtdRetorno - int
     * @return
     * @throws Exception - Exception
     */
    public List listaDataGrid(String nome, int qtdRetorno) throws Exception {
        List<Usuario> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            if (nome != null) {
                filtros.add(new Filtro(Usuario.NOME, nome, Filtro.LIKE));
            }

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Usuario.USUARIO_ID));
            propriedades.add(new Propriedade(Usuario.NOME));
            propriedades.add(new Propriedade(Usuario.LOGIN));
            propriedades.add(new Propriedade(Usuario.ATIVO));
            propriedades.add(new Propriedade(Usuario.COMPRADOR));
            propriedades.add(new Propriedade(Usuario.PESSOA));

            //Obtem elementos.
            lista = (ArrayList<Usuario>) genericDao.listarByFilter(propriedades, filtros, Usuario.NOME, false, Usuario.class, qtdRetorno);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;

    }

    /**
     * Método que salva o registro de um usuário qualquer.
     * <p/>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @param usuario - Usuario
     * @return idObjeto - String
     * @throws Exception - Exception
     */
    public String salvar(Usuario usuario) throws Exception {
        String idObjeto = "0";
        Integer idObjetoSalvo = 0;
        try {
            if (usuario.getUsuarioId() == null || usuario.getUsuarioId() <= 0) {
                if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
                    usuario.setSenha(Criptografia.criptMD5(usuario.getSenha()));
                    idObjeto = usuarioDao.persist(usuario);
                    usuario.setUsuarioId(idObjetoSalvo);
                }
            } else {
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(Usuario.NOME));
                propriedades.add(new Propriedade(Usuario.LOGIN));
                propriedades.add(new Propriedade(Usuario.ATIVO));
                propriedades.add(new Propriedade(Usuario.PESSOA));

                if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
                    propriedades.add(new Propriedade(Usuario.SENHA));
                    usuario.setSenha(Criptografia.criptMD5(usuario.getSenha()));
                }

                idObjetoSalvo = genericDao.update(usuario, propriedades);
                idObjeto = idObjetoSalvo.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    /**
     * Método que seleciona um registro de usuário para edição.
     * <p/>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @param usuarioId - Integer
     * @return obj - Usuario
     * @throws Exception - Exception
     */
    public Usuario selectByUnique(Integer usuarioId) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        Usuario obj = new Usuario();
        try {
            filtros.add(new Filtro(Usuario.USUARIO_ID, usuarioId, Filtro.EQUAL));

            obj = (Usuario) genericDao.selectUnique(filtros, Usuario.class);
            obj.setSenha(null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return obj;
    }

    /**
     * Método seleciona um registro de um usuário de acordo com o login
     * fornecido.
     * <p/>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @param login - String
     * @return obj - Usuario
     * @throws Exception - Exception
     */
    public Usuario selectByLogin(String login) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        Usuario obj = new Usuario();
        try {
            filtros.add(new Filtro(Usuario.LOGIN, login, Filtro.EQUAL));

            obj = (Usuario) genericDao.selectUnique(filtros, Usuario.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return obj;
    }

    /**
     * Método que verifica se o login e senha informados estão cadastradon no
     * banco de dados e se o usuário está ativo.
     * <p/>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @param login - String
     * @param senha - String
     * @return obj - Usuario
     * @throws java.lang.Exception
     */
    public Usuario login(String login, String senha) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        Usuario obj = new Usuario();
        try {
            filtros.add(new Filtro(Usuario.LOGIN, login, Filtro.EQUAL));
            filtros.add(new Filtro(Usuario.SENHA, Criptografia.criptMD5(senha), Filtro.EQUAL));
            filtros.add(new Filtro(Usuario.ATIVO, "S", Filtro.EQUAL));

            obj = (Usuario) genericDao.selectUnique(filtros, Usuario.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return obj;
    }

    /**
     * Método que realiza a alteração de senha de uma determinada conta.
     * <p/>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @param login - String
     * @param senha - String
     * @return alterado - String
     * @throws Exception - Exception
     */
    public String alteraSenha(String login, String senha) throws Exception {
        Usuario usr = new Usuario();
        String alterado = "";

        try {

            usr = (Usuario) this.selectByLogin(login);
            if (usr != null) {
                usr.setSenha(Criptografia.criptMD5(senha));
                alterado = this.salvar(usr);
            } else {
                alterado = "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
            alterado = "0";
            throw new Exception(e.getMessage());
        }
        return alterado;
    }

    /**
     * Método responsável por gerar relatórios
     * <p/>
     * <b>Autor:</b> marcelo.
     * <b>Data:</b> 15-03-2011
     * <p/>
     *
     * @param outputStream - ServletOutputStream
     * @param nomeJasper - String
     * @throws Exception - Exception
     */
    public void gerarRelatorioTesteServlet(ServletOutputStream outputStream, String nomeJasper) throws Exception {
        try {
            HashMap parameters = new HashMap();

            // Caminho da imagem.
            parameters.put(Constantes.URL_IMAGES, Util.getFileConfig().getString("config.url_images"));

            // Obtem o caminhos dos subrelatórios.
            parameters.put(Constantes.SUBREPORT_DIR, Util.getFileConfig().getString("config.url_relatorios"));

            RelatorioFlexUtil.geraPdf(outputStream, nomeJasper, parameters);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Método que realiza a alteração de senha de uma determinada conta.
     * <p/>
     * <b>Autor:</b> Jerry
     * <b>Data:</b> 02-05-2011
     * <p/>
     *
     *
     * @param usr - Usuario
     * @return "Sucesso" - String
     * @throws Exception - Exception
     */
    public String alteraSenhaUsuario(Usuario usr) throws Exception {

        if (usr != null && usr.getSenha() != null && usr.getUsuarioId() > 0) {
            try {
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(Usuario.SENHA));

                usr.setSenha(Criptografia.criptMD5(usr.getSenha()));

                genericDao.update(usr, propriedades);

            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception(e.getMessage());
            }
        }
        return "Sucesso";
    }
    
    /**
     * <b>getUsuariosPorPerfil</b><br />
     * Recupera todos os usuarios associados a um perfil
     * @param perfilId
     * @return
     * @throws Exception
     */
    public List<Usuario> getUsuariosPorPerfil(Integer perfilId) throws Exception{
    	return usuarioDao.getUsuariosPorPerfil(perfilId);
    }
    
    /**
     * <b>getAprovadoresArea</b><br />
     * REtorna todos os aprovadores de area
     * @return
     */
    public List<Usuario> getAprovadoresArea(){
    	try {
    		return usuarioDao.getUsuariosPorRole(Role.ROLE_APROVADOR_AREA);
    	}catch (Exception e) {
    		return null;
		}
    }
    
    /**
     * <b>getAprovadoresGerenteArea</b><br />
     * REtorna todos os aprovadores de gerente de area
     * @return
     */
    public List<Usuario> getAprovadoresGerenteArea(){
    	try {
    		return usuarioDao.getUsuariosPorRole(Role.ROLE_APROVADOR_GERENTE_AREA);
    	}catch (Exception e) {
    		return null;
		}
    }
    
    /**
     * <b>getAprovadoresCusto</b><br />
     * REtorna todos os aprovadores de custo
     * @return
     */
    public List<Usuario> getAprovadoresCusto(){
    	try {
    		return usuarioDao.getUsuariosPorRole(Role.ROLE_APROVADOR_CUSTO);
    	}catch (Exception e) {
    		return null;
		}
    }
    
    /**
     * <b>getAprovadoresEmergencial</b><br />
     * REtorna todos os aprovadores emergencial
     * @return
     */
    public List<Usuario> getAprovadoresEmergencial(){
    	try {
    		return usuarioDao.getUsuariosPorRole(Role.ROLE_APROVADOR_EMERGENCIAL);
    	}catch (Exception e) {
    		return null;
		}
    }
}
