package com.code.line.core.action.bean.impl;

import com.alibaba.fastjson.JSONObject;
import com.code.line.core.action.SprintContext;
import com.code.line.core.action.bean.Action;
import com.code.line.core.action.bean.BaseAction;
import com.code.line.system.constant.ActionBeanTypeName;
import com.code.line.system.entity.TProject;
import com.code.line.system.entity.TSprintActionListEntity;
import com.code.line.system.entity.TSprintProject;
import com.codeline.framwork.constant.GitStorageType;
import com.codeline.framwork.constant.TypeConstants;
import com.codeline.framwork.dto.MergeRequestDto;
import com.codeline.framwork.exception.SysException;
import com.codeline.framwork.response.ApiResult;
import com.codeline.framwork.util.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: syl
 * @Date: 2022/7/14 01:47
 * @Description:
 */
@Slf4j
@Component
public class AcceptMergeAction extends BaseAction implements Action {


    @Override
    public ActionBeanTypeName getActionBeanTypeName() {
        return ActionBeanTypeName.AcceptMerge;
    }

    @Override
    public ApiResult before() {
        return ApiResult.success();
    }

    @Override
    public ApiResult<String> execute() {
        List<TSprintProject> sprintProjectList = SprintContext.get().getSprintProjectList();
        for (TSprintProject sprintProject : sprintProjectList) {
            TProject project = projectService.getById(sprintProject.getProjectId());
            String domain = UrlUtils.getDomain(project.getGitUrl());
            if (StringUtils.isBlank(domain)){
                return ApiResult.error("Git地址格式错误，没有获取到Git地址中的Domain");
            }
            GitStorageType storageType = GitStorageType.getByName(domain);
            JSONObject paramJson = sprintProject.getParamJson();
            Long iid = paramJson.getLong(TypeConstants.SprintActionParamJsonKey.MergeIid);
            try {
                MergeRequestDto mergeRequestDto = gitApiServiceMap.get(storageType)
                        .acceptMergeRequest(sprintProject.getGitUrl(), iid);
            } catch (SysException e) {
                log.error("Merge自动合并失败：gitUrl={},e={}",sprintProject.getGitUrl(),e);
                sprintProject.setWebUrl("Merge自动合并失败,"+e.getMessage());
                sprintProjectService.updateById(sprintProject);
                return ApiResult.error("project："+sprintProject.getName()+"，Merge自动合并失败,"+e.getMessage());
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
