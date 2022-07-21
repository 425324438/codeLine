package com.code.line.domain.controller;

import com.code.line.system.service.ISysConfigService;
import com.codeline.framwork.request.BaseConfigBo;
import com.codeline.framwork.response.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: syl
 * @Date: 2022/7/4 23:53
 * @Description:
 */
@Api(tags = "系统配置")
@RestController
@RequestMapping("/sys/config")
public class SysConfigController {

    @Autowired
    private ISysConfigService configService;

    /**
     * gitLab新增基础配置
     * @param baseConfigBo
     * @return
     */
    @PostMapping("/saveBaseConfig")
    @ApiOperation("gitLab新增基础配置")
    public ApiResult saveBaseConfig(@RequestBody BaseConfigBo baseConfigBo){
        return configService.saveBaseConfig(baseConfigBo);
    }

    @GetMapping("/getGitLabBaseConfig")
    @ApiOperation("查询gitLab配置")
    public ApiResult<BaseConfigBo> getGitLabBaseConfig(){
        return configService.getGitLabBaseConfig();
    }

    @PutMapping("/save")
    @ApiOperation("保存任意配置")
    public ApiResult save(@RequestBody Map<String,String> config){
        return ApiResult.result(configService.saveConfig(config));
    }
}
