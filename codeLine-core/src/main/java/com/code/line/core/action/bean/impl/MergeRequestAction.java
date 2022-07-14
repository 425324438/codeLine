package com.code.line.core.action.bean.impl;

import com.alibaba.fastjson.JSONObject;
import com.code.line.core.action.SprintContext;
import com.code.line.core.action.bean.Action;
import com.code.line.core.action.bean.BaseAction;
import com.code.line.system.constant.ActionBeanTypeName;
import com.code.line.system.entity.TProject;
import com.code.line.system.entity.TSprint;
import com.code.line.system.entity.TSprintActionListEntity;
import com.code.line.system.entity.TSprintProject;
import com.codeline.framwork.constant.GitStorageType;
import com.codeline.framwork.constant.TypeConstants;
import com.codeline.framwork.dto.MergeRequestDto;
import com.codeline.framwork.exception.SysException;
import com.codeline.framwork.response.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: syl
 * @Date: 2022/7/13 23:48
 * @Description: 创建合并请求
 */
@Slf4j
@Component
public class MergeRequestAction extends BaseAction implements Action {


    @Override
    public ActionBeanTypeName getActionBeanTypeName() {
        return ActionBeanTypeName.CreateMerge;
    }

    @Override
    public ApiResult before() {
        return ApiResult.success();
    }

    @Override
    public ApiResult<String> execute() {
        TSprint sprint = SprintContext.get().getSprint();
        List<TSprintProject> sprintProjectList = SprintContext.get().getSprintProjectList();

        for (TSprintProject sprintProject : sprintProjectList) {
            TProject project = projectService.getById(sprintProject.getProjectId());
            GitStorageType storageType = GitStorageType.getByName(project.getGitStorageType());
            try {
                MergeRequestDto main = gitApiServiceMap.get(storageType)
                        .createMerge(sprintProject.getGitUrl(),
                                sprintProject.getBranch(),
                                mainBranch(),
                                sprint.getVersion() + " -> "+ mainBranch() + " __" + project.getName(),
                                sprint.getName() + " __ " + sprintProject.getName() + " version:" + sprint.getVersion(),
                                assigneeId());

                sprintProject.setWebUrl(main.getWebUrl());
                JSONObject paramJson = sprintProject.getParamJson();
                if (paramJson == null){
                    paramJson = new JSONObject();
                }
                paramJson.put(TypeConstants.SprintActionParamJsonKey.MergeIid,main.getIid());
                sprintProject.setParamJson(paramJson);
                sprintProjectService.updateById(sprintProject);
            } catch (SysException e) {
                log.error("Merge创建失败：gitUrl={},e={}",sprintProject.getGitUrl(),e);
                sprintProject.setWebUrl("Merge创建失败,"+e.getMessage());
                sprintProjectService.updateById(sprintProject);
                return ApiResult.error("project："+sprintProject.getName()+"，Merge创建失败,"+e.getMessage());
            }
        }
        return ApiResult.success();
    }

    @Override
    public ApiResult after() {
        return ApiResult.success();
    }

    @Override
    public ApiResult executeSuccessAfter() {
        return super.exeSuccessAfter();
    }

    @Override
    public ApiResult error(String errorMsg) {
        SprintContext sprintContext = SprintContext.get();
        TSprintActionListEntity sprintAction = sprintContext.getSprintAction();
        execError(sprintAction,errorMsg);
        return ApiResult.success();
    }

}
