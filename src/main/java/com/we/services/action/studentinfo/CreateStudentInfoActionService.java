package com.we.services.action.studentinfo;

import com.we.common.ActionInterface;
import com.we.entity.StudentInfo;
import com.we.repository.StudentInfoRepository;
import com.we.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by aman.ullah on 3/11/2019.
 */
@Service
@Component
public class CreateStudentInfoActionService extends BaseService implements ActionInterface {

    private StudentInfoRepository studentInfoRepository;
    private static String CREATE_SUCCESS_MESSAGE = "Successfully Created";
    private static String UPDATE_SUCCESS_MESSAGE = "Successfully Updated";


    @Autowired
    public CreateStudentInfoActionService(StudentInfoRepository studentInfoRepository) {
        this.studentInfoRepository = studentInfoRepository;
    }

    @Override
    public Map executePreCondition(Map parameters) {
        return parameters;
    }

    @Override
    public Map execute(Map previousResult) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(new Date());

        StudentInfo studentInfo = null;

        if (previousResult.get("id") != null) {
            long id = Long.parseLong((String) previousResult.get("id"));
            studentInfo = studentInfoRepository.findById(id);
            previousResult.put("idAvailable", "true");
        } else {
            studentInfo = new StudentInfo();
        }

        studentInfo.setStudentId((String) previousResult.get("studentId"));
        studentInfo.setStudentName((String) previousResult.get("studentName"));
        studentInfo.setCourseId((String) previousResult.get("courseId"));
        studentInfo.setCourseTitle((String) previousResult.get("courseTitle"));
        studentInfo.setDeptName((String) previousResult.get("deptName"));
        studentInfo.setSemesterInfo((String) previousResult.get("semesterInfo"));
        studentInfo.setCreatedBy("Me");
        studentInfo.setCreatedOn(dateString);

        studentInfoRepository.save(studentInfo);

        return previousResult;
    }

    @Override
    public Map executePostCondition(Map previousResult) {
        return previousResult;
    }

    @Override
    public Map buildSuccessResult(Map executeResult) {
        if (executeResult.get("idAvailable") == "true") {
            return super.setSuccess(executeResult, UPDATE_SUCCESS_MESSAGE);
        } else {
            return super.setSuccess(executeResult, CREATE_SUCCESS_MESSAGE);
        }

    }

    @Override
    public Map buildFailureResult(Map executeResult) {
        return executeResult;
    }
}
