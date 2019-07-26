package com.we.controller;

import com.we.common.BaseController;
import com.we.services.action.studentinfo.ListStudentInfoActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by aman.ullah on 3/12/2019.
 */
@RestController
public class StudentRestController extends BaseController {

    private ListStudentInfoActionService listStudentInfoActionService;

    @Autowired
    public StudentRestController(ListStudentInfoActionService listStudentInfoActionService) {
        this.listStudentInfoActionService = listStudentInfoActionService;
    }

    @GetMapping(value = "/listStudentRest", produces = "application/json")
    @ResponseBody
    public String restStudentList(@RequestParam Map<String, Object> parameters) throws Exception {

        return renderOutput(listStudentInfoActionService, parameters);
    }

}
