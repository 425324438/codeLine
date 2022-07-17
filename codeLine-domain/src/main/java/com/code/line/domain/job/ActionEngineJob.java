package com.code.line.domain.job;

import com.code.line.core.action.ActionEngine;
import com.code.line.system.entity.TSprintActionListEntity;
import com.code.line.system.service.ITSprintActionListService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author: syl
 * @Date: 2022/7/16 00:38
 * @Description: https://zhuanlan.zhihu.com/p/417152781
 */
@Slf4j
@Component
public class ActionEngineJob extends QuartzJobBean {

    @Autowired
    private ActionEngine actionEngine;
    @Autowired
    private ITSprintActionListService sprintActionListService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("ActionEngineJob ------ start -- ");
        TSprintActionListEntity sprintActionList = sprintActionListService.pollNextAction();
        while (sprintActionList != null){
            actionEngine.execute(sprintActionList.getId());
            sprintActionList = sprintActionListService.pollNextAction();
        }
        log.info("ActionEngineJob ------ end -- ");
    }
}
