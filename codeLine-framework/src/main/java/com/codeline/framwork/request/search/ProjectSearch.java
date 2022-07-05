package com.codeline.framwork.request.search;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: syl
 * @Date: 2022/7/5 23:59
 * @Description:
 */
@Data
public class ProjectSearch {

    @ApiModelProperty("group")
    private String group;

    @ApiModelProperty("项目名称")
    private String name;

    @ApiModelProperty("根据git地址模糊查询")
    private String likeGitUrl;
}
