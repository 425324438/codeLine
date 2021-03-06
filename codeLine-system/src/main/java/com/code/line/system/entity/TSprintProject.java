package com.code.line.system.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 迭代内的项目列表
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_sprint_project")
@ApiModel(value="TSprintProject对象", description="迭代内的项目列表")
public class TSprintProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.ASSIGN_ID)
      private Long id;

    @ApiModelProperty(value = "迭代id")
    private Long sprintId;

    @ApiModelProperty(value = "项目id")
    private Long projectId;

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
