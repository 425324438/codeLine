package com.code.line.system.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.codeline.framwork.constant.SprintTypeEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 迭代列表
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_sprint")
@ApiModel(value="TSprint对象", description="迭代列表")
public class TSprint implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.ASSIGN_ID)
      private Long id;

    @ApiModelProperty(value = "迭代名称")
    private String name;

    @ApiModelProperty(value = "迭代模版id")
    private Long sprintTempletId;

    @ApiModelProperty("迭代类型")
    private String sprintType;

    @ApiModelProperty(value = "版本号")
    private String version;

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
