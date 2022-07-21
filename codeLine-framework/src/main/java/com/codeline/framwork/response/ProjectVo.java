package com.codeline.framwork.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: syl
 * @Date: 2022/7/21 23:51
 * @Description:
 */
@Data
public class ProjectVo {


    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "项目名称")
    private String name;

    @ApiModelProperty("git仓库类型，可选值：gitlab,github,gitee")
    private String gitStorageType;

    @ApiModelProperty(value = "项目git地址")
    private String gitUrl;

    @ApiModelProperty(value = "项目所属的组")
    private String group;

    @ApiModelProperty(value = "创建者")
    private String creator;

    @ApiModelProperty(value = "创建人id")
    private Long creatorId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "修改者")
    private String modifier;

    @ApiModelProperty(value = "修改人id")
    private Long modifierId;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifiedTime;

    @ApiModelProperty(value = "状态 0:无效,1:有效")
    private Integer status;

}
