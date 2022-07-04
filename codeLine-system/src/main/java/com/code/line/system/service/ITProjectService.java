package com.code.line.system.service;

import com.code.line.system.entity.TProject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codeline.framwork.request.ProjectBo;
import com.codeline.framwork.request.UpdateProjectBo;
import com.codeline.framwork.response.ResultApi;

import java.util.List;

/**
 * <p>
 * 项目列表 服务类
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-06-30
 */
public interface ITProjectService extends IService<TProject> {

    List<TProject> getByGroup( String group);

    ResultApi save(ProjectBo projectBo);

    ResultApi editProject(UpdateProjectBo updateProjectBo);

}
