package br.com.nextage.rmaweb.service.integracao;

import java.util.ArrayList;
import java.util.List;
import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.dao.VwConsultaRmaDAO;
import br.com.nextage.rmaweb.entitybean.Configuracao;
import br.com.nextage.rmaweb.entitybean.Material;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.vo.integracao.MaterialVo;
import br.com.nextage.rmaweb.vo.integracao.MensagemIntegracao;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.log4j.Logger;

/**
 * @author l.pordeus
 */
public class MaterialIntegracaoService {

    private static final Logger LOG = Logger.getLogger(VwConsultaRmaDAO.class);

    private List<Material> listaMaterial;

    public MaterialIntegracaoService() {
        listaMaterial = new ArrayList<>();
    }

    /**
     * @param listaMaterial
     * @return
     * @author l.pordeus
     * <p/>
     * Salva lista de materiales passada através do parametro listaMaterial.
     */
    public List<MensagemIntegracao> salvar(List<MaterialVo> listaMaterial) {
        Info info = new Info();
        List<MensagemIntegracao> lista = new ArrayList<>();
        Material material = null;
        GenericDao<Material> genericDao = new GenericDao<>();
        UnidadeMedidaIntegracaoService unidadeMedidaIntegracaoService = new UnidadeMedidaIntegracaoService();
        TipoMaterialIntegracaoService tipoMaterialIntegracaoService = new TipoMaterialIntegracaoService();
        List<Propriedade> propriedades = new ArrayList<>();
        List<Filtro> propriedadeWhere = new ArrayList<>();
        Integer qtdeUpdt = 0;
        NxResourceBundle rb = new NxResourceBundle(null);

        Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();

        List<String> codigosCentro = null;
//        if (configuracao.getCentroCod() != null) {
//            codigosCentro = Arrays.asList(configuracao.getCentroCod().split(";"));
//        }

        try {
            if (listaMaterial != null && !listaMaterial.isEmpty()) {
                //Monta propriedades a serem atualziadas
                propriedades.add(new Propriedade(Material.NOME));
                propriedades.add(new Propriedade(Material.NOME_COMPLETO));
                propriedades.add(new Propriedade(Material.UNIDADE_MEDIDA));
                propriedades.add(new Propriedade(Material.TIPO_MATERIAL));
                propriedades.add(new Propriedade(Material.PATRIMONIADO));
                propriedades.add(new Propriedade(Material.HIERARQUIA));
                propriedades.add(new Propriedade(Material.GRUPO_MERCADORIA));
                propriedades.add(new Propriedade(Material.STATUS));
                propriedades.add(new Propriedade(Material.ULTIMA_ATUALIZACAO));
                propriedades.add(new Propriedade(Material.NCM));
                propriedades.add(new Propriedade(Material.ESTOQUE_SAP));
                propriedades.add(new Propriedade(Material.CENTRO));

                for (MaterialVo obj : listaMaterial) {
//                    if (codigosCentro != null && !codigosCentro.contains(obj.getCentro())) {
//                        continue;
//                    }
                    try {
                        //Monta Material
                        material = new Material(-1);
                        material.setNome(obj.getDescricaoBreve());
                        if (obj.getDescricaoLonga() != null && !obj.getDescricaoLonga().trim().isEmpty()) {
                            material.setNomeCompleto(obj.getDescricaoLonga());
                        } else {
                            material.setNomeCompleto(obj.getDescricaoBreve());
                        }
                        material.setCodigo(obj.getMaterial());
                        if (obj.getCautela() != null && obj.getCautela().equalsIgnoreCase("X")) {
                            material.setTipoMaterial(tipoMaterialIntegracaoService.searchTipoMaterial(Constantes.CAUTELAVEL));
                        } else {
                            material.setTipoMaterial(tipoMaterialIntegracaoService.searchTipoMaterial(Constantes.NAO_CAUTELAVEL));
                        }
                        material.setUnidadeMedida(unidadeMedidaIntegracaoService.searchUnidadeMedida(obj.getUnidadeMedida()));
                        material.setPatrimoniado(obj.getPatrimoniado() != null && obj.getPatrimoniado().equalsIgnoreCase("X") ? Constantes.SIM_ABREVIADO : Constantes.NAO_ABREVIADO);
                        material.setEstoqueSap(obj.getEstoqueSap() != null && obj.getEstoqueSap().equalsIgnoreCase("X") ? Constantes.SIM_ABREVIADO : Constantes.NAO_ABREVIADO);
                        material.setNcm(obj.getNcm());
                        material.setHierarquia(obj.getHierarquia());
                        material.setGrupoMercadoria(obj.getGrupoMercadoria());
                        material.setStatus(obj.getStatus());
                        material.setCentro(obj.getCentro());
                        if (obj.getUltimaDataAtualizacao() != null
                                && obj.getUltimaHoraAtualizacao() != null) {
                            material.setUltimaAtualizacao(Util.parseData(obj.getUltimaDataAtualizacao() + " " + obj.getUltimaHoraAtualizacao(), "dd/MM/yyyy hh:mm:ss"));
                        }

                        //Monta propriedades Where
                        propriedadeWhere = new ArrayList<>();
                        propriedadeWhere.add(new Filtro(Material.CODIGO, obj.getMaterial(), Filtro.EQUAL));
                        propriedadeWhere.add(new Filtro(Material.CENTRO, obj.getCentro(), Filtro.EQUAL));

                        qtdeUpdt = genericDao.update(material, propriedades, propriedadeWhere);

                        if (qtdeUpdt.equals(0)) {
                            genericDao.persist(material);
                        }
                    } catch (Exception e) {
                        LOG.error("MaterialIntegracaoService 1: ", e);
                        info.setCodigo(Info.INFO_002);
                        info.setTipo(Info.TIPO_MSG_DANGER);
                        info.setErro(e.getMessage());

                        MensagemIntegracao mensagemIntegracao = new MensagemIntegracao();
                        mensagemIntegracao.setTipoMensagem("E");
                        mensagemIntegracao.setMensagem(rb.getString("label_erro_ao_importar_registro"));
                        mensagemIntegracao.setCodigo(obj.getMaterial());

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
            LOG.error("MaterialIntegracaoService 2: ", e);  MensagemIntegracao mensagemIntegracao = new MensagemIntegracao();
            
            mensagemIntegracao.setTipoMensagem("E");
            mensagemIntegracao.setMensagem(rb.getString("label_erro_ao_importar_registro"));

            lista.add(mensagemIntegracao);
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        } finally {
            genericDao.getCurrentSession().close();
        }
        return lista;
    }

    /**
     * Lista todas as unidades do BD.
     * @param codigo
     * @return
     */
    private Material findByCodigo(String codigo) {
        try {

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Material.MATERIAL_ID));
            propriedades.add(new Propriedade(Material.NOME));
            propriedades.add(new Propriedade(Material.CODIGO));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP));
            propriedades.add(new Propriedade(Material.PATRIMONIADO));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(
                    new Filtro(Material.CODIGO, codigo,
                            Filtro.EQUAL));
            //Obtem elementos.
            GenericDao<Material> genericDao = new GenericDao<>();
            return genericDao.selectUnique(propriedades, Material.class, nxCriterion);

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return null;
    }

