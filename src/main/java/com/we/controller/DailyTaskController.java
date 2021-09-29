package com.we.controller;

import com.we.common.BaseController;
import com.we.repository.DailyTaskRepository;
import com.we.services.action.taskinfo.CreateandUpdateDailyTaskActionService;
import com.we.services.action.taskinfo.GenerateRptDailyTaskActionService;
import com.we.services.action.taskinfo.ListDailyTaskActionService;
import com.we.services.action.taskinfo.SelectDailyTaskActionService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

@Controller
public class DailyTaskController extends BaseController {

    @Autowired
    private DailyTaskRepository dailyTaskRepository;

    private CreateandUpdateDailyTaskActionService createandUpdateDailyTaskActionService;
    private SelectDailyTaskActionService selectDailyTaskActionService;
    private ListDailyTaskActionService listDailyTaskActionService;
    private GenerateRptDailyTaskActionService generateRptDailyTaskActionService;

    @Autowired
    public DailyTaskController(CreateandUpdateDailyTaskActionService createandUpdateDailyTaskActionService,
                               SelectDailyTaskActionService selectDailyTaskActionService,
                               ListDailyTaskActionService listDailyTaskActionService,
                               GenerateRptDailyTaskActionService generateRptDailyTaskActionService) {
        this.createandUpdateDailyTaskActionService = createandUpdateDailyTaskActionService;
        this.selectDailyTaskActionService = selectDailyTaskActionService;
        this.listDailyTaskActionService = listDailyTaskActionService;
        this.generateRptDailyTaskActionService = generateRptDailyTaskActionService;
    }

    @GetMapping("/admin/tasks/show")
    public String showTask(Model model) {
        model.addAttribute("userName", super.sessionValue());
        return "view/tasks/show";
    }

    @GetMapping("/admin/tasks/create")
    public String createTask(Model model) {
        model.addAttribute("userName", super.sessionValue());
        return "view/tasks/create/show";
    }

    @RequestMapping(value = "/admin/tasks/save", method = RequestMethod.POST)
    @ResponseBody
    public String saveTask(@RequestParam Map<String, Object> parameters) throws Exception {
        parameters.put("createdBy", super.getSessionUserName());
        return renderOutput(createandUpdateDailyTaskActionService, parameters);
    }

    @GetMapping("/admin/tasks/list")
    @ResponseBody
    public String ListTask(Map<String, Object> parameters) {
        return renderOutput(listDailyTaskActionService, parameters);
    }

    @RequestMapping(value = "/admin/tasks/report/generate", method = {RequestMethod.GET, RequestMethod.POST})
    public void export(@RequestParam Map<String, Object> parameters, HttpServletResponse response) throws IOException, JRException, SQLException {
        JasperPrint jasperPrint = null;
        OutputStream out = response.getOutputStream();
        response.setHeader("Content-Disposition", String.format("inline; filename=Daily_Task_Report" + "_" + new Date() + "." + parameters.get("exportMode")));
        response.setContentType("application/" + parameters.get("exportMode"));

        jasperPrint = generateRptDailyTaskActionService.exportPdfFile(parameters);
        super.exportModeSelector((String) parameters.get("exportMode"), jasperPrint, out);

    }
//
//    @PostMapping("/admin/selectStudent")
//    @ResponseBody
//    public Long studentInfoids(@RequestParam Map<String, Object> parameters) {
//        long id = Long.parseLong((String) parameters.get("id"));
//        return id;
//    }
//
//    @RequestMapping(value = "/admin/editStudent", method = {RequestMethod.GET, RequestMethod.POST})
//    public String studentInfoEdit(@RequestParam Map<String, Object> parameters, Model model) {
//        StudentInfo studentInfo = selectStudentInfoActionService.studentInfo(parameters);
//        model.addAttribute("studentInfo", studentInfo);
//        return "view/student/edit/show";
//    }
//
//    @RequestMapping(value = "/admin/delStudent", method = {RequestMethod.GET, RequestMethod.POST})
//    public String studentDeleteById(@RequestParam Map<String, Object> parameters) {
//        deleteStudentInfoActionService.studentDelete(parameters);
//        return "redirect: view/student/show";
//    }
}
