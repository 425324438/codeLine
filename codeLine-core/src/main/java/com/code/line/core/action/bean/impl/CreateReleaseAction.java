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
import com.codeline.framwork.dto.ReleaseDto;
import com.codeline.framwork.exception.SysException;
import com.codeline.framwork.response.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: syl
 * @Date: 2022/7/14 01:48
 * @Description:
 */
@Slf4j
@Component
public class CreateReleaseAction extends BaseAction implements Action {


    @Override
    public ActionBeanTypeName getActionBeanTypeName() {
        return ActionBeanTypeName.CreateRelease;
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

            JSONObject paramJson = sprintProject.getParamJson();
            String tagName = paramJson.getString(TypeConstants.SprintActionParamJsonKey.TagName);
            try {
                ReleaseDto release = gitApiServiceMap.get(storageType)
                        .createRelease(sprintProject.getGitUrl(),
                                tagName,
                                sprint.getVersion() + "_Release",
                                sprint.getName());
                sprintProject.setWebUrl(release.getName());
                sprintProjectService.updateById(sprintProject);
            } catch (SysException e) {
                log.error("Release创建失败：gitUrl={},e={}",sprintProject.getGitUrl(),e);
                sprintProject.setWebUrl("Release创建失败,"+e.getMessage());
                sprintProjectService.updateById(sprintProject);
                return ApiResult.error("project："+sprintProject.getName()+"，Release创建失败,"+e.getMessage());
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
