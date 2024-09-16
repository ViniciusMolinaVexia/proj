/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.rmaweb.classes.GraficoResumoCompradores;
import br.com.nextage.rmaweb.dao.RmDao;
import br.com.nextage.rmaweb.entitybean.Comprador;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jerry
 */
public class GraficoResumoCompradoresService {

    private GenericDao<RmMaterial> genericDao;

    public GraficoResumoCompradoresService() {
        genericDao = new GenericDao<>();
    }

    /**
     * Metodo que monta uma lista para ser utilizado no grafico
     *
     * @return List<GraficoResumoCompradores>
     * @throws Exception
     */
    public List listaCompadoresGrafico() throws Exception {
        RmDao rmDao = new RmDao();
        CompradorService compradorService = new CompradorService();
        List<Comprador> listaCompradores = new ArrayList<>();
        List<GraficoResumoCompradores> listaGRC = new ArrayList<>();
        GraficoResumoCompradores gRC;
        String nome;
        try {
            listaCompradores = compradorService.listaCompradores();
            for (Comprador c : listaCompradores) {
                gRC = new GraficoResumoCompradores();
                nome = c.getNome();

                //PEGA O PRIMEIRO NOME
                int aux = nome.indexOf(' ');
                if (aux > 0) {
                    nome = nome.substring(0, aux);
                }

                //NOME COMPRADOR
                gRC.setNome(nome);

                //EM COMPRA
                gRC.setQntdCotacao(rmDao.selectCountRmEmCompra(c.getCompradorId()));

                //CANCELADAS
                gRC.setQntdDataCancelamento(rmDao.selectCountRmCanceladas(c.getCompradorId()));

                //PENDENTE DE RECEBIMENTO
                gRC.setQntdDataCompra(rmDao.selectCountRmPendentesRecebimento(c.getCompradorId()));

                //RECEBIDO TOTALMENTE
                gRC.setQntdRecCompleto(rmDao.selectCountRmTotalmenteRecebidos(c.getCompradorId()));

                //RECEBIDO PARCIALMENTE
                gRC.setQntdRecParcial(rmDao.selectCountRmParcialmenteRecebidos(c.getCompradorId()));

                //RETIRADO PARCIALMENTE
                gRC.setQntdRetParcial(rmDao.selectCountRmParcialmenteRetirados(c.getCompradorId()));

                //RETIRADO TOTALMENTE
                gRC.setQntdRetCompleto(rmDao.selectCountRmTotalmenteRetirados(c.getCompradorId()));

                listaGRC.add(gRC);

            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return listaGRC;
    }

}
