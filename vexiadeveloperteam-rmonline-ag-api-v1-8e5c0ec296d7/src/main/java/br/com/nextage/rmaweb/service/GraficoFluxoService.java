/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.rmaweb.classes.GraficoFluxo;
import br.com.nextage.rmaweb.dao.RmDao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Leandro Cabanillas Orsi
 */
public class GraficoFluxoService {

    private RmDao rmDao;

    public GraficoFluxoService() {
        rmDao = new RmDao();
    }

    /**
     * Metodo que monta uma lista para ser utilizado no grafico
     *
     * @param dataInicioRec
     * @param dataFimRec
     * @return List<GraficoFluxo>
     * @throws Exception
     */
    public List listaFluxos(Date dataInicioRec, Date dataFimRec) throws Exception {

        List<GraficoFluxo> listaGRF = new ArrayList<>();
        GraficoFluxo gRF;
        int cont = 0;
        int aux = 0;
        try {

             // Intervalo Envio ao Sistema.
            /*gRF = new GraficoFluxo();
             aux = rmDao.selectMediaDiasEnvioSistema();
             gRF.setNome("Envio ao Sistema  ("+aux+")");
             gRF.setQtdDias(aux);
             gRF.setAuxBase(0);
             cont=aux;
             listaGRF.add(gRF);

             // Intervalo Envio ao Comprador.
             gRF = new GraficoFluxo();
             aux = rmDao.selectMediaDiasEnvioComprador();
             gRF.setNome("Envio ao Comprador  ("+aux+")");
             gRF.setQtdDias(cont + aux);
             gRF.setAuxBase(cont);
             cont=gRF.getQtdDias();
             listaGRF.add(gRF);

             // Intervalo Aceite do Comprador.
             gRF = new GraficoFluxo();
             aux = rmDao.selectMediaDiasAceiteComprador();
             gRF.setNome("Aceite do Comprador  ("+aux+")");
             gRF.setQtdDias(cont + aux);
             gRF.setAuxBase(cont);
             cont=gRF.getQtdDias();
             listaGRF.add(gRF);

             // Intervalo Efetivação da Compra.
             gRF = new GraficoFluxo();
             aux = rmDao.selectMediaDiasEfetivacaoCompra();
             gRF.setNome("Efetivação da Compra  ("+aux+")");
             gRF.setQtdDias(cont + aux);
             gRF.setAuxBase(cont);
             cont=gRF.getQtdDias();
             listaGRF.add(gRF);*/
            // Recebimento da RM e Compra dos Itens.
            aux = rmDao.selectMediaDiasRecebimentoRmCompra(dataInicioRec, dataFimRec);
            gRF = new GraficoFluxo("Efetivação da Compra  (" + aux + ")");
            gRF.setQtdDias(aux);
            gRF.setAuxBase(0);
            cont = aux;
            listaGRF.add(gRF);

            // Recebimento do Material.
            gRF = new GraficoFluxo("Recebimento do Material");
            aux = rmDao.selectMediaDiasRecebimentoMaterial(dataInicioRec, dataFimRec);
            gRF.setNome("Recebimento do Material  (" + aux + ")");
            gRF.setQtdDias(cont + aux);
            gRF.setAuxBase(cont);
            cont = gRF.getQtdDias();
            listaGRF.add(gRF);

            // Intervalo Efetivação da Compra.
            gRF = new GraficoFluxo();
            gRF.setNome("Total do Fluxo  (" + cont + ")");
            gRF.setQtdDias(cont);
            gRF.setAuxBase(0);
            cont = gRF.getQtdDias();
            listaGRF.add(gRF);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        if (listaGRF != null && listaGRF.size() > 0) {
            Collections.reverse(listaGRF);
        }
        return listaGRF;
    }
}
