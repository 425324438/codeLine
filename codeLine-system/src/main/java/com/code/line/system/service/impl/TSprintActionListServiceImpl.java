package com.code.line.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.code.line.system.constant.DbStatus;
import com.code.line.system.entity.TSprint;
import com.code.line.system.entity.TSprintActionListEntity;
import com.code.line.system.entity.TSprintTemplateActionListEntity;
import com.code.line.system.mapper.TSprintActionListMapper;
import com.code.line.system.service.ITSprintActionListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codeline.framwork.constant.ActionStatusEnums;
import com.codeline.framwork.constant.SprintEnvStatusEnums;
import com.codeline.framwork.response.ApiResult;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * <p>
 * Sprint的执行任务列表 服务实现类
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-07-11
 */
@Service
public class TSprintActionListServiceImpl extends ServiceImpl<TSprintActionListMapper, TSprintActionListEntity>
        implements ITSprintActionListService {

    @Override
    public ApiResult generatorActionList(List<TSprintTemplateActionListEntity> templateActionListEntityList, TSprint sprint) {
        List<TSprintActionListEntity> savebatch = new LinkedList<>();
        if (CollectionUtil.isEmpty(templateActionListEntityList)){
            return ApiResult.error("模版中的action列表不能为空");
        }
        for (TSprintTemplateActionListEntity templateActionEntity : templateActionListEntityList) {
            TSprintActionListEntity sprintActionList = new TSprintActionListEntity();
            sprintActionList.setSprintId(sprint.getId());
            sprintActionList.setActionStatus(ActionStatusEnums.NotStarted.name());
            sprintActionList.setActionBeanTypeName(templateActionEntity.getActionBeanTypeName());
            sprintActionList.setName(templateActionEntity.getName());
            sprintActionList.setDescription(templateActionEntity.getDescription());
            sprintActionList.setSortNo(templateActionEntity.getSortNo());
            sprintActionList.setCreatedTime(new Date());
            sprintActionList.setStatus(DbStatus.DEFAULT.getCode());
            savebatch.add(sprintActionList);
        }
        boolean b = saveBatch(savebatch);
        if (b){
            return ApiResult.success();
        }
        return ApiResult.error("ActionList插入数据失败");
    }

    /**
     * ActionStatusEnums
     * 设置Sprint action队列中能执行的action为 activated 状态，如果有，则不用任何操作，如果没有则拿 sortNo 最小的设置为 activated
     * @param sprintId
     * @param envStatusEnums
     */
    @Override
    public ApiResult settingStartedAction(Long sprintId, SprintEnvStatusEnums envStatusEnums){
        LambdaQueryWrapper<TSprintActionListEntity> query = Wrappers.lambdaQuery();
        query.eq(TSprintActionListEntity::getSprintId,sprintId);
        query.eq(TSprintActionListEntity::getSprintEnvStatus,envStatusEnums.name());
        query.eq(TSprintActionListEntity::getStatus,DbStatus.DEFAULT.getCode());
        query.orderByAsc(TSprintActionListEntity::getSortNo);
        List<TSprintActionListEntity> list = list(query);
        if (CollectionUtil.isEmpty(list)){
            return ApiResult.error("Sprint待执行Action列表为空");
        }
        Optional<TSprintActionListEntity> first = list.stream()
                .filter(s -> !ActionStatusEnums.NotStarted.name().equals(s.getActionStatus())).findFirst();
        if (first.isPresent()){
            return ApiResult.success("Sprint的Action列表中有执行数据，不需处理");
        }
        Optional<TSprintActionListEntity> notStarted = list.stream()
                .filter(s -> ActionStatusEnums.NotStarted.name().equals(s.getActionStatus())).findFirst();
        if (notStarted.isPresent()){
            TSprintActionListEntity sprintActionList = notStarted.get();
            sprintActionList.setActionStatus(ActionStatusEnums.activated.name());
            updateById(sprintActionList);
            return ApiResult.success();
        } else {
            return ApiResult.error("Sprint的Action列表中没有未开始的Action");
        }
    }


    @Override
    public List<TSprintActionListEntity> pollNextAction(Long sprintId,
            SprintEnvStatusEnums envStatusEnums) {

        LambdaQueryWrapper<TSprintActionListEntity> query = Wrappers.lambdaQuery();
        query.eq(TSprintActionListEntity::getSprintId,sprintId);
        query.eq(TSprintActionListEntity::getSprintEnvStatus,envStatusEnums.name());
        query.eq(TSprintActionListEntity::getStatus,DbStatus.DEFAULT.getCode());
        query.eq(TSprintActionListEntity::getActionStatus,ActionStatusEnums.activated.name());
        List<TSprintActionListEntity> list = list(query);

        List<TSprintActionListEntity> update = new ArrayList<>();
        for (TSprintActionListEntity sprintActionList : list) {
            sprintActionList.setActionStatus(ActionStatusEnums.executing.name());
            update.add(sprintActionList);
        }
        updateBatchById(update);
        return list;
    }
}
