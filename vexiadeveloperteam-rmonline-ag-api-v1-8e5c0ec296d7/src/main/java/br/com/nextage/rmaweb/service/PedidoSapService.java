package br.com.nextage.rmaweb.service;

import br.com.nextage.persistence.classes.Filtro;
import br.com.nextage.persistence.classes.Propriedade;
import br.com.nextage.persistence.dao.GenericDao;
import br.com.nextage.rmaweb.dao.DaoContext;
import br.com.nextage.rmaweb.dao.PedidoSapDao;
import br.com.nextage.rmaweb.entitybean.Fornecedor;
import br.com.nextage.rmaweb.entitybean.PedidoSap;
import br.com.nextage.rmaweb.importing.CodigoFornecedorAdapter;
import br.com.nextage.util.adapter.ExcelDateAdapter;
import br.com.nextage.util.adapter.MappedProperty;
import br.com.nextage.util.adapter.SimpleExcelStringAdapter;
import br.com.nextage.util.adapter.WorkbookDataAdapter;
import br.com.nextage.util.adapter.WorkbookDataAdapterConfiguration;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nextage
 */
public class PedidoSapService {

    private boolean done = false;
    private BlockingQueue<PedidoSap> pQueue;
    private GenericDao<PedidoSap> genericDao;
    private PedidoSapDao pedidoDao;
    private DaoContext daoBatchUpdateContext;

    public PedidoSapService() {
        genericDao = new GenericDao<>();
        pedidoDao = new PedidoSapDao(daoBatchUpdateContext);
    }

    public Collection importaPlanilhaPedidos(byte[] dados) throws Exception {
        SortedMap<Integer, String> logLinesError = new TreeMap<>();
        try {
            System.out.println("RMA - IMPORTANDO SAP.");
            pQueue = new LinkedBlockingQueue<>();
            InputStream stream = new ByteArrayInputStream(dados);

            WorkbookDataAdapter<PedidoSap> worbookDataAdapter = new WorkbookDataAdapter<>(pQueue, stream, logLinesError);

            WorkbookDataAdapterConfiguration wConfig = new WorkbookDataAdapterConfiguration();

            wConfig.setTargetType(PedidoSap.class);
            wConfig.setColumns(WorkbookDataAdapterConfiguration.ALL_COLUMNS);
            wConfig.setRows(WorkbookDataAdapterConfiguration.ALL_ROWS);
            wConfig.setSetEmptyFieldsNull(Boolean.TRUE);
            wConfig.setSkipFirstLine(Boolean.TRUE); //default is Boolean.FALSE

            SortedMap<Integer, MappedProperty> fieldMap = new TreeMap<>();

            Class c = PedidoSap.class;

            MappedProperty numeroPedidoMap = new MappedProperty(
                    c.getDeclaredField(PedidoSap.NUMERO_PEDIDO_COMPRA),
                    new SimpleExcelStringAdapter());

            MappedProperty fornecedorMap = new MappedProperty(
                    c.getDeclaredField(PedidoSap.FORNECEDOR),
                    new CodigoFornecedorAdapter());
            fornecedorMap.setWhenNullDiscardRecord(Boolean.TRUE);

            MappedProperty dataMap = new MappedProperty(
                    c.getDeclaredField(PedidoSap.DATA_COMPRA),
                    new ExcelDateAdapter());

            fieldMap.put(0, numeroPedidoMap);
            fieldMap.put(1, fornecedorMap);
            fieldMap.put(2, dataMap);

            wConfig.setTargetFieldMap(fieldMap);

            worbookDataAdapter.setConfiguration(wConfig);

            Runnable dbUpdateTask = new Runnable() {

                @Override
                public void run() {

                    daoBatchUpdateContext = new DaoContext();
                    pedidoDao.setDaoContext(daoBatchUpdateContext);
                    pedidoDao.openBatchUpdateSession();
                    while (!done) {
                        try {
                            PedidoSap ps = pQueue.take();
                            PedidoSap pedidoExistente
                                    = pedidoDao.selectPedidoPorNumero(
                                            daoBatchUpdateContext, ps.getNumeroPedidoCompra());
                            if (pedidoExistente != null) {
                                pedidoExistente.setDataCompra(ps.getDataCompra());
                                pedidoExistente.setFornecedor(ps.getFornecedor());
                                ps = pedidoExistente;
                            }
                            if (ps.getFornecedor() == null) {
                                done = true;
                            } else {
                                System.out.println("Merging PedidoSAP:" + ps.getNumeroPedidoCompra());
                                pedidoDao.addEntityToBatchUpdateList(ps);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            Logger.getLogger(PedidoSapService.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.out.print("Done inserting data from workbook, comitting transaction...");
                    pedidoDao.closeBatchUpdateSession();
                    System.out.println("ok.");
                }
            };

            Thread wbLoadThread = new Thread(worbookDataAdapter);
            Thread dbUpdateThread = new Thread(dbUpdateTask);

            wbLoadThread.start();

            wbLoadThread.join();

            dbUpdateThread.start();
            dbUpdateThread.join();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return logLinesError.values();

    }

    public String salvar(PedidoSap obj) throws Exception {
        System.out.println("RMA - SAVE SAP.");
        String idObjeto = "0";
        try {
            if (obj.getPedidoSapId() > 0) {
                idObjeto = pedidoDao.update(obj);
            } else {
                idObjeto = pedidoDao.persist(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return idObjeto;
    }

    public List<PedidoSap> listaDataGrid(String nome, int qtdRetorno) throws Exception {
        List<PedidoSap> lista = new ArrayList<>();
        try {
            List<Filtro> filtros = new ArrayList<>();

            // Seta as propriedades de retorno da consulta.
            List<Propriedade> propriedades = new ArrayList<>();

            //Obtem elementos.
            lista = genericDao.listarByFilter(propriedades, filtros,
                    PedidoSap.FORNECEDOR, false, PedidoSap.class, qtdRetorno);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return lista;

    }

    public PedidoSap selectByUnique(Integer pedidoId) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        PedidoSap obj = new PedidoSap();
        try {
            filtros.add(new Filtro(PedidoSap.PEDIDO_SAP_ID, pedidoId, Filtro.EQUAL));

            obj = (PedidoSap) genericDao.selectUnique(filtros, PedidoSap.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return obj;
    }

    public List<PedidoSap> listaDataGrid() {
        return null;
    }

    public List<Fornecedor> getComboFornecedores() {
        return null;
    }

    public PedidoSap pesquisarPedido(String nPedido) throws Exception {
        List<Filtro> filtros = new ArrayList<>();
        PedidoSap obj = new PedidoSap();
        try {
            filtros.add(new Filtro(PedidoSap.NUMERO_PEDIDO_COMPRA, nPedido.trim(), Filtro.EQUAL));

            obj = (PedidoSap) genericDao.selectUnique(filtros, PedidoSap.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return obj;
    }
}
