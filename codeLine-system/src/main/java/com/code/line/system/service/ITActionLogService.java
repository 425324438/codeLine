package com.code.line.system.service;

import com.code.line.system.entity.TActionLogEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * action执行日志 服务类
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-07-24
 */
public interface ITActionLogService extends IService<TActionLogEntity> {

    /**
     * 根据SprintId 查询action执行日志
     * @param sprintId
     * @return
     */
    List<TActionLogEntity> listActionLogBySprintId(Long sprintId);

}
