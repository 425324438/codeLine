package com.code.line.system.service;

import com.code.line.system.entity.TSprintProject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 迭代内的项目列表 服务类
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-06-28
 */
public interface ITSprintProjectService extends IService<TSprintProject> {

    List<TSprintProject> getBySprintId(Long sprintId);
}
