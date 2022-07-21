package com.code.line.domain.controller;

import com.code.line.system.service.ITSprintActionListService;
import com.codeline.framwork.response.ApiResult;
import com.codeline.framwork.response.SprintActionListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: syl
 * @Date: 2022/7/19 00:14
 * @Description:
 */
@Slf4j
@Api(tags = "action")
@RestController
@RequestMapping("/sprint/action")
public class SprintActionController {

    @Autowired
    private ITSprintActionListService sprintActionListService;

    /**
     * 1.根据Sprint查询Action列表，以及Action的执行情况
     * @param sprintId
     */
    @GetMapping("{sprintId}")
    @ApiOperation(value = "根据SprintId查询Action列表，以及Action的执行情况")
    public ApiResult<SprintActionListVo> getActionList(@PathVariable("sprintId") Long sprintId){

        return null;
    }

    @PutMapping("/executeAction/{actionId}")
    @ApiOperation(value = "执行某一个Action（将当前Action的状态=激活，由定时任务拉起并执行）")
    public ApiResult executeAction(@PathVariable("actionId") Long id){

        return null;
    }

}
