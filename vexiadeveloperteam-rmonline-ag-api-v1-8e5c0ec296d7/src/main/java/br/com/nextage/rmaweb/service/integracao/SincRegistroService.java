package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Filtro;
import br.com.nextage.persistence_2.classes.NxCriterion;
import br.com.nextage.persistence_2.classes.NxOrder;
import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.SincRegistro;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.Info;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author l.pordeus
 */
public class SincRegistroService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    /**
     * Grava registro de log de acordo com o Info e funcionalidade
     *
     * @param info
     * @param funcionalidade
     * @param sistema
     */
    public void salvar(Info info, String funcionalidade, String sistema) {
        salvar(info, funcionalidade, sistema, null, null, null, null, null);
    }

    /**
     * Grava registro de log de acordo com o Info e funcionalidade
     *
     * @param info
     * @param funcionalidade
     * @param sistema
     * @param agrupadorErro
     * @param rmaConcatenadas
     */
    public void salvar(Info info, String funcionalidade, String sistema, Integer agrupadorErro, String rmaConcatenadas) {
        salvar(info, funcionalidade, sistema, agrupadorErro, rmaConcatenadas, null, null, null);
    }

    /**
     * Grava registro de log de acordo com o Info e funcionalidade
     *
     * @param info
     * @param funcionalidade
     * @param sistema
     * @param agrupadorErro
     * @param rmaConcatenadas
     */
    public void salvar(Info info, String funcionalidade, String sistema, Integer agrupadorErro, String rmaConcatenadas, String rmaMaterialConcatenadas) {
        salvar(info, funcionalidade, sistema, agrupadorErro, rmaConcatenadas, rmaMaterialConcatenadas, null, null);
    }


    /**
     * Grava registro de log de acordo com o Info e funcionalidade e também pode gravar Json (Passar tb o nome da Classe ao salvar o Json).
     *
     * @param info
     * @param funcionalidade
     * @param sistema
     * @param agrupadorErro
     * @param rmaConcatenadas
     * @param rmaMaterialConcatenadas
     * @param json
     * @param nomeClasse
     */
    public void salvar(Info info, String funcionalidade, String sistema, Integer agrupadorErro, String rmaConcatenadas, String rmaMaterialConcatenadas, String json, String nomeClasse) {
        try {
            GenericDao<SincRegistro> genericDao = new GenericDao<>();

            SincRegistro sincRegistro = new SincRegistro();
            sincRegistro.setId(-1);
            sincRegistro.setFuncionalidade(funcionalidade);
            sincRegistro.setDataHora(new Date());
            if (info.getIdObjetoSalvo() != null) {
                sincRegistro.setIdRegistro(info.getIdObjetoSalvo());
            }
            sincRegistro.setObservacao(info.getMensagem());
            sincRegistro.setAtivo(Constantes.SIM_ABREVIADO);
            sincRegistro.setSistema(sistema);
            sincRegistro.setAgrupadorErro(agrupadorErro);
            sincRegistro.setErroMensagem(info.getErro());
            if (json != null && nomeClasse != null) {
                sincRegistro.setJson(json);
                sincRegistro.setNomeClasse(nomeClasse);
            }

            if (rmaConcatenadas != null && !rmaConcatenadas.isEmpty()) {
                rmaConcatenadas = rmaConcatenadas.indexOf(';') == 0 ? rmaConcatenadas : ";" + rmaConcatenadas;
                sincRegistro.setRmaConcatena(rmaConcatenadas);
            }

            if (rmaMaterialConcatenadas != null && !rmaMaterialConcatenadas.isEmpty()) {
                rmaMaterialConcatenadas = rmaMaterialConcatenadas.indexOf(';') == 0 ? rmaMaterialConcatenadas : ";" + rmaMaterialConcatenadas;
                sincRegistro.setRmMaterialConcatena(rmaMaterialConcatenadas);
            }

            genericDao.persist(sincRegistro);
        } catch (Exception e) {
            info.setCodigo(Info.INFO_002);
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setMensagem(Constantes.ERRO_OPERACAO_I18N);
            info.setErro(e.getMessage());
            logger.error(ResourceLogUtil.createMessageError("", this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
    }

    /**
     * Desativa lista de registros de logs
     *
     * @param lista
     */
    public void desativar(List<SincRegistro> lista) {
        GenericDao<SincRegistro> genericDao = new GenericDao<>();

        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(SincRegistro.ATIVO));
            genericDao.beginTransaction();
            for (SincRegistro registro : lista) {
                registro.setAtivo(Constantes.NAO_ABREVIADO);
                genericDao.updateWithCurrentTransaction(registro, propriedades);
            }
            genericDao.commitCurrentTransaction();
        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();
            logger.error(ResourceLogUtil.createMessageError("", this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
    }

    /**
     * Desativa registro de logs
     *
     * @param sincRegistro
     */
    public void desativar(SincRegistro sincRegistro) {
        try {
            GenericDao<SincRegistro> genericDao = new GenericDao<>();

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(SincRegistro.ATIVO));

            sincRegistro.setAtivo(Constantes.NAO_ABREVIADO);
            genericDao.update(sincRegistro, propriedades);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError("", this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
    }

    /**
     * Lista logs ativos e de acordo com a funcionalidade
     *
     * @param funcionalidade
     * @return
     */
    public List<SincRegistro> listar(String funcionalidade) {
        List<SincRegistro> lista = null;
        try {
            if (funcionalidade != null) {
                List<Propriedade> propriedades = new ArrayList<>();
                propriedades.add(new Propriedade(SincRegistro.ID));
                propriedades.add(new Propriedade(SincRegistro.DATA_HORA));
                propriedades.add(new Propriedade(SincRegistro.FUNCIONALIDADE));
                propriedades.add(new Propriedade(SincRegistro.ID_REGISTRO));
                propriedades.add(new Propriedade(SincRegistro.OBSERVACAO));
                propriedades.add(new Propriedade(SincRegistro.ATIVO));
                propriedades.add(new Propriedade(SincRegistro.AGRUPADOR_ERRO));
                propriedades.add(new Propriedade(SincRegistro.RMA_CONCATENA));
                propriedades.add(new Propriedade(SincRegistro.RM_MATERIAL_CONCATENA));
                propriedades.add(new Propriedade(SincRegistro.ERRO_MENSAGEM));
                propriedades.add(new Propriedade(SincRegistro.JSON));
                propriedades.add(new Propriedade(SincRegistro.NOME_CLASSE));

                NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(SincRegistro.ATIVO, Constantes.SIM_ABREVIADO, Filtro.EQUAL));
                nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(SincRegistro.FUNCIONALIDADE, funcionalidade, Filtro.EQUAL)));

                GenericDao<SincRegistro> genericDao = new GenericDao<>();
                lista = genericDao.listarByFilter(propriedades, null, SincRegistro.class, Constantes.NO_LIMIT, nxCriterion);
            }
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError("", this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    /**
     * Lista o proximo codigo agrupamento
     *
     * @return
     */
    public Integer listarProximoCodigoAgrupamento() {
        List<SincRegistro> lista = null;
        Integer codigo = 1;
        try {
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(SincRegistro.ID));
            propriedades.add(new Propriedade(SincRegistro.AGRUPADOR_ERRO));

            NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(SincRegistro.AGRUPADOR_ERRO, null, Filtro.NOT_NULL));

            List<NxOrder> orders = Arrays.asList(new NxOrder(SincRegistro.AGRUPADOR_ERRO, NxOrder.NX_ORDER.DESC));

            GenericDao<SincRegistro> genericDao = new GenericDao<>();
            lista = genericDao.listarByFilter(propriedades, orders, SincRegistro.class, 1, nxCriterion);

            if (!lista.isEmpty()) {
                codigo = lista.get(0).getAgrupadorErro() + 1;
            }

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError("", this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return codigo;
    }

    /**
     * Lista os sincRegistros de acordo o id_registro e a funcionalidade
     *
     * @param idRegistro
     * @param funcionalidade
     * @return
     */
    public List<SincRegistro> listarSincRegistro(Integer idRegistro, String funcionalidade) {
        List<SincRegistro> listaSincRegistro = new ArrayList<>();

        GenericDao<SincRegistro> genericDao = new GenericDao<>();

        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(SincRegistro.ID));

        NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(SincRegistro.ID_REGISTRO, idRegistro, Filtro.EQUAL));
        nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(SincRegistro.ATIVO, Constantes.SIM_ABREVIADO, Filtro.EQUAL)));
        nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(SincRegistro.FUNCIONALIDADE, funcionalidade, Filtro.EQUAL)));

        try {
            listaSincRegistro = genericDao.listarByFilter(propriedades, null, SincRegistro.class, Constantes.NO_LIMIT, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return listaSincRegistro;
    }

    /**
     * Lista os sincRegistros de acordo o agrupador
     *
     * @param codigoAgrupador
     * @return
     */
    public List<SincRegistro> listarSincRegistro(Integer codigoAgrupador) {
        List<SincRegistro> listaSincRegistro = new ArrayList<>();

        GenericDao<SincRegistro> genericDao = new GenericDao<>();

        List<Propriedade> propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(SincRegistro.ID));

        NxCriterion nxCriterion = NxCriterion.montaRestriction(new Filtro(SincRegistro.AGRUPADOR_ERRO, codigoAgrupador, Filtro.EQUAL));
        nxCriterion = NxCriterion.and(nxCriterion, NxCriterion.montaRestriction(new Filtro(SincRegistro.ATIVO, Constantes.SIM_ABREVIADO, Filtro.EQUAL)));

        try {
            listaSincRegistro = genericDao.listarByFilter(propriedades, null, SincRegistro.class, Constantes.NO_LIMIT, nxCriterion);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return listaSincRegistro;
    }
}
