package com.we.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.we.entity.User;
import com.we.services.service.UserService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by aman.ullah on 3/10/2019.
 */
@Service
public class BaseController {

    private static final String TRUE = "true";
    private static final String FALSE = "false";

    @Autowired
    private ObjectMapper mapperObj;

    @Autowired
    private UserService userService;

    protected BaseController() {

    }

    //@ResponseBody
    protected String renderOutput(ActionInterface action, Map<String, Object> params) {
        String output = "";
        Map result = this.getServiceResponse(action, params);
        try {
            output = mapperObj.writeValueAsString(result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return output;
    }

    private Map getServiceResponse(ActionInterface action, Map<String, Object> params) {

        params.put(Tools.IS_ERROR, FALSE);

        Map preResult = action.executePreCondition(params);
        String isError = (String) preResult.get("isError");
        if (isError.equals(TRUE)) {
            return action.buildFailureResult(preResult);
        }

        Map executeResult = action.execute(preResult);
        isError = (String) executeResult.get("isError");
        if (isError.equals(TRUE)) {
            return action.buildFailureResult(executeResult);
        }

        Map postResult = action.executePostCondition(executeResult);
        isError = (String) postResult.get("isError");
        if (isError.equals(TRUE)) {
            return action.buildFailureResult(postResult);
        }

        return action.buildSuccessResult(postResult);
    }

    protected String sessionValue() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        String getRole = String.valueOf(auth.getAuthorities());
        return "Welcome " + user.getName() + " " + " (" + user.getEmail() + ")" + getRole;
    }

    protected String getSessionUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        return user.getName();
    }

    protected void exportModeSelector(String xprtMode, JasperPrint jasperPrint, OutputStream outputStream) throws JRException {
        if (xprtMode.equals("pdf")) {// exports report to pdf
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            exporter.setConfiguration(configuration);
            exporter.exportReport();
        } else {// exports report to excel
//            JRXlsExporter xlsExporter = new JRXlsExporter();
            JRXlsxExporter xlsExporter = new JRXlsxExporter();

            xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
            SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
            reportConfig.setOnePagePerSheet(false);
            reportConfig.setRemoveEmptySpaceBetweenRows(true);
            reportConfig.setDetectCellType(true);
            reportConfig.setWhitePageBackground(false);
            xlsExporter.setConfiguration(reportConfig);

            xlsExporter.exportReport();
        }
    }


}