    private Material findByCodigo(String codigo, String centro) {
        try {
            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Material.MATERIAL_ID));
            propriedades.add(new Propriedade(Material.NOME));
            propriedades.add(new Propriedade(Material.CODIGO));
            propriedades.add(new Propriedade(Material.ESTOQUE_SAP));
            propriedades.add(new Propriedade(Material.PATRIMONIADO));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(
              new Filtro(Material.CODIGO, codigo, Filtro.EQUAL));

            nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(Material.CENTRO, centro, Filtro.EQUAL)));

            //Obtem elementos.
            GenericDao<Material> genericDao = new GenericDao<>();
            return genericDao.selectUnique(propriedades, Material.class, nxCriterion);

        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return null;
    }

    /**
     * Pesquisa unidade com base no nome e na sigla passado por parametro.
     *
     * @param codigo
     * @return
     */
    public Material searchMaterial(String codigo) {
        try {
            if (codigo != null) {
                return findByCodigo(codigo);
            }
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return null;
    }

    public Material searchMaterial(String codigo, String centro) {
        try {
            if (codigo != null) {
                return findByCodigo(codigo, centro);
            }
        } catch (Exception e) {
            LOG.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return null;
    }

    private Material novoMaterial(String codigo) {
        Material material = null;
        Integer idObjeto;
        try {
            material = new Material(-1, codigo);
            GenericDao<Material> genericDao = new GenericDao<>();
            idObjeto = genericDao.persist(material);
            material.setMaterialId(idObjeto);
        } catch (Exception e) {

        }
        return material;
    }
}
