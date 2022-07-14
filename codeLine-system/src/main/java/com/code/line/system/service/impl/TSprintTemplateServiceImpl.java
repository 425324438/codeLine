package com.code.line.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.code.line.system.constant.DbStatus;
import com.code.line.system.entity.TSprint;
import com.code.line.system.entity.TSprintTemplate;
import com.code.line.system.entity.TSprintTemplateActionListEntity;
import com.code.line.system.mapper.TSprintTemplateMapper;
import com.code.line.system.service.ITSprintActionListService;
import com.code.line.system.service.ITSprintTemplateActionListService;
import com.code.line.system.service.ITSprintTemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codeline.framwork.constant.SprintEnvStatusEnums;
import com.codeline.framwork.constant.SprintTypeEnums;
import com.codeline.framwork.response.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 迭代模版 服务实现类
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-06-28
 */
@Slf4j
@Service
public class TSprintTemplateServiceImpl extends ServiceImpl<TSprintTemplateMapper, TSprintTemplate> implements
        ITSprintTemplateService {

    //Sprint模版中配置好的 actionList
    @Autowired
    private ITSprintTemplateActionListService sprintTemplateActionListService;
    //sprint Action待执行的集合
    @Autowired
    private ITSprintActionListService sprintActionListService;

    @Override
    public ApiResult generatorNextSprintActionList(TSprint sprint) {
        TSprintTemplate sprintTemplate = getById(sprint.getSprintTemplateId());
        if (sprintTemplate == null){
            return ApiResult.error("迭代模版没有查询到");
        }
        for (SprintEnvStatusEnums value : SprintEnvStatusEnums.values()) {
            List<TSprintTemplateActionListEntity> templateActionList = sprintTemplateActionListService.getByTemplateIdAndEnvStatus(
                    sprintTemplate.getId(), value.name());
            if (CollectionUtil.isEmpty(templateActionList)){
                return ApiResult.error("Action列表不能为空");
            }
            sprintActionListService.generatorActionList(templateActionList,sprint);
        }
        sprintActionListService.activatedSprintAction(sprint.getId(),SprintEnvStatusEnums.DEV);
        return ApiResult.success();
    }

    @Override
    public TSprintTemplate getByType(SprintTypeEnums sprintType) {
        LambdaQueryWrapper<TSprintTemplate> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(TSprintTemplate::getType,sprintType.name());
        queryWrapper.eq(TSprintTemplate::getStatus, DbStatus.DEFAULT.getCode());
        return getOne(queryWrapper);
    }
}
