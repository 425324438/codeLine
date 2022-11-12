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
    PUBLISHED("已发布"),
    DISCARD("废弃"),

    ;
    private String envName;

    SprintEnvStatusEnums(String envName) {
        this.envName = envName;
    }

    public String getEnvName() {
        return envName;
    }

    public static  SprintEnvStatusEnums getByEnv(String envStatus){
        if (envStatus == null){
            return null;
        }
        for (SprintEnvStatusEnums value : SprintEnvStatusEnums.values()) {
            if (value.name().equals(envStatus)){
                return value;
            }
        }
        return null;
    }
}
