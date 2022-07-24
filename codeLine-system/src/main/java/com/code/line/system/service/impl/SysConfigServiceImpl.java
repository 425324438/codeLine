package com.code.line.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.code.line.system.constant.DbStatus;
import com.code.line.system.entity.SysConfig;
import com.code.line.system.mapper.SysConfigMapper;
import com.code.line.system.service.ISysConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codeline.framwork.constant.GitLabConstant;
import com.codeline.framwork.constant.TypeConstants;
import com.codeline.framwork.request.BaseConfigBo;
import com.codeline.framwork.response.ApiResult;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 系统配置表 服务实现类
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-06-28
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {



    /**
     * 初始化gitLab配置
     * @param baseConfigBo
     * @return
     */
    @Override
    public ApiResult saveBaseConfig(BaseConfigBo baseConfigBo) {

        HashMap<String, String> config = new HashMap<>();
        config.put(GitLabConstant.GITLAB_TOKEN,baseConfigBo.getToken());
        config.put(GitLabConstant.GITLAB_URL,baseConfigBo.getUrl());
        return ApiResult.result(saveConfig(config));
    }

    @Override
    public ApiResult<BaseConfigBo> getGitLabBaseConfig() {
        LambdaQueryWrapper<SysConfig> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(SysConfig::getKeyStr, Arrays.asList(GitLabConstant.GITLAB_TOKEN,GitLabConstant.GITLAB_URL));
        queryWrapper.eq(SysConfig::getStatus, DbStatus.DEFAULT.getCode());
        List<SysConfig> list = list(queryWrapper);
        if (CollectionUtil.isEmpty(list)){
            return ApiResult.error("没有查询到gitLab的配置");
        }
        BaseConfigBo configBo = new BaseConfigBo();
        Optional<SysConfig> firstToken = list.stream().filter(x -> GitLabConstant.GITLAB_TOKEN.equals(x.getKeyStr()))
                .findFirst();
        Optional<SysConfig> firstUrl = list.stream().filter(x -> GitLabConstant.GITLAB_URL.equals(x.getKeyStr()))
                .findFirst();
        if (firstToken.isPresent()){
            configBo.setToken(firstToken.get().getValueStr());
        }
        if (firstUrl.isPresent()){
            configBo.setToken(firstUrl.get().getValueStr());
        }
        return ApiResult.success(configBo,"成功");
    }

    @Override
    public boolean saveConfig( Map<String,String> config){
        List<SysConfig> saveConfig = new LinkedList<>();
        for (Map.Entry<String, String> entry : config.entrySet()) {
            SysConfig sysConfig = new SysConfig();
            sysConfig.setKeyStr(entry.getKey());
            sysConfig.setValueStr(entry.getValue());
            saveConfig.add(sysConfig);
        }
        boolean batch = saveBatch(saveConfig);
        if (batch){
            return true;
        }
        return false;
    }

    @Override
    public String getMainBranchName() {
        LambdaQueryWrapper<SysConfig> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysConfig::getKeyStr,TypeConstants.SprintConfigKey.mainBranchName);
        queryWrapper.eq(SysConfig::getStatus,DbStatus.DEFAULT.getCode());
        return getOne(queryWrapper).getValueStr();
    }

    @Override
    public Long getAssigneeId() {
        LambdaQueryWrapper<SysConfig> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysConfig::getKeyStr,TypeConstants.SprintConfigKey.AssigneeId);
        queryWrapper.eq(SysConfig::getStatus,DbStatus.DEFAULT.getCode());
        String valueStr = getOne(queryWrapper).getValueStr();
        return Long.parseLong(valueStr);
    }

    @Override
    public String getHookCallbackUrl() {
        LambdaQueryWrapper<SysConfig> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysConfig::getKeyStr,TypeConstants.SprintConfigKey.HookCallBackUrl);
        queryWrapper.eq(SysConfig::getStatus,DbStatus.DEFAULT.getCode());
        return getOne(queryWrapper).getValueStr();
    }
}
