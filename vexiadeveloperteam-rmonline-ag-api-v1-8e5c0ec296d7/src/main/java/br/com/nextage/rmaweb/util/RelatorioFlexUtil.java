/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nextage.rmaweb.util;

import br.com.nextage.persistence.util.HibernateUtil;
import br.com.nextage.util.Util;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import javax.servlet.ServletOutputStream;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.hibernate.Session;

/**
 *
 * @author Leco
 */
public class RelatorioFlexUtil {

    /**
     * Método para gerar o PDF. É chamado pelos services para gerar um relatório
     * PDF.
     *
     * @param streamDeSaida
     * @param nomeRelatorio
     * @param parameters
     */
    public static void geraPdf(ServletOutputStream streamDeSaida, String nomeRelatorio, HashMap parameters) {
        String nomeArquivoGerado = "";

        Connection conn = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            conn = session.connection();

            ResourceBundle rb = Util.getFileConfig();
            if (rb != null) {
                String reportPath = rb.getString("config.url_relatorios") + nomeRelatorio;

                Date d = new Date();

                // criando a impressora Jasper a partir do caminho para o arquivo .jasper,
                // dos atributos (um Map que pode ser passado ao retalorio e cujos valores podem ser acessados como paramentros no relatorio)
                // e da DataSource (definida a partir da Colecao dados)
                JasperPrint impressoraJasper = JasperFillManager.fillReport(reportPath, parameters, conn);

                JRPdfExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, streamDeSaida);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, nomeArquivoGerado);

                streamDeSaida.flush();
                exporter.exportReport();
                streamDeSaida.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
