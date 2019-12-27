package com.we.services.action.studentinfo;

import com.we.services.BaseService;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
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

    public JasperPrint exportPdfFile(Map params) {
        try {
            log.error("-------- Student Report printing------------ start----");
            Map<String, Object> parameters = new HashMap<String, Object>();

            String jasperFileName = "StudentRpt";
            String absolutePath = context.getRealPath("/WEB-INF/jasper/" + jasperFileName + ".jrxml");
            String fullPath = request.getSession().getServletContext().getRealPath("/WEB-INF/jasper/" + jasperFileName + ".jrxml");
            // Compile the Jasper report from .jrxml to .japser
            JasperReport jasperReport = JasperCompileManager.compileReport(absolutePath);
            // Parameters for report
            parameters.put("id", Long.parseLong(params.get("id").toString()));

            JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
            log.error("-------- Student Report printing------------ stop----");

            return print;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

}