package com.code.line.system.action;

import com.code.line.system.entity.TSprint;
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

    private TSprint sprint;

    private List<TSprintProject> sprintProject;


}
