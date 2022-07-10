package com.code.line.system.service.impl;

import com.code.line.system.entity.TSprint;
import com.code.line.system.mapper.TSprintMapper;
import com.code.line.system.service.ITSprintService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codeline.framwork.request.CreateSprintBo;
import com.codeline.framwork.response.ApiResult;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 迭代列表 服务实现类
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-06-28
 */
@Service
public class TSprintServiceImpl extends ServiceImpl<TSprintMapper, TSprint> implements ITSprintService {

    @Override
    public ApiResult create(CreateSprintBo createSprintBo) {
        return null;
    }
}
