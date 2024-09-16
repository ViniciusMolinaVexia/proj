package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Configuracao;
import br.com.nextage.rmaweb.entitybean.Fornecedor;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.integracao.FornecedorVo;
import br.com.nextage.rmaweb.vo.integracao.MensagemIntegracao;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author l.pordeus
 */
public class FornecedorIntegracaoService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    List<Fornecedor> listaFornecedor;

    public FornecedorIntegracaoService() {
        listaFornecedor = null;
    }

    /**
     * @author l.pordeus
     *
     * Salva lista de fornecedores passada através do parametro listaFornecedor.
     *
     * @param listaFornecedor
     * @return
     */
    public List<MensagemIntegracao> salvar(List<FornecedorVo> listaFornecedor) {
        Info info = new Info();
        List<MensagemIntegracao> lista = new ArrayList<>();
        Fornecedor fornecedor = null;
        GenericDao<Fornecedor> genericDao = new GenericDao<>();
        CidadeIntegracaoService cidadeIntegracaoService = new CidadeIntegracaoService();
        List<Propriedade> propriedades = new ArrayList<>();
        List<Filtro> propriedadeWhere = new ArrayList<>();
        Integer qtdeUpdt = 0;
        NxResourceBundle rb = new NxResourceBundle(null);
        Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();

//        List<String> codigosCentro = null;
//        if (configuracao.getCentroCod() != null) {
//            codigosCentro = Arrays.asList(configuracao.getCentroCod().split(";"));
//        }

        try {
            if (listaFornecedor != null && !listaFornecedor.isEmpty()) {
                //Monta propriedades a serem atualziadas
                propriedades.add(new Propriedade(Fornecedor.NOME));
                propriedades.add(new Propriedade(Fornecedor.CIDADE));
//                propriedades.add(new Propriedade(Fornecedor.CEP));
//                propriedades.add(new Propriedade(Fornecedor.CONTATO));
//                propriedades.add(new Propriedade(Fornecedor.ENDERECO));
//                propriedades.add(new Propriedade(Fornecedor.ORGC));
//                propriedades.add(new Propriedade(Fornecedor.TELEFONE));
                propriedades.add(new Propriedade(Fornecedor.CNPJ));
                propriedades.add(new Propriedade(Fornecedor.STATUS));
                propriedades.add(new Propriedade(Fornecedor.ULTIMA_ATUALIZACAO));
                propriedades.add(new Propriedade(Fornecedor.CENTRO));

                for (FornecedorVo obj : listaFornecedor) {
//                    if (codigosCentro != null && !codigosCentro.contains(obj.getCentro())) {
//                        continue;
//                    }
                    try {
                        //Monta Fornecedor
                        fornecedor = new Fornecedor(-1);
                        fornecedor.setNome(obj.getNome());
                        fornecedor.setCodigo(obj.getCodigo());
                        fornecedor.setCidade(cidadeIntegracaoService.searchCidade(obj.getCidade(), obj.getEstado()));
                        fornecedor.setCnpj(obj.getCnpj());
                        fornecedor.setStatus(obj.getStatus());
                        fornecedor.setCentro(obj.getCentro());
                        if (obj.getUltimaDataAtualizacao() != null
                                && obj.getUltimaHoraAtualizacao() != null) {
                            fornecedor.setUltimaAtualizacao(Util.parseData(obj.getUltimaDataAtualizacao() + " " + obj.getUltimaHoraAtualizacao(), "DD/MM/YYYY HH:MM:SS"));
                        }

                        //Monta propriedades Where
                        propriedadeWhere = new ArrayList<>();
                        propriedadeWhere.add(new Filtro(Fornecedor.CODIGO, obj.getCodigo(), Filtro.EQUAL));

                        qtdeUpdt = genericDao.update(fornecedor, propriedades, propriedadeWhere);

                        if (qtdeUpdt.equals(0)) {
                            genericDao.persist(fornecedor);
                        }
                    } catch (Exception e) {
                        info.setCodigo(Info.INFO_002);
                        info.setTipo(Info.TIPO_MSG_DANGER);
                        info.setErro(e.getMessage());

                        MensagemIntegracao mensagemIntegracao = new MensagemIntegracao();
                        mensagemIntegracao.setTipoMensagem("E");
                        mensagemIntegracao.setMensagem(rb.getString("label_erro_ao_importar_registro"));
                        mensagemIntegracao.setCodigo(obj.getCodigo());

                        lista.add(mensagemIntegracao);
                    }
                }
            }
            if (info.getCodigo() == null) {
                MensagemIntegracao mensagemIntegracao = new MensagemIntegracao();
                mensagemIntegracao.setTipoMensagem("S");
                mensagemIntegracao.setMensagem(rb.getString(Constantes.REG_SALVO_SUCESSO_I18N));
                lista.add(mensagemIntegracao);
            }
        } catch (Exception e) {
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getMessage());
            info.setMensagem(rb.getString(Constantes.REG_SALVO_ERRO_I18N));
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    public List<Fornecedor> listarFornecedores() {
        List<Fornecedor> lista = null;
        try {

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Fornecedor.FORNECEDOR_ID));
            propriedades.add(new Propriedade(Fornecedor.CODIGO));

            GenericDao<Fornecedor> genericDao = new GenericDao<>();
            lista = genericDao.listarByFilter(propriedades, null, Fornecedor.class, Constantes.NO_LIMIT, null);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    public Fornecedor searchFornecedor(String codigo) {
        Fornecedor fornecedor = null;
        try {
            if (codigo != null) {

                if (listaFornecedor == null) {
                    listaFornecedor = listarFornecedores();
                }

                if (listaFornecedor != null && !listaFornecedor.isEmpty()) {
                    for (Fornecedor obj : listaFornecedor) {
                        if (obj.getCodigo() != null && obj.getCodigo().equals(codigo)) {
                            return obj;
                        }
                    }
                }

                fornecedor = new Fornecedor(-1);
                fornecedor.setNome(codigo);
                fornecedor.setCodigo(codigo);

                GenericDao<Fornecedor> genericDao = new GenericDao<>();
                Integer idObjeto = genericDao.persist(fornecedor);
                fornecedor.setFornecedorId(idObjeto);
            }
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return fornecedor;
    }

}
