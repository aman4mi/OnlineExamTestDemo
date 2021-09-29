package com.we.services.action.taskinfo;

import com.we.common.ActionInterface;
import com.we.entity.DailyTask;
import com.we.repository.DailyTaskRepository;
import com.we.services.BaseService;
import com.we.services.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
@Component
public class CreateandUpdateDailyTaskActionService extends BaseService implements ActionInterface {

    private DailyTaskRepository dailyTaskRepository;
    private static String CREATE_SUCCESS_MESSAGE = "Successfully Created";
    private static String UPDATE_SUCCESS_MESSAGE = "Successfully Updated";


    @Autowired
    public CreateandUpdateDailyTaskActionService(DailyTaskRepository dailyTaskRepository) {
        this.dailyTaskRepository = dailyTaskRepository;
    }

    @Override
    public Map executePreCondition(Map parameters) {
        return parameters;
    }

    @Override
    public Map execute(Map previousResult) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(new Date());

        DailyTask dailyTask = null;

        if (previousResult.get("id") != null) {
            long id = Long.parseLong((String) previousResult.get("id"));
            dailyTask = dailyTaskRepository.findById(id);
            previousResult.put("idAvailable", "true");

            dailyTask.setTaskId((String) previousResult.get("task_id"));

        } else {
            dailyTask = new DailyTask();
            dailyTask.setTaskId((String) previousResult.get("task_id") + " OXD" + Tools.randGen());
        }

        dailyTask.setBusinessDate((String) previousResult.get("bussiness_date"));
        dailyTask.setTaskName((String) previousResult.get("task_name"));
        dailyTask.setStatus((String) previousResult.get("status"));
        dailyTask.setTaskFor((String) previousResult.get("task_for"));
        dailyTask.setSolvedBy((String) previousResult.get("solved_by"));
        dailyTask.setStartedAt((String) previousResult.get("started_at"));
        dailyTask.setEndedAt((String) previousResult.get("ended_at"));
        dailyTask.setRemarks((String) previousResult.get("remarks"));
        dailyTask.setCreatedBy((String) previousResult.get("createdBy"));
        dailyTask.setCreatedOn(dateString);

        dailyTaskRepository.save(dailyTask);

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
