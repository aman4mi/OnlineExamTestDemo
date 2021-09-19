package com.we.services.action.resultinfo;

import com.we.common.ActionInterface;
import com.we.services.BaseService;
import groovy.sql.GroovyRowResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Component
public class ListResultActionService extends BaseService implements ActionInterface {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static String SUCCESS_MESSAGE = "Successfully Displayed";

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
        return super.setSuccess(executeResult, SUCCESS_MESSAGE);
    }

    @Override
    public Map buildFailureResult(Map executeResult) {
        return executeResult;
    }

    private String executeWithParams() {
        String sqlString = "SELECT id, roll_no, sub_one, sub_two, final_mark, expired, remarks FROM result";

        return sqlString;
    }
}
