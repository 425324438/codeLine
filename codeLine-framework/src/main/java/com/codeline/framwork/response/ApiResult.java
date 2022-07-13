package com.codeline.framwork.response;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: syl
 * @Date: 2022/6/30 23:45
 * @Description:
 */
@Data
public class ApiResult<T> {

    private Integer code;

    private String msg;

    private T data;

    private static int success = 0;
    private static int error = -1;

    public boolean isErr(){
        return code.equals(error);
    }

    public boolean isSuccess(){
        return code.equals(success);
    }

    public static ApiResult result(boolean res){
        if (res){
            return ApiResult.success();
        }
        return ApiResult.error("失败");
    }

    public static ApiResult success(){
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(success);
        apiResult.setMsg("成功");
        return apiResult;
    }
    public static ApiResult success(String msg){
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(success);
        apiResult.setMsg(StringUtils.isBlank(msg) ? "成功" :msg);
        return apiResult;
    }

    public static <T> ApiResult<T> success(T data,String msg){
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(success);
        apiResult.setMsg(StringUtils.isBlank(msg) ? "成功" :msg);
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiResult error(String msg){
        ApiResult<String> apiResult = new ApiResult<>();
        apiResult.setCode(error);
        apiResult.setMsg(msg);
        return apiResult;
    }
}
