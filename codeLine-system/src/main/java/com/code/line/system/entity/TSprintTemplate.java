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
 * 迭代模版
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-06-28 template
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_sprint_template")
@ApiModel(value="TSprintTemplate对象", description="迭代模版")
public class TSprintTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.ASSIGN_ID)
      private Long id;

    @ApiModelProperty(value = "模版名")
    private String name;

    @ApiModelProperty(value = "模版类型 2常规迭代，1紧急迭代")
    private Integer type;

    @ApiModelProperty(value = "事件列表，逗号分隔")
    private String eventList;

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
