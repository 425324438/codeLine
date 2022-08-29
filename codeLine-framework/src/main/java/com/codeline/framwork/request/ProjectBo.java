package com.codeline.framwork.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: syl
 * @Date: 2022/7/4 23:20
 * @Description:
 */
@Data
public class ProjectBo  {


    @ApiModelProperty(value = "项目名称")
    private String name;

    @ApiModelProperty(value = "项目git地址")
    private String gitUrl;

    @ApiModelProperty("git仓库类型，可选值：gitlab,github,gitee")
    private String gitStorageType;

    @ApiModelProperty(value = "项目所属的组")
    private String group;

}
