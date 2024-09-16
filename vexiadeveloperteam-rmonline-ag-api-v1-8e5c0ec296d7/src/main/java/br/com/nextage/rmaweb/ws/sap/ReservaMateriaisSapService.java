package br.com.nextage.rmaweb.ws.sap;

import br.com.nextage.persistence_2.classes.Propriedade;
import br.com.nextage.persistence_2.dao.GenericDao;
import br.com.nextage.rmaweb.entitybean.*;
import br.com.nextage.rmaweb.service.ConfIntegracaoServiceImpl;
import br.com.nextage.rmaweb.service.LoginService;
import br.com.nextage.rmaweb.service.integracao.RmaService;
import br.com.nextage.rmaweb.service.integracao.SincRegistroService;
import br.com.nextage.rmaweb.singleton.ConfiguracaoSingleton;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.util.Info;
import br.com.nextage.util.NxResourceBundle;
import br.com.nextage.util.ResourceLogUtil;
import br.com.nextage.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Daniel H. Parisotto
 */
public class ReservaMateriaisSapService {

    Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);

    /**
     * Utilizado pelo listener que tenta enviar novamente as reservas que deram
     * erro em uma outra tentativa
     */
    public void sincronizar() {
        SincRegistroService sincRegistroService = new SincRegistroService();
        List<SincRegistro> listaRegistros = sincRegistroService.listar(Constantes.CODIGO_INTEGRACAO_RESERVA_MATERIAL);
        Pessoa aprovador;

        RmaService rmaService = new RmaService(null);

        for (SincRegistro sincRegistro : listaRegistros) {
            Rm rm = rmaService.listarRm(new Rm(sincRegistro.getIdRegistro()));
            List<RmMaterial> listaRmMaterial = rmaService.listarRmMateriais(rm);
            aprovador = rmaService.listarUltimoAprovador(rm).getAprovador();

            enviarReserva(listaRmMaterial, rm, aprovador);
        }

        sincRegistroService.desativar(listaRegistros);
    }

    /**
     * Monta o objeto reserva e envia para o sap
     *
     * @param listaMaterial
     * @param rm
     * @param aprovador
     * @return
     */
    public Info enviarReserva(List<RmMaterial> listaMaterial, Rm rm, Pessoa aprovador) {
		ConfIntegracaoServiceImpl confIntegracaoService = new ConfIntegracaoServiceImpl();
        ConfIntegracao confIntegracao = confIntegracaoService.listarConfIntegracao(Constantes.CODIGO_INTEGRACAO_RESERVA_MATERIAL);
        Configuracao configuracao = ConfiguracaoSingleton.getConfiguracao();
        DTSolicitaReservaResponse dTSolicitaReservaResponse;
        NxResourceBundle rb = new NxResourceBundle("mensagens_" + LoginService.getLocale(null));

        Info info = new Info();

        List<DTSolicitaReserva.Itens> itens = new ArrayList<>();

        GenericDao<RmMaterial> genericDao = new GenericDao<>();
        try {
            for (RmMaterial rmMaterial : listaMaterial) {
                if (rmMaterial.getMaterial().getEstoqueSap() != null
                        && rmMaterial.getMaterial().getEstoqueSap().equals(Constantes.SIM_ABREVIADO)
                        && rmMaterial.getFluxoMaterial() != null
                        && rmMaterial.getFluxoMaterial().equals(Constantes.FLUXO_MATERIAL_RESERVA)) {
                    DTSolicitaReserva.Itens item = new DTSolicitaReserva.Itens();
                    if (configuracao.getCentroCod() != null) {
                        item.setCentro(configuracao.getCentroCod().split(";")[0]);
                    }
                    item.setDeposito(rmMaterial.getDeposito().getCodigo());
                    item.setMaterial(rmMaterial.getMaterial().getCodigo());
                    item.setQuantidade(String.valueOf(rmMaterial.getQuantidade().intValue()));
                    if (rmMaterial.getMaterial().getUnidadeMedida() != null) {
                        item.setUnMedida(rmMaterial.getMaterial().getUnidadeMedida().getSigla());
                    }

                    //Somente funciona, se todos os campos estiverem zerados, não retirar.
                    XMLGregorianCalendar data = Util.dateToXMLGregorianCalendar(rm.getDataAplicacao());
                    data.setHour(0);
                    data.setMinute(0);
                    data.setSecond(0);
                    data.setMillisecond(0);
                    data.setFractionalSecond(BigDecimal.ZERO);
                    data.setTimezone(0);

                    item.setDataNecessidade(data);

                    itens.add(item);
                }
            }

            if (!itens.isEmpty()) {
                DTSolicitaReserva dTSolicitaReserva = new DTSolicitaReserva();

                confIntegracaoService.autenticar(confIntegracao.getLogin(), confIntegracao.getSenha(), confIntegracao.getUrl());

                //Busca o serviço SAP instanciando com a URL do WSDL configurada no BD, caso não houver, usa a padrão que foi gerada.
                BSRMAWEBSISolicitaReservaSyncOutXX bSRMAWEBSISolicitaReservaSyncOutXX = null;
                if(confIntegracao.getUrlWsdlService() != null){
                    bSRMAWEBSISolicitaReservaSyncOutXX = new BSRMAWEBSISolicitaReservaSyncOutXX(new URL(confIntegracao.getUrlWsdlService()));
                }else{
                    bSRMAWEBSISolicitaReservaSyncOutXX = new BSRMAWEBSISolicitaReservaSyncOutXX();
                }

                SISolicitaReservaSyncOut siSolicitaReservaSyncOut = bSRMAWEBSISolicitaReservaSyncOutXX.getHTTPPort();

                BindingProvider bp = (BindingProvider) siSolicitaReservaSyncOut;
                bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, confIntegracao.getUrl());

                dTSolicitaReserva.setAprovadorRma(aprovador.getReferencia());
                if (configuracao.getCentroCod() != null) {
                    dTSolicitaReserva.setCentroReceptor(configuracao.getCentroCod().split(";")[0]);
                }

                XMLGregorianCalendar data = Util.dateToXMLGregorianCalendar(new Date());
                data.setHour(0);
                data.setMinute(0);
                data.setSecond(0);
                data.setMillisecond(0);
                data.setFractionalSecond(BigDecimal.ZERO);
                data.setTimezone(0);

                dTSolicitaReserva.setDataReserva(data);
                dTSolicitaReserva.setSolicitanteRma(rm.getRequisitante().getReferencia());

                dTSolicitaReserva.getItens().addAll(itens);

                dTSolicitaReservaResponse = siSolicitaReservaSyncOut.siSolicitaReservaSyncOut(dTSolicitaReserva);

                //É necessário a limpeza dos proxys que foram setados para chamadas dos serviços SAP
                //Sem limpar, causava erroa na chamada dos outros serviços que estão usando o mesmo tomcat pois seta
                //váriaveis do sistema.
                System.setProperty("proxySet", "false");
                System.clearProperty("proxySet");
                System.clearProperty("http.proxyHost");
                System.clearProperty("http.proxyPort");
                System.clearProperty("http.proxyUser");
                System.clearProperty("http.proxyPassword");

                if (dTSolicitaReservaResponse.getTipoMensagem().equals(Constantes.TIPO_MENSAGEM_SUCESSO)) {
                    List<Propriedade> propriedades = new ArrayList<>();
                    propriedades.add(new Propriedade(RmMaterial.NUMERO_RESERVA));

                    genericDao.beginTransaction();

                    for (RmMaterial rmMaterial : listaMaterial) {
                        rmMaterial.setNumeroReserva(dTSolicitaReservaResponse.getReserva());
                        genericDao.updateWithCurrentTransaction(rmMaterial, propriedades);
                    }

                    genericDao.commitCurrentTransaction();

                } else {
                    SincRegistroService sincRegistroService = new SincRegistroService();
                    info.setIdObjetoSalvo(rm.getRmId());
                    info.setErro(dTSolicitaReservaResponse.getMensagem());
                    info.setMensagem(dTSolicitaReservaResponse.getMensagem());
                    info.setTipo(Info.TIPO_MSG_DANGER);
                    sincRegistroService.salvar(info, Constantes.CODIGO_INTEGRACAO_RESERVA_MATERIAL, Constantes.SISTEMA_SAP);
                }
            }

        } catch (Exception e) {
            genericDao.rollbackCurrentTransaction();

            SincRegistroService sincRegistroService = new SincRegistroService();
            info.setIdObjetoSalvo(rm.getRmId());
            info.setTipo(Info.TIPO_MSG_DANGER);
            info.setErro(e.getCause() != null && e.getCause().getMessage() != null ? e.getCause().getMessage() : e.getMessage());
            info.setMensagem(rb.getString(Constantes.ERRO_COMUNICACAO_SAP_CONTATE_ADMINISTRADOR_I18N));
            sincRegistroService.salvar(info, Constantes.CODIGO_INTEGRACAO_RESERVA_MATERIAL, Constantes.SISTEMA_SAP);

            logger.error(ResourceLogUtil.createMessageError(LoginService.getUsuarioLogado(null).getNome(), this.getClass().getName(), Util.getNomeMetodoAtual(), e));
        }
        return info;
    }
}
