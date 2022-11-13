package com.codeline.framwork.response;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: syl
 * @Date: 2022/7/21 23:54
 * @Description:
 */
@Data
public class SprintProjectVo {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "迭代id")
    private String sprintId;

    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "项目git地址")
    private String gitUrl;

    @ApiModelProperty(value = "项目名称")
    private String name;

    @ApiModelProperty(value = "分支名称")
    private String branch;

    @ApiModelProperty(value = "页面访问地址")
    private String webUrl;

    @ApiModelProperty(value = "action执行过程需要的数据,冗余字段")
    private JSONObject paramJson;

    @ApiModelProperty(value = "创建者")
    private String creator;

    @ApiModelProperty(value = "创建人id")
    private String creatorId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "修改者")
    private String modifier;

    @ApiModelProperty(value = "修改人id")
    private String modifierId;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifiedTime;

    @ApiModelProperty(value = "状态 0:无效,1:有效")
    private Integer status;
}
