package com.code.line.domain.controller;

import com.code.line.system.entity.TProject;
import com.code.line.system.service.ITProjectService;
import com.codeline.framwork.request.ProjectBo;
import com.codeline.framwork.request.UpdateProjectBo;
import com.codeline.framwork.response.ResultApi;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("新增项目")
    public ResultApi saveProject(@RequestBody ProjectBo projectBo){
        return projectService.save(projectBo);
    }

    @PutMapping()
    @ApiOperation("修改项目")
    public ResultApi editProject(@RequestBody UpdateProjectBo updateProjectBo){
        return projectService.editProject(updateProjectBo);
    }

    @GetMapping("#{id}")
    @ApiOperation("根据id查询")
    public ResultApi<TProject> getById(@PathVariable("id") Long id){
        TProject project = projectService.getById(id);
        return ResultApi.success(project,"成功");
    }

    @GetMapping("#{group}")
    @ApiOperation("根据应用group查询")
    public ResultApi<List<TProject>> getByGroup(@PathVariable("group") String group){
        List<TProject> projectList = projectService.getByGroup(group);
        return ResultApi.success(projectList,"成功");
    }


}