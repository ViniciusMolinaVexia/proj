package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.service.integracao.LogInterfaceService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.LogInterfaceVo;
import br.com.nextage.rmaweb.ws.rhweb.FiltroImportacaoBloco;
import br.com.nextage.rmaweb.ws.rhweb.Funcao;
import br.com.nextage.rmaweb.ws.rhweb.RmawebWs_Service;
import br.com.nextage.util.Info;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Daniel H. Parisotto
 */
public class SincPessoaService {

    @Context
    HttpServletRequest request;

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    private final int QTDE_BLOCO_IMPORTACAO = 500;

    /**
     * Metodo que será chamado para iniciar a importação de pessoas
     */
    public void sincronizar() {
        sincPessoa();
    }

    /**
     * Metodo responsavel por encontrar as pessoas e atualizar/persistir as
     * informações
     *
     * @return
     */
    private Info sincPessoa() {
        Info info = new Info();

        List<Pessoa> listarPessoa = new ArrayList<>();
        List<Setor> listaSetor = new ArrayList<>();
        List<SubArea> listaSubArea = new ArrayList<>();
        List<br.com.nextage.rmaweb.ws.rhweb.Pessoa> listaPessoa = null;

        Configuracao conf = ConfiguracaoSingleton.getConfiguracao();
        String confCaminhoUrl = conf.getCaminhoUrlRhweb();
        GenericDao<Pessoa> genericDao = new GenericDao<>();
        GenericDao<Setor> genericDao2 = new GenericDao<>();
        GenericDao<SubArea> genericDao3 = new GenericDao<>();

        //Cria um FiltroImportacaoBloco para limitar a quantidade de resultados retornados pela consulta.
        FiltroImportacaoBloco filtroImportacaoBloco = new FiltroImportacaoBloco();
        filtroImportacaoBloco.setQtdeBloco(QTDE_BLOCO_IMPORTACAO);
        filtroImportacaoBloco.setUltimoId(null);

        try {
            List<Propriedade> propUpdateSetor = new ArrayList<>();
            propUpdateSetor.add(new Propriedade(Setor.DESCRICAO));

            List<Propriedade> propUpdateSubArea = new ArrayList<>();
            propUpdateSubArea.add(new Propriedade(SubArea.DESCRICAO));

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Pessoa.NOME));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA));
            propriedades.add(new Propriedade(Pessoa.CPF));
            //propriedades.add(new Propriedade(Pessoa.IS_REQUISITANTE));
            propriedades.add(new Propriedade(Pessoa.TELEFONE));
            propriedades.add(new Propriedade(Pessoa.FUNCAO));
            propriedades.add(new Propriedade(Pessoa.TIPO_ATUACAO));
            propriedades.add(new Propriedade(Pessoa.EMAIL));
            propriedades.add(new Propriedade(Pessoa.SETOR));
            propriedades.add(new Propriedade(Pessoa.SUB_AREA));
            propriedades.add(new Propriedade(Pessoa.DATA_ADMISSAO));
            propriedades.add(new Propriedade(Pessoa.DATA_DEMISSAO));
            propriedades.add(new Propriedade(Pessoa.EAP_MULTI_EMPRESA));


            listarPessoa = listarPessoa();
            listaSetor = listarSetor();
            listaSubArea = listarSubArea();

            List<EapMultiEmpresa> eaps = listarEapsSincRhweb();
            List<Integer> idsSetorAtualizado = new ArrayList<>();
            List<Integer> idsSubAreaAtualizado = new ArrayList<>();

            for (final EapMultiEmpresa eap : eaps) {
                try {
                    filtroImportacaoBloco = new FiltroImportacaoBloco();
                    filtroImportacaoBloco.setQtdeBloco(QTDE_BLOCO_IMPORTACAO);
                    filtroImportacaoBloco.setUltimoId(null);
                    listaPessoa = null;

                    //propriedades.add(new Propriedade(Pessoa.TIPO_ATUACAO));
                    RmawebWs_Service d = new RmawebWs_Service(new URL(eap.getCaminhoRhWeb()));

                    Pessoa pessoa = new Pessoa();

                    do {
                        if (listaPessoa != null && !listaPessoa.isEmpty()) {
                            br.com.nextage.rmaweb.ws.rhweb.Pessoa lastElement = listaPessoa.get((listaPessoa.size() - 1));
                            Integer ultimoId = lastElement.getPessoaId();
                            filtroImportacaoBloco.setUltimoId(ultimoId);
                        }

                        listaPessoa = d.getRmawebWsPort().listarPessoasRmaweb(filtroImportacaoBloco);

                        if (listaPessoa != null && !listaPessoa.isEmpty()) {

                            for (final br.com.nextage.rmaweb.ws.rhweb.Pessoa pessoaRhweb : listaPessoa) {

                                try {
                                    pessoa = (Pessoa) CollectionUtils.find(listarPessoa, new Predicate() {
                                        @Override
                                        public boolean evaluate(Object o) {
                                            Pessoa p = (Pessoa) o;
                                            return p != null && p.getCpf() != null &&
                                                    p.getCpf().equals(pessoaRhweb.getCpf())
                                                    && p.getEapMultiEmpresa() != null &&
                                                    p.getEapMultiEmpresa().equals(eap);
                                        }
                                    });
                                    if (pessoa == null) {
                                        pessoa = new Pessoa();
                                    }
                                    //set eap
                                    pessoa.setEapMultiEmpresa(eap);
                                    pessoa.setNome(pessoaRhweb.getNome());
                                    pessoa.setReferencia(pessoaRhweb.getReferencia());
                                    pessoa.setCpf(pessoaRhweb.getCpf());
                                    pessoa.setIsRequisitante((short) 1);
                                    pessoa.setTelefone(pessoaRhweb.getTelefone());
                                    if (pessoaRhweb.getEmailCcpr() != null) {
                                        pessoa.setEmail(pessoaRhweb.getEmailCcpr());
                                    }

                                    if (pessoaRhweb.getDataAdmissao() != null) {
                                        pessoa.setDataAdmissao(pessoaRhweb.getDataAdmissao().toGregorianCalendar().getTime());
                                    }
                                    if (pessoaRhweb.getDataDemissao() != null) {
                                        pessoa.setDataDemissao(pessoaRhweb.getDataDemissao().toGregorianCalendar().getTime());
                                    } else {
                                        pessoa.setDataDemissao(null);
                                    }

                                    if (pessoaRhweb.getArea() != null) {
                                        Setor setor = null;
                                        setor = (Setor) CollectionUtils.find(listaSetor, new Predicate() {
                                            @Override
                                            public boolean evaluate(Object o) {
                                                Setor s = (Setor) o;
                                                return s != null && s.getCodigo() != null && s.getCodigo().equals(pessoaRhweb.getArea().getCodigo());
                                            }
                                        });

                                        if (setor == null) {
                                            setor = new Setor();
                                            setor.setCodigo(pessoaRhweb.getArea().getCodigo());
                                            setor.setDescricao(pessoaRhweb.getArea().getDescricao());

                                            setor.setSetorId(genericDao2.persist(setor));

                                            listaSetor.add(setor);
                                        }else{
                                            if(!idsSetorAtualizado.contains(setor.getSetorId())) {
                                                idsSetorAtualizado.add(setor.getSetorId());
                                                //Faz update da descrição
                                                setor.setDescricao(pessoaRhweb.getArea().getDescricao());
                                                genericDao2.update(setor, propUpdateSetor);
                                            }
                                        }
                                        pessoa.setSetor(setor);
                                    }else{
                                        pessoa.setSetor(null);
                                    }

                                    if (pessoaRhweb.getSubArea() != null) {
                                        SubArea subArea = null;
                                        subArea = (SubArea) CollectionUtils.find(listaSubArea, new Predicate() {
                                            @Override
                                            public boolean evaluate(Object o) {
                                                SubArea s = (SubArea) o;
                                                return s != null && s.getCodigo() != null && s.getCodigo().equals(pessoaRhweb.getSubArea().getCodigo());
                                            }
                                        });

                                        if (subArea == null) {
                                            subArea = new SubArea();
                                            subArea.setCodigo(pessoaRhweb.getSubArea().getCodigo());
                                            subArea.setDescricao(pessoaRhweb.getSubArea().getDescricao());

                                            subArea.setSubAreaId(genericDao3.persist(subArea));

                                            listaSubArea.add(subArea);
                                        }else{
                                            if(!idsSubAreaAtualizado.contains(subArea.getSubAreaId())) {
                                                idsSubAreaAtualizado.add(subArea.getSubAreaId());
                                                //Faz update da descrição
                                                subArea.setDescricao(pessoaRhweb.getSubArea().getDescricao());
                                                genericDao3.update(subArea, propUpdateSubArea);
                                            }
                                        }

                                        pessoa.setSubArea(subArea);
                                    }else{
                                        pessoa.setSubArea(null);
                                    }

                                    if (pessoaRhweb.getFuncao() != null) {
                                        String tipoAtuacao = validaTipoAtuacaoByFuncao(pessoaRhweb.getFuncao(), conf, pessoa);
                                        if (tipoAtuacao != null) {
                                            pessoa.setTipoAtuacao(tipoAtuacao);
                                        }
                                        pessoa.setFuncao(pessoaRhweb.getFuncao().getDescricao());
                                    } else {
                                        pessoa.setFuncao(null);
                                    }

                                    if (pessoa.getPessoaId() == null || pessoa.getPessoaId() <= 0) {
                                        genericDao.persist(pessoa);
                                    } else {
                                        genericDao.update(pessoa, propriedades);
                                    }
                                } catch (Exception e) {
                                    logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));

                                    LogInterfaceVo logInterfaceVo = new LogInterfaceVo();

                                    logInterfaceVo.setTipoMensagem(Constantes.TIPO_MENSAGEM_ERRO);
                                    logInterfaceVo.setMensagem(e.getMessage());
                                    logInterfaceVo.setErroMensagem(e.getMessage());

                                    LogInterfaceService.inserirLog(Constantes.SISTEMA_RMAWEB, Constantes.SINCRONIZACAO_EFETIVO, null, null, null, null, null, LoginService.getUsuarioLogado(request).getNome(), pessoa, logInterfaceVo);
                                }
                            }
                        }
                    } while (listaPessoa != null && !listaPessoa.isEmpty());
                } catch (Exception eFor) {
                    logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
                }
            }

        } catch (Exception e) {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, null));
        }
        return info;
    }

    /**
     * Lista somente as EAP's que possuem caminho de sincronização com o RHWEB.
     *
     * @author Marlos M. Novo
     **/
    private List<EapMultiEmpresa> listarEapsSincRhweb() {
        List<EapMultiEmpresa> listaEaps = null;
        try {
            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, listaEaps));
            List<NxOrder> nxOrders = Arrays.asList(new NxOrder(EapMultiEmpresa.CODIGO, NxOrder.NX_ORDER.ASC));

            String aliasMultiEmpresa = NxCriterion.montaAlias(EapMultiEmpresa.ALIAS_CLASSE, EapMultiEmpresa.MULTI_EMPRESA);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(EapMultiEmpresa.ID));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO));
            propriedades.add(new Propriedade(EapMultiEmpresa.CAMINHO_RH_WEB));

            propriedades.add(new Propriedade(MultiEmpresa.ID, MultiEmpresa.class, aliasMultiEmpresa));
            propriedades.add(new Propriedade(MultiEmpresa.DESCRICAO, MultiEmpresa.class, aliasMultiEmpresa));

            // Restriction para pegar as eaps filhas somente da multiEmpresa passada da eap pai
            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(EapMultiEmpresa.CAMINHO_RH_WEB, null, Filtro.NOT_NULL));

            GenericDao<EapMultiEmpresa> dao = new GenericDao<>();
            listaEaps = dao.listarByFilter(propriedades, nxOrders, EapMultiEmpresa.class, Constantes.NO_LIMIT, nxCriterion);

            logger.debug(ResourceLogUtil.createMessageDebug(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), null, Util.getNomeMetodoAtual(), null, listaEaps));
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(request).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return listaEaps;
    }

    /**
     * Lista as pessoas do rma
     *
     * @return
     */
    private List<Pessoa> listarPessoa() {
        List<Pessoa> lista = null;
        try {
            String aliasSetor = NxCriterion.montaAlias(Pessoa.ALIAS_CLASSE, Pessoa.SETOR);
            String aliasSubArea = NxCriterion.montaAlias(Pessoa.ALIAS_CLASSE, Pessoa.SUB_AREA);
            String aliasEapMultiEmpresa = NxCriterion.montaAlias(Pessoa.ALIAS_CLASSE, Pessoa.EAP_MULTI_EMPRESA);

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Pessoa.PESSOA_ID));
            propriedades.add(new Propriedade(Pessoa.NOME));
            propriedades.add(new Propriedade(Pessoa.REFERENCIA));
            propriedades.add(new Propriedade(Pessoa.CPF));
            propriedades.add(new Propriedade(Pessoa.IS_REQUISITANTE));
            propriedades.add(new Propriedade(Pessoa.TELEFONE));
            propriedades.add(new Propriedade(Pessoa.FUNCAO));
            propriedades.add(new Propriedade(Pessoa.EMAIL));
            propriedades.add(new Propriedade(Pessoa.DATA_ADMISSAO));
            propriedades.add(new Propriedade(Pessoa.DATA_DEMISSAO));
            propriedades.add(new Propriedade(Pessoa.TIPO_ATUACAO));

            propriedades.add(new Propriedade(EapMultiEmpresa.ID, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.CODIGO, EapMultiEmpresa.class, aliasEapMultiEmpresa));
            propriedades.add(new Propriedade(EapMultiEmpresa.DESCRICAO, EapMultiEmpresa.class, aliasEapMultiEmpresa));

            propriedades.add(new Propriedade(Setor.SETOR_ID, Setor.class, aliasSetor));
            propriedades.add(new Propriedade(Setor.CODIGO, Setor.class, aliasSetor));
            propriedades.add(new Propriedade(Setor.DESCRICAO, Setor.class, aliasSetor));

            propriedades.add(new Propriedade(SubArea.SUB_AREA_ID, SubArea.class, aliasSubArea));
            propriedades.add(new Propriedade(SubArea.CODIGO, SubArea.class, aliasSubArea));
            propriedades.add(new Propriedade(SubArea.DESCRICAO, SubArea.class, aliasSubArea));

            GenericDao<Pessoa> genericDao = new GenericDao<>();
            lista = genericDao.listarByFilter(propriedades, null, Pessoa.class, Constantes.NO_LIMIT, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * lista os setores do rma
     *
     * @return
     */
    private List<Setor> listarSetor() {
        List<Setor> lista = null;
        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Setor.SETOR_ID));
            propriedades.add(new Propriedade(Setor.CODIGO));
            propriedades.add(new Propriedade(Setor.DESCRICAO));

            GenericDao<Setor> genericDao = new GenericDao<>();
            lista = genericDao.listarByFilter(propriedades, null, Setor.class, Constantes.NO_LIMIT, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * lista as sub areas do rma
     *
     * @return
     */
    private List<SubArea> listarSubArea() {
        List<SubArea> lista = null;
        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(SubArea.SUB_AREA_ID));
            propriedades.add(new Propriedade(SubArea.CODIGO));
            propriedades.add(new Propriedade(SubArea.DESCRICAO));

            GenericDao<SubArea> genericDao = new GenericDao<>();
            lista = genericDao.listarByFilter(propriedades, null, SubArea.class, Constantes.NO_LIMIT, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * @author Marlos M. Novo
     * <p/>
     * <p/>
     * Valida o tipo de atuacao da pessoa de acordo com os ids configurados
     * paras as funções.
     */
    private String validaTipoAtuacaoByFuncao(Funcao funcao, Configuracao conf, Pessoa pessoa) {

        String tipoAtuacao = null;
        Boolean contemEncarregado = false;

        //Verifico se a pessoa tem o tipo de atuação E já, se tiver o Encarregado, não faço a verificação.
        if(pessoa.getPessoaId() != null && pessoa.getTipoAtuacao() != null) {
            contemEncarregado = pessoa.getTipoAtuacao().contains("E");
        }

        if (contemEncarregado == false) {
            if (conf.getIdFuncaoEncarregado() != null && !conf.getIdFuncaoEncarregado().isEmpty() && funcao != null && funcao.getFuncaoId() != null) {
                String[] ids = conf.getIdFuncaoEncarregado().split(";");
                for (String string : ids) {
                    String aux = "";
                    if(pessoa.getTipoAtuacao() != null){
                        aux = pessoa.getTipoAtuacao();
                    }

                    if (funcao.getFuncaoId().equals(Integer.parseInt(string))) {
                        tipoAtuacao =  aux + ";" + Constantes.TIPO_ATUACAO_ENCARREGADO;
                        break;
                    }
                }
            }
        }

        return tipoAtuacao;
    }
}
