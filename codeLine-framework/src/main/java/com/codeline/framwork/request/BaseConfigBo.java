package com.codeline.framwork.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: syl
 * @Date: 2022/7/4 23:55
 * @Description:
 */
@Data
public class BaseConfigBo {

    @ApiModelProperty("gitLab获取的appToken")
    public String token;
    @ApiModelProperty("gitLab地址")
    public String url;
}
