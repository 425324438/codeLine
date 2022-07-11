package com.code.line.system.service;

import com.code.line.system.entity.TSprint;
import com.code.line.system.entity.TSprintTemplate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codeline.framwork.constant.SprintTypeEnums;
import com.codeline.framwork.response.ApiResult;

/**
 * <p>
 * 迭代模版 服务类
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-06-28
 */
public interface ITSprintTemplateService extends IService<TSprintTemplate> {


    /**
     * 创建Sprint所有需要执行的actionList（目标数据表 t_sprint_action_list）
     */
    ApiResult generatorNextSprintActionList(TSprint sprint);


    TSprintTemplate getByType(SprintTypeEnums sprintType);

}
