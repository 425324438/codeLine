package com.code.line.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * Sprint模版action执行列表
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-07-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_sprint_template_action_list")
@ApiModel(value="TSprintTemplateActionListEntity对象", description="Sprint模版action执行列表")
public class TSprintTemplateActionListEntity extends Model<TSprintTemplateActionListEntity> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private Long sprintTemplateId;

    @ApiModelProperty(value = "SprintEnvStatusEnums 这里指Spint环境阶段信息")
    private String sprintEnvStatus;

    @ApiModelProperty(value = "程序中Action中的type属性，代表了需要执行的Action")
    private String actionBeanTypeName;

    private String name;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "执行序号")
    private Integer sortNo;

    @ApiModelProperty(value = "状态 0:无效,1:有效")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
