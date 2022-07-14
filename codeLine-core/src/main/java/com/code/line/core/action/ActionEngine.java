package com.code.line.core.action;

import com.code.line.core.action.bean.Action;
import com.code.line.system.constant.ActionBeanTypeName;
import com.code.line.system.constant.ActionTypeEnums;
import com.code.line.system.entity.TSprint;
import com.code.line.system.entity.TSprintActionListEntity;
import com.code.line.system.entity.TSprintProject;
import com.code.line.system.service.ITProjectService;
import com.code.line.system.service.ITSprintActionListService;
import com.code.line.system.service.ITSprintProjectService;
import com.code.line.system.service.ITSprintService;
import com.codeline.framwork.response.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: syl
 * @Date: 2022/7/12 01:18
 * @Description: sprint Action 执行引擎，定时启动，查询待执行的Action，并处理执行逻辑
 */
@Slf4j
@Component
public class ActionEngine {

    @Autowired
    protected ITSprintService sprintService;
    @Autowired
    protected ITProjectService projectService;
    @Autowired
    protected ITSprintActionListService actionListService;
    @Autowired
    protected ITSprintProjectService sprintProjectService;
    private Map<ActionBeanTypeName, Action> actionMap = new HashMap<>();

    /**
     * TSprintActionListEntity
     * 定时任务获取待执行的 Action 列表，然后逐个执行
     * 执行 Action
     */
    public ApiResult execute(Long sprintActionId){
        log.info("开始执行Action，id={}",sprintActionId);
        initSprintContext(sprintActionId);

        SprintContext sprintContext = SprintContext.get();
        TSprintActionListEntity action = sprintContext.getSprintAction();
        if (action == null){
            return ApiResult.error("sprintAction不存在");
        }
        //todo 后续版本中区分 ActionTypeEnums.inner 和 ActionTypeEnums.outer 的执行逻辑
        if (ActionTypeEnums.inner.name().equals(action.getType())){
            ActionBeanTypeName actionBeanType = ActionBeanTypeName.getByBeanCode(action.getActionBeanTypeName());
            if (actionBeanType == null){
                return ApiResult.error("Action不存在");
            }
            Action actionBean = actionMap.get(actionBeanType);
            actionBean.before();
            ApiResult<String> execute = actionBean.execute();
            if (execute.isSuccess()){
                actionBean.executeSuccessAfter();
            } else {
                actionBean.error(execute.getMsg());
            }
            actionBean.after();
        }
        SprintContext.remove();
        return  ApiResult.success();
    }

    /**
     * 初始化 SprintContext
     */
    private void initSprintContext(Long sprintActionId){
        TSprintActionListEntity action = actionListService.getById(sprintActionId);
        TSprint sprint = sprintService.getById(action.getSprintId());
        List<TSprintProject> sprintProjectList = sprintProjectService.getBySprintId(action.getSprintId());
        SprintContext.set(sprint,sprintProjectList,action);
    }

    @Autowired
    public void setActionMap(List<Action> actionList){
        actionMap = actionList.stream().collect(Collectors.toMap(Action::getActionBeanTypeName, Function.identity()));
    }
}
