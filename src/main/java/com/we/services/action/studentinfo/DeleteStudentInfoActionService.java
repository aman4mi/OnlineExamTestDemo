package com.we.services.action.studentinfo;

import com.we.entity.StudentInfo;
import com.we.repository.StudentInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by Aman on 12/27/2019.
 */
@Service
@Component
public class DeleteStudentInfoActionService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StudentInfoRepository studentInfoRepository;

    @Transactional
    public String studentDelete(Map parameters) {
        String message = "Student Deleted Successfully!";
        logger.error("-----------Student Selected---------");
        StudentInfo studentInfo = studentInfoRepository.findById(Long.parseLong((String) parameters.get("id")));
        studentInfoRepository.delete(studentInfo);
        return message;
    }
}
