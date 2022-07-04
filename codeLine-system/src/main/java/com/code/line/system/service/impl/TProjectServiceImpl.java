package com.code.line.system.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.code.line.system.constant.DbStatus;
import com.code.line.system.entity.TProject;
import com.code.line.system.mapper.TProjectMapper;
import com.code.line.system.service.ITProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codeline.framwork.request.ProjectBo;
import com.codeline.framwork.request.UpdateProjectBo;
import com.codeline.framwork.response.ResultApi;
import com.mysql.cj.xdevapi.DatabaseObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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



    @Override
    public List<TProject> getByGroup(String group) {
        LambdaQueryWrapper<TProject> query = Wrappers.lambdaQuery();
        query.eq(TProject::getGroup,group);
        query.eq(TProject::getStatus, DbStatus.DEFAULT.getCode());
        query.orderByDesc(TProject::getCreatedTime);
        return list(query);
    }

    @Override
    public ResultApi save(ProjectBo projectBo) {
        if (StringUtils.isEmpty(projectBo.getGitUrl())){
            return ResultApi.error("项目地址不能为空");
        }
        TProject tProject = JSON.parseObject(JSON.toJSONString(projectBo), TProject.class);
        boolean save = save(tProject);
        if (save){
            return ResultApi.success();
        }
        return ResultApi.error("新增失败，数据保存失败");
    }

    @Override
    public ResultApi editProject(UpdateProjectBo updateProjectBo) {
        if (updateProjectBo.getId() == null){
            return ResultApi.error("id不能为空");
        }
        LambdaUpdateWrapper<TProject> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(TProject::getId,updateProjectBo.getId());
        updateWrapper.set(TProject::getGitUrl,updateProjectBo.getGitUrl());
        updateWrapper.eq(TProject::getGroup,updateProjectBo.getGroup());

        boolean update = update(updateWrapper);
        if (update){
            return ResultApi.success();
        }
        return ResultApi.error("修改失败，数据保存失败");
    }
}
