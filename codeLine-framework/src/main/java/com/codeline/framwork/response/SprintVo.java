package com.codeline.framwork.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.codeline.framwork.constant.SprintTypeEnums;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: syl
 * @Date: 2022/7/21 23:50
 * @Description:
 */
@Data
public class SprintVo {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty("Sprint名称")
    private String name;

    @ApiModelProperty("迭代类型")
    private String sprintType;

    @ApiModelProperty(value = "迭代模版id")
    private Long sprintTemplateId;

    @ApiModelProperty("迭代版本号，后端会自动拼接日期（格式=yyyyMMdd）")
    private String version;

    @ApiModelProperty(value = "0无异常，1 action执行有异常")
    private int hasError;

    @ApiModelProperty(value = "创建者")
    private String creator;

    @ApiModelProperty(value = "修改时间")
    @FormDataParam(value = "")
    private LocalDateTime modifiedTime;

    @ApiModelProperty("迭代项目id集合")
    private List<SprintProjectVo> projects;
}
