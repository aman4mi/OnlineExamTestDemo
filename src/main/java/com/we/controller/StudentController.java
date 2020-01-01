package com.we.controller;

import com.we.common.BaseController;
import com.we.entity.StudentInfo;
import com.we.repository.StudentInfoRepository;
import com.we.services.action.studentinfo.*;
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

/**
 * Created by aman.ullah on 2/26/2019.
 */

@Controller
public class StudentController extends BaseController {

    @Autowired
    private StudentInfoRepository studentInfoRepository;

    private CreateStudentInfoActionService createStudentInfoActionService;
    private ListStudentInfoActionService listStudentInfoActionService;
    private SelectStudentInfoActionService selectStudentInfoActionService;
    private GenerateRptStudentInfoActionService generateRptStudentInfoActionService;
    private DeleteStudentInfoActionService deleteStudentInfoActionService;

    @Autowired
    public StudentController(CreateStudentInfoActionService createStudentInfoActionService
            , ListStudentInfoActionService listStudentInfoActionService
            , SelectStudentInfoActionService selectStudentInfoActionService
            , GenerateRptStudentInfoActionService generateRptStudentInfoActionService
            , DeleteStudentInfoActionService deleteStudentInfoActionService) {
        this.createStudentInfoActionService = createStudentInfoActionService;
        this.listStudentInfoActionService = listStudentInfoActionService;
        this.selectStudentInfoActionService = selectStudentInfoActionService;
        this.generateRptStudentInfoActionService = generateRptStudentInfoActionService;
        this.deleteStudentInfoActionService = deleteStudentInfoActionService;
    }

// depricated controllers are (showStudent,createStudent) old and don't support session oser name.
//    @GetMapping("/admin/student")
//    public String showStudent() {
//        return "view/student/show";
//    }
//
//    @GetMapping("/admin/createStudent")
//    public String createStudent() {
//        return "view/student/create/show";
//    }

    @GetMapping("/admin/student")
    public String showStudent(Model model) {
        model.addAttribute("userName", super.sessionValue());
        return "view/student/show";
    }

    @GetMapping("/admin/createStudent")
    public String createStudent(Model model) {
        model.addAttribute("userName", super.sessionValue());
        return "view/student/create/show";
    }

    @GetMapping("/admin/studentRestShow")
    public String studentRestShow() {
        return "view/studentrest/show"; // this is to rest UI
    }

    @RequestMapping(value = "/admin/saveStudent", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(@RequestParam Map<String, Object> parameters) throws Exception {
        // @RequestParam MultiValueMap parameters  //it also very effictive.
        parameters.put("createdBy", super.getSessionUserName());
        return renderOutput(createStudentInfoActionService, parameters);
    }

    @GetMapping("/admin/listStudent")
    @ResponseBody
    public String ListStudent(Map<String, Object> parameters) {

        return renderOutput(listStudentInfoActionService, parameters);
    }

    @PostMapping("/admin/selectStudent")
    @ResponseBody
    public Long studentInfoids(@RequestParam Map<String, Object> parameters) {
        long id = Long.parseLong((String) parameters.get("id"));
        return id;
    }

    @RequestMapping(value = "/admin/editStudent", method = {RequestMethod.GET, RequestMethod.POST})
    public String studentInfoEdit(@RequestParam Map<String, Object> parameters, Model model) {
        StudentInfo studentInfo = selectStudentInfoActionService.studentInfo(parameters);
        model.addAttribute("studentInfo", studentInfo);
        return "view/student/edit/show";
    }

    @RequestMapping(value = "/admin/delStudent", method = {RequestMethod.GET, RequestMethod.POST})
    public String studentDeleteById(@RequestParam Map<String, Object> parameters) {
        deleteStudentInfoActionService.studentDelete(parameters);
        return "redirect: view/student/show";
    }


    @RequestMapping(value = "/admin/rptStudent", method = {RequestMethod.GET, RequestMethod.POST})
    public void export(@RequestParam Map<String, Object> parameters, HttpServletResponse response) throws IOException, JRException, SQLException {
        JasperPrint jasperPrint = null;
        OutputStream out = response.getOutputStream();
        /*response.setHeader("Content-Disposition", String.format("attachment; filename=\"Demo_Report.pdf\""));
        response.setContentType("application/x-download");*/
        response.setHeader("Content-Disposition", String.format("inline; filename=Student_Report" + "_" + new Date() + "." + parameters.get("exportMode")));
        response.setContentType("application/" + parameters.get("exportMode"));

        jasperPrint = generateRptStudentInfoActionService.exportPdfFile(parameters);
        super.exportModeSelector((String) parameters.get("exportMode"), jasperPrint, out);

    }

}
