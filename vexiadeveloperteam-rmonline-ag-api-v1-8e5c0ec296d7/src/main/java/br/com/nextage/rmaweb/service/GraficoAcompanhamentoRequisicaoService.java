/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.rmaweb.classes.ItemGraficoAcompanhamentoRequisicao;
import br.com.nextage.rmaweb.dao.RmDao;
import br.com.nextage.rmaweb.entitybean.RmMaterial;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristiano
 */
public class GraficoAcompanhamentoRequisicaoService {

    List<RmMaterial> listaParcialmenteRecebidos = new ArrayList<>();
    List<RmMaterial> listaTotalmentRecebidos = new ArrayList<>();

    public List<ItemGraficoAcompanhamentoRequisicao> listaItensGrafico() throws Exception {
        List<ItemGraficoAcompanhamentoRequisicao> lista = new ArrayList<>();
        try {
            RmDao rmDao = new RmDao();
            int cont;

            cont = rmDao.selectCountRmEmCompra(-1);
            ItemGraficoAcompanhamentoRequisicao itemGrafico = new ItemGraficoAcompanhamentoRequisicao();
            itemGrafico.setNome("Em Compra  (" + cont + ")");
            itemGrafico.setQuantidade(cont);
            lista.add(itemGrafico);

            cont = rmDao.selectCountRmTotalmenteRecebidos(-1);
            itemGrafico = new ItemGraficoAcompanhamentoRequisicao();
            itemGrafico.setNome("Totalmente Recebidos  (" + cont + ")");
            itemGrafico.setQuantidade(cont);//menos um quer dizer todos
            lista.add(itemGrafico);

            cont = rmDao.selectCountRmParcialmenteRecebidos(-1);
            itemGrafico = new ItemGraficoAcompanhamentoRequisicao();
            itemGrafico.setNome("Parcialmente Recebidos  (" + cont + ")");
            itemGrafico.setQuantidade(cont);
            lista.add(itemGrafico);

            cont = rmDao.selectCountRmPendentesRecebimento(-1);
            itemGrafico = new ItemGraficoAcompanhamentoRequisicao();
            itemGrafico.setNome("Pendentes de Recebimento  (" + cont + ")");
            itemGrafico.setQuantidade(cont);
            lista.add(itemGrafico);

            cont = rmDao.selectCountRmCanceladas(-1);
            itemGrafico = new ItemGraficoAcompanhamentoRequisicao();
            itemGrafico.setNome("Cancelados  (" + cont + ")");
            itemGrafico.setQuantidade(cont);
            lista.add(itemGrafico);

            cont = rmDao.selectCountRmParcialmenteRetirados(-1);
            itemGrafico = new ItemGraficoAcompanhamentoRequisicao();
            itemGrafico.setNome("Parcialmente Retirados  (" + cont + ")");
            itemGrafico.setQuantidade(cont);
            lista.add(itemGrafico);

            cont = rmDao.selectCountRmTotalmenteRetirados(-1);
            itemGrafico = new ItemGraficoAcompanhamentoRequisicao();
            itemGrafico.setNome("Totalmente Retirados  (" + cont + ")");
            itemGrafico.setQuantidade(cont);
            lista.add(itemGrafico);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;
    }

}
