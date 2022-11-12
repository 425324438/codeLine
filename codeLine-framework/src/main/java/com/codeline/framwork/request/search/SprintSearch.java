package com.codeline.framwork.request.search;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: syl
 * @Date: 2022/7/5 23:59
 * @Description:
 */
@Data
public class SprintSearch {

    @ApiModelProperty("模糊查询名称")
    private String likeName;

    @ApiModelProperty("模糊查询版本")
    private String likeVersion;

}
