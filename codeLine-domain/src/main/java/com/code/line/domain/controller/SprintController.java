package com.code.line.domain.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.code.line.system.entity.TSprint;
import com.code.line.system.service.ITActionLogService;
import com.code.line.system.service.ITSprintService;
import com.codeline.framwork.request.CreateSprintBo;
import com.codeline.framwork.request.search.SprintSearch;
import com.codeline.framwork.response.ApiResult;
import com.codeline.framwork.response.SprintVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

/**
 * @author: syl
 * @Date: 2022/7/5 23:52
 * @Description:
 */
@Api(tags = "sprint")
@RestController
@RequestMapping("/sprint")
public class SprintController {

    @Autowired
    private ITSprintService sprintService;


    /**
     * 创建Sprint
     */
    @PutMapping()
    @ApiModelProperty("创建Sprint")
    public ApiResult create(@RequestBody @Validated CreateSprintBo createSprintBo){
        return sprintService.create(createSprintBo);
    }


    /**
     * 查询Sprint详情
     * @return
     */
    @GetMapping("{id}")
    @ApiModelProperty("查询Sprint详情")
    public ApiResult<SprintVo> getSprintDetail(@PathVariable("id") Long id){
        return sprintService.getSprintDetail(id);
    }

    /**
     * 查询Sprint列表
     */
    @GetMapping("/page")
    @ApiModelProperty("查询Sprint列表")
    public ApiResult<Page<TSprint>> getSprintListPage(@RequestBody SprintSearch sprintSearch){
        return sprintService.getSprintListPage(sprintSearch);
    }


    /**
     * Sprint 状态向下个节点推进
     */
    @PostMapping
    @ApiModelProperty("Sprint 状态向下个节点推进")
    public ApiResult pushStageNext(){
        return null;
    }

}
