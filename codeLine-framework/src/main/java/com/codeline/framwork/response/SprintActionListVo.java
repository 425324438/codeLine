package com.codeline.framwork.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: syl
 * @Date: 2022/7/19 00:54
 * @Description: 根据Sprint查询Action列表，以及Action的执行情况
 */
@Data
public class SprintActionListVo {

    private Long sprintId;

    @ApiModelProperty(value = "key=sprint的阶段，val=Action列表，以及Action的执行情况")
    private Map<String,List<ActionVo>> sprintActionListMap;

}
