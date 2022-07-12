package com.code.line.system.constant;

/**
 * @author: syl
 * @Date: 2022/7/12 23:53
 * @Description: outer:调用外部接口，inner 内部Action
 */
public enum ActionTypeEnums {
    inner("内部"),
    outer("外部"),
    ;
    private String doc;

    public String getDoc() {
        return doc;
    }

    ActionTypeEnums(String doc) {
        this.doc = doc;
    }
}
