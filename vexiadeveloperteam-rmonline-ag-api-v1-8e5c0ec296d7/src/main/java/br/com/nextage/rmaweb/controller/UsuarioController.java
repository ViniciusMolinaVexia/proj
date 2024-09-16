package br.com.nextage.rmaweb.controller;


import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.persistence_2.util.Paginacao;
import br.com.nextage.persistence_2.vo.PaginacaoVo;
import br.com.nextage.rmaweb.dao.CentroDAO;
import br.com.nextage.rmaweb.dao.PerfilRoleDao;
import br.com.nextage.rmaweb.dao.TipoRequisicaoDAO;
import br.com.nextage.rmaweb.dao.UsuarioDao;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.filtro.FiltroUsuario;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.SincPessoaService;
import br.com.nextage.rmaweb.service.UsuarioService;
import br.com.nextage.rmaweb.service.integracao.PessoaService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.CadastroUsuarioVo;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import br.com.nextage.util.criptografia.Criptografia;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Path("UsuarioController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioController {

    private static final Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    @Context
    HttpServletRequest request;
    
    private TipoRequisicaoDAO tipoRequisicaoDAO = TipoRequisicaoDAO.getInstance();
    
    private CentroDAO centroDAO = CentroDAO.getInstance();
    
    /**
     * Lista usuários paginados
     * aprovador.
     * <p/>
     * Utilizado na tela de aprovação pelo sistema
     *
     * @param filtro
     * @return PaginacaoVo
     */
    @POST
    @Path("listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PaginacaoVo listar(FiltroUsuario filtro) {

        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Usuario.USUARIO_ID));
            propriedades.add(new Propriedade(Usuario.NOME));
            propriedades.add(new Propriedade(Usuario.LOGIN));
            propriedades.add(new Propriedade(Usuario.ATIVO));

            String aliasPessoa = NxCriterion.montaAlias(Usuario.ALIAS_CLASSE, Usuario.PESSOA);
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.CPF, Pessoa.class, aliasPessoa));

            String aliasComprador = NxCriterion.montaAlias(Usuario.ALIAS_CLASSE, Usuario.COMPRADOR);
            propriedades.add(new Propriedade(Comprador.COMPRADOR_ID, Comprador.class, aliasComprador));
            propriedades.add(new Propriedade(Comprador.NOME, Comprador.class, aliasComprador));
            propriedades.add(new Propriedade(Comprador.ATIVO, Comprador.class, aliasComprador));

            NxCriterion nxCriterion = null;
            NxCriterion nxCriterionAux = null;
            if(filtro.getNome() != null) {
                nxCriterion = NxCriterion.montaRestriction(new Filtro(Usuario.NOME, filtro.getNome(), Filtro.LIKE));
            }

            if(filtro.getLogin() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Usuario.LOGIN, filtro.getLogin(), Filtro.LIKE));
                if(nxCriterion != null){
                    nxCriterion = NxCriterion.and(nxCriterion,nxCriterionAux);
                }else{
                    nxCriterion = nxCriterionAux;
                }
            }

            if(filtro.getAtivo() != null) {
                nxCriterionAux = NxCriterion.montaRestriction(new Filtro(Usuario.ATIVO, filtro.getAtivo(), Filtro.EQUAL));
                if(filtro.getAtivo().equals("S")) {
                    nxCriterionAux = NxCriterion.or(nxCriterionAux, NxCriterion.montaRestriction(new Filtro(Usuario.ATIVO, null, Filtro.IS_NULL)));
                }
                if (nxCriterion != null) {
                    nxCriterion = NxCriterion.and(nxCriterion, nxCriterionAux);
                } else {
                    nxCriterion = nxCriterionAux;
                }
            }

            List<NxOrder> orders = Arrays.asList(new NxOrder(Usuario.NOME, NxOrder.NX_ORDER.ASC));
            Paginacao.build(propriedades, orders, Usuario.class, nxCriterion, filtro.getPaginacaoVo());

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }

        return filtro.getPaginacaoVo();
    }

    @POST
    @Path("selectById")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CadastroUsuarioVo selectById(int id) {
        CadastroUsuarioVo cadastroUsuarioVo = new CadastroUsuarioVo();
    	Usuario usuario = null;
    	List<UsuarioCentro> usuarioCentros = new ArrayList<UsuarioCentro>();
    	
    	GenericDao<Usuario> genericDao = new GenericDao<Usuario>();
    	UsuarioDao usuarioDAO = new UsuarioDao();
        try {
        	//Select Usuario
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Usuario.USUARIO_ID));
            propriedades.add(new Propriedade(Usuario.NOME));
            propriedades.add(new Propriedade(Usuario.LOGIN));
            propriedades.add(new Propriedade(Usuario.ATIVO));
            propriedades.add(new Propriedade(Usuario.COMPRADOR));
            propriedades.add(new Propriedade(Usuario.PESSOA));
            propriedades.add(new Propriedade(Usuario.FL_ADMIN));
            propriedades.add(new Propriedade(Usuario.PERFIL));
            propriedades.add(new Propriedade(Usuario.CENTRO));
            propriedades.add(new Propriedade(Usuario.AREA));

            String aliasPessoa = NxCriterion.montaAlias(Usuario.ALIAS_CLASSE, Usuario.PESSOA);
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.NOME, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA, Pessoa.class, aliasPessoa));
            propriedades.add(new Propriedade(Pessoa.CPF, Pessoa.class, aliasPessoa));

            String aliasComprador = NxCriterion.montaAlias(Usuario.ALIAS_CLASSE, Usuario.COMPRADOR);
            propriedades.add(new Propriedade(Comprador.COMPRADOR_ID, Comprador.class, aliasComprador));
            propriedades.add(new Propriedade(Comprador.NOME, Comprador.class, aliasComprador));
            propriedades.add(new Propriedade(Comprador.ATIVO, Comprador.class, aliasComprador));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Usuario.USUARIO_ID, id, Filtro.EQUAL));

            List<NxOrder> orders = Arrays.asList(new NxOrder(Usuario.USUARIO_ID, NxOrder.NX_ORDER.ASC));

            
            usuario = genericDao.selectUnique(propriedades, Usuario.class, nxCriterion);

            if(usuario != null && usuario.getUsuarioId() != null){
                usuario.setTipoRequisicoes(listaTipoRequisicoes());
                usuario.setEapMultiEmpresas(listaUsuarioEapMultiEmpresaToUsuario(usuario.getUsuarioId()));
            }
            cadastroUsuarioVo.setTipoRequisicoes(listaTipoRequisicoes());
            
            //Seta Usuario[]
            cadastroUsuarioVo.setUsuario(usuario);
            
            //select UsuarioCentro
            usuarioCentros = usuarioDAO.getUsuariosCentrosById(id);
            
            //Seta UsuarioCentro[]
            cadastroUsuarioVo.setUsuarioCentro(usuarioCentros);           
            
            List<Centro> centros = new ArrayList<Centro>();
            for (UsuarioCentro centro : cadastroUsuarioVo.getUsuarioCentro()) {
				centros.add(centro.getCentro());
			}  
          //Seta Centros[]
            cadastroUsuarioVo.setCentro(centros);
            
         // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedadesAll = new ArrayList<>();
            propriedadesAll.add(new Propriedade(Centro.CENTRO_ID));
            propriedadesAll.add(new Propriedade(Centro.NOME));
            propriedadesAll.add(new Propriedade(Centro.DESCRICAO));

            List<NxOrder> ordersAll = Arrays.asList(new NxOrder(Centro.NOME, NxOrder.NX_ORDER.ASC));

            //Obtem elementos.
            GenericDao<Centro> genericDaoAll = new GenericDao<Centro>();
            List<Centro> listaAll = genericDaoAll.listarByFilter(propriedadesAll, ordersAll,Centro.class, -1,null);
            
            //Todos os centros cadastrados
            cadastroUsuarioVo.setAllCentro(listaAll);
            
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return cadastroUsuarioVo;
    }

    @POST
    @Path("excluir")
    @Consumes("application/json")
    public Info excluir(Usuario usuario) {
        GenericDao<Usuario> genericDao = new GenericDao<>();
        GenericDao<UsuarioCentro> genericCentroDao = new GenericDao<>();
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(request));
        Info info;
        try {
            if (usuario != null) {
            	if(usuario.getUsuarioId() != null || usuario.getUsuarioId() > 0) {
            		UsuarioCentro objUsuarioCentro = new UsuarioCentro();
            		objUsuarioCentro.setUsuario(usuario);
            		genericCentroDao.delete(objUsuarioCentro);
            	}
                genericDao.delete(usuario);
            }

            info = new Info();
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem("msg_registro_exclusao_sucesso");
            info.setObjeto(usuario);
        } catch (Exception e) {
            info = Info.GetError(Constantes.REG_EXCLUSAO_ERRO_I18N);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;


    }


    @POST
    @Path("salvar")
    @Consumes("application/json")
    public Info salvar(CadastroUsuarioVo cadastroUsuarioVo) {
    	
        Info info = null;
        Integer idObjeto = -1;
    	UsuarioCentro usuarioCentro = new UsuarioCentro();
    	GenericDao<UsuarioCentro> genericUsuarioCentroDao = new GenericDao<UsuarioCentro>();
        GenericDao<Usuario> genericUsuarioDao = new GenericDao<Usuario>();
        UsuarioDao usuarioDAO = new UsuarioDao();
        
        try {
        	
            if (cadastroUsuarioVo.getUsuario().getUsuarioId() == null || cadastroUsuarioVo.getUsuario().getUsuarioId() <= 0) {
                if (cadastroUsuarioVo.getUsuario().getSenha() != null && !cadastroUsuarioVo.getUsuario().getSenha().isEmpty()) {
                	
                	cadastroUsuarioVo.getUsuario().setSenha(Criptografia.criptMD5(cadastroUsuarioVo.getUsuario().getSenha()));
                	cadastroUsuarioVo.getUsuario().setPessoa(cadastroUsuarioVo.getPessoa());
                	//Insert Usuario
                	genericUsuarioDao.beginTransaction();
                	
                    idObjeto = genericUsuarioDao.persist(cadastroUsuarioVo.getUsuario());
                    
                    genericUsuarioDao.commitCurrentTransaction();
                    
                    cadastroUsuarioVo.getUsuario().setUsuarioId(idObjeto);
                    cadastroUsuarioVo.getUsuario().setSenha(null);
                    
                    System.out.println("SIZE CENTRO LIST: =========> " + cadastroUsuarioVo.getCentro().size());
                    
                    for(int i = 0; i < cadastroUsuarioVo.getCentro().size(); i++) {
                    	
                    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                        Transaction trx = null;
                        trx = session.beginTransaction();
                        
                    	//genericUsuarioCentroDao.beginTransaction();
                    	//Insert Usuario Centro
                        usuarioCentro.setArea(cadastroUsuarioVo.getArea());
                        usuarioCentro.setUsuario(cadastroUsuarioVo.getUsuario());
                        usuarioCentro.setComprador(cadastroUsuarioVo.getComprador());
                        usuarioCentro.setPerfil(cadastroUsuarioVo.getPerfil());
                        usuarioCentro.setPessoa(cadastroUsuarioVo.getPessoa());
                        usuarioCentro.setCentro(cadastroUsuarioVo.getCentro().get(i));
                        
                        genericUsuarioCentroDao.persistWithNewTransaction(usuarioCentro, trx, session);
                        
                        trx.commit();
                    }
                    
                    info = new Info();
                    info.setTipo(Info.TIPO_MSG_SUCCESS);
                    info.setMensagem("msg_registro_salvo_sucesso");
                } 
                 
            } else {
                idObjeto = cadastroUsuarioVo.getUsuario().getUsuarioId();
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(Usuario.NOME));
                propriedades.add(new Propriedade(Usuario.LOGIN));
                propriedades.add(new Propriedade(Usuario.ATIVO));
                propriedades.add(new Propriedade(Usuario.PESSOA));
                propriedades.add(new Propriedade(Usuario.COMPRADOR));
                propriedades.add(new Propriedade(Usuario.PERFIL));
                propriedades.add(new Propriedade(Usuario.CENTRO));
                propriedades.add(new Propriedade(Usuario.AREA));

                if (cadastroUsuarioVo.getUsuario().getSenha() != null && !cadastroUsuarioVo.getUsuario().getSenha().isEmpty()) {
                    propriedades.add(new Propriedade(Usuario.SENHA));
                    cadastroUsuarioVo.getUsuario().setSenha(Criptografia.criptMD5(cadastroUsuarioVo.getUsuario().getSenha()));
                }

                //Atualiza usuário
                genericUsuarioDao.update(cadastroUsuarioVo.getUsuario(), propriedades);
                cadastroUsuarioVo.getUsuario().setSenha(null);
                
                //Lista centros atuais do usuário
                List<UsuarioCentro> listCentrosExcluir = usuarioDAO.getCentrosByUsuario(cadastroUsuarioVo.getUsuario().getUsuarioId());
                
                //Apaga centros atuais do usuário
                for (UsuarioCentro centros : listCentrosExcluir) {
					usuarioDAO.deleteCentrosByUsuario(centros.getUsuarioCentroId(), cadastroUsuarioVo.getUsuario().getUsuarioId());
				}
                  
                //Cadastra centros novos              
                for(int i = 0; i < cadastroUsuarioVo.getCentro().size(); i++) {
                	
                	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                    Transaction trx = null;
                    trx = session.beginTransaction();
                    
                	//Insert Usuario Centro
                    usuarioCentro.setArea(cadastroUsuarioVo.getArea());
                    usuarioCentro.setUsuario(cadastroUsuarioVo.getUsuario());
                    usuarioCentro.setComprador(cadastroUsuarioVo.getComprador());
                    usuarioCentro.setPerfil(cadastroUsuarioVo.getPerfil());
                    usuarioCentro.setPessoa(cadastroUsuarioVo.getPessoa());
                    usuarioCentro.setCentro(cadastroUsuarioVo.getCentro().get(i));
                    
                    genericUsuarioCentroDao.persistWithNewTransaction(usuarioCentro, trx, session);
                    
                    trx.commit();
                }
                
                info = new Info();
                info.setTipo(Info.TIPO_MSG_SUCCESS);
                info.setMensagem("msg_registro_salvo_sucesso");
            }

            if(info != null && Info.TIPO_MSG_SUCCESS.equals(info.getTipo())){
                info = salvarEapMultiEmpresas(cadastroUsuarioVo.getUsuario(),cadastroUsuarioVo.getEapMultiEmpresas());

                cadastroUsuarioVo.getUsuario().setTipoRequisicoes(listaTipoRequisicoes());
                cadastroUsuarioVo.getUsuario().setEapMultiEmpresas(listaUsuarioEapMultiEmpresaToUsuario(idObjeto));
                
                info.setIdObjetoSalvo(idObjeto);
                info.setObjeto(cadastroUsuarioVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            info = Info.GetError(Constantes.REG_SALVO_ERRO_I18N);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }


    @POST
    @Path("salvarSenha")
    @Consumes("application/json")
    public Info salvarSenha(Usuario usuario) {
        Info info = null;

        Integer idObjeto = -1;
        try {
            GenericDao<Usuario> genericDao = new GenericDao<Usuario>();
            if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {

                idObjeto = usuario.getUsuarioId();
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(Usuario.SENHA));

                usuario.setSenha(Criptografia.criptMD5(usuario.getSenha()));
                genericDao.update(usuario, propriedades);

                info = new Info();
                info.setTipo(Info.TIPO_MSG_SUCCESS);
                info.setMensagem("msg_registro_salvo_sucesso");
            }
        } catch (Exception e) {
            e.printStackTrace();
            info = Info.GetError(Constantes.REG_SALVO_ERRO_I18N);
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }

    /**
     * Lista de tipos requisições do usuario para o multiselect
     *
     * @param  usuarioId
     * @return List<UsuarioRole>
     */
    @POST
    @Path("listaUsuarioEapMultiEmpresaToUsuario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsuarioEapMultiEmpresa> listaUsuarioEapMultiEmpresaToUsuario(Integer usuarioId) throws Exception {
        List<UsuarioEapMultiEmpresa> lista = new ArrayList<>();

        try {

            // Lista todas as telas cadastradas no sistema.
            List<EapMultiEmpresa> requisicoes = listaAllEapMultiEmpresa();
            if (requisicoes != null && requisicoes.size() > 0) {

                List<UsuarioEapMultiEmpresa> listaAux = listarEapMultiEmpresasOfUsuario(usuarioId);
                for (EapMultiEmpresa req : requisicoes) {
                    UsuarioEapMultiEmpresa utr = null;
                    if(listaAux != null) {
                        for (UsuarioEapMultiEmpresa urAux : listaAux) {
                            if (urAux.getEapMultiEmpresa().equals(req)) {
                                utr = urAux;
                                break;
                            }
                        }
                    }

                    // Se o usuário não tiver permissão, então cria com ID -1.
                    if (utr == null) {
                        // Cria UsuarioTela temporários.
                        utr = new UsuarioEapMultiEmpresa(-1);
                        utr.setSelected(false);
                    }else{
                        utr.setSelected(true);
                    }
                    utr.setEapMultiEmpresa(req);
                    utr.setUsuario(new Usuario(usuarioId));

                    lista.add(utr);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    /**
     * Lista tipos de requisicao
     *
     * @param  usuarioId
     * @return List<TipoRequisicao>
     */
    @POST
    @Path("listaTiposRequisicao")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoRequisicao> listaTipoRequisicoes() throws Exception {
    	return tipoRequisicaoDAO.getAll();
    }

    /**
     * Método que persiste uma lista de EapMultiEmpresa de um dado usuário
     * <p/>
     * <b>Autor:</b> jerr.
     * <b>Data:</b> 23/08/2016
     * <p/>
     *
     * @param lista - List
     */
    private Info salvarEapMultiEmpresas(Usuario usuario,List<UsuarioEapMultiEmpresa> lista) {
        Info info = null;
        try {
            GenericDao<UsuarioEapMultiEmpresa> genericDao = new GenericDao<UsuarioEapMultiEmpresa>();

            if (lista != null && lista.size() > 0) {
                List<UsuarioEapMultiEmpresa> listaAux = listarEapMultiEmpresasOfUsuario(usuario.getUsuarioId());
                for (UsuarioEapMultiEmpresa del : listaAux) {
                    genericDao.delete(del);
                }
                for (UsuarioEapMultiEmpresa obj : lista) {
                    if (obj.getSelected()) {
                        obj.setId(-1);
                        obj.setUsuario(usuario);
                        genericDao.persist(obj);
                    }
                }
            }
            info = new Info();
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem("msg_registro_salvo_sucesso");

        } catch (Exception e) {
            e.printStackTrace();
            info = Info.GetError(Constantes.REG_SALVO_ERRO_I18N);
        }
        return info;
    }
    
    public List<Role> listaAllRole() throws Exception {
        List<Role> lista = new ArrayList<>();
        try {

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Role.ROLE_ID));
            propriedades.add(new Propriedade(Role.DESCRICAO));
            propriedades.add(new Propriedade(Role.NOME));
            propriedades.add(new Propriedade(Role.ATIVO));

            List<NxOrder> orders = Arrays.asList(new NxOrder(Role.NOME, NxOrder.NX_ORDER.ASC));

            //Obtem elementos.
            GenericDao<Role> genericDao = new GenericDao<Role>();
            lista =  genericDao.listarByFilter(propriedades, orders,Role.class, -1,null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<EapMultiEmpresa> listaAllEapMultiEmpresa() throws Exception {
        List<EapMultiEmpresa> lista = new ArrayList<>();
        try {

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(EapMultiEmpresa.ID));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO));

            List<NxOrder> orders = Arrays.asList(new NxOrder(EapMultiEmpresa.DESCRICAO, NxOrder.NX_ORDER.ASC));

            //Obtem elementos.
            GenericDao<EapMultiEmpresa> genericDao = new GenericDao<EapMultiEmpresa>();
            lista =  genericDao.listarByFilter(propriedades, orders,EapMultiEmpresa.class, -1,null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public List<Centro> listaAllCentros() throws Exception {
        List<Centro> lista = new ArrayList<>();
        try {

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Centro.CENTRO_ID));
            propriedades.add(new Propriedade(Centro.NOME));
            propriedades.add(new Propriedade(Centro.DESCRICAO));

            List<NxOrder> orders = Arrays.asList(new NxOrder(Centro.NOME, NxOrder.NX_ORDER.ASC));

            //Obtem elementos.
            GenericDao<Centro> genericDao = new GenericDao<Centro>();
            lista =  genericDao.listarByFilter(propriedades, orders,Centro.class, -1,null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * <b>listarRolesOfUsuario</b><br />
     * Recupera todas as roles de um usuario
     * @param usuarioId
     * @return
     * @throws Exception
     */
    private List<Role> listarRolesOfUsuario(Integer usuarioId) throws Exception {
        try {
            if (usuarioId != null && usuarioId > 0) {
            	PerfilRoleDao prDAO = new PerfilRoleDao();
            	UsuarioService usuarioService = new UsuarioService();
            	Usuario usuario = usuarioService.selectByUnique(usuarioId);
            	return prDAO.getRolesPorPerfilId(usuario.getPerfil().getPerfilId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<UsuarioEapMultiEmpresa> listarEapMultiEmpresasOfUsuario(Integer usuarioId) throws Exception {
        List<UsuarioEapMultiEmpresa> lista = new ArrayList<>();

        try {
            if (usuarioId != null && usuarioId > 0) {

                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(UsuarioEapMultiEmpresa.ID));

                String aliasEapMultiEmpresa = NxCriterion.montaAlias(UsuarioEapMultiEmpresa.ALIAS_CLASSE,UsuarioEapMultiEmpresa.EAP_MULTI_EMPRESA);
                propriedades.add(new Propriedade(EapMultiEmpresa.ID,EapMultiEmpresa.class,aliasEapMultiEmpresa));

                String aliasUsuario = NxCriterion.montaAlias(UsuarioEapMultiEmpresa.ALIAS_CLASSE,UsuarioEapMultiEmpresa.USUARIO);
                propriedades.add(new Propriedade(Usuario.USUARIO_ID,Usuario.class,aliasUsuario));

                NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Usuario.USUARIO_ID, usuarioId, Filtro.EQUAL,aliasUsuario));

                GenericDao<UsuarioEapMultiEmpresa> genericDao = new GenericDao<UsuarioEapMultiEmpresa>();
                lista = genericDao.listarByFilter(propriedades,null,UsuarioEapMultiEmpresa.class,-1,nxCriterion);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    /**
     * Lista as roles do usuario
     *
     * @param  pessoaId
     * @return List<UsuarioRole>
     */
    @POST
    @Path("listaTipoAtuacaoByPessoaId")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoAtuacao> listaTipoAtuacaoByPessoaId(Integer pessoaId) throws Exception {
        List<TipoAtuacao> lista = new ArrayList<>();
        try {
            //CONSULTA OS TIPOS DE ATUAÇÃO
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(TipoAtuacao.TIPO_ATUACAO_ID));
            propriedades.add(new Propriedade(TipoAtuacao.DESCRICAO));
            propriedades.add(new Propriedade(TipoAtuacao.CODIGO));

            List<NxOrder> orders = Arrays.asList(new NxOrder(TipoAtuacao.DESCRICAO, NxOrder.NX_ORDER.ASC));

            GenericDao<TipoAtuacao> genericDao = new GenericDao<TipoAtuacao>();
            lista =  genericDao.listarByFilter(propriedades, orders,TipoAtuacao.class, -1,null);

            if(lista != null && lista.size() > 0){
                //CONSULTA OS TIPOS DE ATUAÇÃO DA PESSOA
                propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
                propriedades.add(new Propriedade(Pessoa.TIPO_ATUACAO));

                NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(Pessoa.PESSOA_ID,pessoaId,Filtro.EQUAL));

                GenericDao<Pessoa> genericDaoPessoa = new GenericDao<Pessoa>();
                Pessoa p =  genericDaoPessoa.selectUnique(propriedades, Pessoa.class,nxCriterion);
                if(p != null &&  p.getTipoAtuacao() != null && p.getTipoAtuacao().length() > 0) {
                    String[] arrayTp = p.getTipoAtuacao().split(";");
                    List<String> listaTp = Arrays.asList(arrayTp);
                    for (TipoAtuacao ta : lista) {
                        if(listaTp.contains(ta.getCodigo())){
                            ta.setSelected(true);
                        }else{
                            ta.setSelected(false);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Método que persiste uma lista de roles de um dado usuário
     * <p/>
     * <b>Autor:</b> jerr.
     * <b>Data:</b> 13/05/2016
     * <p/>
     *
     * @param lista
     */
    @POST
    @Path("salvarTipoAtuacao")
    @Consumes("application/json")
    public Info salvarTipoAtuacao(List<TipoAtuacao> lista) {
        Info info = null;
        try {
            if (lista != null && lista.size() > 0) {
                GenericDao<Pessoa> genericDao = new GenericDao<Pessoa>();
                String atuacao = "";
                for (TipoAtuacao ta : lista) {
                    if (ta.getSelected() != null && ta.getSelected()) {
                        atuacao += (";"+ta.getCodigo());
                    }
                }

                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(Pessoa.TIPO_ATUACAO));

                PessoaService service = new PessoaService();
                Pessoa pessoa = service.selectPessoaById(lista.get(0).getPessoaId());
                pessoa.setTipoAtuacao(atuacao);
                genericDao.update(pessoa,propriedades);
            }

            info = new Info();
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem("msg_registro_salvo_sucesso");

        } catch (Exception e) {
            e.printStackTrace();
            info = Info.GetError(Constantes.REG_SALVO_ERRO_I18N);
        }
        return info;
    }

    /**
     * Método que sincroniza com o RHWEB
     * <p/>
     * <b>Autor:</b>
     * <b>Data:</b>
     * <p/>
     */
    @POST
    @Path("sincronizar")
    @Consumes("application/json")
    public Info Sincronizar() {
        Info info = null;
        try {
            SincPessoaService sincPessoaService = new SincPessoaService();
            sincPessoaService.sincronizar();

            info = new Info();
            info.setTipo(Info.TIPO_MSG_SUCCESS);
            info.setMensagem("msg_sincronizacao_realizada_sucesso");

        } catch (Exception e) {
            e.printStackTrace();
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setMensagem("msg_sincronizacao_nao_realizada");
        }
        return info;
    }
    
    @POST
    @Path("listaUsuarioTipoRequisicaoToUsuario")
    @Consumes("application/json")
    public List<TipoRequisicao> getTipoRequisicaoToUsuario(Integer id){
    	return tipoRequisicaoDAO.getAll();
    }
    
    @POST
    @Path("listaUsuarioCentroCustoToUsuario")
    @Consumes("application/json")
    public List<Centro> getCentroCustoToUsuario(Integer id){
    	return centroDAO.getAllCentroCusto();
    }


}
