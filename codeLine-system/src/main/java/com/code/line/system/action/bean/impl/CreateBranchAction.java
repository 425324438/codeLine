package com.code.line.system.action.bean.impl;

import com.code.line.system.action.bean.Action;
import com.code.line.system.action.bean.BaseAction;
import com.code.line.system.constant.ActionBeanTypeName;
import com.code.line.system.entity.TSprintActionListEntity;
import com.codeline.framwork.response.ApiResult;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * @author: syl
 * @Date: 2022/7/12 01:33
 * @Description: 创建分支
 */
@Service
public class CreateBranchAction  extends BaseAction implements Action {


    @Override
    public ActionBeanTypeName getActionBeanTypeName() {
        return ActionBeanTypeName.CreateBranch;
    }

    @Override
    public ApiResult before(TSprintActionListEntity action) {
        return ApiResult.success();
    }

    @Override
    public ApiResult execute(TSprintActionListEntity action) {

        return null;
    }

    @Override
    public ApiResult after(TSprintActionListEntity action) {
        return ApiResult.success();
    }

    @Override
    public ApiResult executeSuccessAfter(TSprintActionListEntity action) {
        return super.exeSuccessAfter(action);
    }
}
