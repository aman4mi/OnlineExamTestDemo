package com.we.services.action.studentinfo;

import com.we.services.BaseService;
import net.sf.jasperreports.engine.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
@Component
public class GenerateRptStudentInfoActionService extends BaseService {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    DataSource dataSource;
    @Autowired
    HttpServletRequest request;
    @Autowired
    ServletContext context;

    public JasperPrint exportPdfFile(Map params) throws SQLException, JRException, IOException {
        Map<String, Object> parameters = new HashMap<String, Object>();

        String jasperFileName = "SpringBootTest1";


        String absolutePath = context.getRealPath("/WEB-INF/jasper/" + jasperFileName + ".jrxml");
        String fullPath = request.getSession().getServletContext().getRealPath("/WEB-INF/jasper/" + jasperFileName + ".jrxml");
        // Compile the Jasper report from .jrxml to .japser
        JasperReport jasperReport = JasperCompileManager.compileReport(absolutePath);
        // Parameters for report

        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());

        return print;
    }

/*
    Map execute(Map previousResult) {
        String jasperFileName, subReportFilePath, mainJasperFileName, subReportSubsidiaryFilePath, subReportName, reportPath = "";
        String PDF_STREAM = "pdfStream";
        ByteArrayOutputStream pdfStream = null;
        Map<String, Object> parameters = new HashMap<String, Object>();

        try {
            jasperFileName = "voucherControlSheet";
            JasperPrint print;
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\SpringBootTest1.jrxml");
            parameters.put("moduleIds", null);

            print = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());

            pdfStream = new ByteArrayOutputStream();

            if (previousResult.get("exportMode").equals("pdf")) {// exports report to pdf
                JRPdfExporter exporter = new JRPdfExporter();
                exporter.setExporterInput(new SimpleExporterInput(print));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfStream));
                SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
                exporter.setConfiguration(configuration);
                exporter.exportReport();
            } else {// exports report to excel
                JRXlsExporter xlsExporter = new JRXlsExporter();
                xlsExporter.setExporterInput(new SimpleExporterInput(print));
                xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfStream));
                SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
                xlsReportConfiguration.setOnePagePerSheet(false);
                xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
                xlsReportConfiguration.setDetectCellType(true);
                xlsReportConfiguration.setWhitePageBackground(false);
                xlsExporter.setConfiguration(xlsReportConfiguration);
                xlsExporter.exportReport();
            }
            previousResult.put(PDF_STREAM, pdfStream);

            return previousResult;

        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
        */
/*finally {
            pdfStream.close();
        }*//*

    }
*/

}