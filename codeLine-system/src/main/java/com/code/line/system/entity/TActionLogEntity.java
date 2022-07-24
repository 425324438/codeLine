package com.code.line.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * action执行日志
 * </p>
 *
 * @author 暮色听雨
 * @since 2022-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_action_log")
@ApiModel(value="TActionLogEntity对象", description="action执行日志")
public class TActionLogEntity extends Model<TActionLogEntity> {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private Long sprintId;

    private Long sprintActionId;

    private String actionName;

    @ApiModelProperty(value = "action执行状态，页面展示")
    private String actionExeStatus;

    private String log;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "状态 0:无效,1:有效")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
