package com.code.line.system.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.code.line.system.constant.DbStatus;
import com.code.line.system.entity.TProject;
import com.code.line.system.mapper.TProjectMapper;
import com.code.line.system.service.GitApiService;
import com.code.line.system.service.ISysConfigService;
import com.code.line.system.service.ITProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codeline.framwork.constant.GitStorageType;
import com.codeline.framwork.exception.SysException;
import com.codeline.framwork.request.ProjectBo;
import com.codeline.framwork.request.UpdateProjectBo;
import com.codeline.framwork.response.ApiResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 项目列表 服务实现类
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-06-30
 */
@Service
public class TProjectServiceImpl extends ServiceImpl<TProjectMapper, TProject> implements ITProjectService {

    @Autowired
    private ISysConfigService configService;
    protected Map<GitStorageType, GitApiService> gitApiServiceMap = new HashMap<>();

    @Autowired
    public void setGitApiServiceMap(List<GitApiService> gitApiServiceList){
        gitApiServiceMap = gitApiServiceList.stream()
                .collect(Collectors.toMap(GitApiService::getStorageType, Function.identity()));
    }

    @Override
    public List<TProject> getByGroup(String group) {
        LambdaQueryWrapper<TProject> query = Wrappers.lambdaQuery();
        query.eq(TProject::getGroup,group);
        query.eq(TProject::getStatus, DbStatus.DEFAULT.getCode());
        query.orderByDesc(TProject::getCreatedTime);
        return list(query);
    }

    @Override
    @Transactional()
    public ApiResult save(ProjectBo projectBo) throws SysException {
        if (StringUtils.isEmpty(projectBo.getGitUrl())){
            return ApiResult.error("项目地址不能为空");
        }
        TProject tProject = JSON.parseObject(JSON.toJSONString(projectBo), TProject.class);
        boolean save = save(tProject);

        Long assigneeId = configService.getAssigneeId();
        String hookCallbackUrl = configService.getHookCallbackUrl();
        if (StringUtils.isEmpty(hookCallbackUrl)){
            return ApiResult.error("需要管理员配置 GitLab webHook 地址");
        }
        GitStorageType storageType = GitStorageType.getByName(tProject.getGitStorageType());
        try {
            gitApiServiceMap.get(storageType).addMember(tProject.getGitUrl(),assigneeId);
            gitApiServiceMap.get(storageType).addHook(tProject.getGitUrl(),hookCallbackUrl);
        } catch (SysException e) {
            log.error("项目添加管理员异常，e={}",e);
            throw e;
        }

        if (save){
            return ApiResult.success();
        }
        return ApiResult.error("新增失败，数据保存失败");
    }

    @Override
    public ApiResult editProject(UpdateProjectBo updateProjectBo) {
        if (updateProjectBo.getId() == null){
            return ApiResult.error("id不能为空");
        }
        LambdaUpdateWrapper<TProject> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(TProject::getId,updateProjectBo.getId());
        updateWrapper.set(TProject::getGitUrl,updateProjectBo.getGitUrl());
        updateWrapper.eq(TProject::getGroup,updateProjectBo.getGroup());

        boolean update = update(updateWrapper);
        if (update){
            return ApiResult.success();
        }
        return ApiResult.error("修改失败，数据保存失败");
    }

    @Override
    public ApiResult delProject(Long id) {
        if (id == null){
            return ApiResult.error("id不能为空");
        }
        LambdaUpdateWrapper<TProject> delWrapper = Wrappers.lambdaUpdate();
        delWrapper.eq(TProject::getId, id);
        delWrapper.set(TProject::getStatus,DbStatus.DELETE.getCode());

        boolean update = update(delWrapper);
        if (update){
            return ApiResult.success();
        }
        return ApiResult.error("删除失败，数据保存失败");
    }

}
