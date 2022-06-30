package com.code.line.system.constant;

/**
 * @author: syl
 * @Date: 2022/7/1 00:13
 * @Description:
 */
public enum DbStatus {
    DEFAULT(1),
    DELETE(-1),
    ;
    private int code;

    DbStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
