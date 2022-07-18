package com.code.line.domain.controller;

import com.code.line.system.service.ITSprintActionListService;
import com.codeline.framwork.response.SprintActionListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: syl
 * @Date: 2022/7/19 00:14
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/sprint/action")
public class SprintActionController {

    @Autowired
    private ITSprintActionListService sprintActionListService;

    /*
    1.根据Sprint查询Action列表，以及Action的执行情况
    2.执行某一个Action（将当前Action的状态=激活，由定时任务拉起并执行）
    3.推动Sprint状态进行到下一个节点
     */

    /**
     * 1.根据Sprint查询Action列表，以及Action的执行情况
     * @param sprintId
     */
    @GetMapping("/getActionList/{sprintId}")
    public SprintActionListVo getActionList(@PathVariable("sprintId") Long sprintId){

        return null;
    }
}
