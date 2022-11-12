package com.code.line.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.code.line.system.entity.TSprint;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codeline.framwork.request.CreateSprintBo;
import com.codeline.framwork.request.search.SprintSearch;
import com.codeline.framwork.response.ApiResult;
import com.codeline.framwork.response.SprintVo;

import java.util.List;

/**
 * <p>
 * 迭代列表 服务类
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-06-28
 */
public interface ITSprintService extends IService<TSprint> {

    ApiResult create( CreateSprintBo createSprintBo);


    ApiResult<SprintVo> getSprintDetail(Long id);

    ApiResult<List<TSprint>> getSprintListPage( SprintSearch sprintSearch);

}
