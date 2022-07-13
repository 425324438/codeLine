package com.code.line.system.action.bean.impl;

import com.code.line.system.action.bean.Action;
import com.code.line.system.action.bean.BaseAction;
import com.code.line.system.constant.ActionBeanTypeName;
import com.codeline.framwork.response.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: syl
 * @Date: 2022/7/14 01:47
 * @Description:
 */
@Slf4j
@Component
public class CreateTagAction extends BaseAction implements Action {
    @Override
    public ActionBeanTypeName getActionBeanTypeName() {
        return ActionBeanTypeName.CreateTag;
    }

    @Override
    public ApiResult before() {
        return null;
    }

    @Override
    public ApiResult<String> execute() {
        return null;
    }

    @Override
    public ApiResult after() {
        return null;
    }

    @Override
    public ApiResult executeSuccessAfter() {
        return null;
    }

    @Override
    public ApiResult error(String errorMsg) {
        return null;
    }
}
