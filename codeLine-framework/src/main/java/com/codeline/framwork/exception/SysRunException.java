package com.codeline.framwork.exception;

/**
 * @author: syl
 * @Date: 2022/6/23 00:56
 * @Description: 业务执行异常
 */
public class SysRunException extends Exception{

    public SysRunException(String message,Throwable t) {
        super(message,t);
    }
}
