package com.codeline.framwork.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.codeline.framwork.constant.SprintTypeEnums;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: syl
 * @Date: 2022/7/21 23:50
 * @Description:
 */
@Data
public class SprintVo {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty("Sprint名称")
    private String name;

    @ApiModelProperty("迭代类型")
    private SprintTypeEnums sprintType;

    @ApiModelProperty("迭代版本号，后端会自动拼接日期（格式=yyyyMMdd）")
    private String version;

    @ApiModelProperty("迭代项目id集合")
    private List<SprintProjectVo> projects;
}
