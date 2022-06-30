package com.code.line.domain.controller;

import com.code.line.system.entity.TProject;
import com.code.line.system.service.ITProjectService;
import com.codeline.framwork.response.ResultApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: syl
 * @Date: 2022/6/30 23:44
 * @Description:
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ITProjectService projectService;

    /**
     * 新增项目
     * @return
     */
    @PostMapping()
    public ResultApi saveProject(){

        return null;
    }

    @GetMapping("#{id}")
    public ResultApi<TProject> getById(@PathVariable("id") Long id){
        TProject project = projectService.getById(id);
        return ResultApi.success(project,"成功");
    }

    @GetMapping("#{group}")
    public ResultApi<List<TProject>> getByGroup(@PathVariable("group") String group){
        List<TProject> projectList = projectService.getByGroup(group);
        return ResultApi.success(projectList,"成功");
    }


}
