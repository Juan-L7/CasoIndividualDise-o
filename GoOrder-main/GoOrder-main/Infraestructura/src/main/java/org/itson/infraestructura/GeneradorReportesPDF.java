package org.itson.infraestructura;

import GoOrderDTO.ReportePaquetesResumenDTO;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class GeneradorReportesPDF {

    public static void generarReportePaquetes(
            ReportePaquetesResumenDTO datosReporte
    ) {

        try {

            JRBeanCollectionDataSource dataSource =
                    new JRBeanCollectionDataSource(
                            datosReporte.getDetallesTabla()
                    );

            InputStream reporte =
                    GeneradorReportesPDF.class
                            .getClassLoader()
                            .getResourceAsStream(
                                    "reportes/ReportePaquetes.jrxml"
                            );

            if (reporte == null) {

                JOptionPane.showMessageDialog(
                        null,
                        "No se encontró el archivo ReportePaquetes.jrxml",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            JasperReport jasperReport =
                    JasperCompileManager.compileReport(reporte);

            Map<String, Object> parametros =
                    new HashMap<>();

            parametros.put(
                    "fechaGeneracion",
                    datosReporte.getFechaGeneracion()
            );

            parametros.put(
                    "rangoFechas",
                    datosReporte.getRangoFechas()
            );

            parametros.put(
                    "totalPaquetesRegistrados",
                    String.valueOf(
                            datosReporte.getTotalPaquetesRegistrados()
                    )
            );

            parametros.put(
                    "totalPaquetesVendidos",
                    String.valueOf(
                            datosReporte.getTotalPaquetesVendidos()
                    )
            );

            parametros.put(
                    "paqueteMasVendido",
                    datosReporte.getPaqueteMasVendido()
            );

            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(
                            jasperReport,
                            parametros,
                            dataSource
                    );

            JFileChooser fileChooser =
                    new JFileChooser();

            fileChooser.setDialogTitle(
                    "Guardar reporte PDF"
            );

            FileNameExtensionFilter filtro =
                    new FileNameExtensionFilter(
                            "Archivos PDF (*.pdf)",
                            "pdf"
                    );

            fileChooser.setFileFilter(filtro);

            int opcion =
                    fileChooser.showSaveDialog(null);

            if (opcion != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File archivo =
                    fileChooser.getSelectedFile();

            String ruta =
                    archivo.getAbsolutePath();

            if (!ruta.toLowerCase().endsWith(".pdf")) {
                ruta += ".pdf";
            }

            JasperExportManager.exportReportToPdfFile(
                    jasperPrint,
                    ruta
            );

            JOptionPane.showMessageDialog(
                    null,
                    "Reporte generado correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (JRException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error JasperReports:\n"
                    + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error general:\n"
                    + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }
}