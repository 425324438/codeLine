package com.codeline.framwork.request;

import com.codeline.framwork.constant.SprintTypeEnums;
import javax.validation.constraints.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: syl
 * @Date: 2022/7/10 22:44
 * @Description:
 */
@Data
public class CreateSprintBo {

    @ApiModelProperty("Sprint名称")
    @NotNull
    private String name;

    @ApiModelProperty("迭代类型")
    private SprintTypeEnums sprintTypeEnums;

    @ApiModelProperty("迭代版本号，后端会自动拼接日期（格式=yyyyMMdd）")
    private String sprintVersion;

    @ApiModelProperty("迭代项目id集合")
    private List<Long> projectIds;

}
