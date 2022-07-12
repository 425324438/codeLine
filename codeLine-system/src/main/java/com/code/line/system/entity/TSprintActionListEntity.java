package com.code.line.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * Sprint的执行任务列表
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-07-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_sprint_action_list")
@ApiModel(value="TSprintActionListEntity对象", description="Sprint的执行任务列表")
public class TSprintActionListEntity extends Model<TSprintActionListEntity> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private Long sprintId;

    @ApiModelProperty(value = "SprintEnvStatusEnums 这里指Spint环境阶段信息")
    private String sprintEnvStatus;

    @ApiModelProperty(value = "ActionStatusEnums 默认=未开始，枚举值：未开始，激活，执行中，执行失败，已结束")
    private String actionStatus;

    @ApiModelProperty(value = "程序中Action中的type属性，代表了需要执行的Action")
    private String actionBeanTypeName;

    private String name;

    @ApiModelProperty("outer:调用外部接口，inner 内部Action")
    private String type;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "执行后的返回数据")
    private String exeResult;

    @ApiModelProperty(value = "执行序号")
    private Integer sortNo;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "状态 0:无效,1:有效")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
