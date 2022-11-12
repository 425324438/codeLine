package com.code.line.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.code.line.system.constant.DbStatus;
import com.code.line.system.entity.TProject;
import com.code.line.system.entity.TSprint;
import com.code.line.system.entity.TSprintProject;
import com.code.line.system.entity.TSprintTemplate;
import com.code.line.system.mapper.TSprintMapper;
import com.code.line.system.service.ITProjectService;
import com.code.line.system.service.ITSprintProjectService;
import com.code.line.system.service.ITSprintService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.line.system.service.ITSprintTemplateService;
import com.codeline.framwork.constant.SprintEnvStatusEnums;
import com.codeline.framwork.request.CreateSprintBo;
import com.codeline.framwork.request.search.SprintSearch;
import com.codeline.framwork.response.ApiResult;
import com.codeline.framwork.response.SprintProjectVo;
import com.codeline.framwork.response.SprintVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 迭代列表 服务实现类
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-06-28
 */
@Service
public class TSprintServiceImpl extends ServiceImpl<TSprintMapper, TSprint> implements ITSprintService {

    @Autowired
    private ITProjectService projectService;
    @Autowired
    private ITSprintProjectService sprintProjectService;
    @Autowired
    private ITSprintTemplateService sprintTempletService;



    @Override
    @Transactional
    public ApiResult create(CreateSprintBo createSprintBo) {
        String format = DateUtil.format(new Date(), "yyyyMMdd");
        TSprintTemplate sprintTemplet = sprintTempletService.getByType(createSprintBo.getSprintType());
        if (sprintTemplet == null){
            return ApiResult.error("没有查询到可用的Sprint模版");
        }
        //校验 version 字段是否合法，要比大于历史创建的 version
        TSprint sprint = new TSprint();
        sprint.setName(createSprintBo.getName());
        sprint.setSprintType(createSprintBo.getSprintType().name());
        sprint.setSprintTemplateId(sprintTemplet.getId());
        sprint.setSprintEnvStatus(SprintEnvStatusEnums.DEV.name());
        sprint.setVersion(SprintEnvStatusEnums.DEV.name()+"_"+createSprintBo.getVersion() +"_"+format);
        sprint.setCreator("admin");
        sprint.setCreatorId(1l);
        sprint.setCreatedTime(LocalDateTime.now());
        sprint.setStatus(DbStatus.DEFAULT.getCode());
        boolean save = save(sprint);
        if (!save){
            return ApiResult.error("迭代创建失败");
        }
        //Sprint 中新增项目
        List<TProject> tProjectList = projectService.listByIds(createSprintBo.getProjectIds());
        if (CollectionUtil.isNotEmpty(tProjectList)){
            List<TSprintProject> sprintProjectList = new ArrayList<>();
            for (TProject project : tProjectList) {
                TSprintProject sprintProject = new TSprintProject();
                sprintProject.setSprintId(sprint.getId());
                sprintProject.setProjectId(project.getId());
                sprintProject.setGitUrl(project.getGitUrl());
                sprintProject.setName(project.getName());
                sprintProject.setCreator("admin");
                sprintProject.setCreatorId(1l);
                sprintProject.setCreatedTime(LocalDateTime.now());
                sprintProject.setStatus(DbStatus.DEFAULT.getCode());

                sprintProjectList.add(sprintProject);
            }
            sprintProjectService.saveBatch(sprintProjectList);
        }
        sprintTempletService.generatorNextSprintActionList(sprint);
        return ApiResult.success();
    }

    @Override
    public ApiResult<SprintVo> getSprintDetail(Long id) {
        TSprint sprint = getById(id);
        if (sprint == null){
            return ApiResult.error("Sprint不存在");
        }
        SprintVo sprintVo = JSON.parseObject(JSON.toJSONString(sprint),SprintVo.class);
        List<TSprintProject> sprintProjectList = sprintProjectService.getBySprintId(id);
        if (!CollectionUtils.isEmpty(sprintProjectList)){
            sprintVo.setProjects(JSON.parseObject(JSON.toJSONString(sprintProjectList),new TypeReference<List<SprintProjectVo>>(){}));
        }
        return ApiResult.success(sprintVo,"成功");
    }

    @Override
    public ApiResult<List<TSprint>> getSprintListPage(SprintSearch sprintSearch){
        Page<TSprint> page = new Page<>();
        page.setCurrent(1);
        page = page(page,queryWrappers(sprintSearch));

        ApiResult<List<TSprint>> success = ApiResult.success(page.getRecords());
        success.setPageNum(page.getCurrent());
        success.setPageTotal(page.getTotal());
        success.setPageSize(page.getSize());
        return success;

    }
    private LambdaQueryWrapper<TSprint> queryWrappers(SprintSearch sprintSearch){
        LambdaQueryWrapper<TSprint> query = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(sprintSearch.getLikeName())){
            query.like(TSprint::getName,sprintSearch.getLikeName());
        }
        if (StringUtils.isNotBlank(sprintSearch.getLikeVersion())){
            query.like(TSprint::getVersion,sprintSearch.getLikeVersion());
        }

        query.eq(TSprint::getStatus,DbStatus.DEFAULT.getCode());
        query.orderByDesc(TSprint::getCreatedTime);
        return query;
    }
}
