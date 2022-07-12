package com.code.line.system.action.bean;

import com.code.line.system.entity.TSprint;
import com.code.line.system.entity.TSprintActionListEntity;
import com.code.line.system.service.ITProjectService;
import com.code.line.system.service.ITSprintActionListService;
import com.code.line.system.service.ITSprintProjectService;
import com.code.line.system.service.ITSprintService;
import com.codeline.framwork.constant.SprintEnvStatusEnums;
import com.codeline.framwork.response.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: syl
 * @Date: 2022/7/12 01:27
 * @Description:
 */
@Service
public class BaseAction {

    @Autowired
    protected ITSprintActionListService actionListService;
    @Autowired
    private ITSprintService sprintService;
    @Autowired
    private ITProjectService projectService;
    @Autowired
    private ITSprintProjectService sprintProjectService;


    public ApiResult exeSuccessAfter(TSprintActionListEntity action) {
        TSprint sprint = sprintService.getById(action.getSprintId());
        SprintEnvStatusEnums envStatusEnums = SprintEnvStatusEnums.getByEnv(sprint.getSprintEnvStatus());
        return actionListService.activatedNextSprintAction(action.getSprintId(),envStatusEnums,action.getId());
    }
}
