package com.code.line.system.action.bean;

import com.code.line.system.constant.ActionBeanTypeName;
import com.codeline.framwork.response.ApiResult;

/**
 * @author: syl
 * @Date: 2022/7/12 01:24
 * @Description:
 */
public interface Action {

    ActionBeanTypeName getActionBeanTypeName();

    /**
     * 执行前
     */
    ApiResult before(Long sprintActionId);

    /**
     * 任务开始执行
     */
    ApiResult execute(Long sprintActionId);

    /**
     * 执行后
     */
    ApiResult after(Long sprintActionId);

}
