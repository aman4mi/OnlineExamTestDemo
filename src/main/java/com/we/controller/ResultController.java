package com.we.controller;

import com.we.common.BaseController;
import com.we.entity.StudentInfo;
import com.we.repository.StudentInfoRepository;
import com.we.services.action.resultinfo.ListResultActionService;
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

@Controller
public class ResultController extends BaseController {

    private ListResultActionService listResultActionService;

    @Autowired
    public ResultController(ListResultActionService listResultActionService) {
        this.listResultActionService = listResultActionService;
    }

    @GetMapping("/admin/result")
    public String showResult(Model model) {
        model.addAttribute("userName", super.sessionValue());
        return "view/result/show";
    }

    @GetMapping("/admin/listResult")
    @ResponseBody
    public String ListResult(Map<String, Object> parameters) {
        return renderOutput(listResultActionService, parameters);
    }

    @PostMapping("/admin/createResult")
    @ResponseBody
    public Long createResult(@RequestParam Map<String, Object> parameters) {
        System.out.println("Hello World" + parameters);
        return null;
    }

}
