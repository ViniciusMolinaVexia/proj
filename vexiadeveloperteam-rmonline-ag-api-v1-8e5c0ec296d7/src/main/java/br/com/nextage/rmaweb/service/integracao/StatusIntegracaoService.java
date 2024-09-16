package br.com.nextage.rmaweb.service.integracao;

import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.Status;
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
public class StatusIntegracaoService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    List<Status> listaStatus;

    public StatusIntegracaoService() {
        listaStatus = null;
    }

    public List<Status> listarStatus() {
        List<Status> lista = null;
        try {

            List<Propriedade> propriedades = new ArrayList<>();
            propriedades.add(new Propriedade(Status.ID));
            propriedades.add(new Propriedade(Status.CODIGO));

            GenericDao<Status> genericDao = new GenericDao<>();
            lista = genericDao.listarByFilter(propriedades, null, Status.class, Constantes.NO_LIMIT, null);

        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return lista;
    }

    public Status searchStatus(String codigo) {
        try {
            if (codigo != null) {

                if (listaStatus == null) {
                    listaStatus = listarStatus();
                }

                if (listaStatus != null && !listaStatus.isEmpty()) {
                    for (Status obj : listaStatus) {
                        if (obj.getCodigo() != null && obj.getCodigo().equals(codigo)) {
                            return obj;
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return null;
    }
}
