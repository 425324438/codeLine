package com.code.line.system.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.URLUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import com.codeline.framwork.request.search.ProjectSearch;
import com.codeline.framwork.response.ApiResult;
import com.codeline.framwork.response.ProjectVo;
import com.codeline.framwork.search.PageSearch;
import com.codeline.framwork.util.UrlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Date;
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
        query.eq(TProject::getGitGroup,group);
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
        if (!StringUtils.startsWith(projectBo.getGitUrl(),"http")) {
            return ApiResult.error("项目地址必须是http格式");
        }
        TProject tProject = JSON.parseObject(JSON.toJSONString(projectBo), TProject.class);
        String  gitGroup =  projectBo.getGitUrl().split(".com/")[1].split("/")[0];
        tProject.setGitGroup(gitGroup);
        tProject.setStatus(DbStatus.DEFAULT.getCode());
        tProject.setCreatedTime(LocalDateTime.now());
        boolean save = save(tProject);

        Long assigneeId = configService.getAssigneeId();
        String hookCallbackUrl = configService.getHookCallbackUrl();
        if (StringUtils.isEmpty(hookCallbackUrl)){
            return ApiResult.error("需要管理员配置 GitLab webHook 地址");
        }

        String domain = UrlUtils.getDomain(projectBo.getGitUrl());
        if (StringUtils.isBlank(domain)){
            return ApiResult.error("Git地址格式错误，没有获取到Git地址中的Domain");
        }
        GitStorageType storageType = GitStorageType.getByName(domain);
        try {
            gitApiServiceMap.get(storageType).addMember(tProject.getGitUrl(),assigneeId);
        } catch (SysException e) {
            log.error("项目添加管理员异常，e={}",e);
        }
        try {
            gitApiServiceMap.get(storageType).addHook(tProject.getGitUrl(),hookCallbackUrl);
        } catch (SysException e) {
            log.error("添加Hook失败，e={}",e);
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
        updateWrapper.eq(TProject::getGitGroup,updateProjectBo.getGroup());

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

    @Override
    public ApiResult<List<ProjectVo>> getProjectPage(PageSearch<ProjectSearch> projectSearch) {
        ProjectSearch search = projectSearch.getSearch();

        LambdaQueryWrapper<TProject> wrappers = getWrappers(search);
        IPage<TProject> page = new Page<>();
        page.setSize(projectSearch.getPageSize());
        page.setCurrent(projectSearch.getPageNum());
        page = page(page,wrappers);

        List<ProjectVo> projectVos = JSON.parseArray(JSON.toJSONString(page.getRecords()), ProjectVo.class);
        ApiResult<List<ProjectVo>> success = ApiResult.success(projectVos);
        success.setPageNum(page.getCurrent());
        success.setPageTotal(page.getTotal());
        success.setPageSize(page.getSize());
        return success;
    }

    private LambdaQueryWrapper<TProject> getWrappers(ProjectSearch search) {
        LambdaQueryWrapper<TProject> query = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(search.getGroup())){
            query.eq(TProject::getGitGroup, search.getGroup());
        }
        if (StringUtils.isNotBlank(search.getName())){
            query.eq(TProject::getName, search.getName());
        }
        if (StringUtils.isNotBlank(search.getLikeGitUrl())){
            query.like(TProject::getGitUrl, search.getLikeGitUrl());
        }
        query.eq(TProject::getStatus, DbStatus.DEFAULT.getCode());
        query.orderByDesc(TProject::getId);
        return query;
    }

}
