package com.code.line.system.service;

import com.code.line.system.entity.TSprintTemplet;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codeline.framwork.constant.SprintTypeEnums;

/**
 * <p>
 * 迭代模版 服务类
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-06-28
 */
public interface ITSprintTempletService extends IService<TSprintTemplet> {

    TSprintTemplet getByType(SprintTypeEnums sprintType);

}
