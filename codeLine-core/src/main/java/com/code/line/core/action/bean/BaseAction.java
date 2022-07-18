package com.code.line.core.action.bean;

import com.code.line.core.action.SprintContext;
import com.code.line.system.entity.TSprint;
import com.code.line.system.entity.TSprintActionListEntity;
import com.code.line.system.service.*;
import com.codeline.framwork.constant.ActionStatusEnums;
import com.codeline.framwork.constant.GitStorageType;
import com.codeline.framwork.constant.SprintEnvStatusEnums;
import com.codeline.framwork.constant.TypeConstants;
import com.codeline.framwork.response.ApiResult;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    protected ISysConfigService configService;
    protected Map<GitStorageType, GitApiService> gitApiServiceMap = new HashMap<>();
    private String mainBranchName;
    private Long assigneeId;


    /**
     * 激活后续需要执行的Action
     */
    public ApiResult exeSuccessAfter() {
        SprintContext sprintContext = SprintContext.get();
        TSprint sprint = sprintContext.getSprint();
        TSprintActionListEntity action = sprintContext.getSprintAction();
        action.setActionStatus(ActionStatusEnums.ended.name());
        actionListService.updateById(action);
        SprintContext.set(action);
        SprintEnvStatusEnums envStatusEnums = SprintEnvStatusEnums.getByEnv(sprint.getSprintEnvStatus());
        return actionListService.activatedNextSprintAction(action.getSprintId(),envStatusEnums,action.getId());
    }

    @Autowired
    public void setGitApiServiceMap(List<GitApiService> gitApiServiceList){
        gitApiServiceMap = gitApiServiceList.stream()
                .collect(Collectors.toMap(GitApiService::getStorageType, Function.identity()));
    }

    protected void execError(TSprintActionListEntity sprintAction,String errorMsg){
        sprintAction.setActionStatus(ActionStatusEnums.failed.name());
        sprintAction.setExeResult(errorMsg);
        actionListService.updateById(sprintAction);
        TSprint sprint = SprintContext.get().getSprint();
        sprint.setHasError(TypeConstants.SprintExecStatus.execError);
        sprintService.updateById(sprint);
        SprintContext.set(sprint,sprintAction);
    }

    protected String mainBranch(){
        if (StringUtils.isBlank(mainBranchName)){
            mainBranchName = configService.getMainBranchName();
        }
        return mainBranchName;
    }

    protected Long adminUserId(){
        if (assigneeId == null){
            assigneeId = configService.getAssigneeId();
        }
        return assigneeId;
    }
}
