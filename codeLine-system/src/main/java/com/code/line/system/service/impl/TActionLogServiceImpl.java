package com.code.line.system.service.impl;

import com.code.line.system.entity.TActionLogEntity;
import com.code.line.system.mapper.TActionLogMapper;
import com.code.line.system.service.ITActionLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * action执行日志 服务实现类
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-07-24
 */
@Service
public class TActionLogServiceImpl extends ServiceImpl<TActionLogMapper, TActionLogEntity> implements ITActionLogService {

}
