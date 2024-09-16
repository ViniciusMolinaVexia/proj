/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.dao;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.rmaweb.entitybean.Pessoa;
import br.com.nextage.rmaweb.entitybean.Rm;
import br.com.nextage.rmaweb.util.Constantes;
import br.com.nextage.rmaweb.utils.ConnectionFactory;
import br.com.nextage.util.Util;

/**
 * Classe que permite acesso à persistencia
 *
 * @author Felipe
 */
public class RmDao {

    /**
     * Método que realiza a inserção de um novo registro.
     *
     * <br>
     * <br>
     * <b>Autor:</b> Felipe.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param obj - Rm
     * @return objSalvo.toString - String
     * @throws Exception - Exception
     */
    public String persist(Rm obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        Object objSalvo = new Object();
        try {
            trx = session.beginTransaction();

            //Insere um novo registro
            objSalvo = session.save(obj);

            session.flush();

            session.refresh(obj);

            trx.commit();

        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: persist - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: persist - ERRO: " + e.getMessage());
            }
        }
        return objSalvo.toString();
    }

    /**
     * Método que realiza a atualização dos dados de um RM.
     * <br>
     * <br>
     * <b>Autor:</b> Felipe.
     * <b>Data:</b> 15/03/2011
     * <br>
     * <br>
     *
     * @param obj - Rm
     * @return objSalvo.toString - String
     * @throws Exception - Exception
     */
    public String update(Rm obj) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        Object objSalvo = new Object();
        try {
            trx = session.beginTransaction();

            session.update(obj);

            objSalvo = obj.getRmId();

            trx.commit();

        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: update - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: update - ERRO: " + e.getMessage());
            }
        }
        return objSalvo.toString();
    }

    /* MÉTODOS RELACIONADOS AOS STATUS DAS RM'S */
    /**
     * CANCELADOS
     *
     * Método que retorna a quantidade de itens cancelados. O compradorId se
     * diferente de NULL, é usado como filtro.
     *
     * @param compradorId -Integer
     * @return count - Integer
     */
    public Integer selectCountRmCanceladas(Integer compradorId) {
        Integer count = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            StringBuilder sB = new StringBuilder();
            sB.append("SELECT COUNT(rmm.rm_id) FROM TB_RM_MATERIAL rmm INNER JOIN TB_RM rm ON rmm.RM_ID = rm.RM_ID WHERE rm.data_cancelamento IS NOT NULL ");

            // Verifica se o comprador foi informado.
            if (compradorId != null && compradorId > 0) {
                sB.append("AND rm.comprador_id = ");
                sB.append(String.valueOf(compradorId));
            }

            Query q = session.createSQLQuery(sB.toString());
            count = (Integer) q.uniqueResult();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * PENDENTE DE RECEBIMENTO
     *
     * Método que retorna a quantidade de itens que estão pendente de
     * recebimento, ou seja já foram comprados mas não recebidos. Dá para passar
     * o filtro pelo id do comprador,senão vai retornar de todos os compradores.
     *
     * @param compradorId - Integer
     * @return
     */
    public Integer selectCountRmPendentesRecebimento(Integer compradorId) {
        Integer count = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            StringBuilder sB = new StringBuilder();
            sB.append("select count(rmm.rm_material_id) from TB_RM_MATERIAL rmm Inner join TB_RM rm ON rmm.RM_ID = rm.RM_ID where rm.DATA_CANCELAMENTO is null AND rm.DATA_ACEITE_COMPRADOR is not null AND rmm.DATA_COMPRA is not NULL AND rmm.rm_material_id not in (select rm_material_id from TB_RM_MATERIAL_RECEBIMENTO)");

            // Verifica se o comprador foi informado.
            if (compradorId != null && compradorId > 0) {
                sB.append("AND rm.comprador_id = ");
                sB.append(String.valueOf(compradorId));
            }

            Query q = session.createSQLQuery(sB.toString());
            count = (Integer) q.uniqueResult();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * PARCIALMENTE RECEBIDOS
     *
     * Método que retorna a quantidade de itens que estão parcialmente
     * recebidos, ou seja já foram comprados e recebidos só uma parte. Dá para
     * passar o filtro pelo id do comprador,senão vai retornar de todos os
     * compradores.
     *
     * @param compradorId - Integer
     * @return
     */
    public Integer selectCountRmParcialmenteRecebidos(Integer compradorId) {
        Integer count = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            StringBuilder sB = new StringBuilder();
            sB.append("select COUNT(rmm.rm_material_id) from TB_RM_MATERIAL rmm Inner join TB_RM rm ON rmm.RM_ID = rm.RM_ID where rm.DATA_CANCELAMENTO is null AND rm.DATA_ACEITE_COMPRADOR is not null AND rmm.rm_material_id IN (select rm_material_id from TB_RM_MATERIAL_RECEBIMENTO) AND RMM.QUANTIDADE > (SELECT SUM (QUANTIDADE) FROM TB_RM_MATERIAL_RECEBIMENTO WHERE RM_MATERIAL_ID = RMM.RM_MATERIAL_ID) AND rmm.rm_material_id NOT IN (SELECT rm_material_id FROM TB_RM_MATERIAL_RETIRADA)");

            // Verifica se o comprador foi informado.
            if (compradorId != null && compradorId > 0) {
                sB.append("AND rm.comprador_id = ");
                sB.append(String.valueOf(compradorId));
            }

            Query q = session.createSQLQuery(sB.toString());
            count = (Integer) q.uniqueResult();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * TOTALMENTE RECEBIDOS
     *
     * Método que retorna a quantidade de itens que estão totalmente recebidos,
     * ou seja, foi recebido toda a quantidade comprada do iten. Dá para passar
     * o filtro pelo id do comprador,senão vai retornar de todos os compradores.
     *
     * @param compradorId - Integer
     * @return
     */
    public Integer selectCountRmTotalmenteRecebidos(Integer compradorId) {
        Integer count = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            StringBuilder sB = new StringBuilder();
            sB.append("select COUNT(rmm.rm_material_id) from TB_RM_MATERIAL rmm Inner join TB_RM rm ON rmm.RM_ID = rm.RM_ID where rm.DATA_CANCELAMENTO is null AND rm.DATA_ACEITE_COMPRADOR is not null AND rmm.rm_material_id in ( select rm_material_id from TB_RM_MATERIAL_RECEBIMENTO) AND RMM.QUANTIDADE = (SELECT SUM (QUANTIDADE) FROM TB_RM_MATERIAL_RECEBIMENTO WHERE RM_MATERIAL_ID = RMM.RM_MATERIAL_ID) AND rmm.rm_material_id NOT IN (SELECT rm_material_id FROM TB_RM_MATERIAL_RETIRADA)");

            // Verifica se o comprador foi informado.
            if (compradorId != null && compradorId > 0) {
                sB.append("AND rm.comprador_id = ");
                sB.append(String.valueOf(compradorId));
            }

            Query q = session.createSQLQuery(sB.toString());
            count = (Integer) q.uniqueResult();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * PARCIAMENTE RETIRADOS
     *
     * Método que retorna a quantidade de itens que foram parcialmente
     * retirados, ou seja, foi comprado, recebido e foi retirado so uma parte do
     * estoque,o iten em questão. Dá para passar o filtro pelo id do
     * comprador,senão vai retornar de todos os compradores.
     *
     * @param compradorId - Integer
     * @return
     */
    public Integer selectCountRmParcialmenteRetirados(Integer compradorId) {
        Integer count = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            StringBuilder sB = new StringBuilder();
            sB.append("select COUNT(rmm.rm_material_id) from TB_RM_MATERIAL rmm Inner join TB_RM rm ON rmm.RM_ID = rm.RM_ID where rm.DATA_CANCELAMENTO is null AND rm.DATA_ACEITE_COMPRADOR is not null AND rmm.rm_material_id IN (select rm_material_id from TB_RM_MATERIAL_RETIRADA) AND RMM.QUANTIDADE > (SELECT SUM (QUANTIDADE) FROM TB_RM_MATERIAL_RETIRADA WHERE RM_MATERIAL_ID = RMM.RM_MATERIAL_ID)");

            // Verifica se o comprador foi informado.
            if (compradorId != null && compradorId > 0) {
                sB.append("AND rm.comprador_id = ");
                sB.append(String.valueOf(compradorId));
            }

            Query q = session.createSQLQuery(sB.toString());
            count = (Integer) q.uniqueResult();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * TOTALMENTE RETIRADOS
     *
     * Método que retorna a quantidade de itens que foram totalmente retirados,
     * ou seja, foi comprado, recebido e foi retirado totalmente do estoque a
     * quantidade do iten em questão. Dá para passar o filtro pelo id do
     * comprador,senão vai retornar de todos os compradores.
     *
     * @param compradorId - Integer
     * @return
     */
    public Integer selectCountRmTotalmenteRetirados(Integer compradorId) {
        Integer count = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            StringBuilder sB = new StringBuilder();
            sB.append("select COUNT(rmm.rm_material_id) from TB_RM_MATERIAL rmm Inner join TB_RM rm ON rmm.RM_ID = rm.RM_ID where rm.DATA_CANCELAMENTO is null AND rm.DATA_ACEITE_COMPRADOR is not null AND rmm.rm_material_id in ( select rm_material_id from TB_RM_MATERIAL_RETIRADA) AND RMM.QUANTIDADE = (SELECT SUM (QUANTIDADE) FROM TB_RM_MATERIAL_RETIRADA WHERE RM_MATERIAL_ID = RMM.RM_MATERIAL_ID)");

            // Verifica se o comprador foi informado.
            if (compradorId != null && compradorId > 0) {
                sB.append("AND rm.comprador_id = ");
                sB.append(String.valueOf(compradorId));
            }

            Query q = session.createSQLQuery(sB.toString());
            count = (Integer) q.uniqueResult();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * EM ANÁLISE
     *
     * Método que retorna a quantidade de itens em que a RM está em analise, ou
     * seja,foi indicado o comprador para a rm,e ele ainda não aceitou a rm
     * ,está somente analizando. Dá para passar o filtro pelo id do
     * comprador,senão vai retornar de todos os compradores.
     *
     * @param compradorId - Integer
     * @return
     */
    public Integer selectCountRmEmAnalise(Integer compradorId) {
        Integer count = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            StringBuilder sB = new StringBuilder();
            sB.append("select count(rmm.rm_id) from TB_RM_MATERIAL rmm Inner join TB_RM rm ON rmm.RM_ID = rm.RM_ID where rm.data_cancelamento is null and rm.DATA_ACEITE_COMPRADOR IS null");

            // Verifica se o comprador foi informado.
            if (compradorId != null && compradorId > 0) {
                sB.append("AND rm.comprador_id = ");
                sB.append(String.valueOf(compradorId));
            }

            Query q = session.createSQLQuery(sB.toString());
            count = (Integer) q.uniqueResult();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * EM COMPRA
     *
     * Método que retorna a quantidade de itens em que estão em processo de
     * compra, ou seja,o comprador aceitou a rm mas ainda não comprou o iten. Dá
     * para passar o filtro pelo id do comprador,senão vai retornar de todos os
     * compradores.
     *
     * @param compradorId - Integer
     * @return
     */
    public Integer selectCountRmEmCompra(Integer compradorId) {
        Integer count = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            StringBuilder sB = new StringBuilder();

            /* Itens Em Compra (NÃO PODEM TER SIDO RECEBIDOS) */
            sB.append("SELECT COUNT(RM_MATERIAL_ID) FROM TB_RM_MATERIAL RMM INNER JOIN TB_RM RM ON RMM.RM_ID = RM.RM_ID WHERE DATA_COMPRA IS NULL AND RM.DATA_ACEITE_COMPRADOR IS NOT NULL AND RM.DATA_CANCELAMENTO IS NULL AND rmm.rm_material_id NOT IN (select rm_material_id from TB_RM_MATERIAL_RECEBIMENTO)");

            // Verifica se o comprador foi informado.
            if (compradorId != null && compradorId > 0) {
                sB.append("AND rm.comprador_id = ");
                sB.append(String.valueOf(compradorId));
            }

            Query q = session.createSQLQuery(sB.toString());
            count = (Integer) q.uniqueResult();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * PRAZO DE ITENS
     *
     * Método que retorna a quantidade de itens que estão entre o prazo, O
     * filtro prazo e obrigatorio,prazo base e usasado para criar categorias de
     * prazo exemplo prazo > 0 && prazo < 5. O filtro data inicial e data final
     * e usado para trazer os dados de um periodo especifico.
     *
     * @param prazoBase
     * @param prazo
     * @param dataFinal
     * @param dataInicial
     * @return
     */
    public Integer selectCountItensPrazo(int prazoBase, int prazo, Date dataInicial, Date dataFinal) {
        int count = -1;
        try {
            if (prazo >= 0 && prazoBase >= 0) {
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                StringBuilder sB = new StringBuilder();

                sB.append("SELECT COUNT(RM.RM_ID) FROM TB_RM_MATERIAL RMM INNER JOIN TB_RM RM ON RMM.RM_ID = RM.RM_ID");

                //PRAZO
                if (prazo != 31) {
                    sB.append(" WHERE RM.DATA_APLICACAO - RM.DATA_RECEBIMENTO <=");
                    sB.append(String.valueOf(prazo));
                    //PRAZO BASE
                    if (prazoBase > 0) {
                        sB.append(" AND RM.DATA_APLICACAO - RM.DATA_RECEBIMENTO >");
                        sB.append(String.valueOf(prazoBase));
                    }
                } else if (prazo == 31) {
                    sB.append(" WHERE RM.DATA_APLICACAO - RM.DATA_RECEBIMENTO >=");
                    sB.append(String.valueOf(prazo));
                }

                //PERIODO
                if (dataInicial != null && dataFinal != null) {
                    sB.append(" AND RM.DATA_RECEBIMENTO >= '");
                    sB.append(Util.dateToString(dataInicial, "MM-dd-yyyy"));
                    sB.append("' AND RM.DATA_RECEBIMENTO <= '");
                    sB.append(Util.dateToString(dataFinal, "MM-dd-yyyy")).append("'");
                }

                Query q = session.createSQLQuery(sB.toString());
                count = (Integer) q.uniqueResult();
                session.close();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     *
     * MÉTODO QUE RETORNA A MEDIA DE DIAS UTILIZADOS PARA ENVIO DOS ITENS DE UMA
     * RM PARA O SISTEMA.
     *
     * @return
     */
    public Integer selectMediaDiasEnvioSistema() {
        BigInteger media = new BigInteger("0");
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            StringBuilder sB = new StringBuilder();
            sB.append("SELECT SUM ((RM.DATA_RECEBIMENTO - RM.DATA_EMISSAO) + 1) / (SELECT COUNT(RM_MATERIAL_ID) FROM TB_RM_MATERIAL) FROM TB_RM_MATERIAL RMM INNER JOIN TB_RM RM ON RMM.RM_ID = RM.RM_ID");

            Query q = session.createSQLQuery(sB.toString());
            media = (BigInteger) q.uniqueResult();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return media.intValue();
    }

    /**
     *
     * MÉTODO QUE RETORNA A MEDIA DE DIAS UTILIZADOS PARA ENVIO DOS ITENS DE UMA
     * RM PARA UM COMPRADOR.
     *
     * @return
     */
    public Integer selectMediaDiasEnvioComprador() {
        BigInteger media = new BigInteger("0");
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            StringBuilder sB = new StringBuilder();
            sB.append("SELECT SUM ((RM.DATA_ENVIO_COMPRADOR - RM.DATA_RECEBIMENTO) + 1) / (SELECT COUNT(RMM2.RM_MATERIAL_ID) FROM TB_RM_MATERIAL RMM2 INNER JOIN TB_RM RM2 ON RM2.RM_ID = RMM2.RM_ID WHERE RM2.DATA_ENVIO_COMPRADOR IS NOT NULL) FROM TB_RM_MATERIAL RMM INNER JOIN TB_RM RM ON RMM.RM_ID = RM.RM_ID WHERE RM.DATA_ENVIO_COMPRADOR IS NOT NULL");

            Query q = session.createSQLQuery(sB.toString());
            media = (BigInteger) q.uniqueResult();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return media.intValue();
    }

    /**
     *
     * MÉTODO QUE RETORNA A MEDIA DE DIAS PARA O ACEITE DA RM PELO COMPRADOR
     *
     *
     * @return
     */
    public Integer selectMediaDiasAceiteComprador() {
        BigInteger media = new BigInteger("0");
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            StringBuilder sB = new StringBuilder();
            sB.append("SELECT SUM ((RM.DATA_ACEITE_COMPRADOR - RM.DATA_ENVIO_COMPRADOR) + 1) / (SELECT COUNT(RMM2.RM_MATERIAL_ID) FROM TB_RM_MATERIAL RMM2 INNER JOIN TB_RM RM2 ON RM2.RM_ID = RMM2.RM_ID WHERE RM2.DATA_ENVIO_COMPRADOR IS NOT NULL AND RM2.DATA_ACEITE_COMPRADOR IS NOT NULL) FROM TB_RM_MATERIAL RMM INNER JOIN TB_RM RM ON RMM.RM_ID = RM.RM_ID WHERE RM.DATA_ENVIO_COMPRADOR IS NOT NULL AND RM.DATA_ACEITE_COMPRADOR IS NOT NULL");

            Query q = session.createSQLQuery(sB.toString());
            media = (BigInteger) q.uniqueResult();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return media.intValue();
    }

    /**
     *
     * MÉTODO QUE RETORNA A MEDIA DE DIAS PARA EFETIVAÇÃO DA COMPRA
     *
     *
     * @return
     */
    public Integer selectMediaDiasEfetivacaoCompra() {
        BigInteger media = new BigInteger("0");
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            StringBuilder sB = new StringBuilder();
            sB.append("SELECT SUM ((RMM.DATA_COMPRA - RM.DATA_ACEITE_COMPRADOR) + 1) / (SELECT COUNT(RMM2.RM_MATERIAL_ID) FROM TB_RM_MATERIAL RMM2 INNER JOIN TB_RM RM2 ON RM2.RM_ID = RMM2.RM_ID WHERE RMM2.DATA_COMPRA IS NOT NULL AND RM2.DATA_ACEITE_COMPRADOR IS NOT NULL) FROM TB_RM_MATERIAL RMM INNER JOIN TB_RM RM ON RMM.RM_ID = RM.RM_ID WHERE RMM.DATA_COMPRA IS NOT NULL AND RM.DATA_ACEITE_COMPRADOR IS NOT NULL");

            Query q = session.createSQLQuery(sB.toString());
            media = (BigInteger) q.uniqueResult();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return media.intValue();
    }

    /**
     *
     * MÉTODO QUE RETORNA A MEDIA DE DIAS PARA RECEBIMENTO DO MATERIAL (UTILIZA
     * A ULTIMA DATA DE RECEBIMENTO DO MATERIAL COMO REFERENCIA)
     *
     *
     * @param dataInicioRec
     * @param dataFimRec
     * @return
     */
    public Integer selectMediaDiasRecebimentoMaterial(Date dataInicioRec, Date dataFimRec) {
        BigInteger media = new BigInteger("0");
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            StringBuilder sB = new StringBuilder();
//            sB.append("SELECT SUM (((SELECT MAX (DATA_RECEBIMENTO_MATERIAL) FROM TB_RM_MATERIAL_RECEBIMENTO RMMR2 INNER JOIN TB_RM_MATERIAL RMM2 ON RMM2.RM_MATERIAL_ID = RMMR2.RM_MATERIAL_ID WHERE RMM2.RM_MATERIAL_ID = RMM.RM_MATERIAL_ID) - RMM.DATA_COMPRA) + 1) / (SELECT DISTINCT COUNT(RMMR3.RM_MATERIAL_ID) FROM TB_RM_MATERIAL_RECEBIMENTO RMMR3 INNER JOIN TB_RM_MATERIAL RMM3 ON RMMR3.RM_MATERIAL_ID = RMM3.RM_MATERIAL_ID) FROM TB_RM_MATERIAL RMM INNER JOIN TB_RM RM ON RMM.RM_ID = RM.RM_ID INNER JOIN TB_RM_MATERIAL_RECEBIMENTO RMMR ON RMMR.RM_MATERIAL_ID = RMM.RM_MATERIAL_ID AND RMM.DATA_COMPRA IS NOT NULL");
//            if(dataInicioRec != null && dataFimRec != null){
//                //PERIODO
//                sB.append(" AND RM.DATA_RECEBIMENTO >= '");
//                sB.append(Util.dateToString(dataInicioRec, "MM-dd-yyyy"));
//                sB.append("' AND RM.DATA_RECEBIMENTO <= '");
//                sB.append(Util.dateToString(dataFimRec, "MM-dd-yyyy") + "'");
//            }
            sB.append("SELECT  SUM (((SELECT MAX (DATA_RECEBIMENTO_MATERIAL) FROM TB_RM_MATERIAL_RECEBIMENTO RMMR2 INNER JOIN TB_RM_MATERIAL RMM2 ON RMM2.RM_MATERIAL_ID = RMMR2.RM_MATERIAL_ID WHERE RMM2.RM_MATERIAL_ID = RMM.RM_MATERIAL_ID) - RMM.DATA_COMPRA) + 1) / (SELECT DISTINCT COUNT(RMMR3.RM_MATERIAL_ID) FROM TB_RM_MATERIAL_RECEBIMENTO RMMR3 INNER JOIN TB_RM_MATERIAL RMM3 ON RMMR3.RM_MATERIAL_ID = RMM3.RM_MATERIAL_ID INNER JOIN TB_RM RM3 ON RMM3.RM_ID = RM3.RM_ID WHERE RMM3.DATA_COMPRA IS NOT NULL");
            if (dataInicioRec != null && dataFimRec != null) {
                sB.append(" AND RM3.DATA_RECEBIMENTO >= '");
                sB.append(Util.dateToString(dataInicioRec, "MM-dd-yyyy")).append("'");
                sB.append(" AND RM3.DATA_RECEBIMENTO <= '");
                sB.append(Util.dateToString(dataFimRec, "MM-dd-yyyy")).append("'");
            }
            sB.append(") FROM TB_RM_MATERIAL RMM INNER JOIN TB_RM RM ON RMM.RM_ID = RM.RM_ID INNER JOIN TB_RM_MATERIAL_RECEBIMENTO RMMR ON RMMR.RM_MATERIAL_ID = RMM.RM_MATERIAL_ID AND RMM.DATA_COMPRA IS NOT NULL");
            if (dataInicioRec != null && dataFimRec != null) {
                sB.append(" WHERE RM.DATA_RECEBIMENTO >= '");
                sB.append(Util.dateToString(dataInicioRec, "MM-dd-yyyy")).append("'");
                sB.append(" AND RM.DATA_RECEBIMENTO <= '");
                sB.append(Util.dateToString(dataFimRec, "MM-dd-yyyy")).append("'");
            }

            Query q = session.createSQLQuery(sB.toString());
            media = (BigInteger) q.uniqueResult();
            session.close();

            if (media == null) {
                media = new BigInteger("0");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return media.intValue();
    }

    /**
     *
     * MÉTODO QUE RETORNA A MEDIA DE DIAS ENTRE O RECEBIMENTO DA RM E COMPRA DOS
     * ITENS.
     *
     *
     * @param dataInicioRec
     * @param dataFimRec
     * @return
     */
    public Integer selectMediaDiasRecebimentoRmCompra(Date dataInicioRec, Date dataFimRec) {
        BigInteger media = new BigInteger("0");
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            StringBuilder sB = new StringBuilder();
            sB.append("SELECT SUM ((RMM.DATA_COMPRA - RM.DATA_RECEBIMENTO) + 1) / (SELECT COUNT(RMM2.RM_MATERIAL_ID) FROM TB_RM_MATERIAL RMM2 INNER JOIN TB_RM RM2 ON RM2.RM_ID = RMM2.RM_ID WHERE RMM2.DATA_COMPRA IS NOT NULL");
            //Adiciona filtro se a Data for diferente de null
            if (dataInicioRec != null && dataFimRec != null) {
                //PERIODO
                sB.append(" AND RM2.DATA_RECEBIMENTO >= '");
                sB.append(Util.dateToString(dataInicioRec, "MM-dd-yyyy"));
                sB.append("' AND RM2.DATA_RECEBIMENTO <= '");
                sB.append(Util.dateToString(dataFimRec, "MM-dd-yyyy")).append("'");
            }

            sB.append(") FROM TB_RM_MATERIAL RMM INNER JOIN TB_RM RM ON RMM.RM_ID = RM.RM_ID WHERE RMM.DATA_COMPRA IS NOT NULL");

            //Adiciona filtro se a Data for diferente de null
            if (dataInicioRec != null && dataFimRec != null) {
                //PERIODO
                sB.append(" AND RM.DATA_RECEBIMENTO >= '");
                sB.append(Util.dateToString(dataInicioRec, "MM-dd-yyyy"));
                sB.append("' AND RM.DATA_RECEBIMENTO <= '");
                sB.append(Util.dateToString(dataFimRec, "MM-dd-yyyy")).append("'");
            }
            System.out.println(sB.toString());
            Query q = session.createSQLQuery(sB.toString());
            media = (BigInteger) q.uniqueResult();
            session.close();

            if (media == null) {
                media = new BigInteger("0");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return media.intValue();
    }
    
    public Rm getRmPorId(Integer rmId) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Rm rm = (Rm) session.getNamedQuery("rm.byId").setParameter("id", rmId).uniqueResult();
            session.close();
            return rm;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void atualizarDataNecessidadeRm(final String idRm, final String dataNecessidade) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trx = null;
        try {

            trx = session.beginTransaction();

            StringBuilder sql = new StringBuilder("UPDATE TB_RM ");
            sql.append("SET DATA_APLICACAO=CONVERT(varchar, '");
            sql.append(dataNecessidade);
            sql.append("', 101)");
            sql.append(" WHERE RM_ID=");
            sql.append(idRm);

            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            int i = sqlQuery.executeUpdate();
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            if (e.getCause() != null) {
                throw new Exception("Método: atualizarDataNecessidadeRm - ERRO: " + e.getCause().getMessage());
            } else {
                throw new Exception("Método: atualizarDataNecessidadeRm - ERRO: " + e.getMessage());
            }
        }
    }
    
    public List<Pessoa> selectEmailPessoa(Integer idPessoa) {
    	
        Log logger = LogFactory.getLog(Constantes.NOME_PROJETO);
    	List<Pessoa> list = new ArrayList<>();
        
        try {
        	
        	String sql = "SELECT EMAIL, NOME FROM TB_PESSOA WHERE PESSOA_ID = " + idPessoa;
        	
            PreparedStatement smt = ConnectionFactory.getConnection().prepareStatement(sql);
            ResultSet resultSet = smt.executeQuery();

            while (resultSet.next()) {
           	 	Pessoa pessoa = new Pessoa();
                pessoa.setEmail(resultSet.getString("EMAIL"));
                pessoa.setNome(resultSet.getString("NOME"));
                list.add(pessoa);
            }

            smt.close();
            resultSet.close();	
           
       } catch (Exception e) {
           System.out.println("*************************************************ERRO AO GERAR CONSULTA EMAIL PESSOA*************************************************");
       }
        
       return list;
    }

}
