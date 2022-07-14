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
import com.codeline.framwork.dto.TagDto;
import com.codeline.framwork.exception.SysException;
import com.codeline.framwork.response.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: syl
 * @Date: 2022/7/14 01:47
 * @Description: 创建Tag
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
                TagDto tag = gitApiServiceMap.get(storageType).createTag(
                        sprintProject.getGitUrl(),
                        sprint.getVersion()+"_tag",
                        mainBranch());

                JSONObject paramJson = sprintProject.getParamJson();
                if (paramJson == null){
                    paramJson = new JSONObject();
                }
                paramJson.put(TypeConstants.SprintActionParamJsonKey.TagName,tag.getName());
                sprintProject.setWebUrl(tag.getWebUrl());
                sprintProject.setParamJson(paramJson);
                sprintProjectService.updateById(sprintProject);
            } catch (SysException e) {
                log.error("Tag创建失败：gitUrl={},e={}",sprintProject.getGitUrl(),e);
                sprintProject.setWebUrl("Tag创建失败,"+e.getMessage());
                sprintProjectService.updateById(sprintProject);
                return ApiResult.error("project："+sprintProject.getName()+"，Tag创建失败,"+e.getMessage());
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
