package com.code.line.domain.controller;

import com.code.line.system.service.ITSprintService;
import com.codeline.framwork.request.CreateSprintBo;
import com.codeline.framwork.request.search.SprintSearch;
import com.codeline.framwork.response.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

/**
 * @author: syl
 * @Date: 2022/7/5 23:52
 * @Description:
 */
@RestController
@RequestMapping("/sprint")
public class SprintController {

    @Autowired
    private ITSprintService sprintService;

    /**
     * 创建Sprint
     */
    @PutMapping()
    public ApiResult create(@RequestBody @Validated CreateSprintBo createSprintBo){
        return sprintService.create(createSprintBo);
    }


    /**
     * 查询Sprint详情
     * @return
     */
    @GetMapping("{id}")
    public ApiResult getSprintDetail(@PathVariable("id") Long id){
        return null;
    }

    /**
     * 查询Sprint列表
     */
    @GetMapping("/page")
    public ApiResult getSprintListPage(@RequestBody SprintSearch sprintSearch){
        return null;
    }


    /**
     * Sprint 状态向下个节点推进
     */
    @PostMapping
    public ApiResult stageNext(){
        return null;
    }

}
