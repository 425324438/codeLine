package com.codeline.framwork.response;

import lombok.Data;

/**
 * @author: syl
 * @Date: 2022/6/30 23:45
 * @Description:
 */
@Data
public class ResultApi<T> {

    private Integer code;

    private String msg;

    private T data;

    private static int success = 0;
    private static int error = -1;

    public static ResultApi success(){
        ResultApi resultApi = new ResultApi();
        resultApi.setCode(success);
        resultApi.setMsg("成功");
        return resultApi;
    }
    public static ResultApi success(String msg){
        ResultApi resultApi = new ResultApi();
        resultApi.setCode(success);
        resultApi.setMsg(msg);
        return resultApi;
    }

    public static <T> ResultApi<T> success(T data,String msg){
        ResultApi<T> resultApi = new ResultApi<>();
        resultApi.setCode(success);
        resultApi.setMsg(msg);
        resultApi.setData(data);
        return resultApi;
    }

    public static  ResultApi error(String msg){
        ResultApi resultApi = new ResultApi<>();
        resultApi.setCode(error);
        resultApi.setMsg(msg);
        return resultApi;
    }
}
