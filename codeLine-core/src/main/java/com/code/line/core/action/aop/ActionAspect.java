package com.code.line.core.action.aop;


import com.code.line.core.action.SprintContext;
import com.code.line.system.constant.ActionBeanTypeName;
import com.code.line.system.constant.DbStatus;
import com.code.line.system.entity.TActionLogEntity;
import com.code.line.system.entity.TSprint;
import com.code.line.system.entity.TSprintActionListEntity;
import com.code.line.system.entity.TSprintProject;
import com.code.line.system.service.ITActionLogService;
import com.code.line.system.service.ITSprintActionListService;
import com.code.line.system.service.ITSprintProjectService;
import com.code.line.system.service.ITSprintService;
import com.codeline.framwork.constant.ActionStatusEnums;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author: syl
 * @Date: 2022/7/19 00:19
 * @Description:
 */
@Slf4j
@Aspect
@Component
public class ActionAspect {

    @Autowired
    private ITSprintActionListService actionListService;
    @Autowired
    private ITSprintService sprintService;
    @Autowired
    private ITSprintProjectService sprintProjectService;
    @Autowired
    private ITActionLogService actionLogService;

    @Pointcut("execution (* com.code.line.core.action.bean.impl.*.execute(..))")
    public void pointcut(){}

    /**
     * 初始化 SprintContext
     * @param joinPoint
     */
    @Before(value = "pointcut()")
    public void before(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Long sprintActionId = (Long) args[0];
        log.info("初始化 SprintContext sprintActionId={}",sprintActionId);
        TSprintActionListEntity action = actionListService.getById(sprintActionId);
        TSprint sprint = sprintService.getById(action.getSprintId());
        List<TSprintProject> sprintProjectList = sprintProjectService.getBySprintId(action.getSprintId());
        SprintContext.set(sprint,sprintProjectList,action);
    }

    /**
     * Action执行完成后通知
     */
    @AfterReturning(pointcut = "pointcut()")
    public void afterReturningAdvice(JoinPoint joinPoint){
        TSprintActionListEntity sprintAction = SprintContext.get().getSprintAction();
        ActionStatusEnums statusEnums = ActionStatusEnums.getByName(sprintAction.getActionStatus());
        ActionBeanTypeName actionBeanType = ActionBeanTypeName.getByBeanCode(sprintAction.getActionBeanTypeName());

        TActionLogEntity tActionLogEntity = new TActionLogEntity();
        tActionLogEntity.setActionExeStatus(statusEnums.getStatusName());
        switch (statusEnums){
            case NotStarted:
            case activated:
            case executing:
                //咱不处理
                break;
            case failed:
                log.info("Action执行异常，id={}",sprintAction.getId());
                SprintContext.log("Action执行异常,");
                //记录到Action执行日志表中
                tActionLogEntity.setLog(SprintContext.get().getLog());
                break;
            case ended:
                //action执行完成，生成Action执行日志
                break;
            default:
                break;

        }
        tActionLogEntity.setLog(SprintContext.get().getLog());
        tActionLogEntity.setActionName(actionBeanType.getBeanName());
        tActionLogEntity.setSprintActionId(sprintAction.getId());
        tActionLogEntity.setCreatedTime(new Date());
        tActionLogEntity.setStatus(DbStatus.DEFAULT.getCode());
        actionLogService.save(tActionLogEntity);
    }


}
