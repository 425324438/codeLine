package com.codeline.framwork.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: syl
 * @Date: 2022/7/4 23:32
 * @Description:
 */
@Data
public class UpdateProjectBo {


    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "项目git地址")
    private String gitUrl;

    @ApiModelProperty(value = "项目所属的组")
    private String group;


}
