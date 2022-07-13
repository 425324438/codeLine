package com.code.line.system.action;

import com.code.line.system.entity.TSprint;
import com.code.line.system.entity.TSprintActionListEntity;
import com.code.line.system.entity.TSprintProject;
import lombok.Data;

import java.util.List;

/**
 * @author: syl
 * @Date: 2022/7/13 01:21
 * @Description:
 */
@Data
public class SprintContext {

    private static final ThreadLocal<SprintContext> threadLocal = ThreadLocal.withInitial(() -> new SprintContext());

    private TSprint sprint;
    /**
     * 当前Sprint中的项目列表
     */
    private List<TSprintProject> sprintProjectList;
    /**
     * 当前正在执行的action
     */
    private TSprintActionListEntity sprintAction;

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

    public static void remove(){
        threadLocal.remove();
    }



}
