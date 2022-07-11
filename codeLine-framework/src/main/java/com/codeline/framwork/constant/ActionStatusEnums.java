package com.codeline.framwork.constant;

/**
 * @author: syl
 * @Date: 2022/7/11 22:53
 * @Description: 未开始，激活，执行中，执行失败，已结束
 */
public enum ActionStatusEnums {

    /**
     * 默认状态
     */
    NotStarted("未开始"),
    /**
     * 激活状态说明action可以被执行
     */
    activated("激活"),
    executing("执行中"),
    failed("执行失败"),
    ended("已结束"),
    ;

    private String name;

    ActionStatusEnums(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
