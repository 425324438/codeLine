package com.code.line.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
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
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
    public ApiResult save(List<TSprintTemplateActionListEntity> templateActionListEntityList, TSprint sprint) {
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
        SprintEnvStatusEnums envStatusEnums = SprintEnvStatusEnums.getByEnv(sprint.getSprintEnvStatus());
        settingStartedAction(sprint.getId(),envStatusEnums);

        boolean b = saveBatch(savebatch);
        if (b){
            return ApiResult.success();
        }
        return ApiResult.error("插入数据失败");
    }

    /**
     * ActionStatusEnums
     * 设置Sprint action队列中能执行的action为 activated 状态，如果有，则不用任何操作，如果没有则拿 sortNo 最小的设置为 activated
     * @param SprintId
     * @param envStatusEnums
     */
    @Override
    public ApiResult settingStartedAction(Long SprintId, SprintEnvStatusEnums envStatusEnums){

    }
}
