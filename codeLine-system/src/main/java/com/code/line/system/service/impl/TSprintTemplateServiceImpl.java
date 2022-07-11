package com.code.line.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.code.line.system.constant.DbStatus;
import com.code.line.system.entity.TSprintTemplate;
import com.code.line.system.mapper.TSprintTemplateMapper;
import com.code.line.system.service.ITSprintTemplateService;
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
public class TSprintTemplateServiceImpl extends ServiceImpl<TSprintTemplateMapper, TSprintTemplate> implements
        ITSprintTemplateService {

    @Override
    public TSprintTemplate getByType(SprintTypeEnums sprintType) {
        LambdaQueryWrapper<TSprintTemplate> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(TSprintTemplate::getType,sprintType.name());
        queryWrapper.eq(TSprintTemplate::getStatus, DbStatus.DEFAULT.getCode());
        return getOne(queryWrapper);
    }
}
