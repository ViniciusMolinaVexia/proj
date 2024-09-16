/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.service;

import br.com.nextage.rmaweb.classes.GraficoPrazoItens;
import br.com.nextage.rmaweb.dao.RmDao;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jerry
 */
public class GraficoPrazoItensService {

    RmDao rmDao = new RmDao();

    public List listaPrazoItens(Date dataInicial, Date dataFinal) {

        List<GraficoPrazoItens> lista = new ArrayList<>();
        GraficoPrazoItens item;
        int qntd = 0;
        double totalItens = 0;

        //PRAZO DE 5 DIAS
        item = new GraficoPrazoItens();
        qntd = rmDao.selectCountItensPrazo(0, 5, dataInicial, dataFinal);
        item.setNomePrazo("05 dias");
        item.setQuantidade(qntd);
        lista.add(item);
        totalItens = qntd;

        //PRAZO DE 10 DIAS
        item = new GraficoPrazoItens();
        qntd = rmDao.selectCountItensPrazo(5, 10, dataInicial, dataFinal);
        item.setNomePrazo("10 dias");
        item.setQuantidade(qntd);
        lista.add(item);
        totalItens = qntd + totalItens;

        //PRAZO DE 15 DIAS
        item = new GraficoPrazoItens();
        qntd = rmDao.selectCountItensPrazo(10, 15, dataInicial, dataFinal);
        item.setNomePrazo("15 dias");
        item.setQuantidade(qntd);
        lista.add(item);
        totalItens = qntd + totalItens;

        //PRAZO DE 20 DIAS
        item = new GraficoPrazoItens();
        qntd = rmDao.selectCountItensPrazo(15, 20, dataInicial, dataFinal);
        item.setNomePrazo("20 dias");
        item.setQuantidade(qntd);
        lista.add(item);
        totalItens = qntd + totalItens;

        //PRAZO DE 25 DIAS
        item = new GraficoPrazoItens();
        qntd = rmDao.selectCountItensPrazo(20, 25, dataInicial, dataFinal);
        item.setNomePrazo("25 dias");
        item.setQuantidade(qntd);
        lista.add(item);
        totalItens = qntd + totalItens;

        //PRAZO DE 30 DIAS
        item = new GraficoPrazoItens();
        qntd = rmDao.selectCountItensPrazo(25, 30, dataInicial, dataFinal);
        item.setNomePrazo("30 dias");
        item.setQuantidade(qntd);
        lista.add(item);
        totalItens = qntd + totalItens;

        //PRAZO DE 31 DIAS
        item = new GraficoPrazoItens();
        qntd = rmDao.selectCountItensPrazo(30, 31, dataInicial, dataFinal);
        item.setNomePrazo("31 dias");
        item.setQuantidade(qntd);
        lista.add(item);
        totalItens = qntd + totalItens;

        double porcentagem;
        DecimalFormat dc;
        //ADICIONA A PORCENTAGEM NO NOME
        for (GraficoPrazoItens gPI : lista) {
            porcentagem = (gPI.getQuantidade() * 100 / totalItens);
            dc = new DecimalFormat("0.00");
            gPI.setNomePrazo(gPI.getNomePrazo() + "  (" + dc.format(porcentagem) + "%)");

        }

        return lista;
    }
}
