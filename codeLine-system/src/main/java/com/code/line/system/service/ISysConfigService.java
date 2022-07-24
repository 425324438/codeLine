package com.code.line.system.service;

import com.code.line.system.entity.SysConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codeline.framwork.request.BaseConfigBo;
import com.codeline.framwork.response.ApiResult;

import java.util.Map;

/**
 * <p>
 * 系统配置表 服务类
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-06-28
 */
public interface ISysConfigService extends IService<SysConfig> {

    ApiResult saveBaseConfig(BaseConfigBo baseConfigBo);

    ApiResult<BaseConfigBo> getGitLabBaseConfig();

    boolean saveConfig( Map<String,String> config);

    /**
     * 系统配置：代码主干分支名次 （例：master/main）
     * @return
     */
    String getMainBranchName();

    /**
     * 系统配置的git管理员userId
     * @return
     */
    Long getAssigneeId();

    String getHookCallbackUrl();
}
