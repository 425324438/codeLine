package com.code.line.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.code.line.system.constant.DbStatus;
import com.code.line.system.entity.TProject;
import com.code.line.system.mapper.TProjectMapper;
import com.code.line.system.service.ITProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.xdevapi.DatabaseObject;
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
}
