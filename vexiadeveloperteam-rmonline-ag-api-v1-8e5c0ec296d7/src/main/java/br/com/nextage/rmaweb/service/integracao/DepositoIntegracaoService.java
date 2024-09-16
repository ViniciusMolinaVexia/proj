package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Deposito;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.util.Constantes;
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
public class DepositoIntegracaoService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    private List<Deposito> listaDeposito;

    public DepositoIntegracaoService() {
        listaDeposito = new ArrayList<>();
    }

    /**
     * Lista todas as unidades do BD.
     */
    private void listarDepositos() {
        try {

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Deposito.DEPOSITO_ID));
            propriedades.add(new Propriedade(Deposito.NOME));
            propriedades.add(new Propriedade(Deposito.CODIGO));

            //Obtem elementos.
            GenericDao<Deposito> genericDao = new GenericDao<>();
            listaDeposito = genericDao.listarByFilter(propriedades, null, Deposito.class, Constantes.NO_LIMIT, null);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
    }

    /**
     * Pesquisa unidade com base no nome e na sigla passado por parametro.
     *
     * @param deposito
     * @return
     */
    public Deposito searchDeposito(String deposito, String depositoEap) {
        Deposito dep = null;
        try {
            if (listaDeposito == null || listaDeposito.isEmpty()) {
                listarDepositos();
            }

            boolean depositoEncontrado = false;
            for (Deposito d : listaDeposito) {
                if(depositoEap != null && d.getEapMultiEmpresa() != null){
                    if(d.getCodigo().equals(deposito) && depositoEap.equals(d.getEapMultiEmpresa().getCodigo())){
                        return d;
                    }
                }else if (d.getCodigo().equals(deposito)) {
                    return d;
                }
            }
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return dep;
    }

    /**
     * Pesquisa unidade com base no nome e na sigla passado por parametro.
     *
     * @param codigo
     * @return
     */
    public Deposito searchDepositoByCodigo(String codigo, String depositoEap) {
        Deposito deposito = null;
        try {
            if (listaDeposito == null || listaDeposito.isEmpty()) {
                listarDepositos();
            }

            for (Deposito d : listaDeposito) {
                if(depositoEap != null && d.getEapMultiEmpresa() != null){
                    if(d.getCodigo().equals(codigo) && depositoEap.equals(d.getEapMultiEmpresa().getCodigo())){
                        return d;
                    }
                }else if (d.getCodigo().equals(codigo)) {
                    return d;
                }
            }

            //deposito = novoDeposito(nome);
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return deposito;
    }

    private Deposito novoDeposito(String nome) {
        Deposito deposito = null;
        Integer idObjeto;
        try {
            deposito = new Deposito(-1, nome);
            GenericDao<Deposito> genericDao = new GenericDao<>();
            idObjeto = genericDao.persist(deposito);
            deposito.setDepositoId(idObjeto);
        } catch (Exception e) {

        }
        return deposito;
    }
}
