package com.code.line.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.code.line.system.constant.DbStatus;
import com.code.line.system.entity.TSprint;
import com.code.line.system.entity.TSprintTemplateActionListEntity;
import com.code.line.system.mapper.TSprintTemplateActionListMapper;
import com.code.line.system.service.ITSprintTemplateActionListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * Sprint模版action执行列表 服务实现类
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-07-11
 */
@Service
public class TSprintTemplateActionListServiceImpl extends ServiceImpl<TSprintTemplateActionListMapper, TSprintTemplateActionListEntity>
        implements ITSprintTemplateActionListService {


    @Override
    public List<TSprintTemplateActionListEntity> getByTemplateIdAndEnvStatus(Long SprintTemplateId,
            String SprintEnvStatus) {
        LambdaQueryWrapper<TSprintTemplateActionListEntity> query = Wrappers.lambdaQuery();
        query.eq(TSprintTemplateActionListEntity::getSprintTemplateId,SprintTemplateId);
        query.eq(TSprintTemplateActionListEntity::getSprintEnvStatus,SprintEnvStatus);
        query.eq(TSprintTemplateActionListEntity::getStatus, DbStatus.DEFAULT.getCode());
        query.orderByAsc(TSprintTemplateActionListEntity::getSortNo);
        return list(query);
    }
}
