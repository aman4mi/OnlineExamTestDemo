package com.we.services.action.studentinfo;

import com.we.common.ActionInterface;
import com.we.services.BaseService;
import groovy.sql.GroovyRowResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by aman.ullah on 3/13/2019.
 */
@Service
@Component
public class ListStudentInfoActionService extends BaseService implements ActionInterface {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Map executePreCondition(Map parameters) {
        return parameters;
    }

    @Override
    public Map execute(Map previousResult) {
        try {
            String preparedQuery = executeWithParams();
            List<GroovyRowResult> queryList = executeSelectSql(preparedQuery);
            previousResult.put("queryList", queryList);
            previousResult.put("aman", "aman");
            return previousResult;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Map executePostCondition(Map previousResult) {
        return previousResult;
    }

    @Override
    public Map buildSuccessResult(Map executeResult) {
        return executeResult;
    }

    @Override
    public Map buildFailureResult(Map executeResult) {
        return executeResult;
    }

    private String executeWithParams() {
//        String sqlString = "SELECT id, studentId, studentName, courseId FROM StudentInfo ";
        String sqlString = "SELECT id, course_id, course_title, created_by, created_on, dept_name, \n" +
                            "  semester_info, student_id, student_name\n" +
                            "  FROM student_info";

        return sqlString;
    }
}
