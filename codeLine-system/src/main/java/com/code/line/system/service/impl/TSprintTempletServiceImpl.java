package com.code.line.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.code.line.system.constant.DbStatus;
import com.code.line.system.entity.TSprintTemplet;
import com.code.line.system.mapper.TSprintTempletMapper;
import com.code.line.system.service.ITSprintTempletService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codeline.framwork.constant.SprintTypeEnums;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 迭代模版 服务实现类
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-06-28
 */
@Service
public class TSprintTempletServiceImpl extends ServiceImpl<TSprintTempletMapper, TSprintTemplet> implements ITSprintTempletService {

    @Override
    public TSprintTemplet getByType(SprintTypeEnums sprintType) {
        LambdaQueryWrapper<TSprintTemplet> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(TSprintTemplet::getType,sprintType.name());
        queryWrapper.eq(TSprintTemplet::getStatus, DbStatus.DEFAULT.getCode());
        return getOne(queryWrapper);
    }
}
