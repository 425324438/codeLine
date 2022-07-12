package com.code.line.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.code.line.system.constant.DbStatus;
import com.code.line.system.entity.TSprintProject;
import com.code.line.system.mapper.TSprintProjectMapper;
import com.code.line.system.service.ITSprintProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codeline.framwork.request.search.SprintProjectSearch;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 迭代内的项目列表 服务实现类
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-06-28
 */
@Service
public class TSprintProjectServiceImpl extends ServiceImpl<TSprintProjectMapper, TSprintProject> implements ITSprintProjectService {

    @Override
    public List<TSprintProject> getBySprintId(Long sprintId) {
        if (sprintId == null){
            throw new NullPointerException("sprintId 不能为空");
        }
        LambdaQueryWrapper<TSprintProject> wrapper = getWrapper(SprintProjectSearch.builder().sprintId(sprintId).build());
        return list(wrapper);
    }

    private LambdaQueryWrapper<TSprintProject> getWrapper(SprintProjectSearch search){
        LambdaQueryWrapper<TSprintProject> query = Wrappers.lambdaQuery();
        if (search.getSprintId() != null){
            query.eq(TSprintProject::getSprintId, search.getSprintId());
        }
        query.eq(TSprintProject::getStatus, DbStatus.DEFAULT.getCode());
        return query;
    }
}
