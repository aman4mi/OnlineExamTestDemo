package com.we.services.action.studentinfo;

import com.we.entity.StudentInfo;
import com.we.repository.StudentInfoRepository;
import com.we.services.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Aman on 6/15/2019.
 */
@Service
@Component
public class SelectStudentInfoActionService extends BaseService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StudentInfoRepository studentInfoRepository;


    public StudentInfo studentInfo(Map parameters) {

        System.out.println("sss");

        return studentInfoRepository.findById(Long.parseLong((String) parameters.get("id")));
//        return null;
    }


}
