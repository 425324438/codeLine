package com.code.line.system.service;

import com.code.line.system.entity.TSprint;
import com.code.line.system.entity.TSprintTemplateActionListEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Sprint模版action执行列表 服务类
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-07-11
 */
public interface ITSprintTemplateActionListService extends IService<TSprintTemplateActionListEntity> {

    List<TSprintTemplateActionListEntity> getByTemplateIdAndEnvStatus(Long SprintTemplateId,String SprintEnvStatus);


}
