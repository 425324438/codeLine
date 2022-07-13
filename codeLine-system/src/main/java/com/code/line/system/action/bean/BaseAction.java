package com.code.line.system.action.bean;

import com.code.line.system.action.SprintContext;
import com.code.line.system.entity.TSprint;
import com.code.line.system.entity.TSprintActionListEntity;
import com.code.line.system.service.*;
import com.codeline.framwork.constant.GitStorageType;
import com.codeline.framwork.constant.SprintEnvStatusEnums;
import com.codeline.framwork.response.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    protected ITSprintService sprintService;
    @Autowired
    protected ITProjectService projectService;
    @Autowired
    protected ITSprintProjectService sprintProjectService;
    @Autowired
    protected List<GitApiService> gitApiServiceList;
    protected Map<GitStorageType, GitApiService> gitApiServiceMap = new HashMap<>();


    /**
     * 激活后续需要执行的Action
     */
    public ApiResult exeSuccessAfter() {
        SprintContext sprintContext = SprintContext.get();
        TSprint sprint = sprintContext.getSprint();
        TSprintActionListEntity action = sprintContext.getSprintAction();
        SprintEnvStatusEnums envStatusEnums = SprintEnvStatusEnums.getByEnv(sprint.getSprintEnvStatus());
        return actionListService.activatedNextSprintAction(action.getSprintId(),envStatusEnums,action.getId());
    }

    @Autowired
    public void setGitApiServiceMap(List<GitApiService> gitApiServiceList){
        gitApiServiceMap = gitApiServiceList.stream()
                .collect(Collectors.toMap(GitApiService::getStorageType, Function.identity()));
    }
}
