package com.code.line.system.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 事件子任务表
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TEnventSubtaskConfig对象", description="事件子任务表")
@TableName(value = "t_envent_subtask_config")
public class TEnventSubtaskConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.ASSIGN_ID)
      private Long id;

    @ApiModelProperty(value = "事件")
    private String event;

    @ApiModelProperty(value = "系统内置的actitonKey")
    private String actionTask;

    @ApiModelProperty(value = "task名称")
    private String name;

    @ApiModelProperty(value = "说明")
    private String describe;

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
