package com.codeline.framwork.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author: syl
 * @Date: 2022/7/21 23:42
 * @Description:
 */
@Data
public class ActionVo {
    private Long id;

    @ApiModelProperty(value = "ActionStatusEnums 默认=未开始，枚举值：未开始，激活，执行中，执行失败，已结束")
    private String actionStatus;

    @ApiModelProperty(value = "ActionBeanTypeName 程序中Action中的type属性，代表了需要执行的Action")
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
}
