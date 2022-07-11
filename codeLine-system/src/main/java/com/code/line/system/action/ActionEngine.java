package com.code.line.system.action;

import com.codeline.framwork.response.ApiResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: syl
 * @Date: 2022/7/12 01:18
 * @Description: sprint Action 执行引擎，定时启动，查询待执行的Action，并处理执行逻辑
 */
@Service
public class ActionEngine {


    /**
     * TSprintActionListEntity
     * 执行 Action
     */
    public ApiResult execute(Long sprintActionId){

        return ApiResult.success();
    }

}
