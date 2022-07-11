package com.codeline.framwork.constant;

/**
 * @author: syl
 * @Date: 2022/7/11 23:01
 * @Description: Sprint 迭代环境信息枚举
 */
public enum SprintEnvStatusEnums {
    DEV("开发环境"),
    TEST("测试环境"),
    PRE("预发环境"),
    PRD("生产环境"),
    ;
    private String name;

    SprintEnvStatusEnums(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
