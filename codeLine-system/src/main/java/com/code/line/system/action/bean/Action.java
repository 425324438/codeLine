package com.code.line.system.action.bean;

import com.code.line.system.constant.ActionBeanTypeName;
import com.code.line.system.entity.TSprintActionListEntity;
import com.codeline.framwork.response.ApiResult;
import io.swagger.annotations.Api;

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
    ApiResult before();

    /**
     * 任务开始执行
     */
    ApiResult<String> execute();

    /**
     * 执行后
     */
    ApiResult after();

    /**
     * 执行成功后，唤醒下一个待激活的Action，
     * 如果当前Sprint状态下的Action全部执行完毕，则Sprint进入下一个状态
     */
    ApiResult executeSuccessAfter();

    /**
     * 执行异常
     * @return
     */
    ApiResult error(String errorMsg);

}
