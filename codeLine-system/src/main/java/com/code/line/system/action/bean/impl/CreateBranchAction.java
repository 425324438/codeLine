package com.code.line.system.action.bean.impl;

import ch.qos.logback.classic.Logger;
import com.code.line.system.action.bean.Action;
import com.code.line.system.action.bean.BaseAction;
import com.code.line.system.constant.ActionBeanTypeName;
import com.code.line.system.entity.TProject;
import com.code.line.system.entity.TSprint;
import com.code.line.system.entity.TSprintActionListEntity;
import com.code.line.system.entity.TSprintProject;
import com.code.line.system.service.GitApiService;
import com.codeline.framwork.constant.GitStorageType;
import com.codeline.framwork.dto.BranchDto;
import com.codeline.framwork.exception.SysException;
import com.codeline.framwork.response.ApiResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: syl
 * @Date: 2022/7/12 01:33
 * @Description: 创建分支
 */
@Slf4j
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
        TSprint sprint = sprintService.getById(action.getSprintId());
        List<TSprintProject> sprintProjectList = sprintProjectService.getBySprintId(action.getSprintId());
        for (TSprintProject sprintProject : sprintProjectList) {
            TProject project = projectService.getById(sprintProject.getProjectId());
            GitStorageType storageType = GitStorageType.getByName(project.getGitStorageType());
            try {
                BranchDto main = gitApiServiceMap.get(storageType).createBranch(sprintProject.getGitUrl(), sprint.getVersion(), "main");
                sprintProject.setBranch(main.getName());
                sprintProjectService.updateById(sprintProject);
            } catch (SysException e) {
                log.error("分支创建失败：gitUrl={},e={}",sprintProject.getGitUrl(),e);
                return ApiResult.error("分支创建失败："+e.getMessage());
            }
        }
        return ApiResult.success();
    }

    @Override
    public ApiResult after(TSprintActionListEntity action) {
        return ApiResult.success();
    }

    @Override
    public ApiResult executeSuccessAfter(TSprintActionListEntity action) {
        return super.exeSuccessAfter(action);
    }

    @Override
    public ApiResult error(TSprintActionListEntity action) {
        return null;
    }
}
