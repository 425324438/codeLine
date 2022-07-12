package com.code.line.system.service;

import com.code.line.system.entity.TSprint;
import com.code.line.system.entity.TSprintActionListEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.code.line.system.entity.TSprintTemplateActionListEntity;
import com.codeline.framwork.constant.SprintEnvStatusEnums;
import com.codeline.framwork.response.ApiResult;

import java.util.List;

/**
 * <p>
 * Sprint的执行任务列表 服务类
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-07-11
 */
public interface ITSprintActionListService extends IService<TSprintActionListEntity> {

    /**
     * 根据模版数据生成Sprint 待执行数据
     * @param templateActionListEntityList
     * @param sprint
     */
    ApiResult generatorActionList(List<TSprintTemplateActionListEntity> templateActionListEntityList, TSprint sprint);


    /**
     * 激活Sprint待执行的Action列表，ActionStatusEnums
     * 设置Sprint action队列中能执行的action为 activated 状态，如果有，则不用任何操作，如果没有则拿 sortNo 最小的设置为 activated
     * @param SprintId
     * @param envStatusEnums
     */
    ApiResult activatedSprintAction(Long SprintId, SprintEnvStatusEnums envStatusEnums);

    /**
     * 当前action执行结束后，激活下一个action。如果当前状态的action列表全部执行完毕，则推动Sprint进入下个环节
     * @param SprintId SprintId
     * @param envStatusEnums sprint当前的状态
     * @param sprintActionId sprint当前执行完成的action，基于此激活下一个action
     * @return
     */
    ApiResult activatedNextSprintAction(Long SprintId, SprintEnvStatusEnums envStatusEnums,Long sprintActionId);

    /**
     * 查询Sprint需要执行的Action
     * @param sprintId
     * @param envStatusEnums
     * @return
     */
    List<TSprintActionListEntity> pollNextAction(Long sprintId, SprintEnvStatusEnums envStatusEnums);
}
