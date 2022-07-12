package com.code.line.system.action;

import com.code.line.system.action.bean.Action;
import com.code.line.system.constant.ActionBeanTypeName;
import com.code.line.system.constant.ActionTypeEnums;
import com.code.line.system.entity.TSprintActionListEntity;
import com.code.line.system.service.ITSprintActionListService;
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
 * @Date: 2022/7/12 01:18
 * @Description: sprint Action 执行引擎，定时启动，查询待执行的Action，并处理执行逻辑
 */
@Service
public class ActionEngine {

    @Autowired
    protected ITSprintActionListService actionListService;
    @Autowired
    private List<Action> actionList;
    private Map<ActionBeanTypeName,Action> actionMap = new HashMap<>();

    /**
     * TSprintActionListEntity
     * 定时任务获取待执行的Action列表，然后逐个执行
     * 执行 Action
     */
    public ApiResult execute(Long sprintActionId){
        //区分 ActionTypeEnums.inner 和 ActionTypeEnums.outer 的执行逻辑
        TSprintActionListEntity action = actionListService.getById(sprintActionId);
        if (action == null){
            return ApiResult.error("sprintAction不存在");
        }
        if (ActionTypeEnums.inner.name().equals(action.getType())){
            ActionBeanTypeName actionBeanType = ActionBeanTypeName.getByBeanCode(action.getActionBeanTypeName());
            if (actionBeanType == null){
                return ApiResult.error("Action不存在");
            }
            actionMap.get(actionBeanType).before(action);
            ApiResult execute = actionMap.get(actionBeanType).execute(action);
            if (execute.isSuccess()){
                actionMap.get(actionBeanType).executeSuccessAfter(action);
            }
            actionMap.get(actionBeanType).after(action);
        }

        return  ApiResult.success();
    }


    @Autowired
    public void setActionMap(List<Action> actionList){
        actionMap = actionList.stream().collect(Collectors.toMap(Action::getActionBeanTypeName, Function.identity()));
    }
}
