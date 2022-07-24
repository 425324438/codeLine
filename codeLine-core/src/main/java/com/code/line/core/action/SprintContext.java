package com.code.line.core.action;

import com.code.line.system.entity.TSprint;
import com.code.line.system.entity.TSprintActionListEntity;
import com.code.line.system.entity.TSprintProject;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author: syl
 * @Date: 2022/7/13 01:21
 * @Description:
 */
@Data
public class SprintContext {

    private static final ThreadLocal<SprintContext> threadLocal = new ThreadLocal<>();

    private TSprint sprint;
    /**
     * 当前Sprint中的项目列表
     */
    private List<TSprintProject> sprintProjectList;
    /**
     * 当前正在执行的action
     */
    private TSprintActionListEntity sprintAction;

    /**
     * Action 执行日志
     */
    private String log;

    public static SprintContext get(){
        return threadLocal.get();
    }

    public static void set(TSprint sprint,List<TSprintProject> sprintProject,TSprintActionListEntity sprintAction){
        SprintContext context = new SprintContext();
        context.setSprint(sprint);
        context.setSprintProjectList(sprintProject);
        context.setSprintAction(sprintAction);
        threadLocal.set(context);
    }

    public static void set(TSprintActionListEntity sprintAction){
        SprintContext context = threadLocal.get();
        context.setSprintAction(sprintAction);
        threadLocal.set(context);
    }

    public static void set(TSprint sprint){
        SprintContext context = threadLocal.get();
        context.setSprint(sprint);
        threadLocal.set(context);
    }
    public static void set(TSprint sprint,TSprintActionListEntity sprintAction){
        SprintContext context = threadLocal.get();
        context.setSprint(sprint);
        context.setSprintAction(sprintAction);
        threadLocal.set(context);
    }

    public static void log(String log){
        SprintContext context = threadLocal.get();
        String contextLog = context.getLog();
        if (StringUtils.isBlank(context.getLog())){
            contextLog = "";
        }
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append(contextLog).append(log).append("<br/>");
        context.setLog(logBuilder.toString());
    }


    public static void remove(){
        threadLocal.remove();
    }



}
